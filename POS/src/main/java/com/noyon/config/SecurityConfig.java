package com.noyon.config;


import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.noyon.jwt.JwtFilter;
import com.noyon.service.acl.CustomUserDetailsService;
import com.noyon.service.acl.UrlRoleMappingService;

@Configuration
public class SecurityConfig {

	private final JwtFilter jwtFilter;
	private final CustomLogoutHandler customLogoutHandler;
	private final CustomUserDetailsService customUserDetailsService;
	private final UrlRoleMappingService urlRoleMappingService;
	
	
	
	
	public SecurityConfig(JwtFilter jwtFilter, CustomLogoutHandler customLogoutHandler,
			CustomUserDetailsService customUserDetailsService, UrlRoleMappingService urlRoleMappingService) {
		super();
		this.jwtFilter = jwtFilter;
		this.customLogoutHandler = customLogoutHandler;
		this.customUserDetailsService = customUserDetailsService;
		this.urlRoleMappingService = urlRoleMappingService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 // Load URL -> Roles dynamically
        Map<String, String[]> urlRoleMap = urlRoleMappingService.getUrlRoleMap();

        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {

                    // Public auth endpoints
                    auth.requestMatchers("/api/auth/login").permitAll();

                    // Dynamic URL -> roles from DB
                    urlRoleMap.forEach((url, roles) -> {
                        // Add ant pattern to match all subpaths
                        auth.requestMatchers(url + "/**").hasAnyAuthority(roles);
                    });


                    // Any other request
                    auth.anyRequest().denyAll();
                })
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler((req, res, authn) ->
                                SecurityContextHolder.clearContext())
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
