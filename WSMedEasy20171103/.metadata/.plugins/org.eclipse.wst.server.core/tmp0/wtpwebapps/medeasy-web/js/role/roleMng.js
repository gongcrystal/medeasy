$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var consoleDlg = $("#addRoleModal");

	$.genRoleGrid(grid_selector, pager_selector); // 生成role的jqGrid table

	// 添加role
	$("#add_grid-table").click(function() {
		consoleDlg.modal('show', function() {
		});
	});

	// 点击添加角色时，生成modal, 显示菜单树
	consoleDlg.on('show.bs.modal', function() {

		$.ajax({
			"url" : "listNodes",
			"dataType" : "json",
			success : function(data) {
				$.genAuthTree(data);
			}
		})

		// add role modal中， 当点击保存button 时， 提交数据到server
		$("#btnSaveRole").click(function() {

			var checkedNodesStr = ""; // 用空格分割
			var zTree = $.fn.zTree.getZTreeObj("authTree");
			var checkedNodes = zTree.getChangeCheckedNodes(); // 当前所有被checked的treeNodes;

			for (var i = 0; i < checkedNodes.length; i++) {

				if (i != checkedNodes.length - 1) {
					checkedNodesStr += checkedNodes[i].name + "-";
				} else {
					checkedNodesStr += checkedNodes[i].name;
				}

			}

			$.ajax({
				"url" : "addRoleMenu",
				"dataType" : "json",
				"data" : {
					roleName : $("#roleName").val(),
					description : $("#description").val(),
					menuAuth : checkedNodesStr,

				},
				type : "POST",

				success : function(data) {
					if (!data.success) {
						swa_err(data.prompt);
					} else {
						swa_suc("保存成功");
						consoleDlg.modal("hide");
						$("#grid-table").trigger("reloadGrid")
					}
				}
			})
		});
	});
});

$.genAuthTree = function(zNodes) {

	var setting = {
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		callback : {
			onCheck : zTreeOnCheck
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	$.fn.zTree.init($("#authTree"), setting, zNodes);
}

function zTreeOnCheck(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("authTree");
	checkedNodes = zTree.getChangeCheckedNodes();

}

/* 显示所有的role list */
$.genRoleGrid = function(grid_selector, pager_selector) {
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : "application/json",
		url : '/medeasy-web/role/getRoles',
		mtype : 'POST',
		/*
		 * postData:{ "page":1, "rows":2, },
		 */
		height : 'auto',
		weight : 600,
		colNames : [ ' ', 'ID', '角色', '描述' ],
		colModel : [ {
			name : 'myac',
			index : '',
			width : 80,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : 'actions',
			formatoptions : {
				keys : true,

				delOptions : {
					url : "/medeasy-web/role/delRole",
					mtype : 'POST',
					reloadAfterSubmit : true,
					ajaxDelOptions : {
						contentType : "application/json"
					},
					serializeDelData : function(postdata) {
						var rowdata = jQuery('#grid-table').getRowData(postdata.id);
						return JSON.stringify(rowdata);
					}
				}
			}
		}, {
			name : 'id',
			index : 'id',
			width : 120,
			sorttype : "Integer",
			editable : false
		}, {
			name : 'name',
			index : 'name',
			width : 200,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'description',
			index : 'description',
			width : 350,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		} ],

		viewrecords : true,
		scroll : true,
		pager : pager_selector,
		altRows : true,
		// toppager: true,

		multiselect : true,
		// multikey: "ctrlKey",
		multiboxonly : true,

		loadComplete : function() {
			var table = this;
			setTimeout(function() {

			}, 0);
		},

		onSelectRow : function(rowid, status) {
			
			var rowData = jQuery(this).getRowData(rowid);
			var temp = rowData['name'];
			console.log(temp + " " + rowData['description']+" "+rowData['']);
			
			/*选中行后，查看角色信息*/
			$("#view_grid-table").click(function(){  
				alert("Crystal!");
			})
			

		},

		beforeSelectRow : function(rowid, e) {
			$("#grid-table").jqGrid('resetSelection');
			return (true);
		},

		editurl : "/medeasy-web/role/addRole",// 
		caption : "角色管理"

	});

	$(document).one('ajaxloadstart.page', function(e) {
		$.jgrid.gridDestroy(grid_selector);
		$('.ui-jqdialog').remove();
	});

}