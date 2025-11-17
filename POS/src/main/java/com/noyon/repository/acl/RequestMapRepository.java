package com.noyon.repository.acl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.RequestMap;

@Repository
public interface RequestMapRepository extends JpaRepository<RequestMap, Long> {

	boolean existsByUrl(String url);
}
