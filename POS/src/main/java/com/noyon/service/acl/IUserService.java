package com.noyon.service.acl;
import org.springframework.data.domain.Page;
import com.noyon.dto.AuthenticationResponse;
import com.noyon.entity.acl.User;

public interface IUserService {

	public AuthenticationResponse createUser(User user);
	//public List<User> getAllUser();
	public User getUserById(Long id);
	public User updateUser(User user,Long id);
	
	Page<User>getAllUser(
			int page,
			int size,
			Long plazas,
			Long role,
			String username,
			String fullName
			);
	
	
}
