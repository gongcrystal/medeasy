package com.medimpact.medeasy.common.constant;

public class SYSCONSTANT {
	
	//用户账户状态
	 public static final String USERSTATE_NORMAL_CH="正常";	 
	 public static final String USERSTATE_SUSPEND_CH="锁定";
	 
	 public static final String DEFAULT_PASSWORD="654321";
	 
	 public  static  boolean CONVERTUSERSTATE(String userState){	
		 
		 if(userState.equals("正常")|| userState.equals("1"))
			 return true;
		 else
			 return false;		 
	 }
	 
	//检索数据的级别	
	 public static final int CON_SYS = 1;
	 public static final int CON_AREA = 2;
	 public static final int CON_HOSPITAL = 3;
	 public static final int CON_DEPT = 4;
	 public static final int CON_HOSPITAL_DEPT = 5;
	 public static final int CON_USER = 6;
	 public static final int CON_OTHER = 7;
	 
	 public static final String OPTION_VALUE_NOTHING = "#"; //用于option text 是请选择的情况
	 
	 public static final String STRING_ZERO = "0";
	 public static final Integer INT_ZERO = 0;
	 
	 public static final String FIRSTPAGE_NAME = "首页";
	 public static final Integer FIRSTPAGE_ID = 17;
	 
	 
	 //module_type
	 public static final int MODULE_TYPE_MENU =0;
	 public static final int MODULE_TYPE_FIRSTPAGE = 5; //首页
	 
	 
	 // 用于char Y、N
	 public static final char CHAR_Y = 'Y';
	 public static final char CHAR_N = 'N';
	 
	 public static final String IS_IV = "ZSJ";  //注射剂
	 public static final String IS_HERBAL_IV = "ZYZSJ";  //中药注射剂  	 
	 
	 public static final String ANAONYMOUS_USER = "anonymousUser"; // 未登录用户
	 
	 //反统的检索条件	 
	 public static final  String COND_DR= "getPhysicianKey";  // 1
	 public static final  String COND_DRUG = "getDrug"; // 目前没有按医生统计，按药品检索，此值待定, 2 
	 public static final  String COND_PATIENTTYPE = "getPatientType";  // for test 3
	 public static final  String[] CONDS={COND_PATIENTTYPE,COND_DRUG,COND_DR}; 
	 
	 
	 //OUTPATIENT_URGENT 门急诊
	 public static final  String OUTPATIENT_URGENT="OUTPATIENT_URGENT";
	 public static final  String OUTPATIENT_URGENT_NUM="4";
	 public static final  String  OUTPATIENT_URGENT_CHS = "门急诊";
	 
	 public static final  String OUTPATIENT="OUTPATIENT";
	 public static final  String OUTPATIENT_NUM="1";
	 public static final  String OUTPATIENT_CHS="门诊";
	 
	 
	 
	 public static final  String URGENT_CARE="URGENT_CARE";  
	 public static final  String URGENT_CARE_NUM="2";
	 public static final  String URGENT_CARE_CHS="急诊";
	 
	 
	 //配置权限后， 首页自动添加
	 public static final  String HOME_PAGE_STR="首页";
	 public static final  String HOME_PAGE_NUM="17";
	 
}