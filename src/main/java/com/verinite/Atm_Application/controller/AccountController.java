package com.verinite.Atm_Application.controller;

import com.verinite.Atm_Application.entity.Account;
import com.verinite.Atm_Application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/create/{customerId}")
    public Account createAccount(@PathVariable Long customerId,
                                 @RequestBody Account account) {
        return service.createAccount(customerId, account);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return service.getAccount(accountNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getByCustomer(@PathVariable Long customerId) {
        return service.getAccountsByCustomer(customerId);
    }
}