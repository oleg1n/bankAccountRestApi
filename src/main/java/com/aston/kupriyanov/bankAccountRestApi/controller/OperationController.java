package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.request.TransferRequest;
import com.aston.kupriyanov.bankAccountRestApi.dto.response.TransferResponse;
import com.aston.kupriyanov.bankAccountRestApi.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name="Контроллер операций", description="Отвечает за операции снятия, пополнения и " +
        "перевода денежных средств")
public class OperationController {

    private final OperationService operationService;

    @PutMapping(path = "/accounts")
    @Operation(
            summary = "Перевод денежных средств",
            description = "Переводит денежные средства с одного аккаунта на другой"
    )
    public ResponseEntity<TransferResponse> transferMoney(
            @RequestBody @Parameter(description = "") TransferRequest request){
        return ResponseEntity.ok(operationService.transferMoney(request));
    }
}
