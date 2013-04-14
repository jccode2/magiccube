//初始化页首
function initTopBar(){
	if(userName){
		$('#loginBar').hide();
		$('#registerBar').hide();
		$('#userBar').show();
		$('#exitBar').show();
		$('#userBar').children().html('<i class="icon-user"></i> '+userName);
	}else{
		$('#loginBar').show();
		$('#registerBar').show();
		$('#userBar').hide();
		$('#exitBar').hide();
	}
}

//注册
function register(type){
	var vo = {};
	vo.userName = $('#registerUserName').val();
	vo.password = $('#registerPassword').val();
	vo.email = $('#registerEmail').val();
	LoginAction.regist(vo,function(data){
		if(data.success){
			if(type!=1){
				$('#register').modal('hide');
				showSuccess("注册成功");
			}else{
				window.close();
			}
		}
	});
}

//登录
function login(account,password,isCookie){
	LoginAction.login(account,password,function(data){
		if(data.success){
			$('#login').modal('hide');
			$('#loginBar').hide();
			$('#registerBar').hide();
			$('#userBar').show();
			$('#exitBar').show();
			CommonAction.getUser(function(data){
				$('#userBar').children().html('<i class="icon-user"></i> '+data.userName);
			});
			showSuccess("登录成功");
			if(isCookie){
				var Days = 10;
				var exp = new Date(); 
				exp.setTime(exp.getTime() + Days*24*60*60*1000);		
				setCookie('account', account, exp.toGMTString());
				setCookie('password', password, exp.toGMTString());
			}
		}
	});
}

//退出
function exit(){
	LoginAction.exit(function(){
		$('#loginBar').show();
		$('#registerBar').show();
		$('#userBar').hide();
		$('#exitBar').hide();
		deleteCookie('account');
		deleteCookie('password');
	});
}

//打开找回密码窗口
function openFindPassword(){
	window.open(findPasswordURL,'findPassword');
}

//打开注册窗口
function openRegister(){
	window.open(registerURL,'register');
}
//显示成功消息
function showSuccess(html) {
	showMsg(html, "alert-success");
}

//显示失败消息
function showError(html) {
	showMsg(html, "alert-error");
}

//显示消息
function showMsg(html, className) {
	$("#msg-content").html(html);
	$("#msg").addClass(className).removeClass("hide")
		.fadeIn(400)
		.delay(2000)
		.fadeOut(800, function() {
			$(this).removeClass(className).addClass("hide");
		});
	center($("#msg"));
}

//居中
function center($obj){
	$obj.css('left','50%').css('margin-left','-'+$obj.css('width')/2);
}

//跳转到指定页面
function toPage(url){
	window.location.href = url;
}