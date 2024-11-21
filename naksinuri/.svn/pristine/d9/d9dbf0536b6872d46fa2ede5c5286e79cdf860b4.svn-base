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
<c:set var="depthNum" value="7" />
<c:set var="depthName" value="통합검색" />
<c:set var="pageName" value="통합검색" />
<form id="frm" name="frm" mothod="post">
	<input type="hidden" id="bo_sn" name="bo_sn">
	<input type="hidden" id="nak_id" name="nak_id">
	<input type="hidden" id="san_sn" name="san_sn">
	<input type="hidden" id="mid" name="mid">	
	<input type="hidden" id="evn_no" name="evn_no">	
	<input type="hidden" id="nuri_q_num" name="nuri_q_num">		
</form>



<%@include file="../../layout/m/head.jsp"%>

	<div id="totalSearch" class="content respon2">
		<div class="totalbox">
			<p class="totalNum"><b class="colorSky">"${searchText}"</b> 검색어로 전체 <b class="colorBlue">${total_search_Cnt}</b>건의 게시물이 있습니다.</p>
			<div class="search_area">
				<form id="search_frm" name="search_frm" method="post">
				<input type="text" class="search_input" placeholder="검색어를 입력해주세요." name="searchText" id="searchText1" value="${searchText}"/>
				<button class="search_btn" href="#;" onclick="go_search1()"><i class="fa fa-search" aria-hidden="true"></i></button>
				</form>
			</div>
		</div>
		<c:if test="${co_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시터 정보 <b class="colorBlue">${co_total}</b>건</h2>
				<a href="/info/fishjob/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/info/fishjob/m/list.do" class="path">낚시정보 > 낚시터 정보</a></p>
				<span>낚시정보 > 낚시터 정보</span>	
					<ul>
						<c:forEach  var="co" items="${co_list}">
							<li>     
								 <a href="#;"onclick="co_view('${co.nak_id}')">
									<em>[${fn:split(co.co_addr2_2,' ')[0]} ${fn:split(co.co_addr2_2,' ')[1]}]</em>
									<span class="name">${co.co_nm}</span>
							     </a>
							</li>
						</c:forEach>
					</ul>
			</section>
		</c:if>
		<c:if test="${ang_total ne 0 }">
			<section id="searchBox" class="searchClass">
				<h2>조황정보 <b class="colorBlue">${ang_total}</b>건</h2>
				<a href="/info/angling/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/info/angling/m/list.do" class="path">낚시정보 > 조황정보</a></p>
				<section class="faq_list">
					<div class="faq_head">
						<ul>
							<li class="num">번호</li>
							<li>제목</li>	
						</ul>
					</div>
					<c:forEach var="ang"  items="${ang_list}" varStatus="status">			
						<div class="faq_con">
							<dl>
								<dt>
									<span class="num">${status.count}</span>
									<a><span class="type">${ang.category}</span>${ang.title}</a>
								</dt>
								<dd>
									<div style="width:100%;" >${ang.body}</div>
								</dd>
							</dl>
						</div>
					</c:forEach>			
				</section>
			</section>
		</c:if>
		<c:if test="${lab_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시연구소 <b class="colorBlue">${lab_total}</b>건</h2>
				<a href="/sosig/lab/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/sosig/lab/m/list.do" class="path">낚시정보 > 낚시연구소</a></p>
				<ul>
					<c:forEach var="lab" items="${lab_list}" varStatus="status">
						<li>
							<a href="#;"onclick="lab_view('${lab.bo_sn}')">
								<em>${lab.bo_subject}</em>
								<span class="name">${lab.bo_name}</span><span class="date">${fn:substring(lab.bo_insert_dt,0,10)}</span>
							</a>
						</li>	
					</c:forEach>
				</ul>
			</section>
		</c:if>
		<c:if test="${san_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시산업정보 <b class="colorBlue">${san_total}</b>건</h2>
				<a href="/info/industry/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/info/industry/m/list.do" class="path">낚시정보 > 낚시산업정보</a></p>
					<ul>
						<c:forEach  var="san" items="${san_list}">
							<li>
								<a href="#;" onclick="san_view(${san.san_sn})">
									<em>${fn:substring(san.san_name,0,12)}
										<c:if test="${fn:length(san.san_name)>11}">...</c:if>
									</em>
									<span class="date"><b>사업영역 : </b>${fn:substring(san.san_item,0,13)}
										<c:if test="${fn:length(san.san_item) > 12 }">...</c:if>
									</span>
									<span class="name">
										<c:set var="san_car" value="${item.san_zogu} ${san.san_media} ${san.san_sell} ${san.san_chool} ${san.san_inprov} ${san.san_aag}"/>
										[${fn:substring(san_car,0,19)}
										<c:if test="${fn:length(san_car)>20}">...</c:if>]
									</span>
								</a>
							</li>
						</c:forEach>
					</ul>
			</section>
		</c:if>

		<c:if test="${junior_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>초보탈출하기<b class="colorBlue">${junior_total}</b>건</h2>
				<a href="/lesson/junior/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/lesson/junior/m/list.do" class="path">낚시교실 > 초보탈출하기</a></p>				
				<ul>
					<c:forEach var="junior" items="${junior_list}">						
						<li>
							<a href="#;" onclick="junior_view('${junior.bo_sn}')" class="subject">
								<em>${junior.bo_subject}</em>
								<span class="name">${junior.bo_name }</span><span class="date">${fn:substring(junior.bo_insert_dt,0,10)}</span>
							</a>
						</li>							
					</c:forEach>
				</ul>
			</section>
		</c:if>
	
		<c:if test="${gosu_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시고수되기<b class="colorBlue">${gosu_total}</b>건</h2>
				<a href="/lesson/gosu/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/lesson/gosu/m/list.do" class="path">낚시교실 > 낚시고수되기</a></p>
					<ul>
						<c:forEach var="gosu" items="${gosu_list}">						
							<li>
								<a href="#;" onclick="gosu_view('${gosu.bo_sn}')" class="subject">
									<em>${gosu.bo_subject}</em>
									<span class="name">${gosu.bo_name}</span><span class="date">${fn:substring(gosu.bo_start_dt,0,10)}</span>
								</a>
							</li>							
						</c:forEach>
					</ul>
			</section>
		</c:if>

		<c:if test="${sense_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시상식<b class="colorBlue">${sense_total}</b>건</h2>
				<a href="/lesson/sense/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/lesson/sense/m/list.do" class="path">낚시교실 > 낚시상식</a></p>
				<ul>
					<c:forEach var="sense" items="${sense_list}">						
						<li>
							<a href="#;" onclick="sense_view('${sense.bo_sn}')" class="subject">
								<em>${sense.bo_subject}</em>
								<span class="name">${sense.bo_name}</span><span class="date">${fn:substring(sense.bo_start_dt,0,10)}</span>
							</a>
						</li>							
					</c:forEach>
				</ul>
			</section>
		</c:if>
		
		<c:if test="${binding_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>채비필수 묶음법<b class="colorBlue">${binding_total}</b>건</h2>
				<a href="/lesson/binding/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/lesson/binding/m/list.do" class="path">낚시교실 > 채비필수 묶음법</a></p>
				<ul>
					<c:forEach var="binding" items="${binding_list}">						
						<li>
							<a href="#;" onclick="binding_view('${binding.bo_sn}')" class="subject">
								<em>${binding.bo_subject}</em>
								<span class="name">${binding.bo_name}</span><span class="date">${fn:substring(binding.bo_start_dt,0,10)}</span>
							</a>
						</li>							
					</c:forEach>
				</ul>
			</section>
		</c:if>
		
		<c:if test="${class_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>어종별 낚시교실<b class="colorBlue">${class_total}</b>건</h2>
				<a href="/lesson/class/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/lesson/class/m/list.do" class="path">낚시교실 > 어종별 낚시교실</a></p>
				<ul>
					<c:forEach var="class1" items="${class_list}">						
						<li>
							<a href="#;" onclick="class_view('${class1.bo_sn}')" class="subject">
								<em>${class1.bo_subject}</em>
								<span class="name">${class1.bo_name}</span><span class="date">${fn:substring(class1.bo_start_dt,0,10)}</span>
							</a>
						</li>							
					</c:forEach>
				</ul>
			</section>
		</c:if>
			
	<!-- 		낚시뉴스 -->
		<%-- <c:if test="${news_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시뉴스 <b class="colorBlue">${news_total}</b>건<a href="/sosig/news/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<ul>
					<c:forEach var="news" items="${news_list}" varStatus="status">
						<li>
							<a href="${news.link}" onclick="news_view('${news.mid}')">
							<em>${news.title}</em>
							<span class="date">${fn:substring(news.regdate,0,10)}</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if> --%>
			
		<c:if test="${notice_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>공지사항 <b class="colorBlue">${notice_total}</b>건</h2>
				<a href="/sosig/notice/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/sosig/notice/m/list.do" class="path">낚시소식 > 공지사항</a></p>
				<ul>
					<c:forEach var="notice" items="${notice_list}" varStatus="status">
						<li>
						<a href="#;"onclick="notice_view('${notice.bo_sn}')">								
								<em>${notice.bo_subject}</em>
								<span class="date">${fn:substring(notice.bo_insert_dt,0,10)}</span>
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if>	
	
		<c:if test="${congress_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시 대회 <b class="colorBlue">${congress_total}</b>건</h2>
				<a href="/sosig/congress/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/sosig/congress/m/list.do" class="path">낚시소식 > 낚시 대회</a></p>
					<ul>
						<c:forEach  var="congress" items="${congress_list}">
							<li>
								<a href="#;" onclick="congress_view('${congress.bo_sn}')" class="subject">
									<em>${congress.bo_subject}</em>
									<span class="date">
										<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
										<c:if test="${congress.bo_end_dt >= today}">진행중인 낚시대회</c:if>
					         			<c:if test="${congress.bo_end_dt < today}">종료된 낚시대회</c:if>	
					         			(${congress.bo_cate})	
									</span>
									<span class="date"><b>대회기간:</b>${congress.bo_start_dt} ~ ${congress.bo_end_dt}</span>
								</a> 
							</li>
						</c:forEach>
					</ul>
			</section>
		</c:if>
		
		<c:if test="${event_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>이벤트 <b class="colorBlue">${event_total}</b>건</h2>
				<a href="/sosig/event/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/sosig/event/m/list.do" class="path">낚시소식 > 이벤트</a></p>
				<ul>
					<c:forEach  var="event" items="${event_list}">
						<li>
							<a href="#;" onclick="event_view('${event.evn_no}')" class="subject">
								<em>${event.evn_subject}</em>
								<span class="date">
									<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
									<c:if test="${event.evn_enddate >= today}">진행중인 이벤트</c:if>
				         			<c:if test="${event.evn_enddate < today}">종료된 이벤트</c:if>
								<c:if test="${event.evn_startdate ne null}">
									<span class="date"><b>이벤트 기간 : </b> ${event.evn_startdate} ~ ${event.evn_enddate}</span>
								</c:if>
								<c:if test="${event.evn_startdate eq null}">
									<span class="date">[당첨자 발표]:${fn:substring(event.evn_insert_dt,0,10)}</span>
								</c:if>
							</a>
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if>
		
		<c:if test="${campaign_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시 캠페인 <b class="colorBlue">${campaign_total}</b>건</h2>
				<a href="/promotion/campaign/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/promotion/campaign/m/list.do" class="path">낚시정책 > 낚시 캠페인</a></p>
				<ul>
					<c:forEach  var="campaign" items="${campaign_list}">
						<li>
							<a href="#;" onclick="campaign_view('${campaign.bo_sn}')" class="subject">
								<em>${campaign.bo_subject}</em>
								<span class="date">
									<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
									<c:if test="${campaign.bo_end_dt >= today}">진행중인 캠페인</c:if>
				         			<c:if test="${campaign.bo_end_dt < today}">종료된 캠페인</c:if>
								<span class="date"><b>이벤트 기간 : </b> ${campaign.bo_start_dt} ~ ${campaign.bo_end_dt}</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if>
		
