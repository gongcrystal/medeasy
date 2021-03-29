package com.medimpact.medeasy.common.bean;

import java.util.Date;

public class LogBi {
	private long id;
	private String method;
	private String nameCh;
	private String username;
	private String doctorName;
	private Date loginTime; //待以后用
	private Date logoutTime; //待以后用
	private Date startSensitiveAction; //开始查询敏感数据的时间
	private Integer cond;  //根据药品检索
	private String searchedDrname; //检索的医生姓名
	private String searchedDrugname ;  //检索的药品名称
	
	
	public LogBi() {
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
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	public Date getStartSensitiveAction() {
		return startSensitiveAction;
	}
	public void setStartSensitiveAction(Date startSensitiveAction) {
		this.startSensitiveAction = startSensitiveAction;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getNameCh() {
		return nameCh;
	}
	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}
	public Integer getCond() {
		return cond;
	}
	public void setCond(Integer cond) {
		this.cond = cond;
	}
	public String getSearchedDrname() {
		return searchedDrname;
	}
	public void setSearchedDrname(String searchedDrname) {
		this.searchedDrname = searchedDrname;
	}
	public String getSearchedDrugname() {
		return searchedDrugname;
	}
	public void setSearchedDrugname(String searchedDrugname) {
		this.searchedDrugname = searchedDrugname;
	}
	
	
}
