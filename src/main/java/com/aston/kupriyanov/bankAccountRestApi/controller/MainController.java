package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.NewAccountRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.AccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.NewAccountResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransactionResponse;
import com.aston.kupriyanov.bankAccountRestApi.service.MainFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name="Основной контроллер", description="Создает новые аккаунты, выгружает все аккаунты " +
        "по имени бенефициара, выгружает все транзакции аккаунта")
public class MainController {

    private final MainFlowService mainFlowService;

    @PostMapping(path = "/accounts")
    @Operation(
            summary = "Создание нового аккаунта",
            description = "Создает нового бенефициара и аккаунт. Принимает имя пользователя и " +
                    "пинкод для аккаунта"
    )
    public ResponseEntity<NewAccountResponse> createNewAccount(
            @RequestBody @Parameter(description = "Имя бенефициара и пинкод") NewAccountRequest request){
        return ResponseEntity.ok(mainFlowService.createAccount(request));
    }
    @GetMapping(path = "/accounts/{name}")
    @Operation(
            summary = "Получение списка аккаунтов",
            description = "Предоставляет данные всех аккаунтов, принадлежащих одному бенефициару"
    )
    public ResponseEntity<AccountResponse> getAllAccountsAndBalanceByName(
            @PathVariable @Parameter(description = "Имя бенефициара") String name){
        return ResponseEntity.ok(mainFlowService.getAllAccountsAndBalance(name));
    }

    @GetMapping(path = "/accounts{accountNumber}")
    @Operation(
            summary = "Получение списка транзакций",
            description = "Предоставляет данные всех транзакций по номеру аккаунта"
    )
    public ResponseEntity<TransactionResponse> getAllTransactionsByAccountNumber(
            @RequestParam @Parameter(description = "Номер аккаунта") String accountNumber){
        return ResponseEntity.ok(mainFlowService.getAllTransactions(accountNumber));
    }
}
