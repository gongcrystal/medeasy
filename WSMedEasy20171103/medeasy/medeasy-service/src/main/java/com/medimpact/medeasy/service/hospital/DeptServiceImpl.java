package com.medimpact.medeasy.service.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.DeptBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.dao.hospital.DeptDao;
import com.medimpact.medeasy.service.security.UserService;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Resource
	private DeptDao deptDao;
	
	

	@Override
	public List<DeptBi> getDepts() throws BizException {
		// TODO Auto-generated method stub
		List<DeptBi>  list =  deptDao.list();
		System.out.println(list.get(0).getName());
		return list;
	}

	@Override
	public List<DeptBi> getDeptsByHospitalCode(String hospitalCode) throws BizException {
		// TODO Auto-generated method stub		
		return deptDao.getDeptsByHospitalCode(hospitalCode);
	}

	@Override
	public List<DeptBi> getDeptsByRole(SearchForm form) throws BizException {
		// TODO Auto-generated method stub
		
		List<DeptBi> list =  new ArrayList<>();
		
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = deptDao.listBySys(form);
			break;

		case SYSCONSTANT.CON_AREA:
			list = deptDao.listByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = deptDao.listByHospital(form);
			break;
		case SYSCONSTANT.CON_USER:
			break;

		default:
			return null; 
		}
		return list;
	}

	
}
