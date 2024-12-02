<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services,clusterer"></script>
<!-- 낚시터정보 목록 -->
<style>

</style>
<c:if test="${addWebLink eq 'fishCompany'}">
	<div id="fishjob">
		<div class="fishComBox">
			<div class="modal-header">
				<div class="mbBtnFold">
					<button type="button"></button>
				</div>
				<h1 class="modal-title update"> 
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeListModal()">
					<span aria-hidden="true">×</span>
					</button>
				</h1>
			</div>
			<c:forEach var="item" items="${companyMapList}">
				<div class="form-group row">
					<a href="#;"onclick="mapDetailList(${item.nak_id},${item.adres_lo}, ${item.adres_la})">
						<label class="col-md-8 form-control-label">${item.co_nm}</label>
						<div class="col-md-4">
							<c:choose>
								<c:when test="${item.fishing_type eq 'boatfishing'}">
									<span style="color:#394dc0;">선상낚시</span>
								</c:when>
								<c:when test="${item.fishing_type eq 'seafishing'}">
									<span style="color:#009d68;">바다낚시</span>
								</c:when>
								<c:otherwise>
									<span style="color:#3591f3;">민물낚시</span><!-- 359ef3 -> 3591f3 -->
								</c:otherwise>
							</c:choose>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</c:if>
<!-- end 낚시터정보 목록 -->

<!-- 낚시산업정보 목록 -->
<c:if test="${addWebLink eq 'fishSanup'}">
	<div id="industry">
		<div class="fishComBox">
			<div class="modal-header">
				<div class="mbBtnFold">
					<button type="button"></button>
				</div>
				<h1 class="modal-title update">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeListModal()">
					<span aria-hidden="true">×</span>
					</button>
				</h1>
			</div>					
		    <c:forEach var="item" items="${sanupList}">
				<div class="form-group row">
					<a href="#;"onclick="mapDetailList(${item.san_sn},${item.adres_lo}, ${item.adres_la})">
						<label class="col-md-7 form-control-label">${item.san_name}</label>
						<div class="col-md-5">
							<c:choose>
								<c:when test="${item.san_aag eq '협회/기관/단체'}">협회/기관/단체</c:when>
								<c:when test="${item.san_zogu eq '조구업체'}">조구업체</c:when>
								<c:when test="${item.san_media eq '미디어'}">미디어</c:when>
								<c:when test="${item.san_sell eq '판매점'}">판매점</c:when>
								<c:when test="${item.san_chool eq '출조점'}">출조점</c:when>
								<c:otherwise>낚시정보제공</c:otherwise>
							</c:choose>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</c:if>
<!-- end 낚시산업정보 목록 -->
<!-- <script>
var mapContainer = document.getElementById('map1'); // 지도를 표시할 div 

mapOption = { 
  center: new kakao.maps.LatLng(37.5666805, 126.9784147), // 지도의 중심좌표
  level: 3 // 지도의 확대 레벨
	}; 
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
</script> -->


<script>
/* 	$(function(){
		// 마커 클릭 시 상세 정보 열기
		$(".marker").click(function(){
	        if ($(".infoCon2").css("display") == "none") {
	            $(".infoCon2").removeClass("fold");
	            $(".infoCon2").removeClass("open");
	        } else {
	            $(".infoCon2").removeClass("fold");
	            $(".infoCon2").removeClass("open");
	        }
		});
		
		//상세 정보 상단 클릭 시 위로 올림 
		$(".infoCon2 .mbBtnFold").click(function(){
			$(".infoCon2").toggleClass("open");
			if ($(".infoCon2").hasClass("open")) {
		        $(".infoCon2").removeClass("fold");
		    } else {
		    	$(".infoCon2").addClass("fold");
		    }
		});
	}); */
</script>
