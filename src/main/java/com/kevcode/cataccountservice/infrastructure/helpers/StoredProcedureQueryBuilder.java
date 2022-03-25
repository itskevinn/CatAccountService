package com.kevcode.cataccountservice.infrastructure.helpers;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StoredProcedureQueryBuilder {


    public StoredProcedureQuery build(@NotNull String procedureName,
                                      @NotNull List<StoredProcedureParameter> parameters,
                                      EntityManager entityManager) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
        for (StoredProcedureParameter parameter : parameters) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter.parameterName, parameter.type, parameter.mode);
        }
        return storedProcedureQuery;
    }
}
