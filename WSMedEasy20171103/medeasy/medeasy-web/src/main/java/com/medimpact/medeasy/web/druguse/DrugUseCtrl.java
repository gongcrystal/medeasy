package com.medimpact.medeasy.web.druguse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.StatisticParameter;
import com.medimpact.medeasy.common.bean.DrugCategoryBi;
import com.medimpact.medeasy.common.bean.DrugUseBi;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.bean.statistic.DrugIncomeStBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.common.bean.statistic.JiangSuHealthStBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.form.SearchForm4HomePage;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.service.drugcategory.DrugCategoryService;
import com.medimpact.medeasy.service.drugincome.DrugIncomeService;
import com.medimpact.medeasy.service.druguse.DrugUseSearchService;
import com.medimpact.medeasy.service.druguse.DrugUseService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.jiangsuhealth.JiangSuHealthService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/druguse")
public class DrugUseCtrl {
	@Resource
	private DrugUseService dUseService;

	@Resource
	private DrugUseSearchService dUseSrchService;

	@Resource
	private DrugIncomeService dIncomeServices;

	@Resource
	private JiangSuHealthService jsService;

	@Resource
	private UserService us;

	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;

	@Resource
	private DrugCategoryService dcService;

	// for default
	@RequestMapping(value = "/drugUseStHospital", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<HospitalDrugUseStBi>> drugUseStHospital(SearchForm form) {		
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);
		/* SearchFormUtil.setCurrentYearMonth(form); */
		SearchFormUtil.setStartEndDayCurrentMonth(form);
		System.err.println("drugUseStHospital:"+form.getStartDate()+" "+form.getEndDate());
		return dUseService.drugUseStByHospital(form, false);
	}

	// search
	@RequestMapping(value = "/drugUseStHospitalSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<HospitalDrugUseStBi>> drugUseStHospitalSearch(SearchForm form) {
		System.out.println("drugUseStHospitalSearch: "+form.getCheckedNodesStr()+" "+form.getLocalDrugId());
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.preHandleSearchForm(form);
		
		if (form.getStartDate()!=null && form.getEndDate()!=null && form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}
		System.err.println("drugUseStHospitalSearch:"+form.getStartDate()+" "+form.getEndDate());
		return dUseSrchService.drugUseStByHospital(form);
	}

	@RequestMapping(value = "/drugUseStHospital4Dt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	
	public DataModel<List<HospitalDrugUseStBi>> drugUseStHospital4Dt(
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "areaKey", required = false) String areaKey,
			@RequestParam(value = "hospitalKey", required = false) String hospitalKey) {
System.err.println("drugUseStHospital4Dt: start:"+start+" "+end+" "+areaKey+" "+hospitalKey);
		SearchForm form = new SearchForm();
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		/*SearchFormUtil.preHandleSearchForm(form);*/

		// 默认情况下, 前端传过来的start为0
		if (start == null || start.isEmpty() || start.equals("0")) {
			/*SearchFormUtil.setCurrentYearMonth(form);*/
			SearchFormUtil.setStartEndDayCurrentMonth(form);
			System.err.println("drugUseStHospital4Dt: "+form.getStartDate()+" "+form.getEndDate());
			return dUseService.drugUseStByHospital(form, true);
		} else {
			form.setStartDate(start);
			form.setEndDate(end);
			form.setAreaCode(areaKey);
			form.setHospitalCode(hospitalKey);
			
			if (form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
				SearchFormUtil.convertMonth2WithDaySameMonth(form);
			} else {
				SearchFormUtil.convertMonth2WithDay(form);
			}			
			System.err.println("drugUseStHospital4Dt: "+form.getStartDate()+" end: "+form.getEndDate()+" "+form.getAreaCode()+" "+form.getHospitalCode());
			return dUseSrchService.drugUseStByHospital(form);
		}
	}

