<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.user-center-link {
		background: url("./web/img/user-center-icon.png") 0 7px no-repeat;
	}
	.user-center-link em {
		font-style: normal;
		margin-left: 3px;
	}
	.adress-info {
		display: block;
		float: left;
		margin-left: 150px;
		padding:10px 20px 10px;
		cursor: pointer;
	}
	.adress-info:hover {
		color: white;
	}
</style>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a href="${webRoot}/order" class="brand">1000funs.com &nbsp;&nbsp;千方百味</a>
			<span class="adress-info">地址信息：</span>
			<ul class="nav nav-pills pull-right">
			  <li><a href="#" class="user-center-link"><em>个人中心</em></a></li>
			  <li><a href="#">登录</a></li>
			  <li><a href="#">注册</a></li>
			</ul>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
