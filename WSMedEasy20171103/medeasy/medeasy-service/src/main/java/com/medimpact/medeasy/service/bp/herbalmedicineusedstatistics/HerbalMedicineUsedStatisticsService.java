package com.medimpact.medeasy.service.bp.herbalmedicineusedstatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.HerbalUseBi;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.form.SearchForm;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface HerbalMedicineUsedStatisticsService {
    DataModel<List<HerbalUseStBi>> getHerbalMedicineUsedStatisticsData(SearchForm form);
}
