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
<input type="hidden" name="nuri_q_num" id="nuri_q_num" value="${qnum}" />
<input type="hidden" name="nuri_a_num" id="nuri_a_num" value="${info.nuri_a_num}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/share/zisik/findz.do'/>"/>
	
	<div id="container">
		
		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>누리지식인 답변쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="nuri_a_writer" id="nuri_a_writer" class="frm_input" value="${info.nuri_a_writer}"  /></td>
							</tr>
							<tr>
								<th>패스워드</th>
								<td><input type="password" name="nuri_a_pass" id="nuri_a_pass" class="frm_input" value="${info.nuri_a_pass}"/></td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name="nuri_a_subject" id="nuri_a_subject" class="frm_input" value="${info.nuri_a_subject}"/></td>
							</tr>							
							<tr>
								<th>질문</th>
								<td>
								<textarea name="nuri_a_content" id="nuri_a_content"  class="frm_input"  style="WIDTH: 100%; HEIGHT:600px;">
								<c:choose>
									<c:when test="${info.nuri_a_num ne null}">
										${info.nuri_a_content}
									</c:when>
									<c:otherwise>
										-------------------------------------------------------------<br>>> 질문 ${qcon}
								<br>-------------------------------------------------------------<br>
								<br><br><br><br><br><br><br><br><br><br><br>
									</c:otherwise>
								</c:choose>
								</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				<a href="/admin/share/zisik/list.do" class="btn_size2 btn_bordergray">취소</a>
				<c:choose>
					<c:when test="${info.nuri_a_num eq null}">
						<input type="button" value="답변저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					</c:when>
					<c:otherwise>
						<input type="button" value="수정 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					</c:otherwise>
				</c:choose>
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

<script type="text/javaScript" language="javascript">

var depthName = "${depthName}";
var pageName = "${pageName}";


//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "nuri_a_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["nuri_a_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

//전송버튼 클릭이벤트
function submitContents() {
	
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["nuri_a_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	
	// 에디터의 내용에 대한 값 검증은 이곳에서
	if(document.getElementById("nuri_a_content").value==''){
		alert('내용을 입력해주세요.');
		return false;   	
		}
	if($("#nuri_a_pass").val()==''){
		alert('패스워드를 입력하세요.');
		return false;
	}
	 try {
		 if($('#nuri_a_num').val()!=''){
				$("#imform").attr("action","/admin/share/zisik/updatea.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/share/zisik/inserta.do");				
				document.getElementById("imform").submit();					
			}
	} catch(e) {
	 
	}


}

//textArea에 이미지 첨부
function pasteHTML(filepath){
var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
oEditors.getById["nuri_a_content"].exec("PASTE_HTML", [sHTML]);

}

</script>