package com.noyon.entity.pos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HrZone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;

    private String amEmpId;
    private String amMobileNo;

    private String cmEmpId;
    private String cmMobileNo;

    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
    
    @ManyToOne
    @JoinColumn(name = "development_id")
    @JsonIgnore
    private HrDevelopment development;
    
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Plazas> plazas;

	public HrZone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HrZone(Long id, String title, String amEmpId, String amMobileNo, String cmEmpId, String cmMobileNo,
			Boolean isActive, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy,
			HrDevelopment development, List<Plazas> plazas) {
		super();
		this.id = id;
		this.title = title;
		this.amEmpId = amEmpId;
		this.amMobileNo = amMobileNo;
		this.cmEmpId = cmEmpId;
		this.cmMobileNo = cmMobileNo;
		this.isActive = isActive;
		this.created = created;
		this.createdBy = createdBy;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.development = development;
		this.plazas = plazas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAmEmpId() {
		return amEmpId;
	}

	public void setAmEmpId(String amEmpId) {
		this.amEmpId = amEmpId;
	}

	public String getAmMobileNo() {
		return amMobileNo;
	}

	public void setAmMobileNo(String amMobileNo) {
		this.amMobileNo = amMobileNo;
	}

	public String getCmEmpId() {
		return cmEmpId;
	}

	public void setCmEmpId(String cmEmpId) {
		this.cmEmpId = cmEmpId;
	}

	public String getCmMobileNo() {
		return cmMobileNo;
	}

	public void setCmMobileNo(String cmMobileNo) {
		this.cmMobileNo = cmMobileNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public HrDevelopment getDevelopment() {
		return development;
	}

	public void setDevelopment(HrDevelopment development) {
		this.development = development;
	}

	public List<Plazas> getPlazas() {
		return plazas;
	}

	public void setPlazas(List<Plazas> plazas) {
		this.plazas = plazas;
	}
	
	

    
}
