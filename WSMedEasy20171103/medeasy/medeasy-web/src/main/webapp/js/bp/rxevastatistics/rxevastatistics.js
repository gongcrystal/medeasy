$(function() {

	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	// rxSelect
	$.genSelect("#rxSelect", "/medeasy-web/basic/getPatientTypes", null);
	
	// irritate Select
	$.genSelectValueIsCode("#irritateSelect", "/medeasy-web/basic/getIrritate", null);
	
	$.dataPicker2MonthSet();
	
	$.easyPieChartSet();
	
	//药品分类的选择树
	$.genDrugCategory($("#drugClassBtn"),$("#drugCategoryModal"));

	/*$.genRxSt(grid_selector, pager_selector);*/

	// 中药饮片使用 检索btn: btnSearchIncome
	/*$.searchBtn($("#btnSearchIncome"), $("#searchIncomeForm"), $("#grid-table"), "/medeasy-web/bp/herbUse")*/
		
});

$.genRxSt = function(grid_selector, pager_selector) {
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/bp/herbUse',
		mtype : 'POST',
		height : 'auto',
		weight : 600,
		colNames : [ '医院名称  ', '区名称', '期次类型', '处方数', '抽样数量','科室','医生','药品','药品使用金额（元）',
			'平均处方金额（元）','合理处方数','合理处方占比%','不合理处方数','不合理处方占比%',
			'不规范处方数','用药不适宜处方数','超常处方数','抗菌药物处方数','抗菌药处方占比%','抗菌药物处方不合理数','不合理抗菌药处方占比%',
			'问题代码'],
		colModel : [ {	name : 'hospitalName',index : 'hospitalName',	width : 150,sorttype : "String",editable : false}, 
			{name : 'areaName',index : 'areaName',	width : 90,editable : false},
			{name : 'patientType',index : 'patientType',	width : 90,editable : false},
			{name : 'rxAmount',index : 'rxAmount',	width : 100, editable : false},
			{name : 'sampleAmount',index : 'sampleAmount',	width : 150, editable : false},
			
			{name : 'deptAmount',index : 'deptAmount',	width : 90,editable : false},
			{name : 'drAmount',index : 'drAmount',	width : 90,editable : false},
			{name : 'drugAmount',index : 'drugAmount',	width : 100, editable : false},
			{name : 'mamount',index : 'mamount',	width : 150, editable : false},
			
			{name : 'amountAvgRx',index : 'amountAvgRx',	width : 90,editable : false},
			{name : 'okAmount',index : 'okAmount',	width : 90,editable : false},
			{name : 'perOkAmount',index : 'perOkAmount',	width : 100, editable : false},
			{name : 'nonOkamount',index : 'nonOkamount',	width : 150, editable : false},
			
			{name : 'perNonOkAmount',index : 'perNonOkAmount',	width : 90,editable : false},
			{name : 'unReasonableAmount1',index : 'unReasonableAmount1',	width : 90,editable : false},
			{name : 'unReasonableAmount2',index : 'unReasonableAmount2',	width : 100, editable : false},
			{name : 'unReasonableAmount3',index : 'unReasonableAmount3',	width : 150, editable : false},
			{name : 'abxAmount',index : 'abxAmount',	width : 150, editable : false},
			{name : 'perAbxAmount',index : 'perAbxAmount',	width : 150, editable : false},
			{name : 'nonOkAbxAmount',index : 'nonOkAbxAmount',	width : 150, editable : false},
			{name : 'problemCode',index : 'problemCode',	width : 150, editable : false},
		],

		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		altRows : true,
		pager : pager_selector,
		multiselect : false,
		multiboxonly : false,
		caption : "中药饮片使用情况统计"
	});
}
