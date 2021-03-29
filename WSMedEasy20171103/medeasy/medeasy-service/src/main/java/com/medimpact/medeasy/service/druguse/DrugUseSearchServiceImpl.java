package com.medimpact.medeasy.service.druguse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.DrugCategoryBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseDrugVarietyBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.druguse.DrugUseBigRxSearchDao;
import com.medimpact.medeasy.dao.druguse.DrugUseDrugVarietySearchDao;
import com.medimpact.medeasy.dao.druguse.DrugUseSearchDao;
import com.medimpact.medeasy.dao.druguse.DruguseDrugType4SearchDao;

import net.sf.ehcache.pool.impl.FromLargestCacheOnDiskPoolEvictor;

@Service
public class DrugUseSearchServiceImpl implements DrugUseSearchService {

	@Resource
	private DrugUseSearchDao drugUseDao1;

	@Resource
	private DrugUseDrugVarietySearchDao variatyDao1;//查询， 但是不查询和药品相关的药品品种数计算
	
	@Resource
	private DrugUseBigRxSearchDao bigRxDao;
	
	
	@Resource
	private  DruguseDrugType4SearchDao variatyDao2; //查询， 但是查询和药品相关的药品品种数计算

	@Override
	public DataModel<List<HospitalDrugUseStBi>> drugUseStByHospital(SearchForm form) {

		// TODO Auto-generated method stub
		List<HospitalDrugUseStBi> list = new ArrayList<>();

		List<HospitalDrugUseDrugVarietyBi> listVariety = new ArrayList<>();
		
		//在点击查询的时候， 如果选择了药，bigrx的分母按要求不变化， 所以单独拿出来处理！
		List<HospitalDrugUseStBi> listBigRx =  new ArrayList<>(); 
		
		//drugrelated search， 药品品种数的计算同其他的检索计算方法不一样
		List<HospitalDrugUseDrugVarietyBi> listDrugVariety4DrugSearch = new ArrayList<>();
		
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);			

		if (form.getPatientType() !=null && form.getPatientType().equals(SYSCONSTANT.OUTPATIENT_URGENT))
			form.setPatientType(null); // 检索时， 当时门急诊的时候， 表示检索所有的，此处不含住院
		
		if(form.getLocalDrugId()!=null && form.getLocalDrugId().isEmpty()){
			form.setLocalDrugId(null);
		}
		
		boolean isDRel = SearchFormUtil.isDrugRelatedSearch(form);
		
		if(isDRel){
			SearchFormUtil.controlDrugSearchPrio(form);
		}
				
		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = drugUseDao1.listBySys(form);
			listVariety = variatyDao1.listBySys(form);
			System.err.println("list.size:"+list.size()+" listVariety.size="+listVariety.size());
		
			if(isDRel){
				//drugrelated seach, 分母rxamount不受药品检索的影响
				listBigRx = bigRxDao.listBySys(form); 				
				//drugrelated search， 药品品种数分子，计算同其他的检索计算方法不一样
				listDrugVariety4DrugSearch = variatyDao2.listBySys(form);				
			}
			break;

