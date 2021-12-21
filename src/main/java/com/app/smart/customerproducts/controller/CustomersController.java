package com.app.smart.customerproducts.controller;

import com.app.smart.customerproducts.dto.CustomersDTO;
import com.app.smart.customerproducts.service.CustomersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/")
public class CustomersController {

    private final CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomersDTO>> getAllCustomers() {
        return ResponseEntity.ok(customersService.findAllCustomers());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomersDTO> getProductById(@PathVariable UUID customerId) {
        return ResponseEntity.ok(customersService.findCustomerById(customerId));
    }

    @PostMapping("/customers")
    public ResponseEntity saveCustomer(@RequestBody CustomersDTO customersDTO) {
        customersService.saveCustomer(customersDTO);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/customers/{customerId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity editCustomer(@RequestBody CustomersDTO customersDTO, @PathVariable UUID customerId) {
        customersService.editCustomer(customersDTO, customerId);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(value = "/customers/{customerId}")
    @SecurityRequirement(name = "Authorization")
    public void deleteCustomerById(@PathVariable UUID customerId) {
        customersService.deleteCustomerById(customerId);
    }
}
