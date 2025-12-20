package com.noyon.repository.acl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.RequestMap;

@Repository
public interface RequestMapRepository extends JpaRepository<RequestMap, Long> {

	boolean existsByUrl(String url);
	Optional<RequestMap> findByUrl(String url);
	Optional<RequestMap>findById(Long id);
}
