package com.example.UserRegistration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserRegistration.Entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
