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
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="voc" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="voc_no" id="voc_no" value="${info.voc_no}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/policy/customersound/voc_findCorp.do'/>"/>
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>고객의 소리</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>	
								
							<tr>
								<th>구분</th>
								<td><input type="text" disabled name="voc_type" id="voc_type" class="frm_input" value="${info.voc_type}"  /></td>
								<th>제목</th>
								<td><input type="text" disabled name="voc_subject" id="voc_subject" class="frm_input" value="${info.voc_subject}"/></td>
								<th>작성자</th>
								<td><input type="text" disabled name="voc_name" id="voc_name" class="frm_input" value="${info.voc_name}"  /></td>								
							</tr>	
							
							<tr>
								<input type="hidden" class="basic_radio" name="voc_issecret" id="voc_issecret" value="0" />
								<%-- <th>공개여부</th>
								<td>
									<ul class="radio_check nopm floats liw100">
										<li>
											<input type="radio" class="basic_radio" name="voc_issecret" id="voc_issecret1" value="0" <c:if test="${info.voc_issecret eq '0'}">checked</c:if> />
											<label class="mr-20" for="voc_issecret1">
												<span></span>비공개
											</label>
										</li>
										<li>
											<input type="radio" class="basic_radio" name="voc_issecret" id="voc_issecret2" value="1"<c:if test="${info.voc_issecret eq '1'}">checked</c:if>>
											<label class="mr-20"  for="voc_issecret2">
												<span></span>공개
											</label>
										</li>
									</ul>
								</td>	 --%>
								<th colspan="1">답변여부</th>
								<td colspan="5">
									<ul class="radio_check nopm floats liw100">
										<li>
											<input type="radio" class="basic_radio" name="voc_isanswer" id="voc_isanswer1" value="0" <c:if test="${info.voc_isanswer eq '0'}">checked</c:if> />
											<label class="mr-20" for="voc_isanswer1">
												<span></span>답변미완료
											</label>
										</li>
										<li>
											<input type="radio" class="basic_radio" name="voc_isanswer" id="voc_isanswer2" value="1"<c:if test="${info.voc_isanswer eq '1'}">checked</c:if>>
											<label class="mr-20"  for="voc_isanswer2">
												<span></span>답변완료
											</label>
										</li>
									</ul>
								</td>
							</tr>
							<tr>
								<th colspan="1">내용</th>
								<td colspan="5">
									<div class="agree_box mt-0 mb-0">
										<div class="agree_text mb-0" style="line-height:12px;min-height:200px;">${info.voc_content}</div>
									</div>
								</td>
							</tr>							
							<tr>
								<th colspan="1">첨부파일 목록</th>
								<td colspan="5">
									<c:if test="${not empty info.voc_atch_file}">
										<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                 		 	  <c:param name="param_atchFileId" value="${info.voc_atch_file}" />
			                			</c:import>
                					</c:if>
								</td>
							</tr>
							<tr>
								<th colspan="1">답변</th>
								<td colspan="5">
									<textarea name="voc_answer_content" rows="10" id="voc_answer_content"  class="frm_input"  style="width:100%; resize:none;min-height:300px;" >${info.voc_answer_content}</textarea>
								</td>
							</tr>
								
									
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				<c:if test="${info.voc_trash eq '0' }">
					<a href="#;" class="btn_size2 btn_violet" onclick="submitAnswerContent();">답변 저장</a>
					<a href="/admin/policy/customersound/list.do" class="btn_size2 btn_bordergray">목록</a>
				</c:if>
				<c:if test="${info.voc_trash eq '1' }">
					<a href="/admin/policy/customersound/trash.do" class="btn_size2 btn_bordergray">목록</a>
				</c:if>
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
</html>
<script type="text/javaScript">
var depthName = "${depthName}";
var pageName = "${pageName}";


//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "voc_answer_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	//oEditors.getById["voc_answer_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

    //전송버튼 클릭이벤트

    function submitAnswerContent(){
    	oEditors.getById["voc_answer_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
    	var form = document.getElementById('imform');
    	
    	
    	$.ajax({
    		type:"POST",
    		url :"/admin/policy/customersound/voc_answer_write_act.do",
    		data:$('#imform').serialize(),
    		dataType: 'json',
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			if(data.error == '0') {
					alert(data.msg);
    			} else {
    				location.href = '/admin/policy/customersound/list.do';
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    		}
    	});
    	
    }
</script>
