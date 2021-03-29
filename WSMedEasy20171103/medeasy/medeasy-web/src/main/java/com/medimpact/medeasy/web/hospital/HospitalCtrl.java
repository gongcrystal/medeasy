package com.medimpact.medeasy.web.hospital;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/basic")
public class HospitalCtrl {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	private HospitalService hService;	
	
	@Resource
	private UserService uSerivce;
	
	@Resource
	private TRoleService trService;	
	
	
	@RequestMapping(value = "/getHospitals", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<HospitalBi>  getHospitals () {		
		return hService.getHospitals();
	}
	
	
	@RequestMapping(value = "/getHospitalsByRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<HospitalBi>  getHospitalsByRole () {	
		HospitalBi form = new HospitalBi();
		UserInfoUtil.setUserInfo(uSerivce, trService,hService, form);	
		return hService.getHospitalsByRole(form);
	}
	
	
	@RequestMapping(value = "/getHospitalsByArea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<HospitalBi>  getHospitalsByAreaCode (@RequestParam(value = "areaCode", required = true) String areaCode ) {	
		System.out.println("getHospitalsByAreaCode areaCode="+areaCode);
		return hService.getHospitalsByAreaCode(areaCode);
	}
	
	
}