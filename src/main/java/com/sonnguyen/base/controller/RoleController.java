package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.in.RoleDtoIn;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get all roles successfully")
                        .data(roleService.getAll())
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<?> createRole(@RequestBody RoleDtoIn roleDtoIn) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Create role successfully")
                        .data(roleService.create(roleDtoIn))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleDtoIn roleDtoIn) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Update role successfully")
                        .data(roleService.update(id, roleDtoIn))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Delete role successfully")
                        .build()
        );
    }
}

