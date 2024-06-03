package com.maestria.springmvcstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maestria.springmvcstock.model.Proveedor;
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}