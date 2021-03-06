package com.medimpact.medeasy.common.bean;

import java.io.Serializable;
import java.util.List;

public class ModuleBi  implements Serializable{
	private Integer id;
	private String name;
	private String code;
	private Integer type;
	private Integer parentId;
	private String url;
	private Boolean enable;
	private Integer order;
	private Boolean hasChild;
	private Integer maxOrderChild;
	private List<ModuleBi> children;
	
	public ModuleBi() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}
	public Integer getMaxOrderChild() {
		return maxOrderChild;
	}
	public void setMaxOrderChild(Integer maxOrderChild) {
		this.maxOrderChild = maxOrderChild;
	}
	public List<ModuleBi> getChildren() {
		return children;
	}
	public void setChildren(List<ModuleBi> children) {
		this.children = children;
	}
	
}
