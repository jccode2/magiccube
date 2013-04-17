<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>选择食物</title>
<%@ include file="/web/inc/head.jsp"%>
<link rel="stylesheet" href="${webRoot}/web/order/css/order.css">

<style type="text/css">
</style>
</head>
<body>
	<%@ include file="/web/inc/nav.jsp"%>
	<div class="container order-content">
		<div class="container order-address">
			<div class=" container address-tip">请选择你的地址</div>
			<div class="container address-list">
				<div class="address-item">
				<a><img src="/1000funs/web/client/img/region.jpg" class="img-circle"></a>
				<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
				<a><img src="/1000funs/web/client/img/region.jpg"  class="img-circle"></a>
				<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
				<a><img src="/1000funs/web/client/img/region.jpg" class="img-circle"></a>
				<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
				<a><img src="/1000funs/web/client/img/region.jpg"  class="img-circle"></a>
				<h5>香丽大厦</h5>
				</div>
				
			</div>
		</div>
			<div class="row-fluid">
				<div class="span8 food-panel">
					<div class="food-tab"></div>
					<table class="food-list">
						<tr><td width="20%">10元区</td><td></td></tr>
						<tr><td width="20%">8元区</td><td></td></tr>
						<tr><td width="20%">6元区</td><td></td></tr>
						<tr><td width="20%">4元区</td><td></td></tr>
					</table>
				</div>
				<div class="span4 my-meal">
					<h2>hhhhhhh</h2>
				</div>
			</div>
	</div>
</body>
<%@ include file="/web/inc/foot.jsp"%>

</html>
