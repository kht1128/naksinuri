<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>

<c:set var="depthName" value="gongmo" />
<c:set var="pageName" value="event" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="evn_no" id="evn_no" value="${info.evn_no }"/>
<input type="hidden" name="returnUrl" value="<c:url value='/admin/gongmo/event/event_findCorp.do'/>"/>
<input type="hidden" name="evn_sub_img" value="${info.evn_sub_img}">
<input type="hidden" name="evn_main_img" value="${info.evn_main_img}">
<input type="hidden" name="evn_atch_file" value="${info.evn_atch_file}">
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
<input type="hidden" id="spevn_no" value="${info.evn_no}" />
<input type="hidden" name="eco_no" id="eco_no"/>

	<div id="container">
		
		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>이벤트 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
										
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="evn_writer" id="evn_writer" class="frm_input" value="${info.evn_writer}"  /></td>
							</tr>
							<!-- 
							<tr>
								<th>패스워드</th>
								<td><input type="password" name="evn_pass" id="evn_pass" class="frm_input" value="${info.evn_pass}"/></td>
							</tr>
							 -->
							<tr>
								<th>제목</th>
								<td><input type="text" name="evn_subject" id="evn_subject" class="frm_input" value="${info.evn_subject}" style="WIDTH: 100%;"/></td>
							</tr>
							<tr class="toggle_visible" id="evn_stt">
								<th>이벤트 기간</th>
								<td><input type="text"  name="evn_startdate" id="evn_startdate" class="frm_input" value="${info.evn_startdate }">
								~<input type="text"  name="evn_enddate" id="evn_enddate" class="frm_input" value="${info.evn_enddate }"></td>	
										
							</tr>
							
							<tr>
								<th>내용</th>
								<td><textarea name="evn_content" id="evn_content"  class="frm_input"  style="WIDTH: 100%">${info.evn_content}</textarea></td>
							</tr>							
							<tr class="toggle_visible">
								<th>대표이미지</th>
								<c:choose>
									<c:when test="${emimg ne null }">
										<td>
										<c:out value="${emimg.orignl_file_nm}"/>&nbsp;<c:out value="[${emimg.file_size} Byte]"/>
											<input type="hidden" id="mimg" value="${emimg.evn_main_img}"/>
											<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${emimg.evn_main_img}"/>','<c:out value="${emimg.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" name="mimg" accept="image/*" id="mimg" style="width:290px" /></td>
									</c:otherwise>			
								</c:choose>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<a href="#;" class="btn_size1 btn_violet" onclick="insertinput()">첨부파일 추가 </a>
									<a href="#;" class="btn_size1 btn_bordergray" onclick="deleteinput()">파일 삭제</a>
								</td>
							</tr>
							<!--첨부파일 추가  -->
							<c:forEach items="${filelist}" var="item">
							<tr>
								<th>첨부파일</th>
								<td>
									<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.evn_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
								</td>
							</tr>
							</c:forEach>
							<tr id="file_0">
								<th><label for="chart_0">첨부파일</label></th>
								<td style="padding-top:10px;">
									<input type="checkbox" id="chart_0" name="atch_file_0"/>
									<input type="file" size="30" name="evn_file_0"  style="width:290px" class="file" />
								</td>
							</tr>	
						</tbody>
					</table>
					<%--
					<c:set var="evn_id" value="${info.evn_no }"/>
						<c:choose>
							<c:when test="${evn_id eq null }">
								
							</c:when>
							<c:when test="${evn_id ne null }">
								<%@include file="./evnadmin_reply.jsp"%>
							</c:when>
							
						</c:choose>
					 --%>
				</div>
			</section>

			<div class="btn_area textcenter">
						<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.evn_trash eq '1' }">
					<a href="/admin/gongmo/event/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.evn_trash eq '0' && info.evn_no ne null}">
					<a href="/admin/gongmo/event/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.evn_no eq null}">
					<a href="/admin/gongmo/event/list.do" class="btn_size2 btn_bordergray">취소</a>
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

<script type="text/javaScript">

function eco_del(){
	var form = document.getElementById('imform');
	var evn_no = $("#spevn_no").val();
	var eco_no4	=$("#eco_no4").val();
	$('#evn_no').val(evn_no);
	$('#eco_no').val(eco_no4);

	if(confirm("댓글을 정말 삭제하시겠습니까") == true){
	form.action = "./eco_delete.do";
	form.submit();
		
	
	}else{
		return false;
	}
	
}


	var j=0;
	function insertinput(){
		
	
		if($("#ftable tr").length>=18){
			alert("파일 첨부는 10개까지 가능합니다.");
			return false;
		}else{
		$(".t_write > tbody:last").append('<tr id="file_'+(j+1)+'">\
						<th><label for="chart_'+(j+1)+'">첨부파일</label></th>\
						<td style="padding-top:10px;">\
							<input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
							<input type="file" size="30" name="evn_file_'+(j+1)+'"  style="width:290px" class="file" /></td></tr>');
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
	
    var depthName = "${depthName}";
    var pageName = "${pageName}";
	
    //전역변수선언
    var oEditors = [];

   
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "evn_content",
        sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
        
        fOnAppLoad : function(){
        	oEditors.getById["evn_content"].exec("PASTE_HTML", [" "]);
        },
        fCreator: "createSEditor2"
    });

    //전송버튼 클릭이벤트
	function submitContents() {
    	
	
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["evn_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    if($("#evn_writer").val() == ""){
    	alert("글쓴이를 입력하세요.");
    	return false;
    }
    	
    
    if($("#evn_subject").val()== ""){
    	alert("제목을 입력하세요.");
    	return false;
    }

    
    if($("#evn_startdate").val() == ""){
    	alert("이벤트 시작일을 입력하세요.");
    	return false;
    }
    
    if($("#evn_enddate").val() == ""){
    	alert("이벤트 종료일을 입력하세요.");
    	return false;
    }
    
    if($("#evn_startdate").val() > $("#evn_enddate").val()){
    	alert("이벤트 기간을 다시 확인하세요.");
    	return false;
    }
    
    
    
    if($("#evn_content").val() == ""){
    	alert("이벤트 내용을 입력하세요.");
    	return false;
    }
    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("evn_content").value==' <p>&nbsp;</p>'){
    	alert('내용을 입력해주세요.');
    	return false;   	
    } 
    
    if(!$('#mimg').val()){
    	alert("대표이미지를 등록해주세요.");
    	return false;
    }
     try {
    	 if($('#evn_no').val()!=''){
    		 $("#imform").attr("action","/admin/gongmo/event/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/gongmo/event/insert_data.do");
				document.getElementById("imform").submit();						
			}
    	 
    } catch(e) {
     
    }
   
    
}

// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["evn_content"].exec("PASTE_HTML", [sHTML]);
   
}

function fn_egov_deleteFile(atchFileId, fileSn) {
	forms = document.getElementsByTagName("form");

	
	if(confirm("정말 삭제하시겠습니까??") == true){
		
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
	
	}else{
		return false;
	}
}





// $(document).ready(function(){
// 	$("#evnchk").change(function(){
// 		if($("#evnchk").is(":checked")){
// 			$(".toggle_visible").hide();
// 		}else{
// 			$(".toggle_visible").show();
// 		}
// 	})
// })
 $(document).ready(function(){
	$('#evn_startdate').datepicker({dateFormat : 'yy-mm-dd'});
	$('#evn_enddate').datepicker({dateFormat : 'yy-mm-dd'});
	 
		

 })
</script>
