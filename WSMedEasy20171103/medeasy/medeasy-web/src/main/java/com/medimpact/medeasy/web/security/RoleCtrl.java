package com.medimpact.medeasy.web.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.RoleAuthorityBi;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.RoleMenuForm;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.vo.ZTreeNodeVo;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.menu.MenuService;
import com.medimpact.medeasy.service.menu.RoleMenuService;
import com.medimpact.medeasy.service.security.RoleService;
import com.medimpact.medeasy.service.security.RoleService1;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月21日 类说明
 */
@Controller
@RequestMapping("/role")
public class RoleCtrl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RoleService roleService;
	
	@Resource
	private RoleService1 roleService1;
	
	@Resource
	private TRoleService trService;		

	@Resource
	private MenuService menuService;

	@Resource
	private RoleMenuService rmService;
	
	@Resource
	private UserService us;
	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;
	


	@RequestMapping(value = "/getSecRoleByName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<SecRole> getSecRoleByName(@RequestParam(value = "roleName", required = true) String roleName) {
		System.out.println("getSecRoleByName!");
		return roleService.getSecRoleByName(roleName);
	}

	@RequestMapping(value = "/getSecRoles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<SecRole>> getSecRoles() {
		System.out.println("getSecRoles!");
		SecRole form =  new SecRole();
		UserInfoUtil.setUserInfo(us, trService, hService,form);	
				
		return roleService.getSecRoles(form.getOperateLevel());
	}

	@RequestMapping(value = "/getRoles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel<List<RoleMenuForm>> getRoles(SearchForm form) {
		System.err.println("getRoles: "+form.getRows()+" "+form.getRowStart()+" "+form.getRowSize());
		UserInfoUtil.setUserInfo(us, ts, hService, form);
		return roleService.getRolesUniqueRoleName(form);
		/*return roleService1.getAuthorities(form);*/
	}

	@RequestMapping(value = "/delRole", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel delRole(@RequestBody SecRole form) {

		System.out.println("delItem id= " + form.getId());
		roleService.delRole(form);
		return DataModel.getNotDataSucDataModal();
	}

	@RequestMapping(value = "/addRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel addRole(SecRole form) {
		System.out.println("start addRole" + form.getRoleName() + " " + form.getDescription());

		if (form.getId() != null) {
			return roleService.updateRole(form);
		}
		return roleService.addRole(form);
	}

	@RequestMapping(value = "/getRoleMenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel getRoleMenu(@RequestParam(value = "roleName", required = true) String roleName) {
		return rmService.getRoleMenuByName(roleName);
	}

	@RequestMapping(value = "/addRoleMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel addRoleMenuAuth(@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam("description") String description, @RequestParam("menuAuth") String menuAuth,
			@RequestParam("operateLevel") String operateLevel,@RequestParam("opName") String opName) {
		menuAuth+="-"+SYSCONSTANT.HOME_PAGE_STR+":"+SYSCONSTANT.HOME_PAGE_NUM;
		
		
		RoleMenuForm form = new RoleMenuForm(roleName, description, menuAuth, Integer.valueOf(operateLevel),opName);
		DataModel<SecRole> r = roleService.getSecRoleByName(roleName);	
	
		if(r!=null && r.getData()!=null && r.getData().getRoleName()!=null){			
			return DataModel.getFailDataModalNotException("角色已存在");
		}		
		
		rmService.addRoleMenu(form);
		return DataModel.getNotDataSucDataModal();
	}

	@RequestMapping(value = "/updateRoleMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataModel updateRoleMenuAuth(@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam("description") String description, @RequestParam("menuAuth") String menuAuth,
			@RequestParam("operateLevel") String operateLevel,@RequestParam("opName") String opName) {

		RoleMenuForm form = new RoleMenuForm(roleName, description, menuAuth, Integer.valueOf(operateLevel),opName);
		rmService.updateRoleMenuByName(form);
		/* rmService.addRoleMenu(form); */
		return DataModel.getNotDataSucDataModal();
	}

	@RequestMapping(value = "/listNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ZTreeNodeVo> listNodes() {
		Integer parentId = 0;
		List<MenuBi> list = menuService.listAllMenu(parentId);
		List<ZTreeNodeVo> nodes = new ArrayList<>();

		for (MenuBi mBi : list) {
			this.recuisiveCpy(mBi, nodes);
		}
		for(MenuBi m: list){
			System.out.println(m.getMenuName());
		}
		
		return nodes;
	}

	public void recuisiveCpy(MenuBi mBi, List<ZTreeNodeVo> nodes) {

		if (mBi != null && nodes != null) {
			ZTreeNodeVo node = new ZTreeNodeVo();
			this.singleCpy(mBi, node);
			nodes.add(node);

			if (mBi.isHasSubMenu()) {
				List<MenuBi> subMenuBis = mBi.getSubMenu();
				for (MenuBi mBi2 : subMenuBis) {
					recuisiveCpy(mBi2, nodes);
				}
			}
		}
	}

	public void singleCpy(MenuBi mBi, ZTreeNodeVo node) {
		if (mBi != null && node != null) {
			node.setId(mBi.getMenuId());
			node.setName(mBi.getMenuName());
			node.setpId(mBi.getParentId());
			node.setIsParent(mBi.isHasSubMenu());
		}
	}
}
