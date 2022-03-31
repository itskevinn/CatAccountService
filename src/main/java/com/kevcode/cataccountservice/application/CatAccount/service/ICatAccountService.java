package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransaction;
import com.kevcode.cataccountservice.application.shared.Response;

import java.util.List;

public interface ICatAccountService {
    CatAccountDto save(CatAccountRequest catAccount);

    CatAccountDto findById(String id);

    List<CatAccountDto> findAll();

    List<CatAccountDto> findAllByPersonId(Long personId);

    Response<Long> withdraw(NationalTransaction nationalTransaction);

    public Response<Long> deposit(NationalTransaction nationalTransaction);

    Response<Long> getBalance(Long accountId);
}
