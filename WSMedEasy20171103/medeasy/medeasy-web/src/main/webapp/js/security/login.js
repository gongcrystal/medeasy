$(function() {
	
	if(msieversion()){ // 是ie8 , 并且没有安装chrome框架 		
		var agree = confirm("检测到您当前浏览器不支持本系统，请点击\"确定\"下载并安装相关插件");
		if(agree==true){
			window.location.href = '/medeasy-web/download';
		}		
	}
})