package com.sonnguyen.base.config.initializr;

import com.sonnguyen.base.model.Permission;
import com.sonnguyen.base.model.Role;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.PermissionRepository;
import com.sonnguyen.base.repository.RoleRepository;
import com.sonnguyen.base.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createUser();
    }

    private void createUser(){
        Role adminRole = roleRepository.findByName("ADMIN");
        Role userRole = roleRepository.findByName("USER");

        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole = roleRepository.save(adminRole);
        }

        if (userRole == null) {
            userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        User adminUser = userRepository.findByUsername("admin");

        if(adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setStatus(1);

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);

            userRepository.save(adminUser);
            log.info("User 'admin' created successfully.");
        }
    }
    public void createPermission() {
        // User permissions
        createPermissionIfNotExists("VIEW_USERS", "View all users");
        createPermissionIfNotExists("CREATE_USER", "Create new user");
        createPermissionIfNotExists("UPDATE_USER", "Update user information");
        createPermissionIfNotExists("DELETE_USER", "Delete user");
        createPermissionIfNotExists("CHANGE_USER_PASSWORD", "Change user password");

        // Role permissions
        createPermissionIfNotExists("VIEW_ROLES", "View all roles");
        createPermissionIfNotExists("CREATE_ROLE", "Create new role");
        createPermissionIfNotExists("UPDATE_ROLE", "Update role");
        createPermissionIfNotExists("DELETE_ROLE", "Delete role");
        createPermissionIfNotExists("ASSIGN_ROLE", "Assign role to user");

        // Permission permissions
        createPermissionIfNotExists("VIEW_PERMISSIONS", "View all permissions");
        createPermissionIfNotExists("CREATE_PERMISSION", "Create new permission");
        createPermissionIfNotExists("UPDATE_PERMISSION", "Update permission");
        createPermissionIfNotExists("DELETE_PERMISSION", "Delete permission");

    }

    private void createPermissionIfNotExists(String name, String description) {
        if (permissionRepository.findByName(name) == null) {
            Permission permission = new Permission();
            permission.setName(name);
            permission.setDescription(description);
            permissionRepository.save(permission);
        }
    }
}