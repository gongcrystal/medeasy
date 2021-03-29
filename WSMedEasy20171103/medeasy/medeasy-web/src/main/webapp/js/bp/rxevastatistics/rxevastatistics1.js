$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var rows;
	var myArray = new Array();
	var checkedNodesStr = "";

	$.evaChartDefault("/medeasy-web/bp/rxevast4hp");

	// 显示药品分类中， 来自drug表中的所有药品item
	$.listDrugItems("#drugItemlist", "/medeasy-web/bp/listDrugNodes");

	$("#drugItemSearchBtn").click(function() {
		// 点击检索btn
		$("#drugCategoryModal").modal("hide");
	})

	/* 快速分类Button */
	$.hLRadioSet();

	$("#btnSearchIncome").click(function() {

		// 具体药品的localdrugid
		var treeObj1 = $.fn.zTree.getZTreeObj("mzTree1");
		/* var checkedNodes1 = treeObj1.getChangeCheckedNodes(); */
		var checkedNodes1 = treeObj1.getCheckedNodes(true); // crystal
		// 2018-02-12
		var checkedNodesStr1 = "";
		for (var i = 0; i < checkedNodes1.length; i++) {
			if (i != checkedNodes1.length - 1) {
				checkedNodesStr1 += checkedNodes1[i].id + ",";
			} else {
				checkedNodesStr1 += checkedNodes1[i].id;
			}
		}
		/* console.log("checkedNodesStr1=" + checkedNodesStr1); */

		var dataH = $("#searchIncomeForm").serializeObject();
		dataH.localDrugId = checkedNodesStr1; // 具体的药品名称对应的drug_local_id

		dataH.startDate = $("#startDate").val();
		dataH.endDate = $("#endDate").val();

		/*
		 * console.log("start:" + dataH.startDate + " endDate :" +
		 * dataH.endDate);
		 * console.log("dataH.checkedNodesStr:"+dataH.checkedNodesStr);
		 */
		/* if (checkedNodesStr != "") { */
		dataH.checkedNodesStr = checkedNodesStr;
		/* } */
		/*
		 * console.log("checkedNodesStr:" + checkedNodesStr+"
		 * ."+dataH.checkedNodesStr);
		 */
		
		var hLDrugCty = $('input[name=hLDrugCategory]:checked').val();

		if (!$('input[type=radio][name=hLDrugCategory]').is(':checked')) {
			dataH.hLDrugCategory = null;
		} 
		
		
		$.evaChart("/medeasy-web/bp/rxevast4hpSearch", dataH);

		$("#grid-table").jqGrid("setGridParam", {
			postData : dataH,
			url : "/medeasy-web/bp/rxevastSearch",
		}).trigger("reloadGrid");

		/* start 提出要保留 2018-02-02 */
		// 下次打开modal的时候， 把上次的数据清空
		/*
		 * $("#drutItemName").val(""); $("#mzTree1").css("display","none");
		 */

		/* 药品分类 */
		/* $("#drugCKeyword").val(""); */
		// 去掉highlight
		/*
		 * $.ajax({ "url" : "/medeasy-web/bp/listDrugCategoryNodes", "dataType" :
		 * "json", success : function(data) { zTreeNodes = data;
		 * $.genZTree(data, $("#mzTree")); } })
		 */
		/* end 提出要保留 2018-02-02 */

	})

	// 快速分类
	/*
	 * $('input[type=radio][name=hLDrugCategory]').change(function() {
	 * 
	 * var rdHLValue = $('input:radio:checked').val(); console.log("radio: " +
	 * rdHLValue); // 提交检索，并且refresh table; var dataH =
	 * $("#searchHLDrugUseForm").serializeObject();
	 * 
	 * $.evaChart("/medeasy-web/bp/rxevast4hpSearch",dataH);//trigger图的变化
	 * 
	 * $("#grid-table").jqGrid("setGridParam", { postData : dataH, url :
	 * "/medeasy-web/bp/rxevast", }).trigger("reloadGrid");
	 * 
	 * });
	 */

	// rxSelect
	$.genSelect("#rxSelect", "/medeasy-web/basic/getPatientTypes", null);

	// irritate Select
	$.genSelectIDValue("#irritateSelect", "/medeasy-web/basic/getIrritate", null);

	/* $.genSelect("#irritateSelect", "/medeasy-web/basic/getIrritate", null); */

	$.dataPicker2MonthSet();

	$.easyPieChartSet();

	$.searchKeyDownGeneral1("#drutItemName", "mzTree1"); // 用于具体药品类的回车检索

	// 药品分类的选择树
	$.genDrugCategory($("#drugClassBtn"), $("#drugCategoryModal"));

	$.genRxSt(grid_selector, pager_selector);
	

	$.ajax({
		"url" : "/medeasy-web/bp/listDrugCategoryNodes",
		/* "url" : "/medeasy-web/role/listNodes", */
		"dataType" : "json",
		success : function(data) {
			/* zTreeNodes = data; */
			$.genZTree(data, $("#mzTree"));
		}
	})

	var nodes = null;
	$("#drugCKeyword").keydown(function() {
		if (event.keyCode === 13) {
			var treeObj = $.fn.zTree.getZTreeObj("mzTree");
			if (nodes != null) {
				highLightNodes(treeObj, nodes, false);
			}
			var searchKeyword = $("#drugCKeyword").val();
			var isHlight = false;
			if (searchKeyword == "") {
				isHlight = false;
			} else {
				isHlight = true;
			}

			nodes = treeObj.getNodesByParamFuzzy("name", $.trim(searchKeyword));
			highLightNodes(treeObj, nodes, isHlight);
		}
	});

	// 确定后，提交药品分类名称
	$("#btnDrugCategory").click(function() {
		var treeObj = $.fn.zTree.getZTreeObj("mzTree");
		var checkedNodes = treeObj.getChangeCheckedNodes();
		checkedNodesStr = "";
		for (var i = 0; i < checkedNodes.length; i++) {
			if (checkedNodes[i].isParent == false) {
				if (i != checkedNodes.length - 1) {
					checkedNodesStr += checkedNodes[i].id + ",";
					/* console.log("crystal: "+checkedNodes[i].id); */
				} else {
					checkedNodesStr += checkedNodes[i].id;
				}
			}
		}

		// 点击检索btn
		/* console.log("1checkedNodesStr=" + $.trim(checkedNodesStr)); */

		$("#drugCategoryModal").modal("hide");

		/*
		 * $("#btnSearchIncome").click(function() {
		 * 
		 * var dataH = $("#searchIncomeForm").serializeObject();
		 * dataH.checkedNodesStr = checkedNodesStr;
		 * 
		 * $("#grid-table").jqGrid("setGridParam", { postData : dataH, url :
		 * "/medeasy-web/bp/rxevast", }).trigger("reloadGrid"); })
		 */
	})
});

