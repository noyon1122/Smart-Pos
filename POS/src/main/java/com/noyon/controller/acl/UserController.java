package com.noyon.controller.acl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;
import com.noyon.service.acl.IUserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("create")
	public ResponseEntity<AuthenticationResponse> create(@RequestBody User user)
	{
		AuthenticationResponse response=userService.createUser(user);
		return ResponseEntity.ok(response);
	}
}
