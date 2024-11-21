<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../naksinuri/layout/newHead.jsp"%>
  
<!-- 안전해신호등 스타일 start -->
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

#map{width:100%;height:calc(100%/*  - 151px */);position:relative;overflow:hidden;}

#map:focus { outline: 0px solid transparent; }
</style>
<!-- 안전해신호등 스타일 end -->
<!-- 안전해신호등 정보 start -->
<form:form commandName="naksinuriVO" id="viewform" name="viewform" method="post" >
	<input type="hidden" id="name" name="name" value="" />
	<input type="hidden" id="date" name="date" value="" />
</form:form>
<!-- 안전해신호등 정보 end -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<script>
function caroufredsel_pause(){
	$('#sponcerSlide .spncrWrp').trigger('pause');
}

function caroufredsel_pause2(){
	$('#bnrSlider .bnrWrp').trigger('pause');
}
</script>
<form:form commandName="viewform" id="viewform" method="post" enctype="multipart/form-data" >
   <input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<form:form id="view_go" method="post" enctype="multipart/form-data" >
      <input type="hidden" name="nak_id" id="nak_id" />
</form:form>
   


  
<div class="main_content">
	<section class="main_section main_sec01">
		<div class="wrap_visual">
            <div class="visual-slide">
                <div class="control-box">
                    <!-- 페이징 -->
                    <div class="swiper-pagination pagination_progress swiper-pagination-progressbar"><span class="swiper-pagination-progressbar-fill" style="transform: translate3d(0px, 0px, 0px) scaleX(1) scaleY(1); transition-duration: 300ms;"></span></div>
                    <div class="swiper-pagination pagination_fraction swiper-pagination-fraction"><span class="swiper-pagination-current">4</span> / <span class="swiper-pagination-total">4</span></div>
                    
                    <!--김현태추가 재생 정지 버튼 -->
                    <div class="swiper-button-prev swiper-button-prev_s" title="배경 이미지 이전 슬라이드" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="false">배경 이미지 이전 슬라이드</div>
                    <div class="controler">
                        <button class="sponsor_autoplay-control" title="배경 이미지 슬라이드 정지,재생"></button>
                    </div>
                    <div class="swiper-button-next swiper-button-next_s swiper-button-disabled" title="배경 이미지 다음 슬라이드" tabindex="0" role="button" aria-label="Next slide" aria-disabled="true">배경 이미지 다음 슬라이드</div>
                </div>
                
                                
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide"><img class="sum" src="/naksinuri_original/common_main/img/main/new_main01_bg.png" alt=""></div>
                        <div class="swiper-slide"><img class="sum" src="/naksinuri_original/common_main/img/main/new_main02_bg.png" alt=""></div>
                        <div class="swiper-slide"><img class="sum" src="/naksinuri_original/common_main/img/main/new_sec01_bg.png" alt=""></div>
                        <div class="swiper-slide"><img class="sum" src="/naksinuri_original/common_main/img/main/new_main03_bg.png" alt=""></div>
                    </div>
					
                	<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
				</div>
                
            </div>		
            <div class="shortcut_list">
                <ul>
                    <li>
                        <a href="/educenter/trnng/list.do" title="교육일정 바로가기">
                            <span class="shortcut_txt ico1">교육일정</span>
                        </a>
                    </li>
                    <li>
                        <a href="/educenter/main/serviceGuide.do" title="교육안내 바로가기">
                            <span class="shortcut_txt ico2">교육안내</span>
                        </a>
                    </li>
                    <li>
                        <a href="/educenter/board/faq/list.do" title="수강 FAQ 바로가기">
                            <span class="shortcut_txt ico3">수강 FAQ</span>
                        </a>
                    </li>
                </ul>
            </div>	
		</div>
	</section>


    <section class="main_section main_sec05">
        <div class="wrap">
			<div class="title_container">
				<h2 class="main_sec_title">자주 찾는 메뉴</h2>
			</div>
            <div class="main_sec05_container">
                <article class="left_cont">
                    <ul>
                        <li>
                            <img src="../naksinuri_original/common_main/img/main/isu.png" alt="">
                            <h4>이수증 내역</h4>
                            <a href="/educenter/mbrhstry/list.do" title="이수증 내역 바로가기">GO</a>
                            <p>이수증 내역에서<br>열람이 가능합니다.</p>
                        </li>
                        <li>
                            <img src="../naksinuri_original/common_main/img/main/edu.png" alt="">
                            <h4>온라인 교육신청</h4>
                            <a href="/educenter/trnng/list.do" title="온라인 교육 신청 바로가기">GO</a>
                            <p>신청하기 버튼이<br>활성화 되어 있는 교육은<br>신청이 가능합니다.</p>
                        </li>
                        <li>
                            <img src="../naksinuri_original/common_main/img/main/youtb.png" alt="">
                            <h4>낚시의 품격</h4>
                            <a href="/lesson/dignity/list.do" title="낚시의 품격 바로가기">GO</a>
                            <p>실전 낚시의 품격에서<br>ATOZ를 확인해보세요.</p>
                        </li>
                    </ul>
                </article>
                <!-- 안전해 -->
                <article class="right_cont">
                    <div class="fishing_maps">
                        <div id="map_wrap">
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
						</div>
						<!-- end 컬러 구분 표지판 -->
						<!-- <div id="map" tabindex="-1"></div> 마커 탭인덱스 삭제-->
						<div id="map"></div>
						<div id="map_wrap">
							<div class="colorSign2" style="text-align:center;">
								<a href="/info/oceanFshIdex/list.do" title="안전海신호등 바로가기">크게보기</a>
							</div>
						</div>
                    </div>
                </article>
            </div>
  
        </div>
    </section>

		  

    <section class="main_section main_sec06">
        <div class="wrap">
			<div class="main_sec06_container">
				<div class="left_cont">
				
					<div class="top_cont">
						<div class="title_container">
							<h4 class="main_sec_title">낚시전문 교육 프로그램</h4>
							<!-- <a href="" class="add_btn">+</a> -->
						</div>
						
						<c:set var="found1" value="false" /><!-- 리스트에서 1개만 불러오기 요소 -->
						<c:set var="found2" value="false" />
						<c:set var="found3" value="false" />
						<c:set var="found4" value="false" />
						<c:forEach var="item" varStatus="status" items="${list_crs}">
						<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
						<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd (E)" var="CRS_STR_DT"/>
						<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.MM.dd (E)" var="CRS_END_DT"/>
						<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd" var="CRS_ym"/>
						<fmt:formatDate value="${parse_crs_str_dt}" pattern="MM.dd" var="CRS_day"/>
						<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_STR_DT"/>
						<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_END_DT"/>
						<c:set var="now" value="<%=new java.util.Date()%>" />
						<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy.MM.dd (E)" /></c:set>
						<c:set var="MBR_MAX_CNT_STR" value="" />
						<div class="edu_cont">
							<c:choose>
							  	<c:when test="${item.TYPE_GB eq 'offline' and item.CRS_MBR_CD eq 'CIDN010300'}">
							  		<c:if test="${found1 eq false}">
										<a href="/educenter/trnngoffline/fshhpList.do" class="edu_list" title="${item.CRS_NM}">
											<div class="prg1">
												<h5 class="point_inner">${CRS_day}</h5>
												<span class="date_inner">${CRS_ym}</span>
											</div>
											<div class="prg2">
												<p class="text_inner">${item.CRS_NM}</p>
												<div class="sub_text_inner"><strong>교육장소</strong><span class="location_inner">${item.CRS_ADDR}</span></div>
												<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">${item.MBR_CUR_CNT}/${item.MBR_MAX_CNT}명</span></div>
											</div>
											<c:choose>
								  				<c:when test="${item.MBR_CUR_CNT eq '20' or RECRUIT_END_DT < sysYear}">
													<div class="prg3">
														<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_boxn_01.png" alt=""></p>
													</div>
												</c:when>
												<c:otherwise>
													<div class="prg3">
														<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_box_01.png" alt=""></p>
													</div>
												</c:otherwise>
											</c:choose>
										</a>
										<c:set var="found1" value="true" />
									</c:if>
								</c:when>
								<c:otherwise>
									<%-- <c:if test="${found1 eq false}">
										<div class="edu_list">
										<div class="prg1">
											<h5 class="point_inner">-</h5>
											<span class="date_inner">-</span>
										</div>
										<div class="prg2">
											<p class="text_inner">준비된 낚시어선 신규,재개자 전문교육이 없습니다</p>
											<div class="sub_text_inner"><strong>교육장소</strong><span class="location_inner">-</span></div>
											<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">-</span></div>
										</div>
										<div class="prg3">
											<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_boxn_01.png" alt=""></p>
										</div>
										</div>
										<c:set var="found1" value="true" />
									</c:if> --%>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${item.TYPE_GB eq 'offline' and item.CRS_MBR_CD eq 'CIDN010200'}">
									<c:if test="${found2 eq false}">
										<a href="/educenter/trnngoffline/fshlcList.do" class="edu_list" title="${item.CRS_NM}">
											<div class="prg1">
												<h5 class="point_inner">${CRS_day}</h5>
												<span class="date_inner">${CRS_ym}</span>
											</div>
											<div class="prg2">
												<p class="text_inner">${item.CRS_NM}</p>
												<div class="sub_text_inner"><strong>교육장소</strong><span class="location_inner">${item.CRS_ADDR}</span></div>
												<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">${item.MBR_CUR_CNT}/${item.MBR_MAX_CNT}명</span></div>				   
											</div>
											<c:choose>
								  				<c:when test="${item.MBR_CUR_CNT eq '20' or RECRUIT_END_DT < sysYear}">
													<div class="prg3">
														<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_boxn_02.png" alt=""></p>
													</div>
												</c:when>
												<c:otherwise>
													<div class="prg3">
														<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_box_02.png" alt=""></p>
													</div>
												</c:otherwise>
											</c:choose>
										</a>
										<c:set var="found2" value="true" />
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${found2 eq false}">
										<div class="edu_list">
										<div class="prg1">
											<h5 class="point_inner">-</h5>
											<span class="date_inner">-</span>
										</div>
										<div class="prg2">
											<p class="text_inner">준비된 낚시터 현장 교육이 없습니다</p>
											<div class="sub_text_inner"><strong>교육장소</strong><span class="location_inner">-</span></div>
											<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">-</span></div>
										</div>
										<div class="prg3">
											<p class="whether_box"><img src="/naksinuri_original/common_main/img/main/whether_boxn_02.png" alt=""></p>
										</div>
										</div>
										<c:set var="found2" value="true" />
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
						
						
					</div>
					<div class="bottom_cont">
						<div class="sub_edu_cont">
							<div class="edu_title_inner">
								<h5 class="edu_title">온라인<br>교육</h5>
							</div>
							<div class="edu_list_inner">
							
								<c:forEach var="item" varStatus="status" items="${list_crs}">
								<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
								<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
								<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_STR_DT"/>
								<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_END_DT"/>
								<c:if test="${not found3}">
								<c:choose>
								  	<c:when test="${item.TYPE_GB eq 'online' and item.CRS_MBR_CD eq 'CIDN010200'}">
								  		
										<a href="/educenter/trnng/list.do" class="edu_list2" title="${item.CRS_NM}">							 
											<div>
												<p class="text_inner">${item.CRS_NM}</p>
												<div class="sub_text_inner"><strong>교육일</strong><span class="location_inner">${RECRUIT_STR_DT}~${RECRUIT_END_DT}</span></div>
												<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">${item.MBR_CUR_CNT} / 무제한</span></div>
											</div>
											<div>
												<p class="whether_box">신청가능</p>		
											</div>
										</a>
										<c:set var="found3" value="true" />
										
									</c:when>
									<c:otherwise>
										
											<div class="edu_list2">							 
												<div>
													<p class="text_inner">준비된 교육이 없습니다.</p>
													<div class="sub_text_inner"><strong>교육일</strong><span class="location_inner">-</span></div>
													<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">-</span></div>
												</div>
												<div>
													<p class="whether_box">신청마감</p>		
												</div>
											</div>
											<c:set var="found3" value="true" />
										
									</c:otherwise>
								</c:choose>
								</c:if>
								<c:if test="${not found4}">
								<c:choose>
								  	<c:when test="${item.TYPE_GB eq 'online' and item.CRS_MBR_CD eq 'CIDN010300'}">
								  		
										<a href="/educenter/trnng/list.do" class="edu_list2" title="${item.CRS_NM}">
											<div>
												<p class="text_inner">${item.CRS_NM}</p>
												<div class="sub_text_inner"><strong>교육일</strong><span class="location_inner">${RECRUIT_STR_DT}~${RECRUIT_END_DT}</span></div>
												<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">${item.MBR_CUR_CNT} / 무제한</span></div>
											</div>
											<div>
												<p class="whether_box">신청가능</p>
											</div>  
										</a>
										<c:set var="found4" value="true" />
										
									</c:when>
									<c:otherwise>
										<%-- <c:if test="${found4 eq false}">
											<div class="edu_list2">							 
												<div>
													<p class="text_inner">준비된 교육이 없습니다.</p>
													<div class="sub_text_inner"><strong>교육일</strong><span class="location_inner">-</span></div>
													<div class="sub_text_inner"><strong>신청인원</strong><span class="people_inner">-</span></div>
												</div>
												<div>
													<p class="whether_box">신청마감</p>		
												</div>
											</div>
											<c:set var="found4" value="true" />
										</c:if> --%>
									</c:otherwise>
								</c:choose>
								</c:if>
								
								</c:forEach>
							
							</div> 
						</div>
					</div>
				</div>
				
				

