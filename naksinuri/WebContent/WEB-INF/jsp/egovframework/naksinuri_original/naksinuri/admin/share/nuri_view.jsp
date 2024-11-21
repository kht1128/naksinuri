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
<c:set var="depthName" value="낚시공유" />
<c:set var="pageName" value="자주묻는낚시질문" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="zazu_num" id="zazu_num" value="${info.zazu_num}" />
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>자주묻는 낚시질문 설정</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
			<!-- 				
							<tr>
								<th>질문 유형</th>
								<td>
									<select class="search_select" name="zazu_type" id="zazu_type">
										<option value="">선택하세요</option>
										<option value="낚시관련문의" <c:if test="${info.zazu_type eq '낚시관련문의' }"> selected</c:if> >낚시관련문의</option>
										<option value="무엇관련" <c:if test="${info.zazu_type eq '무엇관련' }"> selected</c:if> >무엇관련</option>
										<option value="이벤트" <c:if test="${info.zazu_type eq '이벤트' }"> selected</c:if>>이벤트</option>
										<option value="낚시용품관련" <c:if test="${info.zazu_type eq '낚시용품관련' }"> selected</c:if>>낚시용품관련</option>
										<option value="바다/강" <c:if test="${info.zazu_type eq '바다/강' }"> selected</c:if>>바다/강</option>
									</select>
								</td>
							</tr>
			-->				
							<tr>
								<th>질문</th>
								<td><input type="text" name="zazu_ques" id="zazu_ques"  class="frm_input"  style="WIDTH: 100%;"  value="${info.zazu_ques}"/></td>
							</tr>
							<tr>
								<th>답변</th>
								<td><textarea name="zazu_answ" id="zazu_answ"  class="frm_input"  style="WIDTH: 100%; HEIGHT : 600px" >${info.zazu_answ}</textarea></td>
							</tr>	
									
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
			
				<c:if test="${info.zazu_trash eq '1' }">
					<a href="/admin/share/nuri/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
					<c:if test="${info.zazu_trash eq '0' && info.zazu_num ne null}">
							<a href="/admin/share/nuri/list.do" class="btn_size2 btn_bordergray">취소</a>
							<c:choose>
								<c:when test="${info.zazu_num eq null }">
									<input type="button" value="저장 " id="insertQues" onclick="submitContents()" class="btn_size2 btn_violet" />					
								</c:when>
								<c:otherwise>
									<input type="button" value="수정" id="updateQues" onclick="submitContents()" class="btn_size2 btn_violet" />
								</c:otherwise>
							</c:choose>
					</c:if>
					<c:if test="${info.zazu_num eq null}">
							<a href="/admin/share/nuri/list.do" class="btn_size2 btn_bordergray">취소</a>
							<c:choose>
								<c:when test="${info.zazu_num eq null }">
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
    elPlaceHolder: "zazu_answ",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["zazu_answ"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

//전송버튼 클릭이벤트
function submitContents() {
	

// 에디터의 내용이 textarea에 적용된다.
oEditors.getById["zazu_answ"].exec("UPDATE_CONTENTS_FIELD", [ ]);

// 에디터의 내용에 대한 값 검증은 이곳에서
if(document.getElementById("zazu_answ").value==''){
	alert('내용을 입력해주세요.');
	return false;   	
	} 
	 try {
		 if($('#zazu_num').val()!=''){
			
				$("#imform").attr("action","/admin/share/nuri/updatezazu.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/share/nuri/insertzazu.do");
				document.getElementById("imform").submit();				
			}
		 
	} catch(e) {
	 
	}


}

//textArea에 이미지 첨부
function pasteHTML(filepath){
var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+ filepath + '">';
		oEditors.getById["zazu_answ"].exec("PASTE_HTML", [ sHTML ]);

	}

	
</script>
