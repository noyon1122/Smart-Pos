package com.noyon.repository.acl;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.noyon.entity.acl.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>,JpaSpecificationExecutor<Menu> {

	List<Menu> findByParentMenuIsNullOrderBySortOrderAsc();
    Optional<Menu>findById(Long id);
    Page<Menu> findAll(Pageable pageable);
    
}