<%-- <c:set var="founda" value="false" />
<c:set var="foundb" value="false" />

<c:forEach var="item" varStatus="status" items="${list_crs}">
	<c:if test="${founda eq false}">
	<c:choose>
		<c:when test="${item.TYPE_GB eq 'offline' and item.CRS_MBR_CD eq 'CIDN010200'}">
		    
		        해당 조건을 만족하는 첫 번째 항목을 찾았을 때만 출력
		        <li>
		            <div class="grpedu_Pn1">
		                <a href="#">
		                    <p class="sbj">${item.CRS_DSCRP}1</p>
		                    <p class="fst_date">${CRS_DT_STR}1</p>
		                    <p class="dates">${item.CRS_PLACE}1</p>
		                    <span class="tag">신청가능</span>
		                </a>
		            </div>
		        </li>
		        <c:set var="founda" value="true" />
		    
		</c:when>
		<c:otherwise>
		<c:if test="${founda eq false}">
		없음1
		<c:set var="founda" value="true" />
		</c:if>
		</c:otherwise>
	</c:choose>
	</c:if>
</c:forEach>
<c:forEach var="item" varStatus="status" items="${list_crs}">
	<c:if test="${foundb eq 'false'}">
	<c:choose>
		<c:when test="${item.TYPE_GB eq 'offline' and item.CRS_MBR_CD eq 'CIDN010300'}">
		    
		        <li>
		            <div class="grpedu_Pn1">
		                <a href="#">
		                    <p class="sbj">${item.CRS_DSCRP}2</p>
		                    <p class="fst_date">${CRS_DT_STR}2</p>
		                    <p class="dates">${item.CRS_PLACE}2</p>
		                    <span class="tag">신청가능</span>
		                </a>
		            </div>
		        </li>
		        <c:set var="foundb" value="true" />
		    
		</c:when>
		<c:otherwise>
		<c:if test="${foundb eq false}">
		없음2
		<c:set var="foundb" value="true" />
		</c:if>
		</c:otherwise>
	</c:choose>
	</c:if>
