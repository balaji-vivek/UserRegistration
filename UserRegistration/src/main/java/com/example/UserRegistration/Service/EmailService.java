package com.example.UserRegistration.Service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
    
    void sendHtmlMessage(String to, String subject, String htmlContent);

}
