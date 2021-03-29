package com.medimpact.medeasy.common.bean;

public class TableRoleBi implements Comparable<TableRoleBi>{
	private Integer id;
	private String roleName;	
	private int operateLevel;
	
	public TableRoleBi() {
		super();
	}	
	
	public TableRoleBi(String roleName) {
		super();
		this.roleName = roleName;		
	}

	public TableRoleBi(String roleName, int operateLevel) {
		super();
		this.roleName = roleName;
		this.operateLevel = operateLevel;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getOperateLevel() {
		return operateLevel;
	}

	public void setOperateLevel(int operateLevel) {
		this.operateLevel = operateLevel;
	}

	@Override
	public int compareTo(TableRoleBi o) {
		// TODO Auto-generated method stub
		return this.roleName.compareTo(o.getRoleName());
	}
	
}
