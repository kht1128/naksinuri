<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishjob" />

<%@include file="../layout/head.jsp"%>

<article id="fishinfoRegister" class="content respon2">
	<section class="fir_login">
		<h1>
			<em class="colorSky">낚시터 <b>정보등록</b></em>
			<span>
				해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.<br />
				선상낚시, 바다낚시터, 민물낚시터, 낚시공원, 낚시카페, 낚시체험 등
			</span>
		</h1>
		<div class="fir_login_area">
			<div class="loginok"><b>"등록신청 완료"</b></div>
			<p class="comment mgt10px">낚시누리 관리자가 등록된 정보 확인 후 최종 등록 승인해 드립니다.<br />약 1~3일 소요</p>
			<div class="btnArea">
				<a href="/" class="btns submitBtn bgBlue">확인</a>
			</div>
		</div>
	</section>
</article>
<%@include file="../layout/tail.jsp"%>