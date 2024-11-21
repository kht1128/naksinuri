<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<%@include file="head.sub.jsp"%>

<jsp:include page="/alert/popup.do" />

<!-- 전역 알림 메세지 출력 { -->
<script>
$(document).ready(function(){
	setTimeout(function(){
		var alert_data_type = '${alert_data.type}';
		var message = '${alert_data.message}';
		var title = '${alert_data.title}';
		var closebtn = '${alert_data.closebtn}';
		if(alert_data_type!=null && alert_data_type.length!=0 && alert_data_type == 'alert' ) {
			if(closebtn==null || closebtn.length==0) {
				closebtn = "확인";
			}
			if(message!=null && message.length!=0) {
				alert(message);
			}
		} else if(alert_data_type!=null && alert_data_type.length!=0 && alert_data_type == 'modal-lock') {
			
			if(message!=null && message.length!=0) {
				$("#allPublicModalMessageLock .modal-message").html(message);
				$("#allPublicModalMessageLock").modal({
					backdrop: 'static',
				    keyboard: false
				});
				
				$("#resmpt").on("click", function(){
					var resmpt = confirm("이 교육은 20.2.21 이후 최초 신고 및 안전사고로 영업정지명령을 받은 후 재개하려는 낚시어선업자가 수강 대상입니다.\n귀하는 수강 대상이 맞습니까?");
					if(resmpt) $(this).attr('href', '/educenter/m/rmndr/agreeResmpt.do');
				});
			}
			
		} else {
			if(message!=null && message.length!=0) {
				//$("#allPublicModalMessage .modal-title").html('알림');
				$("#allPublicModalMessage .modal-message").html(message);
				$("#allPublicModalMessage").modal('show');	
			}
		}
	},500);
});
function allPublicModalMessage(msg) {
	$("#allPublicModalMessage .modal-message").html(msg);
	$("#allPublicModalMessage").modal('show');
}
</script>	
<!-- } 전역 알림 메세지 출력 -->

