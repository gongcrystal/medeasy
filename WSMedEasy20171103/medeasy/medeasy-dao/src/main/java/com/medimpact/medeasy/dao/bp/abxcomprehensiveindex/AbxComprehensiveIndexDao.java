package com.medimpact.medeasy.dao.bp.abxcomprehensiveindex;

import com.medimpact.medeasy.common.bean.bp.AbxComprehensiveIndexBi;
import com.medimpact.medeasy.dao.CommonDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface AbxComprehensiveIndexDao extends CommonDao<AbxComprehensiveIndexBi> {
	public AbxComprehensiveIndexBi getAbxComprehensiveIndex(AbxComprehensiveIndexBi abxComprehensiveIndexBi);
}
