package com.aston.kupriyanov.bankAccountRestApi.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransferRequest extends OperationRequest {
    @NotBlank
    @JsonProperty("destination_account_number")
    private String destinationAccountNumber;
    @DecimalMin(value = "0", inclusive = false)
    @JsonProperty("value")
    private long value;
}
