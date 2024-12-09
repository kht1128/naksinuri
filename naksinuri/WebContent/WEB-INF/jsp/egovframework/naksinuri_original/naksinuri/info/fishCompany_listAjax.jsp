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
<input type="hidden" id="adres_la" name="adres_la" value="${item.adres_la}" />
<input type="hidden" id="adres_lo" name="adres_lo" value="${item.adres_lo}" />
<c:if test="${addWebLink eq 'fishCompany'}">
	<div id="fishjob">
		<div class="fishComBox">
			<div class="modal-header">
				<div class="mbBtnFold">
					<button type="button"></button>
				</div>
				<h1 class="modal-title update">최근 업데이트
					<fmt:parseDate value="${fn:replace(item.upd_date, '.0', '')}" var="parse_upd_date" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:parseDate value="${fn:replace(item.reg_date, '.0', '')}" var="parse_reg_date" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:formatDate value="${parse_upd_date}" pattern="yyyy-MM-dd HH:mm:ss" var="UPD_DATE"/>
					<fmt:formatDate value="${parse_reg_date}" pattern="yyyy-MM-dd HH:mm:ss" var="REG_DATE"/>
					<c:choose>
						<c:when test="${empty item.upd_date}">
							${REG_DATE}
						</c:when>
						<c:otherwise>
							${UPD_DATE}
						</c:otherwise>
					</c:choose>
					<button type="button" id="close_id" class="close" data-dismiss="modal" aria-label="Close" onclick="closeInfoModal('${item.nak_id}')">
					<span aria-hidden="true">×</span>
					</button>
				</h1>
				<div class="fishCom">
					<h2 class="">${item.co_nm}</h2>
					<div class="cate">
						<span>[
							<c:choose>
								<c:when test="${item.searchBoat eq 'boatfishing'}">선상낚시</c:when>
								<c:when test="${item.fishing_type eq 'seafishing'}">바다낚시</c:when>
								<c:otherwise>민물낚시</c:otherwise>
							</c:choose> 
						]</span>
						<span>${item.co_ship_num1}-${item.co_ship_num2}</span>
				    </div>
				</div>
			</div>
			<!-- <img class="fishComImg" src="/naksinuri_original/common_main/img/fishCompany/exImg.png" alt="상호명"> -->
			<a href="#;"onclick="nak_sub('${item.nak_id}')" class="pic">
				<c:choose>
					<c:when test="${item.orignl_file_nm ne null }">
						<img class="fishComImg" src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.co_mimgsrc}"/>&fileSn=<c:out value="${item.file_sn }"/>' alt='<c:out value="${item.co_nm }"/>' />
					</c:when>
					<c:otherwise>
						<img class="fishComImg" alt="낚시누리" src="/naksinuri_original/common_main/img/fishCompany/exImg.png">
					</c:otherwise>
				</c:choose>
			</a>
			
			<div class="infoBox1">
				<dl>
					<dt>
						<img src="/naksinuri_original/common_main/img/fishCompany/ico_info1.png" alt="대표자">
					</dt>
					<dd>${item.ceo_nm}</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info2.png" alt="휴대전화"></dt>
					<dd>
						<c:if test="${not empty item.co_hphone}">${item.co_hphone}</c:if>
						<c:if test="${empty item.co_hphone}">${item.co_2hphone}</c:if>
					</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info3.png" alt="사업장주소"></dt>
					<dd>${item.co_addr2_2}</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info4.png" alt="이용시간"></dt>
					<dd>${item.co_stm} ~ ${item.co_etm}</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info5.png" alt="수용인원"></dt>
					<dd>${item.bo_size} t / ${item.bo_spd} knot / ${item.bo_psg} 명</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info6.png" alt="카드결제"></dt>
					<dd>
						<c:choose>
							<c:when test="${item.co_credit eq '0'}">
								카드 결제 불가
							</c:when>
							<c:otherwise>
								카드 결제 가능
							</c:otherwise>
						</c:choose>
					</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info7.png" alt="홈페이지"></dt>
					<dd><a href="http://haemaho.sunsang24.com" target="_blank">${item.co_web}</a></dd>
				</dl>
			</div>
			
			<div class="infoBox2">
				<dl>
					<dt>· 주요어종</dt>
					<dd>${fn:replace(item.co_fish, '2', '')}</dd>
				</dl>
				<dl>
					<dt>· 주요시설</dt>
					<dd>
						<ul class="faciList">
							<c:forTokens items="${item.co_fct}" delims="," var="item2" >
								<li>${item2}</li>
							</c:forTokens>
						</ul>
					</dd>
				</dl>
				<dl>
					<dt>· 오시는 길</dt>
					<dd>
						<div id="map1" style="width:256px; height:256px;margin-top:8px;position:relative;overflow:hidden;"></div>
					</dd>
				</dl>				
			</div>
			<ul class="btnBox">
				<li><a href="#" class="blueBtn" onclick="go_review()">낚시터 후기</a></li>
				<li><a href="#" class="redBtn" onclick="fishjob_report()">잘못된 정보 신고</a></li>
			</ul>
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
				<h1 class="modal-title update">최근 업데이트
					<fmt:parseDate value="${fn:replace(item.san_update, '.0', '')}" var="parse_upd_date" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:parseDate value="${fn:replace(item.san_date, '.0', '')}" var="parse_reg_date" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:formatDate value="${parse_upd_date}" pattern="yyyy-MM-dd HH:mm:ss" var="UPD_DATE"/>
					<fmt:formatDate value="${parse_reg_date}" pattern="yyyy-MM-dd HH:mm:ss" var="REG_DATE"/>
					<c:choose>
						<c:when test="${empty item.san_update}">
							${REG_DATE}
						</c:when>
						<c:otherwise>
							${UPD_DATE}
						</c:otherwise>
					</c:choose>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeInfoModal('${item.san_sn}')">
					<span aria-hidden="true">×</span>
					</button>
				</h1>
				<div class="fishCom">
					<h2 class="">${item.san_name}</h2>
					<div class="cate">
						<span>[
							<c:choose>
								<c:when test="${item.san_aag eq '협회/기관/단체'}">협회/기관/단체</c:when>
								<c:when test="${item.san_zogu eq '조구업체'}">조구업체</c:when>
								<c:when test="${item.san_media eq '미디어'}">미디어</c:when>
								<c:when test="${item.san_sell eq '판매점'}">판매점</c:when>
								<c:when test="${item.san_chool eq '출조점'}">출조점</c:when>
								<c:otherwise>낚시정보제공</c:otherwise>
							</c:choose> 
						]</span>
				    </div>
				</div>
			</div>					
		            
			<!-- <img class="fishComImg" src="/naksinuri_original/common_main/img/fishCompany/exImg.png" alt="상호명"> -->
			<a href="#;"onclick="nak_sub('${item.san_sn}')" class="pic">
				<c:choose>
					<c:when test="${item.orignl_file_nm ne null }">
						<img class="fishComImg" src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.san_img}"/>&fileSn=<c:out value="${item.file_sn }"/>' alt='<c:out value="${item.san_name }"/>' />
					</c:when>
					<c:otherwise>
						<img class="fishComImg" alt="낚시누리" src="/naksinuri_original/common_main/img/fishCompany/exImg.png" >
					</c:otherwise>
				</c:choose>
			</a>
			
			<div class="infoBox1">
				<dl>
					<dt>
						<img src="/naksinuri_original/common_main/img/fishCompany/ico_info1.png" alt="대표자">
					</dt>
					<dd>${item.san_buisnessman}</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info2.png" alt="휴대전화"></dt>
					<dd>
						<c:if test="${not empty item.san_tel}">${item.san_tel}</c:if>
						<c:if test="${empty item.san_tel}">${item.san_phone}</c:if>
					</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info3.png" alt="사업장주소"></dt>
					<dd>${item.san_address2}</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info6.png" alt="정보"></dt>
					<dd>
						많은 이용 부탁드립니다.
					</dd>
				</dl>
				<dl>
					<dt><img src="/naksinuri_original/common_main/img/fishCompany/ico_info7.png" alt="홈페이지"></dt>
					<dd><a href="http://haemaho.sunsang24.com" target="_blank">${item.san_homepage}</a></dd>
				</dl>
			</div>
			
			<div class="infoBox2">
				<dl>
					<dt>· 사업영역</dt>
					<dd>${fn:replace(item.san_item, '2', '')}</dd>
				</dl>
				<dl>
					<dt>· 오시는 길</dt>
					<dd>
						<div id="map1" style="width:256px; height:256px;margin-top:8px;position:relative;overflow:hidden;"></div>
					</dd>
				</dl>
			</div>
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
	var mapContainer = document.getElementById('map1'); // 지도를 표시할 div 
	
	mapOption = { 
	  center: new kakao.maps.LatLng(37.5666805, 126.9784147), // 지도의 중심좌표
	  level: 3 // 지도의 확대 레벨
		}; 
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	
	// 마커가 표시될 위치입니다 
	var adres_la = $("#adres_la").val();
	var adres_lo = $("#adres_lo").val();
	
	var position  = new kakao.maps.LatLng(adres_la, adres_lo); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: position
	});
	
	setTimeout(function(){
		marker.setMap(map);
		map.setCenter(position);
	}, 100);
	


</script>

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
