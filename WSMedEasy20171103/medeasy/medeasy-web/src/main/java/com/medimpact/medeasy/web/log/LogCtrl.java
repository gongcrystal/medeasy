package com.medimpact.medeasy.web.log;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.LogBi;
import com.medimpact.medeasy.common.form.LogForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.service.log.LogService;

@Controller
@RequestMapping("/log")
public class LogCtrl {
	
	@Resource
	private LogService lService;
	
	@RequestMapping(value = "/getLogs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<LogBi>> getLogs(LogForm form) {
		System.out.println("getCondDr:"+form.getCond()+"getNameCh:"+form.getNameCh()+"getUsername:"+form.getUsername()+ "getStartDate:"+ form.getStartDate() );
				
		return lService.list(form); 
	}

}
