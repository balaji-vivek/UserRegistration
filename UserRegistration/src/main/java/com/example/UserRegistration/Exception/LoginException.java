package com.example.UserRegistration.Exception;

public class LoginException extends RuntimeException {

    private String errorCode;

    public LoginException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
