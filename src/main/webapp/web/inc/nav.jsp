<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/web/inc/head.jsp"%>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="${webRoot}/web/bubbletip/js/jQuery.bubbletip-1.0.6.js"></script>

<link href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip.css"
	rel="stylesheet" type="text/css" />
<!--[if IE]>
	<link href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip-IE.css" rel="stylesheet" type="text/css" />
	<![endif]-->
<style type="text/css">
.user-center-link {
	background: url("${webRoot}/web/img/user-center-icon.png") 0 7px
		no-repeat;
}

.user-center-link em {
	font-style: normal;
	margin-left: 3px;
}

.adress-info {
	display: block;
	float: left;
	margin-left: 150px;
	padding: 10px 20px 10px;
	cursor: pointer;
}

.adress-info:hover {
	color: white;
}

.regist-tips {
	text-align: center;
	font-style: inherit;
	color: gray;
}

.regist-button {
	text-align: center;
}

.regist-checkbox {
	text-align: center;
}

.regist-hr {
	-webkit-margin-before: 0.4em;
	-webkit-margin-after: 0.3em;
}
</style>

<script type="text/javascript">
	$(window).bind('load', function() {
		$('#registlink').bubbletip($('#registboard'), {
			deltaDirection : 'down',
			deltaPosition : 50,
			offsetTop : 0
		});
		$('#loginlink').bubbletip($('#loginboard'), {
			deltaDirection : 'down',
			deltaPosition : 50,
			offsetTop : 0
		});
	});
</script>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="${webRoot}/order" class="brand">1000funs.com
					&nbsp;&nbsp;千方百味</a> <span class="adress-info">当前送餐地址：</span>
				<ul class="nav nav-pills pull-right">
					<li><a href="#" class="user-center-link"><em>个人中心</em></a></li>
					<li><a id="loginlink" href="#">登录</a></li>
					<li><a id="registlink" href="#">注册</a></li>
				</ul>
				
				<div id="loginboard" style="display: none;">
					<form class="navbar-form pull-left">
						<input id="registerUsername" type="text" class="span3 " placeholder="请输入用户名" /><br />
						<input id="registerPassword" type="password" class="span3" placeholder="请输入密码"/><br />
						<hr class="regist-hr" />
						<label class="checkbox"><input id="isAgree"
							class="checkbox" type="checkbox" /> 十天内自动登录
						</label>
						<hr class="regist-hr" />
						<div class="regist-button">
							<button type="submit" class="btn btn-primary btn-block">登录</button>
						</div>
					</form>
				</div>
				
				<div id="registboard" style="display: none;">
					<div class="regist-tips"><p class="text-success">免费注册，享受个性化订餐服务</p></div>
					<hr class="regist-hr" />
					<form class="navbar-form pull-left">
						<input id="registerUsername" type="text" class="span3 " placeholder="请输入用户名" /><br />
						<input id="registerPassword" type="password" class="span3" placeholder="请输入密码"/><br />
						<input id="registerConfirm" type="password" class="span3" placeholder="请确认密码"/><br />
						<hr class="regist-hr" />
						<label class="checkbox"><input id="isAgree"
							class="checkbox" type="checkbox" /> 我同意<a href="#">使用条款和协议</a>
						</label>
						<hr class="regist-hr" />
						<div class="regist-button">
							<button type="submit" class="btn btn-success btn-block">完成注册</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
