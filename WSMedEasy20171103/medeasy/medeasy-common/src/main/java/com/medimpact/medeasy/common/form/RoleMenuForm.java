package com.medimpact.medeasy.common.form;

public class RoleMenuForm {
	private String roleName;
	private String description;
	private Integer operateLevel;	
	private String opName;
	private String menuAuth; // 对应的所有menu组成string[]

	public RoleMenuForm() {
		super();
	}

	public RoleMenuForm(String roleName, String description, String menuAuth, Integer operateLevel,String opName) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.menuAuth = menuAuth;
		this.operateLevel = operateLevel;
		this.opName = opName;

	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuAuth() {
		return menuAuth;
	}

	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}

	public Integer getOperateLevel() {
		return operateLevel;
	}

	public void setOperateLevel(Integer operateLevel) {
		this.operateLevel = operateLevel;
	}

}
