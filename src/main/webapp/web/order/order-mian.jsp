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
			<div class=" container address-tip">请选择你的地址：</div>
			<div class="container address-list">
				<c:forEach items="${addressList}" var="address">
					<div class="address-item">
						<a><img src="/1000funs/${address.image }" class="img-circle"></a>
						<h5>${address.regionName }</h5>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span8 food-panel">
				<ul class="food-tab">
					<li class="self selected"></li><!--  --><li class="classic"></li>
				</ul>
				<div class="food-list">
					<c:forEach items="${groupFoods}" var="group">
						<div class="class-list">
							<div class="class-icon">
								<img src="${webRoot}/${group.image}">
							</div>
							<div class="class-food">
								<c:forEach items="${group.foodList}" var="food">
									<div class="food-item" title="${food.foodName }"
										data-group="${group.id}" data-foodname="${food.foodName }"
										data-foodid="${food.id }"
										data-price="${food.currentPrice }" >
										<img src="${webRoot}/${food.image }" class="food-pic">
										<p class="food-name">${food.foodName }
									</div>
								</c:forEach>
							</div>
							<br style="clear: both;" />
						</div>
					</c:forEach>

				</div>
				<div class="food-list package">
					<c:forEach items="${groupPackages}" var="group">
						<div class="class-list">
							<div class="class-icon">
								<img src="${webRoot}/${group.image}">
							</div>
							<div class="class-food">
								<c:forEach items="${group.packageList}" var="pkg">
									<div class="food-item" title="${pkg.foodName }" data-pkg = "${pkg}"
										data-group="${group.id}" data-foodname="${pkg.foodName }"
										data-foodid="${pkg.id }"
										data-price="${pkg.currentPrice }" >
										<img src="${webRoot}/${pkg.image }" class="food-pic">
										<p class="food-name">${pkg.foodName }
									</div>
								</c:forEach>
							</div>
							<br style="clear: both;" />
						</div>
					</c:forEach>

				</div>

			</div>
			<div class="span4 plate-panel">
				<div class="my-plate"></div>
				<div class="plate-list">
					<div class="plate-item first curr" >
						<h2 class="plate-item-title">餐盒1<em class="plate-price"></em></h2>
						<ul class="plate-food-list">
						</ul>
					</div>
				</div>
				<div class="price-total">
					<em>￥0</em>
				</div>
				<div class="add-plate"></div>
				<div class="submit-order"></div>
			</div>
		</div>
	</div>

</body>
<%@ include file="/web/inc/foot.jsp"%>
<style>
</style>
<script type="text/javascript" src="${webRoot}/web/order/js/order.js"></script>
</html>