// 高亮显示被搜索到的节点
function highLightNodes(zTree, nodeList, isHlight) {
	for (var i = 0, l = nodeList.length; i < l; i++) {
		nodeList[i].highlight = isHlight; // 高亮显示搜索到的节点(highlight是自己设置的一个属性)

		if (isHlight) {
			if (nodeList[i].isParent) {
				zTree.expandNode(nodeList[i], true, false, true);
			} else {
				var pNode = nodeList[i].getParentNode();
				if (pNode) {
					zTree.expandNode(pNode, true, false, true);
				}
				zTree.expandNode(nodeList[i], true, false, true);
			}
		} else {
			if (nodeList[i].isParent) {
				zTree.expandNode(nodeList[i], false, true, false);
			}
		}
		/* zTree.expandNode(nodeList[i].getParentNode(), true, false, false); */// 将搜索到的节点的父节点展开
		zTree.updateNode(nodeList[i]); // 更新节点数据，主要用于该节点显示属性的更新
	}
}

$.genRxSt = function(grid_selector, pager_selector) {
	$("#grid-table").jqGrid(
			{
				datatype : 'json',
				contentType : "application/json; charset=UTF-8",
				url : '/medeasy-web/bp/rxevast',
				mtype : 'POST',
				height : 'auto',
				formatter: {
			        number: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 4, defaultValue: '0.0000' }
			    },
				/* weight : 600, */
				colNames : [ '排名', '医院名称  ', '区名称', '期次类型', '处方数', '科室', '医师', '药品', '药品使用金额(元)', '平均处方金额(元)', '合理处方数', '合理处方占比%', '不合理处方数',
						'不合理处方占比%', '不规范处方数', '用药不适宜处方数', '超常处方数', '抗菌药物处方数', '抗菌药处方占比%', '抗菌药物处方不合理数', '不合理抗菌药处方占比%', '问题代码' ],
				colModel : [ {
					name : 'rankId',
					index : 'rankId',
					frozen: true,
					width : 50,
				}, {
					name : 'hospitalName',
					index : 'hospitalName',
					frozen: true,
					width : 150,
					sorttype : "String",
					editable : false
				}, {
					name : 'areaName',
					index : 'areaName',
					width : 90,
					editable : false
				}, {
					name : 'patientType',
					index : 'patientType',
					width : 90,
					editable : false,
				/*
				 * formatter : function(value, grid, rows, state) {
				 * console.log("rows.id="+rows.id); if (value == 1) { return
				 * "门诊"; }
				 * 
				 * if (value == 2) { return "急诊"; }
				 * 
				 * if (value == 4) { return "门急诊"; } }
				 */
				}, {
					name : 'rxAmount',
					index : 'rxAmount',
					width : 50,
					editable : false
				}, {
					name : 'deptAmount',
					index : 'deptAmount',
					width : 50,
					editable : false
				}, {
					name : 'drAmount',
					index : 'drAmount',
					width : 50,
					editable : false
				}, {
					name : 'drugAmount',
					index : 'drugAmount',
					width : 50,
					editable : false
				}, {
					name : 'mamount',
					index : 'mamount',				
					
					width : 120,
					align: "right",
					editable : false,
					formatter: 'number' 
				}, {
					name : 'amountAvgRx',
					index : 'amountAvgRx',
					width : 120,
					align: "right",
					editable : false
				}, {
					name : 'okAmount',
					index : 'okAmount',
					width : 90,
					editable : false
				}, {
					name : 'perOkAmount',
					index : 'perOkAmount',
					width : 100,
					editable : false
				}, {
					name : 'nonOkamount',
					index : 'nonOkamount',
					width : 100,
					editable : false
				}, {
					name : 'perNonOkAmount',
					index : 'perNonOkAmount',
					width : 130,
					editable : false
				}, {
					name : 'unReasonableAmount1',
					index : 'unReasonableAmount1',
					width : 90,
					editable : false
				}, {
					name : 'unReasonableAmount2',
					index : 'unReasonableAmount2',
					width : 130,
					editable : false
				}, {
					name : 'unReasonableAmount3',
					index : 'unReasonableAmount3',
					width : 100,
					editable : false
				}, {
					name : 'abxCount',
					index : 'abxCount',
					width : 100,
					editable : false
				}, {
					name : 'perAbxAmount',
					index : 'perAbxAmount',
					width : 150,
					editable : false
				}, {
					name : 'nonOkAbxAmount',
					index : 'nonOkAbxAmount',
					width : 150,
					editable : false
				}, {
					name : 'perNonOkAbxAmount',
					index : 'perNonOkAbxAmount',
					width : 150,
					editable : false
				}, {
					name : 'problemCodeAmount',
					index : 'problemCodeAmount',
					width : 100,
					editable : false
				}, ],

				shrinkToFit : false,
				autoScroll : true,
				/* forceFit:true, */
				/*
				 * altRows:true, gridview: true,
				 */
				/* autowidth:true, */

				rowNum : -1,
				multiselect : false,
				multiboxonly : false,
				caption : "处方点评结果医院排名",

				loadComplete : function() {
				}
			});
	
	$("#grid-table").jqGrid('setFrozenColumns');

	/* return $("#grid-table").jqGrid('getRowData'); */

}