</c:forEach> --%>
				<div class="right_cont">
		 
					<div class="title_container">
						<h4 class="main_sec_title">알림마당</h4>
						<!-- <a href="" class="add_btn">+</a> -->
					</div>
					<div class="info_madang">
						<div class="info_madang_title_inner">
							<h5 class="info_madang_title">알림 게시글을 확인하세요</h5>
							<a href="/promotion/info/list.do" class="info_madang_add_btn" title="알림마당 바로가기">더보기</a>
						</div>
		  
		 
						<div class="info_madang_cont">
							<ul>
							<c:forEach var="item" varStatus="status" items="${info_list }">
								<li class="info_madang_list">
									<a href="javascript:void(0);" class="tit" onclick="infoview('${item.bo_sn}')" title="알림마당  ${item.bo_subject } 페이지이동"><p>${item.bo_subject}${item.bo_sn}</p><span class=""> ${fn:substring(item.bo_insert_dt,0,10) }</span></a>
								</li>
								<!-- <li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li>
								<li class="info_madang_list"><a href=""><p>가나다라마바사아자차카</p><span>2222-22-22</span></a></li> -->
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
        </div>
    </section>


	<section class="main_section main_sec02">
		<div class="wrap">
				<!-- 공지사항 탭 -->
				<article class="con con01">
					<div class="title_container">
						<h4 class="main_sec_title">새로운 소식</h4>
						<!-- <a href="" class="add_btn">+</a> -->
					</div>
					<div class="con_area">
						<div class="notice_list">
							<div class="notice_list_tap">
								<ul>
									<li><a href="#none">공지사항</a></li>
									<li><a href="#none">알림마당</a></li>
									<li><a href="#none">낚시정책</a></li>
									<li><a href="#none">낚시캠페인</a></li>
								</ul>
							</div>
							<div class="notice_list_inner">
	
								<div class="notice_list_cont notice_cont1">
									<ul>
									<c:forEach var="item" varStatus="status" items="${notice_list }">
										<li><a href="javascript:void(0);" onclick="noticeview('${item.bo_sn}')" title="공지사항  ${item.bo_subject } 페이지이동"><p class="notice_text dot-list2">${item.bo_subject}</p><span class="notice_date"> ${fn:substring(item.bo_insert_dt,2,10) }</span></a></li>
									</c:forEach>
										<!-- <li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및 신청 안내aaaaaaaaaaaaa</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및 신청</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육</p><span class="notice_date">23.06.01</span></a>
										</li> -->
									</ul>
								</div>
								<div class="notice_list_cont notice_cont2">
									<ul>
									<c:forEach var="item" varStatus="status" items="${info_list }">
										<li><a href="javascript:void(0);" onclick="infoview('${item.bo_sn}')" title="알림마당  ${item.bo_subject } 페이지이동"><p class="notice_text dot-list2">${item.bo_subject}</p><span class="notice_date"> ${fn:substring(item.bo_insert_dt,2,10) }</span></a></li>
									</c:forEach>
										<!-- <li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및 신청 안내</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및 신청</p><span class="notice_date">23.06.01</span></a>
										</li> -->
									</ul>
								</div>
								<div class="notice_list_cont notice_cont3">
									<ul>
									<c:forEach var="item" varStatus="status" items="${policy_list }">
										<li><a href="javascript:void(0);" onclick="policyview('${item.bo_sn}')" title="낚시정책  ${item.bo_subject } 페이지이동"><p class="notice_text dot-list2">${item.bo_subject}</p><span class="notice_date"> ${fn:substring(item.bo_insert_dt,2,10) }</span></a></li>
									</c:forEach>
										<!-- <li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및 신청 안내</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사 및</p><span class="notice_date">23.06.01</span></a>
										</li>
										<li>
											<a href="javascript:void(0);" title="123"><p class="notice_text dot-list2">23년 6월 낚시어선 현장교육 수요조사</p><span class="notice_date">23.06.01</span></a>
										</li> -->
									</ul>
								</div>
								<div class="notice_list_cont notice_cont4">
									<ul>
									<c:choose>
									    <c:when test="${empty campaign_list}">
									        <li><a><p class="notice_text dot-list2">진행중인 캠페인이 없습니다.</p><span class="notice_date">-</span></a></li>
									    </c:when>
									    <c:otherwise>
									        <c:forEach var="item" varStatus="status" items="${campaign_list}">
									            <li><a href="javascript:void(0);" onclick="contsdesignview('${item.bo_sn}')" title="낚시캠페인 ${item.bo_subject} 페이지이동"><p class="notice_text dot-list2">${item.bo_subject}</p><span class="notice_date">${fn:substring(item.bo_insert_dt,2,10)}</span></a></li>
									        </c:forEach>
									    </c:otherwise>
									</c:choose>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</article>
				<!-- 공지사항 탭 end -->
				<!-- 낚시의 품격 -->
				<article class="con con02">
					<div class="title_container">
						<h4 class="main_sec_title">낚시의 품격</h4>
						<!-- <a href="" class="add_btn">+</a> -->
					</div>
					<div class="sec02_title_tap">알아두면 평생 써먹는 물고기 잘 낚는 법</div>
					<div class="dignity-slide">
						<div class="con_area">
							<div class="swiper-container swiper-container-horizontal">
								<div class="swiper-wrapper">
									
									<c:forEach var="item" varStatus="status" items="${dignity_list }">
										<div class="swiper-slide">
											<a href="javascript:void(0);" onclick="dignityview('${item.bo_sn}')" title="낚시의 품격  ${item.bo_subject } 페이지이동">
												<div class="thumb">
													<img class="type2" src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>' alt="${item.bo_subject}"/>
												</div>
												<div class="txt_area">
													<p class="tit">${item.bo_subject}</p>
													<span class="date">${fn:substring(item.bo_insert_dt,0,10) }</span>
												</div>
											</a>
										</div>
									</c:forEach>
									
									<!-- <div class="swiper-slide">
										<a href="javascript:void(0);" onclick="dignityview('1658')" title="낚시의 품격  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">진석기시대 야, 너도 낚시할 수 있어(상편)</p>
												<span class="date">2022-10-14</span>
											</div>
										</a>
									</div>
									<div class="swiper-slide">
										<a href="javascript:void(0);" onclick="dignityview('1658')" title="낚시의 품격  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">진석기시대 야, 너도 낚시할 수 있어(상편)</p>
												<span class="date">2022-10-14</span>
											</div>
										</a>
									</div> -->
									
							</div>
							<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
							<div class="control-box anothertype">
								<!-- 페이징 -->
								<div class="swiper-pagination swiper-pagination02 swiper-pagination-fraction"><span class="swiper-pagination-current">3</span> / <span class="swiper-pagination-total">3</span></div>
								<!-- 이전 버튼 -->
								<div class="swiper-button-prev swiper-button-prev02" title="낚시의 품격 이전 슬라이드" role="button" aria-label="Previous slide">낚시의 품격 이전 슬라이드</div>
								<!-- 재생 정지 버튼 -->
								<div class="controler" title="낚시의 품격 슬라이드 정지,재생">
									<button class="autoplay-control"></button>
								</div>
								<!-- 다음 버튼  -->
								<div class="swiper-button-next swiper-button-next02" title="낚시의 품격 다음 슬라이드" role="button" aria-label="Next slide">낚시의 품격 다음 슬라이드</div>
							</div>
							  
																																	   
						</div>
					</div>
				</article>
				<!-- 낚시의 품격 end -->
												   
				 
		 
				<!-- 팝업존 -->
				<article class="con con03">
					<div class="title_container">
						<h4 class="main_sec_title">해수부 카드뉴스</h4>													 
						<!-- <a href="" class="add_btn">+</a> -->
					</div>
					<div class="sec02_title_tap">카드뉴스</div>

					<div class="dignity-slide2">
	
		 
						<div class="con_area" style="overflow: hidden;">
							<div class="swiper-container swiper-container-horizontal">
								<div class="swiper-wrapper" style="transform: translate3d(-1840px, 0px, 0px); transition-duration: 0ms;">
									
									<!-- <div class="swiper-slide swiper-slide-duplicate swiper-slide-next swiper-slide-duplicate-prev" data-swiper-slide-index="2">
										<a href="javascript:void(0);" onclick="dignityview('1658')" title="팝업존  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">																													
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">진석기시대 야, 너도 낚시할 수 있어(상편)</p>
												<span class="date">2022-10-14</span>
											</div>
										</a>
									</div> -->
									<!-- <div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="0">
										<a href="javascript:void(0);" onclick="dignityview('1781')" title="팝업존  입질의추억 초보자를 위한 입문 가이드!  페이지이동">
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img01.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">입질의추억 초보자를 위한 입문 가이드! </p>
												<span class="date">2023-09-18</span>
											</div>
										</a>
									</div> -->
									<div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img01.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									
									<div class="swiper-slide" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img02.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img03.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img04.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img05.jpeg" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img06.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
								<!-- <div class="swiper-slide swiper-slide-duplicate swiper-slide-active" data-swiper-slide-index="0">
									<a href="javascript:void(0);" onclick="dignityview('1781')" title="팝업존 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage2.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="tit">입질의추억 초보자를 위한 입문 가이드! </p>
											<span class="date">2023-09-18</span>
										</div>
									</a>
								</div> -->
							</div>
							<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
							<div class="control-box anothertype">
								<!-- 페이징 -->
								<div class="swiper-pagination swiper-pagination04 swiper-pagination-fraction"><span class="swiper-pagination-current">3</span> / <span class="swiper-pagination-total">3</span></div>
								<!-- 이전 버튼 -->
								<div class="swiper-button-prev swiper-button-prev04" title="카드뉴스 이전 슬라이드" role="button" aria-label="Previous slide">카드뉴스 이전 슬라이드</div>
								<!-- 재생 정지 버튼 -->
								<div class="controler" title="카드뉴스 슬라이드 정지,재생">
									<button class="autoplay-control"></button>																	  
								</div>
								<!-- 다음 버튼  -->
								<div class="swiper-button-next swiper-button-next04" title="카드뉴스 다음 슬라이드" role="button" aria-label="Next slide">카드뉴스 다음 슬라이드</div>
							</div>
						</div>
					</div>
		  
				</article>
				<!-- 팝업존 end -->
		</div>
	</section>


	<section class="main_section main_sec03">
		<div class="wrap">
			<!-- 해상 정보 -->
			<article class="con con01">
				<div class="title_container">
					<h4 class="main_sec_title">해상정보</h4>
					<span class="sub_sec_title">해상 정보를 확인하세요</span>
				</div>
				<div class="con_area">
					<div class="info_list">
						<ul>
							<li>
								<a href="http://www.khoa.go.kr/swtc/main.do" target="_blank" title="조석정보 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_1.png" class="info_img" alt="">
								</a>
							</li>
							<li>
								<a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=31201000" target="_blank" title="바다갈라짐 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_2.png" class="info_img" alt="">
								</a>
							</li>
							<li>
								<a href="http://www.khoa.go.kr/koofs/kor/observation/obs_real.do?obsItem=WT_TEM&amp;imgIdx=05" target="_blank" title="수온정보 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_3.png" class="info_img" alt="">
								</a>
							</li>
							<li>
								<a href="http://www.kma.go.kr/index.jsp" target="_blank" title="날씨정보 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_4.png" class="info_img" alt="">
								</a>
							</li>
							<li>
								<a href="http://www.khoa.go.kr/koofs/kor/seawf/sea_wf.do?menuNo=01&amp;link=" target="_blank" title="조류예보 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_5.png" class="info_img" alt="">
								</a>
							</li>
							<li>
								<a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank" title="해양예보방송 외부링크">
									<img src="/naksinuri_original/common_main/img/main/info_ico_6.png" class="info_img" alt="">
								</a>
							</li>
						</ul>
					</div><!--//info_list//-->
				</div>
			</article>
			<!-- 해상 정보 end -->
			<!-- 낚시 캠페인 -->
			<%-- <article class="con con02">
			<div class="title_container">
				<h4 class="main_sec_title">해수부 카드뉴스</h4>													 
				<!-- <a href="" class="add_btn">+</a> -->
			</div>

					<div class="dignity-slide2">
	
		 
						<div class="con_area" style="overflow: hidden;">
							<div class="swiper-container swiper-container-horizontal">
								<div class="swiper-wrapper" style="transform: translate3d(-1840px, 0px, 0px); transition-duration: 0ms;">
									
									<!-- <div class="swiper-slide swiper-slide-duplicate swiper-slide-next swiper-slide-duplicate-prev" data-swiper-slide-index="2">
										<a href="javascript:void(0);" onclick="dignityview('1658')" title="팝업존  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">																													
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">진석기시대 야, 너도 낚시할 수 있어(상편)</p>
												<span class="date">2022-10-14</span>
											</div>
										</a>
									</div> -->
									<!-- <div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="0">
										<a href="javascript:void(0);" onclick="dignityview('1781')" title="팝업존  입질의추억 초보자를 위한 입문 가이드!  페이지이동">
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img01.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
											</div>
											<div class="txt_area">
												<p class="tit">입질의추억 초보자를 위한 입문 가이드! </p>
												<span class="date">2023-09-18</span>
											</div>
										</a>
									</div> -->
									<div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img01.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									
									<div class="swiper-slide" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img02.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img03.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img04.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img05.jpeg" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
									<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="">
										<div>
											<div class="thumb">
												<img class="type2" src="/naksinuri_original/common_main/img/main/add_img06.png" alt="" style="height: 350px;">
											</div>
											<div class="txt_area">
												<p class="tit"></p>
												<span class="date"></span>
											</div>
										</div>
									</div>
								<!-- <div class="swiper-slide swiper-slide-duplicate swiper-slide-active" data-swiper-slide-index="0">
									<a href="javascript:void(0);" onclick="dignityview('1781')" title="팝업존 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage2.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="tit">입질의추억 초보자를 위한 입문 가이드! </p>
											<span class="date">2023-09-18</span>
										</div>
									</a>
								</div> -->
							</div>
							<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
							<div class="control-box anothertype">
								<!-- 페이징 -->
								<div class="swiper-pagination swiper-pagination04 swiper-pagination-fraction"><span class="swiper-pagination-current">3</span> / <span class="swiper-pagination-total">3</span></div>
								<!-- 이전 버튼 -->
								<div class="swiper-button-prev swiper-button-prev04" title="카드뉴스 이전 슬라이드" role="button" aria-label="Previous slide">카드뉴스 이전 슬라이드</div>
								<!-- 재생 정지 버튼 -->
								<div class="controler" title="카드뉴스 슬라이드 정지,재생">
									<button class="autoplay-control"></button>																	  
								</div>
								<!-- 다음 버튼  -->
								<div class="swiper-button-next swiper-button-next04" title="카드뉴스 다음 슬라이드" role="button" aria-label="Next slide">카드뉴스 다음 슬라이드</div>
							</div>
						</div>
					</div>
				<div class="title_container">
					<h4 class="main_sec_title">낚시 캠페인</h4>
					<!-- <a href="" class="add_btn">더보기</a> -->
				</div>
				<div class="dignity-slide3">
					<div class="con_area">
						<div class="swiper-container">
							<div class="swiper-wrapper" style="transform: translate3d(-1840px, 0px, 0px); transition-duration: 0ms;">
								
								<c:choose>
								    <c:when test="${empty campaign_list}">
								        <li>게시글이 없습니다.</li>
								    </c:when>
								    <c:otherwise>
								        <c:forEach var="item" varStatus="status" items="${campaign_list}">
								            <li><a href="javascript:void(0);" onclick="contsdesignview('${item.bo_sn}')" title="낚시캠페인 ${item.bo_subject} 페이지이동"><p class="notice_text dot-list2">${item.bo_subject}</p><span class="notice_date">${fn:substring(item.bo_insert_dt,2,10)}</span></a></li>
								        </c:forEach>
								    </c:otherwise>
								</c:choose>
								
								
								<div class="swiper-slide swiper-slide-duplicate swiper-slide-next swiper-slide-duplicate-prev" data-swiper-slide-index="2">
									<a href="javascript:void(0);" onclick="dignityview('1658')" title="낚시 캠페인  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요</p>
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요 지켜주세요</p>
											<p class="campaign_text2"><span class="color_yellow">반드시 안전 수칙을</span> 지켜주세요</p>
										</div>
									</a>
								</div>
								
								<div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="0">
									<a href="javascript:void(0);" onclick="dignityview('1781')" title="낚시 캠페인  입질의추억 초보자를 위한 입문 가이드!  페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage2.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요</p>
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요 지켜주세요</p>
											<p class="campaign_text2"><span class="color_yellow">반드시 안전 수칙을</span> 지켜주세요</p>
										</div>
									</a>
								</div>
								
								<div class="swiper-slide" data-swiper-slide-index="1">
									<a href="javascript:void(0);" onclick="dignityview('1659')" title="낚시 캠페인  진석기시대 야, 너도 낚시할 수 있어(하편) 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage3.png" alt="진석기시대 야, 너도 낚시할 수 있어(하편)바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요</p>
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요 지켜주세요</p>
											<p class="campaign_text2"><span class="color_yellow">반드시 안전 수칙을</span> 지켜주세요</p>
										</div>
									</a>
								</div>
								
								<div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="2">
									<a href="javascript:void(0);" onclick="dignityview('1658')" title="낚시 캠페인  진석기시대 야, 너도 낚시할 수 있어(상편) 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage1.png" alt="진석기시대 야, 너도 낚시할 수 있어(상편)바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요</p>
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요 지켜주세요</p>
											<p class="campaign_text2"><span class="color_yellow">반드시 안전 수칙을</span> 지켜주세요</p>
										</div>
									</a>
								</div>
								
								<div class="swiper-slide swiper-slide-duplicate swiper-slide-active" data-swiper-slide-index="0">
									<a href="javascript:void(0);" onclick="dignityview('1781')" title="낚시 캠페인 페이지이동">
										<div class="thumb">
											<img class="type2" src="/naksinuri_original/common_main/img/main/getImage2.png" alt="입질의추억 초보자를 위한 입문 가이드! 바로가기" style="height: 224px;">
										</div>
										<div class="txt_area">
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요</p>
											<p class="campaign_text">반드시 안전 수칙을 지켜주세요 지켜주세요</p>
											<p class="campaign_text2"><span class="color_yellow">반드시 안전 수칙을</span> 지켜주세요</p>
										</div>
									</a>
								</div>
							</div>
						<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
						<div class="control-box anothertype">
							<!-- 페이징 -->
							<div class="swiper-pagination swiper-pagination05 swiper-pagination-fraction"><span class="swiper-pagination-current">3</span> / <span class="swiper-pagination-total">3</span></div>
							<!-- 이전 버튼 -->
							<div class="swiper-button-prev swiper-button-prev05" title="낚시 캠페인 이전 슬라이드" tabindex="0" role="button" aria-label="Previous slide">낚시 캠페인 이전 슬라이드</div>
							<!-- 재생 정지 버튼 -->
							<div class="controler" title="낚시 캠페인 슬라이드 정지,재생">
								<button class="autoplay-control"></button>
							</div>
							<!-- 다음 버튼  -->
							<div class="swiper-button-next swiper-button-next05" title="낚시 캠페인 다음 슬라이드" tabindex="0" role="button" aria-label="Next slide">낚시 캠페인 다음 슬라이드</div>
						</div>
					</div>
				</div>
		  
			</article> --%>
			<!-- 낚시 캠페인 end -->
			<!-- 교육상담 및 문의 -->
			<article class="con con03">
				<div class="title_container">
					<h4 class="main_sec_title">교육문의</h4>
				</div>
				<div class="con_area">
					<div class="counseling">
						<img src="/naksinuri_original/common_main/img/call_char.png" alt="">
						<a href="tel:1833-7139" class="call">1833-7139</a>
						<p class="edu_hour"><strong class="edu_str">평일</strong> 오전 09:00 ~ 11:30</p>
						<p class="edu_hour"><strong class="edu_str">평일</strong> 오후 13:00 ~ 17:30</p>
					</div>
					<!-- <div class="counseling">
						<a href="tel:1833-7139" class="call"><i class="xi-call"></i>1833-7139</a>
						<div class="hour">
							<dl>
								<dt>평일 오전</dt>
								<dd>09:00~11:30</dd>
							</dl>
							<dl>
								<dt>평일 오후</dt>
								<dd>13:00~17:30</dd>
							</dl>
						</div>
					</div> -->
				</div>
			</article>
			<!-- 교육상담 및 문의 end -->
		</div>
	</section>


