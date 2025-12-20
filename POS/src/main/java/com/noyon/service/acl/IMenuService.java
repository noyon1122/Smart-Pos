package com.noyon.service.acl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.noyon.dto.MenuDto;
import com.noyon.entity.acl.Menu;

public interface IMenuService {

	public Menu create(Menu menu);


	Page<Menu> getAllMenu(
	        int page,
	        int size,
	        Long parentMenu,
	        String title,
	        String urlPath
	);

	public Menu updateMenu(Menu menu,Long id);
    public Menu getMenuById(Long id);
    public List<MenuDto> getAllHiararchicalMenu();
}
