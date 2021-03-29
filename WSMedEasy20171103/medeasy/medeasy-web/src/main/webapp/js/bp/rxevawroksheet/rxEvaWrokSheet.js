var rxEvaWorkTable=null;
var footerData={};
var pageExtraData={};
$(function() {
	$.initFormData();

	$("#chartContainer").css("width","100%");
//	$.showMessage("debug",window.screen.availHeight);
	$("#chartContainer").css("height","100%");
//	$(".widget-header").css("background-image","");
//	$(".widget-header").css("background-color","");
	
	$("#btnSearch").click(function(){
		var mobj = $("#searchForm").serializeObject();
		var mdata = JSON.stringify(mobj);
        footerData={};
		// rxEvaWorkTable.fnDraw();

        if ($("#rxEvaWorkTable").hasClass('dataTable')) {
            pageExtraData={};
            setFootData();
            var dttable = $("#rxEvaWorkTable").dataTable();
            dttable.fnClearTable(); // 清空一下table
            dttable.fnDestroy();
        }
        $.createJQDataTable();
	});
	$.createJQDataTable();
});

$.createJQDataTable = function(){
    $.showMessage("debug",$(window).height()-$("#rxEvaWorkTable").offset().top-$("#indexDescTable").height()+":window.height-"+$(window).height()+":table2Top-"+$("#rxEvaWorkTable").offset().top+":descTableTop-"+$("#indexDescTable").offset().top+":descTableHeight-"+$("#indexDescTable").height());

    var calculateHight=$(window).height()-$("#rxEvaWorkTable").offset().top-$("#indexDescTable").height()-150;
    calculateHight=$(window).height()-$("#rxEvaWorkTable").offset().top-260;

    if(calculateHight<40){
    	calculateHight=40;
	}
	rxEvaWorkTable =$('#rxEvaWorkTable').dataTable({
        "bAutoWidth":false,
        "bServerSide": false,
        "processing": true,
        "searching":true,
        //"sScrollY":calculateHight+"px",
        "sScrollY":"true",
        "sScrollX":"true",
		"bSortCellsTop": false,
        "ordering":true,
        "bPaginate":true,
        "bInfo" : true,
        "lengthChange": true,
        //"deferRender": true,
		//"sAjaxSource": $.getWebRoot()+"/bp/rxEvaWrokSheetData",
        "ajax": {
            "url": $.getWebRoot() + '/bp/rxEvaWrokSheetData',
            "type": 'POST',
            "dataType": "json",
            "data": function (d) {
                var mobj = $("#searchForm").serializeObject();
                var mdata = JSON.stringify(mobj);
                $.showMessage("debug", mdata);
                if (mobj["drugClassBtndrugcategoryid"]) {
                    mobj.drugCategoyCode = mobj["drugClassBtndrugcategoryid"];
                }
                if(mobj["basicDrugCategory"]&&mobj["basicDrugCategory"]!=""){
                    mobj["drugClassBtndrugid"]="";
                    mobj["drugClassBtndrugcategoryid"]="";
                    mobj["drugCategoyCode"]="";
                }
                return mobj;
            },
            "dataSrc": function (json) {
                pageExtraData=json.extraData;
                return json.aaData;
            }
        },
		"aoColumns": [
		               {"mData": 'rxCode'},
		               {"mData": 'rxDate',"width":"100px"},
		               {"mData": 'age',"searchable":false},
		               {"mData": 'diags',"width":"100px","searchable":false},
		               {"mData": 'drugVariety',"searchable":false},
		               {"mData": 'isAbx',"searchable":false},
		               {"mData": 'isIv',"searchable":false},
		               {"mData": 'basicDrugVariety'},
		               {"mData": 'genNameCount'},
		               {"mData": 'amount'},
		               {"mData": 'doctorName'},
		               {"mData": 'doctorCheck'},
		               {"mData": 'doctorDispense'},
		               {"mData": 'isOk',"searchable":false},
		               {"mData": 'irrerItemCode'},
		               {"mData": 'evaPerson'}
		               ],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex) {//相当于对字段格式化
            $('td:eq(9)', nRow).html(toC(aData.amount));
            $('td:eq(9)', nRow).attr('style', 'text-align: right;');

			// if(aData.rxCode != "总计" && aData.rxCode !="平均" && aData.rxCode!='百分比'){
			// 	//$('td:eq(1)', nRow).html(new Date(aData.rxDate).Format("yyyy-MM-dd"));
			// }
		},
		// "fnServerParams":function (aoData) {
		// 	var params=$("#searchForm").serializeObject();
         //    $.showMessage("debug",JSON.stringify(params));
         //    resetDrugParams(params);
         //    $.each(params, function(key, val) {
         //        aoData.push({
         //            "name": key,
         //            "value": val
         //        });
         //    });
/*
            var paramsArray=params.split("&");
			for(var i=0;i<paramsArray.length;i++){
				var param=paramsArray[i];
				var name=param.split("=")[0];
				var value=param.split("=")[1];
				aoData.push({
					"name": name,
					"value": value
				});
			}
			*/
		// },
		"fnDrawCallback": function( oSettings ) {
			// $("td").each(function(){
			// 	$(this).attr("title",$(this).text());
			// });

            //var json=jQuery.parseJSON(oSettings.jqXHR.responseText);
            setFootData();
		},
        "fnInitComplete": function() {
			this.fnAdjustColumnSizing(true);
        },
		"createdRow": function (row, data, dataIndex) {
                setDivWarpForTd(row, [1,3,10]);
        }
	});

    // rxEvaWorkTable =$('#rxEvaWorkTable').dataTable({
    //     "oLanguage": {
    //         "sZeroRecords": "抱歉， 没有找到"
    //     },
		// "sScrollY":calculateHight+"px",
    //     "sScrollX":true,
    //     "bScrollCollapse":true,
    //     "bSortCellsTop":false,
		// //"ordering":false,
		// "sAjaxSource": "/medeasy-web/bp/rxEvaWrokSheetData",
		// "aoColumns": [
		//                {"mData": 'rxCode',"width":"200px"},
		//                {"mData": 'rxDate'},
		//                {"mData": 'age',"width":"50px"},
		//                {"mData": 'diags',"bSortable": false},
		//                {"mData": 'drugVariety',"width":"100px"},
		//                {"mData": 'isAbx',"width":"120px"},
		//                {"mData": 'isIv',"width":"120px"},
		//                {"mData": 'basicDrugVariety',"width":"120px"},
		//                {"mData": 'genNameCount',"width":"120px"},
		//                {"mData": 'amount',"width":"120px"},
		//                {"mData": 'doctorName',"width":"120px","bSortable": false},
		//                {"mData": 'doctorCheck',"width":"120px","bSortable": false},
		//                {"mData": 'doctorDispense',"width":"120px","bSortable": false},
		//                {"mData": 'isOk',"width":"120px","bSortable": true},
		//                {"mData": 'irrerItemCode',"width":"120px","bSortable": false},
		//                {"mData": 'evaPerson',"width":"120px","bSortable": false}
		//                ],
    //     "fnRowCallback" : function(nRow, aData, iDisplayIndex) {//相当于对字段格式化
    //          $('td:eq(9)', nRow).html(toC(aData.amount));
    //     },
    //     "createdRow": function (row, data, dataIndex) {
    //         setDivWarpForTd(row, [1,3,10]);
		// },
    //     "footerCallback": function ( row, data, start, end, display ) {
    //         var api = this.api();
    //         // Update footer
    //         $( api.column( 7 ).footer() ).html(
    //             '$123123' +' ( $123123123' +' total)'
    //         );
    //     },
		// "fnInitComplete": function() {
    //          this.fnAdjustColumnSizing(true);
    //      },
    //     fixedColumns: {
    //         leftColumns: 1
    //     }
    // });
};

