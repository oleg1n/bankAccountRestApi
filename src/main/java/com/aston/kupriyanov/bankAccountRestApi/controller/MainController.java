package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.service.MainFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MainFlowService mainFlowService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createNewAccount(@RequestBody String name, @RequestBody String pincode){
        return ResponseEntity.ok(mainFlowService.createAccount(name, pincode));
    }

}
