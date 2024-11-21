<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<c:set var="depthName" value="share" />
<c:set var="pageName" value="zisik" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body oncontextmenu="return false;">

<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post"  enctype="multipart/form-data" >
	<input type="hidden" name="nuri_q_num" id="nuri_q_num" value="${info.nuri_q_num}" />
	<input type="hidden" name="returnUrl" value="<c:url value='/admin/share/zisik/findz.do'/>"/>
	<input type="hidden" name="nuri_a_num" id="nuri_a_num"/>
		
	<div id="container">

		<div id="content">			
			<section id="table-write" class="table-box">
				<h3>누리지식인 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="nuri_q_writer" id="nuri_q_writer" class="frm_input" value="${info.nuri_q_writer}"  /></td>
							</tr>
							<tr>
								<th>패스워드</th>
								<td><input type="password" name="nuri_q_pass" id="nuri_q_pass" class="frm_input" value="${info.nuri_q_pass}"/></td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name="nuri_q_subject" id="nuri_q_subject" class="frm_input" value="${info.nuri_q_subject}" style="WIDTH: 100%;"/></td>
							</tr>							
							<tr>
								<th>질문</th>
								<td><textarea name="nuri_q_content" id="nuri_q_content"  class="frm_input"  style="WIDTH: 100%; HEIGHT:600px;">${info.nuri_q_content}</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
			
				<c:if test="${info.nuri_q_trash eq '1' }">
					<a href="/admin/share/zisik/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
<!-- 				글 수정 -->
				<c:if test="${info.nuri_q_num ne null && info.nuri_q_trash eq '0'}">
					<a href="/admin/share/zisik/list.do" class="btn_size2 btn_bordergray">취소</a>
					<c:choose>
						<c:when test="${info.nuri_q_num eq null}">
							<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
						</c:when>
						<c:otherwise>
							<input type="button" value="수정 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
							<a href="#;" onclick="answer()" class="btn_size2 btn_bordergray">답변</a>
						</c:otherwise>
					</c:choose>
				</c:if>
<!-- 				글등록 -->
				<c:if test="${info.nuri_q_num eq null}">
					<a href="/admin/share/zisik/list.do" class="btn_size2 btn_bordergray">취소</a>
					<c:choose>
						<c:when test="${info.nuri_q_num eq null}">
							<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
						</c:when>
						<c:otherwise>
							<input type="button" value="수정 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
							<a href="#;" onclick="answer()" class="btn_size2 btn_bordergray">답변</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
		<!-- 답변 목록 {-->
		<c:forEach var="answer" items="${answer_list}">
			<section id="table-write" class="table-box">
				<h3>답변 제목 : ${answer.nuri_a_subject }</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
							<span class="name"><i class="fa fa-user" aria-hidden="true"></i>작성자 : ${answer.nuri_a_writer}  </span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>  ${answer.nuri_a_date}</span>
							<div class="comment">
								${answer.nuri_a_content}
							</div>
						</tbody>
						<c:if test="${info.nuri_q_trash eq '0'}">
						<a href="#;" onclick="answermod(${answer.nuri_a_num})" class="btn_size2 btn_violet">수정</a>
						<input type="button" value="삭제 " id="updateInfo" onclick="delanswer(${answer.nuri_a_num})" class="btn_size2 btn_bordergray" />
						</c:if>
					</table>
				</div>
			</section>
		</c:forEach>
		<!--} 답변 목록 -->
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

<script type="text/javaScript" language="javascript">

function answer(){
	$("#imform").attr("action","/admin/share/zisik/answer.do");
	document.getElementById("imform").submit();		
}

function answermod(nuri_a_num){
	$("#imform").attr("action","/admin/share/zisik/answer.do");
	$('#nuri_a_num').val(nuri_a_num);
	
	document.getElementById("imform").submit();
}

function delanswer(nuri_a_num){
	if(confirm("삭제하시겠습니까?")){
		$("#imform").attr("action", "/admin/share/zisik/deletea.do")
		$('#nuri_a_num').val(nuri_a_num);
		document.getElementById("imform").submit();
	}else{
		return false;
	}
}

var depthName = "${depthName}";
var pageName = "${pageName}";


//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "nuri_q_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["nuri_q_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

//전송버튼 클릭이벤트
function submitContents() {
	
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["nuri_q_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	
	// 에디터의 내용에 대한 값 검증은 이곳에서
	if(document.getElementById("nuri_q_content").value==''){
		alert('내용을 입력해주세요.');
		return false;   	
		} 
	 try {
		 if($('#nuri_q_num').val()!=''){
				$("#imform").attr("action","/admin/share/zisik/updatez.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/share/zisik/insertz.do");				
				document.getElementById("imform").submit();					
			}
	} catch(e) {
	 
	}


}

//textArea에 이미지 첨부
function pasteHTML(filepath){
var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
oEditors.getById["nuri_q_content"].exec("PASTE_HTML", [sHTML]);

}





</script>