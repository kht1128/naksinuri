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

	/* movTop */
	$(window).scroll(function () {			
		if ($(this).scrollTop() > 120) {
			$('#header').addClass("scrollOn");
		} else {
			$('#header').removeClass("scrollOn");
		}
		
		var targetPercentage = 120;
		var scrollPercent = ($(window).scrollTop()/ ($(document).height()-$(window).height())) * 100;
		
		$("#scrollPercentCounter").css({width:scrollPercent+"%"});
	});

	$(document).ready(function(){
		init_unit()

		// 상단 전체메뉴
		$('.allMnuBtn').click(function(){
			$('#allMnuBox').attr('class','on');
			$('.allMnuBtn').addClass('on');
		});

		$('#allMnuBox, #headSearch').mouseleave(function(){
			$('#allMnuBox').attr('class','');
			$('.allMnuBtn').removeClass('on');
			$('#headSearch').attr('class','');
			$('.allSearchBtn').removeClass('on');
		});

		$('#gnbArea #gnb_1dul, .allSearchBtn').mouseenter(function(){
			$('#allMnuBox').attr('class','');
			$('.allMnuBtn').removeClass('on');
		});

		// 상단 통합검색
		$('.allSearchBtn').click(function(){
			$('#headSearch').attr('class','on');
			$('.allSearchBtn').addClass('on');
		});

		/*
		$('#gnbArea #gnb_1dul, .allMnuBtn').mouseenter(function(){
			$('#headSearch').attr('class','');
			$('.allSearchBtn').removeClass('on');
		});
		*/
		
		// menu icon states, opening main nav
		$('#menu-icon').click(function(){
			$(this).toggleClass('open');
			$('#m-nav,#menu-toggle,#page-content,#menu-overlay').toggleClass('open');
			$('#m-nav ul li,.submenu-toggle').removeClass('open');
		});

		// clicking on overlay closes menu
		$('#menu-overlay,#close-toggle').mousedown(function(){
			$('*').removeClass('open');
		});

		// menu icon states, opening main nav
		$('#subGnb dl dt a').click(function(){
			$('#subGnb').toggleClass('open');
		});

		// menu icon states, opening main nav
		$('.tabArea2 dl dt a').click(function(){
			$('.tabArea2').toggleClass('open');
		});
		
		// GNB
		var menu, menuLi, menuLink;
		menu = $("#m-gnb");
		menuLi = $("#m-gnb > ul > li");
		menuLink = $("#m-gnb > ul > li > .depth1 > a.menu");
		menuLink.on("click", function(){
			if($(this).parents("li").hasClass("on")){
				$(this).parents("li:has(ul)").children("ul").slideUp(250);
				$(this).parents("li:has(ul)").removeClass("on");
			}else{
				menuLi.not($(this).parents("li")).children("ul").slideUp(250);
				menuLi.not($(this).parents("li")).removeClass("on");
				$(this).parents("li:has(ul)").children("ul").slideDown(250);
				$(this).parents("li:has(ul)").addClass("on");
			}
			return false;
		});
		
		$('a.pagelink').click(function(){
			$('html, body').animate({
				scrollTop: $( $(this).attr('href') ).offset().top - 100
			}, 500);
			return false;
		});
	});
})(jQuery)

/*
$(function() {
	$(".date_input").datepicker();
});
*/