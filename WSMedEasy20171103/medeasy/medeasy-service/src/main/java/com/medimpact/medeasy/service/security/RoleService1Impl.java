package com.medimpact.medeasy.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleAuthorityBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.dao.menu.RoleMenuDao1;

@Service
public class RoleService1Impl implements RoleService1{

	@Resource
	RoleMenuDao1  rmDao1;
	@Override
	public DataModel<List<RoleAuthorityBi>> getAuthorities(SearchForm form) {
		// TODO Auto-generated method stub
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		switch (opLevel) { // 只有平台管理员有权定义角色，其他人无权定义

		case SYSCONSTANT.CON_SYS:
			return DataModel.getSucDataModal(rmDao1.listBySys(form));			
		
		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
	}

}
