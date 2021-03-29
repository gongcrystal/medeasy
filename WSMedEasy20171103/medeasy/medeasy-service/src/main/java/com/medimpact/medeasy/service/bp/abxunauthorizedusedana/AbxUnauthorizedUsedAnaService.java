package com.medimpact.medeasy.service.bp.abxunauthorizedusedana;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxUnauthorizedUsedAnaBi;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxUnauthorizedUsedAnaService {
	DataModel<List<AbxUnauthorizedUsedAnaBi>> getAbxUnauthorizedUsedAnaData(AbxUnauthorizedUsedAnaBi abxUnauthorizedUsedAnaBi) throws Exception;
}
