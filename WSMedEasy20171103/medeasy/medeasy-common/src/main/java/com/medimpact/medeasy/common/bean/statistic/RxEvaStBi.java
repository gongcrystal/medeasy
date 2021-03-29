package com.medimpact.medeasy.common.bean.statistic;

import com.medimpact.medeasy.common.RequestParameter;

public class RxEvaStBi extends RequestParameter {
	private Long id;
	private String hospitalName;
	private String rxCode;
	private String areaName;
	private String patientType;//处方类型名
	private String hospitalCode; 

	/*private int rxAmount;*/ //处方数 已有
	//抽样数量? 
	private int deptAmount ; //科室数量
	private int drAmount; //医生数量
	private Long drugAmount =new Long(0); //药品数量
	/*private BigDecimal mamount=new BigDecimal(0); */// include statisticParam药品使用金额（元）
	/*private Long okAmount; //合理处方数*/
	/*private Long nonOkamount ; //不合理处方数
*/	private Long unReasonableAmount1 = new Long(0);  //不规范处方数
	private Long unReasonableAmount2 = new Long(0);   //用药不适宜处方数

	private Long unReasonableAmount3= new Long(0); //超常处方数
	/*private Long abxAmount;//抗菌药物处方数;
*/	
	/*private Long nonOkAbxAmount; *///抗菌药物处方不合理数: 同时满足时抗菌药，并且是不合理药
	private String problemCode; //问题代码??
	private String problemCodeAmount; //问题代码数量
	
	
	public RxEvaStBi() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	

	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public int getDeptAmount() {
		return deptAmount;
	}
	public void setDeptAmount(int deptAmount) {
		this.deptAmount = deptAmount;
	}
	public int getDrAmount() {
		return drAmount;
	}
	public void setDrAmount(int drAmount) {
		this.drAmount = drAmount;
	}
	public Long getDrugAmount() {
		return drugAmount;
	}
	public void setDrugAmount(Long drugAmount) {
		this.drugAmount = drugAmount;
	}
	/*public Long getOkAmount() {
		return okAmount;
	}
	public void setOkAmount(Long okAmount) {
		this.okAmount = okAmount;
	}*/
	/*public Long getNonOkamount() {
		return nonOkamount;
	}
	public void setNonOkamount(Long nonOkamount) {
		this.nonOkamount = nonOkamount;
	}*/
	public Long getUnReasonableAmount1() {
		return unReasonableAmount1;
	}
	public void setUnReasonableAmount1(Long unReasonableAmount1) {
		this.unReasonableAmount1 = unReasonableAmount1;
	}
	public Long getUnReasonableAmount2() {
		return unReasonableAmount2;
	}
	public void setUnReasonableAmount2(Long unReasonableAmount2) {
		this.unReasonableAmount2 = unReasonableAmount2;
	}
	public Long getUnReasonableAmount3() {
		return unReasonableAmount3;
	}
	public void setUnReasonableAmount3(Long unReasonableAmount3) {
		this.unReasonableAmount3 = unReasonableAmount3;
	}
	/*public Long getAbxAmount() {
		return abxAmount;
	}
	public void setAbxAmount(Long abxAmount) {
		this.abxAmount = abxAmount;
	}*/
	/*public Long getNonOkAbxAmount() {
		return nonOkAbxAmount;
	}
	public void setNonOkAbxAmount(Long nonOkAbxAmount) {
		this.nonOkAbxAmount = nonOkAbxAmount;
	}*/
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}


	public String getRxCode() {
		return rxCode;
	}


	public void setRxCode(String rxCode) {
		this.rxCode = rxCode;
	}


	public String getProblemCodeAmount() {
		return problemCodeAmount;
	}


	public void setProblemCodeAmount(String problemCodeAmount) {
		this.problemCodeAmount = problemCodeAmount;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
}
