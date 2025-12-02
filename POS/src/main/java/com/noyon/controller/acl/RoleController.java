package com.noyon.controller.acl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.acl.Role;
import com.noyon.service.acl.IRoleService;

@RestController
@RequestMapping("/api/")
public class RoleController {

	private final IRoleService roleService;

	public RoleController(IRoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@PostMapping("role/create")
	public ResponseEntity<Role> create(@RequestBody Role role){
		Role savedRole=roleService.create(role);
		return ResponseEntity.ok(savedRole);
	}
	
	@GetMapping("roles")
	public ResponseEntity<List<Role>> getRoles(){
		List<Role> roles=roleService.getAllRole();
		return ResponseEntity.ok(roles);
	}
}
