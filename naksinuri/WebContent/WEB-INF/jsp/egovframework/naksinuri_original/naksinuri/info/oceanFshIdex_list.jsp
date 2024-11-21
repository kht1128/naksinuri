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
<c:set var="pageName" value="oceanFshIdex" />



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
#map1> img {top:0 !important;left:0 !important;}

.wrapUp {position: absolute;left: 0;bottom: 40px;width: 220px;margin-left: -110px;border-radius: 5px;overflow: hidden;box-shadow: 0 3px 6px 0 rgb(0 0 0 / 12%);}
.wrapDown {position: absolute;left: 0;width: 220px;margin-left: -110px;border-radius: 5px;overflow: hidden;box-shadow: 0 3px 6px 0 rgb(0 0 0 / 12%);}
.wrap .info {width: 220px;background: #fff;}
.info .title {padding: 35px 20px 5px 20px; text-align: center;}
.info.type1 .title {background-color: #fe4c27 !important;}
.info.type2 .title {background-color: #ffb300 !important;}
.info.type3 .title {background-color: #ffe600 !important;}
.info.type4 .title {background-color: #60e640 !important;}
.info.type5 .title {background-color: #57c6ff !important;}
.info .title .top {position: relative; margin-bottom: 5px;}
.info .title .top button {border: 0; background: none; position: absolute; top: 0;}
.info .title .top .prev {left: 0;}
.info .title .top .next {right: 0;}
.info .title .area {display: inline-block; padding: 5px 15px; background-color: #fff; font-weight: 500; font-size: 18px; border-radius: 15px;}
.info .close {position: absolute;top: 5px;right: 5px;color: #000;width: 20px;height: 20px;opacity: .5;text-shadow:0 0 black;font-size:22px;}
.info .close:hover {cursor: pointer;}
.info .body {position: relative;overflow: hidden;}
.info .body dl {display: flex; padding: 5px 20px;}
.info .body dl:nth-child(2n) {background-color: #f3f3f3;}
.info .body dl dt, .info .body dl dd {width: 50%;text-align:center;}
.info .body dl dt {font-weight: 500;}
.info .body dl dt img {margin-right: 8px;}

/* 일단 안 보이게 나중에 수정 */
#topLoca {display: none;}
#map{width:100%;height:calc(100% - 151px);position:relative;overflow:hidden;}
@media(max-width:1280px) {
	#map {height: calc(100% - 110px) !important;}
}


#map:focus { outline: 0px solid transparent; }
</style>

<%@include file="../layout/newHead.jsp"%>

<form:form commandName="naksinuriVO" id="viewform" name="viewform" method="post" >
	<input type="hidden" id="name" name="name" value="" />
	<input type="hidden" id="date" name="date" value="" />
</form:form>

<form:form commandName="supform" id="supform" method="post" >
	<input type="hidden" id="total_score" name="total_score" value="" />
	<div id="fishjobView" class="">	
		<%-- <input type="hidden" name="fishing_type" value="${info.fishing_type}">
		<input type="hidden" name="getco_fish" value="${info.co_fish}">
		<input type="hidden" name="getaddr2_2" id="getaddr2_2"	value="${info.co_addr2_2 }">
		<input type="hidden" name="getpublic_addr" id="getpublic_addr" value="${info.co_addr2_2 }">
		<input type="hidden" name="atch_file_id" value="${info.atch_file_id }">
		<input type="hidden" name="co_nm" value="${info.co_nm}">
		<input type="hidden" id="nak_id" name="nak_id" value="${info.nak_id}">
		<input type="hidden" name="rev_pass" id="rev_pass">
		<input type="hidden" name="rev_sn1" id="rev_sn1"> --%>

		<!-- <section id="map"></section> -->
		<section id="mapArea" style="margin-top: 0;">
			<div id="map_wrap">
				<div id="map" tabindex="-1"></div>
				<!-- <div id="menu_wrap" class="bg_white" style="display:none">
					<ul id="placesList">
					</ul>
				</div> -->
				
				<!-- 컬러 구분 표지판 -->
				<div class="colorSign" style="text-align:center;">
					<ul>
						<li>나쁨</li>
						<li><span class="color1">5</span></li>
						<li><span class="color2">4</span></li>
						<li><span class="color3">3</span></li>
						<li><span class="color4">2</span></li>
						<li><span class="color5">1</span></li>
						<li>좋음</li>
					</ul>
				</div>
				<!-- end 컬러 구분 표지판 -->
				
				<div class="mapLeftMenu type2">
					<!-- custom-select --> 
					<div class="custom-select">
						<div class="selected" tabindex="0" onkeydown="handleKeyDown(event)">
							<div class="selected-value">전체</div>
						</div>
						<!--
						<ul>
							<li class="option" onclick="fnSelectInfs(0);">전체</li>
							<li class="option" onclick="fnSelectInfs(1);">나쁨</li>
							<li class="option" onclick="fnSelectInfs(2);">주의</li>
							<li class="option" onclick="fnSelectInfs(3);">보통</li>
							<li class="option" onclick="fnSelectInfs(4);">안전</li>
							<li class="option" onclick="fnSelectInfs(5);">좋음</li>
						</ul>
						-->
						<ul>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 0);" onclick="fnSelectInfs(0);">전체</li>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 1);" onclick="fnSelectInfs(1);">나쁨</li>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 2);" onclick="fnSelectInfs(2);">주의</li>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 3);" onclick="fnSelectInfs(3);">보통</li>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 4);" onclick="fnSelectInfs(4);">안전</li>
					        <li tabindex="0" class="option" onkeydown="handleKeyDownOption(event, 5);" onclick="fnSelectInfs(5);">좋음</li>
					    </ul>
					</div>
					<!-- end custom-select --> 
					
			        <button type="button" class="btn_fold">정보창 접기</button>
			        
			        <div tabindex="0" class="info infoCon1">
	        			<div class="mapComBox">
			        		<div class="con con1">
			        			<div class="img-box">
			        				<img src="/naksinuri_original/common_main/img/oceanFshIdex/img1.png" alt="">
			        			</div>
				            	<!-- <div class="searchCon">
					                <div class="mapSearch">
					                    <input type="text" class="msearch_input" placeholder="우리 지역 바다 안전지수를 확인해보세요." name="" id="" value="" title="검색어">
					                    <button type=button></button>
					                </div>
					            </div> -->
				            </div>
				            
				            <div class="con con2">
				            	<dl>
				            		<dt>현재 선택 된 지역</dt>
				            		<dd id="selectAreaName">-</dd>
				            	</dl>
				            	<dl style="display:none;">
				            		<div>
				            			<dt>오전</dt>
				            			<dd>-</dd>
				            		</div>
				            		<div>
				            			<dt>오후</dt>
				            			<dd>-</dd>
				            		</div>
				            	</dl>
				            	<dl>
				            		<dt><img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_thermometer.png" alt="">기온 (℃)</dt>
				            		<dd id="selectAreaAirTemp">-</dd>
				            	</dl>
				            	<dl>
				            		<dt><img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_thermometer.png" alt="">수온 (℃)</dt>
				            		<dd id="selectAreaWaterTemp">-</dd>
				            	</dl>
				            	<dl>
				            		<dt><img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_wave.png" alt="">파고 (m)</dt>
				            		<dd id="selectAreaWaveHeight">-</dd>
				            	</dl>
				            	<!-- <dl>
				            		<dt><img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_fish.png" alt="">대상어</dt>
				            		<dd>-</dd>
				            	</dl> -->
				            </div>
				            
				            <div class="con con3">
				            	<!-- toggle-list { -->
								<div class="toggle-list type1">
									<ul>
										<li>
											<!-- toggle-title { -->
											<div tabindex="0" class="toggle-title">
												<img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_good.png" alt="">
												<span>좋음 지역</span>
												<div class="arrow"></div>
											</div>
											<!-- } toggle-title -->
											
											<!-- toggle-content { -->
											<div class="toggle-content">
												<ul class="areaList">
													<c:forEach var="item" items="${goodAreaList}">
														<li class="dot-list">${item.name}</li>
													</c:forEach>
												</ul>
											</div>
											<!-- } toggle-content -->
										</li>
										
										<li>
											<!-- toggle-title { -->
											<div tabindex="0" class="toggle-title">
												<img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_bad.png" alt="">
												<span>나쁨 지역</span>
												<div class="arrow"></div>
											</div>
											<!-- } toggle-title -->
											
											<!-- toggle-content { -->
											<div class="toggle-content">
												<ul class="areaList">
													<c:forEach var="item" items="${badAreaList}">
														<li class="dot-list">${item.name}</li>
													</c:forEach>
												</ul>
											</div>
											<!-- } toggle-content -->
										</li>
									</ul>
								</div>
								<!-- } toggle-list -->
				            </div>
			            </div>
			        </div>
			    </div>
			</div>
		</section>
	</div>
</form:form>
<script>
/* window.onload = function() {
	fnSelectInfs();
} */
// 토글 리스트
$('.toggle-list > ul > li').on('click keydown', function(event) {
    let $this = $(this);

    if (event.type === 'click' || (event.type === 'keydown' && (event.key === 'Enter' || event.keyCode === 13))) {
        $this.toggleClass('active');
        $this.find(' > .toggle-content').toggle();
    }
});

$('.toggle-content').on('click',function(event) {
    event.stopPropagation();
});


// 셀렉트박스 커스텀
if (window.NodeList && !NodeList.prototype.forEach) {
  NodeList.prototype.forEach = Array.prototype.forEach;
}

const selectBoxElements = document.querySelectorAll(".custom-select");

function toggleSelectBox(selectBox) {
  selectBox.classList.toggle("active");
}

function selectOption(optionElement) {
  const selectBox = optionElement.closest(".custom-select");
  const selectedElement = selectBox.querySelector(".selected-value");
  selectedElement.textContent = optionElement.textContent;
}

function handleEnterKey(e) {
  if (e.key === "Enter" || e.keyCode === 13) {
    const targetElement = e.target;
    const isOptionElement = targetElement.classList.contains("option");

    if (isOptionElement) {
      selectOption(targetElement);
    }

    toggleSelectBox(targetElement.closest(".custom-select"));
  }
}

selectBoxElements.forEach(function (selectBoxElement) {
  selectBoxElement.addEventListener("click", function (e) {
    const targetElement = e.target;
    const isOptionElement = targetElement.classList.contains("option");

    if (isOptionElement) {
      selectOption(targetElement);
    }

    toggleSelectBox(selectBoxElement);
  });

  selectBoxElement.addEventListener("keydown", handleEnterKey);
});

document.addEventListener("click", function (e) {
  const targetElement = e.target;
  const isSelect = targetElement.classList.contains("custom-select") || targetElement.closest(".custom-select");

  if (isSelect) {
    return;
  }

  const allSelectBoxElements = document.querySelectorAll(".custom-select");

  allSelectBoxElements.forEach(function (boxElement) {
    boxElement.classList.remove("active");
  });
});
</script>
<script>
	const arrOverlayObjContainer = {};//맵 배치 팝업 모음
	let clusterer;
	const mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	mapOption = { 
	    //center: new kakao.maps.LatLng(35.26500442901402, 125.99105944152339), // 지도의 중심좌표
		center: new kakao.maps.LatLng(36.26500442901402, 127.99105944152339), // 지도의 중심좌표
	    level: 13, // 지도의 확대 레벨
	    disableDoubleClickZoom: true
	};
	let markers = [];
	
	const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	//map.setDraggable(false);
	map.setDraggable(true);
	//map.setZoomable(false);
	map.setZoomable(true);
	//마커를 표시할 위치와 title 객체 배열입니다
	
	if(clusterer != null){
		clusterer.clear();
	}
	// 마커 클러스터러를 생성합니다 
	clusterer = new kakao.maps.MarkerClusterer({
		map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
		averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
		minLevel: 15, // 클러스터 할 최소 지도 레벨 
	});
	
	let listRawData = [];
	function fnSelectInfs(test) {
		
		const form = document.getElementById('supform');
		if(test==0)
			form.total_score.value = '';
		else if(test==1)
			form.total_score.value = '매우나쁨';
		else if(test==2)
			form.total_score.value = '나쁨';
		else if(test==3)
			form.total_score.value = '보통';
		else if(test==4)
			form.total_score.value = '좋음';
		else if(test==5)
			form.total_score.value = '매우좋음';
			
		$.ajax({
			type:"POST",
			url : '/info/oceanFshIdex/listAjax.do',
			data : $('#supform').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			beforeSend: function() {			
				//화면의 높이와 너비를 구합니다.
				const maskHeight = $(document).height();
				const maskWidth  = window.document.body.clientWidth;
				
				//화면에 출력할 마스크를 설정해줍니다.
				const mask       = "<div id='mask' style='position:absolute; z-index:199; background-color:#fff; display:none; left:0; top:0;'></div>";
				let loadingImg = '';
				 
				loadingImg += "<div id='loadingImg'>";
				loadingImg += " <img src='/naksinuri_original/common_main/img/ajax-loader.gif' style='position:absolute; top:50%; left:50%; transform:translate(-50%, -50%); z-index:199;'/>";
				loadingImg += "</div>";  
			 
				//화면에 레이어 추가
				$('#map')
					.append(mask)
					.append(loadingImg)
				   
				//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
				$('#mask').css({
						'width' : maskWidth
						, 'height': maskHeight
						, 'opacity' : '0.5'
				}); 
			 
				//마스크 표시
				$('#mask').show();   
			 
				//로딩중 이미지 표시
				$('#loadingImg').show();
			},		
			success: function(data, status, xhr) {
				$('#mask, #loadingImg').hide(); //로딩중 표시 제거
				//$("#oceanFshIdex_list").val(data.oceanFshIdex_list);
				listRawData = [];
				const oceanFshIdex_list = JSON.parse(data.oceanFshIdex_list);
				for(var i=0; i<oceanFshIdex_list.length; i++) {
					var obj = {};
		 			obj['name'] = oceanFshIdex_list[i].name;
		 			obj['date'] = oceanFshIdex_list[i].date;
		 			obj['totalScore'] = oceanFshIdex_list[i].total_score;
					obj['lat'] = oceanFshIdex_list[i].lat;
					obj['lon'] = oceanFshIdex_list[i].lon;
					obj['addrSeq'] = oceanFshIdex_list[i].name + '_' + i;
	
					listRawData.push(obj); 
				}
				showMap();
			},
			complete : function() { 
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
		
	}	
	// 마커 이미지의 이미지 주소입니다
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker01.png"; //나쁨
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker02.png"; //주의
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker03.png"; //보통
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker04.png"; //안전
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker05.png"; //좋음
	//var imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker.png"; //off
	
	//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
	function closeOverlay(addrSeq) {
	
		var replace_id = addrSeq;
		replace_id = replace_id.split('_');
		
		

		$("#selectAreaName").text("-");
    	$("#selectAreaAirTemp").text("-");
    	$("#selectAreaWaterTemp").text("-");
    	$("#selectAreaWaveHeight").text("-");
	
	
		$('.focus_add').attr('tabindex','0');
		arrOverlayObjContainer[addrSeq].setMap(null);
		
		setTimeout(function(){
			$('#'+replace_id[0]).focus();		
		}, 100);
		
		//$('#id').focus();		
		
		
	}
	
	function handleKeyDown(event) {
        if (event.key === 'Enter' || event.keyCode === 13) {
            // 엔터 키를 눌렀을 때 드롭다운 토글
            toggleDropdown();
        }
    }
	function toggleDropdown() {
        var dropdown = document.querySelector('.custom-select ul');
        dropdown.style.display = (dropdown.style.display === 'none' || dropdown.style.display === '') ? 'block' : 'none';
    }
	
	function handleKeyDownOption(event, index) {
	    if (event.key === 'Enter' || event.keyCode === 13) {
	        fnSelectInfs(index);
	        toggleDropdown();
	        setTimeout(function () {
	                 document.querySelector('.selected').focus();
	        }, 200)
	    }
	}
	
	

    function toggleContent(index) {
        var content = document.querySelectorAll('.toggle-content')[index];
        var listItem = content.closest('li');

        document.querySelectorAll('.toggle-list li').forEach(function (li) {
            li.classList.remove('active');
        });
        content.style.display = (content.style.display === 'none' || content.style.display === '') ? 'block' : 'none';
        
        if (content.style.display === 'block') {
            listItem.classList.add('active');
        }
    }
	
</script>
<script>
	$(function () {
		fnSelectInfs();
	});
	function showMap(){
	    if(clusterer != null){
			clusterer.clear();
		}
		for(var i = 0 ; i < listRawData.length ; i++){
			let item = listRawData[i];
			if(item.lat!=null && item.lat!='') {
				markers = $(item).map(function(i, item){
					let imageSrc = "";
					if(item.totalScore === '매우좋음')
						imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker05_3.png";
					else if(item.totalScore === '좋음')
						imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker04_3.png";
					else if(item.totalScore === '보통')
						imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker03_3.png";
					else if(item.totalScore === '나쁨')
						imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker02_3.png";
					else if(item.totalScore === '매우나쁨')
						imageSrc = "/naksinuri_original/common_main/img/oceanFshIdex/ico_map_marker01_3.png";
					const position = new kakao.maps.LatLng(item.lat, item.lon);
					// 마커 이미지의 이미지 크기 입니다
				    const imageSize = new kakao.maps.Size(24, 35); 
				    
				    // 마커 이미지를 생성합니다    
				   	const imageOption = {alt:item.totalScore}; // 마커이미지의 
				    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption); 
				    
				    // 마커를 생성합니다
				    const marker = new kakao.maps.Marker({
				        map: map, // 마커를 표시할 지도
				        position: position, // 마커를 표시할 위치
				        title : item.name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				        image : markerImage // 마커 이미지 
				    });
				    
				  //console.log(marker);
					$(marker.ca).parent().addClass('focus_add').attr('tabindex','0').css('height','40px').css('width','25px');
					$(marker.ca).parent().attr('id', item.name ); //포커싱
					$(marker.ca).parent().keyup(function(e){
						if(e.keyCode == 13) {
							$('.focus_add').attr('tabindex','-1');
							kakao.maps.event.trigger(marker, 'click');
							
						
						}
					});					    
				    
				    
				    kakao.maps.event.addListener(marker, 'click', function() {
				    	const overlay = makeOverlay({
					    	marker : marker,
					    	name : item.name,
					    	date : item.date,
					    	mapX : item.lat,
					    	mapY : item.lon,
					    	addrSeq : item.addrSeq
			            });
	    				overlay.setPosition(position);
	    		        overlay.setMap(map);
	    		        overlay.setVisible(true);
	    		        Object.keys(arrOverlayObjContainer).forEach(function(k){
	    		            if(k!=item.addrSeq) arrOverlayObjContainer[k].setMap(null);
	    		        });
					    arrOverlayObjContainer[item.addrSeq] = overlay;
	    		    });
				    
				    return marker;
				});
				clusterer.addMarkers(markers);
			}
		}
		
		document.getElementById('map').focus();
	}
	function makeOverlay(targetObj){
		const marker = targetObj.marker;
		const name = targetObj.name;
		const date = targetObj.date;
		const mapX = targetObj.mapX;
		const mapY = targetObj.mapY;
		const addrSeq = targetObj.addrSeq;
		
		const form = document.getElementById('viewform');
		form.name.value = name;
		form.date.value = date;
		
		const markerPosition  = new kakao.maps.LatLng(mapX, mapY);
		let imageSrc = '';// 마커이미지의 주소    
		const imageSize = new kakao.maps.Size(30, 36); // 마커이미지의 크기입니다
		const imageOption = {offset: new kakao.maps.Point(15, 45)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
		let wrapStyle = '';
		if(mapX < '35.26500442901402')
			wrapStyle = 'wrapUp';
		else
			wrapStyle = 'wrapDown';
		
		var overlay;
		
		$.ajax({
			type:"POST",
			url : '/info/oceanFshIdex/infoAjax.do',
			data : $('#viewform').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				const airTemp = data.info.air_temp;
				const waterTemp = data.info.water_temp;
				const waveHeight = data.info.wave_height;
				let type;
				if(data.info.total_score === '매우좋음')
					type = 'type5'
				else if(data.info.total_score === '좋음')
					type = 'type4'
				else if(data.info.total_score === '보통')
					type = 'type3'
				else if(data.info.total_score === '나쁨')
					type = 'type2'
				else if(data.info.total_score === '매우나쁨')
					type = 'type1'

		    	$("#selectAreaName").text(name);
		    	$("#selectAreaAirTemp").text(airTemp);
		    	$("#selectAreaWaterTemp").text(waterTemp);
		    	$("#selectAreaWaveHeight").text(waveHeight);
				
		    	const content = '<div class="wrap ' + wrapStyle + '" id="' + addrSeq + '">' +  
				  '		<div class="info ' + type +'">' + 
				  '        <div class="title">' + 
				  '            <div tabindex="0" class="close" onkeydown="if(event.keyCode === 13) { closeOverlay(\'' + addrSeq + '\'); }" onclick="closeOverlay(\'' + addrSeq + '\')" title="닫기"><span aria-hidden="true">×</span></div>' +
				  '        	 <div class="top">'	+
				  '            	<button type="button" class="prev" onclick="prev()" title="이전 일자"><i class="xi-angle-left"></i></button>' + 
				  '            	<button type="button" class="next" onclick="next()" title="다음 일자"><i class="xi-angle-right"></i></button>' + 
				  '            	<span class="date" id="currentDate">' + date + '</span>' + 
				  '        	 </div>'	+
				  '			 <span class="area" id="currentName">' + name +'</span>' + 
				  '        </div>' + 
				  '        <div class="body">' + 
				  '        	<dl>' +
				  '				<dt>' +
				  '					<img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_thermometer.png" alt=""> 기온 (℃)' +
				  '				</dt>' +
				  '				<dd id="currentAirTemp">' + airTemp + '</dd>' +
			      '			</dl>' +
				  '        	<dl>' +
				  '				<dt>' +
				  '					<img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_thermometer.png" alt="">수온 (℃)' +
				  '				</dt>' +
				  '				<dd id="currentWaterTemp">' + waterTemp + '</dd>' +
				  '			</dl>' +
				  '        	<dl>' +
				  '				<dt>' +
				  '					<img src="/naksinuri_original/common_main/img/oceanFshIdex/ico_wave.png" alt="">파고 (m)' +
				  '				</dt>' +
				  '				<dd id="currentWaveHeight">' + waveHeight + '</dd>' +
				  '			</dl>' +
				  '        </div>' + 
				  '    </div>' +    
				  '</div>';

			    // 마커 위에 커스텀오버레이를 표시합니다
			    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
			    overlay = new kakao.maps.CustomOverlay({
						content: content,
						map: map,
						position: markerPosition,
						clickable : true,
						xAnchor: 0.5, // 컨텐츠의 x 위치
						yAnchor: 0, // 컨텐츠의 y 위치
						zIndex: 3
			    });
			     
			    overlay.setVisible(false); //커스텀 오버레이의 표시 여부를 지정
			},
			complete : function() {
				//console.log("complete");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
		return overlay;
	}
	function next(){
		$.ajax({
			type:"POST",
			url : '/info/oceanFshIdex/nextInfoAjax.do',
			data : {
				"date" : $('#currentDate').text(),
				"name" : $('#currentName').text()
			},
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				if(data.error == 1){
					alert('마지막 일자입니다.');
				} else {
					const date = data.info.date;
					const airTemp = data.info.air_temp;
					const waterTemp = data.info.water_temp;
					const waveHeight = data.info.wave_height;
					
					$('.info').removeClass('type1');
					$('.info').removeClass('type2');
					$('.info').removeClass('type3');
					$('.info').removeClass('type4');
					$('.info').removeClass('type5');
					
					if(data.info.total_score === '매우좋음')
						$('.info').addClass('type5');
					else if(data.info.total_score === '좋음')
						$('.info').addClass('type4');
					else if(data.info.total_score === '보통')
						$('.info').addClass('type3');
					else if(data.info.total_score === '나쁨')
						$('.info').addClass('type2');
					else if(data.info.total_score === '매우나쁨')
						$('.info').addClass('type1');
					
					$('#currentDate').text(date);
					$('#currentAirTemp').text(airTemp);
					$('#currentWaterTemp').text(waterTemp);
					$('#currentWaveHeight').text(waveHeight);
			    	$("#selectAreaAirTemp").text(airTemp);
			    	$("#selectAreaWaterTemp").text(waterTemp);
			    	$("#selectAreaWaveHeight").text(waveHeight);
				}
			},
			complete : function() {
				//console.log("complete");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
	}
	
	function prev(){
		$.ajax({
			type:"POST",
			url : '/info/oceanFshIdex/prevInfoAjax.do',
			data : {
				"date" : $('#currentDate').text(),
				"name" : $('#currentName').text()
			},
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				//console.log(data.error);
				if(data.error == 1){
					alert('처음 일자입니다.');
				} else {
					const date = data.info.date;
					const airTemp = data.info.air_temp;
					const waterTemp = data.info.water_temp;
					const waveHeight = data.info.wave_height;
					
					$('.info').removeClass('type1');
					$('.info').removeClass('type2');
					$('.info').removeClass('type3');
					$('.info').removeClass('type4');
					$('.info').removeClass('type5');
					
					if(data.info.total_score === '매우좋음')
						$('.info').addClass('type5');
					else if(data.info.total_score === '좋음')
						$('.info').addClass('type4');
					else if(data.info.total_score === '보통')
						$('.info').addClass('type3');
					else if(data.info.total_score === '나쁨')
						$('.info').addClass('type2');
					else if(data.info.total_score === '매우나쁨')
						$('.info').addClass('type1');
					
					$('#currentDate').text(date);
					$('#currentAirTemp').text(airTemp);
					$('#currentWaterTemp').text(waterTemp);
					$('#currentWaveHeight').text(waveHeight);
			    	$("#selectAreaAirTemp").text(airTemp);
			    	$("#selectAreaWaterTemp").text(waterTemp);
			    	$("#selectAreaWaveHeight").text(waveHeight);
				}
			},
			complete : function() {
				//console.log("complete");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
	}
</script>

<%-- <%@include file="../layout/tail.jsp"%> --%>
