package com.noyon.service.acl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noyon.dto.AuthenticationResponse;
import com.noyon.dto.UserDto;
import com.noyon.entity.acl.Role;
import com.noyon.entity.acl.User;
import com.noyon.entity.acl.UserRole;
import com.noyon.entity.pos.Plazas;
import com.noyon.exception.CustomException;
import com.noyon.repository.acl.RoleRepository;
import com.noyon.repository.acl.UserRepository;
import com.noyon.repository.acl.UserRoleRepository;
import com.noyon.repository.pos.PlazasRepository;
import com.noyon.utils.Utils;
@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final PlazasRepository plazasRepository;
	private final RoleRepository roleRepository;
	private final UserRoleRepository userRoleRepository;
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			PlazasRepository plazasRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.plazasRepository = plazasRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	@Transactional
	public AuthenticationResponse createUser(User user) {
		// TODO Auto-generated method stub
		AuthenticationResponse response=new AuthenticationResponse();
		try {
			
			if(userRepository.existsByUsername(user.getUsername())) {
				throw new CustomException("Opps!! User already exists");
			}
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User adminUser = (User) auth.getPrincipal();
            
            // plaza
	        if (user.getPlazas() != null && user.getPlazas().getId() != null) {
	            Plazas plaza = plazasRepository.findById(user.getPlazas().getId())
	                    .orElseThrow(() -> new CustomException("Invalid plaza ID"));
	            user.setPlazas(plaza);
	        } 

	        Set<UserRole> userRoles = new HashSet<>();
	        if (user.getRoles() != null) {

	            for (Long roleId : user.getRoles()) {

	                Role role = roleRepository.findById(roleId)
	                        .orElseThrow(() -> new CustomException("Invalid Role ID: " + roleId));

	                UserRole userRole = new UserRole();
	                userRole.setUser(user);
	                userRole.setRole(role);
	                userRoles.add(userRole);
	            }
	        }

	        user.setUserRoles(userRoles);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setCreated(LocalDateTime.now());
			user.setCreatedBy(adminUser.getUsername());
			User savedUser=userRepository.save(user);
			UserDto userDto =Utils.mapUserEntityToUserDto(savedUser,null);
			response.setStatusCode(200);
			response.setUserDto(userDto);
			response.setMessage("User Registration have been done Successfully");
			
		}catch (CustomException e) {
			// TODO: handle exception
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Error Saving a User"+e.getMessage());
		}
		return response;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User> userList=userRepository.findAll();
		
		return userList;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		
		User user=new User();
		try {
			User existingUser=userRepository.findById(id).orElseThrow(()-> new CustomException("User not found with this id : "+id) );
			user=existingUser;
		} catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error occurred: {}", e.getMessage(), e);
		}
		return user;
	}

	@Override
	@Transactional
	public User updateUser(User user, Long id) {

	    User updatedUser = null;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User adminUser = (User) auth.getPrincipal();

	    try {
	        User existingUser = userRepository.findById(id)
	                .orElseThrow(() -> new CustomException("User not found with this id : " + id));

	        existingUser.setFullName(user.getFullName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setMobile(user.getMobile());
            existingUser.setUsername(user.getUsername());
	        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        }

	        
	     // ===== PLAZA UPDATE LOGIC =====
	        if (user.getPlazas() == null) {
	            // frontend explicitly sent plazas: null → REMOVE plaza
	            existingUser.setPlazas(null);

	        } else if (user.getPlazas().getId() != null) {
	            // frontend sent a plaza id → UPDATE plaza
	            Plazas plaza = plazasRepository.findById(user.getPlazas().getId())
	                    .orElseThrow(() -> new CustomException("Invalid plaza ID"));

	            existingUser.setPlazas(plaza);
	        }
	        // else: plazas object exists but id is null → do nothing (optional)

	       
	        // ===== ROLE UPDATE (CORRECT WAY) =====
	        if (user.getRoles() != null) {

	            // delete old roles
	            userRoleRepository.deleteByUser(existingUser);

	            Set<UserRole> newUserRoles = new HashSet<>();

	            for (Long roleId : user.getRoles()) {

	                Role role = roleRepository.findById(roleId)
	                        .orElseThrow(() -> new CustomException("Invalid Role ID: " + roleId));

	                UserRole userRole = new UserRole();
	                userRole.setUser(existingUser);
	                userRole.setRole(role);

	                newUserRoles.add(userRole);
	            }

	            existingUser.setUserRoles(newUserRoles);
	        }
	        existingUser.setModified(LocalDateTime.now());
	        existingUser.setModifiedBy(adminUser.getUsername());

	        updatedUser = userRepository.save(existingUser);

	    } catch (CustomException e) {
	        log.error("CustomException occurred: {}", e.getMessage(), e);
	    } catch (Exception e) {
	        log.error("Unexpected error occurred: {}", e.getMessage(), e);
	    }

	    return updatedUser;
	}

	
	
	
	
	
}
