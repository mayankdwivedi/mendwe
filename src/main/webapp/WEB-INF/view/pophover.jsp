<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<style>

.chart div {
  font: 10px sans-serif;
  text-align: right;
  padding: 3px;
  margin: 1px;
  color: white;
}

</style>

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-tooltip.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>

<script type="text/javascript">
var surveyContent='<div class="msg">BMW began building motorcycle engines and'
	+'then motorcycles after</div><br>'
	+'<div id="q1">'
	+'<button id="#" class="btn btn-small" onclick="firedebate('+'q1'+','+'bar1'+')">'
	+'Support</button>&nbsp;'
	+'<button id="#" class="btn btn-small" onclick="firedebate('+'q1'+','+'bar1'+')">'
	+'Against</button>'
	+'</div>'
	+'<div class="chart" style="display:none;" id="bar1">'
	 +' <div style="width: 40px; background-color:#da4f49">4%</div>'
	  +'<div style="width: 80px;   background-color: steelblue;">8%</div>'
	  +'</div>';
		$(document)
				.ready(
						function() {
							$('#popoverId')
									.popover(
											{   placement : 'bottom',
												html : true,
												title : 'Popover Title <a class="close" href="#");">&times;</a>',
												content :surveyContent ,
											});
							$('#popoverId').click(function(e) {
								e.stopPropagation();
							});
							$(document)
									.click(
											function(e) {
												if (($('.popover')
														.has(e.target).length == 0)
														|| $(e.target).is(
																'.close')) {
													$('#popoverId').popover(
															'hide');
												}
											});
						});
	</script>



<button id="popoverId" class="popoverThis btn btn-large btn-danger" onclick="loadDebateQuestion()">
	Click Me!</button>

<!-- AJAX BUTTON
 --><a href="#" class="btn btn-small" onclick="crunchifyAjax2()"><font style="font-size:10px;">Test</font></a>&nbsp;
	