<!-- 		낚시금지구역 -->
		<%-- <c:if test="${plocation_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시 금지구역 <b class="colorBlue">${plocation_total}</b>건<a href="/promotion/plocation/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<ul>
					<c:forEach var="plo"  items="${plocation_list}" varStatus="status">			
						<li>
							<a>
								<em>${plo.x_name}</em>
								<span class="date">${plo.x_rang}</span>
							</a>
						</li>
					</c:forEach>
				</ul>			
			</section>		
		</c:if> --%>
		
		<%-- <c:if test="${llocation_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시 제한구역 <b class="colorBlue">${llocation_total}</b>건<a href="/promotion/llocation/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
					<section class="faq_list">
						<div class="faq_head">
							<ul>
								<li class="num">이름</li>
								<li class="type">주소</li>	
							</ul>
						</div>
						<c:forEach var="llo"  items="${llocation_list}" varStatus="status">			
							<div class="faq_con">
								<dl>
									<dt>
										<span class="num">${llo.x_name}</span>
										<a><span class="type">${llo.x_rang}</span></a>
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
			</section>
		</c:if> --%>
		
		<c:if test="${policy_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시정책 <b class="colorBlue">${policy_total}</b>건</h2>
				<a href="/promotion/policy/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/promotion/policy/m/list.do" class="path">낚시정책 > 낚시정책안내</a></p>
					<ul>
						<c:forEach var="policy" items="${policy_list}" varStatus="status">
							<li>
								<a href="#;"onclick="policy_view('${policy.bo_sn}')">								
									<em>${policy.bo_subject}</em>
									<span class="date">${fn:substring(policy.bo_insert_dt,0,10)}</span>
								</a>
							</li>
						</c:forEach>
					</ul>
			</section>
		</c:if>	
		
		<c:if test="${qna_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시법 및 유어장 관련 질의회신 사례 <b class="colorBlue">${qna_total}</b>건</h2>
				<a href="/promotion/qna/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/promotion/qna/m/list.do" class="path">낚시정책 > 낚시법 및 유어장 관련 질의회신 사례</a></p>
					<section class="faq_list">
						<div class="faq_head">
							<ul>
								<li class="num">번호</li>
								<li class="type">질의<span style="font-size:12px">(이 회신집에 수록된 사항은 참고용입니다.)</span></li>
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
			</section>
		</c:if>
		
		<c:if test="${travel_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시 여행기<b class="colorBlue">${travel_total}</b>건</h2>
				<a href="/share/travel/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/share/travel/m/list.do" class="path">커뮤니티 > 낚시 여행기</a></p>
					<ul>
						<c:forEach var="travel" items="${travel_list}">						
							<li>
								<a href="#;" onclick="travel_view('${travel.bo_sn}')">
									<em>${travel.bo_subject}</em>
									<span class="name">${travel.bo_name}</span>
									<span class="date">${fn:substring(travel.bo_insert_dt,0,10)}</span>
								</a>
							</li>							
						</c:forEach>
					</ul>
			</section>
		</c:if>
		
		<%-- <c:if test="${zisik_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>누리지식인 <b class="colorBlue">${zisik_total}</b>건<a href="/share/zisik/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a></h2>
				<ul>
					<c:forEach var="zisik" items="${zisik_list}" varStatus="status">
						<li>
							<a href="#"onclick="zisik_view('${zisik.nuri_q_num}')">
								<em>${zisik.nuri_q_subject }</em>
								<c:choose>
									<c:when test="${zisik.nuri_a_count eq 0 }">
										<span class="wait">답변대기</span>
									</c:when>
									<c:otherwise>
										<span class="complete">답변완료</span>
									</c:otherwise>
								</c:choose>
								<span class="date">${fn:substring(zisik.nuri_q_date ,0,10)}</span>
								<span class="name">${zisik.nuri_q_writer }</span>
							</a>		
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if> --%>	
		
		<c:if test="${zazu_total ne 0 }">
			<section id="searchBox" class="searchClass">
				<h2>자주묻는 낚시질문<b class="colorBlue">${zazu_total}</b>건</h2>
				<a href="/share/nuri/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/share/nuri/m/list.do" class="path">커뮤니티 > 자주묻는 낚시질문</a></p>
					<section class="faq_list">
						<div class="faq_head">
							<ul>
								<li class="num">번호</li>
								<li class="type">질문</li>
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
			</section>
		</c:if>
		
		<c:if test="${usage_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시용품 사용기<b class="colorBlue">${usage_total}</b>건</h2>
				<a href="/share/usage/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/share/usage/m/list.do" class="path">커뮤니티 > 낚시용품 사용기</a></p>
					<ul>
						<c:forEach var="usage" items="${usage_list}">
							<li>
								<a href="#;" onclick="usage_view('${usage.bo_sn}')">
									<em>${usage.bo_subject}</em>
									<span class="name">${usage.bo_name}</span>
									<span class="date">${fn:substring(usage.bo_insert_dt,0,10)}</span>
								</a>
							</li>							
						</c:forEach>
					</ul>
			</section>
		</c:if>
		
		<c:if test="${column_total ne 0 }">
			<section id="searchBox" class="noborder searchClass">
				<h2>낚시칼럼<b class="colorBlue">${column_total}</b>건</h2>
				<a href="/share/column/m/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/share/column/m/list.do" class="path">커뮤니티 > 낚시칼럼</a></p>
					<ul>
						<c:forEach var="column" items="${column_list}">
							<li>
								<a href="#;" onclick="column_view('${column.bo_sn}')">
									<em>${column.bo_subject}</em>
									<span class="name">${column.bo_name}</span>
									<span class="date">${fn:substring(usage.bo_insert_dt,0,10)}</span>
								</a>
							</li>							
						</c:forEach>
					</ul>
			</section>
		</c:if>
		
		<!-- 낚시전문 교육 -->
		<c:if test="${edu_notice_total ne 0 }">
			<section id="searchBox">
				<h2>낚시전문교육 공지사항 <b class="colorBlue">${edu_notice_total}</b>건</h2>
				<a href="/educenter/m/board/notice/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/educenter/m/board/notice/list.do" class="path">낚시전문교육 > 공지사항</a></p>
				<ul>
					<c:forEach var="notice" items="${edu_notice_list}" varStatus="status">
						<li>
							<c:choose>
								<c:when test="${not empty notice.LINK_URL}"><c:set var="LINK_URL" value="${notice.LINK_URL }"/></c:when>
								<c:otherwise><c:set var="LINK_URL" value="#;"/></c:otherwise>
							</c:choose>
							<a href="${LINK_URL}">							
								<em>${notice.BD_TITLE}</em>
								<span class="date">${fn:substring(notice.BD_REG_DT,0,10)}</span>
							</a>		
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if>
		
		<c:if test="${edu_faq_total ne 0 }">
			<section id="searchBox">
				<h2>낚시전문교육 FAQ<b class="colorBlue">${edu_faq_total}</b>건</h2>
				<a href="/educenter/m/board/faq/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/educenter/m/board/faq/list.do" class="path">낚시전문교육 > 공지사항 > FAQ</a></p>
				<div class="board_list">
					<section class="faq_list">
						<div class="faq_head">
							<ul>
								<li class="num">번호</li>
								<li class="type">질문</li>
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
				<a href="/educenter/m/board/file/list.do" class="more"><i class="fa fa-plus" aria-hidden="true"></i> 더보기</a>
				<p><a href="/educenter/m/board/file/list.do" class="path">낚시전문교육 > 공지사항 > 자료실</a></p>
				<ul>
					<c:forEach var="notice" items="${edu_file_list}" varStatus="status">
						<li>
							<a href="${notice.LINK_URL}">							
								<em>${notice.BD_TITLE}</em>
								<span class="date">${fn:substring(notice.BD_REG_DT,0,10)}</span>
							</a>		
						</li>
					</c:forEach>
				</ul>
		</c:if>
		<!-- 낚시전문교육 끝 -->
		
	</div>

