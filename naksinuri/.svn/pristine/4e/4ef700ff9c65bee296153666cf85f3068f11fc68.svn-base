<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info" />
<c:set var="pageName" value="industry" />

<%@include file="../../layout/m/head.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/se2/js/HuskyEZCreator.js" charset="utf-8"></script>

<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="save_status" id="save_status" value="r">
	<input type="hidden" name="client_ipaddr" value="<%=request.getRemoteAddr()%>">
	<input type="hidden" value="${info.san_address2}" name="addresse2" id="addresse2"/>
	<input type="hidden" value="${info.san_sn}" id="san_sn"/>
	<input type="hidden" value="${RandKey}" name="psan_sn" id="psan_sn"/>
	<input type="hidden" value="w" name="save_mode" id="save_mode"/>

	<div id="businessWrite" class="content respon3">
		<section id="writeForm" class="write_box mgb30">
			<h2 style="margin-bottom:0px;">낚시산업 정보등록</h2>
			<%--
		<div class="agree_box">
			
			<h3>”낚시산업 정보“ 등록을 위한 개인정보 수집·이용 동의서</h3>
			<p class="dottedbox">낚시정보종합포털 낚시누리(www.naksinuri.kr)(이하 “낚시누리”)는 서비스 제공을 위한 개인정보 수집⦁이용을 위하여 『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다. </p>
			<div class="agree_text">
			<P CLASS=HStyle0 STYLE='text-align:left;text-indent:14.9pt;line-height:140%;'><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";letter-spacing:-5%;font-weight:"bold";line-height:140%'>┃개인정보 수집 및 이용</SPAN></P>
			
				<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD valign="middle" style='width:242;height:28;border-left:none;border-right:dotted #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>&nbsp;개인정보의 수집 및 이용 목적</SPAN></P>
	</TD>
	<TD valign="middle" style='width:400;height:28;border-left:dotted #000000 0.4pt;border-right:none;border-top:solid #000000 1.1pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='margin-left:5.9pt;'><SPAN STYLE='font-family:"맑은 고딕"'>서비스 제공 - 낚시산업 정보 등록 및 홍보</SPAN></P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:242;height:28;border-left:none;border-right:dotted #000000 0.4pt;border-top:dotted #000000 0.4pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>&nbsp;수집하는 개인정보 항목</SPAN></P>
	</TD>
	<TD valign="middle" style='width:400;height:28;border-left:dotted #000000 0.4pt;border-right:none;border-top:dotted #000000 0.4pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='margin-left:5.9pt;line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>성명, 전화번호, 사업장 주소, 이메일 주소</SPAN></P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:242;height:61;border-left:none;border-right:dotted #000000 0.4pt;border-top:dotted #000000 0.4pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>&nbsp;개인정보의 보유 및 이용기간 </SPAN></P>
	</TD>
	<TD valign="middle" style='width:400;height:61;border-left:dotted #000000 0.4pt;border-right:none;border-top:dotted #000000 0.4pt;border-bottom:dotted #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='margin-left:5.9pt;line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>보유기간은 </SPAN><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";font-weight:"bold";text-decoration:"underline";color:#ff0000;line-height:130%'>사용목적 달성 시 까지</SPAN><SPAN STYLE='font-family:"맑은 고딕"'>로 하며, 서비스 제공이 종료된 후에는 처리된 일자의 해당 년 말일까지 보관하고 말일에 일괄 삭제합니다.</SPAN></P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:242;height:80;border-left:none;border-right:dotted #000000 0.4pt;border-top:dotted #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='margin-left:4.6pt;text-indent:-4.6pt;line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>&nbsp;동의 거부 권리 및 동의 거부에 따른 </SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-left:4.6pt;text-indent:-4.6pt;line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>&nbsp;불이익 내용 또는 제한사항</SPAN></P>
	</TD>
	<TD valign="middle" style='width:400;height:80;border-left:dotted #000000 0.4pt;border-right:none;border-top:dotted #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='margin-left:5.9pt;line-height:130%;'><SPAN STYLE='font-family:"맑은 고딕"'>귀하는 개인 정보 수집 및 이용에 대해 동의를 거부할 권리가 있습니다. 동의 거부 시 회원가입이 제한됨을 알려드립니다.</SPAN></P>
	</TD>
