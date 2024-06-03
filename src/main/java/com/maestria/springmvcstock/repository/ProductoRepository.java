package com.maestria.springmvcstock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maestria.springmvcstock.model.Producto;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}