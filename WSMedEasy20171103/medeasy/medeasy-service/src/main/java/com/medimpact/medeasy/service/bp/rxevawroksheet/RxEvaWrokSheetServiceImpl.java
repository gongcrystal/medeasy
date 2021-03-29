package com.medimpact.medeasy.service.bp.rxevawroksheet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.PageData;
import com.medimpact.medeasy.common.bean.bp.RxEvaWrokSheetBi;
import com.medimpact.medeasy.dao.bp.rxevawroksheet.RxEvaWrokSheetDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class RxEvaWrokSheetServiceImpl implements RxEvaWrokSheetService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private RxEvaWrokSheetDao rxEvaWrokSheetDao;
    @Override
    public PageData<RxEvaWrokSheetBi> getRxEvaWrokSheetData(RxEvaWrokSheetBi rxEvaWrokSheetBi) throws Exception {
    	PageData<RxEvaWrokSheetBi> pageData = new PageData<RxEvaWrokSheetBi>();
    	if(null !=rxEvaWrokSheetBi.getDrugClassBtndrugid() && !"".equals(rxEvaWrokSheetBi.getDrugClassBtndrugid())){
    		rxEvaWrokSheetBi.setDrugClassBtndrugcategoryid(null);
    		rxEvaWrokSheetBi.setBasicDrugCategory(null);
    	}else if(null !=rxEvaWrokSheetBi.getBasicDrugCategory() && !"".equals(rxEvaWrokSheetBi.getBasicDrugCategory())){
    		rxEvaWrokSheetBi.setDrugClassBtndrugcategoryid(null);
    	}
    	List<RxEvaWrokSheetBi> list = rxEvaWrokSheetDao.getRxEvaWrokSheet(rxEvaWrokSheetBi);
		List<RxEvaWrokSheetBi> statisticList = rxEvaWrokSheetDao.getRxEvaWrokSheetStatistic(rxEvaWrokSheetBi);

    	RxEvaWrokSheetBi sumRxEva = new RxEvaWrokSheetBi();
    	RxEvaWrokSheetBi avgRxEva = new RxEvaWrokSheetBi();
    	RxEvaWrokSheetBi percentRx = new RxEvaWrokSheetBi();
		List<RxEvaWrokSheetBi> extraList = new ArrayList<RxEvaWrokSheetBi>();

    	if(null != list && !list.isEmpty()&&statisticList!=null && statisticList.size()==1){
    		RxEvaWrokSheetBi statisticRxEva= statisticList.get(0);
    		sumRxEva.setRxCode("总计");
        	sumRxEva.setDrugVariety(statisticRxEva.getDrugVariety());
        	sumRxEva.setIsIv(String.valueOf(statisticRxEva.getIsIv()));
        	sumRxEva.setIsAbx(String.valueOf(statisticRxEva.getIsAbx()));
        	sumRxEva.setBasicDrugVariety(statisticRxEva.getBasicDrugVariety());
        	sumRxEva.setGenNameCount(statisticRxEva.getGenNameCount());
        	sumRxEva.setAmount(statisticRxEva.getAmount());
        	sumRxEva.setIsOk(String.valueOf(statisticRxEva.getIsOk()));

        	avgRxEva.setRxCode("平均");
        	avgRxEva.setAvgDrugVariety(statisticRxEva.getAvgDrugVariety());
        	avgRxEva.setAmount(statisticRxEva.getAvgAmount());

        	percentRx.setRxCode("百分比");
        	percentRx.setIvPercentage(statisticRxEva.getIvPercentage());
        	percentRx.setAbxPercentage(statisticRxEva.getAbxPercentage());
        	percentRx.setOkPercentage(statisticRxEva.getOkPercentage());
        	percentRx.setAvgBasicDrugVariety(statisticRxEva.getAvgBasicDrugVariety());
        	percentRx.setAvgGenNameCount(statisticRxEva.getAvgGenNameCount());

			extraList.add(sumRxEva);
			extraList.add(avgRxEva);
			extraList.add(percentRx);
    	}
		pageData.setAaData(list);
    	pageData.setExtraData(extraList);
    	return pageData;
    }
    
}
