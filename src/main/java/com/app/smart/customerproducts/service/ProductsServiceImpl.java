package com.app.smart.customerproducts.service;

import com.app.smart.customerproducts.dto.CustomersDTO;
import com.app.smart.customerproducts.dto.ProductsDTO;
import com.app.smart.customerproducts.entity.Customers;
import com.app.smart.customerproducts.entity.Products;
import com.app.smart.customerproducts.repository.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final CustomersService customersService;
    private final ModelMapper mapper;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository, ModelMapper mapper, CustomersService customersService) {
        this.productsRepository = productsRepository;
        this.customersService = customersService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
    public ProductsDTO findProductById(UUID id) {
        return mapper.map(productsRepository.getById(id), ProductsDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<ProductsDTO> findProductsByCustomersId(UUID customersId, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return mapper.map(productsRepository.findAllByCustomer(
                        mapper.map(customersService.findCustomerById(customersId), Customers.class), pageRequest),
                new TypeToken<List<ProductsDTO>>() {
                }.getType());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveProduct(ProductsDTO productsDTO, UUID productId) {
        productsRepository.save(mapper.map(productsDTO, Products.class));
    }

    @Override
    public void editProduct(ProductsDTO productsRequst, UUID productId) {
        ProductsDTO product = mapper.map(productsRepository.getById(productId), ProductsDTO.class);
        product.setDeleted(productsRequst.isDeleted());
        product.setCreatedAt(productsRequst.getCreatedAt());
        product.setDescription(productsRequst.getDescription());
        product.setPrice(productsRequst.getPrice());
        product.setTitle(productsRequst.getTitle());
        product.setModifiedAt(productsRequst.getModifiedAt());
        productsRepository.save(mapper.map(product, Products.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveProductByCustomerId(ProductsDTO productsDTO, UUID customerId) {
        CustomersDTO customersDTO = customersService.findCustomerById(customerId);
        productsDTO.setCustomer(customersDTO);
        productsRepository.save(mapper.map(productsDTO, Products.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteProductById(UUID id) {
        productsRepository.deleteById(id);
    }
}
