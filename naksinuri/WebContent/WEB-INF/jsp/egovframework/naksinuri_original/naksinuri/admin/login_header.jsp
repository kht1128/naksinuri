<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="egovframework.all.login.service.LoginVO"%>
<c:choose>
	<c:when test="${not empty LoginVO and not empty LoginVO.MBR_ID}">
		<c:choose>
			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
				<article id="top-mnu">
					<div class="admin_set_in">
						<ul class="floats">
							<li class="pagemove"><a class="display-inline-block" href="/eduadm/index.do">낚시전문교육 관리자페이지 이동</a></li>
							<li class="pagemove"><a class="display-inline-block" href="/cti/index.do">낚시전문교육 CTI 페이지 이동</a></li>
						</ul>
					</div>
					<div class="admin_set_out">
						<ul class="floats">
							<li class="wastebasket"><a href="/admin/lesson/junior/trash.do"><i class="fa fa-trash" aria-hidden="true"></i> 데이터 복구 관리 열기</a></li>
							<li><b>${LoginVO.MBR_NM}</b>님 로그인하셨습니다. </li>
							<li class="logout"><a href="<c:url value='/seadm/member/actionLogout.do'/>"><span aria-hidden="true" data-icon="&#xe097;"></span><span class="skip">로그아웃</span></a></li>
						</ul>
					</div>
				</article>
			</c:when>
			<c:otherwise>
				<script>
					location.href="/seadm/index.do";
				</script>
			</c:otherwise>
		</c:choose>
	
	</c:when>
	<c:otherwise>
		<script>
			//alert("관리자 로그인이 필요합니다.");
			//location.href="/index.do";
 		</script>
	</c:otherwise>
</c:choose>
