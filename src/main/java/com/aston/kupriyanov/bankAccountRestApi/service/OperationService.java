package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.TransferRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransferResponse;
import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Transaction;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OperationService {

    private final AccountService accountService;
    public TransferResponse transferMoney(TransferRequest request) {
        Account sourceAccount = accountService.getAccountByNumber(request.getSourceAccountNumber());
        Account destinationAccount = accountService.getAccountByNumber(request.getDestinationAccountNumber());
        long value = Long.parseLong(request.getValue());

        if (accountService.checkPincodeOnValidation(sourceAccount, request.getPincode())){
            destinationAccount.setBalance(destinationAccount.getBalance() + value);
            sourceAccount.setBalance(sourceAccount.getBalance() - value);

            Transaction transaction = new Transaction(request.getOperation(), destinationAccount.getId(), value);
            sourceAccount.addTransaction(transaction);

            accountService.save(sourceAccount);
            accountService.save(destinationAccount);
        }
    }
}
