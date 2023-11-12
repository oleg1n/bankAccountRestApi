package com.aston.kupriyanov.bankAccountRestApi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewAccountResponse {
    @JsonProperty("beneficiary_id")
    private String beneficiaryId;
    @JsonProperty("beneficiary_name")
    private String beneficiaryName;
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_balance")
    private long accountBalance;
    @JsonProperty("account_pincode")
    private String pinCode;
    @JsonProperty("create_date")
    private String createDate;
}
