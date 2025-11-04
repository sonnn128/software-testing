package com.sonnguyen.base.dto.in;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDtoIn {
    private String name;
    private Set<String> permissionIds;
}
