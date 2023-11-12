package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class OperationRequest {
    @NotBlank
    @JsonProperty("source_account_number")
    private String sourceAccountNumber;
    @Size(min = 4, max = 4)
    @JsonProperty("pin_code")
    private String pinCode;
}
