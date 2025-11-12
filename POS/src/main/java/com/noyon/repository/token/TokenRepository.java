package com.noyon.repository.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.token.Token;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token>findByAccessToken(String accessToken);
	Optional<Token> findByRefreshToken(String refreshToken);
}
