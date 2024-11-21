<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="4"/>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congresscheck" />

<%@include file="../../layout/m/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" id="bo_sn"  name="bo_sn" value=""/>
		<input type="hidden" id="regit_num2"  name="regit_num" value=""/>
		<input type="hidden" id="bo_pass2"  name="bo_pass" value=""/>
		<input type="hidden" id="bo_email2"  name="bo_email" value=""/>
	</form:form>
	

   <div style="padding:20px">
		<div class="agree_text">
			등록하신 낚시대회메뉴에서 등록할때 입력하신<br />접수번호/이메일/비밀번호 항목으로 조회해주세요.</br></br>
		</div>
		<dl>
			<dd><input type="text" class="write_input w100" placeholder="이메일을 입력해주세요." id="bo_email" name="bo_email" /></dd>
		</dl>
		<dl style="margin-top:3px;">
			<dd><input type="text" class="write_input w100" placeholder="접수번호를 입력해주세요." id="regit_num" name="regit_num" /></dd>
		</dl>
		<dl style="margin-top:3px;">
			<dd><input type="password" class="write_input w100" placeholder="비밀번호를 입력해주세요." id="bo_pass" name="bo_pass" /></dd>
		</dl>
		<div id="btnArea" class="noupline">
			<a href="#;" onclick="search_congress()" class="btn_blue" style="width:40%">검색</a>
		</div>
	</div>
<script>

function search_congress(){
	
	var html = '';
	var bo_pass,bo_email,regit_num;
		if(!$.trim($('#bo_pass').val())){
			alert("비밀번호를 입력해주세요");
			return false;
		}else{
			$('#bo_pass2').val($('#bo_pass').val());
			
		}
		
		if(!$.trim($('#bo_email').val())){
			alert("이메일을 입력해주세요");
			return false;
		}else{
			$('#bo_email2').val($('#bo_email').val());
		}
		
		
		if(!$.trim($('#regit_num').val())){
			alert("접수번호를 입력해주세요.");
			return false;
		}else{
			$('#regit_num2').val($('#regit_num').val());
		}
		
		var form = document.getElementById('listform');
		form.action = "/sosig/congress/m/search.do";
		form.submit();
}


</script>

<%@include file="../../layout/m/tail.jsp"%>