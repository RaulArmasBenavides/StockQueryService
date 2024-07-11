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
@RequestMapping("/api/v1/products/")
public class ProductController {
    private final ProductService productoService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> prods = productoService.findAll();
        return ResponseEntity.ok(prods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product prd = productoService.findById(id);
        return ResponseEntity.ok(prd);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product prod) {
        Product createdProduct = productoService.save(prod);
        return ResponseEntity.status(201).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product > updateProduct(@PathVariable Long id, @RequestBody Product prod) {
        Product updatedProduct = productoService.updateProducto(id, prod);
        return ResponseEntity.ok(updatedProduct);
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
