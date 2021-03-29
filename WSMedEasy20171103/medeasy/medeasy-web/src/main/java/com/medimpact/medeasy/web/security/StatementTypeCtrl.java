package com.medimpact.medeasy.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.medimpact.medeasy.common.bean.StatementTypebi;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.AccountHelper;
import com.medimpact.medeasy.service.security.StatementTypeService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/role")
public class StatementTypeCtrl {
	
	@Resource
	private UserService uSerivce;
	
	@Resource
	private TRoleService trService;	
	
	@Resource
	private StatementTypeService  stService;
	
	@Resource
	private HospitalService hService;
	

	@RequestMapping(value = "/getStmTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<StatementTypebi> getStmTypes() {	
		
		StatementTypebi form = new StatementTypebi();
		UserInfoUtil.setUserInfo(uSerivce, trService, hService,form);		
		return stService.getStmType(form.getOperateLevel()); 
	}
}
