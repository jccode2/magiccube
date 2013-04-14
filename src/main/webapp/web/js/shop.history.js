define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');
	require('bootstrap.extension');
	require('pagination');
	require('js/order-tablelist.js');
	
	jQuery(function ($) {
		init();
		initEvent();
	});
	
	function init() {
		// button group init
		var status = $("#orderStatus").val();
		$(".btn-group > button[value='"+status+"']").addClass("active");
	}
	
	function initEvent() {
		$("#page").pagination({
			className: "pagination-right", 
			page: $("#pageNo").val(), 
			count: $("#pageCount").val(), 
			callback: function (current_page, new_page) {
				$("#pageNo").val(new_page);
				$("#orderQueryForm").submit();
			}, 
			refresh: false
		});
		
		$(".btn-group").buttonGroup().change(function(e) {
			$("#orderStatus").val(this.val());
			$("#orderQueryForm").submit();
		});
		
		$(".order-btn-abnormal").click(function() {
			markAsException($(this).attr("value"));
			return false;
		});
	}
	
	//标记为异常
	function markAsException(id) {
		$.ajax({
			type: "put", 
			url: webRoot+"/shop/order/"+id+"?status=2"
		})
		.done(function(data) {
			if(data == true) {
				$("#table_item_"+id).addClass("abnormal");
				return true;
			} else {
				alert("标记为异常失败");
				return false;
			}
		});
	}
	
});