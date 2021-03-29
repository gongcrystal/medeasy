package com.medimpact.medeasy.service.bp.rdustatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.RduStatisticsBi;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RduStatisticsService {
	DataModel<List<RduStatisticsBi>> getRduStatisticsData(RduStatisticsBi rduStatisticsBi) throws Exception;
}
