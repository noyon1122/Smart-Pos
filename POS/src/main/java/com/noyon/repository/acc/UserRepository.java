package com.noyon.repository.acc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acc.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User>findByEmail(String email);
}
