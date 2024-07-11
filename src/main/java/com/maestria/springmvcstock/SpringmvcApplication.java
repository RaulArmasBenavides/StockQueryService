package com.maestria.springmvcstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication(scanBasePackages = "com.maestria.springmvcstock")
@PropertySource("classpath:env.properties")
@EnableDiscoveryClient
public class SpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

}
