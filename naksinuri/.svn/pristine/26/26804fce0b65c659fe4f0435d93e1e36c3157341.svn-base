<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="indexpage" />

<%@include file="../../naksinuri/layout/m/head.jsp"%>

<form id="viewform" name="viewform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
	<input type="hidden" name="mid" id="mid"/>
</form>

<form:form commandName="view_go" id="view_go" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="nak_id" id="nak_id" />
</form:form>

		<!-- 낚시정보검색, 링크 4개 { --
		<article id="mainSearchBoardconBox">
		   <div class="respon">
		      <form name="frm" id="frm" method="post">
				 <section id="mainSearch" class="floats">
					<div class="tab_mnu">
						<ul class="floats">
							<li class="on" id="jobin"><a href="#;"  onclick="infoch()">낚시터정보</a></li>
							<li id="induin"><a href="#;"  onclick="induch()">낚시산업정보</a></li>
						</ul>
					</div>
				<div class="search_box" id="setsyle">
								
							<input type="hidden" id="req_sido" value="${searchSido}" />
							<input type="hidden" id="req_gugun" value="${searchGugun}" />
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
									<dt>· 시도</dt>
									<dd>
										<select class="search_select" name="searchSido" id="searchSido"">
											
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
				 </section>
			  </form>
		</article>
		-->
		
		<section id="mainNaksiBtnBox">
			<%--
			<ul class="floats">
				<li><a href="/info/fishjob/m/list.do"><img src="/naksinuri_original/common_main/img/main_saksiBtn1.jpg" /></a></li>
				<li><a href="/info/industry/m/list.do"><img src="/naksinuri_original/common_main/img/main_saksiBtn2.jpg" /></a></li>
			</ul>
			--%>
			<ul>
			<c:forEach var="scrollimg" items="${scrollimg}">
							<li><a href="${scrollimg.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${scrollimg.m_img_cont}"/>&fileSn=<c:out value="${scrollimg.m_img_cont }"/>' ' alt="낚시누리 메인 슬라이드 배너 3"/></a></li>
			</c:forEach>
			<!--  
				<li><a href="/shortcut/event2/link.do"><img src='/naksinuri_original/common_main/img/main_mobile_1.png' alt="낚시누리 메인 슬라이드 배너 1"/></a></li>
				<li><a href="/shortcut/event3/link.do"><img src='/naksinuri_original/common_main/img/main_mobile_2.png' alt="낚시누리 메인 슬라이드 배너 2"/></a></li>
				<li><a href="/shortcut/gongmo1/link.do"><img src='/naksinuri_original/common_main/img/main_mobile_3.jpg' alt="낚시누리 메인 슬라이드 배너 3"/></a></li>
			-->
			</ul>
			<script>
				$('#mainNaksiBtnBox ul').bxSlider({
					auto: true,
					controls: false,
					pause: 3000
				});
			</script>
		</section>
		
		<!-- 링크 4개 { -->
		<section id="mainBoardcon">
			<ul class="floats">
			<c:forEach var="mid_img1" items="${mid1_list}">
				<c:choose>
					<c:when test="${mid_img1.orignl_file_nm eq null }">
						<li class="b1"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
					</c:when>
					<c:otherwise>
						<c:if test="${mid_img1.mobile_link eq ''}">
							<li class="b1"><a href="${mid_img1.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img1.m_img_cont}"/>&fileSn=<c:out value="${mid_img1.file_sn}"/>'   width="100%" height="100%"  /></em><span>${mid_img1.img_subject}</span></a></li>
						</c:if>
						<c:if test="${mid_img1.mobile_link ne ''}">
							<li class="b1"><a href="${mid_img1.mobile_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img1.m_img_cont}"/>&fileSn=<c:out value="${mid_img1.file_sn}"/>'   width="100%" height="100%"  /></em><span>${mid_img1.img_subject}</span></a></li>
						</c:if>
					</c:otherwise>	
				</c:choose>
			</c:forEach>	
			<c:forEach var="mid_img2" items="${mid2_list}">
				<c:choose>
					<c:when test="${mid_img2.orignl_file_nm eq null }">
						<li class="b2"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
					</c:when>
					<c:otherwise>
						<c:if test="${mid_img2.mobile_link eq ''}">
							<li class="b2"><a href="${mid_img2.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img2.m_img_cont}"/>&fileSn=<c:out value="${mid_img2.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img2.img_subject}</span></a></li>
						</c:if>
						<c:if test="${mid_img2.mobile_link ne ''}">
							<li class="b2"><a href="${mid_img2.mobile_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img2.m_img_cont}"/>&fileSn=<c:out value="${mid_img2.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img2.img_subject}</span></a></li>
						</c:if>
						
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:forEach var="mid_img3" items="${mid3_list}">
				<c:choose>
					<c:when test="${mid_img3.orignl_file_nm eq null }">
						<li class="b3"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
					</c:when>
					<c:otherwise>
						<c:if test="${mid_img3.mobile_link eq ''}">
							<li class="b3"><a href="${mid_img3.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img3.m_img_cont}"/>&fileSn=<c:out value="${mid_img3.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img3.img_subject}</span></a></li>
						</c:if>
						<c:if test="${mid_img3.mobile_link ne ''}">
							<li class="b3"><a href="${mid_img3.mobile_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img3.m_img_cont}"/>&fileSn=<c:out value="${mid_img3.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img3.img_subject}</span></a></li>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:forEach var="mid_img4" items="${mid4_list}">
				<c:choose>
					<c:when test="${mid_img4.orignl_file_nm eq null }">
						<li class="b4"><img src="/naksinuri_original/common_main/img/noImage_writer.png" alt="no_image" width="100%" height="100%" /></li>
					</c:when>
					<c:otherwise>
						<c:if test="${mid_img4.mobile_link eq ''}">
							<li class="b4"><a href="${mid_img4.img_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img4.m_img_cont}"/>&fileSn=<c:out value="${mid_img4.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img4.img_subject}</span></a></li>
						</c:if>
						<c:if test="${mid_img4.mobile_link ne ''}">
							<li class="b4"><a href="${mid_img4.mobile_link}"><em><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mid_img4.m_img_cont}"/>&fileSn=<c:out value="${mid_img4.file_sn }"/>'  width="100%" height="100%"  /></em><span>${mid_img4.img_subject}</span></a></li>
						</c:if>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			</ul>
		</section>
		<!-- } 링크 4개 -->

	<!-- 6개 링크 { -->
	<article id="mainSixlink">
		<ul class="floats">
			<!-- <li><a href="http://www.law.go.kr/lsEfInfoP.do?lsiSeq=162586#" target="_blank" class="sixlink1">낚시 관리 및 육성법</a></li> -->
			<li><a href="http://www.law.go.kr/법령/낚시관리및육성법" target="_blank" class="sixlink1">낚시 관리 및 육성법</a></li>
			
			<li><a href="/info/fishjob/m/list.do" class="sixlink9">낚시터정보</a></li>
			<li><a href="/info/industry/m/list.do" class="sixlink10">낚시산업정보</a></li>
			
			<li><a href="/promotion/policy/m/list.do" class="sixlink2">낚시정책안내</a></li>
			<!--<li><a href="#;" class="sixlink3">수산생물검색</a></li>-->
			<li><a href="/promotion/plocation/m/list.do" class="sixlink4">낚시금지구역</a></li>
			<!--<li><a href="#;" class="sixlink5">포획금지생물종</a></li>-->
			
			<!-- 
			<li><a href="http://www.nifs.go.kr/frcenter/species/?_p=species_clss_info" target="_blank" class="sixlink6">포획금지기간</a></li>
			-->
			<li><a href="http://www.nifs.go.kr/lmo/scb/index2.lmo" target="_blank" class="sixlink8">수산생물포획금지</a></li>
			
			<li><a href="https://www.naksiedu.kr/" target="_blank" class="sixlink3">낚시전문교육</a></li>
			<li><a href="http://www.khoa.go.kr/Onbada/main.do" target="_blank" class="sixlink7">해양정보방송</a></li>
			
		</ul>
	</article>
	<!-- } 6개 링크 -->
		
	<!-- 메인 콘텐츠 { -->
	<div id="mainContainer">

		<!-- 낚시뉴스, 롤링이벤트, 배너 { -->
		<article id="mainLatestBox">
			<section id="mainLatest" class="main_conbox main_latestbox">
			<%--
			<div id="boardmore" style="float:right"><a href="/sosig/news/m/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
			 --%>
			<div id="boardmore" style="float:right"><a href="/sosig/notice/m/list.do"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></a></div>
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
							<li><a target="_blank" href="${news.link }" >
							<c:set var="TextValue" value="${news.title }"/>
							<em>${fn:substring(news.title,0,30)}</em>
							<div style="float:right">${news.regdate}</div>
							</a></li>
						</c:forEach>
					</ul>
					 --%>
					<ul>
						<c:if test="${fn:length(main_notice) eq '' }">
						<li><em>작성된 글이 없습니다.</em><span></span></a></li>
						</c:if>
						<c:forEach var="notice" items="${main_notice }">
						<li><a href="#;" onclick="noticeview('${notice.bo_sn}')"><em>${notice.bo_subject}</em></a></li>
						</c:forEach>
					</ul>					
				</div>	
			</section>
			
			<section id="mainBanner" class="main_conbox floats">
				<ul>
					<li><a href="/sosig/congress/m/write.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_1_m.png"" alt="no_image" width="100%" /></a></li>
					<li><a href="/sosig/congress/m/check.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_2_m.png"" alt="no_image" width="100%" /></a></li>
				</ul>
				<!-- //메뉴 이동 : 2018.09.20 요청// -->
				<%--
				<div class="lbanner">
					<ul>
						<c:forEach var="banner1" items="${main_banner1}">
							<c:choose>
								<c:when test="${banner1.orignl_file_nm eq null }">
									<li><img src="/naksinuri_original/common_main/img/noImage_writer.png"" alt="no_image" width="100%" /></li>
								</c:when>
								<c:otherwise>
									<c:if test="${banner1.mobile_link eq ''}">
										<li><a href="${banner1.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner1.m_img_cont}"/>&fileSn=<c:out value="${banner1.file_sn}"/>'alt="슬라이드 배너1"/></a></li>
									</c:if>
									<c:if test="${banner1.mobile_link ne ''}">
										<li><a href="${banner1.mobile_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner1.m_img_cont}"/>&fileSn=<c:out value="${banner1.file_sn}"/>'alt="슬라이드 배너1"/></a></li>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:forEach var="banner2" items="${main_banner2}">
							<c:if test="${banner2.mobile_link eq '' }">
								<li><a href="${banner2.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner2.m_img_cont}"/>&fileSn=<c:out value="${banner2.file_sn}"/>' alt="슬라이드 배너2"/></a></li>
							</c:if>
							<c:if test="${banner2.mobile_link ne ''}">
								<li><a href="${banner2.mobile_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner2.m_img_cont}"/>&fileSn=<c:out value="${banner2.file_sn}"/>' alt="슬라이드 배너2"/></a></li>
							</c:if>
						</c:forEach>	
						<c:forEach var="banner3" items="${main_banner3}">
							<c:if test="${banner3.mobile_link eq '' }">
								<li><a href="${banner3.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner3.m_img_cont}"/>&fileSn=<c:out value="${banner3.file_sn}"/>' alt="슬라이드 배너3"/></a></li>
							</c:if>
							<c:if test="${banner3.mobile_link ne '' }">
								<li><a href="${banner3.mobile_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner3.m_img_cont}"/>&fileSn=<c:out value="${banner3.file_sn}"/>' alt="슬라이드 배너3"/></a></li>
							</c:if>			
						</c:forEach>	
					</ul>
					<script>
						$('#mainBanner .lbanner ul').bxSlider({
							auto: true,
							controls: false,
							pause: 5000
						});
					</script>
				</div>
				<div class="rbanner">
					<c:forEach var="rightbanner" items="${right_banner}">

						<c:choose>
								<c:when test="${rightbanner.orignl_file_nm eq null }">
									<li><img src="/naksinuri_original/common_main/img/noImage_big.png" alt="no_image" /></li>
								</c:when>
								<c:otherwise>
									<c:if test="${rightbanner.mobile_link eq ''}">
										<li><a href="${rightbanner.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${rightbanner.m_img_cont}"/>&fileSn=<c:out value="${rightbanner.file_sn}"/>'/></a></li>
									</c:if>
									<c:if test="${rightbanner.mobile_link ne ''}">
										<li><a href="${rightbanner.mobile_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${rightbanner.m_img_cont}"/>&fileSn=<c:out value="${rightbanner.file_sn}"/>'/></a></li>
									</c:if>
								</c:otherwise>
						</c:choose>	
					</c:forEach>
				</div>
				 --%>
				 
				<c:forEach var="banner3" items="${main_banner3}">
                                                <li><a href="${banner3.img_link}"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner3.img_cont}"/>&fileSn=<c:out value="${banner3.file_sn}"/>'alt="낚시누리 메인 배너이미지3"/></a></li>
                                        </c:forEach>
                                <!--
                                <a href="/sosig/congress/m/write.do"><img src="/naksinuri_original/common_main/img/ico_fixmenu_3.jpg"" alt="no_image" width="100%" /></a>
                                -->

				<%--
				<!-- //낚시대회 홍보 동영상 : 2018.09.20 요청사항 // -->
				<!-- 
				<iframe width="100%" height="240px" src="https://www.youtube.com/embed/yw957wmPktc" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen=""></iframe>
				-->
				<div id="myElement"></div>
		        <script type="text/javascript">
		        	var filen = $('#filen').val();
		        	$(document).ready(function() {
		        		jwplayer("myElement").setup({					
			                file: "/movupload/2018_mof_naksi_video.mp4",
			                width:"100%",
			                height:"240px",
			                align:"center",
			                autostart: false,
			                mute: false,
			            });
			            jwplayer().onReady(function() { 
		                	jwplayer().play();
		                });
	        		}); 		            
		        </script>
		        --%>				
			</section>
		</article>
		<!-- 여행/핫이슈 -->
		<article id="mainLatestBox" class="respon floats updownpd">
			<section id="mainGallery" class="main_conbox main_latestbox">
			
			<!-- 20171023 민물조황소식에서 낚시 여행기로 변경 -->
				<div align="center" style="height:50px; margin-left:13%;">
					<h2><a href="/share/travel/m/list.do">낚시 <span>여행기</span><a href="/sosig/news/m/list.do"><div style="display:inline;float:right;"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="더보기" /></div></a></a></h2>
				</div>
				<div class="latest_gallery">
					<ul class="floats">
						<c:forEach var="river" items="${river_anglingm }">
							<li>
								<a href="#;" onclick="rangview('${river.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${river.file_sn ne null }">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${river.bo_main_img}"/>&fileSn=<c:out value="${river.file_sn}"/>' alt="${river.orignl_file_nm}" width="100%" height="150" />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="150" />
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
				</div>
			</section>
			<section id="mainGallery" class="main_conbox main_latestbox fr">
				<!-- 
				//20171023 바다조황소식에서 낚시 핫이슈로 변경 
				//2018.10.26 낚시컬럼 -> 낚시대회로 교체 
				//2019.06.18 낚시대회 -> 낚시컬럼으로 교체
				-->
				<%-- 낚시컬럼 --%>
				<div align="center" style="height:50px; margin-left:13%;">
					<h2><a href="/share/column/m/list.do">낚시 <span>컬럼</span><a href="/share/column/m/list.do"><div style="display:inline;float:right;"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="낚시컬럼 더보기" /></div></a></a></h2>
				</div>
				<div class="latest_gallery">
					<ul class="floats">
						<c:forEach var="sea" items="${sea_anglingm}">
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
				</div>
				<%-- 낚시대회
				<div align="center" style="height:50px; margin-left:13%;">
					<h2><a href="/sosig/congress/m/list.do">낚시 <span>대회</span><a href="/sosig/congress/m/list.do"><div style="display:inline;float:right;"><img src="/naksinuri_original/common_main/img/main_moreBtn.png" alt="낚시대회 더보기" /></div></a></a></h2>
				</div>
				<div class="latest_gallery">
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
				</div>
				--%>
				
			</section>
		</article>
		<!-- } 민물낚시 조황소식, 바다낚시 조황소식 -->


