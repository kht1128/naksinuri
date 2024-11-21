<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@include file="./head.sub.jsp"%>

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
					if(resmpt) $(this).attr('href', '/educenter/rmndr/agreeResmpt.do');
				});
			}
			
		} else if(alert_data_type!=null && alert_data_type.length!=0 && alert_data_type == 'isDelete') {
			if(message!=null && message.length!=0){
				var confirmIsDelete = confirm(message);
				if(confirmIsDelete){
					location.href = "/educenter/trnng/agree.do";
				}
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

<div id="wrapper">
	<!-- 상단헤더 { -->
	<header id="header">
		<%--
		<div id="topArea" class="respon">
			<div class="hlc">
				<ul class="floats">
					<li><a href="http://www.mof.go.kr/index.do" target="_blank"><img src="/naksinuri_original/common_main/img/topLink_mof.png" alt="해양수산부" /></a></li>
					<li><a href="https://www.fipa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/topLink_kfpa.png" alt="KFPA" /></a></li>
				</ul>
			</div>
			<h1><a href="/index.do"><img src="/naksinuri_original/common_main/img/logo.png" alt="낚시누리" /></a></h1>
			<div class="lrc">
				<div class="topMnu">
					<a href="https://www.naksiedu.kr/" target="_blank">낚시전문교육</a>
					<a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank">해양정보 인터넷방송</a>
					<a href="https://www.facebook.com/nurinaksi/" target="_blank">페이스북</a>
				</div>
				<!-- 검색공간 { -->
				<div class="head_sraech_box">
					<form id="search_form" name="search_form" method="post">
						<input type="text" class="hsearch_input" placeholder="검색어를 입력해주세요." name="searchText" id="searchText" value="${searchText}"/>
						<button class="hsearch_btn" onclick="go_search()"><i class="fa fa-search" aria-hidden="true"></i></button>
					</form>
				</div>
				<!-- } 검색공간 -->
			</div>
		</div>
		 --%>
		<!-- ##start## new_top ##### -->
		<div id="new_top">
			<div class="Grp">
				<h1 class="logoBx">
					<a href="/index.do" title="메인가기"><img src="/naksinuri_original/common_main/img/logo.png" alt="낚시누리 " /></a>
				</h1><!--//logoBx//-->

				<div class="searchBx">
					<form id="search_form" name="search_form" method="post">
					<div class="search_design">
						<input type="text" class="hsearch_input" placeholder="검색어를 입력해주세요." name="searchText" id="searchText" value="" title="검색어"/>
						<button class="hsearch_btn" onclick="go_search()">검색</button>					
					</div><!--//search_design//-->
					</form>
				</div><!--//searchBx//-->

				<div class="weatherBx">
					<!-- 30 ℃ 구름 많음 <img src="/naksinuri_original/edu/weather.png" alt="" /> --><!-- // 이미지 60*60 // -->
				</div><!--//weatherBx//-->
				<p class="clr"></p>
			</div><!--//Grp//-->
		</div><!--//new_top//-->
		<!-- ##end## new_top ## -->
		
		<div id="allMnuBox">
			<div class="respon">
				<nav id="gnb">
					<ul id="gnb_1dul" class="floats gnb_1dul_m6 gnb1ul">
						<%-- <li class="gnb_1dli <c:if test="${depthName eq 'gongmo'}"> select </c:if>">
							<a href="/gongmo/mof/intro.do" class="gnb_1da">낚시안전 디자인 공모전</a>
							<ul class="gnb_2dul">
								<li class="gnb_2dli"><a href="/gongmo/mof/intro.do" class="gnb_2da">공모전 소개</a></li>
								<li class="gnb_2dli"><a href="/gongmo/gongmo/check.do" class="gnb_2da">접수 및 접수확인</a></li>
								<li class="gnb_2dli"><a href="/gongmo/event/list.do" class="gnb_2da">커뮤니티</a></li>
							</ul>
						</li> --%>
						<li class="gnb1li gnb_1dli <c:if test="${depthName eq 'lesson'}"> select </c:if>">
							<a href="/lesson/dignity/list.do" class="gnb_1da" title="낚시교실 바로가기">낚시교실	</a>
							<ul class="gnb_2dul">
								<li class="gnb_2dli"><a href="/lesson/dignity/list.do" class="gnb_2da" title="낚시의 품격 바로가기">낚시의 품격</a></li>
								<li class="gnb_2dli"><a href="/lesson/junior/list.do" class="gnb_2da" title="초보탈출하기 바로가기">초보탈출하기</a></li>
								<li class="gnb_2dli"><a href="/lesson/gosu/list.do" class="gnb_2da" title="낚시고수되기 바로가기">낚시고수되기</a></li>
								<li class="gnb_2dli"><a href="/lesson/sense/list.do" class="gnb_2da" title="낚시상식 바로가기">낚시상식</a></li>
								<li class="gnb_2dli"><a href="/lesson/binding/list.do" class="gnb_2da" title="채비필수묶음법 바로가기">채비필수묶음법</a></li>
								<li class="gnb_2dli"><a href="/lesson/class/list.do" class="gnb_2da" title="어종별낚시교실 바로가기">어종별낚시교실</a></li>
							</ul>
						</li>
						<li class="gnb1li gnb_1dli <c:if test="${depthName eq 'info'}"> select </c:if>">
							<a href="/info/angling/list.do" class="gnb_1da" title="낚시정보 바로가기">낚시정보</a>
							<ul class="gnb_2dul">
								<!-- <li class="gnb_2dli"><a href="/info/fishjob/list.do" class="gnb_2da">낚시터정보</a></li> -->
								<!-- <li class="gnb_2dli"><a href="/info/industry/list.do" class="gnb_2da">낚시산업정보</a></li> -->
								<li class="gnb_2dli"><a href="/info/angling/list.do" class="gnb_2da">조황정보</a></li>
								<li class="gnb_2dli"><a href="/sosig/lab/list.do" class="gnb_2da">낚시연구소</a></li>
								<!-- <li class="gnb_2dli"><a href="/info/fishCompany/list.do" class="gnb_2da">낚시컴퍼니정보</a></li> -->
								<!-- <li class="gnb_2dli"><a href="/info/fishFshtbSttus/list.do" class="gnb_2da">낚시어선업현황</a></li> -->
								<!-- <li class="gnb_2dli"><a href="/info/oceanFshIdex/list.do" class="gnb_2da">바다낚시지수정보</a></li> -->
 								<!-- 
 								<li class="gnb_2dli"><a href="/info/safe/list.do" class="gnb_2da">낚시안전</a></li>
 								 -->
							</ul>
						</li>
						<li class="gnb1li gnb_1dli <c:if test="${depthName eq 'sosig'}"> select </c:if>">
							<!-- <a href="/sosig/news/list.do" class="gnb_1da">낚시소식</a> -->
							<a href="/sosig/notice/list.do" class="gnb_1da">낚시소식</a>
							<ul class="gnb_2dul">
								<%-- <li class="gnb_2dli"><a href="/sosig/news/list.do" class="gnb_2da">낚시뉴스</a></li> --%>
								<li class="gnb_2dli"><a href="/sosig/notice/list.do" class="gnb_2da">공지사항</a></li>
								<li class="gnb_2dli"><a href="/sosig/congress/list.do" class="gnb_2da">낚시대회</a></li>
								<%--
								<!-- //2018.09.20 요청사항// -->
								<li class="gnb_2dli"><a href="/shortcut/congress1/link.do" class="gnb_2da">전국민물낚시대회</a></li>
								<li class="gnb_2dli"><a href="/shortcut/congress2/link.do" class="gnb_2da">어린이 낚시안전 체험교실</a></li>
								 --%> 
								<li class="gnb_2dli"><a href="/sosig/congress/write.do" class="gnb_2da">낚시대회 등록</a></li>
								<li class="gnb_2dli"><a href="/sosig/congress/check.do" class="gnb_2da">낚시대회 등록 내역조회</a></li>
								<li class="gnb_2dli"><a href="/sosig/congress/mbrcheck.do" class="gnb_2da">낚시대회 참가자 내역조회</a></li>
								<li class="gnb_2dli"><a href="/sosig/event/list.do" class="gnb_2da">이벤트</a></li>															
							</ul>
						</li>
						<li class="gnb1li gnb_1dli <c:if test="${depthName eq 'promotion'}"> select </c:if>">
							<a href="/promotion/info/list.do" class="gnb_1da">낚시정책</a>
							<ul class="gnb_2dul">
								<li class="gnb_2dli"><a href="/promotion/info/list.do" class="gnb_2da">알림마당</a></li>
								<li class="gnb_2dli"><a href="/promotion/policy/list.do" class="gnb_2da">낚시정책안내</a></li>
								<li class="gnb_2dli"><a href="/promotion/campaign/list.do" class="gnb_2da">낚시캠페인</a></li>
								<!-- <li class="gnb_2dli"><a href="/promotion/plocation/list.do" class="gnb_2da">낚시금지구역</a></li> -->
								<li class="gnb_2dli"><a href="/promotion/qna/list.do" class="gnb_2da">낚시법 및 유어장 관련 질의 사례</a></li>
								<li class="gnb_2dli"><a href="/sosig/gosi/list.do" class="gnb_2da">지역별 낚시준수사항</a></li>	
								<li class="gnb_2dli"><a href="/promotion/auditor/list.do" class="gnb_2da">낚시명예감시원</a></li>
							</ul>
						</li>
						<li class="gnb1li gnb_1dli <c:if test="${depthName eq 'share'}"> select </c:if>">
							<a href="/share/travel/list.do" class="gnb_1da">커뮤니티</a>
							<ul class="gnb_2dul">
								<li class="gnb_2dli"><a href="/share/travel/list.do" class="gnb_2da">낚시여행기</a></li>
<!-- 								<li class="gnb_2dli"><a href="/share/zisik/list.do" class="gnb_2da">누리지식인</a></li> -->
								<li class="gnb_2dli"><a href="/share/nuri/list.do" class="gnb_2da">자주묻는 낚시질문</a></li>
								<li class="gnb_2dli"><a href="/share/usage/list.do" class="gnb_2da">낚시용품 사용기</a></li>
								<li class="gnb_2dli"><a href="/share/column/list.do" class="gnb_2da">낚시칼럼</a></li>
								<li class="gnb_2dli"><a href="/survey/survey/list.do" class="gnb_2da">설문조사</a></li>
							</ul>
						</li>
						
						<li class="gnb1li gnb_1dli ">
							<a href="/educenter/index.do" class="gnb_1da bg-cyan-600 ">낚시전문교육</a>
							<ul class="gnb_2dul">
								<li class="gnb_2dli"><a href="/educenter/index.do" class="gnb_2da">낚시전문교육 메인화면</a></li>
								<li class="gnb_2dli"><a href="/educenter/trnng/list.do" class="gnb_2da">온라인 교육신청</a></li>
								<li class="gnb_2dli"><a href="/educenter/trnngoffline/fshlcList.do" class="gnb_2da">오프라인 교육신청(낚시터)</a></li>
								<li class="gnb_2dli"><a href="/educenter/trnngoffline/fshhpList.do" class="gnb_2da">오프라인 교육신청(낚시어선)</a></li>
								<li class="gnb_2dli"><a href="/educenter/mbrhstry/list.do" class="gnb_2da">온라인강의 / 이수증내역</a></li>
								<li class="gnb_2dli"><a href="/educenter/board/notice/list.do" class="gnb_2da">공지사항</a></li>
								<!-- <li class="gnb_2dli"><a href="/educenter/rmndr/hp_agree.do" class="gnb_2da">본인명의 휴대폰 변경</a></li>
								<li class="gnb_2dli"><a href="/educenter/rmndr/cpr_agree.do" class="gnb_2da">법인사업장 교육책임자</a></li> -->
								<li class="gnb_2dli"><a href="/educenter/main/serviceGuide.do" class="gnb_2da">교육안내</a></li>
								<li class="gnb_2dli"><a href="https://www.113366.com/naksinuri" class="gnb_2da" target="_blank">원격지원서비스</a></li>
							</ul>
						</li>
						
					</ul>
				</nav>
			</div>
		</div>
		<div id="scrollPercentCounter"></div>
	</header>
	<!-- } 상단헤더 -->

	<c:if test="${pageMode ne 'indexpage'}">
	<div id="container">
		<nav id="subGnb">
			<div id ="menu" class="respon">
				<ul>
					<c:choose>
					<c:when test="${depthName eq 'gongmo'}">
					<li <c:if test="${pageName eq 'mof'}">class="select"</c:if>><a href="/gongmo/mof/intro.do">해양수산부 소개</a></li>						
					<li <c:if test="${pageName eq 'gm'}">class="select"</c:if>><a href="/gongmo/gm/intro.do">공모전 소개</a></li>
					<li <c:if test="${pageName eq 'summary'}">class="select"</c:if>><a href="/gongmo/summary/intro.do">공모전 요강</a></li>
					<li <c:if test="${pageName eq 'event'}">class="select"</c:if>><a href="/gongmo/event/list.do">커뮤니티</a></li>
					<li <c:if test="${pageName eq 'check'}">class="select"</c:if>><a href="/gongmo/gongmo/check.do">공모전 접수/신청</a></li>
					<li <c:if test="${pageName eq 'mbrwrite'}">class="select"</c:if>><a href="/gongmo/gongmo/check.do">공모전 신청서 작성하기</a></li>
					<li <c:if test="${pageName eq 'search'}">class="select"</c:if>><a href="/gongmo/gongmo/search.do">공모전 접수확인</a></li>
					<li <c:if test="${pageName eq 'mbrconfirm'}">class="select"</c:if>><a href="/gongmo/gongmo/check.do">공모전 접수조회</a></li>
					</c:when>	
					<c:when test="${depthName eq 'lesson'}">
					<li <c:if test="${pageName eq 'dignity'}">class="select"</c:if>><a href="/lesson/dignity/list.do">낚시의 품격</a></li>
					<li <c:if test="${pageName eq 'junior'}">class="select"</c:if>><a href="/lesson/junior/list.do">초보탈출하기</a></li>						
					<li <c:if test="${pageName eq 'gosu'}">class="select"</c:if>><a href="/lesson/gosu/list.do">낚시고수되기</a></li>
					<li <c:if test="${pageName eq 'sense'}">class="select"</c:if>><a href="/lesson/sense/list.do">낚시상식</a></li>
					<li <c:if test="${pageName eq 'binding'}">class="select"</c:if>><a href="/lesson/binding/list.do">채비필수묶음법</a></li>
					<li <c:if test="${pageName eq 'class'}">class="select"</c:if>><a href="/lesson/class/list.do">어종별낚시교실</a></li>
					</c:when>					
					<c:when test="${depthName eq 'info'}">					
					<%-- <li <c:if test="${pageName eq 'fishjob'}">class="select"</c:if>><a href="/info/fishjob/list.do">낚시터정보</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'fishjobWrite'}">class="select"</c:if>><a href="/info/fishjob/list.do">낚시터정보 > 낚시터 정보등록</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'industry'}">class="select"</c:if>><a href="/info/industry/list.do">낚시산업정보</a></li> --%>
					<li <c:if test="${pageName eq 'angling'}">class="select"</c:if>><a href="/info/angling/list.do">조황정보</a></li>
					<li <c:if test="${pageName eq 'lab'}">class="select"</c:if>><a href="/sosig/lab/list.do">낚시연구소</a></li>
					<%-- <li <c:if test="${pageName eq 'fishCompany' }"> class="select" </c:if>><a href="/info/fishCompany/list.do">낚시컴퍼니정보</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'fishFshtbSttus' }"> class="select" </c:if>><a href="/info/fishFshtbSttus/list.do">낚시어선업현황</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'oceanFshIdex' }"> class="select" </c:if>><a href="/info/oceanFshIdex/list.do">바다낚시지수정보</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'report'}">class="select"</c:if>><a href="/info/report/list.do">잘못된 낚시터 정보 신고</a></li> --%>
					<%-- <li <c:if test="${pageName eq 'safe'}">class="select"</c:if>><a href="/info/safe/list.do">낚시안전</a></li> --%>
					</c:when>
					<c:when test="${depthName eq 'share'}">
					<li <c:if test="${pageName eq 'travel'}">class="select"</c:if>><a href="/share/travel/list.do">낚시여행기</a></li>
					<li <c:if test="${pageName eq 'nuri'}">class="select"</c:if>><a href="/share/nuri/list.do">자주묻는 낚시질문</a></li>
					<li <c:if test="${pageName eq 'usage'}">class="select"</c:if>><a href="/share/usage/list.do">낚시용품 사용기</a></li>
					<li <c:if test="${pageName eq 'column'}">class="select"</c:if>><a href="/share/column/list.do">낚시칼럼</a></li>
					<li <c:if test="${pageName eq 'survey'}">class="select"</c:if>><a href="/survey/survey/list.do">설문조사</a></li>
					</c:when>
					<c:when test="${depthName eq 'sosig'}">
					<%-- <li <c:if test="${pageName eq 'news'}">class="select"</c:if>><a href="/sosig/news/list.do">낚시뉴스</a></li> --%>
					<li <c:if test="${pageName eq 'notice'}">class="select"</c:if>><a href="/sosig/notice/list.do">공지사항</a></li>
					<li <c:if test="${pageName eq 'congress'}">class="select"</c:if>><a href="/sosig/congress/list.do">낚시대회</a></li>
					<li <c:if test="${pageName eq 'congressregit'}">class="select"</c:if>><a href="/sosig/congress/write.do">낚시대회 등록</a></li>
					<li <c:if test="${pageName eq 'congresscheck'}">class="select"</c:if>><a href="/sosig/congress/check.do">낚시대회 등록 내역조회</a></li>
					<li <c:if test="${pageName eq 'congressmbrwrite'}">class="select"</c:if>><a href="#;">참가신청서 작성하기</a></li>
					<li <c:if test="${pageName eq 'congressmbrsearch'}">class="select"</c:if>><a href="#;">낚시대회 참가자 내역조회</a></li>
					<li <c:if test="${pageName eq 'congressmbrcheck'}">class="select"</c:if>><a href="/sosig/congress/mbrcheck.do">낚시대회 참가자 내역조회</a></li>
					<li <c:if test="${pageName eq 'event'}">class="select"</c:if>><a href="/sosig/event/list.do">이벤트</a></li>				
					</c:when>
					<c:when test="${depthName eq 'promotion'}">
					<li <c:if test="${pageName eq 'info'}">class="select"</c:if>><a href="/promotion/info/list.do">알림마당</a></li>
					<li <c:if test="${pageName eq 'policy'}">class="select"</c:if>><a href="/promotion/policy/list.do">낚시정책안내</a></li>
					<li <c:if test="${pageName eq 'campaign'}">class="select"</c:if>><a href="/promotion/campaign/list.do">낚시캠페인</a></li>
					<li <c:if test="${pageName eq 'plocation'}">class="select"</c:if>><a href="/promotion/plocation/list.do">낚시금지구역</a></li>
					<li <c:if test="${pageName eq 'qna'}">class="select"</c:if>><a href="/promotion/qna/list.do">낚시법 및 유어장 관련 질의 사례</a></li>
					<li <c:if test="${pageName eq 'gosi'}">class="select"</c:if>><a href="/sosig/gosi/list.do">지역별 낚시준수사항</a></li>	
					<li <c:if test="${pageName eq 'auditor'}">class="select"</c:if>><a href="/promotion/auditor/list.do">낚시명예감시원</a></li>
					</c:when>
					<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main'}">
					<li <c:if test="${urlpagename eq 'service_guide'}">class="select"</c:if>><a href="/educenter/main/serviceGuide.do">이용안내</a></li>
					<li <c:if test="${urlpagename eq 'service_online_guide'}">class="select"</c:if>><a href="/educenter/main/serviceOnlineGuide.do">온라인교육수강 이용안내</a></li>
					<li <c:if test="${urlpagename eq 'curriculum_guide'}">class="select"</c:if>><a href="/educenter/main/curriculumGuide.do">교육과정안내</a></li>
					<li <c:if test="${urlpagename eq 'fshmn_cltvt'}">class="select"</c:if>><a href="/educenter/main/fshMnCltVt.do">낚시 관리 및 육성법</a></li>
					</c:when>
					<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main'}">
					<li <c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_guide'}">class="select"</c:if>><a href="/educenter/main/serviceGuide.do">이용안내</a></li>
					<li <c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_online_guide'}">class="select"</c:if>><a href="/educenter/main/serviceOnlineGuide.do">온라인교육수강 이용안내</a></li>
					<li <c:if test="${urlmethodsubname eq '' and urlpagename eq 'curriculum_guide'}">class="select"</c:if>><a href="/educenter/main/curriculumGuide.do">교육과정안내</a></li>
					<li <c:if test="${urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt'}">class="select"</c:if>><a href="/educenter/main/fshMnCltVt.do">낚시 관리 및 육성법</a></li>
					</c:when>
					<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board'}">
					<li <c:if test="${urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/notice/list.do">공지사항</a></li>
					<li <c:if test="${urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/faq/list.do">FAQ</a></li>
					<li <c:if test="${urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">class="select"</c:if>><a href="/educenter/board/file/list.do">자료실</a></li>
					</c:when>
					<c:otherwise></c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<div id="topLoca">
