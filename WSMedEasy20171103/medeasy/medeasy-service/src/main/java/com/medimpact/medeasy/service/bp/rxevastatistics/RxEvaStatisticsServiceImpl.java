package com.medimpact.medeasy.service.bp.rxevastatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.StatisticParameter;
import com.medimpact.medeasy.common.bean.BaseDrugSheetBi;
import com.medimpact.medeasy.common.bean.bp.RxEvaStatisticsBi;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatisticsDao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatisticsHpDao;

import org.hibernate.validator.constraintvalidators.RegexpURLValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class RxEvaStatisticsServiceImpl implements RxEvaStatisticsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RxEvaStatisticsDao dao;
	
	@Resource
	private RxEvaStatisticsHpDao dao4Hp; 	
	

	@Override
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsData(SearchForm form) {
		// TODO Auto-generated method stub
		
		/*System.out.println("getRxEvaStatisticsData: "+ form.gethLDrugCategory()+" "+form.getIsIV()+form.getSecUser().getUsername()+" "+form.getOperateLevel());*/
		
		List<String> drugs = form.getDrugCLi();
		if (drugs != null) {
			for (String ss : drugs) {
				System.out.println(ss);
			}
		}
		List<RxEvaStBi> list = new ArrayList<>();
		
	/*	Long records = new Long(0);
		Long total;*/

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = dao.listBySys(form);
			
			
			break;

		case SYSCONSTANT.CON_AREA:
			list = dao.listByArea(form);
			
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = dao.listByHospital(form);
			
			break;
		/*case SYSCONSTANT.CON_USER:
			list = dao.listByDr(form);
		
			break;*/

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

		this.st(list);		
		return DataModel.getSucDataModal(list);
	}

	public void st(List<RxEvaStBi> list) {
		if (!list.isEmpty()) {			
			StUtil.okPerRXAmount(list);
			StUtil.nonOkPerRXAmount(list);			
			StUtil.nonOKAbxPerRXAmount(list);
			StUtil.abxPerRXAmount(list);
			StUtil.amountAvgRx(list);
		}
	}

	@Override
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataHpNew(SearchForm form) {

		// TODO Auto-generated method stub
		
		/*System.out.println("getRxEvaStatisticsData: "+ form.gethLDrugCategory()+" "+form.getIsIV()+form.getSecUser().getUsername()+" "+form.getOperateLevel());*/
		
		List<String> drugs = form.getDrugCLi();
		if (drugs != null) {
			for (String ss : drugs) {
				System.out.println(ss);
			}
		}
		List<RxEvaStBi> list = new ArrayList<>();
		
	/*	Long records = new Long(0);
		Long total;*/

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = dao4Hp.listBySys(form);			
			
			break;

		case SYSCONSTANT.CON_AREA:
			list = dao4Hp.listByArea(form);
			
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = dao4Hp.listByHospital(form);
			
			break;
		case SYSCONSTANT.CON_USER:
			list = dao4Hp.listByDr(form);		
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		this.nonOkAmount(list);
		return DataModel.getSucDataModal(list);	
	}
	
	
	// 不合理处方占比%
		public void nonOkAmount(List<RxEvaStBi> list) {
			if(list==null)
				return ; 
			for (RxEvaStBi t : list) {
			
				if (t.getOkAmount() !=null && t.getRxAmount()!=null) {
					if(t.getRxAmount()!=0){
						t.setNonOkamount(t.getRxAmount() - t.getOkAmount());
					}else{
						t.setNonOkamount(new Long(0));
					}
							
				}
			}
			
		}


}
