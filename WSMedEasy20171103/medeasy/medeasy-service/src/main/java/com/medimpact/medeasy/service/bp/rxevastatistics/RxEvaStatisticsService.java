package com.medimpact.medeasy.service.bp.rxevastatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.RxEvaStatisticsBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.form.SearchForm;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RxEvaStatisticsService {
	/*DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataAntiSt(SearchForm form) ;	*/
	DataModel<List<RxEvaStBi>> getRxEvaStatisticsData(SearchForm form) ;
	DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataHpNew(SearchForm form) ;
}
