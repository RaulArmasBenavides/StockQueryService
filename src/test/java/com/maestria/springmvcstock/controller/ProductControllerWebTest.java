package com.maestria.springmvcstock.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.maestria.springmvcstock.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false) // alternativa si no quieres autenticar
class ProductControllerWebTest {

  @Autowired private MockMvc mvc;

  @MockBean private ProductService productService; // satisface la inyección del controller

  @Test
  void getAllProducts_returns200() throws Exception {
    mvc.perform(get("/api/v1/products"))
       .andExpect(status().isOk());
  }
}