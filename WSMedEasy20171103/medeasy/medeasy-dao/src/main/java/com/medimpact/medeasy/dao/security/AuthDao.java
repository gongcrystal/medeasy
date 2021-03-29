package com.medimpact.medeasy.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.security.SecAuth;


public interface AuthDao {	
	List<String> getRoleNamesByUrl(@Param("url") String url);

}
