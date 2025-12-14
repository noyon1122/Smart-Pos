package com.noyon.service.acl;

import java.util.List;

import com.noyon.dto.AuthenticationResponse;

import com.noyon.entity.acl.User;

public interface IUserService {

	public AuthenticationResponse createUser(User user);
	public List<User> getAllUser();
	public User getUserById(Long id);
}
