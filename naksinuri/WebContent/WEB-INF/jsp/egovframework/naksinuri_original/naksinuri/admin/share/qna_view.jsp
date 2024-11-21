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
<c:set var="pageName" value="qna" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="qna_no" id="qna_no" value="${info.qna_no}" />

	<div id="container">
		
		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시법 및 유어장 관련 질의회신 사례</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
							
							<tr>
								<th>질의 유형</th>
								<td>
									<select class="search_select" name="qna_type" id="qna_type">
										<option value="">선택하세요</option>
										<option value="낚시관리및제도일반" <c:if test="${info.qna_type eq '낚시관리및제도일반' }"> selected</c:if> >낚시관리및제도일반</option>
										<option value="낚시터" <c:if test="${info.qna_type eq '낚시터' }"> selected</c:if> >낚시터</option>
										<option value="낚시어선" <c:if test="${info.qna_type eq '낚시어선' }"> selected</c:if>>낚시어선</option>
										<option value="낚시도구및미끼" <c:if test="${info.qna_type eq '낚시도구및미끼' }"> selected</c:if>>낚시도구및미끼</option>
										<option value="유어장" <c:if test="${info.qna_type eq '유어장' }"> selected</c:if>>유어장</option>
										<option value="참고자료" <c:if test="${info.qna_type eq '참고자료' }"> selected</c:if>>참고자료</option>
									</select>
								</td>
							</tr>
						
							<tr>
								<th>질문</th>
								<td><input type="text" name="qna_ques" id="qna_ques"  class="frm_input"  style="WIDTH: 100%;" value="${info.qna_ques}"/></td>
							</tr>
							<tr>
								<th>답변</th>
								<td><textarea name="qna_answ" id="qna_answ"  class="frm_input"  style="WIDTH: 100%; HEIGHT : 600px" >${info.qna_answ}</textarea></td>
							</tr>	
									
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				
				<c:if test="${info.qna_trash eq '1' }">
					<a href="/admin/promotion/qna/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				<c:if test="${info.qna_trash eq '0' && info.qna_no ne null}">
					<a href="/admin/promotion/qna/list.do" class="btn_size2 btn_bordergray">취소</a>
					<c:choose>
						<c:when test="${info.qna_no eq null }">
							<input type="button" value="저장 " id="insertQues" onclick="submitContents()" class="btn_size2 btn_violet" />					
						</c:when>
						<c:otherwise>
							<input type="button" value="수정" id="updateQues" onclick="submitContents()" class="btn_size2 btn_violet" />
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:if test="${info.qna_no eq null}">
					<a href="/admin/promotion/qna/list.do" class="btn_size2 btn_bordergray">취소</a>
					<c:choose>
						<c:when test="${info.qna_no eq null }">
							<input type="button" value="저장 " id="insertQues" onclick="submitContents()" class="btn_size2 btn_violet" />					
						</c:when>
						<c:otherwise>
							<input type="button" value="수정" id="updateQues" onclick="submitContents()" class="btn_size2 btn_violet" />
						</c:otherwise>
					</c:choose>
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
<script>

//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "qna_answ",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["qna_answ"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

//전송버튼 클릭이벤트
function submitContents() {
	
	if($('#qna_type').val() == ''){
		alert('질의 종류를 선택해주세요.');
		return false;
	}

// 에디터의 내용이 textarea에 적용된다.
oEditors.getById["qna_answ"].exec("UPDATE_CONTENTS_FIELD", [ ]);

// 에디터의 내용에 대한 값 검증은 이곳에서
if(document.getElementById("qna_answ").value==''){
	alert('내용을 입력해주세요.');
	return false;   	
	} 
	 try {
		 if($('#qna_no').val()!=''){
			
				$("#imform").attr("action","/admin/promotion/qna/update.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/promotion/qna/insert.do");
				document.getElementById("imform").submit();				
			}
		 
	} catch(e) {
	 
	}


}

//textArea에 이미지 첨부
function pasteHTML(filepath){
var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+ filepath + '">';
		oEditors.getById["qna_answ"].exec("PASTE_HTML", [ sHTML ]);

	}

	
</script>
