package com.medimpact.medeasy.service.security;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.bean.security.UserRoleBi;
import com.medimpact.medeasy.common.bean.statistic.UserStBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.form.UserForm;

public interface UserService {
	
	public DataModel<List<UserStBi>> getUsers(SecUser secUser); 	
	
	public DataModel<List<UserStBi>> getUsersBySearch(SecUser secUser); 
	
	public DataModel<List<SecUser>> vagueSearch(SecUser secUser);
	public  DataModel updateUser(UserForm uForm) throws BizException;
	public DataModel updateUserState (SecUser sUser) throws BizException;
	/*@Cacheable(value=""myCache"",  key = "'SECUSER'+#args[0]")*/
	public SecUser getUserByUserName(String userName) throws BizException;
	public SecUser getUserByUserName1(String userName) throws BizException;
	public DataModel addUser(SecUser secUser,UserRoleBi uRoleBi); 
	public UserStBi  getUserStByname(String username);	
	public UserStBi  getUserRoleByUserName(String username); // 不通过hospital.areacode
	public DataModel updateSecUser(SecUser sUser);  
	
	/*sysAdmin – 全部用户
	areaAdmin – 本Area， 并且不能是Role_admin的
	hospitalAdmin  - 本医院的，并且不能是AreaAdmin, hospitalAdmin*/ 
	public DataModel<List<UserStBi>> getUsersAccordRoleUnit(SecUser secUser); 
	
}
