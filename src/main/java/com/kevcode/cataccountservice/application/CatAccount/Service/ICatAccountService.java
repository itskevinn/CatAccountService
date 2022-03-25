package com.kevcode.cataccountservice.application.CatAccount.Service;

import com.kevcode.cataccountservice.application.CatAccount.Http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.Http.Request.CatAccountRequest;

import java.util.List;

public interface ICatAccountService {
    CatAccountDto save(CatAccountRequest catAccount);

    CatAccountDto findById(String id);

    List<CatAccountDto> findAll();

    List<CatAccountDto> findAllByPersonId(Long personId);
}
