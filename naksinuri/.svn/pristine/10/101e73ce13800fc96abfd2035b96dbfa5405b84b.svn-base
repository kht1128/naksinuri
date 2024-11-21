<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="auditor" />

<%@include file="../layout/newHead.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" action="/promotion/auditor/board_modify_act.do">
	<input type="hidden" name="fileSn" id="fileSn">
	<input type="hidden" name="atchFileId" id="atchFileId">
	<input type="hidden" name="fileListCnt">
	<input type="hidden" name="bo_sn" value="${info.bo_sn }">
		
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시명예감시원 활동보고 수정하기</h2>
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>			
				<dd><input type="text" class="write_input w70" placeholder="제목을 입력해주세요." id="bo_subject" name="bo_subject" title="제목" value="${info.bo_subject }" required></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 장소</dt>			
				<dd><input type="text" class="write_input w70" placeholder="장소를 입력해주세요." id="cg_location" name="cg_location" title="장소" value="${info.cg_location }" required></dd>
			</dl>
	
			<dl>
				<dt><span class="colorRed">*</span> 구분</dt>
				<dd><input type="text" class="write_input w50" placeholder="작성자를 입력해주세요." id="" name="" title="구분"/></dd>
			</dl>
			<dl>
				<dt>이미지</dt>
				<dd>
					<div class="file_input" id="file_input">
						<c:forEach items="${filelist}" var="item">
							<ul>
								<li>
									<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.report_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
								</li>
							</ul>
						</c:forEach>
						<ul id="file_0">
							<li>
								<input type="checkbox" id="chart_0" name="atch_file_0" />
								<label for="chart_0"><span></span></label> 
								<input type="file" size="50" name="report_file_0" class="write_file" title="파일첨부"/>
							</li>								
						</ul>
					</div>
					
					<div id="btnArea">
						<td><a href="#;" class="btn_white" onclick="insertinput()" title="파일첨부 추가">첨부파일 추가 </a></td>
						<td><a href="#;" class="btn_gray" onclick="deleteinput()" title="파일첨부 삭제">파일 삭제</a></td>
					</div>
				</dd>
			</dl>
			<!-- <dl>
				<dt><span class="colorRed">*</span> 이메일</dt>
				<dd><input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" name="bo_email1" id="bo_email1">@
				<select id="email_addr" class="write_input" style="width:200px;" onchange="chgaddr();">					
					<option value="">선택하세요</option>
					<option name="report_email2" value="naver.com">naver.com</option>
					<option name="report_email2" value="daum.net">daum.net</option>
					<option name="report_email2" value="nate.com">nate.com</option>
					<option name="report_email2" value="yahoo.com">yahoo.com</option>
					<option name="report_email2" value="gmail.com">gmail.com</option>	
					<option name="report_email2" value="paran.com">paran.com</option>
					<option name="report_email2" value="hanmail.net">hanmail.net</option>
					<option value="1">직접 입력하기</option>
				</select>
				</dd>
				<dd><input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요" name="bo_email2" id="bo_email2"></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 연락처</dt>
				<dd>
					<select class="naksi_select" id="bo_phone1" name="bo_phone1">
						<option>010</option>
						<option>011</option>
						<option>012</option>
						<option>014</option>
						<option>017</option>
						<option>018</option>
						<option>019</option>
						<option>070</option>
					</select> - 
					<input type="text" class="write_input" size="7" name="bo_phone2" maxlength="4"  id="report_phone2"/> - 
					<input type="text" class="write_input" size="7" name="bo_phone3" maxlength="4"  id="report_phone3"/>
				</dd>
			</dl> -->
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="bo_content" name="bo_content" style="width:98%" required>${info.bo_content }</textarea></dd>
			</dl>
			
			<div id="btnArea">
				<ul class="floats">
					<button type="submit" class="btn_request btn_blue">확인</button>
					<button type="button" class="btn_prev btn_white" onClick="history.go(-1);">취소</button>
				</ul>
			</div>
		</section>
	</div>

</form:form>
</div>

<%@include file="../layout/tail.jsp"%>

<script type="text/javascript">

var j=0;
function insertinput(){

	if($("#file_input ul").length>=10){
		alert("파일 첨부는 10개까지 가능합니다.");
		return false;
	}else{
	$("#file_input").append('<ul id="file_'+(j+1)+'">\
			<li style="padding-top:10px;">\
			<li><input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
			<label for="chart_'+(j+1)+'"><span></span></label>\
						<input type="file" size="30" name="report_file_'+(j+1)+'"  style="width:290px" class="write_file" /></li></li></ul>');
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

<%-- function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["report_cont"].exec("PASTE_HTML", [sHTML]);
   
} --%>


/* var x = document.getElementById("report_email2");

function chgaddr(){
		
	if(imform.email_addr.value=='1'){ // 직접 입력
		x.style.display = "inline";
		imform.report_email2.value="";
		imform.report_email2.focus();
	}else{ // 직접입력아닌경우
		x.style.display="none";
		imform.report_email2.value=imform.email_addr.value;
	}
} */

//전송버튼 클릭이벤트
/* $("#imform").on("submit", function(event) {
	event.preventDefault();

	var ischk1= false;
	var chk1 = document.getElementsByName("agreeing");
	
	for(var i=0;i<chk1.length;i++){
	    if(chk1[i].checked == true) {
	    	ischk1 = true;
	        break;
	    }
	}
	
	if(!ischk1){
		alert("개인정보 수집 및 활동에 관한 동의사항에 동의해주세요.");
		return;
	}
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동에 관한 동의가 필요합니다.");
		return;
	}
	
	document.getElementById("imform").submit();
	
/* var email =  jQuery("#report_email1").val();

var regex = /^([\w-]+(?:\.[\w-]+)*)/;

if(regex.test(email) === false){
	alert("잘못된 이메일 형식입니다.")
	return false;
}

var email2 =  jQuery("#report_email2").val();
var regex2 = /((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; 

if(regex2.test(email2) === false){
	alert("잘못된 이메일 형식입니다.")
	return false;
}

var phone_check = jQuery("#report_phone2").val();
var phone_check1 = jQuery("#report_phone3").val();

var regex3 = /\d{3,4}/;
var regex4 = /\d{4}$/;

if(regex3.test(phone_check) == false){
	alert("연락처는 숫자만 입력할 수 있습니다.")
	return false;
}

if(regex4.test(phone_check1) == false){
	alert("연락처는 숫자만 입력할 수 있습니다.")
	return false;
} */

//에디터의 내용에 대한 값 검증은 이곳에서
/* if ($("#report_cont").val() == " <p>&nbsp;</p>") {
	alert('내용을 입력해주세요.');
	return false;

} else {

	var recaptcha = document.getElementById("g-recaptcha-response").value;

	if (recaptcha.length < 1) {
		alert('자동등록방지를 눌러주세요.');
		return false;
	} else {
		$('input[name=recaptcha]').val(recaptcha);
	}

	$("#imform").attr("action", "/info/fishjobreport/insert_data.do");
	/* var phone1 = $("#report_phone1").val();
	var phone2 = $("#report_phone2").val();
	var phone3 = $("#report_phone3").val();
	var phone = phone1 + "-" + phone2 + "-" + phone3;
	$("#report_phone").val(phone);

	var email1 = $("#report_email1").val();
	var email2 = $("#report_email2").val();
	var email = email1 + "@" + email2;
	$("#report_email").val(email);
	
	
	document.getElementById("imform").submit();

	alert("관리자에게 전송 되었습니다.")
}


}); */
</script>
