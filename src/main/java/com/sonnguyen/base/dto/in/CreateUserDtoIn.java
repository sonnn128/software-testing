package com.sonnguyen.base.dto.in;

import lombok.Data;

import java.util.Set;

@Data
public class CreateUserDtoIn {
    private String username;
    private String password;
    private Set<String> roles;
}
