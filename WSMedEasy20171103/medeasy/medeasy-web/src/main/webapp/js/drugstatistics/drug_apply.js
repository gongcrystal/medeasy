$(function(){
	  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
	 /* var data = [
			{ label: "social networks",  data: 38.7, color: "#68BC31"},
			{ label: "search engines",  data: 24.5, color: "#2091CF"},
			{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
			{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
			{ label: "other",  data: 10, color: "#FEE074"}
		  ]*/
	  
	  $.ajax({
		    "url" : '/medeasy-web/drug/drugapplystatistic',
			"type" : 'POST',
			"dataType" : 'json',
			"timeout" : 20000,	
			success: function(data){
				  drawPieChart(placeholder, data);				 
				  placeholder.data('chart', data);
				
			}
	  });
	  
	 /* drawPieChart(placeholder, data);*/
	 /* placeholder.data('chart', data);
	  placeholder.data('draw', drawPieChart);*/
	  placeholder.data('draw', drawPieChart);
	  
	//pie chart tooltip example
	  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
	  var previousPoint = null;
	  
	  placeholder.on('plothover', function (event, pos, item) {
			if(item) {
				if (previousPoint != item.seriesIndex) {
					previousPoint = item.seriesIndex;
					var tip = item.series['label'] + " : " + item.series['percent']+'%';
					$tooltip.show().children(0).text(tip);
				}
				$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
			} else {
				$tooltip.hide();
				previousPoint = null;
			}
			
		 });
})

function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.95,
							innerRadius: 0.5,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 1
							},
							startAngle: 2
							 
						}
					},
					tooltip: true,
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }