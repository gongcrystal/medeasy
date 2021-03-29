package com.medimpact.medeasy.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.bean.TableRoleBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.RoleMenuForm;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.dao.menu.RoleMenuDao;
import com.medimpact.medeasy.dao.security.RoleDao;
import com.medimpact.medeasy.dao.security.TbRoleDao;

@Service
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RoleDao roleDao;

	@Resource
	private RoleMenuDao rmDao;

	@Resource
	private TbRoleDao tbRoleDao;

	@Override
	public DataModel<List<RoleMenuForm>> getRoles(SearchForm form) {
		// TODO Auto-generated method stub
		List<RoleMenuBi> li = rmDao.getRoleMenus(form);
		List<RoleMenuForm> rForms = new ArrayList<>();

		for (RoleMenuBi rMenuBi : li) {
			RoleMenuForm f = new RoleMenuForm();
			String rName = rMenuBi.getRoleName();
			List<RoleMenuBi> rmList = rmDao.getRoleMenuByName(rName);
			String menus = "";
			for (RoleMenuBi rMenuBi2 : rmList) {
				menus += rMenuBi2.getMenuName() + " ";
			}
			f.setMenuAuth(menus);
			f.setRoleName(rName);
			f.setDescription(rMenuBi.getDescription());

			rForms.add(f);
		}
		return DataModel.getSucDataModal(rForms);
	}

	@Override
	public DataModel delRole(SecRole secRole) {
		// TODO Auto-generated method stub
		roleDao.delete(new Long(secRole.getId()));
		return DataModel.getNotDataSucDataModal();
	}

	@Override
	public DataModel updateRole(SecRole secRole) {
		// TODO Auto-generated method stub
		roleDao.update(secRole);
		return DataModel.getNotDataSucDataModal();
	}

	@Override
	public DataModel addRole(SecRole secRole) {
		// TODO Auto-generated method stub
		roleDao.insert(secRole);
		return DataModel.getNotDataSucDataModal();
	}

	@Override
	public DataModel<List<RoleMenuForm>> getRolesUniqueRoleName(SearchForm form) {
		// TODO Auto-generated method stub

		List<RoleMenuBi> li = rmDao.getRoleMenus(form);
		System.err.println("getRolesUniqueRoleName: li.size"+li.size());
		List<RoleMenuForm> rForms = new ArrayList<>();
		List<TableRoleBi> trs = tbRoleDao.list(null);

		List<String> roleNames = new ArrayList<>();

		for (RoleMenuBi rMenuBi : li) {
			RoleMenuForm f = new RoleMenuForm();
			String rName = rMenuBi.getRoleName();

			List<RoleMenuBi> rmList = rmDao.getRoleMenuByName(rName);
			String menus = "";
			for (RoleMenuBi rMenuBi2 : rmList) {
				menus += rMenuBi2.getMenuName() + " ";
			}
			f.setMenuAuth(menus);
			f.setRoleName(rName);
			f.setOperateLevel(rMenuBi.getDtOpLevelBi().getOperateLevel());
			f.setOpName(rMenuBi.getDtOpLevelBi().getOpName());
			f.setDescription(rMenuBi.getDescription());

			if (!(roleNames.contains(rName))) {
				rForms.add(f);
				roleNames.add(rName);
			}
		}
System.err.println("rForms:"+rForms.size());
		return DataModel.getSucDataModal(rForms);
	}

	@Override
	public DataModel<List<SecRole>> getSecRoles(int opLevel) {
		List<SecRole> li = roleDao.getRolesByOpLevel(opLevel);
		return DataModel.getSucDataModal(li);
	}

	@Override
	public DataModel<SecRole> getSecRoleByName(String roleName) throws BizException {
		SecRole data = roleDao.getSecRoleByName(roleName);
		return DataModel.getSucDataModal(data);
	}
}
