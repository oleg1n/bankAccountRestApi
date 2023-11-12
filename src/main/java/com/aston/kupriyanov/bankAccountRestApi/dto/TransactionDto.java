package com.aston.kupriyanov.bankAccountRestApi.dto;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
