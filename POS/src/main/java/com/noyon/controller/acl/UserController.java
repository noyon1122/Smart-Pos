package com.noyon.controller.acl;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) Long plazas,
			@RequestParam(required = false) Long role,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String fullName
			)
	{
		return ResponseEntity.ok(userService.getAllUser(page, size, plazas, role, username, fullName));
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id)
	{
		
		try {
			User user=userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	@PostMapping("user/update/{id}")
	public ResponseEntity<User> updateuser(@RequestBody User user,@PathVariable Long id)
	{
		try {
			User updateUser=userService.updateUser(user, id);
			return ResponseEntity.ok(updateUser);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
