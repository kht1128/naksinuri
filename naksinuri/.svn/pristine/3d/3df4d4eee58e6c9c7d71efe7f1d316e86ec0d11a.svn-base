<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!doctype html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>낚시누리</title>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/admincommon/css/admin_common.css" />

	<script src="https://use.fontawesome.com/0973aba921.js"></script>
	<script src="/naksinuri_original/admincommon/js/jquery-1.8.3.min.js"></script>
	<script src="/naksinuri_original/admincommon/js/common.js"></script>


</head>
<body oncontextmenu="return false;">

<div id="login-box">
	<article id="login_area">
		<section class="l_login">
			<h1><a href="dashboard.html"><img src="/naksinuri_original/admincommon/img/naksinuri_logo.jpg" alt="낚시누리 관리자모드"></a></h1>
			<h2>Login to your account</h2>
			<!-- 로그인 폼 { -->
			  <form name="loginForm" action ="<c:url value='/admin/admin/actionSecurityLogin.do'/>" method="post"> 
			  <input type="hidden" name="message" value="${message}" />
				<ul>
					<li><input type="text" class="frm_login" name="id" id="id" placeholder="Insert Username" /></li>
					<li><input type="password" class="frm_login" name="password" id="pass" placeholder="Insert Password" onkeypress="javascript:if(event.keyCode == 13){actionLogin();}"/></li>
					<li><input type="button" value="LOGIN" class="btn_login" onclick="actionLogin()"></li>
				</ul>
				 <input name="userSe" type="hidden" value="GNR"/>
	             <input name="j_username" type="hidden"/>
			</form>
			<!-- } 로그인 폼 -->
			<p class="version">Version 5.0.0.1</p>
		</section>
		<p class="copyright"><b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.</p>
	</article>
</div>

</body>
</html>
<script>
var message = document.loginForm.message.value;
if (message != "") {
    alert(message);
}
	    


function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.pass.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="<c:url value='/admin/admin/actionSecurityLogin.do'/>";
        document.loginForm.submit();
    }
}
</script>
