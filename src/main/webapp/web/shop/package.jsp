<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>餐点管理-配餐模式</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
		<style type="text/css">
			body {
				/*padding: 50px 0px;*/
				/*background-color: #f5f5f5;*/
			}
		</style>
	</head>
	<body>
		<div class="row-fluid container-fixed">
			<div class="span12">
				<div class="querybar">
					<div class="row-fluid">
						<div class="span6">
						</div>
						<div class="span3">
						</div>
						<div class="span3 btn-right">
							<a class="btn" href="#dialog-add-package" data-toggle="modal"><i class="icon-plus"></i>添加套餐</a>
							<button class="btn" id="btn_edit" data-toggle="button"><i class="icon-pencil"></i>编辑</button>
						</div>
					</div>
				</div>
				<div class="category">
					<ul>
						<c:forEach items="${packageFoodsList}" var="groupFoods" varStatus="status">
						<li>
							<label class="checkbox"><input type="checkbox" idx="${status.count }" <c:if test="${fn:length(groupFoods.foodList) > 0}">checked</c:if>
							>${groupFoods.groupName}</label>
						</li>
						</c:forEach>
					</ul>
				</div>
				<table id="tb_foods" class="foods-area">
					 <c:forEach items="${packageFoodsList}" var="groupFoods" varStatus="status">
					 <tr id="tr_${status.count }" class="<c:if test="${fn:length(groupFoods.foodList) == 0}">hide</c:if>">
						<td class="food-area-head">${groupFoods.groupName}</td>
						<td>
							<ul class="item-list img-text-below food-area-list">
								<c:forEach items="${groupFoods.foodList}" var="food">
								<li id="item_${food.id }">
									<div>
										<img src="${webRoot}/${food.image}" alt="${food.foodName}"><span>${food.foodName}</span>
										<div class="btns">
											<i class="icon-leaf" title="缺货标记"></i>
											<i class="icon-edit" title="编辑食物"></i>
											<i class="icon-remove" title="删除食物"></i>
										</div>
									</div>
								</li>
								</c:forEach>
								<li><img src="${webRoot}/web/img/plus.png" class="food-add" alt=""></li>
							</ul>
						</td>
					 </tr>
					 </c:forEach>
				</table>
			
			</div>
		</div><!--/ end of container -->


		<!-- 添加套餐窗口 -->
		<div id="dialog-add-package" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3>添加套餐</h3>
			</div>
			<div class="modal-body">

				<!-- Step 1 -->
				<form id="packageForm" action="${webRoot}/shop/package" method="post" enctype="multipart/form-data" class="form-horizontal form-dialog">
					<div id="step1">
						<div class="control-group">
							<div class="control-label">
								<div class="addfood-photo-wrapper"><img id="image-preview" src="" class="addfood-photo img-rounded" alt="" data-toggle="tooltip" data-placement="right" data-original-title="图片不能为空" data-validate="imgValid();"></div>
								<div class="img-tips hide">点击上传图片</div>
								<input type="file" id="file-upload" name="file" class="hide">
							</div>
							<div class="controls controls-clear-right">
								<div class="control-group control-group-small">
									<label for="" class="control-label">名称</label>
									<div class="controls"><input type="text" id="foodName" name="foodName" data-toggle="tooltip" data-original-title="食物名称不能为空" data-validate></div>
									<input type="hidden" name="foodId" id="foodId">
								</div>
								<div class="control-group control-group-small">
									<label for="" class="control-label">分类</label>
									<div class="controls">
										<select name="groupId" id="groupId">
											<c:forEach items="${packageFoodsList}" var="groupFoods" varStatus="status">
											<option value="${groupFoods.id}">${groupFoods.groupName }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="control-group control-group-small">
									<label for="" class="control-label">原价</label>
									<div class="controls">
										<!-- <input type="text"> -->
										<div class="input-prepend input-append">
											<span class="add-on">&yen;</span>
											<input id="originPrice" type="text" name="originPrice" style="width:153px;" data-toggle="tooltip" data-original-title="原价非空且为数字" data-validate="not_null postive_float">
											<span class="add-on">元</span>
										</div>
									</div>
								</div>
								<div class="control-group control-group-small">
									<label for="" class="control-label">现价</label>
									<div class="controls">
										<!-- <input type="text"> -->
										<div class="input-prepend input-append">
											<span class="add-on">&yen;</span>
											<input id="currentPrice" name="currentPrice" type="text" style="width:153px;" data-toggle="tooltip" data-original-title="现价非空且为数字" data-validate="not_null postive_float">
											<span class="add-on">元</span>
										</div>
									</div>
								</div>
								<div class="control-group control-group-small">
									<label for="" class="control-label">库存</label>
									<div class="controls"><input type="text" name="stock" id="stock" data-toggle="tooltip" data-original-title="库存须为整数" data-validate="postive_number"></div>
								</div>

							</div>
						</div>
						<div class="control-group control-group-mini control-group-left addfood-describe">
							<label for="" class="control-label">介绍</label>
							<div class="controls">
								<textarea name="detail" id="detail" name="" rows="3" data-toggle="tooltip" data-original-title="描述不能为空" data-validate></textarea>
							</div>
						</div>

						<div class="pull-right">
							<label class="checkbox inline"><input type="checkbox" value="">缺货标记</label>
							<label class="checkbox inline"><input type="checkbox" id="droped" value="">下架</label>
							<input type="hidden" id="_droped" name="droped" value="false">
						</div>

					</div><!--/ end of Step 1 -->

					<!-- step 2 -->
					<table class="foods-area add-package hide" id="step2">
						<c:forEach items="${groupFoodsList}" var="groupFoods" varStatus="status">
						<tr>
							<td class="food-area-head">${groupFoods.groupName }</td>
							<td>
								<ul class="item-list img-text-below food-area-list">
									<c:forEach items="${groupFoods.foodList }" var="food">
									<li>
										<label>
											<img src="${webRoot}/${food.image}" alt="${food.foodName }">
											<span>${food.foodName }</span>
											<input type="checkbox" value="" class="food-select" foodId="${food.id }"/>
										</label>
									</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
						</c:forEach>
					</table><!--/ end of Step 2 -->
					
					<input type="hidden" name="itemIds" id="itemIds" />
				</form>

			</div>
			<div class="modal-footer">

				<div class="control-group control-group-small control-group-left add-package hide" id="modal-footer-food-select">
					<label for="" class="control-label">已添加</label>
					<div id="food-to-add" class="controls food-to-add">
						<ul id="selected-foods" class="item-list img-text-below food-area-list">
							<%-- <li id="selected_item_31"><img src="${webRoot}/web/img/mifan.jpg" alt="mifan"></li> --%>
						</ul>
					</div>
				</div>

				<a href="#" class="btn hide" id="btn_prev" aria-hidden="true">上一步</a>
				<a href="#" class="btn btn-primary next" id="btn_done">下一步</a>
			</div>
		</div><!--/ end of 添加食物 -->

		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.package"></script>
		<script type="text/javascript">
			function imgValid() {
				return !!$("#file-upload").get(0).files[0];
			}
		</script> 
	</body>
</html>