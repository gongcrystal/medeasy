package com.medimpact.medeasy.dao.bp.BaseDrugUsedStatistics;

import java.util.List;

import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedStatisticsBi;
import com.medimpact.medeasy.dao.CommonDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface BaseDrugUsedStatisticsDao extends CommonDao<BaseDrugUsedStatisticsBi> {
	
	public List<BaseDrugUsedStatisticsBi> getBaseDrugUsedStatistics(BaseDrugUsedStatisticsBi baseDrugUsedStatistics);
	public List<BaseDrugUsedStatisticsBi> getBaseDrugUsed1Statistics(BaseDrugUsedStatisticsBi baseDrugUsedStatistics);
	public List<BaseDrugUsedStatisticsBi> getBaseDrugUsedAmount(BaseDrugUsedStatisticsBi baseDrugUsedStatistics);//基本药物使用金额之和 
	public Long getBaseDrugUsedCount(BaseDrugUsedStatisticsBi form);  
	
}
