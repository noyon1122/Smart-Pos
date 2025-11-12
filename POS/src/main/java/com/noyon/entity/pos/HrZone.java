package com.noyon.entity.pos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HrZone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
