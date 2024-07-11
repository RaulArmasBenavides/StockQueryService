package com.maestria.springmvcstock.controller;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maestria.springmvcstock.model.Product;
import com.maestria.springmvcstock.model.Supplier;
import com.maestria.springmvcstock.service.ProductService;
import com.maestria.springmvcstock.service.SupplierService;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suppliers/")
public class SupplierController {
      private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllProducts() {
        List<Supplier> supls = supplierService.getAllSuppliers();
        return ResponseEntity.ok(supls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getProductById(@PathVariable Long id) {
        Supplier sup = supplierService.getSupplierById(id);
        return ResponseEntity.ok(sup);
    }

    @PostMapping
    public ResponseEntity<Supplier> createProduct(@RequestBody Supplier task) {
        Supplier createdTask = supplierService.createSupplier(task);
        return ResponseEntity.status(201).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateProduct(@PathVariable Long id, @RequestBody Supplier sup) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, sup);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok().header("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS").build();
    }
}
