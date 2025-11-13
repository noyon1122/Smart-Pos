package com.noyon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.noyon.jwt.JwtFilter;
import com.noyon.jwt.JwtService;
import com.noyon.service.impl.CustomUserDetailsService;


@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

	private final JwtFilter jwtFilter;
	private final CustomLogoutHandler customLogoutHandler;
	private final CustomUserDetailsService customUserDetailsService;
	
	
	
	public SecurityConfig(JwtFilter jwtFilter, CustomLogoutHandler customLogoutHandler,
			CustomUserDetailsService customUserDetailsService, JwtService jwtService) {
		super();
		this.jwtFilter = jwtFilter;
		this.customLogoutHandler = customLogoutHandler;
		this.customUserDetailsService = customUserDetailsService;
		this.jwtService = jwtService;

	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(req -> req
						.requestMatchers("api/auth/**").permitAll()
						.requestMatchers("api/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						)
				.userDetailsService(customUserDetailsService)
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
				.logout(l->l
						.logoutUrl("/logout")
						.addLogoutHandler(customLogoutHandler)
						.logoutSuccessHandler((request,response,authentication)->SecurityContextHolder.clearContext())
						)
				.build();
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
}
