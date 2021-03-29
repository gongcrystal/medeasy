$(function(){
	$.genMenu("0");
})

$.genMenu = function(parentId){
	
	 $.ajax({
			"url" : '/sys/listMenus',
			"type" : 'POST',
			"dataType" : 'json',
			"timeout" : 20000,			
			data : {parentId : parentId},			
			success: function(data){				
			}
	  });
};