<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
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
	<meta name="viewport" content="device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
	<title>낚시누리</title>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/mobile.css?ver=20230103" />
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/base.css?ver=20211117"/>
	
	<meta property="fb:app_id" 			content="143280399642277" />
	<meta property="og:title" 			content="낚시누리" />
	<meta property="og:url" 			content="naksinuri.kr" />
	<meta property="og:type" 			content="website" />
	<meta property="og:description" 	content="낚시정보 종합 포털. 서울특별시 금천구 가산디지털2로 위치. 해양수산부, 한국어촌어항공단 운영, 정책, 정보, 커뮤니티 제공." />
	<meta property="og:image" 			content="http://naksinuri.kr/common_main/img/logo.png" />
	<meta name="description" content="낚시정보 종합 포털. 서울특별시 금천구 가산디지털2로 위치. 해양수산부, 한국어촌어항공단 운영, 정책, 정보, 커뮤니티 제공.">
	
	<link rel="canonical" href="https://www.naksinuri.kr">
	<span itemscope="" itemtype="http://schema.org/Organization">
	<link itemprop="url" href="https://www.naksinuri.kr">
	<a itemprop="sameAs" href="https://blog.naver.com/naksinuriweb"></a>
	<a itemprop="sameAs" href="https://www.facebook.com/nurinaksi"></a>
	</span>
	<!--
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=kSzk_875XFgPsLskTEKN&submodules=geocoder"></script>
	-->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="https://use.fontawesome.com/e59ba62350.js"></script> 
	<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
	<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
	<script src="/naksinuri_original/common_main/js/common.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services"></script>
	<script type="text/javascript" src="/naksinuri_original/jwplayer/jwplayer.js"></script>
	<script type="text/javascript">jwplayer.key="OqpCTFsbHwzLSeC7A7rf46NB8Ce+OgLPCjgyhQ==";</script>
	<script type="text/javascript" src="/naksinuri_original/js/datepicker_KR.js"></script>
	<script type="text/javascript" src="/naksinuri_original/js/jquery.slider2.js"></script>
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
//System.out.println(cururl);
//System.out.println(parseUrl.length);
if(parseUrl.length == 7) {	
	urlpath0 = parseUrl[4];
	urlpath1 = "";//5는 mobile 생략함
	urlpath2 = "";
	urlpath3 = parseUrl[6].replace(".jsp", "");
} else if(parseUrl.length == 8) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[6];//5는 mobile 생략함
	urlpath2 = "";
	urlpath3 = parseUrl[7].replace(".jsp", "");
} else if(parseUrl.length >= 9) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[6];
	urlpath2 = parseUrl[7];
	urlpath3 = parseUrl[8].replace(".jsp", "");
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
<%--<script>alert('${urlcontroller } | ${urlmethodname } | ${urlpagename }');</script> --%> 
<c:choose>
	<c:when test="${urlcontroller eq 'naksinuri_original' and urlmethodname eq 'm' and urlmethodsubname eq '' and urlpagename eq 'index' }">
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
		<c:set var="menuNum" value="10"/>
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
		<c:set var="depthName" value="온라인강의 / 이수증내역"/>		
		<c:set var="pageName" value="나의신청내역"/>
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
		<c:set var="depthName" value="온라인강의 / 이수증내역"/>		
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
		<c:set var="depthName" value="온라인 교육신청"/>		
		<c:set var="pageName" value="온라인 교육일정"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshlcList'}">
		<c:set var="paName" value="오프라인 교육일정(낚시터)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="오프라인 교육신청(낚시터)"/>		
		<c:set var="pageName" value="오프라인 교육일정(낚시터)"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnngoffline' and urlmethodsubname eq '' and urlpagename eq 'fshhpList'}">
		<c:set var="paName" value="오프라인 교육일정(낚시어선)"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="오프라인 교육신청(낚시어선)"/>		
		<c:set var="pageName" value="오프라인 교육일정(낚시어선)"/>
		<c:set var="menuNum" value="10"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'trnng' and urlmethodsubname eq '' and urlpagename eq 'agree'}">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="교육신청"/>		
		<c:set var="pageName" value="이용동의서"/>
		<c:set var="menuNum" value="11"/>
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
		<c:set var="menuNum" value="11"/>
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
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'hpreqstdoc' }">
		<c:set var="paName" value="변경신청서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="본인명의 휴대폰 변경"/>
		<c:set var="pageName" value="변경신청서"/>		
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_agree' }">
		<c:set var="paName" value="이용동의서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="법인사업장 교육책임자 지정"/>
		<c:set var="pageName" value="이용동의서"/>		
		<c:set var="menuNum" value="11"/>
	</c:when>
	<c:when test="${urlcontroller eq 'educenter' and urlmethodname eq 'rmndr' and urlmethodsubname eq '' and urlpagename eq 'cpr_bplc' }">
		<c:set var="paName" value="확인서"/>
		<c:set var="pageMode" value=""/>
		<c:set var="groupName" value="낚시전문교육"/>
		<c:set var="depthName" value="법인사업장 교육책임자 지정"/>
		<c:set var="pageName" value="확인서"/>		
		<c:set var="menuNum" value="11"/>
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

