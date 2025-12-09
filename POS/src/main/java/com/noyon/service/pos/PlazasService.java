package com.noyon.service.pos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noyon.entity.pos.Plazas;
import com.noyon.repository.pos.PlazasRepository;
@Service
public class PlazasService implements IPlazasService {

	private final PlazasRepository plazasRepository;

	public PlazasService(PlazasRepository plazasRepository) {
		super();
		this.plazasRepository = plazasRepository;
	}

	@Override
	public List<Plazas> getAllPlaza() {
		// TODO Auto-generated method stub
		return plazasRepository.findAll();
	}
	
	
}
