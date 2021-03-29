package com.medimpact.medeasy.common.bean.security;

import java.io.Serializable;

public class SecAuth implements Serializable{
	
	private static final long serialVersionUID = -820041882527135705L;
	private int id;
	private int moduleId;
	private String roleName;
	
	public SecAuth() {
		super();
	}
	
	
	public SecAuth(int id, int moduleId, String roleName) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.roleName = roleName;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
