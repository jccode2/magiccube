<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>千方百味欢迎您</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
<!-- Le styles -->
<link href="${webRoot}/web/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
	background-image: url("${webRoot}/web/img/indexbg.jpg");
	background-repeat: no-repeat;
	background-attachment: inherit;
	background-position: center;
}

.hero-unit.translucent {
	background-color: rgba(0, 0, 0, 0.5);
}

.menudiv {
	background-color: rgba(0, 0, 0, 0.5);
	text-align: center;
	padding: 130px;
	margin-bottom: 130px;
}

.menuhidden {
	padding: 20px;
	margin-bottom: 20px;
}

.menudata {
	color: rgba(0, 0, 0, 0.5);
}

h3 {
	color: white;
}

h2 {
	color: white;
}

p {
	color: #dddddd;
}

.table thead tr {
	background-color: rgba(100, 100, 100, 0.5);
}

.table thead th {
	text-align: center;
	color: #00FF00;
}

.table tbody td {
	text-align: center;
	color: white;
}
</style>
<link href="${webRoot}/web/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="//cdnjs.bootcss.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
    <![endif]-->
<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="http://www.bootcss.com/assets/ico/apple-touch-icon-144-precomposed.png"
>
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="http://www.bootcss.com/assets/ico/apple-touch-icon-114-precomposed.png"
>
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="http://www.bootcss.com/assets/ico/apple-touch-icon-72-precomposed.png"
>
<link rel="apple-touch-icon-precomposed" href="http://www.bootcss.com/assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="http://www.bootcss.com/assets/ico/favicon.png">
<script type="text/javascript">
	var menuhidden = true;
	var menuinited = false;

	function userLogin() {

	}
	function userRegist() {

	}
	function sayGood() {

	}

	function showMenu() {
		if (menuhidden) {
			$('#menudiv').slideDown("normal", showMenuTable);
			if (!menuinited) {
				$('#menudiv').show();
				menuinited = true;
			}
			menuhidden = false;
		} else {
			$('#menudiv').slideUp("normal", hideMenuTable);
			menuhidden = true;
		}
	}
	function showMenuTable() {
		$('#menudiv').addClass("menuhidden");
		$('#menutable').show();
	}
	function hideMenuTable() {
		$('#menudiv').removeClass("menuhidden");
		$('#menutable').hide();
	}
	function initPage() {
		$('#menudiv').hide();
		$('#menutable').hide();
	}
</script>
</head>
<body onload="initPage();">
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
						<li><a href="#about">关于我们</a></li>
						<li><a href="#menu" onclick="showMenu();">查看今天菜单</a></li>
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="用户名"> <input class="span2" type="password" placeholder="密码">
						<button type="submit" class="btn" onclick="userLogin();">登录</button>
						<button type="submit" class="btn" onclick="userRegist();">注册</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div id="menudiv" class="menudiv">
		<%-- <img src="${webRoot}/web/img/ordermenu.jpg" class="img-polaroid"> --%>
		<form action="${webRoot}/tempindex/setmenu">
			<fieldset>
				<table id="menutable" class="table table-bordered">
					<thead>
						<tr>
							<th>主菜价格</th>
							<th colspan="2">主菜</th>
							<th colspan="2">副菜（赠送）</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input name="price1" type="text" placeholder="价格"></td>
							<td>A</td>
							<td><input name="mainfood1" type="text" placeholder="主菜"></td>
							<td>1</td>
							<td><input name="secondfood1" type="text" placeholder="副菜"></td>
						</tr>
						<tr>
							<td><input name="price2" type="text" placeholder="价格"></td>
							<td>B</td>
							<td><input name="mainfood2" type="text" placeholder="主菜"></td>
							<td>2</td>
							<td><input name="secondfood2" type="text" placeholder="副菜"></td>
						</tr>
						<tr>
							<td><input name="price3" type="text" placeholder="价格"></td>
							<td>C</td>
							<td><input name="mainfood3" type="text" placeholder="主菜"></td>
							<td>3</td>
							<td><input name="secondfood3" type="text" placeholder="副菜"></td>
						</tr>
						<tr>
							<td><input name="price4" type="text" placeholder="价格"></td>
							<td>D</td>
							<td><input name="mainfood4" type="text" placeholder="主菜"></td>
							<td>4</td>
							<td><input name="secondfood4" type="text" placeholder="副菜"></td>
						</tr>
						<tr>
							<td><input name="price5" type="text" placeholder="价格"></td>
							<td>E</td>
							<td><input name="mainfood5" type="text" placeholder="主菜"></td>
							<td>5</td>
							<td><input name="secondfood5" type="text" placeholder="副菜"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<button type="submit" class="btn" onclick="submitMenu();">Submit</button>
		</form>
	</div>
	<div class="container">
		<!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit translucent">
			<h3>网站正在建设中，感谢您的支持！</h3>
			<p>现在，我们正在为大家打造一个全新的用餐环境，请暂时使用QQ订餐，近期我们就会推出网络订餐系统，并带来神秘惊喜！敬请期待。</p>
			<p>
				<a href="#" class="btn btn-primary btn-large">赞一下!</a>
			</p>
		</div>
		<!-- Example row of columns -->
		<div class="row">
			<div class="span4">
				<h2 class="color:white">随心的搭配方式</h2>
				<p>我们将提供多种不同口味，不同价格，不同营养成分的食物供您选择，您可以随心搭配自己的午餐、晚餐，让用餐不再单调.</p>
				<p>
					<a class="btn" href="#">赞一下！</a>
				</p>
			</div>
			<div class="span4">
				<h2>直观的营养分析</h2>
				<p>我们聘请了专业的营养分析师，对每一种食物进行营养成分分析，通过网站，您能够观察到您的食物所含的营养成分及健康建议，这样，您能够为自己合理安排饮食，保持身体健康。</p>
				<p>
					<a class="btn" href="#">赞一下！</a>
				</p>
			</div>
			<div class="span4">
				<h2>贴心的饮食管理</h2>
				<p>如果您完成了免费注册，我们将为您记录每天的用餐情况，您可以很直观地看到自己的用餐喜好，并根据营养成分进行适当调整，对于那些长期支持我们的用户，我们还将提供智能的推荐，根据您的用餐习惯为您推荐对您健康有帮助的食物。</p>
				<p>
					<a class="btn" href="#">赞一下！</a>
				</p>
			</div>
		</div>
		<hr>
		<footer>
			<p>千方百味感谢您的支持！</p>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>