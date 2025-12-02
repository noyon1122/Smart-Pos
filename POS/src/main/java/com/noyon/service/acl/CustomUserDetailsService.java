package com.noyon.service.acl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.noyon.entity.acl.User;
import com.noyon.repository.acl.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOpt=userRepository.findByUsername(username);
		return userOpt.orElseThrow(()-> new UsernameNotFoundException("User not found!!!"));
	}

	
}
