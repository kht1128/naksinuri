<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-115620856-2"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-115620856-2');
	</script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<!-- <meta name="viewport" content="width=1500px,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes"> -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-57x57.png" />
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-114x114.png" />
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-72x72.png" />
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-144x144.png" />
	<link rel="apple-touch-icon-precomposed" sizes="60x60" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-60x60.png" />
	<link rel="apple-touch-icon-precomposed" sizes="120x120" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-120x120.png" />
	<link rel="apple-touch-icon-precomposed" sizes="76x76" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-76x76.png" />
	<link rel="apple-touch-icon-precomposed" sizes="152x152" href="/naksinuri_original/common_main/img/favicomatic/apple-touch-icon-152x152.png" />
	<link rel="icon" type="image/png" href="/naksinuri_original/common_main/img/favicomatic/favicon-196x196.png" sizes="196x196" />
	<link rel="icon" type="image/png" href="/naksinuri_original/common_main/img/favicomatic/favicon-96x96.png" sizes="96x96" />
	<link rel="icon" type="image/png" href="/naksinuri_original/common_main/img/favicomatic/favicon-32x32.png" sizes="32x32" />
	<link rel="icon" type="image/png" href="/naksinuri_original/common_main/img/favicomatic/favicon-16x16.png" sizes="16x16" />
	<link rel="icon" type="image/png" href="/naksinuri_original/common_main/img/favicomatic/favicon-128.png" sizes="128x128" />
	<meta name="application-name" content="&nbsp;"/>
	<meta name="msapplication-TileColor" content="#FFFFFF" />
	<meta name="msapplication-TileImage" content="mstile-144x144.png" />
	<meta name="msapplication-square70x70logo" content="/naksinuri_original/common_main/img/favicomatic/mstile-70x70.png" />
	<meta name="msapplication-square150x150logo" content="/naksinuri_original/common_main/img/favicomatic/mstile-150x150.png" />
	<meta name="msapplication-wide310x150logo" content="/naksinuri_original/common_main/img/favicomatic/mstile-310x150.png" />
	<meta name="msapplication-square310x310logo" content="/naksinuri_original/common_main/img/favicomatic/mstile-310x310.png" />
	<link rel="shortcut icon" href="/naksinuri_original/common_main/img/favicomatic/favicon.ico" />
	
	<title>낚시누리</title>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/swiper.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css?ver=20230103" />
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/respon.css?ver=20221216">
	<link rel="stylesheet" href="/common/seadm/global/vendor/toastr/toastr.min.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/toastr.min.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/c3/c3.min.css">

	<meta property="fb:app_id" 			content="143280399642277" />
	<meta property="og:title" 			content="낚시누리" />
	<meta property="og:url" 			content="naksinuri.kr" />
	<meta property="og:type" 			content="website" />
	<meta property="og:description" 	content="낚시정보 종합 포털. 서울특별시 금천구 가산디지털2로 위치. 해양수산부, 한국어촌어항공단 운영, 정책, 정보, 커뮤니티 제공." />
	<meta property="og:image" 			content="http://naksinuri.kr/common_main/img/logo.png" />
	<meta name="description" content="낚시정보 종합 포털. 서울특별시 금천구 가산디지털2로 위치. 해양수산부, 한국어촌어항공단 운영, 정책, 정보, 커뮤니티 제공.">
	
	<link rel="canonical" href="https://www.naksinuri.kr">
	<span class="hidden" itemscope="" itemtype="http://schema.org/Organization">
	<link itemprop="url" href="https://www.naksinuri.kr">
	<!-- <a itemprop="sameAs" href="https://blog.naver.com/naksinuriweb"></a>
	<a itemprop="sameAs" href="https://www.facebook.com/nurinaksi"></a> -->
	</span>

	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="//use.fontawesome.com/e59ba62350.js"></script>  
	<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
	<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
	<script src="/naksinuri_original/common_main/js/swiper.js"></script>
	<script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>
	<script src="/naksinuri_original/common_main/js/common.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fc34cd2e28855d310f66171d1a3e757a&libraries=services,clusterer"></script>
	<!--
	<script type="text/javascript" src="//openapi.map.naver.com/openapi/v3/maps.js?clientId=kSzk_875XFgPsLskTEKN&submodules=geocoder"></script>
	-->
