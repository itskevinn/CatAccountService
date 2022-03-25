package com.kevcode.cataccountservice.infrastructure;

import com.kevcode.cataccountservice.infrastructure.helpers.NationalTransactions;
import com.kevcode.cataccountservice.infrastructure.helpers.StoredProcedureParameter;
import com.kevcode.cataccountservice.infrastructure.helpers.StoredProcedureQueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CatAccountRepositoryCustom implements ICatAccountRepositoryCustom {
    StoredProcedureQueryBuilder storedProcedureQueryBuilder;
    @PersistenceContext
    private EntityManager entityManager;

    public CatAccountRepositoryCustom() {
        storedProcedureQueryBuilder = new StoredProcedureQueryBuilder();
    }

    @Override
    public Long withdraw(Long value, Long accountId) {
        StoredProcedureQuery storedProcedure = getWithdrawStoredProcedureQuery();
        storedProcedure.setParameter("value", value);
        storedProcedure.setParameter("accountId", accountId);
        storedProcedure.execute();
        return (Long) storedProcedure.getOutputParameterValue("balance");
    }


    @Override
    public Long deposit(Long value, Long accountId) {
        StoredProcedureQuery storedProcedure = getDepositStoredProcedureQuery();
        storedProcedure.setParameter("value", value);
        storedProcedure.setParameter("accountId", accountId);
        storedProcedure.execute();
        return (Long) storedProcedure.getOutputParameterValue("balance");
    }


    @Override
    public Long getBalance(Long accountId) {
        StoredProcedureQuery storedProcedure = getBalanceStoredProcedureQuery();
        storedProcedure.setParameter("accountId", accountId);
        storedProcedure.execute();
        return (Long) storedProcedure.getOutputParameterValue("balance");
    }

    @Override
    public List findAllByPersonId(Long personId) {
        return entityManager.createQuery(
                        "SELECT p FROM Person p WHERE person.id = :personId")
                .setParameter("personId", personId)
                .getResultList();
    }

    private StoredProcedureQuery getBalanceStoredProcedureQuery() {
        String storedProcedureName = "SP_CAT_ACCOUNT_GET_BALANCE";
        return storedProcedureQueryBuilder.build(storedProcedureName,
                buildStoredProcedureParameterList(NationalTransactions.GET_BALANCE),
                entityManager);
    }

    private StoredProcedureQuery getWithdrawStoredProcedureQuery() {
        String storedProcedureName = "SP_CAT_ACCOUNT_WITHDRAW";
        return storedProcedureQueryBuilder.build(storedProcedureName,
                buildStoredProcedureParameterList(NationalTransactions.WITHDRAW),
                entityManager);
    }

    private StoredProcedureQuery getDepositStoredProcedureQuery() {
        String storedProcedureName = "SP_CAT_ACCOUNT_DEPOSIT";
        return storedProcedureQueryBuilder.build(storedProcedureName,
                buildStoredProcedureParameterList(NationalTransactions.DEPOSIT),
                entityManager);
    }

    private List<StoredProcedureParameter> buildStoredProcedureParameterList(NationalTransactions transactionType) {
        switch (transactionType) {
            case DEPOSIT:
            case WITHDRAW: {
                List<StoredProcedureParameter> parameterList = new ArrayList<>();
                parameterList.add(new StoredProcedureParameter("value", Long.class, ParameterMode.IN));
                parameterList.add(new StoredProcedureParameter("accountId", Long.class, ParameterMode.IN));
                parameterList.add(new StoredProcedureParameter("balance", Long.class, ParameterMode.OUT));
                return parameterList;
            }
            case GET_BALANCE: {
                List<StoredProcedureParameter> parameterList = new ArrayList<>();
                parameterList.add(new StoredProcedureParameter("accountId", Long.class, ParameterMode.IN));
                parameterList.add(new StoredProcedureParameter("balance", Long.class, ParameterMode.OUT));
                return parameterList;
            }
            default:
                throw new IllegalStateException("Por favor, proporcione un tipo de transacción válido");
        }
    }

}
