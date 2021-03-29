package com.medimpact.medeasy.service.bp.abxdddstatistics;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxDDDStatisticsBi;
import com.medimpact.medeasy.dao.bp.abxdddstatistics.AbxDDDStatisticsDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class AbxDDDStatisticsServiceImpl implements AbxDDDStatisticsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxDDDStatisticsDao abxDDDStatisticsDao;
    @Override
    public DataModel<List<AbxDDDStatisticsBi>> getAbxDDDStatisticsDataAntiSt(AbxDDDStatisticsBi abxDDDStatisticsBi) throws Exception {
    	List<AbxDDDStatisticsBi> li = abxDDDStatisticsDao.getAbxDDDStatistics(abxDDDStatisticsBi);
    	AbxDDDStatisticsBi totalStatistics = abxDDDStatisticsDao.getAbxTotalStatistics(abxDDDStatisticsBi);
    	for(AbxDDDStatisticsBi abx : li){
    		BigDecimal amountPercentage = new BigDecimal(100*abx.getAmount().doubleValue()/totalStatistics.getAmount().doubleValue());
    		BigDecimal rxCount = new BigDecimal(100.00*abx.getRxCount()/totalStatistics.getRxCount());
    		abx.setAmountPercentage(amountPercentage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setRxPercentage(rxCount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    	}
		Long records =  abxDDDStatisticsDao.getAbxDDDCount(abxDDDStatisticsBi);
		Long total  =  records/abxDDDStatisticsBi.getRows() +1;
        return DataModel.getSucDataModal(li, abxDDDStatisticsBi.getPage(), records, total);
    }
    
}
