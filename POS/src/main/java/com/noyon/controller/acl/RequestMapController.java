package com.noyon.controller.acl;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<RequestMap>> getAllRequestMap(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam (required = false) String url,
			@RequestParam (required = false) String configAttribute
			)
	{
		
		return ResponseEntity.ok(requestMapService.getAllRequestmap(page, size, url, configAttribute));
	}
	
	@GetMapping("requestmap/{id}")
	public ResponseEntity<RequestMap> getRequestMapById(@PathVariable Long id)
	{
		try {
			RequestMap existingRequestMap=requestMapService.getRequestMapById(id);
			return ResponseEntity.ok(existingRequestMap);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	@PostMapping("requestmap/update/{id}")
	public ResponseEntity<RequestMap> updateRequestmap(@RequestBody RequestMap requestMap, @PathVariable Long id){
		try {
			RequestMap updateRequestMap=requestMapService.updateRequestMap(requestMap, id);
			return ResponseEntity.ok(updateRequestMap);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
