$(function() {
	$.genMenu(0); /* 默认调用一级菜单 */
	$.homeClick();
});

/*单独为home处理一下*/
$.homeClick = function(){
	$("#navtab_home").click(function(){
		$.mngTabPane("#tab_home");
	})
}

$.genMenu = function(parentId) {
	var menuUl = $("#menu_ul"); // left menu container
	var tabPane = $("#tab_pane"); // tabPane container
	var tatPaneStr = "";
	
	$.ajax({
		"url" : '/medeasy-web/sys/listMenus',
		"type" : 'POST',
		"dataType" : 'json',
		"timeout" : 20000,
		data : {
			parentId : parentId
		},
		success : function(data) {
			$.each(data, function(index, element) {
				if(element.menuCode !="home"){
					menuUl.genMenu(element);
					$.genTabFrame(element, tabPane);
				}
				/*$.genNavTab(element);*/
			});
		}
	}).always(function() {		
		$(".menu_click").click(function() {
			var mcode = $(this).attr("id");
			$.mngTabPane("#tab_" + $(this).attr("id")); // load iframe
			
			var menuName = $("#a_"+$(this).attr("id")).text();
		
			if($("#tab_nav>li").hasClass('active')){
				$("#tab_nav>li").removeClass('active');
			}
			
			$.genNavTab(mcode,menuName);		
				
		});
		
	});
};



$.extend({
	'genNavTab' : function(menuCode,menuName) {
		
		if(!($("#navtab_"+menuCode).length>0)){
			var icons = " <label class=\"field-icon\"> &nbsp; &nbsp;<i class=\"fa fa-close light red\" id=" +"close_"+menuCode+
					"></i></label>";
			var navtab = "<li class=\"active\" id=navtab_" + menuCode + " ><a href=\"#tab_" + menuCode + "\"  data-toggle=\"tab\" >" + menuName +" "+icons+ " </a></li>";
				
			/*var navtab = "<li class=\"s_navtab\" id=navtab_" + menuCode + " ><a href=\"#tab_" + menuCode + "\"  data-toggle=\"tab\" >" + menuName +" "+icons+ " </a></li>";*/
			$("#tab_nav").append(navtab);	
			/*console.log(navtab);*/
			$("#navtab_"+menuCode).bind('click',function(){
				$.mngTabPane("#tab_" + menuCode);
			})
			$("#close_"+menuCode).bind('click',function(){
				var parentId = $(this).parent().parent().parent().attr("id");
					
				$("#tab_"+menuCode+":visible").hide();				
				
				var activeTabRemoved = false;
				if($("#navtab_"+menuCode).hasClass('active')){
					activeTabRemoved = true;
				}
				$("#navtab_"+menuCode).remove();
				
				/*if navtab对应的是active的被删除的话，选择剩余的index最大的active*/
				if(activeTabRemoved ==true){
					var maxchildren=$('#tab_nav').children().length-1;	
					console.log(maxchildren);
					var maxchildrenobj = $('#tab_nav').children().eq(maxchildren);
					var maxchildrenId = maxchildrenobj.attr("id");
					maxchildrenobj.addClass('active');
				/*	maxchildrenobj.css("background-color","#4c5678");*/
					
					/*	show 剩下的index最大的navtab 对应的tabpane*/					
					$('#'+maxchildrenId.replace('navtab','tab')).show();
				}
					
			});
		}else{  //如果navtab对应的tab已经存在了，则再次点击，只需要让其active即可
			if(!$("#navtab_"+menuCode).hasClass('active')){
				$("#navtab_"+menuCode).addClass('active');
			}			
		}
	}  
});

/*$.extend({
	'genNavTab' : function(element) {
		var menuCode = element.menuCode;

		if (element.menuType == true && !($("#navtab_" + menuCode).length > 0)) {
			var navtab = "<li id=navtab_" + menuCode + " ><a href=\"#tab_" + menuCode + "\"  data-toggle=\"tab\" >" + element.menuName + "</a></li>";
			$("#tab_nav").append(navtab);

		}

		if (element.hasSubMenu == true) {
			var subMenu = element.subMenu;
			$.each(subMenu, function(property, value) {
				$.genNavTab(value);
			});
		}
	}
});*/

/* tab-pane下显示当前menu对应的tab,其他隐藏 */
$.extend({
	'mngTabPane' : function(tabId) {
		$(".tab-pane").not(tabId).hide();
		$(tabId + ":hidden").show();
	}
});

/* 点击菜单的item, 产生相应的tab和iframe页 */
$.extend({
	'genTabFrame' : function(element, component) {
		/* console.log(element.menuName); */
		var mdiv = "";
		if (element.menuType == true) {
			mdiv = " <div class=\"tab-pane\" id=" + "tab_" + element.menuCode + "><iframe src=" + element.menuUrl + "  id=if_" + element.menuCode
					+ " frameborder=\"0\" ></iframe></div> ";
			component.append(mdiv);
		}

		if (element.hasSubMenu == true) {
			var subMenu = element.subMenu;
			$.each(subMenu, function(property, value) {
				$.genTabFrame(value, component);
			});
		}

	/*	console.log(mdiv);*/
		/* return mdiv; */
	}
});

/* element:一级菜单的obj.产生所有下面无限极的子菜单 */
jQuery.fn.genMenu = function(element) {
	/* menuIcon 的格式fa-desktop blue */
	var icons = element.menuIcon.split(" ");
	var iconStr = "";

	for (var i = 0; i < icons.length; i++) {
		iconStr = iconStr + " " + icons[i];
	}

	var iframeSrc = "# ";
	var mmenu = "";
	if (element.menuType == true) {
		mmenu = "<li class=\"menu_click\" id=" + element.menuCode + " > <a href=\"" + iframeSrc + "\" " + " id=a_" + element.menuCode;
	} else {
		mmenu = "<li class=\"\" id=" + element.menuCode + " > <a href=\"" + iframeSrc + "\" " + " id=a_" + element.menuCode;
	}

	if (element.hasSubMenu == true) { /* 判断是否有子菜单 */
		mmenu += " class=\"dropdown-toggle\"";
	}
	mmenu += "><i class= \"menu-icon fa " + iconStr + "\"></i>" + "<span class=\"menu-text\">" + element.menuName + "</span>";

	if (element.hasSubMenu == true) { /* 判断是否有子菜单 */
		mmenu += "<b class=\"arrow fa fa-angle-down\"></b>"
	}
	mmenu += "</a><b class=\"arrow\"></b>" /* 结束对一级菜单的生成 */

	if (element.hasSubMenu == true) {
		var l2Menu = element.subMenu;
		$.each(l2Menu, function(property, value) {
			mmenu += $.genSubMenu(value, element.maxOrderSubMenu);
		});
	}

	mmenu += "</li>";
	$(this).append(mmenu);
	/* console.log(mmenu); */
};

/* e是二级...菜单元素 */
$.extend({
	'genSubMenu' : function(e, maxOrderSubMenu) {
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
