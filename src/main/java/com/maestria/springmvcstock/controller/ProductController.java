package com.maestria.springmvcstock.controller;
import lombok.RequiredArgsConstructor;
import com.maestria.springmvcstock.model.Product;
import com.maestria.springmvcstock.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productos/")
public class ProductController {
    private final ProductService productoService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> tasks = productoService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product task = productoService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product task) {
        Product createdTask = productoService.save(task);
        return ResponseEntity.status(201).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product > updateProduct(@PathVariable Long id, @RequestBody Product task) {
        Product updatedTask = productoService.updateProducto(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok().header("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS").build();
    }
}
