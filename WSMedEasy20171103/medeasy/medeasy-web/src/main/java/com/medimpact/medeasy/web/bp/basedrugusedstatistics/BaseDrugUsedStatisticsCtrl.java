package com.medimpact.medeasy.web.bp.basedrugusedstatistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedStatisticsBi;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.service.bp.basedrugusedstatistics.BaseDrugUsedStatisticsService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class BaseDrugUsedStatisticsCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    private  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Resource
    private BaseDrugUsedStatisticsService baseDrugUsedStatisticsService;
    @Resource
  	private UserService us;	
  	@Resource
  	private TRoleService ts;
  	@Resource
  	private HospitalService hService;

    @RequestMapping(value = "/getBasedrugusedstatistics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<BaseDrugUsedStatisticsBi>> getAbxUsedStatistics(BaseDrugUsedStatisticsBi form) {
    	try {
        	
        	UserInfoUtil.setUserInfo(us, ts, hService, form);
        	
        	if(form.getStartDate()==null || form.getStartDate().isEmpty()){
        		
        		LocalDate now = LocalDate.now(); // 2015-11-23
        		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
        		LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        		
        		form.setStartDate(String.valueOf(firstDay));
        		form.setEndDate(String.valueOf(lastDay));
        	}else{       		
        		
        		String start = form.getStartDate() + "-01";
        		String end =  form.getEndDate()+ "-01";;
        		LocalDate startLD = LocalDate.parse(start, formatter);
        		LocalDate endLD = LocalDate.parse(end, formatter);
        		
        		LocalDate lastDay = endLD.with(TemporalAdjusters.lastDayOfMonth());
        		form.setStartDate(String.valueOf(startLD));
        		form.setEndDate(String.valueOf(lastDay));        		
        		
        	}
        	
    		
            return baseDrugUsedStatisticsService.getBaseDrugUsedStatisticsData(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }
}
