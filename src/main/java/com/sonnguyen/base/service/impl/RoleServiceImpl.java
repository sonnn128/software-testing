package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.RoleDto;
import com.sonnguyen.base.dto.in.RoleDtoIn;
import com.sonnguyen.base.exception.BadRequestException;
import com.sonnguyen.base.model.Permission;
import com.sonnguyen.base.model.Role;
import com.sonnguyen.base.repository.PermissionRepository;
import com.sonnguyen.base.repository.RoleRepository;
import com.sonnguyen.base.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public Set<RoleDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDto::from)
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDto create(RoleDtoIn roleDtoIn) {
        if (roleDtoIn.getName() == null || roleDtoIn.getName().isBlank()) {
            throw new BadRequestException("Name is null or empty: Name of role");
        }

        Role role = new Role();
        role.setName(roleDtoIn.getName());

        Set<Permission> permissions = roleDtoIn.getPermissionIds() != null
                ? roleDtoIn.getPermissionIds().stream()
                .map(permissionId -> permissionRepository.findById(Integer.valueOf(permissionId))
                        .orElseThrow(() -> new BadRequestException("Permission id not found: " + permissionId)))
                .collect(Collectors.toSet())
                : new HashSet<>();

        role.setPermissions(permissions);
        return RoleDto.from(roleRepository.save(role));
    }

    @Override
    public RoleDto update(Long roleId, RoleDtoIn roleDtoIn) {
        if (roleDtoIn.getName() == null || roleDtoIn.getName().isBlank()) {
            throw new BadRequestException("Name is null or empty: Name of role");
        }

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BadRequestException("Role not found with id: " + roleId));

        role.setName(roleDtoIn.getName());
        Set<Permission> permissions = roleDtoIn.getPermissionIds() != null
                ? roleDtoIn.getPermissionIds().stream()
                .map(permissionId -> permissionRepository.findById(Integer.valueOf(permissionId))
                        .orElseThrow(() -> new BadRequestException("Permission id not found: " + permissionId)))
                .collect(Collectors.toSet())
                : new HashSet<>();

        role.setPermissions(permissions);
        return RoleDto.from(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Role not found with id: " + id));

        roleRepository.delete(role);
    }
}
