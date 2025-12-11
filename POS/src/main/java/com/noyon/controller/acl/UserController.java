package com.noyon.controller.acl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;
import com.noyon.service.acl.IUserService;

@RestController
@RequestMapping("/api/")
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("user/create")
	public ResponseEntity<AuthenticationResponse> create(@RequestBody User user)
	{
		AuthenticationResponse response=userService.createUser(user);
		return ResponseEntity.ok(response);
	}
	@GetMapping("users")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> userList=userService.getAllUser();
		return ResponseEntity.ok(userList);
	}
}
