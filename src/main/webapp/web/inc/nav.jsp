<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="brand">1000funs.com</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="">首页</a></li>
						<li><a href="#" onclick="showShop();">关于我们</a></li>
						<li><a href="#" onclick="showMenu();">查看今天菜单</a></li>
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="用户名"> <input class="span2" type="password" placeholder="密码">
						<button type="button" class="btn" onclick="userLogin();">登录</button>
						<button type="button" class="btn" onclick="userRegist();">注册</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>