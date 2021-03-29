package com.medimpact.medeasy.service.security;

import java.util.List;
import com.medimpact.medeasy.common.bean.StatementTypebi;
import com.medimpact.medeasy.common.exception.BizException;

public interface StatementTypeService {
	public List<StatementTypebi> getStmType(int opLevel) throws BizException; //根据登陆用户所有的权限，返回不同的统计类型

}
