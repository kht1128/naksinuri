<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>

<c:set var="depthName" value="adminset" />
<c:set var="pageName" value="info" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="text" name="idx" id="idx" class="frm_input" value="${info.idx}"  />
	<div id="container">
		<div id="content">			
			<section id="table-write" class="table-box">
				<h3>설정</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>		
							<tr>
								<th>이메일</th>
								<td><input type="text" name="gmail" id="gmail" class="frm_input" value="${info.email}"  /></td>
							</tr>
							<tr>
								<th>이메일 pw</th>
								<td><input type="password" name="gmail_pw" id="gmail_pw" class="frm_input" value="${info.email_pw}"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>
			<div class="btn_area textcenter">
				<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
			</div>
		</div>
	</div>
</form:form>
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
</body>

<script type="text/javaScript">
	function submitContents(){
		if(!$.trim($('#gmail').val())){
			alert("메일을 입력해주세요");
			return false;
		}
		
		if($('#gmail_pw').val() == ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		var form = document.getElementById("imform");
		form.action = "./update.do";
		form.submit();
	}
</script>
