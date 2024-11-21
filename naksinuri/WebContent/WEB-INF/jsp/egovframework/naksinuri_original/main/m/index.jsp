<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@include file="../../naksinuri/layout/m/head.jsp"%>

<form:form commandName="viewform" id="viewform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<form:form id="view_go" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="nak_id" id="nak_id" />
</form:form>

	<c:if test="${not empty list_banner_center}">
	<style>
	#mainNaksiBtnBox{
		padding-top: 10px;
		background-color: #D7F2FF;
		/*height:220px;*/
		position: relative;
	}
	
	#mainNaksiBtnBox .bx-wrapper{
		/*height: 210px;*/
	}
	#mainNaksiBtnBox .bx-wrapper .bx-viewport{
		height: 165px;
	}
	#mainNaksiBtnBox .bx-wrapper img{
		/*height: 180px;*/
		display: inline;
		width: 100%;
	}
	
	#mainNaksiBtnBox .bx-wrapper .bx-pager, .bx-wrapper .bx-controls-auto{
		bottom: 3px;
	}
	
	.maintop_slider_control > * {
	    position: absolute;
	    width: 41px;
	    background: #000;
	    background: rgba(0,0,0,0.5);
	    display: block;
	    height: 43px;
	    line-height: 36px;
	    text-align: center;
	    font-size: 20px;
	}
	.maintop_slider_control a.prev {
	    top: 38%;
	    left: 0;
	}
	.maintop_slider_control a.next {
	    top: 38%;
	    right: 0;
	}
	.maintop_slider .maintop_slider_control > a.play, .maintop_slider .maintop_slider_control > a.pause {
	    width: 40px;
	    height: 40px;
	    line-height: 35px;
	}
	.maintop_slider .maintop_slider_control a.play {
	    top: 0;
	    right: 0;
	}
	.maintop_slider .maintop_slider_control a.pause { 
	    top: 0;
	    right: 0;
	}
	</style>
	<section id="mainNaksiBtnBox">
		<ul>
			<!-- <li><a href="/m/gongmo/mof/intro.do"><img src='/naksinuri_original/edu/vis.png' alt="낚시누리 메인 슬라이드 배너 3"/></a></li>
			<li><a href="/m/sosig/event/view.do?evn_no=154"><img src='/naksinuri_original/edu/vis.png' alt="낚시누리 메인 슬라이드 배너 3"/></a></li>
			<li><a href="/m/sosig/event/view.do?evn_no=153"><img src='/naksinuri_original/edu/vis.png' alt="낚시누리 메인 슬라이드 배너 3"/></a></li> -->
			<c:forEach var="item" items="${list_banner_center}">
				<li style="text-align:center;">
					<a <c:if test="${not empty item.PP_LINK_URL}">
								href="${item.PP_LINK_URL}" target="${item.PP_LINK_TARGET}"
							</c:if> 
							<c:if test="${empty item.PP_LINK_URL}">
								href="#;"
							</c:if>>
						<img src='/cmm/fms/getImage.do?atchFileId=${item.PP_FILE}&fileSn=1' alt="${item.PP_CONT}"/>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<div class="maintop_slider_control">
		                     
		  <c:set var="scroll_nav_bar_style" value="display:none;"/>
		  <c:if test="${fn:length(list_banner_center)>1}"><c:set var="scroll_nav_bar_style" value=""/></c:if>                                   
		                                     
          <!-- <div class="slide_number">0/0</div> -->
          
          <a href="#;" data-function="prev" class="prev" style="${scroll_nav_bar_style}" tabindex="0" title="이전">
             <img src="/common/img/roll_left.png" alt="이전">
      	  </a>
          <a href="#;" data-function="next" class="next" style="${scroll_nav_bar_style}" tabindex="0" title="다음">
             <img src="/common/img/roll_right.png" alt="다음">
          </a>
          <!-- <a href="#;" data-function="play" class="play" style="display: none;" tabindex="0" title="재생">
             <img src="/common/img/edu/roll_play.png" alt="재생">
          </a>
          <a href="#;" data-function="pause" class="pause" tabindex="0" title="정지">
             <img src="/common/img/edu/roll_pause.png" alt="정지">
          </a> -->
         </div>
		                        
		<script>
			var notice_slider = $('#mainNaksiBtnBox ul').bxSlider({
				auto: true,
				controls: false,
				pause: 5000
			});
			
			$(".maintop_slider_control a").click(function(event) {
	        	event.preventDefault();

	        	var func = $(this).attr('data-function');
	            if( func == 'next' ) {
	               notice_slider.goToNextSlide();
	            } else if( func == 'prev' ) {
	               notice_slider.goToPrevSlide();
	            } else if( func == 'play' ) {
	               notice_slider.startAuto();
	               $(".maintop_slider_control a[data-function='play']").hide();
	               $(".maintop_slider_control a[data-function='pause']").show();
	            } else if( func == 'pause' ) {
	               notice_slider.stopAuto();
	               $(".maintop_slider_control a[data-function='play']").show();
	               $(".maintop_slider_control a[data-function='pause']").hide();
	            }
	     	});
		</script>
	</section>
	</c:if>
	
	<div class="sky_Wrp">
		<div class="sky_Grp">
		
			<!-- <div class="taC">
				<p><img src="/naksinuri_original/edu/title_travel.png" alt="즐거운 낚시 정볼르 한눈에! 낚시 여행" style="width:60%;"/></p>
			</div>//taC// -->
	
			<!-- ##start## 낚시여행기, 낚시 칼럼 ##### -->
			<div class="travel_gellery_Grp">
				<div id="tabs_container" class="tabs_design">
					<ul id="tabs">
						<li class="on"><span id="tabs_1">공지사항</span></li>	
						<li class=""><span id="tabs_2">알림마당</span></li>	
						<li class=""><span id="tabs_3">낚시정책</span></li>	
						<li class=""><span id="tabs_4">낚시캠페인</span></li>	
					</ul>
				</div><!-- // tabs_design // -->
				
				
				<p class="blk" style="height:10px;"></p>
				
				<style>
				.tab_latest_wrap {
				    height: 190px;
				    border-top: 3px solid #337ab7;
				    background-color: #ffffff;
				}
				.nopm, .nopm > li, .nopm > dt, .nopm > dd {
				    padding: 0;
				    margin: 0;
				    list-style: none;
				}
				.latestarea{
					padding: 5px 10px;
				}
				.latestarea ul li{
					border-bottom: 1px solid #d7d7d7;
    				padding: 12px 5px 17px 0px;
				}
				.latestarea ul li:last-child{
					border-bottom: 0px;
    				
				}
				.latestarea ul li a {
				    overflow: hidden;
				    font-size: 13px;
				    text-overflow: ellipsis;
				    white-space: nowrap;
				    color: #333;
				    padding-left: 5px;
				}
				.latestarea ul li a .date {
				    color: #999;
				    font-size: 12px;
				    float: right;
				}
				.label-primary-custom {
				    background-color: #ffffff;
				    border: 1px solid #337ab7;
				}
				.label-custom {
				    display: inline;
				    padding: .2em .6em .3em;
				    font-size: 75%;
				    font-weight: 700;
				    line-height: 1;
				    color: #337ab7;
				    text-align: center;
				    white-space: nowrap;
				    vertical-align: baseline;
				    border-radius: .25em;
				}
				</style>
				<div class="tab_latest_wrap">
					<div class="latestarea tab_latest" id="tabs_conts1" style="">
						<ul class="nopm">
							<c:forEach var="item" items="${notice_list }">
							<li>
								<span class="label-custom label-primary-custom">공지사항</span>
								<a href="#" onclick="noticeview('${item.bo_sn}')" alt="공지사항  ${item.bo_subject } 페이지이동">
									<em>
										<c:choose>
		                                    <c:when test="${fn:length(item.bo_subject) > 21}">
		                                          <c:out value="${fn:substring(item.bo_subject,0,20)}"/>...
		                                    </c:when>
		                                    <c:otherwise>
		                                       ${item.bo_subject }
		                                    </c:otherwise>
		                                 </c:choose>
									</em>
									<span class="date">${fn:substring(item.bo_insert_dt,0,10) }</span>
								</a>
							</li>
							</c:forEach>
						</ul>
						<!-- <a href="/m/board/notice/list.do" class="more"><i class="fas fa-plus"></i></a> -->
					</div>	
								
					<div class="latestarea tab_latest" id="tabs_conts2" style="display:none;">
						<ul class="nopm">
							<c:forEach var="item" items="${info_list }">
							<li>
								<span class="label-custom label-primary-custom">알림마당</span>
								<a href="#" onclick="infoview('${item.bo_sn}')" alt="알림마당  ${item.bo_subject } 페이지이동">
									<em>
										<c:choose>
		                                    <c:when test="${fn:length(item.bo_subject) > 21}">
		                                          <c:out value="${fn:substring(item.bo_subject,0,20)}"/>...
		                                    </c:when>
		                                    <c:otherwise>
		                                       ${item.bo_subject }
		                                    </c:otherwise>
		                                 </c:choose>
									</em>
									<span class="date">${fn:substring(item.bo_insert_dt,0,10) }</span>
								</a>
							</li>
							</c:forEach>
						</ul>
						<!-- <a href="/m/board/notice/list.do" class="more"><i class="fas fa-plus"></i></a> -->
					</div>				
				
					<div class="latestarea tab_latest" id="tabs_conts3" style="display:none;">
						<ul class="nopm">
							<c:forEach var="item" items="${policy_list }">
							<li>
								<span class="label-custom label-primary-custom">낚시정책</span>
								<a href="#" onclick="policyview('${item.bo_sn}')" alt="낚시정책  ${item.bo_subject } 페이지이동">
									<em>
										<c:choose>
		                                    <c:when test="${fn:length(item.bo_subject) > 21}">
		                                          <c:out value="${fn:substring(item.bo_subject,0,20)}"/>...
		                                    </c:when>
		                                    <c:otherwise>
		                                       ${item.bo_subject }
		                                    </c:otherwise>
		                                 </c:choose>
									</em>
									<span class="date">${fn:substring(item.bo_insert_dt,0,10) }</span>
								</a>
							</li>
							</c:forEach>
						</ul>
						<!-- <a href="/m/board/notice/list.do" class="more"><i class="fas fa-plus"></i></a> -->
					</div>				
				
					<div class="latestarea tab_latest" id="tabs_conts4" style="display:none;">
						<ul class="nopm">
							<c:forEach var="item" items="${campaign_list }">
							<li>
								<span class="label-custom label-primary-custom">낚시캠페인</span>
								<a href="#" onclick="contsdesignview('${item.bo_sn}')" alt="낚시캠페인  ${item.bo_subject } 페이지이동">
									<em>
										<c:choose>
		                                    <c:when test="${fn:length(item.bo_subject) > 21}">
		                                          <c:out value="${fn:substring(item.bo_subject,0,20)}"/>...
		                                    </c:when>
		                                    <c:otherwise>
		                                       ${item.bo_subject }
		                                    </c:otherwise>
		                                 </c:choose>
									</em>
									<span class="date">${fn:substring(item.bo_insert_dt,0,10) }</span>
								</a>
							</li>
							</c:forEach>
							
						</ul>
						<!-- <a href="/m/board/notice/list.do" class="more"><i class="fas fa-plus"></i></a> -->
					</div>
					
				</div>
							
			</div><!--//travel_gellery_Grp//-->
			<!-- ##end## 낚시여행기, 낚시 칼럼 ## -->

			<!-- ##start## 낚시터정보 ##### -->
			<div class="travel_info_Grp">
				<div class="info_act_button">
					<span id="info_prv" class="prv"><img src="/naksinuri_original/edu/tprv.png" alt="" /></span>
					<span id="info_nxt" class="nxt"><img src="/naksinuri_original/edu/tnxt.png" alt="" /></span>
				</div><!--//info_act_button//-->
				<h3 class="title">낚시터정보</h3>


				<div class="latsb_pos_Grp">
					<div class="ltabs_design">
						<ul>
							<li class="on"><span id="ltabs_1">선상낚시</span></li>	
							<li class=""><span id="ltabs_2">바다낚시</span></li>	
							<li class=""><span id="ltabs_3">민물낚시</span></li>	
						</ul>
					</div><!-- // ltabs_design // -->
					
					<div class="ltabs_contents">
						<div id="ltabs_conts1" class="ltabs_contsdesign" style="">
							<!-- <p><img src="/naksinuri_original/edu/pic03.png" alt="" /></p> -->
							<c:forEach var="item" items="${choo_job_boatfishing }">
								<p>
									<a href="#;" onclick="nak_sub('${item.nak_id}')" alt="선상낚시 ${item.co_nm } 페이지이동" >
									<c:choose>
										<c:when test="${item.orignl_file_nm ne null}"><%-- item.file_sn eq '1' 는 지워야 이미지가 노출됨 --%>
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.co_mimgsrc}"/>&fileSn=<c:out value="${item.file_sn}"/>' alt="선상낚시 ${item.orignl_file_nm} 이미지" width="100%" height="100%" />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg" alt="선상낚시 기본이미지" width="100%" height="100%" />
										</c:otherwise>
									</c:choose>
									</a>
								</p>
							</c:forEach>
						</div><!-- // ltabs_contsdesign // -->
						
						<div id="ltabs_conts2" class="ltabs_contsdesign" style="display:none;">
							<c:forEach var="item" items="${choo_job_seafishing }">
								<p>
									<a href="#;" onclick="nak_sub('${item.nak_id}')" alt="바다낚시 ${item.co_nm } 페이지이동" >
									<c:choose>
										<c:when test="${item.orignl_file_nm ne null}"><%-- item.file_sn eq '1' 는 지워야 이미지가 노출됨 --%>
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.co_mimgsrc}"/>&fileSn=<c:out value="${item.file_sn}"/>' alt="바다낚시 ${item.orignl_file_nm} 이미지" width="100%" height="100%" />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg" alt="바다낚시 기본이미지" width="100%" height="100%" />
										</c:otherwise>
									</c:choose>
									</a>
								</p>
							</c:forEach>
						</div><!-- // ltabs_contsdesign // -->

						<div id="ltabs_conts3" class="ltabs_contsdesign" style="display:none;">
							<!-- <p><img src="/naksinuri_original/edu/pic03.png" alt="" /></p> -->							
							<c:forEach var="item" items="${choo_job_riverfishing }">
								<p>
									<a href="#;" onclick="nak_sub('${item.nak_id}')" alt="민물낚시 ${item.co_nm } 페이지이동" >
									<c:choose>
										<c:when test="${item.orignl_file_nm ne null}"><%-- item.file_sn eq '1' 는 지워야 이미지가 노출됨 --%>
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.co_mimgsrc}"/>&fileSn=<c:out value="${item.file_sn}"/>' alt="민물낚시 ${item.orignl_file_nm} 이미지" width="100%" height="100%" />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg" alt="민물낚시 기본이미지" width="100%" height="100%" />
										</c:otherwise>
									</c:choose>
									</a>
								</p>
							</c:forEach>
						</div><!-- // ltabs_contsdesign // -->						
					</div><!--//ltabs_contents//-->
					<p class="clr"></p>
				</div><!--//latsb_pos_Grp//-->
			</div><!--//travel_info_Grp//-->
			<!-- ##end## 낚시터정보 ## -->


			<!-- ##start## 날씨정보 ##### -->
			<div class="icon_info Grp">
				<ul class="icon_info_ul">
					<li><a href="http://www.kma.go.kr/index.jsp"  target="_blank" alt="날씨정보 외부링크"><span class="icon01">날씨정보 아이콘</span><p>날씨정보</p></a></li>
					<li><a href="http://www.khoa.go.kr/swtc/main.do"  target="_blank" alt="조석정보 외부링크"><span class="icon02">조석정보 아이콘</span><p>조석정보</p></a></li>
					<li><a href="http://www.khoa.go.kr/koofs/kor/seawf/sea_wf.do?menuNo=01&link="  target="_blank" alt="조류예보 외부링크"><span class="icon03">조류예보 아이콘</span><p>조류예보</p></a></li>
				</ul>
				<p class="blk" style="height:15px;"></p>
				<ul class="icon_info_ul">
					<li><a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=31201000"  target="_blank" alt="바다갈라짐 외부링크"><span class="icon04">바다갈라짐 아이콘</span><p>바다갈라짐</p></a></li>
					<li><a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=53201000"  target="_blank" alt="해양교실 외부링크"><span class="icon05">해양교실 아이콘</span><p>해양교실</p></a></li>
					<li><a href="http://www.khoa.go.kr/koofs/kor/observation/obs_real.do?obsItem=WT_TEM&imgIdx=05"  target="_blank" alt="수온정보 외부링크"><span class="icon06">수온정보 아이콘</span><p>수온정보</p></a></li>
				</ul>
				<p class="clr"></p>
			</div><!--//icon_info//-->
			<!-- ##end## 날씨정보 ## -->



		</div><!--// sky_Grp //-->
	</div><!--//sky_Wrp//-->


	<div class="orgWrp">
		<div class="orgGrp">


			<!-- ##start## 낚시전문교육 ##### -->
			<div class="taC">
				<p><img src="/naksinuri_original/edu/title_expedu.png" style="width:70%;" /></p>
			</div><!--//taC//-->

			<p class="blk" style="height:20px;"></p>

			<div class="Grp">
