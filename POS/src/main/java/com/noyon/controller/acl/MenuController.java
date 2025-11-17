package com.noyon.controller.acl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.acl.Menu;
import com.noyon.service.IMenuService;

@RestController
@RequestMapping("/api/admin/")
public class MenuController {

	private final IMenuService menuService;

	public MenuController(IMenuService menuService) {
		super();
		this.menuService = menuService;
	}
	
	@PostMapping("menu/create")
	public ResponseEntity<Menu> create(@RequestBody Menu menu)
	{
		Menu savedMenu=menuService.create(menu);
		return ResponseEntity.ok(savedMenu);
	}
}
