package com.noyon.controller.acl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noyon.entity.acl.RequestMap;
import com.noyon.service.acl.IRequestMapService;
@RestController
@RequestMapping("/api/")
public class RequestMapController {

	private final IRequestMapService requestMapService;

	public RequestMapController(IRequestMapService requestMapService) {
		super();
		this.requestMapService = requestMapService;
	}
	
	@PostMapping("requestmap/create")
	public ResponseEntity<RequestMap> create(@RequestBody RequestMap requestMap){
		RequestMap savedRequestMap=requestMapService.create(requestMap);
		return ResponseEntity.ok(savedRequestMap);
	}
	
	@GetMapping("requestmaps")
	public ResponseEntity<List<RequestMap>> getAllRequestMap()
	{
		List<RequestMap> allrequestmap=requestMapService.getAllRequestMaps();
		return ResponseEntity.ok(allrequestmap);
	}
}
