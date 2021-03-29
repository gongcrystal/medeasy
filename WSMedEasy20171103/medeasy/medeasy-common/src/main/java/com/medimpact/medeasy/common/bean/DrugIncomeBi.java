package com.medimpact.medeasy.common.bean;

import java.math.BigDecimal;
import java.util.Date;

public class DrugIncomeBi {
	private Long drugIncomeId;
	private Long doctorKey;
	private Long deptKey;
	private Long hospitalKey;
	private Long drugItemKey;
	private Long calanderKey;
	private String patientType;
	private BigDecimal amount;
	private BigDecimal patientAmount;//就诊人次
	private Integer rxAmount;//处方数
	private Date createTime;
	private Date lastTime;
	public DrugIncomeBi() {
		super();
	}
	public Long getDrugIncomeId() {
		return drugIncomeId;
	}
	public void setDrugIncomeId(Long drugIncomeId) {
		this.drugIncomeId = drugIncomeId;
	}
	public Long getDoctorKey() {
		return doctorKey;
	}
	public void setDoctorKey(Long doctorKey) {
		this.doctorKey = doctorKey;
	}
	public Long getDeptKey() {
		return deptKey;
	}
	public void setDeptKey(Long deptKey) {
		this.deptKey = deptKey;
	}
	public Long getHospitalKey() {
		return hospitalKey;
	}
	public void setHospitalKey(Long hospitalKey) {
		this.hospitalKey = hospitalKey;
	}
	public Long getDrugItemKey() {
		return drugItemKey;
	}
	public void setDrugItemKey(Long drugItemKey) {
		this.drugItemKey = drugItemKey;
	}
	public Long getCalanderKey() {
		return calanderKey;
	}
	public void setCalanderKey(Long calanderKey) {
		this.calanderKey = calanderKey;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPatientAmount() {
		return patientAmount;
	}
	public void setPatientAmount(BigDecimal patientAmount) {
		this.patientAmount = patientAmount;
	}
	public Integer getRxAmount() {
		return rxAmount;
	}
	public void setRxAmount(Integer rxAmount) {
		this.rxAmount = rxAmount;
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
