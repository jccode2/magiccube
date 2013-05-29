define(function(require, exports, module) {
	
	var $ = require('jquery');
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');
	require('bootstrap.extension');
	require('js/order-tablelist.js');
	require('form');
	
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
				$("#groupForm").submit(function() {
					if($.validation().check()) { // validate 
						//ajax submit form
						var options = {
							success: function(responseText, statusText, xhr, $form) {
								var data = responseText, 
									form = $form.get(0), 
									groupName = form.groupName.value, 
									detail = form.detail.value, 
									sort = form.sort.value;
								var groupVO = {
									"groupName": groupName, 
									"detail": detail, 
									"sort": sort, 
									"image": data.image, 
									"id": data.id
								};
								appendToList(groupVO);
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
							url: webRoot+"/shop/group/" + orderId, 
							success: function(responseText, statusText, xhr, $form) {
								saveItemCallback(orderId, $form);
							}
						};
					$(this).ajaxSubmit(options);
					return false;
				});
			}, 
			
			// edit and delete operate btn
			operateEvent: function() {
				$(document).on("click", "#groupList .btns i", function() {
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
				$("#editForm").on("keypress", "input", function(e) {
					var keycode = e.which;
					if(keycode == 13 || keycode == 108) { // Enter
						saveItem($(this).attr("orderId"));
					}
				});
			}, 
			
			// bind window resize event
			resizeEvent: function() {
				$(window).bind("resize", resetHeight);
			}
			
		};
	})(jQuery);

	
	function insertBtnClick (checked) {
		var $addPanel = $("#add_panel"), 
			$groupList = $("#groupList"), 
			winHeight = $(window).height();
		if(checked) {
			$groupList.addClass("overflow");
			$addPanel.removeClass("hide");
			adjustHeight();
		} else {
			$groupList.height(winHeight).removeClass("overflow");
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
		$("#groupList").height($(window).height() - 200);
	}

	function showRowEditBtn(checked, className) {
		var $btns = $("#groupList .btns");
		if(checked) {
			$btns.removeClass("hide").children("i").removeClass().addClass(className);
		} else {
			$btns.addClass("hide");

			// check whether some editing item exist.
			$("#editForm").addClass("hide").prev(".view").removeClass("hide");
		}
	}
	
	/**
	 */
	function appendToList(groupVO) {
		var $firstItem = $("#groupList > ul > li").eq(0), 
			$newNode = $firstItem.clone();
		$firstItem.before($newNode);
		$newNode.attr("id", "item_" + groupVO.id);
		$("img", $newNode).attr("src", webRoot+"/"+groupVO.image)
			.attr("title", groupVO.groupName)
			.attr("alt", groupVO.groupName);
		$(".header", $newNode).html(groupVO.groupName);
		$(".detail", $newNode).html(groupVO.detail);
		$(".btns i", $newNode).attr("value", groupVO.id);
	}
	
	function deleteItem(orderId) {
		var url = webRoot+"/shop/group/" + orderId;
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
		changeMode(orderId, "edit");
		fillEditForm(orderId);
	}
	
	function saveItem(orderId) {
		$("#editForm").submit();
		
	}

	function saveItemCallback(orderId, $form) {
		changeMode(orderId, "view");
	
		var $item = $("#item_" + orderId), 
			editForm = $form.get(0);
		$(".text .header", $item).html(editForm.groupName.value);
		$(".text .detail", $item).html(editForm.detail.value);
	}
	
	
	/**
	 * change mode
	 *
	 * @param mode edit | view
	 */
	function changeMode(orderId, mode) {
	
		var $item = $("#item_" + orderId), 
			$btn = $(".btns i", $item), 
			$pic = $(".pic", $item);
	
		if(mode === "edit") {
			$btn.removeClass().addClass("icon2-ok");
			$pic.addClass("pointer").on("click", function() {
				$("#edit-file-upload").data("orderId", orderId).click();
			});
			$(".text .view", $item).addClass("hide");
			$(".text", $item).append($("#editForm").removeClass("hide"));
		}
		else if(mode === "view") {
			$btn.removeClass().addClass("icon2-edit");
			$pic.removeClass("pointer").off("click");
			$(".text .view", $item).removeClass("hide");
			$("#editForm").addClass("hide");
		}
		else {
			throw "argument mode must be edit or view.";
		}
	}
	
	function fillEditForm(orderId) {
		var $item = $("#item_" + orderId), 
			editForm = $("#editForm").get(0), 
			url = webRoot + "/shop/group/" + orderId;
		$.getJSON(url).done(function(groupVO) {
			editForm.id.value = groupVO.id;
			editForm.groupName.value = groupVO.groupName;
			editForm.sort.value = groupVO.sort;
			editForm.detail.value = groupVO.detail;
		});
	}

	
	
	// main
	jQuery(function ($) {
		Init.run();
	});
});