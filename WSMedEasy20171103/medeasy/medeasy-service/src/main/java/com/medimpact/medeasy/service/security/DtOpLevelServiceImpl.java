package com.medimpact.medeasy.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.DtOpLevelBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.security.DtOpLevelDao;

@Service
public class DtOpLevelServiceImpl implements DtOpLevelService{

	@Resource 
	private DtOpLevelDao dtOpLevelDao;
	
	@Override
	public List<DtOpLevelBi> getDtOpLevels() throws BizException {
		// TODO Auto-generated method stub
		return dtOpLevelDao.list(null);
	}

}
