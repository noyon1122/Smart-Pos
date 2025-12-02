package com.noyon.repository.pos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.pos.HrZone;

@Repository
public interface HrZoneRepository extends JpaRepository<HrZone, Long> {

	boolean existsByTitle(String title);
} 
