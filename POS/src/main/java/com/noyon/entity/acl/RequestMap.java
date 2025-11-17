package com.noyon.entity.acl;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acl_requestmap")
public class RequestMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
	private String url;
    private String configAttribute;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
	public RequestMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RequestMap(Long id, String url, String configAttribute, LocalDateTime created, String createdBy,
			LocalDateTime modified, String modifiedBy) {
		super();
		this.id = id;
		this.url = url;
		this.configAttribute = configAttribute;
		this.created = created;
		this.createdBy = createdBy;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
	}
   
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getConfigAttribute() {
		return configAttribute;
	}
	public void setConfigAttribute(String configAttribute) {
		this.configAttribute = configAttribute;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
    
    
}
