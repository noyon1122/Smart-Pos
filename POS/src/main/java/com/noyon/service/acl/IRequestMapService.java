package com.noyon.service.acl;


import java.util.List;

import com.noyon.entity.acl.RequestMap;

public interface IRequestMapService {

	public RequestMap create(RequestMap requestMap);
	public List<RequestMap> getAllRequestMaps();
}
