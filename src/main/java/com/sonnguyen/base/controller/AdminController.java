package com.sonnguyen.base.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String helloAdmin() {
        return "admin page";
    }

    @GetMapping("/public")
    String helloPublic() {
        return "Public page";
    }
}
