<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info"/>
<c:set var="pageName" value="fishjob" />
<%@include file="../layout/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="rev_sn" id="rev_sn" value="${info.rev_sn}"/>
		<input type="hidden" name="rev_email" id="rev_email">
		<input type="hidden" name="rev_phone" id="rev_phone">
		<input type="hidden" name="nak_id" id="nak_id" value="${nak_id}">
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시터 후기</h2>
				<div class="agree_box">
				<%--
				<h3>개인정보 수집 및 활용에 관한 동의</h3>
				<div class="agree_text">
					1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 사이트 회원관리 등<br />
					2. 수집항목 : 이름, 휴대폰번호, 전자우편<br/>
					3. 개인정보 보유·이용기간 : 동의일로부터 동의철회(직권해지)시 까지<br />
					4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br /><br />
					동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
				</div><br/>
				<div class="agree_yesorno">
					개인정보 수집 및 활용에 대하여 
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				                                                                                                                            
				</div>
				--%>
				<h3>게시물 작성시 유의사항</h3>
				<div class="agree_text" style="line-height:22px;">
					* 낚시터 후기 는 누구나 자유롭게 의견을 올릴 수 있는 공간입니다.<br />
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
				<dd><input type="text" size="100" class="write_input" placeholder="제목을 입력해주세요." id="rev_title" name="rev_title" value="${info.rev_title}"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 작성자</dt>
				<dd><input type="text" size="50" class="write_input" placeholder="작성자를 입력해주세요." id="rev_writer" name="rev_writer" value="${info.rev_writer }"/>
				<span class="txt_red">개인정보보호를 위해 실명기재를 금지합니다.</span>
				</dd>
			</dl>
			
			<dl>
				<dt>이메일</dt>
				<dd>
				<input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" name="rev_email1" id="rev_email1" value="${info.rev_email1 }">@
				<select id="email_addr" name="rev_email2" class="write_input" style="width:200px;" onchange="chgaddr();">					
					<option value="">선택하세요</option>
					<option value="naver.com" <c:if test="${info.rev_email2 eq 'naver.com' }">selected</c:if>>naver.com</option>
					<option value="daum.net" <c:if test="${info.rev_email2 eq 'daum.net' }">selected</c:if>>daum.net</option>
					<option value="nate.com" <c:if test="${info.rev_email2 eq 'nate.com' }">selected</c:if>>nate.com</option>
					<option value="yahoo.com" <c:if test="${info.rev_email2 eq 'yahoo.com' }">selected</c:if>>yahoo.com</option>
					<option value="gmail.com" <c:if test="${info.rev_email2 eq 'gmail.com' }">selected</c:if>>gmail.com</option>	
					<option value="paran.com" <c:if test="${info.rev_email2 eq 'paran.com' }">selected</c:if>>paran.com</option>
					<option value="hanmail.net" <c:if test="${info.rev_email2 eq 'hanmail.net' }">selected</c:if>>hanmail.net</option>
					<option id="self" value="1">직접 입력하기</option>
				</select>
			
				<input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요" id="rev_email2" value="${info.rev_email2 }">
				</dd>
			</dl>
			<dl>
				<dt>연락처</dt>
				<dd>
					<select class="naksi_select" id="rev_phone1" name="rev_phone1" value="${info.rev_phone1 }">
						<option<c:if test="${info.rev_phone1 eq '010' }">selected</c:if>>010</option>
						<option<c:if test="${info.rev_phone1 eq '011' }">selected</c:if>>011</option>
						<option<c:if test="${info.rev_phone1 eq '012' }">selected</c:if>>012</option>
						<option<c:if test="${info.rev_phone1 eq '014' }">selected</c:if>>014</option>
						<option<c:if test="${info.rev_phone1 eq '017' }">selected</c:if>>017</option>
						<option<c:if test="${info.rev_phone1 eq '018' }">selected</c:if>>018</option>
						<option<c:if test="${info.rev_phone1 eq '019' }">selected</c:if>>019</option>
						<option<c:if test="${info.rev_phone1 eq '070' }">selected</c:if>>070</option>
					</select> - 
					<input type="text" class="write_input" size="7" name="rev_phone2" maxlength="4"  id="rev_phone2" value="${info.rev_phone2 }"/> - 
					<input type="text" class="write_input" size="7" name="rev_phone3" maxlength="4"  id="rev_phone3" value="${info.rev_phone3 }"/>
				</dd>
			</dl>	
			<dl>
				<dt>비밀번호</dt>
				<dd><input type="password" id="rev_pass" name="rev_pass" value="${info.rev_pass }"></dd>
			</dl>
		
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="rev_cont" name="rev_cont" style="width:98%">${info.rev_cont}</textarea></dd>
			</dl>

				
			<dl>
				<dt>자동등록방지</dt>
				<dd>
					<div class="g-recaptcha" data-sitekey="${siteKey}"></div>
				</dd>
				<!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
				<input type="hidden" name="recaptcha" />
			</dl>
				
			<div id="btnArea">
				<ul class="floats">
					<a href="#;" class="btn_request btn_blue" onclick="submitContents()">저장</a>
					<a href="#;" onclick="cancel()"  class="btn_prev btn_white">취소</a></li>
				</ul>
			</div>
		</section>
	</div>

	
