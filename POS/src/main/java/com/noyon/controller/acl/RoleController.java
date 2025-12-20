package com.noyon.controller.acl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.acl.Role;
import com.noyon.repository.token.TokenRepository;
import com.noyon.service.acl.IRoleService;

@RestController
@RequestMapping("/api/")
public class RoleController {

    private final TokenRepository tokenRepository;

	private final IRoleService roleService;

	public RoleController(IRoleService roleService, TokenRepository tokenRepository) {
		super();
		this.roleService = roleService;
		this.tokenRepository = tokenRepository;
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
	
	@GetMapping("role/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable Long id)
	{
		try {
	        Role role=roleService.getRoleById(id);
	        return ResponseEntity.ok(role);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/role/update/{id}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role,@PathVariable Long id)
	{
		try {
	        Role updateRole=roleService.updateRole(role, id);
	        return ResponseEntity.ok(updateRole);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
