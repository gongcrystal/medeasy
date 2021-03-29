package com.medimpact.medeasy.common.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.LogForm;
import com.medimpact.medeasy.common.form.SearchForm;

public class SearchFormUtil {

	private  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	//优先检索快速， 然后是药品名称； 最后是分类
	public static void controlDrugSearchPrio(SearchForm form){
		if(form.gethLDrugCategory()!=null && !form.gethLDrugCategory().isEmpty()){
			form.setCheckedNodesStr(null);
			form.setLocalDrugId(null);
			System.err.println("检索快速");
		}
		
		if((form.gethLDrugCategory()==null || form.gethLDrugCategory().isEmpty()) && form.getLocalDrugId() !=null && !form.getLocalDrugId().isEmpty() ){
			form.setCheckedNodesStr(null);
			System.err.println("名称检索");
		}	
		
	}
	
	/*hlDrugCategory: 快速分类
	checkedNodesStr: 药品分类*/
	public static boolean isDrugRelatedSearch(SearchForm form) {
		boolean result = false;  // result=true: 表示有檢測于藥品相關的
		if((form.gethLDrugCategory()!=null && !form.gethLDrugCategory().isEmpty())
				|| (form.getCheckedNodesStr()!=null&& !form.getCheckedNodesStr().isEmpty())
				||(form.getLocalDrugId()!=null && !form.getLocalDrugId().isEmpty())){
			result = true;
			System.err.println("isDrugRelatedSearch!");
		}
		return result;
	}
	//除了药品还要有问题代码
	public static boolean isDrugRelatedSearch1(SearchForm form) {
		boolean result = false;  // result=true: 表示有檢測于藥品相關的
		if(isDrugRelatedSearch(form)|| (form.getIrriteId()!=null && !form.getIrriteId().isEmpty()))
			result = true;
		return result;
	}

	// 按当前月， for druguse , 主要用于特殊情况:外面检索用到日，默认有要求到月的
	public static void setCurrentYearMonth(SearchForm form) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		form.setYear(year);
		form.setMonth(month);
		/* System.err.println("year: "+year+" month="+month); */
	}

	// 不是检查当前月的， 而是检查一个月的， 比如说2017.9月的
	public static void convertMonth2WithDaySameMonth(SearchForm form) {
		String start = form.getStartDate() + "-01";
		LocalDate startLD = LocalDate.parse(start, formatter);
		LocalDate lastDay = startLD.with(TemporalAdjusters.lastDayOfMonth());
		form.setStartDate(String.valueOf(startLD));
		form.setEndDate(String.valueOf(lastDay));
	}

	// 界面是月， 但是按照full_day来检索的， 需要转化一下 - startDate:2018-01 /endDate:2018-02
	public static void convertMonth2WithDay(SearchForm form) {
		String start = form.getStartDate() + "-01";
		String end = form.getEndDate() + "-01";
		LocalDate startLD = LocalDate.parse(start, formatter);
		LocalDate endLD = LocalDate.parse(end, formatter);
		form.setStartDate(String.valueOf(startLD));
		form.setEndDate(String.valueOf(endLD.with(TemporalAdjusters.lastDayOfMonth())));
	}

	public static void setStartEndDayCurrentMonth(SearchForm form) {
		LocalDate now = LocalDate.now(); // 2015-11-23
		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
		LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
		form.setStartDate(String.valueOf(firstDay));
		form.setEndDate(String.valueOf(lastDay));
		System.out.println("firstDay: " + firstDay + " lastDay:" + lastDay);

	}

	// 默认年
	public static void setCurrentYear(SearchForm form) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String year = String.valueOf(localDate.getYear());
		form.setStartYear(year);
		form.setEndYear(year);
		form.setStartMon(String.valueOf(1));
		form.setEndMon(String.valueOf(12));
	}

	public static void preHandleSearchForm(SearchForm form) {
/*System.err.println("1.preHandleSearchForm:"+form.getCheckedNodesStr());*/
		if (form.getCheckedNodesStr() != null) {
			String[] strings = form.getCheckedNodesStr().split("-");
			form.setDrugCLi(Arrays.asList(strings));
		}


		if (form.gethLDrugCategory() != null && form.gethLDrugCategory().equals(SYSCONSTANT.IS_IV)) {
			form.setIsIV(SYSCONSTANT.CHAR_Y);
		}

		/*
		 * if(form.gethLDrugCategory() !=null &&
		 * form.gethLDrugCategory().equals(SYSCONSTANT.IS_HERBAL_IV)){
		 * form.setIsHervalIv(SYSCONSTANT.CHAR_Y); }
		 */

	}

	public static void preHandleLogForm(LogForm form) {
		if (form.getNameCh() != null && form.getNameCh().equals("")) {
			form.setNameCh(null);
		}

		if (form.getUsername() != null && form.getUsername().equals("")) {
			form.setUsername(null);
		}

		if (form.getStartDate() != null && form.getStartDate().equals("")) {
			form.setStartDate(null);
		}

		if (form.getEndDate() != null && form.getEndDate().equals("")) {
			form.setEndDate(null);
		}

	}

	public static void searchDatePreHandle(SearchForm form) {

		if (form.getStartDate() != null) {
			String[] str = form.getStartDate().split("-");
			form.setStartYear(str[0]);
			form.setStartMon(str[1]);
		}

		if (form.getEndDate() != null) {
			String[] str = form.getEndDate().split("-");
			form.setEndYear(str[0]);
			form.setEndMon(str[1]);
		}
	}

	public static void setNull(String str) {
		if (str != null && str.equals(SYSCONSTANT.OPTION_VALUE_NOTHING))
			str = null;
	}

	public static void checkAreaHospitalCodeNull(SearchForm form) {

		if (form == null)
			return;

		if (form.getHospitalCode() != null && form.getHospitalCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			form.setHospitalCode(null);
		}

		if (form.getAreaCode() != null && form.getAreaCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			form.setAreaCode(null);
		}

		if (form.getDeptCode() != null && form.getDeptCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			form.setDeptCode(null);
		}

		if (form.getPatientType() != null && form.getPatientType().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)) {
			form.setPatientType(null);
		}
		if (form.getStartDate() != null && form.getStartDate().equals("")) {
			form.setStartDate(null);
		}

		if (form.getEndDate() != null && form.getEndDate().equals("")) {
			form.setEndDate(null);
		}

	}

	public static String mapPTCode2Num(String code) {

		switch (code) {
		case SYSCONSTANT.URGENT_CARE:
			return SYSCONSTANT.URGENT_CARE_NUM;
		case SYSCONSTANT.OUTPATIENT:
			return SYSCONSTANT.OUTPATIENT_NUM;
		default:
			return SYSCONSTANT.OUTPATIENT_URGENT_NUM;
		}
	}

	public static String mapPTNum2Code(String num) {

		switch (num) {
		case SYSCONSTANT.URGENT_CARE_NUM:
			return SYSCONSTANT.URGENT_CARE;
		case SYSCONSTANT.OUTPATIENT_NUM:
			return SYSCONSTANT.OUTPATIENT;
		default:
			return SYSCONSTANT.OUTPATIENT_URGENT;
		}
	}
}
