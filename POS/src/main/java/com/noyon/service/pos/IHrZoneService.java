package com.noyon.service.pos;

import java.util.List;

import com.noyon.entity.pos.HrZone;

public interface IHrZoneService {

	public HrZone createZone(HrZone zone);
	public List<HrZone> getAllZones();
}
