package com.medimpact.medeasy.service.jiangsuhealth;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.bean.statistic.JiangSuHealthStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.jiangsuhealth.JiangSuHealthDao;

@Service
public class JiangSuHealthServiceImpl implements JiangSuHealthService {

	@Resource
	private JiangSuHealthDao jsDao;

	@Override
	public DataModel<List<JiangSuHealthStBi>> search(SearchForm form) throws BizException {
		// TODO Auto-generated method stub

		List<JiangSuHealthStBi> list = new ArrayList<>();
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = jsDao.listBySys(form);
			break;

		case SYSCONSTANT.CON_AREA:
			list = jsDao.listByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = jsDao.listByHospital(form);
			break;
		case SYSCONSTANT.CON_USER:
			list = jsDao.listByDr(form);
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

		this.st(list);
		return DataModel.getSucDataModal(list);

	}

	public void st(List<JiangSuHealthStBi> list) {
		StUtil.amountPerPt(list);
		StUtil.amountAvgRx(list);
		StUtil.totalSumOfAmount(list);
	}
}
