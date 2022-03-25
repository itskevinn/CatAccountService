package com.kevcode.cataccountservice.infrastructure.helpers;

import javax.persistence.ParameterMode;

public class StoredProcedureParameter {
    String parameterName;
    Class type;
    ParameterMode mode;

    public StoredProcedureParameter(String parameterName, Class type, ParameterMode mode) {
        this.parameterName = parameterName;
        this.type = type;
        this.mode = mode;
    }
}
