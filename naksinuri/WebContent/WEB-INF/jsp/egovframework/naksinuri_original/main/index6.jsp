<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="indexpage" />


<%@include file="../naksinuri/layout/head.jsp"%>

<form:form commandName="viewform" id="viewform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<form:form id="view_go" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="nak_id" id="nak_id" />
</form:form>
	<input type="hidden" id="pageUnit" value=3 />
	<input type="hidden" id="pageIndex" value=1 />
	<input type="hidden" id="fishing_type" value="boatfishing" />
	<c:set var="pagesize" value="${fitot/pageUnit}"/>
<%-- 	<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" /> --%>
	<input type="hidden" id="pagesize" value="${i}" />
	<!-- 메인 콘텐츠 { -->

	<div id="mainContainer">
		<!-- 낚시정보검색, 링크 4개 { -->
		<article id="mainSearchBoardconBox">
			<div class="respon">
				<form name="frm" id="frm" method="post">
					<section id="mainSearch" class="floats">
						<%--
						<div class="tab_mnu">
							<ul class="floats">
								<li class="on" id="jobin"><a href="#;" onclick="infoch()">낚시터정보</a></li>
								<li id="induin"><a href="#;" onclick="induch()">낚시산업정보</a></li>
							</ul>
						</div>
						<div class="map_box">
							<h3 id="maptitle">낚시터 정보검색</h3>
							<h3 id="maptitle2" style="display:none;">낚시산업 정보검색</h3>
							<img src="/naksinuri_original/common_main/img/mapImg00.png" id="imgMap" alt="낚시산업 정보 검색 지역" usemap="#mapChange" />
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
						<div class="search_box" id="setsyle">
							
							<input type="hidden" id="req_sido" value="${searchSido}" />
							<input type="hidden" id="req_gugun" value="${searchGugun}" />
							<h4>지도에서 지역을 선택하시면 빠른 검색이 가능합니다.</h4>
							<div class="scon">
								<dl>
									<dt>· 구분</dt>
									<dd>
										<input type="checkbox" id="searchBoat" name="searchBoat" value="boatfishing"/><label for="searchBoat"><span></span>선상낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="searchSea" name="searchSea" value="seafishing"/><label for="searchSea"><span></span>바다낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="searchRiver" name="searchRiver" value="riverfishing"/><label for="searchRiver"><span></span>민물낚시</label>
									</dd>
								</dl>								
								<dl>
									<dt>· 시/도</dt>
									<dd>
										<select class="search_select" name="searchSido" id="searchSido" onchange="changeMap();">
											
										</select>
									</dd>
								</dl>
								<dl>
									<dt>· 시/군/구</dt>
									<dd>
										<select class="search_select" name="searchGugun" id="searchGugun">
											
										</select>
									</dd>
								</dl>
								<dl>
									<dt>· 주요어종</dt>
									<dd><input type="text" name="searchText" id="searchText" class="search_input" placeholder="예) 붕어, 잉어, 도다리" /></dd>
								</dl>
								<dl>
									<dt>· 사업자명</dt>
									<dd><input type="text" id="searchText2" name="searchText2" class="search_input" /></dd>
								</dl>
								<dl>
									<dt>· 홈페이지</dt>
									<dd><input type="checkbox" id="website" name="website" /><label for="website"><span></span>자체홈페이지 여부</label></dd>
								</dl>
							</div>
							<div class="searchBtn"><button type="submit" onclick="fnSelectInfs()"><i class="fa fa-search" aria-hidden="true"></i><em>검색하기</em></button></div>
						</div>
						 --%>
						 
						 <ul>
						 
						<c:forEach var="scrollimg" items="${scrollimg}">
							<li><a href="${scrollimg.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${scrollimg.img_cont}"/>&fileSn=<c:out value="${scrollimg.img_cont }"/>' ' alt="낚시누리 메인 슬라이드 배너 3"/></a></li>
						</c:forEach> 
						 	
						</ul>
						<script>
							$('#mainSearch ul').bxSlider({
								auto: true,
								controls: false,
								pause: 3000
							});
						</script>
						
					</section>
				</form>
				<section id="mainBoardcon">
					<ul class="floats">
					<c:forEach var="mid_img1" items="${mid1_list}">
						<c:choose>
							<c:when test="${mid_img1.orignl_file_nm eq null }">
								<li class="b1"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
							</c:when>
							<c:otherwise>
								<li class="b1"><a href="${mid_img1.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img1.img_cont}"/>&fileSn=<c:out value="${mid_img1.file_sn}"/>'   width="100%" height="100%"  alt="낚시누리 메인 이미지1"/></em><span>${mid_img1.img_subject}</span></a></li>
							</c:otherwise>	
						</c:choose>
					</c:forEach>	
					<c:forEach var="mid_img2" items="${mid2_list}">
						<c:choose>
							<c:when test="${mid_img2.orignl_file_nm eq null }">
								<li class="b1"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
							</c:when>
							<c:otherwise>
								<li class="b2"><a href="${mid_img2.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img2.img_cont}"/>&fileSn=<c:out value="${mid_img2.file_sn }"/>'  width="100%" height="100%"  alt="낚시누리 메인 이미지2"/></em><span>${mid_img2.img_subject}</span></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:forEach var="mid_img3" items="${mid3_list}">
						<c:choose>
							<c:when test="${mid_img3.orignl_file_nm eq null }">
								<li class="b3"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
							</c:when>
							<c:otherwise>
								<li class="b3"><a href="${mid_img3.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img3.img_cont}"/>&fileSn=<c:out value="${mid_img3.file_sn }"/>'  width="100%" height="100%"  alt="낚시누리 메인 이미지3"/></em><span>${mid_img3.img_subject}</span></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:forEach var="mid_img4" items="${mid4_list}">
						<c:choose>
							<c:when test="${mid_img4.orignl_file_nm eq null }">
								<li class="b4"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
							</c:when>
							<c:otherwise>
								<li class="b4"><a href="${mid_img4.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img4.img_cont}"/>&fileSn=<c:out value="${mid_img4.file_sn }"/>'  width="100%" height="100%" alt="낚시누리 메인 이미지4"/></em><span>${mid_img4.img_subject}</span></a></li>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
					</ul>
				</section>
			</div>
		</article>
		<!-- } 낚시정보검색, 링크 4개 -->

		<!-- 6개 링크 { -->
		<article id="mainSixlink">
			<div class="respon">
				<ul class="floats">
					
					<!-- <li><a href="http://www.law.go.kr/lsEfInfoP.do?lsiSeq=162586#" target="_blank" class="sixlink1">낚시 관리 및 육성법</a></li> -->
					<li><a href="http://www.law.go.kr/법령/낚시관리및육성법" target="_blank" class="sixlink1">낚시 관리 및 육성법</a></li>
					
					<li><a href="/promotion/policy/list.do" class="sixlink2" target="_blank">낚시정책안내</a></li>
					<!--<li><a href="http://www.nifs.go.kr/frcenter/species/?=species_search" target="_blank" class="sixlink3">수산생물검색</a></li>-->
					<li><a href="/promotion/plocation/list.do" class="sixlink4" target="_blank">낚시금지구역</a></li>
					<li><a href="/info/fishjob/list.do" class="sixlink9">낚시터정보</a></li>
					<li><a href="/info/industry/list.do" class="sixlink10">낚시산업정보</a></li>
					<!--<li><a href="http://www.nifs.go.kr/frcenter/species/?_p=species_prohibited" target="_blank" class="sixlink5">포획금지생물종</a></li>-->
					<!-- 
					<li><a href="http://www.nifs.go.kr/frcenter/species/?_p=species_clss_info" target="_blank" class="sixlink6">포획금지기간</a></li>
					-->
					<li><a href="http://www.nifs.go.kr/lmo/scb/index2.lmo" target="_blank" class="sixlink8">수산생물포획금지</a></li>
					
					<li><a href="https://www.naksiedu.kr/" target="_blank" class="sixlink3">낚시전문교육</a></li>
					<li><a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank" class="sixlink7">해양정보 인터넷방송</a></li>
				</ul>
			</div>
		</article>
		<!-- } 6개 링크 -->

		<!-- 낚시뉴스, 롤링이벤트, 배너 { -->
		<article id="mainLatestBox" class="respon floats">
			<section id="mainLatest" class="main_conbox main_latestbox">
				<div class="mnu">
					<ul class="floats">
						<li class="on" id="main_notice" onclick="noticechange()"><h2>공지사항</h2></li>
						<li id="main_news" onclick="newschange()"><a href="#;">낚시뉴스</a></li>
					</ul>
				</div>
	 			
				<div class="latest_list">
					<%--
					<ul>
						<c:if test="${fn:length(main_news) eq '' }">
						<li><em>작성된 글이 없습니다.</em><span></span></a></li>
						</c:if>
						<c:forEach var="news" items="${main_news }">
						<li><a href="${news.link }" target="_blank"><em>${news.title }</em><span>${news.regdate}</span></a></li>
						</c:forEach>
					</ul>
					<div id="boardmore" class="more"><a href="/sosig/news/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
					 --%>
					 <ul>
						<c:if test="${fn:length(main_notice) eq '' }">
						<li><em>작성된 글이 없습니다.</em><span></span></a></li>
						</c:if>
						<c:forEach var="notice" items="${main_notice }">
						<li><a href="#;" onclick="noticeview('${notice.bo_sn}')"><em>${notice.bo_subject}</em></a></li>
						</c:forEach>
					</ul>
					<div id="boardmore" class="more"><a href="/sosig/notice/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
				</div>
				
			</section>
			<section id="mainFixedMenuBanner" class="main_conbox">
				<ul>
					<li><a href="/sosig/congress/write.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_1.png"" alt="no_image" width="100%" /></a></li>
					<li><a href="/sosig/congress/check.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_2.png"" alt="no_image" width="100%" /></a></li>
				</ul>
			</section>
			<!-- //메뉴 이동 : 2018.09.20 요청// -->
			<%--
			<section id="mainRollingBanner" class="main_conbox">
				<ul>
					<c:forEach var="banner1" items="${main_banner1}">
						<c:choose>
							<c:when test="${banner1.orignl_file_nm eq null }">
								<li><img src="/naksinuri_original/common_main/img/noImage_writer.png"" alt="no_image" width="100%" height="100%" /></li>
							</c:when>
							<c:otherwise>
								<li><a href="${banner1.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner1.img_cont}"/>&fileSn=<c:out value="${banner1.file_sn}"/>' alt="낚시누리 메인 배너이미지1"/></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>	
					
					<c:forEach var="banner2" items="${main_banner2}">
						<li><a href="${banner2.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner2.img_cont}"/>&fileSn=<c:out value="${banner2.file_sn}"/>'alt="낚시누리 메인 배너이미지2"/></a></li>
					</c:forEach>	
					<c:forEach var="banner3" items="${main_banner3}">
						<li><a href="${banner3.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner3.img_cont}"/>&fileSn=<c:out value="${banner3.file_sn}"/>'alt="낚시누리 메인 배너이미지3"/></a></li>			
					</c:forEach>	

				</ul>
				<script>
					$('#mainRollingBanner ul').bxSlider({
						auto: true,
						controls: false,
						pause: 5000
					});
				</script>
			</section>
			 --%>
			<section id="mainFixBanner" class="main_conbox">
				<c:forEach var="banner3" items="${main_banner3}">
                  <li><a href="${banner3.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner3.img_cont}"/>&fileSn=<c:out value="${banner3.file_sn}"/>'alt="낚시누리 메인 배너이미지3"/></a></li>
                </c:forEach>
                           <!--
                                <a href="/sosig/congress/write.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_3.jpg"" alt="no_image" width="100%" /></a>^M
                                -->
				<%--
				<!-- //낚시대회 홍보 동영상 : 2018.09.20 요청// -->
				<!-- 
				<iframe width="400px" height="240px" src="https://www.youtube.com/embed/yw957wmPktc" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				-->
				<div id="myElement"></div>
		        <script type="text/javascript">
		        	var filen = $('#filen').val();
		        	$(document).ready(function() {
		        		jwplayer("myElement").setup({					
			                file: "/movupload/2018_mof_naksi_video.mp4",
			                width:"400px",
			                height:"240px",
			                align:"center",
			                autostart: false,
			                mute: true,
			            });
		        		jwplayer().onReady(function() { 
		                	jwplayer().play();
		                });
	        		}); 
		        </script>
		         --%>		
				<%--
				<c:forEach var="rightbanner" items="${right_banner}">
	
					<c:choose>
							<c:when test="${rightbanner.orignl_file_nm eq null }">
								<li><img src="/naksinuri_original/common_main/img/noImage_big.png" alt="no_image" /></li>
							</c:when>
							<c:otherwise>
								<li><a href="${rightbanner.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${rightbanner.img_cont}"/>&fileSn=<c:out value="${rightbanner.file_sn}"/>'alt="낚시누리 메인우측이미지"/></a></li>
							</c:otherwise>
					</c:choose>	
					
				</c:forEach>
				--%>
			</section>
		</article>
		<!-- } 낚시뉴스, 롤링이벤트, 배너 -->

		<!-- 민물낚시 조황소식, 바다낚시 조황소식 { -->
		<article id="mainLatestBox" class="respon floats updownpd">
			<section id="mainGallery" class="main_conbox main_latestbox">
			
			<!-- 20171023 민물조황소식에서 낚시 여행기로 변경 -->
				<h2>낚시 <span>여행기</span></h2>
				<div class="latest_gallery">
					<ul class="floats">
						<c:forEach var="river" items="${river_angling }">
							<li>
								<a href="#;" onclick="rangview('${river.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${river.file_sn ne null }">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${river.bo_main_img}"/>&fileSn=<c:out value="${river.file_sn}"/>' alt="${river.orignl_file_nm}" width="100%" height="100%" alt="낚시여행기 이미지"/>
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg" alt="no_image" width="100%" height="100%" />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="rangview('${river.bo_sn}')" class="tit"><span>
							
									<c:choose>
										<c:when test="${fn:length(river.bo_subject) > 15}">
								            <c:out value="${fn:substring(river.bo_subject,0,14)}"/>...</span>
								         </c:when>
								         <c:otherwise>
											${river.bo_subject }</span>
								         </c:otherwise>
									</c:choose>
								</a>
							</li>
						</c:forEach>
					</ul>
					<div class="more"><a href="/share/travel/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
				</div>
			</section>
			<section id="mainGallery" class="main_conbox main_latestbox fr">
				<h2>낚시 <span>컬럼</span></h2>
				<!-- 
				//20171023 바다조황소식에서 낚시 핫이슈로 변경 
				//2018.10.26 낚시컬럼 -> 낚시대회로 교체 
				//2019.06.18 낚시대회 -> 낚시컬럼으로 교체
				-->
				<div class="latest_gallery">
					<%-- 낚시컬럼 --%>
					<ul class="floats">
						<c:forEach var="sea" items="${sea_angling}">
							<li>
								<a href="#;" onclick="sangview('${sea.bo_sn}')" class="pic">
									<c:choose>
									<c:when test="${sea.file_sn ne null }">
										<div style="width:100%;height:190px;background:url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${sea.bo_main_img}"/>&fileSn=<c:out value="${sea.file_sn }"/>') top no-repeat;background-size:cover;"></div>
									</c:when>
									<c:otherwise>
										<div style="width:100%;height:190px;background:url('<c:url value='/naksinuri_original/common_main/img/noImage_gen.jpg'/>') top no-repeat;background-size:cover;"></div>
									</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="sangview('${sea.bo_sn}')" class="tit"><span>
									<c:choose>
										<c:when test="${fn:length(sea.bo_subject) > 15}">
								            <c:out value="${fn:substring(sea.bo_subject,0,14)}"/>...</span>
								         </c:when>
								         <c:otherwise>
											${sea.bo_subject }</span>
								         </c:otherwise>
									</c:choose>
								</a>
							</li>
						</c:forEach>
					</ul>
					<div class="more"><a href="/share/column/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="낚시컬럼 더보기" /></a></div>
					<%-- 낚시대회
					<ul class="floats">
						<c:forEach var="sea" items="${main_naksi_congress}">
							<li>
								<a href="#;" onclick="sangview('${sea.bo_sn}')" class="pic">
									<c:choose>
									<c:when test="${sea.file_sn ne null }">
										<div style="width:100%;height:190px;background:url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${sea.bo_main_img}"/>&fileSn=<c:out value="${sea.file_sn }"/>') top no-repeat;background-size:cover;"></div>
									</c:when>
									<c:otherwise>
										<div style="width:100%;height:190px;background:url('<c:url value='/naksinuri_original/common_main/img/noImage_gen.jpg'/>') top no-repeat;background-size:cover;"></div>
									</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="sangview('${sea.bo_sn}')" class="tit"><span>
									<c:choose>
										<c:when test="${fn:length(sea.bo_subject) > 15}">
								            <c:out value="${fn:substring(sea.bo_subject,0,14)}"/>...</span>
								         </c:when>
								         <c:otherwise>
											${sea.bo_subject }</span>
								         </c:otherwise>
									</c:choose>
								</a>
							</li>
						</c:forEach>
					</ul>
					<div class="more"><a href="/sosig/congress/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="낚시대회 더보기" /></a></div>
					--%>
					
				</div>
			</section>
		</article>
		<!-- } 민물낚시 조황소식, 바다낚시 조황소식 -->

		<!-- 추천낚시터, 페이스북, 기타 6개 링크 { -->
		<article id="mainLatestBox" class="respon floats">
			<section id="mainFisherySevenlink">
				<div id="mainFishery" class="main_conbox">
					<h2>낚시터 <span>정보</span></h2>
					<div class="tablink">
						<ul>
							<li id="boat_job" class="on"><a href="#;" onclick="boatchange()">선상낚시</a></li>
							<li id="sea_job"><a href="#;" onclick="seachange()">바다낚시</a></li>
							<li id="river_job"><a href="#;" onclick="riverchange()">민물낚시</a></li>
						</ul>
					</div>
			
					<div class="fishery_space" id="choo" >
						<ul class="floats">
							<c:forEach var="boat" items="${choo_job}">
								<li>
									<a href="#;" onclick="nak_sub('${boat.nak_id}')" class="pic">
										<c:choose>
											<c:when test="${boat.orignl_file_nm eq null}">
												<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg"" alt="no_image" width="100%" height="100%" />
											</c:when>
											<c:otherwise>
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${boat.co_mimgsrc}"/>&fileSn=<c:out value="${boat.file_sn}"/>' alt="${boat.orignl_file_nm}" width="238px" height="150px" alt="낚시터정보 이미지"/>
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="nak_sub('${boat.nak_id}')" class="tit"><em>${boat.co_nm }</em><span>${boat.co_addr_2 }</span></a>
								</li>
							</c:forEach>
						</ul>
						<div class="arrow_box">
							<em>추천낚시터</em>
							<span><a href="#;" id="prev_choo"><i class="fa fa-angle-left" aria-hidden="true"></i></a><a href="#;" id="next_choo"><i class="fa fa-angle-right" aria-hidden="true"></i></a></span>
						</div>
					</div>
					<div class="more"><a href="#;" onclick="fishjob_more()"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
				</div>
				<script>
					function fishjob_more(){
						var param ="";
						
						if($('#fishing_type').val()=="boatfishing"){
							param = "searchBoat=boatfishing";
						}
						else if($('#fishing_type').val()=="seafishing"){
							param = "searchSea=seafishing";
						}
						else if($('#fishing_type').val()=="riverfishing"){
							param = "searchRiver=riverfishing";
						}
						location.href="/info/fishjob/list.do?"+param;
						
					}
				</script>
				<div id="mainSevenlink" class="main_conbox">
					<ul class="floats">
						<li><a href="http://www.kma.go.kr/index.jsp" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink1.png" alt="날씨정보" /> 날씨정보</a></li>
						<li><a href="http://www.khoa.go.kr/swtc/main.do" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink2.png" alt="조석정보" /> 조석정보</a></li>
						<li><a href="http://www.khoa.go.kr/koofs/kor/seawf/sea_wf.do?menuNo=01&link=" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink3.png" alt="조류예보" /> 조류예보</a></li>
						<li><a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=31201000" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink4.png" alt="바다갈라짐" /> 바다갈라짐</a></li>
						<li><a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=53201000" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink5.png" alt="해양교실" /> 해양교실</a></li>
						<li><a href="http://www.khoa.go.kr/koofs/kor/observation/obs_real.do?obsItem=WT_TEM&imgIdx=05" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink6.png" alt="수온정보" /> 수온정보</a></li>
						<li><a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank"><img src="/naksinuri_original/common_main/img/ico_sevenLink7.png" alt="해양정보 인터넷방송" /> 해양정보 인터넷방송</a></li>
					</ul>
				</div>
			</section>
			<section id="mainFacebook" class="main_conbox">
				<div class="fb_link"><a href="https://www.facebook.com/nurinaksi/" target="_blank"><i class="fa fa-facebook-square" aria-hidden="true"></i></a></div>
				<iframe src="//www.facebook.com/plugins/likebox.php?href=https%3A%2F%2Fwww.facebook.com%2Fnurinaksi&amp;width=390&amp;height=360&amp;colorscheme=light&amp;show_faces=false&amp;header=false&amp;stream=true&amp;show_border=false&amp;appId=467073916685768" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:390px; height:360px;" allowTransparency="true"></iframe>
			</section>
		</article>
		<!-- } 추천낚시터, 페이스북, 기타 6개 링크 -->
		
		<article id="mainGoLink" class="respon floats">
			<ul>
				<li><a href="http://www.mof.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink01.png" alt="해양수산부" /></a></li>
				<li><a href="https://www.fipa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink02.png" alt="KFPA 한국어촌어항공단" /></a></li>
				<li><a href="https://www.facebook.com/FIPAkor" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink10.png" alt="한국어촌어항공단 공식 페이스북" /></a></li>
				<li><a href="http://www.sealife.go.kr" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink09.png" alt="귀어귀촌" /></a></li>
				<li><a href="http://www.kff.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink03.png" alt="KFF 한국낚시연합" /></a></li>
				<li><a href="http://www.nifs.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink04.png" alt="국립수산과학원" /></a></li>
				<li><a href="http://www.fira.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink05.png" alt="FIRA 한국수산자원관리공단" /></a></li>
				<li><a href="http://www.kst.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink06.png" alt="선박안전기술공단" /></a></li>
				<li><a href="http://www.khoa.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink07.png" alt="국립해양조사원 KHOA" /></a></li>
				<li><a href="http://www.koreafca.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink08.png" alt="한국낚시업중앙회" /></a></li>
			</ul>
			<div class="outside">
				<span id="slider-prev"></span>
				<span id="slider-next"></span>
			</div>
			<script>
				$('#mainGoLink ul').bxSlider({
					auto: true,
					pager: false,
					minSlides: 5,
					maxSlides: 5,
					moveSlides: 1,
					slideWidth: 250,
					slideMargin: 10,
					nextSelector: '#mainGoLink #slider-next',
					prevSelector: '#mainGoLink #slider-prev',
					nextText: '<i class="fa fa-angle-right" aria-hidden="true"></i>',
					prevText: '<i class="fa fa-angle-left" aria-hidden="true"></i>',
					pause: 3000
				});
			</script>
		</article>
	</div>
	<!-- } 메인 콘텐츠 -->
	
	<!-- 팝업영역  -->
	<c:forEach var="popups" items="${popups}" varStatus="status" >
		<div id="popupArea${status.count}" class="popupArea" stlye="display:none;/*left:24%*/">
			<ul>
				<li><a href="${popups.img_link}" target="_blank"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${popups.img_cont}"/>&fileSn=<c:out value="${popups.img_cont }"/>'  /></a></li>
			</ul>
			<div class="closebtnArea">
				<input type="checkbox" id="popupClose${status.count}" name="popupClose${status.count}" /><label for="popupClose${status.count}"><span></span>오늘 하루 열지 않기</label>
				<a href="#;" onclick ="closeMainPopup${status.count}()" class="closeBtn">닫기</a>
			</div>
		</div>	
	</c:forEach>

