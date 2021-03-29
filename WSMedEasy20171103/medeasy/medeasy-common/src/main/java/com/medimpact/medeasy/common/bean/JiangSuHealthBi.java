package com.medimpact.medeasy.common.bean;

import java.math.BigDecimal;
import java.util.Date;

public class JiangSuHealthBi {
	private Long id;
	private Long hospitalId;
	private Long calanderId;
	private int patientTypeId;
	private String name;
	private BigDecimal amount;
	private String regCode;
	private String rx_code;
	private Date createTime;
	private Date lastTime;
	public JiangSuHealthBi() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Long getCalanderId() {
		return calanderId;
	}
	public void setCalanderId(Long calanderId) {
		this.calanderId = calanderId;
	}
	public int getPatientTypeId() {
		return patientTypeId;
	}
	public void setPatientTypeId(int patientTypeId) {
		this.patientTypeId = patientTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRx_code() {
		return rx_code;
	}
	public void setRx_code(String rx_code) {
		this.rx_code = rx_code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}
