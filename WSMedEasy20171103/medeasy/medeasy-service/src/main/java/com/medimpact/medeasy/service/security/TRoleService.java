package com.medimpact.medeasy.service.security;

import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.exception.BizException;

public interface TRoleService {
	public TableRoleBi getbRoleByRoleName(String rolName) throws BizException;
	public TableRoleBi getbRoleByUserName(String userName) throws BizException;
}