<!--  
	<div id="popupArea1" class="popupArea" stlye="display:none;/*left:24%*/">
		<ul>
			<li><a href="/sosig/event/list.do" target="_blank"><img src="/naksinuri_original/common/img/main_popup_1.png" /></a></li>
		</ul>
		<div class="closebtnArea">
			<input type="checkbox" id="popupClose1" name="popupClose1" /><label for="popupClose1"><span></span>오늘 하루 열지 않기</label>
			<a href="#;" onclick ="closeMainPopup1()" class="closeBtn">닫기</a>
		</div>
	</div>
	-->
	<%--
	<div id="popupArea2" class="popupArea" stlye="display:none;">
		<ul>
			<li><a href="/shortcut/congress1/link.do" target="_blank"><img src="/naksinuri_original/common/img/main_popup_2.gif" /></a></li>
		</ul>
		<div class="closebtnArea">
			<input type="checkbox" id="popupClose2" name="popupClose2" /><label for="popupClose2"><span></span>오늘 하루 열지 않기</label>
			<a href="#;" onclick ="closeMainPopup2()" class="closeBtn">닫기</a>
		</div>
	</div>
	 --%>

	<!-- 팝업영역 -->
	
<script type="text/javascript">


 
function closeMainPopup1(){

	if($('#popupClose1').prop("checked")){
		setCookie('notToday1','Y', 1);
		$("#popupArea1").hide('fade');
	}else{
		$("#popupArea1").hide('fade');
	}

}
function closeMainPopup2(){

	if($('#popupClose2').prop("checked")){
		setCookie('notToday2','Y', 1);
		$("#popupArea2").hide('fade');
	}else{
		$("#popupArea2").hide('fade');
	}

}

