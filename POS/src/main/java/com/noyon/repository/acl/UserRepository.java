package com.noyon.repository.acl;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

	Optional<User>findByEmail(String email);
	Optional<User>findByUsername(String username);
	Optional<User>findById(Long id);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	Page<User>findAll(Pageable pageable);
}
