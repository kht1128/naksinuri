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
/* 일단 안 보이게 나중에 수정 */
#topLoca {display: none;}

@media(max-width:1280px) {
	#map {height: calc(100% - 110px) !important;}
}
#searchText1::placeholder {
    color: #636363;
}
#cmpnm_nm::placeholder {
    color: #636363;
}
#companyRegBtn:focus,
#sanupRegBtn:focus {
        outline: 2px solid black; /* 파란색 포커스 링 */
        outline-offset: 2px; /* 포커스 링과 요소 간 간격 */
}
</style>

<%@include file="../layout/newHead.jsp"%>

<form:form commandName="naksinuriVO" id="viewform" name="viewform" method="post" >
<input type="hidden" id="nak_id" name="nak_id" value="" />
</form:form>
<form:form commandName="naksinuriVO" id="mapListForm" name="mapListForm" method="post" >
	<input type="hidden" id="adres_la1" name="adres_la1" value="" />
	<input type="hidden" id="adres_la2" name="adres_la2" value="" />
	<input type="hidden" id="adres_lo1" name="adres_lo1" value="" />
	<input type="hidden" id="adres_lo2" name="adres_lo2" value="" />
</form:form>
<form:form commandName="SanupVO" id="viewform2" name="viewform2" method="post" >
<input type="hidden" id="san_sn" name="san_sn" value="" />
</form:form>
<form:form commandName="SanupVO" id="mapListForm2" name="mapListForm2" method="post" >
	<input type="hidden" id="adres_la1" name="adres_la1" value="" />
	<input type="hidden" id="adres_la2" name="adres_la2" value="" />
	<input type="hidden" id="adres_lo1" name="adres_lo1" value="" />
	<input type="hidden" id="adres_lo2" name="adres_lo2" value="" />
</form:form>

