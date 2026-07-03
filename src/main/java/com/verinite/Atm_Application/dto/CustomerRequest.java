package com.verinite.Atm_Application.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String customerName;
    private String email;
    private String mobile;
}