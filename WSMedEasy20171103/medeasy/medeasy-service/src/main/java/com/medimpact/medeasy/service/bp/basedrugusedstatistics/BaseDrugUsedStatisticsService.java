package com.medimpact.medeasy.service.bp.basedrugusedstatistics;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedStatisticsBi;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface BaseDrugUsedStatisticsService {
    DataModel<List<BaseDrugUsedStatisticsBi>> getBaseDrugUsedStatisticsData(BaseDrugUsedStatisticsBi baseDrugUsedStatistics) throws Exception;
}
