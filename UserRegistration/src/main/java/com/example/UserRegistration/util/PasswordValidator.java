package com.example.UserRegistration.util;

public class PasswordValidator {

    public static boolean isPasswordStrong(String password) {
        // Check for minimum length
        if (password.length() < 8) {
            return false;
        }

        // Check for the presence of both letters and numbers
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasUppercase = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLetter = true;
                if (Character.isUpperCase(ch)) {
                    hasUppercase = true;
                }
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }

            // If both letters, including at least one uppercase, and numbers are found, consider the password strong
            if (hasLetter && hasUppercase && hasDigit) {
                return true;
            }
        }

        // If the loop completes and either letters, uppercase letters, or numbers are missing, consider the password weak
        return false;
    }
}
