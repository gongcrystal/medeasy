$(function() {
	var grid_js = "#gridtb_js531";
	var pager_selector = "#grid-pager";
	
	$.dataPicker2MonthSet();
	
	// 去掉herbaliv/nutrition
	$("#lableNutr").css("display", "none");
	$("#lableHerbalIv").css("display", "none");	
	
	// 江苏省健康531临床指标
	$.searchBtn($("#btnSearchIncome"), $("#searchIncomeForm"), $("#gridtb_js531"), "/medeasy-web/druguse/jiangsuHealthSearch"); //2018

	$.genJs531Grid(grid_js, pager_selector);

	// 区域的select
	/*$.genSelect("#areaSelect", "/medeasy-web/basic/getDists", null);*/
	$.genSelectArea("#areaSelect", "/medeasy-web/basic/getDists", null);
	// hospital的select
	/*$.genSelectwithChosen("#hospital", "/medeasy-web/basic/getHospitalsByRole", null);*/
	$.genSelectwithChosenHospital("#hospital", "/medeasy-web/basic/getHospitalsByRole", null);
	// the change of area triggers hospital
	$.selectChangeArea2Hospital($("#areaSelect"), $("#hospital"), "basic/getHospitalsByArea");

	// rx selection
	$.genSelect("#rxSelect", "/medeasy-web/basic/getPatientTypes", null);

	// rx selection
	$.genSelect("#rxSelect1", "/medeasy-web/basic/getPatientTypes", null);

	// Range picker
	/*$.dataPicker2MonthSet();*/
	/*$.dateRange();*/ 
});


$.genJs531Grid = function(grid_selector, pager_selector) {
	$("#gridtb_js531").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/druguse/jiangsuHealth',
		mtype : 'POST',
		height : 'auto',
		weight : 600,
		colNames : [ '项目名称  ', '总费用(元）', '金额总占比%', '人均药品使用金额（元）', '平均处方金额（元）' ],
		colModel : [ {
			name : 'name',
			index : 'name',
			width : 150,
			sorttype : "Integer",
			editable : false
		}, {
			name : 'mamount',
			index : 'mamount',
			width : 90,
			editable : false
		}, {
			name : 'amountPerAreaTotal',
			index : 'amountPerAreaTotal',
			width : 100,
			editable : false
		}, {
			name : 'amountAvgPt',
			index : 'amountAvgPt',
			width : 150,
			editable : false
		}, {
			name : 'amountAvgRx',
			index : 'amountAvgRx',
			width : 120,
			editable : false
		},

		],
		
		autowidth: true,
        shrinkToFit: true,

		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		altRows : true,
		multiselect : false,
		multiboxonly : false,
		caption : "江苏省健康531临床指标"
	});
}

