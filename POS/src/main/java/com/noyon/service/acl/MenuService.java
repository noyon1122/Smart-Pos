package com.noyon.service.acl;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.noyon.entity.acl.Menu;
import com.noyon.entity.acl.User;
import com.noyon.exception.CustomException;
import com.noyon.repository.acl.MenuRepository;

@Service
public class MenuService implements IMenuService {

	private final MenuRepository menuRepository;
	private static final Logger log = LoggerFactory.getLogger(MenuService.class);
	public MenuService(MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}
	@Override
	public Menu create(Menu menu) {
		Menu savedMenu=new Menu();
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User adminUser = (User) auth.getPrincipal();
            menu.setCreated(LocalDateTime.now());
            menu.setCreatedBy(adminUser.getUsername());
            savedMenu=menuRepository.save(menu);
		}catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error: {}", e.getMessage(), e);
		}
		return savedMenu;
	}
	@Override
	public List<Menu> getMainMenu() {
		// TODO Auto-generated method stub
		return menuRepository.findByParentMenuIsNullOrderBySortOrderAsc();
	}

	
	
	
}
