<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>查看订单情况</title>
<script src="${ctx}/web/chart/Chart.js"></script>
<script src="${ctx}/web/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/web/bootstrap/css/bootstrap.min.css" />
<style type="text/css">
.chart {
	display: block;
}

html,body {
	width: 100%;
	height: 100%;
}
</style>

<script type="text/javascript">
	/* <![CDATA[ */
	$(document).ready(function() {

		drawStuff();

		function drawStuff() {
			var labels_ = "${labels}";
			labels_ = labels_.substring(1, labels_.length - 1);
			labels_ = labels_.split(',');

			var counts_ = "${counts}";
			counts_ = eval(counts_);
			var data_counts = {
				labels : labels_,
				datasets : [ {
					fillColor : "rgba(255,152,69,0.7)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(59,218,100,1)",
					pointStrokeColor : "#fff",
					data : counts_
				} ]
			}
			//Get context with jQuery - using jQuery's .get() method.
			var ctx_counts = $("#chart_counts").get(0).getContext("2d");
			//This will get the first returned node in the jQuery collection.
			var countsChart = new Chart(ctx_counts);
			countsChart.Line(data_counts);

			var totalPrices = "${totalPrices}";
			totalPrices = eval(totalPrices);
			var data_totalPrices = {
				labels : labels_,
				datasets : [ {
					fillColor : "rgba(255,123,169,0.7)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(59,218,100,1)",
					pointStrokeColor : "#fff",
					scaleLabel : "true",
					data : totalPrices
				} ]
			}
			//Get context with jQuery - using jQuery's .get() method.
			var ctx_totalPrices = $("#chart_totalPrices").get(0).getContext("2d");
			//This will get the first returned node in the jQuery collection.
			var totalPricesChart = new Chart(ctx_totalPrices);
			totalPricesChart.Line(data_totalPrices);
		}
	});

	/* ]]> */
</script>
</head>

<body>

	<div style="margin-top: 50px; margin-left: 70px;">
		<p>
			<i class="icon-th-list icon-large"></i> 订单数量
		</p>
		<canvas id="chart_counts" class="chart" width="1290px" height="200px"></canvas>
	</div>
	<div style="margin-top: 20px; margin-left: 70px;">
		<p>
			<i class="icon-edit icon-large"></i> 订单金额
		</p>
		<canvas id="chart_totalPrices" class="chart" width="1290px" height="200px"></canvas>
	</div>
</body>

</html>
