package com.noyon.service.acl;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;

public interface IUserService {

	public AuthenticationResponse createUser(User user);
}
