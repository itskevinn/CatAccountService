package com.kevcode.cataccountservice.application.CatAccount.http.Request;

import lombok.Data;

@Data
public class NationalTransactionRequest {
    private Long accountId;
    private Long value;
}
