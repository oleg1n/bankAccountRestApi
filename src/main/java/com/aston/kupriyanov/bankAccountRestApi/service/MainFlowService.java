package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.dto.TransactionDto;
import com.aston.kupriyanov.bankAccountRestApi.api.request.NewAccountRequest;
import com.aston.kupriyanov.bankAccountRestApi.api.response.AccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.api.response.NewAccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.api.response.TransactionResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.exception.BeneficiaryNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.util.LogHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MainFlowService {

    private static final Logger log = LoggerFactory.getLogger(MainFlowService.class);

    private final BeneficiaryService beneficiaryService;
    private final AccountService accountService;

    public NewAccountResponse createAccount(NewAccountRequest request){
        log.info("Start creating account...");
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryByName(request.getName());
        if (beneficiary == null){
            beneficiary = new Beneficiary(request.getName(), request.getPinCode());
        }else{
            beneficiaryService.checkPincodeOnValidation(beneficiary, request.getPinCode());
        }

        Account account = new Account();
        beneficiary.addAccount(account);

        beneficiaryService.save(beneficiary);
        log.info("Account {} created successfully and saved to DB", account.getNumber());

        return NewAccountResponse.builder().beneficiaryId(beneficiary.getId().toString()).beneficiaryName(beneficiary.getName())
                .accountId(account.getId().toString()).accountNumber(account.getNumber()).accountBalance(account.getBalance())
                .pinCode(beneficiary.getPinCode()).createDate(LocalDateTime.now().toString()).build();
    }
    public AccountResponse getAllAccountsAndBalance(String name) {
        log.info("Start searching accounts by name of beneficiary...");
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryByName(name);
        if (beneficiary == null){
            BeneficiaryNotFoundException e = new BeneficiaryNotFoundException(
                    String.format("Beneficiary name is %s not found", name));
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setBeneficiaryName(beneficiary.getName());
        Map<String, Long> accountsAndBalance = beneficiary.getAccounts().stream().collect(Collectors.toMap(Account::getNumber, Account::getBalance));
        accountResponse.setAccountsAndBalance(accountsAndBalance);

        return accountResponse;
    }

    public TransactionResponse getAllTransactions(String accountNumber){
        log.info("Start searching account by number...");
        Account account = accountService.getAccountByNumber(accountNumber);

        TransactionResponse transactionResponse = new TransactionResponse();

        List<TransactionDto> transactionsDto = account.getTransactions().stream().map(o -> TransactionDto.builder()
                .id(o.getId()).date(o.getDate()).value(o.getValue()).sourceAccount(o.getSourceAccount().getNumber())
                .destinationAccount(o.getDestinationAccount().getNumber()).operation(o.getOperation()).build()).toList();
        transactionResponse.setTransactions(transactionsDto);

        return transactionResponse;
    }
}
