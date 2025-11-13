package com.noyon.service;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acc.User;

public interface IAuthService {

	public AuthenticationResponse register(User user);
	public AuthenticationResponse login(User user);
	
}
