package com.noyon.service.acl;

import org.springframework.data.domain.Page;

import com.noyon.entity.acl.RequestMap;

public interface IRequestMapService {
	public RequestMap create(RequestMap requestMap);
	public RequestMap getRequestMapById(Long id);
	public RequestMap updateRequestMap(RequestMap requestMap,Long id);
	Page<RequestMap> getAllRequestmap(int page,int size,String url,String configAttribute);
}
