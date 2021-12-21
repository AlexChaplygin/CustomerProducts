package com.app.smart.customerproducts.service;

import com.app.smart.customerproducts.dto.ProductsDTO;

import java.util.List;
import java.util.UUID;

public interface ProductsService {

    ProductsDTO findProductById(UUID id);

    List<ProductsDTO> findProductsByCustomersId(UUID customersId, int page, int size);

    void saveProduct(ProductsDTO productsDTO, UUID productId);

    void editProduct(ProductsDTO productsDTO, UUID productId);

    void saveProductByCustomerId(ProductsDTO productsDTO, UUID customerId);

    void deleteProductById(UUID id);
}
