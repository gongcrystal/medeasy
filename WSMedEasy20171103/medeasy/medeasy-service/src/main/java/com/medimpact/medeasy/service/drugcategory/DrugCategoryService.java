package com.medimpact.medeasy.service.drugcategory;

import java.util.List;

import com.medimpact.medeasy.common.bean.DrugCategoryBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;

public interface DrugCategoryService {
	public List<DrugCategoryBi> getDrugCategoryByName(List<String> namesm) throws BizException;

}
