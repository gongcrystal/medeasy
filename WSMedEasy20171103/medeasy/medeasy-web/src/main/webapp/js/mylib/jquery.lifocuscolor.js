;(function($){
	$.fn.extend({
		"focusColor": function(li_col){
			var def_col = "#a01";
			var lst_col = "#fff";
			li_col = (li_col== undefined)? def_col: li_col;
			$(this).find("li").each(function(){
				$(this).mouseover(function(){
					$(this).css("background-color", li_col);
				}).mouseout(function(){
					$(this).css("background-color","#fff");
				})
			});
			return $(this);
		}
	});
	
})(jQuery);