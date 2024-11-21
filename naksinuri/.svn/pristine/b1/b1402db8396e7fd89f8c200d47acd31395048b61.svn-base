<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="plocation" />


<%@include file="../../layout/m/head.jsp"%>	
	
<form method="post" action="./list.do" id="mapsur">
	<div id="noFishing" class="content respon2">
		<div class="tabArea"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li id="plocation" class="on"><a href="/promotion/plocation/m/list.do">낚시금지구역</a></li>
				<li id="llocation" ><a href="/promotion/llocation/m/list.do">낚시제한구역</a></li>
			</ul>
		</div>
		
		<div class="list_searchbox">
<!--  
			<div class="total_num">전체 <b class="colorSky">2,264</b>건의 검색결과가 있습니다.(<b class="colorSky">4</b>/227)</div>
-->
			<select name="sido" id="sido" class="basic_select" ></select>
<!-- 			<select name="gugun" id="gugun" class="basic_select" ></select> -->
		</div>

		<section id="nofishingList" class="floats">

			<div id="nmap" class="mapbox">
				지도 넣는 공간
			</div>
		</section>
	</div>
</form>
<script>
var map = null;
$(document).ready(function (){
	$('#nmap').empty();
	/*
	var map = new naver.maps.Map('nmap', {
        center: new naver.maps.LatLng(37.574515, 126.976930), //지도의 초기 중심 좌표
        zoom: 3, //지도의 초기 줌 레벨
        minZoom: 1, //지도의 최소 줌 레벨
        zoomControl: true, //줌 컨트롤의 표시 여부
        zoomControlOptions: { //줌 컨트롤의 옵션
            position: naver.maps.Position.TOP_RIGHT
        }
    });
	

	var markers = [];
	var infoWindows = [];
	*/

	var mapContainer = document.getElementById('nmap'), // 지도를 표시할 div 
		mapOption = {
			center: new daum.maps.LatLng(37.574515, 126.976930), // 지도의 중심좌표
			level: 12 // 지도의 확대 레벨
		};  
		map = new daum.maps.Map(mapContainer, mapOption); 
	
	<c:forEach items="${select_list}" var="item" varStatus="status" >
	var latitude =${item.x_latitude};
	var hardness =${item.x_hardness};
	/*
    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(latitude, hardness),
        map: map  
        
    })
     
// 	알림창 컨텐츠
	var contentString = [
	                     '<div class="iw_inner">',
	                     '   <h3>${item.x_name}</h3>',
	                     '   <p>< ${item.x_term} > <br/> <${item.x_rang}> <br/> | ${item.x_person}<br />',                
	                     '      ${item.x_purpose} | ${item.x_related}<br />',
	                   //'    주소:  ${item.x_address}  ${item.x_address2}  ${item.x_address3}',
	                     '   </p><br/>',
	                     '</div>'
	                 ].join('');

    
	var infowindow = new naver.maps.InfoWindow({
		    content: contentString,
		    maxWidth: 140,
		    backgroundColor: "#eee",
		    borderColor: "#2db400",
		    borderWidth: 5,
		    anchorSize: new naver.maps.Size(30, 30),
		    anchorSkew: true,
		    anchorColor: "#eee",
		    pixelOffset: new naver.maps.Point(20, -20)
		});
    

	  		
	  	
    markers.push(marker);
    infoWindows.push(infowindow);
	*/

	// 마커를 표시할 위치입니다 
	var position =  new daum.maps.LatLng(latitude, hardness);

	// 마커를 생성합니다
	var marker = new daum.maps.Marker({
	  position: position,
	  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
	});

	// 마커를 지도에 표시합니다.
	marker.setMap(map);


	// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	var iwContent = '<div class="iw_inner">'+
                    '   <h3>${item.x_name}</h3>'+
                     '   <p>< ${item.x_term} > <br/> <${item.x_rang}> <br/> | ${item.x_person}<br />'+                
	                     '      ${item.x_purpose} | ${item.x_related}<br />'+
	                     '   </p><br/>'+
	                     '</div>';

	// 인포윈도우를 생성합니다
	var infowindow = new daum.maps.InfoWindow({
		content : iwContent,
		removable : true
	});

	// 마커에 클릭이벤트를 등록합니다
	daum.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));

	// 이동할 위도 경도 위치를 생성합니다 
	var moveLatLon = new daum.maps.LatLng(latitude,hardness);    
	// 지도 중심을 이동 시킵니다
	map.setCenter(moveLatLon);
		
	</c:forEach>


	function makeOverListener(map, marker, infowindow) {
		return function() {
			infowindow.open(map, marker);
		};
	}

	/*
	naver.maps.Event.addListener(map, 'idle', function() {
	    updateMarkers(map, markers);
	});

	function updateMarkers(map, markers) {

	    var mapBounds = map.getBounds();
	    var marker, position;

	    for (var i = 0; i < markers.length; i++) {
			
	        marker = markers[i]
	        position = marker.getPosition();

	        if (mapBounds.hasLatLng(position)) {
	            showMarker(map, marker);
	        } else {
	            hideMarker(map, marker);
	        }
	    }
	}

	function showMarker(map, marker) {

	    if (marker.setMap()) return;
	    marker.setMap(map);
	}

	function hideMarker(map, marker) {

	    if (!marker.setMap()) return;
	    marker.setMap(null);
	}
	

	// 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
	function getClickHandler(seq) {
	    return function(e) {
	        var marker = markers[seq],
	            infoWindow = infoWindows[seq];

	        if (infoWindow.getMap()) {
	            infoWindow.close();
	        } else {
	            infoWindow.open(map, marker);
	        }
	    }
	}

	for (var i=0, ii=markers.length; i<ii; i++) {
	    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
	    
	}
	
	*/

		

	
	});
	

