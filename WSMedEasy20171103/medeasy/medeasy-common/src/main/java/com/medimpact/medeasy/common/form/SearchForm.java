package com.medimpact.medeasy.common.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.core.serializer.Serializer;

import com.medimpact.medeasy.common.RequestParameter;
import com.medimpact.medeasy.common.bean.DrugCategoryBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;

public class SearchForm extends RequestParameter {
	private String areaId;
	private String hospitalId;
	private String patientType; // 处方类型
	private String startDate;
	private String endDate;
	private String startYear;
	private String startMon;
	
	private String endYear;
	private String endMon;
	
	private String hospitalCode;
	private String hospitalKey; //和homepage的检索一直
	private String deptCode;
	private String areaCode;
	private String doctorName;
	
	private String checkedNodesStr ;
	private List<String> drugCLi;
	private List<DrugCategoryBi> drugCategoryBis; //根据drugCLi,得到2-1,3-2的类似形式
	
	/* private String drugClassBtndrugid;*/ // 药品分类中， 具体的药品drug表里的local_drug_id
	private String localDrugId;// 药品分类中， 具体的药品drug表里的local_drug_id
	
	private String drugCId; // 药品分类
	private String irriteId; // 问题代码Id
	
	private String hLDrugCategory; //药品所属的大类
	private char isIV = SYSCONSTANT.CHAR_N; //是否是注射
	private char isHervalIv = SYSCONSTANT.CHAR_N; 
	
	
	//增加一下内容，用于新增的需求默认检索中的当前year,month , for druguse
	private int year;
	private int month;
	

	public SearchForm() {
		super();
	}
	

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		if (hospitalId.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			this.hospitalId = null;
		} else {
			this.hospitalId = hospitalId;
		}
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		
		if (patientType.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			this.patientType = null;
		} else {
			this.patientType = patientType;
		}
		
		if(patientType.equals(SYSCONSTANT.OUTPATIENT_URGENT)){
			this.patientType=null; 
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		if (startDate!=null && startDate.equals("")) {
			this.startDate = null;
		} else {
			this.startDate = startDate;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate!=null &&endDate.equals("")) {
			this.endDate = null;
		} else {
			this.endDate = endDate;
		}
	}

	/*public String getRxTypeId() {
		return rxTypeId;
	}

	public void setRxTypeId(String rxTypeId) {
		if(rxTypeId.equals("")||rxTypeId.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			this.rxTypeId=null;
		}else{
			this.rxTypeId = rxTypeId;
		}
		
	}*/

	public String getDrugCId() {
		return drugCId;
	}

	public void setDrugCId(String drugCId) {
		
		this.drugCId = drugCId;
	}

	public String getIrriteId() {
		return irriteId;
	}

	public void setIrriteId(String irriteId) {
		if(irriteId!=null && (irriteId.equals("")||irriteId.equals(SYSCONSTANT.OPTION_VALUE_NOTHING))){
			this.irriteId = null;
		}else{
			this.irriteId = irriteId;
		}
		
	}

	public String getCheckedNodesStr() {
		return checkedNodesStr;
	}

	public void setCheckedNodesStr(String checkedNodesStr) {
		if(checkedNodesStr!=null && checkedNodesStr.equals("")){
			this.checkedNodesStr = null; 
		}else {
			this.checkedNodesStr = checkedNodesStr;
		}
		
	}

	public List<String> getDrugCLi() {
		return drugCLi;
	}

	public void setDrugCLi(List<String> drugCLi) {
		this.drugCLi = drugCLi;	
	}

	public String gethLDrugCategory() {
		return hLDrugCategory;
	}

	public void sethLDrugCategory(String hLDrugCategory) {
		if(hLDrugCategory !=null && hLDrugCategory.equals("")){
			this.hLDrugCategory = null;
		}else {
			this.hLDrugCategory = hLDrugCategory;
		}		
	}

	public char getIsIV() {
		return isIV;
	}

	

	public void setIsIV(char isIV) {
		this.isIV = isIV;
	}

	public char getIsHervalIv() {
		return isHervalIv;
	}

	public void setIsHervalIv(char isHervalIv) {
		this.isHervalIv = isHervalIv;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {		
		if(hospitalCode !=null && hospitalCode.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){			
			this.hospitalCode=null; 		
		}
		this.hospitalCode = hospitalCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		
		if(areaCode!=null && areaCode.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			this.areaCode = null; 
		}
		this.areaCode = areaCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getStartMon() {
		return startMon;
	}

	public void setStartMon(String startMon) {
		this.startMon = startMon;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMon() {
		return endMon;
	}

	public void setEndMon(String endMon) {
		this.endMon = endMon;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		if (areaId.equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			this.areaId = null;
		} else {
			this.areaId = areaId;
		}
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public List<DrugCategoryBi> getDrugCategoryBis() {
		return drugCategoryBis;
	}


	public void setDrugCategoryBis(List<DrugCategoryBi> drugCategoryBis) {
		this.drugCategoryBis = drugCategoryBis;
	}


	public String getHospitalKey() {
		return hospitalKey;
	}


	public void setHospitalKey(String hospitalKey) {
		this.hospitalKey = hospitalKey;
	}


	public String getLocalDrugId() {
		return localDrugId;
	}


	public void setLocalDrugId(String localDrugId) {		
		this.localDrugId = localDrugId;
	}
}
