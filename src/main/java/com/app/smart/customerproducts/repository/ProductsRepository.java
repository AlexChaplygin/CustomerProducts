package com.app.smart.customerproducts.repository;

import com.app.smart.customerproducts.entity.Customers;
import com.app.smart.customerproducts.entity.Products;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID> {

    List<Products> findAllByCustomer(Customers customers, Pageable pageable);
}
