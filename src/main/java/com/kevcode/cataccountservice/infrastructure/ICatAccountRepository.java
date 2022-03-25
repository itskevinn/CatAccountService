package com.kevcode.cataccountservice.infrastructure;

import com.kevcode.cataccountservice.domain.cataccount.entities.CatAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatAccountRepository extends JpaRepository<CatAccount, Long> {

}

