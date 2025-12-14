package com.noyon.controller;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.noyon.dto.AuthenticationResponse;
import com.noyon.dto.MenuDto;
import com.noyon.dto.UserDto;
import com.noyon.entity.acl.Menu;
import com.noyon.entity.acl.User;
import com.noyon.repository.acl.MenuRepository;
import com.noyon.service.acl.IAuthService;
import com.noyon.service.acl.RequestMapService;
import com.noyon.utils.Utils;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	private final IAuthService authService;
	private final RequestMapService requestMapService;
	private final MenuRepository menuRepository;

	
	
	public AuthController(IAuthService authService, RequestMapService requestMapService,
			MenuRepository menuRepository) {
		super();
		this.authService = authService;
		this.requestMapService = requestMapService;
		this.menuRepository = menuRepository;
	}
	@PostMapping("register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
		AuthenticationResponse response=authService.register(user);
		return ResponseEntity.ok(response);
	}
	@PostMapping("login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User user){
		AuthenticationResponse response=authService.login(user);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("me")
	@Transactional
	public ResponseEntity<UserDto> getCurrentUserInfo() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) auth.getPrincipal();

	    // roles from DB
	    Set<String> roleNames = user.getUserRoles().stream()
	            .map(ur -> ur.getRole().getAuthority())
	            .collect(Collectors.toSet());

	    // allowed URLs from DB
	    List<String> allowedUrls = requestMapService.getUrlsByRoles(roleNames);

	    // allowed Menus from DB
	    List<Menu> mainMenus = menuRepository.findAll();		
		List<MenuDto> hierarchicalMenus = Utils.buildHierarchicalMenu(mainMenus);
	    List<MenuDto> allowedMenus = hierarchicalMenus.stream()
	            .map(menu -> Utils.mapMenuEntityToMenuDto(menu, allowedUrls))
	            .filter(Objects::nonNull)
	            .toList();

	    UserDto userDto = Utils.mapUserEntityToUserDto(user, allowedUrls);
	    userDto.setAllowedMenus(allowedMenus);

	    return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("testt")
	public String getSomething(){
		return "Successfully access the url2";
	}
	@GetMapping("test")
	public String getSomethin(){
		return "Successfully access the url";
	}


	
	
	
	
}
