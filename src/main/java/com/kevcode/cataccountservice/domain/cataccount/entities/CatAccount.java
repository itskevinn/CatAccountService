package com.kevcode.cataccountservice.domain.cataccount.entities;

public class CatAccount {
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
