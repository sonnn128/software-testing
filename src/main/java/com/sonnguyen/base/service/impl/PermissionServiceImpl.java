package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.PermissionDto;
import com.sonnguyen.base.repository.PermissionRepository;
import com.sonnguyen.base.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public List<PermissionDto> getAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionDto::from)
                .collect(Collectors.toList());
    }
}
