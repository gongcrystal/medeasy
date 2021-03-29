$(function() {

	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	$("#grid-table").jqGrid({

		/* data: grid_data, */
		datatype : 'json',
		contentType : "application/json",
		url : '/medeasy-web/basicdt/getDrRanks',
		mtype : 'POST',
		/*
		 * postData:{ "page":1, "rows":2, },
		 */
		height : 250,
		weight : 600,
		colNames : [ ' ', 'rankId', 'rankName' ],
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
					url : "/medeasy-web/basicdt/delDrRank",
					mtype : 'POST',
					reloadAfterSubmit : true,
					ajaxDelOptions : {
						contentType : "application/json"
					},
					serializeDelData : function(postdata) {
						var rowdata = jQuery('#grid-table').getRowData(postdata.id);
						return JSON.stringify(rowdata);
						/*
						 * return JSON.stringify({rankId:
						 * postdata.rankId,rankName: postdata.rankName });
						 */
					}

				}

			// editformbutton:true, editOptions:{recreateForm: true,
			// beforeShowForm:beforeEditCallback}
			}
		}, {
			name : 'rankId',
			index : 'rankId',
			width : 150,
			sorttype : "Integer",
			editable : true
		}, {
			name : 'rankName',
			index : 'rankName',
			width : 350,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "30"
			}
		}, ],

		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		// toppager: true,

		multiselect : true,
		// multikey: "ctrlKey",
		multiboxonly : true,

		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				/* styleCheckbox(table); */

				/* updateActionIcons(table); */
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},

		editurl : "/medeasy-web/basicdt/addDrRank",// nothing is saved
		caption : "jqGrid with inline editing"

	});

	$("#grid-table").jqGrid('navGrid', '#grid-pager', {
		// navbar options
		edit : true,
		editicon : 'ace-icon fa fa-pencil blue',
		add : true,
		addicon : 'ace-icon fa fa-plus-circle purple',
		addfunc:openDialog4Adding, 
		del : true,
		delicon : 'ace-icon fa fa-trash-o red',
		search : true,
		searchicon : 'ace-icon fa fa-search orange',
		refresh : true,
		refreshicon : 'ace-icon fa fa-refresh green',
		view : true,
		viewicon : 'ace-icon fa fa-search-plus grey',
	}, {

	}, {

	}, {
		url : "/medeasy-web/basicdt/delDrRanks",
		mtype : 'POST',
		reloadAfterSubmit : true,
		ajaxDelOptions : {
			contentType : "application/json"
		},
		serializeDelData : function(postdata) {
			var rowdata = jQuery('#grid-table').getRowData(postdata.id);
			var selectedrows = $('#grid-table').jqGrid("getGridParam", "selarrrow");
			var selectedData = [];
			alert("selectedrows.size=" + selectedrows.length);
			for (var j = 0; j < selectedrows.length; j++) {
				selectedData.push($('#grid-table').jqGrid("getRowData", selectedrows[j]));
			}
			return JSON.stringify(selectedData);
			/*
			 * return JSON.stringify({rankId: postdata.rankId,rankName:
			 * postdata.rankName });
			 */
		}

	},
	{
		//search form
		recreateForm: true,
		afterShowSearch: function(e){
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
			style_search_form(form);
		},
		afterRedraw: function(){
			style_search_filters($(this));
		}
		,
		multipleSearch: true,
		
		/*multipleGroup:true,*/
		showQuery: true
		
	})
	
	
	function style_search_filters(form) {
					form.find('.delete-rule').val('X');
					form.find('.add-rule').addClass('btn btn-xs btn-primary');
					form.find('.add-group').addClass('btn btn-xs btn-success');
					form.find('.delete-group').addClass('btn btn-xs btn-danger');
				}
	function style_search_form(form) {
					var dialog = form.closest('.ui-jqdialog');
					var buttons = dialog.find('.EditTable')
					buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
					buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
					buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
				}
	function style_delete_form(form) {
		alert("hi");
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();// ui-icon,
																							// s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
	}

	function pickDate(cellvalue, options, cell) {
		setTimeout(function() {
			$(cell).find('input[type=text]').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true
			});
		}, 0);
	}

	function aceSwitch(cellvalue, options, cell) {
		setTimeout(function() {
			$(cell).find('input[type=checkbox]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
		}, 0);
	}

	// replace icons with FontAwesome icons like above
	// replace icons with FontAwesome icons like above
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
		})
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}

	// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

	$(document).one('ajaxloadstart.page', function(e) {
		$.jgrid.gridDestroy(grid_selector);
		$('.ui-jqdialog').remove();
	});

});