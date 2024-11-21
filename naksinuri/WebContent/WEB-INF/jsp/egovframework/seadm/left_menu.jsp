<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
/* 메뉴 확장 고정 - 2019.01.01 jhkim 
.site-menubar-unfold .site-menu-item:not(.open)>.site-menu-sub {display:block;}
.site-menubar-unfold .site-menu .site-menu-sub {display:block;}
.site-menubar-unfold .site-menu-item:not(.open)>a .site-menu-arrow {transform: rotate(90deg);}
*/ 
/** 커스텀 라벨 */
.msel { font-weight: bold; color: #ff6600; }
</style>

<%
//현재 페이지 정보 가져오기
String cururl = request.getRequestURI().toString();
String pageName = cururl.substring(cururl.lastIndexOf("/") + 1, cururl.length()).replace(".jsp", "");
String[] parseUrl = cururl.split("/");
String urlpath0 = "";
String urlpath1 = "";
String urlpath2 = "";
String urlpath3 = "";
if(parseUrl.length == 7) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[5];
	urlpath2 = "";
	urlpath3 = parseUrl[6].replace(".jsp", "");
} else if(parseUrl.length >= 8) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[5];
	urlpath2 = parseUrl[6];
	urlpath3 = parseUrl[7].replace(".jsp", "");
} else {
	urlpath0 = parseUrl[4];
	urlpath1 = "";
	urlpath2 = "";
	urlpath3 = pageName;
}
%>
<c:set var="urlcontroller" value="<%=urlpath0%>"/>
<c:set var="urlmethodname" value="<%=urlpath1%>"/>
<c:set var="urlmethodsubname" value="<%=urlpath2%>"/>
<c:set var="urlpagename" value="<%=urlpath3%>"/>
<c:set var="addWebLink" value="${addWebLink}"/>

