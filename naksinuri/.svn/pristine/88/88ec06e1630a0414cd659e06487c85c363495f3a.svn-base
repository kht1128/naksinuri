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
<%@include file="../header_admin.jsp"%>
<c:set var="depthName" value="static" />
<c:set var="pageName" value="invadindex" />
<body oncontextmenu="return false;">
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
	
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<div id="wrapper">
	<!-- 상단 헤더 { -->
	<header id="header">
		<h1><a href="/admin/lesson/junior/list.do">낚시누리</a></h1>

		<!-- 좌측 메뉴 { -->
			<%@include file="../left_menu.jsp"%>
		<!-- } 좌측 메뉴 -->
	</header>
	<!-- } 상단 헤더 -->

	<div id="container">
		<article id="top-mnu">
	
			<div class="admin_set_out">
				<ul class="floats">
					<li><b>슈퍼관리자</b>님 환영합니다.</li>
					<li><a href="#;"><span aria-hidden="true" data-icon="&#xe09a;"></span><span class="skip">환경설정</span></a></li>
					<li><a href="#;"><span aria-hidden="true" data-icon="&#xe097;"></span><span class="skip">로그아웃</span></a></li>
				</ul>
			</div>
		</article>

		<div id="content">			

			<section id="table-list">
				
				준비중입니다.
				
			</section>
		</div>
	</div>

	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
<script>
function view2(bo_sn){
	var form = document.getElementById('listform');
	$('#bo_sn').val(bo_sn);
	
	form.action="./board_findCorp.do";
	form.submit();
};

function delete_list(bo_sn){
	if(!confirm("삭제 하시겠습니까?")){
			return false;
		}else{
			var form = document.getElementById('listform');
			$('#bo_sn').val(bo_sn);
			
			form.action="./delete_list.do";
			form.submit();		
			
		}

	
	
}



function fnSelectInfs(pageIndex) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val(idx);
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "./list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});
</script>
</body>
</html>