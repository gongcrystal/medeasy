package com.medimpact.medeasy.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.DtOpLevelBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.service.security.DtOpLevelService;

@Controller
@RequestMapping("/role")
public class DtOpLevelCtrl {
	
	@Resource
	private DtOpLevelService dtOpLevelService;
	
	@RequestMapping(value = "/getDtOpLevels", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DtOpLevelBi> getDtOpLevels(@RequestBody DtOpLevelBi form)  {
		System.out.println("start getDtOpLevels");
		return dtOpLevelService.getDtOpLevels();
	}
	
	
	@RequestMapping(value = "/getDtOpLevels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DtOpLevelBi> getDtOpLevelsGet()  {
		System.out.println("start getDtOpLevels");
		return dtOpLevelService.getDtOpLevels();
	}
}
