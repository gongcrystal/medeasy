$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var consoleDlg = $("#addRoleModal");
	var zTreeNodes;

	$("#view_grid-table").css("display", "none");

	$("#refresh_grid-table").click(function() {
		$("#grid-table").trigger("reloadGrid")
	})

	$.validator.addMethod("roleNameChk", function(value, element) {
		console.log("value=" + value);

		if (value.indexOf("ROLE_") == 0) {

			return true;
		} else {
			return false;
		}
		;
	}, "角色名以ROLE_开头");

	$("#addRoleForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			roleName : {
				required : true,
				roleNameChk : true,
			},
		},
		messages : {
			roleName : {
				required : "请输入角色名",
			},
		}
	})
	// End 添加角色验证

	$.ajax({
		"url" : "listNodes",
		"dataType" : "json",
		success : function(data) {
			zTreeNodes = data;
			/* console.log("data:"+JSON.stringify(data)); */
			$.genRoleGrid(grid_selector, pager_selector, data); // 生成role的jqGrid
			// table
		}
	})

	// 添加role
	$("#add_grid-table").click(function() {
		consoleDlg.modal('show', function() {

		});
	});

	// 查看role
	$("#view_grid-table").click(function() {
		$.jqgridSltedRowCheck($("#grid-table"), $("#viewRoleModal"))
	});

	// 修改role
	$("#edit_grid-table").click(function() {

		$.jqgridSltedRowCheck($("#grid-table"), $("#editRoleModal"))
	});

	$.listDtOpLevel($("#operateLevel"));
	$.listDtOpLevel($("#eoperateLevel"));

	// 点击添加角色时，生成modal, 显示菜单树
	consoleDlg.on('show.bs.modal', function() {
		// 添加角色验证
		$("#roleName").focus();
		$("#roleName").val("ROLE_");

		$.genAuthTree(zTreeNodes, $("#authTree"));
		/* $.listDtOpLevel( $("#operateLevel")); */// LIST 出所有的可选数据操作级别
		// add role modal中， 当点击保存button 时， 提交数据到server
		$("#btnSaveRole").click(function() {
			
			/*
			 * var bootstrapValidator =
			 * $('#addRoleModal').data('bootstrapValidator'); if
			 * (bootstrapValidator && !bootstrapValidator.isValid()) {
			 * swa_err('数据填写不正确'); return false; }
			 */

			var zTree = $.fn.zTree.getZTreeObj("authTree");
			var checkedNodes = zTree.getChangeCheckedNodes();
			console.log("checkedNodes.length = " + checkedNodes.length);

			if (checkedNodes.length == 0) {
				swa_err('数据填写不正确');
			}
			var opLevel = $("#operateLevel option:selected").val();
			var opName = $("#operateLevel option:selected").text();
			saveRole($("#roleName").val(), $("#description").val(), opLevel, opName, zTree, "addRoleMenu", "#addRoleModal");
		});
	});

	$("#editRoleModal").on('shown.bs.modal', function() {
		$("#btnEditRole").click(function() {
			var zTree = $.fn.zTree.getZTreeObj("eauthTree");
			var opLevel = $("#eoperateLevel option:selected").val();
			var opName = $("#eoperateLevel option:selected").text();
			console.log("opLevel=" + opLevel + "opName =" + opName);

			saveRole($("#eroleName").val(), $("#edescription").val(), opLevel, opName, zTree, "updateRoleMenu", "#editRoleModal");
		});
	})

	/*
	 * $('#addRoleModal').bootstrapValidator({ message : 'This value is not
	 * valid', feedbackIcons : { valid : 'glyphicon glyphicon-ok', invalid :
	 * 'glyphicon glyphicon-remove', validating : 'glyphicon glyphicon-refresh' },
	 * fields : { roleName : { validators : { notEmpty : { message : '名称不能为空' } } },
	 * operateLevel : { validators : { notEmpty : { message : '数据权限不能为空' } } }, }
	 * });
	 */

	function saveRole(roleName, description, operateLevel, opName, zTree, url, modal) {
		console.log("start saveRole" + roleName);		

		var checkedNodesStr = ""; // 用空格分割
		var checkedNodes = zTree.getChangeCheckedNodes(); // 当前所有被checked的treeNodes;

		for (var i = 0; i < checkedNodes.length; i++) {

			if (i != checkedNodes.length - 1) {
				checkedNodesStr += $.trim(checkedNodes[i].name) + ":" + checkedNodes[i].id + "-";
				/* console.log("crystal: "+checkedNodes[i].id); */
			} else {
				checkedNodesStr += $.trim(checkedNodes[i].name) + ":" + checkedNodes[i].id;
			}
		}

		$.ajax({
			"url" : url,
			"dataType" : "json",
			"data" : {
				roleName :roleName,
				description : description,
				menuAuth : checkedNodesStr,
				operateLevel : operateLevel,
				opName : opName

			},
			type : "POST",

			success : function(data) {
				if (!data.success) {
					swa_err(data.prompt);
				} else {
					swa_suc("保存成功");
					$(modal).modal("hide");
					$("#grid-table").trigger("reloadGrid")
					$('#addRoleForm').trigger("reset");
				}
			}
		})
	}
});

