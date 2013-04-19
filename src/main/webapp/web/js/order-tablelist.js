
/**
 * table list
 * @param  {[type]} $ [description]
 * @return {[type]}   [description]
 */

var TableList = {

	/**
	 * click event
	 * @return {[type]} [description]
	 */
	bindClickEvent: function() {
		$(document).on("click", ".table-list > ul > li", function (e) {
			var $this = $(this), 
				// $tableItem = $this.closest("div.table-item"), 
				$tableItem = $("div.table-item", $this), 
				expand = $tableItem.hasClass("expand");

			if(!expand) {
				$tableItem.addClass("expand");
				$("div.food-collapse", $tableItem).hide();
				$("div.food-expand", $tableItem).slideDown(function() {
					$(document).trigger("heightchange");
				});
			} else {
				$tableItem.removeClass("expand");
				$("div.food-expand", $tableItem).slideUp(function () {
					$("div.food-collapse", $tableItem).show();
					$(document).trigger("heightchange");
				});
			}
		});
		return this;
	}, 

	/**
	 * set interval color
	 * @return {[type]} [description]
	 */
	intervalBgColor: function() {
		// $(".table-list > ul > li:odd").css("background-color", "#F2F2F2");
		$(".table-list > ul > li:odd").addClass( "bg-grey" );
		return this;
	}, 

	resetBgColor: function() {
		$(".table-list > ul > li").removeClass( "bg-grey" );
		return this;
	}, 

	/**
	 * Dom ready
	 * @return {[type]} [description]
	 */
	ready: function () {
		this.bindClickEvent().intervalBgColor();
	}
};

jQuery(function ($) {
	
	TableList.ready();
});

