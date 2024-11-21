(function($){
	$(document).ready(function(){
		var menu, menuLi, menuLink;
		menu = $("#gnb");
		menuLi = $("#gnb .sub-menu");
		menuLink = $("#gnb .sub-menu > a");
		menuLink.click(function(e) {
			$("#gnb ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
			e.stopPropagation();
			
			menuLi.not($(this).parent("li")).removeClass("active");
			$(this).parent("li:has(ul)").addClass("active");
		});

		// ���� > �α��� ��ư
		$('.organi_add').click(function(){
			$('*').removeClass('open');
			$('.layerpop,#menu-overlay').toggleClass('open');
		});

		// �˾� �ݱ� 
		$('#menu-overlay,#close-toggle,.btn_close').mousedown(function(){
			$('*').removeClass('open');
		});
	});
})(jQuery)

$(function() {
	$(".date_input").datepicker({
		dateFormat: "yy-mm-dd",
		changeMonth: true,
	    changeYear: true
	});
});