<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>

<style>
	.write_box .line-hi3 {line-height: 3.5rem; height: auto;}
	.d-inline-block {display: inline-block;}
	.w50 {width: calc((100% - 5px)/2);}
	.blue {color: #394DC0;}
	.text-c {text-align: center !important;}
	.agreeBtn {width: 100%; font-family: 'NanumSquare';}
	.content .text-title {text-align: center; line-height: initial; font-size: 18px; height: 70px;}
</style>

<form:form commandName="eduCprBplcVO" id="infoForm" name="infoForm" method="post">
	<input type="hidden" id="ECB_INDVDL_INFO_ST" name="ECB_INDVDL_INFO_ST" value=""/>
	<input type="hidden" id="ECB_DTL_CD" name="ECB_DTL_CD" value=""/>
</form:form>

<div class="content respon3">
	<section id="shortcutForm" class="w100">
		<div class="w50 d-inline-block back write_box">
			<h3 class="line-hi3 text-c font-weight-bold"><b class="blue">낚시터</b> 법인사업장<br>교육책임자 지정</h3>
			<button class="btn btn-outline btn-default agreeBtn" onclick="shortcut('CIDN010200');">개인정보 수집·이용<br>동의 바로가기 ></button>
		</div>
		<div class="w50 d-inline-block write_box">
			<h3 class="line-hi3 text-c font-weight-bold"><b class="blue">낚시어선</b> 법인사업장<br>교육책임자 지정</h3>
			<button class="btn btn-outline btn-default agreeBtn" onclick="shortcut('CIDN010300');">개인정보 수집·이용<br>동의 바로가기 ></button>
		</div>
	</section>
	<section id="writeForm" class="write_box hidden">
		<h3 class="line-hi3 font-weight-bold text-title" id="title">교육 서비스 제공을 위한 개인정보 수집·이용 동의서 (법인대표자, 교육책임자)</h3>
		<div class="agree_box">
			<p class="dottedbox">한국어촌어항공단이 해양수산부로부터 위탁받아 운영 중인 낚시누리(www.naksinuri.kr)는 서비스 제공 및 민원사무 처리를 위한 개인정보 수집·이용을 위하여<br>『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다.</p>
			<table class="basic_tbl mt-10">
				<caption>개인정보 수집·이용</caption>
				<colgroup>
					<col class="w30"/>
					<col />
				</colgroup>
				<thead>
					<tr class="table-cell-blind"><th></th></tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center">개인정보의 수집 및 이용 목적</td>
						<td class=" text-underline font-weight-bold ">낚시누리 서비스 제공(필수), 낚시전문교육 민원사무 처리(필수), 교육 및 관련 정책 안내(필수), 정책 수립 위한 통계 활용(선택)</td>
					</tr>
					<tr>
						<td class="text-center">수집하는 개인정보 항목<br>필수</td>
						<td class="text-underline font-weight-bold">수강인구분, 성명, 생년월일, 주소, 휴대전화번호, 허가(등록)·신고 시·군·구명</td>
					</tr>
					<tr>
						<td class="text-center">수집하는 개인정보 항목<br>선택</td>
						<td class="text-underline font-weight-bold">전화번호, 낚시터(낚시어선) 명칭, 허가(등록)·신고증 번호, 어선번호(낚시어선에 한함)</td>
					</tr>
					<tr>
						<td class="text-center">개인정보의 보유 및 이용 기간</td>
						<td class="" ><span class="font-size-18 blue-600 text-underline font-weight-bold">2년</span>(보유기간 경과 및 보유목적 달성 시 지체 없이 파기합니다)</td>
					</tr>
					<tr>
						<td class="text-center">동의 거부 권리 및 동의 거부에 따른 불이익 내용 또는 제한사항</td>
						<td class="">귀하는 위와 같은 개인정보 수집 및 이용에 동의를 거부할 권리가 있습니다. 필수 항목에 대한 동의 거부 시 교육 접수 및 교육정보 제공이 제한되며, 선택항목 제공에 대한 동의 거부시 교육접수 및 교육정보 제공은 가능하나 교육 대상자 확인에 어려움이 있을 수 있음을 알려드립니다.</td>
					</tr>
				</tbody>
			</table>
			<!-- <div class="agree_text mgt10px"></div> -->
			<ul class="">
				<li class="">
					<div class="agree_yesorno mt-10 text-center line-hi3">
						<span class="mr-10">(필수) 개인정보 수집 및 이용에</span><br>
						<input type="radio" name="approval1" id="approval1Y" value="Y" ><label for="approval1Y"><span></span>동의함</label>
						<input type="radio" name="approval1" id="approval1N" value="N" ><label for="approval1N"><span></span>동의하지 않음</label>
					</div>
					<div class="agree_yesorno mt-10 text-center line-hi3">
						<span class="mr-10">(선택) 개인정보 수집 및 이용에</span><br>
						<input type="radio" name="approval2" id="approval2Y" value="Y" ><label for="approval2Y"><span></span>동의함</label>
						<input type="radio" name="approval2" id="approval2N" value="N" ><label for="approval2N"><span></span>동의하지 않음</label>
					</div>
				</li>	
			</ul>
		</div>
		<div id="btnArea">
			<button type="button" class="btn_blue h60px w50 clk_btn_act"><b>다음단계</b></button>
		</div>
	</section>
</div>

<script>
function shortcut(ECB_DTL_CD){
	var shortcutForm = document.getElementById('shortcutForm');
	var writeForm = document.getElementById('writeForm');
	shortcutForm.classList.add('hidden');
	writeForm.classList.remove('hidden');
	
	if(ECB_DTL_CD == "CIDN010200") {
		document.getElementById('title').innerHTML = "낚시터 교육 서비스 제공을 위한<br>개인정보 수집·이용 동의서<br>(법인대표자, 교육책임자)";
	} else {
		document.getElementById('title').innerHTML = "낚시어선 교육 서비스 제공을 위한<br>개인정보 수집·이용 동의서<br>(법인대표자, 교육책임자)";
	}
	
	var form = document.getElementById('infoForm');
	form.ECB_DTL_CD.value = ECB_DTL_CD;
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
		allPublicModalMessage('필수 및 선택 개인정보 수집·이용에 동의해야 합니다.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval1) {
		allPublicModalMessage('(필수)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval2) {
		allPublicModalMessage('(선택)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval2Y').focus();
		return false;
	}
	var agree = '';
	if(approval1=='Y' && approval2=='Y') {
		agree = '1';
	} else if(approval1=='Y' && approval2=='N') {
		agree = '2';
	}
	
	var ECB_INDVDL_INFO_ST = approval2;
	
	var form = document.getElementById('infoForm');
	form.action = "/educenter/m/rmndr/cpr_bplc.do";
	form.ECB_INDVDL_INFO_ST.value = ECB_INDVDL_INFO_ST;
	form.submit();
});
</script>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
