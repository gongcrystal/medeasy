package com.medimpact.medeasy.web.bp.basedrugusedworksheet;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.BaseDrugSheetBi;
import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedWorkSheetBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.service.bp.basedrugusedworksheet.BaseDrugUsedWorkSheetService;
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
import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class BaseDrugUsedWorkSheetCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private BaseDrugUsedWorkSheetService wsService;
    @Resource
   	private UserService us;	
   	@Resource
   	private TRoleService ts;
   	@Resource
   	private HospitalService hService;   	
   	
   
    @RequestMapping(value = "/baseDrugWorksheet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<BaseDrugSheetBi>> getBaseDrugUsedWorkSheet(SearchForm form) {
    	System.out.println("Start getBaseDrugUsedWorkSheet!"+form.getHospitalId());    
    	UserInfoUtil.setUserInfo(us, ts, hService,form); 
    	SearchFormUtil.searchDatePreHandle(form);
    	SearchFormUtil.setCurrentYear(form); //默认显示当前年
       return wsService.getBaseDrugUsedWorkSheetData(form); 
    }
    
    @RequestMapping(value = "/baseDrugWorksheetSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<BaseDrugSheetBi>> getBaseDrugUsedWorkSheetSearch(SearchForm form) {
    	System.out.println("Start getBaseDrugUsedWorkSheet!"+form.getHospitalId());    
    	UserInfoUtil.setUserInfo(us, ts, hService,form); 
    	SearchFormUtil.searchDatePreHandle(form);
    	/*SearchFormUtil.setCurrentYear(form);*/ //默认显示当前年
       return wsService.getBaseDrugUsedWorkSheetData(form); 
    }
}