function closeMainPopup3(){

	if($('#popupClose3').prop("checked")){
		setCookie('notToday3','Y', 1);
		$("#popupArea3").hide('fade');
	}else{
		$("#popupArea3").hide('fade');
	}

}

function closeMainPopup4(){

	if($('#popupClose4').prop("checked")){
		setCookie('notToday4','Y', 1);
		$("#popupArea4").hide('fade');
	}else{
		$("#popupArea4").hide('fade');
	}

}

function closeMainPopup5(){

	if($('#popupClose5').prop("checked")){
		setCookie('notToday5','Y', 1);
		$("#popupArea5").hide('fade');
	}else{
		$("#popupArea5").hide('fade');
	}

}
function setCookie(name, value, expiredays) {
	
var today = new Date();
    today.setDate(today.getDate() + expiredays);
    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'

}

function getCookie(name) 
{ 

	var cName = name + "="; 
	var x = 0; 
	while ( x <= document.cookie.length ) 
	{ 
	    var y = (x+cName.length); 
	    if ( document.cookie.substring( x, y ) == cName ) 
	    { 
	        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
	            endOfCookie = document.cookie.length;
	        return unescape( document.cookie.substring( y, endOfCookie ) ); 
	    } 
	    x = document.cookie.indexOf( " ", x ) + 1;
	    
	    if ( x == 0 ) 
	        break; 
	} 
	return ""; 
}




