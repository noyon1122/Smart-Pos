package com.noyon.service.acl;

import java.util.List;

import com.noyon.entity.acl.Role;

public interface IRoleService {

	public Role create(Role role);
	public List<Role> getAllRole();
	public Role getRoleById(Long id);
	public Role updateRole(Role role,Long id);
}
