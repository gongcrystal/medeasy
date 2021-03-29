package com.medimpact.medeasy.common.bean.statistic;


public class HospitalDrugUseDrugVarietyBi {
	
	private String hospitalName;
	private Long drugSpecAmount=new Long(0); 
	private String drugBasicCatetory;
	
	private Integer bigRx = 0; //大处方
	
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Long getDrugSpecAmount() {
		return drugSpecAmount;
	}
	public void setDrugSpecAmount(Long drugSpecAmount) {
		this.drugSpecAmount = drugSpecAmount;
	}
	public HospitalDrugUseDrugVarietyBi() {
		super();
	}
	public String getDrugBasicCatetory() {
		return drugBasicCatetory;
	}
	public void setDrugBasicCatetory(String drugBasicCatetory) {
		this.drugBasicCatetory = drugBasicCatetory;
	}
	public Integer getBigRx() {
		return bigRx;
	}
	public void setBigRx(Integer bigRx) {
		this.bigRx = bigRx;
	}
	
	
}
