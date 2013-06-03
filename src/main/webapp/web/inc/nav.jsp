<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/web/inc/head.jsp"%>
<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${webRoot}/web/js/jquery.md5.js"></script>
<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${webRoot}/web/bubbletip/js/jQuery.bubbletip-1.0.6.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/LoginAction.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/UserAction.js"></script>
<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip.css" />
<!--[if IE]>
	<link href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip-IE.css" rel="stylesheet" type="text/css" />
	<![endif]-->
<link rel="stylesheet" href="${webRoot}/web/inc/nav.css" />
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
					<input id="loginUsername" type="text" class="span3 " placeholder="请输入用户名" /><br /> <input id="loginPassword" type="password" class="span3" placeholder="请输入密码" /><br />
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
						<input id="registerUsername" type="text" class="span3 " placeholder="请输入用户名" /><br /> <input id="registerPassword" type="password" class="span3" placeholder="请输入密码" /><br /> <input
							id="registerConfirm" type="password" class="span3" placeholder="请确认密码" /><br />
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
							<a href="#">查看我的订单</a>
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
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${webRoot}/web/inc/nav.js"></script>
</body>
</html>
