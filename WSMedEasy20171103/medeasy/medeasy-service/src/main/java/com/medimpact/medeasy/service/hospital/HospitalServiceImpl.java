package com.medimpact.medeasy.service.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.hospital.HospitalDao;

@Service
public class HospitalServiceImpl implements HospitalService{

	@Resource
	private HospitalDao hDao;
	
	@Override
	public List<HospitalBi> getHospitals() throws BizException {
		// TODO Auto-generated method stub
		List<HospitalBi> list = hDao.list(null);
		return list;
	}

	@Override
	public HospitalBi getHospitalById(int hospitalId) throws BizException {
		// TODO Auto-generated method stub
		return hDao.getHospitalById(hospitalId);
	}

	@Override
	public List<HospitalBi> getHospitalsByRole(HospitalBi hospitalBi) {		
		// TODO Auto-generated method stub
		int operateLevel=hospitalBi.getOperateLevel();
		System.out.println("getHospitalsByRole operateLevel="+operateLevel);
		if(operateLevel==SYSCONSTANT.CON_SYS){
			return hDao.list(null);
		}
		if(operateLevel ==  SYSCONSTANT.CON_AREA){  //检索条件是区域
		/*	return hDao.getHospitalByAreaId(hospitalBi.getSecUser().getHospitalBi().getAreaId());*/
			/*return hDao.getHospitalByAreaCode(hospitalBi.getSecUser().getHospitalBi().getAreaCode());*/
			return hDao.getHospitalByAreaCode(hospitalBi.getSecUser().getAreaCode());
		}
			
		if(operateLevel ==  SYSCONSTANT.CON_HOSPITAL){ //检索条件是医院
			
			List<HospitalBi> li = new ArrayList<>();
		/*	HospitalBi h1 = hDao.getHospitalById(Integer.valueOf(hospitalBi.getSecUser().getHospitalId()));*/
			HospitalBi h1 = hDao.getHospitalByCode(hospitalBi.getSecUser().getHospitalCode());
			li.add(h1);
			return li;
		}
		return null; //对应普通医生， 不能看这些统计数据， 因为此处这些数据的最小级别在医院级
	}

	@Override
	public List<HospitalBi> getHospitalsByArea(Integer areaId) {
		// TODO Auto-generated method stub
		return hDao.getHospitalByAreaId(areaId);
	}

	@Override
	public HospitalBi getHospitalByCode(String hospitalCode) throws BizException {
		// TODO Auto-generated method stub
		
		return hDao.getHospitalByCode(hospitalCode);
	}

	@Override
	public List<HospitalBi> getHospitalsByAreaCode(String areaCode) {
		// TODO Auto-generated method stub
		return hDao.getHospitalByAreaCode(areaCode);
	}
}
