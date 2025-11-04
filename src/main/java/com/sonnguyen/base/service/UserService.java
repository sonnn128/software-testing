package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.UserDto;
import com.sonnguyen.base.dto.in.*;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDto> getAllBySearchString(PageRequestDtoIn pageRequestDtoIn);
    UserDto getById(String userId);
    void create(CreateUserDtoIn createUserDtoIn);
    UserDto adminUpdate(String userId, AdminUpdateUserDtoIn adminUpdateUserDtoIn);
    UserDto update(String userId, UpdateUserDto userDtoIn);
    void delete(String userId);
    void changePassword(String userId, ChangePasswordDtoIn changePasswordDtoIn);
    void changePassword(AdminChangePasswordDtoIn changePasswordDtoIn);
}