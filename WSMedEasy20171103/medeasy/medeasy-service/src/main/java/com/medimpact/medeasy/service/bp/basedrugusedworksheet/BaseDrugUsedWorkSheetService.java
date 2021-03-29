package com.medimpact.medeasy.service.bp.basedrugusedworksheet;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.BaseDrugSheetBi;
import com.medimpact.medeasy.common.form.SearchForm;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface BaseDrugUsedWorkSheetService {
    DataModel<List<BaseDrugSheetBi>> getBaseDrugUsedWorkSheetData(SearchForm form) ;
}
