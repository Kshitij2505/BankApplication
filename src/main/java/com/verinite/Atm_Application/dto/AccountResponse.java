package com.verinite.Atm_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountResponse {

    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
}