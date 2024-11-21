<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="egovframework.all.login.service.LoginVO"%>

<%@include file="static_sub_header.jsp"%>

<!-- 상단헤더 { -->
<div id="wrapper">
	<header id="header">	
		<div class="respon">
			<h1><a href="index.php"><img src="/naksinuri_original/admincommon/common/img/logo.png" alt="낚시누리" /></a></h1>
			<div class="name_logout">
			<c:choose>
				<c:when test="${not empty LoginVO and not empty LoginVO.MBR_ID}">
					<ul class="floats">
		    			<li class="wastebasket"><a href="/admin/lesson/junior/trash.do"><i class="fa fa-trash" aria-hidden="true"></i> 데이터 복구 관리 열기</a></li>
						<li>&nbsp;<b>${LoginVO.MBR_ID }</b>님 로그인하셨습니다. </li>
						<c:if test="${depthName ne 'static' }">
							<li class="logout"><a href="<c:url value='/seadm/member/actionLogout.do'/>"><span class="skip">로그아웃</span></a></li>
						</c:if>
		    		</ul>
				</c:when>
				<c:otherwise>
					<script>
						alert("관리자 로그인이 필요합니다.");
		  				location.href="/seadm/index.do";
		  			</script>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</header>
	<!-- } 상단헤더 -->

	<div id="container">
		<div class="respon floats">
			<div id="gnbBox">
				<nav id="gnb">
					<ul>
						<li <c:if test="${pageName eq 'site' }"> class="active" </c:if>><a href="/admin/static/site/list.do">사이트현황</a></li>
<!-- 						<li <c:if test="${pageName eq 'invit' }"> class="active" </c:if>><a href="javascript:alert('준비중입니다.');">방문현황</a></li>
						<li <c:if test="${pageName eq 'invadindex' }"> class="active" </c:if>><a href="javascript:alert('준비중입니다.');">유입검색어</a></li>
						<li <c:if test="${pageName eq 'invadurl' }"> class="active" </c:if>><a href="javascript:alert('준비중입니다.');">유입상세URL</a></li>
						<li <c:if test="${pageName eq 'likepage' }"> class="active" </c:if>><a href="javascript:alert('준비중입니다.');">인기페이지</a></li>
						
						
				
						<li <c:if test="${pageName eq 'invadindex' }"> class="active" </c:if>><a href="/admin/static/invadindex/list.do">유입검색어</a></li>
						<li <c:if test="${pageName eq 'invadurl' }"> class="active" </c:if>><a href="/admin/static/invadurl/list.do">유입상세URL</a></li>
						
-->						
						<li <c:if test="${pageName eq 'likepage' }"> class="active" </c:if>><a href="/admin/static/likepage/list.do">인기페이지</a></li>
						<li <c:if test="${pageName eq 'invit' }"> class="active" </c:if>><a href="/admin/static/invit/list.do">방문현황</a></li>
						<li <c:if test="${pageName eq 'invitos' }"> class="active" </c:if>><a href="/admin/static/invitos/list.do">운영체제</a></li>
					</ul>
				</nav>
			</div>
			<div id="content">