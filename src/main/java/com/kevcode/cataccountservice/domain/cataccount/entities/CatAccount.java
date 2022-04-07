package com.kevcode.cataccountservice.domain.cataccount.entities;

import com.kevcode.cataccountservice.domain.shared.EntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class CatAccount extends EntityBase {
    private Long balance;
    private Long personId;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getBalance() {
        return this.balance;
    }

    public void setBalance(Long value) {
        this.balance = value;
    }
}
