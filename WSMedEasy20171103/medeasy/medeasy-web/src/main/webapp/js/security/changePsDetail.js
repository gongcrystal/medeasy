$(function() {
	
	$.ajax({
		"url" :"/medeasy-web/user/getPDetail",
		"dataType" : "json",
		 type : "GET",
		success : function(data) {	
			/*console.log("data = "+JSON.stringify(data));*/
			
			 $("form input[type='text']").each(function(){
				/* console.log(data.rows);*/
			        $(this).val(data.rows[$(this).attr("name")] ? data.rows[$(this).attr("name")] : "");
			     });			
		}
	})
	
	$("#btnModifyPsDetail").click(function(){
		$("#mobile1").prop("readonly", false);
		 $('#mobile1').focus();
	});
	
	$("#btnUpdatePsDetail").click(function(){
		var dataF = $("#psDetail").serializeObject();
		dataF.mobile=$('#mobile1').val();
	
		$.ajax({
			"url" :"/medeasy-web/user/updateSingSecUser",
			"dataType" : "json",
			 type : "POST",
			 contentType : 'application/json; charset=UTF-8',
			 data:JSON.stringify(dataF),
			 success : function(data) {
				 if (!data.success) {
						swa_err(data.prompt);
					} else {
						swa_suc("保存成功");						
					}				
			}
		})
	})
	
})