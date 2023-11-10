package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.dto.AccountsResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import com.aston.kupriyanov.bankAccountRestApi.repo.BeneficiaryRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MainFlowService {

    private static final Logger log = LoggerFactory.getLogger(MainFlowService.class);

    private final BeneficiaryRepo beneficiaryRepo;
    private final AccountRepo accountRepo;

    public Account createAccount(String name, String pincode){
        Beneficiary beneficiary = new Beneficiary(name);
        Account account = new Account(pincode);
        beneficiary.addAccount(account);

        beneficiaryRepo.save(beneficiary);

        return account;
    }
    public AccountsResponse getAllAccountsAndBalance(String name){
        Beneficiary beneficiary = beneficiaryRepo.getBeneficiaryByName(name);
        AccountsResponse accountsResponse = new AccountsResponse();

        accountsResponse.setBeneficiaryName(beneficiary.getName());
        Map<String, Long> accountsAndBalance = beneficiary.getAccounts().stream().collect(Collectors.toMap(Account::getNumber, Account::getBalance));
        accountsResponse.setAccountsAndBalance(accountsAndBalance);

        return accountsResponse;
    }
}
