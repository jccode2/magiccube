<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/web/inc/head.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>配置管理</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
		<style type="text/css">
body {
	/*padding: 50px 0px;*/
	/*background-color: #f5f5f5;*/
	
}

.time-input {
	border: 1px solid #ddd;
	background: #f7f7f7;
	font: 12px tahoma, arial;
	height: 21px;
	width: 150px;
	padding: 0 3px;
}
</style>
	</head>
	<body>
		<h3>设置营业时间</h3>
		<div>
			<p>
			开门时间：<input id="open-time" name="open-time" class="time-input" value="${openTime}" >&nbsp;(格式：08:00)</p>
			
			<p>
			打烊时间：<input id="closing-time"  name="closing-time" class="time-input" value="${closingTime}" >&nbsp;(格式：20:00)</p>
		
			<p><span>星期天不营业：</span><input type="checkbox" value="1" name="sunday-close" id="sunday-close" style="vertical-align: text-bottom;"
			 <c:if test="${sundayClose}" >checked="checked"</c:if> /></p>
			<button type="button" class="btn" id="save_business_time" data-toggle="button">保存</button>
			
		</div>
		
	<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="${webRoot}/web/js/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
	<script type="text/javascript" src="${webRoot}/dwr/interface/ShopConfigAction.js?20130729"></script>
	<script>
	$(document).ready(function(){
		//$('#ot-img').calendar({ id:'#open-time', format:'HH:mm' });
		//$('#ct-img').calendar({ id:'#closing-time', format:'HH:mm'});
		
		$('#save_business_time').click(function(){
			var config = {
				openTime: $('#open-time').val(),
				closingTime: $('#closing-time').val(),
				sundayClose: $('#sunday-close').val()
			};
			
			ShopConfigAction.setBusTime(config, function(msg){
				alert(msg);
			})
			
		});
		
	});
	
	</script>
	
	</body>
</html>
