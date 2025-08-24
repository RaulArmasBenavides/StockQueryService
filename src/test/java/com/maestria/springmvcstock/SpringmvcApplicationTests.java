package com.maestria.springmvcstock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.maestria.springmvcstock.service.ProductService;

@SpringBootTest
@ActiveProfiles("test")
class SpringmvcApplicationTests {

	@MockBean
	private ProductService productService;

	@Test
	void contextLoads() {
	}

}
