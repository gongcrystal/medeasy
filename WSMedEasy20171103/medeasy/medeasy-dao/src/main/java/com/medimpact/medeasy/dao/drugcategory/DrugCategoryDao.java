package com.medimpact.medeasy.dao.drugcategory;

import java.util.List;

import com.medimpact.medeasy.common.bean.DrugCategoryBi;

public interface DrugCategoryDao {
	public List<DrugCategoryBi> getDrugCategoryByName(List<String> list);

}
