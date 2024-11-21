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
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>${info.voc_subject}</h2>
			<dl class="floats">
				<dt>작성자</dt>
				<dd><input type="text" class="write_input w100" value="${info.voc_name}" disabled/></dd>
			</dl>
			<dl>
				<dt>작성일자</dt>
				<dd><input type="text" class="basic_input w100" value="${fn:substring(info.voc_insert_dt,0,19)}" disabled/></dd>
			</dl>
			<dl class="floats">
				<dt>구분</dt>
				<dd><input type="text" class="write_input w100" value="${info.voc_type}" disabled/></dd>
			</dl>
			<dl>
				<dt>공개여부</dt>
				<dd>
					<c:choose>
						<c:when test="${info.voc_issecret eq '0'}">
							<input type="text" class="write_input w100" value="비공개" disabled/>
						</c:when>
						<c:otherwise>
							<input type="text" class="write_input w100" value="공개" disabled/>
						</c:otherwise>
					</c:choose>
				</dd>
			</dl>
			<dl>
				<dt>파일첨부</dt>
				<dd>
					<c:if test="${not empty info.voc_atch_file}">
						<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                		 	  <c:param name="param_atchFileId" value="${info.voc_atch_file}" />
               			</c:import>
           		 </c:if>
				</dd>
			</dl>
			<dl>
				<dt>내용</dt>
				<dd>
					<div class="agree_box mt-0 mb-0">
						<div class="agree_text mb-0" style="line-height:20px;min-height:150px;">${info.voc_content}</div>
					</div>
				</dd>
			</dl>
			<c:choose>
				<c:when test="${info.voc_isanswer eq '1'}">
				<dl>
					<dt>답변</dt>
					<dd>
						<div class="agree_box mt-0 mb-0">
							<div class="agree_text mb-0" style="line-height:20px;min-height:150px;">${info.voc_answer_content}</div>
						</div>
					</dd>
					<%-- <dl>
						<div class="agree_box mt-20">
							<h3>답변</h3>
							<div class="agree_text" style="line-height:22px;">${info.voc_answer_content}</div>
						</div>
					</dl> --%>
					</dl>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
			<div id="btnArea" class="mt-0">
				<ul class="floats">
					<a href="/policy/m/customer_sound_list.do" class="btn_request btn_blue">목록</a>
				</ul>
			</div>
		</section>
	</div>
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
    //웹접근성대응 oEditors.getById["voc_content"].exec("PASTE_HTML", [sHTML]);
   
}

var oEditors = [];

/*
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "voc_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	//웹접근성대응 oEditors.getById["voc_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});
*/

//전송버튼 클릭이벤트
function submitContents() {
	
// 에디터의 내용이 textarea에 적용된다.
//웹접근성대응 oEditors.getById["voc_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

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

if(!ischk1){
	
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

	$("#imform").attr("action", "/policy/customer_sound/insert_data.do");
	document.getElementById("imform").submit();

	alert("관리자에게 전송 되었습니다.")
}

	

	}
</script>
