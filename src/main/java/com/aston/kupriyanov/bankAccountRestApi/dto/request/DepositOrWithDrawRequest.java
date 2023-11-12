package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepositOrWithDrawRequest extends OperationRequest {
    @DecimalMin(value = "0", inclusive = false)
    @JsonProperty("value")
    private long value;
}
