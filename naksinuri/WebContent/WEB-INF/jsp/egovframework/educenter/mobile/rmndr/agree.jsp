<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>


<%-- //휴대폰 본인인증 KCB --%>
<%@ page import="kcb.org.json.*" %>
<%
    //**************************************************************************
	// 파일명 : phone_popup2.jsp
	// - 팝업페이지
	// 휴대폰 본인확인 서비스 인증페이지 호출 화면
    //
    // ※주의
    // 	실제 운영시에는 화면에 보여지는 데이터를 삭제하여 주시기 바랍니다.
    // 	방문자에게 사이트데이터가 노출될 수 있습니다.
    //**************************************************************************
	
	//' UTF-8 환경의 경우 주석 해제 + 전체 페이지 상단 charset, pageEncoding 및 파일인코딩 변경 필요
	//request.setCharacterEncoding("UTF-8");
	
	/**************************************************************************
	 * okcert3 휴대폰 본인확인 서비스 파라미터
	 **************************************************************************/
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' 회원사 사이트명, URL
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String SITE_NAME = (String)request.getAttribute("kcbokcert_sitenm");// 요청사이트명 
	String SITE_URL = (String)request.getAttribute("kcbokcert_siteurl");//

	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리) 
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String CP_CD = (String)request.getAttribute("kcbokcert_cpid");//request.getParameter("CP_CD");;	// 회원사코드
	session.setAttribute("PHONE_CP_CD", CP_CD);

	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 리턴 URL 설정
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' opener(popup1)의 도메인과 일치하도록 설정해야 함. 
	//' (http://www.test.co.kr과 http://test.co.kr는 다른 도메인으로 인식하며, http 및 https도 일치해야 함)
	//String RETURN_URL = "http://"+request.getServerName()+":8080/phone_popup/phone_popup3.jsp";// 인증 완료 후 리턴될 URL (도메인 포함 full path)
	String RETURN_URL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/all/auth/kcbOkCertResult.do";// 인증 완료 후 리턴될 URL (도메인 포함 full path)
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 인증요청사유코드 (가이드 문서 참조)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String RQST_CAUS_CD = "00";
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' 채널 코드 (공백가능. 필요한 회원사에서만 입력)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//String CHNL_CD = request.getParameter("CHNL_CD");	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 리턴메시지 (공백가능. returnUrl에서 같이 전달받고자 하는 값이 있다면 설정.)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//String RETURN_MSG = "";
	
	
	//' ########################################################################
	//' # 타겟 및 팝업URL : 운영/테스트 전환시 변경 필요
	//' ########################################################################
	String target = "PROD";	// 테스트="TEST", 운영="PROD"
	//String popupUrl = "";	// 테스트 URL
	String popupUrl = "https://safe.ok-name.co.kr/CommonSvl";// 운영 URL
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 라이센스 파일
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String license = (String)request.getAttribute("kcbokcert_licensepath") + CP_CD + "_IDS_01_" + target + "_AES_license.dat";
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 서비스명 (고정값)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String svcName = "IDS_HS_POPUP_START";
	
	/**************************************************************************
	okcert3 요청 정보
	**************************************************************************/
	JSONObject reqJson = new JSONObject();
	reqJson.put("RETURN_URL", RETURN_URL);
	reqJson.put("SITE_NAME", SITE_NAME);
	reqJson.put("SITE_URL", SITE_URL);
	reqJson.put("RQST_CAUS_CD", RQST_CAUS_CD);
	
	//reqJson.put("CHNL_CD", CHNL_CD);
	//reqJson.put("RETURN_MSG", RETURN_MSG);
	
	//' 거래일련번호는 기본적으로 모듈 내에서 자동 채번되고 채번된 값을 리턴해줌.
	//'	회원사가 직접 채번하길 원하는 경우에만 아래 코드를 주석 해제 후 사용.
	//' 각 거래마다 중복 없는 String 을 생성하여 입력. 최대길이:20
	//reqJson.put("TX_SEQ_NO", "123456789012345"); 
	
	String reqStr = reqJson.toString();
	
	/**************************************************************************
	okcert3 실행
	**************************************************************************/
	kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
	
	// '************ IBM JDK 사용 시, 주석 해제하여 호출 ************
	// okcert.setProtocol2type("22");
	// '객체 내 license를 리로드해야 될 경우에만 주석 해제하여 호출. (v1.1.7 이후 라이센스는 파일위치를 key로 하여 static HashMap으로 사용됨)
	// okcert.delLicense(license);
	
	//' callOkCert 메소드호출 : String license 파일 path로 라이센스 로드
	String resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr);
	/*
	// 'OkCert3 내부에서 String license 파일 path로 라이센스를 못 읽어올 경우(Executable Jar 환경 등에서 발생),
	// '메소드 마지막 파라미터에 InputStream를 사용하여 라이센스 로드
	String resultStr = null;
	if ( ! okcert.containsLicense(license) ) {			// 로드된 라이센스 정보가 HashMap에 없는 경우
		java.io.InputStream is = new java.io.FileInputStream(license);	// 환경에 맞게 InputStream 로드
		resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr, is);
	} else {											// 로드된 라이센스 정보가 HashMap에 있는 경우
		resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr);
	}
	*/
	
	JSONObject resJson = new JSONObject(resultStr);

    String RSLT_CD =  resJson.getString("RSLT_CD");
    String RSLT_MSG = resJson.getString("RSLT_MSG");
    //if(resJson.has("TX_SEQ_NO")) String TX_SEQ_NO = resJson.getString("TX_SEQ_NO"); // 필요 시 거래 일련 번호 에 대하여 DB저장 등의 처리
    String MDL_TKN = "";
    
	boolean succ = false;
	
    if ("B000".equals(RSLT_CD) && resJson.has("MDL_TKN") ) {
		MDL_TKN = resJson.getString("MDL_TKN");
		succ = true;
    }
    
