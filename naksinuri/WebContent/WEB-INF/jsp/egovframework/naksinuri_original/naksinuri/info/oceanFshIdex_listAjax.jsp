<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script>
	var listRawData = [];//맵 데이터
</script>
<c:forEach items="${oceanFshIdexList}" var="item" varStatus="status">
	<script>
		//console.log("-----------------------------------------------------------------------------");
		//console.log('${item.row_sn}'+'_'+'${item.name}'+'_'+'${status.index}');
	{
		var obj = {};
		obj['name'] = '${item.name}';//제목, 명칭 등
		obj['rowSn'] = '${item.row_sn}';//일련번호
		obj['lat'] = '${item.lat}';//위도
		obj['lon'] = '${item.lon}';//경도
		obj['addrSeq'] = '${item.row_sn}'+'_'+'${item.name}'+'_'+'${status.index}';
		listRawData.push(obj);
	}
	</script>
</c:forEach>
<script type="text/javascript">
	kakao.maps.event.addListener(mapUi, 'zoom_changed', function() {        
	    // 지도의 현재 레벨을 얻어옵니다
	    var level = mapUi.getLevel();
	    for(var i=0; i<arrOverlayObjContainer2.length; i++) {
			arrOverlayObjContainer2[i].setMap(mapUi);	
		}
	});
	//map marker clear
	for(var i=0; i<arrMarkerObjContainer.length; i++) {
		arrMarkerObjContainer[i].setMap(null);	
	}
	for(var i=0; i<arrOverlayObjContainer2.length; i++) {
		arrOverlayObjContainer2[i].setMap(null);	
	}
	if(clusterer != null){
		clusterer.clear();
	}
	// 마커 클러스터러를 생성합니다 
	clusterer = new kakao.maps.MarkerClusterer({
		map: mapUi, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
		averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
		minLevel: 14, // 클러스터 할 최소 지도 레벨 
	});
	listRawData.forEach(function(item, index) {
		if(item.lat!=null && item.lat!='' && item.lon!=null && item.lon!='') {
			var markers = $(item).map(function(index, item) {
				var position = new kakao.maps.LatLng(item.lat, item.lon);
				var imageSize = new kakao.maps.Size(25, 33); // 마커이미지의 크기입니다
				var imageOption = {offset: new kakao.maps.Point(15, 45)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
				var imageSrc = '/naksinuri_original/common_main/img/fishCompany/ico_map_marker01.png';
				// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
				var mk = new kakao.maps.Marker({
	                position : position,
	                image: markerImage
	            });
				//custom
	            var overlay = makeOverlay({
	            	marker : mk,
	            	name : item.name,
	            	rowSn : item.rowSn,
	            	lat : item.lat,
	            	lon : item.lon,
	            	addrSeq : item.addrSeq,
	            });
				//End of custom
				var markerPosition  = new kakao.maps.LatLng(item.mapX, item.mapY);
				var overlayTitle = makeOverlayTitle({
					marker : mk,
	            	name : item.name,
	            	rowSn : item.rowSn,
	            	lat : item.lat,
	            	lon : item.lon
				});
						
				overlayTitle.setPosition(markerPosition);
				overlayTitle.setMap(mapUi);
				overlayTitle.setVisible(true);
				
	   			kakao.maps.event.addListener(mk, 'click', function() {
					overlay.setPosition(markerPosition);
					overlay.setMap(mapUi);
					overlay.setVisible(true);
					Object.keys(arrOverlayObjContainer).forEach(function(k){
						if(k!=item.addrSeq) arrOverlayObjContainer[k].setMap(null);
					});
				});
	   			//오브젝트 저장
	   	     	arrOverlayObjContainer[item.addrSeq] = overlay;
	   	     	arrOverlayObjContainer2.push(overlayTitle);
	   	        //arrMarkerObjContainer.push(marker);
				return mk;
	   		});
			clusterer.addMarkers(markers);
		}
	});
	function makeOverlay(targetObj){
		
		var lat = targetObj.lat;
		var lon = targetObj.lon;
		var name = targetObj.name;//제목, 명칭 등
	    var rowSn = targetObj.rowSn;//일련번호
	    var addrSeq = targetObj.addrSeq;//맵 마커 번호
		var marker = targetObj.mk;
		
		var markerPosition  = new kakao.maps.LatLng(lat, lon);
		
		var imageSrc = '';// 마커이미지의 주소    
		var imageSize = new kakao.maps.Size(25, 33); // 마커이미지의 크기입니다
		var imageOption = {offset: new kakao.maps.Point(15, 45)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
		// 커스텀 오버레이에 표시할 컨텐츠 입니다
		var content = '<div class="wrap" id="' + addrSeq + '" style="background-color:#fff;border:solid 1px #D2D2D2; border-radius:4px;position:absolute;top:-50px;">' + 
							'    <div class="info">' + 
							'        <div class="title">' + name +
							'            <div class="close" onclick="closeOverlay(\'' + addrSeq + '\')" title="닫기" style="opacity: 1;">X</div>' + 
							'        </div>' + 
							'        <div class="body">' + 
							'            <div class="desc">' +
							
							'            </div>' + 
							'        </div>' + 
							'    </div>' +    
							'</div>';

	    // 마커 위에 커스텀오버레이를 표시합니다
	    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	    var overlay = new kakao.maps.CustomOverlay({
				content: content,
				map: mapUi,
				position: markerPosition,
				clickable : true,
				xAnchor: 0.5, // 컨텐츠의 x 위치
				yAnchor: 0, // 컨텐츠의 y 위치
				zIndex: 3
	    });
	     
	    overlay.setVisible(false); //커스텀 오버레이의 표시 여부를 지정
		
	  	// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
	    /* kakao.maps.event.addListener(marker, 'click', function() {
			overlay.setPosition(markerPosition);
	        overlay.setMap(mapUi);
	        overlay.setVisible(true);
	    }); */
	          
		return overlay;
	}
	function makeOverlayTitle(targetObj){
		var name = targetObj.name;//제목, 명칭 등
		var lat = targetObj.lat;
		var lon = targetObj.lon;
		var rowSn = targetObj.rowSn;//맵 마커 번호
		var marker = targetObj.mk;
		var markerPosition  = new kakao.maps.LatLng(lat, lon);
		if(name.length > 5){
			name = name.substring(0,5) + "...";
		}
		var content = '<div class="wrap mapTitleOverlay" id="' + rowSn + '" style="background-color:#fff;color:#FC6E38;border:solid 1px #FC6E38; border-radius:4px;position:absolute;top:-67px;left:-35px;width:70px;text-align:center;">' + name + '</div>';
		// 마커 위에 커스텀오버레이를 표시합니다
		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay = new kakao.maps.CustomOverlay({
			content: content,
			map: mapUi,
			position: markerPosition,
			zIndex: 3
		});
		overlay.setVisible(false); //커스텀 오버레이의 표시 여부를 지정
		return overlay;
	}
</script>
<%-- <%@include file="../layout/tail.jsp"%> --%>
