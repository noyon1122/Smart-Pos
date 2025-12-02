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
import com.noyon.entity.pos.HrZone;
import com.noyon.exception.CustomException;
import com.noyon.repository.pos.HrZoneRepository;

@Service
public class HrZoneService implements IHrZoneService {

	private final HrZoneRepository zoneRepository;
	
	private static final Logger log = LoggerFactory.getLogger(HrDevelopmentService.class);

	public HrZoneService(HrZoneRepository zoneRepository) {
		super();
		this.zoneRepository = zoneRepository;
	}

	@Override
	public HrZone createZone(HrZone zone) {
		// TODO Auto-generated method stub
		HrZone savedZone=new HrZone();
		try {
			Authentication auth= SecurityContextHolder.getContext().getAuthentication();
			
			System.out.println("Auth : "+auth);
			
			User user= (User) auth.getPrincipal();
			zone.setCreatedBy(user.getUsername());
			zone.setCreated(LocalDateTime.now());
			savedZone=zoneRepository.save(zone);
			 
		} catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error: {}", e.getMessage(), e);
		}
		return savedZone;
		
	}

	@Override
	public List<HrZone> getAllZones() {
		// TODO Auto-generated method stub
		List<HrZone> allZones=zoneRepository.findAll();
		return allZones;
	}
	
	
}
