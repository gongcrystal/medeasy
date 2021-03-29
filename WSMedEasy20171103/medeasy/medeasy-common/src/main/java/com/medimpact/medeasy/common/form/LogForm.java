package com.medimpact.medeasy.common.form;

import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

public class LogForm extends RequestParameter{
	
	private long id;
	private String method=null;
	private String nameCh=null; 
	private String username=null;
	private String drName=null; //可以根据医生姓名检索	
	private String startDate=null;  //登陆的开始时间，用于检索
	private String endDate=null;    //登陆的结束时间，用于检索
	private String cond;  //根据药品检索  1,
	
	
	
	public LogForm() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getNameCh() {
		return nameCh;
	}
	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
}
