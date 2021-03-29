$(function() {
	$.initFormData();

    var gridColModel=[{
    	label:'医院名称',
        name : 'hospitalName',
        index : 'hospitalName',
        editable : false
    }, {
        label:'区名称',
        name : 'areaName',
        index : 'areaName',
        editable : false
    }, {
        label:'科室',
        name : 'deptCount',
        index : 'deptCount',
        editable : false
    }, {
        label:'医师',
        name : 'doctorCount',
        index : 'doctorCount',
        editable : false
    }, {
        label:'药品',
        name : 'drugCount',
        index : 'drugCount',
        editable : false
    }, {
        label:'越级次数',
        name : 'overRightAccount',
        index : 'overRightAccount',
        editable : false
    }];

    var JQGridObj=$.genJQGridHL("#grid-table",  "#grid-pager",$.getWebRoot()+'/bp/abxUnauthorizedUsedAnaData',gridColModel,"#searchForm","抗菌药越权使用医院排名");

    JQGridObj.registerButtonHL("#btnSearchAbxUnauthorizedUsed");

}); // end