<!-- 여행/핫이슈 -->
	<article id="mainLatestBox" class="respon floats">
		<!-- 추천낚시터 { -->
		<form id="fishjob_form">
			<input type="hidden"  name="searchBoat" id="searchBoat" />
			<input type="hidden"  name="searchSea" id="searchSea" />
			<input type="hidden"  name="searchRiver" id="searchRiver" />
			                       
		</form>
		<script>
			function go_boatfishing(type){
				var form = document.getElementById("fishjob_form");
				$('#searchBoat').val(type);
				form.action = "/info/fishjob/m/list.do";
				form.submit();
			}
			
			function go_seafishing(type){
				var form = document.getElementById("fishjob_form");
				$('#searchSea').val(type);
				form.action = "/info/fishjob/m/list.do";
				form.submit();
			}
			
			function go_riverfishing(type){
				var form = document.getElementById("fishjob_form");
				$('#searchRiver').val(type);
				form.action = "/info/fishjob/m/list.do";
				form.submit();
			}
		</script>
		<section id="mainFisherySevenlink">
			<div id="mainFishery" class="main_conbox">
				<h2 style="margin:30px 0 10px;"><a href="#;" onclick="go_boatfishing('boatfishing')">선상 <span>낚시</span></a></h2>
				<div class="fishery_space" id="boatfishing" >
					<ul class="respon floats">
						<c:forEach var="boat" items="${boatfishing_job}">
							<li>
								<a href="#;" onclick="nak_sub('${boat.nak_id}')" class="pic">
									<c:choose>
										<c:when test="${boat.orignl_file_nm eq null}">
											<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="120" />
										</c:when>
										<c:otherwise>
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${boat.co_mimgsrc}"/>&fileSn=<c:out value="${boat.file_sn}"/>' alt="${boat.orignl_file_nm}" width="100%" height="120" />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="nak_sub('${boat.nak_id}')" class="tit"><em>
									<c:choose>
										<c:when test="${fn:length(boat.co_nm) > 8}">
								            <c:out value="${fn:substring(boat.co_nm,0,7)}"/>...</em></span>
								         </c:when>
								         <c:otherwise>
											${boat.co_nm }</em><span>
								         </c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${fn:length(boat.co_addr_2) > 11}">
								            <c:out value="${fn:substring(boat.co_addr_2,0,10)}"/>...</span>
								         </c:when>
								         <c:otherwise>
										  	${boat.co_addr_2 }</span>
								         </c:otherwise>
									</c:choose>							
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			
				<h2 style="margin:30px 0 10px;"><a href="#;" onclick="go_seafishing('seafishing')">바다 <span>낚시</span></a></h2>
				<div class="fishery_space" id="seafishing" >
					<ul class="respon floats">
						<c:forEach var="sea" items="${seafishing_job}">
							<li>
								<a href="#;" onclick="nak_sub('${sea.nak_id}')" class="pic">
									<c:choose>
										<c:when test="${sea.orignl_file_nm eq null}">
											<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="120" />
										</c:when>
										<c:otherwise>
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${sea.co_mimgsrc}"/>&fileSn=<c:out value="${sea.file_sn}"/>' alt="${sea.orignl_file_nm}" width="100%" height="120" />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="nak_sub('${sea.nak_id}')" class="tit"><em>
									<c:choose>
										<c:when test="${fn:length(sea.co_nm) > 8}">
								            <c:out value="${fn:substring(sea.co_nm,0,7)}"/>...</em></span>
								         </c:when>
								         <c:otherwise>
											${sea.co_nm }</em><span>
								         </c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${fn:length(sea.co_addr_2) > 11}">
								            <c:out value="${fn:substring(sea.co_addr_2,0,10)}"/>...</span>
								         </c:when>
								         <c:otherwise>
										  	${sea.co_addr_2 }</span>
								         </c:otherwise>
									</c:choose>		
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<h2 style="margin:30px 0 10px;"><a href="#;" onclick="go_riverfishing('riverfishing')">민물<span>낚시</span></a></h2>
				<div class="fishery_space" id="riverfishing" >
						<ul class="respon floats">
							<c:forEach var="river" items="${riverfishing_job}">
								<li>
									<a href="#;" onclick="nak_sub('${river.nak_id}')" class="pic">
										<c:choose>
											<c:when test="${river.orignl_file_nm eq null}">
												<img src="/naksinuri_original/common_main/img/naksinuri_noimg3.jpg" alt="no_image" width="100%" height="120" />
											</c:when>
											<c:otherwise>
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${river.co_mimgsrc}"/>&fileSn=<c:out value="${river.file_sn}"/>' alt="${river.orignl_file_nm}" width="100%" height="120" />
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="nak_sub('${river.nak_id}')" class="tit"><em>
										<c:choose>
											<c:when test="${fn:length(river.co_nm) > 8}">
									            <c:out value="${fn:substring(river.co_nm,0,7)}"/>...</em></span>
									         </c:when>
									         <c:otherwise>
												${river.co_nm }</em><span>
									         </c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:length(river.co_addr_2) > 11}">
									            <c:out value="${fn:substring(river.co_addr_2,0,10)}"/>...</span>
									         </c:when>
									         <c:otherwise>
											  	${river.co_addr_2 }</span>
									         </c:otherwise>
										</c:choose>								
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</section>
		</article>
 	</div>
