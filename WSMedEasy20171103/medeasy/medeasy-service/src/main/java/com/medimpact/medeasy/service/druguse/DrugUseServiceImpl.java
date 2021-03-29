package com.medimpact.medeasy.service.druguse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.functors.IfClosure;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.StatisticParameter;
import com.medimpact.medeasy.common.bean.DrugUseBi;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseDrugVarietyBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.druguse.DrugUse4HPDao;
import com.medimpact.medeasy.dao.druguse.DrugUseDao;
import com.medimpact.medeasy.dao.druguse.DrugUseDrugVarietyDao;

@Service
public class DrugUseServiceImpl implements DrugUseService {

	@Resource
	private DrugUseDao drugUseDao;

	@Resource
	private DrugUse4HPDao drugUseDaoHp;

	@Resource
	private DrugUseDrugVarietyDao variatyDao;

	@Override
	public DataModel<List<HospitalDrugUseStBi>> drugUseStByHospital(SearchForm form, boolean is4Dt) {
		// TODO Auto-generated method stub
		List<HospitalDrugUseStBi> list = new ArrayList<>();

		List<HospitalDrugUseDrugVarietyBi> listVariety = new ArrayList<>();
		Long records = new Long(0);
		Long total;

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = drugUseDao.listBySys(form);
			listVariety = variatyDao.listBySys(form);
			System.err.println("Crystal"+list.size()+" "+listVariety.size());
			break;
		case SYSCONSTANT.CON_AREA:
			list = drugUseDao.listByArea(form);
			listVariety = variatyDao.listByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = drugUseDao.listByHospital(form);
			listVariety = variatyDao.listByArea(form);
			break;
	/*	case SYSCONSTANT.CON_USER:
			list = drugUseDao.listByDr(form);
			 records = drugUseDao.selectCountByDr(form); 
			break;*/

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		
		drgVarietyCP(list, listVariety);	
		
		if (is4Dt) { // 如果是datatable
			this.st(list);
			/*
			 * return DataModel.getSucDataModal4DT(list, records, records,
			 * form.getDraw());
			 */
			return DataModel.getSucDataModal(list);// 因安医院显示统一去掉分页
		} else { // jqgrid

			/* total = records / form.getRows() + 1; */
			this.st(list);
			
			/*for(HospitalDrugUseStBi bi: list){
				System.out.println("coco7:"+bi.getHospitalName()+" "+bi.getBigRx()+" "+bi.getRxAmount()+" "+bi.getPerbigRx());
			}*/		
			
			return DataModel.getSucDataModal(list);
			/*
			 * return DataModel.getSucDataModal(list, form.getPage(), records,
			 * total);
			 */
		}
	}
	

	public void st(List<HospitalDrugUseStBi> list) {
	
		if (!list.isEmpty()) {
			StUtil.calRank(list);
			/*StUtil.calAmountPerTotal(list);*/
			StUtil.amountPerPt(list);
			StUtil.drugSpecAvgPt(list);
			StUtil.bidRxPer(list);
			StUtil.amountAvgRx(list);
			StUtil.drugDayAvgPt(list);
			/* StUtil.totalSumOfAmount(list); */
		}
	}

	@Override
	public DataModel<HospitalDrugUseStBi> drugUseSt4HomePage(SearchForm form) {
		// TODO Auto-generated method stub
		List<HospitalDrugUseStBi> list = new ArrayList<>();

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = drugUseDaoHp.listBySys(form);
			break;

		case SYSCONSTANT.CON_AREA:
			list = drugUseDaoHp.listByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = drugUseDaoHp.listByHospital(form);
			break;
		case SYSCONSTANT.CON_USER:
			list = drugUseDaoHp.listByDr(form);
			break;
		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		

		this.stHp(list);
		return DataModel.getSucDataModal(list.get(0));
	}

	public void stHp(List<HospitalDrugUseStBi> list) {
		if (!list.isEmpty()) {

			StUtil.amountAvgRx(list);
			StUtil.drugSpecAvgPt(list);
			StUtil.amountPerPt(list);
		}
	}

	// 把list2里对应和list医院一样的drugvarietyaccount 赋值进来
	public void drgVarietyCP(List<HospitalDrugUseStBi> list, List<HospitalDrugUseDrugVarietyBi> list2) {
		Map<String, HospitalDrugUseStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		Map<String, HospitalDrugUseDrugVarietyBi> map1 = list2.stream()
				.filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseDrugVarietyBi::getHospitalName, bi -> bi));

		/*
		 * map.forEach((k, v) ->
		 * v.setDrugSpecAmount(map1.get(k).getDrugSpecAmount()));
		 */
		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setDrugSpecAmount(map1.get(k).getDrugSpecAmount());
				v.setBigRx(map1.get(k).getBigRx());
			}
		});
	}
}
