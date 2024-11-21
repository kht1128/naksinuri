<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<html lang="ko">
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="plocation" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>
<form  id="imform" method="post" action="./insert_data.do" >
<input type="hidden" name="x_sn" id="x_sn" value="${info.x_sn}" />
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시 금지구역 추가</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>구분</th>
								<td>
									<input type="radio" checked name="x_category" value=1<c:choose><c:when test="${info.x_category eq 1}"> checked </c:when></c:choose>>낚시 금지구역</input>
									<input type="radio" name="x_category" value=2<c:choose><c:when test="${info.x_category eq 2}"> checked </c:when></c:choose>>낚시 제한구역</input>
								</td>
							</tr>							
							<tr>
								<th>명 칭</th>
								<td><input type="text" name="x_name" id="x_name" size="500px" class="frm_input" value="${info.x_name}"  /></td>
							</tr>
							<tr>
								<th>지정기간</th>
								<td><input type="text" name="x_term" id="x_term" class="frm_input" value="${info.x_term}"/> ~ <input type="text" name="x_end" id="x_end" class="frm_input" value="${info.x_end}"/></td>
							</tr>
							<tr>
								<th>지정범위</th>
								<td><input type="text" name="x_rang" id="x_rang" size="500px" class="frm_input" value="${info.x_rang}"/></td>
							</tr>
							<tr>
								<th>지정면적</th>
								<td><input type="text" name="x_area" id="x_area" class="frm_input" value="${info.x_area}" />㎢</td>
							</tr>		
							<tr>
								<th>지정권자</th>
								<td><input type="text" name="x_person" id="x_person" class="frm_input" value="${info.x_person}"/></td>
							</tr>
							<tr>
								<th>이수목적</th>
								<td><input type="text" name="x_purpose" id="x_purpose" size="500px" class="frm_input" value="${info.x_purpose}"/></td>
							</tr>
							<tr>
								<th>관련법령</th>
								<td><input type="text" name="x_related" id="x_related" size="500px" class="frm_input" value="${info.x_related}"/></td>
							</tr>

							<tr>
								<th>주소</th>
								<td><input type="text" id="sample3_postcode" name="x_address" readonly class="frm_input" placeholder="우편번호" value="${info.x_address}"/>
									<input type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"><br>

									<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
										<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
									</div>
									<input type="text" id="sample3_address" name="x_address2" readonly class="frm_input" value="${info.x_address2}" placeholder="주소">
									<input type="text" id="sample4_jibunAddress" name="x_address3" readonly class="frm_input" value="${info.x_address3}" placeholder="지번주소">
								</td>
							</tr>
							<tr>
								<th>위도경도</th>
								<td>
									<input type="text" name="x_latitude" id="x_latitude" readonly class="frm_input" value="${info.x_latitude}"/>
									<input type="text" name="x_hardness" id="x_hardness" readonly class="frm_input" value="${info.x_hardness}"/>
									<button type="button" onclick="serch_result();">위도경도 값 가져오기</button>
								</td>
							</tr>												
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
			<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.x_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.x_trash eq '0' && info.x_sn ne null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.x_sn eq null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>		
			</div>
		</div>
	</div>
</form>
	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
</body>
</html>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
$(document).ready(function(){
	$('#x_term').datepicker();
	$('#x_end').datepicker();
})

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
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
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
    
    
</script>
<script>
function serch_result(){
	var geocoder = new daum.maps.services.Geocoder();
	var serch_address = $('#sample3_address').val();
	if(serch_address==''||serch_address==null){
		alert("주소를 입력해주세요");
		return false;
	}else{
		geocoder.addressSearch(serch_address, function(result, status) {
			$('#x_latitude').val(result[0].y);
			$('#x_hardness').val(result[0].x);

		});	
	}
	
}
//전송버튼 클릭이벤트
function submitContents() {
	 if($('#x_sn').val()!=''){
		 
			$("#imform").attr("action","./update_data.do");
			$("#imform").submit(); 
		}else{
			$("#x_sn").attr("name","");
			$("#imform").attr("action","./insert_data.do");
			$("#imform").submit(); 				
		}
}
</script>