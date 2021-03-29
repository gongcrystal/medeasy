package com.medimpact.medeasy.dao.security;

import java.util.Map;

import javax.swing.text.TabExpander;

import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.dao.CommonDao;

public interface TbRoleDao extends CommonDao<TableRoleBi>{		
	public TableRoleBi getbRoleByCond(TableRoleBi tRoleBi);	
	public void updateOperateLevel(TableRoleBi tRoleBi); 	
	public TableRoleBi getbRoleByUserName(String username);
}