<!-- 최상단 배너 { -->
<c:set var="isbanner" value="N" />
<c:if test="${pageMode eq 'indexpage'}">	
	
	
	<!-- 최상단 배너 { -->
	<%-- <c:if test="${fn:length(list_banner_top) ne 0}">
		<c:set var="isbanner" value="Y" />
		<div id="topBanner">
			<style>
			.topCloseBtn{
				position: absolute;
			    top: 30px;
			    right: 0;
			}
			</style>
			<c:set var="loop_flag" value="false" />
			<c:forEach var="head_img" items="${list_banner_top}" varStatus="status">
				
				<c:if test="${not loop_flag}">
					<c:choose>
			           <c:when test="${status.count eq 2}">
			             원하는 결과가 나오면 true로 선언 : for문의 break 효과
			             <c:set var="loop_flag" value="true" />
			           </c:when>
			           
			           <c:otherwise>
			             
			             <div class="${popupAreaClassName} ${status.count}" id="popupArea_${head_img.PP_SN}">					
							<a 	<c:if test="${not empty head_img.PP_LINK_URL}">
								href="${head_img.PP_LINK_URL}" target="${head_img.PP_LINK_TARGET}"
								</c:if> 
								<c:if test="${empty head_img.PP_LINK_URL}">
								href="#;"
								</c:if> 
								style="background:url('<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${head_img.PP_FILE}"/>&fileSn=0') no-repeat center top">
								<img src="/cmm/fms/getImage.do?atchFileId=${head_img.PP_FILE}&fileSn=0" style="height: 100%;">
							</a>
						
							<div class="topCloseBtn">
							
								<span class="pr-20">
									<input type="checkbox" class="popupAreaClose" id="popupClose_${head_img.PP_SN}" />
									<label for="popupClose_${head_img.PP_SN}" style="color:#000;">
										<span></span>오늘 하루 열지 않기
									</label>
								</span>
								
								<span class="btnClose" data-idnum="${head_img.PP_SN}" style="width: 25px; height: 25px;">
									<img src="/naksinuri_original/common_main/img/top_ico_close.png" alt="닫기" />
								</span>	
								
							</div>
						</div>
			           </c:otherwise>
			           
			        </c:choose>
				</c:if>
				
			
			<script>
			$('document').ready(function(){
				var tidnum = "${head_img.PP_SN}";
				if(getCookie("notToday_"+tidnum)!="Y"){
					$("#popupArea_"+tidnum).show();
				}else{
					$("#popupArea_"+tidnum).hide();
				}
				
				$(".popupArea").draggable();
			});
			</script>
			
			</c:forEach>
		</div>
	</c:if> --%>
	<script>
				
	$('#topBanner .btnClose').click(function(){
		//$('#topBanner').slideUp();
		
		var tidnum = $(this).attr('data-idnum');
		if($('#popupClose_'+tidnum).prop("checked")){
			setCookie('notToday_'+tidnum,'Y', 1);
			$("#popupArea_"+tidnum).hide('fade');
		}else{
			$("#popupArea_"+tidnum).hide('fade');
		}
		
	});
		
	function setCookie(name, value, expiredays) {
		var today = new Date();
	    today.setDate(today.getDate() + expiredays);
	    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
	}
	function getCookie(name) { 
		var cName = name + "="; 
		var x = 0; 
		while ( x <= document.cookie.length ) 
		{ 
		    var y = (x+cName.length); 
		    if ( document.cookie.substring( x, y ) == cName ) 
		    { 
		        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
		            endOfCookie = document.cookie.length;
		        return unescape( document.cookie.substring( y, endOfCookie ) ); 
		    } 
		    x = document.cookie.indexOf( " ", x ) + 1;
		    
		    if ( x == 0 ) 
		        break; 
		} 
		return ""; 
	}
	</script>
	
		
	<%-- <c:if test="${fn:length(head_list) ne 0}">
		<c:set var="isbanner" value="Y" />
		<div id="topBanner">
			<c:forEach var="head_img" items="${head_list}">
				<a href="${head_img.img_link}" target="_blank" style="background:url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${head_img.m_img_cont}"/>&fileSn=<c:out value="${head_img.file_sn}"/>') no-repeat center top;background-size: 100% auto;"></a>
			</c:forEach>
			<div class="btnClose"><a href="#;"><img src="/naksinuri_original/common_main/img/ico_close.png" alt="닫기" /></a></div>
		</div>
	</c:if>
	<script>
		(function($){
			$(document).ready(function(){
				$('#topBanner .btnClose').click(function(){
					$('#topBanner').slideUp();
					$('#wrapper').css('padding-top','100px');
					$('#header').css('top','0px');
				});
			});
		})(jQuery)
	</script> --%>
	
	
	
</c:if>
<!-- } 최상단 배너 -->

<c:if test="${isbanner eq 'Y'}">
<div id="wrapper" class="withBanner">
</c:if>
<c:if test="${isbanner eq 'N'}">
<div id="wrapper" class="">
</c:if>


	<!-- 상단헤더 { -->
	<header id="header">
		
		<h1><a href="/m/index.do"><img src="/naksinuri_original/common_main/img/logo.png" alt="낚시누리" /></a></h1>
		<a href="#;" id="menu-icon"><i class="fa fa-bars"></i></a>
		<a href="#;" onclick="changePc()" id="pc-icon"><i class="fa fa-television" aria-hidden="true"></i> PC</a>
		<section id="mainSearchBox">
		 	<form id="search_form" name="search_form" method="post">
				<input type="text" class="main_search_input" placeholder="검색어를 입력해주세요" id="searchText" name="searchText" value="${searchText}"/>
				<button class="search_button" onclick="go_search()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</form>
		</section>
	</header>
	<!-- } 상단헤더 -->

	<c:if test="${pageMode ne 'indexpage' }">
	<div id="container">
		<c:if test="${not empty menuNum or depthNum ne '7'}">
			<nav id="subGnb" class="sub_gnb<c:out value="${depthNum }"></c:out><c:out value="${menuNum }"></c:out>">
				<div id="divmenu" class="link_select">
					<dl>
					
						<dt><a href="#;" id="select_pageName"><c:out value="${pageName}"></c:out><span><i class="fa fa-angle-down" aria-hidden="true"></i></span></a></dt>
						
						<dd>
							<!-- // 사이드 메뉴는 tail.jsp 에서 사용중. // -->
							<ul id="menu">
								<c:choose>
									<c:when test="${depthNum eq '8' }">
										<c:set var="depthName" value="낚시안전 디자인 공모전"></c:set>															
										<li <c:if test="${pageName eq 'mof'}">class="select"</c:if>><a href="/gongmo/mof/m/intro.do">해양수산부 소개</a></li>
										<li <c:if test="${pageName eq 'check'}">class="select"</c:if>><a href="/gongmo/gongmo/m/check.do">공모전 접수/신청</a></li>						
										<li <c:if test="${pageName eq 'event'}">class="select"</c:if>><a href="/gongmo/event/m/list.do">커뮤니티</a></li>
										<%-- 아래는 노출되지 않음 노출이 필요하면 mobile.css #container #subGnb.sub_gnb 수정 필요 --%>
										<li <c:if test="${pageName eq 'gm'}">class="select"</c:if>><a href="/gongmo/gm/m/intro.do">공모전 소개</a></li> 
										<li <c:if test="${pageName eq 'summary'}">class="select"</c:if>><a href="/gongmo/summary/m/intro.do">공모전 요강</a></li> 
										<li <c:if test="${pageName eq 'mbrwrite'}">class="select"</c:if>><a href="/gongmo/gongmo/m/check.do">공모전 신청서 작성하기</a></li> 
										<li <c:if test="${pageName eq 'search'}">class="select"</c:if>><a href="/gongmo/gongmo/m/search.do">공모전 접수확인</a></li> 
										<li <c:if test="${pageName eq 'mbrconfirm'}">class="select"</c:if>><a href="/gongmo/gongmo/m/check.do">공모전 접수조회</a></li> 
									</c:when>
									<c:when test="${depthNum eq '0' }">
										<c:set var="depthName" value="사이트이용안내"></c:set>															
										<li <c:if test="${pageName eq 'customer_sound' }"> class="select" </c:if>><a href="/policy/m/customer_sound.do">고객의 소리</a></li>
										<li <c:if test="${pageName eq 'privacy' }"> class="select" </c:if>><a href="/policy/m/privacy.do">개인정보처리방침</a></li>
										<li <c:if test="${pageName eq 'copyright' }"> class="select" </c:if>><a href="/policy/m/copyright.do">저작권보호정책</a></li>
										<li <c:if test="${pageName eq 'agree' }"> class="select"</c:if>><a href="/policy/m/agree.do">이용약관</a></li>
										<li <c:if test="${pageName eq 'survey' }"> class="select"</c:if>><a href="/survey/survey/m/list.do">설문조사</a></li>
									</c:when>
									<c:when test="${depthNum eq '1' }">
										<c:set var="depthName" value="낚시교실"></c:set>												
										<%-- <li <c:if test="${pageName eq 'dignity' }"> class="select" </c:if>><a href="/lesson/dignity/m/list.do">낚시의 품격</a></li> --%>
										<li <c:if test="${pageName eq 'junior' }"> class="select" </c:if>><a href="/lesson/junior/m/list.do">초보탈출하기</a></li>
										<li <c:if test="${pageName eq 'gosu' }"> class="select" </c:if>><a href="/lesson/gosu/m/list.do">낚시고수되기</a></li>
										<li <c:if test="${pageName eq 'sense' }"> class="select" </c:if>><a href="/lesson/sense/m/list.do">낚시상식</a></li>
										<li <c:if test="${pageName eq 'binding' }"> class="select"</c:if>><a href="/lesson/binding/m/list.do">채비필수묶음법</a></li>
										<li <c:if test="${pageName eq 'class' }"> class="select" </c:if>><a href="/lesson/class/m/list.do">어종별낚시교실</a></li>
									</c:when>
									<c:when test="${depthNum eq '2' }">
										<c:set var="depthName" value="낚시정보"></c:set>
										<%-- <li <c:if test="${pageName eq 'fishjob' }"> class="select" </c:if>><a href="/info/fishjob/m/list.do">낚시터 정보</a></li> --%>
										<%-- <li <c:if test="${pageName eq 'industry' }"> class="select" </c:if>><a href="/info/industry/m/list.do">낚시산업정보</a></li> --%>
										<li <c:if test="${pageName eq 'angling' }"> class="select" </c:if>><a href="/info/angling/m/list.do">조황정보</a></li>
										<li <c:if test="${pageName eq 'lab' }"> class="select" </c:if>><a href="/sosig/lab/m/list.do">낚시연구소</a></li>
	 									<%-- <li <c:if test="${pageName eq 'fishCompany' }"> class="select" </c:if>><a href="/info/fishCompany/list.do">낚시컴퍼니정보</a></li> --%>
	 									<%-- <li <c:if test="${pageName eq 'fishFshtbSttus' }"> class="select" </c:if>><a href="/info/fishFshtbSttus/list.do">낚시어선업현황</a></li> --%>
	 									<%-- <li <c:if test="${pageName eq 'oceanFshIdex' }"> class="select" </c:if>><a href="/info/oceanFshIdex/list.do">바다낚시지수정보</a></li> --%>
	 									<%-- <li <c:if test="${pageName eq 'safe' }"> class="select" </c:if>><a href="/info/safe/m/list.do">낚시안전</a></li> --%> 
									</c:when>
									<c:when test="${depthNum eq '3' }">
										<c:set var="depthName" value="커뮤니티"></c:set>
										<li <c:if test="${pageName eq 'travel' }"> class="select"</c:if>><a href="/share/travel/m/list.do">낚시여행기</a></li>
	<%-- 									<li <c:if test="${pageName eq 'zisik'}"> class="select"</c:if>><a href="/share/zisik/m/list.do">누리지식인</a></li> --%>
										<li <c:if test="${pageName eq 'nuri'}"> class="select"</c:if>><a href="/share/nuri/m/list.do">자주묻는 낚시질문</a></li>
										<li <c:if test="${pageName eq 'usage' }"> class="select"</c:if>><a href="/share/usage/m/list.do">낚시용품 사용기</a></li>
										<li <c:if test="${pageName eq 'column' }"> class="select"</c:if>><a href="/share/column/m/list.do">낚시칼럼</a></li>
										<li <c:if test="${pageName eq 'survey' }"> class="select"</c:if>><a href="/survey/survey/m/list.do">설문조사</a></li>
									</c:when>
									<c:when test="${depthNum eq '4' }">
										<c:set var="depthName" value="낚시소식"></c:set>
										<%-- <li <c:if test="${pageName eq 'news' }"> class="select" </c:if>><a href="/sosig/news/m/list.do">낚시뉴스</a></li> --%>
										<li <c:if test="${pageName eq 'notice' }"> class="select" </c:if>><a href="/sosig/notice/m/list.do">공지사항</a></li>
										<li <c:if test="${pageName eq 'congress' }"> class="select"</c:if>><a href="/sosig/congress/m/list.do">낚시대회</a></li>
										<li <c:if test="${pageName eq 'congressregit' }"> class="select"</c:if>><a href="/sosig/congress/m/write.do">낚시대회 등록</a></li>
										<li <c:if test="${pageName eq 'congresscheck' }"> class="select"</c:if>><a href="/sosig/congress/m/check.do">낚시대회 등록 내역조회</a></li>
										<li <c:if test="${pageName eq 'congressmbrcheck' }"> class="select"</c:if>><a href="/sosig/congress/m/mbrcheck.do">낚시대회 참가자 내역조회</a></li>
										<li <c:if test="${pageName eq 'congressmbrwrite' }"> class="select"</c:if>><a href="#;">참가신청서 작성하기</a></li>
										<li <c:if test="${pageName eq 'congressmbrsearch' }"> class="select"</c:if>><a href="#;">낚시대회 참가자 내역조회</a></li>
										<li <c:if test="${pageName eq 'event' }"> class="select" </c:if>><a href="/sosig/event/m/list.do">이벤트</a></li>
									</c:when>
									<c:when test="${depthNum eq '5' }">
										<c:set var="depthName" value="낚시정책"></c:set>
										<li <c:if test="${pageName eq 'info' }"> class="select"</c:if>><a href="/promotion/info/m/list.do">알림마당</a></li>
										<li <c:if test="${pageName eq 'policy' }"> class="select"</c:if>><a href="/promotion/policy/m/list.do">낚시정책안내</a></li>
										<li <c:if test="${pageName eq 'campaign' }"> class="select"</c:if>><a href="/promotion/campaign/m/list.do">낚시캠페인</a></li>
										<%-- <li <c:if test="${pageName eq 'plocation' }"> class="select"</c:if>><a href="/promotion/plocation/m/list.do">낚시금지구역</a></li> --%>
										<li <c:if test="${pageName eq 'qna' }"> class="select"</c:if>><a href="/promotion/qna/m/list.do">낚시법 및 유어장 관련 질의 사례</a></li>
										<li <c:if test="${pageName eq 'gosi' }"> class="select" </c:if>><a href="/sosig/gosi/m/list.do">지역별 낚시준수사항</a></li>
										<li <c:if test="${pageName eq 'auditor' }"> class="select"</c:if>><a href="/promotion/auditor/m/list.do">낚시명예감시원</a></li>
									</c:when>
									<c:when test="${menuNum eq '10' and urlcontroller eq 'educenter'}">
										<li ><a href="/educenter/m/index.do">낚시전문교육 메인화면</a></li>
										<li ><a href="/educenter/m/trnng/list.do">교육신청</a></li>
										<li ><a href="/educenter/m/trnngoffline/fshlcList.do">오프라인 교육신청(낚시터)</a></li>
										<li ><a href="/educenter/m/trnngoffline/fshhpList.do">오프라인 교육신청(낚시어선)</a></li>
										<li ><a href="/educenter/m/mbrhstry/list.do">온라인강의/이수증내역</a></li>
										<li ><a href="/educenter/m/board/notice/list.do">공지사항</a></li>
										<li ><a href="/educenter/m/main/serviceGuide.do">교육안내1111</a></li>
										<li ><a href="https://www.113366.com/naksinuri">원격지원서비스</a></li>
										<%-- 아래는 노출되지 않음 노출이 필요하면 mobile.css #container #subGnb.sub_gnb 수정 필요 --%>
										<li <c:if test="${urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'list'}">class="select"</c:if>><a href="/educenter/m/trnng/list.do">온라인 교육일정</a></li>
										<li <c:if test="${urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshlcList'}">class="select"</c:if>><a href="/educenter/m/trnng/list.do">오프라인 교육신청(낚시터)</a></li>
										<li <c:if test="${urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshhpList'}">class="select"</c:if>><a href="/educenter/m/trnng/list.do">오프라인 교육신청(낚시어선)</a></li>
										<li <c:if test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list'}">class="select"</c:if>><a href="/educenter/m/mbrhstry/list.do">나의신청내역</a></li>
										<li <c:if test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_crtf'}">class="select"</c:if>><a href="/educenter/m/mbrhstry/listCrtf.do">이수증내역</a></li>
										<li <c:if test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_crtf_dtl'}">class="select"</c:if>><a href="/educenter/m/mbrhstry/listCrtfDtl.do">이수증내역(발급)</a></li>
										<li <c:if test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">class="select"</c:if>><a href="/educenter/m/mbrhstry/listDtl.do">강의실</a></li>
										<li <c:if test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'survey_view'}">class="select"</c:if>><a href="/educenter/m/mbrhstry/survey_view.do">설문조사</a></li>
										<li <c:if test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'service_guide'}">class="select"</c:if>><a href="/educenter/main/serviceGuide.do">이용안내</a></li>
										<li <c:if test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'service_online_guide'}">class="select"</c:if>><a href="/educenter/main/serviceOnlineGuide.do">온라인교육수강 이용안내</a></li>
										<li <c:if test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'curriculum_guide'}">class="select"</c:if>><a href="/educenter/main/curriculumGuide.do">교육과정안내</a></li>
										<li <c:if test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt'}">class="select"</c:if>><a href="/educenter/main/fshMnCltVt.do">낚시 관리 및 육성법</a></li>
										<li <c:if test="${urlmethodname eq 'board' and urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/notice/list.do">공지사항</a></li>
										<li <c:if test="${urlmethodname eq 'board' and urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/faq/list.do">FAQ</a></li>
										<li <c:if test="${urlmethodname eq 'board' and urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/file/list.do">자료실</a></li>
									</c:when>
									<c:when test="${menuNum eq '11' and urlcontroller eq 'educenter'}">
										<li <c:if test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'find_id'}">class="select"</c:if>><a href="#;">회원정보찾기</a></li>
										<li <c:if test="${urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'agree'}">class="select"</c:if>><a href="#;">이용동의서</a></li>
										<li <c:if test="${urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'write'}">class="select"</c:if>><a href="#;">신청안내</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'hp_agree'}">class="select"</c:if>><a href="#;">이용동의서</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'hpreqstdoc'}">class="select"</c:if>><a href="#;">신청서</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_agree'}">class="select"</c:if>><a href="#;">이용동의서</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_bplc'}">class="select"</c:if>><a href="#;">확인서</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'agree'}">class="select"</c:if>><a href="/educenter/m/rmndr/list.do">이용동의서</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'write_ship'}">class="select"</c:if>><a href="/educenter/m/rmndr/writeShip.do">낚시어선 전문교육 수강신청</a></li>
										<li <c:if test="${urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'write_house'}">class="select"</c:if>><a href="/educenter/m/rmndr/writeHouse.do">낚시터 전문교육 수강신청</a></li>
									</c:when>
									<c:when test="${depthNum eq '7' }">
										<c:set var="depthName" value="통합검색"></c:set>
									</c:when>
								</c:choose>
							</ul>
						</dd>
					</dl>
				</div>
			</nav>
		</c:if>
		<div id="topLoca">
			<h1 class="topTit">
			
				<span class="location" id="select_pageName2"></span>
				<em id="select_pageName3"></em>
				
				
			</h1>