$('document').ready(function(){
	

	if(getCookie("notToday1")!="Y"){
		$("#popupArea1").show();
	}else{
		$("#popupArea1").hide();
	}
	
	if(getCookie("notToday2")!="Y"){
		$("#popupArea2").show();
	}else{
		$("#popupArea2").hide();
	}
if(getCookie("notToday3")!="Y"){
		$("#popupArea3").show();
	}else{
		$("#popupArea3").hide();
	}
if(getCookie("notToday4")!="Y"){
		$("#popupArea4").show();
	}else{
		$("#popupArea4").hide();
	}
if(getCookie("notToday5")!="Y"){
		$("#popupArea5").show();
	}else{
		$("#popupArea5").hide();
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

	});

//검색버튼 클릭
function fnSelectInfs(){
	if($('#jobin').hasClass('on')){
		$("#frm").attr("action", "/naksinuri_original/info/fishjob/list.do");
	}else{
		$("#frm").attr("action", "/naksinuri_original/info/industry/list.do");
	}
	$("#frm").submit();
}



function seachange(){


	
	var url = "/choochange.do";
	var params="param1";
	var data = {"fishing_type":"seafishing","pageUnit":3};
	
	$('#pageIndex').val(1);
	$('#fishing_type').val('seafishing');
	
	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".tablink ul li").removeClass("on");
			$("#sea_job").addClass("on");
			$(".fishery_space ul li").remove();
			
			if(data.length > 0){
				for(var i=0;i<=data.length;i++){
					if(data[i].orignl_file_nm != null){
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
							<div style="width:238px;height:150px;">\
							<img src="/naksinuri_original/cmm/fms/getImage.do?atchFileId='+data[i].co_mimgsrc+'&fileSn='+data[i].file_sn+'" alt='+data[i].orignl_file_nm+' width="100%" height="100%"/>\
							</div>\
							</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}else{
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
								<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="100%"/>\
								</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}
					$('#pagesize').val(Math.ceil(data[0].tot_cnt/3));
					}
				}else{
					$(".fishery_space ul").append(			
							"<li><a>데이터가 없습니다.</a></li>");
					$('#pagesize').val(1);
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
}

function boatchange(){

	
	var url = "/choochange.do";
	var params="param1";
	var data = {"fishing_type":"boatfishing","pageUnit":3};
	
	$('#pageIndex').val(1);
	$('#fishing_type').val('boatfishing');
	
	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".tablink ul li").removeClass("on");
			$("#boat_job").addClass("on");
			$(".fishery_space ul li").remove();
			
			if(data.length > 0){
				for(var i=0;i<=data.length;i++){
					if(data[i].orignl_file_nm !=null){
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
							<div style="width:238px;height:150px;">\
							<img src="/naksinuri_original/cmm/fms/getImage.do?atchFileId='+data[i].co_mimgsrc+'&fileSn='+data[i].file_sn+'" alt='+data[i].orignl_file_nm+' width="100%" height="100%"/>\
							</div>\
							</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}else{
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
								<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="100%" />\
								</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}	
				$('#pagesize').val(Math.ceil(data[0].tot_cnt/3));
				}
			}else{
				$(".fishery_space ul").append(			
						"<li><a>데이터가 없습니다.</a></li>");
				$('#pagesize').val(1);
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}

		
	})
}

