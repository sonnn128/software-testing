package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.UserDto;
import com.sonnguyen.base.dto.in.ChangePasswordDtoIn;
import com.sonnguyen.base.dto.in.UpdateUserDto;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/me")
public class MeController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getProfile() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get me successfully")
                        .data(UserDto.from(user))
                        .build()
        );
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDtoIn changePasswordDtoIn) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        userService.changePassword(username, changePasswordDtoIn);
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .success(true)
                        .message("Change password success!")
                        .build()
        );
    }

    @PatchMapping("")
    public ResponseEntity<?> update(@RequestBody UpdateUserDto dto) {
        String userId = authService.getCurrentUser().getId();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Update account successfully")
                        .data(userService.update(userId, dto))
                        .build()
        );
    }

}
