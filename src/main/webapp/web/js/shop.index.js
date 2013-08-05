define(function (require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');
	require('jquery.cookie');

	jQuery(function () {
		
		$(".sidebar .nav > li").click(function() {
			var $this = $(this);
			$this.addClass("active").siblings().removeClass("active");
			setFrameURL($this.children("a").attr("href"));
			return false;
		})

		// 选中第一个.
		.eq(10).click();

		initHeight();
		
		initData();
		
		btnEvent();

		$(window).bind("resize", initHeight);

	});

	function setFrameURL (url) {
		$("#mainFrame").attr("src", url);
	}

	function initHeight () {
		var height = $(window).height() - $(".navbar-fixed-top").outerHeight() - 60;
		$("#mainFrame").attr("height", height);
	}
	
	function initData() {
		var url = webRoot + "/shop/shopdata";
		$.get(url, function(data) {
			var vo = util.toJSON(data), 
				userVO = vo["user"];
			if(userVO) {
				$(".username").html(userVO["userName"]);
				$("#user-email").html(userVO["email"]);
			}
			
			// todo-count
			$("#todo-count").html(vo["todo"]);
		});
	}
	
	function btnEvent() {
		$("#btn_logout").on("click", function() {
			var url = webRoot + "/logout";
			$.post(url, function() {
				$.cookie("rem10-u", null);
				$.cookie("rem10-p", null);
				window.location.href = webRoot + "/order";
			});
		});
		
	}
});
