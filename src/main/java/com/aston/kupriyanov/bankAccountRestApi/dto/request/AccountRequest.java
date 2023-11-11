package com.aston.kupriyanov.bankAccountRestApi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountRequest {
    @JsonProperty("name")
    private String name;
}
