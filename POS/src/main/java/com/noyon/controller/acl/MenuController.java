package com.noyon.controller.acl;
import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.noyon.entity.acl.Menu;
import com.noyon.repository.token.TokenRepository;
import com.noyon.service.acl.IMenuService;
import com.noyon.service.acl.UrlRoleMappingService;

@RestController
@RequestMapping("/api/")
public class MenuController {

    private final TokenRepository tokenRepository;

	private final IMenuService menuService;
	private final UrlRoleMappingService urlRoleMappingService;

	public MenuController(IMenuService menuService,UrlRoleMappingService urlRoleMappingService, TokenRepository tokenRepository) {
		super();
		this.menuService = menuService;
		this.urlRoleMappingService=urlRoleMappingService;
		this.tokenRepository = tokenRepository;
	}
	
	@PostMapping("menu/create")
	public ResponseEntity<Menu> create(@RequestBody Menu menu)
	{
		Menu savedMenu=menuService.create(menu);
		return ResponseEntity.ok(savedMenu);
	}
	
	@GetMapping("menus")
	public ResponseEntity<List<Menu>> getMainMenu() {
		List<Menu> menuList=menuService.getAllMenu();
		return ResponseEntity.ok(menuList);
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
	
}
