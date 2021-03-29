$(function() {
	$.initFormData();
	
	getComprehensiveIndex();
	
	$("#btnSearchAbxComprehensiveIndex").click(function() {
//		var mobj = $("#searchForm").serializeObject();
		getComprehensiveIndex();
	});

}); // end

function getComprehensiveIndex(){
	var dataF1 = $("#searchForm").serializeObject();
	var mdata = JSON.stringify(dataF1);
	$.ajax({
		type: "post",
		async : false,
		url : "/medeasy-web/bp/abxComprehensiveIndexData",
		contentType : "application/json; charset=UTF-8",
		data: mdata,
		dataType: "json",
		success:function(result){
			if(result != null){
				$("#perAbxVariety").text(result.perAbxVariety);
				$("#abxAmount").text(result.abxAmount);
				$("#perAbxAmount").text(result.perAbxAmount);
				$("#abxAmountPercent").text(result.abxAmountPercent);
				$("#abxRxAmountPercent").text(result.abxRxAmountPercent);
				$("#outpatientAbxIvPercent").text(result.outpatientAbxIvPercent);
				$("#outpatientAbxPercent").text(result.outpatientAbxPercent);
				$("#abxUnrestrictPercent").text(result.abxUnrestrictPercent);
				$("#abxRestrictPercent").text(result.abxRestrictPercent);
				$("#abxIvCasePercent").text(result.abxIvCasePercent);
			}else{
				$("#perAbxVariety").text(0);
				$("#abxAmount").text(0);
				$("#perAbxAmount").text(0);
				$("#abxAmountPercent").text(0);
				$("#abxRxAmountPercent").text(0);
				$("#outpatientAbxIvPercent").text(0);
				$("#outpatientAbxPercent").text(0);
				$("#abxUnrestrictPercent").text(0);
				$("#abxRestrictPercent").text(0);
				$("#abxIvCasePercent").text(0);
			}
		},
		error:function(errorMsg,er){
            $("#perAbxVariety").text(0);
            $("#abxAmount").text(0);
            $("#perAbxAmount").text(0);
            $("#abxAmountPercent").text(0);
            $("#abxRxAmountPercent").text(0);
            $("#outpatientAbxIvPercent").text(0);
            $("#outpatientAbxPercent").text(0);
            $("#abxUnrestrictPercent").text(0);
            $("#abxRestrictPercent").text(0);
            $("#abxIvCasePercent").text(0);
		}
	});
};