<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="fishjob" />
<c:set var="depthNum" value="2" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishjob" />


<%@include file="../../layout/m/head.jsp"%>
<script type="text/javascript">
function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "/info/fishjob/m/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}


</script>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="searchType" id="searchType" value="${order_type}" />

<input type="hidden" id="req_sido" value="${searchSido}" />
<input type="hidden" id="req_gugun" value="${searchGugun}" />

	<div id="fishjobList" class="content respon2">
		<!-- 필요없어짐 (탭메뉴) { --
		<div class="tabArea">
			<ul class="floats">
				<li class="on"><a href="#;">낚시업정보</a></li>
				<li><a href="#;">낚시산업정보</a></li>
			</ul>
		</div>
		!-- } 필요없어짐 (탭메뉴) -->

		<section id="mainSearch" class="floats">
			
			<div class="search_box">
				<div class="scon">
					<dl>
						<dt>· 구분</dt>
						<dd>
							<input type="checkbox"  name="searchBoat" id="searchBoat" value="boatfishing" <c:if test ="${searchBoat eq 'boatfishing'}">checked</c:if> /><label for="searchBoat"><span></span>선상낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox"  name="searchSea" id="searchSea" value="seafishing" <c:if test ="${searchSea eq 'seafishing'}">checked</c:if>/><label for="searchSea"><span></span>바다낚시</label>
							<input type="checkbox"  name="searchRiver" id="searchRiver" value="riverfishing" <c:if test ="${searchRiver eq 'riverfishing'}">checked</c:if>/><label for="searchRiver"><span></span>민물낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
						</dd>
					</dl> 
					<div id="searchco_info" >
						
					</div>
					<dl>
						<dt>· 시/도</dt>
						<dd>
							<select class="search_select" name="searchSido" id="searchSido" title="시/도 선택">
								

							</select>
						</dd>
					</dl>
					<dl>
						<dt>· 시/군/구</dt>
						<dd>
							<select class="search_select"  name="searchGugun" id="searchGugun" title="시/군/구 선택">
								

							</select>
						</dd>
					</dl>
					<dl>
						<dt>· 주요어종</dt>
						<dd><input type="text" id="searchText" name="searchText1" class="search_input" value="${searchText1}" placeholder="예) 붕어, 잉어, 도다리" title="주요어종"/></dd>
					</dl>
					<dl>
						<dt>· 상호명</dt>
						<dd><input type="text" id="searchText2" name="searchText2" class="search_input" value="${searchText2}" title="상호명"/></dd>
					</dl>
					<dl>
						<dt>· 홈페이지</dt>
						<dd><input type="checkbox" id="website" name="website" /><label for="website"><span></span>자체홈페이지 여부</label></dd>
					</dl>

				</div>
				<div class="searchBtn"><button type="button" onclick="fnSelectInfs(1)"><i class="fa fa-search" aria-hidden="true" title="검색하기"></i><em>검색하기</em></button></div>
			</div>
		</section>
		<section id="galleryList" class="list_box">
		
