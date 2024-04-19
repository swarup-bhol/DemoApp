package com.example.DempApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class DempAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DempAppApplication.class, args);
	}

}