	/* HomePage 药品使用基础信息查询 */
	@RequestMapping(value = "/drugUseStHomePage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<HospitalDrugUseStBi> drugUseStHomePage(SearchForm form) {

		UserInfoUtil.setUserInfo(us, ts, hService, form);
		
		SearchFormUtil.setStartEndDayCurrentMonth(form);
		System.err.println("drugUseStHomePage: "+form.getStartDate()+" "+form.getEndDate());
		
		List<HospitalDrugUseStBi> list = dUseService.drugUseStByHospital(form, false).getData();

		HospitalDrugUseStBi stBi = new HospitalDrugUseStBi();

		Long tRxAmount = new Long(0); // 处方数；
		Long tRegAmount = new Long(0); // 就诊人次
		BigDecimal tMamount = new BigDecimal(0); // 药品使用金额
		Long tDrugSpecAmount = new Long(0); // 药品品种数

		Double drugSpecAvgPt = new Double(0); // 人均药品品种数:就诊用药“处方品种数”合计/同期使用该药“就诊人次”
		Double amountAvgRx = new Double(0);// 平均处方金额:平均处方金额（元）：就诊用药“药品使用金额”/同期使用该药“处方数”
		Double amountAvgPt = new Double(0);// 人均药品使用金额:就诊用药“药品使用金额”/同期使用该药“就诊人次”

		if (list != null) {

			for (HospitalDrugUseStBi li : list) {
				tRxAmount += li.getRxAmount();
				tRegAmount += li.getRegAmount();
				tMamount = tMamount.add(li.getMamount());
				tDrugSpecAmount += li.getDrugSpecAmount();
			}
			System.out.println("co1:" + tRxAmount + " " + tRegAmount + " " + tMamount);
			if (tRegAmount != 0)
				drugSpecAvgPt = Double
						.valueOf(StUtil.decimalFormat1.format(new Double(tDrugSpecAmount) / new Double(tRegAmount)));
			if (tRxAmount != 0)
				amountAvgRx = Double.valueOf(
						StUtil.decimalFormat1.format(tMamount.divide(new BigDecimal(tRxAmount), 2).doubleValue()));
			if (tRegAmount != 0)
				amountAvgPt = Double.valueOf(
						StUtil.decimalFormat1.format(tMamount.divide(new BigDecimal(tRegAmount), 2).doubleValue()));
		}

		stBi.setRxAmount(tRxAmount);
		stBi.setRegAmount(tRegAmount);
		stBi.setMamount(tMamount);
		stBi.setDrugSpecAvgPt(drugSpecAvgPt);
		stBi.setAmountAvgRx(amountAvgRx);
		stBi.setAmountAvgPt(amountAvgPt);

		return DataModel.getSucDataModal(stBi);
	}

	public SearchForm convertSearchParam(SearchForm4HomePage formHp) {
		SearchForm form = new SearchForm();

		if (formHp != null) {

			form.setStartDate(formHp.getStart());
			form.setEndDate(formHp.getEnd());

			if (formHp.getAreaKey().isEmpty()) {
				form.setAreaCode(null);
			} else {
				form.setAreaCode(formHp.getAreaKey());
			}

			if (formHp.getHospitalKey().isEmpty()) {
				form.setHospitalCode(null);
			} else {
				form.setHospitalCode(formHp.getHospitalKey());
			}
		}

		return form;

	}

