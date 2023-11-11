package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class OperationRequest {
    @NotBlank
    @JsonProperty("operation")
    private String operation;
    @NotBlank
    @JsonProperty("account_number")
    private String sourceAccountNumber;
    @NotBlank
    @JsonProperty("pincode")
    private String pincode;
}
