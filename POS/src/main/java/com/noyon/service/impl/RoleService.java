package com.noyon.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.noyon.entity.acc.Role;
import com.noyon.entity.acc.User;
import com.noyon.exception.CustomException;
import com.noyon.repository.acc.RoleRepository;
import com.noyon.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	
	private final RoleRepository roleRepository;
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);
	

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}


	@Override
	public Role create(Role role) {
		// TODO Auto-generated method stub
		Role savedRole=new Role();
		try {
			if(roleRepository.existsByAuthority(role.getAuthority())) {
				throw new CustomException("Opps !! This authority already present");
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User adminUser = (User) auth.getPrincipal();
			role.setCreated(LocalDateTime.now());
			role.setCreatedBy(adminUser.getId().toString());
			savedRole=roleRepository.save(role);
		}catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error: {}", e.getMessage(), e);
		}
		return savedRole;
	}

	
}
