package com.noyon.controller.pos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.pos.HrDevelopment;
import com.noyon.service.pos.IHrDevelopmentService;

@RestController
@RequestMapping("/api/")
public class HrDevelopmentController {

	private final IHrDevelopmentService developmentService;

	public HrDevelopmentController(IHrDevelopmentService developmentService) {
		super();
		this.developmentService = developmentService;
	}
	
	@PostMapping("development/create")
	public ResponseEntity<HrDevelopment> createDivision(@RequestBody HrDevelopment development)
	{
		HrDevelopment savedDevelopment=developmentService.createDivision(development);
		
		return ResponseEntity.ok(savedDevelopment);
	}
	
	@GetMapping("developments")
	public ResponseEntity<List<HrDevelopment>> getAllDevelopments()
	{
		List<HrDevelopment> allDevelopments=developmentService.getAllDevelopment();
		return ResponseEntity.ok(allDevelopments);
	}
	
}
