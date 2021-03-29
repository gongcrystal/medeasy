$.hLRadioSet = function(){
	$('input[type=radio][name=hLDrugCategory]').click(function() {

		if ($(this).data('wasChecked') == true) {
			$(this).data("wasChecked", false);
			console.log("hi");
			this.checked = false;
		} else {
			$(this).data("wasChecked", false);
			$(this).data("wasChecked", true);
			this.checked = true;
		}

	});
}


/*根据登陆用户的用户名 ， 检索出所在医院的dept , 主要用于hospital admin 的检索form ,
对于area admin , sysadmin , 很可能没有对应的医院;*/
$.getDeptsByLoginUsername =function (selectComp,url){	
	
	$.ajax({
		"url" :url,
		"dataType" : "json",
		 type : "POST",		
		success : function(data) {	
			
			if(data.length>=1){
				/* $(selectComp).append('<option value="#">科室:请选择</option>');*/
				 for(var i=0; i<data.length; i++){				 
					 $(selectComp).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
				 }
				 
				 if(data.length==1){
					 $(selectComp + " option[value=" + deptCode + "]").prop('selected', true);
					}
			}
		} 				
	})
}
//显示来自drug的所有药品
$.listDrugItems = function(component, url){
	$.ajax({
		"url" :url,
		"dataType" : "json",
		 type : "GET",		
		success : function(data) {			
			$.genZTree(data, $("#mzTree1"));			
			$("#mzTree1").css("display","none");
			$.genZTree(data, $("#mzTree2"));
		} 				
	})	
}

$.searchKeyDownGeneral = function(cmp,mztree){
	
	var setting = {
			view: {fontCss: setFontCss}, 
			check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		/*
		 * callback : { onCheck : zTreeOnCheck },
		 */
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	
	var nodes=null;
	$(cmp).keydown(function() {	
		if (event.keyCode === 13) {			
			var treeObj = $.fn.zTree.getZTreeObj(mztree);	
			if(nodes!=null){
				highLightNodes(treeObj, nodes,false);
			}
			var searchKeyword = $(cmp).val();
			
			var isHlight=false;
			if(searchKeyword==""){
				isHlight = false;
			}else{
				isHlight = true;
			}
				
			nodes = treeObj.getNodesByParamFuzzy("name", $.trim(searchKeyword));	
			
			$.fn.zTree.init($("#mzTree1"),setting, nodes);
			nodes = treeObj.getNodesByParamFuzzy("name", $.trim(searchKeyword));
			highLightNodes(treeObj, nodes,isHlight);
			$("#mzTree1").css("display","block");
			
		}
	});
}

$.searchKeyDownGeneral1 = function(cmp,mztree){
	
	
	
	var setting = {
			view: {fontCss: setFontCss}, 
			check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},	
		
	};
	
	var nodes=null;
	
	$(cmp).keydown(function() {	
		if (event.keyCode === 13) {	
			
			var treeObj1Pre = $.fn.zTree.getZTreeObj("mzTree1");
			var checkedNodesPre = treeObj1Pre.getCheckedNodes(true);
			/*console.log("checkedNodesPre: "+checkedNodesPre.length);*/
			
			var treeObj = $.fn.zTree.getZTreeObj("mzTree2");	//用于保存初始状态
			
			var searchKeyword = $(cmp).val();	
			nodes = treeObj.getNodesByParamFuzzy("name", $.trim(searchKeyword));
			/*console.log("before nodes"+nodes.length);*/
			/*$.merge(nodes,checkedNodesPre)*/
			
			$.merge(checkedNodesPre,nodes);			
			
			/*console.log("end nodes"+nodes.length);*/
			var treeObj1 = $.fn.zTree.getZTreeObj("mzTree1");
			treeObj1.addNodes(null, 0,checkedNodesPre);
			highLightNodes(treeObj1, nodes,true)
			
			/*$.fn.zTree.init($("#mzTree1"),setting, nodes);*/
			
			$.fn.zTree.init($("#mzTree1"),setting, checkedNodesPre);	
			
			
			$("#mzTree1").css("display","block");
			
		}
	});
}

function  msieversion() 
{
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0) // If Internet Explorer, return version number
    {
        return true;
    }
    else  // If another browser, return 0
    {      
        return false;
    }   
}
