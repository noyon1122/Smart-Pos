package com.noyon.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.noyon.entity.token.Token;
import com.noyon.repository.token.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

	private final TokenRepository tokenRepository;

	public CustomLogoutHandler(TokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		
		String authHeader=request.getHeader("Authorization");
		
		if(authHeader ==null || !authHeader.startsWith("Bearer "))
		{
			return;
		}
		
		String token=authHeader.substring(7);
		
		Token storedToken=tokenRepository.findByAccessToken(token).orElse(null);
		
		if(storedToken !=null) {
			storedToken.setLogout(true);
			tokenRepository.save(storedToken);
		}
		
	}
	
	
}
