package com.medimpact.medeasy.dao.hospital;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.dao.CommonDao;

@Mapper
public interface HospitalDao extends CommonDao<HospitalBi>{	
	public HospitalBi getHospitalById(@Param("hospitalId") int hospitalId);
	public List<HospitalBi> getHospitalByAreaId(@Param("distId") int distId);	
	public List<HospitalBi> getHospitalByAreaCode(@Param("areaCode") String areaCode);	
	public HospitalBi getHospitalByCode(@Param("hospitalCode") String hospitalCode);
}
