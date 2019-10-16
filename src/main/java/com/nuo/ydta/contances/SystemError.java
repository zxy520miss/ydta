package com.nuo.ydta.contances;

import com.nuo.ydta.exception.IError;

public enum SystemError implements IError {
    SYSTEM_INTERNAL_ERROR("0000", "System Internal Error"),
    INVALID_TOKEN("0004", "Invalid token"),
    CALL_THIRD_SYSTEM_ERROR("9998", "call third system error"),
    OTHER("9999", "unrecognized error");

    String errorCode;
    String errorMessage;

    SystemError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private static final String nameSpace = "SYS";

    @Override
    public String getNamespace() {
        return nameSpace;
    }

    @Override
    public String getErrorCode() {
        return nameSpace + "." + errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}