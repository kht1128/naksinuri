<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="pageName" value="auditor" />

<%@include file="../../layout/m/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" action="/promotion/auditor/board_write_act.do">
	<input type="hidden" name="fileSn" id="fileSn">
	<input type="hidden" name="atchFileId" id="atchFileId">
	<input type="hidden" name="fileListCnt">
		
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시명예감시원 활동보고</h2>
			<div class="agree_box">
				<h3>개인정보 수집 및 활용에 관한 동의</h3>
				<div class="agree_text" style="line-height:22px;">
					1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락통지, 처리결과 통보, 사이트 회원관리 등<br>
					2. 수집항목 : 이름, 휴대폰번호, 전자우편<br>
					3. 개인정보 보유이용기간 : 동의일로부터 동의절회(직권해지)시 까지<br>
					4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br><br>
					동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
				</div>
				<br>
				<div class="agree_yesorno">
					개인정보 수집 및 활용에 대하여
					<input type="radio" id="agreeingy" name="agreeing" value="동의"><label for="agreeingy"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){ $('#agreeingy').click();}"></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"><label for="agreeingn"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){ $('#agreeingn').click();}"></span>동의하지 않습니다.</label>
				</div>
			</div>
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>			
				<dd><input type="text" class="write_input w100" placeholder="제목을 입력해주세요." id="bo_subject" name="bo_subject" title="제목" required></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 장소</dt>			
				<dd><input type="text" class="write_input w100" placeholder="장소를 입력해주세요." id="cg_location" name="cg_location" title="장소" required></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 구분</dt>
				<dd><input type="text" class="write_input w100" placeholder="구분을 입력해주세요." id="" name="" title="구분"/></dd>
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
			
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="bo_content" name="bo_content" style="width:98%" required></textarea></dd>
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

<%@include file="../../layout/m/tail.jsp"%>

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
$("#imform").on("submit", function(event) {
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
});

</script>
