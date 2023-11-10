package com.aston.kupriyanov.bankAccountRestApi;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import com.aston.kupriyanov.bankAccountRestApi.repo.BeneficiaryRepo;
import com.aston.kupriyanov.bankAccountRestApi.repo.TransactionRepo;
import com.aston.kupriyanov.bankAccountRestApi.service.MainFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableJpaAuditing

public class BankAccountRestApiApplication implements CommandLineRunner {

	@Autowired
	private MainFlowService mainFlowService;


	public static void main(String[] args) {
		SpringApplication.run(BankAccountRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
