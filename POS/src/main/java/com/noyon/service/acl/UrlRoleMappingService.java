package com.noyon.service.acl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.noyon.entity.acl.RequestMap;

import com.noyon.repository.acl.RequestMapRepository;

@Service
public class UrlRoleMappingService {

	private final RequestMapRepository requestMapRepository;

	

    public UrlRoleMappingService(RequestMapRepository requestMapRepository) {
		super();
		this.requestMapRepository = requestMapRepository;
	}



	 public Map<String, String[]> getUrlRoleMap() {

        Map<String, String[]> urlRoleMap = new HashMap<>();

        List<RequestMap> requestMaps = requestMapRepository.findAll();

        for (RequestMap rm : requestMaps) {

            if (rm.getUrl() == null || rm.getUrl().isEmpty()) continue;
            if (rm.getConfigAttribute() == null || rm.getConfigAttribute().isEmpty()) continue;

            // Split comma-separated roles into array
            String[] roles = rm.getConfigAttribute()
                               .split("\\s*,\\s*"); // splits "ADMIN,USER" -> ["ADMIN","USER"]
            
            String[] fixedRoles = Arrays.stream(roles)
            	    .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
            	    .toArray(String[]::new);

            	urlRoleMap.put(rm.getUrl(), fixedRoles);

           
        }

        return urlRoleMap;
    }
}
