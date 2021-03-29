$(function() {
	$.initFormData();

	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	$.genBaseDrugGrid(grid_selector, pager_selector, []);

	$("#add_grid-table").hide(); // user register
	$("#view_grid-table").hide(); // table shows all info

	// searchbtn
	$("#btnSearchBaseDrug").click(function() {
        $("#grid-table").jqGrid('setGridParam', {postData : getReqParams(), page : 1}).trigger("reloadGrid");
	});

	// 表格宽度自适应
	$(window).resize(function() {
		$("#grid-table").setGridWidth($(window).width());
	});

}); // end

/* 显示所有的basedrugusedstatistics list */
$.genBaseDrugGrid = function(grid_selector, pager_selector) {
	var dataF1 = $("#searchForm").serializeObject();
	// var mdata = JSON.stringify(dataF1);
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : $.getWebRoot()+'/bp/getBasedrugusedstatistics',
		mtype : 'POST',
        postData : getReqParams(),
		height : 'auto',
		width : window.screen.availWidth - 220,
		shrinkToFit : true,
		colNames : [ '排名', '医院名称', '区名称', '医师', '科室', '患者', '诊断', '基本药物品种数', '基本药品品种数占比%', '药品使用金额（元）', '基本药品使用金额（元）', '基本药品使用金额占比%' ],
		colModel : [
			{
			name : 'rankId',
			index : 'rankId',
			width : 50,
		}, {
			name : 'hospitalName',
			index : 'hospitalName',
			width : 120,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'areaName',
			index : 'areaName',
			width : 80,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'doctorCount',
			index : 'doctorCount',
			width : 80,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'deptCount',
			index : 'deptCount',
			width : 80,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'patientCount',
			index : 'patientCount',
			width : 80,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'diagnosisCount',
			index : 'diagnosisCount',
			width : 80,
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'baseDrugCount',
			index : 'baseDrugCount',
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'countPercentage',
			index : 'countPercentage',
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'amount',
			index : 'amount',
			editable : false,
			hidden : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'baseDrugAmount',
			index : 'baseDrugAmount',
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'amountPercentage',
			index : 'amountPercentage',
			editable : false,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		} ],
		// scroll : true,
		viewrecords : true,
		rowNum : -1,
		/* rowList : [ 10, 20, 30 ], */
		/* pager : pager_selector, */
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
		caption : "基本药物使用情况"
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

function getReqParams(){
    var dataF1 = $("#searchForm").serializeObject();
    if (dataF1.startDate == '' || dataF1.endDate == '') {
        $.showMessage("提示", "请先选择日期！");
    }
    if (dataF1.searchTypeKey == '') {
        $.showMessage("提示", "请先选择报表类型！");
    }
    if(dataF1["basicDrugCategory"]&&dataF1["basicDrugCategory"]!=""){
        dataF1["drugClassBtndrugid"]="";
        dataF1["drugClassBtndrugcategoryid"]="";
    }else{
    	dataF1["basicDrugCategory"]="";
	}
    $.showMessage("debug",JSON.stringify( dataF1 ));
    return dataF1;
}