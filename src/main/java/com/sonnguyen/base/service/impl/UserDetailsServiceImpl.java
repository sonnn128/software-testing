package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.exception.CommonException;
import com.sonnguyen.base.model.CustomUserDetails;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        if(user.getStatus() == 0){
            throw new CommonException("User is disabled", HttpStatus.FORBIDDEN);
        }
        return new CustomUserDetails(user);
    }
}
