package com.medimpact.medeasy.dao.bp.abxunauthorizedusedana;

import com.medimpact.medeasy.common.bean.bp.AbxUnauthorizedUsedAnaBi;
import com.medimpact.medeasy.dao.CommonDao;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxUnauthorizedUsedAnaDao extends CommonDao<AbxUnauthorizedUsedAnaBi> {
    public List<AbxUnauthorizedUsedAnaBi> getAbxUnauthorizedUsedAna(AbxUnauthorizedUsedAnaBi abxUnauthorizedUsedAnaBi);

	public Long getAbxUnauthorizedUsedCount(AbxUnauthorizedUsedAnaBi abxUnauthorizedUsedAnaBi);
}
