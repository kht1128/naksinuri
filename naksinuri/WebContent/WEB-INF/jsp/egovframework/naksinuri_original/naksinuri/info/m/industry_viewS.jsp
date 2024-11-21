<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="pageMode" value="industry"/>
<c:set var="depthNum" value="2" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="industry" />


<%@include file="../../layout/m/head.jsp"%>

	<script src="/naksinuri_original/common_main/js/jquery.sticky.js"></script>
	<input type="hidden" value="${info.san_address2}" id="addresse2"/>
	<input type="hidden" value="${info.san_name}" id="san_name"/> 

	<div id="businessView" class="content respon2 floats">
		<div class="leftconBox">
			<section id="basicInfo">
				<div class="business_logo">
					<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.san_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' alt=<c:out value="${info.orignl_file_nm}"/> />
				</div>
				<div class="business_info">
					<h1>${info.san_name}</h1>
					<div class="info">
						<dl><dt>상호명</dt><dd>${info.san_name}</dd></dl>
						<dl><dt>대표자</dt><dd>${info.san_buisnessman}</dd></dl>
						<dl><dt>일반전화</dt><dd>${info.san_tel}</dd></dl>
						<dl><dt>휴대전화</dt><dd>${info.san_phone}</dd></dl>
						<dl><dt>사업영역</dt><dd>${info.san_item}</dd></dl>
						<dl><dt>홈페이지</dt><dd><a href="${info.san_homepage}" target="_blank">${info.san_homepage}</a></dd></dl>
						<dl><dt>사업장주소</dt><dd>${info.san_address}</dd></dl>
					</div>
				</div>
			</section>
		</div>

		<div class="rightconBox">
			<div class="viewTit">
				<h1 class="tit">${info.san_name}</h1>
				<div class="name_date">${info.san_item}</div>
				<!--  
				<ul class="eyes_heart floats">

					<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
					<li><a href="#;"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
					 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> 
				</ul>
				-->
			</div>
			
			<section id="majorComment">
				<div class="comment">
					${info.san_content}
				</div>
			</section>

			<section id="mapArea">
			<div class="tabArea">
					<ul class="floats">
						<li class="on"><a href="#;"><i class="fa fa-map-marker" aria-hidden="true"></i> 오시는길</a></li>
<!-- 						<li><a href="#;"><i class="fa fa-flag" aria-hidden="true"></i> 축제/행사</a></li> -->
<!-- 						<li><a href="#;"><i class="fa fa-map" aria-hidden="true"></i> 관광지</a></li> -->
<!-- 						<li><a href="#;"><i class="fa fa-cutlery" aria-hidden="true"></i> 음식점</a></li> -->
<!-- 						<li><a href="#;"><i class="fa fa-shopping-bag" aria-hidden="true"></i> 쇼핑</a></li> -->
					</ul>
				</div>
				
		
				<div id="map"class="mapbox" style="width:100%;height:450px;"></div>
			</section>

			<div id="btnArea">
				<ul class="floats">
<!--  
					<li>
						<a href="#;" class="btn_prev btn_white"><i class="fa fa-angle-left" aria-hidden="true"></i> 이전글</a>
						<a href="#;" class="btn_next btn_white">다음글 <i class="fa fa-angle-right" aria-hidden="true"></i></a>
					</li>
-->
					<li class="fr">
<!-- 						<a href="#;" class="btn_report btn_red">잘못된 정보 신고</a> -->
<!-- 						<a href="#;" class="btn_request btn_orange">사업주 정보 변경 요청</a> -->
<!-- 						<a href="/info/industry/m/list.do" class="btn_list btn_gray">목록</a> -->
					</li>
				</ul>
				<a href="#;" onclick="go_modify()" class="btn_review btn_blue">수정하기</a>
				<a href="/info/m/industry/login.do" class="btn_review btn_blue">취소</a>
				
			</div>
		</div>
	</div>

	<form name="findForm" id="findForm" action ="<c:url value='/info/m/findSanupCorp.do'/>" method="post">
		<input type="hidden" id="san_name" name="san_name" value="${info.san_name}" />
		<input type="hidden" id="san_buisnessman" name="san_buisnessman" value="${info.san_buisnessman}" />
	</form>	

<script type="text/javascript">

function go_modify() {
	$('#findForm').submit();
}

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
var address=$('#addresse2').val();

var sanname = $('#san_name').val();


geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === daum.maps.services.Status.OK) {

        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new daum.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new daum.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+sanname+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>

<%@include file="../../layout/m/tail.jsp"%>