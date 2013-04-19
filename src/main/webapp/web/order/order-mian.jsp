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
					<a><img src="/1000funs/web/client/img/region.jpg"
						class="img-circle"></a>
					<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
					<a><img src="/1000funs/web/client/img/region.jpg"
						class="img-circle"></a>
					<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
					<a><img src="/1000funs/web/client/img/region.jpg"
						class="img-circle"></a>
					<h5>香丽大厦</h5>
				</div>
				<div class="address-item">
					<a><img src="/1000funs/web/client/img/region.jpg"
						class="img-circle"></a>
					<h5>香丽大厦</h5>
				</div>

			</div>
		</div>
		<div class="row-fluid">
			<div class="span8 food-panel">
				<ul class="food-tab">
					<li class="self">随意搭配</li><!--  --><li class="classic">经典套餐</li>
				</ul>
				<table class="food-list">
					<tr>
						<td width="15%">10元区</td>
						<td><img src="/1000funs/web/img/paigu.jpg" class="food-pic">
							<img src="/1000funs/web/img/paigu.jpg" class="food-pic"> <img
							src="/1000funs/web/img/paigu.jpg" class="food-pic"> <img
							src="/1000funs/web/img/paigu.jpg" class="food-pic"> </td>
					</tr>
					<tr>
						<td width="15%">8元区</td>
						<td><img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
						</td>
					</tr>
					<tr>
						<td width="15%">6元区</td>
						<td><img src="/1000funs/web/img/kuguachaodang.jpg"
							class="food-pic"> <img
							src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
							<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
							<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
						</td>
					</tr>
					<tr>
						<td width="15%">4元区</td>
						<td><img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
							<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
						</td>
					</tr>
				</table>
			</div>
			<div class="span4 plate-panel">
				<div class="my-plate">我的餐盒</div>
				<div class="plate-list">
					<div class="plate-item">餐盒1</div>
					<div class="plate-item">餐盒2</div>
				</div>
				<div class="add-plate">为更多人点餐</div>
				<div class="submit-order">提交订单</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/web/inc/foot.jsp"%>

</html>