<script>
$(function(){
	Array.prototype.forEach.call(document.getElementsByClassName("searchClass"), function(el) {
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
		form.action="/promotion/policy/m/view.do";
		form.submit();
	}

	function junior_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/junior/m/view.do";
		form.submit();
	}
	
	function gosu_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/gosu/m/view.do";
		form.submit();
	}
	
	function sense_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/sense/m/view.do";
		form.submit();
	}
	
	
	function binding_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/binding/m/view.do";
		form.submit();
	}
	
	function class_view(bo_sn){
		var form=document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action="/lesson/class/m/view.do";
		form.submit();
	}
	
	function co_view(nak_id){
		var form=document.getElementById("frm");
		$('#nak_id').val(nak_id);
		form.action="/info/fishjob/m/view.do";
		form.submit();
	}
	
	function san_view(san_sn){
		var form=document.getElementById("frm");
		$('#san_sn').val(san_sn);
		form.action="/info/industry/m/ind_find.do";
		form.submit();
	}

	function ang_view(mid){
		var form=document.getElementById("frm");
		$('#mid').val(mid);
		form.action="/info/angling/m/view.do";
		form.submit();
	}
	
	function news_view(mid){
		var form=document.getElementById("frm");
		$('#mid').val(mid);
		form.action="/sosig/news/m/view.do";
		form.submit();
	}

	function notice_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/notice/m/view.do";
		form.submit();
	}
	
	function congress_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/congress/m/view.do";
		form.submit();
	}
	
	function event_view(evn_no){
		var form = document.getElementById("frm");
		$('#evn_no').val(evn_no);
		form.action = "/sosig/event/m/view.do";
		form.submit();
	}
	
	function travel_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/travel/m/view.do";
		form.submit();
	}
	
	function zisik_view(nuri_q_num){
		var form = document.getElementById("frm");
		$('#nuri_q_num').val(nuri_q_num);
		form.action = "/share/zisik/m/view.do";
		form.submit();
	}
	
	function usage_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/usage/m/view.do";
		form.submit();
	}
	
	function column_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/share/column/m/view.do";
		form.submit();
	}
	
	function lab_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/sosig/lab/m/view.do";
		form.submit();
	}
	
	function campaign_view(bo_sn){
		var form = document.getElementById("frm");
		$('#bo_sn').val(bo_sn);
		form.action = "/promotion/campaign/m/view.do";
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
			form.action="/search/m/search_list.do";
			form.submit;	
		}
		
	}
</script>

<%@include file="../../layout/m/tail.jsp"%>