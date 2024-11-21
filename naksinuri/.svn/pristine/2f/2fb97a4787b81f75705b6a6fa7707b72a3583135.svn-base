<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="customer_sound"/>
<c:set var="depthName" value="사이트이용안내"/>
<c:set var="pageName" value="고객의 소리"/>


<%@include file="../../layout/m/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="voc_no" id="voc_no" value="${info.voc_no}"/>
		<input type="hidden" name="voc_atch_file" id="voc_atch_file" value="${info.voc_atch_file}">
		<input type="hidden" name="fileSn" id="fileSn">
		<input type="hidden" name="atchFileId" id="atchFileId">
		<input type="hidden" name="co_nm" value="${co_nm}">
		<input type="hidden" name="voc_phone" value="${LoginVO.MBR_HP}">
		<input type="hidden" name="voc_email" value="${LoginVO.MBR_EMAIL}">

<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>고객의 소리</h2>
			<div class="agree_box">
				<h3>게시물 작성시 유의사항</h3>
				<div class="agree_text" style="line-height:22px;">
					* 낚시누리 고객의소리 는 누구나 자유롭게 의견을 올릴 수 있는 공간입니다.<br />
					* 광고성, 타인을 근거 없이 비방하거나 욕설, 음란성, 도배성 게시물은 삭제될 수 있음을 알려드립니다.<br />
					* 개인정보는 수집하지 않으므로 실명, 전화번호, 이메일, 주민번호, 주소 등 모든 개인정보의 기재를 금합니다.
				</div><br/>
				<div class="agree_yesorno">
					게시물 작성시 유의사항에 
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				</div>
			</div>
			<dl>
				<dt><span class="colorRed">*</span> 구분</dt>
				<dd>
					<ul class="choicebox floats">
						<li><input type="radio" id="checking1" name="voc_type" value="문의" /><label for="checking1"><span></span>문의</label></li>
						<li><input type="radio" id="checking2" name="voc_type" value="장애"/><label for="checking2"><span></span>장애</label></li>
						<li><input type="radio" id="checking3" name="voc_type" value="오류"<c:if test="${co_nm ne null }">checked</c:if>/><label for="checking3"><span></span>오류</label></li>
						<li><input type="radio" id="checking4" name="voc_type" value="의견"/><label for="checking4"><span></span>의견</label></li>
						<li><input type="radio" id="checking5" name="voc_type" value="기타"/><label for="checking5"><span></span>기타</label></li>
					</ul>
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>
				<c:choose>
					<c:when test="${not empty co_nm}">
						<dd><input type="text" size="100" class="write_input w100" placeholder="제목을 입력해주세요." value="${co_nm}"/></dd>
					</c:when>
					<c:otherwise>
						<dd><input type="text" size="100" class="write_input w100" placeholder="제목을 입력해주세요." id="voc_subject" name="voc_subject" value="${info.voc_subject }"/></dd>
					</c:otherwise>
				</c:choose>
			</dl>
			<c:choose>
				<c:when test="${not empty LoginVO and not empty LoginVO.MBR_ID}">
					<dl>
						<dt><span class="colorRed">*</span>작성자</dt>
						<dd><input type="text" name="voc_name" id="voc_name" class="basic_input w40" title="작성자" value="${LoginVO.MBR_NM}" disabled/></dd>
					</dl>
				</c:when>
				<c:otherwise>
					<dl>
						<dt><span class="colorRed">*</span>작성자</dt>
						<dd><input type="text" name="voc_name" id="voc_name" class="basic_input" title="작성자"/> <span class="red-600">개인정보보호를 위해 실명기재를 금지합니다.</span></dd>
					</dl>
				</c:otherwise>
			</c:choose>
			<dl>
				<dt><span class="colorRed">*</span>비밀번호</dt>
				<dd><input type="password" name="voc_pwd" id="voc_pwd" class="basic_input w40" title="비밀번호"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 비밀글여부</dt>
				<dd>
					<ul class="radio_check nopm floats liw100 pt-10">
						<li>
							<input type="radio" class="basic_radio" name="voc_issecret" id="voc_issecret1" value="0" checked>
							<label class="mr-10" for="voc_issecret1">
								<span></span>비공개
							</label>
						</li>
						<li>
							<input type="radio" class="basic_radio mr-10" name="voc_issecret" id="voc_issecret2" value="1">
							<label class="mr-10"  for="voc_issecret2">
								<span></span>공개</label>
						</li>
					</ul>
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="voc_content" name="voc_content" style="width:98%">${info.voc_content}</textarea></dd>
			</dl>
			<dl>
				<dt>파일첨부</dt>
				<dd>
					<div class="file_input" id="file_input">
						
