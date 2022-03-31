package com.kevcode.cataccountservice.application.CatAccount.http.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatAccountDto {
    private Long balance;
    private Long personId;
    private Long accountId;
}
