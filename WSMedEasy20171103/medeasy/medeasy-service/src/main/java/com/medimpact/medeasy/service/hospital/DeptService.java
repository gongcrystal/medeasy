package com.medimpact.medeasy.service.hospital;

import java.util.List;

import com.medimpact.medeasy.common.bean.DeptBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;

public interface DeptService {
	
	List<DeptBi> getDepts() throws BizException;
	List<DeptBi> getDeptsByHospitalCode(String hospitalCode) throws BizException;
	List<DeptBi> getDeptsByRole(SearchForm form) throws BizException;
	
	

}