</div>
<!-- 스폰서 배너 -->																	  
<div class="sponsor_wrap">
	<div class="Grp">
		<div class="inner">
			<div class="sponsor-slide">
				<div class="control_btn">
					<!-- 이전 버튼 -->
					<div class="swiper-button-prev swiper-button-prev03 sponsor_btn_prev" title="스폰서 이전 슬라이드" tabindex="0" role="button" aria-label="Previous slide">스폰서 이전 슬라이드</div>
					
					<!-- 김현태추가 스폰서슬라이드 컨트롤 버튼 -->
					<div class="controler" title="스폰서 슬라이드 정지,재생">
						<button class="sponsor_autoplay-control sponsor_btn_play"></button>
					</div>
					
					<!-- 다음 버튼  -->
					<div class="swiper-button-next swiper-button-next03 sponsor_btn_next" title="스폰서 다음 슬라이드" tabindex="0" role="button" aria-label="Next slide">스폰서 다음 슬라이드</div>
				</div>
				<div class="swiper-container swiper-container-horizontal">
					<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(-3432.8px, 0px, 0px);">
						<!-- <div class="swiper-slide"><a href="http://www.fira.or.kr/" target="_blank">FIRA 한국수산자원관리공단</a></div>
						<div class="swiper-slide"><a href="http://www.kst.or.kr/" target="_blank">선박안전기술공단</a></div>
						<div class="swiper-slide"><a href="https://www.komsa.or.kr/" target="_blank">KOSMA 한국해양교통안전공단</a></div>
						<div class="swiper-slide"><a href="http://www.khoa.go.kr/" target="_blank">국립해양조사원 KHOA</a></div>
						<div class="swiper-slide"><a href="http://www.koreafca.kr/" target="_blank">사단법인 한국낚시업중앙회</a></div> -->
						
						<div class="swiper-slide"><a href="http://www.mof.go.kr/" target="_blank">해양수산부</a></div>
						<div class="swiper-slide"><a href="https://www.fipa.or.kr/" target="_blank">FIPA 한국어촌어항공단</a></div>
						<div class="swiper-slide"><a href="https://www.facebook.com/FIPAkor" target="_blank">한국어촌어항공단<br>공식 페이스북 페이지</a></div>
						<div class="swiper-slide"><a href="http://www.sealife.go.kr" target="_blank">BLUE OCEAN GUIDE<br>귀어귀촌 종합센터</a></div>
						<div class="swiper-slide"><a href="http://www.kff.or.kr/" target="_blank">KFF 한국낚시연합</a></div>
						<div class="swiper-slide"><a href="http://www.nifs.go.kr/" target="_blank">국립수산과학원</a></div>
						<div class="swiper-slide"><a href="http://www.fira.or.kr/" target="_blank">FIRA 한국수산자원관리공단</a></div>
						<div class="swiper-slide"><a href="http://www.kst.or.kr/" target="_blank" >선박안전기술공단</a></div>
						<div class="swiper-slide"><a href="https://www.komsa.or.kr/" target="_blank" >KOSMA<br>한국해양교통안전공단</a></div>
						<div class="swiper-slide"><a href="http://www.khoa.go.kr/" target="_blank" >국립해양조사원 KHOA</a></div>
						<div class="swiper-slide"><a href="http://www.koreafca.kr/" target="_blank" >사단법인 한국낚시업중앙회</a></div>
						
						<!-- <div class="swiper-slide"><a href="http://www.mof.go.kr/" target="_blank" >해양수산부</a></div>
						<div class="swiper-slide"><a href="https://www.fipa.or.kr/" target="_blank" >FIPA 한국어촌어항공단</a></div>
						<div class="swiper-slide"><a href="https://www.facebook.com/FIPAkor" target="_blank" >한국어촌어항공단 공식 페이스북 페이지</a></div>
						<div class="swiper-slide"><a href="http://www.sealife.go.kr" target="_blank" >BLUE OCEAN GUIDE 귀어귀촌 종합센터</a></div>
						<div class="swiper-slide"><a href="http://www.kff.or.kr/" target="_blank" >KFF 한국낚시연합</a></div> -->
					</div>
				<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
			</div>
		</div>
	</div>
