package com.maestria.springmvcstock.service;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maestria.springmvcstock.model.Product;
import com.maestria.springmvcstock.repository.ProductRepository;
import com.maestria.springmvcstock.service.impl.ProductServiceImp;
 
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Test
  void save_callsRepository() {
    ProductRepository repo = mock(ProductRepository.class);
    ProductService service = new ProductServiceImp(repo);

    Product p = new Product(); p.setName("X");
    service.save(p);

    ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
    verify(repo).save(captor.capture());
    assertThat(captor.getValue().getName()).isEqualTo("X");
  }
}