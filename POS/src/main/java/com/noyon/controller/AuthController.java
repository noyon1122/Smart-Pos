package com.noyon.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;
import com.noyon.service.acl.IAuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	private final IAuthService authService;

	public AuthController(IAuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
		AuthenticationResponse response=authService.register(user);
		return ResponseEntity.ok(response);
	}
	@PostMapping("login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User user){
		AuthenticationResponse response=authService.login(user);
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
