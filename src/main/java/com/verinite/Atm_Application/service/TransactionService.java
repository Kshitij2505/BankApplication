package com.verinite.Atm_Application.service;

import com.verinite.Atm_Application.entity.Account;
import com.verinite.Atm_Application.entity.Transaction;
import com.verinite.Atm_Application.repository.AccountRepository;
import com.verinite.Atm_Application.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public String deposit(String accountNumber,BigDecimal amount){

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account Not Found"));

        account.setBalance(account.getBalance().add(amount));

        accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setStatus("SUCCESS");
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Amount Deposited Successfully";
    }

    public String withdraw(String accountNumber,BigDecimal amount){

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account Not Found"));

        if (account.getBalance().compareTo(amount) >= 0){
            throw new RuntimeException("Insufficient Balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAW");
        transaction.setStatus("SUCCESS");
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Amount Withdrawn Successfully";
    }

    public String transfer(String from,String to,BigDecimal amount){

        Account sender = accountRepository.findByAccountNumber(from)
                .orElseThrow(() ->
                        new RuntimeException("Sender Account Not Found"));

        Account receiver = accountRepository.findByAccountNumber(to)
                .orElseThrow(() ->
                        new RuntimeException("Receiver Account Not Found"));

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance().subtract(amount));

        receiver.setBalance(
                receiver.getBalance().add(BigDecimal.ONE.subtract(amount))
        );
        accountRepository.save(sender);
        accountRepository.save(receiver);

        return "Transfer Successful";
    }

    public List<Transaction> getTransactionHistory(String accountNumber){

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account Not Found"));

        return transactionRepository.findByAccount(account);
    }

}