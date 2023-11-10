package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.CreateAccountRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.AccountsAndBalanceResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.CreateAccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransactionsResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.exception.BeneficiaryNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import com.aston.kupriyanov.bankAccountRestApi.repo.BeneficiaryRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MainFlowService {

    private static final Logger log = LoggerFactory.getLogger(MainFlowService.class);

    private final BeneficiaryRepo beneficiaryRepo;
    private final AccountRepo accountRepo;

    public CreateAccountResponse createAccount(CreateAccountRequest request){
        log.info("Start creating account...");
        Beneficiary beneficiary = new Beneficiary(request.getName());
        Account account = new Account(request.getPincode());
        beneficiary.addAccount(account);

        beneficiaryRepo.save(beneficiary);
        log.info("Account {} created successfully and saved to DB", account.getNumber());

        return CreateAccountResponse.builder().beneficiaryId(beneficiary.getId().toString()).beneficiaryName(beneficiary.getName())
                .accountId(account.getId().toString()).accountNumber(account.getNumber()).accountBalance(String.valueOf(account.getBalance()))
                .accountPincode(account.getPincode()).createDate(account.getCreateDate().toString()).build();
    }
    public AccountsAndBalanceResponse getAllAccountsAndBalance(String name) throws BeneficiaryNotFoundException{
        log.info("Start searching accounts by name of beneficiary...");
        Beneficiary beneficiary = beneficiaryRepo.getBeneficiaryByName(name);
        if (beneficiary == null){
            throw new BeneficiaryNotFoundException(String.format("Beneficiary name %s not found", name));
        }
        AccountsAndBalanceResponse accountsAndBalanceResponse = new AccountsAndBalanceResponse();

        accountsAndBalanceResponse.setBeneficiaryName(beneficiary.getName());
        Map<String, Long> accountsAndBalance = beneficiary.getAccounts().stream().collect(Collectors.toMap(Account::getNumber, Account::getBalance));
        accountsAndBalanceResponse.setAccountsAndBalance(accountsAndBalance);

        return accountsAndBalanceResponse;
    }

    public TransactionsResponse getAllTransactions(String accountNumber){
        log.info("Start searching account by number...");
        Account account = accountRepo.getAccountByNumber(accountNumber);

        TransactionsResponse transactionsResponse = new TransactionsResponse();
        transactionsResponse.setTransactions(account.getTransactions());

        return transactionsResponse;
    }
}
