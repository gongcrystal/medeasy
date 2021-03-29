$(function() {
	$.initFormData();
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	var grid_selector_1 = "#grid-table_1";
	var pager_selector_1 = "#grid-pager_1";
	
	$.genRduStatisticsByHospitalGrid(grid_selector, pager_selector);
	$.genRduStatisticsByAlertTypeGrid(grid_selector_1, pager_selector_1);
	//默认按医院显示加载数据
	$(".hospitalGrid").show();
	$(".alertTypeGrid").hide();
	
	$("#add_grid-table").hide(); // user register
	$("#view_grid-table").hide(); // table shows all info

	$("#grid-pager_left").removeAttr("style");
	
	// searchbtn
	$("#btnSearchRduStatistics").click(function() {
		var dataF1 = $("#searchForm").serializeObject();
		if(dataF1["rsFormTypeKey"] == 'hospital' || dataF1["rsFormTypeKey"] == ''){
//			$.genRduStatisticsByHospitalGrid(grid_selector, pager_selector);
			$(".hospitalGrid").show();
			$(".alertTypeGrid").hide();
			$("#grid-table").jqGrid('setGridParam',{
				postData : dataF1,
				page : 1
			}).trigger("reloadGrid");
		}else{
//			$.genRduStatisticsByAlertTypeGrid(grid_selector_1, pager_selector_1);
			$(".alertTypeGrid").show();
			$(".hospitalGrid").hide();
			$("#grid-table_1").jqGrid('setGridParam',{
				postData : dataF1,
				page : 1
			}).trigger("reloadGrid");
		}
	});
	
	//表格宽度自适应
	$(window).resize(function(){
		$("#grid-table").setGridWidth($(window).width());
		$("#grid-table_1").setGridWidth($(window).width());
	});
	
}); // end

/* 显示所有的rduStatistics list */
$.genRduStatisticsByHospitalGrid = function(grid_selector, pager_selector) {
	var dataF1 = $("#searchForm").serializeObject();
	var mdata = JSON.stringify(dataF1);
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : $.getWebRoot()+'/bp/rduStatisticsData',
		mtype : 'POST',
        postData : dataF1,
		height : 'auto',
		width : '100%',
		autowidth : true,
        rownumbers: true,
		colNames : [ '医院名称', '区名称', '处方数', '药品使用金额（元）', '1级报警', '2级报警', '3级报警', '4级报警', '报警总数', '报警比例', '报警类型','药品','科室','医师'],
		colModel : [
					{
						name : 'hospitalName',
						index : 'hospitalName',
						editable : false
					}, {
						name : 'areaName',
						index : 'areaName',
						editable : false
					}, {
						name : 'rxCount',
						index : 'doctorCount',
						width : 80,
						editable : false
					}, {
						name : 'amount',
						index : 'amount',
						editable : false,
            			formatter:currencyFmatter,
            			align:"right"
					}, {
						name : 'alertLevel1Count',
						index : 'alertLevel1Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel2Count',
						index : 'alertLevel2Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel3Count',
						index : 'alertLevel3Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel4Count',
						index : 'alertLevel4Count',
						width : 80,
						editable : false
					}, {
						name : 'alertTotalCount',
						index : 'alertTotalCount',
						width : 80,
						editable : false
					}, {
						name : 'alertPercentage',
						index : 'alertPercentage',
						width : 80,
						editable : false
					}, {
						name : 'alertTypeCount',
						index : 'alertTypeCount',
						editable : false
					}, {
						name : 'drugCount',
						index : 'drugCount',
						width : 80,
						editable : false
					}, {
						name : 'deptCount',
						index : 'deptCount',
						width : 80,
						editable : false
					}, {
						name : 'doctorCount',
						index : 'doctorCount',
						width : 80,
						editable : false
					}],
//					shrinkToFit : false,
//					autoScroll : false,
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		// toppager: true,
		multiselect : false,
		// multikey: "ctrlKey",
		multiboxonly : false,
		loadComplete : function() {
			var table = this;

			setTimeout(function() {
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
//					beforeSelectRow : function(rowid, e) {
//						$("#grid-table").jqGrid('resetSelection');
//						return (true);
//					},
		caption : "合理用药结果排名统计"
	});

	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

			if ($class in replacement)
				icon.attr('class', 'ui-icon ' + replacement[$class]);
		});
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}

	$(document).one('ajaxloadstart.page', function(e) {
		$.jgrid.gridDestroy(grid_selector);
		$('.ui-jqdialog').remove();
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
	
};

/* 显示所有的rduStatistics list */
$.genRduStatisticsByAlertTypeGrid = function(grid_selector_1, pager_selector_1) {
	var dataF1 = $("#searchForm").serializeObject();
	var mdata = JSON.stringify(dataF1);
	$("#grid-table_1").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/bp/rduStatisticsData',
		mtype : 'POST',
        postData : dataF1,
		height : 'auto',
		width : '100%',
        rownumbers: true,
		colNames : [ '报警名称', '处方数', '药品使用金额（元）', '1级报警', '2级报警', '3级报警', '4级报警', '报警总数', '报警比例', '药品', '区', '医院', '科室', '医师'],
		colModel : [
					{
						name : 'alertTypeName',
						index : 'alertTypeName',
						editable : false
					}, {
						name : 'rxCount',
						index : 'doctorCount',
						width : 80,
						editable : false
					}, {
						name : 'amount',
						index : 'amount',
						editable : false,
						formatter:currencyFmatter,
            			align:"right"
					}, {
						name : 'alertLevel1Count',
						index : 'alertLevel1Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel2Count',
						index : 'alertLevel2Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel3Count',
						index : 'alertLevel3Count',
						width : 80,
						editable : false
					}, {
						name : 'alertLevel4Count',
						index : 'alertLevel4Count',
						width : 80,
						editable : false
					}, {
						name : 'alertTotalCount',
						index : 'alertTotalCount',
						width : 120,
						editable : false
					}, {
						name : 'alertPercentage',
						index : 'alertPercentage',
						width : 120,
						editable : false
					}, {
						name : 'drugCount',
						index : 'drugCount',
						width : 80,
						editable : false
					}, {
						name : 'areaCount',
						index : 'areaCount',
						width : 80,
						editable : false
					}, {
						name : 'hospitalCount',
						index : 'hospitalCount',
						width : 80,
						editable : false
					}, {
						name : 'deptCount',
						index : 'deptCount',
						width : 80,
						editable : false
					}, {
						name : 'doctorCount',
						index : 'doctorCount',
						width : 80,
						editable : false
					}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector_1,
		altRows : true,
		// toppager: true,
		multiselect : false,
		// multikey: "ctrlKey",
		multiboxonly : false,
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
		beforeSelectRow : function(rowid, e) {
			$("#grid-table_1").jqGrid('resetSelection');
			return (true);
		},
		caption : "合理用药结果排名统计"
	});

	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

			if ($class in replacement)
				icon.attr('class', 'ui-icon ' + replacement[$class]);
		});
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}

	$(document).one('ajaxloadstart.page', function(e) {
		$.jgrid.gridDestroy(grid_selector);
		$('.ui-jqdialog').remove();
	});

	$("#grid-table_1").jqGrid('navGrid', '#grid-pager_1', {
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
	
};

function currencyFmatter (cellvalue, options, rowObject)
{
    return toC(cellvalue);
}
