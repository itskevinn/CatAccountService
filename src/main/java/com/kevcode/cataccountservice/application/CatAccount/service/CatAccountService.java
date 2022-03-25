package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.shared.Response;
import com.kevcode.cataccountservice.domain.cataccount.entities.CatAccount;
import com.kevcode.cataccountservice.infrastructure.CatAccountRepositoryCustom;
import com.kevcode.cataccountservice.infrastructure.ICatAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatAccountService implements ICatAccountService {
    @Autowired
    private CatAccountRepositoryCustom catAccountCustomRepository;
    @Autowired
    private ICatAccountRepository catAccountRepository;
    @Autowired
    private ModelMapper _modelMapper;

    @Override
    public CatAccountDto save(CatAccountRequest catAccountRequest) {
        CatAccount catAccount = _modelMapper.map(catAccountRequest, CatAccount.class);
        catAccount = catAccountRepository.save(catAccount);
        return _modelMapper.map(catAccount, CatAccountDto.class);
    }

    @Override
    public CatAccountDto findById(String id) {
        return null;
    }

    @Override
    public List<CatAccountDto> findAll() {
        return null;
    }

    @Override
    public List<CatAccountDto> findAllByPersonId(Long personId) {
        return null;
    }

    @Override
    public Response<Long> withdraw(Long value, Long accountId) {
        Long balance = catAccountCustomRepository.withdraw(value, accountId);
        return new Response<>(balance, HttpStatus.OK, "Su balance es de " + balance.toString());
    }

    @Override
    public Long deposit(Long value, Long accountId) {
        if (value <= 0) return -1L;
        return catAccountCustomRepository.deposit(value, accountId);
    }

    @Override
    public Long getBalance(Long accountId) {
        return null;
    }
}
