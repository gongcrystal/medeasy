var appPath = "/";

var signOfRelease=true;//是否发布的标记，发布状态会屏蔽到调试的一些信息

var showDebugFlag_Flag=false;//是否展示调试信息

if(signOfRelease){
    if(window.parent){
        window.parent.allowMaxTabNum=16;
    }
    showDebugFlag_Flag=false;
}else{
    showDebugFlag_Flag=true;
    if(window.parent){
        window.parent.allowMaxTabNum=50;
    }
}

function toC(number) {
	return formatNumber(number, '#,##0.00');
}

function toCurrency(number) {
	if (isNaN(number) || number == "")
		return "";
	number = Math.round(number * 100) / 100;
	if (number < 0)
		return '-' + outputDollars(Math.floor(Math.abs(number) - 0) + '')
				+ outputCents(Math.abs(number) - 0);
	else
		return outputDollars(Math.floor(number - 0) + '')
				+ outputCents(number - 0);
}

function outputDollars(number) {
	if (number.length <= 3)
		return (number == '' ? '0' : number);
	else {
		var mod = number.length % 3;
		var output = (mod == 0 ? '' : (number.substring(0, mod)));
		for (i = 0; i < Math.floor(number.length / 3); i++) {
			if ((mod == 0) && (i == 0))
				output += number.substring(mod + 3 * i, mod + 3 * i + 3);
			else
				output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
		}
		return (output);
	}
}

function outputCents(amount) {
	amount = Math.round(((amount) - Math.floor(amount)) * 100);
	return (amount < 10 ? '.0' + amount : '.' + amount);
}

/**
 * 鏍煎紡鍖栨暟瀛楁樉绀烘柟寮� 鐢ㄦ硶 formatNumber(12345.999,'#,##0.00');
 * formatNumber(12345.999,'#,##0.##'); formatNumber(123,'000000');
 * 
 * @param num
 * @param pattern
 */
function formatNumber(num, pattern) {
	var strarr = num ? num.toString().split('.') : [ '0' ];
	var fmtarr = pattern ? pattern.split('.') : [ '' ];
	var retstr = '';

	// 鏁存暟閮ㄥ垎
	var str = strarr[0];
	var fmt = fmtarr[0];
	var i = str.length - 1;
	var comma = false;
	for ( var f = fmt.length - 1; f >= 0; f--) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			break;
		case '0':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			else
				retstr = '0' + retstr;
			break;
		case ',':
			comma = true;
			retstr = ',' + retstr;
			break;
		}
	}
	if (i >= 0) {
		if (comma) {
			var l = str.length;
			for (; i >= 0; i--) {
				retstr = str.substr(i, 1) + retstr;
				if (i > 0 && ((l - i) % 3) == 0)
					retstr = ',' + retstr;
			}
		} else
			retstr = str.substr(0, i + 1) + retstr;
	}

	retstr = retstr + '.';
	// 澶勭悊灏忔暟閮ㄥ垎
	str = strarr.length > 1 ? strarr[1] : '';
	fmt = fmtarr.length > 1 ? fmtarr[1] : '';
	i = 0;
	for ( var f = 0; f < fmt.length; f++) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			break;
		case '0':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			else
				retstr += '0';
			break;
		}
	}
	return retstr.replace(/^,+/, '').replace(/\.$/, '');
}

function padLeft(str, lenght) {
	if (str.length >= lenght)
		return str;
	else
		return padLeft("0" + str, lenght);
}

function getParam(name){
    var search = document.location.search;
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if(null != matcher){
    	try{
    		items = decodeURIComponent(decodeURIComponent(matcher[1]));
    	}catch(e){
    		try{
    			items = decodeURIComponent(matcher[1]);
    		}catch(e){
    			items = matcher[1];
    		}
    	}
    }
    return items;
}

function accAdd(arg1, arg2) {  
    var r1,r2,m;  
    try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;} 
    try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;} 
    m = Math.pow(10,Math.max(r1,r2));  
    return (arg1*m+arg2*m)/m;
	/*
	 * var r1, r2, s1, s2; try { r1 = parseInt(arg1.toString().split(".")[0]); }
	 * catch (e) { r1 = 0; } try { r2 = parseInt(arg2.toString().split(".")[0]); }
	 * catch (e) { r2 = 0; } try { s1 = parseInt(arg1.toString().split(".")[1]); }
	 * catch (e) { s1 = 0; } try { s2 = parseInt(arg2.toString().split(".")[1]); }
	 * catch (e) { s2 = 0; } r1 += r2; s1 += s2; return r1 + "." + s1;
	 */
}

function accMul(arg1,arg2) {  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length;}catch(e){} 
    try{m+=s2.split(".")[1].length;}catch(e){} 
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
}

function accDiv(arg1,arg2) {  
    var t1=0,t2=0,r1,r2;  
    try{t1=arg1.toString().split(".")[1].length;}catch(e){}  
    try{t2=arg2.toString().split(".")[1].length;}catch(e){}  
    with(Math){  
        r1=Number(arg1.toString().replace(".","")); 
        r2=Number(arg2.toString().replace(".",""));  
        return (r1/r2)*pow(10,t2-t1);  
    }  
}

