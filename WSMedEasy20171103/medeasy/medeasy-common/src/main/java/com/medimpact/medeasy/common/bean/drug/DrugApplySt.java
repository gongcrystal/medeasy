package com.medimpact.medeasy.common.bean.drug;

import java.io.Serializable;

public class DrugApplySt implements Serializable{
	
	private static final long serialVersionUID = -282070297809070018L;
	private String label;
	private long data;
	private String color; 
	
	
	public DrugApplySt() {
		super();
	}
	
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public long getData() {
		return data;
	}


	public void setData(long data) {
		this.data = data;
	}


	
}
