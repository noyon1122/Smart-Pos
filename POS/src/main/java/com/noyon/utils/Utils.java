package com.noyon.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.noyon.dto.UserDto;
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
}