<!-- 			<div class="social"> -->
<%-- 				<a href="#;"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a> --%>
<%-- 				<a href="#;"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a> --%>
<%-- 				<a href="#;"><img src="/naksinuri_original/common_main/img/ico_social_kakao.png" alt="카카오스토리" /></a> --%>
<!-- 			</div> -->
		</div>
	</c:if>
	
	
	<c:if test="${pageMode ne 'indexpage'}">
		<c:choose>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main'}">
				<div class="respon2 pb-10 pt-15">
					<section class="list_box">
						<div class="tabArea tab4"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_guide'}">on</c:if>"><a href="/educenter/m/main/serviceGuide.do">이용안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_online_guide'}">on</c:if>"><a href="/educenter/m/main/serviceOnlineGuide.do">온라인교육수강 이용안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'curriculum_guide'}">on</c:if>"><a href="/educenter/m/main/curriculumGuide.do">교육과정안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt'}">on</c:if>"><a href="/educenter/m/main/fshMnCltVt.do">낚시 관리 및 육성법</a></li>
							</ul>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board'}">
				<div class="respon2 pb-10 pt-15">
					<section class="list_box">
						<div class="tabArea tab3"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/m/board/notice/list.do">공지사항</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/m/board/faq/list.do">FAQ</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/m/board/file/list.do">자료실</a></li>
							</ul>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry'  and ( urlpagename eq 'list' or urlpagename eq 'list_crtf' or urlpagename eq 'list_crtf_dtl' ) }">
				<div class="respon2 pb-10 pt-15">
					<section class="list_box">
						<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq '' and ( urlpagename eq 'list' )}">on</c:if>"><a href="/educenter/m/mbrhstry/list.do">나의신청내역</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and ( urlpagename eq 'list_crtf' or urlpagename eq 'list_crtf_dtl' )}">on</c:if>"><a href="/educenter/m/mbrhstry/listCrtf.do">이수증내역</a></li>
							</ul>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${pageMode eq 'promotion' and pageName eq 'auditor' }">
				<c:if test="${(urlpagename ne 'auditor_board_write' or urlpagename ne 'auditor_board_modify') and MBR_LV_ID le '5'}">
				<div class="respon2 pb-10 pt-15">
					<section class="list_box">
						<div class="tabArea tab2">
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq 'm' and urlpagename eq 'auditor_list'}">on</c:if>"><a href="/promotion/auditor/m/list.do" ${urlmethodsubname eq '' and ( urlpagename eq 'auditor_list' ) ? 'title="낚시명예감시원 선택됨"' : 'title="낚시명예감시원"'}>낚시명예감시원</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'm' and ( urlpagename eq 'auditor_board_list' or urlpagename eq 'auditor_board_view')}">on</c:if>"><a href="/promotion/m/auditor/board_list.do">활동보고서 작성</a></li>
							</ul>
						</div>
					</section>
				</div>
				</c:if>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</c:if>
	
	
	<script type="text/javascript">
	
	function go_search(){
		var tmp = document.search_form.searchText.value.replace(/\s|　/gi, '');
		var form = document.getElementById("search_form");
		if(tmp==''){
			alert('통합 검색 키워드가 없습니다.');
			$('#searchText').val($('#searchText1').val());
			return false;
		}else{
		form.action="/search/m/search_list.do";
		form.submit;
		}
	}

	
	$(document).ready(function (){
		
		var groupHome="<c:out value="${groupName}" />";
		if(groupHome=='') {
			groupHome = '홈';
		}
		
		var pageName=$('#menu > li.select').text();
		var depthName="${depthName}";

		$('#select_pageName').text(pageName);
		$('#select_pageName3').text(pageName);
		$('#select_pageName2').text(groupHome+" > "+depthName+" > "+pageName);

	});
	
	
	function shfabook(bo_sn){
		if(bo_sn){
			if(spairPage=="fishjob"){
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?nak_id='+bo_sn);	
			}else if(spairPage=="industry"){
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?san_sn='+bo_sn);	
			}else if(spairPage=="event"){
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?evn_no='+bo_sn);
			}else if(spairPage=="zisik"){
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?nuri_q_num='+bo_sn);
			}else{
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?bo_sn='+bo_sn);
			}
		}else{
			window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]);
		}
		
		
	}

	function shtwitter(bo_sn){
		if(bo_sn){
			if(spairPage=="fishjob"){
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?nak_id='+bo_sn);
			}else if(spairPage=="industry"){
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?san_sn='+bo_sn);
			}else if(spairPage=="event"){
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?evn_no='+bo_sn);
			}else if(spairPage=="zisik"){
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?nuri_q_num='+bo_sn);
			}else{
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?bo_sn='+bo_sn);
			}
		}else{
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]);
		}
		
	}

	
	function changePc(){
		sessionStorage.setItem('naksi_device', 'pc');
		location.href="/index.do";
	}
	
	</script>
	
