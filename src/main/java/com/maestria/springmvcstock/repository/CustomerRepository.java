package com.maestria.springmvcstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maestria.springmvcstock.model.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingOrEmailContaining(String name, String email);
}