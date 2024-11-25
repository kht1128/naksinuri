<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<style>
/* 메뉴 확장 고정 
.site-menubar-unfold .site-menu-item:not(.open)>.site-menu-sub {display:block;}
.site-menubar-unfold .site-menu .site-menu-sub {display:block;}
.site-menubar-unfold .site-menu-item:not(.open)>a .site-menu-arrow {transform: rotate(90deg);}
*/ 
/* 커스텀 라벨 */
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
	<%-- 1 그룹 --%>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_adm'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="관리자계정"/>
		<c:set var="pageUrl" value="/eduadm/member/listAdm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'author_log'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="관리자권한기록"/>
		<c:set var="pageUrl" value="/eduadm/member/author_log.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'access_log'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="관리자접속기록"/>
		<c:set var="pageUrl" value="/eduadm/member/access_log.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_lv'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="권한관리"/>
		<c:set var="pageUrl" value="/adm/member/list_lv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="회원목록"/>
		<c:set var="pageUrl" value="/eduadm/member/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="수강내역"/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_mbr_dtl'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="회원목록"/>
		<c:set var="pageUrl" value="/eduadm/member/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="온라인교과목 관리"/>
	</c:when>
	<%-- 2 그룹 --%>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq ''}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="전체보기"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2024'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="14"/>
		<c:set var="pageName" value="2024년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2023'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="13"/>
		<c:set var="pageName" value="2023년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>		
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2022'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="12"/>
		<c:set var="pageName" value="2022년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2021'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="11"/>
		<c:set var="pageName" value="2021년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
	<%-- <c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2020'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="2020년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2019'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="2019년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2018'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="2018년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'list_edu_target' and addWebLink eq '2017'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="5"/>
		<c:set var="pageName" value="2017년 대상자"/>
		<c:set var="pageUrl" value="/eduadm/member/list_edu_target.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when> --%>
	<c:when test="${urlmethodname eq 'member' and urlpagename eq 'loc_gov_upload'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="전체보기"/>
		<c:set var="pageUrl" value="/eduadm/member/loc_gov_upload.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="지자체명단 현행화 업로드"/>
	</c:when>
	<c:when test="${urlmethodname eq 'member' and urlpagename eq 'loc_gov_adm_upload'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="전체보기"/>
		<c:set var="pageUrl" value="/eduadm/member/loc_gov_adm_upload.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="지자체명단 현행화 업로드"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'rmndr' and urlpagename eq 'list_online'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="6"/>
		<c:set var="pageName" value="낚시전문교육 신청서"/>
		<c:set var="pageUrl" value="/eduadm/board/rmndr/list_online.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'rmndr' and urlpagename eq 'list_offline'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="7"/>
		<c:set var="pageName" value="신규재개자 전문교육 신청서"/>
		<c:set var="pageUrl" value="/eduadm/board/rmndr/list_offline.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'esrequest' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="8"/>
		<c:set var="pageName" value="문자신청리스트"/>
		<c:set var="pageUrl" value="/eduadm/board/esrequest/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'hpchange' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="9"/>
		<c:set var="pageName" value="본인명의 휴대폰 변경"/>
		<c:set var="pageUrl" value="/eduadm/board/hpchange/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'cpredu' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="교육대상자관리"/>
		<c:set var="pageNum" value="10"/>
		<c:set var="pageName" value="법인사업장 교육책임자"/>
		<c:set var="pageUrl" value="/eduadm/board/cpredu/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<%-- 3 그룹 --%>
	<c:when test="${urlmethodname eq 'curriculum' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="교육목록"/>
		<c:set var="pageUrl" value="/eduadm/curriculum/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'curriculum' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="교육목록"/>
		<c:set var="pageUrl" value="/eduadm/curriculum/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="교과목관리"/>
	</c:when>
	<c:when test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="교육목록"/>
		<c:set var="pageUrl" value="/eduadm/curriculum/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="수강자목록"/>
	</c:when>
	<c:when test="${urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="교육목록"/>
		<c:set var="pageUrl" value="/eduadm/curriculum/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="수강자목록(교과목 이수내역)"/>
	</c:when>
	<c:when test="${urlmethodname eq 'tdata' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="교육(콘텐츠)자료"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'category' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="교육카테고리"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'category' and urlmethodsubname eq 'academy' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="교육기관 카테고리"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value="교육기관 목록"/>
	</c:when>		
	<c:when test="${urlmethodname eq 'category' and urlmethodsubname eq 'academy' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="교육기관 카테고리"/>
		<c:set var="pageUrl" value="/eduadm/category/academy/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="교육기관 상세보기"/>
	</c:when>
	<c:when test="${urlmethodname eq 'category' and urlmethodsubname eq 'academy' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="교육기관 카테고리"/>
		<c:set var="pageUrl" value="/eduadm/category/academy/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="교육기관 수정하기"/>
	</c:when>
	<c:when test="${urlmethodname eq 'category' and urlmethodsubname eq 'academy' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="교육관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="교육기관 카테고리"/>
		<c:set var="pageUrl" value="/eduadm/category/academy/list.do"/>
		<c:set var="subpageNum" value="4"/>
		<c:set var="subpageName" value="교육기관 신규작성"/>
	</c:when>
	<%-- 4 그룹 --%>
	<c:when test="${urlmethodname eq 'certificate' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="4"/>
		<c:set var="depthName" value="출력관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="이수증 목록"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
	<c:when test="${urlmethodname eq 'certificate' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">
		<c:set var="depthNum" value="4"/>
		<c:set var="depthName" value="출력관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="이수증 목록"/>
		<c:set var="pageUrl" value="/eduadm/certificate/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="발급내역 목록"/>
	</c:when>	
	<c:when test="${urlmethodname eq 'dwldConfim' and ( urlpagename eq 'list' or urlpagename eq 'list_adm')}">
		<c:set var="depthNum" value="4"/>
		<c:set var="depthName" value="출력관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="엑셀 다운로드"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
	<%-- 5 그룹 --%>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'notice' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'notice' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항"/>
		<c:set var="pageUrl" value="/eduadm/board/notice/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="공지사항 글쓰기"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'notice' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항"/>
		<c:set var="pageUrl" value="/eduadm/board/notice/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="공지사항 게시글"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'notice' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="공지사항"/>
		<c:set var="pageUrl" value="/eduadm/board/notice/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="공지사항 수정"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'file' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="자료실"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'file' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="자료실"/>
		<c:set var="pageUrl" value="/eduadm/board/file/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="자료실 글쓰기"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'file' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="자료실"/>
		<c:set var="pageUrl" value="/eduadm/board/file/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="자료실 게시글"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'file' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="자료실"/>
		<c:set var="pageUrl" value="/eduadm/board/file/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="자료실 수정"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'faq' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="FAQ"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'faq' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="FAQ"/>
		<c:set var="pageUrl" value="/eduadm/board/faq/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="FAQ 글쓰기"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'faq' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="FAQ"/>
		<c:set var="pageUrl" value="/eduadm/board/faq/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="FAQ 게시글"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'faq' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="FAQ"/>
		<c:set var="pageUrl" value="/eduadm/board/faq/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="FAQ 수정"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'admnotice' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="안내사항(관리자)"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'admnotice' and urlpagename eq 'write'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="안내사항(관리자)"/>
		<c:set var="pageUrl" value="/eduadm/board/admnotice/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value="안내사항(관리자) 글쓰기"/>
	</c:when>
	 <c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'admnotice' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="안내사항(관리자)"/>
		<c:set var="pageUrl" value="/eduadm/board/admnotice/list.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="안내사항(관리자) 게시글"/>
	</c:when>
	<c:when test="${urlmethodname eq 'board' and urlmethodsubname eq 'admnotice' and urlpagename eq 'modify'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthName" value="게시판관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="안내사항(관리자)"/>
		<c:set var="pageUrl" value="/eduadm/board/admnotice/list.do"/>
		<c:set var="subpageNum" value="3"/>
		<c:set var="subpageName" value="안내사항(관리자) 수정"/>
	</c:when>
	<%-- 6 그룹 --%>
	<%-- //analytics --%>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'summary' and urlpagename eq 'sitesummary'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="사이트현황"/>
		<c:set var="pageUrl" value="/eduadm/analytics/summary/sitesummary.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'visit' and urlpagename eq 'uv'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="방문현황(UV)"/>
		<c:set var="pageUrl" value="/eduadm/analytics/visit/uv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'visit' and urlpagename eq 'pv'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="페이지뷰(PV)"/>
		<c:set var="pageUrl" value="/eduadm/analytics/visit/pv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'inflow' and urlpagename eq 'searchdashboard'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="검색 유입현황"/>
		<c:set var="pageUrl" value="/eduadm/analytics/inflow/searchdashboard.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'environment' and urlpagename eq 'osdashboard'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="5"/>
		<c:set var="pageName" value="운영체제(OS) 분석"/>
		<c:set var="pageUrl" value="/eduadm/analytics/environment/osdashboard.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'analytics' and urlmethodsubname eq 'inflow' and urlpagename eq 'urls'}">
		<c:set var="depthNum" value="6"/>
		<c:set var="depthName" value="통계관리"/>
		<c:set var="pageNum" value="6"/>
		<c:set var="pageName" value="페이지 분석"/>
		<c:set var="pageUrl" value="/eduadm/analytics/inflow/urls.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<%-- 7 그룹 --%>
	<c:when test="${urlmethodname eq 'sms' and urlmethodsubname eq 'resve' and urlpagename eq 'list' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="7"/>
		<c:set var="depthName" value="SMS관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="메세지 예약관리"/>
		<c:set var="pageUrl" value="/adm/sms/resveEduadm/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'sms' and urlmethodsubname eq 'ment' and urlpagename eq 'list' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="7"/>
		<c:set var="depthName" value="SMS관리"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="메세지 탬플릿"/>
		<c:set var="pageUrl" value="/adm/sms/mentEduadm/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'sms' and urlmethodsubname eq 'log' and urlpagename eq 'list' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="7"/>
		<c:set var="depthName" value="SMS관리"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="메세지 이력"/>
		<c:set var="pageUrl" value="/adm/sms/logEduadm/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<%-- 8 그룹 --%>
	<c:when test="${urlmethodname eq 'log' and urlmethodsubname eq '' and urlpagename eq 'list_rec' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="로그"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="접속자 로그 기록"/>
		<c:set var="pageUrl" value="/adm/log/listMbrEduadm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'log' and urlmethodsubname eq '' and urlpagename eq 'list_sys' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="로그"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="시스템 로그 기록"/>
		<c:set var="pageUrl" value="/adm/log/listSysEduadm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'log' and urlmethodsubname eq '' and urlpagename eq 'list_mbr_mod' and addWebLink eq 'Eduadm'}">
		<c:set var="depthNum" value="8"/>
		<c:set var="depthName" value="로그"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="회원정보수정 로그 기록"/>
		<c:set var="pageUrl" value="/adm/log/listMbrModEduadm.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<%-- 9 그룹 --%>
	<c:when test="${urlmethodname eq 'report' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="9"/>
		<c:set var="depthName" value="보고서 다운로드"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="보고서 목록"/>
		<c:set var="pageUrl" value="/eduadm/main/report/list.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<%-- 10 그룹 --%>
	<c:when test="${urlmethodname eq 'notice' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="10"/>
		<c:set var="depthName" value="안내사항"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="안내사항 목록"/>
		<c:set var="pageUrl" value="/eduadm/notice/list.do"/>
		<c:set var="subpageNum" value="1"/>
		<c:set var="subpageName" value=""/>
	</c:when>
	<c:when test="${urlmethodname eq 'notice' and urlpagename eq 'view'}">
		<c:set var="depthNum" value="10"/>
		<c:set var="depthName" value="안내사항"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="안내사항 게시글"/>
		<c:set var="pageUrl" value="/eduadm/notice/view.do"/>
		<c:set var="subpageNum" value="2"/>
		<c:set var="subpageName" value="안내사항 게시글"/>
	</c:when>

