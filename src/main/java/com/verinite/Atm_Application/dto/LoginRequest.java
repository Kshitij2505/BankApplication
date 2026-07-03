package com.verinite.Atm_Application.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}