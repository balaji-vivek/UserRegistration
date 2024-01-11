package com.example.UserRegistration.Controller;

import com.example.UserRegistration.Dto.UserDTO;
import com.example.UserRegistration.Entity.PasswordResetToken;
import com.example.UserRegistration.Entity.User;
import com.example.UserRegistration.Exception.LoginException;
import com.example.UserRegistration.Exception.RegistrationException;
import com.example.UserRegistration.Repo.UserRepo;
import com.example.UserRegistration.Service.EmailService;
import com.example.UserRegistration.Service.PasswordResetTokenService;
import com.example.UserRegistration.Service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordResetTokenService tokenService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserRepo userRepo;

	@PostMapping(path = "/registerUser")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserDTO userDTO) {
		Map<String, String> response = new HashMap<>();

		try {
			String username = userService.addUser(userDTO);

			response.put("status", "success");
			response.put("message", "User registered successfully");
			response.put("username", username);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (RegistrationException e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
			response.put("errorCode", e.getErrorCode());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

	}
	
	 @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> loginUser(@RequestParam String username, @RequestParam String password) {
	        Map<String, String> response = new HashMap<>();

	        try {
	            User user = userService.login(username, password);

	            response.put("status", "success");
	            response.put("message", "Login successful");
	            response.put("username", user.getUsername());

	            return ResponseEntity.ok(response);
	        } catch (LoginException e) {
	            response.put("status", "error");
	            response.put("message", e.getMessage());
	            response.put("errorCode", e.getErrorCode());

	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	        }
	    }
	 
	 @PostMapping("/forgot-password")
	    public ResponseEntity<Map<String, String>> forgotPassword(@RequestParam String email) {
	        Map<String, String> response = new HashMap<>();

	        try {
	            User user = userRepo.findByEmail(email);

	            if (user == null) {
	                response.put("status", "error");
	                response.put("message", "Email not found");
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	            }

	            PasswordResetToken token = tokenService.createToken(user);
	            sendResetEmail(user, token.getToken());

	            response.put("status", "success");
	            response.put("message", "Password reset email sent successfully");
	            return ResponseEntity.ok(response);

	        } catch (RegistrationException e) {
	            response.put("status", "error");
	            response.put("message", e.getMessage());
	            response.put("errorCode", e.getErrorCode());

	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	    }

	 private void sendResetEmail(User user, String token) {
		    String resetUrl = "https://nettstedtech.in/reset-password?token=" + token;
		    ClassPathResource resource = new ClassPathResource("emailTemplates/password-reset-email.html");
            InputStream inputStream=null;
			try {
				inputStream = resource.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
            String template = convertInputStreamToString(inputStream, StandardCharsets.UTF_8);
            template = template.replace("{USERNAME}", user.getUsername());
            template = template.replace("{RESET_URL}", resetUrl);
            template = template.replace("{EXPIRY_TIME}", "15 minutes");
            template = template.replace("{SUPPORT_EMAIL}", "nettstedtech@gmail.com");
            template = template.replace("{COMPANY_NAME}", "Nettsted Tech");


            emailService.sendHtmlMessage(user.getEmail(), "Password Reset Request", template);
		}
	 private String convertInputStreamToString(InputStream inputStream, Charset charset) {
	        try (Scanner scanner = new Scanner(inputStream, charset.name())) {
	            return scanner.useDelimiter("\\A").next();
	        }
	    }
	 
	 


}
