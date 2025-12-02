package com.noyon.service.acl;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;

public interface IAuthService {

	public AuthenticationResponse register(User user);
	public AuthenticationResponse login(User user);
	
}
