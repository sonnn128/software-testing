package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.in.AdminChangePasswordDtoIn;
import com.sonnguyen.base.dto.in.AdminUpdateUserDtoIn;
import com.sonnguyen.base.dto.in.CreateUserDtoIn;
import com.sonnguyen.base.dto.in.PageRequestDtoIn;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CREATE_USER')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDtoIn createUserDtoIn) {
        userService.create(createUserDtoIn);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Create account successfully")
                        .build()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VIEW_USERS')")
    public ResponseEntity<?> getAllUsers(PageRequestDtoIn pageRequestDtoIn) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get all users successfully")
                        .data(new PagedModel<>(userService.getAllBySearchString(pageRequestDtoIn)))
                        .build()
        );
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'UPDATE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody AdminUpdateUserDtoIn dto) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Update account successfully")
                        .data(userService.adminUpdate(userId, dto))
                        .build()
        );
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DELETE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Delete account successfully")
                        .build()
        );
    }

    @PatchMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHANGE_USER_PASSWORD')")
    public ResponseEntity<?> changePassword(@RequestBody AdminChangePasswordDtoIn changePasswordDtoIn) {
        userService.changePassword(changePasswordDtoIn);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Change password successfully")
                        .build()
        );
    }
    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VIEW_USERS')")
//    internal api -> userId luôn
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get user by id successfully")
                        .data(userService.getById(userId))
                        .build()
        );
    }
}