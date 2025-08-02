package com.maestria.springmvcstock.service;

import com.maestria.springmvcstock.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product updateProducto(Long id, Product product);
    void deleteProduct(Long id);
}