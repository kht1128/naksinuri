(function($){
	
	function latest_tabs() {
		$(".tab_latest").css("display","none");
		if (!$('ul.latest_tabs').length) { return; }
		$('div.tab_latest_wrap').each(function() {
			$(this).find('div.tab_latest:first').fadeIn();
		});
		$('ul.latest_tabs a').click(function() {
			if (!$(this).hasClass('choice')) {
				$(this).addClass('choice').parent('li').siblings('li').find('a.choice').removeClass('choice');
				$($(this).attr('title')).fadeIn().siblings('div.tab_latest').hide();
			}
			this.blur();
			return false;
		});
	}

	$(function() {
		$(".datepicker").datepicker({
			closeText: '닫기',
			prevText: '이전달',
			nextText: '다음달',
			currentText: '오늘',
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			dateFormat:'yy-mm-dd',
			yearRange: 'c-10:c+5',
			weekHeader: 'Wk',
			isRTL: false,
			showButtonPanel: true,
			buttonImageOnly: true,
			showMonthAfterYear: true ,
			changeMonth: true,
			changeYear: true
		});
	});
	$(function() {
		$(".datepickerBirth").datepicker({
			closeText: '닫기',
			prevText: '이전달',
			nextText: '다음달',
			currentText: '오늘',
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			dateFormat:'yy-mm-dd',
			yearRange: 'c-100:c',
			weekHeader: 'Wk',
			isRTL: false,
			showButtonPanel: true,
			buttonImageOnly: true,
			showMonthAfterYear: true ,
			changeMonth: true,
			maxDate: 0,
			changeYear: true
		});
	});
	$(function() {
		$(".datepickerReserve").datepicker({
			closeText: '닫기',
			prevText: '이전달',
			nextText: '다음달',
			currentText: '오늘',
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			dateFormat:'yy-mm-dd',
			yearRange: 'c:c+2',
			weekHeader: 'Wk',
			isRTL: false,
			showButtonPanel: true,
			buttonImageOnly: true,
			showMonthAfterYear: true ,
			changeMonth: true,
			minDate: 0,
			changeYear: true
		});
	});	
	
	$(document).ready(function(){
		latest_tabs()

		$('#gnb').mouseover(function(){
			$('#header').addClass('open');
		});

		$('#gnb').mouseout(function(){
			$('#header').removeClass('open');
		});

		// menu icon states, opening main nav
		$('#menu-icon, #nav a').click(function(){
			$(".mnubtn").toggleClass('open');
			$('#header').toggleClass('open');
			$('#m-nav').toggleClass('open');
		});

		// toggle child menus
		$('.submenu-toggle').click(function(){
			var currentToggle=$(this);
			currentToggle.parent().toggleClass('open');
			currentToggle.toggleClass('open');
		});

		$('a.pagelink').click(function(){
			$('html, body').animate({
				scrollTop: $( $(this).attr('href') ).offset().top
			}, 500);
			return false;
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

		/* movTop */
		$(window).scroll(function () {
			if ($(this).scrollTop() > 80) {
				$('#header').addClass("scrollOn");
				$('.toparea').addClass("scrollOn");
			} else {
				$('#header').removeClass("scrollOn");
				$('.toparea').removeClass("scrollOn");
			}
		});

	});
})(jQuery)

function loginChk(obj) {
	alert('로그인이 필요한 서비스입니다.');
	if(!obj) {
		location.href="/member/login.do";
	} else {
		location.href="/m/member/login.do";
	}
}
function tel(obj) {
	location.href="tel:"+obj;
	return false;
}
function work() {
	alert('기능을 준비중입니다.');
	return false;
}
