package com.medimpact.medeasy.common.bean.statistic;

import java.math.BigDecimal;

public class HerbalUseStBi {
	
	private String name ; // hospital name
	private int decoction;
	private int nonDecoction;
	private BigDecimal decoctionAmount;
	private BigDecimal nonDecoctionAmount;
	

	public HerbalUseStBi() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