<%--
				<div class="wtabs_design">
					<ul>
											<li class="on"><span id="wtabs_1">서울·인천·경기</span></li>	
											<li class=""><span id="wtabs_2">대전·충북·충남·세종</span></li>	
											<li class=""><span id="wtabs_3">전북</span></li>	
											<li class=""><span id="wtabs_4">광주·전남</span></li>	
											<li class=""><span id="wtabs_5">대구·울산·경북</span></li>	
											<li class=""><span id="wtabs_6">부산·경남</span></li>	
											<li class=""><span id="wtabs_7">강원</span></li>	
											<li class=""><span id="wtabs_8">제주</span></li>	
										</ul>
					<p class="clr"></p>
				</div><!-- // tabs_design // -->
				 
				<p class="blk" style="height:20px;"></p>
				--%>
				<div id="wtabs_conts1" class="wtabs_contentsdesign" style="">

					<c:choose>
						<c:when test="${empty list_crs}">
							<div class="text-center font-weight-bold pt-20 pb-50">
								현재 준비 된 교육일정이 없습니다.
							</div>	
						
						</c:when>
						<c:otherwise>
	
							<div id="bnrSlider">
								<div class="prev"><img src="/naksinuri_original/edu/prv.png" alt="" /></div>
								<div class="next"><img src="/naksinuri_original/edu/nxt.png" alt="" /></div>
								<ul class="wsliders bnrWrp">
								<c:forEach var="item" varStatus="status" items="${list_crs}">
									
									<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
									<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
									<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
									<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
									<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd (E)" var="CRS_STR_DT"/>
									<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.MM.dd (E)" var="CRS_END_DT"/>
									<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_STR_DT"/>
									<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.MM.dd (E)" var="RECRUIT_END_DT"/>
								
									<c:set var="MBR_MAX_CNT_STR" value="" />
									<c:choose>
										<c:when test="${not empty item.MBR_MAX_CNT && item.MBR_MAX_CNT eq '0' }">
											<c:set var="MBR_MAX_CNT_STR" value="무제한" />
										</c:when>
										<c:otherwise>
											<c:set var="MBR_MAX_CNT_STR" value="${item.MBR_MAX_CNT}명" />	
										</c:otherwise>
									</c:choose>
									
									<c:set var="CRS_DT_STR" value="" />
									<c:choose>
										<c:when test="${not empty item.TYPE_GB && item.TYPE_GB eq 'offline' }">
											<c:set var="CRS_DT_STR" value="${CRS_STR_DT}" />
										</c:when>
										<c:otherwise>
											<c:set var="CRS_DT_STR" value="${CRS_STR_DT}~${CRS_END_DT}" />	
										</c:otherwise>
									</c:choose>
									
								
									<li class="wslider_Set bnrBx">
										<a href="javascript:void(0)" class="clk_modal_eduinfo" 
							          		data-crs-sn="${item.CRS_SN}"
							          		data-linkurl="/educenter/m/trnng/view.do">
											<div class="slider_Pn">
												<p class="sbj">
												<c:forEach var="item_category" items="${list_mbr_cd }">
													<c:if test="${item_category.CD_ID eq item.CRS_MBR_CD }">${item_category.CD_NM}</c:if>
												</c:forEach>
												</p>
												<div class="cont">
													<p>${item.CRS_PLACE}</p>
													${item.CRS_ADDR}
												</div>
												<p class="tx font-size-12">교육신청기간 : ${RECRUIT_STR_DT}~${RECRUIT_END_DT}</p>
			                                    <p class="tx font-size-12">교육일 : ${CRS_DT_STR}</p>
			                                    <p class="tx font-size-12">교육신청인원 : ${item.MBR_CUR_CNT}명/${MBR_MAX_CNT_STR}</p>
											</div>
										</a>
									</li>
									<%--
									<li class="wslider_Set bnrBx">
										<a href="#">
											<div class="slider_Pn">
												<p class="sbj">낚시어선업차 및 선원</p>
												<div class="cont">
													<p>용인시 농업기술 센터(2층 대강당)</p>
													인천 옹진군 영흥면 영흥로176번길 8 (내리 1867)
												</div><!--//cont//-->
												<p class="tx">교육신청기간 : 19/01/01~19/02/18</p>
												<p class="tx">교육일 : 19/02/19</p>
											</div><!--//slider_Pn//-->
										</a>
									</li>	
									 --%>
								</c:forEach>					
								</ul>
								<p class="clr"></p>
								<span class="pagination"></span>
							</div><!--//wslider_Set//-->
							
						</c:otherwise>
					</c:choose>

				</div><!-- // wtabs_contentsdesign // -->
