package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.DepositOrWithDrawRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.request.TransferRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.DepositOrWithDrawResponse;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransferResponse;
import com.aston.kupriyanov.bankAccountRestApi.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/operation/")
@Tag(name="Контроллер операций", description="Отвечает за операции снятия, пополнения и " +
        "перевода денежных средств")
public class OperationController {

    private final OperationService operationService;

    @PutMapping(path = "/transfer")
    @Operation(
            summary = "Перевод денежных средств",
            description = "Переводит денежные средства с одного аккаунта на другой"
    )
    public ResponseEntity<TransferResponse> transferMoney(
            @RequestBody @Parameter(description = "Наименование операции,   аккаунт источник, пинкод, " +
                    "целевой аккаунт, значение перевода") @Valid TransferRequest request){
        return ResponseEntity.ok(operationService.transferMoney(request));
    }

    @PutMapping(path = "/deposit")
    @Operation(
            summary = "Пополнение баланса",
            description = "Пополняет баланс аккаунта"
    )
    public ResponseEntity<DepositOrWithDrawResponse> depositMoney(
            @RequestBody @Parameter(description = "Наименование операции,   аккаунт источник, пинкод, " +
                    "значение перевода") @Valid DepositOrWithDrawRequest request){
        return ResponseEntity.ok(operationService.depositMoney(request));
    }
    @PutMapping(path = "/withdraw")
    @Operation(
            summary = "Снятие денежных средств",
            description = "Снимает денежные средства"
    )
    public ResponseEntity<DepositOrWithDrawResponse> withdrawMoney(
            @RequestBody @Parameter(description = "Наименование операции,   аккаунт источник, пинкод, " +
                    "значение перевода") @Valid DepositOrWithDrawRequest request){
        return ResponseEntity.ok(operationService.withdrawMoney(request));
    }
}
