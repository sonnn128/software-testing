package com.sonnguyen.base.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminChangePasswordDtoIn {
    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "New password is required")
    private String newPassword;
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}
