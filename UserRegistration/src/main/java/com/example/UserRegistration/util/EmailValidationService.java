package com.example.UserRegistration.util;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidationService {

    public static boolean isValidEmail(String email) {
        // Use EmailValidator from Apache Commons Validator
        return EmailValidator.getInstance().isValid(email);
    }
}
