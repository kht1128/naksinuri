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
		init_unit();

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
				$(this).parents("li:has(ul)").children("ul").slideUp(400);
				$(this).parents("li:has(ul)").removeClass("on");
			}else{
				menuLi.not($(this).parents("li")).children("ul").slideUp(400);
				menuLi.not($(this).parents("li")).removeClass("on");
				$(this).parents("li:has(ul)").children("ul").slideDown(400);
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


function isEmpty(value){ 
   if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){ 
      return true 
   }else{ 
      return false 
   } 
}

$(function(){
	// 메뉴 접으면 변경되는
	$(".mapLeftMenu .btn_fold").click(function(){
		$(this).toggleClass("open");
		var text = $(this).text();
		$(".mapLeftMenu .btn_fold").text(text == "정보창 접기" ? "정보창 열기" : "정보창 접기");
		$(".mapLeftMenu").toggleClass("on");
		if ($('.mapLeftMenu').hasClass("on")) { // 메뉴 접었을 때 
			$(".mapCateCom").text("중복 선택 가능"); // 
			$("input#searchText1").attr("placeholder", "어종 입력");
			$("input#co_nm").attr("placeholder", "상호명");
			$("input#san_name").attr("placeholder", "상호명");
			$(".mapComBox .searchCon .searchBtn").text("검색");
			//$(".jsWebsite").text("홈페이지 여부");
	    } else { // 메뉴 펼쳤을 때
	    	$(".mapCateCom").text("중복 선택이 가능합니다.");
	    	$("input#searchText1").attr("placeholder", "주요 어종을 입력 해주세요. 예) 붕어, 잉어");
	    	$("input#co_nm").attr("placeholder", "상호명을 입력해주세요.");
	    	$("input#san_name").attr("placeholder", "상호명을 입력해주세요.");
	    	$(".mapComBox .searchCon .searchBtn").text("검색하기");
	    	//$(".jsWebsite").text("자체 홈페이지 여부");
	    } if ($('.mapLeftMenu').hasClass("mbShow")) { // 메뉴 접었을 때 
			$(".mapCateCom").text("중복 선택 가능"); // 
			$("input#searchText1").attr("placeholder", "어종 입력");
			$("input#co_nm").attr("placeholder", "상호명");
			$("input#san_name").attr("placeholder", "상호명");
			$(".mapComBox .searchCon .searchBtn").text("검색");
			//$(".jsWebsite").text("홈페이지 여부");
	    }
	});
	
	// 마커 클릭 시 상세 정보 열기
	/*$(".marker").click(function(){
        if ($(".infoCon2").css("display") == "none") {
            $(".infoCon2").css("display", "block");
            $(".infoCon2").removeClass("fold");
            $(".infoCon2").removeClass("open");
        } else {
            $(".infoCon2").css("display", "block");
            $(".infoCon2").removeClass("fold");
            $(".infoCon2").removeClass("open");
        }
	});*/
	
	// 상세 정보 상단 클릭 시 위로 올림 
	$(".infoCon2 .mbBtnFold").click(function(){
		$(".infoCon2").toggleClass("open");
		if ($(".infoCon2").hasClass("open")) {
            $(".infoCon2").removeClass("fold");
        } else {
        	$(".infoCon2").addClass("fold");
        }
	});
	
	
	// 모바일에서 변경되는
	$(window).on("resize", function (e) {
	    checkScreenSize();
	});
	
	checkScreenSize();
	
	function checkScreenSize(){
	    var newWindowWidth = $(window).width();
	    if (newWindowWidth <= 767) {
	    	var text = $(".mapLeftMenu .btn_fold").text();
			$(".mapLeftMenu .btn_fold").text(text == "정보창 접기" ? "정보창 열기" : "정보창 접기");
			
	    	$(".mapLeftMenu").addClass("mbShow");
	    	$(".mapLeftMenu").addClass("on");
    		$(".mapCateCom").text("중복 선택 가능");  
    		$("input#searchText1").attr("placeholder", "어종 입력");
    		$("input#co_nm").attr("placeholder", "상호명");
    		$("input#san_name").attr("placeholder", "상호명");
    		$(".mapComBox .searchCon .searchBtn").text("검색");
    		//$(".jsWebsite").text("홈페이지 여부");
	        
	    } else {
	    	$(".mapLeftMenu").removeClass("mbShow");
	    	$(".mapLeftMenu").removeClass("on");
    		$(".mapCateCom").text("중복 선택이 가능합니다.");
        	$("input#searchText1").attr("placeholder", "주요 어종을 입력 해주세요. 예) 붕어, 잉어");
        	$("input#co_nm").attr("placeholder", "상호명을 입력해주세요.");
        	$("input#san_name").attr("placeholder", "상호명을 입력해주세요.");
        	$(".mapComBox .searchCon .searchBtn").text("검색하기");
        	//$(".jsWebsite").text("자체 홈페이지 여부");
	    }
	}
	
	// 낚시터정보, 낚시산업정보 탭 메뉴
	//$(".mapTabCon > div").hide();
	$('.mapTabMenu a').click(function(){
	    $('.mapTabCon > div').filter(this.hash).fadeIn();
	    $('.mapTabMenu a').removeClass('active');
	    $(this).addClass('active');
	    return false;
	}).filter(':eq(0)').click();
});

