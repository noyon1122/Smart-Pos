package com.noyon.repository.pos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.pos.HrDevelopment;
@Repository
public interface HrDevelopmentRepository extends JpaRepository<HrDevelopment, Long> {

	boolean existsByTitle(String title);
}
