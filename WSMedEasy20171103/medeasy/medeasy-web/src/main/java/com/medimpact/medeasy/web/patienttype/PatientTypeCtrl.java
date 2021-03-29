package com.medimpact.medeasy.web.patienttype;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.bean.DrugUseBi;
import com.medimpact.medeasy.common.bean.PatientTypeBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.service.basicdt.patienttype.PatientTypeService;
import com.medimpact.medeasy.web.UserInfoUtil;

@Controller
@RequestMapping("/basic")
public class PatientTypeCtrl {
	
	@Resource
	private PatientTypeService  ptService;
	
	@RequestMapping(value = "/getPatientTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public List<PatientTypeBi> getPatientTypes() {		
		
		return ptService.listPatientTypes();
	}

}
