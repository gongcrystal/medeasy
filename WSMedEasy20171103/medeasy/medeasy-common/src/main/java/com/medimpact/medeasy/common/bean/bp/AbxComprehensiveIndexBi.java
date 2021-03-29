package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class AbxComprehensiveIndexBi extends RequestParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9046479325326327563L;
	
	private Long id;
	private Long dimensionHospitalKey;
	private Integer dimensionPatientType;
	private Long deptKey;
	private Long dimensionDoctorKey;
	private Integer year;//年
	private Integer month;//月
	private Integer abxVariety;//抗菌药总品种数
	private Integer outpatientAmount;//（就诊）人次
	private Integer inpatientAmount;//（出院）人次
	private BigDecimal abxAmount;//抗菌药物使用金额
	private BigDecimal drugAmount;//药品金额
	private Integer abxRxAmount;//抗菌药处方数
	private Integer outpatientAbxIvAmount;//静脉输液人次
	private Integer outpatientAbxAmount;//抗菌药物人次
	private Integer abxUnrestrictAmount;//非限制抗菌药例数
	private Integer abxRestrictAmount;//限制抗菌药例数
	private Integer abxIvCaseNum;//静脉输液抗菌药物例数
	
	private Double perAbxVariety;//人均抗菌药物品种数
	private Double perAbxAmount;//人均抗菌药物使用金额（元）
	private Double abxAmountPercent;//抗菌药物使用金额占比%
	private Double abxRxAmountPercent;//抗菌药物处方数占比%
	private Double outpatientAbxIvPercent;//静脉输液使用率%
	private Double outpatientAbxPercent;//抗菌药物使用率%
	private Double abxUnrestrictPercent;//非限制抗菌药物使用率%
	private Double abxRestrictPercent;//限制抗菌药物使用率%
	private Double abxIvCasePercent;//静脉输液抗菌药物使用率%
	
	private String areaKey;
	private String hospitalKey;
	private String deptSerKey;
	private String physicianKey;
	private String patientTypeKey;
	private String startDate;
	private String endDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDimensionHospitalKey() {
		return dimensionHospitalKey;
	}
	public void setDimensionHospitalKey(Long dimensionHospitalKey) {
		this.dimensionHospitalKey = dimensionHospitalKey;
	}
	public Integer getDimensionPatientType() {
		return dimensionPatientType;
	}
	public void setDimensionPatientType(Integer dimensionPatientType) {
		this.dimensionPatientType = dimensionPatientType;
	}
	public Long getDeptKey() {
		return deptKey;
	}
	public void setDeptKey(Long deptKey) {
		this.deptKey = deptKey;
	}
	public Long getDimensionDoctorKey() {
		return dimensionDoctorKey;
	}
	public void setDimensionDoctorKey(Long dimensionDoctorKey) {
		this.dimensionDoctorKey = dimensionDoctorKey;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getAbxVariety() {
		return abxVariety;
	}
	public void setAbxVariety(Integer abxVariety) {
		this.abxVariety = abxVariety;
	}
	public Integer getOutpatientAmount() {
		return outpatientAmount;
	}
	public void setOutpatientAmount(Integer outpatientAmount) {
		this.outpatientAmount = outpatientAmount;
	}
	public Integer getInpatientAmount() {
		return inpatientAmount;
	}
	public void setInpatientAmount(Integer inpatientAmount) {
		this.inpatientAmount = inpatientAmount;
	}
	public BigDecimal getAbxAmount() {
		return abxAmount;
	}
	public void setAbxAmount(BigDecimal abxAmount) {
		this.abxAmount = abxAmount;
	}
	public BigDecimal getDrugAmount() {
		return drugAmount;
	}
	public void setDrugAmount(BigDecimal drugAmount) {
		this.drugAmount = drugAmount;
	}
	public Integer getAbxRxAmount() {
		return abxRxAmount;
	}
	public void setAbxRxAmount(Integer abxRxAmount) {
		this.abxRxAmount = abxRxAmount;
	}
	public Integer getOutpatientAbxIvAmount() {
		return outpatientAbxIvAmount;
	}
	public void setOutpatientAbxIvAmount(Integer outpatientAbxIvAmount) {
		this.outpatientAbxIvAmount = outpatientAbxIvAmount;
	}
	public Integer getOutpatientAbxAmount() {
		return outpatientAbxAmount;
	}
	public void setOutpatientAbxAmount(Integer outpatientAbxAmount) {
		this.outpatientAbxAmount = outpatientAbxAmount;
	}
	public Integer getAbxUnrestrictAmount() {
		return abxUnrestrictAmount;
	}
	public void setAbxUnrestrictAmount(Integer abxUnrestrictAmount) {
		this.abxUnrestrictAmount = abxUnrestrictAmount;
	}
	public Integer getAbxRestrictAmount() {
		return abxRestrictAmount;
	}
	public void setAbxRestrictAmount(Integer abxRestrictAmount) {
		this.abxRestrictAmount = abxRestrictAmount;
	}
	public Integer getAbxIvCaseNum() {
		return abxIvCaseNum;
	}
	public void setAbxIvCaseNum(Integer abxIvCaseNum) {
		this.abxIvCaseNum = abxIvCaseNum;
	}
	public Double getPerAbxVariety() {
		return perAbxVariety;
	}
	public void setPerAbxVariety(Double perAbxVariety) {
		this.perAbxVariety = perAbxVariety;
	}
	public Double getPerAbxAmount() {
		return perAbxAmount;
	}
	public void setPerAbxAmount(Double perAbxAmount) {
		this.perAbxAmount = perAbxAmount;
	}
	public Double getAbxAmountPercent() {
		return abxAmountPercent;
	}
	public void setAbxAmountPercent(Double abxAmountPercent) {
		this.abxAmountPercent = abxAmountPercent;
	}
	public Double getAbxRxAmountPercent() {
		return abxRxAmountPercent;
	}
	public void setAbxRxAmountPercent(Double abxRxAmountPercent) {
		this.abxRxAmountPercent = abxRxAmountPercent;
	}
	public Double getOutpatientAbxIvPercent() {
		return outpatientAbxIvPercent;
	}
	public void setOutpatientAbxIvPercent(Double outpatientAbxIvPercent) {
		this.outpatientAbxIvPercent = outpatientAbxIvPercent;
	}
	public Double getOutpatientAbxPercent() {
		return outpatientAbxPercent;
	}
	public void setOutpatientAbxPercent(Double outpatientAbxPercent) {
		this.outpatientAbxPercent = outpatientAbxPercent;
	}
	public Double getAbxUnrestrictPercent() {
		return abxUnrestrictPercent;
	}
	public void setAbxUnrestrictPercent(Double abxUnrestrictPercent) {
		this.abxUnrestrictPercent = abxUnrestrictPercent;
	}
	public Double getAbxRestrictPercent() {
		return abxRestrictPercent;
	}
	public void setAbxRestrictPercent(Double abxRestrictPercent) {
		this.abxRestrictPercent = abxRestrictPercent;
	}
	public Double getAbxIvCasePercent() {
		return abxIvCasePercent;
	}
	public void setAbxIvCasePercent(Double abxIvCasePercent) {
		this.abxIvCasePercent = abxIvCasePercent;
	}
	public String getAreaKey() {
		return areaKey;
	}
	public void setAreaKey(String areaKey) {
		this.areaKey = areaKey;
	}
	public String getHospitalKey() {
		return hospitalKey;
	}
	public void setHospitalKey(String hospitalKey) {
		this.hospitalKey = hospitalKey;
	}
	public String getDeptSerKey() {
		return deptSerKey;
	}
	public void setDeptSerKey(String deptSerKey) {
		this.deptSerKey = deptSerKey;
	}
	public String getPhysicianKey() {
		return physicianKey;
	}
	public void setPhysicianKey(String physicianKey) {
		this.physicianKey = physicianKey;
	}
	public String getPatientTypeKey() {
		return patientTypeKey;
	}
	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
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
		return "AbxComprehensiveIndexBi [id=" + id + ", dimensionHospitalKey=" + dimensionHospitalKey
				+ ", dimensionPatientType=" + dimensionPatientType + ", deptKey=" + deptKey + ", dimensionDoctorKey="
				+ dimensionDoctorKey + ", year=" + year + ", month=" + month + ", abxVariety=" + abxVariety
				+ ", outpatientAmount=" + outpatientAmount + ", inpatientAmount=" + inpatientAmount + ", abxAmount="
				+ abxAmount + ", drugAmount=" + drugAmount + ", abxRxAmount=" + abxRxAmount + ", outpatientAbxIvAmount="
				+ outpatientAbxIvAmount + ", outpatientAbxAmount=" + outpatientAbxAmount + ", abxUnrestrictAmount="
				+ abxUnrestrictAmount + ", abxRestrictAmount=" + abxRestrictAmount + ", abxIvCaseNum=" + abxIvCaseNum
				+ ", perAbxVariety=" + perAbxVariety + ", perAbxAmount=" + perAbxAmount + ", abxAmountPercent="
				+ abxAmountPercent + ", abxRxAmountPercent=" + abxRxAmountPercent + ", outpatientAbxIvPercent="
				+ outpatientAbxIvPercent + ", outpatientAbxPercent=" + outpatientAbxPercent + ", abxUnrestrictPercent="
				+ abxUnrestrictPercent + ", abxRestrictPercent=" + abxRestrictPercent + ", abxIvCasePercent="
				+ abxIvCasePercent + ", areaKey=" + areaKey + ", hospitalKey=" + hospitalKey + ", deptSerKey="
				+ deptSerKey + ", physicianKey=" + physicianKey + ", patientTypeKey=" + patientTypeKey + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
}
