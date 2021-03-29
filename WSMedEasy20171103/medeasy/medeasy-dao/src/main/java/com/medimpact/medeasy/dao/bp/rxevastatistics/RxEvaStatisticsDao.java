package com.medimpact.medeasy.dao.bp.rxevastatistics;

import java.util.List;

import org.apache.ibatis.jdbc.Null;

import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.dao.CommonStDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RxEvaStatisticsDao extends CommonStDao<RxEvaStBi> {
   /* List<RxEvaStBi> getRxEvaStatistics(RxEvaStatisticsBi rxEvaStatisticsBi);*/
	public List<RxEvaStBi> rxOkOrNot(SearchForm form);
}
