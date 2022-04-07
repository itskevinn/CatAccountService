package com.kevcode.cataccountservice.infrastructure;

import com.kevcode.cataccountservice.domain.cataccount.entities.CatAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CatAccountRepositoryCustom implements ICatAccountRepositoryCustom {
    private final JdbcTemplate _jdbcTemplate;

    public CatAccountRepositoryCustom(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long withdraw(Long value, Long accountId) {
        SimpleJdbcCall jdbcCall = getSimpleJdbcCall("SP_WITHDRAW");
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("value", value).addValue("accountId", accountId);
        Map<String, Object> executionResult = jdbcCall.execute(parameterSource);
        return (Long) executionResult.get("balance");
    }


    @Override
    public Long deposit(Long value, Long accountId) {
        SimpleJdbcCall jdbcCall = getSimpleJdbcCall("SP_DEPOSIT");
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("value", value).addValue("accountId", accountId);
        Map<String, Object> executionResult = jdbcCall.execute(parameterSource);
        return (Long) executionResult.get("balance");
    }


    @Override
    public Long getBalance(Long accountId) {
        SimpleJdbcCall jdbcCall = getSimpleJdbcCall("SP_GET_BALANCE");
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("accountId", accountId);
        Map<String, Object> executionResult = jdbcCall.execute(parameterSource);
        return (Long) executionResult.get("balance");
    }

    @Override
    public List<CatAccount> findAllByPersonId(Long personId) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(_jdbcTemplate);
        String sql = "select * from cataccount where personId = :personId";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("personId", personId);
        List<CatAccount> catAccounts = namedParameterJdbcTemplate.query(sql, argMap, createResultSetExtractor());
        return catAccounts;
    }

    private ResultSetExtractor<List<CatAccount>> createResultSetExtractor() {
        return rs -> {
            List<CatAccount> catAccounts = new ArrayList<>();
            while (rs.next()) {
                CatAccount catAccount = new CatAccount();
                catAccount.setPersonId(rs.getLong("personId"));
                catAccount.setBalance(rs.getLong("balance"));
                catAccount.setId(rs.getLong("id"));
            }
            return catAccounts;
        };
    }

    private SimpleJdbcCall getSimpleJdbcCall(String storedProcedureName) {
        return new SimpleJdbcCall(_jdbcTemplate).withProcedureName(storedProcedureName);
    }
}
