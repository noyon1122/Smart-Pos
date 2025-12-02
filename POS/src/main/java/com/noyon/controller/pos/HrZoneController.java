package com.noyon.controller.pos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.pos.HrZone;
import com.noyon.service.pos.IHrZoneService;

@RestController
@RequestMapping("/api/zones")
public class HrZoneController {

	private final IHrZoneService zoneService;

	public HrZoneController(IHrZoneService zoneService) {
		super();
		this.zoneService = zoneService;
	}
	
	@PostMapping("")
	public ResponseEntity<HrZone> create(@RequestBody HrZone zone)
	{
		HrZone savedZone=zoneService.createZone(zone);
		return ResponseEntity.ok(savedZone);
	}
}
