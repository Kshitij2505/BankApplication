package com.verinite.Atm_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionResponse {

    private Long transactionId;
    private String type;
    private BigDecimal amount;
    private String status;
    private LocalDateTime timestamp;
}