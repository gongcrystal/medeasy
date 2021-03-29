package com.medimpact.medeasy.common.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.medimpact.medeasy.common.RequestParameter;

public class HerbalUseBi extends RequestParameter{
	
	private Long id;	
	private Long hospitalId;
	private int decoction;
	private int nonDecoction;
	private BigDecimal decoctionAmount;
	private BigDecimal nonDecoctionAmount;
	private Date createTime;
	private Date updateTime;
	private int year;
	private int month;
	
	private Date startDate;
	private Date endDate;
	
	public HerbalUseBi() {
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
	public int getDecoction() {
		return decoction;
	}
	public void setDecoction(int decoction) {
		this.decoction = decoction;
	}
	public int getNonDecoction() {
		return nonDecoction;
	}
	public void setNonDecoction(int nonDecoction) {
		this.nonDecoction = nonDecoction;
	}
	public BigDecimal getDecoctionAmount() {
		return decoctionAmount;
	}
	public void setDecoctionAmount(BigDecimal decoctionAmount) {
		this.decoctionAmount = decoctionAmount;
	}
	public BigDecimal getNonDecoctionAmount() {
		return nonDecoctionAmount;
	}
	public void setNonDecoctionAmount(BigDecimal nonDecoctionAmount) {
		this.nonDecoctionAmount = nonDecoctionAmount;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
