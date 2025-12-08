package com.noyon.service.acl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.noyon.entity.acl.RequestMap;
import com.noyon.entity.acl.User;
import com.noyon.exception.CustomException;
import com.noyon.repository.acl.RequestMapRepository;
import com.noyon.repository.acl.UserRoleRepository;
@Service
public class RequestMapService implements IRequestMapService{

    private final UserRoleRepository userRoleRepository;
	private final RequestMapRepository requestMapRepository;
	private static final Logger log = LoggerFactory.getLogger(RequestMapService.class);

	public RequestMapService(RequestMapRepository requestMapRepository, UserRoleRepository userRoleRepository) {
		super();
		this.requestMapRepository = requestMapRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public RequestMap create(RequestMap requestMap) {
		// TODO Auto-generated method stub
		RequestMap savedRequestMap=new RequestMap();
		try {
			if(requestMapRepository.existsByUrl(requestMap.getUrl())) {
				throw new CustomException("Opps !! This url already present");
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User adminUser = (User) auth.getPrincipal();
            requestMap.setCreated(LocalDateTime.now());
            requestMap.setCreatedBy(adminUser.getUsername());
            savedRequestMap=requestMapRepository.save(requestMap);
		}catch (CustomException e) {
			// TODO: handle exception
			log.error("CustomException occurred: {}", e.getMessage(), e);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Unexpected error: {}", e.getMessage(), e);
		}
		return savedRequestMap;
	}
	
	public List<String> getUrlsByRoles(Set<String> roles) {

        List<RequestMap> all = requestMapRepository.findAll();

        return all.stream()
                .filter(rm -> {
                    List<String> rmRoles = Arrays.asList(rm.getConfigAttribute().split(","));
                    return rmRoles.stream().anyMatch(roles::contains);
                })
                .map(RequestMap::getUrl)
                .collect(Collectors.toList());
    }
	
}
