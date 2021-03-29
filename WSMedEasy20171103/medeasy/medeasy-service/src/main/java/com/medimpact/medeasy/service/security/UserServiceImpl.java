package com.medimpact.medeasy.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.functors.IfClosure;
import org.apache.ibatis.annotations.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.common.bean.HospitalBi;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.bean.security.UserRoleBi;
import com.medimpact.medeasy.common.bean.statistic.UserStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.form.UserForm;
import com.medimpact.medeasy.dao.dist.DistDao;
import com.medimpact.medeasy.dao.hospital.HospitalDao;
import com.medimpact.medeasy.dao.security.UserDao;
import com.medimpact.medeasy.dao.security.UserDao1;
import com.medimpact.medeasy.dao.security.UserDaoSearch;
import com.medimpact.medeasy.dao.security.UserRoleDao;

@Service
public class UserServiceImpl implements UserService{	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	private UserDao uDao;
	
	@Resource
	private DistDao dDao; 
	
	@Resource
	private HospitalDao  hDao; 
	
	@Resource
	private UserDao1 u1Dao;
	
	@Resource
	private UserDaoSearch uDaoSearch;
	
	
	@Resource
	private UserRoleDao uRDao;
	
	@Resource
	private TRoleService tRoleS;

	@Override
	public DataModel<List<UserStBi>> getUsers(SecUser form) {	
		
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		List<UserStBi> list  = new ArrayList<>();
		Long records=new Long(0);
		Long total ;		

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = u1Dao.listBySys(form);	
			
			records = u1Dao.selectCountBySys(form);
			
			System.out.println("list = "+list.size()+" records="+records);
			System.out.println(form.getRows());
			break;

		case SYSCONSTANT.CON_AREA:			
			list = u1Dao.listByArea(form);		
			records = u1Dao.selectCountByArea(form);
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = u1Dao.listByHospital(form);	
			records = u1Dao.selectCountByHospital(form);
			break;
		case SYSCONSTANT.CON_USER:
			
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		
		total = records / form.getRows() + 1;
		return DataModel.getSucDataModal(list, form.getPage(), records, total);
		
	}

	@Override
	public DataModel<List<SecUser>> vagueSearch(SecUser secUser) {
		List<SecUser> li =  uDao.vagueSearch(secUser);
		Long records = new Long(li.size());
		Long total  =  records/10 +1;
		return DataModel.getSucDataModal(li, secUser.getPage(), records, total);	
	}

	@Override
	
	public DataModel updateUser(UserForm uForm) throws BizException {	
		
		if(uForm.getAreaCode()!=null && uForm.getAreaCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			uForm.setAreaName(null);
			uForm.setAreaCode(null);
		}
			
		if(uForm.getHospitalCode()!=null && uForm.getHospitalCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			uForm.setHospitalName(null);
			uForm.setHospitalCode(null);
		}
			
		
		if(uForm.getDeptCode()!=null && uForm.getDeptCode().equals(SYSCONSTANT.OPTION_VALUE_NOTHING)){
			uForm.setDeptName(null);
			uForm.setDeptCode(null);
		}
			
		System.err.println("updateUser:"+uForm.getAreaName()+"."+uForm.getHospitalName()+"."+uForm.getDeptName());
		u1Dao.updateUserAndRole(uForm);
		return DataModel.getNotDataSucDataModal();
	}

	@Override
	public DataModel updateUserState(SecUser secUser) throws BizException {
		// TODO Auto-generated method stub
		u1Dao.updateSecUserState(secUser);
		return  DataModel.getNotDataSucDataModal();
	}
	
	@Override
	/*@Cacheable(value="myCache",  key = "#userName")*/
	public SecUser getUserByUserName(String userName) throws BizException {
		/*logger.info("getUserByUserName cache!");*/
		System.out.println("enter getUserByUserName cache!");
		SecUser secUser = uDao.getUserByname(userName);	
		return secUser;
	}

