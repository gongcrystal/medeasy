package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class AbxUsedStatisticsBi extends RequestParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4820228019167855288L;
	
	private Long id;
	private Long dimensionHospitalKey;
	private Integer dimensionPatientType;
	private Long deptKey;
	private Long dimensionDoctorKey;
	private Long dimesionDrugKey;
	private Long dimensionCalanderKey;
	private String rxCode;//处方号
	private String regCode;//就诊号
	private Integer haveAbxIn;//处方是否含抗菌药
	private BigDecimal amount;//药品金额
	private Integer isAbx;//是否抗菌药
	private String abxLevel;//抗菌药级别
	private Integer rxAbxVariety;//处方抗菌药品种数
	private BigDecimal rxAbxAmount;//处方抗菌药金额
	private Integer regAbxVariety;//就诊抗菌药品种数
	private BigDecimal regAbxAmount;//就诊抗菌药金额
	private Integer isIv;//是否静脉输液
	private Integer isOver2;//是否二联以上用处方
	private String hospitalCode;//医院编码
	private String hospitalName;//医院名称
	private String areaName;//地区名称
	private Integer hospitalCount;//医院数量
	private Integer deptCount;//科室数量
	private Integer doctorCount;//医师数量
	private Integer drugCount;//药品数量
	private Integer patientCount;//就诊人次
	private Integer drugUsedCount;//抗菌药物使用人次
	private Integer rxCount;//抗菌药物处方数
	private Double rxPercentage;//抗菌药物处方数占比
	private Double amountPercentage;//抗菌药使用金额占比
	private Double totalPercentage;//金额总占比
	private Double averageRxDrug;//平均处方抗菌药物品种数
	private BigDecimal averageRxAmount;//平均处方抗菌药物金额
	private Double perDrugCount;//人均抗菌药物品种数
	private BigDecimal perDrugAmount;//人均抗菌药物使用金额
	private Double abxDrugUsage;//抗菌药物使用率
	private Double ivDrugUsage;//静脉输液抗菌药物使用率
	private Double over2DrugUsage;//抗菌药物二联以上用处方比例
	private Integer ivDrugRegCount;//静脉输液抗菌药物就诊人次
	private Integer Over2DrugRegCount;//抗菌药物二联以上用处方数
	
	private Date createTime;
	private Date lastUpdateTime;
	
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
	public Long getDimesionDrugKey() {
		return dimesionDrugKey;
	}
	public void setDimesionDrugKey(Long dimesionDrugKey) {
		this.dimesionDrugKey = dimesionDrugKey;
	}
	public Long getDimensionCalanderKey() {
		return dimensionCalanderKey;
	}
	public void setDimensionCalanderKey(Long dimensionCalanderKey) {
		this.dimensionCalanderKey = dimensionCalanderKey;
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
	public Integer getHaveAbxIn() {
		return haveAbxIn;
	}
	public void setHaveAbxIn(Integer haveAbxIn) {
		this.haveAbxIn = haveAbxIn;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getIsAbx() {
		return isAbx;
	}
	public void setIsAbx(Integer isAbx) {
		this.isAbx = isAbx;
	}
	public String getAbxLevel() {
		return abxLevel;
	}
	public void setAbxLevel(String abxLevel) {
		this.abxLevel = abxLevel;
	}
	public Integer getRxAbxVariety() {
		return rxAbxVariety;
	}
	public void setRxAbxVariety(Integer rxAbxVariety) {
		this.rxAbxVariety = rxAbxVariety;
	}
	public BigDecimal getRxAbxAmount() {
		return rxAbxAmount;
	}
	public void setRxAbxAmount(BigDecimal rxAbxAmount) {
		this.rxAbxAmount = rxAbxAmount;
	}
	public Integer getRegAbxVariety() {
		return regAbxVariety;
	}
	public void setRegAbxVariety(Integer regAbxVariety) {
		this.regAbxVariety = regAbxVariety;
	}
	public BigDecimal getRegAbxAmount() {
		return regAbxAmount;
	}
	public void setRegAbxAmount(BigDecimal regAbxAmount) {
		this.regAbxAmount = regAbxAmount;
	}
	public Integer getIsIv() {
		return isIv;
	}
	public void setIsIv(Integer isIv) {
		this.isIv = isIv;
	}
	public Integer getIsOver2() {
		return isOver2;
	}
	public void setIsOver2(Integer isOver2) {
		this.isOver2 = isOver2;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
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
	public Integer getHospitalCount() {
		return hospitalCount;
	}
	public void setHospitalCount(Integer hospitalCount) {
		this.hospitalCount = hospitalCount;
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
	public Integer getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(Integer patientCount) {
		this.patientCount = patientCount;
	}
	public Integer getDrugUsedCount() {
		return drugUsedCount;
	}
	public void setDrugUsedCount(Integer drugUsedCount) {
		this.drugUsedCount = drugUsedCount;
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
	public Double getAverageRxDrug() {
		return averageRxDrug;
	}
	public void setAverageRxDrug(Double averageRxDrug) {
		this.averageRxDrug = averageRxDrug;
	}
	public BigDecimal getAverageRxAmount() {
		return averageRxAmount;
	}
	public void setAverageRxAmount(BigDecimal averageRxAmount) {
		this.averageRxAmount = averageRxAmount;
	}
	public Double getPerDrugCount() {
		return perDrugCount;
	}
	public void setPerDrugCount(Double perDrugCount) {
		this.perDrugCount = perDrugCount;
	}
	public BigDecimal getPerDrugAmount() {
		return perDrugAmount;
	}
	public void setPerDrugAmount(BigDecimal perDrugAmount) {
		this.perDrugAmount = perDrugAmount;
	}
	public Double getAbxDrugUsage() {
		return abxDrugUsage;
	}
	public void setAbxDrugUsage(Double abxDrugUsage) {
		this.abxDrugUsage = abxDrugUsage;
	}
	public Double getIvDrugUsage() {
		return ivDrugUsage;
	}
	public void setIvDrugUsage(Double ivDrugUsage) {
		this.ivDrugUsage = ivDrugUsage;
	}
	public Double getOver2DrugUsage() {
		return over2DrugUsage;
	}
	public void setOver2DrugUsage(Double over2DrugUsage) {
		this.over2DrugUsage = over2DrugUsage;
	}
	public Integer getIvDrugRegCount() {
		return ivDrugRegCount;
	}
	public void setIvDrugRegCount(Integer ivDrugRegCount) {
		this.ivDrugRegCount = ivDrugRegCount;
	}
	public Integer getOver2DrugRegCount() {
		return Over2DrugRegCount;
	}
	public void setOver2DrugRegCount(Integer over2DrugRegCount) {
		Over2DrugRegCount = over2DrugRegCount;
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
		return "AbxUsedStatisticsBi [id=" + id + ", dimensionHospitalKey=" + dimensionHospitalKey
				+ ", dimensionPatientType=" + dimensionPatientType + ", deptKey=" + deptKey + ", dimensionDoctorKey="
				+ dimensionDoctorKey + ", dimesionDrugKey=" + dimesionDrugKey + ", dimensionCalanderKey="
				+ dimensionCalanderKey + ", rxCode=" + rxCode + ", regCode=" + regCode + ", haveAbxIn=" + haveAbxIn
				+ ", amount=" + amount + ", isAbx=" + isAbx + ", abxLevel=" + abxLevel + ", rxAbxVariety="
				+ rxAbxVariety + ", rxAbxAmount=" + rxAbxAmount + ", regAbxVariety=" + regAbxVariety + ", regAbxAmount="
				+ regAbxAmount + ", isIv=" + isIv + ", isOver2=" + isOver2 + ", hospitalCode=" + hospitalCode
				+ ", hospitalName=" + hospitalName + ", areaName=" + areaName + ", hospitalCount=" + hospitalCount
				+ ", deptCount=" + deptCount + ", doctorCount=" + doctorCount + ", drugCount=" + drugCount
				+ ", patientCount=" + patientCount + ", drugUsedCount=" + drugUsedCount + ", rxCount=" + rxCount
				+ ", rxPercentage=" + rxPercentage + ", amountPercentage=" + amountPercentage + ", totalPercentage="
				+ totalPercentage + ", averageRxDrug=" + averageRxDrug + ", averageRxAmount=" + averageRxAmount
				+ ", perDrugCount=" + perDrugCount + ", perDrugAmount=" + perDrugAmount + ", abxDrugUsage="
				+ abxDrugUsage + ", ivDrugUsage=" + ivDrugUsage + ", over2DrugUsage=" + over2DrugUsage
				+ ", ivDrugRegCount=" + ivDrugRegCount + ", Over2DrugRegCount=" + Over2DrugRegCount + ", createTime="
				+ createTime + ", lastUpdateTime=" + lastUpdateTime + ", statisticsTypeKey=" + statisticsTypeKey
				+ ", patientTypeKey=" + patientTypeKey + ", abxLevelKey=" + abxLevelKey + ", drugClassBtndrugid="
				+ drugClassBtndrugid + ", drugClassBtndrugcategoryid=" + drugClassBtndrugcategoryid + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
}
