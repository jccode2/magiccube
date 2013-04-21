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
				<c:forEach items="${addressList}" var="address">
					<div class="address-item">
						<a><img src="/1000funs/${address.image }"
							class="img-circle"></a>
						<h5>${address.regionName }</h5>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span8 food-panel">
				<ul class="food-tab">
					<li class="self">随意搭配</li><!--  --><li class="classic">经典套餐</li>
				</ul>
				<div class="food-list">
					<div class="class-list">
						<div class="class-icon"><img src="/1000funs/web/order/css/images/10yuan.png" ></div>
						<div class="class-food">
							<div class="food-item">
								<img src="/1000funs/web/img/paigu.jpg" class="food-pic">
								<p class="food-name">红烧茄子
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/paigu.jpg" class="food-pic">
								<p class="food-name">红烧茄子
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/paigu.jpg" class="food-pic">
								<p class="food-name">红烧茄子
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/paigu.jpg" class="food-pic">
								<p class="food-name">红烧茄子
							</div>
						</div>
					</div>
					
					
					<div class="class-list">
						<div class="class-icon"><img src="/1000funs/web/order/css/images/8yuan.png" ></div>
						<div class="class-food">
							<div class="food-item">
								<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
								<p class="food-name">苦瓜炒蛋
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
								<p class="food-name">苦瓜炒蛋
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
								<p class="food-name">苦瓜炒蛋
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/kuguachaodang.jpg" class="food-pic">
								<p class="food-name">苦瓜炒蛋
							</div>
						</div>
					</div>
					
					<div class="class-list">
						<div class="class-icon"><img src="/1000funs/web/order/css/images/6yuan.png" ></div>
						<div class="class-food">
							<div class="food-item">
								<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
								<p class="food-name">菜心
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
								<p class="food-name">菜心
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
								<p class="food-name">菜心
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/chaixin.jpg" class="food-pic">
								<p class="food-name">菜心
							</div>
						</div>
					</div>
					
					<div class="class-list">
						<div class="class-icon"><img src="/1000funs/web/order/css/images/4yuan.png" ></div>
						<div class="class-food">
							<div class="food-item">
								<img src="/1000funs/web/img/mifan.jpg" class="food-pic">
								<p class="food-name">米饭
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/mifan.jpg" class="food-pic">
								<p class="food-name">米饭
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/mifan.jpg" class="food-pic">
								<p class="food-name">米饭
							</div>
							<div class="food-item">
								<img src="/1000funs/web/img/mifan.jpg" class="food-pic">
								<p class="food-name">米饭
							</div>
						</div>
					</div>
					
				</div>
				
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
