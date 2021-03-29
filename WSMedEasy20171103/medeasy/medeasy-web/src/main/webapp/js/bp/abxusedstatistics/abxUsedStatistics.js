$(function() {
	$.initFormData();
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	$.genAbxUsedGrid(grid_selector, pager_selector);

    $("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
    $("#grid-table").jqGrid('setFrozenColumns');

	$("#add_grid-table").hide(); // user register
	$("#view_grid-table").hide(); // table shows all info

	// searchbtn
	$("#btnSearchAbxUsedStatistics").click(function() {
		var mobj = $("#searchForm").serializeObject();
		$("#grid-table").jqGrid("setGridParam", {
			postData : mobj,
			page : 1
		}).trigger("reloadGrid");
	});

	//表格宽度自适应
	$(window).resize(function(){
		$("#grid-table").setGridWidth($(window).width());
	});
	
}); // end

/* 显示所有的basedrugusedstatistics list */
$.genAbxUsedGrid = function(grid_selector, pager_selector) {

	var dataF1 = $("#searchForm").serializeObject();
	var mdata = JSON.stringify(dataF1);
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/bp/abxUsedStatisticsData',
		mtype : 'POST',
		postData : dataF1,
		height : 'auto',
        shrinkToFit:false,
        autoScroll: true,
        rownumbers: true,
        colNames : [ '医院', '区名称', '科室', '医师', '药品', '就诊次数','抗菌药物使用人次','抗菌药物处方数','抗菌药物处方数占比%','药品使用金额','抗菌药物使用金额','抗菌药物使用金额占比%','平均处方抗菌药物品种数','平均处方抗菌药物金额','人均抗菌药物品种数','人均抗菌药物使用金额','抗菌药物使用率%','静脉输液抗菌药物使用率%','抗菌药物二联以上用处方比例%'],
		colModel : [
					{
						name : 'hospitalName',
						index : 'hospitalName',
						editable : false,
						width:"230px",
            			frozen: true
					}, {
						name : 'areaName',
						index : 'areaName',
						editable : false,
						editoptions : {
							size : "auto",
							maxlength : "30"
						}
					}, {
						name : 'deptCount',
						index : 'deptCount',
            			width : 120,
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'doctorCount',
						index : 'doctorCount',
						width : 120,
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'drugCount',
						index : 'drugCount',
						width : 120,
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'patientCount',
						index : 'patientCount',
						width : 120,
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'drugUsedCount',
						index : 'drugUsedCount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'rxCount',
						index : 'rxCount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'rxPercentage',
						index : 'rxPercentage',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'amount',
						index : 'amount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						},
            			hidden:true
					}, {
						name : 'rxAbxAmount',
						index : 'rxAbxAmount',
						editable : false,
						formatter:currencyFmatter,
						align:"right"

					}, {
						name : 'totalPercentage',
						index : 'totalPercentage',
						editable : false,
            			width:"130px",
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'averageRxDrug',
						index : 'averageRxDrug',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'averageRxAmount',
						index : 'averageRxAmount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'perDrugCount',
						index : 'perDrugCount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'perDrugAmount',
						index : 'perDrugAmount',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'abxDrugUsage',
						index : 'abxDrugUsage',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'ivDrugUsage',
						index : 'ivDrugUsage',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}, {
						name : 'over2DrugUsage',
						index : 'over2DrugUsage',
						editable : false,
						editoptions : {
							size : "20",
							maxlength : "30"
						}
					}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		// toppager: true,
		multiselect : false,
		// multikey: "ctrlKey",
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
		beforeSelectRow : function(rowid, e) {
			$("#grid-table").jqGrid('resetSelection');
			return (true);
		},
		caption : "抗菌药物使用金额医院排名"
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

function currencyFmatter (cellvalue, options, rowObject)
{
    return toC(cellvalue);
}