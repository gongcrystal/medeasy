package com.medimpact.medeasy.service.drugincome;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;

public interface DrugIncomeService {
	public   DataModel<List<DrugIncomeStBi>> stDifDrugCategoryTotalAmount(SearchForm form) ;

}
