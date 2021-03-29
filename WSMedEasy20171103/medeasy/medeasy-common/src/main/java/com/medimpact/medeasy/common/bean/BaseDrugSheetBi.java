package com.medimpact.medeasy.common.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

public class BaseDrugSheetBi extends RequestParameter{
	private Long id;
	private Long hospitalId;

	//c : country; p: province
	
	private int cVariety;	//基药采购品种国家目录
	private int pVariety;
	private BigDecimal cPrice;
	private BigDecimal pPrice;
	
	private int cDVariety;//基药配送国家目录验收品种数
	private int pDVariety;	
	private BigDecimal cDPrice;
	private BigDecimal pDPrice;
	
	
	private int uCVariety;//基药配备国家目录在用品种数
	private int uPVariety;	
	private BigDecimal uCPrice;//基药配备国家目录销售金额
	private BigDecimal uPPrice;
	
	private BigDecimal usedIv;
	private BigDecimal usedAbx;
	
	private BigDecimal usedCDecline;//国家指导售价相比平均降幅%
	private BigDecimal usedPDecline;
	
	private Date createTime;
	private Date updateTime;
	
	private int year;
	private int month;
	private String fullDay;

	public BaseDrugSheetBi() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getcVariety() {
		return cVariety;
	}

	public void setcVariety(int cVariety) {
		this.cVariety = cVariety;
	}

	public int getpVariety() {
		return pVariety;
	}

	public void setpVariety(int pVariety) {
		this.pVariety = pVariety;
	}

	public BigDecimal getcPrice() {
		return cPrice;
	}

	public void setcPrice(BigDecimal cPrice) {
		this.cPrice = cPrice;
	}

	public BigDecimal getpPrice() {
		return pPrice;
	}

	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}

	public int getcDVariety() {
		return cDVariety;
	}

	public void setcDVariety(int cDVariety) {
		this.cDVariety = cDVariety;
	}

	public int getpDVariety() {
		return pDVariety;
	}

	public void setpDVariety(int pDVariety) {
		this.pDVariety = pDVariety;
	}

	public BigDecimal getcDPrice() {
		return cDPrice;
	}

	public void setcDPrice(BigDecimal cDPrice) {
		this.cDPrice = cDPrice;
	}

	public BigDecimal getpDPrice() {
		return pDPrice;
	}

	public void setpDPrice(BigDecimal pDPrice) {
		this.pDPrice = pDPrice;
	}

	public int getuCVariety() {
		return uCVariety;
	}

	public void setuCVariety(int uCVariety) {
		this.uCVariety = uCVariety;
	}

	public int getuPVariety() {
		return uPVariety;
	}

	public void setuPVariety(int uPVariety) {
		this.uPVariety = uPVariety;
	}

	public BigDecimal getuCPrice() {
		return uCPrice;
	}

	public void setuCPrice(BigDecimal uCPrice) {
		this.uCPrice = uCPrice;
	}

	public BigDecimal getuPPrice() {
		return uPPrice;
	}

	public void setuPPrice(BigDecimal uPPrice) {
		this.uPPrice = uPPrice;
	}

	public BigDecimal getUsedIv() {
		return usedIv;
	}

	public void setUsedIv(BigDecimal usedIv) {
		this.usedIv = usedIv;
	}

	public BigDecimal getUsedAbx() {
		return usedAbx;
	}

	public void setUsedAbx(BigDecimal usedAbx) {
		this.usedAbx = usedAbx;
	}

	public BigDecimal getUsedCDecline() {
		return usedCDecline;
	}

	public void setUsedCDecline(BigDecimal usedCDecline) {
		this.usedCDecline = usedCDecline;
	}

	public BigDecimal getUsedPDecline() {
		return usedPDecline;
	}

	public void setUsedPDecline(BigDecimal usedPDecline) {
		this.usedPDecline = usedPDecline;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getFullDay() {
		return fullDay;
	}

	public void setFullDay(String fullDay) {
		this.fullDay = fullDay;
	}
}