$(function(){
	// 상단 이동 버튼
	$(window).scroll(function() {
        if ($(this).scrollTop() > 300) {
            $('#top_btn').fadeIn();
        } else {
            $('#top_btn').fadeOut();
        }
    });
    $("#top_btn").click(function() {
        $('html, body').scrollTo(0,0);
        return false;
    });
    
    // 웹접근성 추가
    $(function () {
        $("#skipnavigation a").on("click", function(){
            var headerHeight = $("header").outerHeight();
            var href = $(this).attr("href");
            var target = $(href == "#" || href == "" ? "body" : href);
            var position = target.offset().top - headerHeight;
            $("html, body").animate({ scrollTop: position }, 100, "swing");
        });
    });
    
    // ##2022.11.22 모달 팝업창 웹접근성 추가##
    // 모달이 보여질때 이벤트
	$('#allPublicModalMessage').on('shown.bs.modal', function (e) {
		$("#allPublicModalMessage").attr("tabindex", 0).show().focus();
	});
    // 모달 닫을때 이벤트
    $('#allPublicModalMessage').on('hidden.bs.modal', function (e) {
    	$(".login_submit").focus(); //낚시전문교육
    	$(".last_con a").focus(); //인트로
    });

    
    // ##2022.11.23 검색바 감싸는 태그 추가##
    $('.list_searchbox .basic_select, .list_searchbox .basic_input, .list_searchbox .searchBtn').wrapAll('<div class="search_group"></div>');
    $('.list_searchbox .change_type').wrapAll('<div class="change_group"></div>');
    
    //모바일 th갯수에 colspan 맞추기 
    var $len = $(".list_tbl thead th").filter(function() {
    	return $(this).css('display') != 'none';
    });
    $(".list_tbl tbody td.noData").attr("colspan", $len.length);
    
    //웹접근성 추가
    /*$('.mapComBox .maptabMenu ul li a').click(function() {
		var text = $(this).text();
		if($(this).hasClass("active")){
			$(this).attr('title',text+' 선택됨');
        } else {
        	$(this).attr('title',text);
        }
	});*/
    
    // ##2022.12.20 모바일 뷰페이지, faq_list 테이블 감싸는 태그 추가##
    $('#boardView #viewContent .content table').wrapAll('<div class="respon_table"></div>');
    $('.faq_list .faq_con dl dd table').wrapAll('<div class="respon_table"></div>');
    // 모바일 뷰페이지 동영상 감싸는 태그 추가
    $('#boardView #viewContent .content iframe').addClass('video');
    $('#boardView #viewContent .content iframe').wrapAll('<div class="video_wrap"></div>');
});


/* 데이터트리 수정진행 GNB*/
/*$(document).ready(function(){
	$(".gnb_2dul").hide();
	$('#gnb_1dul > .gnb_1dli').mouseenter(function(){
		$(this).find(".gnb_2dul").show();
	});
	$('#gnb_1dul > .gnb_1dli').mouseleave(function(){
		$(this).find(".gnb_2dul").hide();
	});
});

$(document).ready(function(){
	$('.gnb_1da').focus(function(){
		$(this).next(".gnb_2dul").show();
	});
	$('.gnb_2dli:last-child a').blur(function(){
		$('.gnb_2dul').hide();
	});
});*/

/* 데이터트리 수정진행 새로운 소식 */

$(document).ready(function(){
	for (let i=0; i<$('.notice_list_tap ul li').length; i++){
		$('.notice_list_cont').eq(0).css('display','block');
		$('.notice_list_tap ul li').eq(i).on("click",function(){
		$('.notice_list_cont').eq(i).css('display','block');
		$('.notice_list_cont').eq(i).siblings().css('display','none');
	 });
	}
});


