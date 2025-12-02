package com.noyon.service.pos;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.noyon.entity.acl.User;
import com.noyon.entity.pos.HrDevelopment;
import com.noyon.exception.CustomException;
import com.noyon.repository.pos.HrDevelopmentRepository;


@Service
public class HrDevelopmentService implements IHrDevelopmentService {

	private final HrDevelopmentRepository developmentRepository;
	private static final Logger log = LoggerFactory.getLogger(HrDevelopmentService.class);

	public HrDevelopmentService(HrDevelopmentRepository developmentRepository) {
		super();
		this.developmentRepository = developmentRepository;
	}

	@Override
	public HrDevelopment createDivision(HrDevelopment development) {
		// TODO Auto-generated method stub
		HrDevelopment savedDevelopment=new HrDevelopment();
		try {
			Authentication auth= SecurityContextHolder.getContext().getAuthentication();
			
			System.out.println("Auth : "+auth);
			
			User user= (User) auth.getPrincipal();
			development.setCreatedBy(user.getUsername());
			development.setCreated(LocalDateTime.now());
			savedDevelopment=developmentRepository.save(development);
			 
		} catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error: {}", e.getMessage(), e);
		}
		return savedDevelopment;
	}

	@Override
	public List<HrDevelopment> getAllDevelopment() {
		// TODO Auto-generated method stub
		List<HrDevelopment> allDevelopments=developmentRepository.findAll();
		return allDevelopments;
	}
	
	
	
}
