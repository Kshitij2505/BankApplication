package com.verinite.Atm_Application.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String role;
}