package com.medimpact.medeasy.service.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.management.relation.Role;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.RoleMenuForm;
import com.medimpact.medeasy.dao.menu.RoleMenuDao;
import com.medimpact.medeasy.dao.security.RoleDao;
import com.medimpact.medeasy.dao.security.TbRoleDao;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	@Resource
	private  RoleDao rDao;
	
	@Resource
	private RoleMenuDao rmDao;	
	
	@Resource
	private TbRoleDao tbRoleDao;

	@Override
	@Transactional
	public void addRoleMenu(RoleMenuForm form) {
		System.err.println("addRoleMenu: "+form.getRoleName());
		//检查角色是否已存在
	/*	SecRole secRole =  rDao.getSecRoleByName(form.getRoleName().trim());
		if(secRole !=null){
			return DataModel.getFailDataModalNotException("角色已存在");
		}*/
		// TODO Auto-generated method stub
		SecRole role  =  new SecRole();
		role.setRoleName(form.getRoleName());
		role.setDescription(form.getDescription());
		if(form.getOperateLevel()>1){
			role.setReqOpLevel(form.getOperateLevel() - 1); // 比如，要看area admin 的， 只能是sysadmin,同级的不能看， sysadmin除外
		}else {
			role.setReqOpLevel(1);
		}
				
		rDao.insert(role);	
		
		/*tbRoleDao.updateOperateLevel(new TableRoleBi(form.getRoleName(),form.getOperateLevel()));*/
		tbRoleDao.insert(new TableRoleBi(form.getRoleName(),form.getOperateLevel()));
		
		String[] menus = form.getMenuAuth().trim().split("-");
		List<RoleMenuBi> rMenuBis = new ArrayList<>();
		for(int i=0; i<menus.length; i++){
			String[] section=menus[i].split(":");
			RoleMenuBi rMenuBi =  new RoleMenuBi();
			rMenuBi.setRoleName(form.getRoleName());
			rMenuBi.setMenuId(Integer.valueOf(section[1]));
			/*rMenuBi.setMenuName(menus[i]);*/
			rMenuBi.setMenuName(section[0]);
			rMenuBi.setDescription(form.getDescription());
			rMenuBis.add(rMenuBi);
		}
		//add 首页
		/*RoleMenuBi hp = new RoleMenuBi();
		hp.setRoleName(form.getRoleName());
		hp.setMenuName(SYSCONSTANT.FIRSTPAGE_NAME);
		hp.setMenuId(SYSCONSTANT.FIRSTPAGE_ID);
		rMenuBis.add(hp);*/
		
		rmDao.insertBatch(rMenuBis);	
		/*return DataModel.getNotDataSucDataModal(); */
	}


	@Override
	public DataModel<List<RoleMenuBi>> getRoleMenuByName(String roleName)  throws BizException {
		
		List<RoleMenuBi> rMenuBi = rmDao.getRoleMenuByName(roleName);
		return DataModel.getSucDataModal(rMenuBi); 
	}


	@Override
	@Transactional
	public DataModel updateRoleMenuByName(RoleMenuForm form) throws BizException {
		// TODO Auto-generated method stub
		rmDao.delByName(form.getRoleName());
		
		String[] menus = form.getMenuAuth().trim().split("-");
		List<RoleMenuBi> rMenuBis = new ArrayList<>();
		
		
		for(int i=0; i<menus.length; i++){
			String[] section=menus[i].split(":");			
			
			RoleMenuBi rMenuBi =  new RoleMenuBi();
			rMenuBi.setRoleName(form.getRoleName());
			rMenuBi.setMenuId(Integer.valueOf(section[1]));
			/*rMenuBi.setMenuName(menus[i]);*/
			rMenuBi.setMenuName(section[0]);
			rMenuBi.setDescription(form.getDescription());
			rMenuBis.add(rMenuBi);
		}		
		rmDao.insertBatch(rMenuBis);
		tbRoleDao.updateOperateLevel(new TableRoleBi(form.getRoleName(),form.getOperateLevel()));
		/*tbRoleDao.insert(new TableRoleBi(form.getRoleName(),form.getOperateLevel()));*/
		
		return DataModel.getNotDataSucDataModal();
	}
}