<!-- 							<li><input type="checkbox" id="filing1" name="filing1" /><label for="filing1"><span></span></label> <input type="file" size="50" class="write_file" /></li> -->
<!-- 							<li><input type="checkbox" id="filing2" name="filing2" /><label for="filing2"><span></span></label> <input type="file" size="50" class="write_file" /></li> -->
<!-- 							<li><input type="checkbox" id="filing3" name="filing3" /><label for="filing3"><span></span></label> <input type="file" size="50" class="write_file" /></li> -->
				
							<c:forEach items="${filelist}" var="item">
									<ul>
										
										<li>
											<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.voc_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
										</li>
									</ul>
								</c:forEach>
									<ul id="file_0">
										<li>
											<input type="checkbox" id="chart_0" name="atch_file_0" />
											<label for="chart_0"><span></span></label> 
											<input type="file" size="50" name="voc_file_0" class="write_file" />
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
				<c:if test="${info.voc_no eq null}">
					<dl>
						<dt>자동등록방지</dt>
						<dd>
							<div class="g-recaptcha" data-sitekey="${siteKey}"></div>
						</dd>
						<!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
						<input type="hidden" name="recaptcha" />
					</dl>
				</c:if>
			<!--  -->
			<div id="btnArea">
				<ul class="floats">
					<a href="#;" class="btn_request btn_blue" onclick="submitContents()">확인</a>
					<a href="/policy/m/customer_sound_list.do" class="btn_prev btn_white">취소</a></li>
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
						<input type="file" size="30" name="voc_file_'+(j+1)+'"  style="width:290px" class="write_file" /></li></li></ul>');
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
    oEditors.getById["voc_content"].exec("PASTE_HTML", [sHTML]);
   
}

//전송버튼 클릭이벤트
function submitContents() {
	
// 에디터의 내용이 textarea에 적용된다.


var ischk1= false;
var ischk2= false;
var chk1 = document.getElementsByName("agreeing");
var chk2 = document.getElementsByName("voc_type");

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
for(var i=0;i<chk2.length;i++){
    if(chk2[i].checked == true) {
    	ischk2 = true;
        break;
    }
}

if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
	//alert("개인정보 수집 및 활동 동의가 필요합니다.");
	alert("게시물 작성시 유의사항 동의가 필요합니다.");
	return false;
}


if(!ischk2){
	alert("구분을 선택해주세요.")
	return false;
}



if($("#voc_subject").val()== ""){
	alert("제목을 입력하세요.");
	return false;
}

if($("#voc_name").val()== ""){
	alert("작성자를 입력하세요.");
	return false;
}

if($("#voc_pwd").val()== ""){
	alert("비밀번호를 입력해주세요.");
	return false;
}

//에디터의 내용에 대한 값 검증은 이곳에서
if ($("#voc_content").val() == " <p>&nbsp;</p>") {
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

	$("#imform").attr("action", "/policy/customer_sound/m/insert_data.do");
	document.getElementById("imform").submit();

	alert("관리자에게 전송 되었습니다.")
}

	

	}
</script>

<%@include file="../../layout/m/tail.jsp"%>
