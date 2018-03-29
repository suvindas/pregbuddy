package com.pregbuddy.base;


public abstract class ResponseBase {
    private Boolean hasError;
    private Integer errorCode;
    private String message;


    public boolean isHasError() {
        return hasError;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
