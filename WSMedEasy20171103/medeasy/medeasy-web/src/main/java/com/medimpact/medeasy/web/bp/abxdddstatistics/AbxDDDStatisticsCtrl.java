package com.medimpact.medeasy.web.bp.abxdddstatistics;

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
import com.medimpact.medeasy.common.bean.bp.AbxDDDStatisticsBi;
import com.medimpact.medeasy.service.bp.abxdddstatistics.AbxDDDStatisticsService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class AbxDDDStatisticsCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxDDDStatisticsService abxDDDStatisticsService;
    @Resource
  	private UserService us;	
  	@Resource
  	private TRoleService ts;
  	@Resource
  	private HospitalService hService;
    
    @RequestMapping(value = "/abxDDDstatisticsData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<AbxDDDStatisticsBi>> getAbxDDDStatistics(AbxDDDStatisticsBi form) {
        try {
        	UserInfoUtil.setUserInfo(us, ts, hService, form);
            return abxDDDStatisticsService.getAbxDDDStatisticsDataAntiSt(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }
}
