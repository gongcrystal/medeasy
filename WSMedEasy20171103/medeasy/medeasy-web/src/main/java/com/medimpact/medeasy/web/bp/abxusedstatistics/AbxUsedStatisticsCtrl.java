package com.medimpact.medeasy.web.bp.abxusedstatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.AbxUsedStatisticsBi;
import com.medimpact.medeasy.service.bp.abxusedstatistics.AbxUsedStatisticsService;
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
public class AbxUsedStatisticsCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxUsedStatisticsService abxUsedStatisticsService;
    @Resource
  	private UserService us;	
  	@Resource
  	private TRoleService ts;
  	@Resource
  	private HospitalService hService;
  	
    @RequestMapping(value = "/abxUsedStatisticsData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<AbxUsedStatisticsBi>> getAbxUsedStatistics(AbxUsedStatisticsBi form) {
        try {
        	UserInfoUtil.setUserInfo(us, ts, hService, form);
            return abxUsedStatisticsService.getAbxUsedStatisticsData(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }
}
