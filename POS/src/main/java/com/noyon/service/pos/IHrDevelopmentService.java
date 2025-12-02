package com.noyon.service.pos;

import java.util.List;

import com.noyon.entity.pos.HrDevelopment;

public interface IHrDevelopmentService {
	
	public HrDevelopment createDivision(HrDevelopment development);
	public List<HrDevelopment> getAllDevelopment();
}
