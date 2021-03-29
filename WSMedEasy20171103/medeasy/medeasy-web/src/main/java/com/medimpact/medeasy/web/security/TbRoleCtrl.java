package com.medimpact.medeasy.web.security;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.service.security.TRoleService;

@Controller
@RequestMapping("/role")
public class TbRoleCtrl {
	
	@Resource
	private   TRoleService trService; 
	
	@RequestMapping(value = "/getTbRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<TableRoleBi> getTbRoleByName(@RequestParam(value = "roleName", required = true) String roleName) {
		System.out.println("getTbRoleByName!");
		TableRoleBi trBi = null; 
		try{
			trBi = trService.getbRoleByRoleName(roleName);
			return DataModel.getSucDataModal(trBi);
		}catch (BizException e) {
			// TODO: handle exception
			return DataModel.getFailDataModalNotException(ERROCONSTANT.GENERAL_RETURN_NULL);
		}		
	}

}
