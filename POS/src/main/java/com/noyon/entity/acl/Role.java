package com.noyon.entity.acl;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "acl_roles")
public class Role {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String authority;
	private String description;
	private Boolean isDefault;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	
	@OneToMany(mappedBy = "role")
	private Set<UserRole> userRoles;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long id, String authority, String description, Boolean isDefault, LocalDateTime created,
			String createdBy, LocalDateTime modified, String modifiedBy, Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.authority = authority;
		this.description = description;
		this.isDefault = isDefault;
		this.created = created;
		this.createdBy = createdBy;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.userRoles = userRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
