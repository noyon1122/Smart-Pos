package com.noyon.repository.acc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acc.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