<%--				
				<div id="wtabs_conts2" class="wtabs_contentsdesign" style="display:none;">
					대전
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts3" class="wtabs_contentsdesign" style="display:none;">
					전북
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts4" class="wtabs_contentsdesign" style="display:none;">
					광주
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts5" class="wtabs_contentsdesign" style="display:none;">
					대구
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts6" class="wtabs_contentsdesign" style="display:none;">
					부산
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts7" class="wtabs_contentsdesign" style="display:none;">
					강원
				</div><!-- // wtabs_contentsdesign // -->

				<div id="wtabs_conts8" class="wtabs_contentsdesign" style="display:none;">
					제주
				</div><!-- // wtabs_contentsdesign // -->

				<p class="blk" style="height:30px;"></p>
 --%>
				<ul class="threeCon">
					<li>
						<div>
							<a href="/educenter/m/trnng/list.do" class="con01">
								<p>교육일정</p>
								낚시관련 교육강좌를<br/>확인할 수 있습니다.
								<p><img src="/naksinuri_original/edu/go.png" alt="바로가기" /></p>
							</a><!--//con01//-->
						</div>
					</li>
					<li>
						<div>
							<a href="/educenter/m/main/curriculumGuide.do" class="con02">
								<p>교육과정</p>
								낚시전문교육 교육과정을<br/>확인할 수 있습니다.
								<p><img src="/naksinuri_original/edu/go.png" alt="바로가기" /></p>
							</a><!--//con01//-->
						</div>
					</li>
					<li>
						<div>
							<a href="/educenter/m/board/faq/list.do" class="con03">
								<p>수강 FAQ</p>
								낚시전문교육 관련<br/>궁금하신 사항 해소시켜드립니다.
								<p><img src="/naksinuri_original/edu/go.png" alt="바로가기" /></p>
							</a><!--//con01//-->
						</div>
					</li>
				</ul>
				<p class="blk" style="height:10px;"></p>
			</div><!--//Grp//-->
			<!-- ##end## 낚시전문교육 ## -->


		</div><!--//orgGrp//-->
	</div><!--//orgWrp//-->


	<!-- ##start## 커뮤니티하단 ##### -->
	<div class="comm_Wrp">

		<div class="Grp">
			<div class="latest_set">

				<div class="ctabs_design">
					<ul>
						<li class="tab2 on"><span id="ctabs_1">공지사항</span></li>	
						<li class="tab2"><span id="ctabs_2">낚시전문교육</span></li>	
						<!-- <li class=""><span id="ctabs_3">낚시뉴스</span></li> -->	
					</ul>
				</div><!-- // ctabs_design // -->				
				
				<div id="ctabs_conts1" class="ctabs_contsdesign" style=""><!-- 공지사항 -->
					<!-- ##start## latest 덩어리 ##### -->
					<div class="latest_Bx">
						<ul class="comm_ul">
							<c:forEach var="item" varStatus="status" items="${main_notice }">
								<li>
								<c:choose>
									<c:when test="${status.index == 0}">
										<a href="#;" onclick="noticeview('${item.bo_sn}')">
											<p class="sbj">${item.bo_subject}</p>
											<div class="cont">${item.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}</div>
											<p class="date1">${item.bo_insert_dt}</p>
										</a>
									</c:when>
									<c:otherwise>
										<a href="#;" onclick="noticeview('${item.bo_sn}')">${item.bo_subject}</a>
										<div class="date"><span><!-- <img src="/naksinuri_original/edu/new.png" alt="새로운글" /> --></span>${item.bo_insert_dt}</div>
										<p class="clr"></p>
									</c:otherwise>
								</c:choose>
								</li>
							</c:forEach>
							<%--
							<li>
								<a href="#">
									<p class="sbj">제4회 수간자원을 부 海 공모전</p>
									<div class="cont">
										○ 안녕하세요, 누리지기 입니다. 올해로 제4회를 맞는 '수산자원을 부탁해' 공모전이 5.13(월)부터 시작되었습니다~ 앞으로 약 한 달간 진행되오니, 많은 관심 부탁드립니다~! 
									</div><!--//cont//-->
									<p class="date1">2019.08.12</p>
								</a>
							</li>
							<li>
								<a href="#">낚시전문 교육 12월 교육장소 변경안내</a>
								<div class="date"><span><img src="/naksinuri_original/edu/new.png" alt="새로운글" /></span>2019.08.13</div>
								<p class="clr"></p>
							</li>
							<li>
								<a href="#">낚시전문 교육 12월 교육장소 변경안내</a>
								<div class="date">2019.08.13</div>
								<p class="clr"></p>
							</li>
							 --%>
						</ul>
					</div><!--//latest_Bx//-->
					<!-- ##end## latest 덩어리 ## -->
				</div><!-- // tabs_contsdesign // -->
				
				<div id="ctabs_conts2" class="ctabs_contsdesign" style="display:none;"><!-- 낚시전문교육 -->
					<div class="latest_Bx">
						<ul class="comm_ul">
							<c:forEach var="item" varStatus="status" items="${list_edu_notice }">
								<fmt:parseDate value="${fn:replace(item.BD_REG_DT, '.0', '')}" var="parse_bd_reg_dt" pattern="yyyy-MM-dd" scope="page"/>
								<fmt:formatDate value="${parse_bd_reg_dt}" pattern="yyyy-MM-dd" var="BD_REG_DT"/>
								<li>
								<c:choose>
									<c:when test="${status.index == 0}">
										<a href="javascript:void(0)" onclick="noticeEduView('${item.BD_SN}')">
											<p class="sbj">${item.BD_TITLE}</p>
											<div class="cont">${item.BD_CONT.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}</div>
											<p class="date1">${BD_REG_DT}</p>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="noticeEduView('${item.BD_SN}')">${item.BD_TITLE}</a>
										<div class="date"><span><!-- <img src="/naksinuri_original/edu/new.png" alt="새로운글" /> --></span>${BD_REG_DT}</div>
										<p class="clr"></p>
									</c:otherwise>
								</c:choose>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div><!-- // tabs_contsdesign // -->

				<div id="ctabs_conts3" class="ctabs_contsdesign" style="display:none;"><!-- 낚시뉴스 -->
					<div class="latest_Bx">
						<ul class="comm_ul">
							<c:forEach var="item" varStatus="status" items="${main_news }">
								<li>
								<c:choose>
									<c:when test="${status.index == 0}">
										<a href="${item.link}" target="_blank">
											<p class="sbj">${item.title}</p>
											<div class="cont">${item.title.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}</div>
											<p class="date1">${item.regdate}</p>
										</a>
									</c:when>
									<c:otherwise>
										<a href="${item.link}" target="_blank">${item.title}</a>
										<div class="date"><span><!-- <img src="/naksinuri_original/edu/new.png" alt="새로운글" /> --></span>${item.regdate}</div>
										<p class="clr"></p>
									</c:otherwise>
								</c:choose>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div><!-- // tabs_contsdesign // -->
			</div><!--//latest_set//-->
		
			<div class="ion_set">
				<ul class="ion_ul">
					<li><a href="http://www.law.go.kr/법령/낚시관리및육성법" target="_blank" alt="낚시 관리 및 육성법 외부링크"><img src="/naksinuri_original/edu/ion_01.png" /> 낚시 관리 및 육성법</a></li>
					<li><a href="/promotion/policy/m/list.do" target="_self" alt="낚시정책안내 페이지이동"><img src="/naksinuri_original/edu/ion_02.png" /> 낚시정책안내</a></li>
					<!-- <li><a href="/promotion/plocation/m/list.do" target="_self" alt="낚시금지구역 페이지이동"><img src="/naksinuri_original/edu/ion_03.png" /> 낚시금지구역</a></li> -->
					<li><a href="/info/fishjob/m/list.do" target="_self" alt="낚시터정보 페이지이동"><img src="/naksinuri_original/edu/ion_04.png" /> 낚시터정보</a></li>
					<li><a href="/info/industry/m/list.do" target="_self" alt="낚시산업정보 페이지이동"><img src="/naksinuri_original/edu/ion_05.png" /> 낚시산업정보</a></li>
					<li><a href="http://www.nifs.go.kr/lmo/scb/index2.lmo" target="_blank" alt="수산물생물포획금지 외부링크"><img src="/naksinuri_original/edu/ion_06.png" /> 수산물생물포획금지</a></li>
					<li><a href="/educenter/m/index.do" target="_self" alt="낚시전문교육 페이지이동"><img src="/naksinuri_original/edu/ion_07.png" /> 낚시전문교육</a></li>
					<li><a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank" alt="해양정보 인터넷방송 외부링크"><img src="/naksinuri_original/edu/ion_08.png" /> 해양정보 인터넷방송</a></li>
				</ul>
				<p class="clr"></p>
			</div><!--//ion_set//-->

			<p class="clr"></p>
		</div><!--//Grp//-->
	</div><!--//comm_Wrp//-->
	<!-- ##end## 커뮤니티하단 ## -->

	<!--### 스폰서 ######-->
	<div class="sponcer_wrp">
		<div class="Grp">
			
			<div class="act_button">
				<ul class="act_btn">
					<li><span id="nprv"><img src="/naksinuri_original/edu/nprv.png" alt="" /></span></li>
					<!-- // <li><span id=""><img src="/naksinuri_original/edu/npause.png" alt="" /></span></li>
					<li><span id=""><img src="/naksinuri_original/edu/nplay.png" alt="" /></span></li> // -->
					<li><span id="nnxt"><img src="/naksinuri_original/edu/nnxt.png" alt="" /></span></li>
				</ul>
			</div><!--//act_button//-->

			<div class="sponcer_Bx">
				<div id="sponcerSlide">
				<!--▼ sponcerSlide ▼-->
					<!-- <div class="prev"><img src="/naksinuri_original/edu/nprv.png" alt="" /></div>
					<div class="next"><img src="/naksinuri_original/edu/nnxt.png" alt="" /></div> -->
					<div class="spncrWrp">
						<div class="spncrBx"><a href="http://www.mof.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink01.png" alt="해양수산부" /></a></div>
						<div class="spncrBx"><a href="https://www.fipa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink02.png" alt="KFPA 한국어촌어항공단" /></a></div>
						<div class="spncrBx"><a href="https://www.facebook.com/FIPAkor" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink10.png" alt="한국어촌어항공단 공식 페이스북" /></a></div>
						<div class="spncrBx"><a href="http://www.sealife.go.kr" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink09.png" alt="귀어귀촌" /></a></div>
						<div class="spncrBx"><a href="http://www.kff.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink03.png" alt="KFF 한국낚시연합" /></a></div>
						<div class="spncrBx"><a href="http://www.nifs.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink04.png" alt="국립수산과학원" /></a></div>
						<div class="spncrBx"><a href="http://www.fira.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink05.png" alt="FIRA 한국수산자원관리공단" /></a></div>
						<!-- <div class="spncrBx"><a href="http://www.kst.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink06.png" alt="선박안전기술공단" /></a></div> -->
						<div class="spncrBx"><a href="https://www.komsa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink11.png" alt="한국해양교튱안전공단" /></a></div>
						<div class="spncrBx"><a href="http://www.khoa.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink07.png" alt="국립해양조사원 KHOA" /></a></div>
						<div class="spncrBx"><a href="http://www.koreafca.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink08.png" alt="한국낚시업중앙회" /></a></div>
						<span class="pagination"></span>
					</div>
				<!--▲ sponcerSlide ▲-->
				</div><!-- //sponcerSlide -->
			</div>
		</div><!--//Grp//-->
	</div><!--//sponcer_wrp//-->
	<!--end.### 스폰서 ###-->

