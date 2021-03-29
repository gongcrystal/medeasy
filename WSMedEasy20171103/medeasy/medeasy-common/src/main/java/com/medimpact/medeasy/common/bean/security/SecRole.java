package com.medimpact.medeasy.common.bean.security;

import java.io.Serializable;

import com.medimpact.medeasy.common.RequestParameter;
import com.medimpact.medeasy.common.bean.DtOpLevelBi;

public class SecRole   extends RequestParameter implements Serializable {

	private static final long serialVersionUID = -4769185837699343856L;
	
	private Integer id;
	private String roleName;
	private String description;
	private int reqOpLevel=1; //要获取db中的此行数据， 需要的权限不能低于此值
	
	
	public SecRole() {
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
	public int getReqOpLevel() {
		return reqOpLevel;
	}
	public void setReqOpLevel(int reqOpLevel) {
		this.reqOpLevel = reqOpLevel;
	}
}
