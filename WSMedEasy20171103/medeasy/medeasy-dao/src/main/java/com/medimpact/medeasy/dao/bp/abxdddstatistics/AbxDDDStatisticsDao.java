package com.medimpact.medeasy.dao.bp.abxdddstatistics;

import java.util.List;

import com.medimpact.medeasy.common.bean.bp.AbxDDDStatisticsBi;
import com.medimpact.medeasy.dao.CommonDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxDDDStatisticsDao extends CommonDao<AbxDDDStatisticsBi> {
	
	public List<AbxDDDStatisticsBi> getAbxDDDStatistics(AbxDDDStatisticsBi abxDDDStatisticsBi);
	
	public AbxDDDStatisticsBi getAbxTotalStatistics(AbxDDDStatisticsBi abxDDDStatisticsBi);

	public Long getAbxDDDCount(AbxDDDStatisticsBi abxDDDStatisticsBi);
	
}
