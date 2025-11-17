package com.noyon.repository.acl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User>findByEmail(String email);
	Optional<User>findByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}
