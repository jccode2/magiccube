define(function (require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');

	jQuery(function () {
		
		$(".sidebar .nav > li").click(function() {
			var $this = $(this);
			$this.addClass("active").siblings().removeClass("active");
			setFrameURL($this.children("a").attr("href"));
			return false;
		})

		// 选中第一个.
		.eq(1).click();

		initHeight();
		
		initData();

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
		var url = webRoot = "/shop/shopdata";
		$.get(url, function(data) {
			var vo = util.toJSON(data), 
				userVO = vo["user"];
			if(userVO) {
				$(".username").html(userVO["userName"]);
				$("#user-email").html(userVO["email"]);
			}
		});
	}
});