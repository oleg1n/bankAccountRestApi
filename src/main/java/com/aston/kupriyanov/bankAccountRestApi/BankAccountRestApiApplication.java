package com.aston.kupriyanov.bankAccountRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class BankAccountRestApiApplication{
	public static void main(String[] args) {
		SpringApplication.run(BankAccountRestApiApplication.class, args);
	}

}