</TR>
</TABLE>
			</div><br/>
<P CLASS=HStyle0 STYLE='margin-left:14.9pt;margin-right:4.9pt;margin-bottom:4.0pt;text-align:left;line-height:140%;'><SPAN STYLE='font-family:"맑은 고딕"'>※ 귀하가 동의한 내용 외의 다른 목적으로 활용하지 않으며, 제공된 개인정보의 이용을 거부하고자 할 때에는 개인정보 보호책임자를 통해 열람,정정,삭제를 요구할 수 있음</SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:left;line-height:105%;'><SPAN STYLE='font-size:8.0pt;font-family:"맑은 고딕";letter-spacing:6%;font-weight:"bold";line-height:105%'>&nbsp;&nbsp;</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-left:14.9pt;margin-right:4.9pt;text-align:left;line-height:140%;'><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";letter-spacing:6%;font-weight:"bold";color:#437fc1;line-height:140%'>「개인정보보호법」등 관련 법규에 의거하여 상기 본인은 위와 같이 개</SPAN><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";letter-spacing:5%;font-weight:"bold";color:#437fc1;line-height:140%'>인정</SPAN><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";letter-spacing:7%;font-weight:"bold";color:#437fc1;line-height:140%'>보 수</SPAN><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";letter-spacing:8%;font-weight:"bold";color:#437fc1;line-height:140%'>집 및 이용에 동의함.</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-left:14.9pt;margin-right:4.9pt;text-align:left;line-height:140%;'><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";font-weight:"bold";line-height:140%'><IMG src=".\PICD898.gif" alt="" width="716" height="0" vspace="0" hspace="0" border="0"></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:right;line-height:300%;'><SPAN STYLE='font-size:12.0pt;font-family:"맑은 고딕";font-weight:"bold";color:#282828;line-height:300%'>본인은 상기 내용과 같이 개인정보를 수집⦁이용하는데 동의합니다.</SPAN></P>			
			<div class="agree_yesorno">
				개인정보 수집 및 이용에
				<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의함</label>
				<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않음</label>
			</div>
			 			
		</div>
		--%>
			</section>
			<section id="writeForm" class="write_box">
			<dl>
				<dt>구분</dt>
				<dd>
					<ul class="choicebox floats">
						<li><input type="checkbox" id="san_aag" name="san_aag" value="협회/기관/단체" <c:if test ="${info.san_aag eq'협회/기관/단체'}">checked</c:if>/><label for="san_aag"><span></span>협회/기관/단체</label></li>
						<li><input type="checkbox" id="san_zogu" name="san_zogu" value="조구업체" <c:if test ="${info.san_zogu eq'조구업체'}">checked</c:if>/><label for="san_zogu"><span></span>조구업체</label></li>
						<li><input type="checkbox" id="san_media" name="san_media" value="미디어"  <c:if test ="${info.san_media eq '미디어'}">checked</c:if>  /><label for="san_media"><span></span>미디어</label></li>
						<li><input type="checkbox" id="san_sell"  name="san_sell" value="판매점"  <c:if test ="${info.san_sell eq'판매점'}">checked</c:if> /><label for="san_sell"><span></span>판매점</label></li>
						<li><input type="checkbox" id="san_chool" name="san_chool" value="출조점" <c:if test ="${info.san_chool eq '출조점'}">checked</c:if> /><label for="san_chool"><span></span>출조점</label></li>
						<li><input type="checkbox" id="san_inprov" name="san_inprov"  value="낚시정보제공" <c:if test ="${info.san_inprov eq'낚시정보제공'}">checked</c:if>/><label for="san_inprov"><span></span>낚시정보제공</label></li>
					</ul>
				</dd>
			</dl>
			<dl>
				<dt>상호명</dt>
				<dd><input type="text" size="50" class="write_input w100" name="san_name" id="san_name" placeholder="예)낚시누리낚시점"  value="${info.san_name}"  /></dd>
			</dl>
			<dl>
				<dt>대표자</dt>
				<dd><input type="text" size="50" class="write_input w100" name="san_buisnessman" id="san_buisnessman"  value="${info.san_buisnessman}" placeholder="예)누리지기" /></dd>
			</dl>
			<dl>
				<dt>회사소개</dt>
				<dd><textarea class="write_textarea" rows="10" name="san_content" id="san_content" style="WIDTH: 100%" placeholder="예) 1997년 오픈, 국내최대 낚시용품 매장">${info.san_content}</textarea></dd>
			</dl>
			<dl>
				<dt>사업영역</dt>
				<dd><input type="text" size="100" class="write_input w100" name="san_item" id="san_item" placeholder="ex) 낚시대 제조, 판매, 출조정보 제공" value="${info.san_item}" /></dd>
			</dl>
			<dl>
				<dt>홈페이지</dt>
				<dd><input type="text" size="100" class="write_input w100" name="san_homepage" id="san_homepage" value=<c:choose><c:when test="${info.san_homepage eq null }">"http://"</c:when><c:otherwise>"${info.san_homepage}"</c:otherwise></c:choose>  /></dd>
			</dl>
			<dl>
				<dt>일반전화</dt>
				<dd>
					<select class="naksi_select" name="san_tel1" id="san_tel1">
						<option value=''>없음</option>
							<option <c:if test="${info.san_tel1 eq '02' }">selected</c:if>>02</option>
							<option <c:if test="${info.san_tel1 eq '031' }">selected</c:if>>031</option>
							<option <c:if test="${info.san_tel1 eq '032' }">selected</c:if>>032</option>
							<option <c:if test="${info.san_tel1 eq '033' }">selected</c:if>>033</option>
							<option <c:if test="${info.san_tel1 eq '041' }">selected</c:if>>041</option>
							<option <c:if test="${info.san_tel1 eq '042' }">selected</c:if>>042</option>
							<option <c:if test="${info.san_tel1 eq '043' }">selected</c:if>>043</option>
							<option <c:if test="${info.san_tel1 eq '051' }">selected</c:if>>051</option>
							<option <c:if test="${info.san_tel1 eq '052' }">selected</c:if>>052</option>
							<option <c:if test="${info.san_tel1 eq '053' }">selected</c:if>>053</option>
							<option <c:if test="${info.san_tel1 eq '054' }">selected</c:if>>054</option>
							<option <c:if test="${info.san_tel1 eq '055' }">selected</c:if>>055</option>
							<option <c:if test="${info.san_tel1 eq '061' }">selected</c:if>>061</option>
							<option <c:if test="${info.san_tel1 eq '062' }">selected</c:if>>062</option>
							<option <c:if test="${info.san_tel1 eq '063' }">selected</c:if>>063</option>
							<option <c:if test="${info.san_tel1 eq '064' }">selected</c:if>>064</option>
					</select> - 
					<input type="text" class="write_input" name="san_tel2" id="san_tel2" maxlength="4" value="${info.san_tel2}" size="7" /> - 
					<input type="text" class="write_input" name="san_tel3" id="san_tel3" maxlength="4" value="${info.san_tel3}" size="7" />
					<input type="hidden" name="san_tel" id="san_tel">
				</dd>
			</dl>
			<dl>
				<dt>휴대전화</dt>
				<dd>
					<select class="naksi_select" id="co_hphone1" name="san_phone1">
							<option <c:if test="${info.san_phone1 eq '010' }">selected</c:if>>010</option>
							<option <c:if test="${info.san_phone1 eq '011' }">selected</c:if>>011</option>
							<option <c:if test="${info.san_phone1 eq '016' }">selected</c:if>>016</option>
							<option <c:if test="${info.san_phone1 eq '017' }">selected</c:if>>017</option>
							<option <c:if test="${info.san_phone1 eq '019' }">selected</c:if>>019</option>
						</select> - 
					<input type="text" class="write_input" id="co_hphone2" name="san_phone2" value="${info.san_phone2}"   maxlength="4" size="7" /> - 
					<input type="text" class="write_input" id="co_hphone3" name="san_phone3" value="${info.san_phone3}"  maxlength="4" size="7" />
					<input type="hidden" name="san_phone" id="san_phone">
				</dd>
			</dl>
			<dl>
				<dt>주소</dt>
				<dd class="multi_input">
					<input type="text" class="write_input readonly" name="san_address1" id="newpostcode" value="${info.san_address1}" readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="newaddress()">주소검색</a><br />
					<div id="wrap2" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode2()" alt="접기 버튼">
					</div>
					<input type="text" id="newaddress1" name="san_address2"  readonly class="write_input w100" value="${info.san_address2}" /><br />
					<input type="text" id="newaddress2"  name="san_address3" class="write_input w100" placeholder="상세주소" value="${info.san_address3}" />
					<input type="hidden" name="san_address"/>
				</dd>
			</dl>
			<dl>
				<dt>회사로고</dt>
				<dd>
					<div class="file_input">
					<c:choose>
							<c:when test="${info.file_sn ne null }">
								<td>
									<c:out value="${info.orignl_file_nm }"/>&nbsp;<c:out value="[${info.file_size} Byte]"/>
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
					       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${info.san_img}"/>','<c:out value="${info.file_sn}"/>');" />
								</td>
							</c:when>
							<c:otherwise>
								<input type="file" size="50" class="write_file" id="mimg" name="mimg" accept="image/*" id="mascortimg" />
							</c:otherwise>
						</c:choose>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>소개이미지</dt>
				<dd>
					<div class="file_input">
						<ul id="imgul">
							<c:forEach items="${simglist}" var="item">
								<li>
									<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.san_simg}"/>','<c:out value="${item.file_sn}"/>');" />
								</li>
							</c:forEach>
							<li id="imgli_0"><input type="checkbox" id="chart_0"/><label for="chart_0"><span></span></label> <input type="file" size="50" name="bo_file_0" class="write_file" /></li>
						</ul>
					</div>
					<div id="btnArea">
						<a href="#;" onclick="insertinput()" class="btn_white">이미지 추가</a>
						<a href="#;" onclick="deleteinput()" class="btn_gray">이미지 삭제</a>
					</div>
				</dd>
			</dl>
			<style>
				.btns{display:inline-block;height:30px;line-height:28px;padding:0 10px;border:1px solid #e0e0e0;font-size:14px;}
			</style>
			<c:choose>
					<c:when test="${info.san_sn ne null }">
						<div id="btnArea">
							<button type="button" class="btns btn_request btn_blue" id="industy_fsubmit">수정하기</button>
					</c:when>
					<c:otherwise>
						<%--
						<dl>
							<dt>자동등록방지</dt>
							<dd><div class="g-recaptcha" data-sitekey="6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-"></div></dd> <!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
							<input type="hidden" name="recaptcha" />
						</dl>
						--%>
						<div id="btnArea">
							<button type="button" class="btns btn_request btn_blue" id="industy_fsubmit">등록 신청</button>
					</c:otherwise>
			</c:choose>	
				<button type="button" class="btns btn_prev btn_blue" id="preview" >미리보기</button>
				<button type="button" class="btns btn_prev btn_white" onclick="cancel()">취소</button>
			</div>
		</section>
	</div>
</form:form>


<script type="text/javascript" language="javascript">

$(document).ready(function(){
	// 이미지 4개다 채웠을때 파일첨부 행 없애기
	if($("#imgul").children().size()>=10){
		$("#imgli_0").remove();
	}
});

var j=0;
//행 추가삭제
function insertinput(){
	if($("ul#imgul").children().size()==10){
		alert("소개 이미지는 10개까지 추가 가능합니다.");
		return false;;
	}else{
		$('#imgul').append('<li id="imgli_'+(j+1)+'"><input type="checkbox" id="chart_'+(j+1)+'"/><label for="chart_'+(j+1)+'"><span></span></label> <input type="file" size="50" name="bo_file_'+(j+1)+'" class="write_file" /></li>');
		j++;
	}
	
}

function deleteinput(){
	for(var k=0;k<=j;k++){
		if($("#chart_"+k).prop("checked")) {
		$("#imgli_"+k).remove();
		
		}
	}
}

//전역변수선언
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "san_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["san_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});


