<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<jsp:useBean id="now" class="java.util.Date"/>

<c:set var="pageMode" value="agree"/>
<c:set var="depthNum" value="0" />
<c:set var="depthName" value="검색" />
<c:set var="pageName" value="통합검색" />
<form id="frm" name="frm" mothod="post">
	<input type="hidden" id="bo_sn" name="bo_sn">
	<input type="hidden" id="nak_id" name="nak_id">
	<input type="hidden" id="san_sn" name="san_sn">
	<input type="hidden" id="mid" name="mid">	
	<input type="hidden" id="evn_no" name="evn_no">	
	<input type="hidden" id="nuri_q_num" name="nuri_q_num">		
</form>

<%@include file="../layout/newHead.jsp"%>

	<div id="totalSearch" class="content respon2">
		<div class="totalbox">
			<p class="totalNum"><b class="colorSky">"${searchText}"</b> 검색어로 전체 <b class="colorBlue">${total_search_Cnt}</b>건의 게시물이 있습니다.</p>
			<div class="search_area">
				<form id="search_frm" name="search_frm" method="post">
				<input type="text" class="search_input" placeholder="검색어를 입력해주세요." name="searchText" id="searchText1" value="${searchText}" title="검색어"/>
				<button class="search_btn" href="#;" onclick="go_search1()"><span class="blind">낚시터 정보 검색</span><i class="fa fa-search" aria-hidden="true"></i></button>
				</form>
			</div>
		</div>
		<%-- <c:if test="${co_total ne 0 }">
			<section id="searchBox">
				<h2>낚시터 정보 <b class="colorBlue">${co_total}</b>건</h2>
				<a href="/info/fishjob/list.do" class="path">낚시정보 > 낚시터 정보</a>
				<a href="/info/fishjob/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="gallery_list">
					<ul class="floats">
						<c:forEach  var="co" items="${co_list}">
							<li>
								 <a href="#;"onclick="co_view('${co.nak_id}')" class="pic">
							 		<c:choose>
								 		<c:when test="${co.orignl_file_nm ne null }">
									 		<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${co.co_mimgsrc}"/>&fileSn=<c:out value="${co.file_sn }"/>' alt='<c:out value="${co.orignl_file_nm }"/>' width="100%" height="100%" />
								 		</c:when>
								 		<c:otherwise>
								 			<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png" height="100%">
								 		</c:otherwise>
							 		 </c:choose>
					       		  </a>
					         
								 <a href="#;"onclick="co_view('${co.nak_id}')" class="subject">
									<span class="cate">[${fn:split(co.co_addr2_2,' ')[0]} ${fn:split(co.co_addr2_2,' ')[1]}]</span>
									<em>${fn:substring(co.co_nm,0,12)}
									<c:if test="${fn:length(co.co_nm)>11 }">...</c:if>
									</em>
									<c:set var="TextValue" value="${co.co_fish}"/>
								
									<span class="bestfish"><b>주요어종 : </b>${fn:substring(fn:replace(TextValue,'2',''),0,15)}
										<c:if test="${fn:length(fn:replace(TextValue,'2','')) > 14 }">...</c:if>
									</span>
							     </a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if> --%>
		<%-- <c:if test="${ang_total ne 0 }">
			<section id="searchBox">
				<h2>조황정보 <b class="colorBlue">${ang_total}</b>건</h2>
				<a href="/info/angling/list.do" class="path">낚시정보 > 조황정보</a>
				<a href="/info/angling/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<section class="faq_list">
							<div class="faq_head">
								<ul class="floats">
									<li class="num">번호</li>
									<li class="type">구분</li>
									<li>제목</li>	
								</ul>
							</div>
							<c:forEach var="ang"  items="${ang_list}" varStatus="status">			
							<div class="faq_con">
								<dl>
									<dt>
										<span class="num">${status.count}</span><!-- <span style="width:70px;"></span> -->
										<span class="type">${ang.category}</span>
										<a href="#;"><span class="type mbShow">${ang.category}</span>${ang.title}</a>
									</dt>
									<dd>
										<div style="width:100%;" >${ang.body}</div>
									</dd>
								</dl>
							</div>
							</c:forEach>			
					</section>		
				</div>
			</section>	
		</c:if> --%>
		<%-- <c:if test="${lab_total ne 0 }">
			<section id="searchBox">
				<h2>낚시연구소 <b class="colorBlue">${lab_total}</b>건</h2>
				<a href="/sosig/lab/list.do" class="path">낚시정보 > 낚시연구소</a>
				<a href="/sosig/lab/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
							<col width="140" />
							<col width="140" />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="lab" items="${lab_list}" varStatus="status">
							<tr>
								<td><span>${status.count}</span></td>
								<td class="subject align_left"><a href="#;"onclick="lab_view('${lab.bo_sn}')">${lab.bo_subject}</a></td>
								<td><span>${lab.bo_name }</span></td>
								
								<td><c:if test="${lab.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
								
								<td class="date">${fn:substring(lab.bo_insert_dt,0,10)}</td>
								<td><c:out value="${lab.bo_view }"></c:out></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if> --%>
		<%-- <c:if test="${san_total ne 0 }">
			<section id="searchBox">
				<h2>낚시산업정보 <b class="colorBlue">${san_total}</b>건</h2>
				<a href="/info/industry/list.do" class="path">낚시정보 > 낚시산업정보</a>
				<a href="/info/industry/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="gallery_list">
						<ul class="floats">
						<c:forEach  var="san" items="${san_list}">
							<li>
							<c:choose>
								<c:when test="${san.orignl_file_nm ne null }">
									<a href="#;" onclick="san_view(${san.san_sn})" class="pic" style="line-height:206px"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${san.san_img}"/>&fileSn=<c:out value="${san.file_sn }"/>' alt='<c:out value="${san.orignl_file_nm }"/>' /></a>
								</c:when>
								<c:otherwise>
									<a href="#;" onclick="san_view(${san.san_sn})" class="pic"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>' height="100%"/></a>
								</c:otherwise>
							</c:choose>
								<a href="#;" onclick="san_view(${san.san_sn})"class="subject">
									<span class="cate">
									<c:set var="san_car" value="${item.san_zogu} ${san.san_media} ${san.san_sell} ${san.san_chool} ${san.san_inprov} ${san.san_aag}"/>
									[${fn:substring(san_car,0,19)}
									<c:if test="${fn:length(san_car)>20}">...</c:if>]
									</span>
									<em>${fn:substring(san.san_name,0,12)}
										<c:if test="${fn:length(san.san_name)>11}">...</c:if>
									</em>
									<span class="bestfish"><b>사업영역 : </b>${fn:substring(san.san_item,0,13)}
										<c:if test="${fn:length(san.san_item) > 12 }">...</c:if>
									</span>
								</a>
							</li>
						</c:forEach>
					</ul>
				
				</div>
			</section>
		</c:if> --%>
			
		<c:if test="${junior_total ne 0 }">
			<section id="searchBox">
				<h2>초보탈출하기<b class="colorBlue">${junior_total}</b>건</h2>
				<a href="/lesson/junior/list.do" class="path">낚시교실 > 초보탈출하기</a>
				<a href="/lesson/junior/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="webzine_list">
					<ul class="floats">
						<c:forEach var="junior" items="${junior_list}">						
							<li>
								<a href="#;" onclick="junior_view('${junior.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${junior.orignl_file_nm ne null}">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${junior.bo_main_img}"/>&fileSn=<c:out value="${junior.file_sn }"/>' alt="${junior.orignl_file_nm}"  width="100%" height="100%"  />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="junior_view('${junior.bo_sn}','${junior.bo_type}')" class="subject">
									<em>${junior.bo_subject}</em>
									<c:choose>
										<c:when test="${fn:length(junior.bo_content)>110 }">
											<span class="txt">${fn:substring(junior.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
										</c:when>
										<c:otherwise>
											<span class="txt">${(junior.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
										</c:otherwise>
									</c:choose>
								</a>
								<ul class="eyes_heart floats">
									<li class="name_date"><span class="name">${junior.bo_name}</span><span class="date">${fn:substring(junior.bo_insert_dt,0,10)}</span></li>
									<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${junior.bo_view }</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${junior.bo_like}</a></li>
								</ul>
							</li>							
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if>
			
			<c:if test="${gosu_total ne 0 }">
				<section id="searchBox">
					<h2>낚시고수되기<b class="colorBlue">${gosu_total}</b>건</h2>
					<a href="/lesson/gosu/list.do" class="path">낚시교실 > 낚시고수되기</a>
					<a href="/lesson/gosu/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
					<div class="webzine_list">
						<ul class="floats">
							<c:forEach var="gosu" items="${gosu_list}">						
								<li>
									<a href="#;" onclick="gosu_view('${gosu.bo_sn}')" class="pic">
										<c:choose>
											<c:when test="${gosu.orignl_file_nm ne null}">
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${gosu.bo_main_img}"/>&fileSn=<c:out value="${gosu.file_sn }"/>' alt="${gosu.orignl_file_nm}"  width="100%" height="100%"  />
											</c:when>
											<c:otherwise>
												<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="gosu_view('${gosu.bo_sn}')" class="subject">
										<em>${gosu.bo_subject}</em>
										<c:choose>
											<c:when test="${fn:length(gosu.bo_content)>110 }">
												<span class="txt">${fn:substring(gosu.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
											</c:when>
											<c:otherwise>
												<span class="txt">${(gosu.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
											</c:otherwise>
										</c:choose>
									</a>
									<ul class="eyes_heart floats">
										<li class="name_date"><span class="name">${gosu.bo_name}</span><span class="date">${fn:substring(gosu.bo_insert_dt,0,10)}</span></li>
										<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${gosu.bo_view }</li>
										<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${gosu.bo_like}</a></li>
									</ul>
								</li>							
							</c:forEach>
						</ul>
					</div>
				</section>
			</c:if>
	
			<c:if test="${sense_total ne 0 }">
				<section id="searchBox">
					<h2>낚시상식<b class="colorBlue">${sense_total}</b>건</h2>
					<a href="/lesson/sense/list.do" class="path">낚시교실 > 낚시상식</a>
					<a href="/lesson/sense/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
					<div class="webzine_list">
						<ul class="floats">
							<c:forEach var="sense" items="${sense_list}">						
								<li>
									<a href="#;" onclick="sense_view('${sense.bo_sn}')" class="pic">
										<c:choose>
											<c:when test="${sense.orignl_file_nm ne null}">
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${sense.bo_main_img}"/>&fileSn=<c:out value="${sense.file_sn }"/>' alt="${sense.orignl_file_nm}"  width="100%" height="100%"  />
											</c:when>
											<c:otherwise>
												<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="sense_view('${sense.bo_sn}')" class="subject">
										<em>${sense.bo_subject}</em>
										<c:choose>
											<c:when test="${fn:length(sense.bo_content)>110 }">
												<span class="txt">${fn:substring(sense.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
											</c:when>
											<c:otherwise>
												<span class="txt">${(sense.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
											</c:otherwise>
										</c:choose>
									</a>
									<ul class="eyes_heart floats">
										<li class="name_date"><span class="name">${sense.bo_name}</span><span class="date">${fn:substring(sense.bo_insert_dt,0,10)}</span></li>
										<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${sense.bo_view }</li>
										<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${sense.bo_like}</a></li>
									</ul>
								</li>							
							</c:forEach>
						</ul>
					</div>
				</section>
			</c:if>
			
			
			<c:if test="${binding_total ne 0 }">
				<section id="searchBox">
					<h2>채비필수 묶음법<b class="colorBlue">${binding_total}</b>건</h2>
					<a href="/lesson/binding/list.do" class="path">낚시교실 > 채비필수 묶음법</a>
					<a href="/lesson/binding/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
					<div class="webzine_list">
						<ul class="floats">
							<c:forEach var="binding" items="${binding_list}">						
								<li>
									<a href="#;" onclick="binding_view('${binding.bo_sn}')" class="pic">
										<c:choose>
											<c:when test="${binding.orignl_file_nm ne null}">
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${binding.bo_main_img}"/>&fileSn=<c:out value="${binding.file_sn }"/>' alt="${binding.orignl_file_nm}"  width="100%" height="100%"  />
											</c:when>
											<c:otherwise>
												<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="binding_view('${binding.bo_sn}')" class="subject">
										<em>${binding.bo_subject}</em>
										<c:choose>
											<c:when test="${fn:length(binding.bo_content)>110 }">
												<span class="txt">${fn:substring(binding.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
											</c:when>
											<c:otherwise>
												<span class="txt">${(binding.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
											</c:otherwise>
										</c:choose>
									</a>
									<ul class="eyes_heart floats">
										<li class="name_date"><span class="name">${binding.bo_name}</span><span class="date">${fn:substring(binding.bo_insert_dt,0,10)}</span></li>
										<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${binding.bo_view }</li>
										<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${binding.bo_like}</a></li>
									</ul>
								</li>							
							</c:forEach>
						</ul>
					</div>
				</section>
			</c:if>
			
			<c:if test="${class_total ne 0 }">
				<section id="searchBox">
					<h2>어종별 낚시교실<b class="colorBlue">${class_total}</b>건</h2>
					<a href="/lesson/class/list.do" class="path">낚시교실 > 어종별 낚시교실</a>
					<a href="/lesson/class/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
					<div class="webzine_list">
						<ul class="floats">
							<c:forEach var="class1" items="${class_list}">						
								<li>
									<a href="#;" onclick="class_view('${class1.bo_sn}')" class="pic">
										<c:choose>
											<c:when test="${class1.orignl_file_nm ne null}">
												<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${class1.bo_main_img}"/>&fileSn=<c:out value="${class1.file_sn }"/>' alt="${class1.orignl_file_nm}"  width="100%" height="100%"  />
											</c:when>
											<c:otherwise>
												<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
											</c:otherwise>
										</c:choose>
									</a>
									<a href="#;" onclick="class_view('${class1.bo_sn}')" class="subject">
										<em>${class1.bo_subject}</em>
										<c:choose>
											<c:when test="${fn:length(class1.bo_content)>110 }">
												<span class="txt">${fn:substring(class1.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
											</c:when>
											<c:otherwise>
												<span class="txt">${(class1.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
											</c:otherwise>
										</c:choose>
									</a>
									<ul class="eyes_heart floats">
										<li class="name_date"><span class="name">${class1.bo_name}</span><span class="date">${fn:substring(class1.bo_insert_dt,0,10)}</span></li>
										<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${class1.bo_view }</li>
										<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${class1.bo_like}</a></li>
									</ul>
								</li>							
							</c:forEach>
						</ul>
					</div>
				</section>
			</c:if>
			
	<!-- 		낚시뉴스 -->
		<%-- <c:if test="${news_total ne 0 }">
			<section id="searchBox">
				<h2>낚시뉴스 <b class="colorBlue">${news_total}</b>건<a href="/sosig/news/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="news" items="${news_list}" varStatus="status">
							<tr>
								<td><span>${status.count}</span></td>
								<td class="subject align_left"><a href="${news.link}" onclick="news_view('${news.mid}')">${news.title}</a></td>
								<td class="date">${fn:substring(news.regdate,0,10)}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if> --%>
			
		<c:if test="${notice_total ne 0 }">
			<section id="searchBox">
				<h2>공지사항 <b class="colorBlue">${notice_total}</b>건</h2>
				<a href="/sosig/notice/list.do" class="path">낚시소식 > 공지사항</a>
				<a href="/sosig/notice/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
							<col width="140" />
							<col width="140" />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>	
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="notice" items="${notice_list}" varStatus="status">
								<tr>
									<td><span>${status.count}</span></td>
									<td class="subject align_left"><a href="#;"onclick="notice_view('${notice.bo_sn}')">${notice.bo_subject}</a></td>
									<td><span>${notice.bo_name}</span></td>
									<td><c:if test="${notice.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
									<td class="date">${fn:substring(notice.bo_insert_dt,0,10)}</td>
									<td><c:out value="${notice.bo_view }"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if>	
	
		<%-- <c:if test="${congress_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 대회 <b class="colorBlue">${congress_total}</b>건</h2>
				<a href="/sosig/congress/list.do" class="path">낚시소식 > 낚시 대회</a>
				<a href="/sosig/congress/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="gallery_list">
					<ul class="floats">
						<c:forEach  var="congress" items="${congress_list}">
							<li>
							    <a href="#;"onclick="congress_view('${congress.bo_sn}')" class="pic">
							 		<c:choose>
								 		<c:when test="${congress.orignl_file_nm ne null }">
									 		<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${congress.bo_main_img}"/>&fileSn=<c:out value="${congress.file_sn }"/>' alt='<c:out value="${congress.orignl_file_nm }"/>' width="100%" height="100%" />
								 		</c:when>
								 		<c:otherwise>
								 			<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png" height="100%">
								 		</c:otherwise>
							 		</c:choose>
					       		</a>
								<a href="#;" onclick="congress_view('${congress.bo_sn}')" class="subject">
									<span class="cate">
										<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
										<c:if test="${congress.bo_end_dt >= today}">진행중인 낚시대회</c:if>
					         			<c:if test="${congress.bo_end_dt < today}">종료된 낚시대회</c:if>
										(${congress.bo_cate})</span>
									<em>${fn:substring(congress.bo_subject,0,13)}<c:if test="${fn:length(congress.bo_subject) > 12 }">...</c:if></em>
									<span class="date"><b>대회기간 : </b> ${congress.bo_start_dt} <c:if test="${congress.bo_end_dt ne null}"> ~ ${congress.bo_end_dt} </c:if></span>
								</a>
								<a href="#;" onclick="congress_view('${congress.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${congress.bo_subject} 상세보기"></i></a>
								<ul class="eyes_heart floats">
									<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em> ${congress.bo_view}</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${congress.bo_like }</a></li>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if> --%>
		
		<c:if test="${event_total ne 0 }">
			<section id="searchBox">
				<h2>이벤트 <b class="colorBlue">${event_total}</b>건</h2>
				<a href="/sosig/event/list.do" class="path">낚시소식 > 이벤트</a>
				<a href="/sosig/event/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="gallery_list">
					<ul class="floats">
						<c:forEach  var="event" items="${event_list}">
							<li>
						 	  <a href="#;"onclick="event_view('${event.evn_no}')" class="pic">
						 		 <c:choose>
							 		 <c:when test="${event.orignl_file_nm ne null }">
								 		 <img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${event.evn_main_img}"/>&fileSn=<c:out value="${event.file_sn }"/>' alt='<c:out value="${event.orignl_file_nm }"/>' width="100%" height="100%" />
							 		 </c:when>
							 		 <c:otherwise>
							 			 <img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png" height="100%">
							 		 </c:otherwise>
						 		  </c:choose>
				       		   </a>
								<a href="#;" onclick="event_view('${event.evn_no}')" class="subject">
									<span class="cate">
										<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
										<c:if test="${event.evn_enddate >= today}">진행중인 이벤트</c:if>
					         			<c:if test="${event.evn_enddate < today}">종료된 이벤트</c:if>
									<em>${fn:substring(event.evn_subject,0,13)}<c:if test="${fn:length(event.evn_subject) > 12 }">...</c:if></em>
									<c:if test="${event.evn_startdate ne null}">
										<span class="date"><b>이벤트 기간 : </b> ${event.evn_startdate} ~ ${event.evn_enddate}</span>
									</c:if>
									<c:if test="${event.evn_startdate eq null }">
										<span class="date"><b>[당첨자 발표]:</b>${fn:substring(event.evn_insert_dt,0,10)}</span>
									</c:if>
								</a>
								<a href="#;" onclick="event_view('${event.evn_no}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${event.evn_subject} 상세보기"></i></a>
								<ul class="eyes_heart floats">
									<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em> ${event.evn_viewhit}</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${event.evn_like }</a></li>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if>
		
		<c:if test="${campaign_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 캠페인 <b class="colorBlue">${campaign_total}</b>건</h2>
				<a href="/promotion/campaign/list.do" class="path">낚시정책 > 낚시 캠페인</a>
				<a href="/promotion/campaign/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="gallery_list">
					<ul class="floats">
						<c:forEach  var="campaign" items="${campaign_list}">
							<li>
						 	  <a href="#;"onclick="campaign_view('${campaign.bo_sn}')" class="pic">
						 		 <c:choose>
							 		 <c:when test="${campaign.orignl_file_nm ne null }">
								 		 <img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${campaign.bo_main_img}"/>&fileSn=<c:out value="${campaign.file_sn }"/>' alt='<c:out value="${campaign.orignl_file_nm }"/>' width="100%" height="100%" />
							 		 </c:when>
							 		 <c:otherwise>
							 			 <img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png" height="100%">
							 		 </c:otherwise>
						 		  </c:choose>
				       		   </a>
								<a href="#;" onclick="campaign_view('${campaign.bo_sn}')" class="subject">
									<span class="cate">
										<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
										<c:if test="${campaign.bo_end_dt >= today}">진행중인 캠페인</c:if>
					         			<c:if test="${campaign.bo_end_dt < today}">종료된 캠페인</c:if>
									<em>${fn:substring(campaign.bo_subject,0,13)}<c:if test="${fn:length(campaign.bo_subject) > 12 }">...</c:if></em>
									<span class="date"><b>이벤트 기간 : </b> ${campaign.bo_start_dt} ~ ${campaign.bo_end_dt}</span>
								</a>
								<a href="#;" onclick="campaign_view('${campaign.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${campaign.bo_subject} 상세보기"></i></a>
								<ul class="eyes_heart floats">
									<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em> ${campaign.bo_view}</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${campaign.bo_like}</a></li>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if>
		
<!-- 		낚시금지구역 -->
		<%-- <c:if test="${plocation_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 금지구역 <b class="colorBlue">${plocation_total}</b>건<a href="/promotion/plocation/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<div class="board_list">
					<section class="faq_list">
							<div class="faq_head">
								<ul class="floats">
									<li class="num">번호</li>
									<li class="type">이름</li>
									<li>주소</li>	
								</ul>
							</div>
							<c:forEach var="plo"  items="${plocation_list}" varStatus="status">			
							<div class="faq_con">
								<dl>
									<dt>
										<span class="num">${status.count}</span><span style="width:70px;"></span>
										<a class="type">${plo.x_name}</a>
										<a href="#;">${plo.x_rang}</a>
									</dt>
									<dd>
										<div style="width:100%;" >
											<ul>
												<li><em>지정기간</em><span>${plo.x_term} ~ ${plo.x_end}</span></li>
												<li><em>지정범위</em><span>${plo.x_rang}</span></li>
												<li><em>지정면적</em><span>${plo.x_area}㎢</span></li>
												<li><em>지정권자</em><span>${plo.x_person}</span></li>
												<li><em>이수목적</em><span>${plo.x_purpose}</span></li>
												<li><em>관련법령</em><span>${plo.x_related}</span></li>
											</ul>		
										</div>
									</dd>
								</dl>
							</div>
							</c:forEach>			
					</section>		
				</div>
			</section>
		</c:if> --%>
		
		<%-- <c:if test="${llocation_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 제한구역 <b class="colorBlue">${llocation_total}</b>건<a href="/promotion/llocation/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<div class="board_list">
					<section class="faq_list">
							<div class="faq_head">
								<ul class="floats">
									<li class="num">번호</li>
									<li class="type">이름</li>
									<li>주소</li>	
								</ul>
							</div>
							<c:forEach var="llo"  items="${llocation_list}" varStatus="status">			
							<div class="faq_con">
								<dl>
									<dt>
										<span class="num">${status.count}</span><span style="width:70px;"></span>
										<a class="type">${llo.x_name}</a>
										<a href="#;">${llo.x_rang}</a>
									</dt>
									<dd>
										<div style="width:100%;" >
											<ul>
												<li><em>지정기간</em><span>${llo.x_term} ~ ${llo.x_end}</span></li>
												<li><em>지정범위</em><span>${llo.x_rang}</span></li>
												<li><em>지정면적</em><span>${llo.x_area}㎢</span></li>
												<li><em>지정권자</em><span>${llo.x_person}</span></li>
												<li><em>이수목적</em><span>${llo.x_purpose}</span></li>
												<li><em>관련법령</em><span>${llo.x_related}</span></li>
											</ul>		
										</div>
									</dd>
								</dl>
							</div>
							</c:forEach>			
					</section>		
				</div>
			</section>
		</c:if> --%>
		
		<c:if test="${policy_total ne 0 }">
			<section id="searchBox">
				<h2>낚시정책 <b class="colorBlue">${policy_total}</b>건</h2>
				<a href="/promotion/policy/list.do" class="path">낚시정책 > 낚시정책안내</a>
				<a href="/promotion/policy/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
							<col width="140" />
							<col width="140" />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>	
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="policy" items="${policy_list}" varStatus="status">
								<tr>
									<td><span>${status.count}</span></td>
									<td class="subject align_left"><a href="#;"onclick="policy_view('${policy.bo_sn}')">${policy.bo_subject}</a></td>
									<td><span>${policy.bo_name}</span></td>
									<td><c:if test="${policy.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
									<td class="date">${fn:substring(policy.bo_insert_dt,0,10)}</td>
									<td><c:out value="${policy.bo_view }"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if>	
		
		<c:if test="${qna_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 FAQ <b class="colorBlue">${qna_total}</b>건</h2>
				<a href="/promotion/qna/list.do" class="path">낚시정보 > 낚시 FAQ</a>
				<a href="/promotion/qna/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<section class="faq_list">
						<div class="faq_head">
							<ul class="floats">
								<li class="num">번호</li>
								<li class="type"></li>
								<li>질의 <span style="font-size:12px">(이 회신집에 수록된 사항은 참고용입니다.)</span></li>
							</ul>
						</div>
						<c:forEach var="qna"  items="${qna_list}" varStatus="status">			
						<div class="faq_con">
							<dl>
								<dt>
									<span class="num">${status.count}</span><span class="type"></span>
									<a href="#;">${qna.qna_ques}</a>
								</dt>
								<dd>
									<div style="width:100%;" >${qna.qna_answ}</div>
								</dd>
							</dl>
						</div>
						</c:forEach>			
					</section>
				</div>
			</section>
		</c:if>
		
		<%-- <c:if test="${travel_total ne 0 }">
			<section id="searchBox">
				<h2>낚시 여행기<b class="colorBlue">${travel_total}</b>건</h2>
				<a href="/share/travel/list.do" class="path">커뮤니티 > 낚시 여행기</a>
				<a href="/share/travel/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="webzine_list">
					<ul class="floats">
						<c:forEach var="travel" items="${travel_list}">						
							<li>
								<a href="#;" onclick="travel_view('${travel.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${travel.orignl_file_nm ne null}">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${travel.bo_main_img}"/>&fileSn=<c:out value="${travel.file_sn }"/>' alt="${travel.orignl_file_nm}"  width="100%" height="100%"  />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="travel_view('${travel.bo_sn}')" class="subject">
									<em>${travel.bo_subject}</em>
									<c:choose>
										<c:when test="${fn:length(travel.bo_content)>110 }">
											<span class="txt">${fn:substring(travel.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
										</c:when>
										<c:otherwise>
											<span class="txt">${(travel.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
										</c:otherwise>
									</c:choose>
								</a>
								<ul class="eyes_heart floats">
									<li class="name_date"><span class="name">${travel.bo_name}</span><span class="date">${fn:substring(travel.bo_insert_dt,0,10)}</span></li>
									<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${travel.bo_view }</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${travel.bo_like}</a></li>
								</ul>
							</li>							
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if> --%>
		
		<%-- <c:if test="${zisik_total ne 0 }">
			<section id="searchBox">
				<h2>누리지식인 <b class="colorBlue">${zisik_total}</b>건</h2>
				<a href="/share/zisik/list.do" class="path">커뮤니티 > 낚시 여행기</a>
				<a href="/share/zisik/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="120" />
							<col />
							<col width="150" />
							<col width="150" />
							<col width="130" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>답변여부</th>
								<th>작성일</th>
								<th>작성자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="zisik" items="${zisik_list}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td class="subject align_left"><a href="#"onclick="zisik_view('${zisik.nuri_q_num}')">${zisik.nuri_q_subject }</a></td>
									
									<c:choose>
										<c:when test="${zisik.nuri_a_count eq 0 }">
											<td class="state"><span class="wait">답변대기</span></td>
										</c:when>
										<c:otherwise>
											<td class="state"><span class="complete">답변완료</span></td>
										</c:otherwise>
									</c:choose>
									<td class="date">${zisik.nuri_q_date }</td>
									<td class="name">${zisik.nuri_q_writer }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if>	 --%>
		
		<c:if test="${zisik_total ne 0 }">
			<section id="searchBox">
				<h2>Q & A<b class="colorBlue">${zazu_total}</b>건</h2>
				<a href="/share/nuri/list.do" class="path">커뮤니티 > Q & A</a>
				<a href="/share/nuri/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<section class="faq_list">
						<div class="faq_head">
							<ul class="floats">
								<li class="num">번호</li>
								<li class="type"></li>
								<li>질문</li>
							</ul>
						</div>
						<c:forEach var="zazu"  items="${zazu_list }" varStatus="status">			
						<div class="faq_con">
							<dl>
								<dt>
									<span class="num">${status.count}</span><span class="type"></span>
									<a href="#;">${zazu.zazu_ques}</a>
								</dt>
								<dd>
									<div style="width:100%;" >${zazu.zazu_answ}</div>
								</dd>
							</dl>
						</div>
						</c:forEach>			
					</section>
				</div>
			</section>
		</c:if>
		
		<%-- <c:if test="${usage_total ne 0 }">
			<section id="searchBox">
				<h2>낚시용품 사용기<b class="colorBlue">${usage_total}</b>건</h2>
				<a href="/share/usage/list.do" class="path">커뮤니티 > 낚시용품 사용기</a>
				<a href="/share/usage/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="webzine_list">
					<ul class="floats">
						<c:forEach var="usage" items="${usage_list}">
							<li>
								<a href="#;" onclick="usage_view('${usage.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${usage.orignl_file_nm ne null}">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${usage.bo_main_img}"/>&fileSn=<c:out value="${usage.file_sn }"/>' alt="${usage.orignl_file_nm}"  width="100%" height="100%"  />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="usage_view('${usage.bo_sn}')" class="subject">
									<em>${usage.bo_subject}</em>
									<c:choose>
										<c:when test="${fn:length(usage.bo_content)>110 }">
											<span class="txt">${fn:substring(usage.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
										</c:when>
										<c:otherwise>
											<span class="txt">${(usage.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
										</c:otherwise>
									</c:choose>
								</a>
								<ul class="eyes_heart floats">
									<li class="name_date"><span class="name">${usage.bo_name}</span><span class="date">${fn:substring(usage.bo_insert_dt,0,10)}</span></li>
									<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${usage.bo_view }</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${usage.bo_like}</a></li>
								</ul>
							</li>							
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if> --%>
		
		<c:if test="${column_total ne 0 }">
			<section id="searchBox">
				<h2>낚시칼럼(자유게시판)<b class="colorBlue">${column_total}</b>건</h2>
				<a href="/share/column/list.do" class="path">커뮤니티 > 낚시칼럼(자유게시판)</a>
				<a href="/share/column/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="webzine_list">
					<ul class="floats">
						<c:forEach var="column" items="${column_list}">
							<li>
								<a href="#;" onclick="column_view('${column.bo_sn}')" class="pic">
									<c:choose>
										<c:when test="${column.orignl_file_nm ne null}">
											<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${column.bo_main_img}"/>&fileSn=<c:out value="${column.file_sn }"/>' alt="${column.orignl_file_nm}"  width="100%" height="100%"  />
										</c:when>
										<c:otherwise>
											<img src="/naksinuri_original/common_main/img/noImage_gen.jpg"  alt="낚시누리"  width="100%" height="100%"  />
										</c:otherwise>
									</c:choose>
								</a>
								<a href="#;" onclick="column_view('${column.bo_sn}')" class="subject">
									<em>${column.bo_subject}</em>
									<c:choose>
										<c:when test="${fn:length(column.bo_content)>110 }">
											<span class="txt">${fn:substring(column.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 110)}...</span>
										</c:when>
										<c:otherwise>
											<span class="txt">${(column.bo_content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))}</span>
										</c:otherwise>
									</c:choose>
								</a>
								<ul class="eyes_heart floats">
									<li class="name_date"><span class="name">${column.bo_name}</span><span class="date">${fn:substring(column.bo_insert_dt,0,10)}</span></li>
									<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${column.bo_view }</li>
									<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em>${column.bo_like}</a></li>
								</ul>
							</li>							
						</c:forEach>
					</ul>
				</div>
			</section>
		</c:if>
		
		<!-- 낚시전 교육 -->
		<c:if test="${edu_notice_total ne 0 }">
			<section id="searchBox">
				<h2>낚시전문교육 공지사항 <b class="colorBlue">${edu_notice_total}</b>건</h2>
				<a href="/educenter/board/notice/list.do" class="path">낚시전문교육 > 공지사항</a>
				<a href="/educenter/board/notice/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
							<col width="140" />
							<col width="140" />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>	
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="notice" items="${edu_notice_list}" varStatus="status">
								<tr>
									<td><span>${status.count}</span></td>
									<td class="subject align_left"><a href="${notice.LINK_URL}">${notice.BD_TITLE}</a></td>
									<td><span>${notice.MBR_NCNM}</span></td>
									<td><c:if test="${notice.BD_FILE ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
									<td class="date">${fn:substring(notice.BD_REG_DT,0,10)}</td>
									<td><c:out value="${notice.BD_VIEW_CNT }"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if>
		
		<c:if test="${edu_faq_total ne 0 }">
			<section id="searchBox">
				<h2>낚시전문교육 FAQ<b class="colorBlue">${edu_faq_total}</b>건</h2>
				<a href="/educenter/board/faq/list.do" class="path">낚시전문교육 > 공지사항 > FAQ</a>
				<a href="/educenter/board/faq/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<section class="faq_list">
						<div class="faq_head">
							<ul class="floats">
								<li class="num">번호</li>
								<li class="type"></li>
								<li>질문</li>
							</ul>
						</div>
						<c:forEach var="list"  items="${edu_faq_list }" varStatus="status">			
						<div class="faq_con">
							<dl>
								<dt>
									<span class="num">${status.count}</span><span class="type"></span>
									<a>${list.BD_TITLE}</a>
								</dt>
								<dd>
									<div style="width:100%;" >${list.BD_CONT}</div>
								</dd>
							</dl>
						</div>
						</c:forEach>			
					</section>
				</div>
			</section>
		</c:if>
		
		<c:if test="${edu_file_total ne 0 }">
			<section id="searchBox">
				<h2>낚시전문교육 자료실 <b class="colorBlue">${edu_file_total}</b>건</h2>
				<a href="/educenter/board/file/list.do" class="path">낚시전문교육 > 공지사항 > 자료실</a>
				<a href="/educenter/board/file/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<div class="board_list">
					<table class="list_tbl">
						<colgroup>
							<col width="130" />
							<col />
							<col width="140" />
							<col width="140" />
							<col width="140" />
							<col width="140" />
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>	
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="notice" items="${edu_file_list}" varStatus="status">
								<tr>
									<td><span>${status.count}</span></td>
									<td class="subject align_left">${notice.BD_TITLE}</td>
									<td><span>${notice.MBR_NCNM}</span></td>
									<td><c:if test="${notice.BD_FILE ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
									<td class="date">${fn:substring(notice.BD_REG_DT,0,10)}</td>
									<td><c:out value="${notice.BD_VIEW_CNT }"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</c:if>
		<!-- 낚시전문교육 끝 -->
	</div>

<script>
$(function(){
	Array.prototype.forEach.call(document.getElementsByTagName("section"), function(el) {
		el.innerHTML = el.innerHTML.replace(/(${searchText})/g, "<i class='blue'>$1</i>");
	}); 
});

$(document).on("click",'.list_con dl dt a',function(){
	if($(this).parent().parent().parent().hasClass('active')){
		$('.list_con').removeClass('active');
		$('.list_con dl dd').slideUp();
	}else{
		$('.list_con').removeClass('active');
		$(this).parent().parent().parent().addClass('active');
		$('.list_con dl dd').slideUp();
		$(this).parent().next().slideDown();
	}

});


$('.faq_con dl dt a').click(function() {
	if($(this).parent().parent().parent().hasClass('active')){
		$('.faq_con').removeClass('active');
		$('.faq_con dl dd').slideUp();
	}else{
		$('.faq_con').removeClass('active');
		$(this).parent().parent().parent().addClass('active');
		$('.faq_con dl dd').slideUp();
		$(this).parent().next().slideDown();
	}
});

	function policy_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/promotion/policy/view.do";
		form.submit();
	}

	function junior_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/junior/view.do";
		form.submit();
	}
	
	function gosu_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/gosu/view.do";
		form.submit();
	}
	
	function sense_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/sense/view.do";
		form.submit();
	}
	
	
	function binding_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/binding/view.do";
		form.submit();
	}
	
	function class_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/class/view.do";
		form.submit();
	}
	
	function co_view(nak_id){
		var form=document.getElementById("frm");
		$('#nak_id').val(nak_id);
		form.action="/info/fishjob/view.do";
		form.submit();
	}
	
	function san_view(san_sn){
		var form=document.getElementById("frm");
		$('#san_sn').val(san_sn);
		form.action="/info/industry/ind_find.do";
		form.submit();
	}

	function ang_view(mid){
		var form=document.getElementById("frm");
		$('#mid').val(mid);
		form.action="/info/angling/view.do";
		form.submit();
	}
	
	function news_view(mid){
		var form=document.getElementById("frm");
		$('#mid').val(mid);
		form.action="/sosig/news/view.do";
		form.submit();
	}

	function notice_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/notice/view.do";
		form.submit();
	}
	
	function congress_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/congress/view.do";
		form.submit();
	}
	
	function event_view(evn_no){
		var form = document.getElementById("frm");
		$('#evn_no').val(evn_no);
		form.action = "/sosig/event/view.do";
		form.submit();
	}
	
	function travel_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/travel/view.do";
		form.submit();
	}
	
	function zisik_view(nuri_q_num){
		var form = document.getElementById("frm");
		$('#nuri_q_num').val(nuri_q_num);
		form.action = "/share/zisik/view.do";
		form.submit();
	}
	
	function usage_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/usage/view.do";
		form.submit();
	}
	
	function column_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/column/view.do";
		form.submit();
	}
	
	function lab_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/lab/view.do";
		form.submit();
	}
	
	function campaign_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/promotion/campaign/view.do";
		form.submit();
	}
	
	function go_search1(){

		var tmp1 = document.search_frm.searchText.value.replace(/\s|　/gi, '');
		var form = document.getElementById("search_frm");
		if(tmp1==''){
			alert('통합 검색 키워드가 없습니다.');
			$('#searchText1').val($('#searchText').val());
			return false;
		}else{
			form.action="/search/search_list.do";
			form.submit;	
		}
		
	}
</script>

<%@include file="../layout/tail.jsp"%>