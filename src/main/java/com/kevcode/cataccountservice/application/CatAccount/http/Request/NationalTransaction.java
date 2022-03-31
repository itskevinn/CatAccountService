package com.kevcode.cataccountservice.application.CatAccount.http.Request;

import lombok.Data;

@Data
public class NationalTransaction {
   private Long accountId;
    private Long value;
}
