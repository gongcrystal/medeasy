package com.medimpact.medeasy.common.bean;

import java.io.Serializable;
import java.util.List;

public class RoleMenuBi implements Serializable{
	
	private static final long serialVersionUID = 6634587950645395049L;
	private Integer id;
	private String roleName;
	private String menuName;
	private String description;
	private Integer menuId;
	private Integer operatLevel;
	private DtOpLevelBi dtOpLevelBi;
	private int reqOpLevel; //需要的最小级别
	

	public RoleMenuBi() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getOperatLevel() {
		return operatLevel;
	}

	public void setOperatLevel(Integer operatLevel) {
		this.operatLevel = operatLevel;
	}

	public DtOpLevelBi getDtOpLevelBi() {
		return dtOpLevelBi;
	}

	public void setDtOpLevelBi(DtOpLevelBi dtOpLevelBi) {
		this.dtOpLevelBi = dtOpLevelBi;
	}

	public int getReqOpLevel() {
		return reqOpLevel;
	}

	public void setReqOpLevel(int reqOpLevel) {
		this.reqOpLevel = reqOpLevel;
	}
	
	
	
}
