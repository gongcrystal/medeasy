package com.medimpact.medeasy.service.bp.abxusedstatistics;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxUsedStatisticsBi;
import com.medimpact.medeasy.dao.bp.abxusedstatistics.AbxUsedStatisticsDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class AbxUsedStatisticsServiceImpl implements AbxUsedStatisticsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxUsedStatisticsDao abxUsedStatisticsDao;
    @Override
    public DataModel<List<AbxUsedStatisticsBi>> getAbxUsedStatisticsData(AbxUsedStatisticsBi abxUsedStatisticsBi) throws Exception {
    	if(null !=abxUsedStatisticsBi.getDrugClassBtndrugid() && !"".equals(abxUsedStatisticsBi.getDrugClassBtndrugid())){
    		abxUsedStatisticsBi.setDrugClassBtndrugcategoryid(null);
    	}
    	List<AbxUsedStatisticsBi> li = abxUsedStatisticsDao.getAbxUsedStatistics(abxUsedStatisticsBi);
    	List<AbxUsedStatisticsBi> childLi = abxUsedStatisticsDao.getCommonStatistics(abxUsedStatisticsBi);
    	AbxUsedStatisticsBi totalStatistics = abxUsedStatisticsDao.getTotalStatistics(abxUsedStatisticsBi);
    	for(AbxUsedStatisticsBi abx : li){
    		abx.setPatientCount(totalStatistics.getPatientCount());
    		BigDecimal rxPercentage = new BigDecimal(100*abx.getRxCount()/totalStatistics.getRxCount());
//    		BigDecimal amountPercentage = new BigDecimal(100*abx.getRxAbxAmount().doubleValue()/totalStatistics.getAmount().doubleValue());
    		BigDecimal totalPercentage = null;
    		BigDecimal averageRxDrug = new BigDecimal(abx.getRxCount()/totalStatistics.getRxCount());
    		BigDecimal averageRxAmount = new BigDecimal(abx.getRxAbxAmount().doubleValue()/totalStatistics.getRxCount());
    		BigDecimal perDrugCount = new BigDecimal(abx.getRegAbxVariety()/totalStatistics.getPatientCount());
    		BigDecimal perDrugAmount = new BigDecimal(abx.getRxAbxAmount().doubleValue()/totalStatistics.getPatientCount());
    		BigDecimal abxDrugUsage = null;
    		BigDecimal ivDrugUsage = new BigDecimal(100*abx.getIvDrugRegCount()/totalStatistics.getPatientCount());
    		BigDecimal over2DrugUsage = new BigDecimal(100*abx.getOver2DrugRegCount()/totalStatistics.getRxCount());
    		for(AbxUsedStatisticsBi child : childLi){
    			if(abx.getHospitalCode().equals(child.getHospitalCode())){
    				totalPercentage = new BigDecimal(100*abx.getRxAbxAmount().doubleValue()/child.getAmount().doubleValue());
    				abxDrugUsage = new BigDecimal(100*abx.getDrugUsedCount()/child.getPatientCount());
    				abx.setAmount(child.getAmount());
    			}
    		}
    		abx.setRxPercentage(rxPercentage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//    		abx.setAmountPercentage(amountPercentage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setTotalPercentage(totalPercentage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setAverageRxDrug(averageRxDrug.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setAverageRxAmount(averageRxAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
    		abx.setPerDrugCount(perDrugCount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setPerDrugAmount(perDrugAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
    		abx.setAbxDrugUsage(abxDrugUsage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setIvDrugUsage(ivDrugUsage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    		abx.setOver2DrugUsage(over2DrugUsage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    	}
		Long records =  abxUsedStatisticsDao.getAbxUsedCount(abxUsedStatisticsBi);
		Long total  =  records/abxUsedStatisticsBi.getRows() +1;
        return DataModel.getSucDataModal(li, abxUsedStatisticsBi.getPage(), records, total);
    }
}
