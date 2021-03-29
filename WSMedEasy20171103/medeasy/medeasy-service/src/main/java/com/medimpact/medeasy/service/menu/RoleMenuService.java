package com.medimpact.medeasy.service.menu;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.RoleMenuForm;

public interface RoleMenuService {
	
	public void addRoleMenu(RoleMenuForm form) throws BizException;
	public DataModel<List<RoleMenuBi>> getRoleMenuByName(String roleName)  throws BizException;	
	public DataModel updateRoleMenuByName(RoleMenuForm form)  throws BizException;
}