<script>
$(function(){
	/*## 슬라이드 감춤 */
	$('span[id="slider_closer"]').on("click",function () {
		if ($('#mainSearch').css("display") == "block") {
			$('#mainSearch').slideUp(500);
		} else {
			$('#mainSearch').slideDown(500);
		}
	});
	/*## 낚시터정보 좌우 */
	$('span[id^="info_"]').on("click",function () {
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
	});
	/*## 낚시 전문교육 슬라이드 */
	$('#bnrSlider .bnrWrp').carouFredSel({
		width: '100%',
		align: "center",
		items: { visible:1,minimum:null,start:0},
		circular: true,
		scroll: {
			items: 1,
			fx: "scroll",
			easing: "swing"
	    },
//		scroll:{ pauseOnHover : true},
		auto : { pauseDuration : 4000 },
		padding : null,
		prev : {  button  : "#bnrSlider .prev" },
		next : {  button  : "#bnrSlider .next" }
	});
	/*## 하단 스폰서 슬라이드 */
	$('#sponcerSlide .spncrWrp').carouFredSel({
		items: { visible:5,minimum:null,start:0},
		circular: true,
		scroll: {
			items: 1,
			fx: "scroll",
			easing: "swing"
	    },
//		scroll:{ pauseOnHover : true},
		auto : false,//{ pauseDuration : 4000 },
		padding : null,
		prev : {  button  : ".act_btn #nprv" },
		next : {  button  : ".act_btn #nnxt" }
	});
});
</script>	

