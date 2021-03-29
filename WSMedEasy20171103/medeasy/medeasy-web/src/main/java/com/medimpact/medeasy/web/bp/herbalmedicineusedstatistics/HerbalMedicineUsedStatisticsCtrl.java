package com.medimpact.medeasy.web.bp.herbalmedicineusedstatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.HerbalUseBi;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.service.bp.herbalmedicineusedstatistics.HerbalMedicineUsedStatisticsService;
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
public class HerbalMedicineUsedStatisticsCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private HerbalMedicineUsedStatisticsService herbalService;
    @Resource
	private UserService us;	
	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;

    @RequestMapping(value = "/herbUse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<HerbalUseStBi>> getHerbalUse(SearchForm form) {
    	System.out.println("Start getHerbalUse!"+form.getHospitalId());
    	UserInfoUtil.setUserInfo(us, ts, hService,form);
    	SearchFormUtil.searchDatePreHandle(form);
    	SearchFormUtil.setCurrentYear(form); //默认显示当前年
    	return herbalService.getHerbalMedicineUsedStatisticsData(form);        
    }
    
    @RequestMapping(value = "/herbUseSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<HerbalUseStBi>> herbUseSearch(SearchForm form) {
    	System.out.println("Start getHerbalUse!"+form.getHospitalId());
    	UserInfoUtil.setUserInfo(us, ts, hService,form);
    	SearchFormUtil.searchDatePreHandle(form);
    	/*SearchFormUtil.setCurrentYear(form); *///默认显示当前年
    	return herbalService.getHerbalMedicineUsedStatisticsData(form);        
    }
}
