package com.noyon.controller.acl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.acl.Role;
import com.noyon.service.IRoleService;

@RestController
@RequestMapping("/api/admin/")
public class RoleController {

	private final IRoleService roleService;

	public RoleController(IRoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@PostMapping("roles/create")
	public ResponseEntity<Role> create(@RequestBody Role role){
		Role savedRole=roleService.create(role);
		return ResponseEntity.ok(savedRole);
	}
	
	@GetMapping("demo")
	public ResponseEntity<String> getSomething(){
		return ResponseEntity.ok("Data can be fetch");
	}
}