</div>
<!-- 스폰서 배너 end -->




<script>
$(function(){
	/* 슬라이드 감춤 */
	$('span[id="slider_closer"]').on("click",function () {
		if ($('#mainSearch').css("display") == "block") {
			$('#mainSearch').slideUp(500);
		} else {
			$('#mainSearch').slideDown(500);
		}
	});
	/* 낚시터정보 좌우 */
	/* $('span[id^="info_"]').on("click",function () {
		var $idx = $(this).attr("id").replace("info_","");
		if ($idx == "prv") {
			if ($('#ltabs_conts2').css("display") == "block") {
				$('div[id^="ltabs_conts"]').hide();
				$('#ltabs_conts1').show();
				$('span[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_1').parent().addClass("on");
			} else if ($('#ltabs_conts3').css("display") == "block") {
				$('div[id^="ltabs_conts"]').hide();
				$('#ltabs_conts2').show();
				$('span[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_2').parent().addClass("on");
			}
		} else { // next
			if ($('#ltabs_conts1').css("display") == "block") {
				$('div[id^="ltabs_conts"]').hide();
				$('#ltabs_conts2').show();
				$('span[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_2').parent().addClass("on");
			} else if ($('#ltabs_conts2').css("display") == "block") {
				$('div[id^="ltabs_conts"]').hide();
				$('#ltabs_conts3').show();
				$('span[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_3').parent().addClass("on");
			}
		}
	}); */
   
	$('a[id^="info_"]').on("click",function () {
		var $idx = $(this).attr("id").replace("info_","");
		if ($idx == "prv") {
			if ($('#ltabs_conts2').css("display") == "block") {
				$('li[id^="ltabs_conts"]').hide();
				$('#ltabs_conts1').show();
				$('a[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_1').parent().addClass("on");
			} else if ($('#ltabs_conts3').css("display") == "block") {
				$('li[id^="ltabs_conts"]').hide();
				$('#ltabs_conts2').show();
				$('a[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_2').parent().addClass("on");
			}
		} else { // next
			if ($('#ltabs_conts1').css("display") == "block") {
				$('li[id^="ltabs_conts"]').hide();
				$('#ltabs_conts2').show();
				$('a[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_2').parent().addClass("on");
			} else if ($('#ltabs_conts2').css("display") == "block") {
				$('li[id^="ltabs_conts"]').hide();
				$('#ltabs_conts3').show();
				$('a[id^="ltabs_"]').parent("li").removeClass("on");
				$('#ltabs_3').parent().addClass("on");
			}
		}
	});
	/* 낚시 전문교육 슬라이드 */
	/* $('#bnrSlider .bnrWrp').carouFredSel({
		items: { visible:4,minimum:null,start:0},
		circular: true,
		scroll: {
			items: 1,
			fx: "scroll",
			easing: "swing",
			pauseOnHover  : true
		},
		//scroll:{ pauseOnHover : true},
		//auto : { pauseDuration : 4000 },
		auto : false,
		padding : null,
		prev : {  button  : "#bnrSlider .prev" },
		next : {  button  : "#bnrSlider .next" }
	}); */
	/* 하단 스폰서 슬라이드 */
	$('#sponcerSlide .spncrWrp').carouFredSel({
		items: { visible:5,minimum:null,start:0},
		circular: true,
		scroll: {
			items: 1,
			fx: "scroll",
			easing: "swing",
			pauseOnHover  : true
		},
		//scroll:{ pauseOnHover : true},
		auto : { pauseDuration : 4000 },
		padding : null,
		prev : {  button  : ".act_btn #nprv" },
		next : {  button  : ".act_btn #nnxt" }
	});
	/* 새롭게 작성한 부분 ## */
	// 비주얼 슬라이드
	var visualSlide = new Swiper ('.visual-slide .swiper-container', {
		slidesPerView: 1,
		effect : 'fade',
		//loop: true,
		allowTouchMove: false,
		autoplay: { 
			delay: 4000, 
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next_s', 
			prevEl: '.swiper-button-prev_s', 
		},
		pagination: {
			el: '.pagination_fraction', 
			type: 'fraction',
		},
	});
	//김현태추가 비주얼 슬라이드 컨트롤 박스
	$('.visual-slide .sponsor_autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			visualSlide.autoplay.stop();
		} else {
			visualSlide.autoplay.start();
		}
	});
	
	// progress Bar
	var pagingSwiper = new Swiper(".visual-slide .swiper-container", {
		//loop: true,
		effect : 'fade',
		allowTouchMove: false,
		pagination: {
			el: ".pagination_progress",
			type: "progressbar",
		},
	}); 
	// visualSlide로 progress Bar 제어
	visualSlide.controller.control = pagingSwiper;
	// 낚시전문 교육 프로그램 슬라이드
	var eduSlide = new Swiper ('.edu-slide .swiper-container', {
		slidesPerView: 1,
		loop: true,
		autoplay: { 
			delay: 4000,
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next01', 
			prevEl: '.swiper-button-prev01', 
		},
		pagination: {
			el: '.swiper-pagination01', 
			type: 'fraction',
		},
	});
	// 낚시전문 교육 슬라이드 컨트롤 박스
	$('.edu-slide .autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			eduSlide.autoplay.stop();
		} else {
			eduSlide.autoplay.start();
		}
	});
	// 낚시의 품격 슬라이드
	var dignitySlide = new Swiper ('.dignity-slide .swiper-container', {
		slidesPerView: 1,
		loop: true,
		autoplay: {
			delay: 4000, 
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next02', 
			prevEl: '.swiper-button-prev02', 
		},
		pagination: {
			el: '.swiper-pagination02', 
			type: 'fraction',
		},
		a11y: { // 웹접근성 
		enabled: true,
		prevSlideMessage: '이전 슬라이드',
		nextSlideMessage: '다음 슬라이드',   
		slideLabelMessage: '총 {{slidesLength}}장의 슬라이드 중 {{index}}번 슬라이드 입니다.',
		},
	}); 
	// 낚시의 품격 슬라이드 컨트롤 박스
	$('.dignity-slide .autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			dignitySlide.autoplay.stop();
		} else {
			dignitySlide.autoplay.start();
		}
	});

	// 팝업존 슬라이드
	var dignitySlide2 = new Swiper ('.dignity-slide2 .swiper-container', {
		slidesPerView: 1,
		loop: true,
		autoplay: {
			delay: 4000, 
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next04', 
			prevEl: '.swiper-button-prev04', 
		},
		pagination: {
			el: '.swiper-pagination04', 
			type: 'fraction',
		},
	}); 
	// 팝업존 슬라이드 컨트롤 박스
	$('.dignity-slide2 .autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			dignitySlide2.autoplay.stop();
		} else {
			dignitySlide2.autoplay.start();
		}
	});

	// 낚시캠페인 슬라이드
	var dignitySlide3 = new Swiper ('.dignity-slide3 .swiper-container', {
		slidesPerView: 1,
		loop: true,
		autoplay: {
			delay: 4000, 
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next05', 
			prevEl: '.swiper-button-prev05', 
		},
		pagination: {
			el: '.swiper-pagination05', 
			type: 'fraction',
		},
	}); 
	// 낚시캠페인 슬라이드 컨트롤 박스
	$('.dignity-slide3 .autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			dignitySlide3.autoplay.stop();
		} else {
			dignitySlide3.autoplay.start();
		}
	});
	
	// 스폰서 배너
	// 
	var sponsorSlide = new Swiper ('.sponsor-slide .swiper-container', {
		slidesPerView: 5,
		spaceBetween: 40,
		loop: true,
		autoplay: { 
			delay: 4000, 
			disableOnInteraction: false, 
		}, 
		navigation: {
			nextEl: '.swiper-button-next03', 
			prevEl: '.swiper-button-prev03', 
		},
		breakpoints: {
			1280: { 
				slidesPerView: 4,
			},
			1024: { 
				slidesPerView: 3,
			},
			768: { 
				slidesPerView: 1,
				spaceBetween: 0,
				centeredSlides: true, 
			},
		},
	});
	//김현태추가 스폰서 슬라이드 멈춤 
	$('.sponsor-slide .sponsor_autoplay-control').click(function(){
		$(this).toggleClass('on');
		if($(this).hasClass('on')) {
			sponsorSlide.autoplay.stop();
		} else {
			sponsorSlide.autoplay.start();
		}
	});
	
	
	// 팝업존 이미지 비율
	$(window).resize(function(){
		var winWidth = $(window).width();
		var boxWidth = $('.thumb img').width();
		if(winWidth <= 768){
			$('.thumb img.type1').height(boxWidth*1);
			$('.thumb img.type2').height(boxWidth*0.681);
		} else {
			$('.thumb img').height(boxWidth);
		}
	}); 
	//메인 공지사항 탭 메뉴
	// jQuery(function($){
	// 	var $tab_list = $('.notice_list');
	// 	$tab_list.removeClass('jx').find('ul ul').hide();
	// 	$tab_list.find('li li.active').parents('li').addClass('active');
	// 	$tab_list.find('li.active>ul').show();
	// 	$tab_list.each(function(){
	// 		var $this = $(this);
	// 		$this.height($this.find('li.active>ul').height()+32);
	// 	});
	// 	function listTabMenuToggle(event){
	// 		var $this = $(this);
	// 		$this.next('ul').show().parent('li').addClass('active').siblings('li').removeClass('active').find('>ul').hide();
	// 		$this.closest('.notice_list').height($this.next('ul').height()+32);
	// 		if($this.attr('href') === '#'){
	// 			return false;
	// 		}
	// 	}
	// 	$tab_list.find('>ul>li>a').click(listTabMenuToggle).focus(listTabMenuToggle);
	// });
});





