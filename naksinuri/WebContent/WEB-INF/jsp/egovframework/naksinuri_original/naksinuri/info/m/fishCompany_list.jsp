<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageMode" value="info" /> 
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishCompany" />

<style>
#container #subGnb, #container #topLoca {
	display: none;
}
#fishjobView #mapArea {
	margin-top: 0;
}
</style>

<%@include file="../../layout/m/head.jsp"%>

<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >
	
	<div id="fishjobView" class="">	
<%-- 		<input type="hidden" name="fishing_type" value="${info.fishing_type}">
		<input type="hidden" name="getco_fish" value="${info.co_fish}">
		<input type="hidden" name="getaddr2_2" id="getaddr2_2"	value="${info.co_addr2_2 }">
		<input type="hidden" name="getpublic_addr" id="getpublic_addr" value="${info.co_addr2_2 }">
		<input type="hidden" name="atch_file_id" value="${info.atch_file_id }">
		<input type="hidden" name="co_nm" value="${info.co_nm}">
		<input type="hidden" id="nak_id" name="nak_id" value="${info.nak_id}">
		<input type="hidden" name="rev_pass" id="rev_pass">
		<input type="hidden" name="rev_sn1" id="rev_sn1">
 --%>
		
		<section id="mapArea">
			<div id="map_wrap">
				<h3 id="map_title" class="blind">오시는길</h3>
				<div id="map" style="width:100%;height:calc(100vh - 100px);position:relative;overflow:hidden;"></div>
				 <div id="menu_wrap" class="bg_white" style="display:none">
				 <ul id="placesList">
				 </ul>
				 </div>
			</div>
		</section>

	
		
</form:form>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services"></script>
<script type="text/javascript">
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	
	mapOption = { 
	      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	      level: 3 // 지도의 확대 레벨
	  }; 
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// 커스텀 오버레이에 표시할 내용입니다     
	// HTML 문자열 또는 Dom Element 입니다 
	var content1 = '<div class ="marker" id="customOverlay1"><span class="mkTxt">선상낚시1</span></div>';
	var content2 = '<div class ="marker" id="customOverlay2"><span class="mkTxt">낚시터1</span></div>';
	
	// 커스텀 오버레이가 표시될 위치입니다 
	var position1 = new kakao.maps.LatLng(33.450701, 126.570667);  
	var position2 = new kakao.maps.LatLng(33.450879, 126.569940);
	
	// 커스텀 오버레이를 생성합니다
	var customOverlay1 = new kakao.maps.CustomOverlay({
	  position: position1,
	  content: content1   
	});
	
	var customOverlay2 = new kakao.maps.CustomOverlay({
	  position: position2,
	  content: content2   
	});
	
	// 지도 확대 또는 축소 이벤트
	kakao.maps.event.addListener(map, 'zoom_changed', function() {
		// 지도의 현재 레벨을 얻어옵니다
	    var level = map.getLevel();
		
		if(4 <= level && level <= 11){ // 레벨 4~11 마커 모양 변경
			$(".marker").addClass("on");
		} else if(1 <= level && level <= 3){
			$(".marker").removeClass("on");
		}
	});
	
	// 커스텀 오버레이를 지도에 표시합니다
	customOverlay1.setMap(map);
	customOverlay2.setMap(map);
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	/*	// 지도를 생성합니다    
	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();
	
	document.getElementById("menu_wrap").style.display="none"
		
	$("#map").empty();
	
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
	geocoder.addressSearch( addr2_2 , function(result, status) {
		
	// 정상적으로 검색이 완료됐으면 
	if (status === daum.maps.services.Status.OK) {
	
	   var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
	// 결과값으로 받은 위치를 마커로 표시합니다
	var marker = new daum.maps.Marker({
	    map: map,
	    position: coords
	});
		
	infowindow.open(map, marker);
	
	// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	map.setCenter(coords);
	} 
		});
	 */
</script>

<%-- <%@include file="../../layout/m/tail.jsp"%> --%>