</script>
<script type="text/javascript">
$('document').ready(function() {
 var area0 = ["시/도 선택","서울","인천","대전","광주","대구","울산","부산","경기","강원","충북","충남","전북","전남","경북","경남","제주"];
  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
   var area3 = ["대덕구","동구","서구","유성구","중구"];
   var area4 = ["광산구","남구","동구","북구","서구"];
   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
   var area6 = ["남구","동구","북구","중구","울주군"];
   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
   var area16 = ["서귀포시","제주시","남제주군","북제주군"];
 
 // 시/도 선택 박스 초기화
 $("select[name^=sido]").each(function() {
  $selsido = $(this);
  $.each(eval(area0), function() {
	  if('${sido}'==this){
   		$selsido.append("<option value='"+this+"' selected>"+this+"</option>");
   		
	  }else{
		  $selsido.append("<option value='"+this+"'>"+this+"</option>");
  	}
  });
  $selsido.next().append("<option value=''>구/군 선택</option>");
 });
 
 // 시/도 선택시 구/군 설정
 $("select[name^=sido]").change(function() {
	var area = "area"+$("option",$('#sido')).index($("option:selected",$('#sido'))); // 선택지역의 구군 Array
	var $gugun = $('#sido').next();
	$('#gugun').val(null);
	var markers = [];
	var infoWindows = [];
	 $.ajax({
		 type:"post",
		 url:"${contextPath}/promotion/m/plocation.do",
		 datatype:"json",
		 data:$("#mapsur").serialize(),
		 success:function(data){ 		
			 $("option",$gugun).remove(); // 구군 초기화
			 if(area == "area0"){
			  $gugun.append("<option value=''>시/구/군 선택</option>");
			 } else {
			  $gugun.append("<option value=''>구/군 선택</option>");
			  $.each(eval(area), function() {
				   
				if('${gugun}'==this){
			   		$gugun.append("<option value='"+this+"' selected>"+this+"</option>");
				}else{
					$gugun.append("<option value='"+this+"'>"+this+"</option>");
				};
				});
			 }
			 
			 $('#nmap').empty();

				/*
			 var map = new naver.maps.Map('nmap', {
			        center: new naver.maps.LatLng(37.574515, 126.976930), //지도의 초기 중심 좌표
			        zoom: 3, //지도의 초기 줌 레벨
			        minZoom: 1, //지도의 최소 줌 레벨
			        zoomControl: true, //줌 컨트롤의 표시 여부
			        zoomControlOptions: { //줌 컨트롤의 옵션
			            position: naver.maps.Position.TOP_RIGHT
			        }
			    });
				*/

				var mapContainer = document.getElementById('nmap'), // 지도를 표시할 div 
				mapOption = {
					center: new daum.maps.LatLng(37.574515, 126.976930), // 지도의 중심좌표
					level: 12 // 지도의 확대 레벨
				};  
				map = new daum.maps.Map(mapContainer, mapOption); 
			 
				for(var i=0;i<=data.length;i++){
				var latitude =data[i].x_latitude;
				var hardness =data[i].x_hardness;



				/*
			    var marker = new naver.maps.Marker({
			        position: new naver.maps.LatLng(latitude, hardness),
			        map: map  
			        
			    });
				*/
			    var x_name = data[i].x_name;
			    var x_term = data[i].x_term;
			    var x_rang = data[i].x_rang;
			    var x_person = data[i].x_person;
			    var x_purpose = data[i].x_purpose;
			    var x_related = data[i].x_related;
			     
			    /*
//			 	알림창 컨텐츠
				var contentString = [
				                     '<div class="iw_inner">',
				                     '   <h3>'+x_name+'</h3>',
				                     '   <p>< '+x_term+' > <br/> <'+x_rang+'> <br/> | '+x_person+'<br />',                
				                     '      '+x_purpose+' | '+x_related+'<br />',				                   
				                     '   </p><br/>',
				                     '</div>'
				                 ].join('');
			
				var infowindow = new naver.maps.InfoWindow({
					    content: contentString,
					    maxWidth: 140,
					    backgroundColor: "#eee",
					    borderColor: "#2db400",
					    borderWidth: 5,
					    anchorSize: new naver.maps.Size(30, 30),
					    anchorSkew: true,
					    anchorColor: "#eee",
					    pixelOffset: new naver.maps.Point(20, -20)
					});
				
				  
			    markers.push(marker);
			    infoWindows.push(infowindow);	
				
				naver.maps.Event.addListener(map, 'idle', function() {
				    updateMarkers(map, markers);				 
				});
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
				*/

				// 마커를 표시할 위치입니다 
				var position =  new daum.maps.LatLng(latitude, hardness);

				// 마커를 생성합니다
				var marker = new daum.maps.Marker({
				  position: position,
				  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
				});

				// 마커를 지도에 표시합니다.
				marker.setMap(map);


				// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				var iwContent = '<div class="iw_inner">'+
								'   <h3>'+x_name+'</h3>'+
								 '   <p>< '+x_term+' > <br/> <'+x_rang+'> <br/> | '+x_person+'<br />'+                
									 '      '+x_purpose+' | '+x_related+'<br />'+
									 '   </p><br/>'+
									 '</div>';

				// 인포윈도우를 생성합니다
				var infowindow = new daum.maps.InfoWindow({
					content : iwContent,
					removable : true
				});

				// 마커에 클릭이벤트를 등록합니다
				daum.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));

				// 이동할 위도 경도 위치를 생성합니다 
				var moveLatLon = new daum.maps.LatLng(latitude,hardness);    
				// 지도 중심을 이동 시킵니다
				map.setCenter(moveLatLon);
					
			}

			function makeOverListener(map, marker, infowindow) {
				return function() {
					infowindow.open(map, marker);
				};
			}
				
				/*
				function updateMarkers(map, markers) {

				    var mapBounds = map.getBounds();
				    var marker, position;

				    for (var i = 0; i < markers.length; i++) {
						
				        marker = markers[i]
				        position = marker.getPosition();

				        if (mapBounds.hasLatLng(position)) {
				            showMarker(map, marker);
				        } else {
				            hideMarker(map, marker);
				        }
				    }
				}

				function showMarker(map, marker) {				

				    if (marker.setMap()) return;
				    marker.setMap(map);
				}

				function hideMarker(map, marker) {
					
				    if (!marker.setMap()) return;
				    marker.setMap(null);
				}
				
		

				// 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
				function getClickHandler(seq) {
				    return function(e) {
				        var marker = markers[seq],
				            infoWindow = infoWindows[seq];

				        if (infoWindow.getMap()) {
				            infoWindow.close();
				        } else {
				            infoWindow.open(map, marker);
				        }
				    }
				    
				}
				*/

				
				    
				    
				
				
		 },error:function(x,o,e){
			 var msg="페이지 호출 중 에러 발생\n"+x.status+":"+o+":"+e;
			 alert(msg);
		 }				
	 });	

				
 });
 $("#gugun").change(function() {
		var markers = [];
		var infoWindows = [];
	 $.ajax({
		 type:"post",
		 url:"${contextPath}/promotion/m/plocation.do",
		 datatype:"json",
		 data:$("#mapsur").serialize(),
		 success:function(data){			 
			
			 $('#nmap').empty();

			 /*
			 var map = new naver.maps.Map('nmap', {
			        center: new naver.maps.LatLng(37.574515, 126.976930), //지도의 초기 중심 좌표
			        zoom: 3, //지도의 초기 줌 레벨
			        minZoom: 1, //지도의 최소 줌 레벨
			        zoomControl: true, //줌 컨트롤의 표시 여부
			        zoomControlOptions: { //줌 컨트롤의 옵션
			            position: naver.maps.Position.TOP_RIGHT
			        }
			    });
				*/

				var mapContainer = document.getElementById('nmap'), // 지도를 표시할 div 
				mapOption = {
					center: new daum.maps.LatLng(37.574515, 126.976930), // 지도의 중심좌표
					level: 12 // 지도의 확대 레벨
				};  
				map = new daum.maps.Map(mapContainer, mapOption); 
			 
				for(var i=0;i<=data.length;i++){
				var latitude =data[i].x_latitude;
				var hardness =data[i].x_hardness;
				/*
			    var marker = new naver.maps.Marker({
			        position: new naver.maps.LatLng(latitude, hardness),
			        map: map  
			        
			    });
				*/
			    var x_name = data[i].x_name;
			    var x_term = data[i].x_term;
			    var x_rang = data[i].x_rang;
			    var x_person = data[i].x_person;
			    var x_purpose = data[i].x_purpose;
			    var x_related = data[i].x_related;
			     /*
			    
//			 	알림창 컨텐츠
				var contentString = [
				                     '<div class="iw_inner">',
				                     '   <h3>'+x_name+'</h3>',
				                     '   <p>< '+x_term+' > <br/> <'+x_rang+'> <br/> | '+x_person+'<br />',                
				                     '      '+x_purpose+' | '+x_related+'<br />',				                   
				                     '   </p><br/>',
				                     '</div>'
				                 ].join('');
			
				var infowindow = new naver.maps.InfoWindow({
					    content: contentString,
					    maxWidth: 140,
					    backgroundColor: "#eee",
					    borderColor: "#2db400",
					    borderWidth: 5,
					    anchorSize: new naver.maps.Size(30, 30),
					    anchorSkew: true,
					    anchorColor: "#eee",
					    pixelOffset: new naver.maps.Point(20, -20)
					});
				
				  
			    markers.push(marker);
			    infoWindows.push(infowindow);	
				
				naver.maps.Event.addListener(map, 'idle', function() {
				    updateMarkers(map, markers);				 
				});
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
				*/


				// 마커를 표시할 위치입니다 
				var position =  new daum.maps.LatLng(latitude, hardness);

				// 마커를 생성합니다
				var marker = new daum.maps.Marker({
				  position: position,
				  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
				});

				// 마커를 지도에 표시합니다.
				marker.setMap(map);


				// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				var iwContent = '<div class="iw_inner">'+
								'   <h3>'+x_name+'</h3>'+
								 '   <p>< '+x_term+' > <br/> <'+x_rang+'> <br/> | '+x_person+'<br />'+                
									 '      '+x_purpose+' | '+x_related+'<br />'+
									 '   </p><br/>'+
									 '</div>';

				// 인포윈도우를 생성합니다
				var infowindow = new daum.maps.InfoWindow({
					content : iwContent,
					removable : true
				});

				// 마커에 클릭이벤트를 등록합니다
				daum.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));

				// 이동할 위도 경도 위치를 생성합니다 
				var moveLatLon = new daum.maps.LatLng(latitude,hardness);    
				// 지도 중심을 이동 시킵니다
				map.setCenter(moveLatLon);
			}

			function makeOverListener(map, marker, infowindow) {
				return function() {
					infowindow.open(map, marker);
				};
			}
				
				/*
				
				function updateMarkers(map, markers) {

				    var mapBounds = map.getBounds();
				    var marker, position;

				    for (var i = 0; i < markers.length; i++) {
						
				        marker = markers[i]
				        position = marker.getPosition();

				        if (mapBounds.hasLatLng(position)) {
				            showMarker(map, marker);
				        } else {
				            hideMarker(map, marker);
				        }
				    }
				}

				function showMarker(map, marker) {				

				    if (marker.setMap()) return;
				    marker.setMap(map);
				}

				function hideMarker(map, marker) {
					
				    if (!marker.setMap()) return;
				    marker.setMap(null);
				}
				
		

				// 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
				function getClickHandler(seq) {
				    return function(e) {
				        var marker = markers[seq],
				            infoWindow = infoWindows[seq];

				        if (infoWindow.getMap()) {
				            infoWindow.close();
				        } else {
				            infoWindow.open(map, marker);
				        }
				    }
				    
				}
				*/



		 },error:function(x,o,e){
			 var msg="페이지 호출 중 에러 발생\n"+x.status+":"+o+":"+e;
			 alert(msg);
		 }
		 
	 });
	 

 });
 
});

</script>

<%@include file="../../layout/m/tail.jsp"%>