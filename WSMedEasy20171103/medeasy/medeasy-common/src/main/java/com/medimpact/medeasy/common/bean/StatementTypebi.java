package com.medimpact.medeasy.common.bean;

import com.medimpact.medeasy.common.RequestParameter;

public class StatementTypebi extends RequestParameter{
	
	private int stmId;
	private String stmName;
	private int stmOpLevel; //需要的最低级别
	
	public StatementTypebi() {
		super();
	}
	public int getStmId() {
		return stmId;
	}
	public void setStmId(int stmId) {
		this.stmId = stmId;
	}
	public String getStmName() {
		return stmName;
	}
	public void setStmName(String stmName) {
		this.stmName = stmName;
	}
	public int getStmOpLevel() {
		return stmOpLevel;
	}
	public void setStmOpLevel(int stmOpLevel) {
		this.stmOpLevel = stmOpLevel;
	}	
	
}
