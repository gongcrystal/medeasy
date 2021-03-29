package com.medimpact.medeasy.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

public class DrugUseBi extends RequestParameter implements  Serializable{
	private static final long serialVersionUID = 6492775496447488078L;
	private Long drugUseId;
	private Long hospitalKey;	
	private Long drKey;
	private Long deptKey;
	private Long drugKey;
	private Long calendarKey;
	private String regCode;
	private String rxCode;
	private String patientType;
	private Integer drugVarietyVccount;
	private BigDecimal amount;  
	private Integer bigRxAccount;
	private Integer drugDays;
	
	private Date createTime;
	private Date updateTime;
	public DrugUseBi() {
		super();
	}
	public Long getDrugUseId() {
		return drugUseId;
	}
	public void setDrugUseId(Long drugUseId) {
		this.drugUseId = drugUseId;
	}
	public Long getHospitalKey() {
		return hospitalKey;
	}
	public void setHospitalKey(Long hospitalKey) {
		this.hospitalKey = hospitalKey;
	}
	public Long getDrKey() {
		return drKey;
	}
	public void setDrKey(Long drKey) {
		this.drKey = drKey;
	}
	public Long getDeptKey() {
		return deptKey;
	}
	public void setDeptKey(Long deptKey) {
		this.deptKey = deptKey;
	}
	public Long getDrugKey() {
		return drugKey;
	}
	public void setDrugKey(Long drugKey) {
		this.drugKey = drugKey;
	}
	public Long getCalendarKey() {
		return calendarKey;
	}
	public void setCalendarKey(Long calendarKey) {
		this.calendarKey = calendarKey;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRxCode() {
		return rxCode;
	}
	public void setRxCode(String rxCode) {
		this.rxCode = rxCode;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public Integer getDrugVarietyVccount() {
		return drugVarietyVccount;
	}
	public void setDrugVarietyVccount(Integer drugVarietyVccount) {
		this.drugVarietyVccount = drugVarietyVccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getBigRxAccount() {
		return bigRxAccount;
	}
	public void setBigRxAccount(Integer bigRxAccount) {
		this.bigRxAccount = bigRxAccount;
	}
	public Integer getDrugDays() {
		return drugDays;
	}
	public void setDrugDays(Integer drugDays) {
		this.drugDays = drugDays;
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
}