%>
<style>.d-none {display: none !important;}</style>
<script language=javascript>
	var kcbOkCert_window;
	function kcbOkCertAct(){
		kcbOkCert_window = window.open("", "auth_popup", "width=430,height=640,scrollbar=yes");
		document.reqKCBOKCERTForm.target = "auth_popup";
		document.reqKCBOKCERTForm.action = "https://safe.ok-name.co.kr/CommonSvl";
		document.reqKCBOKCERTForm.submit();
	}
	function go_step() {
		$('#age14UnderParentCheckLabel').html('인증완료');
		$('#age14UnderParentCheckBtn').hide();
	}
</script>	
<form name="reqKCBOKCERTForm" method="post" action="#">
	<input type="hidden" name="tc" value="kcb.oknm.online.safehscert.popup.cmd.P931_CertChoiceCmd"/>	<!-- 변경불가-->
	<input type="hidden" name="cp_cd" value="<%=CP_CD%>">	<!-- 회원사코드 -->
	<input type="hidden" name="mdl_tkn" value="<%=MDL_TKN%>">	<!-- 토큰 --> 
	<input type="hidden" name="target_id" value="">
</form>
<%-- //End of 휴대폰 본인인증 KCB --%>

<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>

<form:form commandName="eduCenterMainVO" id="listForm" name="listForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
	<input type="hidden" id="agree" name="agree" value="1"/>
	<input type="hidden" id="INDVDL_INFO_ST" name="INDVDL_INFO_ST" value=""/>
	<%-- //본인인증정보결과 --%>
	<input type="hidden" name="page_type" value="" />
	<input type="hidden" name="per_auth_type" id="per_auth_type" value="" />
	<input type="hidden" name="per_auth" id="per_auth" value="" />			
	<input type="hidden" name="name" id="name" value="" />			
	<input type="hidden" name="phoneNo" id="phoneNo" value="" />			
	<input type="hidden" name="birthDay" id="birthDay" value="" />
	<input type="hidden" name="gender" id="gender" value="" />
	<input type="hidden" name="parent_relationship" id="parent_relationship" value="" />
	<%-- //End of 본인인증정보결과 --%>
</form:form>

