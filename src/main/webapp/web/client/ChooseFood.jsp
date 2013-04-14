<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>选择食物</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/client/css/clientNew.css">
		<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${webRoot}/web/js/cookie.js"></script>
		<script type="text/javascript" src="${webRoot}/web/client/js/client.js"></script>
		<script type="text/javascript" src="${webRoot}/web/js/1000funs.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/FoodAction.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/PackageAction.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/LoginAction.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/CommonAction.js"></script>
		<style type="text/css">
		
			body {
				font-family: "Microsoft Yahei", SimSun, Tahoma, Helvetica, Arial, sans-serif;
			}
			
		</style>
		<script type="text/javascript">
			var userName = "${environmentInfo.user.userName}";
			var regionId = "${param.regionId}";
			var regionName = "${param.regionName}";
			var addressId = "${param.addressId}";
			var addressName = "${param.addressName}";
			var plateCount=1;//餐盘数量
			//订单
			var order={foodList:[]};

			$(function(){
				$('#personNO').val(plateCount);
				initTopBar();
				initPosition();
				queryFoods();
				queryPackages();
			});

			//初始化当前地址
			function initPosition(){
				if(regionId){
					$('#position').html('您的当前地址:'+decodeURI(getParam(window.location.href,'regionName')));
				}else{
					$('#position').html('您的当前地址:'+decodeURI(getParam(window.location.href,'addressName')));
				}
			}

			//激活单品
			function activateSingle(){
				$('#single-bar').removeClass('notActive');
				$('#single-bar').addClass('active');
				$('#package-bar').removeClass('active');
				$('#package-bar').addClass('notActive');
				$("#package").hide();
				$("#single").show();
			}

			//激活套餐
			function activatePackage(){
				$('#package-bar').removeClass('notActive');
				$('#package-bar').addClass('active');
				$('#single-bar').removeClass('active');
				$('#single-bar').addClass('notActive');
				$("#single").hide();
				$("#package").show();
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

			//添加餐盘
			function plusPlate(){
				if(plateCount==5){
					$('#alert-over-max-num').show();
					return;
				}
				plateCount++;
				createPlate(plateCount);
			}

			//创建餐盘
			function createPlate(plateId){
				$('#plate-list').append(
					$('<div>').attr('class','plate').attr('id',plateId).append(
						$('<div>').attr('class','plate_title notActive').html('餐盘'+plateId).click(function(){
							$(this).activatePlate();
						})
					).append(
						$('<div>').attr('class','plate_content')
						.append('<table class="table table-condensed table-hover" style="margin-bottom: 0px;"></table>')
					).append(
						$('<div>').attr('class','plate_bottom')
					)
				);
			}

			//向餐盘添加食物
			function putFoodToPlateBox(vo,plateId){
				if(getFoodFromOrder(vo.id,plateId)){//餐盘中已经有该食物
					$('#'+vo.id+'_'+plateId+'amount').val(parseInt($('#'+vo.id+'_'+plateId+'amount').val())+1);
					return;
				}
				vo.name = vo.foodName?vo.foodName:vo.packageName;
				var food = $('<tr>').append($('<td style="width:50%">').html(vo.name))
									.append($('<td>').html(vo.currentPrice+'元    X'))
									.append($('<td>').append(
													$('<a>').attr('href','#').click(function(){
														$(this).plus();
														putFoodToOrder(vo,plateId);
													}).html("<i class='icon-plus'></i>")
											).append(
													$('<input>').attr('id',vo.id+'_'+plateId+'amount').attr('class','input_num').attr('type','text').attr('value',1)
											).append(
													$('<a>').attr('href','#').click(function(){
														if($(this).reduce()){
															deleteFoodFromOrder(vo.id,plateId);
														}
													}).html("<i class='icon-minus'></i>")
											).append($('<span>').html('份')));
				$('#'+plateId).children('.plate_content').children().append(food);
			}

			//向订单添加食物
			function putFoodToOrder(vo,plateId){
				var foodVO = getFoodFromOrder(vo.id,plateId);
				if(foodVO==null){
					foodVO = {};
					foodVO.id = vo.id;
					foodVO.itemId = vo.id;
					foodVO.name = vo.name;
					foodVO.price = vo.currentPrice;
					foodVO.plate = plateId;
					foodVO.amount = 1;
					order.foodList.push(foodVO);
				}else{
					foodVO.amount++;
				}
				updateOrderTotal();
				updatePlateTotal(plateId);
			}

			//从订单中去除一份食物
			function deleteFoodFromOrder(foodId,plateId){
				getFoodFromOrder(foodId,plateId).amount--;
				updateOrderTotal();
				updatePlateTotal(plateId);
			}

			//从订单中获取食物
			function getFoodFromOrder(foodId,plateId){
				for(var i=0;i<order.foodList.length;++i){
					if(order.foodList[i].id==foodId&&order.foodList[i].plate==plateId){
						return order.foodList[i];
					}
				}
				return null;
			}

			//删除餐盘
			function reducePlate(){
				if(plateCount==1){
					return;
				}
				$('#plate'+plateCount).remove();
				plateCount--;
			}

			//收起餐盘
			jQuery.fn.fold = function(){
				this.children('.plate_content').hide('fast');
				this.children('.plate_bottom').hide('fast');
			}

			//展开餐盘
			jQuery.fn.unfold = function(){
				this.children('.plate_content').show('fast');
				this.children('.plate_bottom').show('fast');
			}

			//激活一个餐盘，收起其他餐盘
			jQuery.fn.activatePlate = function(){
				$('#plate-area').find('.active').removeClass('active').addClass('notActive');
				this.removeClass('notActive').addClass('active');
				$('.plate').fold();
				this.parent().unfold();
			}

			//查询食物单品
			function queryFoods(){
				FoodAction.queryFoods(function(data){
					for(var i=0;i<data.length;++i){
						createFood(data[i]);
					}
				});
			}

			//查询套餐
			function queryPackages(){
				PackageAction.queryPackages(function(data){
					for(var i=0;i<data.length;++i){
						createFood(data[i]);
					}
				});
			}

			//创建一个食物
			function createFood(vo){
				var name = vo.foodName?vo.foodName:vo.packageName;
				var $area = vo.foodName?$('#single'):$('#package');
				var obj=$('<li>').attr('id','food_'+vo.id).attr('title',name).click(function(){
					var plateId = $('#plate-area').find('.active').parent().attr('id');
					putFoodToPlateBox(vo,plateId);
					putFoodToOrder(vo,plateId);
				}).append(
						$('<img>').attr('src','${webRoot}'+vo.image)
						);
				if($('#ul_'+vo.groupId).length==1){//已存在分区
					$('#ul_'+vo.groupId).append(obj);
				}else{//未存在分区
					$area.append(
							$('<tr>').append(
									$('<td>').attr('class','food-area-head').html(vo.groupName)
									).append(
									$('<td>').append(
											$('<ul>').attr('id','ul_'+vo.groupId).attr('class','food-area-list').append(obj)
											)
									)
							);
				}
			}

			//计算并更新订单总价
			function updateOrderTotal(){
				var total=0;
				for(var i=0;i<order.foodList.length;++i){
					total+=order.foodList[i].price*order.foodList[i].amount;
				}
				order.totalPrice = total;
				$('#total').html('合计   '+total+'元');
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

			//打开订单页面
			function openOrderPage(){
				window.open('${webRoot}/web/client/SubmitOrder.jsp','submitOrder');
			}

			//修改地址
			function changeAddress(){
				deleteCookie('regionId');
				deleteCookie('regionName');
				window.location.href = "${webRoot}/web/client/FirstPage.jsp";
			}
		</script>
	</head>
	<body>
		<div class="navbar navbar-static-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<img title="跳转到首页" src="${webRoot}/web/client/img/logo.png" class="logo" onclick="toPage('${webRoot}/web/client/FirstPage.jsp');">
					<ul class="nav pull-right">
						<li id="shopBar">
							<a href="#">
								<i class="icon-home"></i> 景田店(001)
							</a>
						</li>
						<li id="userBar">
							<a href="#">
							</a>
						</li>
						<li id="loginBar"><a href="#login" data-toggle="modal">登录</a></li>
						<li id="registerBar"><a href="#register" data-toggle="modal">注册</a></li>
						<li id="exitBar"><a href="#" onclick="exit();">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="msg" class="alert hide" style="position: absolute;">
	    	<div id="msg-content">
	    	</div>
		</div>
		<div style="padding-left: 50px; padding-top: 10px; padding-bottom: 10px;">
			<span id="position"></span>
			<a href="#" onclick="changeAddress();">[修改]</a>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
			    <div class="span8" style="border-right: 1px solid rgba(0, 0, 0, 0.15);">
			    	<div class="container-fluid">
						<div class="row-fluid">
						    <div id="single-bar" class="span3 tab active" onclick="activateSingle();">
						    	随意配餐
						    </div>
						    <div id="package-bar" class="span3 tab notActive" onclick="activatePackage();">
						    	经典套餐
						    </div>
						</div>
					</div>
					<div>
						<table id="single" class="foods-area">
						</table>
						<table id="package" class="foods-area" style="display: none;">
						</table>
					</div>
			    </div>
			    <div class="span4">
			    	<div id="plate-area" class="plate-area">
				    	<label>
				    		共
				    		<a href="#" onclick="$(this).plus();plusPlate();"><i class="icon-plus"></i></a>
				    		<input id="personNO" class="input_num" type="text" value="1" style="margin-bottom: 0px;" disabled="disabled">
							<a href="#" onclick="$(this).reduce();reducePlate();"><i class="icon-minus"></i></a>
				    		人用餐
				    		<div id="alert-over-max-num" class="alert alert-error" style="display: none;float: left;position: absolute">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								一个订单最多5人用餐，你可以提交一个新的订单
							</div>
				    	</label>
				    	<div id="plate-list">
					    	<div id=1 class="plate">
					    		<div class="plate_title active" onclick="$(this).activatePlate();">
					    			餐盘1
					    		</div>
					    		<div class="plate_content">
					    			<table class="table table-condensed table-hover" style="margin-bottom: 0px;">
									</table>
					    		</div>
					    		<div class="plate_bottom">
					    		</div>
					    	</div>
					    </div>
				    	<div id="total" class="total">
				    	</div>
						<button class="btn btn-large btn-primary ok" type="button" onclick="openOrderPage();">确定</button>
					</div>
			    </div>
			</div>
		</div>
		
				<div id="register" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">免费注册</h2>
		  </div>
		  <div class="modal-body">
		    <form class="form-sign">
			  <fieldset>
			    <label>用户名</label>
			    <input id="registerUserName" type="text" class="input-block-level">
				<label>密码</label>
			    <input id="registerPassword" type="password" class="input-block-level">
			    <label>确认密码</label>
			    <input id="registerRePassword" type="password" class="input-block-level">
			    <label>电子邮箱</label>
			    <input id="registerEmail" type="email" required class="input-block-level">
			    <label class="checkbox">
      				<input type="checkbox"> 我同意使用<a href="#">条款和协议</a>
    			</label>
			  </fieldset>
			</form>
		  </div>
		  <div class="modal-footer">
		    <button class="btn btn-primary" onclick="register();">提交</button>
		  </div>
		</div>
		
		<div id="login" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">会员登录</h2>
		  </div>
		  <div class="modal-body">
		    <form class="form-sign">
			  <fieldset>
			    <label>用户名/Email/手机号</label>
			    <input id="loginAccount" type="text" class="input-block-level">
				<label>密码</label>
			    <input id="loginPassword" type="password" class="input-block-level">
			    <label class="checkbox">
      				<input type="checkbox"> 10天内免登录
      				<div style="float: right;">
      					<a href="#" onclick="openFindPassword();">忘记密码</a>|<a href="#" onclick="openRegister();">注册账号</a>
      				</div>
    			</label>
			  </fieldset>
			</form>
		  </div>
		  <div class="modal-footer">
		    <button class="btn btn-primary" onclick="login();">登录</button>
		  </div>
		</div>
	</body>
</html>
