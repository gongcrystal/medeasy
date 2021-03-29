package com.medimpact.medeasy.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.dao.CommonDao;

public interface UserDao extends CommonDao<SecUser>{
	public SecUser getUserByname(@Param("username") String username);
	public List<SecUser> getUsers(SecUser secUser);
	public List<SecUser> vagueSearch(SecUser secUser);
	
}
