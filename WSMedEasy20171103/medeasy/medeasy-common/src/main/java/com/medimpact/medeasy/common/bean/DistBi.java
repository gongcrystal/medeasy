package com.medimpact.medeasy.common.bean;

import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;


//dist切换为操作数据库表area
public class DistBi extends RequestParameter{
	
	private int id;
	private String name;
	private String code;
	private Date createTime;
	private Date updateTime;
	private int opLevel; //需要的最低级别
	
	public DistBi() {
		super();
	}
	
	
	public DistBi(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getOpLevel() {
		return opLevel;
	}

	public void setOpLevel(int opLevel) {
		this.opLevel = opLevel;
	}
}
