<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="share"/>
<c:set var="depthNum" value="3" />
<c:set var="depthName" value="share" />
<c:set var="pageName" value="nuri" />

<%@include file="../layout/head.jsp"%>

<div align="center">
	<form:form commandName="zzform" id="zzform" method="post" action="/share/nuri/insertzazu.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>분 야</td>
			<td><select class="search_select" name="zazu_type" id="zazu_type">
				<option value="">선택하세요</option>
				<option>낚시관련문의</option>
				<option>무엇관련</option>
				<option>이벤트</option>
				<option>낚시용품관련</option>
				<option>바다/강</option>
			</select>
			</td>
		</tr>
		<tr>
			<th> 질 문 </th>
			<td><input type="text" class="reply_input" name="zazu_ques" id="zazu_ques" placeholder="질문" /></td>
		</tr>
		<tr>
			<th> 답 변 </th>
			<td><textarea rows="20" name="zazu_answ" id="zazu_answ" cols="50" placeholder="답변"></textarea></td>
		</tr>
		</table>		
		<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
	</form:form>
</div>


<%@include file="../layout/tail.jsp"%>


<script>
//전송버튼 클릭이벤트
function submitContents() {
	if(document.getElementById("zazu_type").value==''){
		alert('유형을 선택해주세요.');
		return false;   	
	}else if(document.getElementById("zazu_ques").value==''){
		alert('질문을 입력해주세요.');
		return false;
	}else if(document.getElementById("zazu_answ").value==''){
		alert('답변을 입력해주세요.');
		return false;
	}else{
		document.getElementById("zzform").submit();				
		}
	}
</script>

