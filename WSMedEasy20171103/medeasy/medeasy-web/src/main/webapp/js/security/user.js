$(function() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var hName, dName; // hospitalName deptName;
	var rid4Update, rowData4Update;

	$.getDeptsByLoginUsername("#dept", "basic/getDeptsByUserName"); // 检索form里的dept列表

	/* $.genSelectArea("#area_code1", "/medeasy-web/basic/getDists", null); */
	$.genSelectArea4Add("#area_code1", "#hospitalId1", "#deptId1", "/medeasy-web/basic/getDists", null);

	$.genSelectArea("#area_code", "/medeasy-web/basic/getDists", null);
	// 根据所属的区设置update时设置的

	$.addUserValid();

	$.genRoleGrid(grid_selector, pager_selector);
	$.genBtn();
	$.getHospitals($("#hospital")); // for search cond 检索

	$.selectHospital2Dept1($("#hospital"), $("#dept"), "/medeasy-web/basic/getDeptsByHpCode"); // 检索

	$.genRoleSelect($("#roleId"), "/medeasy-web/role/getSecRoles");

	/* $("#add_grid-table").hide(); */// user register
	$("#view_grid-table").hide(); // table shows all info

	// add : 区的变化，trigger医院列表的变化
	$.selectChangeArea2Hospital($("#area_code1"), $("#hospitalId1"), "basic/getHospitalsByArea");

	// update : 区的变化，trigger医院列表的变化
	$.selectChangeArea2Hospital($("#area_code"), $("#hospitalId"), "basic/getHospitalsByArea");

	$.selectHospital2Dept($("#hospitalId1"), $("#deptId1"), "/medeasy-web/basic/getDeptsByHpCode");

	// update: hp的变化，trigger dept列表的变化
	$.selectHospital2Dept($("#hospitalId"), $("#deptId"), "/medeasy-web/basic/getDeptsByHpCode");

	$.genRoleSelect($("#roleId1"), "/medeasy-web/role/getSecRoles");

	// 重置密码为初始密码
	$("#resetPwd").click(function() {
		var id = $("#grid-table").jqGrid('getGridParam', 'selrow');

		if (id == null) {
			swa("", "请选择一条记录");
		} else {

			var rowData1 = $("#grid-table").getRowData(id);
			var userName = rowData1['username']
			swa_OkOrCancel("重置密码为初始密码", "重置后无法恢复为原密码", "/medeasy-web/user/resetPwdToOriginal", userName);
		}
	})

	// add user
	$("#add_grid-table").click(function() {
		$("#addUserModal").modal('show', function() {

		});
	});

	$("#btnAddUser").click(function() {
		/* console.log("btnAddUser click!"); */
		var areaV = $("#area_code1").val();
		var hospitalV = $("#hospitalId1 option:selected").val();
		var deptV = $("#deptId1 option:selected").val();
		var uNameV = $("#username1").val();

		var dataF = $("#addRoleForm").serializeObject();
		dataF.hospitalName = $("#hospitalId1 option:selected").text();
		dataF.hospitalCode = $("#hospitalId1 option:selected").val();
		dataF.deptName = $("#deptId1 option:selected").text();
		dataF.deptCode = $("#deptId1 option:selected").val();
		dataF.roleName = $("#roleId1 option:selected").text();
		dataF.areaName = $("#area_code1 option:selected").text();

		if (uNameV == null || uNameV == "") {
			swa_err("用户名为空", "");
		} else {

			$.ajax({
				url : "/medeasy-web/role/getTbRole",
				type : 'POST',
				dataType : 'json',
				data : {
					roleName : $("#roleId1 option:selected").text()
				},
				/* contentType : 'application/json; charset=UTF-8', */
				timeout : 20000,
				success : function(data) {

					var opLv = data.rows.operateLevel;
					console.log("opLv=" + opLv);

					switch (opLv) {
					case 2:
						if (opLv == 2 && areaV == '#') { // areaAdmin
							swa_err("请检查区已选择", "");
							break;
						}

					case 3:
						if (opLv == 3 && (areaV == '#' || hospitalV == '#')) { // hospitaAdmin
							swa_err("请检查区、医院都已选择", "");
							break;
						}

					case 6:
						if (opLv == 6 && (areaV == '#' || hospitalV == '#' || deptV == '#')) {
							swa_err("请检查区、医院、科室都已选择", "");
							break;
						}

					default:
						$.ajax({
							url : "/medeasy-web/user/addUser",
							type : 'POST',
							dataType : 'json',
							data : JSON.stringify(dataF),
							contentType : 'application/json; charset=UTF-8',
							timeout : 20000,
							success : function(data) {
								if (!data.success) {
									swa_err(data.prompt);
								} else {
									swa_suc("保存成功");
									$("#addUserModal").modal("hide");
									$("#grid-table").trigger("reloadGrid");
									$('#addRoleForm').trigger("reset");
								}
							}
						});
						break;
					}
				}
			});
		}

		/* console.log("dataF = " + JSON.stringify(dataF)); */
		/*
		 * $.ajax({ url : "/medeasy-web/user/addUser", type : 'POST', dataType :
		 * 'json', data : JSON.stringify(dataF), contentType :
		 * 'application/json; charset=UTF-8', timeout : 20000, success :
		 * function(data) { if (!data.success) { swa_err(data.prompt); } else {
		 * swa_suc("保存成功"); $("#addUserModal").modal("hide");
		 * $("#grid-table").trigger("reloadGrid");
		 * $('#addRoleForm').trigger("reset"); } } });
		 */
	})

	$("#addUserModal").on('shown.bs.modal', function() {
		$("#username1").focus();
		/* bootstrapValidator.resetForm().validate(); */

	});

	/*
	 * $('#addRoleForm').bootstrapValidator({
	 * 
	 * message: '此值无效', feedbackIcons: { valid: 'glyphicon glyphicon-ok',
	 * invalid: 'glyphicon glyphicon-remove', validating: 'glyphicon
	 * glyphicon-refresh' }, fields: { username:{ validators: { notEmpty: {
	 * message: '用户名不能为空' } } } } })
	 */

	// 修改user
	$("#edit_grid-table").click(function() {

		$.jqgridSltedRowCheck($("#grid-table"), $("#editUserModal"))
		/*
		 * $("#editUserModal").modal('show', function() {
		 * 
		 * });
		 */
	});

	// modal中的保存btn
	$.SaveUserData = function(hName, dName) {

		$("#btnEditUser").click(function() {

			var areaV = $("#area_code").val();
			var hospitalV = $("#hospitalId option:selected").val();
			var deptV = $("#deptId option:selected").val();

			var dataF = $("#editRoleForm").serializeObject();
			dataF.hospitalName = $("#hospitalId option:selected").text();
			dataF.deptName = $("#deptId option:selected").text();
			dataF.roleName = $("#roleId option:selected").text();
			dataF.areaName = $("#area_code option:selected").text();

			/* console.log("dataF = " + JSON.stringify(dataF)); */

			$.ajax({
				url : "/medeasy-web/role/getTbRole",
				type : 'POST',
				dataType : 'json',
				data : {
					roleName : $("#roleId option:selected").text()
				},
				/* contentType : 'application/json; charset=UTF-8', */
				timeout : 20000,
				success : function(data) {

					var opLv = data.rows.operateLevel;
					console.log("opLv=" + opLv);

					switch (opLv) {
					case 2:
						if (opLv == 2 && areaV == '#') { // areaAdmin
							swa_err("请检查区已选择", "");
							break;
						}

					case 3:
						if (opLv == 3 && (areaV == '#' || hospitalV == '#')) { // hospitaAdmin
							swa_err("请检查区、医院都已选择", "");
							break;
						}

					case 6:
						if (opLv == 6 && (areaV == '#' || hospitalV == '#' || deptV == '#')) {
							swa_err("请检查区、医院、科室都已选择", "");
							break;
						}

					default:

						$.ajax({
							url : "/medeasy-web/user/updateUser",
							type : 'POST',
							dataType : 'json',
							data : JSON.stringify(dataF),
							contentType : 'application/json; charset=UTF-8',
							timeout : 20000,
							success : function(data) {
								if (!data.success) {
									swa_err(data.prompt);
								} else {
									swa_suc("保存成功");
									$("#editUserModal").modal("hide");
									$("#grid-table").trigger("reloadGrid")
								}
							}
						});
						break;
					}
				}
			});

		});
	}
	$("#refresh_grid-table").css('display', 'none');
	// 更新btn
	$("#refresh_grid-table").click(function() {
		$("#grid-table").jqGrid('clearGridData');
		$("#grid-table").jqGrid("setGridParam", {
			url : "/medeasy-web/user/getUsers"
		})
		$("#grid-table").trigger('reloadGrid');
	})

	// searchbtn
	$("#btnSearchUser").click(function() {
		var mobj = $("#searchForm").serializeObject();

		$("#grid-table").jqGrid("setGridParam", {
			postData : mobj,
			url : "/medeasy-web/user/getUsersBySearch",
			page : 1

		}).trigger("reloadGrid");
	});

}); // end

