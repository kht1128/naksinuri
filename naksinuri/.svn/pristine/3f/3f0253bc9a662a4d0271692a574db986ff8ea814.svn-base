<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>

<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>

<form:form commandName="eduCenterMainVO" id="listForm" name="listForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
	<input type="hidden" id="agree" name="agree" value="1"/>
	<input type="hidden" id="isDelete" name="isDelete" value="${isDelete}"/>
</form:form>

<div class="content respon3">
	<section id="writeForm" class="write_box">
		<h3 class="font-weight-bold">교육 서비스 제공을 위한 개인정보 수집·이용 동의서</h3>
		<div class="agree_box">
			<p class="dottedbox">한국어촌어항공단이 해양수산부로부터 위탁받아 운영 중인 낚시전문교육은 서비스 제공 및 민원사무 처리를 위한 개인정보 수집·이용을 위하여 『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다.</p>
			<table class="basic_tbl mt-10">
				<caption>개인정보 수집·이용</caption>
				<colgroup>
					<col />
					<col width="80px"/>
					<col />
				</colgroup>
				<thead>
					<tr class="table-cell-blind"><th></th></tr>
				</thead>
				<tbody>
					<tr>
						<td>개인정보의 수집 및 이용 목적</td>
						<td colspan="2" class=" text-underline font-weight-bold ">낚시누리 서비스 제공(필수), 낚시전문교육 민원사무 처리(필수), 교육 및 관련 정책 안내(필수), 정책 수립 위한 통계 활용(선택)</td>
					</tr>
					<tr>
						<td rowspan="2">수집하는 개인정보 항목</td>
						<td class="text-center">필수</td>
						<td class="text-underline font-weight-bold">수강인구분, 성명, 생년월일, 주소, 휴대전화번호, 허가(등록)·신고 시·군·구명</td>
					</tr>
					<tr>
						<td class="text-center">선택</td>
						<td class="text-underline font-weight-bold">전화번호, 낚시터(낚시어선) 명칭, 허가(등록)·신고증 번호, 어선번호(낚시어선에 한함)</td>
					</tr>
					<tr>
						<td>개인정보의 보유 및 이용 기간</td>
						<td class="" colspan="2"><span class="font-size-18 blue-600 text-underline font-weight-bold">2년</span>(보유기간 경과 및 보유목적 달성 시 지체 없이 파기합니다)</td>
					</tr>
					<tr>
						<td>동의 거부 권리 및 동의 거부에 따른 불이익 내용 또는 제한사항</td>
						<td colspan="2" class="">귀하는 위와 같은 개인정보 수집 및 이용에 동의를 거부할 권리가 있습니다. 필수 항목에 대한 동의 거부 시 교육 접수 및 교육정보 제공이 제한되며, 선택항목 제공에 대한 동의 거부시 교육접수 및 교육정보 제공은 가능하나 교육 대상자 확인에 어려움이 있을 수 있음을 알려드립니다.</td>
					</tr>
				</tbody>
			</table>
			<!-- <div class="agree_text mgt10px"></div> -->
			<ul class="floats">
				<li class="fr">
					<div class="agree_yesorno mt-10">
						<span class="mr-10">(필수) 개인정보 수집 및 이용에</span><br><br>
						<input type="radio" name="approval1" id="approval1Y" value="Y" ><label for="approval1Y"><span></span>동의함</label>
						<input type="radio" name="approval1" id="approval1N" value="N" ><label for="approval1N"><span></span>동의하지 않음</label>
					</div>
					<div class="agree_yesorno mt-10">
						<span class="mr-10">(선택) 개인정보 수집 및 이용에</span><br><br>
						<input type="radio" name="approval2" id="approval2Y" value="Y" ><label for="approval2Y"><span></span>동의함</label>
						<input type="radio" name="approval2" id="approval2N" value="N" ><label for="approval2N"><span></span>동의하지 않음</label>
					</div>
				</li>	
			</ul>
		</div>
		<div id="btnArea">
			<button type="button" class="btn_request btn_blue h60px w50 clk_btn_act"><b>다음단계</b></button>
		</div>
	</section>
</div>

<script>
var isDelete = $("#isDelete").val();
if(isDelete == 1){
	alert("이미 수강중 이거나 신청 된 교육과정입니다.\n기존 이력을 삭제 후 새로 신청됩니다.");
}
$(".clk_btn_act").click(function() {
	var approval1 = $(':radio[name="approval1"]:checked').val();
	var approval2 = $(':radio[name="approval2"]:checked').val();
	var islockall = false;
	var islockapproval1 = false;
	var islockapproval2 = false;
	if ((typeof approval1 == "undefined") && (typeof approval2 == "undefined")) {
		islockall = true;
	} else if (typeof approval1 == "undefined") {
		islockapproval1 = true;
	} else {
		if(approval1 == 'N') {
			islockapproval1 = true;
		} else {
			if(typeof approval2 == "undefined") {
				islockapproval2 = true;
			}
		}
	}
	if(islockall) {
		alert('필수 및 선택 개인정보 수집·이용에 동의해야 합니다.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval1) {
		alert('(필수)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval2) {
		alert('(선택)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval2Y').focus();
		return false;
	}
	var agree = '';
	if(approval1=='Y' && approval2=='Y') {
		agree = '1';
	} else if(approval1=='Y' && approval2=='N') {
		agree = '2';
	}
	var form = document.getElementById('listForm');
	form.action = "/educenter/m/trnng/write.do";
	form.agree.value = agree;
	form.submit();
});
</script>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
