<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>

<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();
cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1); //월은 -1해줘야 해당월로 인식
String lastDay = String.format("%02d", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//System.out.println("해당년도: "+cal.get(Calendar.YEAR));
//System.out.println("해당월: "+(cal.get(Calendar.MONTH)+1)); //보여줄때 +1로 하여 사람기준으로 설정
//System.out.println("마지막 일(현재 날짜 기준 최대수): "+cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //기본적으로 이걸 사용
%>

<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet> 

<style>
	.emphasis {background-color:#f9f2f4; padding: 10px 4px; border-radius: 4px; color: #c7254e; text-align: center; font-size: 1.5rem;}
	.under-blue {padding: 10px 4px; border-bottom: 3px solid #23527c; font-weight: 900; font-size: 1.5rem;}
	.content .text-title {font-size: 23px; margin-bottom: 0 !important; line-height: 25px;}
</style>

<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>
<c:set var="lastday" value="<%=lastDay%>"/>

<form:form commandName="mbrHpChngVO" id="listForm" name="listForm" method="post" >
	<input type="hidden" name="MHC_INDVDL_INFO_ST" value="${MHC_INDVDL_INFO_ST}"/>
	<div class="content respon3">
		<section id="writeForm" class="write_box">
			<h2 class="font-weight-bold text-title mt-10">본인명의 휴대폰 미소지자<br>정보 변경 신청서</h2>
			<p class="dottedbox font-weight-bold mt-20 mb-20 emphasis">이름,생년월일,연락처는 로그인(본인인증)시 본인여부 확인에 사용되므로 불일치시 로그인이 불가능 할 수 있으므로 주의하여 입력해주시기 바랍니다.</p>
			<p class="dottedbox">이메일 naksinuri@fipa.or.kr / 팩스 0505-742-1004 <br>/ 문의 ☎1833-7139</p>
			<div class="agree_box">
				<h3 class="mt-10 under-blue wx140">교육 이수자</h3>
				<table class="basic_tbl mt-10">
					<caption>본인명의 휴대폰 미소지자 정보 변경 신청서</caption>
					<colgroup>
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>		
						<tr>
							<td>
								<h4>성 명 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 " name="MHC_EDU_NM" value="" placeholder="성명을 입력해주세요." data-fail-message="성명은 필수 입력정보입니다." autocomplete="off"required  >
							</td>
						</tr>
						<tr>
							<td>
								<h4>생 년 월 일 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-birth-input-pattern" name="MHC_EDU_BRTHDY" value="" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" required >
							</td>
						</tr>
						<!-- <tr>
							<th class="text-center">주 소 <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100px mr-5" id="RMNDR_MBR_ZIP" name="RMNDR_MBR_ZIP" placeholder="우편번호" readonly />
								<button type="button" onclick="zipcode('RMNDR_MBR_ZIP','RMNDR_MBR_ADDR1','RMNDR_MBR_ADDR2');" class="btn">우편번호 찾기</button> 
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR1" name="RMNDR_MBR_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly>
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR2" name="RMNDR_MBR_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" >
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
								<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>	
							</td>
						</tr> -->
						<tr>
							<td>
								<h4>연 락 처(휴대폰) <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-hp-input-pattern" name="MHC_EDU_HP" value="" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" required >
							</td>
						</tr>
						<tr>
							<td>
								<h4>어선명</h4>
								<input type="text" class="basic_input w100" name="MHC_EDU_SHIP" value="" placeholder="예)낚시누리호" data-fail-message="어선명을 입력하세요." autocomplete="off">
							</td>
						</tr>					
					</tbody>
				</table>
			</div>
			
			<div class="agree_box">
				<h3 class="mt-10 wx140 under-blue">핸드폰 명의자</h3>
				<table class="basic_tbl mt-10">
					<caption>본인명의 휴대폰 미소지자 정보 변경 신청서</caption>
					<colgroup>
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>		
						<tr>
							<td>
							<h4>성 명 <span class="red-600">*</span></h4>
							<input type="text" class="basic_input w100 " name="MHC_NM" value="${name}" placeholder="성명을 입력해주세요." data-fail-message="성함은 필수 입력정보입니다." autocomplete="off" readonly >
							</td>
						</tr>
						<tr>
							<td>
								<h4>생 년 월 일 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-birth-input-pattern" name="MHC_BRTHDY" value="${birthDay}" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" readonly >
							</td>
						</tr>
						<!-- <tr>
							<th class="text-center">주 소 <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100px mr-5" id="RMNDR_MBR_ZIP" name="RMNDR_MBR_ZIP" placeholder="우편번호" readonly />
								<button type="button" onclick="zipcode('RMNDR_MBR_ZIP','RMNDR_MBR_ADDR1','RMNDR_MBR_ADDR2');" class="btn">우편번호 찾기</button> 
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR1" name="RMNDR_MBR_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly>
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR2" name="RMNDR_MBR_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" >
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
								<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>	
							</td>
						</tr> -->
						<tr>
							<td>
								<h4>연 락 처(휴대폰) <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-hp-input-pattern" name="MHC_HP" value="${phoneNo}" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" readonly >
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p class="dottedbox mt-30 mb-7 text-center"><b><주의사항></b></p>
			<p class="dottedbox mt-20 mb-7">① 정보 변경 전, 휴대폰 명의자의 이름으로 교육 이수를 반드시 완료하여야 합니다.</p>
			<p class="dottedbox mt-7 mb-7">② 정보 변경 시, 이전 정보(핸드폰 명의자)는 삭제되며 복구할 수 없습니다.</p>
			<p class="dottedbox mt-7 mb-7">③ 본인명의 핸드폰 미소지자의 경우 변경 후 로그인이 전면 제한되며, 이수증 직접 출력이 불가합니다.
			<p class="dottedbox mt-7 mb-7">④  한국어촌어항공단은 타명의 핸드폰 사용으로 발생하는 문제에 대해 어떠한 책임도 지지 않으며, 모든 책임은 이수자 본인에게 있습니다.</p>
			<div class="agree_yesorno dottedbox mt-20 text-center">
				<span class="mr-10">본인은 위 사항을 확인 하였으며, 이에 대해 </span>
				<input type="radio" name="approval" id="approvalY" value="Y" ><label class="mt-10" for="approvalY"><span></span>동의함</label>
				<input type="radio" name="approval" id="approvalN" value="N" ><label class="mt-10" for="approvalN"><span></span>동의하지 않음</label>
			</div>
			<div id="btnArea">
				<button type="button" class="btn_blue h60px w50 trg_btn_submit" id="btn_submit"><b>신청하기</b></button>
			</div>
		</section>
	</div>
	
</form:form>

<script>
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	<%--
		//처리 로직은 tail.jsp 파일에 존재함.
		//공통 : 사용 할 대상(input)의 속성(attr)에 필히 추가 data-pattern-cnt="0"
	   	//파라미터 : target 			= keyup 이벤트를 받을 input text 의 jquery selector
	   	//파라미터 : default_pattern 	= 기본 패턴 형태값,자리수
	   	//파라미터 : over_pattern		= 기본 패턴의 자리수 이상인 경우 다음 단계 패턴 형태 및 해당 패턴의 자리수를 기입하며 , 해당 자리수 이상인 경우 다음 단계 패턴으로 전환 
	   	//파라미터 : automatch			= 화면 로딩시 해당 폼(input)에 입력값이 존재 할 경우 패턴을 자동 적용 할 것인지에 대한 설정(true:패턴 형태를 적용하여 노출)
	--%>
	formatterCustomMultiple([
	    {
			'target' : $('.mbr-birth-input-pattern'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-hp-input-pattern'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : true,
		},
		
	]);
	
});

$("#btn_submit").click(function() {
	
	var form = document.getElementById('listForm');
		
	if(form.MHC_EDU_NM.value == '') {
		allPublicModalMessage($(form.MHC_EDU_NM).attr('data-fail-message'));
		$(form.MHC_EDU_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.MHC_EDU_HP.value == '') {
		allPublicModalMessage($(form.MHC_EDU_HP).attr('data-fail-message'));
		$(form.MHC_EDU_HP).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.MHC_EDU_BRTHDY.value.trim().length < 10) {
		allPublicModalMessage('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	if(form.MHC_NM.value == '') {
		allPublicModalMessage($(form.MHC_NM).attr('data-fail-message'));
		$(form.MHC_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.MHC_HP.value == '') {
		allPublicModalMessage($(form.MHC_HP).attr('data-fail-message'));
		$(form.MHC_HP).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.MHC_BRTHDY.value.trim().length < 10) {
		allPublicModalMessage('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	
	//공백제거
	form.MHC_EDU_NM.value = form.MHC_EDU_NM.value.trim();
	form.MHC_EDU_HP.value = form.MHC_EDU_HP.value.trim();
	form.MHC_NM.value = form.MHC_NM.value.trim();
	form.MHC_HP.value = form.MHC_HP.value.trim();
	if(!form.MHC_EDU_SHIP.value) {
		form.MHC_EDU_SHIP.value = form.MHC_EDU_SHIP.value.trim();
	}
	
	var islockal = false;
	var approval = document.getElementsByName('approval');
	var ynValue = "";
	for(var i = 0; i < approval.length; i++){
		if(approval[i].checked == true){
			ynValue = approval[i].value;
		}
	}
	if(ynValue == "N" || ynValue == "") {
		islockal = true;
	}
	
	if(islockal) {
		allPublicModalMessage('본인명의 휴대폰 미소지자 정보 변경에 동의해야 신청이 가능합니다.');
		return false;
	}
	
	$.ajax({
		type:"POST",
		url :"/educenter/rmndr/hpreqstdoc_act.do",
		data:$('#listForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);			
			if(data.error == '1') {
				allPublicModalMessage(data.msg);
			} else {
				alert('본인명의 휴대폰 정보 변경 신청이 완료되었습니다.');
				location.href = '/educenter/index.do';	
			}
		
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
			clickRequestLockStop();
		}
	});
});

</script>


<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
