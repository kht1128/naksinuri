<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="depthName" value="share" />
<c:set var="pageName" value="travel" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="bo_type" value="travel" />
<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/${depthName}/${pageName}/board_findCorp.do'/>"/>
<input type="hidden" name="bo_sub_img" value="${info.bo_sub_img}">
<input type="hidden" name="bo_main_img" value="${info.bo_main_img}">
<input type="hidden" name="bo_atch_file" value="${info.bo_atch_file}">
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
	
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시여행기 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>		
							<!-- 게시물 승인 제도 추가 - 2019.07.31 -->
							<tr>
								<th>승인여부</th>
								<td>
									<select class="frm_select" name="bo_trash" id="bo_trash">
										<option value="0"<c:if test="${info.bo_trash eq '0'}">selected</c:if>>승인</option>
										<option value="3"<c:if test="${info.bo_trash eq '3'}">selected</c:if>>미승인</option>								
									</select> 
								</td>
							</tr>	
							<!-- 게시물 승인 제도 추가 - 2019.07.31 -->				
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="bo_name" id="bo_name" class="frm_input" value="${info.bo_name}"  /></td>
							</tr>
							<tr>
								<th>패스워드</th>
								<td><input type="password" name="bo_pass" id="bo_pass" class="frm_input" value="${info.bo_pass}"/></td>
							</tr>
							<tr>
								<th>여행 카테고리</th>
								<td>
									<select class="naksi_select" id="bo_cate" name="bo_cate" value="${info.bo_cate}">
										<option value="바다" <c:if test="${info.bo_cate eq '바다' }">selected</c:if>>바다</option>
										<option value="민물"<c:if test="${info.bo_cate eq '민물' }">selected</c:if>>민물</option>
									</select> 
								</td>
							</tr>	
							<tr>
								<th>제목</th>
								<td><input type="text" name="bo_subject" id="bo_subject" class="frm_input" style="WIDTH: 100%" value="${info.bo_subject}"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="bo_content" id="bo_content"  class="frm_input"  style="WIDTH: 100%; HEIGHT:500px;">${info.bo_content}</textarea></td>
							</tr>		
							<tr>
								<th>대표이미지</th>
								<c:choose>
									<c:when test="${bmimg ne null }">
										<td>
										<c:out value="${bmimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bmimg.file_size} Byte]"/>
											<input type="hidden" id="mimg" value="${bmimg.bo_main_img}"/>
											<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bmimg.bo_main_img}"/>','<c:out value="${bmimg.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" name="mimg" accept="image/*" id="mimg" style="width:290px" /></td>
									</c:otherwise>			
								</c:choose>
							</tr>
							<c:choose>
<c:when test="${info.bo_email ne null }">
<tr>
<th>이메일</th>
<td>
${info.bo_email}
</td>
</tr>
</c:when>
</c:choose>

<c:choose>
<c:when test="${info.bo_phone ne null }">
<tr>
<th>연락처</th>
<td>
${info.bo_phone}
</td>
</tr>
</c:when>
</c:choose>

																		
							<tr>
								<th>작성자이미지</th>
								<c:choose>
									<c:when test="${bsimg ne null }">
										<td>
											<ul>
												<li>
													<c:out value="${bsimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bsimg.file_size} Byte]"/>
														<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
										       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bsimg.bo_sub_img}"/>','<c:out value="${bsimg.file_sn}"/>');" />
												</li>
											</ul>
										</td>
										</c:when>
										<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" name="simg" accept="image/*" id="simg" style="width:290px" /></td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>작성자정보</th>
								<td><textarea name="bo_info" id="bo_info"  class="frm_input"  style="WIDTH: 100%">${info.bo_info}</textarea></td>
							</tr>
							<tr>
								<td></td>
								<td><a href="#;" class="btn_size1 btn_violet" onclick="insertinput()">첨부파일 추가 </a><a href="#;" class="btn_size1 btn_bordergray" onclick="deleteinput()">파일 삭제</a></td>
							</tr>
								<c:forEach items="${filelist}" var="item">
									<tr>
										<th>첨부파일</th>
										<td>
											<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.bo_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
										</td>
									</tr>
								</c:forEach>
									<tr id="file_0">
										<th><label for="chart_0">첨부파일</label></th>
										<td style="padding-top:10px;">
											<input type="checkbox" id="chart_0" name="atch_file_0"/>
											<input type="file" size="30" name="bo_file_0"  style="width:290px" class="file" />
										</td>									
									</tr>
						</tbody>
					</table>
						<%@include file="../comment.jsp"%>
				</div>
			</section>

			<div class="btn_area textcenter">
					<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.bo_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 / 게시물 승인 제도 추가 - 2019.07.31 -->
				<c:if test="${(info.bo_trash eq '0' or info.bo_trash eq '3') && info.bo_sn ne null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.bo_sn eq null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
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
    var j=0;

   
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "bo_content",
        sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
        htParams : {
    		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
    		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
    		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
    		bSkipXssFilter : true		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
    		
    	},
        
        fOnAppLoad : function(){
        	oEditors.getById["bo_content"].exec("PASTE_HTML", [" "]);
        },
        fCreator: "createSEditor2"
    });
    
    $(document).ready(function(){
		if($("#file_list_size").val()>=10){
				$("#file_0").remove();
		}
	});
    
	function insertinput(){
		
		
		if($("#ftable tr").length>=18){
			alert("파일 첨부는 10개까지 가능합니다.");
			return false;
		}else{
		$(".t_write > tbody:last").append('<tr id="file_'+(j+1)+'">\
						<th><label for="chart_'+(j+1)+'">첨부파일</label></th>\
						<td style="padding-top:10px;">\
							<input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
							<input type="file" size="30" name="bo_file_'+(j+1)+'"  style="width:290px" class="file" /></td></tr>');
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

    //전송버튼 클릭이벤트
	function submitContents() {
    	
	
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["bo_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("bo_content").value==''){
    	alert('내용을 입력해주세요.');
    	return false;   	
    } 
    
    if(!$('#mimg').val()){
    	alert("대표이미지를 등록해주세요.");
    	return false;
    }
     try {
    	 if($('#bo_sn').val()!=''){
    		 $("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/insert_data.do");
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

function fn_egov_deleteFile(atchFileId, fileSn) {
	forms = document.getElementsByTagName("form");

	for (var i = 0; i < forms.length; i++) {
		if (typeof(forms[i].atchFileId) != "undefined" &&
				typeof(forms[i].fileSn) != "undefined" &&
				typeof(forms[i].fileListCnt) != "undefined") {
			form = forms[i];
		}
	}
	//form = document.forms[0];
	form.atchFileId.value = atchFileId;
	form.fileSn.value = fileSn;
	form.action = "<c:url value='/naksinuri_original/cmm/fms/deleteFileInfs.do'/>";
	form.submit();
}


</script>
