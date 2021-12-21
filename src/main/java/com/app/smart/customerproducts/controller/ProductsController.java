package com.app.smart.customerproducts.controller;

import com.app.smart.customerproducts.dto.ProductsDTO;
import com.app.smart.customerproducts.service.ProductsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/customers/{customerId}/products")
    public ResponseEntity<List<ProductsDTO>> findProductsByCustomersId(@PathVariable UUID customerId,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productsService.findProductsByCustomersId(customerId, page, size));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productsService.findProductById(productId));
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity save(@RequestBody ProductsDTO productsDTO, @PathVariable UUID productId) {
        productsService.saveProduct(productsDTO, productId);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/products/{productId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity edit(@RequestBody ProductsDTO productsDTO, @PathVariable UUID productId) {
        productsService.editProduct(productsDTO, productId);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/customers/{customerId}/products")
    public ResponseEntity saveProduct(@RequestBody ProductsDTO productsDTO, @PathVariable UUID customerId) {
        productsService.saveProductByCustomerId(productsDTO, customerId);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(value = "/products/delete/{productId}")
    @SecurityRequirement(name = "Authorization")
    public void deleteProductById(@PathVariable UUID productId) {
        productsService.deleteProductById(productId);
    }
}
