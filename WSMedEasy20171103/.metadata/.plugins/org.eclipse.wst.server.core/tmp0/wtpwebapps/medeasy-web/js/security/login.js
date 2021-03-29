$(function(){
	
	$("#submit").click(function() {
		$.ajax({
			url:'/security/login',
			type: 'POST',
			data:{
				username: $("#username").val(),
				password: $("#password").val(),
			},
			success: function(data){
				console.out("ok");
			},    
			
			
		});
	})
	
	
})