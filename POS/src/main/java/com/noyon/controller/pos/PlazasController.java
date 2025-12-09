package com.noyon.controller.pos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.pos.Plazas;
import com.noyon.service.pos.IPlazasService;

@RestController
@RequestMapping("/api/")
public class PlazasController {

	private final IPlazasService plazasService;

	public PlazasController(IPlazasService plazasService) {
		super();
		this.plazasService = plazasService;
	}
	
	@GetMapping("plazas")
	public ResponseEntity<List<Plazas>> getAllPlazas()
	{
		List<Plazas> allPlaza=plazasService.getAllPlaza();
		return ResponseEntity.ok(allPlaza);
	}
}