function audit(val) {
	var rowDataEnabled = $("#grid-table").jqGrid('getRowData', val);
	var enabledVal = rowDataEnabled['enabled'];
	var postState;
	if (enabledVal == "正常") {
		postState = false;
	} else {
		postState = true;
	}
	/* console.log("hi"+enabledVal+postState); */
	$.ajax({
		url : "/medeasy-web/user/updateUserState",
		type : 'POST',
		dataType : 'json',
		data : {
			enabled : postState,
			username : rowDataEnabled['username']
		},
		/* contentType : 'application/json; charset=UTF-8', */
		timeout : 20000,
		success : function(data) {
			if (!data.success) {
				swa_err(data.prompt);
			} else {
				$("#grid-table").trigger("reloadGrid")
			}
		}
	});

}

// 在editusermodal 里，当roleName改变的时候， trigger to update role description
$.selectChange = function(selectCmp) {

	selectCmp.on('change', function(event) {

		var optionSelected = $(this).find("option:selected");
		var selected_roleName = optionSelected.text();

		$.ajax({
			url : "/medeasy-web/role/getSecRoleByName",
			"dataType" : "json",
			"data" : {
				roleName : selected_roleName
			},
			type : "POST",
			success : function(data) {
				$("#description").val(data.data.description);
			}
		});
	});
};

