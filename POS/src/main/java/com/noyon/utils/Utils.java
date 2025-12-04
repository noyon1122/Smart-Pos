package com.noyon.utils;


import java.util.List;
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
		userDto.setCsdOrg(user.getCsdOrg());
		
		
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
	
	public static MenuDto mapMenuEntityToMenuDto (Menu menu,List<String> allowedUrls) {
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

}
