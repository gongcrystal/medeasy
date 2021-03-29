package com.medimpact.medeasy.service.menu;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.exception.BizException;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月16日 类说明
 */

public interface MenuService {	
	public List<MenuBi> listSubMenuByParentId(Integer id) throws BizException;	
	public List<MenuBi> listAllMenu(Integer menuId)  throws BizException;
	public List<MenuBi> listMenusByRoleName(String roleName) throws BizException;
}
 