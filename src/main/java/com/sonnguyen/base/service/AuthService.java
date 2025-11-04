package com.sonnguyen.base.service;

import com.sonnguyen.base.model.User;
import com.sonnguyen.base.dto.res.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
    void register(String username, String password);
    boolean hasAnyRole(String... roles);
    User getCurrentUser();
}
