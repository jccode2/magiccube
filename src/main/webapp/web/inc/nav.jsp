<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/web/inc/head.jsp"%>
<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${webRoot}/web/bubbletip/js/jQuery.bubbletip-1.0.6.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/LoginAction.js"></script>
<script type="text/javascript" src="${webRoot}/dwr/interface/UserAction.js"></script>
<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css" />
<link href="${webRoot}/web/bubbletip/js/bubbletip/bubbletip.css" rel="stylesheet" type="text/css" />
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
	display: none;
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

.usercenter-hr {
	-webkit-margin-before: 0.4em;
	-webkit-margin-after: 0.3em;
}

.usercenter-label {
	text-align: center;
	font-style: inherit;
	color: gray;
}

textarea {
	width: 97%;
}
</style>

<script type="text/javascript">
	var currentuser;

	$(document).ready(function() {

		UserAction.getCurrentUser(function(result) {
			currentuser = result;

			if (currentuser == null) {
				var username = $.cookie('rem10-u');
				var password = $.cookie('rem10-p');
				if (username != null) {
					LoginAction.login(username, password, function(result) {
						if (result.success) {
							UserAction.getCurrentUser(function(result) {
								currentuser = result;
								initUserCenter();
							});
						}
					});
				} else {
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
				}
			} else {
				initUserCenter();
			}

		});

		initRegistButton();

		initLoginButton();

		initQuickLoginButton();

		initLogoutButton();

		initSuggestButton();

	});

	function initSuggestButton() {
		$('#suggestbutton').click(function(e) {
			$('#suggest-frame').show();
			$('#success-tip-btn').show();
			$('#suggest-result').hide();
		});

		$('#suggestclose').click(function(e) {
			$('#suggest-frame').hide();
		});

		$('#suggestclose2').click(function(e) {
			$('#suggest-frame').hide();
		});

		$('#success-tip-btn').click(function(e) {
			var suggestVO = {
				userName : currentuser.userName,
				suggestContent : $('#suggest-content').val()
			};
			$('#success-tip-btn').tooltip('destroy');
			OrderAction.suggest(suggestVO, function(result) {
				if (result.success) {
					$('#success-tip-btn').hide();
					$('#suggest-result').show();
				} else {
					$('#success-tip-btn').tooltip({
						title : result.message
					}).tooltip('show');
				}
			});
		});
	}

	function initUserCenter() {
		$('#loginlink').hide();
		$('#registlink').hide();
		$('#usercenterlink').show();
		$('#usercenterbutton').bubbletip($('#usercenter'), {
			deltaDirection : 'down',
			deltaPosition : 50,
			offsetTop : 0
		});
		$('#usercenterlabel').text(currentuser.userName);
		$('#userscore').text("积分:" + currentuser.score);
		$('#userscorediv').tooltip({
			title : "将来您可以通过积分享受各种优惠",
			placement : "left"
		});
		$('#userbutton3').tooltip({
			title : "即将推出",
			placement : "left"
		});
		$('#userbutton4').tooltip({
			title : "即将推出",
			placement : "left"
		});
	}

	function initLogoutButton() {
		$('#logoutbutton').bind('click', function() {
			LoginAction.exit(function() {
				$('#usercenterlink').hide();
				$('#usercenterbutton').removeBubbletip();
				$('#loginlink').show();
				$('#registlink').show();
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
				$.cookie("rem10-u", null);
				$.cookie("rem10-p", null);
			});
		});

	}

	function initQuickLoginButton() {
		$('#loginafterregist').bind('click', function() {
			var strUsername = $.trim($('#registerUsername').val());
			var strPassword = $.trim($('#registerPassword').val());
			$('#loginafterregist').tooltip('destroy');
			LoginAction.login(strUsername, strPassword, function(result) {
				if (result.success) {
					$('#loginlink').removeBubbletip();
					$('#registlink').removeBubbletip();
					UserAction.getCurrentUser(function(result) {
						currentuser = result;
						initUserCenter();
					});
				} else {
					$('#loginafterregist').tooltip({
						title : result.message
					}).tooltip('show');
				}
			});
		});
	}

	function initLoginButton() {
		$('#loginbutton').bind('click', function() {
			var inputRight = true;
			var strUsername = $.trim($('#loginUsername').val());
			var strPassword = $.trim($('#loginPassword').val());

			if (strUsername == "") {
				$('#loginUsername').tooltip({
					title : '请输入用户名'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#loginUsername').tooltip('destroy');
			}

			if (strPassword == "") {
				$('#loginPassword').tooltip({
					title : '请输入密码'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#loginPassword').tooltip('destroy');
			}

			if (!inputRight) {
				return;
			}

			LoginAction.login(strUsername, strPassword, function(result) {
				$('#loginbutton').tooltip('destroy');
				if (result.success) {
					$('#loginlink').removeBubbletip();
					$('#registlink').removeBubbletip();
					UserAction.getCurrentUser(function(result) {
						currentuser = result;
						initUserCenter();
					});
				} else {
					$('#loginbutton').tooltip({
						title : result.message
					}).tooltip('show');
				}
			});

			if ($("#remember10day").attr('checked') == undefined) {
			} else {
				$.cookie('rem10-u', strUsername, {
					path : '/',
					expires: 10
				});
				$.cookie('rem10-p', strPassword, {
					path : '/',
					expires: 10
				});
			}

		});
	}

	function initRegistButton() {
		$('#submitregist').bind('click', function() {
			var inputRight = true;
			var strUsername = $.trim($('#registerUsername').val());
			var strPassword = $.trim($('#registerPassword').val());
			var strConfirm = $.trim($('#registerConfirm').val());

			if ($("#isAgree").attr('checked') == undefined) {
				$('#policy').tooltip({
					title : '请先阅读并同意使用条款和协议'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#policy').tooltip('destroy');
			}

			if (strUsername == "") {
				$('#registerUsername').tooltip({
					title : '请输入用户名'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#registerUsername').tooltip('destroy');
			}

			if (strPassword == "") {
				$('#registerPassword').tooltip({
					title : '请输入密码'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#registerPassword').tooltip('destroy');
			}

			if (strPassword != strConfirm) {
				$('#registerConfirm').tooltip({
					title : '两次输入的密码不一致，请检查'
				}).tooltip('show');
				inputRight = false;
			} else {
				$('#registerConfirm').tooltip('destroy');
			}

			if (!inputRight) {
				return;
			}

			var userVO = {
				userName : strUsername,
				password : $.trim($('#registerPassword').val())
			};
			LoginAction.regist(userVO, function(result) {
				if (result.success) {
					$('#regist1').hide();
					$('#regist2').show();
					$('#submitregist').tooltip('destroy');
				} else {
					$('#submitregist').tooltip('destroy');
					$('#submitregist').tooltip({
						title : result.message
					}).tooltip('show');
				}
			});
		})
	}
</script>
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
</body>
</html>