<!-- 			<div class="tabArea tab4">	탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐  -->
<!-- 				<ul class="floats" id="cata"> -->
<!-- 					<li id="allca" class="on"><a href="/info/fishjob/list.do">등록순</a></li> -->
<!-- 					<li id="boatca"><a href="#;" onclick="boatfind()">선상낚시</a></li> -->
<!-- 					<li id="seaca"><a href="#;" onclick="seafind()">바다낚시</a></li> -->
<!-- 					<li id="rica"><a href="#;" onclick="rivfind()">민물낚시</a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
			
			<div class="sortbox">
				<p class="totalNum">전체 <b class="colorSky">${fish_total}</b>건의 게시물이 있습니다. (<b class="colorSky">${pageIndex}</b>/${pagesize})</p>
				<ul class="floats">
					<li class="on" id="ids"><a href="#;" onclick="order_id()">등록순</a></li>
					<li id="kor"><a href="#;" onclick="order_kor()">한글순</a></li>
					<li id="views"><a href="#;" onclick="order_views()">조회수순</a></li>
				</ul>
			</div>
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${fish_list}">
					<li class="fix208">
					
				         <c:choose>
							<c:when test="${item.orignl_file_nm ne null }">
								<a href="#;" onclick="nak_sub('${item.nak_id}')" class="pic lh100">
									<div style="width: 303px; height: 227px;">
										<c:import url="/naksinuri_original/cmm/fms/selectImageFileInfs.do"
											charEncoding="utf-8">
											<c:param name="atchFileId" value="${item.co_mimgsrc}" />
										</c:import>
									</div>
								</a>
							</c:when>
							<c:otherwise>
								<a href="#;" onclick="nak_sub(${item.nak_id})" class="pic lh100"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>'/></a>
							</c:otherwise>
						</c:choose>
						
						<a href="#;"onclick="nak_sub('${item.nak_id}')" class="subject">
							<span class="cate">[${fn:split(item.co_addr2_2,' ')[0]} ${fn:split(item.co_addr2_2,' ')[1]}]</span>
							<em>${fn:substring(item.co_nm,0,13)}
							<c:if test="${fn:length(item.co_nm)>12 }">...</c:if>
							</em>
							<c:set var="TextValue" value="${item.co_fish}"/>
					
							<span class="bestfish"><b>주요어종 : </b>${fn:substring(fn:replace(TextValue,'2',''),0,15)}
								<c:if test="${fn:length(fn:replace(TextValue,'2','')) > 14 }">...</c:if>
							</span>
						</a>
<%-- 						<a href="#;"onclick="nak_sub('${item.nak_id}')" class="search_square"><i class="fa fa-search" aria-hidden="true"></i></a> --%>
						<ul class="eyes_heart floats">

						</ul>
					</li>
					</c:forEach>
					<c:if test="${fish_list eq null }">
					<li>
						<a class="subject" style="width:100%">정보가 없습니다.</a>
					</li>
				</c:if>
					
				</ul>
			</div>
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
			<div id="btnArea" class="noupline">
				<ul class="floats">
					<li class="fr">
						<a href="/info/m/fishjob/login.do" class="btn_report btn_red">정보등록신청</a>
					</li>
				</ul>
			</div>
		</section>
	</div>
	</form>
	<form id="veiw_go" method="post">
		<input type="hidden" name="nak_id" id="nak_id" />
	</form>
<script type="text/javascript">


function order_id(){
	$('#searchType').val('id');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/m/list.do");
	$("#frm").submit();
}

function order_kor(){
	$('#searchType').val('kor');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/m/list.do");
	$("#frm").submit();
}

function order_views(){
	$('#searchType').val('views');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/m/list.do");
	$("#frm").submit();
}


function nak_sub(nak_id){
$('#nak_id').val(nak_id);
$("#veiw_go").attr("action", "/info/fishjob/m/view.do");
$('#veiw_go').submit();

}

