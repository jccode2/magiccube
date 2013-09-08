/**
 * This is part of charisma js. 
 */
;(function($) {
	
	// dom ready
	$(function() {
		
		// for box
		$('.btn-close').click(function(e){
			e.preventDefault();
			$(this).parent().parent().parent().fadeOut();
		});
		
		$('.btn-minimize').click(function(e){
			e.preventDefault();
			var $target = $(this).parent().parent().next('.box-content');
			if($target.is(':visible')) $('i',$(this)).removeClass('icon-chevron-up').addClass('icon-chevron-down');
			else 					   $('i',$(this)).removeClass('icon-chevron-down').addClass('icon-chevron-up');
			$target.slideToggle();
		});
		
	});
	
})(jQuery);