<form:form commandName="supform" id="supform" method="post" >
	<input type="hidden" id="req_sido" value="${searchSido}" />
	<input type="hidden" id="req_gugun" value="${searchGugun}" />
	<input type="hidden" name="fish_list" id="fish_list" value=""/>	
	<input type="hidden" name="addWebLink" id="addWebLink" value=""/>	

	
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
		
		<section id="mapArea" style="margin-top: 0;">
			<div id="map_wrap">
				<h3 id="map_title" class="blind" style="display: none;">오시는길</h3>
				<div id="map" style="width:100%;height:calc(100% - 151px);position:relative;overflow:hidden;"  tabindex=1></div>
				<!-- <div id="menu_wrap" class="bg_white" style="display:none">
					<ul id="placesList">
					</ul>
				</div> -->
				
				<div class="mapLeftMenu">
					<!-- 맵 상단 마커 목록 --> 
					<div class="markerList">
						<!-- 낚시터정보 -->
						<ul class="collapse">
							<li class="markerEx" id="customOverlay1"><span class="mkTxt">선상낚시</span></li>
							<li class="markerEx" id="customOverlay2"><span class="mkTxt">바다낚시</span></li>
							<li class="markerEx" id="customOverlay3"><span class="mkTxt">민물낚시</span></li>
						</ul>
						<!-- 낚시산업정보 -->
						<ul class="collapse2">
							<li class="markerEx" id="customOverlay4"><span class="mkTxt">상호명</span></li>
						</ul>
					</div>
					<!-- end 맵 상단 마커 목록 --> 
					
			        <button tabindex=1 type="button" class="btn_fold">정보창 접기</button>
			        
			        <!-- 정보등록신청 버튼 추가 -->	
					<a tabindex=1 href="/info/fishjob/login.do" id="companyRegBtn" class="redBtnA" title="낚시터정보등록">정보<br>등록</a>
					<a tabindex=1 href="/info/industry/login.do" id="sanupRegBtn" class="redBtnA" title="낚시산업정보등록">정보<br>등록</a>
			        
			        <div class="info infoCon1" >
	        			<div class="mapComBox">
			        		<div class="map_box">
				                <span class="mapCom" >
							                    지도에서 지역을 선택하시면<br>
							                    해당 지역의 낚시터 정보를 확인 할 수 있습니다.
				                </span>
				                <img id="imgMap" src="/naksinuri_original/common_main/img/fishCompany/mapImg.png" alt="전국지도" usemap="#mapChange" >
				                <map name="mapChange" id="mapChange" onclick="fnSelectInfs()">
									<area tabindex=1  shape="poly" coords="95,73,97,89,105,97,110,85,111,77,102,69" href="#" alt="인천" onclick='changSido("인천");'/>
									<area tabindex=1  shape="poly" coords="111,77,119,77,121,70,125,75,127,69,133,76,135,81,135,87,127,90,119,91,111,88" href="#" alt="서울특별시" onclick='changSido("서울");' />
									<area tabindex=1  shape="poly" coords="138,22,144,33,150,29,149,37,157,36,159,45,169,53,163,62,162,77,178,81,181,86,176,90,174,118,139,136,132,131,116,133,107,122,100,111,104,105,111,105,105,94,112,77,120,88,130,93,136,91,138,81,132,69,120,76,96,77,97,60,107,58,107,47,121,38" href="#" alt="경기도" onclick='changSido("경기");' />
									<area tabindex=1  shape="poly" coords="134,21,165,20,178,16,184,22,201,22,217,17,220,1,235,40,260,77,280,127,267,134,241,130,230,131,207,121,192,113,171,117,175,86,162,77,164,64,172,54" href="#" alt="강원도" onclick='changSido("강원");' />
									<area tabindex=1  shape="poly" coords="139,135,154,127,169,119,180,119,186,113,190,118,202,115,210,119,203,123,220,125,231,132,214,142,214,147,208,151,201,145,186,157,171,167,178,172,172,193,184,195,185,201,179,205,170,213,154,208,156,193,149,191,153,178,142,171,136,156,147,149" href="#" alt="충청북도" onclick='changSido("충북");' />
									<area tabindex=1  shape="poly" coords="131,149,137,154,141,165,143,172,135,177,133,166,128,159" href="#" alt="세종시" onclick='changSido("세종");' />
									<area tabindex=1  shape="poly" coords="135,177,134,177,143,169,150,173,152,179,148,189,151,191,143,194,136,194,132,186" href="#" alt="대전광역시" onclick='changSido("대전");' />
									<area tabindex=1  shape="poly" coords="111,136,131,130,143,136,149,150,138,155,130,150,130,160,136,170,136,178,135,185,140,195,152,192,156,197,159,208,148,215,140,209,135,201,121,206,118,199,108,198,103,205,93,210,82,196,86,179,83,153,74,151,77,171,69,170,67,149,61,139,75,125,88,118,101,123" href="#" alt="충청남도" onclick='changSido("충남");' />
									<area tabindex=1  shape="poly" coords="230,133,242,132,248,135,261,132,270,138,278,127,282,136,281,154,285,168,279,177,280,190,275,209,279,217,276,222,288,218,284,232,277,256,264,251,253,248,245,254,232,257,217,253,218,239,226,243,231,226,227,217,209,223,203,234,203,246,187,241,186,228,171,222,171,213,182,207,186,195,174,188,174,169,180,154,198,145,207,150,217,150,219,135" href="#" alt="경상북도" onclick='changSido("경북");' />
									<area tabindex=1  shape="poly" coords="205,228,216,219,230,218,231,228,223,242,215,244,205,247" href="#" alt="대구광역시" onclick='changSido("대구");' />
									<area tabindex=1  shape="poly" coords="246,252,253,244,264,246,272,249,277,255,273,264,267,274,263,279,256,266,244,260" href="#" alt="울산광역시" onclick='changSido("울산");' />
									<area tabindex=1  shape="poly" coords="171,222,179,229,184,229,190,237,188,243,200,246,214,247,229,253,245,250,244,259,260,270,229,290,226,298,221,318,210,326,179,326,163,317,161,300,153,285,147,271,154,258,150,249,159,230" href="#" alt="경상남도" onclick='changSido("경남");' />
									<area tabindex=1  shape="poly" coords="228,289,236,284,244,284,254,273,263,271,265,276,255,290,240,299,231,295" href="#" alt="부산광역시" onclick='changSido("부산");' />
									<area tabindex=1  shape="poly" coords="85,210,102,208,107,204,107,197,119,198,122,205,135,200,143,211,149,213,157,207,165,210,171,210,174,216,162,227,150,250,153,258,147,270,136,269,123,265,114,266,111,253,106,257,98,250,90,259,81,263,74,253,80,247,91,247,76,240,85,231,90,224,97,228,99,218,84,217" href="#" alt="전라북도" onclick='changSido("전북");' />
									<area tabindex=1  shape="poly" coords="87,259,96,252,108,257,113,254,115,266,128,265,137,268,143,264,151,270,152,282,161,297,147,301,150,309,159,306,148,322,139,307,131,316,137,328,126,336,112,328,120,315,104,320,100,333,93,346,83,346,71,343,66,326,57,319,61,293,66,283,63,270,73,252,87,274,94,288,108,284,107,272,94,269" href="#" alt="전라남도" onclick='changSido("전남");' />
									<area tabindex=1  shape="poly" coords="84,278,92,270,103,270,108,278,103,285,92,286" href="#" alt="광주광역시" onclick='changSido("광주");' />
									<area tabindex=1  shape="poly" coords="56,395,67,382,106,375,111,386,105,397,84,405,62,403" href="#" alt="제주도" onclick='changSido("제주");' />
								</map>
				            </div>
				            
							<!-- 낚시터정보, 낚시산업정보 탭 메뉴 -->
				            <div class="mapTabMenu">
				            	<ul>
				            		<li class="mapTabMenu1"><a tabindex=1 href="#" class="mapTabCon1" title="낚시터 정보">낚시터 정보</a></li>
				            		<li class="mapTabMenu2"><a tabindex=1 href="#" class="mapTabCon2" title="낚시 산업 정보">낚시 산업 정보</a></li>
				            	</ul>
				            </div>
				            <!-- end 낚시터정보, 낚시산업정보 탭 메뉴 -->
				            <div class="mapTabCon">
				            	<!-- 낚시터정보 컨텐츠 -->
				            	<div id="mapTabCon1">
				            	<div id="menuContainer">
					            	<div class="mapCate">
						            	<span class="mapCateCom">중복 선택이 가능합니다.</span>
						                <ul>
						                    <li>
						                    	<input tabindex=-1 type='checkbox' name='searchBoat' id="searchBoat" <c:if test="${not empty searchBoat}"> checked</c:if> />
						                		<label tabindex=1 for="searchBoat" onkeypress="if (event.keyCode==13 || event.keyCode==32){ $('#searchBoat').click();}">선상낚시</label>
						                    </li>
						                    <li>
						                    	<input tabindex=-1 type='checkbox' name='searchSea' id="searchSea" <c:if test="${not empty searchSea}"> checked</c:if> /> 
												<label tabindex=1 for="searchSea" onkeypress="if (event.keyCode==13 || event.keyCode==32){ $('#searchSea').click();}">바다낚시</label>
						                    </li>
						                    <li>
						                    	<input tabindex=-1 type='checkbox' name='searchRiver' id="searchRiver" <c:if test="${not empty searchRiver}"> checked</c:if> />
												<label tabindex=1 for="searchRiver" onkeypress="if (event.keyCode==13 || event.keyCode==32){ $('#searchRiver').click();}">민물낚시</label>
						                    </li>
						                </ul>
						            </div>
						
						            <div class="searchCon">
						            	<select tabindex=1 class="search_select" name="sanupSelect" id="sanupSelect" onchange="" title="구분 옵션선택">
						                    <option value="">구분</option>
						                    <option value="san_aag">협회/기관/단체</option>
						                    <option value="san_zogu">조구업체</option>
						                    <option value="san_media">미디어</option>
						                    <option value="san_sell">판매점</option>
						                    <option value="san_chool">출조점</option>
						                    <option value="san_inprov">낚시정보제공</option>
						                </select>
						                <select tabindex=1 class="search_select" name="searchSido" id="searchSido" onchange="selectChange(this)" title="시,도 옵션선택">
						                    
						                </select>
						                <select tabindex=1 class="search_select" name="searchGugun" id="searchGugun" title="구,군 옵션선택">
						                   
						                </select>
						                <div class="mapSearch" id="mapSearchText1"> 
						                    <input tabindex=1 type="text" class="" placeholder="주요 어종을 입력 해주세요. 예) 붕어, 잉어" name="searchText1" id="searchText1" value="${searchText1}" title="주요 어종">
						                </div>
						                <div class="mapSearch">
						                    <input tabindex=1 type="text" class="" placeholder="상호명을 입력해주세요." name="cmpnm_nm" id="cmpnm_nm" value="" title="상호명">
						                </div>
						                
						                <input tabindex=1 type="button" class="searchBtn" onclick="fnSelectInfs()" value="검색하기"/>
						            </div>
					            </div>
					            <!-- end 낚시터정보 컨텐츠 -->

				            </div>
				            </div>
			            </div>
			        </div>
				    <div class="info infoCon2" tabindex=1>
				    </div>
				    <div class="info infoCon3" tabindex=1>
				    </div>
			    </div>
			</div>
		</section>

	
		
