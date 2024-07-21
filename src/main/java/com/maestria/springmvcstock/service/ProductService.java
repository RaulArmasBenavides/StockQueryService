package com.maestria.springmvcstock.service;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maestria.springmvcstock.controller.exception.ResourceNotFoundException;
import com.maestria.springmvcstock.model.Product;
import com.maestria.springmvcstock.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
      @Autowired
    private final ProductRepository productoRepository;
    public List<Product> findAll() {
        return productoRepository.findAll();
    }

    public Product findById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public Product save(Product prod) {
        return productoRepository.save(prod);
    }

    public Product updateProducto(Long id, Product producto) {
        Product existingProducto = productoRepository.findById(id).orElse(null);
        if (existingProducto != null) {
            existingProducto.setName(producto.getName());
            existingProducto.setDescription(producto.getDescription());
            existingProducto.setPrice(producto.getPrice());
            existingProducto.setQuantityStock(producto.getQuantityStock());
            existingProducto.setSupplier(producto.getSupplier());
            return productoRepository.save(existingProducto);
        }
        return null;
    }

    // public Task partialUpdate(Long id, Map<String, Object> updates) {
    //     Task existingTask = findById(id);
    //     updates.forEach((key, value) -> {
    //         switch (key) {
    //             case "title":
    //                 existingTask.setTitle((String) value);
    //                 break;
    //             case "body":
    //                 existingTask.setBody((String) value);
    //                 break;
    //             case "status":
    //                 existingTask.setStatus((Boolean) value);
    //                 break;
    //             case "datefinished":
    //                 existingTask.setDatefinished((LocalDate) value);
    //                 break;
    //         }
    //     });
    //     return taskRepository.save(existingTask);
    // }

    public void deleteProduct(Long id) {
        productoRepository.deleteById(id);
    }
}
