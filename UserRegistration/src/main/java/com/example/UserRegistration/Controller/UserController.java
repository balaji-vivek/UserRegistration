package com.example.UserRegistration.Controller;

import com.example.UserRegistration.Dto.UserDTO;
import com.example.UserRegistration.Entity.User;
import com.example.UserRegistration.Exception.LoginException;
import com.example.UserRegistration.Exception.RegistrationException;
import com.example.UserRegistration.Service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
	@Autowired
	private UserService userService;

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

}
