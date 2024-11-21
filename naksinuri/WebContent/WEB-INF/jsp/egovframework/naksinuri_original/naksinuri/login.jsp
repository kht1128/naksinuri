<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<meta name="viewport" content="device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
	<title>낚시터 정보 등록</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body onLoad="fnInit();">

<div id="wrapper">
	<header id="header">
		<h1>
			<em>해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.</em>
			<span>낚시터 <b>정보 등록</b></span>
		</h1>
	</header>

	<div id="container" class="respon">
		<section id="login" class="content">
			<div class="btnArea" style="margin-top:-0.5%;padding-bottom:20px">
				<a href="/info/boatfishing.do" class="submitBtn bgBlue">신규등록</a>
			</div>
			
			<form name="findForm" id="findForm" action ="<c:url value='/info/findCorp.do'/>" method="post">
			 <input type="hidden" name="message" value="${message}" />

			<div class="writeBox">
				<dl>
					<dt>상호명</dt>
					<dd><input type="text" id="co_nm" name="co_nm" class="naksi_input w100" /></dd>
				</dl>
				<dl>
					<dt>대표자</dt>
					<dd><input type="text" id="ceo_nm" name="ceo_nm" class="naksi_input w100" /></dd>
				</dl>
			</div>
			<div class="btnArea">
				<a href="#" class="submitBtn bgBlue" id="mod">이미 등록된 정보 수정</a>
			</div>
			</form>
		</section>
	</div>

	<footer id="footer">
		<div class="respon">
			<h1><img src="${pageContext.request.contextPath}/common/img/logo_foot.png" alt="낚시누리" /></h1>
			<ul class="floats">
				<li>(08588) 서울특별시 금천구 가산디지털2로 53 한라시그마벨리 10층</li>
				<li>TEL : 1833-7139</li>
				<!-- <li>FAX : 02)6098-0810</li> -->
				<li>상호 : 한국어촌어항공단</li>
				<li>사업자번호 : 220-82-00065</li>
			</ul>
			<p>Copyright(C) 2015 Naksi-Nuri. all rights reserved.</p>
		</div>
	</footer>
</div>

</body>
</html>
<script type="text/javaScript" language="javascript">
<!--
function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        alert(message);
    }
    
    getid(document.loginForm);
}

$('#mod').click(function(){
	if(document.findForm.co_nm.value==""){
		alert("상호명을 입력하세요.");
	}else if(document.findForm.ceo_nm.value==""){
		alert("대표자명을 입력하세요.");
	}else{		
	document.findForm.submit();
	}
});
-->

</script>