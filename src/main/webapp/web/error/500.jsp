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
			<h4>亲，非常抱歉，服务器出了点问题，我们正在努力修复中!</h4><p>您随时可以通过电话 <i class="icon-user icon-white"></i> 23946364 与客服联系，获取您需要的服务。</p><a href="${webRoot}" class="btn btn-primary btn-large" ><i class="icon-home icon-white"></i>回到首页</a>
	</div>
</body>
</html>