function riverchange(){
	
	
	var url = "/choochange.do";
	var params="param1";
	var data = {"fishing_type":"riverfishing","pageUnit":3};
	
	$('#pageIndex').val(1);
	$('#fishing_type').val('riverfishing');

	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".tablink ul li").removeClass("on");
			$("#river_job").addClass("on");
			$(".fishery_space ul li").remove();
			
			if(data.length > 0){
				for(var i=0;i<=data.length;i++){
					if(data[i].orignl_file_nm== null){
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
								<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="100%" />\
								</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}else{
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
							<div style="width:238px;height:150px;">\
							<img src="/naksinuri_original/cmm/fms/getImage.do?atchFileId='+data[i].co_mimgsrc+'&fileSn='+data[i].file_sn+'" alt='+data[i].orignl_file_nm+' width="100%px" height="100%px"/>\
							</div>\
							</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}	
					$('#pagesize').val(Math.ceil(data[0].tot_cnt/3));
				}
				
			}else{
				$(".fishery_space ul").append(			
						"<li><a>데이터가 없습니다.</a></li>");
				$('#pagesize').val(1);
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
}

function newschange(){
	var url = "/newschange.do";
	var params="param1";

	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".mnu ul li").remove();
			//$(".mnu ul").append("<li class=\"on\" id=\"main_news\" onclick=\"newschange()\"><h2>낚시뉴스</h2></li><li id=\"main_notice\" onclick=\"noticechange()\"><a href=\"#;\">공지사항</a></li>");
			$(".mnu ul").append("<li id=\"main_notice\" onclick=\"noticechange()\"><a href=\"#;\">공지사항</a></li><li class=\"on\" id=\"main_news\" onclick=\"newschange()\"><h2>낚시뉴스</h2></a></li>");
			$(".latest_list ul li").remove();
			$("#boardmore a").remove();
			
			if(data.length==''){
				$(".latest_list ul").append("<li><em>작성된 글이 없습니다.</em><span></span></a></li>");
				$("#boardmore").append("<a href=\"/sosig/news/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
			}else{
				for(var i=0;i<=data.length;i++) {
					$(".latest_list ul").append("<li><a href="+data[i].link+"><em>"+data[i].title+"</em></a></li>");
					if(i==0){
						$("#boardmore").append("<a href=\"/sosig/news/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
					}
				}
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}

		
	})
}

