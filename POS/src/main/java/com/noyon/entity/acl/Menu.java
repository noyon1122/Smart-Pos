package com.noyon.entity.acl;

import java.time.LocalDateTime;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "acl_menu")
public class Menu {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String urlPath;
    private String menuClass;
    private String menuType = "MAIN_MENU";

    @ManyToOne
    @JoinColumn(name = "parent_menu_id")
    @JsonBackReference
    private Menu parentMenu;

    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Menu> children;

    private Boolean isExternal;
    private Boolean isOpenNewTab;
    private Boolean isActive;
    private Integer sortOrder;

    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;

  
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Menu(Long id, String title, String description, String urlPath, String menuClass, String menuType,
			Menu parentMenu, List<Menu> children, Boolean isExternal, Boolean isOpenNewTab, Boolean isActive,
			Integer sortOrder, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.urlPath = urlPath;
		this.menuClass = menuClass;
		this.menuType = menuType;
		this.parentMenu = parentMenu;
		this.children = children;
		this.isExternal = isExternal;
		this.isOpenNewTab = isOpenNewTab;
		this.isActive = isActive;
		this.sortOrder = sortOrder;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Boolean getIsExternal() {
		return isExternal;
	}
	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}
	public Boolean getIsOpenNewTab() {
		return isOpenNewTab;
	}
	public void setIsOpenNewTab(Boolean isOpenNewTab) {
		this.isOpenNewTab = isOpenNewTab;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
