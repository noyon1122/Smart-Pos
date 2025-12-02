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
	public ResponseEntity<List<Menu>> getMainMenu() {
		List<Menu> mainMenus= menuService.getMainMenu();
        return ResponseEntity.ok(mainMenus);
    }
	
	@GetMapping("menus/{parentId}")
    public List<Menu> getChildren(@PathVariable Long parentId) {
        return menuService.getChildMenu(parentId);
    }
}
