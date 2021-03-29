package com.medimpact.medeasy.common.bean.statistic;


import com.medimpact.medeasy.common.RequestParameter;

public class DrugIncomeStBi extends RequestParameter{
	
	private String drugItemName;
	/*private BigDecimal amount=new BigDecimal(0); */

	public DrugIncomeStBi() {
		super();
	}

	public String getDrugItemName() {
		return drugItemName;
	}

	public void setDrugItemName(String drugItemName) {
		this.drugItemName = drugItemName;
	}

	/*public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	} */
}