<c:choose>
	<c:when test="${urlmethodname eq 'survey' and urlmethodsubname eq 'survey' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="7"/>
		<c:set var="depthName" value="설문조사"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="설문조사"/>
		<c:set var="pageUrl" value="/admin/survey/survey/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'summary' and urlpagename eq 'sitesummary'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="사이트현황"/>
		<c:set var="pageUrl" value="/seadm/analytics/summary/sitesummary.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'visit' and urlpagename eq 'uv'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="방문현황(UV)"/>
		<c:set var="pageUrl" value="/seadm/analytics/visit/uv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'visit' and urlpagename eq 'pv'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="페이지뷰(PV)"/>
		<c:set var="pageUrl" value="/seadm/analytics/visit/pv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'inflow' and urlpagename eq 'searchdashboard'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="검색 유입현황"/>
		<c:set var="pageUrl" value="/seadm/analytics/inflow/searchdashboard.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'environment' and urlpagename eq 'osdashboard'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="5"/>
		<c:set var="pageName" value="운영체제(OS) 분석"/>
		<c:set var="pageUrl" value="/seadm/analytics/environment/osdashboard.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'inflow' and urlpagename eq 'urls'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="6"/>
		<c:set var="pageName" value="페이지 분석"/>
		<c:set var="pageUrl" value="/seadm/analytics/inflow/urls.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	
	<c:when test="${urlmethodname eq 'popup' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="9"/>
		<c:set var="depthName" value="팝업 및 배너관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="팝업 및 배너목록"/>
		<c:set var="pageUrl" value="/seadm/popup/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'popup' and urlmethodsubname eq '' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="9"/>
		<c:set var="depthName" value="팝업 및 배너관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="팝업 및 배너글쓰기"/>
		<c:set var="pageUrl" value="/seadm/popup/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'popup' and urlmethodsubname eq '' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="9"/>
		<c:set var="depthName" value="팝업 및 배너관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="팝업 및 배너글수정"/>
		<c:set var="pageUrl" value="/seadm/popup/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'log' and urlmethodsubname eq '' and urlpagename eq 'list_rec' and addWebLink eq 'Seadm'}">
		<c:set var="depthNum" value="12"/>
		<c:set var="depthName" value="로그"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="접속자 로그 기록"/>
		<c:set var="pageUrl" value="/adm/log/listRecSeadm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'log' and urlmethodsubname eq '' and urlpagename eq 'list_sys' and addWebLink eq 'Seadm'}">
		<c:set var="depthNum" value="12"/>
		<c:set var="depthName" value="로그"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="시스템 로그 기록"/>
		<c:set var="pageUrl" value="/adm/log/listSysSeadm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>


	<%--
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="전체회원목록"/>
		<c:set var="pageUrl" value="/seadm/member/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="회원정보수정"/>
		<c:set var="pageUrl" value="/seadm/member/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_lv'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="권한관리"/>
		<c:set var="pageUrl" value="/seadm/member/list_lv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="진행중인 이벤트 목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'list_end'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="종료된 이벤트 목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list_end.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'list_winner'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="이벤트 당첨자발표"/>
		<c:set var="pageUrl" value="/seadm/board/event/list_winner.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="이벤트 글쓰기"/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'write_winner'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list_winner.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="이벤트 당첨자발표 글쓰기"/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="이벤트 게시물보기"/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'view_winner'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list_winner.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="이벤트 게시물보기"/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list.do"/>
		<c:set var="subpageNum" value="4"/>
		<c:set var="subpageName" value="이벤트 게시물수정"/>
	</c:when>
	<c:when test="${pageMode1 eq 'event' and pageMode2 eq 'modify_winner'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="목록"/>
		<c:set var="pageUrl" value="/seadm/board/event/list_winner.do"/>
		<c:set var="subpageNum" value="4"/>
		<c:set var="subpageName" value="이벤트 당첨자발표 게시물수정"/>
	</c:when>
	<c:when test="${pageMode1 eq 'communication' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="소통공간"/>
		<c:set var="pageUrl" value="/seadm/board/communication/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'communication' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="소통공간 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/communication/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'communication' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="소통공간 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/communication/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'communication' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="소통공간 게시글보기"/>
		<c:set var="pageUrl" value="/seadm/board/communication/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="귀어닥터 등록 현황"/>
		<c:set var="pageUrl" value="/seadm/doctor/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="귀어닥터 상세정보"/>
		<c:set var="pageUrl" value="/seadm/doctor/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="귀어닥터 상세정보 변경"/>
		<c:set var="pageUrl" value="/seadm/doctor/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="귀어닥터 신규등록"/>
		<c:set var="pageUrl" value="/seadm/doctor/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'rqlist'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 신청자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="신청자 목록"/>
		<c:set var="pageUrl" value="/seadm/seadm/doctor/rqlist.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'doctor' and pageMode2 eq 'rqview'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="귀어닥터 신청자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="답변하기"/>
		<c:set var="pageUrl" value="/seadm/seadm/doctor/rqlist.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	
	<c:when test="${pageMode1 eq 'news' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="어촌소식 목록"/>
		<c:set var="pageUrl" value="/seadm/board/news/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'news' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="어촌소식 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/news/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'news' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="어촌소식 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/news/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'news' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="어촌소식 게시글보기"/>
		<c:set var="pageUrl" value="/seadm/board/news/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'faq' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="자주묻는질문 목록"/>
		<c:set var="pageUrl" value="/seadm/board/faq/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'faq' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="자주묻는질문 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/faq/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'faq' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="자주묻는질문 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/faq/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'faq' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="자주묻는질문 게시글보기"/>
		<c:set var="pageUrl" value="/seadm/board/faq/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>		
	<c:when test="${pageMode1 eq 'prmov' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홍보영상 목록"/>
		<c:set var="pageUrl" value="/seadm/board/prmov/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'prmov' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홍보영상 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/prmov/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'prmov' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홍보영상 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/prmov/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'prmov' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홍보영상 게시글보기"/>
		<c:set var="pageUrl" value="/seadm/board/prmov/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'notice' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항 목록"/>
		<c:set var="pageUrl" value="/seadm/board/notice/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'notice' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/notice/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'notice' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/notice/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'notice' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항 게시글보기"/>
		<c:set var="pageUrl" value="/seadm/board/notice/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'qna' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="Q&A 목록"/>
		<c:set var="pageUrl" value="/seadm/board/qna/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'qna' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="Q&A 답변하기"/>
		<c:set var="pageUrl" value="/seadm/board/qna/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'qna' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="Q&A 상세보기"/>
		<c:set var="pageUrl" value="/seadm/board/qna/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	 	
	
	<c:when test="${pageMode1 eq 'bestexam' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="우수사례"/>
		<c:set var="pageUrl" value="/seadm/board/bestexam/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'bestexam' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="우수사례 글쓰기"/>
		<c:set var="pageUrl" value="/seadm/board/bestexam/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'bestexam' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="우수사례 글수정"/>
		<c:set var="pageUrl" value="/seadm/board/bestexam/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'bestexam' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="우수사례 글보기"/>
		<c:set var="pageUrl" value="/seadm/board/bestexam/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'list'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홈스테이 등록 현황"/>
		<c:set var="pageUrl" value="/seadm/homestay/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'view'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홈스테이 상세정보"/>
		<c:set var="pageUrl" value="/seadm/homestay/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'modify'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홈스테이 상세정보 변경"/>
		<c:set var="pageUrl" value="/seadm/homestay/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'write'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="홈스테이 신규등록"/>
		<c:set var="pageUrl" value="/seadm/homestay/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'rqlist'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 신청자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="신청자 목록"/>
		<c:set var="pageUrl" value="/seadm/seadm/homestay/rqlist.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${pageMode1 eq 'homestay' and pageMode2 eq 'rqview'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="홈스테이 신청자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="답변하기"/>
		<c:set var="pageUrl" value="/seadm/seadm/homestay/rqlist.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	--%>