// 우편번호 찾기 찾기 화면을 넣을 element
var element_wrap2 = document.getElementById('wrap2');

function foldDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_wrap.style.display = 'none';
}

function foldDaumPostcode2() {
    // iframe을 넣은 element를 안보이게 한다.
    element_wrap2.style.display = 'none';
}
function newaddress() {
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
            document.getElementById('newpostcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('newaddress1').value = fullAddr;
			

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap2.style.display = 'none';

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element_wrap2.style.height = size.height+'px';
        },
        width : '100%',
        height : '100%'
    }).embed(element_wrap2);

    // iframe을 넣은 element를 보이게 한다.
    element_wrap2.style.display = 'block';
}

//textArea에 이미지 첨부
function pasteHTML(filepath){
  var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
  //oEditors.getById["san_content"].exec("PASTE_HTML", [sHTML]);
 
}

$("#industy_fsubmit").click(function(){	
	oEditors.getById["san_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	/*
	var ischk1 = false;
	var chk1 = document.getElementsByName("agreeing");
	
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	
	if(!ischk1){
		alert("약관에 동의해주세요.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}
	*/
	
	 if($('#san_name').val()==''){
		 alert("상호명을 입력해주세요.");
		 return false;
	 }
	 if($('#san_buisnessman').val()==''){
		 alert("대표자를 입력해주세요.");
		 return false;
	 }	 
	 if($.trim($('#san_name').val())==$.trim($('#san_buisnessman').val())){
		 alert("상호명과 대표자명을 다르게 입력해주세요.");
		 return false;
	 }
	 
	 var tel1=$('#san_tel1').val();
	 var tel2=$('#san_tel2').val();
	 var tel3=$('#san_tel3').val();
	 if(tel1=='' && (tel2!='' && tel3!='')){
		 $('#san_tel').val(tel2+'-'+tel3);
	 }else if(tel2!='' && tel3==''){
		 alert('올바른 전화번호를 입력해주세요.');
		 return false;
	 }else if(tel2=='' && tel3!=''){
		 alert('올바른 전화번호를 입력해주세요.');
		 return false;
	 }else if(tel1!='' && tel2!='' && tel3!=''){
		 $('#san_tel').val(tel1+'-'+tel2+'-'+tel3);
	 }
	 
	 var phone1=$('#co_hphone1').val();
	 var phone2=$('#co_hphone2').val();
	 var phone3=$('#co_hphone3').val();
	 if(phone2!='' && phone3!=''){
		 $('#san_phone').val(phone1+'-'+phone2+'-'+phone3);
	 }
	
	 /*
	 if($('#san_homepage').val()==''||$('#san_homepage').val()=='http://' ){
		 alert("홈페이지를 입력해주세요");
		 return false;
	 }
	 */
	 
	 if($('#newpostcode').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 if($('#newaddress1').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 var san_addr =	$('input[name=san_address2]').val() + $('input[name=san_address3]').val();
		$('input[name=san_address]').val(san_addr);	
	
	 if(document.getElementById("san_content").value==''){
	    	alert('회사소개를 입력해주세요.');
	    	return false;   	
	 } 
	 
	 if(!$('#mimg').val()){
		 alert('회사로고를 등록해주세요.');
		 return false;
	 }
	 
	/*
	 var recaptcha = document.getElementById("g-recaptcha-response").value;

	if (recaptcha.length < 1) {
		alert('자동등록방지를 눌러주세요.');
		return false;
	} else {
		$('input[name=recaptcha]').val(recaptcha);
	}
	*/
	window.name = "parent_window";
	$('#save_status').val('r');
	$("#supform").attr("target","parent_window");	
	$("#supform").attr("action","/info/m/insertCorpSanupPreview.do");
	$('#supform').submit();
});


$("#preview").click(function(){
	oEditors.getById["san_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	 /*
	var ischk1 = false;
	var chk1 = document.getElementsByName("agreeing");
	
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	
	if(!ischk1){
		alert("약관에 동의해주세요.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}
	*/
	
	 if($('#san_name').val()==''){
		 alert("상호명을 입력해주세요.");
		 return false;
	 }
	 if($('#san_buisnessman').val()==''){
		 alert("대표자를 입력해주세요.");
		 return false;
	 }	 
	 if($.trim($('#san_name').val())==$.trim($('#san_buisnessman').val())){
		 alert("상호명과 대표자명을 다르게 입력해주세요.");
		 return false;
	 }
	 var tel1=$('#san_tel1').val();
	 var tel2=$('#san_tel2').val();
	 var tel3=$('#san_tel3').val();
	 if(tel1=='' && (tel2!='' && tel3!='')){
		 $('#san_tel').val(tel2+'-'+tel3);
	 }else if(tel2!='' && tel3==''){
		 alert('올바른 전화번호를 입력해주세요.');
		 return false;
	 }else if(tel2=='' && tel3!=''){
		 alert('올바른 전화번호를 입력해주세요.');
		 return false;
	 }else if(tel1!='' && tel2!='' && tel3!=''){
		 $('#san_tel').val(tel1+'-'+tel2+'-'+tel3);
	 }
	 
	 var phone1=$('#co_hphone1').val();
	 var phone2=$('#co_hphone2').val();
	 var phone3=$('#co_hphone3').val();
	 if(phone2!='' && phone3!=''){
		 $('#san_phone').val(phone1+'-'+phone2+'-'+phone3);
	 }
	 
	 /*
	 if($('#san_homepage').val()==''||$('#san_homepage').val()=='http://' ){
		 alert("홈페이지를 입력해주세요");
		 return false;
	 }
	 */
	 
	 if($('#newpostcode').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 if($('#newaddress1').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 var san_addr =	$('input[name=san_address2]').val() + $('input[name=san_address3]').val();
		$('input[name=san_address]').val(san_addr);	
	
	 if(document.getElementById("san_content").value==''){
	    	alert('회사소개를 입력해주세요.');
	    	return false;   	
	 } 
	 
	 if(!$('#mimg').val()){
		 alert('회사로고를 등록해주세요.');
		 return false;
	 }
	 
	/*
	 var recaptcha = document.getElementById("g-recaptcha-response").value;

	if (recaptcha.length < 1) {
		alert('자동등록방지를 눌러주세요.');
		return false;
	} else {
		$('input[name=recaptcha]').val(recaptcha);
	}
	*/
	
	window.name = "parent_window";
	$('#save_status').val('w');
	var openwin = window.open('','preview', 'width=1400, height=800, scrollbars=yes, resizable=yes');
	$("#supform").attr("target","preview");			
	$("#supform").attr("action","/info/m/insertCorpSanupPreview.do");
	$('#supform').submit();
	openwin.focus();
});

function cancel(){
	var url = "/info/industry/m/list.do";
	location.href=url;
}


</script>
<%@include file="../../layout/m/tail.jsp"%>