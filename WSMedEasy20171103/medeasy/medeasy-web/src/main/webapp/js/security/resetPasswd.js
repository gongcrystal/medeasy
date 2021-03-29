$(function() {
	var oldPs, newPs, newPs1;

	$("#pwdForm").validate({
		rules : {			
			password: {
				required : true,
			},
			passwordnew : {
				required : true,
				/*minlength : 6*/
			},
			passwordnew1 : {
				required : true,
				/*minlength : 6,*/
				equalTo : "#passwordnew"
			},
		},
		messages : {
			
			password: {
				required : "请输入密码",
			},
			passwordnew : {
				required : "请输入密码",
				/*minlength : "密码长度不能小于 6 个字母"*/
			},
			passwordnew1 : {
				required : "请输入密码",
			/*	minlength : "密码长度不能小于 6 ",*/
				equalTo : "两次密码输入不一致"
			},
		}

	})

	$("#btnSavePasswd").click(function() {
		newPs = $("#passwordnew").val();
		newPs1 = $("#passwordnew1").val();
	/*	console.log("newPs=" + newPs);
		console.log("length: "+newPs.length);*/		
			
		if (newPs != newPs1 ) {
			
				swa_err("两次输入的密码不一致","");
			/*if(newPs.length < 6)
				swa_err("密码长度小于6","");*/
		} else {
			// 验证输入的当前密码是否正确 - verifyPwd
			$.ajax({
				"url" : "/medeasy-web/user/verifyPwd",
				"dataType" : "json",
				type : "POST",
				data : {
					password : $("#password").val()
				},
				success : function(data) {
					if (!data.success) {
						swa_err("当前密码不正确");
					} else {

						// start update pwd
						var dataF = $("#pwdForm").serializeObject();
						dataF.password=$("#passwordnew").val(); 
						/* dataF.password= $("#passwordnew").val(); */

						$.ajax({
							"url" : "/medeasy-web/user/updateSingSecUser",
							"dataType" : "json",
							contentType : 'application/json; charset=UTF-8',
							type : "POST",
							data : JSON.stringify(dataF),
							success : function(data) {
								if (!data.success) {
									swa_err(data.prompt);
								} else {
									swa_suc("保存成功");
								}
							}
						}) // end update pwd
					}
				}
			})
		}

	});

})