package com.verinite.Atm_Application.repository;

import com.verinite.Atm_Application.entity.Account;
import com.verinite.Atm_Application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomer(Customer customer);

    boolean existsByAccountNumber(String accountNumber);
}