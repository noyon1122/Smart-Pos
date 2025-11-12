package com.noyon.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.stereotype.Service;

import com.noyon.entity.acc.User;
import com.noyon.repository.token.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private TokenRepository tokenRepository;
	
	private final String SECURITY_KEY="d169552a202ace4ed9b31a326df08noyon3e197a10213030f7c4be596ba99b6";
	private long accessTokenExpire=8640000;
	 

	 private long refreshTokenExpire=604800000;


	 public JwtService(TokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
		
	 }
	 
	 //Generate Token
	 
	 private String generateToken(User user,long expiredTime) {
		 return Jwts
				 .builder()
				 .subject(user.getUsername())
				 .issuedAt(new Date(System.currentTimeMillis()))
				 .expiration(new Date(System.currentTimeMillis()+expiredTime))
				 .signWith(getSigninKey())
				 .compact();
	 }
	 
	 //generate accessToken
	 public String generateAccessToken(User user) {
		 return generateToken(user, accessTokenExpire);
	 }
	 
	 //generated refresh token 
	 public String generateRefreshToken(User user)
	 {
		 return generateToken(user, refreshTokenExpire);
	 }
	 
	 private SecretKey getSigninKey() {
		 byte [] keyBytes=Decoders.BASE64URL.decode(SECURITY_KEY);
		 return Keys.hmacShaKeyFor(keyBytes);
	 }
	 
	 //extract all claims 
	 
	 private Claims extractAllClaims(String token) {
		 return Jwts
				 .parser()
				 .verifyWith(getSigninKey())
				 .build()
				 .parseSignedClaims(token)
				 .getPayload();
	 }
	 
	 //extract claims
	 
	 public <T> T extractClaim(String token,Function<Claims, T>resolver) {
		 Claims claims=extractAllClaims(token);
		 return resolver.apply(claims);
	 }
	 
	 public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
	}
		 
	 
	 public Date extractExpiration(String token) {
		 return extractClaim(token, Claims::getExpiration);
	 }
	 
	 public boolean isTokenExpired(String token) {
		 return extractExpiration(token).before(new Date());
	 }
	 
	 public boolean isValidToken(String token,UserDetails userDetails) {
		 String username=extractUsername(token);
		 
		 boolean validToken=tokenRepository.findByAccessToken(token).map(t-> !t.isLogout()).orElse(false);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && validToken);
	 }
	 
	 public boolean isvalidRefreshToken(String token,UserDetails userDetails)
	 {
		 String username=extractUsername(token);
		 boolean isValidRefreshToken=tokenRepository.findByRefreshToken(token).map(t-> !t.isLogout()).orElse(false);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && isValidRefreshToken);
	 }
	 
}
