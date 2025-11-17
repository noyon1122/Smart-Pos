package com.noyon.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.dto.UserDto;
import com.noyon.entity.acl.User;
import com.noyon.entity.token.Token;
import com.noyon.exception.CustomException;
import com.noyon.jwt.JwtService;
import com.noyon.repository.acl.UserRepository;
import com.noyon.repository.token.TokenRepository;
import com.noyon.service.IAuthService;
import com.noyon.utils.Utils;

@Service 
public class AuthService implements IAuthService {
	
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	

	public AuthService(JwtService jwtService, UserRepository userRepository, TokenRepository tokenRepository,
			AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
		super();
		this.jwtService = jwtService;
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AuthenticationResponse register(User user) {
		AuthenticationResponse response=new AuthenticationResponse();
		try {
			
			if(userRepository.existsByEmail(user.getEmail())) {
				throw new CustomException("Opps!! User already exists");
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setCreated(LocalDateTime.now());
			user.setCreatedBy("SYSTEM");
			user.setEnabled(true);
			user.setAccountExpired(false);
			user.setAccountLocked(false);
			user.setPasswordExpired(false);
			User savedUser=userRepository.save(user);
			UserDto userDto =Utils.mapUserEntityToUserDto(savedUser);
			String accessToken=jwtService.generateAccessToken(savedUser);
			String refreshToken=jwtService.generateRefreshToken(savedUser);
			savedUserToken(accessToken,refreshToken,savedUser);
			response.setStatusCode(200);
			response.setAccessToken(accessToken);
			response.setRefreshToken(refreshToken);
			response.setUserDto(userDto);
			response.setMessage("User Registration have been done Successfully");
			
		}catch (CustomException e) {
			// TODO: handle exception
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Error Saving a User"+e.getMessage());
		}
		return response;
	}
	
	

	@Override
	public AuthenticationResponse login(User user) {
		// TODO Auto-generated method stub
		
		AuthenticationResponse response=new AuthenticationResponse();
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			User saveUser=userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new CustomException("Opps Sorry!! User not found"));
			String accessToken=jwtService.generateAccessToken(saveUser);
			String refreshToken=jwtService.generateRefreshToken(saveUser);
			UserDto userDto=Utils.mapUserEntityToUserDto(saveUser);
			invalidUserToken(saveUser);
			savedUserToken(accessToken, refreshToken, saveUser);
			response.setStatusCode(200);
			response.setAccessToken(accessToken);
			response.setRefreshToken(refreshToken);
			response.setUserDto(userDto);
			response.setMessage("User login Successfully");
			
		} catch (CustomException e) {
			// TODO: handle exception
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(400);
			response.setMessage("Error loging a user "+e.getMessage());
		}
		return response;
	}
	
	
	private void savedUserToken(String accessToken,String refreshToken, User user) {
		Token token=new Token();
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setUser(user);
		token.setLogout(false);
		tokenRepository.save(token);
	}
	
	private void invalidUserToken(User user) {
		List<Token> tokenList=tokenRepository.findAllTokenByUserId(user.getId());
		
		if(tokenList.isEmpty()) {
			return;
		}
		
		tokenList.forEach(t->t.setLogout(true));
		tokenRepository.saveAll(tokenList);
	}

	
}
