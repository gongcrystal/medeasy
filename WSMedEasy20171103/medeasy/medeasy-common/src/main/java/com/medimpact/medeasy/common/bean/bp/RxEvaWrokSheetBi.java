package com.medimpact.medeasy.common.bean.bp;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/2.
 */
public class RxEvaWrokSheetBi extends RequestParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933375316253737722L;
	
	private Long id;
	private String rxCode;//处方号
	private String rxDate;//处方日期
	private String age;//年龄
	private String diags;//诊断
	private Integer drugVariety;//药品品种
	private String isAbx;//是否含抗菌药
	private String isIv;//是否含注射剂
	private Integer basicDrugVariety;//国家基药品种
	private Integer genNameCount;//通用名额
	private BigDecimal amount;//处方金额
	private String doctorName;//处方医师
	private String doctorCheck;//审核调配药师
	private String doctorDispense;//核对发药药师
	private String isOk;//是否合理
	private String irrerItemCode;//问题代码
	private String evaPerson;//点评人

	private BigDecimal avgAmount;//平均药品金额
	private BigDecimal avgDrugVariety;//药品品种
	private BigDecimal avgBasicDrugVariety;//国家基药品种
	private BigDecimal avgGenNameCount;//通用名额
	private BigDecimal ivPercentage;//注射剂使用百分率
	private BigDecimal abxPercentage;//抗菌药使用百分率
	private BigDecimal okPercentage;//合理处方百分率
	
	private String startDate;
	private String endDate;
	private String areaKey;//地区名称
	private String hospitalKey;//医院名称
	private String patientTypeKey;//处方类型
	private String irrCode;//
	private String drugClassBtndrugid;//药品id（,分割）
	private String drugClassBtndrugcategoryid;//药品分类id（,分割）
	private String basicDrugCategory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRxCode() {
		return rxCode;
	}
	public void setRxCode(String rxCode) {
		this.rxCode = rxCode;
	}
	public String getRxDate() {
		return rxDate;
	}
	public void setRxDate(String rxDate) {
		this.rxDate = rxDate;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDiags() {
		return diags;
	}
	public void setDiags(String diags) {
		this.diags = diags;
	}
	public Integer getDrugVariety() {
		return drugVariety;
	}
	public void setDrugVariety(Integer drugVariety) {
		this.drugVariety = drugVariety;
	}
	public String getIsAbx() {
		return isAbx;
	}
	public void setIsAbx(String isAbx) {
		this.isAbx = isAbx;
	}
	public String getIsIv() {
		return isIv;
	}
	public void setIsIv(String isIv) {
		this.isIv = isIv;
	}
	public Integer getBasicDrugVariety() {
		return basicDrugVariety;
	}
	public void setBasicDrugVariety(Integer basicDrugVariety) {
		this.basicDrugVariety = basicDrugVariety;
	}
	public Integer getGenNameCount() {
		return genNameCount;
	}
	public void setGenNameCount(Integer genNameCount) {
		this.genNameCount = genNameCount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorCheck() {
		return doctorCheck;
	}
	public void setDoctorCheck(String doctorCheck) {
		this.doctorCheck = doctorCheck;
	}
	public String getDoctorDispense() {
		return doctorDispense;
	}
	public void setDoctorDispense(String doctorDispense) {
		this.doctorDispense = doctorDispense;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public String getIrrerItemCode() {
		return irrerItemCode;
	}
	public void setIrrerItemCode(String irrerItemCode) {
		this.irrerItemCode = irrerItemCode;
	}
	public String getEvaPerson() {
		return evaPerson;
	}
	public void setEvaPerson(String evaPerson) {
		this.evaPerson = evaPerson;
	}
	public BigDecimal getAvgDrugVariety() {
		return avgDrugVariety;
	}
	public void setAvgDrugVariety(BigDecimal avgDrugVariety) {
		this.avgDrugVariety = avgDrugVariety;
	}
	public BigDecimal getAvgBasicDrugVariety() {
		return avgBasicDrugVariety;
	}
	public void setAvgBasicDrugVariety(BigDecimal avgBasicDrugVariety) {
		this.avgBasicDrugVariety = avgBasicDrugVariety;
	}
	public BigDecimal getAvgGenNameCount() {
		return avgGenNameCount;
	}
	public void setAvgGenNameCount(BigDecimal avgGenNameCount) {
		this.avgGenNameCount = avgGenNameCount;
	}
	public BigDecimal getIvPercentage() {
		return ivPercentage;
	}
	public void setIvPercentage(BigDecimal ivPercentage) {
		this.ivPercentage = ivPercentage;
	}
	public BigDecimal getAbxPercentage() {
		return abxPercentage;
	}
	public void setAbxPercentage(BigDecimal abxPercentage) {
		this.abxPercentage = abxPercentage;
	}
	public BigDecimal getOkPercentage() {
		return okPercentage;
	}
	public void setOkPercentage(BigDecimal okPercentage) {
		this.okPercentage = okPercentage;
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
	public String getPatientTypeKey() {
		return patientTypeKey;
	}
	public void setPatientTypeKey(String patientTypeKey) {
		this.patientTypeKey = patientTypeKey;
	}
	public String getIrrCode() {
		return irrCode;
	}
	public void setIrrCode(String irrCode) {
		this.irrCode = irrCode;
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
	@Override
	public String toString() {
		return "RxEvaWrokSheetBi [id=" + id + ", rxCode=" + rxCode + ", rxDate=" + rxDate + ", age=" + age + ", diags="
				+ diags + ", drugVariety=" + drugVariety + ", isAbx=" + isAbx + ", isIv=" + isIv + ", basicDrugVariety="
				+ basicDrugVariety + ", genNameCount=" + genNameCount + ", amount=" + amount + ", doctorName="
				+ doctorName + ", doctorCheck=" + doctorCheck + ", doctorDispense=" + doctorDispense + ", isOk=" + isOk
				+ ", irrerItemCode=" + irrerItemCode + ", evaPerson=" + evaPerson + ", avgDrugVariety=" + avgDrugVariety
				+ ", avgBasicDrugVariety=" + avgBasicDrugVariety + ", avgGenNameCount=" + avgGenNameCount
				+ ", ivPercentage=" + ivPercentage + ", abxPercentage=" + abxPercentage + ", okPercentage="
				+ okPercentage + ", startDate=" + startDate + ", endDate=" + endDate + ", areaKey=" + areaKey
				+ ", hospitalKey=" + hospitalKey + ", patientTypeKey=" + patientTypeKey + ", irrCode=" + irrCode
				+ ", drugClassBtndrugid=" + drugClassBtndrugid + ", drugClassBtndrugcategoryid="
				+ drugClassBtndrugcategoryid + ", basicDrugCategory=" + basicDrugCategory + "]";
	}

	public BigDecimal getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(BigDecimal avgAmount) {
		this.avgAmount = avgAmount;
	}
}
