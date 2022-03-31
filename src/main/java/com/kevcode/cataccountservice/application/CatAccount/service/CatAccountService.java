package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransaction;
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
    public Response<Long> withdraw(NationalTransaction nationalTransaction) {
        Long accountId = nationalTransaction.getAccountId();
        Long value = nationalTransaction.getValue();
        if (accountId <= 0)
            return new Response<>(0L, HttpStatus.BAD_REQUEST, "Por favor, digite un id de cuenta válido.");
        if (value <= 0) return new Response<>(0L, HttpStatus.BAD_REQUEST, "Por favor, digite un valor válido.");
        Long balance = catAccountCustomRepository.withdraw(value, accountId);
        return new Response<>(balance, HttpStatus.OK, "Su nuevo balance es de " + balance.toString());
    }


    @Override
    public Response<Long> deposit(NationalTransaction nationalTransaction) {
        Long accountId = nationalTransaction.getAccountId();
        Long value = nationalTransaction.getValue();
        if (numberLowerOrEqualsToZero(accountId))
            return new Response<>(0L, HttpStatus.BAD_REQUEST, "Por favor, digite un id de cuenta válido.");
        if (numberLowerOrEqualsToZero(value))
            return new Response<>(0L, HttpStatus.BAD_REQUEST, "Por favor, digite un valor válido.");
        Long balance = catAccountCustomRepository.deposit(value, accountId);
        return new Response<>(balance, HttpStatus.OK, "Su nuevo balance es de " + balance.toString());
    }

    @Override
    public Response<Long> getBalance(Long accountId) {
        if (numberLowerOrEqualsToZero(accountId))
            return new Response<>(0L, HttpStatus.BAD_REQUEST, "Por favor, digite un valor válido");
        Long balance = catAccountCustomRepository.getBalance(accountId);
        return new Response<>(balance, HttpStatus.OK, "Su balance es de " + balance);
    }

    private boolean numberLowerOrEqualsToZero(Long value) {
        return value <= 0;
    }


}
