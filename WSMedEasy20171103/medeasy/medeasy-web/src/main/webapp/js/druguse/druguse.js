$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	/*
	 * var grid_drugIncome = "#gridtb_drugincome"; var grid_js =
	 * "#gridtb_js531";
	 */
	var checkedNodesStr = "";
	var zTreeNodes = "";
/*	var mjson =
		[
		    {
		        "id": "1",
		        "elementName": "libgtop2-devel-2.14.4-3.el5",
		        "subCategory": "patch",
		        "isEqual": false,
		        "isPrasentinXml1": false,
		        "isPrasentinXml2": false,
		        "isPrasentinXml3": true
		    }
		    ]
	$("#exportExcel").click(function(){
	
		ExportToExcel(mjson,"My Test", true);
	})*/
	
	$("#exportExcel").click(function(){
		var allRowInGrid= $('#grid-table').jqGrid('getGridParam','data');
		console.log("allRowInGrid: "+allRowInGrid);
	});

	$.dataPicker2MonthSet();

	// 去掉herbaliv/nutrition
	$("#lableNutr").css("display", "none");
	$("#lableHerbalIv").css("display", "none");
	/* $("#rdCancel").css("display", "none"); */

	// 显示药品分类中， 来自drug表中的所有药品item
	$.listDrugItems("#drugItemlist", "bp/listDrugNodes");

	// 以前用来做快速检索的
	/*
	 * $('input[type=radio][name=hLDrugCategory]').change(function() {
	 * 
	 * var rdHLValue = $('input:radio:checked').val(); console.log("radio: " +
	 * rdHLValue); // 提交检索，并且refresh table; var dataH =
	 * $("#searchHLDrugUseForm").serializeObject();
	 * 
	 * $("#grid-table").jqGrid("setGridParam", { postData : dataH, url :
	 * "/medeasy-web/druguse/drugUseStHospital", }).trigger("reloadGrid");
	 * 
	 * });
	 */
	
	$.hLRadioSet();
	/*$('input[type=radio][name=hLDrugCategory]').click(function() {

		if ($(this).data('wasChecked') == true) {
			$(this).data("wasChecked", false);
			console.log("hi");
			this.checked = false;
		} else {
			$(this).data("wasChecked", false);
			$(this).data("wasChecked", true);
			this.checked = true;
		}

	});*/

	/* 第二列的检索 */
	// 药品分类的选择树
	$.genDrugCategory($("#drugClassBtn"), $("#drugCategoryModal"));

	// 药品分类
	$.ajax({
		"url" : "/medeasy-web/bp/listDrugCategoryNodes",
		"dataType" : "json",
		success : function(data) {
			zTreeNodes = data;
			$.genZTree(zTreeNodes, $("#mzTree"));
		}
	})

	$.searchKeyDown("#drugCKeyword");

	$.searchKeyDownGeneral1("#drutItemName", "mzTree1"); // 用于具体药品类的回车检索
	$.genDrugCategory($("#drugClassBtn"), $("#drugCategoryModal"));

	// 没有选择药品分类
	/*
	 * $("#btnSearchIncome1").click(function() { var dataH; console.log("mytest:
	 * "+checkedNodesStr); if(checkedNodesStr!=""){ dataH =
	 * $("#searchDrugUseForm").serializeObject(); dataH.checkedNodesStr =
	 * $.trim(checkedNodesStr); dataH.areaCode = $("#areaSelect").val();
	 * dataH.hospitalCode = $("#hospitalSelectSearchId").val(); dataH.startDate =
	 * $("#startDate").val(); dataH.endDate = $("#endDate").val(); }else{ dataH =
	 * $("#searchIncomeForm").serializeObject(); }
	 * 
	 * $("#grid-table").jqGrid("setGridParam", { postData : dataH, url :
	 * "/medeasy-web/druguse/drugUseStHospital", }).trigger("reloadGrid"); });
	 */

	$("#drugItemSearchBtn").click(function() {
		// 点击检索btn
		$("#drugCategoryModal").modal("hide");
	})

	// 确定后，提交药品分类名称
	$("#btnDrugCategory").click(function() {
		/*
		 * var treeObj = $.fn.zTree.getZTreeObj("mzTree"); var checkedNodes =
		 * treeObj.getChangeCheckedNodes(); checkedNodesStr = ""; for (var i =
		 * 0; i < checkedNodes.length; i++) { if (checkedNodes[i].isParent ==
		 * false) { if (i != checkedNodes.length - 1) { checkedNodesStr +=
		 * checkedNodes[i].name + "-"; console.log("crystal:
		 * "+checkedNodes[i].id); } else { checkedNodesStr +=
		 * checkedNodes[i].name; } } }
		 */
		// 点击检索btn
		$("#drugCategoryModal").modal("hide");

	})

	/* end of 第二列的检索 */

	/* 检索btnSearchIncome trigger 药品收入金额统计 && 江苏省健康531临床指标 */
	// 药品收入金额统计 btnSearchIncome
	/*
	 * $.searchBtn($("#btnSearchIncome"), $("#searchIncomeForm"),
	 * $("#gridtb_drugincome"), "/medeasy-web/druguse/drugIncome");
	 */// 2018
	// 江苏省健康531临床指标
	/*
	 * $.searchBtn($("#btnSearchIncome"), $("#searchIncomeForm"),
	 * $("#gridtb_js531"), "/medeasy-web/druguse/jiangsuHealth");
	 */// 2018
	/*-------druguse:药品使用按医院统计---------*/
	/* $.searchBtn($("#btnSearchIncome"),$("#searchDrugUseForm"),$("#grid-table"),"/medeasy-web/druguse/drugUseStHospital") */// 2018
	$.searchBtn4DrugUse($("#btnSearchIncome"), $("#searchIncomeForm"), $("#grid-table"), "/medeasy-web/druguse/drugUseStHospitalSearch");

	$.genDrugUseGrid(grid_selector, pager_selector);
	/*
	 * $.genDrugIncomeGrid(grid_drugIncome, pager_selector);
	 * $.genJs531Grid(grid_js, pager_selector);
	 */

	// 区域的select
	/* $.genSelect("#areaSelect", "/medeasy-web/basic/getDists", null); */
	$.genSelectArea("#areaSelect", "/medeasy-web/basic/getDists", null);

	// hospital的select
	/*
	 * $.genSelectwithChosen("#hospital",
	 * "/medeasy-web/basic/getHospitalsByRole", null);
	 */
	$.genSelectwithChosenHospital("#hospital", "/medeasy-web/basic/getHospitalsByRole", null);
	// the change of area triggers hospital
	$.selectChangeArea2Hospital($("#areaSelect"), $("#hospital"), "basic/getHospitalsByArea");

	// rx selection
	$.genSelect("#rxSelect", "/medeasy-web/basic/getPatientTypes", null);

	// rx selection
	$.genSelect("#rxSelect1", "/medeasy-web/basic/getPatientTypes", null);

	// Range picker
	/* $.dataPicker2MonthSet(); */
	/* $.dateRange(); */
});

