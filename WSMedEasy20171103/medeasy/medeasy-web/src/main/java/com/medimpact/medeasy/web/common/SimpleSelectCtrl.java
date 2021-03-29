package com.medimpact.medeasy.web.common;

import java.util.List;

import javax.annotation.Resource;

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

import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.service.common.SimpleSelectService;

/**
 * Created by matrixliu on 2017/11/30.
 */
@Controller
@RequestMapping("/common")
public class SimpleSelectCtrl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private SimpleSelectService simpleSelectService;
    @Resource
    private UserService us;
    @Resource
    private TRoleService ts;
    @Resource
    private HospitalService hService;

    @RequestMapping(value = "/simpleSelect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<SimpleSelectBi> getUsers(SimpleSelectBi form) {
        UserInfoUtil.setUserInfo(us, ts, hService,form);
        return simpleSelectService.getSimpleSelectData(form);
    }
}
