package com.aston.kupriyanov.bankAccountRestApi.dto.request;

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
    @JsonProperty("pincode")
    private String pincode;
}
