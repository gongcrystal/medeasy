package com.medimpact.medeasy.service.irritate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.IrritationItemBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.irritate.IrritateItemDao;

@Service
public class IrritateItemServiceImpl implements IrritateItemService{
	
	@Resource
	private IrritateItemDao iDao;

	@Override
	public List<IrritationItemBi> list() throws BizException {
		// TODO Auto-generated method stub
		return iDao.list(null);
	}
}
