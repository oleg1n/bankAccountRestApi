package com.aston.kupriyanov.bankAccountRestApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("sourceAccount")
    private String sourceAccount;
    @JsonProperty("destinationAccount")
    private String destinationAccount;
    @JsonProperty("value")
    private long value;
    @JsonProperty("date")
    private LocalDateTime date;
}
