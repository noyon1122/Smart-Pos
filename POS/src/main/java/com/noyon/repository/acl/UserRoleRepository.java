package com.noyon.repository.acl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	
}
