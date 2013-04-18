define(function(require, exports, module) {
	
	var $ = require('jquery'), 
		util = require('util'), 
		webRoot = util.webRoot;
	require('bootstrap');
	require('bootstrap.extension');

	var Init = (function($) {

		return {
			// main
			run: function() {
				for(var fn in Init) {
					if(fn === "run" || fn.startsWith('_')) continue;
					Init[fn].call(window);
				}
			}, 
			modal: function () {
				$("#btn_add, .foods-area .food-add").click(function () {
					showModal('add', null);
				});
			}, 
			checkboxEvt: function () {
				$(".category :checkbox").change(function () {
					showOrHideTR($(this));
				});
			}, 
			addFoodDlgBtnEvt: function() {

				// 添加套餐,保存&下一步
				$("#btn_done").click(function() {
					var $this = $(this), 
						$prev = $("#btn_prev");
					if($this.hasClass("next")) { // next
						// validate
						if($.validation().check()) {
							
							switchStepView("step2");
						}
					}
					// submit
					else {
						// set itemIds value
						setItemIds();
						$("#packageForm").submit();
					}
				});

				// 上一步
				$("#btn_prev").click(function() {
					

					switchStepView("step1");
				});
			}, 
			btnEvt: function() {
				$("#btn_edit").toggleButton(function(checked) {
					var $tbFoods = $("#tb_foods");
					if(checked) {
						$tbFoods.addClass("edit");
					} else {
						$tbFoods.removeClass("edit");
					}
				});
			}, 
			foodOperationBtnEvt: function() {
				$(".foods-area .icon-remove").click(function () {
					console.log("delete a food");
					var $this = $(this), 
						$item = $this.closest("li"), 
						packageId = $item.attr("id").substring(5);

					deletePackage(packageId);
				});

				$(".foods-area .icon-edit").click(function () {
					var $this = $(this), 
						$item = $this.closest("li"), 
						foodId = $item.attr("id").substring(5);

					editPackage(foodId);
				});

				$(".foods-area .icon-download, .foods-area .icon-upload").click(function () {
					var $this = $(this), 
						$item = $this.closest("li"), 
						foodId = $item.attr("id").substring(5);

					foodDrop(foodId, droped);
				});
			}, 
			validate: function() {
				$.validation().init();
				$("#packageForm").submit(function () {
					if(!$.validation().check()) return false;
				});
			}, 
			formBind: function() {
				$("#droped").change(function() {
					$("#_droped").val(this.checked);
				});
			}, 
			// image wrapper hover & click event
			imgUploadEvent: function() {
				$(".addfood-photo-wrapper").hover(function () {
					$(this).siblings(".img-tips").removeClass("hide");
				}, function () {
					$(this).siblings(".img-tips").addClass("hide");
				})
				.click(function() {
					$(this).siblings("input[type='file']").click();
				});
			}, 
			imagePreview: function() {
				util.imagePreview($("#file-upload"), $("#image-preview"));
			}, 
			selectFoodEvt: function() {
				
				$(".food-select").change(function() {
					var $this = $(this),
						$img = $this.prevAll("img"), 
						foodId = $this.attr("foodId"), 
						src = $img.attr("src"), 
						foodName = $img.attr("alt"), 
						checked = $this.is(":checked");

					// console.log("foodId: " + foodId + "; src: "+ src + "; checked: " + checked);
					if(checked) {
						addSelecedFood(foodId, src, foodName);
					} else {
						removeSelectedFood(foodId);
					}
				});
			}
		};

	})(jQuery);

	// template
	var tempSelectedFood = '<li id="selected_item_${foodId}"><img src="${src}" alt="${foodName}"><span>${foodName}</span></li>';

	/**
	 * show or hide TR, according to the checkbox state.
	 */
	function showOrHideTR($checkbox) {
		var $tr = $("#tr_" + $checkbox.attr("idx"));
		if($checkbox.is(":checked")) { // show
			$tr.removeClass("hide");
		} else {
			$tr.addClass("hide");
		}
	}

	function switchStepView(step) {
		var $prev = $("#btn_prev"), 
			$next = $("#btn_done"), 
			$step1 = $("#step1"), 
			$step2 = $("#step2"), 
			$dlg = $("#dialog-add-package"), 
			$footerSelect = $("#modal-footer-food-select"), 
			$form = $("#packageForm");

		if(step === "step1") {
			$prev.addClass("hide");
			$next.html("下一步").addClass("next");

			$step1.removeClass("hide");
			$step2.addClass("hide");
			$dlg.removeClass("dialog-select-food");
			$footerSelect.addClass("hide");
			$form.addClass("form-dialog");

		} else if (step === "step2") {
			$prev.removeClass("hide");
			$next.html("保存").removeClass("next");

			$step1.addClass("hide");
			$step2.removeClass("hide");
			$dlg.addClass("dialog-select-food");
			$footerSelect.removeClass("hide");
			$form.removeClass("form-dialog");

		} else {}
	}

	/**
	 * show modal window
	 * @param type "add|edit"
	 * @param packageVO init form
	 */
	function showModal(type, packageVO) {
		initForm(packageVO);
		setActionType(type);
		clearTooltips();

		// if(type === "add") {
		// 	switchStepView("step1");
			
		// } else if(type === "edit") {
		// 	switchStepView("step2");
			
		// } else {}
		switchStepView("step1");
		
		$("#dialog-add-package").modal("show");
	}

	/**
	 * set action type 
	 * @param type "add|edit"
	 */
	function setActionType(type) {
		$("#actionType").val(type);
	}

	function clearTooltips() {
		$(".tooltip").remove();
	}

	function addSelecedFood(foodId, src, foodName) {
		var html = tempSelectedFood
						.replace("${foodId}", foodId)
						.replace("${src}", src)
						.replace(/\${foodName}/g, foodName);
		$("#selected-foods").append(html);
		adjustSelectedFoodSize();
	}

	function removeSelectedFood(foodId) {
		$("#selected_item_"+foodId).remove();
		adjustSelectedFoodSize();
	}

	function adjustSelectedFoodSize() {
		var $foodToAdd = $("#food-to-add"), 
			$selectedFoods = $("#selected-foods"), 
			foodToAddWidth = $foodToAdd.width(), 
			width = $selectedFoods.children("li").length * 90 + 10, 
			overflow = width > foodToAddWidth;

		if(overflow) {
			$selectedFoods.width(width);
			$foodToAdd.addClass("overflow");
		}
		else {
			$selectedFoods.width(foodToAddWidth);
			$foodToAdd.removeClass("overflow");
		}
	}

	function setItemIds() {
		var id, ids = "";
		$("#selected-foods > li").each(function() {
			id = $(this).attr("id").substring(14);
			ids += (id + ",");
		});
		$("#itemIds").val(ids);
	}

	function clearSelectedFoods() {
		$("#step2 :checkbox:checked").attr("checked", false);
		$("#selected-foods > li").remove();
		$("#food-to-add").removeClass("overflow");
	}

	/**
	 * init form
	 */
	function initForm(foodVO) {
		var noEmpty = !!foodVO;
		$("#image-preview").attr("src", noEmpty ? webRoot + foodVO["image"] : "");
		$("#foodId").val(noEmpty ? foodVO["id"] : "");
		$("#foodName").val(noEmpty ? foodVO["foodName"] : "");
		$("#groupId").val(noEmpty ? foodVO["groupId"] : "");
		$("#originPrice").val(noEmpty ? foodVO["originPrice"] : "");
		$("#currentPrice").val(noEmpty ? foodVO["currentPrice"] : "");
		$("#stock").val(noEmpty ? foodVO["stock"] : "");
		$("#detail").val(noEmpty ? foodVO["detail"] : "");
		$("#stockout").attr("checked", false);
		$("#droped").attr("checked", noEmpty ? foodVO["droped"] : false);
		$("#_droped").val(noEmpty ? foodVO["droped"] : false);

		clearSelectedFoods();
		if(noEmpty) {
			var items = foodVO.items, 
				foodId;
			for(var i = 0; i < items.length; i++) {
				foodId = items[i].foodId;
				$("#step2 :checkbox[foodId='" + foodId + "']").click();
			}
		}
	}

	function deletePackage(packageId) {
		var url = webRoot + "/shop/package/" + packageId;
		$.ajax({
			url: url, 
			type: "delete"
		})
		.done(function() {
			var $item = $("#item_" + packageId);
			$item.fadeOut(function() {
				$item.remove();
			});
		})
		.fail(function(jqXHR) {
			alert("删除失败. " + jqXHR.responseText);
		});
	}

	function editPackage(foodId) {
		var url = webRoot + "/shop/package/" + foodId;
		$.getJSON(url, function(packageVO) {
			showModal("edit", packageVO);
		});
	}

	function foodDrop(foodId) {
		var $item = $("#item_" + foodId), 
			$btndrop = $("#drop_" + foodId), 
			droped = $item.hasClass("drop"), 
			url = webRoot + "/shop/foodreshop/" + foodId + "?droped=" + !droped;
		$.ajax({
			url: url, 
			type: "put"
		})
		.done(function() {
			var ret = !droped;
			if(ret) {
				$item.addClass("drop");
				$btndrop.removeClass("icon-download").addClass("icon-upload").attr("title", "快速上架");
				$("span", $item).prepend("<b>[下架]</b>");
			} else {
				$item.removeClass("drop");
				$btndrop.removeClass("icon-upload").addClass("icon-download").attr("title", "快速下架");
				$("b", $item).remove();
			}
		})
		.fail(function(jqXHR) {
			alert("更新上/下架失败. " + jqXHR.responseText);
		});
	}



	jQuery(function ($) {
		Init.run();
	});

});