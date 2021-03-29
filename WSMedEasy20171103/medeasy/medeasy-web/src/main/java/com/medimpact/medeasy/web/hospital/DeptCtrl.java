package com.medimpact.medeasy.web.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.medimpact.medeasy.common.bean.DeptBi;
import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.service.hospital.DeptService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.AccountHelper1;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/basic")
public class DeptCtrl {	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Resource
	private DeptService deptService;
	
	@Resource
	private UserService uService;
	
	@Resource
	private HospitalService hService;
	
	
	@RequestMapping(value = "/getDepts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DeptBi>  getDepts () {	
		return deptService.getDepts();
	}
	

	@RequestMapping(value = "/getDeptsByRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DeptBi>  getDeptsByRole (SearchForm form) {	
		return deptService.getDepts();
	}
	
	
	@RequestMapping(value = "/getDeptsByHpCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DeptBi>  getDeptsByHpCode (@RequestParam(value = "hospitalCode", required = true) String hospitalCode) {	
		System.out.println("getDeptsByHpCode : hospitalCode="+hospitalCode);
		return deptService.getDeptsByHospitalCode(hospitalCode);
	}
	
	@RequestMapping(value = "/getDeptsByUserName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DeptBi>  getDeptsByUserName () {
		
		List<DeptBi> depts=new ArrayList<>();
		
		String username = new AccountHelper1().getUserName();
		
		SecUser secUser  =  uService.getUserByUserName(username);	
		
		if(secUser!=null && secUser.getHospitalCode()!=null)
			depts = deptService.getDeptsByHospitalCode(secUser.getHospitalCode());
		
		return depts;
	}
	
}
