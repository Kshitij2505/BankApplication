package com.verinite.Atm_Application.controller;

import com.verinite.Atm_Application.dto.LoginRequest;
import com.verinite.Atm_Application.dto.RegisterRequest;
import com.verinite.Atm_Application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}