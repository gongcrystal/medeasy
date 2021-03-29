package com.medimpact.medeasy.service.bp.rxevawroksheet;

import com.medimpact.medeasy.common.PageData;
import com.medimpact.medeasy.common.bean.bp.RxEvaWrokSheetBi;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RxEvaWrokSheetService {
	PageData<RxEvaWrokSheetBi> getRxEvaWrokSheetData(RxEvaWrokSheetBi rxEvaWrokSheetBi) throws Exception;
}
