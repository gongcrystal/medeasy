/*
Only allow open tabs with the number of maxTabNum
by HL
 */
var currentOpenTabNum=0;
var allowMaxTabNum=6;
var tabNavCollection={};
var tabHomeWidth=0;
var closedFlagWidth=18.8;
var tabNavWidth=0;
$(function() {
	
	$.ajax({
		"url" : "user/getLoginUser",
		"dataType" : "json",
		 type : "GET",
		 success : function(data) {
			 console.log("operateLevel="+data.operateLevel);

//			$("#userName").html("MAN");
			//个人，则显示欢迎页面
			 if(data.operateLevel==6){	
				 $('#jgsy').html("首页");
				 $('#if_home').attr('src',"welcome");
			 }
		 }
	})	
	
	$.genMenu(0); /* 默认调用一级菜单 */
	$.homeClick();
	$("#if_home").css('height', $(window).height()-$("#tab_pane").offset().top-30);
    //tabNavCollection["#navtab_home"]={"width":$("#navtab_home").width()};
	//在不同的分辨率下全屏的宽度不同
	tabHomeWidth=$("#navtab_home").width()+3;
    tabNavWidth=$("#tab_nav").width();

	console.log( "height: "+$("#if_home").height() +" "+ $(window).height());
    $(window).resize(function() {
        tabNavWidth=$("#tab_nav").width();
        tabHomeWidth=$("#navtab_home").width()+3;
        $.adjustTabNav();
    });
    
    
	
	
});


//设置iframe高度
function setIframeHeight(id){
	$("#"+id).css('height', ($(window).height()-$("#tab_nav").height() *2 )*1.5+'px');
}

function setFrameHeight(mframe){
	console.log($(window).height());
	$(mframe).css('height', '1500px');
}

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
	
	var prevActiveMenu; //记录上一个active的menuId; 用于active菜单的小尖
	
	$.ajax({
		/*"url" : '/medeasy-web/sys/listMenus',*/   
		"url" : '/medeasy-web/sys/listMenusByRName',
		"type" : 'POST',
		"dataType" : 'json',
		"timeout" : 20000,
		/*data : {
			parentId : parentId
		},*/
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
            if(!($("#navtab_"+mcode).length>0)&&currentOpenTabNum+1>allowMaxTabNum){
                $.gritter.add({
                    title: "<i class='ace-icon fa fa-exclamation-triangle bigger-120 red'></i><span style='color:red'>提示信息</span>",
                    text: "超过了可以打开页面的最大数量！",
                    time: 3000,
                    speed:500,
                    class_name: 'gritter-info gritter-light gritter-center'
                });
                $(".gritter-close").html(" ");/*take out "close notification"*/
                console.log("currentOpenTabNum "+ currentOpenTabNum);
                return;
            }
			$.mngTabPane("#tab_" + $(this).attr("id")); // load iframe
			var menuName = $("#a_"+$(this).attr("id")).text();
			console.log("mcode = "+mcode);
			
			/*menu active 尖*/
			$(this).addClass('active');
			if(prevActiveMenu!=null && $("#"+prevActiveMenu).hasClass('active')){
				$("#"+prevActiveMenu).removeClass('active');
			}
			prevActiveMenu = mcode;
			/*end menu active 尖*/
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
			currentOpenTabNum=currentOpenTabNum+1;//add by HL
			var icons = " <label style='float:right' class=\"field-icon\"> &nbsp; &nbsp;<i class=\"fa fa-close light black\" id=" +"close_"+menuCode+"></i></label>";
			var navtab = "<li class=\"active \" id=navtab_" + menuCode + " ><a href=\"#tab_" + menuCode + "\"  style='overflow: hidden' data-toggle=\"tab\" title='"+menuName+"'><div style='float:left;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;'>" + menuName +"</div> "+icons+ " </a></li>";
				
			/*var navtab = "<li class=\"s_navtab\" id=navtab_" + menuCode + " ><a href=\"#tab_" + menuCode + "\"  data-toggle=\"tab\" >" + menuName +" "+icons+ " </a></li>";*/
			$("#tab_nav").append(navtab);
            tabNavCollection["#navtab_"+menuCode]={"width":$("#navtab_"+menuCode).width()};
			$.adjustTabNav();
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
                currentOpenTabNum=currentOpenTabNum-1;

                $.adjustTabNav();
				/*if navtab对应的是active的被删除的话，选择剩余的index最大的active*/
				if(activeTabRemoved ==true){
					var maxchildren=$('#tab_nav').children().length-1;	
					/*console.log(maxchildren);*/
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
	},
	'adjustTabNav':function () {
        var tabTotalWidth=0;
        var autoSTabWith=0+tabHomeWidth;
        //计算一下默认宽度
        $("#tab_nav li").each(function (i,e) {
			tabTotalWidth+=$(e).width();
			if(tabNavCollection["#"+$(e).attr("id")]){
				autoSTabWith+=tabNavCollection["#"+$(e).attr("id")].width;
			}
        });
        //如果当前的屏幕宽度不够，需要重新调整宽度
        if(tabTotalWidth>tabNavWidth){
			//均分一下
            var avgWidth=(tabNavWidth-tabHomeWidth)/(currentOpenTabNum);

            $("#tab_nav li").each(function (i,e) {
            	//均分的宽度如果小于自然宽度才调整
            	if(tabNavCollection["#"+$(e).attr("id")]&&tabNavCollection["#"+$(e).attr("id")].width>=avgWidth){
                    $(e).width(avgWidth);
                    $("#"+$(e).attr("id")+" div").width(avgWidth-($("#"+$(e).attr("id")+" label").width()+23));
                }
            });
        }
        if(tabNavWidth>autoSTabWith){
            $("#tab_nav li").each(function (i,e) {
                $(e).width("");
                $("#"+$(e).attr("id")+" div").width("");
            });
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
        if(!$(tabId).find("iframe").attr("src")){//第一次请求时加载页面
            $(tabId).find("iframe").attr('src', $(tabId).find("iframe").attr("lazyLoadSrc"));
		}
        setIframeHeight2($(tabId).find("iframe").attr("id"));
	}
});

/* 点击菜单的item, 产生相应的tab和iframe页 */
$.extend({
	'genTabFrame' : function(element, component) {
		/* console.log(element.menuName); */
		var mdiv = "";
		if (element.menuType == true) {
/*
			mdiv = " <div class=\"tab-pane\" id=" + "tab_" + element.menuCode + "><iframe  onload=\"setIframeHeight(this.id)\" src=" + element.menuUrl + "  id=if_" + element.menuCode
					+ " frameborder=\"0\" onload=\"setFrameHeight(this.id)\" ></iframe></div> ";
*/
            mdiv = " <div class=\"tab-pane\" id=" + "tab_" + element.menuCode + "><iframe  onload=\"setIframeHeight2(this.id)\" lazyLoadSrc='" + element.menuUrl + "'  id=if_" + element.menuCode
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
