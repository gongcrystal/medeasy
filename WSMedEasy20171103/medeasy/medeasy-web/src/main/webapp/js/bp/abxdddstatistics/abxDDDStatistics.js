$(function() {
	$.initFormData();

	var gridColModel=[
            {	label:'药品类别',
                name : 'drugCategoryName',
                index : 'drugCategoryName',
                editable : false,
                frozen: true,
                hidden:true
            },
            {
                label:'药品名称',
                name : 'drugName',
                index : 'drugName',
                editable : false,
                frozen: true
            },
            {
                label:'通用名',
                sortable: false,
                name : 'genericName',
                index : 'genericName',
                editable : false
            },
            {
                label:'剂型',
                sortable: false,
                name : 'dosageForm',
                index : 'dosageForm',
                width : 120,
                editable : false
            },
            {
                label:'规格',
                sortable: false,
                name : 'spec',
                index : 'spec',
                width : 120,
                editable : false
            },
            {
                label:'厂家',
                sortable: false,
                name : 'manufacture',
                index : 'manufacture',
                width : 120,
                editable : false
            },
            {
                label:'级别',
                name : 'abxLevel',
                index : 'abxLevel',
                width : 120,
                editable : false
            },
            {
                label:'使用数量',
                name : 'drugPackage',
                index : 'drugPackage',
                width : 120,
                editable : false
            },
            {
                label:'抗菌药物使用金额',
                name : 'amount',
                index : 'amount',
                editable : false,
                align:"right"
            },
            {
                label:'抗菌药物使用金额占比%',
                sortable: false,
                name : 'amountPercentage',
                index : 'amountPercentage',
                editable : false
            },
            {
                label:'金额总占比',
                name : 'totalPercentage',
                index : 'totalPercentage',
                editable : false,
                hidden:true
            },
            {
                label:'抗菌药物处方数',
                name : 'rxCount',
                index : 'rxCount',
                editable : false
            },
            {
                label:'抗菌药物处方数占比',
                sortable: false,
                name : 'rxPercentage',
                index : 'rxPercentage',
                editable : false
            },
            {
                label:'抗菌药物处方数总占比',
                name : 'rxTotalPercentage',
                index : 'rxTotalPercentage',
                editable : false
            },
            {
                label:'抗菌药物使用人次',
                name : 'drugUsedCount',
                index : 'drugUsedCount',
                editable : false
            },
            {
                label:'人均抗菌药物使用金额',
                name : 'averageAmount',
                index : 'averageAmount',
                editable : false,
                align:"right"
            },
            {
                label:'抗菌药物使用率',
                name : 'drugUsage',
                index : 'drugUsage',
                editable : false
            },
            {
                label:'DDDS',
                name : 'ddds',
                index : 'ddds',
                width : 120,
                editable : false
            },
            {
                label:'区',
                sortable: false,
                name : 'areaCount',
                index : 'areaCount',
                width : 50,
                editable : false
            },
            {
                label:'医院',
                sortable: false,
                name : 'hospitalCount',
                index : 'hospitalCount',
                width : 50,
                editable : false
            },
            {
                label:'医师',
                sortable: false,
                name : 'doctorCount',
                index : 'doctorCount',
                width : 50,
                editable : false
            },
            {
                label:'科室',
                sortable: false,
                name : 'deptCount',
                index : 'deptCount',
                width : 50,
                editable : false
            }];

	var JQGridObj=$.genJQGridHL("#grid-table",  "#grid-pager",$.getWebRoot()+'/bp/abxDDDstatisticsData',gridColModel,"#searchForm","抗菌药物使用强度排名");
	JQGridObj.registerButtonHL("#btnSearchAbxDDDstatistics");

}); // end
