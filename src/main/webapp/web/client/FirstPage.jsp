<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>	
		<title>首页</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/client/css/clientNew.css">
		<script type="text/javascript" src="${webRoot}/web/js/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="${webRoot}/web/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${webRoot}/web/js/cookie.js"></script>
		<script type="text/javascript" src="${webRoot}/web/client/js/client.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/engine.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/AddressAction.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/LoginAction.js"></script>
		<script type="text/javascript" src="${webRoot}/dwr/interface/CommonAction.js"></script>
		<style type="text/css">
			body{
				background: url("${webRoot}/web/client/img/bg.jpg") no-repeat;
				font-family: "Microsoft Yahei", SimSun, Tahoma, Helvetica, Arial, sans-serif;
			}
		</style>
		<script type="text/javascript">
		var userName = "${environmentInfo.user.userName}";
		var chooseURL = '${webRoot}/web/client/ChooseFood.jsp';
		var findPasswordURL = '${webRoot}/web/client/FindPassword.jsp';
		var registerURL = '${webRoot}/web/client/register.jsp';
		$(function(){
			readCookie();
			initRegion();
			initTopBar();
		});

		//读cookie中的信息，做相应操作
		function readCookie(){
			if(getCookie('regionId')){
				window.location.href = chooseURL+'?regionId='+getCookie('regionId')+'&regionName='+getCookie('regionName');
			}
			if(getCookie('account')){
				login(getCookie('account'),getCookie('password'),false);
			}
		}

		//初始化区域
		function initRegion(){
			AddressAction.queryChildRegion(-1,function(data){
				for(var i=0;i<data.length;++i){
					createRegion(data[i]);
				}
			});
		}

		//创建一个区域
		function createRegion(obj){
			var objLi = $('<li>').append(
						$('<a>').attr('href',encodeURI(chooseURL+'?regionId='+obj.id+'&regionName='+obj.regionName)).attr('class','thumbnail text_center').append(
							$('<img>').attr('src','${webRoot}'+obj.image).css('width','100px').css('height','100px').attr('class','img-circle')
						).append($('<div>').html(obj.regionName)).click(function(){
							var Days = 10;
							var exp = new Date(); 
							exp.setTime(exp.getTime() + Days*24*60*60*1000);		
							setCookie('regionId', obj.id, exp.toGMTString());
							setCookie('regionName', obj.regionName, exp.toGMTString());
						})
					);
			$('#region_box').append(objLi);
		}

		//创建一个地址
		function createAddress(obj){
			var objTr = $('<tr>').append(
							$('<td>').attr('id',obj.id).html(obj.fullName).css('cursor','pointer').click(function(){
								window.location.href = chooseURL+'?addressId='+obj.id+'&addressName='+obj.shortName;
							})
						);
			$('#list').append(objTr);
		}

		//添加临时地址
		function addTempAddress(){
			AddressAction.addTempAddress($('#tempAddress').val(),$('#phone').val(),function(data){
				$('#addAddress').modal('hide');
			});
		}

		//查询地址
		function queryAddress(){
			var keyword = $('#keyword').val();
			if(keyword!=''){
				AddressAction.queryAddress(keyword,function(data){
					if(data.length==0){
						$("#alert").show();
						$("#list").hide();
					}else{
						$("#list").children().remove();
						$("#list").show();
						$("#alert").hide();
						for(var i=0;i<data.length;++i){
							createAddress(data[i]);
						}
						$('#list').css('top','-'+$('#list').css('height'));
					}
				});
			}else{
				$("#list").hide();
			}
		}
		</script>
	</head>
	<body style="overflow-x: hidden">
		<div id="msg" class="alert hide" style="position: absolute;">
	    	<div id="msg-content">
	    	</div>
		</div>
		<div class="navbar navbar-static-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<img title="跳转到首页" src="${webRoot}/web/client/img/logo.png" class="logo" onclick="toPage('${webRoot}/web/client/FirstPage.jsp');">
					<ul class="nav pull-right">
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
		<div>
			<div class="word"></div>
			<div class="center">
				<ul id="region_box" class="thumbnails">
				</ul>
			</div>
			<div class="bottom" align="center">
				<table id="list" class="table table-hover list">
				</table>
				<div class="input-prepend" align="center" style="display: none;">
					<span class="add-on"><i class="icon-search"></i></span>
					<input class="span4" id="keyword" type="text" placeholder="输入地址查找" onkeyup="queryAddress();">
				</div>
				<div id="alert" class="alert" style="display: none; cursor: pointer;">
				  <button type="button" class="close" data-dismiss="alert">&times;</button>
				  <a href="#addAddress" data-toggle="modal">没找到您的位置吗？请点击这里</a>
				</div>
			</div>
		</div>

		<div id="addAddress" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 490px;">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel">请提交您的地址</h3>
		  </div>
		  <div class="modal-body">
		    <form>
			  <fieldset>
			     <input id="tempAddress" type="text" class="span6" placeholder="请输入地址">
			     <br>
			     <input id="phone" type="text" placeholder="请输入手机">
			  </fieldset>
			</form>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    <button class="btn btn-primary" onclick="addTempAddress();">提交</button>
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
      				<input id="isCookie" type="checkbox"> 10天内免登录
      				<div style="float: right;">
      					<a href="#" onclick="openFindPassword();">忘记密码</a>|<a href="#" onclick="openRegister();">注册账号</a>
      				</div>
    			</label>
			  </fieldset>
			</form>
		  </div>
		  <div class="modal-footer">
		    <button class="btn btn-primary" onclick="login($('#loginAccount').val(),$('#loginPassword').val(),$('#isCookie').attr('checked'));">登录</button>
		  </div>
		</div>
	</body>
</html>
