package com.maestria.springmvcstock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maestria.springmvcstock.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {
}