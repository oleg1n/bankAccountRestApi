package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepositOrWithDrawRequest extends OperationRequest {
    @NotBlank
    @JsonProperty("value")
    private String value;
}
