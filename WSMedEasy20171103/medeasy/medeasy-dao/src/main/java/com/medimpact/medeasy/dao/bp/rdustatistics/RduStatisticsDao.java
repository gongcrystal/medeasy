package com.medimpact.medeasy.dao.bp.rdustatistics;

import com.medimpact.medeasy.common.bean.bp.RduStatisticsBi;
import com.medimpact.medeasy.dao.CommonDao;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface RduStatisticsDao extends CommonDao<RduStatisticsBi> {
	
	public List<RduStatisticsBi> getRduStatisticsByHospital(RduStatisticsBi rduStatisticsBi);
	
	public Long getCountByHospital(RduStatisticsBi rduStatisticsBi);
	
	public List<RduStatisticsBi> getRduStatisticsByAlert(RduStatisticsBi rduStatisticsBi);

	public Long getCountByAlert(RduStatisticsBi rduStatisticsBi);

	public List<RduStatisticsBi> getAmountByHospital(RduStatisticsBi rduStatisticsBi);
	
}