<script>
$(function(){
	/*## 낚시여행기 탭 */
	$('span[id^="tabs_"]').click(function () {
		$('span[id^="tabs_"]').parent("li").removeClass("on");
		$('div[id^="tabs_conts"]').hide();
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("tabs_","tabs_conts");
		$('#'+$idx).show();
	});

	/*## 낚시터정보 */
	$('span[id^="ltabs_"]').click(function () {
		$('span[id^="ltabs_"]').parent("li").removeClass("on");
		$('div[id^="ltabs_conts"]').hide();
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("ltabs_","ltabs_conts");
		$('#'+$idx).show();
	});

	/*## 전문교육탭 */
	$('span[id^="wtabs_"]').click(function () {
		$('span[id^="wtabs_"]').parent("li").removeClass("on");
		$('div[id^="wtabs_conts"]').hide();
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("wtabs_","wtabs_conts");
		$('#'+$idx).show();
	});

	/*## 공지사항 탭 */
	$('span[id^="ctabs_"]').click(function () {
		$('span[id^="ctabs_"]').parent("li").removeClass("on");
		$('div[id^="ctabs_conts"]').hide();
		$(this).parent().addClass("on");
		var $idx = $(this).attr("id").replace("ctabs_","ctabs_conts");
		$('#'+$idx).show();
	});
});


function noticeview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	form.action="/sosig/notice/m/view.do";
	form.submit();	
}

function rangview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	form.action="/share/travel/m/view.do";
	form.submit();	
}

function sangview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	//form.action="/sosig/congress/m/view.do";
	form.action="/share/column/m/view.do";
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

function noticeview(bo_sn){
	
	var form = document.getElementById('viewform');
	$('#bo_sn').val(bo_sn);
	
	form.action="/sosig/notice/view.do";
	form.submit();	
}

function nak_sub(nak_id){
	$('#nak_id').val(nak_id);
	$("#view_go").attr("action", "/info/fishjob/m/view.do");
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
			//console.log('success!');
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
    document.noticeEduForm.action = "<c:url value='/educenter/m/board/notice/view.do'/>";
    document.noticeEduForm.submit();
}
</script>


<%@ include file="../../naksinuri/layout/m/tail.jsp" %>
