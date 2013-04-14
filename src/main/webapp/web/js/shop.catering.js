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
				$("#btn_add, .food-add").click(function () {
					showModal("add");
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
					if($(this).hasClass("next")) { // next
						switchStepView("step2");
						initEditImage();
					} 
					// submit
					else {
						$("#foodreshopForm").submit();
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
					
					var $this = $(this), 
						$item = $this.closest("li"), 
						foodId = $item.attr("id").substring(5);

					deleteFood(foodId);
				});

				$(".foods-area .icon-edit").click(function () {
					var $this = $(this), 
						$item = $this.closest("li"), 
						foodId = $item.attr("id").substring(5);

					console.log("edit a food: " + foodId);

					editFood(foodId);
				});

				$(".foods-area .icon-leaf").click(function () {
					console.log("mark out of stock");
				});
			}, 
			addFoodDlgChooseFoodEvt: function () {
				$(".food-grid .item-list img").click(function() {
					var $this = $(this), 
						$foodList = $("#food-list"), 
						oldId = $foodList.data("selected_id");
					if(oldId) {
						$("#"+oldId).removeClass("selected");
					}
					$this.addClass("selected");
					$foodList.data("selected_id", $this.attr("id"));
				});
			}, 
			validate: function () {
				$.validation().init();
				$("#foodreshopForm").submit(function () {
					if(!$.validation().check()) return false;
				});
			}, 
			formBind: function() {
				$("#droped").change(function() {
					$("#_droped").val(this.checked);
				});
			}
			
		};

	})(jQuery);

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

	/**
	 * switch to show which view.
	 */
	function switchStepView(step) {
		var $prev = $("#btn_prev"), 
			$next = $("#btn_done"), 
			$step1 = $("#step1"), 
			$step2 = $("#step2"), 
			$dlg = $("#dialog-add-package");
		
		if(step === "step1") {
			$next.html("下一步").addClass("next");
			$prev.addClass("hide");
			
			$step1.removeClass("hide");
			$step2.addClass("hide");
			$dlg.removeClass("dialog-select-food");
			
		} else if (step === "step2") {
			$next.html("保存").removeClass("next");
			$prev.removeClass("hide");
			
			$step1.addClass("hide");
			$step2.removeClass("hide");
			$dlg.addClass("dialog-select-food");
		} else {}
	}
	
	/**
	 * show modal window
	 * @param type "add|edit"
	 */
	function showModal(type) {
		if(type === "add") {
			switchStepView("step1");
			
		} else if(type === "edit") {
			switchStepView("step2");
			$("#btn_prev").addClass("hide"); // 隐藏"上一步"按钮
			
		} else {}
		
		$("#dialog-add-food").modal("show");
	}

	function initEditImage() {
		var selectedId = $("#food-list").data("selected_id");
		if(selectedId) {
			var $img = $("#"+selectedId);
			var url = $img.attr("src"), 
				name = $img.attr("alt"), 
				id = $img.attr("id").substring(5); //"food_${id}"

			$("#foodId").val(id);
			$("#addfood-photo").attr("src", url).attr("alt", name);
			$("#foodName").val(name);
			
			// get food detail
			$.getJSON(webRoot+"/shop/food/"+id, function (json) {
				$("#detail").val(json['detail']);
			});
		}
	}

	function deleteFood(foodId) {
		var url = webRoot + "/shop/foodreshop/" + foodId;
		$.ajax({
			url: url, 
			type: "delete"
		})
		.done(function() {
			var $item = $("#item_" + foodId);
			$item.fadeOut(function() {
				$item.remove();
			});
		})
		.fail(function(jqXHR) {
			alert("删除失败. " + jqXHR.responseText);
		});
	}

	function editFood(foodId) {
		showModal("edit");
	}


	jQuery(function ($) {

		Init.run();

	});
	
	
});