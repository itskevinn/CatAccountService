package com.kevcode.cataccountservice.infrastructure;

import com.kevcode.cataccountservice.domain.cataccount.entities.CatAccount;

import java.util.List;

public interface ICatAccountRepositoryCustom {
    Long withdraw(Long value, Long accountId);
    Long deposit(Long value, Long accountId);
    Long getBalance(Long accountId);
    List<CatAccount> findAllByPersonId(Long personId);
}
