$(function() {

	var pager_selector = "#grid-pager";
	var grid_drugIncome = "#gridtb_drugincome";
	var grid_selector = "#grid-table";
	
	
	/*var start=null,end=null, areakey=null,hospitalKey=null;*/
	

	/* $.genDrugUseGrid(grid_selector, pager_selector); */// 按要求改为datatables
	/* $.genDrugIncomeGrid(grid_drugIncome, pager_selector); */// 按要求改为datatables
	// 一期/1.6.药品使用金额统计（另见一-3-1.2）
	/*$('#dynamic-table-drug-income').dataTable({

		"processing" : true,
		"serverSide" : true,
		"searching" : false,
		"bFilter" : false,
		"bInfo" : false,
		"bPaginate" : false,

		"ajax" : {
			"url" : "/medeasy-web/druguse/drugIncome",
			"type" : 'POST',
			"dataType" : "json",
			"data" : function(d) {
				if (start)
					d.start = start;
				if (end)
					d.end = end;
				if (areakey)
					d.areakey = areakey;
				if (hospitalKey)
					d.hospitalKey = hospitalKey;
				return d;
			}

		},
		"columns" : [ {
			"data" : "drugItemName"
		}, {
			"data" : "mamount"
		}, {
			"data" : "amountPerAreaTotal"
		}, {
			"data" : "amountAvgPt"
		}, {
			"data" : "amountAvgRx"
		}, {
			"data" : "turnoverRate"
		} ],

	});
*/
	/*// 一期/1.8.医院药品使用金额排名（另见一-3-1.1）
	$('#dynamic-table').dataTable({

		"processing" : true,
		"serverSide" : true,
		"searching" : false,
		"bFilter" : false,
		"bInfo" : false,
		"bPaginate" : false,

		"ajax" : {
			"url" : "/medeasy-web/druguse/drugUseStHospital4Dt",
			"type" : 'POST',
			"dataType" : "json",

		},
		"columns" : [ {
			"data" : "rankId"
		}, {
			"data" : "hospitalName"
		}, {
			"data" : "areaName"
		}, {
			"data" : "regAmount"
		}, {
			"data" : "rxAmount"
		}, {
			"data" : "drugSpecAmount"
		},

		{
			"data" : "mamount"
		}, {
			"data" : "amountPerAreaTotal"
		}, {
			"data" : "amountPerTotal"
		}, {
			"data" : "amountAvgPt"
		}, {
			"data" : "drugSpecAvgPt"
		}, {
			"data" : "perbigRx"
		},

		{
			"data" : "amountAvgRx"
		}, {
			"data" : "drugDayAvgPt"
		}, {
			"data" : "drugDayPerHp"
		}, {
			"data" : "turnoverRate"
		}, {
			"data" : "depts"
		}, {
			"data" : "drs"
		} ],

	});
*/
	// 1.1.药品使用基础信息
	$.ajax({
		"url" : "/medeasy-web/druguse/drugUseStHomePage",
		"dataType" : "json",
		type : "POST",
		success : function(data) {
			$("#t1 span").each(function() {
				/* console.log(" " + $(this).attr("name")); */
				$(this).text(data.rows[$(this).attr("name")] ? data.rows[$(this).attr("name")] : "");
			});

		}
	});

	//处方点评结果汇总图
	$.evaChartDefault("/medeasy-web/bp/rxevast4hp");

	/*
	 * $.ajax({ "url" : "/medeasy-web/bp/rxevast4hp", "dataType" : "json", type :
	 * "POST", success : function(data) {
	 * 
	 * $.setPBar($("#noOkPercent_out"), $("#noOkPercent"), data.perUnOk); //
	 * 不合理处方 $.setPBarWithTitle($("#noOkPercent_out"), $("#noOkPercent"),
	 * data.perUnOk, "不合理处方"); // 不合理处方 $.setPBarWithTitle($("#unReason3_out"),
	 * $("#unReason3"), data.perUnr3, "超常处方"); // unReasonableAmount3
	 * $.setPBarWithTitle($("#unReason2_out"), $("#unReason2"), data.perUnr2,
	 * "用药不适宜处方"); $.setPBarWithTitle($("#unReason1_out"), $("#unReason1"),
	 * data.perUnr1, "不规范处方"); $.setPBarWithTitle($("#reasonRx_out"),
	 * $("#reasonRx"), data.perOk,"合理处方");
	 *  } });
	 */

	/*
	 * $.setPBar($("#noOkPercent_out"), $("#noOkPercent"), nonOkPer.toFixed(2) *
	 * 1); // 不合理处方 $.setPBarWithTitle($("#unReason3_out"), $("#unReason3"),
	 * unR3Per, "超常处方"); // unReasonableAmount3
	 * $.setPBarWithTitle($("#unReason2_out"), $("#unReason2"), unR2Per,
	 * "用药不适宜处方"); $.setPBarWithTitle($("#unReason1_out"), $("#unReason1"),
	 * unR1Per, "不规范处方"); $.setPBarWithTitle($("#okRX_out"), $("#okRX"),
	 * okPer.toFixed(2) * 1);
	 */

})


//用一个统一的前后台数据交互函数
function queryData(url,type,req,successFun,errFun){
    $.ajax({
        "url" : $.getWebRoot() + url,
        "type" : type,
        "dataType" : 'json',
        "timeout" : 20000,
        data : req,
        success :successFun ,
        error : (errFun?errFun:(function(a, b, c) {
            $.showMessage("error", "数据加载异常：" + b + c + a);
        }))
    });
}

function getBasicInfoSuccess(data) {
    if (data.length == 1) {
        if (data[0]) {
            $("#t1 span").each(function() {
                $(this).text(data[0][$(this).attr("name")] ? data[0][$(this).attr("name")] : "-");
            });
        }else {
            $("#t1 span").each(function() {
                $(this).text("-");
            });
        }
    }
}

function getBasicInfoErr(a, b, c) {
    $.showMessage("error", "药品使用基础信息数据加载异常：" + b + c + a);
}

function getRequestObj(){
    var sobj = $("#searchForm").serializeObject();
    if (sobj.start == "" || sobj.end == "") {
        $.showMessage("提示", "请先选择时间范围！");
        return;
    }
    sobj.start = sobj.start.replace('-', '');
    sobj.end = sobj.end.replace('-', '');
    sobj.startDate = sobj.start.replace('-', '');
    sobj.endDate = sobj.end.replace('-', '');

    return sobj;
}
