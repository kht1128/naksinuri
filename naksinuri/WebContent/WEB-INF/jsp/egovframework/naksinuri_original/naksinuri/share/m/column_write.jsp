<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="3"/>
<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />

<%@include file="../../layout/m/head.jsp"%>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}"/>
		<input type="hidden" name="returnUrl" value="<c:url value='/share/column/updateview.do'/>"/>
		<input type="hidden" name="bo_atch_file" id="bo_atch_file" value="${info.bo_atch_file}">
		<input type="hidden" name="fileSn" id="fileSn">
		<input type="hidden" name="atchFileId" id="atchFileId">
		<input type="hidden" name="bo_main_img" value="${info.bo_main_img}">
		<input type="hidden" name="bo_sub_img" value="${info.bo_sub_img}">
		<input type="hidden" name="fileListCnt">
		<input type="hidden" name="pass_chk" value="${info.bo_pass }"/>
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시칼럼</h2>
				
			<div class="agree_box">
				<%--
				<h3>개인정보 수집 및 활용에 관한 동의</h3>
				<div class="agree_text">
					1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 사이트 회원관리 등<br />
					2. 수집항목 : 이름, 휴대폰번호, 전자우편<br />
					3. 개인정보 보유·이용기간 : 동의일로부터 동의철회(직권해지)시 까지<br />
					4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br /><br />
					동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
				</div><br/>
				<div class="agree_yesorno">
					개인정보 수집 및 활용에 대하여 <br/><br/>
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>                                                                                                                       
				</div>
				--%>
				<h3>게시물 작성시 유의사항</h3>
				<div class="agree_text" style="line-height:22px;">
					* 낚시누리 커뮤니티는 카테고리와 일치하는 내용을 누구나 자유롭게 의견을 올릴 수 있는 공간입니다.<br />
					* 광고성, 타인을 근거 없이 비방하거나 욕설, 음란성, 도배성 게시물은 삭제될 수 있음을 알려드립니다.<br />
					* 개인정보는 수집하지 않으므로 실명, 전화번호, 주민번호, 주소 등 모든 개인정보의 기재를 금합니다.
				</div><br/>
				<div class="agree_yesorno">
					게시물 작성시 유의사항에 
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				</div>
			</div>
			
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>
				<dd><input type="text" class="w100" class="write_input" placeholder="제목을 입력해주세요." id="bo_subject" name="bo_subject" value="${info.bo_subject }"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 작성자</dt>
				<dd><input type="text" class="w100" class="write_input" placeholder="작성자를 입력해주세요." id="bo_name" name="bo_name" value="${info.bo_name }"/>
					<span class="txt_red">개인정보보호를 위해 실명기재를 금지합니다.</span>
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 비밀번호</dt>
				<dd><input type="password" placeholder="제목을 입력해주세요." id="bo_pass" name="bo_pass" /></dd>
			</dl>
			<dl>
				<dt>* 대표 이미지</dt>
				<c:choose>
					<c:when test="${bmimg ne null }">
						<dd>
							<c:out value="${bmimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bmimg.file_size} Byte]"/>
							<input type="hidden" id="mimg" value="${bmimg.bo_main_img}">
							<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							 width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bmimg.bo_main_img}"/>','<c:out value="${bmimg.file_sn}"/>');" />
						</dd>
					</c:when>
					
					<c:otherwise>
					<dd><input type="file" id="mimg" name="mimg" ></dd>
					</c:otherwise>
				</c:choose>
			</dl>
			
			<dl>
				<dt>작성자 이미지</dt>
				<c:choose>
					<c:when test="${bsimg ne null }">
						<dd>
							<c:out value="${bsimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bsimg.file_size} Byte]"/>
							<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							 width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bsimg.bo_sub_img}"/>','<c:out value="${bsimg.file_sn}"/>');" />
						</dd>
					</c:when>
					
					<c:otherwise>
					<dd><input type="file" id="simg" name="simg" ></dd>
					</c:otherwise>
					
				</c:choose>
			</dl>
			<%--
			<dl>
				<dt>이메일</dt>
				<dd>
				<input type="text" class="write_input" style="width:140px;" placeholder="이메일을 입력하세요" name="bo_email1" id="bo_email1" value="${info.bo_email1 }">@
				<select id="email_addr" name="bo_email2" class="write_input" style="width:140px;" onchange="chgaddr();">					
					<option value="">선택하세요</option>
					<option value="naver.com"   <c:if test="${info.bo_email2 eq 'naver.com' }">selected</c:if>>naver.com</option>
					<option value="daum.net"    <c:if test="${info.bo_email2 eq 'daum.net' }">selected</c:if>>daum.net</option>
					<option value="nate.com"    <c:if test="${info.bo_email2 eq 'nate.com' }">selected</c:if>>nate.com</option>
					<option value="yahoo.com"   <c:if test="${info.bo_email2 eq 'yahoo.com' }">selected</c:if>>yahoo.com</option>
					<option value="gmail.com"   <c:if test="${info.bo_email2 eq 'gmail.com' }">selected</c:if>>gmail.com</option>	
					<option value="paran.com"   <c:if test="${info.bo_email2 eq 'paran.com' }">selected</c:if>>paran.com</option>
					<option value="hanmail.net" <c:if test="${info.bo_email2 eq 'hanmail.net' }">selected</c:if>>hanmail.net</option>
					<option id="self" value="1">직접 입력하기</option>
				</select>
			
				<input type="text" style="display:none;margin-top:15px; width:300px;" class="write_input" placeholder="이메일을 입력하세요."  id="bo_email2" value="${info.bo_email2 }">
				</dd>
			</dl>
			<dl>
				<dt>연락처</dt>
				<dd>
					<select class="naksi_select" id="bo_phone1" name="bo_phone1" value="${info.bo_phone1 }">
						<option<c:if test="${info.bo_phone1 eq '010' }">selected</c:if>>010</option>
						<option<c:if test="${info.bo_phone1 eq '011' }">selected</c:if>>011</option>
						<option<c:if test="${info.bo_phone1 eq '012' }">selected</c:if>>012</option>
						<option<c:if test="${info.bo_phone1 eq '014' }">selected</c:if>>014</option>
						<option<c:if test="${info.bo_phone1 eq '017' }">selected</c:if>>017</option>
						<option<c:if test="${info.bo_phone1 eq '018' }">selected</c:if>>018</option>
						<option<c:if test="${info.bo_phone1 eq '019' }">selected</c:if>>019</option>
						<option<c:if test="${info.bo_phone1 eq '070' }">selected</c:if>>070</option>
					</select> - 
					<input type="text" class="write_input" size="7" name="bo_phone2" maxlength="4"  id="bo_phone2" value="${info.bo_phone2 }"/> - 
					<input type="text" class="write_input" size="7" name="bo_phone3" maxlength="4"  id="bo_phone3" value="${info.bo_phone3 }"/>
				</dd>
			</dl>
			 --%>
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="bo_content" name="bo_content" style="width:98%">${info.bo_content}</textarea></dd>
			</dl>
			<dl>
				<dt>파일첨부</dt>
				<dd>
					<div class="file_input" id="file_input">
										
						<c:forEach items="${filelist}" var="item">
								<ul id="file_0">
									
									<li>
										<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
											<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.bo_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
									</li>
								</ul>
							</c:forEach>
							<input type="checkbox" id="chart_0" name="atch_file_0" />
							<label for="chart_0" ><span></span></label> 								
							<input type="file" size="30" id="chart_0" name="bo_file_0" class="write_file" />	
							
					</div>
					<div id="btnArea">
				
						<td><a href="#;" class="btn_white" onclick="insertinput()">첨부파일 추가 </a></td>

						<td><a href="#;" class="btn_gray" onclick="deleteinput()">파일 삭제</a></td>
					</div>
					
					
					
				</dd>
			</dl>

			<div id="btnArea">
				<ul class="floats">
					<a href="#;" class="btn_request btn_blue" onclick="submitContents()">정보등록신청</a>
					<a href="#;" onclick="cancel()"  class="btn_prev btn_white">취소</a></li>
				</ul>
			</div>
		</section>
	</div>

	
