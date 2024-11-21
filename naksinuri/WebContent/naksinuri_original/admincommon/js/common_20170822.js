(function($){
	function init_unit() {
		$(".tab_unit").css("display","none");
		if (!$('ul.unit_tabs').length) { return; }
		$('div.tab_unit_wrap').each(function() {
			$(this).find('div.tab_unit:first').show();
		});
		$('ul.unit_tabs a').click(function() {
			if (!$(this).hasClass('current')) {
				$(this).addClass('current').parent('li').siblings('li').find('a.current').removeClass('current');
				$($(this).attr('title')).show().siblings('div.tab_unit').hide();
			}
			this.blur();
			return false;
		});
	}

	$(document).ready(function(){
		init_unit()
		
		$('a.pagelink').click(function(){
			$('html, body').animate({
				scrollTop: $( $(this).attr('href') ).offset().top
			}, 500);
			return false;
		});
		
		/* movTop */
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#header').addClass("scrollOn");
			} else {
				$('#header').removeClass("scrollOn");
			}

			/*
			if ($(this).scrollTop() > 130) { 
				$('#movTopBtn').css('bottom','20px');
			} else {
				$('#movTopBtn').css('bottom','-150px');
			}
			*/
		});
	});
})(jQuery)

/*
$(function() {
	$(".date_input").datepicker();
});
*/