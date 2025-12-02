package com.noyon.entity.pos;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Plazas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private String mobile;
	private String address;
	private String status;
	private String groupName;
	
	@ManyToOne
	@JoinColumn(name = "development_id",nullable = true)
	@JsonIgnore
	private HrDevelopment development;
	
	@ManyToOne
	@JoinColumn(name = "zone_id",nullable = true)
	@JsonIgnore
	private HrZone zone;
	
	private Date openDate;
	
	private String createdBy;
	private LocalDateTime created;
	private String modifiedBy;
	private LocalDateTime modified;
	public Plazas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Plazas(Long id, String code, String name, String mobile, String address, String status, String groupName,
			HrDevelopment development, HrZone zone, Date openDate, String createdBy, LocalDateTime created,
			String modifiedBy, LocalDateTime modified) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.status = status;
		this.groupName = groupName;
		this.development = development;
		this.zone = zone;
		this.openDate = openDate;
		this.createdBy = createdBy;
		this.created = created;
		this.modifiedBy = modifiedBy;
		this.modified = modified;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public HrDevelopment getDevelopment() {
		return development;
	}
	public void setDevelopment(HrDevelopment development) {
		this.development = development;
	}
	public HrZone getZone() {
		return zone;
	}
	public void setZone(HrZone zone) {
		this.zone = zone;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	
	
	
}
