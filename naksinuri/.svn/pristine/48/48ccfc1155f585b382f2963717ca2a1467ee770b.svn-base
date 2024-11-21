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

<c:set var="pageMode" value="conference" />
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congresscheck" />

<%@include file="../layout/newHead.jsp"%>

<style>
	#writeForm{width:100%;max-width:650px;margin:0 auto}
</style>
	
	<div id="customerSound" class="content">
		<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
			<input type="hidden" id="bo_sn"  name="bo_sn" value=""/>
			<input type="hidden" id="regit_num2"  name="regit_num" value=""/>
			<input type="hidden" id="bo_pass2"  name="bo_pass" value=""/>
			<input type="hidden" id="bo_email2"  name="bo_email" value=""/>
		</form:form>
		
		<section id="writeForm" class="write_box">
			<div class="agree_text">
				등록하신 낚시대회메뉴에서 등록할때 입력하신<br />접수번호/이메일/비밀번호 항목으로 조회해주세요.</br></br>
			</div>
			
			<dl>
				<dt><span class="colorRed">*</span> 접수번호</dt>
				<dd><input type="text" class="write_input w70" placeholder="접수번호를 입력해주세요." id="regit_num" title="접수번호"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 비밀번호</dt>
				<dd><input type="password" class="write_input w70" placeholder="비밀번호를 입력해주세요." id="bo_pass" title="비밀번호"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 이메일</dt>
				<dd><input type="text" class="write_input w70" placeholder="이메일을 입력해주세요." id="bo_email" title="이메일"/></dd>
			</dl>
			<div id="btnArea" class="noupline">
				<a href="#;" class="btn_blue" onclick="search_congress()" title="검색">검색</a>
			</div>
		</section>
	</div>
<script>

function search_congress(){
	
	var html = '';
	var bo_pass,bo_email,regit_num;

	if(!$.trim($('#regit_num').val())){
		alert("접수번호를 입력해주세요.");
		return false;
	}else{
		$('#regit_num2').val($('#regit_num').val());
	}
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

	var form = document.getElementById('listform');
	form.action = "/sosig/congress/search.do";
	form.submit();
}

function show_applicant(bo_sn){
	if(!$.trim(bo_sn)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	var form = document.getElementById('listform');
	var url = "/sosig/congress/list_show.do";
	window.open("about:blank","popwin","width=1400,height=800,scrollbars=yes");
	$('#bo_sn').val(bo_sn);
	form.action = url;
	form.method = "post";
	form.target="popwin";
	form.bo_sn = bo_sn;
	form.submit();
	
}

function go_link(bo_sn){
	if(!$.trim(bo_sn)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	
	var form = document.getElementById('listform');
	$('#bo_sn').val(bo_sn);
	form.action="./write2.do";
	form.submit();
}


</script>
<%@include file="../layout/tail.jsp"%>