package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.RoleDto;
import com.sonnguyen.base.dto.in.RoleDtoIn;

import java.util.Set;

public interface RoleService {
    Set<RoleDto> getAll();
    RoleDto create(RoleDtoIn roleDtoIn);
    RoleDto update(Long roleId, RoleDtoIn roleDtoIn);
    void delete(Long id);
}
