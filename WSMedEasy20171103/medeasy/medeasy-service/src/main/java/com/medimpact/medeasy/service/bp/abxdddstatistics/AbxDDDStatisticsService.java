package com.medimpact.medeasy.service.bp.abxdddstatistics;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxDDDStatisticsBi;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxDDDStatisticsService {
	DataModel<List<AbxDDDStatisticsBi>> getAbxDDDStatisticsDataAntiSt(AbxDDDStatisticsBi abxDDDStatisticsBi) throws Exception;
}
