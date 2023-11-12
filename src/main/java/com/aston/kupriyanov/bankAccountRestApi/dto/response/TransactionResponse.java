package com.aston.kupriyanov.bankAccountRestApi.dto.response;

import com.aston.kupriyanov.bankAccountRestApi.dto.TransactionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TransactionResponse {
    @JsonProperty("transactions")
    private List<TransactionDto> transactions;
}
