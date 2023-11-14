package com.aston.kupriyanov.bankAccountRestApi.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DepositOrWithDrawResponse {
    @JsonProperty("id_transaction")
    private UUID idTransaction;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("source_account")
    private String sourceAccountNumber;
    @JsonProperty("value")
    private long value;
}
