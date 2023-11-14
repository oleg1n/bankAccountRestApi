package com.aston.kupriyanov.bankAccountRestApi.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank
    @JsonProperty("name")
    private String name;
}
