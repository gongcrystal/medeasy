$(function(){

	$("#imgtest").click(function(){
		
		$("#testdiv").after('<div th:insert="frame::tt2">good</div>');
		/*$("#testdiv").append('<div th:insert="frame::top">the new guy</div>');*/
	});
	
	$("#gallery").click(function(){
		alert("hi");	
		$("#myTabContent").after('<div th:insert="frame::tt2">good2</div>');
	});
	
	var $div=$("<div id=\"testdiv\" th:insert=\"frame::tt2\">good1</div>");
	$("#myTabContent").append($div);
	
	
	
	/*$('#imgtest').click(function(){
		
		$('#tt0').hide();
		$('#thzzqs').hide();
	});
	
	$('#imgtest1').click(function(){
		
		$('#tt1').hide();
		$('#thzzqs1').hide();
	});
	
	$('#imgtest2').click(function(){
	
		$('#tt2').hide();
		$('#thzzqs2').hide();
		
	});
	
	$('#li3').hide();
	$('#li1').hide();
	$('#li2').hide();
	
	
	$('#mclick').click(function(){
		console.log("hi, morning!");
		alert("hi morning!");
		
	}) ; 
	
	
	$('#gallery').click(function(){	
	
		$("#tt0").show();
		$('#li1').show();
		
	}) ;
	$('#calendar').click(function(){	
		
		$("#tt1").show();
		
		$('#li2').show();
	}) ;
	
	$('#Statistic').click(function(){
	
		$("#tt2").show();
		$('#li3').show();
	}) ;*/
	
})