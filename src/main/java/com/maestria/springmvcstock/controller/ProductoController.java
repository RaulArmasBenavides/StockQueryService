package com.maestria.springmvcstock.controller;
import lombok.RequiredArgsConstructor;
import com.maestria.springmvcstock.model.Producto;
import com.maestria.springmvcstock.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productos/")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts() {
        List<Producto> tasks = productoService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        Producto task = productoService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@RequestBody Producto task) {
        Producto createdTask = productoService.save(task);
        return ResponseEntity.status(201).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto > updateProduct(@PathVariable Long id, @RequestBody Producto task) {
        Producto updatedTask = productoService.updateProducto(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    // @PatchMapping("/{id}")
    // public ResponseEntity<Task> partialUpdateTask(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
    //     Task updatedTask = productoService.partialUpdate(id, updates);
    //     return ResponseEntity.ok(updatedTask);
    // }

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
