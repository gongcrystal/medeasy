package com.medimpact.medeasy.service.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.menu.MenuDao;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月16日 类说明
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;

	@Override
	public List<MenuBi> listSubMenuByParentId(Integer id) throws BizException {
		// TODO Auto-generated method stub
		return menuDao.listSubMenuByParentId(id);
	}

	@Override
	public List<MenuBi> listAllMenu(Integer menuId) throws BizException {
		// TODO Auto-generated method stub
		List<MenuBi> mList = this.listSubMenuByParentId(menuId);
		for (MenuBi mBi : mList) {
			if (mBi.isHasSubMenu()) {
				List<MenuBi> subMenus = listAllMenu(mBi.getMenuId());
				mBi.setSubMenu(subMenus);
			}
		}
		return mList;
	}

	@Override
	public List<MenuBi> listMenusByRoleName(String roleName) throws BizException {
		List<MenuBi> menus = menuDao.getMenusByRoleName(roleName);// 所有将要显示的菜单都在此
		List<MenuBi> result = new ArrayList<>();
		for (MenuBi mBi : menus) {
			addSubMenu(mBi, menus);
		}
		for (MenuBi m : menus) {
			if (m.getParentId() == 0)
				result.add(m);
		}
		System.out.println("listMenusByRoleName size="+result.size()+" "+roleName);
		return result;
	}

	// mBi作为parent, 从menus里获取元素，添加到mBi.submenus
	public void addSubMenu(MenuBi mBi, List<MenuBi> menus) {
		Integer menuId = mBi.getMenuId();
		List<MenuBi> subMenus = new ArrayList<>();

		for (MenuBi mBi2 : menus) {
			if (menuId == mBi2.getParentId()) {
				if (mBi2.isHasSubMenu())
					addSubMenu(mBi2, menus);

				subMenus.add(mBi2);
			}
		}
		if (!subMenus.isEmpty()) {
			Collections.sort(subMenus);
			for (int i = 0; i < subMenus.size(); i++)
				subMenus.get(i).setMenuOrder(i+1);

			mBi.setSubMenu(subMenus);
			mBi.setMaxOrderSubMenu(subMenus.get(subMenus.size() - 1).getMenuOrder());
		}
	}
}
