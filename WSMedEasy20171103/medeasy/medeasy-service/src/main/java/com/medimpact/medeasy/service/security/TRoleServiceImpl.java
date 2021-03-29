package com.medimpact.medeasy.service.security;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.security.TbRoleDao;

@Service
public class TRoleServiceImpl implements TRoleService{

	@Resource
	private TbRoleDao trDao;
	
	@Override
	public TableRoleBi getbRoleByRoleName(String rolName) throws BizException {
		// TODO Auto-generated method stub
		TableRoleBi trBi = trDao.getbRoleByCond(new TableRoleBi(rolName));
		return trBi;
	}

	@Override
	public TableRoleBi getbRoleByUserName(String userName) throws BizException {
		// TODO Auto-generated method stub
		return trDao.getbRoleByUserName(userName);
	}

}
