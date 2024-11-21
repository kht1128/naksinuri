<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="depthName" value="info" />
<c:set var="pageName" value="safe" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<style>
#image_preview {
    display:none;
}
</style>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post"  enctype="multipart/form-data" >
<input type="hidden" name="bo_type" value="safe" />
<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/${depthName}/${pageName}/board_findCorp.do'/>"/>
<input type="hidden" name="bo_atch_file" value="${info.bo_atch_file}">
<input type="hidden" name="bo_mov_file" value="${info.bo_mov_file}">
<input type="hidden" name="file_list_size" id="file_list_size" value="${fn:length(filelist)}">
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
<input type="hidden" name="bo_pdf_img_file" value="${info.bo_pdf_img_file}">
<input type="hidden" name="pdfImgFileCnt" id="pdfImgFileCnt" value="">
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시안전 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
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
								<td><input type="text" name="bo_subject" id="bo_subject" class="frm_input" value="${info.bo_subject}" style="WIDTH: 100%;"/></td>
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
												<c:out value="${bmimg.orignl_file_nm }"/>&nbsp;<c:out value="[${bmimg.file_size} Byte]"/>
												<input type="hidden" id="mimg" value="${info.bo_main_img}"/>		
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" 
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${info.bo_main_img}"/>','<c:out value="${bmimg.file_sn}"/>');" />										
											</td>
										</c:when>
										<c:otherwise>
											<td style="padding-top:10px;"><input type="file" size="30" id="mimg" name="mimg" accept="image/*" id="mascortimg" style="width:290px" value=""/></td>
										</c:otherwise>
									</c:choose>									
							</tr>
							<tr>
								<th>동영상</th>
								<c:choose>
									<c:when test="${movfile ne null}">
										<td>
											<c:out value="${movfile.orignl_file_nm}"/>&nbsp;<c:out value="[${movfile.file_size} Byte]"/>
											<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${movfile.bo_mov_file}"/>','<c:out value="${movfile.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td><input type="file" id="movfile" name="movfile" style="width:290px" class="file"/></td>
									</c:otherwise>
								</c:choose>								
							</tr>
							  
							<tr>
								<th>유투브링크</th>
								<td>
								<input type="text" name="bo_youtubelink" id="bo_youtubelink" class="frm_input" value="${info.bo_youtubelink}" style="WIDTH: 100%;" placehold="유투브 링크URL 키값을 입력하세요. hFXtWQbb6nc"/>
								<!--
								<button onclick="addyoutubelink()";>추가</button>
								--> 
							
								<td> 
							</tr>
							
						
							<tr>
								<th>PDF슬라이드 이미지</th>
								<td>
									<a href="#;" class="btn_size1 btn_violet" onclick="insertimg()">이미지 추가</a><a href="#;" class="btn_size1 btn_gray" onclick="delrow()">이미지 삭제</a>
									<ul class="imgul" id="imgul" style="margin-bottom:10px">
										  <c:forEach var="pdfimgfile" items= "${pdfimgfile}" varStatus="status">
											<li><c:out value="${pdfimgfile.orignl_file_nm }"/>&nbsp;<c:out value="[${pdfimgfile.file_size} Byte]"/>							
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" 
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${pdfimgfile.bo_pdf_img_file}"/>','<c:out value="${pdfimgfile.file_sn}"/>');" />
							       			</li>
										  </c:forEach>			
										<li id="imgli_0">
											<em><input type="checkbox" id="pdfimgfile_0"  />
											<label for="pdfimgfile_0"><span></span></label></em><span><input type="file" size="30" id="egovComFileUploader" accept="image/*" name="pdfimgfile_1" value="${info.bo_pdf_img_file }"  /></span>
										</li>
									</ul>									
								</td>							
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
			
												
									
	<!-- //예전 주석되어 있던 정보
							<tr>
								<th>첨부파일</th>
								<td>
								    <p>
								        <input type="file" name="image" id="image" />
								    </p>
									<div id="image_preview">
									    <img src="#" />
									    <br />
									    <a href="#">첨부파일제거</a>
									
									</div>
								</td>
							</tr>
	-->					
												
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.bo_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.bo_trash eq '0' && info.bo_sn ne null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.bo_sn eq null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
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
<script type="text/javaScript" language="javascript">

	$(document).ready(function(){
		if($("#file_list_size").val()>=10){
				$("#file_0").remove();
		}
		
	
	});

	function addyoutubelink() {
		var youtubeurl = $('#bo_youbute').val();
		
		var sHTML = '<div class="youtube-embed-wrapper" style="position:relative;padding-bottom:56.25%;padding-top:30px;height:0;overflow:hidden"><iframe width="640" height="480" src="'+youtubeurl+'?autoplay=1" style="position:absolute;top:0;left:0;width:100%;height:100%" frameborder="0" allowfullscreen=""></iframe></div>';
		//oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
		oEditors.getById["bo_content"].exec("PASTE_HTML", [sHTML]);

	}
		
	

	//첨부파일 행 추가 삭제
	var j=0;
	function insertinput(){
			
	
		if($("#ftable tr").length>=16){
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


jQuery('#image').on('change', function () {
    ext = jQuery(this).val().split('.').pop().toLowerCase();
    if (jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        resetFormElement(jQuery(this));
        window.alert('Not an image!');
    } else {
        file = jQuery('#image').prop("files")[0];
        blobURL = window.URL.createObjectURL(file);
        jQuery('#image_preview img').attr('src', blobURL);
        jQuery('#image_preview').slideDown();
        jQuery(this).slideUp();
    }
});

/**
onclick event handler for the delete button.
It removes the image, clears and unhides the file input field.
*/
jQuery('#image_preview a').bind('click', function () {
    resetFormElement(jQuery('#image'));
    jQuery('#image').slideDown();
    jQuery(this).parent().slideUp();
    return false;
});

/** 
 * Reset form element
 * 
 * @param e jQuery object
 */
function resetFormElement(e) {
    e.wrap('<form>').closest('form').get(0).reset();
    e.unwrap();
}


var depthName = "${depthName}";
var pageName = "${pageName}";


//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "bo_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["bo_content"].exec("PASTE_HTML", [" "]);
    },
    htParams : {
    	bSkipXssFilter : true
    },    
    fCreator: "createSEditor2"    
});

