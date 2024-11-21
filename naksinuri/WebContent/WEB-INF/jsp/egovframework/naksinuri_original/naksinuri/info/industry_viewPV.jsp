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

<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

<style>
#map_wrap {position:relative;/*width:100%;height:500px;*/}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:320px;margin:10px 0px 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item span {display: block;margin-top:4px;}
#placesList  {display: inline;margin-top:4px;}

#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
</style>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/e59ba62350.js"></script> 
<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
<script src="/naksinuri_original/common_main/js/common.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=kSzk_875XFgPsLskTEKN&submodules=geocoder"></script>

<script src="/naksinuri_original/common_main/js/jquery.sticky.js"></script>

<script>
	$(document).ready(function(){
		/* 낚시산업정보 상세페이지 기본정보 스크롤 (Feat.sticky-kit) */
		$("#businessView .leftconBox").stick_in_parent()
		.on("sticky_kit:stick", function(e) {
			$('#basicInfo').addClass("scrollOn");
		})
		.on("sticky_kit:unstick", function(e) {
			$('#basicInfo').removeClass("scrollOn");
		});
		
		$("#opener_save").click(function(){
			if(confirm('위 정보로 데이터를 저장하시겠습니까?')) {
				var san_sn = $('#san_sn', opener.document).val();
				$('#save_status', opener.document).val('r');
				$("#supform", opener.document).attr("target","parent_window");			
				$("#industy_fsubmit", opener.document).click();
				self.window.close();
			}
		});
	});
</script>

<div id="container">

	<input type="hidden" value="${preview.san_address2}" id="addresse2"/>
	<input type="hidden" value="${preview.san_name}" id="san_name"/> 

	<div id="businessView" class="content respon2 floats">

		<center><div style="font-size: 48px; font-weight: bold; letter-spacing: -0.5pt; font-family: NanumSquare; padding-bottom: 20px;">미리보기</div></center>

		<div class="leftconBox">
			<section id="basicInfo">
				<div class="business_logo">
					<c:choose>
						<c:when test="${preview.orignl_file_nm ne null}">
							<img src='<c:url value='/naksinuri_original/cmm/fms/getImagePreview.do'/>?atchFileId=<c:out value="${preview.san_img}"/>&fileSn=<c:out value="${preview.file_sn}"/>' alt=<c:out value="${preview.orignl_file_nm}"/> />
						</c:when>
						<c:when test="${info.orignl_file_nm ne null}">
							<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.san_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' alt=<c:out value="${info.orignl_file_nm}"/> />
						</c:when>
						<c:otherwise>
							<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="business_info">
					<h1>${preview.san_name}</h1>
					<div class="info">
						<dl><dt>상호명</dt><dd>${preview.san_name}</dd></dl>
						<dl><dt>대표자</dt><dd>${preview.san_buisnessman}</dd></dl>
						<dl><dt>일반전화</dt><dd>${preview.san_tel}</dd></dl>
						<dl><dt>휴대전화</dt><dd>${preview.san_phone}</dd></dl>
						<dl><dt>사업영역</dt><dd>${preview.san_item}</dd></dl>
						<dl><dt>홈페이지</dt><dd><a href="${preview.san_homepage}" target="_blank">${preview.san_homepage}</a></dd></dl>
						<dl><dt>사업장주소</dt><dd>${preview.san_address}</dd></dl>
					</div>
				</div>
			</section>
		</div>

		<div class="rightconBox">
			<div class="viewTit">
				<h1 class="tit">${preview.san_name}</h1>
				<div class="name_date">${preview.san_item}</div>
				<!--  
				<ul class="eyes_heart floats">

					<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
					<li><a href="#;"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
					 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> 
				</ul>
				-->
			</div>
			
			<section id="majorComment">
				<div class="businessSlide">
					<ul>
						<c:forEach var="simges" items="${prefilelist}" varStatus="status">
							<li style="text-align:center;background-color:#f3f3f3"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${simges.p_atch_file_id}"/>&fileSn=<c:out value="${simges.p_file_sn}"/>' alt='<c:out value="${simges.p_orignl_file_nm}"/>' style="width:auto;height:540px;margin:0 auto;" /></li>
						</c:forEach>
					</ul>
					<script>
						$('.businessSlide ul').bxSlider({
							auto: true,
							controls: false,
							pause: 3000
						});
					</script>
				</div>
				<div class="comment">
					${preview.san_content}
				</div>
			</section>

			<section id="mapArea">
				
				<div class="tabArea">
					<ul class="floats">
						  <li class="on"><a href="#;"><i class="fa fa-map-marker" aria-hidden="true"></i> 오시는길</a></li>
						<!--<li><a href="#;"><i class="fa fa-flag" aria-hidden="true"></i> 축제/행사</a></li>
						<li><a href="#;"><i class="fa fa-map" aria-hidden="true"></i> 관광지</a></li>
						<li><a href="#;"><i class="fa fa-cutlery" aria-hidden="true"></i> 음식점</a></li>
						<li><a href="#;"><i class="fa fa-shopping-bag" aria-hidden="true"></i> 쇼핑</a></li>-->
					</ul>
				</div>
				
				<div id="map"class="mapbox" style="width:100%;height:450px;"></div>
			</section>
<%--
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
						<a href="/info/industry/list.do" class="btn_list btn_gray">목록</a>
					</li>
				</ul>
			</div>
--%>
			<div id="btnArea">
				<button type="button" class="btn_review btn_blue" id="opener_save" >등록 신청</button>
				<button type="button" onclick="self.window.close();" class="btn_review btn_blue">취소</button>
			</div>
			
		</div>
	</div>

</div>

<script>


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