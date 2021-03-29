package com.medimpact.medeasy.common;

import java.math.BigDecimal;

public class StatisticParameter {
	private Long rankId=new Long(0); //用于统计中的排名	
	
	private BigDecimal areaAmount=new BigDecimal(0);//区域“药品使用金额
	
	private String areaName;
	
	private BigDecimal mamount=new BigDecimal(0); //药品使用金额（元）
	
	//金额总占比%  “药品使用金额”/同期区域“药品使用金额”×100%
	private Double amountPerAreaTotal=new Double(0);
	
	//药占比%  “药品使用金额”/“总费用（元）” ×100%
	private Double amountPerTotal=new Double(0);	
	
	private Long regAmount=new Long(0);//“就诊人次”
	
	private Long rxAmount = new Long(0); //处方数
	
	//人均药品使用金额 :  就诊用药“药品使用金额”/同期使用该药“就诊人次”
	private Double amountAvgPt=new Double(0);
	
	private Long drugSpecAmount=new Long(0); //药品品种数, 对应数据库里的drug_variety_account
	
	//人均药品品种数:就诊用药“处方品种数”合计/同期使用该药“就诊人次”
	private Double drugSpecAvgPt=new Double(0);
	
	private Double amountAvgRx=new Double(0); //平均处方金额（元）
	
	private Integer bigRx = 0; //大处方
	private Double perbigRx=new Double(0);  //大处方百分率%	
	private Double drugDayAvgPt=new Double(0); //人均用药天数（天）
	private Integer drugDayPerHp = 0; //用药天数（天）
	
	//药品周转率%:期末药品库存金额/本期药品销售金额×100%
	private BigDecimal drugStock=new BigDecimal(0);  //药品库存金额  药品的销售金额？
	private Double turnoverRate= new Double(0);  //药品周转率%
	
	private Double perOkAmount= new Double(0); //合理处方占比%
	private Double perNonOkAmount= new Double(0); //不合理处方占比%
	private Double perAbxAmount= new Double(0);//抗菌药处方占比%
	private Double perNonOkAbxAmount= new Double(0); //不合理抗菌药处方占比%
	
	
	private Long okAmount = new Long(0); //合理处方数
	private Long nonOkamount = new Long(0);  //不合理处方数
	private Long abxCount = new Long(0);//抗菌药物处方数;
	private Long nonOkAbxAmount = new Long(0);//抗菌药物处方不合理数: 同时满足时抗菌药，并且是不合理药

	
	public Long getRankId() {
		return rankId;
	}
	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}
	
	
	public BigDecimal getMamount() {
		return mamount;
	}
	public void setMamount(BigDecimal mamount) {
		this.mamount = mamount;
	}
	public Double getAmountPerAreaTotal() {
		return amountPerAreaTotal;
	}
	public void setAmountPerAreaTotal(Double amountPerAreaTotal) {
		this.amountPerAreaTotal = amountPerAreaTotal;
	}
	public Double getAmountPerTotal() {
		return amountPerTotal;
	}
	public void setAmountPerTotal(Double amountPerTotal) {
		this.amountPerTotal = amountPerTotal;
	}
	public Long getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(Long regAmount) {
		this.regAmount = regAmount;
	}
	public Double getAmountAvgPt() {
		return amountAvgPt;
	}
	public void setAmountAvgPt(Double amountAvgPt) {
		this.amountAvgPt = amountAvgPt;
	}
	public Long getDrugSpecAmount() {
		return drugSpecAmount;
	}
	public void setDrugSpecAmount(Long drugSpecAmount) {
		this.drugSpecAmount = drugSpecAmount;
	}
	public Double getDrugSpecAvgPt() {
		return drugSpecAvgPt;
	}
	public void setDrugSpecAvgPt(Double drugSpecAvgPt) {
		this.drugSpecAvgPt = drugSpecAvgPt;
	}
	public Integer getBigRx() {
		return bigRx;
	}
	public void setBigRx(Integer bigRx) {
		this.bigRx = bigRx;
	}
	public Double getPerbigRx() {
		return perbigRx;
	}
	public void setPerbigRx(Double perbigRx) {
		this.perbigRx = perbigRx;
	}
	public Double getAmountAvgRx() {
		return amountAvgRx;
	}
	public void setAmountAvgRx(Double amountAvgRx) {
		this.amountAvgRx = amountAvgRx;
	}
	public Double getDrugDayAvgPt() {
		return drugDayAvgPt;
	}
	public void setDrugDayAvgPt(Double drugDayAvgPt) {
		this.drugDayAvgPt = drugDayAvgPt;
	}
	public Integer getDrugDayPerHp() {
		return drugDayPerHp;
	}
	public void setDrugDayPerHp(Integer drugDayPerHp) {
		this.drugDayPerHp = drugDayPerHp;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getRxAmount() {
		return rxAmount;
	}
	public void setRxAmount(Long rxAmount) {
		this.rxAmount = rxAmount;
	}
	
	public BigDecimal getDrugStock() {
		return drugStock;
	}
	public void setDrugStock(BigDecimal drugStock) {
		this.drugStock = drugStock;
	}
	public Double getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(Double turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	public BigDecimal getAreaAmount() {
		return areaAmount;
	}
	public void setAreaAmount(BigDecimal areaAmount) {
		this.areaAmount = areaAmount;
	}
	public Double getPerOkAmount() {
		return perOkAmount;
	}
	public void setPerOkAmount(Double perOkAmount) {
		this.perOkAmount = perOkAmount;
	}
	public Double getPerNonOkAmount() {
		return perNonOkAmount;
	}
	public void setPerNonOkAmount(Double perNonOkAmount) {
		this.perNonOkAmount = perNonOkAmount;
	}
	public Double getPerAbxAmount() {
		return perAbxAmount;
	}
	public void setPerAbxAmount(Double perAbxAmount) {
		this.perAbxAmount = perAbxAmount;
	}
	public Double getPerNonOkAbxAmount() {
		return perNonOkAbxAmount;
	}
	public void setPerNonOkAbxAmount(Double perNonOkAbxAmount) {
		this.perNonOkAbxAmount = perNonOkAbxAmount;
	}
	
	public Long getOkAmount() {
		return okAmount;
	}
	public void setOkAmount(Long okAmount) {
		this.okAmount = okAmount;
	}
	
	public Long getNonOkamount() {
		return nonOkamount;
	}
	public void setNonOkamount(Long nonOkamount) {
		this.nonOkamount = nonOkamount;
	}	
	
	public Long getAbxCount() {
		return abxCount;
	}
	public void setAbxCount(Long abxCount) {
		this.abxCount = abxCount;
	}
	public Long getNonOkAbxAmount() {
		return nonOkAbxAmount;
	}
	public void setNonOkAbxAmount(Long nonOkAbxAmount) {
		this.nonOkAbxAmount = nonOkAbxAmount;
	}
	
}