	@Override
	@Transactional
	public DataModel addUser(SecUser secUser, UserRoleBi uRoleBi) {
		// TODO Auto-generated method stub
		System.out.println("service addUser!");
		
		SecUser sUser = uDao.getUserByname(secUser.getUsername());
		if(sUser !=null)
			return DataModel.getFailDataModalNotException(ERROCONSTANT.USER_EXIST_MSG);
		
		String password = new BCryptPasswordEncoder().encode(SYSCONSTANT.DEFAULT_PASSWORD);
		secUser.setPassword(password);
		uDao.insert(secUser);
		SecUser secUser2 = uDao.getUserByname(secUser.getUsername());
		uRoleBi.setUserId(secUser2.getId());
		uRDao.insert(uRoleBi);
		return DataModel.getNotDataSucDataModal();		
	}

	@Override
	public UserStBi getUserStByname(String username) {
		// TODO Auto-generated method stub
		UserStBi userStBi = u1Dao.getUserStsByUserName(username);
		return 	userStBi;	
	}

	@Override
	public DataModel updateSecUser(SecUser sUser) {
		// TODO Auto-generated method stub
		u1Dao.updateSecUser(sUser);
		return DataModel.getNotDataSucDataModal();
	}

	@Override
	public DataModel<List<UserStBi>> getUsersBySearch(SecUser form) {	
		
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		List<UserStBi> list  = new ArrayList<>();
		Long records=new Long(0);
		Long total ;		

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			
			list = uDaoSearch.listBySys(form);	
			
			records = uDaoSearch.selectCountBySys(form);
			
			System.out.println("getUsersBySearch - list = "+list.size()+" records="+records);
			System.out.println("getUsersBySearch: getRows"+form.getRows());
			break;

		case SYSCONSTANT.CON_AREA:			
			list = uDaoSearch.listByArea(form);		
			records = uDaoSearch.selectCountByArea(form);
			System.err.println("getUsersBySearch  CON_AREA : "+list.size()+" "+records+" "+form.getOperateLevel()+" "+form.getAreaCode());
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = uDaoSearch.listByHospital(form);	
			records = uDaoSearch.selectCountByHospital(form);
			break;
		case SYSCONSTANT.CON_USER:
			
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		
		total = records / form.getRows() + 1;
		return DataModel.getSucDataModal(list, form.getPage(), records, total);
		
	}

	@Override
	public SecUser getUserByUserName1(String userName) throws BizException {
		// TODO Auto-generated method stub
		return u1Dao.getUserByUserName1(userName);
	}

	@Override
	public UserStBi getUserRoleByUserName(String username) {
		// TODO Auto-generated method stub
		UserStBi uStBi = u1Dao.getUserRoleByUserName(username);
		//如果areaCode 不为null的话
		
		if(uStBi.getAreaCode()!=null && !uStBi.getAreaCode().isEmpty()){
			DistBi dBi = dDao.getDistByCode(uStBi.getAreaCode()); 
			uStBi.setAreaName(dBi.getName());
		}
		
		//如果hospitalCode 不为null的话
		if(uStBi.getHospitalCode()!=null && !uStBi.getHospitalCode().isEmpty()){
			HospitalBi  hBi = hDao.getHospitalByCode(uStBi.getHospitalCode()); 
			uStBi.setHospitalName(hBi.getName());
		}
		return uStBi;
	}

	@Override
	public DataModel<List<UserStBi>> getUsersAccordRoleUnit(SecUser form) {
		// TODO Auto-generated method stub
		List<UserStBi> list  = new ArrayList<>();
		long records;
		long total ;
		list = u1Dao.getUsersAccordRoleUnit(form);		
		
		records = u1Dao.getUsersAccordRoleUnitCount(form);
		if( records % form.getRows()==0){
			total = records / form.getRows();
		}else{
			total = records / form.getRows() + 1;
		}
		
		
		System.err.println("getUsersAccordRoleUnit:"+form.getOperateLevel()+" "+form.getAreaCode()+" "+records+" "+form.getRows()+" "+list.size()+"total= "+total);
		return DataModel.getSucDataModal(list, form.getPage(), records, total);
	}	
}