function noticechange(){
	var url = "/noticechange.do";
	var params="param1";

	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".mnu ul li").remove();
			//$(".mnu ul").append("<li id=\"main_news\" onclick=\"newschange()\"><a href=\"#;\">낚시뉴스</a></li><li class=\"on\" id=\"main_notice\" onclick=\"noticechange()\"><h2>공지사항</h2></li>");
			$(".mnu ul").append("<li class=\"on\" id=\"main_notice\" onclick=\"noticechange()\"><h2>공지사항</h2></li><li id=\"main_news\" onclick=\"newschange()\"><a href=\"#;\">낚시뉴스</a></li>");
			$(".latest_list ul li").remove();
			$("#boardmore a").remove();
			
			
			if(data.length==''){
				$(".latest_list ul").append("<li><em>작성된 글이 없습니다.</em><span></span></a></li>");
				$("#boardmore").append("<a href=\"/sosig/notice/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
			}else{
				for(var i=0;i<=data.length;i++) {
					$(".latest_list ul").append("<li><a href=\"#;\" onclick=\"noticeview('"+data[i].bo_sn+"')\"><em>"+data[i].bo_subject+"</em></a></li>");				
					if(i==0){
						$("#boardmore").append("<a href=\"/sosig/notice/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
						}
				}
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}

		
	})
}



