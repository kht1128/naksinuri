<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="2"/>
<c:set var="depthName" value="info"/>
<c:set var="pageName" value="fishjob"/>


<%@include file="../../layout/m/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="report_sn" id="report_sn" value="${info.report_sn}"/>
		<!-- <input type="hidden" name="report_phone" id="report_phone"/>
		<input type="hidden" name="report_email" id="report_email"/> -->
		<input type="hidden" name="report_atch_file" id="report_atch_file" value="${info.report_atch_file}">
		<input type="hidden" name="fileSn" id="fileSn">
		<input type="hidden" name="atchFileId" id="atchFileId">
		<input type="hidden" name="fileListCnt">
	
	
	
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>잘못된 정보 신고</h2>
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
					개인정보 수집 및 활용에 대하여 
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
			
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				                                                                                                                            
				</div>
				--%>
				<h3>게시물 작성시 유의사항</h3>
				<div class="agree_text" style="line-height:22px;">
					* 잘못된 정보 신고 는 누구나 자유롭게 의견을 올릴 수 있는 공간입니다.<br />
					* 광고성, 타인을 근거 없이 비방하거나 욕설, 음란성, 도배성 게시물은 삭제될 수 있음을 알려드립니다.<br />
					* 개인정보는 수집하지 않으므로 실명, 전화번호, 이메일, 주민번호, 주소 등 모든 개인정보의 기재를 금합니다.
				</div><br/>
				<div class="agree_yesorno">
					<p>게시물 작성시 유의사항에 </p>
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				</div>
			</div>
			<dl>
				<dt><span class="colorRed">*</span> 상호명</dt>			
				<dd><input type="text" class="write_input w100" placeholder="제목을 입력해주세요." id="co_nm" name="co_nm" value="${co_nm}"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>			
				<dd><input type="text" class="write_input w100" placeholder="제목을 입력해주세요." id="report_subject" name="report_subject"/></dd>
			</dl>
	
			<dl>
				<dt><span class="colorRed">*</span> 작성자</dt>
				<dd><input type="text" class="write_input w100" placeholder="작성자를 입력해주세요." id="report_writer" name="report_writer"/>
				<span class="txt_red">개인정보보호를 위해 실명기재를 금지합니다.</span>
				</dd>
			</dl>
			<!-- <dl>
				<dt>이메일</dt>
				<dd><input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" name="report_email1" id="report_email1">@
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
				<dd><input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요" name="report_email2" id="report_email2"></dd>
			</dl>
			<dl>
				<dt>연락처</dt>
				<dd>
					<select class="naksi_select" id="report_phone1" name="report_phone1">
						<option>010</option>
						<option>011</option>
						<option>012</option>
						<option>014</option>
						<option>017</option>
						<option>018</option>
						<option>019</option>
						<option>070</option>
					</select> - 
					<input type="text" class="write_input" size="7" name="report_phone2" maxlength="4"  id="report_phone2"/> - 
					<input type="text" class="write_input" size="7" name="report_phone3" maxlength="4"  id="report_phone3"/>
				</dd>
			</dl> -->
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="report_cont" name="report_cont" style="width:98%"></textarea></dd>
			</dl>
			<dl>
				<dt>파일첨부</dt>
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
											<input type="file" size="50" name="report_file_0" class="write_file" />
										</li>								
									</ul>
						
									
							
					</div>
					<div id="btnArea">
				
						<td><a href="#;" class="btn_white" onclick="insertinput()">첨부파일 추가 </a></td>

						<td><a href="#;" class="btn_gray" onclick="deleteinput()">파일 삭제</a></td>
					</div>
					
					
					
				</dd>
			</dl>
			<!--  -->
<!-- 			<dl> -->
<!-- 				<dt>자동등록방지</dt> -->
<!-- 				<dd><div class="g-recaptcha" data-sitekey="6LflyDIUAAAAAB-PMisxT_D6bmIKkzyKVr_Qrd0P"></div></dd> https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
<!-- 			</dl> -->
				<dl>
					<dt>자동등록방지</dt>
					<dd>
						<div class="g-recaptcha" data-sitekey="${siteKey}"></div>
					</dd>
					<!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
					<input type="hidden" name="recaptcha" />
				</dl>
			<!--  -->
			<div id="btnArea">
				<ul class="floats">
					<a href="#;" class="btn_request btn_blue" onclick="submitContents()">확인</a>
					<a href="/index.do" class="btn_prev btn_white">취소</a></li>
				</ul>
			</div>
		</section>
	</div>

</form:form>
</div>
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

function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["report_cont"].exec("PASTE_HTML", [sHTML]);
   
}


var x = document.getElementById("report_email2");

function chgaddr(){
		
		if(imform.email_addr.value=='1'){ // 직접 입력
			x.style.display = "inline";
			imform.report_email2.value="";
			imform.report_email2.focus();
		}else{ // 직접입력아닌경우
			x.style.display="none";
			imform.report_email2.value=imform.email_addr.value;
		}
}

//전송버튼 클릭이벤트
function submitContents() {	

var ischk1= false;
var chk1 = document.getElementsByName("agreeing");

for(var i=0;i<chk1.length;i++){
    if(chk1[i].checked == true) {
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


if($("#report_subject").val()== ""){
	alert("제목을 입력하세요.");
	return false;
}

if($("#report_writer").val()== ""){
	alert("작성자를 입력하세요.");
	return false;
}

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
if ($("#report_cont").val() == " <p>&nbsp;</p>") {
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
	$("#report_email").val(email); */
	
	document.getElementById("imform").submit();

	alert("관리자에게 전송 되었습니다.")
}

	

	}
</script>
<%@include file="../../layout/m/tail.jsp"%>
