package com.medimpact.medeasy.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.bean.statistic.UserStBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.form.UserForm;
import com.medimpact.medeasy.dao.CommonStDao;

@Mapper
public interface UserDao1 extends CommonStDao<UserStBi>{  
	public UserStBi getUserStsByUserName(@Param("username") String username);//有所属医院
	public UserStBi getUserRoleByUserName(@Param("username") String username);

	public UserStBi getUserStsByUserNameSysAdmin(@Param("username") String username);
	public UserStBi getUserStsByUserNameAreaAdmin(@Param("username") String username);
	public SecUser  getUserByUserName1(@Param("username") String username);
	public void updateSecUser(SecUser secUser);
	public void updateSecUserState(SecUser secUser);
	public void updateUserAndRole(UserForm form);  
	
	public List<UserStBi> getUsersAccordRoleUnit(SecUser secUser);  //显示的用户及考虑所属的行政区域，和级别两种
	public long getUsersAccordRoleUnitCount(SecUser secUser);
	
	
}