function getCookie(c_name) {
	if (document.cookie.length>0) {
		var c_start=document.cookie.indexOf(c_name + "=");
		var c_end;
		if (c_start!=-1) {
			c_start = c_start + c_name.length + 1;
			c_end=document.cookie.indexOf(";", c_start);
			if (c_end == -1) 
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}



jQuery.prototype.mySerializeObject = function(){  
    var o = {}, j = $(this);
    j.find("input[name]").each(function(){
    	o[$(this).attr("name")] = $(this).val();
    });
    j.find("select[name]").each(function(){
    	o[$(this).attr("name")] = $(this).val();
    });
    j.find("textarea[name]").each(function(){
    	o[$(this).attr("name")] = $(this).val();
    });
    return o;  
};

function getCookie(c_name) {
	if (document.cookie.length>0) {
		var c_start=document.cookie.indexOf(c_name + "=");
		var c_end;
		if (c_start!=-1) {
			c_start = c_start + c_name.length + 1;
			c_end=document.cookie.indexOf(";", c_start);
			if (c_end == -1) 
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

Date.prototype.Format = function(fmt)   
{ // author: meizz
  var o = {   
    "M+" : this.getMonth()+1,                 // 月份
    "d+" : this.getDate(),                    // 日
    "h+" : this.getHours(),                   // 小时
    "m+" : this.getMinutes(),                 // 分
    "s+" : this.getSeconds(),                 // 秒
    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
    "S"  : this.getMilliseconds()             // 毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};  






jQuery.prototype.serializeObject = function(){
		    var a,o,h,i,e;  
		    a = this.serializeArray();  
		    o = {};  
		    h = o.hasOwnProperty;  
		    for(i=0;i<a.length;i++){  
		        e=a[i];  
		        if(!h.call(o,e.name)){  
		            o[e.name] = e.value;
		           /* console.log("name="+e.name +" value="+e.value); */
		        }  
		    }  
		   
		    return o;  
		};
		function swa_cf(title, text, func) {
			swal({   
				title: title || "确认吗？", 
				text: text || "", 
				type: "warning",
				showCancelButton: true, 
				confirmButtonText: "是的, 确认!", 
				cancelButtonText: "取消", 
				closeOnConfirm: true }, 
				func
				);
		}
		
		
		function swa_cf1(title, text) {
			swal({   
				title: title || "确认吗？", 
				text: text || "", 
				type: "warning",
				showCancelButton: false, 
				confirmButtonText: "确定",				
				closeOnConfirm: true }, 
				function(isConfirm){
					if(isConfirm){
						console.log(top.location);
						console.log(self.location);
						/*if (top.location != self.location){*/
							top.location.href="/medeasy-web/login" 
							/*}*/
						console.log("isConfirm="+isConfirm);
						/*location.reload();*/
					}
				})		
		};

		function swa_cf4ajax(title, text, func) {
			swal({
				  title: title || "确认吗？", 
				  text: text || "", 
				  type: "info",
				  showCancelButton: true,
				  closeOnConfirm: false,
				  showLoaderOnConfirm: true,
				  confirmButtonText: "是的, 确认!", 
				  cancelButtonText: "取消"
				},
				func);	
		}
		
		
		
		function swa_OkOrCancel(mtitle,mtext,url,userName) {
			swal({
				  title: mtitle, 
				  text: mtext, 
				  type: "warning",
				  showCancelButton: true, 
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "确定！", 
				  closeOnConfirm: false
				},
				function(){  
					
					$.ajax({
						"url" : url,
						"dataType" : "json",
						/* contentType : 'application/json; charset=UTF-8', */
						type : "POST",
						data : {userName:userName},
						success : function(data) {
							if (!data.success) {
								swa_err(data.prompt);
							}else{
								swal("密码重置！", "密码已被重置为初始密码。", "success"); 
								$("#grid-table").trigger("reloadGrid")
							}
						}
					}) // end update pwd
				});	
		}
		

		function swa(text, smalltext) {
			sweetAlert(text, smalltext ? smalltext : "");
		}

		function swa_html(text, smalltext) {
			swal({
				  title: text || "",
				  text: smalltext || "",
				  html: true
				});
		}

		function swa_err(text) {
			sweetAlert(text || "错误", "", "error");
		}
		
		function swa_err1(text) {
			sweetAlert(  "错误", text||"", "error");
		}

		function swa_suc(text, func) {
			if (!func)
				sweetAlert(text || "操作成功", "", "success");
			else
				swal({
					  title: text || "操作成功",
					  text: "",
					  type: "success",
					  showCancelButton: false,
					  confirmButtonText: "确定, 并刷新页面！",
					  closeOnConfirm: false
					},
					func);
		}

		function swa_pro(title, text, placeholder, func) {
			title = title || '请输入数据';
			text = text || '请输入数据';
			placeholder =  placeholder || '数据';
			func = func || function(inputValue) {
				if (inputValue === false) return false;      
				if (inputValue === "") {     
					swal.showInputError("请输入相关内容!");     
					return false;   
				}      
				swal("Nice!", "You wrote: " + inputValue, "success"); 
			};
			swal({   
				title: title,   
				text: text,   
				type: "input",   
				showCancelButton: true,   
				closeOnConfirm: true,   
				animation: "slide-from-top",  
				inputPlaceholder: placeholder }, 
				func
			);
		}

		function swa_pro4value(title, text, placeholder, inputValue, func) {
			title = title || '请输入数据';
			text = text || '请输入数据';
			placeholder =  placeholder || '数据';
			inputValue = inputValue || '';
			func = func || function(inputValue) {
				if (inputValue === false) return false;      
				if (inputValue === "") {     
					swal.showInputError("You need to write something!");     
					return false;   
				}      
				swal("Nice!", "You wrote: " + inputValue, "success"); 
			};
			swal({   
				title: title,   
				text: text,   
				type: "input",   
				inputValue: inputValue,
				showCancelButton: true,   
				closeOnConfirm: true,   
				animation: "slide-from-top",  
				inputPlaceholder: placeholder }, 
				func
			);
		}

		function swa_pro4ajax(title, text, placeholder, func) {
			title = title || '请输入数据';
			text = text || '请输入数据';
			placeholder =  placeholder || '数据';
			func = func || function(inputValue) {
				if (inputValue === false) return false;      
				if (inputValue === "") {     
					swal.showInputError("You need to write something!");     
					return false;   
				}      
				swal("Nice!", "You wrote: " + inputValue, "success"); 
			};
			swal({   
				title: title,   
				text: text,   
				type: "input",   
				showCancelButton: true,   
				closeOnConfirm: false,  
				showLoaderOnConfirm: true,
				animation: "slide-from-top",  
				inputPlaceholder: placeholder }, 
				func
			);
		}
		
		
		/* 添加jqgrid pager下的左侧按钮,用于分页，但是不用自带的button */
		$.genBtn = function(){
			var str = "<tbody><tr>" +
					"<td class=\"ui-pg-button ui-corner-all\" title=\"添加\" id=\"add_grid-table\"><div class=\"ui-pg-div\">" +
					"<span class=\"ui-icon ace-icon fa fa-plus-circle purple\"></span> </div></td>" +
					"<td class=\"ui-pg-button ui-corner-all\" title=\"修改\" id=\"edit_grid-table\"><div class=\"ui-pg-div\">" +
					"<span class=\"ui-icon ace-icon fa fa-pencil blue\"></span></div></td>" +
					"<td class=\"ui-pg-button ui-corner-all\" title=\"查看\" id=\"view_grid-table\"><div class=\"ui-pg-div\">" +
					"<span class=\"ui-icon ace-icon fa fa-search-plus grey\"></span></div></td>" +
					"<td class=\"ui-pg-button ui-corner-all\" title=\"重置密码为初始密码\" id=\"resetPwd\"><div class=\"ui-pg-div\">" +
					"<span class=\"ui-icon ace-icon fa fa-key orange\"></span></div></td>" +				
					"<td class=\"ui-pg-button ui-state-disabled\" style=\"width: 4px;\"><span class=\"ui-separator\"></span></td>" +
						
					"<td class=\"ui-pg-button ui-corner-all\" title=\"刷新\" id=\"refresh_grid-table\"><div class=\"ui-pg-div\">" +
					"<span class=\"ui-iconace-icon fa fa-refresh green\"></span></div></td>" +	
					"</tr>	</tbody>";
			$("#grid-pager_left").html(str);
		}

		
		
		// 查询条件-填充hospital select option
		$.getHospitals = function(selectComp){
		/* console.log("$.getHospitals"); */
			$.ajax({
				"url" : $.getWebRoot()+"/basic/getHospitalsByRole",
				"dataType" : "json",
				 type : "POST",
				success : function(data) {
					if(data.length==1){ //hospital admin, 对应确定的hospital 
						selectComp.append('<option selected value="'+data[0].hospitalCode +'">'+data[0].name+'</option>');
					}else{
						for(var i=0; i<data.length; i++){				 
							 selectComp.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
						 }
					}
				}
			})
		}
		
		//selectComp: hospital; select2LComp : dept
		$.getHospitals4UserAdd = function(selectComp,select2LComp){
			/* console.log("$.getHospitals"); */
				$.ajax({
					"url" : $.getWebRoot()+"/basic/getHospitalsByRole",
					"dataType" : "json",
					 type : "POST",
					success : function(data) {
						if(data.length==1){ //hospital admin, 对应确定的hospital 
							selectComp.append('<option selected value="'+data[0].hospitalCode +'">'+data[0].name+'</option>');
							//医院确定了， 就会显示dept的option 
							$.getDeptsByHpCode(select2LComp,data[0].hospitalCode,null);
							
						}else{
							for(var i=0; i<data.length; i++){				 
								 selectComp.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
							 }
						}
						
						
					}
				})
			}
	
		
		
		$.getHospitals1 = function(selectComp1,selectComp2,selectComp3){
			/* console.log("$.getHospitals"); */
				$.ajax({
					"url" : $.getWebRoot()+"/basic/getHospitalsByRole",
					"dataType" : "json",
					 type : "POST",
					success : function(data) {
									 
						 for(var i=0; i<data.length; i++){				 
							 selectComp1.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
							 selectComp2.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
							 selectComp3.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
						 }
					}
				})
			}

		// 查询条件-填充dept select option
		$.getDepts =function (selectComp){	
			/*console.log("$.getDepts");*/
			$.ajax({
				"url" : $.getWebRoot()+"/basic/getDepts",
				"dataType" : "json",
				 type : "POST",
				success : function(data) {
				/*	console.log("data =  "+data.length);*/
					 for(var i=0; i<data.length; i++){				 
						 selectComp.append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
					 }
				}
			})
		}
		
		
	/*
	 * $.getDepts1 =function (selectComp1,selectComp2){
	 * console.log("$.getDepts"); $.ajax({ "url" :
	 * $.getWebRoot()+"/basic/getDepts", "dataType" : "json", type : "POST",
	 * success : function(data) { console.log("data = "+data.length); for(var
	 * i=0; i<data.length; i++){ selectComp1.append('<option
	 * value="'+data[i].code +'">'+data[i].name+'</option>');
	 * selectComp2.append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
	 *  } } }) }
	 */
		
		// 通用的生成select options
		$.genRoleSelect =function (selectComp,url){	
			
			$.ajax({
				"url" : url,
				"dataType" : "json",
				 type : "POST",
				success : function(data) {						
					 for(var i=0; i<data.data.length; i++){	
						 selectComp.append('<option value="'+data.data[i].id +'">'+data.data[i].roleName+'</option>');
					 }
				}
			})
		}

// 取部署跟目录
$.getWebRoot=function GetWR(){
    var webroot=document.location.href;
    webroot=webroot.substring(webroot.indexOf('//')+2,webroot.length);
    webroot=webroot.substring(webroot.indexOf('/')+1,webroot.length);
    webroot=webroot.substring(0,webroot.indexOf('/'));
    return rootpath="/"+webroot;
}
// 日期格式化
Date.prototype.format = function(partten)
{
    if(partten ==null||partten=='')
    {
        partten = 'y-m-d'    ;
    }
    var y = this.getFullYear();
    var m = this.getMonth()+1;
    var d = this.getDate();
    var r = partten.replace(/y+/gi,y);
    r = r.replace(/m+/gi,(m<10?"0":"")+m);
    r = r.replace(/d+/gi,(d<10?"0":"")+d);
    return r;
}

// 封装的日期初始化
$.dateRangeHL=function DateRange(classId,startName,endName){
	if($(classId).attr("eltype")=="dateRangeYM"||$(classId).attr("eltype")=="dateRangeY"){
		if(typeof(startName)=="undefined"){startName=classId.replace("#","")+"start";}
		if(typeof(endName)=="undefined"){endName=classId.replace("#","")+"end";}
	}
	$.showMessage("debug",startName+":"+endName);
	$(classId).html('<input type="text" class="input-sm" name="'+startName+'" placeholder="开始日期"/><span class="input-group-addon"><i class="fa fa-exchange"></i></span><input type="text" class="input-sm" name="'+endName+'" placeholder="结束日期" />');

    $.fn.datepicker.dates['cn'] = {
        days: ["星期一", "星期二", "星期三", "星期四", "星期五","星期六"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
        daysMin: ["日", "一", "二", "三", "四", "五","六"],
        months: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        today: "今天",
        clear: "清除",
        format: "mm/dd/yyyy",
        titleFormat: "MM yyyy", /* Leverages same syntax as 'format' */
        weekStart: 0
    };
    var obj={
        classId:classId,
        YM:function (minViewMode,dateFormat) {
            $(classId).datepicker({autoclose:true,format:'yyyy-mm',minViewMode:1,language:'cn'});
            //$("[name='"+startName+"']").datepicker('setDate',(new Date()).format("y-m-d").substring(0,4)+'-01');
            $("[name='"+startName+"']").datepicker('setDate',(new Date()).format("y-m-d").substring(0,7));
            $("[name='"+endName+"']").datepicker('setDate',(new Date()).format("y-m-d").substring(0,7));
        },
        Y:function (minViewMode,dateFormat) {
            $(classId).datepicker({autoclose:true,format:'yyyy',minViewMode:2,language:'cn'});
            $("[name='"+startName+"']").datepicker('setDate',(new Date()).format("y-m-d").substring(0,4)+'-01');
            $("[name='"+endName+"']").datepicker('setDate',(new Date()).format("y-m-d").substring(0,7));
        }
    };

    if($(classId).attr("eltype")=="dateRangeYM"){
		obj.YM();
	}
	if($(classId).attr("eltype")=="dateRangeY"){
		obj.Y();
	}
    return obj;
}

var rGrp=[{text:"抗菌药",value:"2-1"}
    ,{text:'基本药物',value:"2-2"}
    ,{text:'注射剂',value:"ZSJ"}
    ,{text:'西药',value:"0-1"}
    ,{text:'中成药',value:"0-583"}
    ,{text:'饮片',value:"0-520"}];

var rGrpLong=[{text:"抗菌药",value:"2-1"}
    ,{text:'基本药物',value:"2-2"}
    ,{text:'注射剂',value:"ZSJ"}
    ,{text:'西药',value:"0-1"}
    ,{text:'中成药',value:"0-583"}
    ,{text:'辅助用药&营养药',value:"2-4"}
    // ,{text:'中药注射剂',value:"ZYZSJ"}
    ];

// 封装单选框
$.radioGroup=function RadioGroup(id,title){
	// $(id).html('<label class="control-label bolder
	// blue">'+title+'</label><br>');
	//
	$(id).css("padding","15px 10px 10px");
    var obj={
        id:id,
        name:"",
        init:function (data,name) {
            $.each(data, function(index, element) {
                $(id).append("<div class='form-group' style='margin-left:10px' ><label class='radio-inline'><input name='"+name+"' type='radio' onclick='if($(this).data(\"wasChecked\")==true){$(this).data(\"wasChecked\",false);this.checked=false;}else{$(\""+id+" input\").data(\"wasChecked\",false);$(this).data(\"wasChecked\",true);this.checked=true;}' value='"+element.value+"' /><span class='lbl'>"+element.text+"</span></label></div>");
            });
            obj.name=name;
            return obj;
        }
    }
    return obj;
}
// 封装折线图
$.lineChart=function LineChart(id){
    var obj={
        id:id,
        init:function (title,subtitle,datas,xAxisLabel,height) {
        	$("#"+id).css("height",height);
            var myChart = echarts.init(document.getElementById(id),"macarons");
            // myChart.showLoading({
            // text: '正在努力的读取数据中...', //loading话术
            // });
            if(datas) {
                var seriesData = new Array();
                var legend=new Array();

                $.each(datas, function (index, element) {
                    legend[index]=element.xaxis;
                    seriesData[index]={
                        name: element.xaxis,
                        type: 'line',
                        data: element.data,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    }
                });

                var option = {
                    title: {
                        text: title,
                        subtext: subtitle,
						x:"center"
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: legend,
						y:"bottom"
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xAxisLabel
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series: seriesData
                };
                myChart.setOption(option);
                // myChart.hideLoading();
                myChart.setTheme("macarons");
            }else{
                myChart.clear();
                myChart.dispose();
			}
        }
    }
    return obj;
}

// css设置
// 设置提示消息的窗口位置
document.write('<style type="text/css">.gritter-center1{    position:fixed;    left:33%;    right:33%;    top:20%}</style>');
// 去掉页面横向滚动条,调整查询部分控件的高度和间距，按钮的颜色
document.write('<style>html { overflow-x:hidden; } form .input-sm {height: 33px !important;padding: 0; 1border: 10px solid red;}.btn-info, .btn-info.focus, .btn-info:focus {background-color: #ff9000 !important;border-color: #ff9000;}.btn-info.focus:hover, .btn-info:active:hover, .btn-info:focus:active, .btn-info:focus:hover, .btn-info:hover, .open>.btn-info.dropdown-toggle, .open>.btn-info.dropdown-toggle.focus, .open>.btn-info.dropdown-toggle:active, .open>.btn-info.dropdown-toggle:focus, .open>.btn-info.dropdown-toggle:hover {    background-color: #ff7800 !important;    border-color: #ff7800;}</style>');

// if(!$("#fixColBG").attr("id")){//设置固定列的背景不透明
//     document.write('<style id="fixColBG">#grid-table_frozen td{background-color:#fff!important;}</style>');
// }

// 消息展示
$.showMessage=function(title,text) {
    if ("提示" == title) {
        $.gritter.add({
            title: title,
            text: text,
			time: 1500,
            speed:500,
            position: 'top-left',
            class_name: 'gritter-light gritter-center1'
        });
    } else {
    	if("debug"==title){
    		if(showDebugFlag_Flag) {
                $.gritter.add({
                    title: title,
                    text: text,
                    class_name: 'gritter-error gritter-light'
                });
            }
		}else{
            $.gritter.add({
                title: title,
                text: text,
                class_name: 'gritter-error gritter-light'
            });
		}
    }
}
// 封装的下拉列表初始化方法
/*
<select class="form-control" id="patient_type" name="patientTypeKey" eltype="chosen" dvalue="OUTPATIENT_URGENT" datakey="getPatientTypeSelect" style="width:150px" data-placeholder="处方类型:请选择"></select>
id:唯一标识，用于获取该组件
name:用于向后台传输数据
eltype:组件类型标识
dvalue:默认值的ID
datakey:加载数据的请求路径
*/
$.chosenSelect =function ChosenSelect(id){
	if($(id).attr("datakey")) {
        $(id).html("<option value=''></option>");
    }
    if(!$(id).css("width")||$(id).css("width").replace("px","")<150){
        $(id).css("width","150px");
	}
    // 先初始化数据
    var obj2=$(id).chosen(
        {
            no_results_text: "无匹配数据！",
            search_contains:true,   // 关键字模糊搜索，设置为false，则只从开头开始匹配
            allow_single_deselect:true, // 是否允许取消选择
        });

	obj2.selftAdjustWidth=function () {
        if ($(id).css("width") && $(id).css("width") != "100%" && $(id).css("width") != "20px") {
            $(id + "_chosen").css("width", $(id).css("width"));
        } else {
            $(id + "_chosen").css("width", "150px");
        }
    }

    var obj={
        id:id,
        chosenObj:obj2,
        loadData: function(dataObj,dValue,onchangeFunc){
            dataObj.selectKey=obj2.attr("datakey");
            $.showMessage("debug",JSON.stringify(dataObj));
            if(dataObj.selectKey) {
                obj2.html("<option value=''></option>");
                $.ajax({
                    "url": $.getWebRoot() + '/common/simpleSelect/',
                    "type": 'POST',
                    "dataType": 'json',
                    "timeout": 20000,
                    async:false,//带默认值时，如果采用异步加载表格数据初始化娶不到默认值
                    data: dataObj,
                    success: function (data) {
                        if (data) {
                            $.each(data, function (index, element) {
                                $(id).append("<option value='" + element.value + "'>" + element.text + "</option>");
                            });
                            if (dValue) {
                                $(id + " option[value='" + dValue + "']").attr('selected', 'selected');
                            }
                        } else {
                            $.showMessage("提示信息", id + "无数据！")
                        }
                        // $(id).trigger("chosen:updated");
                        obj2.trigger("chosen:updated");

                        obj2.selftAdjustWidth();
                    },
                    error: function (a, b, c) {
                        $.showMessage("error", "数据加载异常：" + b + c + a);
                    }
                });
            }
            obj2.selftAdjustWidth();
            return obj;
        }
    }
    return obj;
}
// 药品分类封装
$.drugCategoryTree=function(id){
    var tid=id.replace("#","");
    $(id).addClass("btn btn-white input-small");
    $(id).css("width","150px");
    $(id).append('<input type="text" class="form-control fade" style="display: none" placeholder="药品ID隐藏列" id="'+tid+'drugid" name="'+tid+'drugid"/>');
    $(id).append('<input type="text" class="form-control fade" style="display: none" placeholder="药品分类ID隐藏列" id="'+tid+'drugcategoryid" name="'+tid+'drugcategoryid"/>');
    $("body").append('<div class="modal fade"  id="'+tid+'drugClassSelect"><div class="modal-dialog"><div class="modal-content">            <div class="modal-header" style="height: 40px;"><button type="button" class="close" style="height: 30px" data-dismiss="modal" aria-hidden="true">&times;</button><h5 class="modal-title" style="font-weight: bold">            <span>药品与分类选择</span></h5>            </div>            <div class="tabbable">            <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="'+tid+'drugAndCategoryTree">            <li class="active">            <a data-toggle="tab" href="#'+tid+'drugTree">药品</a>            </li>            <li>            <a data-toggle="tab" href="#'+tid+'drugcategoryTree">药品分类</a>            </li>            </ul>            <div class="tab-content">            <div id="'+tid+'drugTree" class="tab-pane in active">            <div class="modal-body">            药品名称：        <input type="text" id="'+tid+'drugkey" value="" class="empty" />            <button type="button" class="btn btn-info btn-sm" id="'+tid+'btnDrugSelectOK">确定</button>            <!--button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button-->            <br/>            <div class="modal-footer">            <label for="" class="col-sm-2 control-label">药品列表: </label>        <div class="col-sm-12" style="height:300px;overflow: auto;">            <ul id="'+tid+'drugVauthTree" class="ztree"  readonly="readonly"></ul>            </div>            </div>            </div>            </div>            <div id="'+tid+'drugcategoryTree" class="tab-pane">            <div class="modal-body">            分类名称：        <input type="text" id="'+tid+'key" value="" class="empty" />            <button type="button" class="btn btn-info btn-sm" id="'+tid+'btnDrugCategorySelectOK">确定</button>            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>            <br/>            <div class="modal-footer">            <label for="" class="col-sm-2 control-label">药品分类: </label>        <div class="col-sm-12" style="height:300px;overflow: auto;">            <ul id="'+tid+'vauthTree" class="ztree"  readonly="readonly"></ul>            </div>            </div>            </div>            </div>            </div>            </div>            </div>            </div>            </div>');

    var lastValue = "", okNodeList=[], nodeList = [], fontCss = {};
    var druglastValue = "", drugokNodeList=[], drugnodeList = [], drugfontCss = {};
    var obj={
        id:id,
        key:{},
        drugkey:{},
        okDrugNodeList:[],
        okNodeList:[],
        viewDrugCategoryTree:function(zNodes,treeName,isHide) {
            var setting = {
                check : {
                    enable : true,
                    chkStyle : "checkbox",
                    chkboxType : {
                        "Y" : "ps",
                        "N" : "ps"
                    }
                },
                callback: {
                    onCheck: function(event, treeId, treeNode){
                        obj.setCheckedDrugNode();
                        obj.setCheckedCategoryNodes();
                        //alert(treeId)
						//alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
                    }
                },
                data : {
                    simpleData : {
                        enable : true
                    }
                },
                view: {
                    fontCss: obj.getFontCss
                }
            };
            var objTree=$.fn.zTree.init(treeName, setting, zNodes);
            if(isHide){
                obj.hidenAllDrug();
			}
        },
        searchNode:function searchNode(e) {
            var zTree = $.fn.zTree.getZTreeObj(tid + "vauthTree");
            var value = $.trim(obj.key.get(0).value);
            var keyType = "name";
            if (lastValue === value) return;
            lastValue = value;
            obj.updateNodes(false);
            if (value === "") {
                zTree.expandAll(false);
                return;
            }
            nodeList = zTree.getNodesByParamFuzzy(keyType, value);
            obj.updateNodes(true);
        },
        searchDrugNode:function searchNode(e) {
            var drugzTree = $.fn.zTree.getZTreeObj(tid+"drugVauthTree");
            var drugvalue = $.trim(obj.drugkey.get(0).value);
            var drugkeyType = "name";
            if (druglastValue === drugvalue) return;
            druglastValue = drugvalue;
            obj.updateDrugNodes(false);
            if (drugvalue === "") {
                drugzTree.expandAll(false);
                obj.hidenAllDrug();
                return;
            }
            drugnodeList = drugzTree.getNodesByParamFuzzy(drugkeyType, drugvalue);
            obj.hidenAllDrug();
            obj.updateDrugNodes(true);
        },
        hidenAllDrug:function(){
            var drugzTree = $.fn.zTree.getZTreeObj(tid+"drugVauthTree");
            //var allCheckedNodes = drugzTree.getCheckedNodes(true);
            var allUncheckedNodes = drugzTree.getCheckedNodes(false);
            drugzTree.hideNodes(allUncheckedNodes);
            //drugzTree.showNodes(allCheckedNodes);
		},
        updateNodes:function updateNodes(highlight) {
            var zTree = $.fn.zTree.getZTreeObj(tid+"vauthTree");
            for( var i=0, l=nodeList.length; i<l; i++) {
                nodeList[i].highlight = highlight;
                zTree.updateNode(nodeList[i]);
                // zTree.checkNode(nodeList[i], true, true);
                if(highlight){
                    if(nodeList[i].isParent){
                        zTree.expandNode(nodeList[i], true, false, true);
                    }else{
                        var pNode=nodeList[i].getParentNode();
                        if(pNode) {
                            zTree.expandNode(pNode, true, false, true);
                        }
                        zTree.expandNode(nodeList[i], true, false, true);
                    }
                }else{
                    if(nodeList[i].isParent) {
                        zTree.expandNode(nodeList[i], false, true, false);
                    }
                }
            }
        },
        updateDrugNodes:function updateNodes(highlight) {
            var zTree = $.fn.zTree.getZTreeObj(tid+"drugVauthTree");
            for( var i=0, l=drugnodeList.length; i<l; i++) {
            	if(drugnodeList[i].isHidden){
            		zTree.showNode(drugnodeList[i]);
				}
                drugnodeList[i].highlight = highlight;
                zTree.updateNode(drugnodeList[i]);
                // zTree.checkNode(nodeList[i], true, true);
                if(highlight){
                    if(drugnodeList[i].isParent){
                        zTree.expandNode(drugnodeList[i], true, false, true);
                    }else{
                        var pNode=drugnodeList[i].getParentNode();
                        if(pNode) {
                            zTree.expandNode(pNode, true, false, true);
                        }
                        zTree.expandNode(drugnodeList[i], true, false, true);
                    }
                }else{
                    if(drugnodeList[i].isParent) {
                        zTree.expandNode(drugnodeList[i], false, true, false);
                    }
                }
            }
        },
        getFontCss:function getFontCss(treeId, treeNode) {
            return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        },
        getDrugFontCss:function getFontCss(treeId, treeNode) {
            return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        }
    }
    // 分类树输入框初始化
    obj.key = $("#"+tid+"key");
    // obj.key.bind("propertychange", obj.searchNode)
    // obj.key.bind("input", obj.searchNode);
    obj.key.keydown(function (e) {
        var curKey = e.which;
        if (curKey == 13) {
            obj.searchNode(e);
        }
    });

    // 药品树输入框初始化
    obj.drugkey = $("#"+tid+"drugkey");
    // obj.key.bind("propertychange", obj.searchNode)
    // obj.key.bind("input", obj.searchNode);
    obj.drugkey.keydown(function (e) {
        var curKey = e.which;
        if (curKey == 13) {
            obj.searchDrugNode(e);
        }
    });

    // 加载药品分类树的数据
    $.ajax({
        "url" : "../bp/listDrugCategoryNodes",
        "dataType" : "json",
        success : function(data) {
            obj.viewDrugCategoryTree(data,$("#"+tid+"vauthTree"));
        }
    })

    // 加载药品树的数据
    $.ajax({
        "url" : "../bp/listDrugNodes",
        "dataType" : "json",
        success : function(data) {
            obj.viewDrugCategoryTree(data,$("#"+tid+"drugVauthTree"),true);
        }
    })

    // 查看药品分类
    $(id).click(function() {
        $("#"+tid+"drugClassSelect").modal('show', function() {
        });
    });

	obj.setCheckedCategoryNodes=function () {
        var zTree = $.fn.zTree.getZTreeObj(tid+"vauthTree");
        if(zTree){
            obj.okNodeList=zTree.getCheckedNodes(true);
            var idstr="";
            $.each(obj.okNodeList,function(index,ele){
            	if(ele.isParent==false) /*add by crystal2018-02-11 */
            		idstr=idstr+ele.id+",";
            })
            $("#"+tid+"drugcategoryid").val(idstr);
            $.showMessage("debug",idstr);
        }

        if(obj.okDrugNodeList.length>0&&obj.okNodeList.length>0){
            $("#"+tid+"drugcategoryid").val("");
            $.showMessage("debug","由于选择了药品药品分类数据被清空");
            $.showMessage("提示","存在药品选择，药品分类条件将不起作用！");
            return;
        }
    }
    // 药品分类选择确定按钮绑定
    $("#"+tid+"btnDrugCategorySelectOK").click(function(){
        $('#'+tid+'drugClassSelect').modal('hide');
    })

	obj.setCheckedDrugNode=function () {
        var zTree = $.fn.zTree.getZTreeObj(tid+"drugVauthTree");
        if(zTree){
            obj.okDrugNodeList=zTree.getCheckedNodes(true);
            var idstr="";
            $.each(obj.okDrugNodeList,function(index,ele){
                idstr=idstr+ele.id+",";
            })
            $("#"+tid+"drugid").val(idstr);
            $.showMessage("debug",idstr);
        }
    }
    // 药品选择确定按钮绑定
    $("#"+tid+"btnDrugSelectOK").click(function(){
        $('#'+tid+'drugClassSelect').modal('hide');
    })
}

// 页面组件初始化
/*
* */
$.initFormData=function() {
	var btnClassStr="btn btn-sm btn-info";

	$("form select").each(function (index, element) {

        if ("chosen" == $(element).attr("eltype")) {
            var dataobj = {
                selectKey: $(element).attr("datakey"),
                areaCode: ($("#area")==null ? "": $("#area").val()),
                hospitalCode: ($("#hospital")==null  ? "": $("#hospital").val())
            };

            $.chosenSelect("#" + $(element).attr("id")).loadData(dataobj,$("#" + $(element).attr("id")).attr("dvalue")).chosenObj.change(function () {
                if ($(this).attr("rObj")) {
                    $.showMessage("debug",$(this).attr("rObj"))
                    var robjarray = $(this).attr("rObj").split(',');

                    for (var i = 0; i < robjarray.length; i++) {
                    	if($("#" + robjarray[i]).attr("id")){
							$.chosenSelect("#" + robjarray[i]).loadData({
								selectKey: $("#" + robjarray[i]).attr("datakey"),
								areaCode: (($("#area")==null) ? "": $("#area").val()),
								hospitalCode: (($("#hospital")==null) ? "": $("#hospital").val())
							});
                        }
                    }
                }
            });
        }
    });
    $("form div").each(function(index,element){
        var eltype=$(element).attr("eltype");
        var elid="#"+$(element).attr("id");

        if("dateRangeYM"==eltype||"dateRangeY"==eltype){
            $.dateRangeHL(elid,$(element).attr("rangeStartName"),$(element).attr("rangeEndName"));
        }
        if("radio"==eltype){
            $.radioGroup(elid,$(element).attr("title")).init(eval($(element).attr("dataObj")),$(element).attr("radioName"));
        }

        if("drugcattree"==eltype){
            $.showMessage("debug","找到药品分类树！");
            $.drugCategoryTree(elid);
        }
    });
	$("form input[type='button']").each(
		function(index,element){
			$(this).addClass(btnClassStr);
		}
	);
    $("form input[type='reset']").each(
        function(index,element){
            $(this).addClass(btnClassStr);
        }
    );
}

// 清除datatable数据并销毁
$.clearAndDistoryDataTable=function(id){
    if ($(id).hasClass('dataTable')) {
        var dttable = $(id).dataTable();
        dttable.fnClearTable(); // 清空一下table
        dttable.fnDestroy(); // 还原初始化了的datatable
    }
}

// datatable 汉化
var oZHLanguage={
    "oAria": {
        "sSortAscending": ": 升序排列",
        "sSortDescending": ": 降序排列"
    },
    "oPaginate": {
        "sFirst": "首页",
        "sLast": "末页",
        "sNext": "下页",
        "sPrevious": "上页"
    },
    "sEmptyTable": "没有相关记录",
    "sInfo": "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
    "sInfoEmpty": "第 0 到 0 条记录，共 0 条",
    "sInfoFiltered": "(从 _MAX_ 条记录中检索)",
    "sInfoPostFix": "",
    "sDecimal": "",
    "sThousands": ",",
    "sLengthMenu": "每页显示条数: _MENU_",
    "sLoadingRecords": "正在载入...",
    "sProcessing": "正在载入...",
    "sSearch": "搜索:",
    "sSearchPlaceholder": "",
    "sUrl": "",
    "sZeroRecords": "没有相关记录",
    "select":{
        "rows":{_:" 选中 %d 行",0:"",1:" 选中 1 行"}
    }
}
if($.fn.dataTable){
    $.fn.dataTable.defaults.oLanguage=oZHLanguage;
}
// 区域的selection compSelect: selectId , postData:上传到server的数据 value=id
$.genSelect =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {				
			/* console.log("data.length = "+data.length); */
			 for(var i=0; i<data.length; i++){	
				 /* console.log(data[i].id+" "+data[i].name); */
				/*
				 * console.log("in genSelect area data[i].code =
				 * "+data[i].code);
				 */
				 $(compSelect).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
			 }			 
			
		}
	})	
}


$.genSelectIDValue =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {				
			/* console.log("data.length = "+data.length); */
			 for(var i=0; i<data.length; i++){	
				 /* console.log(data[i].id+" "+data[i].name); */
				/*
				 * console.log("in genSelect area data[i].code =
				 * "+data[i].code);
				 */
				 $(compSelect).append('<option value="'+data[i].id +'">'+data[i].name+'</option>');
			 }			 
			
		}
	})	
}


$.genSelectArea =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {	
			/* console.log("data.length="+data.length);*/
			 for(var i=0; i<data.length; i++){	
				 if(data.length==1){ //表示areaAdmin  or hospital Admin , areaCode是确定的
					 $(compSelect).html("");
					 //add: 可以根据role 找到对应的 hospital 
					 
				 }
				 $(compSelect).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
			 }	
		}
	})	
}
// mainly for adding  user 
$.genSelectArea4Add =   function(compSelect,comp2LSelect,comp3LSelect, url,postData){	

	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {	
			/* console.log("data.length="+data.length);*/
			 for(var i=0; i<data.length; i++){	
				 if(data.length==1){ //表示areaAdmin  or hospital Admin , areaCode是确定的
					 $(compSelect).html("");
					 //add: 可以根据role 找到对应的 hospital 
					 $.getHospitals4UserAdd($(comp2LSelect),$(comp3LSelect));
				 }
				 $(compSelect).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
			 }	
		}
	})	

	
}

