<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="pageName" value="auditor" />

<%@include file="../../layout/m/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" action="/promotion/auditor/board_modify_act.do">
	<input type="hidden" name="fileSn" id="fileSn">
	<input type="hidden" name="atchFileId" id="atchFileId">
	<input type="hidden" name="fileListCnt">
	<input type="hidden" name="bo_sn" value="${info.bo_sn }">
		
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시명예감시원 활동보고 수정하기</h2>
			<dl>
				<dt><span class="colorRed w100">*</span> 제목</dt>			
				<dd><input type="text" class="write_input" placeholder="제목을 입력해주세요." id="bo_subject" name="bo_subject" title="제목" value="${info.bo_subject }" required></dd>
			</dl>
			<dl>
				<dt><span class="colorRed w100">*</span> 장소</dt>			
				<dd><input type="text" class="write_input" placeholder="장소를 입력해주세요." id="cg_location" name="cg_location" title="장소" value="${info.cg_location }" required></dd>
			</dl>
	
			<dl>
				<dt><span class="colorRed w100">*</span> 구분</dt>
				<dd><input type="text" class="write_input" placeholder="작성자를 입력해주세요." id="" name="" title="구분"/></dd>
			</dl>
			<dl>
				<dt>이미지</dt>
				<dd>
					<div class="file_input" id="file_input">
						<c:forEach items="${filelist}" var="item">
							<ul>
								<li>
									<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.report_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
								</li>
							</ul>
						</c:forEach>
						<ul id="file_0">
							<li>
								<input type="checkbox" id="chart_0" name="atch_file_0" />
								<label for="chart_0"><span></span></label> 
								<input type="file" size="50" name="report_file_0" class="write_file" title="파일첨부"/>
							</li>								
						</ul>
					</div>
					
					<div id="btnArea">
						<td><a href="#;" class="btn_white" onclick="insertinput()" title="파일첨부 추가">첨부파일 추가 </a></td>
						<td><a href="#;" class="btn_gray" onclick="deleteinput()" title="파일첨부 삭제">파일 삭제</a></td>
					</div>
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="bo_content" name="bo_content" style="width:98%" required>${info.bo_content }</textarea></dd>
			</dl>
			
			<div id="btnArea">
				<ul class="floats">
					<button type="submit" class="btn_request btn_blue">확인</button>
					<button type="button" class="btn_prev btn_white" onClick="history.go(-1);">취소</button>
				</ul>
			</div>
		</section>
	</div>

</form:form>
</div>

<%@include file="../../layout/m/tail.jsp"%>

<script type="text/javascript">

var j=0;
function insertinput(){

	if($("#file_input ul").length>=10){
		alert("파일 첨부는 10개까지 가능합니다.");
		return false;
	}else{
	$("#file_input").append('<ul id="file_'+(j+1)+'">\
			<li style="padding-top:10px;">\
			<li><input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
			<label for="chart_'+(j+1)+'"><span></span></label>\
						<input type="file" size="30" name="report_file_'+(j+1)+'"  style="width:290px" class="write_file" /></li></li></ul>');
	j++;
	}
}

function deleteinput(){
	for(var k=0;k<=j;k++){
		if($("#chart_"+k).prop("checked")){
			$("#file_"+k).remove();
		}
	}

}
</script>
