package com.noyon.repository.acl;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.RequestMap;

@Repository
public interface RequestMapRepository extends JpaRepository<RequestMap, Long>,JpaSpecificationExecutor<RequestMap> {

	boolean existsByUrl(String url);
	Optional<RequestMap> findByUrl(String url);
	Optional<RequestMap>findById(Long id);
	Page<RequestMap>findAll(Pageable pageable);
}
