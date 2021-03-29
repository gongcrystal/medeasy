package com.medimpact.medeasy.dao.bp.abxusedstatistics;

import com.medimpact.medeasy.common.bean.bp.AbxUsedStatisticsBi;
import com.medimpact.medeasy.dao.CommonDao;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxUsedStatisticsDao extends CommonDao<AbxUsedStatisticsBi> {
	
	public List<AbxUsedStatisticsBi> getAbxUsedStatistics(AbxUsedStatisticsBi abxUsedStatisticsBi);
	
	public List<AbxUsedStatisticsBi> getCommonStatistics(AbxUsedStatisticsBi abxUsedStatisticsBi);
	
	public AbxUsedStatisticsBi getTotalStatistics(AbxUsedStatisticsBi abxUsedStatisticsBi);

	public Long getAbxUsedCount(AbxUsedStatisticsBi abxUsedStatisticsBi);
	
}
