package com.medimpact.medeasy.web.bp.rdustatistics;

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
import com.medimpact.medeasy.common.bean.bp.RduStatisticsBi;
import com.medimpact.medeasy.service.bp.rdustatistics.RduStatisticsService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class RduStatisticsCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private RduStatisticsService rduStatisticsService;
    @Resource
  	private UserService us;	
  	@Resource
  	private TRoleService ts;
  	@Resource
  	private HospitalService hService;
  	
    @RequestMapping(value = "/rduStatisticsData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public DataModel<List<RduStatisticsBi>> getRduStatistics(RduStatisticsBi form) {
        try {
        	UserInfoUtil.setUserInfo(us, ts, hService, form);
            return rduStatisticsService.getRduStatisticsData(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }
}