</form:form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-rwdImageMaps/1.6/jquery.rwdImageMaps.min.js"></script> 
<script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>



<script type="text/javascript">
var listRawData = [];
var points = [];
var iwContent;
var image;
var nak_id;
var key = 0;
var linkUrl;
var checkFish;
var selectClass;
var number =0;
function fnSelectInfs() {
	
	closeListModal();
	closeInfoModal();
	clusterer.clear();
	//linkUrl = "/info/"+ checkFish +"/listAjax.do";
	
	if(selectClass.indexOf("mapTabCon1") != -1){
		linkUrl = "/info/fishCompany/listAjax.do";	
	} else {
		linkUrl = "/info/fishSanup/listAjax.do";
	}
	
	listRawData = [];
	var update = [];
	key = 0;
	$.ajax({
		type:"POST",
		url : linkUrl,
		data : $('#supform').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		beforeSend: function() {			
			//화면의 높이와 너비를 구합니다.
			var maskHeight = $(document).height();
			var maskWidth  = window.document.body.clientWidth;
			
			//화면에 출력할 마스크를 설정해줍니다.
			var mask       = "<div id='mask' style='position:absolute; z-index:199; background-color:#fff; display:none; left:0; top:0;'></div>";
			var loadingImg = '';
			 
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
		
			$("#fish_list").val(data.fish_list);
 			$("#req_sido").val(data.searchSido);
			$("#req_gugun").val(data.searchGugun);
			$("#addWebLink").val(data.addWebLink); 
			var geocoder = new kakao.maps.services.Geocoder();
			var fish_list = JSON.parse(data.fish_list);
			for(var i=0; i<fish_list.length; i++) {
				var obj = {};
				
	 			obj['co_nm'] = fish_list[i].co_nm;//상호명
				obj['co_addr_2'] = fish_list[i].co_addr_2;//주소
				obj['san_address2'] = fish_list[i].san_address2;//주소
				obj['fishing_type'] = fish_list[i].fishing_type;//선상낚시여부
				obj['san_sn'] = fish_list[i].san_sn;//낚시 산업정보 sn
				obj['san_name'] = fish_list[i].san_name;//낚시 산업정보 상호명
				obj['nak_id'] = fish_list[i].nak_id;//sn
				obj['adres_la'] = null;//위도
				obj['adres_lo'] = null;//경도
				
				if(fish_list[i].adres_la != null && fish_list[i].adres_la != '')
					obj['adres_la'] = fish_list[i].adres_la;//위도
			
				if(fish_list[i].adres_lo != null && fish_list[i].adres_lo != '')	
					obj['adres_lo'] = fish_list[i].adres_lo;//경도

				listRawData.push(obj); 
			} 
			
			if(selectClass.indexOf("mapTabCon1") != -1){ //포함되어 있을때 
				$("#sanupSelect").hide();
				$("#mapSearchText1").show();
				$(".mapCate").show();
				$(".collapse2").css({'visibility':'collapse'});
				$(".collapse").css({'visibility':'visible'});
				$("#companyRegBtn").show();
				$("#sanupRegBtn").hide();
				
				$("#menuContainer h4").remove();
			    $("#menuContainer").prepend('<h4 style="display: none;">낚시터정보</h4>');
			} else {
				$("#sanupSelect").show();
				$("#mapSearchText1").hide();
				$(".mapCate").hide();
				$(".collapse2").css({'visibility':'visible'});
				$(".collapse").css({'visibility':'collapse'});
				$("#companyRegBtn").hide();
				$("#sanupRegBtn").show();
				
				$("#menuContainer h4").remove();
			    $("#menuContainer").prepend('<h4 style="display: none;">낚시산업정보</h4>');
			} 
			
			number=fish_list.length;
			showMap();
			
		/* 	alert(fish_list.length);
				

		 		var length_total =	$('#map').children('div').children('div').children('div').children('div').children('div').length;
			 	
		 		 
		  		for(var n=0; n<=length_total; n++){
		 			
		 			
		 			$('#map').children('div').children('div').children('div').children('div').children('div').eq(n).parent().addClass('focus_add'+n).attr('tabindex',0); 	
		 	 		
		 			$('.focus_add'+n).keyup(function(e){
		 				if(e.keyCode == 13) {
		 					var searchSido = $("#req_sido").val()
		 					alert(searchSido + "지역의 낚시터 정보를 조회합니다.");
		 
		 					mapList2(map.getBounds().ha, map.getBounds().oa, map.getBounds().qa, map.getBounds().pa);

		 				


		 				}
		 			});		 		 			
		 		}							
			
		 */
			
			
	
			
			/* listRawData.forEach(function(item, index) {
				var addrSearch = '충북 단양군';
				geocoder.addressSearch(addrSearch, function(result, status) {
					if (status === kakao.maps.services.Status.OK) {
						var position = new kakao.maps.LatLng(result[0].y, result[0].x);
						var updateStr = "update NAKSINURI set adres_lo = '" + result[0].x + "', adres_la = '" + result[0].y + "' WHERE co_addr_2_2 = '" + addrSearch + "';";
		  					
		  				update.push(updateStr);
		  				console.log(updateStr);
					}
					
				});
			}); */
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

</script>



<script type="text/javascript">
    // rwdImageMaps로 이미지맵 동적 할당하도록 설정
    $('img[usemap]').rwdImageMaps();
    
</script>

<script>
window.onload = function() {
	changSido("경기");
	selectClass = $(".mapTabMenu").find("a").attr("class");
	fnSelectInfs();
}
</script>


<script>
	var clusterer;
	var mapContainer = document.getElementById('map'); // 지도를 표시할 div 

	mapOption = { 
		      center: new kakao.maps.LatLng(37.5666805, 126.9784147), // 지도의 중심좌표
		      level: 10 // 지도의 확대 레벨
		}; 
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	map.setMinLevel(6);

	if(clusterer != null){
		clusterer.clear();
	}
	
	// 마커 클러스터러를 생성합니다 
	clusterer = new kakao.maps.MarkerClusterer({
	    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	    minLevel: 6, // 클러스터 할 최소 지도 레벨 
	    minClusterSize: 1
	}); 
</script>

<script>

var number_chk = 0;
		



	kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
		var level = map.getLevel();
		//console.log(" click clusterer " + level);
		console.log(" level :: " + level);
		if(level <= 6 ){
			console.log(cluster.getBounds().ha);
			console.log(cluster.getBounds().oa);
			console.log(cluster.getBounds().qa);
			console.log(cluster.getBounds().pa);
			mapList(cluster.getBounds().ha, cluster.getBounds().oa, cluster.getBounds().qa, cluster.getBounds().pa);
		}
	});
	kakao.maps.event.addListener(map, 'zoom_changed', function() {
		// 지도의 현재 레벨을 얻어옵니다
	    var level = map.getLevel();
		//console.log(" level :: " + level);
	});
	
	
	function showMap(){
	

	
	  		
	  		
	  		
	  		

	 					

		var markers = [];
		var customOverlays = [];
		
 		if(key == 0){
			clusterer.clear();
		}
 		
 	

 
		for(var i=key; i<100+key;i++) {
			
		
	
		

			var next = i;
			if(next >= listRawData.length) continue;
			var item = listRawData[next];
			
			
			var addWebLink = $("#addWebLink").val();
			//console.log("next ::::::: >> " + next);
			// 주소로 좌표를 검색합니다
			// 정상적으로 검색이 완료됐으면 

			if(item.adres_la!=null && item.adres_la!='') {
				
			
				if(addWebLink == 'fishCompany'){
					if(item.fishing_type == 'boatfishing'){
						iwContent = '<div class ="marker" id="customOverlay1" onclick="mapDetailList(' + item.nak_id + ',' + item.adres_lo + ',' + item.adres_la + ')"><span class="mkTxt">' + item.co_nm + '</span></div>';
						image = '/naksinuri_original/common_main/img/fishCompany/ico_map_marker01.png';	
						
			 			
/* 			 			$('#map').children('div').children('div').children('div').children('div').addClass('focus_add').attr('tabindex',0); 	
			 	 		
			 			$('.focus_add').keyup(function(e){
			 				if(e.keyCode == 13) {
			 					
			 					mapList(map.getBounds().ha, map.getBounds().oa, map.getBounds().qa, map.getBounds().pa);
			 					mapDetailList(item.nak_id,item.adres_lo,item.adres_la);
			 				
			 						
			 				


			 				}
			 			});		 
						 */
						
						
					} else if(item.fishing_type == 'seafishing'){
						iwContent = '<div class ="marker" id="customOverlay2" onclick="mapDetailList(' + item.nak_id + ',' + item.adres_lo + ',' + item.adres_la + ')"><span class="mkTxt">' + item.co_nm + '</span></div>';
						image = '/naksinuri_original/common_main/img/fishCompany/ico_map_marker02.png';	
		
					
						

					} else {
						iwContent = '<div class ="marker" id="customOverlay3" onclick="mapDetailList(' + item.nak_id + ',' + item.adres_lo + ',' + item.adres_la + ')"><span class="mkTxt">' + item.co_nm + '</span></div>';
						image = '/naksinuri_original/common_main/img/fishCompany/ico_map_marker03.png';	
				
		 
					} 
				} else {
					iwContent = '<div class ="marker" id="customOverlay4" onclick="mapDetailList(' + item.san_sn + ',' + item.adres_lo + ',' + item.adres_la + ')"><span class="mkTxt">' + item.san_name + '</span></div>';
					image = '/naksinuri_original/common_main/img/fishCompany/ico_map_marker04.png';	
				
				}
				
				

				
				var position = new kakao.maps.LatLng(item.adres_la, item.adres_lo);
				//console.log(position);
				var imageSize = new kakao.maps.Size(25, 33); // 마커이미지의 크기입니다
				var imageOption = {offset: new kakao.maps.Point(15, 45)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

	 			var markerImage = new kakao.maps.MarkerImage(image, imageSize, imageOption);
				
 	 			var marker = new kakao.maps.Marker({
					position: position,
					image: markerImage
 	 			}); 
		
 	 			

 	 			

 	 	
				var customOverlay = new kakao.maps.CustomOverlay({
					content : iwContent,
					marker : marker,
					position: position
				});				
 				
	 			points.push(position);
	 			markers.push(marker); 
 	 			customOverlays.push(customOverlay);

	 			var bounds = new kakao.maps.LatLngBounds();
 				for (var j = 0; j < points.length; j++) {
				    // LatLngBounds 객체에 좌표를 추가합니다
				    bounds.extend(points[j]);
				}	
 				map.setBounds(bounds);	  				


 			}  
		}
		clusterer.addMarkers(customOverlays);
		

		
		
		if(key < listRawData.length){
			setTimeout(showMap, 10);
			

 	 			
 	 						
			
			
		} else {
			toastr.info("로드가 완료되었습니다.","",{
				tapToDismiss: true,
				closeButton: false,
				debug: false,
				newestOnTop: true,
				progressBar: true,
				positionClass: "toast-bottom-full-width",
				preventDuplicates: false,
				onclick: false,
				//showDuration: "300",
				//hideDuration: "300",
				timeOut: 3000,
				showEasing: "swing",
				hideEasing: "linear",
				showMethod: "slideDown",//fadeIn
				hideMethod: "fadeOut",
				allowHtml: true,
			});
			points=[];
			
			
			
/* 			

	 		var length_total =	$('#map').children('div').children('div').children('div').children('div').children('div').length;
	 	
	 
	  		for(var n=0; n<=length_total; n++){
	 			
	 			
	 			$('#map').children('div').children('div').children('div').children('div').children('div').eq(n).parent().addClass('focus_add'+n).attr('tabindex',0); 	
	 	 		
	 			$('.focus_add'+n).keyup(function(e){
	 				if(e.keyCode == 13) {
	 					var searchSido = $("#req_sido").val()
	 					alert(searchSido + "지역의 낚시터 정보를 조회합니다.");
	 			
	 					mapList2(map.getBounds().ha, map.getBounds().oa, map.getBounds().qa, map.getBounds().pa);
	 


	 				}
	 			});		 		 			
	 		}			
			
			
			 */
			 
			 
			 
			
			var length_total =	$('#map').children('div').children('div').children('div').children('div').children('div').length;
		 	
	 		 
	  	/* 	for(var n=length_total-1; n<=length_total; n++){ */

	 			
	 			$('#map').children('div').children('div').children('div').children('div').children('div').parent().addClass('focus_add').attr('tabindex',2); 	
	 	 		
	 			$('.focus_add').keyup(function(e){
	 				if(e.keyCode == 13) {
	 					var searchSido = $("#req_sido").val()
	 					alert(searchSido + "지역(모든지역)의 낚시터 정보를 조회합니다.");
	 			
	 					mapList2(map.getBounds().ha, map.getBounds().oa, map.getBounds().qa, map.getBounds().pa);


	 				}
	 			});		 		 			
	 /* 		}	 */						
			
						 
			 
			 
			 
			 
			
			
			
		}
		key += 100;
	}
	
	
			  	

	
</script>


<script type="text/javascript">

	$('document').ready(function()	{
		var area0 = ["시/도 선택","서울","세종","인천","대전","광주","대구","울산","부산","경기","강원","충북","충남","전북","전남","경북","경남","제주"];
		var area1 = ["구/군 선택","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
		var area2 = ["구/군 선택","조치원읍","연기면","연동면","부강면","금남면","장군면","연서면","전의면","전동면","소정면","한솔동","도담동","아름동","종촌동","고운동","보람동"]
		var area3 = ["구/군 선택","계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];                                                                                                                                               
		var area4 = ["구/군 선택","대덕구","동구","서구","유성구","중구"];                                                                                                                                                                            
		var area5 = ["구/군 선택","광산구","남구","동구","북구","서구"];                                                                                                                                                                             
		var area6 = ["구/군 선택","남구","달서구","동구","북구","서구","수성구","중구","달성군"];                                                                                                                                                            
		var area7 = ["구/군 선택","남구","동구","북구","중구","울주군"];                                                                                                                                                                             
		var area8 = ["구/군 선택","강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];                                                                                                          
		var area9 = ["구/군 선택","고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];          
		var area10 = ["구/군 선택","강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];                                                                                           
		var area11 = ["구/군 선택","제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];                                                                                                                              
		var area12 = ["구/군 선택","계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];                                                                                                      
		var area13 = ["구/군 선택","군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];                                                                                                                  
		var area14 = ["구/군 선택","광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];                                                                  
		var area15 = ["구/군 선택","경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];                                                            
		var area16 = ["구/군 선택","거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];                                                                              
		var area17 = ["구/군 선택","서귀포시","제주시","남제주군","북제주군"]; 
		
		var searchSido = $("#req_sido").val();
		if(searchSido){
			switch(searchSido){
				case "강원" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg1.png");
					break;
				case "서울" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg2.png");
					break;
				case "인천" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg3.png");
					break;
				case "경기" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg4.png");
					break;
				case "충남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg5.png");
					break;
				case "대전" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg6.png");
					break;
				case "세종" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg7.png");
					break;
				case "충북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg8.png");
					break;
				case "전북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg9.png");
					break;
				case "전남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg10.png");
					break;
				case "광주" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg11.png");
					break;
				case "대구" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg12.png");
					break;
				case "울산" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg13.png");
					break;
				case "부산" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg14.png");
					break;
				case "경북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg15.png");
					break;
				case "경남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg16.png");
					break;
				case "제주" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg17.png");
					break;
				default :
					break;
			}			
		}
		
		// 시/도 선택 박스 초기화
		$("select[name^=searchSido]").each(function() {
			$selsido = $(this);  
			$.each(eval(area0), function(i,v)  {
				if(i==0){
					$selsido.append("<option value=''>"+this+"</option>");
				}else if($('#req_sido').val()==this){
					$selsido.append("<option selected value='"+this+"'>"+this+"</option>");
				}else{
					$selsido.append("<option value='"+this+"'>"+this+"</option>");
				}
			});
			$('#searchGugun').append("<option value=''>구/군 선택</option>");
		});
		 
		 // 시/도 선택시 구/군 설정
		$("select[name^=searchSido]").change(function() {
			switch($("select[name^=searchSido]").val()){
				case "강원" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg1.png");
					break;
				case "서울" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg2.png");
					break;
				case "인천" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg3.png");
					break;
				case "경기" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg4.png");
					break;
				case "충남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg5.png");
					break;
				case "대전" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg6.png");
					break;
				case "세종" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg7.png");
					break;
				case "충북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg8.png");
					break;
				case "전북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg9.png");
					break;
				case "전남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg10.png");
					break;
				case "광주" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg11.png");
					break;
				case "대구" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg12.png");
					break;
				case "울산" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg13.png");
					break;
				case "부산" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg14.png");
					break;
				case "경북" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg15.png");
					break;
				case "경남" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg16.png");
					break;
				case "제주" :
					$("#imgMap").attr("src","/naksinuri_original/common_main/img/fishCompany/mapImg17.png");
					break;
				default :
					break;
			}
	   
		
			var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
			var $gugun = $('#searchGugun'); // 선택영역 군구 객체
			$("option",$gugun).remove(); // 구군 초기화
			if(area == "area0")
				$gugun.append("<option value=''>구/군 선택</option>");
			else {
				$.each(eval(area), function(i,v) {
					if(i==0){
						$gugun.append("<option value=''>"+this+"</option>");
					}else if($('#req_gugun').val()==this){
						$gugun.append("<option selected value='"+this+"'>"+this+"</option>");
					}else{
						$gugun.append("<option value='"+this+"'>"+this+"</option>");
					}
				});
			}
		});
		
		var area = "area"+$("option",$("#searchSido")).index($("option:selected",$("#searchSido"))); // 선택지역의 구군 Array
		var $gugun = $('#searchGugun'); // 선택영역 군구 객체
		$("option",$gugun).remove(); // 구군 초기화
		if(area == "area0")
		  $gugun.append("<option value=''>구/군 선택</option>");
		 else {
		  $.each(eval(area), function(i,v) {
			   if(i==0){
		   	   $gugun.append("<option value=''>"+this+"</option>");
			   }else if($('#req_gugun').val()==this){
				   $gugun.append("<option selected value='"+this+"'>"+this+"</option>");
			   }else{
				   $gugun.append("<option value='"+this+"'>"+this+"</option>");
			   }
		   });
		}
		
		$('.mapTabMenu a').on("click", function(){
			selectClass = $(this).attr("class");
			//changSido("경기");
			fnSelectInfs();
		}); 
	});	
	
	function go_review(){
		var form = document.getElementById('supform');
		form.action="/info/fishjob/review.do";
		form.submit();
		
	}

	function fishjob_report(){
		var form = document.getElementById('supform');
		form.action="/info/fishjobreport/write.do";
		form.submit();
	}
	
	function changSido(sido){
		$("#searchSido").val(sido).prop("selected", true);
		$('#searchSido').change();
		
	}
	
	
	

	
	function mapDetailList(id, resultY, resultX){
		var position = new kakao.maps.LatLng(resultY, resultX); 
		// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		//console.log(position);
		
		var viewLinkUrl;
		var form = document.getElementById('viewform');
		var form2 = document.getElementById('viewform2');
		
		var formData;
		
		if(selectClass.indexOf("mapTabCon1") != -1){ //포함되어 있을때 
			viewLinkUrl = "/info/fishCompany/view.do";
			form.nak_id.value = id;
			formData = $('#viewform').serialize();
		} else {
			viewLinkUrl = "/info/fishSanup/view.do";
			form2.san_sn.value = id;
			formData = $('#viewform2').serialize();
		}
		
		$.ajax({
			type:"POST",
			url : viewLinkUrl,
			data : formData,
			dataType: "html",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			beforeSend: function() {
				//$(".infoCon2").css("display","block");
				$(".infoCon3").hide();
			},
			success: function(data, status, xhr) {	
				$(".infoCon3").html(data);
			},
			complete : function() {
				//$(".infoCon2").css("display","none");
				$(".infoCon3").show();
				map.relayout();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		}); 

		/* for(var i=0; i<$(".infoCon2").length; i++){
			if($(".infoCon2").eq(i).attr("data-sn") == nakId){
				$(".infoCon2").eq(i).show();
			} else {
				$(".infoCon2").eq(i).hide();
			}
		} */
	}
	
	function mapList(adresLo1, adresLo2, adresLa1, adresLa2){
		var viewLinkUrl;
		var formData;
		var form = document.getElementById('mapListForm');
		var form2 = document.getElementById('mapListForm2');
		
		if(selectClass.indexOf("mapTabCon1") != -1){ //포함되어 있을때 
			viewLinkUrl = "/info/fishCompany/mapList.do";
			form.adres_la1.value = adresLa1;
			form.adres_la2.value = adresLa2;
			form.adres_lo1.value = adresLo1;
			form.adres_lo2.value = adresLo2;
			formData = $('#mapListForm').serialize();
			
			
		} else {
			viewLinkUrl = "/info/fishSanup/mapList.do";
			form2.adres_la1.value = adresLa1;
			form2.adres_la2.value = adresLa2;
			form2.adres_lo1.value = adresLo1;
			form2.adres_lo2.value = adresLo2;
			formData = $('#mapListForm2').serialize();
			
		
		}
		
		$.ajax({
			type:"POST",
			url : viewLinkUrl,
			data : formData,
			dataType: "html",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			beforeSend: function() {
				//$(".infoCon2").css("display","block");
				$(".infoCon2").hide();
			},
			success: function(data, status, xhr) {	
				$(".infoCon2").html(data);
			},
			complete : function() {
				console.log('complete!');
				//$(".infoCon2").css("display","none");
				$(".infoCon2").show();
				map.relayout();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
	}
	
	
	function mapList2(adresLo1, adresLo2, adresLa1, adresLa2){
		var viewLinkUrl;
		var formData;
		var form = document.getElementById('mapListForm');
		var form2 = document.getElementById('mapListForm2');
		
		if(selectClass.indexOf("mapTabCon1") != -1){ //포함되어 있을때 
			viewLinkUrl = "/info/fishCompany/mapList.do";
			form.adres_la1.value = adresLa1;
			form.adres_la2.value = adresLa2;
			form.adres_lo1.value = adresLo1;
			form.adres_lo2.value = adresLo2;
			formData = $('#mapListForm').serialize();
		} else {
			

			viewLinkUrl = "/info/fishSanup/mapList.do";
			form2.adres_la1.value = adresLa1;
			form2.adres_la2.value = adresLa2;
			form2.adres_lo1.value = adresLo1;
			form2.adres_lo2.value = adresLo2;
			formData = $('#mapListForm2').serialize();
		}
		
		$.ajax({
			type:"POST",
			url : viewLinkUrl,
			data : formData,
			dataType: "html",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			beforeSend: function() {
				//$(".infoCon2").css("display","block");
				$(".infoCon2").hide();
			},
			success: function(data, status, xhr) {	
				$(".infoCon2").html(data);
			},
			complete : function() {
				console.log('complete!');
				//$(".infoCon2").css("display","none");
				$(".infoCon2").show();
				map.relayout();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error!');
	 			console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown); 
			}
		});
	}	
	
	
	
	
	function closeListModal() {
		$(".infoCon2").hide();
	}
	function closeInfoModal(nakId) {
		$(".infoCon3").hide();
	}
	
	function selectChange(item) {
		if(item.value == '') {
			$("#imgMap").attr("src", "/naksinuri_original/common_main/img/fishCompany/mapImg.png");
		}
	}
	
	

	

</script>
<%@include file="../layout/tail.jsp"%>
