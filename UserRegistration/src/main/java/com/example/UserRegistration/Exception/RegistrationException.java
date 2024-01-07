package com.example.UserRegistration.Exception;

public class RegistrationException extends RuntimeException {

    private String errorCode;

    public RegistrationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