		case SYSCONSTANT.CON_AREA:
			list = drugUseDao1.listByArea(form);
			listVariety = variatyDao1.listByArea(form);
			if(isDRel){
				listBigRx = bigRxDao.listByArea(form);
				
				//drugrelated search， 药品品种数的计算同其他的检索计算方法不一样
				listDrugVariety4DrugSearch = variatyDao2.listByArea(form);
			}
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = drugUseDao1.listByHospital(form);
			listVariety = variatyDao1.listByHospital(form);
			if(isDRel){
				listBigRx = bigRxDao.listByHospital(form);
				
				//drugrelated search， 药品品种数的计算同其他的检索计算方法不一样
				listDrugVariety4DrugSearch = variatyDao2.listByHospital(form);
			}
			break;
	/*	case SYSCONSTANT.CON_USER:

			break;*/

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}			
		if(isDRel==false){ //不是藥品相關的檢測
			drgVarietyCP(list, listVariety);
			StUtil.bidRxPer(list);
			StUtil.drugSpecAvgPt(list);//人均药品品种数
		}else{
			//把listVariety里的bigrx数stream.copy 到listBigRx; 然后以listBigRx来计算bigrx的百分比；		
			drgBigRXCP(listBigRx,listVariety);			
			StUtil.bidRxPer(listBigRx);			
			//把listBigRx里计算的百分比，put进list,
			drgPerBigRXCP(list, listBigRx);
			
			//人均药品品种数
			drgVarietyCPDrugCategory(list,listDrugVariety4DrugSearch);
			
			/*for(HospitalDrugUseDrugVarietyBi bi: listDrugVariety4DrugSearch){
				System.err.println("c6: "+bi.getHospitalName()+" "+bi.getDrugSpecAmount());
			}*/			
			
			StUtil.drugSpecAvgPt(list);//人均药品品种数
			
			/*for(HospitalDrugUseStBi bi: list){
				System.err.println("c7: "+bi.getHospitalName()+" "+bi.getDrugSpecAmount()+" "+bi.getDrugSpecAvgPt());
			}*/
			
		}
			
		
		if (form.getDrugCategoryBis() != null && form.getDrugCategoryBis().size() > 0) {
			List<HospitalDrugUseStBi> rslt1 = new ArrayList<>();
			drugCategory(list, rslt1, form);
			this.st(rslt1);
			return DataModel.getSucDataModal(rslt1);
		} else {
			this.st(list);
			return DataModel.getSucDataModal(list);
		}
		
	}

	@Override
	public DataModel<HospitalDrugUseStBi> drugUseSt4HomePage(SearchForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public void st(List<HospitalDrugUseStBi> list) {

		if (!list.isEmpty()) {
			StUtil.calRank(list);
			/*StUtil.calAmountPerTotal(list);*/  //药占比目前不能做， 没有总金额 
			StUtil.amountPerPt(list);
			/*StUtil.drugSpecAvgPt(list);*/
			/*StUtil.bidRxPer(list);*/ //bigrx要拿出来单独处理
			StUtil.amountAvgRx(list);
			StUtil.drugDayAvgPt(list);
			/* StUtil.totalSumOfAmount(list); */
		}
	}

	public void drugCategory(List<HospitalDrugUseStBi> list, List<HospitalDrugUseStBi> result1, SearchForm form) {
		if (form.getDrugCategoryBis() != null && form.getDrugCategoryBis().size() > 0) {
			for (DrugCategoryBi bi : form.getDrugCategoryBis()) {
				list.stream().forEach(v -> {
					if (v.getDrugBasicCatetory().contains(bi.getSearch())) {
						result1.add(v);
					}
				});

			}

		}
	}
	
	
	

	// 把list2里对应和list医院一样的drugvarietyaccount 赋值进来
	public void drgVarietyCP(List<HospitalDrugUseStBi> list, List<HospitalDrugUseDrugVarietyBi> list2) {
		Map<String, HospitalDrugUseStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		Map<String, HospitalDrugUseDrugVarietyBi> map1 = list2.stream()
				.filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseDrugVarietyBi::getHospitalName, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setDrugSpecAmount(map1.get(k).getDrugSpecAmount());
				v.setBigRx(map1.get(k).getBigRx());
			}
		});
	}
	
	public void drgBigRXCP(List<HospitalDrugUseStBi> list, List<HospitalDrugUseDrugVarietyBi> list2) {
		Map<String, HospitalDrugUseStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		Map<String, HospitalDrugUseDrugVarietyBi> map1 = list2.stream()
				.filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseDrugVarietyBi::getHospitalName, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {				
				v.setBigRx(map1.get(k).getBigRx());
			}
		});
	}
	
	
	
	public void drgPerBigRXCP(List<HospitalDrugUseStBi> list, List<HospitalDrugUseStBi> list2) {
		Map<String, HospitalDrugUseStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		Map<String, HospitalDrugUseStBi> map1 = list2.stream()
				.filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {				
				v.setPerbigRx(map1.get(k).getPerbigRx());
			}
		});
	}
	
	//drugrelated search, cp the amount of drugCategory from list2 to list. 
	public void drgVarietyCPDrugCategory(List<HospitalDrugUseStBi> list, List<HospitalDrugUseDrugVarietyBi> list2) {
		Map<String, HospitalDrugUseStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseStBi::getHospitalName, bi -> bi));

		Map<String, HospitalDrugUseDrugVarietyBi> map1 = list2.stream()
				.filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(HospitalDrugUseDrugVarietyBi::getHospitalName, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setDrugSpecAmount(map1.get(k).getDrugSpecAmount());
				
			}
		});
	}
	
}
