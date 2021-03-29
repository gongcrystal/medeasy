package com.medimpact.medeasy.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.StatementTypebi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.security.StatementTypeDao;

@Service
public class StatementTypeServiceImpl implements StatementTypeService {

	@Resource
	private StatementTypeDao stDao;
	
	@Override
	public List<StatementTypebi> getStmType(int opLevel) throws BizException {
		// TODO Auto-generated method stub
		return stDao.getStatementTypeByOpLevel(opLevel);
	}

}
