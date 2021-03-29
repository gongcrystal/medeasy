package com.medimpact.medeasy.dao.bp.rxevastatistics;

import java.util.List;

import com.medimpact.medeasy.common.bean.statistic.RxReasobleBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface RxEvaStatistics2RsDao {

	public List<RxReasobleBi> listBySys(SearchForm form);
	public List<RxReasobleBi> listByHospital(SearchForm form);
	public List<RxReasobleBi> listByArea(SearchForm form);



}
