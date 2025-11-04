package com.sonnguyen.base.dto;

import com.sonnguyen.base.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String description;
    private Integer status;
    private String createdAt;
    private String updatedAt;


    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .description(user.getDescription())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .updatedAt(user.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .build();
    }
}

