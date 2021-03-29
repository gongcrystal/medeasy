package com.medimpact.medeasy.common.bean.statistic;

import java.math.BigDecimal;

import com.medimpact.medeasy.common.StatisticParameter;

public class HospitalDrugUseStBi extends StatisticParameter{
	private String hospitalName;
	private String drugBasicCatetory;
	
	
	private Long drugSpecAmount1=new Long(0);  // 按药品ID去重 ，计算药品总数，这里不用于计算人均药品品种数，跟drug_variety_account无关
	
	private Integer depts =0;
	private Integer drs =0;
	
	
	public HospitalDrugUseStBi() {
		super();
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	public Integer getDepts() {
		return depts;
	}
	public void setDepts(Integer depts) {
		this.depts = depts;
	}
	public Integer getDrs() {
		return drs;
	}
	public void setDrs(Integer drs) {
		this.drs = drs;
	}
	public String getDrugBasicCatetory() {
		return drugBasicCatetory;
	}
	public void setDrugBasicCatetory(String drugBasicCatetory) {
		this.drugBasicCatetory = drugBasicCatetory;
	}
	public Long getDrugSpecAmount1() {
		return drugSpecAmount1;
	}
	public void setDrugSpecAmount1(Long drugSpecAmount1) {
		this.drugSpecAmount1 = drugSpecAmount1;
	}
	
	
}
