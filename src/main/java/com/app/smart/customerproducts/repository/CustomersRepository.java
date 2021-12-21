package com.app.smart.customerproducts.repository;

import com.app.smart.customerproducts.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, UUID> {

}
