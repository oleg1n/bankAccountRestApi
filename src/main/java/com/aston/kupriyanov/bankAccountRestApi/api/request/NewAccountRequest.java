package com.aston.kupriyanov.bankAccountRestApi.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewAccountRequest {
    @NotBlank
    @JsonProperty("name")
    private String name;
    @Size(min = 4, max = 4)
    @JsonProperty("pin_code")
    private String pinCode;
}
