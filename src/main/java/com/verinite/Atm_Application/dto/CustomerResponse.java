package com.verinite.Atm_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {

    private Long customerId;
    private String customerName;
    private String email;
    private String mobile;
}