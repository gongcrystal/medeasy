package com.medimpact.medeasy.web.irritate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.bean.IrritationItemBi;
import com.medimpact.medeasy.common.bean.PatientTypeBi;
import com.medimpact.medeasy.service.irritate.IrritateItemService;

@Controller
@RequestMapping("/basic")
public class IrritateItemCtrl {
	@Resource
	private IrritateItemService iService;
	
	@RequestMapping(value = "/getIrritate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public List<IrritationItemBi> getIrritate() {		
		
		return iService.list();
	}
}
