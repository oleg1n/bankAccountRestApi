package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;
}