function setDivWarpForTd(row,tdIndex){
    var tdcssStr="overflow:hidden;max-width:150px;height:20px;text-overflow:ellipsis;white-space:nowrap";
    for(var j=0;j<tdIndex.length;j++){
        var coltext=$(row).children('td').eq(tdIndex[j]).text();
        $(row).children('td').eq(tdIndex[j]).html("<div style='"+tdcssStr+"' title='"+coltext+"'>"+coltext+"</div>")
    }
}

function setFootData(obj){
    footerData={};
	var sign=false;
    $(pageExtraData).each(function (k,aData) {
		if(aData.rxCode==undefined){
            return ;
        }
        if(aData.rxCode == "平均"){
            footerData.B=aData.avgDrugVariety;
            footerData.L=aData.amount;
        }else if(aData.rxCode == "百分比"){
            footerData.D=aData.ivPercentage+"%";
            footerData.F=aData.abxPercentage+"%";
            footerData.H=aData.avgBasicDrugVariety+"%";
            footerData.J=aData.avgGenNameCount+"%";
            footerData.P=aData.okPercentage+"%";
        }else if(aData.rxCode == "总计"){
            footerData.A=aData.drugVariety;
            footerData.C=aData.isIv;
            footerData.E=aData.isAbx;
            footerData.G=aData.basicDrugVariety;
            footerData.I=aData.genNameCount;
            footerData.K=aData.amount;
            footerData.O=aData.isOk;
            sign=true;
        }
    });

	$("#RxEvaA", $(".dataTables_scrollFoot")).html(footerData.A ? footerData.A : "-");
	$("#RxEva_C", $(".dataTables_scrollFoot")).html(footerData.C ? footerData.C : "-");
	$("#RxEva_E", $(".dataTables_scrollFoot")).html(footerData.E ? footerData.E : "-");
	$("#RxEva_G", $(".dataTables_scrollFoot")).html(footerData.G ? footerData.G : "-");
	$("#RxEva_I", $(".dataTables_scrollFoot")).html(footerData.I ? footerData.I : "-");
	$("#RxEva_K", $(".dataTables_scrollFoot")).html(footerData.K ? toC(footerData.K) : "-").css("text-align", "right");
	$("#RxEva_O", $(".dataTables_scrollFoot")).html(footerData.O ? footerData.O : "-");

	$("#RxEva_B", $(".dataTables_scrollFoot")).html(footerData.B ? footerData.B : "-");
	$("#RxEva_L", $(".dataTables_scrollFoot")).html(footerData.L ? toC(footerData.L) : "-").css("text-align", "right");

	$("#RxEva_D", $(".dataTables_scrollFoot")).html(footerData.D ? footerData.D : "-");
	$("#RxEva_F", $(".dataTables_scrollFoot")).html(footerData.F ? footerData.F : "-");
	$("#RxEva_H", $(".dataTables_scrollFoot")).html(footerData.H ? footerData.H : "-");
	$("#RxEva_J", $(".dataTables_scrollFoot")).html(footerData.J ? footerData.J : "-");
	$("#RxEva_P", $(".dataTables_scrollFoot")).html(footerData.P ? footerData.P : "-");
}

