package com.aston.kupriyanov.bankAccountRestApi.dto.response;

import com.aston.kupriyanov.bankAccountRestApi.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {
    @JsonProperty("transactions")
    private List<Transaction> transactions;
}
