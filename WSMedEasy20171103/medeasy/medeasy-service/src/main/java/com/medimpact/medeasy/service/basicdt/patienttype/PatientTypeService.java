package com.medimpact.medeasy.service.basicdt.patienttype;

import java.util.List;

import com.medimpact.medeasy.common.bean.PatientTypeBi;
import com.medimpact.medeasy.common.exception.BizException;

public interface PatientTypeService {
	public List<PatientTypeBi> listPatientTypes() throws BizException;

}
