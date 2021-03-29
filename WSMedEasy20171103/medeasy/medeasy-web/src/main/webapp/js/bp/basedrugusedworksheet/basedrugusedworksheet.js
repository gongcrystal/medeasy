$(function() {
	
	/*swa("","请选择起始日期！");*/
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	//区域的select
	/*$.genSelect("#areaSelect","/medeasy-web/basic/getDists",null);*/
	$.genSelectArea("#areaSelect", "/medeasy-web/basic/getDists", null);
	//hospital的select
	/* $.genSelectwithChosen("#hospital","/medeasy-web/basic/getHospitalsByRole",null);*/
	 $.genSelectwithChosenHospital("#hospital", "/medeasy-web/basic/getHospitalsByRole", null);
	//the change of area triggers hospital
	 $.selectChangeArea2Hospital($("#areaSelect"),$("#hospital"),"/medeasy-web/basic/getHospitalsByArea");
	 
	 
	//基本药物制度工作基药配备使用统计表 检索btn: btnSearchIncome
	$.searchBtn($("#btnSearchIncome"),$("#searchIncomeForm"),$("#grid-table"),"/medeasy-web/bp/baseDrugWorksheetSearch")
	
	 //Range picker
/*	 $('.input-daterange').datepicker({
		 autoclose:true,
		 defaultDate : new Date(),
		 todayHighlight : true ,
		 format: "yyyy-mm-dd"			
	 });*/ 
	
	$.dataPicker2MonthSet();
	 
	 $.genWorkSheet(grid_selector,pager_selector);	 
	 
	//中药饮片使用 检索btn: btnSearchIncome
	$.searchBtn($("#btnSearchIncome"),$("#searchIncomeForm"),$("#grid-table"),"/medeasy-web/bp/baseDrugWorksheet")
	 
	
	//三级表头
	 jQuery("#grid-table").jqGrid("setGroupHeaders",{
		 useColSpanStyle: false,
		 groupHeaders:[
		     {startColumnName:'cVariety',numberOfColumns:4,titleText:'基本药品采购情况'},
		     {startColumnName:'cDVariety',numberOfColumns:4,titleText:'基本药物配送情况'},		     
		     {startColumnName:'uCVariety',numberOfColumns:8,titleText:'基本药物配送情况'}
		  ]	
	});		
	 
	 //二级表头
	 $("#grid-table").jqGrid('setGroupHeaders', {
		    useColSpanStyle: true,
		    groupHeaders:[
		        {startColumnName:'cVariety', numberOfColumns:2,  titleText: '采购品种(个)'},
		        {startColumnName:'cPrice', numberOfColumns: 2, titleText: '采购金额(万元)'},
		        
		        {startColumnName:'cDVariety', numberOfColumns:2,  titleText: '验收品种(个)'},
		        {startColumnName:'cDPrice', numberOfColumns: 2, titleText: '验收金额(万元)'} ,
		        
		        {startColumnName:'uCVariety', numberOfColumns:2,  titleText: '在用基本药品品种数(个)'},
		        {startColumnName:'uCPrice', numberOfColumns: 4, titleText: '基本药物销售金额'} ,
		        
		        {startColumnName:'usedCDecline', numberOfColumns: 2, titleText: '国家指导售价相比平均降幅%'} 
		        
		    ] 
		});
	 
});


$.genWorkSheet = function(grid_selector, pager_selector) {
	$("#grid-table").jqGrid(
			{
				datatype : 'json',
				contentType : "application/json; charset=UTF-8",
				url : '/medeasy-web/bp/baseDrugWorksheet',
				mtype : 'POST',				
				height : 'auto',
				weight : 600,
				colNames : [ '时间',
							'国家目录', '省医保目录', 
							 '国家目录', '省增补目录',
							'国家目录', '省增补目录', 
							'国家目录', '省增补目录',
							'国家目录', '省增补目录',
							'国家目录', '省增补目录',
							'注射剂占比','抗菌药占比',
							'国家目录', '省增补目录'],
				colModel : [
					{name : 'fullDay',index : 'fullDay',	width : 150,	/*sorttype : "Integer",*/editable : false,
						/*formatter : function(cellvalue, options, row) {
							return new Date(cellvalue).Format("yyyy-MM-dd");
						},*/
					},
					{name : 'cVariety',index : 'cVariety',	width : 90,editable : false},
					{name : 'pVariety',index : 'pVariety',	width : 90,editable : false},
					{name : 'cPrice',index : 'cPrice',	width : 100, editable : false},
					{name : 'pPrice',index : 'pPrice',	width : 150, editable : false},
					
					{name : 'cDVariety',index : 'cDVariety',	width : 90,editable : false},
					{name : 'pDVariety',index : 'pDVariety',	width : 90,editable : false},
					{name : 'cDPrice',index : 'cDPrice',	width : 100, editable : false},
					{name : 'pDPrice',index : 'pDPrice',	width : 150, editable : false},
					
					{name : 'uCVariety',index : 'uCVariety',	width : 90,editable : false},
					{name : 'uPVariety',index : 'uPVariety',	width : 90,editable : false},
					{name : 'uCPrice',index : 'uCPrice',	width : 100, editable : false},
					{name : 'uPPrice',index : 'uPPrice',	width : 150, editable : false},
					
					{name : 'usedIv',index : 'usedIv',	width : 90,editable : false},
					{name : 'usedAbx',index : 'usedAbx',	width : 90,editable : false},
					{name : 'usedCDecline',index : 'usedCDecline',	width : 100, editable : false},
					{name : 'usedPDecline',index : 'usedPDecline',	width : 150, editable : false}
					
				],
				autowidth: true,
			    shrinkToFit: true,

				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				altRows : true,
				/*pager : pager_selector,*/
				multiselect : false,
				multiboxonly : false,
				caption : "基本药物制度工作基药配备使用统计表(江苏省)"
			});
}
