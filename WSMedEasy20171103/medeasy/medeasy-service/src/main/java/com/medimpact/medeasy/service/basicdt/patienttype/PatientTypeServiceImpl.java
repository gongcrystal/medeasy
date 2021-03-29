package com.medimpact.medeasy.service.basicdt.patienttype;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.PatientTypeBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.patienttype.PatientTypeDao;

@Service
public class PatientTypeServiceImpl implements PatientTypeService {

	@Resource
	private PatientTypeDao ptDao;

	@Override
	public List<PatientTypeBi> listPatientTypes() throws BizException {
		// TODO Auto-generated method stub
		return ptDao.list(null);
	}

}
