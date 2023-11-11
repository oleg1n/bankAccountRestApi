package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.NewAccountRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.AccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.NewAccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransactionResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.exception.AccountNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.exception.BeneficiaryNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import com.aston.kupriyanov.bankAccountRestApi.repo.BeneficiaryRepo;
import com.aston.kupriyanov.bankAccountRestApi.util.LogHelper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class MainFlowService {

    private static final Logger log = LoggerFactory.getLogger(MainFlowService.class);

    private final BeneficiaryService beneficiaryService;
    private final AccountService accountService;

    public NewAccountResponse createAccount(@Valid NewAccountRequest request){
        log.info("Start creating account...");
        Beneficiary beneficiary = new Beneficiary(request.getName());
        Account account = new Account(request.getPincode());
        beneficiary.addAccount(account);

        beneficiaryService.save(beneficiary);
        log.info("Account {} created successfully and saved to DB", account.getNumber());

        return NewAccountResponse.builder().beneficiaryId(beneficiary.getId().toString()).beneficiaryName(beneficiary.getName())
                .accountId(account.getId().toString()).accountNumber(account.getNumber()).accountBalance(String.valueOf(account.getBalance()))
                .accountPincode(account.getPincode()).createDate(account.getCreateDate().toString()).build();
    }
    public AccountResponse getAllAccountsAndBalance(@NotBlank String name) {
        log.info("Start searching accounts by name of beneficiary...");
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryByName(name);

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setBeneficiaryName(beneficiary.getName());
        Map<String, Long> accountsAndBalance = beneficiary.getAccounts().stream().collect(Collectors.toMap(Account::getNumber, Account::getBalance));
        accountResponse.setAccountsAndBalance(accountsAndBalance);

        return accountResponse;
    }

    public TransactionResponse getAllTransactions(@NotBlank String accountNumber){
        log.info("Start searching account by number...");
        Account account = accountService.getAccountByNumber(accountNumber);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactions(account.getTransactions());

        return transactionResponse;
    }
}
