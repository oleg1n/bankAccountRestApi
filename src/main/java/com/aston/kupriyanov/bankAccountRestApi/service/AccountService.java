package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.exception.AccountNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.exception.PincodeIsNotValidException;
import com.aston.kupriyanov.bankAccountRestApi.repo.AccountRepo;
import com.aston.kupriyanov.bankAccountRestApi.util.LogHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepo accountRepo;

    public Account getAccountByNumber (String accountNumber){
        Account account = accountRepo.getAccountByNumber(accountNumber);
        if (account == null){
            AccountNotFoundException e = new AccountNotFoundException(
                    String.format("Account number is %s not found", accountNumber));
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }
        return account;
    }

    public boolean checkPincodeOnValidation(Account account, String pincode){
        if (!account.getPincode().equals(pincode)){
            PincodeIsNotValidException e = new PincodeIsNotValidException("Pincode is not valid. Try again!");
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }
        return true;
    }

    public void save(Account account){
        accountRepo.save(account);
    }
}