$.searchBtn4DrugUse = function(searchButton, searchform, gridTable, url) {

	searchButton.click(function() {

		var treeObj = $.fn.zTree.getZTreeObj("mzTree");
		var checkedNodes = treeObj.getChangeCheckedNodes();
		checkedNodesStr = "";
		for (var i = 0; i < checkedNodes.length; i++) {
			if (checkedNodes[i].isParent == false) {
				if (i != checkedNodes.length - 1) {
					/* checkedNodesStr += checkedNodes[i].name + "-"; */
					checkedNodesStr += checkedNodes[i].id + ",";
					/* console.log("crystal: "+checkedNodes[i].id); */
				} else {
					checkedNodesStr += checkedNodes[i].id;
				}
			}
		}

		// 具体药品的localdrugid

		var treeObj1 = $.fn.zTree.getZTreeObj("mzTree1");
		/* var checkedNodes1 = treeObj1.getChangeCheckedNodes(); */
		var checkedNodes1 = treeObj1.getCheckedNodes(true);
		/* console.log("checkedNodes1.length="+checkedNodes1.length); */
		var checkedNodesStr1 = "";
		for (var i = 0; i < checkedNodes1.length; i++) {
			if (i != checkedNodes1.length - 1) {
				checkedNodesStr1 += checkedNodes1[i].id + ",";
			} else {
				checkedNodesStr1 += checkedNodes1[i].id;
			}
		}
		/* console.log("checkedNodesStr1="+checkedNodesStr1); */

		var dataH = searchform.serializeObject();
		dataH.checkedNodesStr = checkedNodesStr
		dataH.localDrugId = checkedNodesStr1; // 具体的药品名称对应的drug_local_id
		dataH.startDate = $("#startDate").val();
		dataH.endDate = $("#endDate").val();

		var hLDrugCty = $('input[name=hLDrugCategory]:checked').val();

		if (!$('input[type=radio][name=hLDrugCategory]').is(':checked')) {
			dataH.hLDrugCategory = null;
		} 

		gridTable.jqGrid("setGridParam", {
			postData : dataH,
			url : url
		}).trigger("reloadGrid");

		/* start 提出要保留 2018-02-02 */

		// 下次打开modal的时候， 把上次的数据清空
		/*
		 * $("#drutItemName").val(""); $("#mzTree1").css("display","none");
		 */

		/* 药品分类 */
		/* $("#drugCKeyword").val(""); */
		// 去掉highlight
		// 收起树 -
		/*
		 * $.ajax({ "url" : "/medeasy-web/bp/listDrugCategoryNodes", "dataType" :
		 * "json", success : function(data) { zTreeNodes = data;
		 * $.genZTree(data, $("#mzTree")); } })
		 */

		/* end 提出要保留 2018-02-02 */
	})
}

