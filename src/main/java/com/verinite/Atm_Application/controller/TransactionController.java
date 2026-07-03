package com.verinite.Atm_Application.controller;

import com.verinite.Atm_Application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber,
                          @RequestParam BigDecimal amount) {
        return service.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber,
                           @RequestParam BigDecimal amount) {
        return service.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccount,
                           @RequestParam String toAccount,
                           @RequestParam BigDecimal amount) {
        return service.transfer(fromAccount, toAccount, amount);
    }

    @GetMapping("/history/{accountNumber}")
    public Object history(@PathVariable String accountNumber) {
        return service.getTransactionHistory(accountNumber);
    }
}