/* 显示所有的user list */
$.genRoleGrid = function(grid_selector, pager_selector) {

	var dataF1 = $("#searchForm").serializeObject();
	var mdata = JSON.stringify(dataF1);

	$("#grid-table").jqGrid(
			{
				datatype : 'json',
				contentType : "application/json; charset=UTF-8",
				url : '/medeasy-web/user/getUsersAccordRoleUnit',
				mtype : 'POST',

				height : 'auto',
				weight : 600,
				colNames : [ '账户审核', 'ID', '用户名', /* '姓名', */'医生', '区', '医院', '科室', '角色', '角色描述', '创建时间', '联系电话', '医院Id', 'deptId', 'roleId', '状态',
						'doctorCode'/* ,'账户审批' */, 'areaCode' ],
				colModel : [
						{
							name : 'enabled',
							index : 'enabled',
							width : 80,
							fixed : true,
							sortable : false,
							resize : false,
							formatter : function(value, grid, rows, state) {
								/* console.log("rows.id="+rows.id); */
								if (value == true) {
									return "<input checked=\"\" type=\"checkbox\" class=\"ace ace-switch ace-switch-5\"   onclick=\"audit(" + rows.id
											+ ")\"> <span class=\"lbl middle\"></span> </input>";
								} else {
									return "<input  type=\"checkbox\" class=\"ace ace-switch ace-switch-5\"   onclick=\"audit(" + rows.id
											+ ")\"> <span class=\"lbl middle\"></span> </input>";
								}
							}

						},

						{
							name : 'id',
							index : 'id',
							width : 60,
							hidden : true,
							/* sorttype : "Integer", */
							editable : false
						}, {
							name : 'username',
							index : 'username',
							width : 120,
							editable : false,
							editoptions : {
								size : "20",
								maxlength : "30"
							}
						}, {
							name : 'doctorName',
							index : 'doctorName',
							width : 100,
							editable : false,

						}, {
							name : 'areaName',
							index : 'areaName',
							width : 100,
							editable : false,

						}, {
							name : 'hospitalName',
							index : 'hospitalName',
							width : 200,
							editable : true,
						/* sorttype : 'string', */
						}, {
							name : 'deptName',
							index : 'deptName',
							width : 200,
							editable : true,
						},

						{
							/*
							 * name : 'role.roleName', index : 'role.roleName',
							 */
							name : 'roleName',
							index : 'roleName',
							width : 200,
							editable : false,

						},

						{
							/*
							 * name : 'role.description', index :
							 * 'role.description',
							 */
							name : 'description',
							index : 'description',
							width : 5,
							hidden : true,
							editable : false,

						}, {
							name : 'created',
							index : 'created',
							width : 1,
							hidden : true,
							editable : false,
							formatter : function(cellvalue, options, row) {
								/*
								 * return new Date(cellvalue).Format("yyyy-MM-dd
								 * hh:mm");
								 */
								return new Date(cellvalue).Format("yyyy-MM-dd");
							},
							editoptions : {
								size : "20",
								maxlength : "30"
							}
						}, {
							name : 'mobile',
							index : 'mobile',
							width : 100,
							hidden : true,
							editable : false,
						}, {
							name : 'hospitalCode',
							index : 'hospitalCode',
							width : 1,
							editable : false,
							hidden : true,
						}, {
							name : 'deptCode',
							index : 'deptCode',
							width : 1,
							editable : false,
							hidden : true,
						}, {
							/*
							 * name : 'role.id', index : 'role.id',
							 */

							name : 'roleId',
							index : 'roleId',
							width : 1,
							editable : false,
							hidden : true,
						}, {
							name : 'enabled',
							index : 'enabled',
							width : 90,
							editable : false,
							formatter : function(cellvalue, options, row) {
								/* console.log(cellvalue); */
								if (cellvalue == true)
									return "正常";
								else
									return "锁定";
							},
						},

						{
							name : 'doctorCode',
							index : 'doctorCode',
							width : 5,
							editable : false,
							hidden : true
						}, {
							name : 'areaCode',
							index : 'areaCode',
							width : 1,
							editable : false,
							hidden : true
						}, ],

				/*
				 * viewrecords : true, scroll : true,
				 */
				/*
				 * loadonce : true, sortable : true,
				 */
				/*
				 * autowidth: true, shrinkToFit: true,
				 */
				/*
				 * pgtext: "{0} 共 {1} 页", recordtext: "{0}-{1} 共 {2} 条",
				 * emptyrecords: "没有相关记录",
				 */
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : pager_selector,
				altRows : true,
				// toppager: true,

				multiselect : true,
				// multikey: "ctrlKey",
				multiboxonly : true,

				/*
				 * loadComplete : function() {
				 * 
				 * $("#grid-table").trigger("reloadGrid"); },
				 */
				loadComplete : function() {
					var table = this;
					setTimeout(function() {
						$.updatePagerIcons(table);
					}, 0);

				},

				onSelectRow : function(rowid, status) {
					var rowData = jQuery(this).getRowData(rowid);
					/* console.log("1: rowid="+rowid); */
					console.log("onSelectRow:" + rowData['areaCode'] + "." + rowData['hospitalCode'] + "." + rowData['deptCode'])
					if (rowData['areaCode'] != "" && rowData['hospitalCode'] != "")
						$.genSelectwithChosenArea("#hospitalId", 'basic/getHospitalsByArea', rowData['areaCode'], rowData['hospitalCode']);
					if (rowData['hospitalCode'] != "" && rowData['deptCode'] != "")
						$.getDeptsByHpCode("#deptId", rowData['hospitalCode'], rowData['deptCode']);

					/* 编辑时选中行后，查看用户信息 */
					$("#edit_grid-table").click(function() {
						$("#editUserModal").on('shown.bs.modal', function() {
							// for update user
							/* $.selectChange($("#roleId")); */
							$("#id").val(rowData['id']);
							$("#username").val(rowData['username']);
							/* $("#name").val(rowData['name']); */
							$("#mobile").val(rowData['mobile']);
							$("#doctorName").val(rowData['doctorName']);
							$("#doctorCode").val(rowData['doctorCode']);
							/* $("#description").val(rowData['role.description']); */
							/* $("#created").val(rowData['created']); */
							$("#enabled").val(rowData['enabled']);

							// updat user,时，区域的显示及默认
							if (rowData['areaCode'] != "")
								$("#area_code  option[value=" + rowData['areaCode'] + "]").prop('selected', true);
							else {
								$("#area_code  option[value=\"#\"]").prop('selected', true);
							}

							if (rowData['hospitalCode'] != "")
								$("#hospitalId  option[value=" + rowData['hospitalCode'] + "]").prop('selected', true);
							else {
								$("#hospitalId  option[value=\"#\"]").prop('selected', true);
							}

							if (rowData['deptCode'] == null || rowData['deptCode'] != "")
								$("#deptId  option[value=" + rowData['deptCode'] + "]").prop('selected', true);
							else {
								$("#deptId  option[value=\"#\"]").prop('selected', true);
							}
							/*
							 * $("#roleId option[value=" + rowData['role.id'] +
							 * "]").prop('selected', true);
							 */

							$("#roleId  option[value=" + rowData['roleId'] + "]").prop('selected', true);

							// 如果没有改变这些值得话，
							// 它的默认值
							hName = $("#hospitalId option:selected").text();
							dName = $("#deptId option:selected").text();

							$.SaveUserData(hName, dName);

						});
					}) /* end 编辑时选中行后，查看角色信息 */
				},

				beforeSelectRow : function(rowid, e) {
					$("#grid-table").jqGrid('resetSelection');
					return (true);
				},

				/* editurl : "/medeasy-web/role/addRole", */// 
				caption : "用户管理"
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

$.addUserValid = function() {

	$("#addRoleForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			username : {
				required : true,
			},
		},
		messages : {
			username : {
				required : "请输入用户名",
			},
		}
	})
}