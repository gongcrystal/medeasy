$(function() {
	
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
	 
	
	 //Range picker
/*	 $('.input-daterange').datepicker({
		 autoclose:true,
		 defaultDate : new Date(),
		 todayHighlight : true ,
		 format: "yyyy-mm-dd"			
	 }); */	 
	$.dataPicker2MonthSet();
	 
	 $.genHerbalUse(grid_selector,pager_selector);
	 
	//中药饮片使用 检索btn: btnSearchIncome
	$.searchBtn($("#btnSearchIncome"),$("#searchIncomeForm"),$("#grid-table"),"/medeasy-web/bp/herbUseSearch")
	 
	 
	 $("#grid-table").jqGrid('setGroupHeaders', {
		    useColSpanStyle: true,
		    groupHeaders:[
		        {startColumnName:'decoction', numberOfColumns:2,  titleText: '中药饮片处方数'},
		        {startColumnName:'decoctionAmount', numberOfColumns: 2, titleText: '中药饮片总金额(元)'} 
		    ] 
		})
	 
});


$.genHerbalUse = function(grid_selector, pager_selector) {
	$("#grid-table").jqGrid(
			{
				datatype : 'json',
				contentType : "application/json; charset=UTF-8",
				url : '/medeasy-web/bp/herbUse',
				mtype : 'POST',				
				height : 'auto',
				weight : 600,
				colNames : [ '医院名称  ', '代煎', '未代煎', '代煎', '未代煎' ],
				colModel : [
					{name : 'name',index : 'name',	width : 150,	sorttype : "Integer",editable : false},
					{name : 'decoction',index : 'decoction',	width : 90,editable : false},
					{name : 'nonDecoction',index : 'nonDecoction',	width : 100, editable : false},
					{name : 'decoctionAmount',index : 'decoctionAmount',	width : 150, editable : false},
					{name : 'nonDecoctionAmount',index : 'nonDecoctionAmount',	width : 120, editable : false},
					
				],

				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				altRows : true,
				/*pager : pager_selector,*/
				multiselect : false,
				multiboxonly : false,
				caption : "中药饮片使用情况统计"
			});
}
