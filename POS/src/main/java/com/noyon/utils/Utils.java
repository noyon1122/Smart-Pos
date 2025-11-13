package com.noyon.utils;

import com.noyon.dto.UserDto;
import com.noyon.entity.acc.User;

public class Utils {

	public static UserDto mapUserEntityToUserDto(User user) {
		UserDto userDto=new UserDto();
		
		userDto.setId(user.getId());
		userDto.setFullName(user.getFullName());
		userDto.setMobile(user.getMobile());
		userDto.setEmail(user.getEmail());
		userDto.setUsername(user.getUsername());
		userDto.setPlazas(user.getPlazas());
		userDto.setCsdOrg(user.getCsdOrg());
		userDto.setSalesZone(user.getSalesZone());
		
		userDto.setPsd(user.getPsd());
		userDto.setUserRoles(user.getUserRoles());
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
