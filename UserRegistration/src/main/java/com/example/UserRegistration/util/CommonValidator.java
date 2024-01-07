package com.example.UserRegistration.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CommonValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isStrongPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Add more validation methods as needed

    public static void main(String[] args) {
        String email = "test@example.com";
        String password = "StrongP@ss1";

        if (isValidEmail(email)) {
            System.out.println("Email is valid.");
        } else {
            System.out.println("Invalid email format.");
        }

        if (isStrongPassword(password)) {
            System.out.println("Password is strong.");
        } else {
            System.out.println("Weak password. Please use a stronger password.");
        }
    }
}
