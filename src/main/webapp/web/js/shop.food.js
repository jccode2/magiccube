define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');
	require('bootstrap.extension');
	require('js/order-tablelist.js');
	require('form');
	require('pagination');
	
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
			
			// bind button event
			btnEvent: function() {
				$("#btn_insert").toggleButton(insertBtnClick);
				$("#btn_edit").toggleButton(function (checked) {
					$("#btn_delete.active").removeClass("active");
					showRowEditBtn(checked, "icon2-edit");
				});
				$("#btn_delete").toggleButton(function (checked) {
					$("#btn_edit.active").removeClass("active");
					showRowEditBtn(checked, "icon2-remove");
				});
			}, 
			
			// image wrapper hover & click event
			imgUploadEvent: function() {
				$(".img-wapper").hover(function () {
					$(this).siblings(".img-tips").removeClass("hide");
				}, function () {
					$(this).siblings(".img-tips").addClass("hide");
				})
				.click(function() {
					$(this).siblings("input[type='file']").click();
				});
			}, 
			
			// html5 upload image preview
			imagePreview: function() {
				util.imagePreview($("#file-upload"), $("#image-preview"));
				
				// when edit
				$("#edit-file-upload").change(function() {
					var img = this.files[0], 
						reader = new FileReader(), 
						orderId = $(this).data("orderId");
					
					reader.onload = function(evt) {
						$("#img_" + orderId).attr("src", evt.target.result);
					}
					reader.readAsDataURL(img);
				});
			}, 
			
			// form submit validate
			validate: function() {
				$.validation().init();
			}, 
			
			submit: function() {
				$("#foodForm").submit(function() {
					if($.validation().check()) { // validate 
						//ajax submit form
						var options = {
							success: function(responseText, statusText, xhr, $form) {
								var data = responseText, 
									form = $form.get(0), 
									foodName = form.foodName.value, 
									detail = form.detail.value;
								var foodVO = {
									"foodName": foodName, 
									"detail": detail, 
									"image": data.image, 
									"id": data.id
								};
								appendToList(foodVO);
							}
						};
						$(this).ajaxSubmit(options);
					}
					return false;
				});
				
				// form for update
				$("#editForm").submit(function() {
					var orderId = $("input[name='id']", this).val(), 
						options = {
							type: "put", 
							url: webRoot+"/shop/food/" + orderId, 
							success: function(responseText, statusText, xhr, $form) {
								//alert(responseText);
							}
						};
					$(this).ajaxSubmit(options);
					return false;
				});
			}, 
			
			// edit and delete operate btn
			operateEvent: function() {
				$(document).on("click", "#foodList .btns i", function() {
					var $this = $(this), 
						orderId = $this.attr("value");
					if($this.hasClass("icon2-remove")) {
						deleteItem(orderId);
					} else if($this.hasClass("icon2-edit")) {
						editItem(orderId);
					} else if($this.hasClass("icon2-ok")) {
						saveItem(orderId);
					} else {
						throw "btns的样式必须是icon2-remove, icon2-edit, icon2-ok之一";
					}
				});
			}, 
			
			// event for edit/update record dynamic
			dynamicUpdateEvent: function() {
				$(document).on("keypress", "#foodList .text p input", function(e) {
					var keycode = e.which;
					if(keycode == 13 || keycode == 108) { // Enter
						saveItem($(this).attr("orderId"));
					}
				});
			}, 
			
			// bind window resize event
			resizeEvent: function() {
				$(window).bind("resize", resetHeight);
			}, 
			
			// pagination
			pagination: function() {
				$("#page").pagination({
					className: "pagination-right", 
					page: $("#pageNo").val(), 
					count: $("#pageCount").val(), 
					callback: function (current_page, new_page) {
						$("#pageNo").val(new_page);
						$("#queryForm").submit();
					}, 
					refresh: false
				});
			}
		};
	})(jQuery);

	
	/**
	 * single toggle button click event
	 * @param  {string}   id       
	 * @param  {Function} callback 
	 */
	function toggle(id, callback) {
		$("#"+id).click(function () {
			var $this = $(this);
			setTimeout(function() {
				var checked = $this.hasClass("active");
				callback(checked, $this);
			}, 0);
		});
	}
	

	function insertBtnClick (checked) {
		var $addPanel = $("#add_panel"), 
			$foodList = $("#foodList"), 
			winHeight = $(window).height();
		if(checked) {
			$foodList.addClass("overflow");
			$addPanel.removeClass("hide");
			adjustHeight();
		} else {
			$foodList.height(winHeight).removeClass("overflow");
			$addPanel.addClass("hide");
		}
	}

	function resetHeight () {
		var insertChecked = $("#btn_insert").hasClass("active");
		if(insertChecked) {
			adjustHeight();
		}
	}
	
	function adjustHeight() {
		$("#foodList").height($(window).height() - 280);
	}

	function showRowEditBtn(checked, className) {
		var $btns = $("#foodList .btns");
		if(checked) {
			$btns.removeClass("hide").children("i").removeClass().addClass(className);
		} else {
			$btns.addClass("hide");
		}
	}
	
	/**
	 */
	function appendToList(foodVO) {
		var $firstItem = $("#foodList > ul > li").eq(0), 
			$newNode = $firstItem.clone();
		$firstItem.before($newNode);
		$newNode.attr("id", "item_" + foodVO.id);
		$("img", $newNode).attr("src", webRoot+"/"+foodVO.image);
		$(".header", $newNode).html(foodVO.foodName);
		$(".detail", $newNode).html(foodVO.detail);
		$(".btns i", $newNode).attr("value", foodVO.id);
	}
	
	function deleteItem(orderId) {
		var url = webRoot+"/shop/food/" + orderId;
		$.ajax({
			url: url, 
			type: "delete"
		})
		.done(function() {
			$("#item_"+orderId).animate({
				opacity: 0, 
				height: 0
			}, 1000, function() {
				$(this).remove();
			});
		})
		.fail(function(jqXHR) {
			alert("删除失败. " + jqXHR.responseText);
		});
	}
	
	function editItem(orderId) {
		$("#item_" + orderId + " .text p").each(function() {
			var $this = $(this);
			$this.wrapInner("<input type='text' orderId='"+orderId+"' value='"+$this.html()+"'>");
		});
		$("#item_" + orderId + " .btns i").removeClass().addClass("icon2-ok");
		$("#item_" + orderId + " .pic").addClass("pointer").on("click", function() {
			$("#edit-file-upload").data("orderId", orderId).click();
		});
	}
	
	function saveItem(orderId) {
		
		$("#item_" + orderId + " .text p").each(function() {
			var $this = $(this), 
				text = $this.children("input").val();
			$this.html(text);
			
			if($this.hasClass("header")) {
				$("#editForm input[name='foodName']").val(text);
			} else if($this.hasClass("detail")) {
				$("#editForm input[name='detail']").val(text);
			} else {
			}
			
		});
		$("#item_" + orderId + " .btns i").removeClass().addClass("icon2-edit");
		$("#item_" + orderId + " .pic").removeClass("pointer").off("click");
		
		// do update to db
		$("#editForm input[name='id']").val(orderId);
		$("#editForm").submit();
	}
	
	
	
	
	// main
	jQuery(function ($) {
		Init.run();
	});
});