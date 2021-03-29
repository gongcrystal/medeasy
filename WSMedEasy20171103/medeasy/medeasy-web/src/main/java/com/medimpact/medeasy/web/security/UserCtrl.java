package com.medimpact.medeasy.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.dev.EFBiffViewer;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.bean.security.UserRoleBi;
import com.medimpact.medeasy.common.bean.statistic.UserStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.form.UserForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.AccountHelper1;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

import net.sf.ehcache.pool.impl.FromLargestCacheOnDiskPoolEvictor;

@Controller
@RequestMapping("/user")
public class UserCtrl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private UserService uService;
	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;
	
	@Resource
	private TRoleService trService; 

	
	@RequestMapping(value = "/getUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<UserStBi>> getUsers(SecUser form) {
		String userName = new AccountHelper1().getUserName();
		
		TableRoleBi tRoleBi = trService.getbRoleByUserName(userName);
		SecUser secUser  =  uService.getUserByUserName(userName);
		int opLevel = tRoleBi.getOperateLevel();
		
		form.setOperateLevel(tRoleBi.getOperateLevel());
		if(opLevel==SYSCONSTANT.CON_AREA)
			form.setAreaCode(secUser.getAreaCode());
		if(opLevel==SYSCONSTANT.CON_HOSPITAL){
			form.setAreaCode(secUser.getAreaCode());
			form.setHospitalCode(secUser.getHospitalCode());
		}			
			
		return uService.getUsers(form);
	}
	
	@RequestMapping(value = "/getUsersAccordRoleUnit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<UserStBi>> getUsersAccordRoleUnit(SecUser form) {
		String userName = new AccountHelper1().getUserName();
		
		TableRoleBi tRoleBi = trService.getbRoleByUserName(userName);
		SecUser secUser  =  uService.getUserByUserName(userName);
		
		int opLevel = tRoleBi.getOperateLevel();
		
		form.setOperateLevel(tRoleBi.getOperateLevel());
		if(opLevel==SYSCONSTANT.CON_AREA)
			form.setAreaCode(secUser.getAreaCode());
		if(opLevel==SYSCONSTANT.CON_HOSPITAL){
			form.setAreaCode(secUser.getAreaCode());
			form.setHospitalCode(secUser.getHospitalCode());
		}			
		
		return uService.getUsersAccordRoleUnit(form); 
	}
	
	
	
	
	@RequestMapping(value = "/getLoginUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public TableRoleBi getLoginUser() {
		/*UserStBi uBi =  uService.getUserStByname(new AccountHelper1().getUserName());*/
		TableRoleBi uBi = trService.getbRoleByUserName(new AccountHelper1().getUserName());
		return uBi;
	}	
	

	@RequestMapping(value = "/getUsersBySearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<UserStBi>> getUsersBySearch(SearchForm form) {
			
		SearchFormUtil.checkAreaHospitalCodeNull(form);
		
		SecUser secUser = uService.getUserByUserName(new AccountHelper1().getUserName());		
		TableRoleBi tableRoleBi = trService.getbRoleByUserName(new AccountHelper1().getUserName());

		String areaCode= secUser.getAreaCode();
		BeanUtils.copyProperties(form, secUser);// secuser areacode被form覆盖为null
		secUser.setAreaCode(areaCode);
		//显式的检索条件
		secUser.setSearchHospitalCode(form.getHospitalCode());
				
		if(form.getDoctorName()!=null){
			if(form.getDoctorName().isEmpty()){
				secUser.setSearchDoctorName(null);
			}else {
				secUser.setSearchDoctorName(form.getDoctorName());
			}
		}else {
			secUser.setSearchDoctorName(null);
		}
		
		secUser.setSearchDoctorName(form.getDoctorName());
		
		secUser.setSearchDeptCode(form.getDeptCode());
		
		secUser.setOperateLevel(tableRoleBi.getOperateLevel());

		//隐式的检索条件: 查询操作的用户可以获得的用户数据
		/*secUser.setHospitalCode(uStBi.getHospitalCode());
		secUser.setAreaCode(uStBi.getAreaCode());
		secUser.setOperateLevel(uStBi.getOpLevel());
		secUser.setRows(form.getRows());		
		secUser.setRowStart(form.getRowStart());
		secUser.setRowSize(form.getRowStart());*/		
		
		return uService.getUsersBySearch(secUser);		
		
	}

	@RequestMapping(value = "/updateUserState", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DataModel updateUserState(@RequestParam("enabled") boolean enabled,
			@RequestParam("username") String username) {
		System.out.println("start updateUserState: enabled=" + enabled);
		SecUser secUser = new SecUser();
		secUser.setUsername(username);
		secUser.setEnabled(enabled);
		return uService.updateUserState(secUser);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DataModel addUser(@RequestBody UserForm form) {
		System.out.println("addUser enabled=" + form.getEnabled());
		if(form.getAreaCode()!=null && form.getAreaCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			form.setAreaCode(null);
			form.setAreaName(null);
		}
		SecUser secUser = new SecUser();
		BeanUtils.copyProperties(form, secUser);
		
		secUser.setEnabled(SYSCONSTANT.CONVERTUSERSTATE(form.getEnabled()));

		UserRoleBi uRoleBi = new UserRoleBi();
		BeanUtils.copyProperties(form, uRoleBi);
		if(secUser.getHospitalCode()!=null && secUser.getHospitalCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			secUser.setHospitalCode(null);
			secUser.setHospitalName("");
		}
		if(secUser.getDeptCode() !=null && secUser.getDeptCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			secUser.setDeptCode(null);
			secUser.setDeptName("");
		}
		
		
		
		System.err.println("addUser: "+secUser.getUsername()+" Area:"+ secUser.getAreaCode()+" Hospital:"+secUser.getHospitalCode()+ " dept:"+secUser.getDeptCode());
		return uService.addUser(secUser, uRoleBi);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DataModel updateUser(@RequestBody UserForm form) {
		System.err.println("updateUser:"+form.getUsername()+ "Area:"+ form.getAreaCode()+" "+form.getAreaName()+"Hospital:"+form.getHospitalCode()+"doctor:"+
				form.getDoctorCode()+" roleId="+form.getRoleName());	
		
		return uService.updateUser(form); 
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DataModel<List<SecUser>> vagueSearch(SecUser form) {
		System.out.println("vagueSearch: " + form.getDoctorName());
		if (form.getHospitalId().equals("#")) {
			form.setHospitalId(null);
		}
		if (form.getDeptId().equals("#")) {
			form.setDeptId(null);
		}
		return uService.vagueSearch(form);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<UserStBi> getUserByUserName() {
		UserStBi uStBi = uService.getUserStByname(new AccountHelper1().getUserName());
		if (uStBi == null) {
			return DataModel.getFailDataModalNotException(ERROCONSTANT.USER_NONEXIST_MSG);
		} else {
			return DataModel.getSucDataModal(uStBi);
		}
	}
	
	//查看个人基本信息
	@RequestMapping(value = "/getPDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<UserStBi> getUserRoleByUserName() {
		UserStBi uStBi = uService.getUserRoleByUserName(new AccountHelper1().getUserName());
		if (uStBi == null) {
			return DataModel.getFailDataModalNotException(ERROCONSTANT.USER_NONEXIST_MSG);
		} else {
			return DataModel.getSucDataModal(uStBi);
		}
	}

	@RequestMapping(value = "/updateSingSecUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel updateSingSecUser(@RequestBody SecUser secUser) {
		System.out.println("start updateSingSecUser " + secUser.getMobile() + " " + secUser.getPassword());

		System.out.println("enabled!" + secUser.isEnabled());

		secUser.setUsername(new AccountHelper1().getUserName());
		if (secUser.getPassword() != null) {
			secUser.setPassword(new BCryptPasswordEncoder().encode(secUser.getPassword()));
		}
		return uService.updateSecUser(secUser);
	}

	@RequestMapping(value = "/resetPwdToOriginal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel resetPwdToOriginal(@RequestParam("userName") String userName) {
		System.out.println("resetPwdToOriginal userName="+userName);
		SecUser secUser =  uService.getUserByUserName(userName);
		if(secUser==null ){
			return DataModel.getFailDataModalNotException(ERROCONSTANT.USER_NONEXIST_MSG);
		}
		secUser.setPassword(new BCryptPasswordEncoder().encode(SYSCONSTANT.DEFAULT_PASSWORD));
		return uService.updateSecUser(secUser);
	}

	@RequestMapping(value = "/verifyPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel verifyCurrentPasswd(@RequestParam("password") String password) {
		System.err.println("password="+password);
		SecUser uStBi = uService.getUserByUserName1(new AccountHelper1().getUserName());

		if (new BCryptPasswordEncoder().matches(password, uStBi.getPassword())) {
			
			return DataModel.getNotDataSucDataModal();
		} else {

			return DataModel.getFailDataModalNotException(ERROCONSTANT.INCORRECT_PASSWORD);
		}
	}

}
