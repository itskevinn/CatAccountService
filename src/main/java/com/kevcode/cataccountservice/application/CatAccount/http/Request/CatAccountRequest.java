package com.kevcode.cataccountservice.application.CatAccount.http.Request;

import lombok.Data;

@Data
public class CatAccountRequest {
    private Long balance;
    private Long accountId;
}
