package com.verinite.Atm_Application.service;

import com.verinite.Atm_Application.entity.Account;
import com.verinite.Atm_Application.entity.Customer;
import com.verinite.Atm_Application.repository.AccountRepository;
import com.verinite.Atm_Application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {

        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(Long customerId, Account account){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        account.setCustomer(customer);

        return accountRepository.save(account);
    }

    public Account getAccount(String accountNumber){

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account Not Found"));
    }

    public List<Account> getAccountsByCustomer(Long customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        return accountRepository.findByCustomer(customer);
    }

}