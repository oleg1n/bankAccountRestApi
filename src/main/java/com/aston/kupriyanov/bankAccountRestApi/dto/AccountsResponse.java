package com.aston.kupriyanov.bankAccountRestApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class AccountsResponse {
    @JsonProperty("beneficiary_name")
    private String beneficiaryName;
    @JsonProperty("account_and_balance")
    private Map<String, Long> accountsAndBalance;
}
