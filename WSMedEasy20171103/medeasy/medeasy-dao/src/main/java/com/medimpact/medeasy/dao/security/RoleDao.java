package com.medimpact.medeasy.dao.security;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.dao.CommonDao;

@Mapper
public interface RoleDao extends CommonDao<SecRole>{
	public List<SecRole> getRolesByUserId(@Param("userId") int userId);
	public List<SecRole> getRoles();
	public SecRole getSecRoleByName(@Param("roleName") String roleName);
	public List<SecRole> getRolesByOpLevel(@Param("opLevel") int opLevel);
	
}
