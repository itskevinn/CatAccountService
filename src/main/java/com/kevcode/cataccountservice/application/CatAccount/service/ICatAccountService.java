package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.shared.Response;

import java.util.List;

public interface ICatAccountService {
    CatAccountDto save(CatAccountRequest catAccount);

    CatAccountDto findById(String id);

    List<CatAccountDto> findAll();

    List<CatAccountDto> findAllByPersonId(Long personId);

    Response<Long> withdraw(Long value, Long accountId);

    Long deposit(Long value, Long accountId);

    Long getBalance(Long accountId);
}
