package com.medimpact.medeasy.common.bean;

import java.io.Serializable;

import com.medimpact.medeasy.common.RequestParameter;

public class DtOpLevelBi  extends RequestParameter implements Serializable{
	
	private Integer id;
	private String opName;
	private int operateLevel;
	
	public DtOpLevelBi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}

	public int getOperateLevel() {
		return operateLevel;
	}

	public void setOperateLevel(int operateLevel) {
		this.operateLevel = operateLevel;
	}
}