$.genSelectValueIsCode =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {				
			/* console.log("data.length = "+data.length); */
			 for(var i=0; i<data.length; i++){	
				/* console.log(data[i].id+" "+data[i].name); */
				 $(compSelect).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
			 }			 
			
		}
	})	
}


$.genSelectwithChosenHospital =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {	
			/* console.log("hospital="+data.length)*/
			 for(var i=0; i<data.length; i++){	
				/* console.log(data[i].hospitalCode+" "+data[i].name); */
				 if(data.length==1){
					 $(compSelect).html("");
				 }
				 $(compSelect).append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
			 }
			 
			/* $(compSelect).chosen({allow_single_deselect:true}); */			
		
		}
	})	
}


$.genSelectwithChosen =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {		
			 for(var i=0; i<data.length; i++){	
				 $(compSelect).append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
			 }			 
			/* $(compSelect).chosen({allow_single_deselect:true}); */		
		}
	})	
}

// 将替换上面的
$.genSelectwithChosenCode =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {	
			 for(var i=0; i<data.length; i++){	
				 $(compSelect).append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
			 }			 
			/* $(compSelect).chosen({allow_single_deselect:true}); */			
		}
	})	
}


$.genAreaSelectwithChosenCode =  function(compSelect,url,postData){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: postData,
		 success : function(data) {		
			 for(var i=0; i<data.length; i++){	
				 $(compSelect).append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
			 }			 
			/* $(compSelect).chosen({allow_single_deselect:true}); */	
		}
	})	
}

