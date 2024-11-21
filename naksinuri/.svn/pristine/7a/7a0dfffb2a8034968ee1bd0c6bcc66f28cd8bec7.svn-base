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
<c:set var="depthName" value="낚시정보" />
<c:set var="pageName" value="낚시터정보" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" action="./insert_data.do" enctype="multipart/form-data" >
<input type="hidden" name="bo_type" value="fishjoblist" />
<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}" />
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>채비필수 묶음법 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="bo_name" id="bo_name" class="frm_input" value="${info.bo_name}"  /></td>
							</tr>
							<tr>
								<th>패스워드</th>
								<td><input type="password" name="bo_pass" id="bo_pass" class="frm_input" value="${info.bo_pass}"/></td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name="bo_subject" id="bo_subject" class="frm_input" value="${info.bo_subject}"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="bo_content" id="bo_content"  class="frm_input"  style="WIDTH: 100%">${info.bo_content}</textarea></td>
							</tr>		
							<tr>
								<th>대표이미지</th>
								<td style="padding-top:10px;"><input type="file" size="30" name="bo_main_img" accept="image/*" id="mascortimg" style="width:290px" /></td>
							</tr>			
							<tr>
								<th>작성자이미지</th>
								<td style="padding-top:10px;"><input type="file" size="30" name="bo_sub_img" accept="image/*" id="mascortimg" style="width:290px" /></td>
							</tr>
							<tr>
								<th>작성자정보</th>
								<td><textarea name="bo_info" id="bo_info"  class="frm_input"  style="WIDTH: 100%">${info.bo_info}</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				<a href="boatfishing.do" class="btn_size2 btn_bordergray">취소</a>
				<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
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
        elPlaceHolder: "bo_content",
        sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
        
        fOnAppLoad : function(){
        	oEditors.getById["bo_content"].exec("PASTE_HTML", [" "]);
        },
        fCreator: "createSEditor2"
    });

    //전송버튼 클릭이벤트
	function submitContents() {
    	
	
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["bo_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("bo_content").value==''){
    	alert('내용을 입력해주세요.');
    	return false;   	
    } 
     try {
    	 if($('#bo_sn').val()!=''){
    		 
				$("#imform").attr("action","./update_data.do");
				$("#imform").submit(); 
			}else{
				document.getElementById("imform").submit();				
			}
    	 
    } catch(e) {
     
    }
   
    
}

// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["bo_content"].exec("PASTE_HTML", [sHTML]);
   
}



</script>