//전송버튼 클릭이벤트
function submitContents() {
	
	if(!$('#bo_subject').val()){
		alert('제목을 입력해주세요.');
		return false;
	}
	
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["bo_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("bo_content").value==''){
    	alert('내용을 입력해주세요.');
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

$("document").ready(function(){
    $("#movfile").change(function() {
	if( $("#movfile").val() != "" ){
	    var ext = $('#movfile').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['mp4','mkv','avi','wmv']) == -1) {
	       alert('mp4,mkv,avi,wmv 파일만 업로드 할수 있습니다.');
	       $("#movfile").val("");
		   }
		}
	});
});


//행 추가 삭제
var i = 1;
function insertimg(){		
	//if($("ul#imgul").children().size()==10){
		//alert("이미지는 10개까지 추가 가능합니다.");
		//return false;
	//}else{
		$('#imgul').append('<li id="imgli_'+i+'" name="imgli[]"><em><input type="checkbox" id="pdfimgfile_'+i+'" /><label for="pdfimgfile_'+i+'"><span>&nbsp;</span></label></em><span><input type="file" size="30" id="file"  name="pdfimgfile_'+(i+1)+'" accept="image/*" /></span></li>');	
		i++;
	//}
	$("#pdfImgFileCnt").val(i);
}
function delrow(){		
	for(var j=0;j<=i;j++){
		if($("#pdfimgfile_"+j).prop("checked")) {
			$("#imgli_"+j).remove();
		}
	}
	$("#pdfImgFileCnt").val(i);
}

</script>
