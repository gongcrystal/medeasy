package com.medimpact.medeasy.common.bean;

import java.io.Serializable;
import java.util.List;

public class MenuBi  implements Serializable, Comparable<MenuBi>{
	private static final long serialVersionUID = -4298423068808958100L;
	private Integer menuId;		//菜单ID
	private String menuCode;	//菜单CODE
	private String menuName;	//菜单名称
	private String menuUrl;	    //链接 对应iframe src的值	
	private Integer parentId;	//上级菜单ID
	private Integer menuOrder;	//排序 ，所在级别菜单级别中的排序
	private String menuIcon;	//图标
	private boolean menuType=true;	//类型 ,代表是否有对应的iframe,及实际的功能 0: 无实际功能 1：有
	private boolean menuState=true;	//菜单状态 true：有效
	private MenuBi parentMenu;
	private List<MenuBi> subMenu;
	private boolean hasSubMenu = true; //有子菜单	
	
	private Integer maxOrderSubMenu=0;  //子菜单中最大的序号;
	private int moduleType = 0; //如果是3级的菜单，需要特殊说明, 3及菜单不同于1，2级菜单
	
	public MenuBi() {
		super();
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public boolean isMenuType() {
		return menuType;
	}
	public void setMenuType(boolean menuType) {
		this.menuType = menuType;
	}
	public boolean isMenuState() {
		return menuState;
	}
	public void setMenuState(boolean menuState) {
		this.menuState = menuState;
	}
	public MenuBi getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(MenuBi parentMenu) {
		this.parentMenu = parentMenu;
	}
	public List<MenuBi> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuBi> subMenu) {
		this.subMenu = subMenu;
	}
	public boolean isHasSubMenu() {
		return hasSubMenu;
	}
	public void setHasSubMenu(boolean hasSubMenu) {
		this.hasSubMenu = hasSubMenu;
	}

	public Integer getMaxOrderSubMenu() {
		return maxOrderSubMenu;
	}

	public void setMaxOrderSubMenu(Integer maxOrderSubMenu) {
		this.maxOrderSubMenu = maxOrderSubMenu;
	}
	
	
	
	public int getModuleType() {
		return moduleType;
	}
	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}
	@Override
	public int compareTo(MenuBi o) {
		// TODO Auto-generated method stub
		return this.menuOrder.compareTo(o.getMenuOrder());
	}
	
}