/*
 * function drugUseSearch(postData) {
 * 
 * $("#btnSearchIncome1").click(function() {
 * 
 * $("#grid-table").jqGrid("setGridParam", { postData : postData, url :
 * "/medeasy-web/druguse/drugUseStHospital", }).trigger("reloadGrid"); }); }
 */

/*
 * $.genJs531Grid = function(grid_selector, pager_selector) {
 * $("#gridtb_js531").jqGrid({ datatype : 'json', contentType :
 * "application/json; charset=UTF-8", url :
 * '/medeasy-web/druguse/jiangsuHealth', mtype : 'POST', height : 'auto', weight :
 * 600, colNames : [ '项目名称 ', '总费用(元）', '金额总占比%', '人均药品使用金额（元）', '平均处方金额（元）' ],
 * colModel : [ { name : 'name', index : 'name', width : 150, sorttype :
 * "Integer", editable : false }, { name : 'mamount', index : 'mamount', width :
 * 90, editable : false }, { name : 'amountPerAreaTotal', index :
 * 'amountPerAreaTotal', width : 100, editable : false }, { name :
 * 'amountAvgPt', index : 'amountAvgPt', width : 150, editable : false }, { name :
 * 'amountAvgRx', index : 'amountAvgRx', width : 120, editable : false }, ],
 * 
 * autowidth: true, shrinkToFit: true,
 * 
 * viewrecords : true, rowNum : 10, rowList : [ 10, 20, 30 ], altRows : true,
 * multiselect : false, multiboxonly : false, caption : "江苏省健康531临床指标" }); }
 */

