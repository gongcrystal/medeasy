package com.medimpact.medeasy.service.log;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.LogBi;
import com.medimpact.medeasy.common.form.LogForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.dao.log.LogDao;

@Service
public class LogServiceImpl implements LogService{
	
	@Resource
	private LogDao logdao;

	@Override
	public void save(LogBi logBi) {
		// TODO Auto-generated method stub
		logdao.insert(logBi);
	}

	@Override
	public DataModel<List<LogBi>> list(LogForm form) {
		// TODO Auto-generated method stub
		
		SearchFormUtil.preHandleLogForm(form);
		
		Long records=logdao.getCount(form);			
		Long total = records / form.getRows() + 1;		
		List<LogBi> list =  logdao.list(form);
		
		if(list==null || records==null || total==null)
			return DataModel.getNotDataSucDataModal();
		
		return DataModel.getSucDataModal(list, form.getPage(), records, total);
	}
}
