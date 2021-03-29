package com.medimpact.medeasy.dao.bp.rxevawroksheet;

import com.medimpact.medeasy.common.bean.bp.RxEvaWrokSheetBi;
import com.medimpact.medeasy.dao.CommonDao;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RxEvaWrokSheetDao extends CommonDao<RxEvaWrokSheetBi> {
    public List<RxEvaWrokSheetBi> getRxEvaWrokSheet(RxEvaWrokSheetBi rxEvaWrokSheetBi);
    public List<RxEvaWrokSheetBi> getRxEvaWrokSheetStatistic(RxEvaWrokSheetBi rxEvaWrokSheetBi);
}
