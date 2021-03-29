package com.medimpact.medeasy.web.dist;

import java.util.ArrayList;
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
import com.medimpact.medeasy.common.bean.DeptBi;
import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.common.bean.StatementTypebi;
import com.medimpact.medeasy.common.bean.statistic.UserStBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.dao.security.UserDao1;
import com.medimpact.medeasy.service.dist.DistService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.AccountHelper1;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/basic")
public class DistCtrl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private DistService dService;
	
	@Resource
	private UserService uSerivce;
	
	@Resource
	private UserDao1 u1Dao;
	
	@Resource
	private TRoleService trService;	
	
	@Resource
	private HospitalService hService;
	

	@RequestMapping(value = "/getDists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DistBi> getDists() {
		DistBi form = new DistBi();
		UserInfoUtil.setUserInfo(uSerivce, trService,hService, form);
		List<DistBi> list = dService.listDists(form);
				
		if( form.getOperateLevel()==SYSCONSTANT.CON_HOSPITAL && (list==null || list.size()==0) ){
			
			UserStBi userStBi = u1Dao.getUserStsByUserName(new AccountHelper1().getUserName());
			if(userStBi!=null && userStBi.getAreaCode()!=null){
				DistBi bi = dService.getDistByCode(userStBi.getAreaCode());
				list.add(bi);
			}
		}			
		return list;
	}	
}
