package com.noyon.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.noyon.dto.MenuDto;
import com.noyon.dto.UserDto;
import com.noyon.entity.acl.Menu;
import com.noyon.entity.acl.User;


public class Utils {


	public static UserDto mapUserEntityToUserDto(User user,List<String> allowedUrls) {
		UserDto userDto=new UserDto();
		
		userDto.setId(user.getId());
		userDto.setFullName(user.getFullName());
		userDto.setMobile(user.getMobile());
		userDto.setEmail(user.getEmail());
		userDto.setUsername(user.getUsername());
		userDto.setPlazas(user.getPlazas());
		userDto.setPsd(user.getPsd());
		Set<String> roleNames = user.getUserRoles().stream()
                .map(ur -> ur.getRole().getAuthority())
                .collect(Collectors.toSet());
		userDto.setRoles(roleNames);
		
		userDto.setAllowedUrls(allowedUrls);
		userDto.setEnabled(user.getEnabled());
		userDto.setAccountExpired(user.getAccountExpired());
		userDto.setAccountLocked(user.getAccountLocked());
		userDto.setPasswordExpired(user.getPasswordExpired());
		userDto.setCreated(user.getCreated());
		userDto.setCreatedBy(user.getCreatedBy());
		userDto.setModified(user.getModified());
		userDto.setModifiedBy(user.getModifiedBy());
		
		return userDto;
		
	}
	
	
	
	public static MenuDto mapMenuEntityToMenuDto (MenuDto menu,List<String> allowedUrls) {
		  // If this menu has no URL (parent), keep it anyway
		boolean isAllowed = menu.getChildren().isEmpty()
                ? (menu.getUrlPath() == null || menu.getUrlPath().equals("#") || allowedUrls.contains(menu.getUrlPath()))
                : true;  // Keep parent menus even if URL not in allowedUrls


	    if (!isAllowed) return null;

	    MenuDto dto = new MenuDto();
	    
	    dto.setId(menu.getId());
	    dto.setTitle(menu.getTitle());
	    dto.setDescription(menu.getDescription());
	    dto.setUrlPath(menu.getUrlPath());
	    dto.setMenuClass(menu.getMenuClass());
	    dto.setMenuType(menu.getMenuType());
	    dto.setIsExternal(menu.getIsExternal());
	    dto.setIsOpenNewTab(menu.getIsOpenNewTab());
	    dto.setIsActive(menu.getIsActive());
	    dto.setSortOrder(menu.getSortOrder());

	    // children
	    List<MenuDto> childList = menu.getChildren().stream()
	            .map(child -> mapMenuEntityToMenuDto(child, allowedUrls))
	            .filter(m -> m != null)
	            .toList();

	    if (childList.isEmpty() && (menu.getUrlPath() != null && !allowedUrls.contains(menu.getUrlPath()) && !menu.getUrlPath().equals("#"))) {
	        return null;
	    }

	    dto.setChildren(childList);


	    return dto;
	}
	
	
	 public static List<MenuDto> buildHierarchicalMenu(List<Menu> allMenus) {
	        Map<Long, MenuDto> menuDtoMap = new HashMap<>();
	        List<MenuDto> rootMenus = new ArrayList<>();

	        // Step 1: Convert all Menu entities to MenuDto
	        for (Menu menu : allMenus) {
	            MenuDto dto = mapMenuToDto(menu);
	            menuDtoMap.put(menu.getId(), dto);
	        }

	        // Step 2: Build hierarchy
	        for (Menu menu : allMenus) {
	            MenuDto dto = menuDtoMap.get(menu.getId());
	            if (menu.getParentMenu() != null) {
	                MenuDto parentDto = menuDtoMap.get(menu.getParentMenu().getId());
	                if (parentDto.getChildren() == null) {
	                    parentDto.setChildren(new ArrayList<>());
	                }
	                parentDto.getChildren().add(dto);
	            } else {
	                // No parent means root menu
	                rootMenus.add(dto);
	            }
	        }

	        return rootMenus;
	    }

	    private static MenuDto mapMenuToDto(Menu menu) {
	        MenuDto dto = new MenuDto();
	        dto.setId(menu.getId());
	        dto.setTitle(menu.getTitle());
	        dto.setDescription(menu.getDescription());
	        dto.setUrlPath(menu.getUrlPath());
	        dto.setMenuClass(menu.getMenuClass());
	        dto.setMenuType(menu.getMenuType());
	        dto.setIsExternal(menu.getIsExternal());
	        dto.setIsOpenNewTab(menu.getIsOpenNewTab());
	        dto.setIsActive(menu.getIsActive());
	        dto.setSortOrder(menu.getSortOrder());
	        dto.setChildren(new ArrayList<>()); // initialize empty list
	        return dto;
	    }

}
