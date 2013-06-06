<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/web/inc/head.jsp"%>
<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${webRoot}/web/js/jquery.md5.js"></script>
<script type="text/javascript" src="${webRoot}/web/js/dust-full-1.2.5.min.js"></script>
<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${webRoot}/web/bubbletip/js/jQuery.bubbletip-1.0.6.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/LoginAction.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/UserAction.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/OrderAction.js"></script>
<script type="text/javascript" src="${webRoot}/web/js/order-tablelist.js"></script>
<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip.css" />
<!--[if IE]>
	<link href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip-IE.css" rel="stylesheet" type="text/css" />
	<![endif]-->
<link rel="stylesheet" href="${webRoot}/web/inc/nav.css" />
<link rel="stylesheet" href="${webRoot}/web/css/order.css"/>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="${webRoot}/order" class="brand">1000funs.com &nbsp;&nbsp;千方百味</a> <span class="adress-info">当前送餐地址：</span>
				<ul class="nav nav-pills pull-right">
					<li id="usercenterlink" style="display: none;"><a href="#" id="usercenterbutton" class="user-center-link"><em><span id="usercenterlabel">个人中心</span></em></a></li>
					<li><a id="loginlink" href="#">登录</a></li>
					<li><a id="registlink" href="#">注册</a></li>
				</ul>

				<div id="loginboard" style="display: none;">
					<input id="loginUsername" type="text" maxlength=14 class="span3 " placeholder="请输入用户名" /><br /> <input id="loginPassword" type="password" class="span3" placeholder="请输入密码" maxlength=14/><br />
					<hr class="regist-hr" />
					<label class="checkbox"><input id="remember10day" class="checkbox" type="checkbox" checked /> 十天内自动登录 </label>
					<hr class="regist-hr" />
					<div class="regist-button">
						<button id="loginbutton" class="btn btn-primary btn-block">登录</button>
					</div>
				</div>

				<div id="registboard" style="display: none;">
					<div id="regist1">
						<div class="regist-tips">
							<p class="text-success">免费注册，享受个性化订餐服务</p>
						</div>
						<hr class="regist-hr" />
						<input id="registerUsername" type="text" class="span3 " placeholder="请输入用户名" maxlength=14/><br /> <input id="registerPassword" type="password" class="span3" placeholder="请输入密码" maxlength=14/><br /> <input
							id="registerConfirm" type="password" class="span3" placeholder="请确认密码" maxlength=14/><br />
						<hr class="regist-hr" />
						<label class="checkbox"><input id="isAgree" class="checkbox" type="checkbox" /> 我同意<a id="policy" href="#">使用条款和协议</a> </label>
						<hr class="regist-hr" />
						<div class="regist-button">
							<button id="submitregist" class="btn btn-success btn-block">完成注册</button>
						</div>
					</div>
					<div id="regist2" style="display: none;">
						<div class="regist-tips">
							<p class="text-success">恭喜您，注册成功！</p>
						</div>
						<hr class="regist-hr" />
						<button id="loginafterregist" class="btn btn-success btn-block">立即登录</button>
					</div>
				</div>

				<div id="usercenter" style="display: none;">
					<div id="userscorediv" class="usercenter-label">
						<span id="userscore" class="text-warning">积分:0</span>
					</div>
					<hr class="usercenter-hr" />
					<div class="usercenter-label">
						<p class="text-success">
							<a id="viewRecentOrder" href="#">查看我的订单</a>
						</p>
					</div>
					<hr class="usercenter-hr" />
					<div class="usercenter-label">
						<p class="text-success">
							<a id="suggestbutton" href="#">提出宝贵的意见或建议</a>
						</p>
					</div>
					<hr class="usercenter-hr" />
					<div id="userbutton3" class="usercenter-label">
						<p class="muted">提前预订</p>
					</div>
					<hr class="usercenter-hr"></hr>
					<div id="userbutton4" class="usercenter-label">
						<p class="muted">积分换礼</p>
					</div>
					<hr class="usercenter-hr" />
					<div class="regist-button">
						<button id="logoutbutton" class="btn btn-block">注销</button>
					</div>
				</div>
				<div class="modal hide" id="suggest-frame">
					<div class="modal-header">
						<button id="suggestclose" type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>非常感谢您的支持，请填写您的建议</h4>
					</div>
					<div class="modal-body">
						<textarea id="suggest-content" rows="4" maxlength=250></textarea>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-success" id="success-tip-btn">提交建议</a>
						<p id="suggest-result" class="text-success hide">
							非常感谢您的建议！<a id="suggestclose2" href="#">关闭建议窗口</a>
						</p>
					</div>
				</div>
				
				<div class="modal hide" id="recent-order-frame">
					<div class="modal-header">
						<button id="recent-order-close" type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>我的订单(只显示最近的5条)</h4>
					</div>
					<div class="modal-body" style="min-height:300px;overflow-y:auto;">
						Hey~ guys :) data loading....
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-success" id="recent-order-close-btn">关闭</a>
					</div>
				</div>
				
				<script type="text/template" id="recentOrderTemplate">
				<div class="common-list table-list">
					{?orderViewList}
					<ul>
						{#orderViewList}
						<li>
							<div id="table_item_{id}" class="list-item table-item ">
								<div class="row-fluid head">
									<div class="pull-left link left-panel">
										<span class=""> {address} </span>
										<span class="forestgreen"> {phone} </span>
                                        {?orderStatus}
                                        <span>已出单</span>
                                        {:else}
                                        <span>处理中</span>
                                        {/orderStatus}
									</div>
									<div class="pull-right">
										<span class="brown">{actuallyPrice}元 </span>
									</div>
								</div>
								<div class="row-fluid body">
									<div>
										<span class="order-number">订单号: {id}</span>
										<span class="order-time">
											下单时间: {createTime}
										</span>
                                        <span> 预计送达时间:{exceptTime}</span>
									</div>
									<div class="food-collapse">
										<p class="food-list">
										{#plateList}
											<!-- <span>餐盘{no}:</span> -->
											{#foodList}
												<span>{food} {@if cond="{amount} > 1"}x{amount}{/if}</span>
											{/foodList}
										{/plateList}
										</p>
									</div>
									<div class="food-expand">
										<ul class="plate-list">
										{#plateList}
											<li>
												<ul class="food-list">
													{#foodList}
													<li>
														{food}{@if cond="{amount} > 1"}x{amount}{/if}
													</li>
													{/foodList}
													<li><span class="price">￥{price}</span></li>
												</ul>
											</li>
										{/plateList}
										</ul>
									</div>
								</div>
							</div>
						</li>
						{/orderViewList}
					{:else}
						<div class="list-item table-item center">
							本列表暂无记录
						</div>
					</ul>
					{/orderViewList}
				</div>
				</script>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${webRoot}/web/inc/nav.js"></script>
</body>
</html>