</script>   

<script>
$(function(){
	/* ## 낚시여행기 탭 */
	/* $('span[id^="tabs_"]').click(function () {
		$('span[id^="tabs_"]').parent("li").removeClass("on");
		$('div[id^="tabs_conts"]').hide();
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("tabs_","tabs_conts");
		$('#'+$idx).show();
	}); */
	$('a[id^="tabs_"]').click(function () {
		$('a[id^="tabs_"]').parent("li").removeClass("on");
		$('a[id^="tabs_"]').parent("li").attr("title", "");
		$('div[id^="tabs_conts"]').hide();
		$(this).parent().addClass("on");
		$(this).parent().attr("title", "선택됨");
		var $idx = $(this).attr("id").replace("tabs_","tabs_conts");
		$('#'+$idx).show();
	});
	$('a[id^="tabs_"]').bind('keydown', function(e){
		$('a[id^="tabs_"]').parent("li").removeClass("on");
		$('div[id^="tabs_conts"]').hide();  
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("tabs_","tabs_conts");
		$('#'+$idx).show();
	});
	/* 낚시터정보 */
	$('span[id^="ltabs_"]').click(function () {
		$('span[id^="ltabs_"]').parent("li").removeClass("on");
		$('span[id^="ltabs_"]').parent("li").attr("title", "");
		$('li[id^="ltabs_conts"]').hide();
		$(this).parent().addClass("on");
		$(this).parent().attr("title", "선택됨");
		var $idx = $(this).attr("id").replace("ltabs_","ltabs_conts");
		$('#'+$idx).show();
	});
	/* 낚시터정보 */
	$('a[id^="ltabs_"]').on("click focusin",function(e){
		$('a[id^="ltabs_"]').parent("li").removeClass("on");
		$('a[id^="ltabs_"]').parent("li").attr("title", "");
		$('li[id^="ltabs_conts"]').hide();
		$(this).parent().addClass("on");
		$(this).parent().attr("title", "선택됨");
		var $idx = $(this).attr("id").replace("ltabs_","ltabs_conts");
		$('#'+$idx).show();
	});
	/* 전문교육탭 */
	$('span[id^="wtabs_"]').click(function () {
		$('span[id^="wtabs_"]').parent("li").removeClass("on");
		$('span[id^="wtabs_"]').parent("li").attr("title", "");
		$('div[id^="wtabs_conts"]').hide();
		$(this).parent().addClass("on");
		$(this).parent().attr("title", "선택됨");
		var $idx = $(this).attr("id").replace("wtabs_","wtabs_conts");
		$('#'+$idx).show();
	});
	/* 공지사항 탭 */
	$('span[id^="ctabs_"]').click(function () {
		$('span[id^="ctabs_"]').parent("li").removeClass("on");
		$('span[id^="ctabs_"]').parent("li").attr("title", "");
		$('div[id^="ctabs_conts"]').hide();
		$(this).parent().addClass("on");
		$(this).parent().attr("title", "선택됨");
		var $idx = $(this).attr("id").replace("ctabs_","ctabs_conts");
		$('#'+$idx).show();
	});
	/* 낚시캠페인&낚시정책 */
	var $tab_list = $('.tab.list');
	$tab_list.removeClass('jx').find('ul ul').hide();
	$tab_list.find('li li.active').parents('li').addClass('active');
	$tab_list.find('li.active>ul').show();
	$tab_list.each(function(){
		var $this = $(this);
		$this.height($this.find('li.active>ul').height()+40);
	});
	function listTabMenuToggle(event){
		console.log('=====');
		var $this = $(this);
		$this.next('ul').show().parent('li').addClass('active').siblings('li').removeClass('active').find('>ul').hide();
		$this.closest('.tab.list').height($this.next('ul').height()+40);
		if($this.attr('href') === '#'){
			return false;
		}
	}
	$tab_list.find('>ul>li>a').click(listTabMenuToggle).focus(listTabMenuToggle);
	/* 공지사항&낚시전문교육 */
	var $tab2List = $('.tab2List');
	$tab2List.removeClass('jx').find('ul ul').hide();
	$tab2List.find('li li.active').parents('li').addClass('active');
	$tab2List.find('li.active>ul').show();
	$tab2List.each(function(){
		var $this = $(this);
		$this.height($this.find('li.active>ul').height()+40);
	});
	function listTabMenuToggle2(event){
		console.log('=====2');
		var $this = $(this);
		$this.next('ul').show().parent('li').addClass('active').siblings('li').removeClass('active').find('>ul').hide();
		$this.closest('.tab.list').height($this.next('ul').height()+40);
		if($this.attr('href') === '#'){
			return false;
		}
	}
	// 접근성 대비
	//$tab2List.find('>ul>li>a').click(listTabMenuToggle2).focus(listTabMenuToggle2);
	$tab2List.find('>ul>li>a').focus(listTabMenuToggle2);
});
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
	form.action="/share/column/view.do";
	form.submit(); 
}
function contsdesignview(bo_sn){
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	form.action="/promotion/campaign/view.do";
	form.submit(); 
}
function policyview(bo_sn){
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	form.action="/promotion/policy/view.do";
	form.submit(); 
}
function infoview(bo_sn){
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	form.action="/promotion/info/view.do";
	form.submit(); 
}
function dignityview(bo_sn){
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	form.action="/lesson/dignity/view.do";
	form.submit(); 
}

