package com.medimpact.medeasy.dao.hospital;

import java.util.List;

import com.medimpact.medeasy.common.bean.DeptBi;
import com.medimpact.medeasy.dao.CommonStDao;

public interface DeptDao extends CommonStDao<DeptBi>{
	public List<DeptBi> list();
	public List<DeptBi> getDeptsByHospitalCode(String hospitalCode);
	 
	
}
