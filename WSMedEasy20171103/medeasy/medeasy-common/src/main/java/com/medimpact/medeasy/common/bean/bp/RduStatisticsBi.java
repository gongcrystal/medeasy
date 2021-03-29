package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class RduStatisticsBi extends RequestParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1352016403133454160L;
	private Long id;
	private Long dimensionEventCodeKey;
	private Long dimensionHospitalKey;
	private Long dimensionPatientType;
	private Long dimensionDoctorKey;
	private Long deptKey;
	private Long dimesionDrugKey;
	private Long dimensionPatientKey;
	private Long dimensionCalanderKey;
	private Integer durLevel;//报警级别
	private String rxCode;//处方编码
	private BigDecimal amount;//药品金额
	private Date createTime;
	private Date lastUpdateTime;
	
	private String hospitalCode;//医院编码
	private String hospitalName;//医院名称
	private String areaName;//地区名称
	private String alertTypeName;//报警类型名称
	private String doctorName;//医生姓名
	private String drugName;//药品名称
	
	private Integer rxCount;//处方数
	private Integer alertLevel1Count;//1级告警数量
	private Integer alertLevel2Count;//2级告警数量
	private Integer alertLevel3Count;//3级告警数量
	private Integer alertLevel4Count;//4级告警数量
	private Integer alertTotalCount;//报警总数
	private Double alertPercentage;//报警比例
	private Integer alertTypeCount;//报警类型数量
	private Integer drugCount;//药品数量
	private Integer deptCount;//科室数量
	private Integer doctorCount;//医师数量
	private Integer hospitalCount;//医院数量
	private Integer areaCount;//区数量
	
	private String rsFormTypeKey;//报表类型
	private String patientTypeKey;//处方类型
	private String dataTypeKey;//数据类型
	private String alertTypeKey;//报警类型
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
	public Long getDimensionEventCodeKey() {
		return dimensionEventCodeKey;
	}
	public void setDimensionEventCodeKey(Long dimensionEventCodeKey) {
		this.dimensionEventCodeKey = dimensionEventCodeKey;
	}
	public Long getDimensionHospitalKey() {
		return dimensionHospitalKey;
	}
	public void setDimensionHospitalKey(Long dimensionHospitalKey) {
		this.dimensionHospitalKey = dimensionHospitalKey;
	}
	public Long getDimensionPatientType() {
		return dimensionPatientType;
	}
	public void setDimensionPatientType(Long dimensionPatientType) {
		this.dimensionPatientType = dimensionPatientType;
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
	public Long getDimensionPatientKey() {
		return dimensionPatientKey;
	}
	public void setDimensionPatientKey(Long dimensionPatientKey) {
		this.dimensionPatientKey = dimensionPatientKey;
	}
	public Long getDimensionCalanderKey() {
		return dimensionCalanderKey;
	}
	public void setDimensionCalanderKey(Long dimensionCalanderKey) {
		this.dimensionCalanderKey = dimensionCalanderKey;
	}
	public Integer getDurLevel() {
		return durLevel;
	}
	public void setDurLevel(Integer durLevel) {
		this.durLevel = durLevel;
	}
	public String getRxCode() {
		return rxCode;
	}
	public void setRxCode(String rxCode) {
		this.rxCode = rxCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	public String getAlertTypeName() {
		return alertTypeName;
	}
	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Integer getRxCount() {
		return rxCount;
	}
	public void setRxCount(Integer rxCount) {
		this.rxCount = rxCount;
	}
	public Integer getAlertLevel1Count() {
		return alertLevel1Count;
	}
	public void setAlertLevel1Count(Integer alertLevel1Count) {
		this.alertLevel1Count = alertLevel1Count;
	}
	public Integer getAlertLevel2Count() {
		return alertLevel2Count;
	}
	public void setAlertLevel2Count(Integer alertLevel2Count) {
		this.alertLevel2Count = alertLevel2Count;
	}
	public Integer getAlertLevel3Count() {
		return alertLevel3Count;
	}
	public void setAlertLevel3Count(Integer alertLevel3Count) {
		this.alertLevel3Count = alertLevel3Count;
	}
	public Integer getAlertLevel4Count() {
		return alertLevel4Count;
	}
	public void setAlertLevel4Count(Integer alertLevel4Count) {
		this.alertLevel4Count = alertLevel4Count;
	}
	public Integer getAlertTotalCount() {
		return alertTotalCount;
	}
	public void setAlertTotalCount(Integer alertTotalCount) {
		this.alertTotalCount = alertTotalCount;
	}
	public Double getAlertPercentage() {
		return alertPercentage;
	}
	public void setAlertPercentage(Double alertPercentage) {
		this.alertPercentage = alertPercentage;
	}
	public Integer getAlertTypeCount() {
		return alertTypeCount;
	}
	public void setAlertTypeCount(Integer alertTypeCount) {
		this.alertTypeCount = alertTypeCount;
	}
	public Integer getDrugCount() {
		return drugCount;
	}
	public void setDrugCount(Integer drugCount) {
		this.drugCount = drugCount;
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
	public Integer getHospitalCount() {
		return hospitalCount;
	}
	public void setHospitalCount(Integer hospitalCount) {
		this.hospitalCount = hospitalCount;
	}
	public Integer getAreaCount() {
		return areaCount;
	}
	public void setAreaCount(Integer areaCount) {
		this.areaCount = areaCount;
	}
	public String getRsFormTypeKey() {
		return rsFormTypeKey;
	}
	public void setRsFormTypeKey(String rsFormTypeKey) {
		this.rsFormTypeKey = rsFormTypeKey;
	}
	public String getPatientTypeKey() {
		return patientTypeKey;
	}
	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
	}
	public String getDataTypeKey() {
		return dataTypeKey;
	}
	public void setDataTypeKey(String dataTypeKey) {
		this.dataTypeKey = dataTypeKey;
	}
	public String getAlertTypeKey() {
		return alertTypeKey;
	}
	public void setAlertTypeKey(String alertTypeKey) {
		this.alertTypeKey = alertTypeKey;
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
		return "RduStatisticsBi [id=" + id + ", dimensionEventCodeKey=" + dimensionEventCodeKey
				+ ", dimensionHospitalKey=" + dimensionHospitalKey + ", dimensionPatientType=" + dimensionPatientType
				+ ", dimensionDoctorKey=" + dimensionDoctorKey + ", deptKey=" + deptKey + ", dimesionDrugKey="
				+ dimesionDrugKey + ", dimensionPatientKey=" + dimensionPatientKey + ", dimensionCalanderKey="
				+ dimensionCalanderKey + ", durLevel=" + durLevel + ", rxCode=" + rxCode + ", amount=" + amount
				+ ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + ", hospitalCode=" + hospitalCode
				+ ", hospitalName=" + hospitalName + ", areaName=" + areaName + ", alertTypeName=" + alertTypeName
				+ ", doctorName=" + doctorName + ", drugName=" + drugName + ", rxCount=" + rxCount
				+ ", alertLevel1Count=" + alertLevel1Count + ", alertLevel2Count=" + alertLevel2Count
				+ ", alertLevel3Count=" + alertLevel3Count + ", alertLevel4Count=" + alertLevel4Count
				+ ", alertTotalCount=" + alertTotalCount + ", alertPercentage=" + alertPercentage + ", alertTypeCount="
				+ alertTypeCount + ", drugCount=" + drugCount + ", deptCount=" + deptCount + ", doctorCount="
				+ doctorCount + ", hospitalCount=" + hospitalCount + ", areaCount=" + areaCount + ", rsFormTypeKey="
				+ rsFormTypeKey + ", patientTypeKey=" + patientTypeKey + ", dataTypeKey=" + dataTypeKey
				+ ", alertTypeKey=" + alertTypeKey + ", drugClassBtndrugid=" + drugClassBtndrugid
				+ ", drugClassBtndrugcategoryid=" + drugClassBtndrugcategoryid + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
}