</form:form>
</div>



<script type="text/javascript">


$(document).ready(function(){
	/*
	//자신의 글 수정시 이메일 주소값을 직접입력하기로 입력한 경우 id="bo_email2"에 나타납니다.
	var x1 = document.getElementById("bo_email2");
	for(var i=0;i<imform.email_addr.length;i++){
		if(imform.email_addr.value == '' && $('#bo_sn').val()!=''){
			$('#self').attr("selected",true);
		}
	}
	

	

	
		
		if(imform.email_addr.value=='1'){ // 직접 입력
			x1.style.display = "inline";
		
		}else{ // 직접입력아닌경우
			x1.style.display="none";
		
		}
	*/
})

var j=0;
function insertinput(){

	if($("#file_input ul").length>=10){
		alert("파일 첨부는 10개까지 가능합니다.");
		return false;
	}else{
	$("#file_input").append('<ul id="file_'+(j+1)+'">\
			<li style="padding-top:2px;">\
			<li><input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
			<label for="chart_'+(j+1)+'"><span></span></label>\
						<input type="file" size="30" id="chart_'+(j+1)+'" name="bo_file_'+(j+1)+'"  style="width:290px" class="write_file" /></li></li></ul>');
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



function cancel(){
	$("#imform").attr("action", "./view.do");
	document.getElementById("imform").submit();
}
var x = document.getElementById("bo_email2");

function chgaddr(){
	
	if(imform.email_addr.value=='1'){ // 직접 입력
		x.style.display = "inline";
		imform.bo_email2.value="";
		imform.bo_email2.focus();
	}else{ // 직접입력아닌경우
		x.style.display="none";
		imform.bo_email2.value=imform.email_addr.value;
	}
}


//전송버튼 클릭이벤트
function submitContents() {

	var ischk1 = false;
	var chk1 = document.getElementsByName("agreeing");
	
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	/*
	if(!ischk1){
		alert("약관에 동의해주세요.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}
	*/
	if(!ischk1){
		alert("게시물 작성시 유의사항에 동의해주세요.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("게시물 작성시 유의사항 동의가 필요합니다.");
		return false;
	}
	
	if($("#bo_subject").val()== ""){
		alert("제목을 입력하세요.");
		return false;
	}
	
	if($("#bo_name").val()== ""){
		alert("작성자를 입력하세요.");
		return false;
	}
	
	if(!$('#mimg').val()){
		alert('대표 이미지를 등록해주세요.');
		return false;
	}
	
	if($("#bo_pass").val()== ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	/*
	var xy = document.getElementById("bo_email2");
	if($('#email_addr').val()=="1"){
		addr_value = $('#self').val();
		addr_value ="";
		addr_value=xy.value;
		$('#self').val(addr_value);
	}
	
	var email1 =  jQuery("#bo_email1").val();

	var regex = /^([\w-]+(?:\.[\w-]+)*)/;

	if(regex.test(email1) === false){
		alert("잘못된 이메일 형식입니다.")
		return false;
	}

	var email2 =  jQuery("#email_addr").val();
	var regex2 = /((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; 
	
	if(regex2.test(email2) === false){
		alert("잘못된 이메일 형식입니다.")
		return false;
	}

	var phone_check = jQuery("#bo_phone2").val();
	var phone_check1 = jQuery("#bo_phone3").val();

	var regex3 = /\d{3,4}/;
	var regex4 = /\d{4}$/;

	if(regex3.test(phone_check) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}

	if(regex4.test(phone_check1) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}

	
	var phone1 = $("#bo_phone1").val();
	var phone2 = $("#bo_phone2").val();
	var phone3 = $("#bo_phone3").val();
	var phone = phone1 + "-" + phone2 + "-" + phone3;
	$("#bo_phone").val(phone);

	var email1 = $("#bo_email1").val();
	var email2 = $("#bo_email2").val();
	var email = email1 + "@" + email2;
	$("#bo_email").val(email);
	*/


//에디터의 내용에 대한 값 검증은 이곳에서
if ($("#bo_content").val() == " <p>&nbsp;</p>") {
	alert('내용을 입력해주세요.');
	return false;

}   
	try {

		if ($('#bo_sn').val() != '') {
				$("#imform")
						.attr("action", "/share/column/m/update_data.do");
				$("#imform").submit();
			} else {
				$("#imform")
						.attr("action", "/share/column/m/insert_data.do");
				document.getElementById("imform").submit();
			}

		} catch (e) {

		}

	}
</script>
<%@include file="../../layout/m/tail.jsp"%>
