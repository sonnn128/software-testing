package com.sonnguyen.base.dto;

import com.sonnguyen.base.model.Permission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionDto {
    private Integer id;
    private String name;
    private String description;

    public static PermissionDto from(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
