<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishjob" />


<%@include file="../layout/newHead.jsp"%>

<script type="text/javascript">
function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "/info/fishjob/list.do");
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
<input type="hidden" name="getco_info" id="getco_info" value="${search_info}"/>
<input type="hidden" name="tab_cate" id="tab_cate" value="" />
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
			<div class="map_box">
				<h3>낚시터 정보검색</h3>
				<img src="/naksinuri_original/common_main/img/mapImg00.png" id="imgMap" alt="전국지도" usemap="#mapChange" />
				<map name="mapChange" id="mapChange">
					<area shape="poly" coords="107,77,101,73,97,76,99,86,103,91,107,87" href="#" alt="인천" onclick='changSido("인천");'/>
					<area shape="poly" coords="128,87,128,83,130,81,125,81,127,77,125,73,120,72,118,76,113,79,108,78,108,81,110,86,115,90,122,90" href="#" alt="서울특별시" onclick='changSido("서울");' />
					<area shape="poly" coords="104,92,105,93,103,94,104,98,96,98,94,101,94,103,97,103,99,104,101,109,105,107,107,108,108,110,105,114,108,120,112,125,114,124,121,125,124,122,126,123,132,128,144,117,149,118,154,115,154,111,157,112,160,109,160,99,164,94,164,92,162,89,163,88,167,86,162,83,159,83,155,78,151,79,150,64,155,62,157,58,151,52,148,51,148,47,146,45,142,45,141,47,140,46,140,40,138,40,136,42,134,42,133,39,134,36,130,35,130,32,126,32,123,35,123,36,117,35,112,38,111,40,111,43,115,45,117,46,115,49,112,51,114,54,113,56,111,56,109,53,106,53,103,53,106,55,108,57,107,59,105,61,105,71,103,71,103,65,97,65,93,62,91,59,87,62,87,68,88,73,90,75,94,76,97,77,100,74,103,74,108,78,112,78,118,73,122,71,126,73,127,75,128,78,127,81,130,81,129,85,128,88,123,91,116,91,113,89,109,87,108,82,107,82,107,89,105,91" href="#" alt="경기도" onclick='changSido("경기");' />
					<area shape="poly" coords="134,29,130,32,130,34,133,34,135,36,135,39,135,40,138,39,140,39,141,45,142,44,147,44,148,46,149,50,151,51,157,58,158,60,155,63,151,65,151,77,155,78,159,82,163,83,167,85,168,86,163,89,164,91,164,96,161,100,160,109,160,112,168,113,170,108,173,106,176,107,176,111,178,111,186,108,192,111,192,112,187,114,190,116,194,114,197,117,202,118,206,121,208,121,212,124,215,124,219,121,222,124,225,121,230,123,235,122,239,126,242,126,243,122,249,119,250,112,238,95,238,90,235,87,235,83,225,69,225,66,216,54,204,17,202,14,199,14,198,17,198,22,197,25,191,29,186,32,180,32,174,31,170,33,167,30,159,31,154,30,152,32,148,30,137,29" href="#" alt="강원도" onclick='changSido("강원");' />
					<area shape="poly" coords="210,123,203,119,197,119,193,115,190,117,187,115,191,111,187,109,182,110,179,112,175,112,175,107,173,107,170,109,168,113,161,113,159,111,158,113,155,113,154,117,149,119,145,118,133,128,134,131,138,137,139,138,135,140,131,142,131,148,133,152,134,156,136,158,141,160,144,162,142,166,141,173,144,175,145,179,148,186,152,188,157,190,163,190,167,183,167,178,171,178,171,175,168,172,166,174,161,171,164,167,163,164,164,157,159,150,163,147,166,151,167,149,165,146,169,142,172,144,175,144,175,140,180,137,183,137,185,135,189,136,190,138,195,138,196,137,196,134,198,128,204,127" href="#" alt="충청북도" onclick='changSido("충북");' />
					<area shape="poly" coords="132,141,128,140,125,137,123,138,123,143,122,146,125,148,126,150,126,153,127,155,127,159,129,161,131,159,133,157,134,156,133,152,131,147,130,143,134,140" href="#" alt="세종시" onclick='changSido("세종");' />
					<area shape="poly" coords="141,169,141,165,143,163,141,161,138,160,135,158,134,157,129,161,129,164,127,167,128,171,130,175,135,177,139,176,140,174" href="#" alt="대전광역시" onclick='changSido("대전");' />
					<area shape="poly" coords="148,186,145,180,143,175,142,174,140,176,135,177,130,177,127,172,126,167,129,164,129,161,126,158,127,155,125,148,122,146,122,139,123,136,126,138,130,140,136,139,138,138,132,129,130,128,125,123,121,125,115,125,113,125,111,128,107,129,105,121,98,117,92,114,86,115,77,119,68,129,67,131,69,136,74,140,75,143,77,146,76,152,79,155,79,161,86,164,86,169,86,174,84,176,88,178,92,185,96,188,101,185,104,184,107,179,111,179,115,182,123,184,130,182,133,185,132,189,139,192,144,190,146,191" href="#" alt="충청남도" onclick='changSido("충남");' />
					<area shape="poly" coords="160,190,163,190,165,188,168,182,167,179,171,179,171,176,169,172,166,174,164,172,161,172,163,169,164,167,163,164,164,161,164,156,160,151,163,148,165,151,168,149,165,147,168,143,171,143,172,144,175,144,175,140,179,138,183,138,185,135,187,136,190,139,196,139,196,134,198,128,202,128,208,125,210,124,213,125,216,123,220,121,222,124,226,121,228,123,235,123,239,126,242,126,243,123,247,119,250,119,252,123,253,126,252,129,252,138,255,145,255,148,251,157,251,159,253,162,252,166,252,170,247,176,247,185,245,187,249,191,247,195,248,199,250,200,253,197,256,195,258,198,253,210,250,222,245,221,240,222,237,221,236,218,231,217,226,223,220,221,214,224,207,225,201,223,199,224,196,220,195,218,197,215,199,213,205,215,206,213,207,209,209,205,210,199,207,195,202,194,198,199,196,202,193,202,193,199,190,200,188,202,190,205,194,206,193,208,188,207,189,212,191,214,191,215,186,215,189,220,188,221,183,219,176,217,177,213,176,208,172,204,166,204,160,199,162,196" href="#" alt="경상북도" onclick='changSido("경북");' />
					<area shape="poly" coords="190,221,186,215,191,215,191,214,189,209,190,208,193,208,194,206,192,204,189,204,189,202,192,199,193,202,195,202,197,200,201,197,204,195,207,196,210,198,209,201,209,204,207,206,206,208,205,211,205,214,204,215,201,213,200,213,197,215,196,218,195,220" href="#" alt="대구광역시" onclick='changSido("대구");' />
					<area shape="poly" coords="249,222,243,222,240,223,238,222,236,220,234,219,233,217,228,220,225,224,224,224,221,227,222,230,226,231,232,237,234,240,237,240,239,246,241,244,243,236,246,236,250,231,250,224" href="#" alt="울산광역시" onclick='changSido("울산");' />
					<area shape="poly" coords="233,239,228,234,226,231,224,231,220,230,221,227,225,223,223,222,218,222,214,224,211,226,205,225,199,224,195,221,188,221,185,221,184,219,179,218,175,217,176,215,176,211,171,204,166,204,160,199,154,202,150,204,142,219,144,224,144,230,142,235,141,247,145,249,145,253,150,259,150,261,147,264,145,266,148,269,153,279,163,282,196,285,206,276,210,261,210,254,212,253,215,250,219,250,224,246,226,246,231,239" href="#" alt="경상남도" onclick='changSido("경남");' />
					<area shape="poly" coords="210,260,209,254,212,253,214,250,220,250,226,246,231,241,234,239,238,241,239,245,237,247,236,249,235,252,232,256,230,259,225,262,224,264,221,264,217,263,217,261,213,262" href="#" alt="부산광역시" onclick='changSido("부산");' />
					<area shape="poly" coords="141,239,141,234,144,232,144,225,142,220,146,209,149,204,159,199,161,198,162,196,160,193,159,191,156,191,149,187,147,189,146,192,139,192,133,190,130,182,125,184,116,184,112,180,108,179,104,184,97,188,87,188,84,189,88,194,92,194,93,198,92,201,86,207,81,210,79,213,80,215,88,214,88,216,85,218,81,219,77,223,78,229,80,233,86,231,90,230,92,225,96,223,100,223,102,226,104,230,106,231,108,224,110,224,109,228,109,233,111,236,116,235,122,236,124,238,133,234,137,236" href="#" alt="전라북도" onclick='changSido("전북");' />
					<area shape="poly" coords="82,233,89,241,86,244,86,246,89,248,93,250,95,252,100,252,104,252,107,248,107,245,104,243,103,240,102,238,99,239,97,240,94,237,90,238,85,233,90,232,93,228,94,224,98,223,102,229,105,231,108,225,109,233,112,236,118,235,123,237,127,237,131,235,134,234,141,239,141,247,144,249,144,252,148,257,149,261,146,265,145,266,139,267,139,268,140,271,141,272,147,271,148,277,147,279,149,284,149,290,144,285,138,286,133,288,120,298,116,292,109,292,95,296,100,301,85,305,80,298,78,303,72,303,71,297,73,294,71,292,71,287,65,284,60,277,61,273,64,272,68,271,67,267,64,264,60,264,64,259,65,256,70,251,73,253,75,250,67,238,70,235,76,224,78,229,82,232" href="#" alt="전라남도" onclick='changSido("전남");' />
					<area shape="poly" coords="97,240,91,239,89,240,87,243,86,245,89,248,93,250,97,251,104,251,106,248,107,246,106,244,103,240,100,238" href="#" alt="광주광역시" onclick='changSido("광주");' />
					<area shape="poly" coords="124,342,137,342,139,344,145,342,154,340,161,333,161,324,154,321,143,322,139,324,130,323,116,332,115,335,118,342,122,344" href="#" alt="제주도" onclick='changSido("제주");' />
				</map>
			</div>
			<div class="search_box">
				<h4>지도에서 지역을 선택하시면 해당 지역의 낚시터 정보를 확인할 수 있습니다.</h4>
				<div class="scon">
					<dl>
						<dt>· 구분</dt>
						<dd>
							<input type="checkbox"  name="searchBoat" id="searchBoat" value="boatfishing" <c:if test ="${searchBoat eq 'boatfishing'}">checked</c:if> tabindex="-1"/><label for="searchBoat"><span tabindex="0" onkeyPress="if (event.keyCode==13){$('#searchBoat').click();}"></span>선상낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox"  name="searchSea" id="searchSea" value="seafishing"<c:if test ="${searchSea eq 'seafishing'}">checked</c:if> tabindex="-1"/><label for="searchSea"><span tabindex="0" onkeyPress="if (event.keyCode==13){$('#searchSea').click();}"></span>바다낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox"  name="searchRiver" id="searchRiver" value="riverfishing" <c:if test ="${searchRiver eq 'riverfishing'}">checked</c:if> tabindex="-1"/><label for="searchRiver"><span tabindex="0" onkeyPress="if (event.keyCode==13){$('#searchRiver').click();}"></span>민물낚시</label>
						</dd>
					</dl> 
					<div id="searchco_info" >
						
					</div>
					<dl>
						<dt>· 시/도</dt>
						<dd>
							<select class="search_select" name="searchSido" id="searchSido" title="시,도 옵션선택">
								

							</select>
						</dd>
					</dl>
					<dl>
						<dt>· 시/군/구</dt>
						<dd>
							<select class="search_select"  name="searchGugun" id="searchGugun" title="시,군,구 옵션선택">
								

							</select>
						</dd>
					</dl>
					<dl>
						<dt>· 주요어종</dt>
						<dd><input type="text" id="searchText1" name="searchText1" class="search_input" value="${searchText1}" placeholder="예) 붕어, 잉어, 도다리" title="주요어종"/></dd>
					</dl>
					<dl>
						<dt>· 상호명</dt>
						<dd><input type="text" id="searchText2" name="searchText2" class="search_input" value="${searchText2}" title="상호명"/></dd>
					</dl>
					<dl>
						<dt>· 홈페이지</dt>
						<dd><input type="checkbox" id="website" name="website" tabindex="-1" /><label for="website"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$('#website').click();}"></span>자체홈페이지 여부</label></dd>
					</dl>
	<!--  
					<dl>
						<dt>· 폐업</dt>
						<dd><input type="checkbox" id="website" name="fish" /><label for="website"><span></span>폐업지도 함께 검색</label></dd>
					</dl>
	-->
				</div>
				<div class="searchBtn"><button type="button" onclick="fnSelectInfs(1)"><i class="fa fa-search" aria-hidden="true"></i><em>검색</em></button></div>
			</div>
		</section>
		<section id="galleryList" class="list_box">
		
			<div class="tabArea tab4 mbNone">	<!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 --> 
				<ul class="floats" id="cata">
					<li id="allca" class="on"><a href="/info/fishjob/list.do" ${category_type == "all" ? 'title="전체 선택됨"' : 'title="전체"'}>전체</a></li>
					<li id="boatca"><a href="#;" onclick="boatfind()" ${category_type == "searchBoat" ? 'title="선상낚시 선택됨"' : 'title="선상낚시"'}>선상낚시</a></li>
					<li id="seaca"><a href="#;" onclick="seafind()" ${category_type == "searchSea" ? 'title="바다낚시 선택됨"' : 'title="바다낚시"'}>바다낚시</a></li>
					<li id="rica"><a href="#;" onclick="rivfind()" ${category_type == "searchRiver" ? 'title="민물낚시 선택됨"' : 'title="민물낚시"'}>민물낚시</a></li>
				</ul>
			</div>
			
			<!--  -->
			<div class="sortbox">
				<ul class="floats">
					<li id="ids" class="on"><a href="javascript:void(0)" onclick="order_id()" ${searchType == "id" || empty(searchType) ? 'title="등록순 선택됨"' : 'title="등록순"'}>등록순</a></li>	
					<li id="kor"><a href="#;" onclick="order_kor()" ${searchType == "kor" ? 'title="한글순 선택됨"' : 'title="한글순"'}>한글순</a></li>
					<li id="views"><a href="#;"  onclick="order_views()" ${searchType == "views" ? 'title="조회수순 선택됨"' : 'title="조회수순"'}>조회수순</a></li>
				</ul>
				<p class="totalNum" style="right:145px;">전체 <b class="colorSky">${fish_total}</b>건의 게시물이 있습니다. (<b class="colorSky">${pageIndex}</b>/${pagesize})</p>
				<div id="btnArea" class="noupline" style="right: 0px; position: absolute; top: -17px;">
					<ul class="floats">
						<li class="fr">
							<a href="/info/fishjob/login.do" class="btn_report btn_red" title="정보등록신청">정보등록신청</a>
						</li>
					</ul>
				</div>
			</div>
			<!--  -->
			
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${fish_list}">
					<li>
						 <a href="#;"onclick="nak_sub('${item.nak_id}')" class="pic">
					 		<c:choose>
						 		<c:when test="${item.orignl_file_nm ne null }">
							 		<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.co_mimgsrc}"/>&fileSn=<c:out value="${item.file_sn }"/>' alt='<c:out value="${item.co_nm }"/>' width="100%" height="100%" />
						 		</c:when>
						 		<c:otherwise>
						 			<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png" height="100%">
						 		</c:otherwise>
					 		</c:choose>
				         </a>
				         
						
						<a href="#;"onclick="nak_sub('${item.nak_id}')" class="subject">
							<span class="cate">[${fn:split(item.co_addr2_2,' ')[0]} ${fn:split(item.co_addr2_2,' ')[1]}]</span>
							<em>${fn:substring(item.co_nm,0,12)}
							<c:if test="${fn:length(item.co_nm)>11 }">...</c:if>
							</em>
							<c:set var="TextValue" value="${item.co_fish}"/>
					
							<span class="bestfish"><b>주요어종 : </b>${fn:substring(fn:replace(TextValue,'2',''),0,15)}
								<c:if test="${fn:length(fn:replace(TextValue,'2','')) > 14 }">...</c:if>
							</span>
						</a>
						<a href="#;"onclick="nak_sub('${item.nak_id}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.co_nm} 상세보기"></i></a>
						<ul class="eyes_heart floats">