// 当一个select组件变化的时候， 触发另外一个select值得变化 -
$.selectChangeArea2Hospital = function(selectMainCmp, selectSlaveCmp, url) {
	
	selectMainCmp.on('change', function(event) {

		var optionSelected = $(this).find("option:selected");
		var mainValue = optionSelected.val();
	/*	console.log("selectChangeArea2Hospital mainValue="+mainValue);*/

		$.ajax({
			url : url,
			"dataType" : "json",
			"data" : {
				areaCode: mainValue
				},
			type : "POST",
			success : function(data) {
				 selectSlaveCmp.html('<option value="#">医院:请选择</option>');
				 for(var i=0; i<data.length; i++){	
					 selectSlaveCmp.append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
				 }				 
				/* $(selectSlaveCmp).chosen({allow_single_deselect:true}); */
			}
		});
	});
};

// 当一个select组件变化的时候， 触发另外一个select值得变化 - 用code检索，替换上面的
$.selectHospital2Dept = function(selectMainCmp, selectSlaveCmp, url) {
	selectSlaveCmp.html("");
	
	selectMainCmp.on('change', function(event) {
	
		/*selectSlaveCmp.html("");*/		
		
		var optionSelected = $(this).find("option:selected");
		var mainValue = optionSelected.val();
		/* console.log("mainValue="+mainValue); */

		$.ajax({
			url : url,
			"dataType" : "json",
			"data" : {
				hospitalCode: mainValue
				},
			type : "POST",
			success : function(data) {
				 selectSlaveCmp.html('<option value="#">科室:请选择</option>');
				 for(var i=0; i<data.length; i++){	
					 selectSlaveCmp.append('<option value="'+data[i].code +'">'+data[i].name+'</option>')
				 }
			}
		});
	});
};


