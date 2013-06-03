var currentuser;

$(document).ready(function() {

	UserAction.getCurrentUser(function(result) {
		historySuggest = "default";
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
		
		// 加载用户最近订单
		initMyRecentOrder(currentuser.id);
	}
	
	function initMyRecentOrder(userId) {
		$("#viewRecentOrder").click(function() {
			$("#recent-order-frame").show();
		});
		
		$("#recent-order-close, #recent-order-close-btn").click(function() {
			$("#recent-order-frame").hide();
		});
		
		//render
		OrderAction.queryOrdersByUserId(userId, function(orderViewList) {
			console.log(orderViewList);
			var data = {"orderViewList": orderViewList};
			dust.loadSource(dust.compile($("#recentOrderTemplate").html(), "recentOrder"));
			dust.render("recentOrder", data, function(err,out) {
				$("#recent-order-frame .modal-body").html(out);
			});
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

		if ($.trim($('#suggest-content').val()) == "") {

			$('#suggest-content').tooltip({
				title : "说点什么吧？",
				placement : "left"
			}).tooltip('show');
			return;
		} else if (historySuggest == $.trim($('#suggest-content').val())) {

			$('#suggest-content').tooltip({
				title : "这个建议您已经反馈过啦，说点别的吧？",
				placement : "left"
			}).tooltip('show');
			return;
		}
		$('#suggest-content').tooltip('destroy');
		historySuggest = $.trim($('#suggest-content').val());
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
		var strPassword = $.md5($.trim($('#registerPassword').val()));
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
		var strPassword = $.md5($.trim($('#loginPassword').val()));

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
				expires : 10
			});
			$.cookie('rem10-p', strPassword, {
				path : '/',
				expires : 10
			});
		}

	});
}

function initRegistButton() {
	$('#submitregist').bind('click', function() {
		var inputRight = true;
		var strUsername = $.trim($('#registerUsername').val());
		var strPassword = $.md5($.trim($('#registerPassword').val()));
		var strConfirm = $.md5($.trim($('#registerConfirm').val()));

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
			password : $.md5($.trim($('#registerPassword').val()))
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
