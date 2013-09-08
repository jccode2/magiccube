define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
    require('bootstrap');
	require('bootstrap.extension');
	require('charisma');
	
	// Init
	var Init = (function($) {
		
		return {
			// main
			run: function() {
				for(var fn in Init) {
					if(fn === "run" || fn.startsWith('_')) continue;
					Init[fn].call(window);
				}
			}, 
			
			reload: function() {
				window.setInterval("window.location.reload()", 2 * 60 * 1000);
			}, 
			
			btnEvent: function() {
				$("#new_win").on("click", function() {
					var url = webRoot + '/shop/dashboard';
					window.open(url, "_blank");
				});
			}
		};
		
	})(jQuery);
	
    // main
	jQuery(function ($) {
		Init.run();
	});
});