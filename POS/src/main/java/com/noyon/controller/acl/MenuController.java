package com.noyon.controller.acl;
import java.util.List;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.noyon.entity.acl.Menu;
import com.noyon.service.acl.IMenuService;
import com.noyon.service.acl.UrlRoleMappingService;

@RestController
@RequestMapping("/api/")
public class MenuController {

	private final IMenuService menuService;
	private final UrlRoleMappingService urlRoleMappingService;

	public MenuController(IMenuService menuService,UrlRoleMappingService urlRoleMappingService) {
		super();
		this.menuService = menuService;
		this.urlRoleMappingService=urlRoleMappingService;
	}
	
	@PostMapping("menu/create")
	public ResponseEntity<Menu> create(@RequestBody Menu menu)
	{
		Menu savedMenu=menuService.create(menu);
		return ResponseEntity.ok(savedMenu);
	}
	
	@GetMapping("menus")
	public ResponseEntity<List<Menu>> getMainMenu() {
		List<Menu> menuList=menuService.getMainMenu();
		return ResponseEntity.ok(menuList);
    }
	
	
}
