package com.medimpact.medeasy.common.bean;

public class RoleAuthorityBi {
	private Integer id; //role id
	private String roleName;
	private String description;
	private String menuAuth;
	private Integer operateLevel;	 
	private String opName; //数据权限的名字
	
	
	public RoleAuthorityBi() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
}
