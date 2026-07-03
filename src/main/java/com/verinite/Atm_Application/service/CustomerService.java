package com.verinite.Atm_Application.service;

import com.verinite.Atm_Application.entity.Customer;
import com.verinite.Atm_Application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer){
        return repository.save(customer);
    }

    public Customer getCustomer(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public String deleteCustomer(Long id){

        repository.deleteById(id);

        return "Customer Deleted Successfully";
    }

}