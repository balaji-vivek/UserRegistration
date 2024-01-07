package com.example.UserRegistration.Repo;

import com.example.UserRegistration.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
	boolean existsByUsername(String username);
	User findByEmail(String email);
	User findByUsername(String username);
}
