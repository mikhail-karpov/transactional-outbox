package com.mikhailkarpov;

import org.springframework.boot.SpringApplication;

public class TestTransactionalOutboxApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransactionalOutboxApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