function noticeview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	form.action="/sosig/notice/view.do";
	form.submit();	
}

function rangview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	form.action="/share/travel/view.do";
	form.submit();	
}

function sangview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	//form.action="/sosig/congress/view.do";
	form.action="/share/column/view.do";
	form.submit();	
}

function nak_sub(nak_id){
	$('#nak_id').val(nak_id);
	$("#view_go").attr("action", "/naksinuri_original/info/fishjob/view.do");
	$('#view_go').submit();
}


//지도를 먼저 클릭했을경우
function changSido(sido){
	
	$("#searchSido").val(sido).prop("selected", true);
	$('#searchSido').change();

}

function induch(){
	$('.search_box div').remove();
	$('#jobin').removeClass();
	$('#map_box h3').remove();
	$('#map_box h3').append();
	$('#induin').addClass("on");
	$('#setsyle').addClass("fish_business")
	$('#maptitle').css("display","none");
	$('#maptitle2').css("display","");
	$('.search_box').append('<div class="scon">\
		<dl>\
			<dt>· 구분</dt>\
			<dd class="type">\
				<input type="checkbox" id="fish1" name="san_zogu" 	value="조구업체" 		<c:if test ="${san_zogu eq'조구업체'}">checked</c:if>/><label for="fish1"><span></span>조구업체</label>\
				<input type="checkbox" id="fish2" name="san_media" 	value="미디어"   		<c:if test ="${san_media eq '미디어'}">checked</c:if>/><label for="fish2"><span></span>미디어</label>\
				<input type="checkbox" id="fish3" name="san_sell" 	value="판매점"   		<c:if test ="${san_sell eq'판매점'}">checked</c:if>/><label for="fish3"><span></span>판매점</label>\
				<input type="checkbox" id="fish4" name="san_chool" 	value="출조점"   		<c:if test ="${san_chool eq '출조점'}">checked</c:if>/><label for="fish4"><span></span>출조점</label>\
				<input type="checkbox" id="fish5" name="san_inprov" value="낚시정보제공"	<c:if test ="${san_inprov eq'낚시정보제공'}">checked</c:if>/><label for="fish5" class="last"><span></span>낚시정보제공</label>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 시/도</dt>\
			<dd>\
				<select class="search_select" name="searchSido" id="searchSido">\
				</select>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 시/군/구</dt>\
			<dd>\
				<select class="search_select"  name="searchGugun" id="searchGugun" >\
				</select>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 상호명</dt>\
			<dd><input type="text"  name="searchText" class="search_input" /></dd>\
		</dl>\
		<dl>\
			<dt>· 홈페이지</dt>\
			<dd><input type="checkbox" id="website" name="fish" /><label for="website"><span></span>자체홈페이지 여부</label></dd>\
		</dl>\
	</div>\
	<div class="searchBtn"><button type="submit" onclick="fnSelectInfs()"><i class="fa fa-search" aria-hidden="true"></i><em>검색</em></button></div>');
	
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
}

