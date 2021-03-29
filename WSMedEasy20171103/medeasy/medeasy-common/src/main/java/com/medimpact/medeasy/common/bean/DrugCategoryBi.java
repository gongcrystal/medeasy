package com.medimpact.medeasy.common.bean;

//drug_basic_category: drug_category_type-category_code
public class DrugCategoryBi {
	private long id;
	private String name;  //category_name
	private String code;  //category_code
	private String type;  //drug_category_type
	private String search; //drug_basic_category
	public DrugCategoryBi() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
