package com.app.smart.customerproducts.service;

import com.app.smart.customerproducts.dto.CustomersDTO;
import com.app.smart.customerproducts.entity.Customers;
import com.app.smart.customerproducts.repository.CustomersRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository customersRepository;
    private final ModelMapper mapper;

    @Autowired
    public CustomersServiceImpl(CustomersRepository customersRepository, ModelMapper mapper) {
        this.customersRepository = customersRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
    public CustomersDTO findCustomerById(UUID id) {
        return mapper.map(customersRepository.getById(id), CustomersDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<CustomersDTO> findAllCustomers() {
        return mapper.map(customersRepository.findAll(), new TypeToken<List<CustomersDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveCustomer(CustomersDTO customersDTO) {
        customersRepository.save(mapper.map(customersDTO, Customers.class));
    }

    @Override
    public void editCustomer(CustomersDTO customerRequest, UUID id) {
        CustomersDTO customer = mapper.map(customersRepository.getById(id), CustomersDTO.class);
        customer.setDeleted(customerRequest.isDeleted());
        customer.setCreatedAt(customerRequest.getCreatedAt());
        customer.setTitle(customerRequest.getTitle());
        customer.setModifiedAt(customerRequest.getModifiedAt());
        customersRepository.save(mapper.map(customer, Customers.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomerById(UUID id) {
        customersRepository.deleteById(id);
    }
}
