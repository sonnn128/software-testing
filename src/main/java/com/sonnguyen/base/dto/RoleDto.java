package com.sonnguyen.base.dto;

import com.sonnguyen.base.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private Boolean isDefault;
    private Set<PermissionDto> permissions;

    public static RoleDto from(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .isDefault(role.getIsDefault())
                .permissions(role.getPermissions().stream()
                        .map(PermissionDto::from)
                        .collect(Collectors.toSet()))
                .build();
    }
}