function infoch(){
	$('.search_box div').remove();
	$('#induin').removeClass();
	$('#jobin').addClass("on");
	$('#setsyle').removeClass("fish_business");
	$('#maptitle2').css("display","none");
	$('#maptitle').css("display","");
	$('.search_box').append('<div class="scon">\
			<dl>\
			<dt>· 구분</dt>\
			<dd>\
				<input type="checkbox" id="fish1" name="searchBoat" value="boatfishing"/><label for="fish1"><span></span>선상낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;\
				<input type="checkbox" id="fish2" name="searchRiver" value="riverfishing"/><label for="fish2"><span></span>민물낚시</label>&nbsp;&nbsp;&nbsp;&nbsp;\
				<input type="checkbox" id="fish3" name="searchSea" value="seafishing"/><label for="fish3"><span></span>바다낚시</label>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 시/도</dt>\
			<dd>\
				<select class="search_select" name="searchSido" id="searchSido" onchange="changeMap();">\
				</select>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 시/군/구</dt>\
			<dd>\
				<select class="search_select" name="searchGugun" id="searchGugun">\
				</select>\
			</dd>\
		</dl>\
		<dl>\
			<dt>· 주요어종</dt>\
			<dd><input type="text" name="searchText" id="searchText" class="search_input" placeholder="예) 붕어, 잉어, 도다리" /></dd>\
		</dl>\
		<dl>\
			<dt>· 사업자명</dt>\
			<dd><input type="text" id="searchText2" name="searchText2" class="search_input" /></dd>\
		</dl>\
		<dl>\
			<dt>· 홈페이지</dt>\
			<dd><input type="checkbox" id="website" name="fish" /><label for="website"><span></span>자체홈페이지 여부</label></dd>\
		</dl>\
	</div>\
		<div class="searchBtn"><button type="submit" onclick="fnSelectInfs()"><i class="fa fa-search" aria-hidden="true"></i><em>검색</em></button></div>');
		
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
	
}

$('#prev_choo').click(function() {
	var url = "/choochange.do";
	var params="param1";
	var pagesize = $('#pagesize').val();
	
	var pageIndex = parseInt($('#pageIndex').val())-parseInt(1);
	if(pageIndex < 1 || pageIndex==''){
		pageIndex = pagesize;
	}
	var fishing_type = $('#fishing_type').val();
	var pageUnit = $('#pageUnit').val();
	var data = {"fishing_type":fishing_type,"pageIndex":pageIndex,"pageUnit":3};
	$('#pageIndex').val(pageIndex);
	if(pageIndex < 1 || pageIndex==''){
		pageIndex = pagesize;
		$('#pageIndex').val(pagesize);
	}
	
	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".tablink ul li").removeClass("on");
			if(fishing_type=='riverfishing'){
				$("#river_job").addClass("on");
			}else if(fishing_type=='boatfishing'){
				$("#boat_job").addClass("on");
			}else if(fishing_type=='seafishing'){
				$("#sea_job").addClass("on");
			}
			$(".fishery_space ul li").remove();
			
			if(data.length > 0){
				for(var i=0;i<=data.length;i++){
					if(data[i].orignl_file_nm==null){
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
								<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="100%" />\
								</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}else{
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
							<div style="width:238px;height:150px;">\
							<img src="/naksinuri_original/cmm/fms/getImage.do?atchFileId='+data[i].co_mimgsrc+'&fileSn='+data[i].file_sn+'" alt='+data[i].orignl_file_nm+' width="100%px" height="100%px"/>\
							</div>\
							</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}
				}
			}else{
				$(".fishery_space ul").append(			
						"<li><a>데이터가 없습니다.</a></li>");
			
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
	});
	
$('#next_choo').click(function() {
	var url = "/choochange.do";
	var params="param1";
	
	
	var pageIndex = parseInt($('#pageIndex').val()) + parseInt(1);
	var pagesize = $('#pagesize').val();
	var fishing_type = $('#fishing_type').val();
	var pageUnit = $('#pageUnit').val();
	var data = {"fishing_type":fishing_type,"pageIndex":pageIndex,"pageUnit":3};
	$('#pageIndex').val(pageIndex);
	if(pageIndex > pagesize){
		pageIndex = 1;
		$('#pageIndex').val(pageIndex);
	}
	
	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$(".tablink ul li").removeClass("on");
			if(fishing_type=='riverfishing'){
				$("#river_job").addClass("on");
			}else if(fishing_type=='boatfishing'){
				$("#boat_job").addClass("on");
			}else if(fishing_type=='seafishing'){
				$("#sea_job").addClass("on");
			}
			$(".fishery_space ul li").remove();
			
			if(data.length > 0){
				for(var i=0;i<=data.length;i++){
					if(data[i].orignl_file_nm==null){
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
								<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="100%" />\
								</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}else{
						$(".fishery_space ul").append('<li><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="pic">\
							<div style="width:238px;height:150px;">\
							<img src="/naksinuri_original/cmm/fms/getImage.do?atchFileId='+data[i].co_mimgsrc+'&fileSn='+data[i].file_sn+'" alt='+data[i].orignl_file_nm+' width="100%px" height="100%px"/>\
							</div>\
							</a><a href="#;" onclick="nak_sub('+data[i].nak_id+')" class="tit"><em>'+data[i].co_nm+'</em><span>'+data[i].co_addr_2+'</span></a></li>');
					}
				}
			}else{
				$(".fishery_space ul").append(			
						"<li><a>데이터가 없습니다.</a></li>");
			
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
});


	

</script>
	
<%@include file="../naksinuri/layout/tail.jsp"%> 
