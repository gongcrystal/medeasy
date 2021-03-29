;(function($){
	$.extend({
		"genAccordion": function(menu_ul,data){
			var menuUl = $("#menu_ul");
			var prevActiveMenu; //记录上一个active的menuId; 用于active菜单的小尖
			$.each(data, function(index, element) {
				if(element.menuCode !="home"){
					menuUl.genMenu(element);
				}
			}).always(function() {
				$(".menu_click").click(function() {
					/*menu active 尖*/
					$(this).addClass('active');
					if(prevActiveMenu!=null && $("#"+prevActiveMenu).hasClass('active')){
						$("#"+prevActiveMenu).removeClass('active');
					}
					prevActiveMenu = mcode;
				});
			});
		},
	
	"genSubMenu" : function(e, maxOrderSubMenu) {
		console.log("Crystal genSubMenu");
		var icons = e.menuIcon.split(" ");
		var iconStr = "";

		for (var i = 0; i < icons.length; i++) {
			iconStr = iconStr + " " + icons[i];
		}

		var hasSubMenu = e.hasSubMenu;
		var menuStr = "";

		if (e.menuOrder == 1) {
			menuStr += "<ul class=\"submenu\">";
		}

		var iframeSrc = "# ";
		if (e.menuType == true) {
			menuStr += "<li class=\"menu_click\" id=" + e.menuCode + "> <a href=\"" + iframeSrc + "\"" + " id=a_" + e.menuCode;
		} else {
			menuStr += "<li class=\"\" id=" + e.menuCode + " > <a href=\"" + iframeSrc + "\" " + " id=a_" + e.menuCode;
		}

		if (hasSubMenu == true) {
			menuStr += " class=\"dropdown-toggle\"";
		}

		menuStr += "><i class= \"menu-icon fa " + iconStr + "\"></i>" + "<span class=\"menu-text\">" + e.menuName + "</span>";

		if (hasSubMenu == true) {
			menuStr += "<b class=\"arrow fa fa-angle-down\"></b>";
		}
		menuStr += "</a><b class=\"arrow\"></b>";

		if (hasSubMenu == true) {
			$.each(e.subMenu, function(index1, e1) {
				menuStr += $.genSubMenu(e1);
			});
			menuStr += "</li>";
			menuStr += "</ul>"
		}

		menuStr += "</li>";
		if (e.menuOrder == (maxOrderSubMenu)) {
			menuStr += "</ul>"
		}
		return menuStr;
		/* $(this).append(menuStr); */// 错误的原因！！
	}
	});
	
	$.fn.extend({
		"genMenu": function(element){
			/* menuIcon 的格式fa-desktop blue */
			var icons = element.menuIcon.split(" ");
			var iconStr = "";

			for (var i = 0; i < icons.length; i++) {
				iconStr = iconStr + " " + icons[i];
			}

			var iframeSrc = "# ";
			var mmenu = "";
			if (element.menuType == true) {
				mmenu = "<li class=\"menu_click \" id=" + element.menuCode + " > <a href=\"" + iframeSrc + "\" " + " id=a_" + element.menuCode;
			} else {
				mmenu = "<li class=\" \" id=" + element.menuCode + " > <a href=\"" + iframeSrc + "\" " + " id=a_" + element.menuCode;
			}

			if (element.hasSubMenu == true) { /* 判断是否有子菜单 */
				mmenu += " class=\"dropdown-toggle\"";
			}
			mmenu += "><i class= \"menu-icon fa " + iconStr + "\"></i>" + "<span class=\"menu-text\">" + element.menuName + "</span>";

			if (element.hasSubMenu == true) { /* 判断是否有子菜单 */
				mmenu += "<b class=\"arrow fa fa-angle-down\"></b>"
			}
			mmenu += "</a><b class=\"arrow\"></b>" /* 结束对一级菜单的生成 */
		/*console.log("crystal!"+element.menuName);*/
			if (element.hasSubMenu == true) {
				var l2Menu = element.subMenu;
				/*console.log("size="+l2Menu.length);*/
				$.each(l2Menu, function(property, value) {
				/*	console.log("value="+value+"element.maxOrderSubMenu= "+element.maxOrderSubMenu);*/
					mmenu += $.genSubMenu(value, element.maxOrderSubMenu);
				});
			}

			mmenu += "</li>";
			$(this).append(mmenu);
			/* console.log(mmenu); */
			return $(this);
		}
	})
})(jQuery);


