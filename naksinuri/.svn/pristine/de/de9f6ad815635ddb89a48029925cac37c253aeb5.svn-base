<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
	@media (min-width: 480px){
		.modal-dialog {max-width: 1080px; margin: 65px auto;}
	}
	.basic_tbl { border-spacing: 0; border-collapse: collapse;}
	.basic_tbl td { border: 1px solid #e4eaec; padding: 0.572rem;}
	.agree_box {padding: 0 40px 40px 40px;}
	.agree_box ul {list-style: none; padding: 0;}

	.agree_yesorno input[type="radio"]{display:none;}
	.agree_yesorno input[type="radio"] + label{font-weight:normal;}
	.agree_yesorno input[type="radio"] + label span{
		display:inline-block;width:24px;height:24px;margin:-3px 5px 0 0;vertical-align:middle;
		background:url("/naksinuri_original/common_main/img/ico_radio.png") left top no-repeat;
		cursor:pointer;
	}
	.agree_yesorno input[type="radio"]:checked + label span{background:url("/naksinuri_original/common_main/img/ico_radio.png") -24px top no-repeat;}
	.modal-header {padding: 20px 50px 0px 20px;}
</style>


<div class="modal-dialog" role="document">
	<div class="modal-content form-horizontal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	 			<span aria-hidden="true">×</span>
			</button>
		<!-- 	<h4 class="modal-title">이용정보동의서</h4> -->
		</div>
	<c:if test="${empty info}">
		<p class="help-text font-weight-800 font-size-16 red-600 text-center">해당 아이디 <c:out value="${id}"/> 는 삭제된 회원 입니다.</p>
	</c:if>
	<c:if test="${not empty info}">
	<div class="agree_box">
		<h2 class="text-center">교육 서비스 제공을 위한 개인정보 수집·이용 동의서</h2>
		<p class="dottedbox">한국어촌어항공단이 해양수산부로부터 위탁받아 운영 중인 낚시누리(www.naksinuri.kr)는 서비스 제공 및 민원사무 처리를 위한 개인정보 수집·이용을 위하여 『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다.</p>
		<table class="basic_tbl mt-10">
			<colgroup>
				<col width="200"/>
				<col width="80px"/>
				<col />
			</colgroup>
			<thead>
				<tr class="table-cell-blind"><th></th></tr>
			</thead>
			<tbody>
				<tr>
					<td>개인정보의 수집 및 이용 목적</td>
					<td colspan="2" class=" text-underline font-weight-bold ">낚시누리 서비스 제공(필수), 낚시전문교육 민원사무 처리(필수),<br>교육 및 관련 정책 안내(필수), 정책 수립 위한 통계 활용(선택)</td>
				</tr>
				<tr>
					<td rowspan="2">수집하는 개인정보 항목</td>
					<td class="text-center">필수</td>
					<td class="text-underline font-weight-bold">전화번호, 낚시터(낚시어선) 명칭, 허가(등록)·신고증 번호, 어선번호(낚시어선에 한함)</td>
				</tr>
				<tr>
					<td class="text-center">선택</td>
					<td class="text-underline font-weight-bold">일반전화번호, 낚시터(낚시어선) 명칭, 허가(등록) 신고증 번호, 어선번호(낚시어선에 한함), 유효기간</td>
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
			<fmt:parseDate var="parseregdatestring" value="${info.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
  			<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
			<ul class="floats float-right">
				<li class="fr">
					<div class="agree_yesorno mt-30">
						<span class="mr-10">(필수) 개인정보 수집 및 이용에</span>
						<input type="radio" name="approval1" id="approval1Y" value="Y" checked><label for="approval1Y"><span></span>동의함</label>
						<input type="radio" name="approval1" id="approval1N" value="N" onclick="return(false);"><label for="approval1N"><span></span>동의하지 않음</label>
						<span class="red-600">(${regdatestring})</span>
					</div>
					<div class="agree_yesorno mt-10">
						<span class="mr-10">(선택) 개인정보 수집 및 이용에</span>
						<c:choose>
							<c:when test="${info.INDVDL_INFO_ST eq 'Y'}">
								<input type="radio" name="approval2" id="approval2Y" value="Y" checked><label for="approval2Y"><span></span>동의함</label>
								<input type="radio" name="approval2" id="approval2N" value="N" onclick="return(false);"><label for="approval2N"><span></span>동의하지 않음</label>
								<span class="red-600"><c:out value="(${regdatestring})"/></span>
							</c:when>
							<c:when test="${info.INDVDL_INFO_ST eq 'N'}">
								<input type="radio" name="approval2" id="approval2Y" value="Y" onclick="return(false);"><label for="approval2Y"><span></span>동의함</label>
								<input type="radio" name="approval2" id="approval2N" value="N" checked><label for="approval2N"><span></span>동의하지 않음</label>
								<span class="red-600"><c:out value="(${regdatestring})"/></span>
							</c:when>
							<c:otherwise>
								<input type="radio" name="approval2" id="approval2Y" value="Y" onclick="return(false);"><label for="approval2Y"><span></span>동의함</label>
								<input type="radio" name="approval2" id="approval2N" value="N" onclick="return(false);"><label for="approval2N"><span></span>동의하지 않음</label>
								<span class="red-600">(정보없음)</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>	
			</ul>
		</div>
	</c:if>
</div>
</div>