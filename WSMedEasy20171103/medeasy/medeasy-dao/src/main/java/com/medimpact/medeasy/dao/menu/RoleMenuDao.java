package com.medimpact.medeasy.dao.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.dao.CommonDao;
import com.medimpact.medeasy.dao.CommonStDao;

public interface RoleMenuDao extends CommonDao<RoleMenuBi>{	
	
	public void insertBatch(@Param("rolelist") List<RoleMenuBi> list);
	public List<RoleMenuBi> getRoleMenuByName(String roleName); 	
	public List<RoleMenuBi>  getRoleMenus(SearchForm form);
	public void delByName(@Param("roleName") String roleName);	
	public List<String> getRoleNamesByUrl(@Param("url") String url);
	
} 