$.selectHospital2Dept1 = function(selectMainCmp, selectSlaveCmp, url) {	
	
	selectMainCmp.on('change', function(event) {
		
		selectSlaveCmp.html("");		
		selectSlaveCmp.append("<option value=\"#\">科室:请选择</option>");
		
		var optionSelected = $(this).find("option:selected");
		var mainValue = optionSelected.val();
		/* console.log("mainValue="+mainValue); */

		$.ajax({
			url : url,
			"dataType" : "json",
			"data" : {
				hospitalCode: mainValue
				},
			type : "POST",
			success : function(data) {
				
				 for(var i=0; i<data.length; i++){	
					 selectSlaveCmp.append('<option value="'+data[i].code +'">'+data[i].name+'</option>')
				 }
			}
		});
	});
};



// 检索的click button
$.searchBtn = function(searchButton, searchform,gridTable,url){
	
	searchButton.click(function(){	
		
		var dataH = searchform.serializeObject();
		/*console.log("dataH="+JSON.stringify(dataH));*/
		dataH.checkedNodesStr=
		
		gridTable.jqGrid("setGridParam", {
			postData : dataH,
			url :url
		}).trigger("reloadGrid");	
	})
}

// 检索的click button
$.searchWithDrugCBtn = function(searchButton, searchform,gridTable,url,drugC){
	
	searchButton.click(function(){	
		/*console.log("drugC="+drugC);*/
		
		var dataH = searchform.serializeObject();
		dataH.checkedNodesStr = drugC;
		
		gridTable.jqGrid("setGridParam", {
			postData : dataH,
			url :url
		}).trigger("reloadGrid");	
	})
}

