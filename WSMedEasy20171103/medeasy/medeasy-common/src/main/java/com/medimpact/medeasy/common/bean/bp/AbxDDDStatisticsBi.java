package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class AbxDDDStatisticsBi extends RequestParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7539861458753509412L;
	
	private Long id;
	private Long dimensionCalanderKey;
	private Long dimensionHospitalKey;
	private Long deptKey;
	private Long dimensionDoctorKey;
	private Long dimesionDrugKey;
	private String patientTypeKey;
	private BigDecimal amount;//药品金额 
	private String rxCode;//处方号
	private String regCode;//就诊号
	private BigDecimal ddds;
	private String drugCategoryName;//药品分类
	private String drugName;//药品名称
	private String genericName;//通用名称
	private String dosageForm;//剂型
	private String spec;//规格
	private String manufacture;//厂家
	private String abxLevel;//级别
	private String drugPackage;//数量(=包装单位)
	private Double amountPercentage;//抗菌药使用金额占比
	private Double totalPercentage;//金额总占比
	private Integer rxCount;//抗菌药物处方数
	private Double rxPercentage;//抗菌药物处方数占比
	private Double rxTotalPercentage;//抗菌药物处方数总占比
	private Integer drugUsedCount;//抗菌药物使用人次
	private Double averageAmount;//人均抗菌药物使用金额
	private Double drugUsage;//抗菌药物使用率
	private Integer dddCount;//累计DDD数
	private Integer areaCount;//区
	private Integer hospitalCount;//医院
	private Integer doctorCount;//医师
	private Integer deptCount;//科室
	
	private Date createTime;
	private Date lastUpdateTime;
	
	private String areaKey;
	private String hospitalKey;
	private String deptSerKey;
	private String physicianKey;
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
	public Long getDimesionDrugKey() {
		return dimesionDrugKey;
	}
	public void setDimesionDrugKey(Long dimesionDrugKey) {
		this.dimesionDrugKey = dimesionDrugKey;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRxCode() {
		return rxCode;
	}
	public void setRxCode(String rxCode) {
		this.rxCode = rxCode;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public BigDecimal getDdds() {
		return ddds;
	}
	public void setDdds(BigDecimal ddds) {
		this.ddds = ddds;
	}
	public String getDrugCategoryName() {
		return drugCategoryName;
	}
	public void setDrugCategoryName(String drugCategoryName) {
		this.drugCategoryName = drugCategoryName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getAbxLevel() {
		return abxLevel;
	}
	public void setAbxLevel(String abxLevel) {
		this.abxLevel = abxLevel;
	}
	public String getDrugPackage() {
		return drugPackage;
	}
	public void setDrugPackage(String drugPackage) {
		this.drugPackage = drugPackage;
	}
	public Double getAmountPercentage() {
		return amountPercentage;
	}
	public void setAmountPercentage(Double amountPercentage) {
		this.amountPercentage = amountPercentage;
	}
	public Double getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(Double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	public Integer getRxCount() {
		return rxCount;
	}
	public void setRxCount(Integer rxCount) {
		this.rxCount = rxCount;
	}
	public Double getRxPercentage() {
		return rxPercentage;
	}
	public void setRxPercentage(Double rxPercentage) {
		this.rxPercentage = rxPercentage;
	}
	public Double getRxTotalPercentage() {
		return rxTotalPercentage;
	}
	public void setRxTotalPercentage(Double rxTotalPercentage) {
		this.rxTotalPercentage = rxTotalPercentage;
	}
	public Integer getDrugUsedCount() {
		return drugUsedCount;
	}
	public void setDrugUsedCount(Integer drugUsedCount) {
		this.drugUsedCount = drugUsedCount;
	}
	public Double getAverageAmount() {
		return averageAmount;
	}
	public void setAverageAmount(Double averageAmount) {
		this.averageAmount = averageAmount;
	}
	public Double getDrugUsage() {
		return drugUsage;
	}
	public void setDrugUsage(Double drugUsage) {
		this.drugUsage = drugUsage;
	}
	public Integer getDddCount() {
		return dddCount;
	}
	public void setDddCount(Integer dddCount) {
		this.dddCount = dddCount;
	}
	public Integer getAreaCount() {
		return areaCount;
	}
	public void setAreaCount(Integer areaCount) {
		this.areaCount = areaCount;
	}
	public Integer getHospitalCount() {
		return hospitalCount;
	}
	public void setHospitalCount(Integer hospitalCount) {
		this.hospitalCount = hospitalCount;
	}
	public Integer getDoctorCount() {
		return doctorCount;
	}
	public void setDoctorCount(Integer doctorCount) {
		this.doctorCount = doctorCount;
	}
	public Integer getDeptCount() {
		return deptCount;
	}
	public void setDeptCount(Integer deptCount) {
		this.deptCount = deptCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
		return "AbxDDDStatisticsBi [id=" + id + ", dimensionCalanderKey=" + dimensionCalanderKey
				+ ", dimensionHospitalKey=" + dimensionHospitalKey + ", deptKey=" + deptKey + ", dimensionDoctorKey="
				+ dimensionDoctorKey + ", dimesionDrugKey=" + dimesionDrugKey + ", amount=" + amount + ", rxCode="
				+ rxCode + ", regCode=" + regCode + ", ddds=" + ddds + ", drugCategoryName=" + drugCategoryName
				+ ", drugName=" + drugName + ", genericName=" + genericName + ", dosageForm=" + dosageForm + ", spec="
				+ spec + ", manufacture=" + manufacture + ", abxLevel=" + abxLevel + ", drugPackage=" + drugPackage
				+ ", amountPercentage=" + amountPercentage + ", totalPercentage=" + totalPercentage + ", rxCount="
				+ rxCount + ", rxPercentage=" + rxPercentage + ", rxTotalPercentage=" + rxTotalPercentage
				+ ", drugUsedCount=" + drugUsedCount + ", averageAmount=" + averageAmount + ", drugUsage=" + drugUsage
				+ ", dddCount=" + dddCount + ", areaCount=" + areaCount + ", hospitalCount=" + hospitalCount
				+ ", doctorCount=" + doctorCount + ", deptCount=" + deptCount + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", areaKey=" + areaKey + ", hospitalKey=" + hospitalKey
				+ ", deptSerKey=" + deptSerKey + ", physicianKey=" + physicianKey + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	public String getPatientTypeKey() {
		return patientTypeKey;
	}

	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
	}
}
