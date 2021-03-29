package com.medimpact.medeasy.common;
/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年9月19日 
* 类说明 
* ex:
* filters:{
* "groupOp":"OR","rules":[{"field":"rankId","op":"eq","data":"2"}],
* "groups":
* [{"groupOp":"AND","rules":[{"field":"rankId","op":"eq","data":"1"},{"field":"rankName","op":"ne","data":"Shu"}],
* "groups":[]},{"groupOp":"AND","rules":[{"field":"rankName","op":"bw","data":"S"}],"groups":[]}]}
searchField:
searchString:
searchOper:	
*/

import java.io.Serializable;
import java.util.List;

public class SearchFilter implements Serializable {
	
	private String groupOp;
	private List<SearchRule> rules;
	/*private List<SearchFilter> groups;*/ //用来做multiple group的嵌套查询用	
	
	public SearchFilter() {
		super();
	}

	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<SearchRule> getRules() {
		return rules;
	}
	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "SearchFilter [groupOp=" + groupOp + ", rules=" + rules;
	}
}