</c:choose>

<div class="site-menubar">
	<div class="site-menubar-body">
		<div>
			<div>
				<ul class="site-menu" data-plugin="menu">
              		<li class="site-menu-category"></li>
              		<!-- //Menu -->
              		<c:choose>
              			<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
		              		<li class="site-menu-item has-sub <c:if test="${depthNum eq '1'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-user-secret" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '1'}">msel</c:if>" >회원관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
			                		<li class="site-menu-item is-shown <c:if test="${depthNum eq '1' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/member/listAdm.do">
											<span class="site-menu-title <c:if test="${depthNum eq '1' and pageNum eq '1'}">msel</c:if>">관리자계정관리</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '1' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/member/author_log.do">
											<span class="site-menu-title <c:if test="${depthNum eq '1' and pageNum eq '2'}">msel</c:if>">관리자권한기록</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '1' and pageNum eq '3'}">active</c:if>">
										<a href="/eduadm/member/access_log.do">
											<span class="site-menu-title <c:if test="${depthNum eq '1' and pageNum eq '3'}">msel</c:if>">관리자접속기록</span>
										</a>
									</li>
									<%-- <li class="site-menu-item is-shown <c:if test="${depthNum eq '1' and pageNum eq '2'}">active</c:if>">
			                    		<a href="/adm/member/list_lv.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '1' and pageNum eq '2'}">msel</c:if>">권한관리</span>
			                    		</a>
			                  		</li> --%>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '2'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-users" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '2'}">msel</c:if>" >교육대상자관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
			                		<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/member/eduList.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '1'}">msel</c:if>">전체보기</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '14'}">active</c:if>">
										<a href="/eduadm/member/eduList2024.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '14'}">msel</c:if>">2024년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '13'}">active</c:if>">
										<a href="/eduadm/member/eduList2023.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '13'}">msel</c:if>">2023년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '12'}">active</c:if>">
										<a href="/eduadm/member/eduList2022.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '12'}">msel</c:if>">2022년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '11'}">active</c:if>">
										<a href="/eduadm/member/eduList2021.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '11'}">msel</c:if>">2021년 대상자</span>
										</a>
									</li>
									<%-- <li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/member/eduList2020.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '2'}">msel</c:if>">2020년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '3'}">active</c:if>">
										<a href="/eduadm/member/eduList2019.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '3'}">msel</c:if>">2019년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '4'}">active</c:if>">
										<a href="/eduadm/member/eduList2018.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '4'}">msel</c:if>">2018년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '5'}">active</c:if>">
										<a href="/eduadm/member/eduList2017.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '5'}">msel</c:if>">2017년 대상자</span>
										</a>
									</li> --%>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '6'}">active</c:if>">
										<a href="/eduadm/board/rmndr/list_online.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '6'}">msel</c:if>">낚시전문교육 신청서</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '7'}">active</c:if>">
										<a href="/eduadm/board/rmndr/list_offline.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '7'}">msel</c:if>">신규재개자 전문교육 신청서</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '8'}">active</c:if>">
										<a href="/eduadm/board/esrequest/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '8'}">msel</c:if>">문자 신청 리스트</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '9'}">active</c:if>">
										<a href="/eduadm/board/hpchange/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '9'}">msel</c:if>">본인명의 휴대폰 변경</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '10'}">active</c:if>">
										<a href="/eduadm/board/cpredu/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '10'}">msel</c:if>">법인사업장 교육책임자</span>
										</a>
									</li>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '3'}">active open</c:if>">
								<a href="javascript:void(0)">
									<i class="site-menu-icon fa-graduation-cap" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '3'}">msel</c:if>">교육관리</span>
									<span class="site-menu-arrow"></span>
								</a>
								<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/curriculum/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '1'}">msel</c:if>">교육목록</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/category/academy/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '2'}">msel</c:if>">교육기관 카테고리</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '3'}">active</c:if>">
										<a href="/eduadm/category/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '3'}">msel</c:if>">교육카테고리</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '4'}">active</c:if>">
										<a href="/eduadm/tdata/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '4'}">msel</c:if>">교육(콘텐츠)자료</span>
										</a>
									</li>
									<%--
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '4'}">active</c:if>">
										<a href="/eduadm/certificate/list.do">
											<span class="site-menu-title">이수증출력내역</span>
										</a>
									</li>
									--%>
								</ul>
							</li>	
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '4'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-print" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '4'}">msel</c:if>" >출력관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '4' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/certificate/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '4' and pageNum eq '1'}">msel</c:if>">이수증출력내역</span>
										</a>
									</li>
			                	</ul>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '4' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/main/dwldConfim/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '4' and pageNum eq '2'}">msel</c:if>">엑셀 다운로드(요청건)</span>
										</a>
									</li>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '5'}">active open</c:if>">
								<a href="javascript:void(0)">
									<i class="site-menu-icon fa-laptop" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '5'}">msel</c:if>">게시판관리</span>
									<span class="site-menu-arrow"></span>
								</a>
								<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '5' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/board/notice/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '5' and pageNum eq '1'}">msel</c:if>">공지사항</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '5' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/board/file/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '5' and pageNum eq '2'}">msel</c:if>">자료실</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '5' and pageNum eq '3'}">active</c:if>">
										<a href="/eduadm/board/faq/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '5' and pageNum eq '3'}">msel</c:if>">FAQ</span>
										</a>
									</li>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '5' and pageNum eq '4'}">active</c:if>">
										<a href="/eduadm/board/admnotice/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '5' and pageNum eq '4'}">msel</c:if>">안내사항(관리자)</span>
										</a>
									</li>
									<!-- 
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/board/rmndr/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '2'}">msel</c:if>">교육사전알림신청</span>
										</a>
									</li>
									 -->
								</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '6'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-bar-chart" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '6'}">msel</c:if>" >통계관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '1'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/summary/sitesummary.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '1'}">msel</c:if>">사이트 현황</span>
			                    		</a>
			                  		</li>
			                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '2'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/visit/uv.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '2'}">msel</c:if>">방문현황(UV)</span>
			                    		</a>
			                  		</li>
			                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '3'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/visit/pv.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '3'}">msel</c:if>">페이지뷰(PV)</span>
			                    		</a>
			                  		</li>
			                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '4'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/inflow/searchdashboard.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '4'}">msel</c:if>">검색 유입현황</span>
			                    		</a>
			                  		</li>
			                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '5'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/environment/osdashboard.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '5'}">msel</c:if>">운영체제(OS) 분석</span>
			                    		</a>
			                  		</li>
			                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '6' and pageNum eq '6'}">active</c:if>">
			                    		<a class="animsition-link" href="/eduadm/analytics/inflow/urls.do" >
			                    			<span class="site-menu-title <c:if test="${depthNum eq '6' and pageNum eq '6'}">msel</c:if>">페이지 분석</span>
			                    		</a>
			                  		</li>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '7'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-envelope" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '7'}">msel</c:if>" >SMS관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '7' and pageNum eq '1'}">active</c:if>">
										<a href="/adm/sms/resveEduadm/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '7' and pageNum eq '1'}">msel</c:if>">메세지 예약관리</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '7' and pageNum eq '2'}">active</c:if>">
										<a href="/adm/sms/mentEduadm/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '7' and pageNum eq '2'}">msel</c:if>">메세지 탬플릿</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '7' and pageNum eq '3'}">active</c:if>">
										<a href="/adm/sms/logEduadm/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '7' and pageNum eq '3'}">msel</c:if>">메세지 이력</span>
										</a>
									</li>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '8'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-history" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '8'}">msel</c:if>" >로그관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '1'}">active</c:if>">
										<a href="/adm/log/listRecEduadm.do">
											<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '1'}">msel</c:if>">접속자 로그 기록</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '2'}">active</c:if>">
										<a href="/adm/log/listSysEduadm.do">
											<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '2'}">msel</c:if>">시스템 로그 기록</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '8' and pageNum eq '3'}">active</c:if>">
										<a href="/adm/log/listMbrModEduadm.do">
											<span class="site-menu-title <c:if test="${depthNum eq '8' and pageNum eq '3'}">msel</c:if>">회원정보수정 로그 기록</span>
										</a>
									</li>
			                	</ul>
							</li>
							<li class="site-menu-item <c:if test="${depthNum eq '9'}">active</c:if>"">
								<a class="animsition-link" href="/eduadm/main/report/list.do">
									<i class="site-menu-icon fa-file-excel-o" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '9'}">msel</c:if>">보고서 다운로드</span>
									<div class="site-menu-label">
										<span class="label label-danger label-round"></span>
									</div>
								</a>
							</li>	
              			</c:when>
              			<%-- 해양경찰서 담당자 --%>
              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
              				<li class="site-menu-item has-sub <c:if test="${depthNum eq '2'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-users" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '2'}">msel</c:if>" >교육대상자관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
			                		<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/member/eduList.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '1'}">msel</c:if>">전체보기</span>
										</a>
									</li>									
			                	</ul>
							</li>
							<li class="site-menu-item <c:if test="${depthNum eq '10'}">active</c:if>"">
								<a class="animsition-link" href="/eduadm/notice/list.do">
									<i class="site-menu-icon fa-laptop" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '10'}">msel</c:if>">안내사항</span>
									<div class="site-menu-label">
										<span class="label label-danger label-round"></span>
									</div>
								</a>
							</li>
              			</c:when>
              			<%-- 지자체 담당자 --%>
              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
              				<li class="site-menu-item has-sub <c:if test="${depthNum eq '2'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-users" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '2'}">msel</c:if>" >교육대상자관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
			                		<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/member/eduList.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '1'}">msel</c:if>">전체보기</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '14'}">active</c:if>">
										<a href="/eduadm/member/eduList2024.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '14'}">msel</c:if>">2024년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '13'}">active</c:if>">
										<a href="/eduadm/member/eduList2023.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '13'}">msel</c:if>">2023년 대상자</span>
										</a>
									</li>
									<%-- <li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '12'}">active</c:if>">
										<a href="/eduadm/member/eduList2022.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '12'}">msel</c:if>">2022년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '11'}">active</c:if>">
										<a href="/eduadm/member/eduList2021.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '11'}">msel</c:if>">2021년 대상자</span>
										</a>
									</li> --%>
									<%-- <li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/member/eduList2020.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '2'}">msel</c:if>">2020년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '3'}">active</c:if>">
										<a href="/eduadm/member/eduList2019.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '3'}">msel</c:if>">2019년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '4'}">active</c:if>">
										<a href="/eduadm/member/eduList2018.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '4'}">msel</c:if>">2018년 대상자</span>
										</a>
									</li>
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '5'}">active</c:if>">
										<a href="/eduadm/member/eduList2017.do">
											<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '5'}">msel</c:if>">2017년 대상자</span>
										</a>
									</li> --%>
			                	</ul>
							</li>
							<li class="site-menu-item has-sub <c:if test="${depthNum eq '4'}">active open</c:if>">
			                	<a href="javascript:void(0)">
									<i class="site-menu-icon fa-print" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '4'}">msel</c:if>" >출력관리</span>
									<span class="site-menu-arrow"></span>
								</a>
			                	<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '4' and pageNum eq '2'}">active</c:if>">
										<a href="/eduadm/main/dwldConfim/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '4' and pageNum eq '2'}">msel</c:if>">엑셀 다운로드</span>
										</a>
									</li>
			                	</ul>
							</li>
							<li class="site-menu-item <c:if test="${depthNum eq '10'}">active</c:if>"">
								<a class="animsition-link" href="/eduadm/notice/list.do">
									<i class="site-menu-icon fa-laptop" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '10'}">msel</c:if>">안내사항</span>
									<div class="site-menu-label">
										<span class="label label-danger label-round"></span>
									</div>
								</a>
							</li>
              			</c:when>
              			<%-- 교육기관 담당자 --%>
              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
              				<c:if test="${isKoreafcaMember eq true}">
	              				<li class="site-menu-item has-sub <c:if test="${depthNum eq '2'}">active open</c:if>">
		              				<a href="javascript:void(0)">
										<i class="site-menu-icon fa-users" aria-hidden="true"></i>
										<span class="site-menu-title <c:if test="${depthNum eq '2'}">msel</c:if>" >교육대상자관리</span>
										<span class="site-menu-arrow"></span>
									</a>
				                	<ul class="site-menu-sub">
				                		<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '1'}">active</c:if>">
											<a href="/eduadm/member/eduList.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '1'}">msel</c:if>">전체보기</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '14'}">active</c:if>">
											<a href="/eduadm/member/eduList2024.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '14'}">msel</c:if>">2024년 대상자</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '13'}">active</c:if>">
											<a href="/eduadm/member/eduList2023.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '13'}">msel</c:if>">2023년 대상자</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '12'}">active</c:if>">
											<a href="/eduadm/member/eduList2022.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '12'}">msel</c:if>">2022년 대상자</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '11'}">active</c:if>">
											<a href="/eduadm/member/eduList2021.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '11'}">msel</c:if>">2021년 대상자</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '2'}">active</c:if>">
											<a href="/eduadm/member/eduList2020.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '2'}">msel</c:if>">2020년 대상자</span>
											</a>
										</li>
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '2' and pageNum eq '6'}">active</c:if>">
											<a href="/eduadm/board/rmndr/list_online.do">
												<span class="site-menu-title <c:if test="${depthNum eq '2' and pageNum eq '6'}">msel</c:if>">낚시전문교육 신청서</span>
											</a>
										</li>
									</ul>
								</li>
							</c:if>
              				<li class="site-menu-item has-sub <c:if test="${depthNum eq '3'}">active open</c:if>">
								<a href="javascript:void(0)">
									<i class="site-menu-icon fa fa-graduation-cap" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '3'}">msel</c:if>">교육관리</span>
									<span class="site-menu-arrow"></span>
								</a>
								<ul class="site-menu-sub">
									<li class="site-menu-item is-shown <c:if test="${depthNum eq '3' and pageNum eq '1'}">active</c:if>">
										<a href="/eduadm/curriculum/list.do">
											<span class="site-menu-title <c:if test="${depthNum eq '3' and pageNum eq '1'}">msel</c:if>">교육목록</span>
										</a>
									</li>
								</ul>
							</li>	
							<c:if test="${isKoreafcaMember eq true}">
								<li class="site-menu-item has-sub <c:if test="${depthNum eq '4'}">active open</c:if>">
				                	<a href="javascript:void(0)">
										<i class="site-menu-icon fa-print" aria-hidden="true"></i>
										<span class="site-menu-title <c:if test="${depthNum eq '4'}">msel</c:if>" >출력관리</span>
										<span class="site-menu-arrow"></span>
									</a>
				                	<ul class="site-menu-sub">
										<li class="site-menu-item is-shown <c:if test="${depthNum eq '4' and pageNum eq '2'}">active</c:if>">
											<a href="/eduadm/main/dwldConfim/list.do">
												<span class="site-menu-title <c:if test="${depthNum eq '4' and pageNum eq '2'}">msel</c:if>">엑셀 다운로드</span>
											</a>
										</li>
				                	</ul>
								</li>
							</c:if>
							<li class="site-menu-item <c:if test="${depthNum eq '10'}">active</c:if>"">
								<a class="animsition-link" href="/eduadm/notice/list.do">
									<i class="site-menu-icon fa-laptop" aria-hidden="true"></i>
									<span class="site-menu-title <c:if test="${depthNum eq '10'}">msel</c:if>">안내사항</span>
									<div class="site-menu-label">
										<span class="label label-danger label-round"></span>
									</div>
								</a>
							</li>
              			</c:when>
              			<%-- 기타 거부 --%>
              			<c:otherwise>
              				
              			</c:otherwise>
              		</c:choose>
					<!-- //End Menu -->
			 	</ul>	 
			</div>
		</div>
	</div>
</div>
