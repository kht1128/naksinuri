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

<style>
	.under-blue {padding: 10px 4px; border-bottom: 3px solid #23527c; font-weight: 900; font-size: 1.5rem;}
	.w50 {width: calc((100% - 5px)/2);}
</style>

<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet> 


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

<c:choose>
	<c:when test="${ECB_DTL_CD eq 'CIDN010200'}">
		<c:set var="DTL_NAME" value="낚시터" />
		<c:set var="SIGNGU" value="허가·등록 시·군·구" />
		<c:set var="REG_NUMBER" value="허가·등록 번호" />
	</c:when>
	<c:otherwise>
		<c:set var="DTL_NAME" value="낚시어선" />
		<c:set var="SIGNGU" value="신고 시·군·구" />
		<c:set var="REG_NUMBER" value="신고증번호" />
	</c:otherwise>
</c:choose>	


<form:form commandName="CodeSetVO" id="modalMemberWriteFormSido" name="modalMemberWriteFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="eduCprBplcVO" id="listForm" name="listForm" method="post" >
	<input type="hidden" name="ECB_DTL_CD" value="${ECB_DTL_CD }"/>
	<input type="hidden" name="ECB_INDVDL_INFO_ST" value="${ECB_INDVDL_INFO_ST }"/>
	<div class="content respon3">
		<section id="writeForm" class="write_box mt-30">
			<h2><c:out value="${DTL_NAME}"/> 법인사업장 교육책임자 지정 확인서</h2>
			<div class="agree_box">
				<h3 class="mt-30 under-blue wx140">법인대표자</h3>
				<table class="basic_tbl mt-10">
					<caption>법인사업장 교육책임자 지정 확인서 양식</caption>
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
							<td><input type="text" class="basic_input w100 " name="ECB_CPR_NM" id="ECB_CPR_NM" value="" placeholder="성명을 입력해주세요." data-fail-message="성명은 필수 입력정보입니다." autocomplete="off" required></td>
							<th class="text-center">생 년 월 일 <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 mbr-birth-input-pattern" name="ECB_CPR_BRTHDY" id="ECB_CPR_BRTHDY" value="" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" required ></td>
						</tr>
						<tr>
							<th class="text-center">${SIGNGU } <span class="red-600">*</span></th>
							<td>
								<select class="basic_input sel_area_sido w50" name="ECB_CPR_SIDO_CD" id="ECB_CPR_SIDO_CD" data-fail-message="${SIGNGU}는 필수 입력정보입니다." required>  
									<option value="">시도 선택</option> 
									<c:forEach var="item" items="${list_address_cd}">
										<option value="${item.CD_ID}">${item.CD_NM}</option>
									</c:forEach>
								</select>
								<select class="basic_input sel_area_signgu w50" name="ECB_CPR_SIGNGU_CD" id="ECB_CPR_SIGNGU_CD" data-fail-message="${SIGNGU}는 필수 입력정보입니다." required>   
									<option value="" >시군구선택</option>
								</select>
							</td>
							<th class="text-center">${REG_NUMBER } <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 reg-num-cd-input-pattern" name="ECB_REG_NUM_CD" id="ECB_REG_NUM_CD" value="" placeholder="${REG_NUMBER }를 입력해주세요." data-fail-message="${REG_NUMBER }는 필수 입력정보입니다." autocomplete="off" maxlength="10" required ></td>
						</tr>
						<tr>
							<th class="text-center"><c:out value="${DTL_NAME}"/>명 <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 " name="ECB_CPR_DTL_NM" id="ECB_CPR_DTL_NM" value="" placeholder="<c:out value="${DTL_NAME}"/>명을 입력해주세요." data-fail-message="<c:out value="${DTL_NAME}"/>명은 필수 입력정보입니다." autocomplete="off" required></td>
							<th class="text-center">휴대전화번호<span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 mbr-hp-input-pattern" name="ECB_CPR_HP" id="ECB_CPR_HP" value="" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" required ></td>
						</tr>
						<tr>
							<th class="text-center">주 소 <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100px mr-5" id="ECB_CPR_ZIP" name="ECB_CPR_ZIP" id="" placeholder="우편번호" readonly />
								<button type="button" onclick="zipcode('ECB_CPR_ZIP','ECB_CPR_ADDR1','ECB_CPR_ADDR2');" class="btn">우편번호 찾기</button> 
								<input type="text" class="basic_input w100 mt-5" id="ECB_CPR_ADDR1" name="ECB_CPR_ADDR1" id="ECB_CPR_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly>
								<input type="text" class="basic_input w100 mt-5" id="ECB_CPR_ADDR2" name="ECB_CPR_ADDR2" id="ECB_CPR_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" >
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
								<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>																
							</td>
						</tr>
					</tbody>
				</table>
				<h3 class="mt-30 under-blue wx140">교육책임자</h3>
				<table class="basic_tbl mt-10">
					<caption>상세정보</caption>
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
							<td><input type="text" class="basic_input w100 " name="ECB_EDU_NM" id="ECB_EDU_NM" value="" placeholder="성명을 입력해주세요." data-fail-message="성함은 필수 입력정보입니다." autocomplete="off" required></td>
							<th class="text-center">생 년 월 일 <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 mbr-birth-input-pattern" name="ECB_EDU_BRTHDY" id="ECB_EDU_BRTHDY" value="" placeholder="예)19190301" data-fail-message="생년월일은 필수 입력정보입니다.<br>19190301 형식으로 8자리를 입력해주세요." autocomplete="off" required ></td>
						</tr>
						<tr>
							<th class="text-center">직 책 · 자 격 <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 " name="ECB_EDU_POSIT" id="ECB_EDU_POSIT" value="" placeholder="직책 · 자격을 입력해주세요." data-fail-message="성함은 필수 입력정보입니다." autocomplete="off" required></td>
							<th class="text-center">휴대전화번호 <span class="red-600">*</span></th>
							<td><input type="text" class="basic_input w100 mbr-hp-input-pattern" name="ECB_EDU_HP" id="ECB_EDU_HP" value="" placeholder="예)01012345678" data-fail-message="연락처는 필수 입력정보입니다." autocomplete="off" required ></td>
						</tr>
						<tr>
							<th class="text-center">주 소 <span class="red-600">*</span></th>
							<td colspan="3">
								<input type="text" class="basic_input w100px mr-5" id="ECB_EDU_ZIP" name="ECB_EDU_ZIP" id="ECB_EDU_ZIP" placeholder="우편번호" readonly />
								<button type="button" onclick="zipcode('ECB_EDU_ZIP','ECB_EDU_ADDR1','ECB_EDU_ADDR2');" class="btn">우편번호 찾기</button> 
								<input type="text" class="basic_input w100 mt-5" id="ECB_EDU_ADDR1" name="ECB_EDU_ADDR1" id="ECB_EDU_ADDR1" value="" placeholder="주소를 입력해주세요." data-fail-message="주소는 필수 입력정보입니다." autocomplete="off" required readonly>
								<input type="text" class="basic_input w100 mt-5" id="ECB_EDU_ADDR2" name="ECB_EDU_ADDR2" id="ECB_EDU_ADDR2" value="" placeholder="상세주소를 입력해주세요." data-fail-message="상세주소는 필수 입력정보입니다." autocomplete="off" >
								<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
								<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>																
							</td>
						</tr>
					</tbody>
				</table>
			</div>			
			<p class="dottedbox mt-20 text-center">『낚시 관리 및 육성법』 제47조제1항에 따른 교육의 책임자임을 확인합니다.</p>
			<p class="dottedbox mt-20 text-center">${year }년 ${month }월 ${day }일</p>
			<p class="dottedbox mt-20 text-center">확인자(법인대표자) : <span id="name"></span></p>
			<p class="dottedbox mt-20 text-center"><b>한국어촌어항공단이사장 귀하</b></p>
			
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
	$("#CRS_STR_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	$("#CRS_END_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	//$("#RMNDR_SHIP_LICENSE_STR_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	//$("#RMNDR_SHIP_LICENSE_END_DT").datepicker({ language: "kr", dateFormat : 'yy-mm-dd' });
	/* 기존방식
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
			'target' : $('.reg-num-cd-input-pattern'),
			'default_pattern' : ['{{9999}}-{{999}}',7],
			'over_pattern' : null,
			'automatch' : true,
		},
	]);
	$('#searchBtn').click();
});

$("#ECB_CPR_NM").on("keyup change", function(){
	$("#name").text($(this).val());
});

$(".sel_area_sido").on("change", function() {
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

$("#btn_submit").click(function() {

	var form = document.getElementById('listForm');
//'ECB_CPR_SIGNGU_CD',
	var arrayList = new Array();
	arrayList = ['ECB_CPR_NM', 'ECB_CPR_BRTHDY', 'ECB_REG_NUM_CD', 'ECB_CPR_DTL_NM', 'ECB_CPR_HP', 'ECB_CPR_ADDR1', 'ECB_CPR_ADDR2',
	             'ECB_EDU_NM', 'ECB_EDU_BRTHDY', 'ECB_EDU_POSIT', 'ECB_EDU_HP', 'ECB_EDU_ADDR1', 'ECB_EDU_ADDR2'];
	
	var getId = "";
	for(var i = 0; i < arrayList.length; i++){
		getId = document.getElementById(arrayList[i]);
		if(!getId.value) {
			allPublicModalMessage(getId.getAttribute('data-fail-message'));
			getId.focus();//모달메세지시 포커스가 빠지므로 의미없음..
			return;
		}
	}
	
	var localList = new Array('ECB_CPR_SIDO_CD', 'ECB_CPR_SIGNGU_CD');
	for(var i = 0; i < localList.length; i++){
		var local = document.getElementById(localList[i]);
		var optionValue = local.options[local.selectedIndex].value;
		if(!optionValue){
			allPublicModalMessage(getId.getAttribute('data-fail-message'));
			getId.focus();//모달메세지시 포커스가 빠지므로 의미없음..
			return;
		}
	}

	if(form.ECB_CPR_BRTHDY.value.trim().length < 10) {
		allPublicModalMessage('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	if(form.ECB_EDU_BRTHDY.value.trim().length < 10) {
		allPublicModalMessage('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	//공백제거
	for(var i = 0; i < arrayList.length; i++){
		getId = document.getElementById(arrayList[i]).value;
		getId = getId.trim();
	}

	$.ajax({
		type:"POST",
		url :"/educenter/rmndr/cpr_bplc_act.do",
		data:$('#listForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			
			if(data.error == '1') {
				alert(data.msg);
			} else {
				alert('법인사업장 교육책임자 지정 확인서 제출이 완료되었습니다.');
				location.href = '/educenter/index.do';	
			}
			
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
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
