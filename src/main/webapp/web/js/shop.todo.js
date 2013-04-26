define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot, 
		ws = null; //websocket
	require('bootstrap');
	require('bootstrap.extension');
	require('js/order-tablelist.js');
	
	jQuery(function ($) {
		init();
		initEvent();
		initWebSocket();
	});

	function init() {
		// button group init
		var ot = $("#overtime").val();
		$(".btn-group > button[value='"+ot+"']").addClass("active");
		
		// init auto print state
		$.get(webRoot+"/shop/autoprint", function(checked) {
			if(checked) {
				$("#auto_checkbox").attr("checked", true);
			}
		});
	}

	function initEvent() {

		$(".btn-group").buttonGroup().change(function(e) {
			$("#overtime").val(this.val());
			$("#queryForm").submit();
		});
		
		//出单
		$(".order-btn-out").click(function() {
			orderOut($(this).attr("value"));
			return false;
		});
		
		//是否自动出单
		$("#auto_checkbox").change(function() {
			var state = $(this).is(":checked"), 
				url = webRoot + "/shop/autoprint/" + state;
			$.ajax({
				url: url, 
				type: "put"
			})
			.fail(function(xhr) { alert(xhr.responseText); }); //自动出单更新失败
		});
	}
	
	// 出单
	function orderOut(id) {
		$.ajax({
			type: "put", 
			url: webRoot+"/shop/issue/"+id
		})
		.done(function(data) {
			if(data == true) {
				$("#order_item_"+id).slideUp();
				return true;
			} else {
				alert("出单失败");
				return false;
			}
		});
	}
	
	
	function initWebSocket() {
		ws_connect();
	}

	function ws_connect() {
		var url = "ws://" + window.location.host + webRoot + "/websocket/order";
		ws = new WebSocket(url);
		// ws.onopen = function() { }
		ws.onmessage = function(event) {
			ws_receiveData(event.data);
		}
		// ws.onclose = function() { }
	}

	function ws_receiveData(orderview) {
		orderview = JSON.parse(orderview);
		var rowhtml = RowBuilder.build(orderview);
		$("#tableList > ul").prepend(rowhtml);

		TableList.resetBgColor().intervalBgColor();
	}


	// ----------- build a row ------------


	var RowBuilder = (function(argument) {

		// template to build one row
		var tpl_main = '<li id="order_item_${order.id}">\
								<div class="list-item table-item">\
									<div class="row-fluid head">\
										<div class="pull-left link left-panel">\
											<span class="">${order.address}</span>\
											<span class="forestgreen">${order.phone}</span>\
										</div>\
										<div class="pull-right">\
											<span class="brown">${order.totalPrice}元</span>\
											<a href="#" class="btn btn-mini order-btn-out" value="${order.id}"><i class="icon-print"></i>&nbsp;出单</a>\
										</div>\
									</div>\
									<div class="row-fluid body">\
										<div class="left-panel">\
											<span class="order-number">订单号: ${order.id}</span>\
											<span class="order-time">\
												预计送达时间: ${order.exceptTime} (下单时间: ${order.createTime})\
											</span>\
										</div>\
										<div class="food-collapse">\
											<p class="food-list">\
												${tpl_collapse}\
											</p>\
										</div>\
										<div class="food-expand">\
											<ul class="plate-list">\
												${tpl_expand}\
											</ul>\
										</div>\
									</div>\
								</div>\
							</li>', 

			tpl_collapse = '<span>餐盘${plate.no}:</span>\
												${tpl_collapse_items}', 

			tpl_collapse_items = '<span>${food.food} ${food_amount}</span>', 

			tpl_expand = '<li>\
													<ul class="food-list">\
														${tpl_expand_items}\
														<li><span class="price">￥${plate.price}</span></li>\
													</ul>\
												</li>', 

			tpl_expand_items = '<li>${food.food} ${food_amount}</li>';


		// replaceAll
		if (typeof String.prototype.replaceAll != 'function') {
			String.prototype.replaceAll = function(s1, s2) {
				return this.replace(new RegExp(s1, "gm"), s2);
			};
		}

		/**
		 * <c:forEach items="${order.plateList }" var="plate">
				<span>餐盘${plate.no }:</span>
				${tpl_collapse_items}
			</c:forEach>
		 */
		function buildCollapse(plateList) {
			var html = "";

			for(var i = 0; i < plateList.length; i++) {
				var tpl = tpl_collapse, 
					plate = plateList[i], 
					htmlCollapseItems = buildCollapseItems(plate.foodList);

				html += tpl.replaceAll("\\${plate.no}", plate.no)
							.replaceAll("\\${tpl_collapse_items}", htmlCollapseItems);
			}

			return html;
		}

		/**
		 * <c:forEach items="${plate.foodList}" var="food">\
				<span>${food.food} <c:if test="${food.amount > 1}">x${food.amount}</c:if></span>\
			</c:forEach>
		 */
		function buildCollapseItems(foodList) {
			var html = "";
			for(var i = 0; i < foodList.length; i++) {
				var tpl = tpl_collapse_items, 
					food_amount = "", 
					food = foodList[i];
				if(food.amount > 1) {
					food_amount = "x${food.amount}".replaceAll("\\${food.amount}", food.amount);
				}
				html += tpl.replaceAll("\\${food.food}", food.food)
							.replaceAll("\\${food_amount}", food_amount);
			}
			return html;
		}

		/**
		 * <c:forEach items="${order.plateList}" var="plate">\
				<li>\
					<ul class="food-list">\
						${tpl_expand_items}
						<li><span class="price">￥${plate.price}</span></li>\
					</ul>\
				</li>\
			</c:forEach>
		 */
		function buildExpand(plateList) {
			var html = ""; 
			for(var i = 0; i < plateList.length; i++) {
				var plate = plateList[i], 
					tpl = tpl_expand, 
					htmlExpandItems = buildExpandItems(plate.foodList);
				html += tpl.replaceAll("\\${tpl_expand_items}", htmlExpandItems)
							.replaceAll("\\${plate.price}", plate.price);
			}
			return html;
		}

		/**
		 * '<c:forEach items="${plate.foodList}" var="food">\
				<li>${food.food} <c:if test="${food.amount > 1}">x${food.amount}</c:if> </li>\
			</c:forEach>'
		 */
		function buildExpandItems(foodList) {
			var html = "";
			for(var i = 0; i < foodList.length; i++) {
				var tpl = tpl_expand_items, 
					food_amount = "", 
					food = foodList[i];
				if(food.amount > 1) {
					food_amount = "x${food.amount}".replaceAll("\\${food.amount}", food.amount);
				}
				html += tpl.replaceAll("\\${food.food}", food.food)
							.replaceAll("\\${food_amount}", food_amount);
			}
			return html;
		}

		return {

			build: function(order) {
				var html = tpl_main, 
					htmlCollapse = buildCollapse(order.plateList), 
					htmlExpand = buildExpand(order.plateList);

				return html.replaceAll('\\${order.id}', order.id)
							.replaceAll('\\${order.address}', order.address)
							.replaceAll('\\${order.phone}', order.phone)
							.replaceAll('\\${order.totalPrice}', order.totalPrice)
							.replaceAll('\\${order.exceptTime}', order.exceptTime)
							.replaceAll('\\${order.createTime}', order.createTime)
							.replaceAll('\\${tpl_collapse}', htmlCollapse)
							.replaceAll('\\${tpl_expand}', htmlExpand);
			}
		};

	})();

});