$('document').ready(function()	{
	
	if($('#searchType').val()=="kor"){
		$('#ids').removeClass();
		$('#views').removeClass();
		$('#kor').addClass('on');
	}else if($('#searchType').val()=="views"){
		$('#ids').removeClass();
		$('#kor').removeClass();
		$('#views').addClass('on');
	}else if($('#searchType').val()=="id"){
		$('#views').removeClass();
		$('#kor').removeClass();
		$('#ids').addClass('on');
	}
	
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
	
	 // 시/도 선택 박스 초기화
	 $("select[name^=searchSido]").each(function() {
	  $selsido = $(this);  
	  $.each(eval(area0), function(i,v) {
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
	 
	
	//체크에 따라 카테고리 클래스 온으로 넣어주는거
	 var chksun=$("input:checkbox:checked").length;
	
	 if($('input:checkbox[name="website"]').is(":checked")==true){
		 if(chksun==2){
			 if($('input:checkbox[name="searchBoat"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#boatca').addClass('on');
			 }else if($('input:checkbox[name="searchRiver"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#rica').addClass('on');
				 $("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
								<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd>\
						</dl>');
			 }else if($('input:checkbox[name="searchSea"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#seaca').addClass('on');
				 $("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
								<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd></dl>');
			 }else{
				 $('#cata li').removeClass();
				 $('#allca').addClass('on');
			 }
		 }
	 }else{
		 if(chksun==1){
			 if($('input:checkbox[name="searchBoat"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#boatca').addClass('on');
			 }else if($('input:checkbox[name="searchRiver"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#rica').addClass('on');
				 $("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
								<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd>\
						</dl>');
			 }else if($('input:checkbox[name="searchSea"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#seaca').addClass('on');
				 $("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
								<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd></dl>');
			 }else{
				 $('#cata li').removeClass();
				 $('#allca').addClass('on');
			 }
		 }
	 }
	 

	});


$("#searchBoat").change(function(){
	var chksun=$("input:checkbox:checked").length;
	if($("#searchBoat").is(":checked")){
		$("#searchco_info dl").remove();
	}else{
		if(chksun==1 && $('#searchRiver').is(":checked")){
			$("#searchco_info").append('<dl>\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
					</dd>\
				</dl>');
		}else if(chksun==1 && $('#searchSea').is(":checked")){
			$("#searchco_info").append('<dl>\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
						<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
						<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
					</dd></dl>');
		}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
			if($('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl>\
												<dt>· 상세구분</dt>\
												<dd>\
													<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
													<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
													<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
													<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
													<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
													<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												</dd>\
											</dl>');
				
			}else if($('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl>\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						</dd></dl>');
			}else {
				$("#searchco_info dl").remove();
			}
		}
	}
});

$("#searchRiver").change(function(){
	//체크에 따라 카테고리 클래스 온으로 넣어주는거
	 var chksun=$("input:checkbox:checked").length;
	 if(($('input:checkbox[name="website"]').is(":checked")==true && chksun==2)||($('input:checkbox[name="website"]').is(":checked")==false && chksun==1 )){
		if($('#searchRiver').is(":checked")){
			$("#searchco_info").append('<dl>\
											<dt>· 상세구분</dt>\
											<dd>\
												<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
												<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
											</dd>\
										</dl>');
			
		}else{
			if(chksun==1 && $('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl>\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
							<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						</dd>\
					</dl>');
			}else if(chksun==1 && $('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl>\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						</dd></dl>');
			}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
				if($('#searchRiver').is(":checked")){
					$("#searchco_info").append('<dl>\
													<dt>· 상세구분</dt>\
													<dd>\
														<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
														<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
													</dd>\
												</dl>');
					
				}else if($('#searchSea').is(":checked")){
					$("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
								<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd></dl>');
				}else {
					$("#searchco_info dl").remove();
				}
			}		
		}
	 }else{
			$("#searchco_info dl").remove();
	 }
});

$("#searchSea").change(function(){
	//체크에 따라 카테고리 클래스 온으로 넣어주는거
	 var chksun=$("input:checkbox:checked").length;
	if(($('input:checkbox[name="website"]').is(":checked")==true && chksun==2)||($('input:checkbox[name="website"]').is(":checked")==false && chksun==1 )){
		if($('#searchSea').is(":checked")){
			$("#searchco_info").append('<dl>\
											<dt>· 상세구분</dt>\
											<dd>\
												<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
												<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
												<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
											</dd></dl>');
		}else{
			if(chksun==1 && $('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl>\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
							<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						</dd>\
					</dl>');
			}else if(chksun==1 && $('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl>\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
						</dd></dl>');
			}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
				if($('#searchRiver').is(":checked")){
					$("#searchco_info").append('<dl>\
													<dt>· 상세구분</dt>\
													<dd>\
														<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span></span>민물낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span></span>민물낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span></span>낚시까페</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span></span>민물수상좌대</label>\
														<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span></span>민물낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
														<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span></span>민물낚시체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;\
													</dd>\
												</dl>');
					
				}else if($('#searchSea').is(":checked")){
					$("#searchco_info").append('<dl>\
							<dt>· 상세구분</dt>\
							<dd>\
								<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span></span>바다낚시터(실외)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span></span>바다낚시터(실내)</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span></span>바다수상좌대</label>\
								<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span></span>해상펜션</label>&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span></span>바다낚시공원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span></span>바다체험장</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span></span>갯바위출조</label>&nbsp;&nbsp;&nbsp;\
								<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span></span>바다낚시출조점</label>&nbsp;&nbsp;&nbsp;&nbsp;\
							</dd></dl>');
				}else {
					$("#searchco_info dl").remove();
				}
			}	
		}
		
	}else{
			$("#searchco_info dl").remove();
	 }
});




</script>
<%@include file="../../layout/m/tail.jsp"%>