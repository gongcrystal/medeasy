package com.medimpact.medeasy.service.jiangsuhealth;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.JiangSuHealthStBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;

public interface JiangSuHealthService {
	public DataModel<List<JiangSuHealthStBi>> search(SearchForm form) ;	
}