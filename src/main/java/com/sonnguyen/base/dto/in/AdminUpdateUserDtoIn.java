package com.sonnguyen.base.dto.in;

import lombok.Data;

import java.util.Set;

@Data
public class AdminUpdateUserDtoIn {
    private Integer status;
    private Set<String> roles;
}