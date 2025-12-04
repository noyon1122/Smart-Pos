package com.noyon.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.noyon.entity.csd.Organization;
import com.noyon.entity.pos.HrDevelopment;
import com.noyon.entity.pos.HrSaleZone;
import com.noyon.entity.pos.HrZone;
import com.noyon.entity.pos.Plazas;

public class UserDto {

	private Long id;
	private String fullName;
	private String email;
	private String mobile;
	private String username;
	private Plazas plazas;
	private Organization csdOrg;
	private HrSaleZone salesZone;
	private HrDevelopment psd;
	private HrZone zone;
	private Set<String> roles = new HashSet<>();
	private List<String> allowedUrls;
	private List<MenuDto> allowedMenus;
	private Boolean enabled;
	private Boolean accountExpired;
	private Boolean accountLocked;
	private Boolean passwordExpired;
	
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDto(Long id, String fullName, String email, String mobile, String username, Plazas plazas,
			Organization csdOrg, HrSaleZone salesZone, HrDevelopment psd, HrZone zone, Set<String> roles,
			List<String> allowedUrls, List<MenuDto> allowedMenus, Boolean enabled, Boolean accountExpired,
			Boolean accountLocked, Boolean passwordExpired, LocalDateTime created, String createdBy,
			LocalDateTime modified, String modifiedBy) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.username = username;
		this.plazas = plazas;
		this.csdOrg = csdOrg;
		this.salesZone = salesZone;
		this.psd = psd;
		this.zone = zone;
		this.roles = roles;
		this.allowedUrls = allowedUrls;
		this.allowedMenus = allowedMenus;
		this.enabled = enabled;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.passwordExpired = passwordExpired;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Plazas getPlazas() {
		return plazas;
	}
	public void setPlazas(Plazas plazas) {
		this.plazas = plazas;
	}
	public Organization getCsdOrg() {
		return csdOrg;
	}
	public void setCsdOrg(Organization csdOrg) {
		this.csdOrg = csdOrg;
	}
	public HrSaleZone getSalesZone() {
		return salesZone;
	}
	public void setSalesZone(HrSaleZone salesZone) {
		this.salesZone = salesZone;
	}
	public HrDevelopment getPsd() {
		return psd;
	}
	public void setPsd(HrDevelopment psd) {
		this.psd = psd;
	}
	public HrZone getZone() {
		return zone;
	}
	public void setZone(HrZone zone) {
		this.zone = zone;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	public Boolean getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public Boolean getPasswordExpired() {
		return passwordExpired;
	}
	public void setPasswordExpired(Boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
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

	public List<String> getAllowedUrls() {
		return allowedUrls;
	}

	public void setAllowedUrls(List<String> allowedUrls) {
		this.allowedUrls = allowedUrls;
	}


	public List<MenuDto> getAllowedMenus() {
		return allowedMenus;
	}


	public void setAllowedMenus(List<MenuDto> allowedMenus) {
		this.allowedMenus = allowedMenus;
	}
	
	
	
	
}
