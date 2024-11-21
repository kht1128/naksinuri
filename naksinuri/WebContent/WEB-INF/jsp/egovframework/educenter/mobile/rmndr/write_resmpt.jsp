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
			$('#target_crs_data').html('<tr><td colspan="2" class="text-center">교육정보를 불러오는 중입니다..</td></tr>');	
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
<input type="hidden" name="TYPE_GB" value=""/>
<input type="hidden" name="CRS_LAW_TYPE" value="CIDLAW002" />
<input type="hidden" id="CRS_ADDR" name="CRS_ADDR" value="${info_crs.CRS_ADDR}"/>
<input type="hidden" id="CRS_PLACE" name="CRS_PLACE" value="${info_crs.CRS_PLACE}"/>


</form:form>		

<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post" >
	<input type="hidden" name="RMNDR_DTL_CD" value="${CRS_MBR_CD }"/>
	<input type="hidden" name="INDVDL_INFO_ST" value="${INDVDL_INFO_ST}"/>
	<div class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>교육과정선택</h2>
			<p class="dottedbox"><b>수강신청문의</b><br>(전화) 1833-7139<br>(팩스) 0505-742-1004<br>(이메일) naksinuri@fipa.or.kr</p>
			<div class="list_searchbox mt-30">
				<span class="ml-5 mr-5 basic_input text-middle">교육일정</span> 
				<br><input type="text" id="CRS_STR_DT" class="frm_input basic_input wp35 text-center" value="${CRS_STR_DT}" autocomplete="off" readonly/>
				<span class="ml-5 mr-5 basic_input text-middle">부터 ~</span> 
				<input type="text" id="CRS_END_DT" class="frm_input basic_input wp35 text-center" value="${CRS_END_DT}" autocomplete="off" readonly/>
				<span class="ml-5 mr-5 basic_input text-middle">까지</span> 
				<br><select class="basic_select wp80" title="검색조건선택" id="CRS_GRP_CD"/>
					<option value="${item.CD_ID}">지역조건 전체보기</option>
					<c:forEach var="item" items="${list_edu_grp_cd}">
						<option value="${item.CD_ID}" <c:if test="${CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
					</c:forEach>
				</select>
				<button class="searchBtn btn_search mr-0" ><i class="fa fa-search" aria-hidden="true" ></i></button>				
			</div>
			<div class="list_searchbox_no_wrapAll mt-80 mb-5">
				<select class="basic_select wp100" title="교육과정선택" style="border:2px solid #000;" name="RMNDR_CRS_SN" onchange="sel_crs_data(this)" id="target_sel_crs_data" data-fail-message="신청하고자 하는 교육과정을 선택해주세요." required/>
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
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>		
						<tr>
							<td>
								<h4>성 명 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 readonly" name="RMNDR_MBR_NM_CHK" value="${MBR_NM }" placeholder="성함을 입력해주세요." data-fail-message="성함은 필수 입력정보입니다." autocomplete="off" required disabled>
								<input type="hidden" name="RMNDR_MBR_NM" value="${MBR_NM }" readonly/>
							</td>
						</tr>
						<tr>
							<td>
								<h4>생년월일 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-birth-input-pattern readonly" name="RMNDR_MBR_BIRTH_CHK" value="${MBR_BIRTH }" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" required disabled>
								<input type="hidden" name="RMNDR_MBR_BIRTH" value="${MBR_BIRTH }" readonly/>
							</td>
						</tr>
						<tr>
							<td>
								<h4>주 소 <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100px mr-5" id="RMNDR_MBR_ZIP" name="RMNDR_MBR_ZIP" placeholder="우편번호" readonly />
								<button type="button" onclick="zipcode('RMNDR_MBR_ZIP','RMNDR_MBR_ADDR1','RMNDR_MBR_ADDR2');" class="btn">우편번호 찾기</button>
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR1" name="RMNDR_MBR_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly>
								<input type="text" class="basic_input w100 mt-5" id="RMNDR_MBR_ADDR2" name="RMNDR_MBR_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" >
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
									<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<h4>연 락 처(휴대폰) <span class="red-600">*</span></h4>
								<input type="text" class="basic_input w100 mbr-hp-input-pattern readonly" name="RMNDR_MBR_HP_CHK" value="${MBR_HP }" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" required disabled>
								<input type="hidden" name="RMNDR_MBR_HP" value="${MBR_HP }" readonly/>
							</td>
						</tr>
						<tr>
							<td>
								<h4>연 락 처(일반전화)</h4>
								<input type="text" class="basic_input w100 mbr-tel-input-pattern" name="RMNDR_MBR_TEL" value="" placeholder="예)0260980812" data-fail-message="일반전화번호를 입력하세요." autocomplete="off" >
							</td>
						</tr>						
					</tbody>
				</table>
				<div>
					<h3 class="mt-30">
						<i class="fa fa-info-circle" aria-hidden="true"
							style="color: crimson;"></i> 알림사항
					</h3>
					<div class="mt-10 bg-emphasis">
						<p class="font-weight-bold emphasis">
							「신규·재개자 전문교육」은 20.2.21. 이후 최초로 낚시어선업을 신고(신규)하거나 영업정지명령을 받은 후 재개하려는 낚시어선업자에 해당하는 교육입니다.	
						<span class="font-weight-bold emphasis2">선장, 선원, 안전요원, 사무장, 조리사 등에 해당하시면 낚시어선 온라인교육을 신청</span>
						하여 주시기 바랍니다.</p>				
					</div>
				</div>						
				<h3 class="mt-30">추가상세정보</h3>
				<table class="basic_tbl mt-10">
					<caption>상세정보</caption>
					<colgroup>
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>
						<tr>
							<td>
								<h4>수강인구분 <span class="red-600">*</span></h4>
								<select class="basic_input wp45" title="수강인구분선택" id="RMNDR_DTL_LICENSE_CD" name="RMNDR_DTL_LICENSE_CD" data-fail-message="수강인구분은 필수 선택정보입니다." <%-- onchange="chageSelect();" --%> required>  
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
								<select class="basic_input wp45" title="해기사면허유무선택" name="RMNDR_SHIP_LICENSE" data-fail-message="해기사면허 선택은 필수 선택정보입니다." required>		
									<option value="">해기사면허</option>			
									<option value="유">유</option>			
									<option value="무">무</option>		
								</select>
								<br><span class="red-600 font-weight-bold">선주 겸 선장은 개인업자로 선택해주시기 바랍니다.</span>
							</td>
						</tr>
						
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<td>
								<h4>낚시어선명칭</h4>
								<input type="text" class="basic_input w100 " name="RMNDR_DTL_NM" value="" placeholder="낚시어선 명칭을 입력하세요." data-fail-message="" autocomplete="off">
							</td>
						</tr>
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<td>
								<h4>신고확인증번호</h4>
								<input type="text" class="basic_input wp100 " name="RMNDR_REG_NUM_CD" value="" placeholder="낚시어선 신고확인증번호를 입력하세요." data-fail-message="" autocomplete="off">
								<%--
								(&nbsp;유효기간&nbsp;:&nbsp;
								<input type="text" id="RMNDR_SHIP_LICENSE_STR_DT" class="frm_input basic_input wp15 text-center" value="" autocomplete="off" readonly/>
								&nbsp;~&nbsp;<input type="text" id="RMNDR_SHIP_LICENSE_END_DT" class="frm_input basic_input wp15 text-center" value="" autocomplete="off" readonly/>&nbsp;)
								--%>
							</td>
						</tr>
						<tr class="<c:if test="${not empty INDVDL_INFO_ST and INDVDL_INFO_ST eq 'N' }">hide</c:if>">
							<td>
								<h4>어선번호</h4>
								<input type="text" class="basic_input w100 ship-cd-input-pattern" name="RMNDR_SHIP_CD" value="" placeholder="어선번호를 입력하세요." data-fail-message="" autocomplete="off">
							</td>
						</tr>
						
						<tr>
							<td>
								<h4>낚시어선 신고지 <span class="red-600">*</span></h4>
								<select class="basic_input sel_area_sido wp100" title="낚시어선 신고지 시도" name="RMNDR_SIDO_CD" data-fail-message="낚시어선 신고지(시도)는 필수 선택정보입니다." required>  
									<option value="">시도 선택</option> 
									<c:forEach var="item" items="${list_address_cd}">
										<option value="${item.CD_ID}">${item.CD_NM}</option>
									</c:forEach>
								</select>
								<select class="basic_input sel_area_signgu wp100 mt-5" name="RMNDR_SIGNGU_CD" data-fail-message="낚시어선 신고지(시군구)는 필수 선택정보입니다." required>   
									<option value="" >시군구선택</option>
								</select>
								<input type="text" class="basic_input wp100 mt-5" name="RMNDR_YMD_NM" value="" placeholder="읍면동을 입력하세요." data-fail-message="" autocomplete="off">
							</td>
						</tr>
					</tbody>
				</table>
				
				<div class="hide" id="smsCheck">
					<h3 class="mt-30"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;"></i> 알림사항</h3>
					<div class="mt-10 bg-emphasis">
						<p class="font-weight-bold emphasis">
							귀하는 「신규·재개자 전문교육」 대상이 아닙니다. 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 「낚시어선 전문교육」을 수강하여 주시기 바랍니다.<br>
							20.2.21. 이후 최초 신고(신규) 및 영업정지명령을 받은 후 재개하려는 낚시어선업자(재개자)의 경우
						</p>
						<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계없이 「신규·재개자 전문교육(현장교육, 연간 21시간 이상)」을 수강하여야 합니다.</span>
						<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 기존 선택 및「낚시어선 전문교육」을 신청하여 주시기 바랍니다.</p>
					</div>
				</div>
			</div>
			
			<div class="mt-10" id="more_msg"></div>
			<div id="text_submit">
				<p class="dottedbox red-600  mt-30 mb-5 text-center">신청서 제출 시 관리자 검토 후 승인이 완료되면 <b>문자로 안내됩니다.</b></p>
				<p class="dottedbox mt-5 mb-5 text-center">(신청서는 순차적으로 검토되어 승인 완료까지 다소 시간이 걸릴 수 있으니 양해 부탁드리며,<br>신청 후 익일 정오 12시까지 안내 문자 미수신시 1833-7139로 신청 확인 부탁드립니다.)</p>
				<%--<p class="dottedbox green-800 font-weight-bold mt-5 mb-5 text-center">회원인 경우에는 로그인 후 교육신청 페이지에서 신청을 하시면 관리자 검토없이 수강신청이 가능합니다.</p> --%>
				<p class="dottedbox mt-20  text-center">『낚시전문교육 및 교육기관 지정에 관한 고시』 제8조에 따라 위와 같이 신청합니다.</p>
			</div>
			<div id="btnArea">
				<button type="button" class="btn_blue h60px w50 trg_btn_submit" id="btn_submit"><b>신청하기</b></button>
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
					alertify.alert(data.msg);
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
	
	if(form.RMNDR_DTL_LICENSE_CD.value == '') {
		allPublicModalMessage($(form.RMNDR_DTL_LICENSE_CD).attr('data-fail-message'));
		$(form.RMNDR_MBR_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(RMNDR_DTL_LICENSE_CD == 'CIDL00001' || RMNDR_DTL_LICENSE_CD == 'CIDL00002'){
		if(form.MBR_FSHRBT_TYPE.value == '') {	// 교육수강 구분
			//allPublicModalMessage($(form.MBR_FSHRBT_TYPE).attr('data-fail-message'));
			alert($(form.MBR_FSHRBT_TYPE).attr('data-fail-message'));
			return;
		}
	}
	
	if(form.RMNDR_SHIP_LICENSE.value == '') {
		allPublicModalMessage($(form.RMNDR_SHIP_LICENSE).attr('data-fail-message'));
		$(form.RMNDR_SHIP_LICENSE).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.RMNDR_SIDO_CD.value == '') {
		allPublicModalMessage($(form.RMNDR_SIDO_CD).attr('data-fail-message'));
		$(form.RMNDR_MBR_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
	
	if(form.RMNDR_SIGNGU_CD.value == '') {
		allPublicModalMessage($(form.RMNDR_SIGNGU_CD).attr('data-fail-message'));
		$(form.RMNDR_MBR_NM).focus();//모달메세지시 포커스가 빠지므로 의미없음..
		return;
	}
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
	
	//var confirmValue = confirm("신청하신 교육은 " + Math.floor(DATE) + "일 후 수강이 종료됩니다.\n(~" + BF_END_DT + "까지 이수 필수)\n기간 내 교육이수를 하지 않았을 경우, 재수강을 해야합니다.\n\n해당 교육을 신청하시겠습니까?");
	
	//2023-09-12 고광훈추가 (날짜/장소 반영)
	var CRS_GRP_CD = $('#CRS_GRP_CD option:selected').val();
	
	if(CRS_GRP_CD!='CIDE00000000000'){
		
		var rows = document.getElementById("target_crs_data").getElementsByTagName("tr");
		var location = '';
		var location_name = '';
		for( var r=0; r<rows.length; r++ ){
			var cells_th = rows[r].getElementsByTagName("th");
			var cells_td = rows[r].getElementsByTagName("td");
		    var cell_1 = cells_th[0].firstChild.data;		
		    var cell_2 = cells_td[0].firstChild.data;	
		    
		    if(cell_1=='교육일시'){
		    	location = cell_2;
		    }
		    
		    if(cell_1=='교육장소'){
		    	location_name = cell_2;
		    }
		}		
		
		var confirmValue = confirm("신청하신 교육은 " + location + " " + location_name + "에서 진행되는 현장교육입니다.\n기간 내 교육이수를 하지 않았을 경우, 재수강을 해야합니다.\n\n해당 교육을 신청하시겠습니까?");
	
	}else{
		
		var confirmValue = confirm("신청하신 교육은 " + Math.floor(DATE) + "일 후 수강이 종료됩니다.\n(~" + BF_END_DT + "까지 이수 필수)\n기간 내 교육이수를 하지 않았을 경우, 재수강을 해야합니다.\n\n해당 교육을 신청하시겠습니까?");	
		
	}			
	
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
					alert('낚시어선 전문교육 수강신청서 제출이 완료되었습니다.\n관리자 검토 후 승인 완료 시 문자로 안내됩니다.\n신청 익일 정오 12시까지 안내 문자 미수신시 ☎  1833-7139로 신청 확인 부탁드립니다.');
					location.href = '/educenter/index.do';	
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
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
			clickRequestLockStop();
		}
	});
}

function changeSelect() {
	var val = $("#MBR_FSHRBT_TYPE option:selected").val();
	if(val == 'legacy'){
		$("#smsCheck").removeClass("hide");
		$("#text_submit").addClass("hide");
		$("#btn_submit").addClass("hide");
	} else {
		$("#smsCheck").addClass("hide");
		$("#text_submit").removeClass("hide");
		$("#btn_submit").removeClass("hide");		
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


<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
