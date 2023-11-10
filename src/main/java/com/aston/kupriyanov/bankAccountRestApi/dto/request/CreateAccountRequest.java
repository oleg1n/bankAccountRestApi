package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAccountRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("pincode")
    private String pincode;
}
