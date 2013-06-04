<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/head.jsp" %>
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
		<p>
			getFood <input type="text" id="txt-get-food" class="input-small" value="" placeholder="foodId"/>
			<input type="button" id="btn_get_food" value="getFood" class="btn"/>
		</p>
		<p>
			getPackage <input type="text" id="txt-get-package" class="input-small" value="" placeholder="packageId"/>
			<input type="button" id="btn_get_package" value="getPackage" class="btn"/>
		</p>
		<hr>
		<p>
			websocket connect to server
			<input type="button" id="btn_ws_conn" value="connect" class="btn"/>
			<input type="button" id="btn_ws_disconn" value="disconnect" class="btn" disabled/>
		</p>
		<p>
			websocket send <input type="text" id="txt_ws_send" class="input" value="" placeholder="send message to server"/>
			<input type="button" id="btn_ws_send" value="send" class="btn"/>
		</p>
		<p>
			websocket invoke push message <input type="text" id="txt_ws_invoke" class="input" value="" placeholder="message will push to client"/>
			<input type="button" id="btn_ws_invoke" value="invoke" class="btn"/>
		</p>
		<hr>
		<p>
			submit order test <input type="text" id="txt_submit_order" class="input-small" value="" placeholder="orderId"/>
			<input type="button" id="btn_submit_order" value="submit" class="btn"/>
		</p>
		<hr>
		<p>
			getPhoneState <input type="text" id="txt_phone_state" class="input" value="" placeholder="phone"/>
			<input type="button" id="btn_phone_state" value="getPhoneState" class="btn"/> <br>
			getPhoneStates <input type="text" id="txt_phone_states" class="input" value="" placeholder="phone1,phone2,..."/>
			<input type="button" id="btn_phone_states" value="getPhoneStates" class="btn"/>
		</p>
		<hr>
		<p>
			test print order<input type="text" id="txt_test_print" class="input" value="" placeholder="orderId"/>
			<input type="button" id="btn_test_print" class="btn" value="testPrintOrder" />
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
			
			
			function errorHandler(xhr) {
				showError("<strong>Error!</strong> " + xhr.responseText);
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

				$("#btn_get_food").click(function() {
					$.getJSON("${webRoot}/shop/food/" + $("#txt-get-food").val(), function(json) {
						showSuccessFix("<strong>Well done!</strong> data: " + JSON.stringify(json));
					})
					.fail(function(xhr) {
						showError("<strong>Error!</strong> " + xhr.responseText);
					});
				});

				$("#btn_get_package").click(function() {
					$.getJSON("${webRoot}/shop/package/" + $("#txt-get-package").val(), function(json) {
						showSuccessFix("<strong>Well done!</strong> data: " + JSON.stringify(json));
					})
					.fail(function(xhr) {
						showError("<strong>Error!</strong> " + xhr.responseText);
					});
				});

				$("#btn_submit_order").click(function() {
					var url = "${webRoot}/test/autoprint/" + $("#txt_submit_order").val();
					$.post( url, function(){
						showSuccess("<strong>Well done!</strong> 订单提交成功" );
					} );
				});
				
				$("#btn_phone_state").click(function() {
					var url = "${webRoot}/shop/phonestate?p=" + $("#txt_phone_state").val();
					$.get(url, showSuccess).fail(errorHandler);
				});
				
				$("#btn_phone_states").click(function() {
					var url = "${webRoot}/shop/phonestates"
					//?p=" + $("#txt_phone_state").val();
					
					$.post(url, {
						phones: $("#txt_phone_states").val()
					}, showSuccess, "text").fail(errorHandler);
				});
				
				$("#btn_test_print").click(function() {
					var url = "${webRoot}/shop/testprint/"+$("#txt_test_print").val();
					$.get(url, showSuccess).fail(errorHandler);
				});


				initWebsocket();
			});
			
			
			// ------------ websocket -------------

			var ws = null;

			function ws_connect() {
				var url = "ws://" + window.location.host + "${webRoot}/websocket/order";
				ws = new WebSocket(url);
				ws.onopen = function() {
					showSuccess("Info: WebSocket connection opened.");
					setConnected(true);
				}
				ws.onmessage = function(event) {
					showSuccess("<strong>Receive Message!</strong> data: " + event.data);
				}
				ws.onclose = function() {
					showSuccess("Info: WebSocket connection closed.");
					setConnected(false);
				}
			}

			function setConnected (connected) {
				$("#btn_ws_conn").attr("disabled", connected);
				$("#btn_ws_disconn").attr("disabled", !connected);
			}

			function ws_disconnect() {
				if(ws != null) {
					ws.close();
					ws = null;
				}
				setConnected(false);
			}

			function initWebsocket() {
				$("#btn_ws_conn").click(ws_connect);
				$("#btn_ws_disconn").click(ws_disconnect);
				$("#btn_ws_send").click(function() {
					if(ws) ws.send($("#txt_ws_send").val());
				});
				$("#btn_ws_invoke").click(function() {
					var url = "${webRoot}/shop/websocket?m="+$("#txt_ws_invoke").val();
					$.getJSON(url, function(data) {
						// do nothing
					})
					.fail(function(xhr) {
						showError("<strong>Error!</strong> " + xhr.responseText);
					});
				});
			}

			
		</script>
	</body>
</html>