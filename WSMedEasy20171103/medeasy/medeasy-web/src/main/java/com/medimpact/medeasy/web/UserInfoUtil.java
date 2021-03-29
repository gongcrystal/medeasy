package com.medimpact.medeasy.web;

import java.security.Principal;
import java.text.Normalizer.Form;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.medimpact.medeasy.common.RequestParameter;
import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.AccountHelper;
import com.medimpact.medeasy.service.security.AccountHelper1;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;

public class UserInfoUtil {	

	//设置用户的权限和数据操作权限
	/**
	 * @param uService
	 * @param tRService
	 * @param hService
	 * @param t
	 */
	public static <T extends RequestParameter> void  setUserInfo(UserService uService, TRoleService tRService, HospitalService hService, T t){
		/*SecUser secUser  =  uService.getUserByUserName(AccountHelper.getUserName());*/
		String userName = new AccountHelper1().getUserName();
		System.out.println("the current userName : "+userName);	
		
		
		if(userName.equals(SYSCONSTANT.ANAONYMOUS_USER)){
			throw new BizException(ERROCONSTANT.NONLOGIN, ERROCONSTANT.NONLOGIN_MSG);
		}
		SecUser secUser  =  uService.getUserByUserName(userName);
	
		
		if(secUser==null){			
			throw new BizException(ERROCONSTANT.USER_NONEXIST, ERROCONSTANT.USER_NONEXIST_MSG);
		}
		
		/*System.out.println("setUserInfo: areaCode="+secUser.getAreaCode()+" hospitalcode: "+secUser.getHospitalCode());*/
		
		TableRoleBi tBi = tRService.getbRoleByRoleName(new AccountHelper1().getRoles());
		
		if(tBi==null){
			throw new BizException(ERROCONSTANT.TABLEROLE_NONEXIST,ERROCONSTANT.TABLEROLE_NONEXIST_MSG);
		}
		/*System.err.println("tBi.getOperateLevel()="+tBi.getOperateLevel()+"."+secUser.getHospitalCode()+".");*/
		
		t.setOperateLevel(tBi.getOperateLevel());
		
		
		if(tBi.getOperateLevel()!=SYSCONSTANT.CON_SYS && tBi.getOperateLevel()!=SYSCONSTANT.CON_AREA){
			/*HospitalBi hBi = hService.getHospitalById(Integer.valueOf(secUser.getHospitalId()));*/
			
			//现在改为根据code来检索 2017.12.26			
			HospitalBi hBi = hService.getHospitalByCode(secUser.getHospitalCode());
		
			/*if(hBi ==null){
				throw new BizException(ERROCONSTANT.HOSPITAL_NONEXIST, ERROCONSTANT.HOSPITAL_NONEXIST_MSG);
			}*/
			secUser.setHospitalBi(hBi);					
		}		
		t.setSecUser(secUser);
		t.setRoleName(AccountHelper.getRoles());
		
	}
}