$.listDtOpLevel = function(selectCmp) {
	$.ajax({
		"url" : "getDtOpLevels",
		"dataType" : "json",
		type : "GET",

		success : function(data) {
			/*for (var i = 0; i < data.length; i++) {*/
				for (var i = 3; i < data.length; i++) { // 临时要去去掉头三个，而且不修改数据库 by crystal 2018.3.1
				selectCmp.append('<option value="' + data[i].operateLevel + '">' + data[i].opName + '</option>');
			}
		}
	})
}

$.genAuthTree = function(zNodes, treeName) {

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

	$.fn.zTree.init(treeName, setting, zNodes);
}
function zTreeOnCheck(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("authTree");
	checkedNodes = zTree.getChangeCheckedNodes();

}

// 显示菜单树，
$.viewAuthTree = function(zNodes, treeName) {
	var setting = {
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				/*"Y" : "ps",*/
				/*一下未正确的*/
				/*"Y" : "p",
				"N" : "ps"*/
				
				"Y" : "p",
				"N" : "ps"
			}
		},
		/*
		 * callback : { onCheck : zTreeOnCheck },
		 */
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	$.fn.zTree.init(treeName, setting, zNodes);
}

/* 显示所有的role list */
$.genRoleGrid = function(grid_selector, pager_selector, ztNodes) {
	$("#grid-table").jqGrid({
		datatype : 'json',
		contentType : 'application/json; charset=UTF-8',
		timeout : 20000,
		url : '/medeasy-web/role/getRoles',
		mtype : 'POST',
		/*
		 * postData:{ "page":1, "rows":2, },
		 */
		height : 'auto',
		weight : 600,
		colNames : [ /* ' ', */'角色', '描述', '数据权限', "操作权限ID", '角色权限' ],
		colModel : [ {
			name : 'roleName',
			index : 'roleName',
			width : 200,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, {
			name : 'description',
			index : 'description',
			width : 200,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		},

		{
			name : 'opName',
			index : 'opName',
			width : 200,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		},

		{
			name : 'operateLevel',
			index : 'operateLevel',
			width : 1,
			editable : false,
			hidden : true,
		}, {
			name : 'menuAuth',
			index : 'menuAuth',
			width : 80,
			editable : false,
			hidden : true,
		/*
		 * formatter : function(value, grid, rows, state) { var mvalue="无";
		 * if(value!=null) mvalue=value;
		 * 
		 * return "<a href=\"#\" class=\"green bigger-140 show-details-btn\" " +
		 * 
		 * "title=" +mvalue+ ">" + "<i class=\"ace-icon fa
		 * fa-angle-double-down\"></i>" ; }
		 */
		} ],

		/*
		 * autowidth : true, shrinkToFit : true,
		 */

		viewrecords : true,
		scroll : true,
		pager : pager_selector,
		altRows : true,
		// toppager: true,

		multiselect : true,
		// multikey: "ctrlKey",
		multiboxonly : true,
		rowNum : -1,

		loadComplete : function(data) {

			var table = this;
			setTimeout(function() {

			}, 0);
		},

		onSelectRow : function(rowid, status) {

			var rowData = jQuery(this).getRowData(rowid);

			/* 选中行后，查看角色信息 */
			$("#view_grid-table").click(function() {
				$("#viewRoleModal").on('shown.bs.modal', function() {
					$("#vroleName").val(rowData['roleName']);
					$("#vdescription").val(rowData['description']);
					$("#voperateLevel").val(rowData['opName']);
					// 显示ztree
					$.viewAuthTree(ztNodes, $("#vauthTree"));
					var vzTree = $.fn.zTree.getZTreeObj("vauthTree");

					var menuAuths = rowData['menuAuth'].trim();

					var menuAuth = menuAuths.split(" ");

					for (var i = 0; i < menuAuth.length; i++) {
						var vnode = vzTree.getNodeByParam("name", menuAuth[i]);
						vzTree.checkNode(vnode, true, true);
						vnode.checked = true;
						vzTree.updateNode(vnode);
					}
				});
			})

			/* 编辑时选中行后，修改角色信息 */
			$("#edit_grid-table").click(function() {
				$("#editRoleModal").on('shown.bs.modal', function() {
					$("#eroleName").val(rowData['roleName']);
					$("#edescription").val(rowData['description']);
					$("#eoperateLevel  option[value=" + rowData['operateLevel'] + "]").prop('selected', true);
					/* $.listDtOpLevel($("#eoperateLevel")); */
					// 显示ztree
					$.viewAuthTree(ztNodes, $("#eauthTree"));
					var vzTree = $.fn.zTree.getZTreeObj("eauthTree");
					var menuAuths = rowData['menuAuth'].trim();
					
					var menuAuth = menuAuths.split(" ");

					for (var i = 0; i < menuAuth.length; i++) {
						
						var vnode = vzTree.getNodeByParam("name", menuAuth[i].trim());
						if (vnode != null) {
							vzTree.checkNode(vnode, true, true);
							vnode.checked = true;
							vzTree.updateNode(vnode);
						}
					}
				});
			}) /* end 编辑时选中行后，查看角色信息 */

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