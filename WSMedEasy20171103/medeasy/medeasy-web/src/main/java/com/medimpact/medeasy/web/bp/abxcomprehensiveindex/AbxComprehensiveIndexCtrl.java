package com.medimpact.medeasy.web.bp.abxcomprehensiveindex;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.bean.bp.AbxComprehensiveIndexBi;
import com.medimpact.medeasy.service.bp.abxcomprehensiveindex.AbxComprehensiveIndexService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class AbxComprehensiveIndexCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxComprehensiveIndexService abxComprehensiveIndexService;
    @Resource
  	private UserService us;	
  	@Resource
  	private TRoleService ts;
  	@Resource
  	private HospitalService hService;

    @RequestMapping(value = "/abxComprehensiveIndexData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AbxComprehensiveIndexBi getAbxComprehensiveIndex(@RequestBody AbxComprehensiveIndexBi form) {
        try {
        	UserInfoUtil.setUserInfo(us, ts, hService, form);
            return abxComprehensiveIndexService.getAbxComprehensiveIndexData(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }
}
