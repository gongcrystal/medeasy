package com.medimpact.medeasy.web.bp.rxevastatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.RxEvaStatisticsBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaSt4HpBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.service.bp.rxevastatistics.RxEvaStatistics1Service;
import com.medimpact.medeasy.service.bp.rxevastatistics.RxEvaStatisticsService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class RxEvaStatisticsCtrl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RxEvaStatisticsService rxEva;
	
	@Resource
	private RxEvaStatistics1Service rxEva1;

	@Resource
	private UserService us;
	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;

	@RequestMapping(value = "/rxevast", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsSelect(SearchForm form) {
		/*System.out.println("checkedNodesStr=" + form.getCheckedNodesStr() + form.gethLDrugCategory() + form.getIsIV()
				+ "1: " + form.getIrriteId() + "2:" + form.getPatientType() + " " + form.getRows());*/

		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);
		SearchFormUtil.setStartEndDayCurrentMonth(form);
		/*System.err.println("rxevast:"+form.getStartDate()+" "+form.getEndDate());*/
		return rxEva1.getRxEvaStatisticsData(form);
	}

	@RequestMapping(value = "/rxevastSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsSelectSearch(SearchForm form) {
		/*System.out.println("checkedNodesStr=" + form.getCheckedNodesStr() + form.gethLDrugCategory() + form.getIsIV()
				+ "1: " + form.getIrriteId() + "2:" + form.getPatientType() + " " + form.getRows());*/

		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);
		// 界面是月， 但是按照full_day来检索的， 需要转化一下 -
		/*SearchFormUtil.convertMonth2WithDay(form);*/	
		
		if (form.getStartDate()!=null && form.getEndDate()!=null && form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}		
		/*System.err.println("rxevastSearch:"+form.getStartDate()+" "+form.getEndDate());*/
		return rxEva1.getRxEvaStatisticsData(form);
	}

	@RequestMapping(value = "/rxevast4hpSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public RxEvaSt4HpBi getRxEvaStatistics4HPSelectSearch(SearchForm form) {
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);
		/*SearchFormUtil.convertMonth2WithDay(form);*/
		
		if (form.getStartDate()!=null && form.getEndDate()!=null && form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}
		/*System.err.println("rxevast4hpSearch:"+form.getStartDate()+" "+form.getEndDate());*/
	
		/*List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsDataAntiSt(form).getData();*/
		List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsDataHpNew(form).getData();
		return calEvaSt(list);
	}
	
	//hp 的检索，因和内部的条件不同2018.1.12
	@RequestMapping(value = "/rxevast4hpSearchNew", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public RxEvaSt4HpBi getRxEvaStatistics4HPSelectSearchNew(SearchForm form) {
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		
		/*System.err.println("rxevast4hpSearchNew:"+form.getStartDate()+" "+form.getEndDate()+" "+form.getAreaCode()+"."+form.getHospitalCode()+"."+form.getHospitalKey()+".");*/
		
		if (form.getStartDate()!=null && form.getEndDate()!=null && form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}
		
		
		if(form.getAreaCode().isEmpty())
			form.setAreaCode(null);
		
		String hospitalcode = form.getHospitalKey(); 
		if(hospitalcode!=null && !hospitalcode.isEmpty()){
			form.setHospitalCode(hospitalcode);
		}
		
		if(hospitalcode==null || hospitalcode.isEmpty())
			form.setHospitalCode(null);
		
		/*System.err.println("getRxEvaStatistics4HPSelectSearch: "+form.getStartDate()+" "+form.getEndDate()+ " "+form.getAreaCode()+" "+form.getHospitalCode());*/	
		
		List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsData(form).getData(); /*？*/
		/*List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsDataHpNew(form).getData();*/ /*？*/
		return calEvaSt(list);
	}

	@RequestMapping(value = "/rxevast4hp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public RxEvaSt4HpBi getRxEvaStatistics4HPSelect(SearchForm form) {

		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);

		SearchFormUtil.setStartEndDayCurrentMonth(form);
		/*System.err.println("rxevast4hp:"+form.getStartDate()+" "+form.getEndDate());*/
		List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsData(form).getData();  
		/*List<RxEvaStBi> list = rxEva1.getRxEvaStatisticsDataHpNew(form).getData();*/
		return calEvaSt(list);
	}
	

	public RxEvaSt4HpBi calEvaSt(List<RxEvaStBi> list) {
		if(list==null){
			return null; 
		}
		RxEvaSt4HpBi rBi = new RxEvaSt4HpBi();
		Double tOkAmount = new Double(0); // 合理处方
		Double tNonOkAmount = new Double(0); // 不合理处方
		Double tUnr1 = new Double(0);
		Double tUnr2 = new Double(0);
		Double tUnr3 = new Double(0);
		Double tRxAmount = new Double(0);

		Double perOkAmount = new Double(0); // 合理处方占比%
		Double perNonOkAmount = new Double(0); // 不合理处方占比%
		Double perUnr1 = new Double(0); // 合理处方占比%
		Double perUnr2 = new Double(0); // 不合理处方占比%
		Double perUnr3 = new Double(0); // 不合理处方占比%

		for (RxEvaStBi li : list) {
			
			/*System.err.println(li.getHospitalName()+" "+ li.getRxAmount()+" ok:"+li.getOkAmount()+" nonok:"+li.getNonOkamount());*/

			if (li.getRxAmount() != null)
				tRxAmount += li.getRxAmount();
			if (li.getOkAmount() != null)
				tOkAmount += li.getOkAmount();
			if (li.getNonOkamount() != null)
				tNonOkAmount += li.getNonOkamount();
			if (li.getUnReasonableAmount1() != null)
				tUnr1 += li.getUnReasonableAmount1();
			if (li.getUnReasonableAmount2() != null)
				tUnr2 += li.getUnReasonableAmount2();
			if (li.getUnReasonableAmount3() != null)
				tUnr3 += li.getUnReasonableAmount3();

		}
		if (tRxAmount != 0) {
			if (Double.valueOf(StUtil.decimalFormat1.format((tOkAmount / tRxAmount) * 100)) != null)
				perOkAmount = Double.valueOf(StUtil.decimalFormat1.format((tOkAmount / tRxAmount) * 100));

			if (Double.valueOf(StUtil.decimalFormat1.format((tNonOkAmount / tRxAmount) * 100)) != null)
				perNonOkAmount = Double.valueOf(StUtil.decimalFormat1.format((tNonOkAmount / tRxAmount) * 100));
			if (Double.valueOf(StUtil.decimalFormat1.format((tUnr1 / tRxAmount) * 100)) != null)
				perUnr1 = Double.valueOf(StUtil.decimalFormat1.format((tUnr1 / tRxAmount) * 100));

			if (Double.valueOf(StUtil.decimalFormat1.format((tUnr2 / tRxAmount) * 100)) != null)
				perUnr2 = Double.valueOf(StUtil.decimalFormat1.format((tUnr2 / tRxAmount) * 100));

			if (Double.valueOf(StUtil.decimalFormat1.format((tUnr3 / tRxAmount) * 100)) != null)
				perUnr3 = Double.valueOf(StUtil.decimalFormat1.format((tUnr3 / tRxAmount) * 100));
		}

		rBi.setPerOk(perOkAmount);
		rBi.setPerUnOk(perNonOkAmount);
		rBi.setPerUnr1(perUnr1);
		rBi.setPerUnr2(perUnr2);
		rBi.setPerUnr3(perUnr3);

		return rBi;
	}

}
