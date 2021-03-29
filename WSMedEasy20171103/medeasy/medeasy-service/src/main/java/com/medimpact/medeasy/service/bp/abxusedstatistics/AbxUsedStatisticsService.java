package com.medimpact.medeasy.service.bp.abxusedstatistics;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxUsedStatisticsBi;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxUsedStatisticsService {
	DataModel<List<AbxUsedStatisticsBi>> getAbxUsedStatisticsData(AbxUsedStatisticsBi abxUsedStatisticsBi) throws Exception;
}
