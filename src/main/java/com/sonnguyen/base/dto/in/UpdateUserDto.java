package com.sonnguyen.base.dto.in;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String fullname;
    private String phoneNumber;
    private String description;
    private String email;
}
