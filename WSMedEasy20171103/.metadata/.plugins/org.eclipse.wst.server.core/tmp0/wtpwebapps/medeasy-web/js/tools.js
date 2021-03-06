var appPath = "/";

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
 * 鏍煎紡鍖栨暟瀛楁樉绀烘柟寮� 
 * 鐢ㄦ硶 
 * formatNumber(12345.999,'#,##0.00'); 
 * formatNumber(12345.999,'#,##0.##'); 
 * formatNumber(123,'000000'); 
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
	/*var r1, r2, s1, s2;
	try {
		r1 = parseInt(arg1.toString().split(".")[0]);
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = parseInt(arg2.toString().split(".")[0]);
	} catch (e) {
		r2 = 0;
	}
	try {
		s1 = parseInt(arg1.toString().split(".")[1]);
	} catch (e) {
		s1 = 0;
	}
	try {
		s2 = parseInt(arg2.toString().split(".")[1]);
	} catch (e) {
		s2 = 0;
	}
	r1 += r2;
	s1 += s2;
	return r1 + "." + s1;*/
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
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
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
		            console.log("name="+e.name +" value="+e.value);
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
			sweetAlert(text || "出错了", "Something went wrong!", "error");
		}

		function swa_suc(text, func) {
			if (!func)
				sweetAlert(text || "操作成功", "OK!", "success");
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