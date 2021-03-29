package com.medimpact.medeasy.dao.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.dao.CommonDao;

public interface MenuDao extends CommonDao<MenuBi>{
	public List<MenuBi> listSubMenuByParentId(Integer id);
	public List<MenuBi> getMenusByRoleName(@Param("roleName") String roleName); 
	public int getMaxSubByRoleName(RoleMenuBi roleMenuBi);  
	public List<MenuBi> getSubMenuByName(RoleMenuBi roleMenuBi); 
	
}
