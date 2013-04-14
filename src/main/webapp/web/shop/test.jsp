<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>test</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap-responsive.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
	</head>
	<body>
	    <div class="alert hide" id="msg">
		    <div >
		    	<a class="close" href="#">&times;</a>
		    	<div id="msg-content">
		    		<strong>Warning!</strong> Best check yo self, you're not looking too good.
		    	</div>
		    </div>
	    </div>
	    
		<p>queryTest: <input type="button" value="queryTest" class="btn" id="queryTest"/> </p>
		<p>
			update order status. id:<input type="text" id="order_id" class="input-small" value=""/> 
			status:<input type="text" id="order_status" class="input-small" value=""/> 
			<button id="update_order_status" value="" class="btn">updateOrderStatus</button>
		</p>
		<p>
			getConfig. <input type="text" id="txt_config" class="input-small" placeholder="key"/>
			<input type="button" id="btn_get_config" value="getConfig" class="btn"/>
		</p>
		<p>
			getAllFoods. <input type="button" id="btn_get_foods" value="getAllFoods" class="btn"/>
		</p>
		
		<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">

			function showSuccess(html) {
				showMsg(html, "alert-success");
			}
	
			function showError(html) {
				showMsg(html, "alert-error");
			}
	
			function showMsg(html, className) {
				$("#msg-content").html(html);
				$("#msg").addClass(className).removeClass("hide")
					.fadeIn(400)
					.delay(2000)
					.fadeOut(800, function() {
						$(this).removeClass(className).addClass("hide");
					});
			}
			
			function showMsgFixed(html, className) {
				$("#msg-content").html(html);
				$("#msg").addClass(className).removeClass("hide").fadeIn(400);
			}
			
			function showSuccessFix(html) {
				showMsgFixed(html, "alert-success");
			}
	
			function showErrorFix(html) {
				showMsgFixed(html, "alert-error");
			}
			
			jQuery(function($) {
				
				$("#msg .close").click(function() {
					$("#msg").fadeOut(400, function() {
						$(this).removeClass("alert-success alert-error").addClass("hide");
					});
				});

				$("#queryTest").click(function() {
					var url = "${webRoot}/shop/test";
					$.getJSON(url, function(json) {
						showSuccess("<strong>Well done!</strong> data: " + JSON.stringify(json));
					})
					.fail(function(xhr) {
						showError("<strong>Error!</strong> " + xhr.responseText);
					});
				});
				
				$("#update_order_status").click(function() {
					var id = $("#order_id").val(), status = $("#order_status").val();
					$.ajax({
						type: "put", 
						url: "${webRoot}/shop/order/"+id+"?status="+status
					})
					.done(function(data) { showSuccess("Success! data: " + data); })
					.fail(function(xhr) { showError("Fail! error: " + xhr.responseText); });
				});
				
				$("#btn_get_config").click(function() {
					$.get("${webRoot}/config/test?key=" + $("#txt_config").val(), function(data) {
						showSuccessFix("<strong>Well done!</strong> data: " + JSON.stringify(data));
					})
					.fail(function(xhr) {
						showErrorFix("<strong>Error!</strong> " + xhr.responseText);
					});
				});

				$("#btn_get_foods").click(function() {
					$.getJSON("${webRoot}/shop/food", function(json) {
						showSuccess("<strong>Well done!</strong> data: " + JSON.stringify(json));
					})
					.fail(function(xhr) {
						showError("<strong>Error!</strong> " + xhr.responseText);
					});
				});
			});

		</script>
	</body>
</html>