<!--  
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
-->	
						</ul>
					</li>
					</c:forEach>
					<c:if test="${fish_list eq null }">
					<li>
						<span class="subject" style="width:100%">정보가 없습니다.</span>
					</li>
				</c:if>
					
				</ul>
			</div>
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</section>
	</div>
	</form>
	<form id="veiw_go" method="post">
		<input type="hidden" name="nak_id" id="nak_id" />
	</form>
<script type="text/javascript">

function nak_sub(nak_id){
$('#nak_id').val(nak_id);
$("#veiw_go").attr("action", "/info/fishjob/view.do");
$('#veiw_go').submit();

}

$('document').ready(function()	{
	 
	if($('#searchType').val()=="id"){
		$('#kor').removeClass();
		$('#views').removeClass();
		$('#ids').addClass('on');
	}else if($('#searchType').val()=="kor"){
		$('#ids').removeClass();
		$('#views').removeClass();
		$('#kor').addClass('on');
	}else if($('#searchType').val()=="views"){
		$('#ids').removeClass();
		$('#kor').removeClass();
		$('#views').addClass('on');
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
		 switch($("select[name^=searchSido]").val()){
			case "강원" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg01.png");
				break;
			case "서울" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg02.png");
				break;
			case "인천" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg03.png");
				break;
			case "경기" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg04.png");
				break;
			case "충남" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg05.png");
				break;
			case "대전" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg06.png");
				break;
			case "세종" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg07.png");
				break;
			case "충북" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg08.png");
				break;
			case "전북" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg09.png");
				break;
			case "전남" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg10.png");
				break;
			case "광주" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg11.png");
				break;
			case "대구" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg12.png");
				break;
			case "울산" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg13.png");
				break;
			case "부산" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg14.png");
				break;
			case "경북" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg15.png");
				break;
			case "경남" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg16.png");
				break;
			case "제주" :
				$("#imgMap").attr("src","/naksinuri_original/common_main/img/mapImg17.png");
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
				 $("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}" ></span>민물낚시터(실외)</label>\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
					</dd>\
				</dl>');
			 }else if($('input:checkbox[name="searchSea"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#seaca').addClass('on');
				 $("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
						<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
						<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
						<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
						<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
						<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
						<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
						<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
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
				 $("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
					</dd>\
				</dl>');
			 }else if($('input:checkbox[name="searchSea"]').is(":checked") ==  true){
				 $('#cata li').removeClass();
				 $('#seaca').addClass('on');
				 $("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
						<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
						<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
						<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
						<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
						<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
						<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
						<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
					</dd></dl>');
			 }else{
				 $('#cata li').removeClass();
				 $('#allca').addClass('on');
			 }
		 }
	 }
	 var getco_info=$('#getco_info').val();
	 
	 if(getco_info.indexOf("민물낚시터(실외)")!= -1){
		 $("#search_info_router").prop("checked", true);
	 }
	 if(getco_info.indexOf("민물낚시터(실내)")!= -1){
		 $("#search_info_rinner").prop("checked", true);
	 }
	 if(getco_info.indexOf("낚시까페")!= -1){
		 $("#search_info_rcafe").prop("checked", true);
	 }
	 if(getco_info.indexOf("민물수상좌대")!= -1){
		 $("#search_info_rsit").prop("checked", true);
	 }
	 if(getco_info.indexOf("민물낚시공원")!= -1){
		 $("#search_info_rpark").prop("checked", true);
	 }
	 if(getco_info.indexOf("민물낚시체험장")!= -1){
		 $("#search_info_rexp").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다낚시터(실외)")!= -1){
		 $("#search_info_souter").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다낚시터(실내)")!= -1){
		 $("#search_info_sinner").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다수상좌대")!= -1){
		 $("#search_info_ssit").prop("checked", true);
	 }
	 if(getco_info.indexOf("해상펜션")!= -1){
		 $("#search_info_scafe").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다낚시공원")!= -1){
		 $("#search_info_spark").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다체험장")!= -1){
		 $("#search_info_sexp").prop("checked", true);
	 }
	 if(getco_info.indexOf("갯바위출조")!= -1){
		 $("#search_info_stone").prop("checked", true);
	 }
	 if(getco_info.indexOf("바다낚시출조점")!= -1){
		 $("#search_info_school").prop("checked", true);
	 }
	 
	 
	 

	});



function boatfind(){
	$('input:checkbox[name="searchBoat"]').attr("checked", true);
	$('input:checkbox[name="searchRiver"]').attr("checked", false);
	$('input:checkbox[name="searchSea"]').attr("checked", false);
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
}

function order_id(){
	$('#searchType').val('id');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
}

function order_kor(){
	$('#searchType').val('kor');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
}

function order_views(){
	$('#searchType').val('views');
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
}


function seafind(){
	$('input:checkbox[name="searchSea"]').attr("checked", true);
	$('input:checkbox[name="searchBoat"]').attr("checked", false);
	$('input:checkbox[name="searchRiver"]').attr("checked", false);
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
	
}
function rivfind(){
	$('input:checkbox[name="searchRiver"]').attr("checked", true);
	$('input:checkbox[name="searchBoat"]').attr("checked", false);
	$('input:checkbox[name="searchSea"]').attr("checked", false);
	$("#pageIndex").val(1);	
	$("#frm").attr("action", "/info/fishjob/list.do");
	$("#frm").submit();
	
}

$("#searchBoat").change(function(){
	var chksun=$("input:checkbox:checked").length;
	if($("#searchBoat").is(":checked")){
		$("#searchco_info dl").remove();
	}else{
		if(chksun==1 && $('#searchRiver').is(":checked")){
			$("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
					</dd>\
				</dl>');
		}else if(chksun==1 && $('#searchSea').is(":checked")){
			$("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
						<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
						<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
						<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
						<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
						<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
						<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
						<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
					</dd></dl>');
		}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
			if($('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
					</dd></dl>');
			}else if($('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
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
			$("#searchco_info").append('<dl class="d_type">\
				<dt>· 상세구분</dt>\
				<dd>\
					<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
					<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
					<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
					<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
					<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
					<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
				</dd>\
			</dl>');
			
		}else{
			if(chksun==1 && $('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
							<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
							<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
							<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
							<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
							<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
						</dd>\
					</dl>');
			}else if(chksun==1 && $('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
						</dd></dl>');
			}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
				if($('#searchRiver').is(":checked")){
					$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
							<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
							<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
							<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
							<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
							<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
						</dd>\
					</dl>');
					
				}else if($('#searchSea').is(":checked")){
					$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
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
			$("#searchco_info").append('<dl class="d_type">\
				<dt>· 상세구분</dt>\
				<dd>\
					<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
					<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
					<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
					<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
					<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
					<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
					<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
					<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
				</dd></dl>');
		}else{
			if(chksun==1 && $('#searchRiver').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
						<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
						<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
						<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
						<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
						<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
					</dd>\
				</dl>');
			}else if(chksun==1 && $('#searchSea').is(":checked")){
				$("#searchco_info").append('<dl class="d_type">\
					<dt>· 상세구분</dt>\
					<dd>\
						<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
						<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
						<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
						<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
						<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
						<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
						<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
						<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
					</dd></dl>');
			}else if(chksun==2 && $('input:checkbox[name="website"]').is(":checked")==true){
				if($('#searchRiver').is(":checked")){
					$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_router" name="search_info" value="민물낚시터(실외)" /><label for="search_info_router"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_router\').click();}"></span>민물낚시터(실외)</label>\
							<input type="checkbox" id="search_info_rinner" name="search_info"  value="민물낚시터(실내)" /><label for="search_info_rinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rinner\').click();}"></span>민물낚시터(실내)</label>\
							<input type="checkbox" id="search_info_rcafe" name="search_info"  value="낚시까페"/><label for="search_info_rcafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rcafe\').click();}"></span>낚시까페</label>\
							<input type="checkbox" id="search_info_rsit" name="search_info"  value="민물수상좌대"/><label for="search_info_rsit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rsit\').click();}"></span>민물수상좌대</label>\
							<input type="checkbox" id="search_info_rpark" name="search_info"  value="민물낚시공원"/><label for="search_info_rpark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rpark\').click();}"></span>민물낚시공원</label>\
							<input type="checkbox" id="search_info_rexp" name="search_info"  value="민물낚시체험장"/><label for="search_info_rexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_rexp\').click();}"></span>민물낚시체험장</label>\
						</dd>\
					</dl>');
					
				}else if($('#searchSea').is(":checked")){
					$("#searchco_info").append('<dl class="d_type">\
						<dt>· 상세구분</dt>\
						<dd>\
							<input type="checkbox" id="search_info_souter" name="search_info" value="바다낚시터(실외)"/><label for="search_info_souter"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시터(실외)</label>\
							<input type="checkbox" id="search_info_sinner" name="search_info"  value="바다낚시터(실내)"/><label for="search_info_sinner"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sinner\').click();}"></span>바다낚시터(실내)</label>\
							<input type="checkbox" id="search_info_ssit" name="search_info"  value="바다수상좌대"/><label for="search_info_ssit"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_ssit\').click();}"></span>바다수상좌대</label>\
							<input type="checkbox" id="search_info_scafe" name="search_info"  value="해상펜션"/><label for="search_info_scafe"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_scafe\').click();}"></span>해상펜션</label>\
							<input type="checkbox" id="search_info_spark" name="search_info"  value="바다낚시공원"/><label for="search_info_spark"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_souter\').click();}"></span>바다낚시공원</label>\
							<input type="checkbox" id="search_info_sexp" name="search_info"  value="바다체험장"/><label for="search_info_sexp"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_sexp\').click();}"></span>바다체험장</label>\
							<input type="checkbox" id="search_info_stone" name="search_info"  value="갯바위출조"/><label for="search_info_stone"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_stone\').click();}"></span>갯바위출조</label>\
							<input type="checkbox" id="search_info_school" name="search_info"  value="바다낚시출조점"/><label for="search_info_school"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$(\'#search_info_school\').click();}"></span>바다낚시출조점</label>\
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

function changSido(sido){
	
	$("#searchSido").val(sido).prop("selected", true);
	$('#searchSido').change();
	
}

//1024px 이하에서 정보등록신청버튼 위치 변경
if (matchMedia("screen and (max-width: 1024px)").matches) {
	$('#pagenation').after($('#btnArea'));
	$('#btnArea').css('position','static');
} 

</script>
<%@include file="../layout/tail.jsp"%>