<div class="content respon3">
	<section id="writeForm" class="write_box">
		<h3 class="font-weight-bold">교육 서비스 제공을 위한 개인정보 수집·이용 동의서</h3>
		<div class="agree_box">
			<p class="dottedbox">한국어촌어항공단이 해양수산부로부터 위탁받아 운영 중인 낚시누리(www.naksinuri.kr)는 서비스 제공 및 민원사무 처리를 위한 개인정보 수집·이용을 위하여 『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다.</p>
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
			<%--//만14세 미만 법정대리인 동의 --%>
			<div class="<c:choose><c:when test="${not empty IS_14AGE_UNDER and IS_14AGE_UNDER eq 'Y' }"></c:when><c:otherwise>d-none</c:otherwise></c:choose>" id="age14UnderParentCheck">
				<p class="dottedbox mt-30">※ 만14세 미만 정보주체의 개인정보 수집 시 법정대리인의 동의를 구하고 있음을 알려드립니다.</p>
				<table class="basic_tbl mt-10">
					<caption>※ 만14세 미만 정보주체의 개인정보 수집 시 법정대리인의 동의를 구하고 있음을 알려드립니다.</caption>
					<colgroup>
						<col />
						<col width="80px"/>
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>
						<tr>
							<td>정보주체(신청자)와의 관계</td>
							<td colspan="2">
								<input type="text" class="" value="" id="age14UnderParentRelationship" placeholder="관계"/>
							</td>						
						</tr>
						<tr>
							<td>법정대리인 본인인증하기</td>
							<td colspan="2">
								<input type="button" class="btn h60px w50 mb-10" value="휴대폰인증" id="age14UnderParentCheckBtn" onclick="kcbOkCertAct()"/>
								<p class="red-600" id="age14UnderParentCheckLabel"></p>
							</td>						
						</tr>
						<tr>
							<td colspan="3">※ 귀하가 동의한 내용 외의 다른 목적으로 활용하지 않으며, 제공된 개인정보의 이용을 거부하고자 할 때에는 개인정보 보조책임자를 통해 열람,정정,삭제를 요구할 수 있습니다.</td>
						</tr>
					</tbody>
				</table>
				<ul class="floats">
					<li class="fr">
						<div class="agree_yesorno mt-30">
							<span class="mr-10">(필수) 만14세 미만고객(회원)에 대한 법정대리인의 수집동의 여부</span>
							<input type="radio" name="age14UnderParentCheckBox" id="age14UnderParentCheckBoxY" value="Y" ><label for="age14UnderParentCheckBoxY"><span></span>동의함</label>
							<input type="radio" name="age14UnderParentCheckBox" id="age14UnderParentCheckBoxN" value="N" ><label for="age14UnderParentCheckBoxN"><span></span>동의하지 않음</label>
						</div>
					</li>	
				</ul>
			</div>
			<%--//End of 만14세 미만 법정대리인 동의 --%>
		</div>
		<div id="btnArea">
			<button type="button" class="btn_request btn_blue h60px w50 clk_btn_act"><b>다음단계</b></button>
		</div>
	</section>
</div>

<script>
var is14ageUnder = '${IS_14AGE_UNDER}';
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
	
	var age14UnderParentRelationship = '';
	if(is14ageUnder == 'Y') {//만14세 미만 법정대리인 동의여부
		
		age14UnderParentRelationship = $('#age14UnderParentRelationship').val();
		if(typeof age14UnderParentRelationship == "undefined" || age14UnderParentRelationship==null || age14UnderParentRelationship.length == 0) {
			alert('정보주체(신청자)와의 관계를 입력해주세요.');
			return false;
		}
		
		var age14UnderParentCheckLabel = $('#age14UnderParentCheckLabel').html();
		if(typeof age14UnderParentCheckLabel == "undefined" || age14UnderParentCheckLabel==null || age14UnderParentCheckLabel.length == 0) {
			alert('법정대리인의 본인인증을 진행해주세요.');
			return false;
		}
		
		var age14UnderParentCheckBox = $(':radio[name="age14UnderParentCheckBox"]:checked').val();
		var isLockAge14UnderMsg = '';
		var islockAge14UnderParentCheckBox = false;
		if (typeof age14UnderParentCheckBox == "undefined") {
			isLockAge14UnderMsg = '(필수) 만14세 미만고객(회원)에 대한 법정대리인의 수집동의 여부에 동의해야 합니다.';
			islockAge14UnderParentCheckBox = true;
		} else {
			if(age14UnderParentCheckBox == 'N') {
				isLockAge14UnderMsg = '만14세 미만고객(회원)은 법정대리인의 동의가 있어야 신청하실 수 있습니다.';
				islockAge14UnderParentCheckBox = true;
			} 
		}
		if(islockAge14UnderParentCheckBox) {
			alert(isLockAge14UnderMsg);
			$('#age14UnderParentCheckBoxY').focus();
			return false;
		}

	}
	
	var INDVDL_INFO_ST = approval2;
	
	var form = document.getElementById('listForm');
	form.action = "/educenter/m/rmndr/write${addWebLink}.do";
	form.agree.value = agree;
	form.parent_relationship.value = age14UnderParentRelationship;
	form.INDVDL_INFO_ST.value = INDVDL_INFO_ST;
	form.submit();
});
</script>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
