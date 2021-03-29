package com.medimpact.medeasy.web.menu;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.service.menu.MenuService;
import com.medimpact.medeasy.service.security.AccountHelper;
import com.medimpact.medeasy.service.security.AccountHelper1;

@Controller
@RequestMapping("/sys")
public class MenuCtrl {
	
	@Resource
	private MenuService menuService;	
	
	/*所有菜单并填充每个菜单的子菜单列表*/
	@RequestMapping(value = "/listMenus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MenuBi>  listSubMenuByParentId (@RequestParam("parentId") Integer parentId) {
		List<MenuBi> list = menuService.listAllMenu(parentId);			
		return list;
	}	
	
	@RequestMapping(value = "/listMenusByRName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MenuBi>  getMenusByRoleName () {
		/*return menuService.listMenusByRoleName(AccountHelper.getRoles()); */
		return menuService.listMenusByRoleName(new AccountHelper1().getRoles());
	}		
}