<%-- 			<div class="respon2">
				<h1 class="topTit">
					<span class="location" id="select_name"></span>
					<em id="select_pageName"><c:out value="${pageName}"></c:out></em>
				</h1>
			</div> --%>
			<%--
			<div class="social">	
			<c:if test="${urlcontroller ne 'educenter'}">	
			<c:choose>
				<c:when test="${pageName eq 'fishjob'}">
					<a href="#;" onclick="shfabook(${info.nak_id});"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.nak_id});"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:when test="${pageName eq 'industry'}">
					<a href="#;" onclick="shfabook(${info.san_sn})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.san_sn})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:when test="${pageName eq 'event'}">
					<a href="#;" onclick="shfabook(${info.evn_no})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.evn_no})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:when test="${pageName eq 'zisik'}">
					<a href="#;" onclick="shfabook(${info.nuri_q_num})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.nuri_q_num})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:when test="${pageName eq 'angling'}">
					<a href="#;" onclick="shfabook(${info.mid})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.mid})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:when test="${pageName eq 'survey'}">
					<a href="#;" onclick="shfabook(${info.sv_id})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.sv_id})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:when>
				<c:otherwise>
					<a href="#;" onclick="shfabook(${info.bo_sn})"><img src="/naksinuri_original/common_main/img/ico_social_fb.png" alt="페이스북" /></a>
					<a href="#;" onclick="shtwitter(${info.bo_sn})"><img src="/naksinuri_original/common_main/img/ico_social_twit.png" alt="트위터" /></a>
				</c:otherwise>			
			</c:choose>
			</c:if>
			</div>
			 --%>
		</div>
	</c:if>
		
	<c:if test="${pageMode ne 'indexpage'}">
		<c:choose>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main'}">
				<div class="respon2 pt-50">
					<section class="list_box">
						<div class="tabArea tab4 mbNone"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_guide'}">on</c:if>"><a href="/educenter/main/serviceGuide.do" ${urlmethodsubname eq '' and urlpagename eq 'service_guide' ? 'title="이용안내 선택됨"' : 'title="이용안내"'}>이용안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'service_online_guide'}">on</c:if>"><a href="/educenter/main/serviceOnlineGuide.do" ${urlmethodsubname eq '' and urlpagename eq 'service_online_guide' ? 'title="온라인교육수강 이용안내 선택됨"' : 'title="온라인교육수강 이용안내"'}>온라인교육수강 이용안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'curriculum_guide'}">on</c:if>"><a href="/educenter/main/curriculumGuide.do" ${urlmethodsubname eq '' and urlpagename eq 'curriculum_guide' ? 'title="교육과정안내 선택됨"' : 'title="교육과정안내 이용안내"'}>교육과정안내</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt'}">on</c:if>"><a href="/educenter/main/fshMnCltVt.do" ${urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt' ? 'title="낚시 관리 및 육성법 선택됨"' : 'title="낚시 관리 및 육성법 이용안내"'}>낚시 관리 및 육성법</a></li>
							</ul>
						</div>
						
						<div class="tabArea2 mbShow"> <!-- 모바일 ui -->
							<div class="link_select">
								<dl>
									<dt><a href="#;" id="this_cate"> <span><i class="fa fa-angle-down" aria-hidden="true"></i></span></a></dt>
									<dd>
										<ul>
											<li><a href="/educenter/main/serviceGuide.do">이용안내</a></li>
											<li><a href="/educenter/main/serviceOnlineGuide.do">온라인교육수강 이용안내</a></li>
											<li><a href="/educenter/main/curriculumGuide.do">교육과정안내</a></li>
											<li><a href="/educenter/main/fshMnCltVt.do">낚시 관리 및 육성법</a></li>
										</ul>
									</dd>
								</dl>
							</div>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board'}">
				<div class="respon2 pt-50">
					<section class="list_box">
						<div class="tabArea tab3"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/board/notice/list.do" ${urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' ) ? 'title="공지사항 선택됨"' : 'title="공지사항"'}>공지사항</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/board/faq/list.do" ${urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' ) ? 'title="FAQ 선택됨"' : 'title="FAQ"'}>FAQ</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' )}">on</c:if>"><a href="/educenter/board/file/list.do" ${urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' ) ? 'title="자료실 선택됨"' : 'title="자료실"'}>자료실</a></li>
							</ul>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and ( urlpagename eq 'list' or urlpagename eq 'list_crtf' or urlpagename eq 'list_crtf_dtl' ) }">
				<div class="respon2 pb-50 pt-50">
					<section class="list_box">
						<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq '' and ( urlpagename eq 'list' )}">on</c:if>"><a href="/educenter/mbrhstry/list.do" ${urlmethodsubname eq '' and ( urlpagename eq 'list' ) ? 'title="나의신청내역 선택됨"' : 'title="나의신청내역"'}>나의신청내역</a></li>
								<li class="<c:if test="${urlmethodsubname eq '' and ( urlpagename eq 'list_crtf' or urlpagename eq 'list_crtf_dtl' )}">on</c:if>"><a href="/educenter/mbrhstry/listCrtf.do" ${urlmethodsubname eq '' and ( urlpagename eq 'list_crtf' or urlpagename eq 'list_crtf_dtl' ) ? 'title="이수증내역 선택됨"' : 'title="이수증내역"'}>이수증내역</a></li>
							</ul>
						</div>
					</section>
				</div>
			</c:when>
			<c:when test="${pageMode eq 'promotion' and pageName eq 'auditor' }">
				<c:if test="${urlpagename ne 'auditor_board_write' and MBR_LV_ID le '5'}">
				<div class="respon2 pb-50 pt-50">
					<section class="list_box">
						<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
							<ul class="floats">
								<li class="<c:if test="${urlmethodsubname eq 'promotion' and urlpagename eq 'auditor_list'}">on</c:if>"><a href="/promotion/auditor/list.do" ${urlmethodsubname eq '' and ( urlpagename eq 'auditor_list' ) ? 'title="낚시명예감시원 선택됨"' : 'title="낚시명예감시원"'}>낚시명예감시원</a></li>
								<li class="<c:if test="${urlmethodsubname eq 'promotion' and ( urlpagename eq 'auditor_board_list' or urlpagename eq 'auditor_board_view')}">on</c:if>"><a href="/promotion/auditor/board_list.do">활동보고서 작성</a></li>
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
			form.action="/search/search_list.do";
			form.submit;
		}
	}
	
	var href=location.href;
	href = href.replace("#;","");
	var href2 = href.split("?",1);
	
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
			}else if(spairPage=="survey"){
				window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?sv_id='+bo_sn);
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
			}else if(spairPage=="survey"){
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?sv_id='+bo_sn);				
			}else{
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?bo_sn='+bo_sn);
			}
		}else{
				window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]);
		}
		
	}
	
	//모바일 탭 ui
	$(document).ready(function(){
		var text = $('.tabArea ul li.on a').text();
		$('#this_cate').append(text);
	});


	
	</script>
<!-- 본문시작 -->
<div id="containerWrap"  tabIndex="0">
