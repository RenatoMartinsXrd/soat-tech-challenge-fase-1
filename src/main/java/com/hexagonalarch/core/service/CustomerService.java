package com.hexagonalarch.core.service;
import com.hexagonalarch.core.ports.in.CreateCustomerUseCase;
import com.hexagonalarch.core.ports.in.GetAllCustomersUseCase;
import com.hexagonalarch.core.ports.in.GetCustomerUseCase;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService implements CreateCustomerUseCase, GetCustomerUseCase, GetAllCustomersUseCase {

    private final CustomerRepositoryPort customerRepository;

    public CustomerService(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer storedCustomer = customer.orElseThrow(() -> new RuntimeException("Customer not found"));

        return storedCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
