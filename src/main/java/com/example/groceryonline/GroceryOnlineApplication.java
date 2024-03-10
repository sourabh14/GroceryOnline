package com.example.groceryonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GroceryOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryOnlineApplication.class, args);
	}

}
