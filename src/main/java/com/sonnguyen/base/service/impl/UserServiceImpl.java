package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.in.*;
import com.sonnguyen.base.model.Role;
import com.sonnguyen.base.repository.RoleRepository;
import com.sonnguyen.base.dto.UserDto;
import com.sonnguyen.base.exception.BadRequestException;
import com.sonnguyen.base.exception.NotFoundException;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import com.sonnguyen.base.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Page<UserDto> getAllBySearchString(PageRequestDtoIn pageRequestDtoIn) {
        Sort sort = Sort.by(pageRequestDtoIn.getSortBy());
        if (pageRequestDtoIn.getOrder().equals("acs")) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageRequestDtoIn.getPage() - 1, pageRequestDtoIn.getSize(), sort);
        return userRepository.findAllByUsernameContaining(pageRequestDtoIn.getSearch(), pageable).map(UserDto::from);
    }

    @Override
    public UserDto getById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return UserDto.from(user);
    }

    @Override
    public void create(CreateUserDtoIn createUserDtoIn) {
        User user = new User();
        user.setUsername(createUserDtoIn.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDtoIn.getPassword()));
        user.setStatus(1);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            throw new NotFoundException("Role not found: USER");
        }
        roles.add(userRole);

        createUserDtoIn.getRoles().forEach(item -> {
            Role role = roleRepository.findByName(item);
            roles.add(role);
        });

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void changePassword(String userId, ChangePasswordDtoIn changePasswordDtoIn) {
        if (!changePasswordDtoIn.getNewPassword().equals(changePasswordDtoIn.getConfirmPassword())) {
            throw new BadRequestException("Password not match: newPassword, confirmPassword");
        }
        User user = userRepository.findByUsername(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (!passwordEncoder.matches(changePasswordDtoIn.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDtoIn.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void changePassword(AdminChangePasswordDtoIn changePasswordDtoIn) {
        User user = userRepository.findById(changePasswordDtoIn.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!changePasswordDtoIn.getNewPassword().equals(changePasswordDtoIn.getConfirmPassword())) {
            throw new BadRequestException("password not match: newPassword, confirmPassword");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDtoIn.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto update(String userId, UpdateUserDto userDtoIn) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setFullname(userDtoIn.getFullname());
        user.setPhoneNumber(userDtoIn.getPhoneNumber());
        user.setDescription(userDtoIn.getDescription());
        user.setEmail(userDtoIn.getEmail());
        userRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    public void delete(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto adminUpdate(String userId, AdminUpdateUserDtoIn adminUpdateUserDtoIn) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found: " + userId));
        if (adminUpdateUserDtoIn.getStatus() != null) {
            user.setStatus(adminUpdateUserDtoIn.getStatus());
        }
        if (adminUpdateUserDtoIn.getRoles() != null) {
            user.setRoles(Set.of((Role) adminUpdateUserDtoIn.getRoles().stream().map(roleRepository::findByName)));
        }
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

}
