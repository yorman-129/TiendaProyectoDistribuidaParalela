package com.example.tienda;

import com.example.tienda.resolver.MutationResolver;
import com.example.tienda.resolver.queryResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}
	@Bean
	public MutationResolver authorResolver() {
		return new MutationResolver();
	}

	@Bean
	public queryResolver query() {
		return new queryResolver();
	}


}
