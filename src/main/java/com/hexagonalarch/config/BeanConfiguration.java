package com.hexagonalarch.config;

import com.hexagonalarch.adapters.inbound.ServicesFacade.CustomerServiceFacade;
import com.hexagonalarch.adapters.inbound.ServicesFacade.OrderServiceFacade;
import com.hexagonalarch.adapters.inbound.ServicesFacade.ProductServiceFacade;
import com.hexagonalarch.core.ports.in.*;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.ports.out.OrderRepositoryPort;
import com.hexagonalarch.core.ports.out.ProductRepositoryPort;
import com.hexagonalarch.core.service.CustomerService;
import com.hexagonalarch.core.service.OrderService;
import com.hexagonalarch.core.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomerService customerService(CustomerRepositoryPort customerRepositoryPort) {
        return new CustomerService(customerRepositoryPort);
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, CustomerRepositoryPort customerRepository, ProductRepositoryPort productRepository) {
        return new OrderService(orderRepositoryPort, customerRepository, productRepository);
    }

    @Bean
    public CustomerServiceFacade customerServiceFacade(CreateCustomerUseCase customerService, GetCustomerUseCase getCustomerUseCase, GetAllCustomersUseCase getAllCustomersUseCase) {
        return new CustomerServiceFacade(customerService, getCustomerUseCase, getAllCustomersUseCase);
    }

    @Bean
    public ProductServiceFacade productServiceFacade(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase, GetAllProductsUseCase getAllProductsUseCase) {
        return new ProductServiceFacade(createProductUseCase, getProductUseCase, getAllProductsUseCase);
    }

    @Bean
    public OrderServiceFacade orderServiceFacade(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase) {
        return new OrderServiceFacade(createOrderUseCase, getOrderUseCase, getAllOrdersUseCase);
    }
}
