package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransferRequest extends OperationRequest {
    @NotBlank
    @JsonProperty("account_number")
    private String destinationAccountNumber;
    @NotBlank
    @JsonProperty("value")
    private String value;
}
