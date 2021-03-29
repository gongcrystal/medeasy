$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	$.genLogGrid(grid_selector, pager_selector);
	$.dateRange();

	$("#btnSearch").click(function() {
		var mobj = $("#searchForm").serializeObject();
		var mobjStr = JSON.stringify(mobj);
		console.log("crystal: mobjStr=" + mobjStr);

		$("#grid-table").jqGrid("setGridParam", {
			postData : mobj,
			url : "/medeasy-web/log/getLogs"
		}).trigger("reloadGrid");
	});
})

$.genLogGrid = function(grid_selector, pager_selector) {

	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/log/getLogs',
		mtype : 'POST',

		height : 'auto',
		weight : 600,
		pgtext : "{0} 共 {1} 页",
		recordtext : "{0}-{1} 共 {2} 条",
		viewrecords : true,
		emptyrecords : "没有相关记录",
		colNames : [ 'ID', 'method', '页面', '操作者用户名', '操作者姓名', '开始操作时间', '检索条件', '被检索的医生', '被检索的药品' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 0,
			hidden : true,
		},

		{
			name : 'method',
			index : 'method',
			width : 0,
			hidden : true,
		}, {
			name : 'nameCh',
			index : 'nameCh',
			width : 150,
		}, {
			name : 'username',
			index : 'username',
			width : 150,
			editable : false,
		}, {
			name : 'doctorName',
			index : 'doctorName',
			width : 100,
			editable : false,
		}, {
			name : 'startSensitiveAction',
			index : 'startSensitiveAction',
			width : 100,
			editable : false,
			formatter : function(cellvalue, options, row) {
				return new Date(cellvalue).Format("yyyy-MM-dd hh:mm");
			},

		}, {
			name : 'cond',
			index : 'cond',
			width : 100,
			formatter : function(cellvalue, options, row) {

				switch (cellvalue) {
				case 1:
					return "按医生检索";
				case 2:
					return "按药品检索";
				case 3:
					return "按pt检索(仅用于测试)";
				default:
					return "未做条件检索";
				}
			},
		},

		{
			name : 'searchedDrname',
			index : 'searchedDrname',
			width : 120,
		}, {
			name : 'searchedDrugname',
			index : 'searchedDrugname',
			width : 150,
		} ],

		autowidth : true,
		shrinkToFit : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,

		multiselect : false,
		// multikey: "ctrlKey",
		multiboxonly : true,

		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				$.updatePagerIcons(table);
			}, 0);
		},

		beforeSelectRow : function(rowid, e) {
			$("#grid-table").jqGrid('resetSelection');
			return (true);
		},
		caption : "日志管理"
	});

	$("#grid-table").jqGrid('navGrid', '#grid-pager', {
		// navbar options
		edit : false,
		editicon : 'ace-icon fa fa-pencil blue',
		add : false,
		addicon : 'ace-icon fa fa-plus-circle purple',
		/* addfunc:openDialog4Adding, */
		del : false,
		delicon : 'ace-icon fa fa-trash-o red',
		search : false,
		searchicon : 'ace-icon fa fa-search orange',
		refresh : false,
		refreshicon : 'ace-icon fa fa-refresh green',
		view : false,
		viewicon : 'ace-icon fa fa-search-plus grey',
	}, {});
}