// Range picker 到天的选择
$.dateRange = function(){	
	$('#rangePicker2Day').datepicker({
		autoclose : true,
		language : "zh-CN",
		defaultDate : new Date(),
		todayHighlight : true,
		format : "yyyy-mm-dd"	
	});
}


$.dataPicker2MonthSet = function(){	
	$('#rangePicker2Month').datepicker({
		autoclose : true,
		language : "zh-CN",
		defaultDate : new Date(),
		todayHighlight : true,
		/* format : "yyyy-mm-dd" */
		format : "yyyy-mm",
		viewMode: "months", 
	    minViewMode: "months"
	});
	var now = new Date();
	var fnow = now.format("yyyy-MM");

	$("#startDate").attr("placeholder",fnow);
	$("#endDate").attr("placeholder",fnow);	
	
	$("#startDate").attr("value",fnow);
	$("#endDate").attr("value",fnow);	
}

$.easyPieChartSet = function(){
	$('.easy-pie-chart.percentage').each(function(){
		$(this).easyPieChart({
			barColor: $(this).data('color'),
			trackColor: '#EEEEEE',
			scaleColor: false,
			lineCap: 'butt',
			lineWidth: 8,
			animate: ace.vars['old_ie'] ? false : 1000,
			size:75
		}).css('color', $(this).data('color'));
	});
}

$.genDrugCategory = function(btnComp,modalComp){
	
	// 生成modal
	$(btnComp).click(function() {
		modalComp.modal('show', function() {
			
		});
	});
	
	modalComp.on('shown.bs.modal', function() {
		
		
	})	
}


