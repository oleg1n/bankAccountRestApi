package com.aston.kupriyanov.bankAccountRestApi.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransferResponse {
    @JsonProperty("id_transaction")
    private UUID idTransaction;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("source_account")
    private String sourceAccountNumber;
    @JsonProperty("destination_account")
    private String destinationAccountNumber;
    @JsonProperty("value")
    private long value;
}
