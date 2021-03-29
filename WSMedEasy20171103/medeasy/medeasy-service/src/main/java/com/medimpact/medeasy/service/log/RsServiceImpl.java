package com.medimpact.medeasy.service.log;

import javax.annotation.Resource;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RsBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.dao.log.RsDao;

public class RsServiceImpl implements RsService{
	
	@Resource
	private RsDao rsDao;

	@Override
	public DataModel<RsBi> getRsByMethod(String method) {
		// TODO Auto-generated method stub
		if(method==null || method.isEmpty()){
			return DataModel.getFailDataModalNotException(ERROCONSTANT.GENERAL_INPUTNULL);
		}
		RsBi rsBi  = rsDao.getRsByMethod(method);
		if (rsBi==null) {
			return DataModel.getFailDataModalNotException(ERROCONSTANT.GENERAL_RETURN_NULL);
			
		}
		return DataModel.getSucDataModal(rsDao.getRsByMethod(method));
	}
}
