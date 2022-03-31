package com.kevcode.cataccountservice.application.CatAccount.service;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransactionRequest;
import com.kevcode.cataccountservice.application.shared.Response;
import com.kevcode.cataccountservice.domain.cataccount.entities.CatAccount;
import com.kevcode.cataccountservice.infrastructure.CatAccountRepositoryCustom;
import com.kevcode.cataccountservice.infrastructure.ICatAccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatAccountService implements ICatAccountService {
    @Autowired
    private CatAccountRepositoryCustom catAccountCustomRepository;
    @Autowired
    private ICatAccountRepository catAccountRepository;
    @Autowired
    private ModelMapper _modelMapper;

    @Override
    public Response<CatAccountDto> save(CatAccountRequest catAccountRequest) {
        CatAccount catAccount = _modelMapper.map(catAccountRequest, CatAccount.class);
        catAccount = catAccountRepository.save(catAccount);
        CatAccountDto catAccountDto = _modelMapper.map(catAccount, CatAccountDto.class);
        return new Response<>(catAccountDto, HttpStatus.OK, "¡Cuenta creada!");
    }

    @Override
    public Response<CatAccountDto> findById(Long id) {
        Optional<CatAccount> catAccount = catAccountRepository.findById(id);
        if (catAccount.isEmpty()) return new Response<>(null, HttpStatus.NO_CONTENT, "Cuenta no encontrada");
        CatAccountDto catAccountDto = _modelMapper.map(catAccount, CatAccountDto.class);
        return new Response<>(catAccountDto, HttpStatus.OK, "Cuenta encontrada");
    }

    @Override
    public Response<List<CatAccountDto>> findAll() {
        List<CatAccount> accounts = catAccountRepository.findAll();
        List<CatAccountDto> accountsDto = _modelMapper.map(accounts, new TypeToken<List<CatAccountDto>>() {
        }.getType());
        return new Response<>(accountsDto, HttpStatus.OK, "Estas son las cuentas");
    }

    @Override
    public Response<List<CatAccountDto>> findAllByPersonId(Long personId) {
        List<CatAccount> accounts = catAccountCustomRepository.findAllByPersonId(personId);
        List<CatAccountDto> accountsDto = _modelMapper.map(accounts, new TypeToken<List<CatAccountDto>>() {
        }.getType());
        return new Response<>(accountsDto, HttpStatus.OK, "Estas son las cuentas");
    }

    @Override
    public Response<Long> withdraw(NationalTransactionRequest request) {
        Long balance = catAccountCustomRepository.withdraw(value, accountId);
        return new Response<>(balance, HttpStatus.OK, "Su nuevo balance es de " + balance.toString());
    }

    @Override
    public Response<Long> deposit(Long value, Long accountId) {
        if (value <= 0) return new Response<>(null, HttpStatus.BAD_REQUEST, "Proporcione valores válidos");
        Long balance = catAccountCustomRepository.deposit(value, accountId);
        return new Response<>(balance, HttpStatus.OK, "Su nuevo balance es de " + balance.toString());
    }

    @Override
    public Response<Long> getBalance(Long accountId) {
        if (accountId <= 0) return new Response<>(null, HttpStatus.BAD_REQUEST, "Proporcione valores válidos");
        Long balance = catAccountCustomRepository.getBalance(accountId);
        return new Response<>(balance, HttpStatus.OK, "Su balance es de " + balance.toString());
    }
}