$.genZTree = function(zNodes,treeName) {
	
	var setting = {
			view: {fontCss: setFontCss}, 
			check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
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

 

 function setFontCss(treeId, treeNode) {  
    return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};  
}  

// pbCmpOut 对应外层<div> pbCmp:..内层
$.setPBar = function(pbCmpOut, pbCmp,val){	
	pbCmp.css('width', val+'%')  ;	 
	/* pbCmpOut.attr('data-percent', val+'%'); */ // 显示在外面的值
}


$.setPBarWithTitle = function(pbCmpOut, pbCmp,val,mtitle){	
	pbCmp.css('width', val+'%');
	/* pbCmpOut.attr('data-percent', mtitle); */// 显示在外面的值
	pbCmpOut.attr('data-percent', val+'%');
}

// 高亮显示被搜索到的节点
function  highLightNodes (zTree,nodeList,isHlight) {     
    for( var i=0, l=nodeList.length; i<l; i++) {  
        nodeList[i].highlight = isHlight; // 高亮显示搜索到的节点(highlight是自己设置的一个属性)
        
        if(isHlight){
			if(nodeList[i].isParent){
                zTree.expandNode(nodeList[i], true, false, true);
			}else{
				var pNode=nodeList[i].getParentNode();
				if(pNode) {
                    zTree.expandNode(pNode, true, false, true);
                }
                zTree.expandNode(nodeList[i], true, false, true);
			}
		}else{
            if(nodeList[i].isParent) {
                zTree.expandNode(nodeList[i], false, true, false);
            }
		}        
       /* zTree.expandNode(nodeList[i].getParentNode(), true, false, false); */ // 将搜索到的节点的父节点展开
        zTree.updateNode(nodeList[i]); // 更新节点数据，主要用于该节点显示属性的更新
    }  
} 



$.searchKeyDown = function(cmp){
	var nodes=null;
	
	
	$(cmp).keydown(function() {	
		if (event.keyCode === 13) {			
			var treeObj = $.fn.zTree.getZTreeObj("mzTree");	
		
			
			if(nodes!=null){
				highLightNodes(treeObj, nodes,false);
			}
			var searchKeyword = $("#drugCKeyword").val();
			var isHlight=false;
			if(searchKeyword==""){
				isHlight = false;
			}else{
				isHlight = true;
			}
				
			nodes = treeObj.getNodesByParamFuzzy("name", $.trim(searchKeyword));
			highLightNodes(treeObj, nodes,isHlight);
		}
	});
}


$.genDrugIncomeGrid = function(grid_selector, pager_selector) {
	$("#gridtb_drugincome").jqGrid({
		datatype : 'json',
		contentType : "application/json; charset=UTF-8",
		url : '/medeasy-web/druguse/drugIncome',
		mtype : 'POST',
		height : 'auto',
		weight : 600,
		colNames : [ '项目名称  ', '总费用(元）', '金额总占比%', '人均药品使用金额（元）', '平均处方金额（元）', '药品周转率%' ],
		colModel : [ {
			name : 'drugItemName',
			index : 'drugItemName',
			width : 150,
			/* sorttype : "Integer", */
			editable : false
		}, {
			name : 'mamount',
			index : 'mamount',
			width : 100,
			editable : false
		}, {
			name : 'amountPerAreaTotal',
			index : 'amountPerAreaTotal',
			width : 90,
			editable : false
		}, {
			name : 'amountAvgPt',
			index : 'amountAvgPt',
			width : 150,
			editable : false
		}, {
			name : 'amountAvgRx',
			index : 'amountAvgRx',
			width : 120,
			editable : false
		}, {
			name : 'turnoverRate',
			index : 'turnoverRate',
			width : 100,
			editable : false
		}

		],
		autowidth: true,
        shrinkToFit: true,
        
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		/* pager : pager_selector, */
		altRows : true,
		multiselect : false,
		// multikey: "ctrlKey",
		multiboxonly : false,
		caption : "药品使用金额统计"
	});
}

$.genDrugUseGrid = function(grid_selector, pager_selector) {

	$("#grid-table").jqGrid(
			{
				datatype : 'json',
				/* contentType : "application/json; charset=UTF-8", */
				contentType : "application/json",
				url : '/medeasy-web/druguse/drugUseStHospital',
				mtype : 'POST',
				formatter: {
			        number: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 4, defaultValue: '0.0000' }
			    },

				height : 'auto',
				/*weight : 600,*/
				colNames : [ '排名', '医院名称', '区名称', '就诊人次', '处方数', '药品品种数', '药品使用金额(元)', /* '金额总占比%', */ '药占比%', '人均药品使用金额(元)', '人均药品品种数', '大处方百分率%',
						'平均处方金额(元)', '人均用药天数(天)', '用药天数(天)', '药品周转率%', '科室', '医师' ],
				colModel : [ {
					name : 'rankId',
					index : 'rankId',
					frozen: true,
					width : 50,
				/* sorttype : "Integer", */
					editable : false
				}, {
					name : 'hospitalName',
					index : 'hospitalName',
					frozen: true,
					width : 200,
					editable : false
				}, {
					name : 'areaName',
					index : 'areaName',
					width : 100,
					editable : false
				}, {
					name : 'regAmount',
					index : 'regAmount',
					width : 80,
					editable : false
				}, {
					name : 'rxAmount',
					index : 'rxAmount',
					width : 60,
					editable : false
				}, {
					name : 'drugSpecAmount1',
					index : 'drugSpecAmount1',
					width : 90,
					editable : false
				},
				/*
				 * {name : 'amountPerHp',index : 'amountPerHp', width : 100,
				 * editable : false},
				 */
				{
					name : 'mamount',
					index : 'mamount',
					width : 130,
					align: "right",
					formatter: 'number',
					editable : false
					
				}, /*
					 * { name : 'amountPerAreaTotal', index :
					 * 'amountPerAreaTotal', width : 100, editable : false },
					 */ {
					name : 'amountPerTotal',
					index : 'amountPerTotal',
					width : 80,
					
					editable : false
				}, {
					name : 'amountAvgPt',
					index : 'amountAvgPt',
					align: "right",
					formatter:'number',
					width : 140,
					editable : false
				}, {
					name : 'drugSpecAvgPt',
					index : 'drugSpecAvgPt',
					width : 120,
					editable : false
				}, {
					name : 'perbigRx',
					index : 'perbigRx',
					width : 100,
					
					editable : false
				}, {
					name : 'amountAvgRx',
					index : 'amountAvgRx',
					width : 110,
					align: "right",
					editable : false
				}, {
					name : 'drugDayAvgPt',
					index : 'drugDayAvgPt',
					width : 130,
					editable : false
				}, {
					name : 'drugDayPerHp',
					index : 'drugDayPerHp',
					formatter:'number',
					width : 110,
					editable : false
				}, {
					name : 'turnoverRate',
					index : 'turnoverRate',
					width : 100,
					
					editable : false
				}, {
					name : 'depts',
					index : 'depts',
					width : 80,
					editable : false
				}, {
					name : 'drs',
					index : 'drs',
					width : 80,
					editable : false
				}, ],

				
				/*autowidth: true,
			    shrinkToFit: true,*/
			    
				viewrecords : true,
				rowNum : -1,
				/*rowList : [ 10, 20, 30 ],*/
				/* pager : pager_selector, */
				altRows : true,
				multiselect : false,
				// multikey: "ctrlKey",
				multiboxonly : false,
				caption : "药品使用按医院统计"					
			});
	/*$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });*/
	$("#grid-table").jqGrid('setFrozenColumns');
}

// 确定后，提交药品分类名称
/*
 * $.submitDrugCategory = function(cmp,cmp1,form1,gridtable,murl){
 * $(cmp).click(function(){ var treeObj = $.fn.zTree.getZTreeObj("mzTree"); var
 * checkedNodes = treeObj.getChangeCheckedNodes(); var checkedNodesStr = ""; for
 * (var i = 0; i < checkedNodes.length; i++) {
 * if(checkedNodes[i].isParent==false){ if (i != checkedNodes.length - 1) {
 * checkedNodesStr += checkedNodes[i].name+ "-"; console.log("crystal:
 * "+checkedNodes[i].id); } else { checkedNodesStr += checkedNodes[i].name; } } } //
 * 点击检索btn console.log("1checkedNodesStr="+$.trim(checkedNodesStr));
 * $.searchWithDrugCBtn($("#btnSearchIncome"), $("#searchIncomeForm"),
 * $("#grid-table"), "/medeasy-web/bp/rxevast",$.trim(checkedNodesStr));
 * $("#drugCategoryModal").modal("hide");
 * 
 * $(cmp1).click(function(){
 * 
 * var dataH = $(form1).serializeObject(); dataH.checkedNodesStr =
 * checkedNodesStr; console.log("checkedNodesStr = "+checkedNodesStr);
 * $(gridtable).jqGrid("setGridParam", { postData : dataH, url :murl,
 * }).trigger("reloadGrid"); }) }) }
 */

