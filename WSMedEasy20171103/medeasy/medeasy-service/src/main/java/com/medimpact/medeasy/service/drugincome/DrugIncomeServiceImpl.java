package com.medimpact.medeasy.service.drugincome;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.BaseDrugSheetBi;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.drugincome.DrugIncomeDao;

import net.sf.ehcache.search.expression.And;

@Service
public class DrugIncomeServiceImpl implements DrugIncomeService {

	@Resource
	private DrugIncomeDao inDao;

	@Override
	public DataModel<List<DrugIncomeStBi>> stDifDrugCategoryTotalAmount(SearchForm form) {

		List<DrugIncomeStBi> list = new ArrayList<>();
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		
		SearchFormUtil.checkAreaHospitalCodeNull(form);
		
		switch (opLevel) {
		
		case SYSCONSTANT.CON_SYS:
			list = inDao.listBySys(form);			
			break;

		case SYSCONSTANT.CON_AREA:
			list = inDao.listByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = inDao.listByHospital(form);
			break;
		/*case SYSCONSTANT.CON_USER:
			list = inDao.listByDr(form);
			break;*/

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

		this.st(list);
		
		//第一行总收入部分暂时这样处理
		DrugIncomeStBi t = list.get(0);
		t.setAmountPerAreaTotal(null); //金额总占比%
		t.setAmountAvgPt(null); //平均处方金额（元）
		t.setAmountAvgRx(null);  //平均处方金额（元）
		t.setTurnoverRate(null); //药品周转率%
		
		return DataModel.getSucDataModal(list);
	}

	public void st(List<DrugIncomeStBi> list) {
		if (!list.isEmpty()) {
			StUtil.amountPerPt(list);
			StUtil.amountAvgRx(list);
		}
	}
}
