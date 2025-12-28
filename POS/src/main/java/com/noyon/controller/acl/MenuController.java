package com.noyon.controller.acl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.dto.MenuDto;
import com.noyon.entity.acl.Menu;
import com.noyon.service.acl.IMenuService;


@RestController
@RequestMapping("/api/")
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
	
	@GetMapping("menus")
	public ResponseEntity<Page<Menu>> getMenus(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(required = false) Long parentMenu,
	        @RequestParam(required = false) String title,
	        @RequestParam(required = false) String urlPath
	) {
	    return ResponseEntity.ok(
	        menuService.getAllMenu(page, size, parentMenu, title, urlPath)
	    );
	}

	
	@GetMapping("menu/update/{id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable Long id)
	{
		try {
			Menu menu=menuService.getMenuById(id);
			return ResponseEntity.ok(menu);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("menu/update/{id}")
	public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu,@PathVariable Long id)
	{
		Menu updateMenu=menuService.updateMenu(menu, id);
		return ResponseEntity.ok(updateMenu);
	}
	
	@GetMapping("hiMenus")
	public ResponseEntity<List<MenuDto>> getHiararchicalMenu()
	{
		List<MenuDto> menuList=menuService.getAllHiararchicalMenu();
		return ResponseEntity.ok(menuList);
	}
	
}
