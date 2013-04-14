<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>提交订单</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/client/css/client.css">
		<link rel="stylesheet" href="${webRoot}/web/client/css/clientNew.css">
		<script type="text/javascript" src="${webRoot}/web/js/1000funs.js"></script>
		<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/OrderAction.js"></script>
		<style type="text/css">
			body {
				padding: 50px 0px 0px;
			}
		</style>
		
		<script type="text/javascript">
		var order = window.opener.order;
		$(function(){
			initPlate();
		});

		//初始化餐盘
		function initPlate(){
			for(var i=0;i<order.foodList.length;++i){
				if($('#'+order.foodList[i].plate).length==0){
					createPlate(order.foodList[i].plate);
					updatePlateTotal(order.foodList[i].plate);
				}
				putFoodToPlateBox(order.foodList[i]);
			}
			updateOrderTotal();
		}

		//创建餐盘
		function createPlate(plateId){
			$('#plate-list').append(
				$('<div>').attr('class','plate_cross').attr('id',plateId).append(
					$('<div>').attr('class','plate_title notActive').html('餐盘'+plateId)
				).append(
					$('<div>').attr('class','plate_content')
					.append('<table class="table table-condensed table-hover" style="margin-bottom: 0px;"></table>')
				).append(
					$('<div>').attr('class','plate_bottom')
				)
			);
		}

		//向餐盘添加食物
		function putFoodToPlateBox(food){
			var foodDOM = $('<tr>').append($('<td style="width:50%">').html(food.name))
								.append($('<td>').html(food.price+'元    X'))
								.append($('<td>').append(
												$('<a>').attr('href','#').click(function(){
													$(this).plus();
													putFoodToOrder(food);
												}).html("<i class='icon-plus'></i>")
										).append(
												$('<input>').attr('id',food.id+'_'+food.plate+'amount').attr('class','input_num').attr('type','text').attr('value',1)
										).append(
												$('<a>').attr('href','#').click(function(){
													if($(this).reduce()){
														deleteFoodFromOrder(food);
													}
												}).html("<i class='icon-minus'></i>")
										).append($('<span>').html('份')));
			$('#'+food.plate).children('.plate_content').children().append(foodDOM);
			$('#'+food.id+'_'+food.plate+'amount').val(food.amount);
		}

		//向订单添加食物
		function putFoodToOrder(food){
			food.amount++;
			updateOrderTotal();
			updatePlateTotal(food.plate);
		}

		//从订单中去除一份食物
		function deleteFoodFromOrder(food){
			food.amount--;
			updateOrderTotal();
			updatePlateTotal(food.plate);
		}

		//加一
		jQuery.fn.plus = function(){
			var input = this.next();
			var val = input.val();
			if(val==''){
				val=0;
			}
			input.val(parseInt(val)+1);
		}
		
		//减一
		jQuery.fn.reduce = function(){
			$('#alert-over-max-num').hide();
			var input = this.prev();
			var val = input.val();
			if(val==''||val==1){
				return false;
			}
			input.val(parseInt(val)-1);
			return true;
		}

		//计算并更新订单总价
		function updateOrderTotal(){
			var total=0;
			for(var i=0;i<order.foodList.length;++i){
				total+=order.foodList[i].price*order.foodList[i].amount;
			}
			order.totalPrice = total;
			$('#total').html('合计 '+total+'元');
			$('#factTotal').html(total);
		}

		//计算并更新餐盘总价
		function updatePlateTotal(plateId){
			var total=0;
			for(var i=0;i<order.foodList.length;++i){
				if(order.foodList[i].plate==plateId){
					total+=order.foodList[i].price*order.foodList[i].amount;
				}
			}
			$('#'+plateId).children('.plate_bottom').html('总价   '+total+'元');
		}

		//提交订单
		function submitOrder(){
			order.phone = $('#phone').val();
			order.address = $('#address').val();
			order.userRemark = $('#userRemark').val();
			order.exceptTimeType = $('input:radio:checked').val();
			console.log(order);
			OrderAction.submitOrder(order,function(data){
				if(data.success){
					window.location.href = '${webRoot}/web/client/register.jsp?from=order';
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="container submit-order-main">
			<div class="submit-order-title">
				<div class="row">
					<div class="span12">我的订单</div>
				</div>
			</div>
			<div id="plate-list">
			</div>
			<div style="clear: both;"></div>
			<div class="order-total">
				<div class="row">
					<div id="total" class="span2 total-amount"></div>
					<div class="span5">
						<label class="checkbox preferential">
							<input type="checkbox">首次订餐优惠3元
						</label>
					</div>
					<div class="span5"><div class="pull-right">实际需付 <span id="factTotal" class="actual-payment"></span> 元</div></div>
				</div>
			</div>
			<form action="" class="form-horizontal user-info-form">
				<div class="row">
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="telphone">联系电话</label>
							<div class="controls">
								<input type="text" id="phone">
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="pull-right">期望到达时间</div>
					</div>
					<div class="span4">
						<label for="" class="radio inline"><input type="radio" name="setExceptTimeType" checked="checked" value="0">12:00</label>
						<label for="" class="radio inline"><input type="radio" name="setExceptTimeType" value="1">30分钟内</label>
					</div>
				</div>
				<div class="row">
					<div class="span12">
						<div class="control-group">
							<label for="address" class="control-label">详细地址</label>
							<div class="controls">
								<input type="text" id="address" class="span8">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span12">
						<div class="control-group">
							<label for="remark" class="control-label">备注</label>
							<div class="controls">
								<input type="text" id="userRemark" class="span8">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4"></div>
					<div class="span2">
						<button class="btn btn-large btn-primary" type="button" onclick="submitOrder();">提交订单</button>
					</div>
					<div class="span2">
						<button class="btn btn-large" type="button" onclick="window.close();">继续挑选</button>
					</div>
					<div class="span4"></div>
				</div>
			</form>
		</div>
	</body>
</html>