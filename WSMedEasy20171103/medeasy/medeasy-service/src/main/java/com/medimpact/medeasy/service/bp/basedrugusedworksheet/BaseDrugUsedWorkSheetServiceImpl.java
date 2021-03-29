package com.medimpact.medeasy.service.bp.basedrugusedworksheet;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.BaseDrugSheetBi;
import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedWorkSheetBi;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.dao.bp.basedrugusedworksheet.BaseDrugUsedWorkSheetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class BaseDrugUsedWorkSheetServiceImpl implements BaseDrugUsedWorkSheetService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private BaseDrugUsedWorkSheetDao wsDao;

	@Override
	public DataModel<List<BaseDrugSheetBi>> getBaseDrugUsedWorkSheetData(SearchForm form) {

		//更新需求要求显示当前年 
		/*if (form.getStartDate() == null || form.getEndDate() == null) {
			System.out.println("请选择起止时间");
			return DataModel.getFailDataModalNotException("请选择起止时间");
		}*/
		
		List<BaseDrugSheetBi> list = new ArrayList<>();
		/*Long records = new Long(0);
		Long total;*/

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);
		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = wsDao.listBySys(form);
		/*	records = wsDao.selectCountBySys(form);*/
			
			break;

		case SYSCONSTANT.CON_AREA:
			list = wsDao.listByArea(form);
			/*records = wsDao.selectCountByArea(form);*/
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = wsDao.listByHospital(form);
			/*records = wsDao.selectCountByHospital(form);*/
			break;
		case SYSCONSTANT.CON_USER:
			list = wsDao.listByDr(form);
			/*records = wsDao.selectCountByDr(form);*/
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

	/*	total = records / form.getRows() + 1;*/
		for(BaseDrugSheetBi li: list){
			li.setFullDay(li.getYear()+"-"+li.getMonth());
		}
		return DataModel.getSucDataModal(list);

	}

}
