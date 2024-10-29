package com.hexagonalarch.adapters.outbound.jpa;

import com.hexagonalarch.adapters.outbound.jpa.entity.CustomerEntity;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.domain.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final JpaCustomerRepository jpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = jpaRepository.save(modelMapper.map(customer, CustomerEntity.class));
        return modelMapper.map(customerEntity, Customer.class);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Customer.class));
    }

    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Customer.class))
                .toList();
    }
}

