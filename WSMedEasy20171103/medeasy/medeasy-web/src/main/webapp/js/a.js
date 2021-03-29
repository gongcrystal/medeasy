$(function() {
	
	$("#u1").focusColor();
	
	$("#Button1").click(function(){
		console.log("hi"+$("#Text1").val()+$("#Text2").val());
		$("#Text3").val(
			$.addNum($("#Text1").val(),$("#Text2").val())
				)
	});
//	window.location.href = '/medeasy-web/download';
//	if(msieversion()){ // 是ie8 , 并且没有安装chrome框架 		
//		var agree = confirm("1检测到您当前浏览器不支持本系统，请点击\"确定\"下载并安装相关插件");
//		if(agree==true){
//			window.location.href = '/medeasy-web/download';
//		}		
//	}else{
//		alert("other browser");
//	}

})

/*function  msieversion() 
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
}*/
