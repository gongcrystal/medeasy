package com.medimpact.medeasy.service.bp.abxunauthorizedusedana;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxUnauthorizedUsedAnaBi;
import com.medimpact.medeasy.dao.bp.abxunauthorizedusedana.AbxUnauthorizedUsedAnaDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class AbxUnauthorizedUsedAnaServiceImpl implements AbxUnauthorizedUsedAnaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxUnauthorizedUsedAnaDao abxUnauthorizedUsedAnaDao;
    @Override
    public DataModel<List<AbxUnauthorizedUsedAnaBi>> getAbxUnauthorizedUsedAnaData(AbxUnauthorizedUsedAnaBi abxUnauthorizedUsedAnaBi) throws Exception {
    	if(null !=abxUnauthorizedUsedAnaBi.getDrugClassBtndrugid() && !"".equals(abxUnauthorizedUsedAnaBi.getDrugClassBtndrugid())){
    		abxUnauthorizedUsedAnaBi.setDrugClassBtndrugcategoryid(null);
    	}
    	List<AbxUnauthorizedUsedAnaBi> li = abxUnauthorizedUsedAnaDao.getAbxUnauthorizedUsedAna(abxUnauthorizedUsedAnaBi);
		Long records = abxUnauthorizedUsedAnaDao.getAbxUnauthorizedUsedCount(abxUnauthorizedUsedAnaBi);
		Long total = records/abxUnauthorizedUsedAnaBi.getRows() +1;
        return DataModel.getSucDataModal(li, abxUnauthorizedUsedAnaBi.getPage(), records, total);
    }
}
