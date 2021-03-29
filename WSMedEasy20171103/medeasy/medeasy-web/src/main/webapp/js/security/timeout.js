$(function() {
	swal({
		  title: "登陆超时", 
		  text: "", 
		  type: "warning",
		  showCancelButton: false, 
		  /*confirmButtonColor: "#DD6B55",*/
		  confirmButtonText: "确定！", 
		  closeOnConfirm: false
		},
		function(){  
			window.location.href = "/medeasy-web/login";
		});		
})