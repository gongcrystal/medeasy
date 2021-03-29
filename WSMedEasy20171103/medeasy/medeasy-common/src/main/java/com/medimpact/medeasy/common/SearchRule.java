package com.medimpact.medeasy.common;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月7日 类说明
 */
import java.io.Serializable;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年9月19日 
* 类说明 
*/
public class SearchRule implements Serializable {
	
	private String field;
	private String op;
	private String data;
	public SearchRule() {
		super();
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
