package com.noyon.service.acl;

import java.util.List;


import com.noyon.entity.acl.Menu;

public interface IMenuService {

	public Menu create(Menu menu);
	public List<Menu> getAllMenu();
	public Menu updateMenu(Menu menu,Long id);
    public Menu getMenuById(Long id);
}
