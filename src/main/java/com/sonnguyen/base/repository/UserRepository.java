package com.sonnguyen.base.repository;

import com.sonnguyen.base.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    Page<User> findAllByUsernameContaining(String search, Pageable pageable);
}