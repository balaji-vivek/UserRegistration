package com.example.UserRegistration.Service.impl;

import com.example.UserRegistration.Dto.UserDTO;
import com.example.UserRegistration.Entity.User;
import com.example.UserRegistration.Exception.LoginException;
import com.example.UserRegistration.Exception.RegistrationException;
import com.example.UserRegistration.Repo.UserRepo;
import com.example.UserRegistration.Service.UserService;
import com.example.UserRegistration.util.CommonValidator;
import com.example.UserRegistration.util.PasswordValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CommonValidator commonValidator;

	@Override
	public String addUser(UserDTO userDTO) {
		//null and empty check
		if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() 
				|| userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
			throw new RegistrationException("Username and password are mandatory fields", "MANDATORY_FIELDS");
		}
		//unique username check
		if (userRepo.existsByUsername(userDTO.getUsername())) {
			throw new RegistrationException("Username is already taken", "DUPLICATE_USERNAME");
		}
		
		User existingUser = userRepo.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            throw new RegistrationException("Email address is already registered", "DUPLICATE_EMAIL");
        }
        
        if (!CommonValidator.isValidEmail(userDTO.getEmail())) {
		    throw new RegistrationException("Invalid email address. Please provide a valid email.", "INVALID_EMAIL");
		}
		
		//password arch check
		if (!CommonValidator.isStrongPassword(userDTO.getPassword())) {
			throw new RegistrationException("Weak password. Please use a stronger password.", "WEAK_PASSWORD");
		}


		//final insertion happen using DTO class
		User user = new User(userDTO.getUserid(), userDTO.getUsername(), userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()));
		userRepo.save(user);

		return user.getUsername();
	}

	@Override
	public User login(String username, String password) {
		// here check if the user is exist in database or not
		User user = userRepo.findByUsername(username);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {

			return user;
		} else {
			throw new LoginException("Invalid username or password", "INVALID_CREDENTIALS");
		}
	}

	@Override
	public User findByEmail(String email) {
	    User user = userRepo.findByEmail(email);

	    if (user != null) {
	        return user;
	    } else {
	        // Handle the case where the email is not found
	        throw new LoginException("User not found for email: " , email);
	    }
	}

}