<!-- 	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=9MMZVYtGwVlMXcNxm5VT&submodules=geocoder"></script> -->
	<script type="text/javascript" src="/naksinuri_original/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<!-- //별도로 사용하는곳이 없는것으로 판단
	<script type="text/javascript" src="/naksinuri_original/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
	 -->
	<script type="text/javascript" src="/naksinuri_original/jwplayer/jwplayer.js"></script>
	<script type="text/javascript">jwplayer.key="OqpCTFsbHwzLSeC7A7rf46NB8Ce+OgLPCjgyhQ==";</script>
	<script type="text/javascript" src="/naksinuri_original/js/datepicker_KR.js"></script>
	<script type="text/javascript" src="/naksinuri_original/js/jquery.slider2.js"></script>
	<script src="/common/seadm/global/vendor/d3/d3.min.js"></script>
	<script src="/common/seadm/global/vendor/c3/c3.min.js"></script>
	<!-- <script src="/common/js/fontawesome-all.js"></script> -->
	
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
<c:set var="groupName" value=""/>
<c:set var="menuNum" value=""/>
<%-- ${urlcontroller } | ${urlmethodname } | ${urlmethodsubname } | ${urlpagename } --%>
<c:choose>
	<c:when test="${urlcontroller eq 'naksinuri_original' and urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'index' }">
		<c:set var="paName" value="메인"/>
		<c:set var="pageMode" value="indexpage"/>
		<c:set var="depthName" value=""/>
		<c:set var="pageName" value=""/>
		<c:set var="menuNum" value=""/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq '' and urlmethodsubname eq '' and urlpagename eq 'index' }">
		<c:set var="paName" value="교육센터메인"/>
		<c:set var="pageMode" value="indexpage"/>
		<c:set var="groupName" value=""/>
		<c:set var="depthName" value=""/>
		<c:set var="pageName" value=""/>
		<c:set var="menuNum" value=""/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'service_guide' }">
		<c:set var="paName" value="이용안내"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육안내"/>
		<c:set var="pageName" value="이용안내"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'service_online_guide' }">
		<c:set var="paName" value="온라인교육수강 이용안내"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육안내"/>
		<c:set var="pageName" value="온라인교육수강 이용안내"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'curriculum_guide' }">
		<c:set var="paName" value="교육과정안내"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육안내"/>
		<c:set var="pageName" value="교육과정안내"/>	
		<c:set var="menuNum" value="10"/>	
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'fshmn_cltvt' }">
		<c:set var="paName" value="낚시 관리 및 육성법"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육안내"/>		
		<c:set var="pageName" value="낚시 관리 및 육성법"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board' and urlmethodsubname eq 'notice' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">
		<c:set var="paName" value="공지사항"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="공지사항"/>		
		<c:set var="pageName" value="공지사항"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board' and urlmethodsubname eq 'faq' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">
		<c:set var="paName" value="FAQ"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="공지사항"/>		
		<c:set var="pageName" value="FAQ"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'board' and urlmethodsubname eq 'file' and ( urlpagename eq 'list' or urlpagename eq 'view' ) }">
		<c:set var="paName" value="자료실"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="공지사항"/>		
		<c:set var="pageName" value="자료실"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="paName" value="나의신청내역"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육신청내역 / 이수증내역"/>		
		<c:set var="pageName" value="교육신청내역"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_dtl'}">
		<c:set var="paName" value="강의실"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="마이페이지"/>		
		<c:set var="pageName" value="강의실"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_crtf'}">
		<c:set var="paName" value="이수증내역"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육신청내역 / 이수증내역"/>		
		<c:set var="pageName" value="이수증내역"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'list_crtf_dtl'}">
		<c:set var="paName" value="이수증내역(발급)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="마이페이지"/>		
		<c:set var="pageName" value="이수증내역(발급)"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'mbrhstry' and urlmethodsubname eq '' and urlpagename eq 'survey_view'}">
		<c:set var="paName" value="설문조사"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인교육 이수"/>		
		<c:set var="pageName" value="설문조사"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="paName" value="온라인 교육일정"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인교육 일정"/>		
		<c:set var="pageName" value="온라인교육 신청"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshlcList'}">
		<c:set var="paName" value="현장교육 일정(낚시터)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="현장교육 일정(낚시터)"/>		
		<c:set var="pageName" value="현장교육 신청(낚시터)"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshhpList2'}">
		<c:set var="paName" value="현장교육 일정(낚시어선)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="현장교육 일정(낚시어선)"/>		
		<c:set var="pageName" value="현장교육 신청(낚시어선)"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshhpList'}">
		<c:set var="paName" value="현장교육 일정(낚시어선)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="낚시어선 신규·재개자 전문교육"/>		
		<c:set var="pageName" value="낚시어선 신규·재개자 전문교육"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'agree'}">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육신청"/>		
		<c:set var="pageName" value="이용동의서"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'write'}">
		<c:set var="paName" value="신청안내"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육신청"/>		
		<c:set var="pageName" value="신청안내"/>
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'member' and urlmethodsubname eq '' and urlpagename eq 'find_id'}">
		<c:set var="paName" value="회원정보찾기"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="마이페이지"/>		
		<c:set var="pageName" value="회원정보찾기"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'agree'}">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인수강신청"/>		
		<c:set var="pageName" value="이용동의서"/>
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'write_ship'}">
		<c:set var="paName" value="낚시어선 전문교육 수강신청"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인수강신청"/>		
		<c:set var="pageName" value="낚시어선 전문교육 수강신청"/>
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'write_house'}">
		<c:set var="paName" value="낚시터 전문교육 수강신청"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인수강신청"/>		
		<c:set var="pageName" value="낚시터 전문교육 수강신청"/>
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'write_resmpt'}">
		<c:set var="paName" value="신규/재개자 교육 수강신청"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="온라인수강신청"/>		
		<c:set var="pageName" value="신규/재개자 교육 수강신청"/>
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'hp_agree' }">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="본인명의 휴대폰 변경"/>
		<c:set var="pageName" value="이용동의서"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'hpreqstdoc' }">
		<c:set var="paName" value="변경신청서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="본인명의 휴대폰 변경"/>
		<c:set var="pageName" value="변경신청서"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_agree' }">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="법인사업장 교육책임자 지정"/>
		<c:set var="pageName" value="이용동의서"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_bplc' }">
		<c:set var="paName" value="확인서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="법인사업장 교육책임자 지정"/>
		<c:set var="pageName" value="확인서"/>		
		<c:set var="menuNum" value="10"/>
	</c:when>
	
	
	<c:when test="${pageName eq '낚시터정보 잘못된 정보 신고' }">
		<c:set var="paName" value="낚시터정보 잘못된 정보 신고"/>
	</c:when>
	<c:when test="${SiteSet eq 'naksinuri_original' and depthName eq 'lesson' and pageName eq 'junior' }">
		<c:set var="paName" value="초보탈출하기"/>
	</c:when>
	<c:when test="${pageName eq 'dignity' }">
		<c:set var="paName" value="낚시의 품격"/>
	</c:when>
	<c:when test="${pageName eq 'safe' }">
		<c:set var="paName" value="낚시안전"/>
	</c:when>
	<c:when test="${pageName eq 'gosu' }">
		<c:set var="paName" value="낚시고수되기"/>
	</c:when>
	<c:when test="${pageName eq 'sense' }">
		<c:set var="paName" value="낚시상식"/>
	</c:when>
	<c:when test="${pageName eq 'binding' }">
		<c:set var="paName" value="채비필수묶음법"/>
	</c:when>
	<c:when test="${pageName eq 'class' }">
		<c:set var="paName" value="어종별낚시교실"/>
	</c:when>
	<c:when test="${pageName eq 'fishjob' }">
		<c:set var="paName" value="낚시터정보"/>
	</c:when>
	<c:when test="${pageName eq 'industry' }">
		<c:set var="paName" value="낚시산업정보"/>
	</c:when>
	<c:when test="${pageName eq 'angling' }">
		<c:set var="paName" value="조황정보"/>
	</c:when>
	<c:when test="${pageName eq 'travel' }">
		<c:set var="paName" value="낚시여행기"/>
	</c:when>
	<c:when test="${pageName eq 'zisik' }">
		<c:set var="paName" value="누리지식인"/>
	</c:when>
	<c:when test="${pageName eq 'qna' }">
		<c:set var="paName" value="낚시육성법 및 유어장관련 질의회신"/>
	</c:when>
	<c:when test="${pageName eq 'nuri' }">
		<c:set var="paName" value="자주묻는낚시질문"/>
	</c:when>
	<c:when test="${pageName eq 'usage' }">
		<c:set var="paName" value="낚시용품 사용기"/>
	</c:when>
	<c:when test="${pageName eq 'column' }">
		<c:set var="paName" value="낚시칼럼"/>
	</c:when>
	<c:when test="${pageName eq 'notice' }">
		<c:set var="paName" value="공지사항"/>
	</c:when>
	<c:when test="${pageName eq 'gosi' }">
		<c:set var="paName" value="지역별 낚시준수사항"/>
	</c:when>
	<c:when test="${pageName eq 'congress' }">
		<c:set var="paName" value="낚시대회"/>
	</c:when>
	<c:when test="${pageName eq 'event' }">
		<c:set var="paName" value="이벤트"/>
	</c:when>
	<c:when test="${pageName eq 'news' }">
		<c:set var="paName" value="낚시뉴스"/>
	</c:when>
	<c:when test="${pageName eq 'campaign' }">
		<c:set var="paName" value="낚시캠페인"/>
	</c:when>
	<c:when test="${pageName eq 'plocation' }">
		<c:set var="paName" value="낚시금지구역"/>
	</c:when>
	<c:when test="${pageName eq 'llocation' }">
		<c:set var="paName" value="낚시제한구역"/>
	</c:when>
	<c:when test="${pageName eq 'policy' }">
		<c:set var="paName" value="낚시정책안내"/>
	</c:when>
	<c:when test="${pageName eq 'auditor' }">
		<c:set var="paName" value="낚시명예감시원"/>
	</c:when>
	<c:when test="${pageName eq '고객의 소리' }">
		<c:set var="paName" value="고객의 소리"/>
	</c:when>
	<c:when test="${pageName eq '개인정보처리방침' }">
		<c:set var="paName" value="개인정보처리방침"/>
	</c:when>
	<c:when test="${pageName eq '저작권보호정책' }">
		<c:set var="paName" value="저작권보호정책"/>
	</c:when>
	<c:when test="${pageName eq '이용약관' }">
		<c:set var="paName" value="이용약관"/>
	</c:when>
	<c:when test="${pageName eq '설문조사' }">
		<c:set var="paName" value="설문조사"/>
	</c:when>
	<c:when test="${pageName eq '낚시터정보 잘못된 정보 신고' }">
		<c:set var="paName" value="낚시터정보 잘못된 정보 신고"/>
	</c:when>
</c:choose>
</head>
<body>

<script type="text/javascript">

    var uAgent = navigator.userAgent.toLowerCase();
 
    //< 모바일 기기 추가시 등록
    var mobilePhones = new Array('iphone', 'ipod', 'android', 'blackberry', 'windows ce', 'nokia', 'webos', 'opera mini', 'sonyericsson', 'opera mobi', 'iemobile', 'windows phone');
     
    var isMobile = false;
     
    for( var i = 0 ; i < mobilePhones.length ; ++i )
    {
        if( uAgent.indexOf(mobilePhones[i]) > -1)
        {
            isMobile = true;
             
            break;
        }
    }
</script>

<style>
#skipnavigation a {position:absolute;left:-3000%;}
#skipnavigation a:focus {display:block;left:0;top:0;z-index:10000000000;width:100%;height:30px;line-height:30px;background:#039;color:#fff;text-align:center;}
</style>	
<div id="skipnavigation">
    <ul>
        <li><a href="#containerWrap" class="containerFocus" title="본문바로가기">본문 바로가기</a></li>
        <li><a href="#gnb"  class="gnbFocus"  title="주메뉴바로가기">주 메뉴 바로가기</a></li>
    </ul>
</div>

