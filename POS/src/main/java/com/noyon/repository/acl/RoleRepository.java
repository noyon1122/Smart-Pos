package com.noyon.repository.acl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByAuthority(String authority);
	Optional<Role>findById(Long id);
	
}
