package com.app.smart.customerproducts.service;

import com.app.smart.customerproducts.dto.CustomersDTO;

import java.util.List;
import java.util.UUID;

public interface CustomersService {

    CustomersDTO findCustomerById(UUID id);

    List<CustomersDTO> findAllCustomers();

    void saveCustomer(CustomersDTO customersDTO);

    void editCustomer(CustomersDTO customersDTO, UUID id);

    void deleteCustomerById(UUID id);
}