function nak_sub(nak_id){
	$('#nak_id').val(nak_id);
	$("#view_go").attr("action", "/info/fishjob/view.do");
	$('#view_go').submit();
}
</script>

<form:form commandName="eduCenterBoardVO" id="noticeEduForm" name="noticeEduForm" method="post">
	<input type="hidden" id="BD_SN" name="BD_SN" value=""/>
</form:form>
<form:form commandName="eduMyHistoryVO" id="crsViewForm" name="crsViewForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
</form:form>

<script>
$(".clk_modal_eduinfo").click(function() {
	var form = document.getElementById('crsViewForm');
	form.CRS_SN.value = $(this).attr('data-crs-sn');
	form.action = '';
	var data_linkurl = $(this).attr('data-linkurl');
	$.ajax({
		type:"POST",
		url :data_linkurl,
		data:$('#crsViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal('show');
		},
		complete : function() {
			//console.log('complete!');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}); 
function noticeEduView(bdsn) {
	document.noticeEduForm.BD_SN.value = bdsn;
	document.noticeEduForm.action = "<c:url value='/educenter/board/notice/view.do'/>";
	document.noticeEduForm.submit();
}
</script>

<!-- 안전해신호등 스크립트 start -->
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
	    level: 14, // 지도의 확대 레벨
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
	
	
		/* $('.focus_add').attr('tabindex','0');
		arrOverlayObjContainer[addrSeq].setMap(null);
		
		setTimeout(function(){
			$('#'+replace_id[0]).focus();		
		}, 100); 마커 탭인덱스 삭제*/
		
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
					/* $(marker.ca).parent().addClass('focus_add').attr('tabindex','0').css('height','40px').css('width','25px');
					$(marker.ca).parent().attr('id', item.name ); //포커싱
					$(marker.ca).parent().keyup(function(e){
						if(e.keyCode == 13) {
							$('.focus_add').attr('tabindex','-1');
							kakao.maps.event.trigger(marker, 'click');
							
						
						}
					}); 마커 탭인덱스 삭제*/	
				    
				    
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
<!-- 안전해신호등 스크립트 end -->

<%@include file="../naksinuri/layout/tail.jsp"%>

