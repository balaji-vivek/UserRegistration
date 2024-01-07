package com.example.UserRegistration.Config;

import com.example.UserRegistration.Service.UserService;
import com.example.UserRegistration.Service.impl.UserServiceIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserServiceIMPL();
    }
}
