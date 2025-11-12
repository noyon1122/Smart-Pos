package com.noyon.entity.acc;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.noyon.entity.csd.Organization;
import com.noyon.entity.pos.HrDevelopment;
import com.noyon.entity.pos.HrSaleZone;
import com.noyon.entity.pos.HrZone;
import com.noyon.entity.pos.Plazas;
import com.noyon.entity.token.Token;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "acl_users")

public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String email;
	private String mobile;
	private String username;
	private String password;
	
	// Relation
	@ManyToOne
	@JoinColumn(name = "plaza_id")
	private Plazas plazas;

	@ManyToOne
	@JoinColumn(name = "csd_org_id")
	private Organization csdOrg;

	@ManyToOne
	@JoinColumn(name = "sales_zone_id")
	private HrSaleZone salesZone;

	@ManyToOne
	@JoinColumn(name = "psd_id")
	private HrDevelopment psd;

	@ManyToOne
	@JoinColumn(name = "zone_id")
	private HrZone zone;
	
	@OneToMany
	private List<Token> tokens;

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();
	
	private Boolean enabled = true;
	private Boolean accountExpired = false;
	private Boolean accountLocked = false;
	private Boolean passwordExpired = false;
	
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 return userRoles.stream()
	                .map(userRole -> (GrantedAuthority) () -> "ROLE_" + userRole.getRole().getAuthority())
	                .collect(Collectors.toSet());
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonExpired();
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonLocked();
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isCredentialsNonExpired();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return UserDetails.super.isEnabled();
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String fullName, String email, String mobile, String username, String password, Plazas plazas,
			Organization csdOrg, HrSaleZone salesZone, HrDevelopment psd, HrZone zone, Set<UserRole> userRoles,
			Boolean enabled, Boolean accountExpired, Boolean accountLocked, Boolean passwordExpired,
			LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.plazas = plazas;
		this.csdOrg = csdOrg;
		this.salesZone = salesZone;
		this.psd = psd;
		this.zone = zone;
		this.userRoles = userRoles;
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
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
