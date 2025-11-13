package com.noyon.entity.token;

import com.noyon.entity.acc.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String accessToken;
    private String refreshToken;
    private boolean logout;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Token(Long id, String accessToken, String refreshToken, boolean logout, User user) {
		super();
		this.id = id;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.logout = logout;
		this.user = user;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}
	
	
    
    
}
