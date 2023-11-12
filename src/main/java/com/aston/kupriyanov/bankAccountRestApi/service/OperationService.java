package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.constant.OperationType;
import com.aston.kupriyanov.bankAccountRestApi.dto.request.DepositOrWithDrawRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.request.TransferRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.DepositOrWithDrawResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransferResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Transaction;
import com.aston.kupriyanov.bankAccountRestApi.exception.InsufficientFundsException;
import com.aston.kupriyanov.bankAccountRestApi.util.LogHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);

    private final AccountService accountService;
    private final BeneficiaryService beneficiaryService;

    public TransferResponse transferMoney(TransferRequest request) {
        log.info("Start transferring money...");
        Account sourceAccount = accountService.getAccountByNumber(request.getSourceAccountNumber());
        Account destinationAccount = accountService.getAccountByNumber(request.getDestinationAccountNumber());
        long value = request.getValue();

        beneficiaryService.checkPincodeOnValidation(sourceAccount.getOwner(), request.getPinCode());
        if (!checkIfSufficientFunds(sourceAccount.getBalance(), value)){
            InsufficientFundsException e = new InsufficientFundsException("Insufficient funds on the account");
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }

        destinationAccount.setBalance(destinationAccount.getBalance() + value);
        sourceAccount.setBalance(sourceAccount.getBalance() - value);

        Transaction transaction = new Transaction(OperationType.TRANSFER.toString(), destinationAccount, value);
        sourceAccount.addTransaction(transaction);

        accountService.save(sourceAccount);
        accountService.save(destinationAccount);

        log.info("Money transferred successfully");

        return TransferResponse.builder().idTransaction(transaction.getId()).operation(transaction.getOperation())
                .sourceAccountNumber(sourceAccount.getNumber()).destinationAccountNumber(destinationAccount.getNumber()).value(value).build();
    }

    public DepositOrWithDrawResponse depositMoney(DepositOrWithDrawRequest request) {
        log.info("Start deposit operation...");
        Account sourceAccount = accountService.getAccountByNumber(request.getSourceAccountNumber());
        long value = request.getValue();

        beneficiaryService.checkPincodeOnValidation(sourceAccount.getOwner(), request.getPinCode());

        sourceAccount.setBalance(sourceAccount.getBalance() + value);

        Transaction transaction = new Transaction(OperationType.DEPOSIT.toString(), sourceAccount, value);
        sourceAccount.addTransaction(transaction);

        accountService.save(sourceAccount);

        log.info("Money deposited successfully");

        return DepositOrWithDrawResponse.builder().idTransaction(transaction.getId()).operation(transaction.getOperation())
                .sourceAccountNumber(sourceAccount.getNumber()).value(value).build();
    }

    public DepositOrWithDrawResponse withdrawMoney(DepositOrWithDrawRequest request) {
        log.info("Start withdraw operation...");
        Account sourceAccount = accountService.getAccountByNumber(request.getSourceAccountNumber());
        long value = request.getValue();

        beneficiaryService.checkPincodeOnValidation(sourceAccount.getOwner(), request.getPinCode());

        sourceAccount.setBalance(sourceAccount.getBalance() - value);

        Transaction transaction = new Transaction(OperationType.WITHDRAW.toString(), sourceAccount, value);
        sourceAccount.addTransaction(transaction);

        accountService.save(sourceAccount);

        log.info("Money withdrew successfully");

        return DepositOrWithDrawResponse.builder().idTransaction(transaction.getId()).operation(transaction.getOperation())
                .sourceAccountNumber(sourceAccount.getNumber()).value(value).build();
    }

    private boolean checkIfSufficientFunds(long balance, long value){
        return (balance - value) >= 0;
    }
}
