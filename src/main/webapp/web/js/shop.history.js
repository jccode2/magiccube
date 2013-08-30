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
		$("#btn-group-status > button[value='"+status+"']").addClass("active");
		
		var startDate = $("#startDate").val(),
			endDate = $("#endDate").val();
		$("#btn-group-date > button[value='"+
				((!startDate && !endDate) ? "" : startDate+"~"+endDate)
				+"']").addClass("active");
	}
	
	function initEvent() {
		$("#page").pagination({
			className: "pagination-right", 
			num: 20,
			page: $("#pageNo").val(), 
			count: $("#pageCount").val(), 
			callback: function (current_page, new_page) {
				$("#pageNo").val(new_page);
				$("#orderQueryForm").submit();
			}, 
			refresh: false
		});
		
		$("#btn-group-status").buttonGroup().change(function(e) {
			$("#orderStatus").val(this.val());
			$("#orderQueryForm").submit();
		});
		
		$("#btn-group-date").buttonGroup().change(function(e) {
			var v = this.val(), 
				startDate, 
				endDate;
			if(v == "") {
				startDate = endDate = "";
			} else {
				startDate = v.split("~")[0];
				endDate = v.split("~")[1];
			}
			$("#startDate").val(startDate);
			$("#endDate").val(endDate);
			$("#orderQueryForm").submit();
		});
		
		$(".order-btn-abnormal").click(function() {
			if($(this).attr("status") != 2) {
                showExceptionDetailDlg( $(this).attr("value") );
			}
			return false;
		});


        $("#btn_save_exception").on("click", function(){
            // save and mark as exception
			markAsException($(this));
            $("#exception_detail_dlg").modal("hide");
            return false;
        });
	}

    function showExceptionDetailDlg(orderId){
        $("#exception_detail_area").val("");
        $("#exception_detail_dlg").data("orderId", orderId).modal();
    }

	
	//标记为异常
	function markAsException() {
		var id = $("#exception_detail_dlg").data("orderId"), 
            exceptionDesc = $("#exception_detail_area").val();

		$.ajax({
			type: "put", 
			url: webRoot+"/shop/order/"+id+"?exceptionDesc="+exceptionDesc
		})
		.done(function(data) {
			if(data == true) {
                var $tableItem = $("#table_item_"+id);
				$tableItem.addClass("abnormal");
				$(".order-btn-abnormal", $tableItem).attr("status", 2);
                $(".exception-desc", $tableItem).html("异常描述:&nbsp;"+exceptionDesc);
				return true;
			} else {
				alert("标记为异常失败");
				return false;
			}
		});
	}
	
});