/*
 * $.genDrugIncomeGrid = function(grid_selector, pager_selector) {
 * $("#gridtb_drugincome").jqGrid({ datatype : 'json', contentType :
 * "application/json; charset=UTF-8", url : '/medeasy-web/druguse/drugIncome',
 * mtype : 'POST', height : 'auto', weight : 600, colNames : [ '项目名称 ',
 * '总费用(元）', '金额总占比%', '人均药品使用金额（元）', '平均处方金额（元）', '药品周转率%' ], colModel : [ {
 * name : 'drugItemName', index : 'drugItemName', width : 150, sorttype :
 * "Integer", editable : false }, { name : 'mamount', index : 'mamount', width :
 * 100, editable : false }, { name : 'amountPerAreaTotal', index :
 * 'amountPerAreaTotal', width : 90, editable : false }, { name : 'amountAvgPt',
 * index : 'amountAvgPt', width : 150, editable : false }, { name :
 * 'amountAvgRx', index : 'amountAvgRx', width : 120, editable : false }, { name :
 * 'turnoverRate', index : 'turnoverRate', width : 100, editable : false } ],
 * autowidth: true, shrinkToFit: true,
 * 
 * viewrecords : true, rowNum : 10, rowList : [ 10, 20, 30 ], pager :
 * pager_selector, altRows : true, multiselect : false, // multikey: "ctrlKey",
 * multiboxonly : false, caption : "药品使用金额统计" }); }
 */

/*
 * $.genDrugUseGrid = function(grid_selector, pager_selector) {
 * 
 * $("#grid-table").jqGrid( { datatype : 'json', contentType :
 * "application/json; charset=UTF-8", contentType : "application/json", url :
 * '/medeasy-web/druguse/drugUseStHospital', mtype : 'POST',
 * 
 * height : 'auto', weight : 600, colNames : [ '排名', '医院名称', '区名称', '就诊人次',
 * '处方数', '药品品种数', '药品使用金额(元)', '金额总占比%', '药占比%', '人均药品使用金额(元)', '人均药品品种数',
 * '大处方百分率%', '平均处方金额(元)', '人均用药天数(天)', '用药天数(天)', '药品周转率%', '科室', '医师' ],
 * colModel : [ { name : 'rankId', index : 'rankId', width : 50, sorttype :
 * "Integer", editable : false }, { name : 'hospitalName', index :
 * 'hospitalName', width : 150, editable : false }, { name : 'areaName', index :
 * 'areaName', width : 100, editable : false }, { name : 'regAmount', index :
 * 'regAmount', width : 70, editable : false }, { name : 'rxAmount', index :
 * 'rxAmount', width : 60, editable : false }, { name : 'drugSpecAmount', index :
 * 'drugSpecAmount', width : 80, editable : false },
 * 
 * {name : 'amountPerHp',index : 'amountPerHp', width : 100, editable : false}, {
 * name : 'mamount', index : 'mamount', width : 110, editable : false }, { name :
 * 'amountPerAreaTotal', index : 'amountPerAreaTotal', width : 100, editable :
 * false }, { name : 'amountPerTotal', index : 'amountPerTotal', width : 80,
 * editable : false }, { name : 'amountAvgPt', index : 'amountAvgPt', width :
 * 140, editable : false }, { name : 'drugSpecAvgPt', index : 'drugSpecAvgPt',
 * width : 100, editable : false }, { name : 'perbigRx', index : 'perbigRx',
 * width : 100, editable : false }, { name : 'amountAvgRx', index :
 * 'amountAvgRx', width : 110, editable : false }, { name : 'drugDayAvgPt',
 * index : 'drugDayAvgPt', width : 110, editable : false }, { name :
 * 'drugDayPerHp', index : 'drugDayPerHp', width : 100, editable : false }, {
 * name : 'turnoverRate', index : 'turnoverRate', width : 100, editable : false }, {
 * name : 'depts', index : 'depts', width : 100, editable : false }, { name :
 * 'drs', index : 'drs', width : 100, editable : false }, ],
 * 
 * autowidth: true, shrinkToFit: true,
 * 
 * viewrecords : true, rowNum : 10, rowList : [ 10, 20, 30 ], pager :
 * pager_selector, altRows : true, multiselect : false, // multikey: "ctrlKey",
 * multiboxonly : false, caption : "药品使用按医院统计" }); }
 */