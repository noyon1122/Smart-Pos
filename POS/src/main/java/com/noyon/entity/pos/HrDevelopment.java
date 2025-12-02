package com.noyon.entity.pos;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class HrDevelopment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String cdoEmpId; //chief divisional officer
    private String cdoEmpMobileNo;
    private String dcmEmpId; //divisional credit manager
    private String dcmEmpMobileNo;
    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
    
    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Plazas> plazas;
    
    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HrZone> zone;

	public HrDevelopment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HrDevelopment(Long id, String title, String cdoEmpId, String cdoEmpMobileNo, String dcmEmpId,
			String dcmEmpMobileNo, Boolean isActive, LocalDateTime created, String createdBy, LocalDateTime modified,
			String modifiedBy, List<Plazas> plazas, List<HrZone> zone) {
		super();
		this.id = id;
		this.title = title;
		this.cdoEmpId = cdoEmpId;
		this.cdoEmpMobileNo = cdoEmpMobileNo;
		this.dcmEmpId = dcmEmpId;
		this.dcmEmpMobileNo = dcmEmpMobileNo;
		this.isActive = isActive;
		this.created = created;
		this.createdBy = createdBy;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.plazas = plazas;
		this.zone = zone;
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

	public String getCdoEmpId() {
		return cdoEmpId;
	}

	public void setCdoEmpId(String cdoEmpId) {
		this.cdoEmpId = cdoEmpId;
	}

	public String getCdoEmpMobileNo() {
		return cdoEmpMobileNo;
	}

	public void setCdoEmpMobileNo(String cdoEmpMobileNo) {
		this.cdoEmpMobileNo = cdoEmpMobileNo;
	}

	public String getDcmEmpId() {
		return dcmEmpId;
	}

	public void setDcmEmpId(String dcmEmpId) {
		this.dcmEmpId = dcmEmpId;
	}

	public String getDcmEmpMobileNo() {
		return dcmEmpMobileNo;
	}

	public void setDcmEmpMobileNo(String dcmEmpMobileNo) {
		this.dcmEmpMobileNo = dcmEmpMobileNo;
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

	public List<Plazas> getPlazas() {
		return plazas;
	}

	public void setPlazas(List<Plazas> plazas) {
		this.plazas = plazas;
	}

	public List<HrZone> getZone() {
		return zone;
	}

	public void setZone(List<HrZone> zone) {
		this.zone = zone;
	}

	
	
    
	
    
}
