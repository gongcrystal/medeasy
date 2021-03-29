package com.medimpact.medeasy.service.hospital;

import java.util.List;

import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.common.exception.BizException;

public interface HospitalService {
	public List<HospitalBi> getHospitals() throws BizException;
	public HospitalBi getHospitalById(int hospitalId) throws BizException;
	public HospitalBi getHospitalByCode(String hospitalCode) throws BizException;
	public List<HospitalBi> getHospitalsByRole(HospitalBi hospitalBi);	
	public List<HospitalBi> getHospitalsByArea(Integer areaId);	
	public List<HospitalBi> getHospitalsByAreaCode(String areaCode);
}
