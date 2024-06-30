package com.maestria.springmvcstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maestria.springmvcstock.model.Supplier;
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}