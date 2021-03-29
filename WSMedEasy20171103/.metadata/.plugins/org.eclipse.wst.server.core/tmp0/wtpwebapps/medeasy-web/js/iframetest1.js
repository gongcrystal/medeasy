$(function(){
	
	var mdiv = "<div class=\"tab-pane\" id=\"tt1\">	<iframe src=\"a\" id=\"miframe1\"></iframe></div>";
	var mdiv1 = "<div class=\"tab-pane\" id=\"tt2\">	<iframe src=\"b\" id=\"miframe2\"></iframe></div>";
	var mdiv2 = "<div class=\"tab-pane\" id=\"tt3\">	<iframe src=\"c\" id=\"miframe3\"></iframe></div>";
	
	/*var mTab = "<li id=\"lia\"><a href=\"#tt1\" data-toggle=\"tab\">widgets</a></li>";
	$("#myTab_add1").append(mTab);
	
	var mTab1 = "<li id=\"lia\"><a href=\"#tt2\" data-toggle=\"tab\">callendar</a></li>";
	$("#myTab_add1").append(mTab1);*/
	/*$.addTab("lia","tt1","Widgets");
	$.addTab("lib","tt2","callendar");
	$.addTab("lic","tt3","tools");
	 $("#lia").hide();
	 $("#lib").hide();
	 $("#lic").hide();
	 */
	
	$("#li1").click(function(){
		$.addTab("lia","tt1","Widgets","tt1");
		$("#myTabContent").append(mdiv);
		$("#tt1:hidden").show();
		
		
	});	
	$("#li2").click(function(){
		$.addTab("lib","tt2","callendar");
		$("#myTabContent").append(mdiv1);
		$("#tt1:visible").hide();
		$("#tt2:hidden").show();
	});
	
	
	$("#li3").click(function(){
		$.addTab("lic","tt3","tools");
		$("#myTabContent").append(mdiv2);
		$("#tt3:hidden").show();
		$("#tt1:visible").hide();
		$("#tt2:visible").hide();
		
	});	
	
	$("#lia").click(function(){
		alert("hi");
		$("#tt1:hidden").show();
		$("#tt2:visible").hide();
	});
	
	
	$("#lib").click(function(){
		alert("hi");
		$("#tt2:hidden").show();
		$("#tt1:visible").hide();
	});
	

	/*$("#li1").click(function(){
		$("#miframe").prop('src', 'a');
	});
	$("#li2").click(function(){
		$("#miframe").prop('src', 'b');
	});
	$("#li3").click(function(){
		$("#miframe").prop('src', 'c');
	});*/
	
	/*
	 * $("#lia").click(function(){ $("#miframe").prop('src', 'a'); });
	 */
	
	/*$("#home").click(function(){
		$("#miframe").prop('src', 'a');
	});*/
})

$.addTab = function(vId, vHref, vText,tabpane){
	var mTab = "<li"+" id="+vId+"> <a href="+vHref+" data-toggle=\"tab\">"+vText+"</a></li>";
	$("#myTab_add1").append(mTab);
	$("#"+vId).bind("click",function(){
		
		$("#"+tabpane).show();
	})
	
}
