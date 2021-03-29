package com.medimpact.medeasy.common;

import java.util.List;

public class PageData<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6897855798976318936L;
	
	private String sEcho;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords=10l;
	private List<T> aaData;
	private Integer iDisplayStart=0;
	private Integer iDisplayLength=10;
	private String iSortCol_0;
	private String sSortDir_0;
	private List<T> extraData;
	
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	public Long getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public List<T> getExtraData() {
		return extraData;
	}
	public void setExtraData(List<T> extraData) {
		this.extraData = extraData;
	}
	public Integer getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public Integer getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(String iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0() {
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}
	
	 
	 

}