</div>
	<!-- } 메인 콘텐츠 -->
	
	<!-- 팝업영역 -->
	<c:forEach var="popups" items="${popups}" varStatus="status" >
	<div id="popupArea${status.count}" class="popupArea" style="display:none;">
		<ul>
			<li><a href="${popups.img_link}" target="_blank"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${popups.img_cont}"/>&fileSn=<c:out value="${popups.img_cont }"/>'  /></a></li>
		</ul>
		<div class="closebtnArea">
			<input type="checkbox" id="popupClose${status.count}" name="popupClose${status.count}" /><label for="popupClose${status.count}"><span></span>오늘 하루 열지 않기</label>
			<a href="#;" onclick ="closeMainPopup${status.count}()" class="closeBtn">닫기</a>
		</div>
	</div>
	</c:forEach>
 
	
	
	<%--
	
	<div id="popupArea2" class="popupArea" style="display:none;">
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


function go_search(){
	var tmp = document.search_form.searchText.value.replace(/\s|　/gi, '');
	var form = document.getElementById("search_form");
	if(tmp==''){
		alert('통합 검색 키워드가 없습니다.');
		return false;
	}else{
	form.action="/search/m/search_list.do";
	form.submit;
	}
}

$('#mainFishery #boatfishing ul,#mainFishery #seafishing ul,#mainFishery #riverfishing ul').bxSlider({
	auto: true,
	pager: false,
	contols: false,
	minSlides: 2,
	maxSlides: 5,
	moveSlides: 1,
	slideWidth: 300,
	slideMargin: 5,
	pause: 3000
});
	function newschange(){
		var url = "/newschange.do";

		$.ajax({
			url : url,
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				console.log(data);
				$(".mnu ul li").remove();
				//$(".mnu ul").append("<li class=\"on\" id=\"main_news\" onclick=\"newschange()\"><h2>낚시뉴스</h2></li><li id=\"main_notice\" onclick=\"noticechange()\"><a href=\"#;\">공지사항</a></li>");
				$(".mnu ul").append("<li id=\"main_notice\" onclick=\"noticechange()\"><a href=\"#;\">공지사항</a></li><li class=\"on\" id=\"main_news\" onclick=\"newschange()\"><h2>낚시뉴스</h2></a></li>");
				$(".latest_list ul").empty();
				$("#boardmore a").remove();
				if(data.length==''){
					$(".latest_list ul").append("<li><em>작성된 글이 없습니다.</em><span></span></a></li>");
					$("#boardmore").append("<a href=\"/sosig/news/m/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
				}else{
					for(var i=0;i<=data.length;i++) {
						$(".latest_list ul").append("<li><a target='_blank' href="+data[i].link+" onclick=\"noticeview('"+data[i].mid+"')\"><em>"+data[i].title.substring(0,30)+"</em><div style=\"float:right\">"+data[i].regdate+"</div></a></li>");
						if(i==0){
						$("#boardmore").append("<a href=\"/sosig/news/m/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
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

		$.ajax({
			url : url,
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				console.log(data);
				$(".mnu ul li").remove();
				//$(".mnu ul").append("<li id=\"main_news\" onclick=\"newschange()\"><a href=\"#;\">낚시뉴스</a></li><li class=\"on\" id=\"main_notice\" onclick=\"noticechange()\"><h2>공지사항</h2></li>");
				$(".mnu ul").append("<li class=\"on\" id=\"main_notice\" onclick=\"noticechange()\"><h2>공지사항</h2></li><li id=\"main_news\" onclick=\"newschange()\"><a href=\"#;\">낚시뉴스</a></li>");
				$(".latest_list ul").empty();
				$("#boardmore a").remove();
				
				
				if(data.length==''){
					$(".latest_list ul").append("<li><em>작성된 글이 없습니다.</em><span></span></a></li>");
					$("#boardmore").append("<a href=\"/sosig/notice/m/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
				}else{
					for(var i=0;i<=data.length;i++) {
						$(".latest_list ul").append("<li><a href=\"#;\" onclick=\"noticeview('"+data[i].bo_sn+"')\"><em>"+data[i].bo_subject.substring(0,30)+"</em><div style=\"float:right\">"+data[i].bo_insert_dt+"</div></a></li>");				
						if(i==0){
							$("#boardmore").append("<a href=\"/sosig/notice/m/list.do\"><img src=\"/naksinuri_original/common_main/img/main_moreBtn.png\" alt=\"더보기\" /></a>");
							}
					}
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}

			
		})
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

function nak_sub(nak_id){
	$('#nak_id').val(nak_id);
	$("#view_go").attr("action", "/naksinuri_original/info/fishjob/m/view.do");
	$('#view_go').submit();
}


function induch(){
	$('.search_box div').remove();
	$('#jobin').removeClass();
	$('#map_box h3').remove();
	$('#map_box h3').append();
	$('#induin').addClass("on");
	$('#setsyle').addClass("fish_business")
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
	<div class="searchBtn"><button type="submit" onclick="fnSelectInfs()"><i class="fa fa-search" aria-hidden="true"></i><em>검색하기</em></button></div>');
	
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
}


function infoch(){
	$('.search_box div').remove();
	$('#induin').removeClass();
	$('#jobin').addClass("on");
	$('#setsyle').removeClass("fish_business");
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
		<div class="searchBtn"><button type="submit" onclick="fnSelectInfs()"><i class="fa fa-search" aria-hidden="true"></i><em>검색하기</em></button></div>');
		
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
	
}


//검색버튼 클릭
function fnSelectInfs(){
	if($('#jobin').hasClass('on')){
		$("#frm").attr("action", "/naksinuri_original/info/fishjob/m/list.do");
	}else{
		$("#frm").attr("action", "/naksinuri_original/info/industry/m/list.do");
	}
	$("#frm").submit();
}







</script>
<%@ include file="../../naksinuri/layout/m/tail.jsp" %>