</c:choose>


<div class="site-menubar">
	<div class="site-menubar-body">
		<div>
			<div>
	            <ul class="site-menu" data-plugin="menu">
					<li class="site-menu-category"></li>
	              	<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-pencil-square-o"></i>
							<span class="site-menu-title">공모전 정보</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/gongmo/gongmo/list.do" >
	                    			<span class="site-menu-title ">공모전</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/gongmo/event/list.do" >
	                    			<span class="site-menu-title ">커뮤니티</span>
	                    		</a>
	                  		</li>
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-pencil-square-o"></i>
							<span class="site-menu-title">낚시교실</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/dignity/list.do" >
	                    			<span class="site-menu-title ">낚시의 품격</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/junior/list.do" >
	                    			<span class="site-menu-title ">초보탈출하기</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/gosu/list.do" >
	                    			<span class="site-menu-title ">낚시고수되기</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/sense/list.do" >
	                    			<span class="site-menu-title ">낚시상식</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/binding/list.do" >
	                    			<span class="site-menu-title ">채비필수묶음법</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/lesson/class/list.do" >
	                    			<span class="site-menu-title ">어종별낚시교실</span>
	                    		</a>
	                  		</li>
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-pencil-square-o"></i>
							<span class="site-menu-title">낚시 정보</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/info/admin/fishingAM.do" >
	                    			<span class="site-menu-title ">낚시터 등록/수정</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/info/admin/sanupAM.do" >
	                    			<span class="site-menu-title ">낚시산업 등록/수정</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/info/admin/boatfishing.do" >
	                    			<span class="site-menu-title ">선상낚시</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/info/admin/riverfishing.do" >
	                    			<span class="site-menu-title ">민물낚시</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/info/admin/seafishing.do" >
	                    			<span class="site-menu-title ">바다낚시</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/info/industry/list.do" >
	                    			<span class="site-menu-title ">낚시 산업정보</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/info/angling/list.do" >
	                    			<span class="site-menu-title ">조황정보</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/sosig/lab/list.do" >
	                    			<span class="site-menu-title ">낚시연구소</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/info/report/list.do" >
	                    			<span class="site-menu-title ">낚시터 잘못된 정보 신고</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/info/safe/list.do" >
	                    			<span class="site-menu-title ">낚시안전</span>
	                    		</a>
	                  		</li>
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-signal"></i>
							<span class="site-menu-title">커뮤니티</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/share/travel/list.do" >
	                    			<span class="site-menu-title ">낚시여행기</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/share/zisik/list.do" >
	                    			<span class="site-menu-title ">누리지식인</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/share/nuri/list.do" >
	                    			<span class="site-menu-title ">자주묻는낚시질문</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/share/usage/list.do" >
	                    			<span class="site-menu-title ">낚시용품사용기</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/share/column/list.do" >
	                    			<span class="site-menu-title ">낚시칼럼</span>
	                    		</a>
	                  		</li>	                  		
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-book"></i>
							<span class="site-menu-title">낚시소식</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/sosig/news/list.do" >
	                    			<span class="site-menu-title ">낚시뉴스</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/sosig/notice/list.do" >
	                    			<span class="site-menu-title ">공지사항</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/sosig/congress/list.do" >
	                    			<span class="site-menu-title ">낚시대회</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/sosig/event/list.do" >
	                    			<span class="site-menu-title ">이벤트</span>
	                    		</a>
	                  		</li>                 		
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title">정책홍보</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/promotion/info/list.do" >
	                    			<span class="site-menu-title ">알림마당</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/promotion/policy/list.do" >
	                    			<span class="site-menu-title ">낚시정책안내</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/promotion/campaign/list.do" >
	                    			<span class="site-menu-title ">낚시캠페인</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/promotion/plocation/list.do" >
	                    			<span class="site-menu-title ">낚시금지구역</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/promotion/qna/list.do" >
	                    			<span class="site-menu-title font-size-10">낚시법 및 유어장 관련 질의회신 사례</span>
	                    		</a>
	                  		</li>    
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/policy/customersound/list.do" >
	                    			<span class="site-menu-title ">고객의 소리</span>
	                    		</a>
	                  		</li>             		
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub <c:if test="${depthNum eq '7'}">active open</c:if>">
		                <a href="/admin/survey/survey/list.do">
							<i class="site-menu-icon fa-university" aria-hidden="true"></i>
							<span class="site-menu-title <c:if test="${depthNum eq '7'}">msel</c:if>">설문조사</span>
							<span class="site-menu-arrow"></span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${depthNum eq '8'}">active open</c:if>">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-university" aria-hidden="true"></i>
							<span class="site-menu-title <c:if test="${depthNum eq '8'}">msel</c:if>">통계</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/static/site/list.do" target="_blank">
	                    			<span class="site-menu-title ">구버전 통계(낚시누리)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '1'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/summary/sitesummary.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '1'}">msel</c:if>">사이트 현황</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '2'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/visit/uv.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '2'}">msel</c:if>">방문현황(UV)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '3'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/visit/pv.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '3'}">msel</c:if>">페이지뷰(PV)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '4'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/inflow/searchdashboard.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '4'}">msel</c:if>">검색 유입현황</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '5'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/environment/osdashboard.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '5'}">msel</c:if>">운영체제(OS) 분석</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '6'}">active</c:if>">
	                    		<a class="animsition-link" href="/seadm/analytics/inflow/urls.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '6'}">msel</c:if>">페이지 분석</span>
	                    		</a>
	                  		</li>	                  		      		
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub <c:if test="${depthNum eq '9'}">active open</c:if>">
		                <a href="/seadm/popup/list.do">
							<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title <c:if test="${depthNum eq '9' and pageNum eq '1'}">msel</c:if>">팝업 및 배너관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub ">
		                <a href="/admin/info/adminset/view.do">
							<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title ">관리자메일설정</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub ">
		                <a href="/admin/survey/survey/list.do">
							<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title ">문자관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${depthNum eq '12'}">active open</c:if>">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title <c:if test="${depthNum eq '12'}">msel</c:if>">로그</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/admin/logs/list.do">
	                    			<span class="site-menu-title ">구버전 로그이력(낚시누리)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '12' and pageNum eq '1'}">active</c:if>">
								<a href="/adm/log/listRecSeadm.do">
									<span class="site-menu-title <c:if test="${depthNum eq '12' and pageNum eq '1'}">msel</c:if>">접속자 로그 기록</span>
								</a>
							</li>
							<li class="site-menu-item is-shown <c:if test="${depthNum eq '12' and pageNum eq '2'}">active</c:if>">
								<a href="/adm/log/listSysSeadm.do">
									<span class="site-menu-title <c:if test="${depthNum eq '12' and pageNum eq '2'}">msel</c:if>">시스템 로그 기록</span>
								</a>
							</li>                 		                 		      		
	                  	</ul>
					</li>				
					
					<!-- 
					 <li class="site-menu-item has-sub <c:if test="${PL2 eq 'member'}">active open</c:if>">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa-university"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'member'}">msel open</c:if>">회원관리</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/member/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL2 eq 'member' and ( PL3 eq 'list' or PL3 eq 'modify' ) }">msel</c:if>">전체회원목록</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/member/list_lv.do" >
	                    			<span class="site-menu-title <c:if test="${PL2 eq 'member' and PL3 eq 'list_lv'}">msel</c:if>">권한관리</span>
	                    		</a>
	                  		</li>
	                  	</ul>
					</li>
					<li class="site-menu-item has-sub  <c:if test="${PL2 eq 'board'}">active open</c:if>">
		                <a href="javascript:void(0)">
							<i class="site-menu-icon fa fa-chalkboard"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'board'}">msel open</c:if>">게시판 관리 <span id='aaa'></span></span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/notice/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'notice'}">msel</c:if>">공지사항</span>
	                    		</a>
	                  		</li>
	                   		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/event/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'event'}">msel</c:if>">이벤트</span>
	                    		</a>
	                  		</li>
	
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/news/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'news'}">msel</c:if>">어촌소식</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/faq/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'faq'}">msel</c:if>">자주묻는질문</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/qna/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'qna'}">msel</c:if>">Q&A</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/bestexam/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'bestexam'}">msel</c:if>">우수사례</span>
	                    		</a>
	                  		</li>
	                  		 
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/prmov/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'prmov'}">msel</c:if>">홍보영상</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/board/communication/list.do" >
	                    			<span class="site-menu-title <c:if test="${PL3 eq 'communication'}">msel</c:if>">소통공간</span>
	                    		</a>
	                  		</li>
		                </ul>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'doctor' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify' or PL3 eq 'write')}">active open</c:if>">
		                <a href="/seadm/doctor/list.do">
							<i class="site-menu-icon fa fa-stethoscope <c:if test="${PL2 eq 'doctor' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify') }">msel</c:if> "></i>
							<span class="site-menu-title  <c:if test="${PL2 eq 'doctor' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify' or PL3 eq 'write') }">msel</c:if>" >귀어닥터 관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'doctor' && ( pageMode2 eq 'rqlist' or pageMode2 eq 'rqview' )}">active open</c:if>">
		                <a href="/seadm/doctor/rqlist.do">
							<i class="site-menu-icon fa fa-stethoscope <c:if test="${PL2 eq 'doctor' && ( pageMode2 eq 'rqlist' or pageMode2 eq 'rqview' ) }">msel</c:if>"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'doctor' && ( pageMode2 eq 'rqlist' or pageMode2 eq 'rqview' ) }">msel</c:if>">귀어닥터 신청자관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'homestay' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify' or PL3 eq 'write')}">active open</c:if>">
		                <a href="/seadm/homestay/list.do">
							<i class="site-menu-icon fa fa-home <c:if test="${PL2 eq 'homestay' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify') }">msel</c:if>"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'homestay' && (PL3 eq 'list' or PL3 eq 'view' or PL3 eq 'modify' or PL3 eq 'write') }">msel</c:if>">홈스테이 관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'homestay' && ( PL3 eq 'rqlist' or PL3 eq 'rqview' )}">active open</c:if>">
		                <a href="/seadm/homestay/rqlist.do">
							<i class="site-menu-icon fa fa-home <c:if test="${PL2 eq 'homestay'  && ( PL3 eq 'rqlist' or PL3 eq 'rqview' ) }">msel</c:if>"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'homestay' && ( PL3 eq 'rqlist' or PL3 eq 'rqview' ) }">msel</c:if>">홈스테이 신청자관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'popup'}">active open</c:if>">
		                <a href="/seadm/popup/list.do">
							<i class="site-menu-icon fa fa-window-restore "></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'popup'}">msel</c:if> ">팝업 및 배너관리</span>
		                </a>
					</li>
					<li class="site-menu-item has-sub <c:if test="${PL2 eq 'analytics'}">active open</c:if>">
		                <a href="javascript:void(0)">
							<i class="site-menu-icon fa fa-chart-line"></i>
							<span class="site-menu-title <c:if test="${PL2 eq 'analytics'}">msel open</c:if>">통계관리<span id='analytics'></span></span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/summary/sitesummary.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'sitesummary'}">msel</c:if>">사이트 현황</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/visit/uv.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'uv'}">msel</c:if>">방문현황(UV)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/visit/pv.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'pv'}">msel</c:if>">페이지뷰(PV)</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/inflow/searchdashboard.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'searchdashboard'}">msel</c:if>">검색 유입현황</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/environment/osdashboard.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'osdashboard'}">msel</c:if>">운영체제(OS) 분석</span>
	                    		</a>
	                  		</li>
	                  		<li class="site-menu-item ">
	                    		<a class="animsition-link" href="/seadm/analytics/inflow/urls.do" >
	                    			<span class="site-menu-title <c:if test="${PL4 eq 'urls'}">msel</c:if>">페이지 분석</span>
	                    		</a>
	                  		</li>
		                </ul>
					</li>	
					 -->
				</ul>
			</div>
		</div>
	</div>
</div>