</form:form>
</div>

<%@include file="../layout/tail.jsp"%>


<script type="text/javascript">

$(document).ready(function(){
	//자신의 글 수정시 이메일 주소값을 직접입력하기로 입력한 경우 id="bo_email2"에 나타납니다.
	var x1 = document.getElementById("rev_email2");
	for(var i=0;i<imform.email_addr.length;i++){
		if(imform.email_addr.value == '' && $('#rev_sn').val()!=''){
			$('#self').attr("selected",true);
		}
	}
		
		if(imform.email_addr.value=='1'){ // 직접 입력
			x1.style.display = "inline";
		
		}else{ // 직접입력아닌경우
			x1.style.display="none";
		
		}
	
})



var x = document.getElementById("rev_email2");

function chgaddr(){
	
	if(imform.email_addr.value=='1'){ // 직접 입력
		x.style.display = "inline";
	
	}else{ // 직접입력아닌경우
		x.style.display="none";
	
	}
}







function cancel(){
	$("#imform").attr("action", "./view.do");
	document.getElementById("imform").submit();
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
	
	if(!ischk1){
		//alert("약관에 동의해주세요.")
		alert("게시물 작성시 유의사항에 동의해주세요.");
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		//alert("개인정보 수집 및 활동 동의가 필요합니다.");
		alert("게시물 작성시 유의사항 동의가 필요합니다.");
		return false;
	}
	
	if($("#rev_title").val()== ""){
		alert("제목을 입력하세요.");
		return false;
	}

	if($("#rev_writer").val()== ""){
		alert("작성자를 입력하세요.");
		return false;
	}
	
	var xy = document.getElementById("rev_email2");
	if($('#email_addr').val()=="1"){
		addr_value = $('#self').val();
		addr_value ="";
		addr_value=xy.value;
		$('#self').val(addr_value);
	}
	

	var email1 =  jQuery("#rev_email1").val();

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

	var phone_check = jQuery("#rev_phone2").val();
	var phone_check1 = jQuery("#rev_phone3").val();

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

	
	var phone1 = $("#rev_phone1").val();
	var phone2 = $("#rev_phone2").val();
	var phone3 = $("#rev_phone3").val();
	var phone = phone1 + "-" + phone2 + "-" + phone3;
	$("#rev_phone").val(phone);

	var email = email1 + "@" + email_addr.value;
	$("#rev_email").val(email);
	


//에디터의 내용에 대한 값 검증은 이곳에서
if ($("#rev_cont").val() == "") {
	alert('내용을 입력해주세요.');
	return false;

}   

var recaptcha = document.getElementById("g-recaptcha-response").value;

if (recaptcha.length < 1) {
	alert('자동등록방지를 눌러주세요.');
	return false;
} else {
	$('input[name=recaptcha]').val(recaptcha);
}
	try {

		if ($('#rev_sn').val() != '') {
				$("#imform")
						.attr("action", "/info/review/update_data.do");
				$("#imform").submit();
			} else {
				$("#imform")
						.attr("action", "/info/review/insert_data.do");
				document.getElementById("imform").submit();
			}

		} catch (e) {

		}

		
	
	}
</script>
