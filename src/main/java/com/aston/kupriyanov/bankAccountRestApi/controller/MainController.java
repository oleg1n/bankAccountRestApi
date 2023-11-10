package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.CreateAccountRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.AccountsAndBalanceResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.CreateAccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransactionsResponse;
import com.aston.kupriyanov.bankAccountRestApi.service.MainFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainFlowService mainFlowService;

    @PostMapping(path = "/accounts")
    public ResponseEntity<CreateAccountResponse> createNewAccount(@RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(mainFlowService.createAccount(request));
    }
    @GetMapping(path = "/accounts/{name}")
    public ResponseEntity<AccountsAndBalanceResponse> getAllAccountsAndBalanceByName(@PathVariable String name){
        return ResponseEntity.ok(mainFlowService.getAllAccountsAndBalance(name));
    }

    @GetMapping(path = "/accounts{accountNumber}")
    public ResponseEntity<TransactionsResponse> getAllTransactionsByAccountNumber(@RequestParam String accountNumber){
        return ResponseEntity.ok(mainFlowService.getAllTransactions(accountNumber));
    }
}
