<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<meta name="viewport" content="device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
	<title>낚시누리 낚시산업 등록</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
	<script type="text/javascript" src="<c:url value='/naksinuri_original/js/EgovMultiFile.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/naksinuri_original/js/EgovCalPopup.js'/>" ></script>
	<script type="text/javascript" src="<c:url value="/naksinuri_original/validator.do"/>"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/common.js"></script>
	<!--다음 주소 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
	<!--다음 끝 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
</head>
<body>

<div id="wrapper">
	<header id="header">
		<h1>
			<em>해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.</em>
			<span><b>낚시산업 정보입력</b></span>
		</h1>
	</header>
	
<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >

	
	<div id="container" class="respon">
		<section id="basicform" class="content">
			<h2>기본정보</h2>
			<div class="writeBox">
	
				<dl>
					<dt>구 분</dt>
					<dd>
						<ul class="choicebox floats">
							<li><input type="checkbox" id="san_divsion1" name="san_zogu" value="조구업체" />
							<label for="san_divsion1"><span></span>조구업체</label></li>							
							<li><input type="checkbox" id="san_divsion2" name="san_media" value="미디어"  />
							<label for="san_divsion2"><span></span>미디어</label></li>
							<li><input type="checkbox" id="san_divsion3" name="san_sell" value="판매점"  />
							<label for="san_divsion3"><span></span>판매점</label></li>
							<li><input type="checkbox" id="san_divsion4" name="san_chool" value="출조점"  />
							<label for="san_divsion4"><span></span>출조점</label></li>
							<li><input type="checkbox" id="san_divsion5" name="san_inprov" value="낚시정보제공"  />
							<label for="san_divsion5"><span></span>낚시정보제공</label></li>
						</ul>
					</dd>
				</dl>
		
				<dl>
					<dt>상호명</dt>
					<dd><input type="text" name="san_name" id="san_name" class="naksi_input"/></dd>
				</dl>
				<dl>
					<dt>대표자</dt>
					<dd><input type="text" name="san_buisnessman" id="san_buisnessman" class="naksi_input" /></dd>
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
						<input type="text" class="naksi_input" name="san_tel2" id="san_tel2" maxlength="4" value="${info.co_phone2}" size="7" /> - 
						<input type="text" class="naksi_input" name="san_tel3" id="san_tel3" maxlength="4" value="${info.co_phone3}" size="7" />
						<input type="hidden" name="san_tel" id="san_tel">
					</dd>
				</dl>
				<dl>
					<dt>휴대전화</dt>
					<dd>
						<select class="naksi_select" id="co_hphone1" name="san_phone1">
							<option>010</option>
							<option>011</option>
							<option>016</option>
							<option>017</option>
							<option>019</option>
						</select> - 
						<input type="text" class="naksi_input"  id="co_hphone2" name="san_phone2"  maxlength="4" size="7"  /> - 
						<input type="text" class="naksi_input"  id="co_hphone3" name="san_phone3"  maxlength="4" size="7" />
						<input type="hidden" name="san_phone" id="san_phone">
					</dd>
				</dl>
				<dl>
					<dt>사업영역</dt>
					<dd><input type="text" class="naksi_input" name="san_item" id="san_item"/> ex)낚시 전문 미디어 채널,낚시 커뮤티니 및 선박예약</dd>
				</dl>
				<dl>
					<dt>홈페이지</dt>
					<dd class="multi_input">
						<input type="text" class="naksi_input w100" name="san_homepage" value="http://" /> 
					</dd>
				</dl>
				<dl>
					<dt>회사 소개글</dt>
					<dd><textarea class="naksi_input" name="san_content" id="san_content" style="WIDTH: 100%"> </textarea></dd>
				</dl>
				<!--<dl>
					<dt>구주소</dt>
					<dd class="multi_input">
						<input type="text" class="naksi_input readonly" id="oldpostcode" readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="oldaddress()">우편번호검색</a><br />

						<div id="wrap" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>

						<input type="text" id="oldaddress1" readonly class="naksi_input w100" /><br />
						<input type="text" id="oldaddress2" class="naksi_input w100" placeholder="상세주소"/>
					</dd>
				</dl>
				-->
				<dl>
					<dt>사업장 주소</dt>
					<dd class="multi_input">
						<input type="text" name="san_address1" class="naksi_input readonly" id="sample3_postcode"  readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="sample3_execDaumPostcode()">우편번호검색</a><br />
						
						<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
						<input type="text" id="sample3_address" name="san_address2"  readonly class="naksi_input w100" /><br />
						<input type="text" id="sample3_address2" class="naksi_input w100" placeholder="상세주소"/>
						<input type="hidden" id="san_address3" name="san_address3"/>
						
					</dd>
				</dl>
				<dl>
					<dt>회사 로고</dt>
					<dd style="padding-top:10px;"><input type="file" size="30" name="mimg" accept="image/*" id="mascortimg" style="width:290px" /></dd>
				</dl>
			</div>
			<div class="btnArea">
				<a href="#;" class="submitBtn bgBlue" onclick="industy_submit();" id="insertCorp">등록</a>
				<a href="#;" class="submitBtn" id="cancel">취소</a>
			</div>
		</section>

	
	</div>
</form:form>

	<footer id="footer">
		<div class="respon">
			<h1><img src="${pageContext.request.contextPath}/common/img/logo_foot.png" alt="낚시누리" /></h1>
			<ul class="floats">
				<li>(08588) 서울특별시 금천구 가산디지털2로 53 한라시그마벨리 10층</li>
				<li>TEL : 1833-7139</li>
				<li>상호 : 한국어촌어항공단</li>
				<li>사업자번호 : 220-82-00065</li>
			</ul>
			<p>Copyright(C) 2015 Naksi-Nuri. all rights reserved.</p>
		</div>
	</footer>
</div>
<% %>
</body>
</html>

<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function sample3_execDaumPostcode() {
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
                document.getElementById('sample3_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample3_address').value = fullAddr;

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
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
 function industy_submit(){

	 
	 if($('#san_name').val()==''){
		 alert("상호명을 입력해주세요.");
		 return false;
	 }
	 if($('#san_buisnessman').val()==''){
		 alert("대표자를 입력해주세요.");
		 return false;
	 }
	 if($('#co_hphone2').val()==''){
		 alert("휴대전화를 입력해주세요.");
		 return false;
	 }
	 if($('#co_hphone3').val()==''){
		 alert("휴대전화를 입력해주세요.");
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
	 }else{
		 
	 }
	 
	 var phone1=$('#co_hphone1').val();
	 var phone2=$('#co_hphone2').val();
	 var phone3=$('#co_hphone3').val();
	 $('#san_phone').val(phone1+'-'+phone2+'-'+phone3);
	 
	 if($('#sample3_postcode').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 if($('#sample3_address').val()==''){
		 alert("주소를 입력해주세요.");
		 return false;
	 }
	 var address1=$('#sample3_address').val();
	 var address2=$('#sample3_address2').val();
	 
	 $('#san_address3').val(address1+' '+address2);

	 if($('#mascortimg').val()==''){
		 alert("회사로고를 등록해주세요.");
		 return false;
	 }
	 if(document.getElementById("san_content").value==''){
	    	alert('내용을 입력해주세요.');
	    	return false;   	
	 } 
	 
	$('#supform').attr('action','./ind_insert.do') 
	$('#supform').submit();
	 
 }
</script>

<script>






</script>