	// HomePage 药品使用基础信息查询, 因前端传过来的查询参数不同， 此处令处理
	@RequestMapping(value = "/drugUseStHomePageSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<HospitalDrugUseStBi> drugUseStHomePageSearch(SearchForm4HomePage  formHp) {
		System.err.println(formHp.getStart()+ formHp.getEnd()+ formHp.getAreaKey()+formHp.getHospitalKey());
		SearchForm form = convertSearchParam(formHp);
		/*form.setHospitalCode(form.getHospitalKey());*/
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		/*SearchFormUtil.convertMonth2WithDay(form);*/
		SearchFormUtil.preHandleSearchForm(form);
		
		if (form.getStartDate()!=null  && form.getEndDate()!=null &&form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}		
		
		/*
		 * List<HospitalDrugUseStBi> list =
		 * dUseService.drugUseStByHospital(form, false).getData();
		 */
		System.err.println("drugUseStHomePageSearch:"+form.getStartDate()+" "+form.getEndDate()+" "+form.getAreaCode()+" "+form.getHospitalCode());
		List<HospitalDrugUseStBi> list = dUseSrchService.drugUseStByHospital(form).getData();
		
		HospitalDrugUseStBi stBi = new HospitalDrugUseStBi();

		Long tRxAmount = new Long(0); // 处方数；
		Long tRegAmount = new Long(0); // 就诊人次
		BigDecimal tMamount = new BigDecimal(0); // 药品使用金额
		Long tDrugSpecAmount = new Long(0); // 药品品种数

		Double drugSpecAvgPt = new Double(0); // 人均药品品种数:就诊用药“处方品种数”合计/同期使用该药“就诊人次”
		Double amountAvgRx = new Double(0);// 平均处方金额:平均处方金额（元）：就诊用药“药品使用金额”/同期使用该药“处方数”
		Double amountAvgPt = new Double(0);// 人均药品使用金额:就诊用药“药品使用金额”/同期使用该药“就诊人次”

		if (list != null) {

			for (HospitalDrugUseStBi li : list) {
				tRxAmount += li.getRxAmount();
				tRegAmount += li.getRegAmount();
				tMamount = tMamount.add(li.getMamount());
				tDrugSpecAmount += li.getDrugSpecAmount();
			}
			if (tRegAmount != 0)
				drugSpecAvgPt = Double
						.valueOf(StUtil.decimalFormat1.format(new Double(tDrugSpecAmount) / new Double(tRegAmount)));
			if (tRxAmount != 0)
				amountAvgRx = Double.valueOf(
						StUtil.decimalFormat1.format(tMamount.divide(new BigDecimal(tRxAmount), 2).doubleValue()));
			if (tRegAmount != 0)
				amountAvgPt = Double.valueOf(
						StUtil.decimalFormat1.format(tMamount.divide(new BigDecimal(tRegAmount), 2).doubleValue()));
		}

		stBi.setRxAmount(tRxAmount);
		stBi.setRegAmount(tRegAmount);
		stBi.setMamount(tMamount);
		stBi.setDrugSpecAvgPt(drugSpecAvgPt);
		stBi.setAmountAvgRx(amountAvgRx);
		stBi.setAmountAvgPt(amountAvgPt);

		return DataModel.getSucDataModal(stBi);
	}
	
	@RequestMapping(value = "/drugIncome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<DrugIncomeStBi>> drugIncomeSearch(
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "areaKey", required = false) String areaKey,
			@RequestParam(value = "hospitalKey", required = false) String hospitalKey) {

		SearchForm form = new SearchForm();
		UserInfoUtil.setUserInfo(us, ts, hService, form);

		// 默认情况下
		if (start == null || start.isEmpty() || start.equals("0")) {
			/*SearchFormUtil.setCurrentYearMonth(form);*/
			SearchFormUtil.setStartEndDayCurrentMonth(form);
		} else {
			form.setStartDate(start);
			form.setEndDate(end);
			form.setAreaCode(areaKey);
			form.setHospitalCode(hospitalKey);
			/*SearchFormUtil.convertMonth2WithDay(form);*/
			
			if (form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
				SearchFormUtil.convertMonth2WithDaySameMonth(form);
			} else {
				SearchFormUtil.convertMonth2WithDay(form);
			}			
		}
		System.err.println("drugIncome:"+form.getStartDate()+" "+form.getEndDate()+" "+form.getAreaCode()+" "+form.getHospitalCode());
		return dIncomeServices.stDifDrugCategoryTotalAmount(form);
	}

	@RequestMapping(value = "/jiangsuHealth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<JiangSuHealthStBi>> jiangSuHealth(SearchForm form) {
		System.out.println(
				"start jiangSuHealth:" + form.getStartDate() + " " + form.getEndDate() + " " + form.getHospitalId());
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		SearchFormUtil.setStartEndDayCurrentMonth(form);
		return jsService.search(form);
	}

	@RequestMapping(value = "/jiangsuHealthSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<JiangSuHealthStBi>> jiangSuHealthSearch(SearchForm form) {
		System.out.println(
				"start jiangSuHealth:" + form.getStartDate() + " " + form.getEndDate() + " " + form.getHospitalId());
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		
		if (form.getStartDate().equals(form.getEndDate())) { //说明只检查一个月的
			SearchFormUtil.convertMonth2WithDaySameMonth(form);
		} else {
			SearchFormUtil.convertMonth2WithDay(form);
		}
		
		return jsService.search(form);
	}
}
