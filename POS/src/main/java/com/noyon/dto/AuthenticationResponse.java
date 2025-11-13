package com.noyon.dto;

public class AuthenticationResponse {

	private int statusCode;
	private String message;
	private String accessToken;
	private String refreshToken;
	private UserDto userDto;
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationResponse(int statusCode, String message, String accessToken, String refreshToken,
			UserDto userDto) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userDto = userDto;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [statusCode=" + statusCode + ", message=" + message + ", accessToken="
				+ accessToken + ", refreshToken=" + refreshToken + ", userDto=" + userDto + "]";
	}
	
	
	
}
