package com.noyon.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
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

	    return http
	            .csrf(csrf -> csrf.disable())
	            .cors(Customizer.withDefaults())
	            .authorizeHttpRequests(auth -> auth
	                
	                // public endpoints
	                .requestMatchers("/api/auth/login").permitAll()
	                .requestMatchers("/api/auth/me").authenticated()

	                // everything else → run DB-based check
	                .anyRequest().access((authentication, context) -> {
	                    String path = context.getRequest().getRequestURI();
	                    
	                    if (path.startsWith("/api")) {
	                        path = path.substring(4);
	                    }

	                    // Get roles of logged-in user
	                    var authorities = authentication.get().getAuthorities()
	                            .stream()
	                            .map(a -> a.getAuthority())
	                            .toList();

	                    boolean allowed = urlRoleMappingService.canAccess(path, authorities);
	                    return new AuthorizationDecision(allowed);
	                   
	                })
	            )
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
