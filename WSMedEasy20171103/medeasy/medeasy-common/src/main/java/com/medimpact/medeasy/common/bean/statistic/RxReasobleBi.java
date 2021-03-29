package com.medimpact.medeasy.common.bean.statistic;

import java.math.BigDecimal;

public class RxReasobleBi {
	private String hospitalName;
	private String hospitalCode;
	private Long hospitalId; //
	private Long okAmount = new Long(0); //合理处方数
	private Long nonOkamount = new Long(0);  //不合理处方数
	private Long unReasonableAmount1 = new Long(0);  //不规范处方数
	private Long unReasonableAmount2 = new Long(0);   //用药不适宜处方数
	private Long unReasonableAmount3= new Long(0); //超常处方数
	private Long abxAmount;//抗菌药物处方数;		
	private Long nonOkAbxAmount; //抗菌药物处方不合理数: 同时满足时抗菌药，并且是不合理药
	private BigDecimal mamount=new BigDecimal(0);
	
	public RxReasobleBi() {
		super();
	}
	
	
	public BigDecimal getMamount() {
		return mamount;
	}



	public void setMamount(BigDecimal mamount) {
		this.mamount = mamount;
	}



	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getOkAmount() {
		return okAmount;
	}
	public void setOkAmount(Long okAmount) {
		this.okAmount = okAmount;
	}
	public Long getNonOkamount() {
		return nonOkamount;
	}
	public void setNonOkamount(Long nonOkamount) {
		this.nonOkamount = nonOkamount;
	}
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

	public Long getAbxAmount() {
		return abxAmount;
	}

	public void setAbxAmount(Long abxAmount) {
		this.abxAmount = abxAmount;
	}

	public Long getNonOkAbxAmount() {
		return nonOkAbxAmount;
	}

	public void setNonOkAbxAmount(Long nonOkAbxAmount) {
		this.nonOkAbxAmount = nonOkAbxAmount;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