$.jqgridSltedRowCheck = function(jqtable,mmodal){
	
	var id = jqtable.jqGrid('getGridParam','selrow');
	
	if(id==null){
		swa("", "请选择一条记录") 
	}else{		
		mmodal.modal('show', function() {

		});
		
	}
}


$.updatePagerIcons = function(table) {
    var replacement = 
    {
        'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
        'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
        'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
        'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        
        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    })
}


$.evaChartDefault = function(purl){
	$.ajax({
		/* "url" : "/medeasy-web/bp/rxevast4hp", */
		"url" : purl,
		"dataType" : "json",
		type : "POST",
		success : function(data) {

			/*
			 * $.setPBar($("#noOkPercent_out"), $("#noOkPercent"),
			 * data.perUnOk);
			 */ // 不合理处方
			$.setPBarWithTitle($("#noOkPercent_out"), $("#noOkPercent"), data.perUnOk, "不合理处方"); // 不合理处方
			$.setPBarWithTitle($("#unReason3_out"), $("#unReason3"), data.perUnr3, "超常处方"); // unReasonableAmount3
			$.setPBarWithTitle($("#unReason2_out"), $("#unReason2"), data.perUnr2, "用药不适宜处方");
			$.setPBarWithTitle($("#unReason1_out"), $("#unReason1"), data.perUnr1, "不规范处方");
			$.setPBarWithTitle($("#reasonRx_out"), $("#reasonRx"), data.perOk,"合理处方");
		}
	});	
}

$.evaChart = function(purl,pdata){
	$.ajax({
		/* "url" : "/medeasy-web/bp/rxevast4hp", */
		"url" : purl,
		"dataType" : "json",
		type : "POST",
		data: pdata,
		success : function(data) {
			

			/*
			 * $.setPBar($("#noOkPercent_out"), $("#noOkPercent"),
			 * data.perUnOk);
			 */ // 不合理处方
			$.setPBarWithTitle($("#noOkPercent_out"), $("#noOkPercent"), data.perUnOk, "不合理处方"); // 不合理处方
			$.setPBarWithTitle($("#unReason3_out"), $("#unReason3"), data.perUnr3, "超常处方"); // unReasonableAmount3
			$.setPBarWithTitle($("#unReason2_out"), $("#unReason2"), data.perUnr2, "用药不适宜处方");
			$.setPBarWithTitle($("#unReason1_out"), $("#unReason1"), data.perUnr1, "不规范处方");
			$.setPBarWithTitle($("#reasonRx_out"), $("#reasonRx"), data.perOk,"合理处方");
		}
	});	
}
//根据areacode，检索hotpital select 
$.genSelectwithChosenArea =  function(compSelect,url,areaCode,hospitalCode){
	$.ajax({
		"url" : url,
		"dataType" : "json",
		 type : "POST",
		 data: {areaCode: areaCode},
		 success : function(data) {	
			/* console.log("areaCode="+areaCode)*/
			 $(compSelect).html("");
			 $(compSelect).append('<option value="#">医院:请选择</option>');
			 for(var i=0; i<data.length; i++){	
				/* console.log(data[i].hospitalCode+" "+data[i].name); */
				 if(data.length==1){
					 $(compSelect).html("");
				 }
				
				 $(compSelect).append('<option value="'+data[i].hospitalCode +'">'+data[i].name+'</option>');
			 }
			 console.log("genSelectwithChosenArea hospitalCode="+hospitalCode);
			 $(compSelect + "  option[value=" + hospitalCode + "]").prop('selected', true);
			 
			/* $(compSelect).chosen({allow_single_deselect:true}); */
			 
			 //根据某个值来设置被选中
		
		}
	})	
}


// 查询条件-填充dept select option, 4 update 
$.getDeptsByHpCode =function (selectComp,hpCode,deptCode){	
	/*console.log("getDeptsByHpCode hpCode="+hpCode);*/
	$.ajax({
		"url" : $.getWebRoot()+"/basic/getDeptsByHpCode",
		"dataType" : "json",
		 type : "POST",
		 "data":{hospitalCode:hpCode},
		success : function(data) {
			/*console.log("data =  "+data.length);*/
			$(selectComp).html("");
			 $(selectComp).append('<option value="#">科室:请选择</option>');
			 for(var i=0; i<data.length; i++){				 
				 $(selectComp).append('<option value="'+data[i].code +'">'+data[i].name+'</option>');
			 }
			 if(deptCode!=null){
				 $(selectComp + " option[value=" + deptCode + "]").prop('selected', true);
			 }			
		} 				
	})
}




/*
 *封装的JQgrid生成函数
 gSelector:表格的#ID
 pSelector:分页#ID
 gUrl:数据请求地址
 gColModel:表格的列模型配置信息
 reqFormSelector:查询条件Form的#ID
 gCaption：表格的标题
*/
/*
使用方式：
 var gridColModel=[
 {
 label:'列标题1',
 sortable: false,
 name : 'spec',
 index : 'spec',
 width : 120,
 editable : false
 },
 ...
 {
 label:'列标题N',
 sortable: false,
 name : 'deptCount',
 index : 'deptCount',
 width : 50,
 editable : false
 }];
 var JQGridObj=$.genJQGridHL("#grid-table",  "#grid-pager",$.getWebRoot()+'/bp/abxDDDstatisticsData',gridColModel,"#searchForm","抗菌药物使用强度排名");
 JQGridObj.registerButtonHL("#btnSearchAbxDDDstatistics");//把查询按钮注册给grid

 * */
$.genJQGridHL = function(gSelector, pSelector, gUrl, gColModel, reqFormSelector, gCaption) {
    $.showMessage("debug",gCaption+":"+JSON.stringify( $(reqFormSelector).serializeObject()));

    var obj=$(gSelector).jqGrid({
        datatype : 'json',
        contentType : "application/json; charset=UTF-8",
        url : gUrl,
        mtype : 'POST',
        postData : $(reqFormSelector).serializeObject(),
        height : 'auto',
        colModel :gColModel,
        shrinkToFit:false,
        autoScroll: true,
        viewrecords : true,
        rownumbers: true,
        rowNum : 15,
        rowList : [ 10, 20, 30 ],
        pager : pSelector,
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
        caption : gCaption
    });

    $(gSelector).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
    $(gSelector).jqGrid('setFrozenColumns');

    //表格宽度自适应
    $(window).resize(function(){
        $(gSelector).setGridWidth($(window).width());
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
	/*
	 $(document).one('ajaxloadstart.page', function(e) {
	 $.jgrid.gridDestroy(grid_selector);
	 $('.ui-jqdialog').remove();
	 });

	 $(grid_selector).jqGrid('navGrid', '#grid-pager', {
	 // navbar options
	 edit : false,
	 editicon : 'ace-icon fa fa-pencil blue',
	 add : false,
	 addicon : 'ace-icon fa fa-plus-circle purple',
	 // addfunc:openDialog4Adding,
	 del : false,
	 delicon : 'ace-icon fa fa-trash-o red',
	 search : false,
	 searchicon : 'ace-icon fa fa-search orange',
	 refresh : false,
	 refreshicon : 'ace-icon fa fa-refresh green',
	 view : false,
	 viewicon : 'ace-icon fa fa-search-plus grey',
	 }, {});
	 */

    obj.refreshdataHL=function(){
        $.showMessage("debug",gCaption+":"+JSON.stringify( $(reqFormSelector).serializeObject()));
        $(gSelector).jqGrid("setGridParam", {
            postData : $(reqFormSelector).serializeObject(),
            page : 1
        }).trigger("reloadGrid");
    }

    obj.registerButtonHL=function(selector){
        $(selector).click(function() {
            obj.refreshdataHL();
        });
    }
    return obj;
};
