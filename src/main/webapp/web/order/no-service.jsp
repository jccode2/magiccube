<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/web/inc/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>出错啦</title>

<%@ include file="/web/inc/nav.jsp"%>

<link rel="stylesheet"
	href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${webRoot}/web/order/css/order.css" />

<style type="text/css">
.hero-unit.translucent {
	background-color: rgba(0, 0, 0, 0.3);
	color: white;
	margin-top: 15%;
	text-align: center;
}
</style>

</head>
<body>
	<div class="hero-unit translucent">
		<h3>亲， 现在是非营业时间，我们的营业时间是
		<c:if test="${sundayClose }">周一至周六</c:if>
		<c:if test="${!sundayClose }">每天</c:if>
		${openTime}-${openTime}，只能在该时间范围内订餐哦</h3>
	</div>
	<%@ include file="/web/inc/foot.jsp"%>
	<script type="text/javascript" src="${webRoot}/web/js/jquery.cookie.js"></script>
</body>
</html>