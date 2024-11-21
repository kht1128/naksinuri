<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/head.jsp"%>

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
	.bg-emphasis {background-color:#f9f2f4; padding: 25px; border-radius: 4px;}
	.emphasis {color: #B75552;font-size: 1.7rem;}
	.emphasis2 {color: #fff;font-size: 1.8rem;background-color: #C00000;line-height: 4.3rem;padding: 6px;}
	.line-h18 {line-height: 1.8rem;}
	.emphasis3 {color: #fff; background-color: #000;display: inline-block;padding: 3px 5px; font-weight: bold;}
	#writeForm ul li {list-style: disc;}
	.light-red {color: #B75552;}
</style>

<script>
function sel_crs_data(obj) {
	
	if(obj.value == '') {
		$('#target_crs_data').html('<tr><td colspan="2" class="text-center">선택이 불가능한 교육정보 입니다.</td></tr>');	
		return;
	}
	
	var form = document.getElementById('dataCRSForm');
	form.CRS_SN.value = obj.value;
	form.action = '';
	
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/view.do",
		data:$('#dataCRSForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$('#target_crs_data').html(data);			
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
			$('#target_crs_data').html('<tr><td colspan="2" class="text-center">교육정보를 불러오는CRS_MBR_CD 중입니다..</td></tr>');	
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});	
}
</script>

<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>
<c:set var="lastday" value="<%=lastDay%>"/>

<c:if test="${empty CRS_STR_DT}">
	<c:set var="CRS_STR_DT" value="${year}-${month}-01"/>
</c:if>
<c:if test="${empty CRS_END_DT}">
	<c:set var="CRS_END_DT" value="${year}-${month}-${lastday}"/>
</c:if>

<form:form commandName="CodeSetVO" id="modalMemberWriteFormSido" name="modalMemberWriteFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="eduCenterMainVO" id="dataCRSForm" name="dataCRSForm" method="post">
<input type="hidden" name="CRS_SN" value=""/>
<input type="hidden" name="IS_TABLE_TR" value="Y"/>
</form:form>

<form:form commandName="eduCenterMainVO" id="searchCRSForm" name="searchCRSForm" method="post">
<input type="hidden" name="start" value="${CRS_STR_DT}"/>
<input type="hidden" name="end" value="${CRS_END_DT}"/>
<input type="hidden" name="CRS_STR_DT" value="${CRS_STR_DT}"/>
<input type="hidden" name="CRS_END_DT" value="${CRS_END_DT}"/>
<input type="hidden" name="CRS_GRP_CD" value="${CRS_GRP_CD}"/>
<input type="hidden" name="CRS_MBR_CD" value="${CRS_MBR_CD}"/>
<input type="hidden" name="LOCK_ST" value="0"/>
<input type="hidden" name="CRS_LAW_TYPE" value="default" />
<!-- <input type="hidden" name="TYPE_GB" value="online"/> -->
</form:form>		

<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post" >	
	<input type="hidden" name="RMNDR_DTL_CD" value="${CRS_MBR_CD }"/>
	<input type="hidden" name="INDVDL_INFO_ST" value="${INDVDL_INFO_ST}"/>	
	<input type="hidden" name="page_type" value="" />	
	<div class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>교육과정선택</h2>
			<p class="dottedbox"><b>수강신청문의</b> (전화) 1833-7139 | (팩스) 0505-742-1004 | (이메일) naksinuri@fipa.or.kr</p>
			<div class="list_searchbox mt-30">
				<span class="ml-5 mr-5 basic_input text-middle">교육일정</span> 
				<input type="text" id="CRS_STR_DT" class="frm_input basic_input wp15 text-center" value="${CRS_STR_DT}" autocomplete="off" placeholder="예)1990-01-01" title="교육일정 시작일 예)YYYY-MM-DD"/>
				<span class="ml-5 mr-5 basic_input text-middle">부터 ~</span> 
				<input type="text" id="CRS_END_DT" class="frm_input basic_input wp15 text-center" value="${CRS_END_DT}" autocomplete="off" placeholder="예)1990-01-01" title="교육일정 종료일 예)YYYY-MM-DD"/>
				<span class="ml-5 mr-5 basic_input text-middle">까지</span> 
				<select class="basic_select wp30" title="지역조건 선택옵션(온라인교육,서울인천경기지역,제주지역,강원지역,부산경남지역,대구울산경북지역,광주전남지역,대전충북충남세종지역,전북지역,전국)" id="CRS_GRP_CD"/>
					<option value="${item.CD_ID}">지역조건 전체보기</option>
					<c:forEach var="item" items="${list_edu_grp_cd}">
						<option value="${item.CD_ID}" <c:if test="${CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
					</c:forEach>
				</select>
				<button class="searchBtn btn_search mr-0" id="searchBtn"><span class="blind">교육일정 검색</span><i class="fa fa-search" aria-hidden="true"></i></button>				
			</div>
			<div class="list_searchbox mt-30 mb-5">
				<select class="basic_select wp100" title="교육과정 선택옵션" style="border:2px solid #000;" name="RMNDR_CRS_SN" onchange="sel_crs_data(this)" id="target_sel_crs_data" data-fail-message="신청하고자 하는 교육과정을 선택해주세요." required/>
					<option value="">교육과정선택하기 (위에 조회 버튼으로 교육과정을 검색해주세요)</option>
				</select>
			</div>
			<div class="agree_box">
				<table class="basic_tbl mt-10">
					<caption>교육정보</caption>
					<colgroup>
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody id="target_crs_data">		
					</tbody>
				</table>
				<p class="red-600">* 교육과정은 필수 선택입니다.</p>
			</div>
		</section>
		<section id="writeForm" class="write_box mt-30">
			<h2>수강신청서</h2>
			<p class="dottedbox red-600 font-weight-bold mb-30">이름,생년월일,연락처는 로그인(본인인증)시 본인여부 확인에 사용되므로 불일치시 로그인이 불가능 할 수 있으므로 주의하여 입력해주시기 바랍니다.</p>
			<div class="agree_box">
				<h3 class="">기본회원정보</h3>
				<table class="basic_tbl mt-10">
					<caption>수강신청서 양식</caption>
					<colgroup>
						<col width="140" />
						<col />
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>		
						<tr>
							<th class="text-center">성 명 <span class="red-600">*</span></th>
							<td>
								<input type="text" class="basic_input w100 readonly" placeholder="성함을 입력해주세요." name="RMNDR_MBR_NM_CHK"  value="${MBR_NM }" data-fail-message="성함은 필수 입력정보입니다." autocomplete="off" required disabled title="성명">
								<input type="hidden" name="RMNDR_MBR_NM" value="${MBR_NM }" readonly/>
							</td>
							<th class="text-center">생년월일 <span class="red-600">*</span></th>
							<td>
								<input type="text" class="basic_input w100 mbr-birth-input-pattern readonly" name="RMNDR_MBR_BIRTH_CHK" value="${MBR_BIRTH }" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" required disabled title="생년월일 예)19190301">
								<input type="hidden" name="RMNDR_MBR_BIRTH" value="${MBR_BIRTH }" readonly/>
							</td>
						</tr>
						<tr>
							<th class="text-center">주 소 <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100px mr-5" id="RMNDR_MBR_ZIP" name="RMNDR_MBR_ZIP" placeholder="우편번호" readonly title="우편번호"/>
								<button type="button" onclick="zipcode('RMNDR_MBR_ZIP','RMNDR_MBR_ADDR1','RMNDR_MBR_ADDR2');" class="btn" title="우편번호 찾기">우편번호 찾기</button> 
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR1" name="RMNDR_MBR_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly title="주소">
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR2" name="RMNDR_MBR_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" title="상세주소">
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
								<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>	
							</td>
						</tr>
						<tr>
							<th class="text-center">연 락 처(휴대폰) <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100 mbr-hp-input-pattern readonly" name="RMNDR_MBR_HP_CHK" value="${MBR_HP }" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" required disabled title="휴대전화 (-없이 입력)">
								<input type="hidden" name="RMNDR_MBR_HP" value="${MBR_HP }" readonly/>
							</td>
						</tr>
						<tr>
							<th class="text-center">연 락 처(일반전화)</th>
							<td colspan="3">
								<input type="text" class="basic_input w100 mbr-tel-input-pattern" name="RMNDR_MBR_TEL" value="" placeholder="예)0260980812" data-fail-message="일반전화번호를 입력하세요." autocomplete="off" title="일반전화(-없이입력)">
							</td>
						</tr>						
					</tbody>
				</table>
				
				<h3 class="mt-30">추가상세정보</h3>
				<table class="basic_tbl mt-10" id="dtl_table">
					<colgroup>
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>
						<tr>
							<th>수강인구분 <span class="red-600">*</span></th>
							<td>
								<select class="basic_input" title="수강인구분선택옵션(개인업자,법인업자,해기사면허 소지 선장,해기사면허 소지 선원,해기사면허 미소지 선원)" id="RMNDR_DTL_LICENSE_CD" name="RMNDR_DTL_LICENSE_CD" data-fail-message="수강인구분은 필수 선택정보입니다." <%-- onchange="chageSelect();" --%> required>  
									<option value="">수강인구분선택</option> 
									<c:forEach var="item" items="${list_license_se_cd}"> 
										<%-- <c:if test="${item.CD_ID ne 'CIDL00001' and item.CD_ID ne 'CIDL00002'}"> --%>
											<option value="${item.CD_ID}">${item.CD_NM}</option>
										<%-- </c:if> --%>
									</c:forEach> 
								</select>
								<select class="basic_input" title="교육수강 구분선택" id="MBR_FSHRBT_TYPE" name="MBR_FSHRBT_TYPE" data-fail-message="교육수강 구분선택은 필수 선택정보입니다." onchange="changeSelect()">
									<option value="">교육수강 구분</option>
									<option value="legacy">기존</option>			
									<option value="new">신규</option>			
									<option value="resmpt">재개자</option>	
								</select>
								<select class="basic_input" title="해기사면허유무선택옵션(유,무)" name="RMNDR_SHIP_LICENSE" data-fail-message="해기사면허 선택은 필수 선택정보입니다." required>		
									<option value="">해기사면허</option>			
									<option value="유">유</option>			
									<option value="무">무</option>		
								</select>
								<br><span class="red-600 font-weight-bold">선주 겸 선장은 개인업자로 선택해주시기 바랍니다.</span>
								<div class="hide" id="alert_text">
									<h3 class="mt-10"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;"></i> 알림사항</h3>
									<div class="mt-10 bg-emphasis">
										<p class="font-weight-bold emphasis">귀하는 「낚시어선 전문교육」 대상이 아닙니다. 「신규·재개자 전문교육」을 수강하여 주시기 바랍니다.<br>
										20.2.21. 이후 최초 신고(신규) 및 영업정지명령을 받은 후 재개하려는 낚시어선업자(재개자)의 경우 </p>
										<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계없이 「신규·재개자 전문교육(현장교육, 연간 21시간 이상)」을 수강하여야 합니다.</span>
										<p>- 온라인교육을 신청하시는 경우 기재하신 휴대전화 번호로 신규·재개자 전문교육이 안내됩니다.<br>
										- 온라인교육 수강을 원치 않으실 경우 교육 안내를 위하여 ☎1833-7139로 연락주시기 바랍니다.</p>
										<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 기존 선택 후 교육을 신청하여 주시기 바랍니다.</p>
									</div>
								</div>
							</td>
						</tr>
						
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<th>낚시어선명칭</th>
							<td><input type="text" class="basic_input w100 " name="RMNDR_DTL_NM" value="" placeholder="낚시어선 명칭을 입력하세요." data-fail-message="" autocomplete="off" title="낚시어선명칭"></td>
						</tr>
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<th>신고확인증번호</th>
							<td>
								<input type="text" class="basic_input wp100 " name="RMNDR_REG_NUM_CD" value="" placeholder="낚시어선 신고확인증번호를 입력하세요." data-fail-message="" autocomplete="off" title="신고확인증번호">
								<%--
								(&nbsp;유효기간&nbsp;:&nbsp;
								<input type="text" id="RMNDR_SHIP_LICENSE_STR_DT" class="frm_input basic_input wp15 text-center" value="" autocomplete="off" readonly/>
								&nbsp;~&nbsp;<input type="text" id="RMNDR_SHIP_LICENSE_END_DT" class="frm_input basic_input wp15 text-center" value="" autocomplete="off" readonly/>&nbsp;)
								--%>
							</td>
						</tr>
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<th>어선번호</th>
							<td><input type="text" class="basic_input w100 ship-cd-input-pattern" name="RMNDR_SHIP_CD" value="" placeholder="어선번호를 입력하세요." data-fail-message="" autocomplete="off" title="어선번호"></td>
						</tr>
						
						<tr>
							<th>낚시어선 신고지 <span class="red-600">*</span></th>
							<td>
								<select class="basic_input sel_area_sido wp30" title="낚시어선 신고지 시도 선택옵션(서울특별시,부산광역시,대구광역시,인천광역시,광주광역시,대전광역시,울산광역시,경기도,강원도,충청북도,충청남도,전라북도,전라남도,경상북도,경상남도,제주특별자치도,세종특별자치시)" name="RMNDR_SIDO_CD" data-fail-message="낚시어선 신고지(시도)는 필수 선택정보입니다." required>  
									<option value="">시도 선택</option> 
									<c:forEach var="item" items="${list_address_cd}">
										<option value="${item.CD_ID}">${item.CD_NM}</option>
									</c:forEach>
								</select>
								<select class="basic_input sel_area_signgu wp30" name="RMNDR_SIGNGU_CD" data-fail-message="낚시어선 신고지(시군구)는 필수 선택정보입니다." required title="시군구 선택옵션">   
									<option value="" >시군구선택</option>
								</select>
								<input type="text" class="basic_input wp30" name="RMNDR_YMD_NM" value="" placeholder="읍면동을 입력하세요." data-fail-message="" autocomplete="off" title="읍,면,동">
							</td>
						</tr>
					</tbody>
				</table>
			
				<div class="hide" id="smsCheck">
					<h3 class="mt-30"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;" title="알림사항"></i> 알림사항</h3>
					<div class="mt-10 bg-emphasis">
						<p class="font-weight-bold emphasis">
							귀하는 「낚시어선 전문교육」 대상이 아닙니다. 「신규·재개자 전문교육」을 수강하여 주시기 바랍니다.<br>
							20.2.21. 이후 최초 신고(신규) 및 영업정지명령을 받은 후 재개하려는 낚시어선업자(재개자)의 경우
						</p>
						<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계없이 「신규·재개자 전문교육(현장교육, 연간 21시간 이상)」을 수강하여야 합니다.</span>
						<p>- 온라인교육을 신청하시는 경우 기재하신 휴대전화 번호로 신규·재개자 전문교육이 안내됩니다.<br>
						   - 온라인교육 수강을 원치 않으실 경우 교육 안내를 위하여 ☎1833-7139로 연락주시기 바랍니다.
						</p>
						<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 기존 선택 후 교육을 신청하여 주시기 바랍니다.</p>
					</div>
					<%-- <table class="basic_tbl mt-10">
					<caption>알림사항</caption>
					<colgroup>
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>	
						<tr>
							<th>교육 알림 신청</th>
							<td>
								<input type="radio" name="REQ_YN" id="sendSmsN" value="N" ><label class="mt-7" for="sendSmsN"><span></span>기존 낚시어선업 신고자 ( 기간만료 후 재신고자 포함 )</label><br>
								<input type="radio" name="REQ_YN" id="sendSmsY" value="Y" >
								<label class="mt-7" for="sendSmsY"><span></span>20.2.21 이후 <p class="emphasis3">최초</p>로 낚시어선업을 신고한 자</label><br>
								<input type="radio" name="REQ_YN" id="sendSmsR" value="R">
								<label class="mt-7" for="sendSmsR"><span></span>20.2.21 이후 낚시 관리 및 육성법 제38조제1항제5호에 따라 영업정지명령을 받은 후 재개하려는 자</label>
							</td>						
						</tr>
					</tbody>
				
				<%--
				<div class="<c:choose><c:when test="${not empty IS_14AGE_UNDER and IS_14AGE_UNDER eq 'Y' }"></c:when><c:otherwise>d-none</c:otherwise></c:choose>" id="age14UnderParentCheck">
					<h3 class="mt-30">14세 미만 가입자 보호자 동의 <span class="red-600">*</span></h3>
					<table class="basic_tbl mt-10">
						<caption>14세 미만 가입자 보호자 동의</caption>
						<colgroup>
							<col width="140" />
							<col />
						</colgroup>
						<thead><tr class="table-cell-blind"><th></th></tr></thead>
						<tbody>
							<tr>
								<th>보호자 인증</th>
								<td>
									<input type="button" class="btn h60px w50 mb-10" value="휴대폰인증" id="age14UnderParentCheckBtn" onclick="kcbOkCertAct()"/>
									<p class="red-600" id="age14UnderParentCheckLabel"></p>
								</td>						
							</tr>
						</tbody>
					</table>
				</div>
				--%>
				<!-- <p class="dottedbox"></p> -->
				<!-- <div class="agree_text mgt10px"></div> -->
			</div>
			<div class="mt-10" id="more_msg"></div>
			<div id="text_submit">
				<p class="dottedbox red-600  mt-30 mb-5 text-center">신청서 제출 시 관리자 검토 후 승인이 완료되면 <b>문자로 안내됩니다.</b></p>
				<p class="dottedbox mt-5 mb-5 text-center">(신청서는 순차적으로 검토되어 승인 완료까지 다소 시간이 걸릴 수 있으니 양해 부탁드리며,<br>신청 후 익일 정오 12시까지 안내 문자 미수신시 1833-7139로 신청 확인 부탁드립니다.)</p>
				<%--<p class="dottedbox green-800 font-weight-bold mt-5 mb-5 text-center">회원인 경우에는 로그인 후 교육신청 페이지에서 신청을 하시면 관리자 검토없이 수강신청이 가능합니다.</p> --%>
				<p class="dottedbox mt-20  text-center">『낚시전문교육 및 교육기관 지정에 관한 고시』 제8조에 따라 위와 같이 신청합니다.</p>
			</div>	
			<div id="btnArea">
				<button type="button" class="btn_blue h60px w50 trg_btn_submit" id="btn_submit" title="신청하기"><b>신청하기</b></button>
			</div>
		</section>
	</div>
	
</form:form>

<script>
//셀렉트 박스 변경시 교육 수강 구분 hide
$('#RMNDR_DTL_LICENSE_CD').change(function(){
	var result = $('#RMNDR_DTL_LICENSE_CD option:selected').val();
	if(result == 'CIDL00001' || result == 'CIDL00002'){
		$('#MBR_FSHRBT_TYPE').show();
		$('#MBR_FSHRBT_TYPE').attr("required", false);
		changeSelect();
	} else {
		$('#MBR_FSHRBT_TYPE').hide();
		$('#MBR_FSHRBT_TYPE').attr("required", true);
		$('#MBR_FSHRBT_TYPE').val("");
		changeSelect();		
	}
});

$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	$("#CRS_STR_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	$("#CRS_END_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	//$("#RMNDR_SHIP_LICENSE_STR_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	//$("#RMNDR_SHIP_LICENSE_END_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	/*
	$('.mbr-birth-input-pattern').formatter({
		'pattern': '{{9999}}-{{99}}-{{99}}',
		'persistent': false
	});
	$('.mbr-hp-input-pattern').formatter({
		'pattern': '{{999}}-{{9999}}-{{9999}}',
		'persistent': false
	});
	$('.mbr-tel-input-pattern').formatter({
		'pattern': '{{999}}-{{999}}-{{9999}}',
		'persistent': false
	});
	$('.ship-cd-input-pattern').formatter({
	  	'pattern': '{{9999999}}-{{9999999}}',
	  	'persistent': false
	});
	*/
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
		{
			'target' : $('.mbr-tel-input-pattern'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : true,
		},		
		{
			'target' : $('.ship-cd-input-pattern'),
			'default_pattern' : ['{{9999999}}-{{9999999}}',14],
			'over_pattern' : null,
			'automatch' : true,
		},
	]);
	$('#searchBtn').click();
});
$(document).on("change",".sel_area_sido",function() {
	var target = $(this).parent().find(".sel_area_signgu");
	var val = $(this).val();
	var form = document.getElementById('modalMemberWriteFormSido');
	form.CD_MASTER_ID.value = val;
	form.action = '';	
	if(val=='') {
		target.html('<option value="">시군구선택</option>');
	} else {
		$.ajax({
			type:"POST",
			url :"/all/code.do",
			data:$('#modalMemberWriteFormSido').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.error == '1') {
					alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '<option value="">시군구선택</option>';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
					}
					target.html(htmlString);
				}
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	}
});  
$(".btn_search").click(function(e) {
	e.preventDefault();
	
	var form = document.getElementById('searchCRSForm');
	form.start.value = form.CRS_STR_DT.value = $('#CRS_STR_DT').val();
	form.end.value = form.CRS_END_DT.value = $('#CRS_END_DT').val();
	form.CRS_GRP_CD.value = $('#CRS_GRP_CD option:selected').val();
	form.action = '';
	
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/ajax_calendar_trnng.do",
		data:$('#searchCRSForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			var htmlString = '';
			if(data.length > 0) {
				htmlString = '<option value="">검색 된 교육과정 중 하나를 선택해주세요.</option>';
			} else {
				htmlString = '<option value="">해당 조건에 맞는 교육과정이 없습니다.</option>';
			}
			for (i=0; i<data.length; i++) {	
				var item = data[i];
				if(item.status == 'lock') {
					htmlString += '<option value="" class="grey-400" >[모집마감] '+item.title+' ('+item.subtitle+' , '+item.subinfo+')</option>';
				} else if(item.status == 'last') {
					htmlString += '<option value="" class="grey-400" >[기간만료] '+item.title+' ('+item.subtitle+' , '+item.subinfo+')</option>';
				} else if(item.online == 'Y') {
					htmlString += '<option value="'+item.crs_sn+'" class="font-weight-bold" style="background-color:#9de6f5;">'+item.title+'</option>';
				} else {
					htmlString += '<option value="'+item.crs_sn+'">'+item.title+' ('+item.subtitle+' , '+item.subinfo+')</option>';
				}
			}
			$('#target_sel_crs_data').html(htmlString);			
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
			$('#target_crs_data').html('');	
			$('#target_sel_crs_data').html('');		
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});	
	
});
$("#btn_submit").click(function() {
	
	var form = document.getElementById('listForm');
	var RMNDR_DTL_LICENSE_CD = $('#RMNDR_DTL_LICENSE_CD option:selected').val();	
		
	if(form.RMNDR_CRS_SN.value == '') {
		//allPublicModalMessage($(form.RMNDR_CRS_SN).attr('data-fail-message'));
		alert($(form.RMNDR_CRS_SN).attr('data-fail-message'));
		$(form.RMNDR_CRS_SN).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
		
	if(form.RMNDR_MBR_NM_CHK.value == '') {
		//allPublicModalMessage($(form.RMNDR_MBR_NM_CHK).attr('data-fail-message'));
		alert($(form.RMNDR_MBR_NM_CHK).attr('data-fail-message'));
		$(form.RMNDR_MBR_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
		
	if(form.RMNDR_MBR_BIRTH_CHK.value == '') {
		//allPublicModalMessage($(form.RMNDR_MBR_BIRTH_CHK).attr('data-fail-message'));
		alert($(form.RMNDR_MBR_BIRTH_CHK).attr('data-fail-message'));
		$(form.RMNDR_MBR_BIRTH_CHK).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
		
	if(form.RMNDR_MBR_BIRTH_CHK.value.trim().length < 10) {
		//allPublicModalMessage('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
		
	if(form.RMNDR_MBR_ADDR1.value == '') {
		//allPublicModalMessage($(form.RMNDR_MBR_ADDR1).attr('data-fail-message'));
		alert($(form.RMNDR_MBR_ADDR1).attr('data-fail-message'));
		$(form.RMNDR_MBR_ADDR1).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	/*
	if(form.RMNDR_MBR_ADDR2.value == '') {
		allPublicModalMessage($(form.RMNDR_MBR_ADDR2).attr('data-fail-message'));
		$(form.RMNDR_MBR_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	*/
	if(form.RMNDR_MBR_HP_CHK.value == '') {
		//allPublicModalMessage($(form.RMNDR_MBR_HP_CHK).attr('data-fail-message'));
		alert($(form.RMNDR_MBR_HP_CHK).attr('data-fail-message'));
		$(form.RMNDR_MBR_HP_CHK).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}

	if(form.RMNDR_DTL_LICENSE_CD.value == '') {	// 수강인구분선택옵션
		//allPublicModalMessage($(form.RMNDR_DTL_LICENSE_CD).attr('data-fail-message'));
		alert($(form.RMNDR_DTL_LICENSE_CD).attr('data-fail-message'));
		$(form.RMNDR_DTL_LICENSE_CD).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(RMNDR_DTL_LICENSE_CD == 'CIDL00001' || RMNDR_DTL_LICENSE_CD == 'CIDL00002'){
		if(form.MBR_FSHRBT_TYPE.value == '') {	// 교육수강 구분
			//allPublicModalMessage($(form.MBR_FSHRBT_TYPE).attr('data-fail-message'));
			alert($(form.MBR_FSHRBT_TYPE).attr('data-fail-message'));
			return;
		}
	}
	
	if(form.RMNDR_SHIP_LICENSE.value == '') {	//해기사면허
		//allPublicModalMessage($(form.RMNDR_SHIP_LICENSE).attr('data-fail-message'));
		alert($(form.RMNDR_SHIP_LICENSE).attr('data-fail-message'));
		$(form.RMNDR_SHIP_LICENSE).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.RMNDR_SIDO_CD.value == '') {
		//allPublicModalMessage($(form.RMNDR_SIDO_CD).attr('data-fail-message'));
		alert($(form.RMNDR_SIDO_CD).attr('data-fail-message'));
		$(form.RMNDR_SIDO_CD).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.RMNDR_SIGNGU_CD.value == '') {
		//allPublicModalMessage($(form.RMNDR_SIGNGU_CD).attr('data-fail-message'));
		alert($(form.RMNDR_SIGNGU_CD).attr('data-fail-message'));
		$(form.RMNDR_SIGNGU_CD).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
		
	/* var select = document.getElementById('RMNDR_DTL_LICENSE_CD');
	var selectValue = select.options[select.selectedIndex].value;
	if(selectValue == 'CIDL00001' || selectValue == 'CIDL00002'){
		var REQ_YN = document.getElementsByName('REQ_YN');
		var checkCnt = 0;
		for(var i = 0; i < REQ_YN.length; i++){
			if(REQ_YN[i].checked == true) checkCnt++;
		}
		if(checkCnt < 1) {
			$(form.REQ_YN).attr('data-fail-message', '교육 알림 선택은 필수입니다');
			allPublicModalMessage($(form.REQ_YN).attr('data-fail-message'));
			return;
		}
	} */
	var BF_END_DT = form.crsEndDt.value.substring(0,10);
	var END_DT = new Date(BF_END_DT);
	var formattedDate = new Date(); 
	var d = formattedDate.getDate(); 
	var m = formattedDate.getMonth(); m += 1;
	var y = formattedDate.getFullYear();
	//var BF_STR_DT = y + "-" + m + "-" + d;
	var BF_STR_DT = "";
	if(m < 10)
		BF_STR_DT = y + "-0" + m + "-" + d;
	else 
		BF_STR_DT = y + "-" + m + "-" + d;
	var STR_DT = new Date(BF_STR_DT);
	var DIFF_DATE = END_DT.getTime() - STR_DT.getTime();
	var DATE = Math.abs(DIFF_DATE / (1000 * 3600 * 24));
	var confirmValue = confirm("신청하신 교육은 " + Math.floor(DATE) + "일 후 수강이 종료됩니다.\n(~" + BF_END_DT + "까지 이수 필수)\n기간 내 교육이수를 하지 않았을 경우, 재수강을 해야합니다.\n\n해당 교육을 신청하시겠습니까?");
	if(confirmValue){
		//비회원 가입시 이미 신청정보가 있는지 확인
		$.ajax({
			type:"POST",
			url :"/educenter/rmndr/checkInfo.do",
			data:$('#listForm').serialize(),
			dataType: "html",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data != ""){
					$("#allPublicModal").html(data);
					$("#allPublicModal").modal({
							backdrop: 'static',
						    keyboard: false
						});
				} else {
					writeAct();
				}
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	}
	//공백제거
	form.RMNDR_MBR_NM_CHK.value = form.RMNDR_MBR_NM_CHK.value.trim();
	form.RMNDR_MBR_BIRTH_CHK.value = form.RMNDR_MBR_BIRTH_CHK.value.trim();
	form.RMNDR_MBR_ADDR1.value = form.RMNDR_MBR_ADDR1.value.trim();
	form.RMNDR_MBR_ADDR2.value = form.RMNDR_MBR_ADDR2.value.trim();
	form.RMNDR_MBR_HP_CHK.value = form.RMNDR_MBR_HP_CHK.value.trim();	
	form.RMNDR_MBR_TEL.value = form.RMNDR_MBR_TEL.value.trim();	
	
	form.RMNDR_DTL_NM = form.RMNDR_DTL_NM.value.trim();
	form.RMNDR_REG_NUM_CD = form.RMNDR_REG_NUM_CD.value.trim();           
	form.RMNDR_SHIP_CD = form.RMNDR_SHIP_CD.value.trim();
	form.RMNDR_YMD_NM = form.RMNDR_YMD_NM.value.trim();
	
	
});

function deleteAct(){
	$.ajax({
		type:"POST",
		url :"/educenter/rmndr/remove_act.do",
		data:$('#listForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data.error == '0'){
				writeAct();
			} else if(data.error == '0'){
				alert('기존데이터를 삭제하는데 실패하였습니다.');
			}
		},
		complete : function() {

	    },
		error: function(jqXHR, textStatus, errorThrown) {

		}
	});
}

function writeAct(){
	$.ajax({
		type:"POST",
		url :"/educenter/rmndr/write_act.do",
		data:$('#listForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			 if(data.errCnt > 0) {
				var pass = true;
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');
					pass = false;
					
				}
				if(pass) {
					var cn = new EN();
					cn.setData("uid", "sealifeexpo");
					cn.setData("ordcode", "");
					cn.setData("qty", "1");
					cn.setData("price", "1"); 
					cn.setData("pnm", encodeURIComponent(encodeURIComponent("counsel")));
					//cn.setSSL(true);
					cn.sendConv();

					alert(data.msg);
				}
			} else {
				if(data.error == '1') {
					alert(data.msg);
				} else if(data.error == '14') {
					alert(data.msg);
					$('#age14UnderParentCheckLabel').html(data.msg);
					$('#age14UnderParentCheckBtn').show();
				} else {
					if(data.auto_reg == '0') {
						alert('낚시어선 전문교육 수강신청서 제출이 완료되었습니다.\n관리자 검토 후 승인 완료 시 문자로 안내됩니다.\n신청 익일 정오 12시까지 안내 문자 미수신시 ☎  1833-7139로 신청 확인 부탁드립니다.');
					} else {
						alert('낚시어선 전문교육 수강신청서 제출이 완료되었습니다.');
					}
					location.href = data.return_url;
				}
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
/* 			console.log('error!');
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
			clickRequestLockStop(); */
		}
	});
}


//알림사항
/* function chageSelect(){
	var select = document.getElementById('RMNDR_DTL_LICENSE_CD');
	var selectValue = select.options[select.selectedIndex].value;
	
	var smsCheck = document.getElementById('smsCheck');

	if(selectValue == 'CIDL00001' || selectValue == 'CIDL00002'){
		smsCheck.style.display = "block";
		
		//smsCheck.style.transition = ".3s ease-in-out";
	} else {
		smsCheck.style.display = "none";
		//체크해제
		var sendSmsCheck = document.getElementsByName('REQ_YN');
		for(var i = 0; i < sendSmsCheck.length; i++){
			sendSmsCheck[i].checked = false;
		}
	}
} */

/* $("input[name='REQ_YN']").on("click", function(){
	if($(this).val() == 'Y' || $(this).val() == 'R'){
		var html = '<ul class="ml-20">';
			html += '<li class="line-h18">현재 코로나19로 교육 개설이 되지 않아 추후 집합교육 개설 시, 입력해 주신 연락처로 교육 안내문자를 발송해드리겠습니다.</li>';
			html += '<li class="line-h18">온라인교육 수강을 원치 않으실 경우 교육 개설 안내를 위하여 1833-7139로 연락 주시기 바랍니다.</li>';
			html += '</ul>';
		$("#more_msg").html(html);
	} else {
		$("#more_msg").text("");
	}
}); */

/* $("#MBR_FSHRBT_TYPE").on("change", function(){

var val = $(this).val();
if(val == 'legacy' || !val){
	//$("#smsCheck").removeClass("hide");
	$("#alert_text").addClass("hide");
	$("#text_submit").removeClass("hide");
	$("#btn_submit").removeClass("hide");
} else {
	//$("#smsCheck").addClass("hide");
	$("#alert_text").removeClass("hide");
	$("#text_submit").addClass("hide");
	$("#btn_submit").addClass("hide");
}
}); */

function changeSelect() {
var val = $("#MBR_FSHRBT_TYPE option:selected").val();
if(val == 'legacy' || !val){
	//$("#smsCheck").removeClass("hide");
	$("#alert_text").addClass("hide");
	$("#text_submit").removeClass("hide");
	$("#btn_submit").removeClass("hide");
} else {
	//$("#smsCheck").addClass("hide");
	$("#alert_text").removeClass("hide");
	$("#text_submit").addClass("hide");
	$("#btn_submit").addClass("hide");
}
}
</script>

<!-- //다음주소찾기// -->
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('zipcode_layer');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }
    function zipcode(zipcode, addr1, addr2) {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById(zipcode).value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById(addr1).value = fullAddr;
                document.getElementById(addr2).focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : '10'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- End //다음주소찾기// -->

<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>
