package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class BaseDrugUsedStatisticsBi extends RequestParameter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6804181784489532801L;
	
	private Long id;
	private Long dimensionHospitalKey;
	private String hospitalName;//医院名称
	private String hospitalCode; 
	private String areaName;//地区名称
	private Long dimesionDrugKey;
	private Long dimensionDoctorKey;
	private Long deptKey;
	private Long dimensionCalanderKey;
	private BigDecimal amount;//药品使用金额, 分母不受“药品/药品分类/快速选择药品”条件影响
	private BigDecimal baseDrugAmount;//基本药品使用金额
	private Integer doctorCount;//医师数量
	private Integer deptCount;//科室数量
	private Integer patientCount;//患者数量
	private Integer diagnosisCount;//诊断数量
	private Integer baseDrugCount;//基本药物品种数
	private Double countPercentage;//基本药物品种数占比
	private Double amountPercentage;//基本药物使用金额占比
	
	private Date createTime;
	private Date lastUpdateTime;
	
	private String searchTypeKey;
	private String patientTypeKey;
	private String drugClassBtndrugid;//药品id（,分割）
	private String drugClassBtndrugcategoryid;//药品分类id（,分割）
	private String basicDrugCategory;
	private String startDate;
	private String endDate;	
	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
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
	public Long getDimesionDrugKey() {
		return dimesionDrugKey;
	}
	public void setDimesionDrugKey(Long dimesionDrugKey) {
		this.dimesionDrugKey = dimesionDrugKey;
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
	public Long getDimensionCalanderKey() {
		return dimensionCalanderKey;
	}
	public void setDimensionCalanderKey(Long dimensionCalanderKey) {
		this.dimensionCalanderKey = dimensionCalanderKey;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getBaseDrugAmount() {
		return baseDrugAmount;
	}
	public void setBaseDrugAmount(BigDecimal baseDrugAmount) {
		this.baseDrugAmount = baseDrugAmount;
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
	public Integer getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(Integer patientCount) {
		this.patientCount = patientCount;
	}
	public Integer getDiagnosisCount() {
		return diagnosisCount;
	}
	public void setDiagnosisCount(Integer diagnosisCount) {
		this.diagnosisCount = diagnosisCount;
	}
	public Integer getBaseDrugCount() {
		return baseDrugCount;
	}
	public void setBaseDrugCount(Integer baseDrugCount) {
		this.baseDrugCount = baseDrugCount;
	}
	public Double getCountPercentage() {
		return countPercentage;
	}
	public void setCountPercentage(Double countPercentage) {
		this.countPercentage = countPercentage;
	}
	public Double getAmountPercentage() {
		return amountPercentage;
	}
	public void setAmountPercentage(Double amountPercentage) {
		this.amountPercentage = amountPercentage;
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
	public String getSearchTypeKey() {
		return searchTypeKey;
	}
	public void setSearchTypeKey(String searchTypeKey) {
		this.searchTypeKey = searchTypeKey;
	}
	public String getPatientTypeKey() {
		return patientTypeKey;
	}
	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
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
	public String getBasicDrugCategory() {
		return basicDrugCategory;
	}
	public void setBasicDrugCategory(String basicDrugCategory) {
		this.basicDrugCategory = basicDrugCategory;
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
		return "BaseDrugUsedStatisticsBi [id=" + id + ", dimensionHospitalKey=" + dimensionHospitalKey
				+ ", hospitalName=" + hospitalName + ", areaName=" + areaName + ", dimesionDrugKey=" + dimesionDrugKey
				+ ", dimensionDoctorKey=" + dimensionDoctorKey + ", deptKey=" + deptKey + ", dimensionCalanderKey="
				+ dimensionCalanderKey + ", amount=" + amount + ", baseDrugAmount=" + baseDrugAmount + ", doctorCount="
				+ doctorCount + ", deptCount=" + deptCount + ", patientCount=" + patientCount + ", diagnosisCount="
				+ diagnosisCount + ", baseDrugCount=" + baseDrugCount + ", countPercentage=" + countPercentage
				+ ", amountPercentage=" + amountPercentage + ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + ", searchTypeKey=" + searchTypeKey + ", patientTypeKey=" + patientTypeKey
				+ ", drugClassBtndrugid=" + drugClassBtndrugid + ", drugClassBtndrugcategoryid="
				+ drugClassBtndrugcategoryid + ", basicDrugCategory=" + basicDrugCategory + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
}
