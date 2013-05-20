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
					<li class="self selected"></li>
					<!--  -->
					<li class="classic"></li>
				</ul>
				<div class="food-list free">
					<c:forEach items="${groupFoods}" var="group">
						<div class="class-list">
							<div class="class-icon">
								<img src="${webRoot}/${group.image}">
							</div>
							<div class="class-food">
								<c:forEach items="${group.foodList}" var="food">
									<div class="food-item" title="${food.foodName }"
										data-group="${group.id}" data-foodname="${food.foodName }"
										data-foodid="${food.id }" data-price="${food.currentPrice }">
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
							<div class="class-food"
								<c:if test="${group.packageList.size()>4}" >style="min-height:368px;"</c:if>>
								<c:forEach items="${group.packageList}" var="pkg">
									<div class="food-item" title="${pkg.foodName }"
										data-group="${group.id}" data-foodname="${pkg.foodName }"
										data-foodid="${pkg.id }" data-price="${pkg.currentPrice }">
										<img src="${webRoot}/${pkg.image }" class="food-pic">
										<div class="package-food-list">
											<div class="package-content">
												<c:forEach items="${pkg.items }" var="item">
													<p class="food-name">${item.foodName }</p>
												</c:forEach>
											</div>
										</div>
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
					<div class="plate-item first curr">
						<h2 class="plate-item-title">
							餐盒1<em class="plate-price"></em>
						</h2>
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


	<div class="modal hide order">
		<div class="modal-header order-title">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
		</div>
		<div class="modal-body order-plate-list">
			<div class="plate-list-detail"></div>
			<div class="order-tip">
				<span class="order-price-total" >合计<em id="order-price-total">45</em>元</span><span
					class="order-discount">首次订餐优惠3元</span><span class="order-reality">实际需付<em
					id="order-reality-price">42</em>元
				</span>
			</div>
			<table class="order-info">
				<tr>
					<td class="info-title">联系电话</td>
					<td class="info-content"><input type="text" name="phone" id="phone"
						value="13502840913" style="width: 200px;" /><span
						style="margin-left: 20px; color: #469E0A">期望送达时间：</span>
						<a class="lunch-time"></a>
						<a class="dinner-time"></a>
						<a class="thirty-time"></a>
						<a class="hour-time"></a>
					</td>
				</tr>
				<tr>
					<td class="info-title">详细地址</td>
					<td class="info-content"><input type="text" value="香丽大厦"  name="address" id="address" /></td>
				</tr>
				<tr>
					<td class="info-title">备注</td>
					<td class="info-content"><input type="text" value="景发大厦" name="remark" id="remark" /></td>
				</tr>
			</table>
		</div>
		<div class="modal-footer order-button">
			<a href="#" class="submit-btn"></a> <a href="#" class="cancle-btn"></a>
		</div>
	</div>



</body>
<%@ include file="/web/inc/foot.jsp"%>

<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/OrderAction.js"></script>
<script type="text/javascript" src="${webRoot}/web/order/js/order.js"></script>

</html>
