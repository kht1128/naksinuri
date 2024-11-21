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

<form:form commandName="supform" id="supform" name="supform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="save_status" id="save_status" value="r">
	<input type="hidden" name="client_ipaddr" value="<%=request.getRemoteAddr()%>">
	<input type="hidden" value="${info.san_address2}" name="addresse2" id="addresse2"/>
	<input type="hidden" value="${info.san_sn}" name="san_sn" id="san_sn"/>
	
	<div id="businessWrite" class="content">
		<section id="writeForm" class="write_box">
			<h2>낚시산업정보 등록</h2>
			<div class="agree_box">
				<h3>개인정보 수집 및 활용에 관한 동의</h3>
				<div class="agree_text">
					1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 사이트 회원관리 등<br />
					2. 수집항목 : 이름, 휴대폰번호, 전자우편<br />
					3. 개인정보 보유·이용기간 : 동의일로부터 동의철회(직권해지)시 까지<br />
					4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br /><br />
					동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
				</div><br/>
				<div class="agree_yesorno">
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>&nbsp;&nbsp;&nbsp;&nbsp;
			
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>
				                                                                                                                            
				</div>
			</div>
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
				<dd><input type="text" class="write_input w100" name="san_name" id="san_name" placeholder="예)낚시누리낚시점"  value="${info.san_name}"  /></dd>
			</dl>
			<dl>
				<dt>대표자</dt>
				<dd><input type="text" class="write_input w100" name="san_buisnessman" id="san_buisnessman"  value="${info.san_buisnessman}" placeholder="예)누리지기" /></dd>
			</dl>
			<dl>
				<dt>회사소개</dt>
				<dd><textarea class="write_textarea" rows="10" name="san_content" id="san_content" style="WIDTH: 100%;min-width:270px;" placeholder="예) 1997년 오픈, 국내최대 낚시용품 매장">${info.san_content}</textarea></dd>
			</dl>
			<dl>
				<dt>사업영역</dt>
				<dd><input type="text" class="write_input w100" name="san_item" id="san_item" placeholder="ex) 낚시대 제조, 판매, 출조정보 제공" value="${info.san_item}" /></dd>
			</dl>
			<dl>
				<dt>홈페이지</dt>
				<dd><input type="text" class="write_input w100" name="san_homepage" id="san_homepage" value=<c:choose><c:when test="${info.san_homepage eq null }">"http://"</c:when><c:otherwise>"${info.san_homepage}"</c:otherwise></c:choose>  /></dd>
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
				<dd class="file_input">
					<c:if test="${info.orignl_file_nm ne null }">
					<li>
					<c:out value="${info.orignl_file_nm}"/>&nbsp;<c:out value="[${info.file_size} Byte]"/>
					<input type="hidden" id="is_mimg" value="1" />
					<%--
					<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
	       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${info.san_img}"/>','<c:out value="${info.file_sn}"/>');" />
	       			--%>
	       			</li>
					</c:if>
					<c:if test="${info.orignl_file_nm ne null }">
					<input type="hidden" id="is_mimg" value="2" />
					</c:if>
					<li>
						<input type="file" size="50" class="write_file" id="mimg" name="mimg" accept="image/*" id="mascortimg" />
	       			</li>
	       			<c:if test="${info.orignl_file_nm ne null }">
					<li class="help">
						※ 파일첨부시 대표이미지는 자동으로 교체됩니다.
	       			</li>
	       			</c:if>	
				</dd>
			</dl>
			<dl>
				<dt>소개이미지</dt>
				<dd>
					<div class="file_input">
						<ul id="imgul">
							<c:forEach items="${filelist}" var="item">
								<li>
									<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
					       			<input type="checkbox" id="delcheck_${item.san_simg}_${item.file_sn}" name="delcheck" value="${item.san_simg}_${item.file_sn}" />
					       			<label for="delcheck_${item.san_simg}_${item.file_sn}"><span></span>삭제 ${item.san_simg}_${item.file_sn}</label>
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
				.btns{display:inline-block;height:30px;line-height:28px;padding:0 10px;margin:0 2px;border:1px solid #e0e0e0;background-color:#fff;font-size:14px;}
				.btns.btn_blue{border:1px solid #ed6d00;background-color:#ed6d00;color:#fff}
			</style>
			<c:choose>
					<c:when test="${info.san_sn ne null }">
						<div id="btnArea">
							<button type="button" class="btns btn_request btn_blue" id="industy_fsubmit">수정하기</button>
					</c:when>
					<c:otherwise>
						<dl>
							<dt>자동등록방지</dt>
							<dd><div class="g-recaptcha" data-sitekey="6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-"></div></dd> <!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
							<input type="hidden" name="recaptcha" />
						</dl>
						<div id="btnArea">
							<button type="button" class="btns bgBlue" id="industy_fsubmit">신청</button>
					</c:otherwise>
			</c:choose>	
				<button type="button" class="btns bgBlue" id="preview" >미리보기</button>
				<button type="button" class="btns" onclick="cancel()">취소</button>
			</div>
		</section>
	</div>
</form:form>


<%@include file="../../layout/m/tail.jsp"%>

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
	
	 if($('#san_name').val()==''){
		 alert("상호명을 입력해주세요.");
		 return false;
	 }
	 if($('#san_buisnessman').val()==''){
		 alert("대표자를 입력해주세요.");
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
	 
	 if($('#san_homepage').val()==''||$('#san_homepage').val()=='http://' ){
		 alert("홈페이지를 입력해주세요");
		 return false;
	 }
	 
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
	 
	 if($('#is_mimg').val() == "2" && !$('#mimg').val()){
		 alert('회사로고를 등록해주세요.');
		 return false;
	 }
	 
	if($('#san_sn').val()==''){
		var recaptcha = document.getElementById("g-recaptcha-response").value;
	
		if (recaptcha.length < 1) {
			alert('자동등록방지를 눌러주세요.');
			return false;
		} else {
			$('input[name=recaptcha]').val(recaptcha);
		}
	}
	
	window.name = "parent_window";
	$('#save_status').val('r');
	$("#supform").attr("target","parent_window");	
	$("#supform").attr("action","/info/m/insertCorpSanupPreview.do");
	$('#supform').submit();

 });
 
$("#preview").click(function(){	
	oEditors.getById["san_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	 
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
	
	 if($('#san_name').val()==''){
		 alert("상호명을 입력해주세요.");
		 return false;
	 }
	 if($('#san_buisnessman').val()==''){
		 alert("대표자를 입력해주세요.");
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
	 
	 if($('#san_homepage').val()==''||$('#san_homepage').val()=='http://' ){
		 alert("홈페이지를 입력해주세요");
		 return false;
	 }
	 
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
	 
	 if($('#is_mimg').val() == "2" && !$('#mimg').val()){
		 alert('회사로고를 등록해주세요.');
		 return false;
	 }
	 
	/*  리캡차 임시주석
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
	openwin,focus();
 }); 
 
 
function cancel(){
	var url = "/info/industry/m/list.do";
	location.href=url;
}


</script>