<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>出错啦</title>
<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
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
			<h3>亲，可能地址出了点错误，您访问的页面不存在哦! </h3><a href="${webRoot}" class="btn btn-primary btn-large" ><i class="icon-home icon-white"></i>回到首页</a>
	</div>
</body>
</html>