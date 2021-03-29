package com.medimpact.medeasy.service.bp.rxevastatistics;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface RxEvaStatistics1Service {
	/*DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataAntiSt(SearchForm form) ;	*/
	DataModel<List<RxEvaStBi>> getRxEvaStatisticsData(SearchForm form) ;	
	DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataHpNew(SearchForm form) ;

}
