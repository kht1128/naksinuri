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

<c:set var="depthName" value="info" />
<c:set var="pageName" value="angling" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="mid" id="mid" value="${info.mid}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/info/angling/board_findCorp.do'/>"/>
<input type="hidden" name="ftv" id="ftv" <c:if test="${info.ftv ne null }">value="${info.ftv}"</c:if> <c:if test="${info.ftv eq null }">value="0"</c:if> >
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>조황정보 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>		
							<tr>
								<td colspan="2"><label for="isftv"><input type = "checkbox" name = "isftv" id = "isftv"<c:if test="${info.ftv eq 1}">checked</c:if> class="frm_input"/> *체크시 FTV 게시글로 등록합니다.</label></td>
			
							</tr>					
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="writer_name" id="writer_name" class="frm_input" value="${info.writer_name}"  /></td>
							</tr>
							<tr>
								<th> 구분</th>
								<td>
									<select class="naksi_select" id="category" name="category" value="${info.category }">
									
											<option <c:if test ="${info.category eq'바다'}">selected</c:if>>바다</option>
											<option <c:if test ="${info.category eq'루어'}">selected</c:if>>루어</option>
											<option <c:if test ="${info.category eq'민물'}">selected</c:if>>민물</option>
									</select>
								</td>
								</tr>
							
							<tr>
								<th>제목</th>
								<td><input type="text" name="title" id="title" class="frm_input" value="${info.title}" style="WIDTH: 100%;"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="body" id="body"  class="frm_input"  style="WIDTH: 100%">${info.body}</textarea></td>
							</tr>		
									
									
									
							
	
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
					
					<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.isdel eq '1' }">
					<a href="/admin/info/angling/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.isdel eq '0' && info.mid ne null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/info/angling/list.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.mid eq null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/info/angling/list.do" class="btn_size2 btn_bordergray">취소</a>
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


	var oEditors = [];
	
	
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "body",
	    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
	    
	    fOnAppLoad : function(){
	    	oEditors.getById["body"].exec("PASTE_HTML", [" "]);
	    },
	    fCreator: "createSEditor2"
	});

    //전송버튼 클릭이벤트
	function submitContents() {
    
	// 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", [ ]);

	if($("#title").val()== ""){
		alert("제목을 입력하세요.");
		return false;
	}
	    	
    	
	if($("#writer_name").val()== ""){
		alert("작성자를 입력하세요.");
		return false;
	}
    	
	if(document.getElementsByName("category").value==''){
		alert('카테고리를 선택하여 주세요.');
		return false;
	}
	
    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("body").value==' <p>&nbsp;</p>'){
    	alert('내용을 입력해주세요.');
    	return false;   	
    } 
     try {
    	 if($('#mid').val()!=''){
    		 	$("#imform").attr("action","/admin/info/angling/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/info/angling/insert_data.do");
				document.getElementById("imform").submit();				
			}
    	 
    } catch(e) {
     
    }
   
    
}
	$("#isftv").change(function(){
		if(this.checked){
			$('#ftv').val(1);	
		}else{
			$('#ftv').val(0);
		}
	});

	// textArea에 이미지 첨부
	function pasteHTML(filepath){
	    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
	    oEditors.getById["body"].exec("PASTE_HTML", [sHTML]);
	   
	}



</script>
