package com.medimpact.medeasy.common.bean.bp;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class AbxUnauthorizedUsedAnaBi extends RequestParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3191314291885396747L;
	private Long id;
	private Long dimensionCalanderKey;
	private Long dimensionHospitalKey;
	private Long dimensionDoctorKey;
	private Long deptKey;
	private Long dimesionDrugKey;
	private Integer overRightAccount;//越级次数
	
	private String hospitalName;//医院名称
	private String areaName;//地区名称
	private Integer deptCount;//科室数量
	private Integer doctorCount;//医师数量
	private Integer drugCount;//药品数量
	
	private String statisticsTypeKey;
	private String patientTypeKey;
	private String abxLevelKey;
	private String drugClassBtndrugid;//药品id（,分割）
	private String drugClassBtndrugcategoryid;//药品分类id（,分割）
	private String startDate;
	private String endDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDimensionCalanderKey() {
		return dimensionCalanderKey;
	}
	public void setDimensionCalanderKey(Long dimensionCalanderKey) {
		this.dimensionCalanderKey = dimensionCalanderKey;
	}
	public Long getDimensionHospitalKey() {
		return dimensionHospitalKey;
	}
	public void setDimensionHospitalKey(Long dimensionHospitalKey) {
		this.dimensionHospitalKey = dimensionHospitalKey;
	}
	public Long getDimensionDoctorKey() {
		return dimensionDoctorKey;
	}
	public void setDimensionDoctorKey(Long dimensionDoctorKey) {
		this.dimensionDoctorKey = dimensionDoctorKey;
	}
	public Long getDeptKey() {
		return deptKey;
	}
	public void setDeptKey(Long deptKey) {
		this.deptKey = deptKey;
	}
	public Long getDimesionDrugKey() {
		return dimesionDrugKey;
	}
	public void setDimesionDrugKey(Long dimesionDrugKey) {
		this.dimesionDrugKey = dimesionDrugKey;
	}
	public Integer getOverRightAccount() {
		return overRightAccount;
	}
	public void setOverRightAccount(Integer overRightAccount) {
		this.overRightAccount = overRightAccount;
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
	public Integer getDeptCount() {
		return deptCount;
	}
	public void setDeptCount(Integer deptCount) {
		this.deptCount = deptCount;
	}
	public Integer getDoctorCount() {
		return doctorCount;
	}
	public void setDoctorCount(Integer doctorCount) {
		this.doctorCount = doctorCount;
	}
	public Integer getDrugCount() {
		return drugCount;
	}
	public void setDrugCount(Integer drugCount) {
		this.drugCount = drugCount;
	}
	public String getStatisticsTypeKey() {
		return statisticsTypeKey;
	}
	public void setStatisticsTypeKey(String statisticsTypeKey) {
		this.statisticsTypeKey = statisticsTypeKey;
	}
	public String getPatientTypeKey() {
		return patientTypeKey;
	}
	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
	}
	public String getAbxLevelKey() {
		return abxLevelKey;
	}
	public void setAbxLevelKey(String abxLevelKey) {
		this.abxLevelKey = abxLevelKey;
	}
	public String getDrugClassBtndrugid() {
		return drugClassBtndrugid;
	}
	public void setDrugClassBtndrugid(String drugClassBtndrugid) {
		this.drugClassBtndrugid = drugClassBtndrugid;
	}
	public String getDrugClassBtndrugcategoryid() {
		return drugClassBtndrugcategoryid;
	}
	public void setDrugClassBtndrugcategoryid(String drugClassBtndrugcategoryid) {
		this.drugClassBtndrugcategoryid = drugClassBtndrugcategoryid;
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
	@Override
	public String toString() {
		return "AbxUnauthorizedUsedAnaBi [id=" + id + ", dimensionCalanderKey=" + dimensionCalanderKey
				+ ", dimensionHospitalKey=" + dimensionHospitalKey + ", dimensionDoctorKey=" + dimensionDoctorKey
				+ ", deptKey=" + deptKey + ", dimesionDrugKey=" + dimesionDrugKey + ", overRightAccount="
				+ overRightAccount + ", hospitalName=" + hospitalName + ", areaName=" + areaName + ", deptCount="
				+ deptCount + ", doctorCount=" + doctorCount + ", drugCount=" + drugCount + ", statisticsTypeKey="
				+ statisticsTypeKey + ", patientTypeKey=" + patientTypeKey + ", abxLevelKey=" + abxLevelKey
				+ ", drugClassBtndrugid=" + drugClassBtndrugid + ", drugClassBtndrugcategoryid="
				+ drugClassBtndrugcategoryid + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
