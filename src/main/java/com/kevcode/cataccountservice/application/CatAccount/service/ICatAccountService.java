package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransactionRequest;
import com.kevcode.cataccountservice.application.shared.Response;

import java.util.List;

public interface ICatAccountService {
    Response<CatAccountDto> save(CatAccountRequest catAccount);

    Response<CatAccountDto> findById(Long id);

    Response<List<CatAccountDto>> findAll();

    Response<List<CatAccountDto>> findAllByPersonId(Long personId);

    Response<Long> withdraw(NationalTransactionRequest request);

    Response<Long> deposit(NationalTransactionRequest request);

    Response<Long> getBalance(Long accountId);
}
