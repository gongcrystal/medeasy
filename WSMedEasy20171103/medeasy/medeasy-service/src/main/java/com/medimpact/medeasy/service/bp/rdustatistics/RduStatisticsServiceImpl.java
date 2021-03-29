package com.medimpact.medeasy.service.bp.rdustatistics;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.RduStatisticsBi;
import com.medimpact.medeasy.dao.bp.rdustatistics.RduStatisticsDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class RduStatisticsServiceImpl implements RduStatisticsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private RduStatisticsDao rduStatisticsDao;
    @Override
    public DataModel<List<RduStatisticsBi>> getRduStatisticsData(RduStatisticsBi rduStatisticsBi) throws Exception {
    	List<RduStatisticsBi> li = null;
    	List<RduStatisticsBi> amountList = null;
    	Long records =  new Long(0);
    	if(null !=rduStatisticsBi.getDrugClassBtndrugid() && !"".equals(rduStatisticsBi.getDrugClassBtndrugid())){
    		rduStatisticsBi.setDrugClassBtndrugcategoryid(null);
    	}
    	if("hospital".equals(rduStatisticsBi.getRsFormTypeKey()) 
    			|| "".equals(rduStatisticsBi.getRsFormTypeKey()) || null == rduStatisticsBi.getRsFormTypeKey()){
    		li = rduStatisticsDao.getRduStatisticsByHospital(rduStatisticsBi);
//    		amountList = rduStatisticsDao.getAmountByHospital(rduStatisticsBi);
//    		for(RduStatisticsBi rdu : li){
//    			for(RduStatisticsBi amount : amountList){
//    				if(rdu.getHospitalCode().equals(amount.getHospitalCode())){
//    					rdu.setAmount(amount.getAmount());
//    				}
//    			}
//    		}
    		records = rduStatisticsDao.getCountByHospital(rduStatisticsBi);
    	}else{
    		li = rduStatisticsDao.getRduStatisticsByAlert(rduStatisticsBi);
    		records = rduStatisticsDao.getCountByAlert(rduStatisticsBi);
    	}
		Long total  =  records/rduStatisticsBi.getRows() +1;
        return DataModel.getSucDataModal(li, rduStatisticsBi.getPage(), records, total);
    }
    
}
