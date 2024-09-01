package com.mikhailkarpov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionalOutboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionalOutboxApplication.class, args);
	}

}
