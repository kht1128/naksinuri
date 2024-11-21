<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="sosig"/>
<c:set var="depthNum" value="4" />
<c:set var="depthName" value="낚시소식" />
<c:set var="pageName" value="공지사항" />


<%@include file="../../layout/m/head.jsp"%>

	<div id="conferenceList" class="content">
		<section id="conferenceCalendar">
			<div class="schedule_latest">
				<h3>날짜별 주요 <b class="colorBlue">낚시대회 일정</b></h3>
				<p>낚시누리는 회원님들의 편의를 위하여 날짜별 낚시대회 정보를 상세하게 제공하고 있습니다.</p>
				<ul class="floats">
					<li><a href="#;"><img src="/naksinuri_original/common_main/img/sample_scheduleImg1.jpg" alt="제10회 청양군수배 전국 민물 낚시대회" /></a></li>
					<li><a href="#;"><img src="/naksinuri_original/common_main/img/sample_scheduleImg2.jpg" alt="제3회 바다거북 낚시대회" /></a></li>
					<li><a href="#;"><img src="/naksinuri_original/common_main/img/sample_scheduleImg3.jpg" alt="국제 릴낚시 페스티벌" /></a></li>
					<li><a href="#;"><img src="/naksinuri_original/common_main/img/sample_scheduleImg4.jpg" alt="마이 트레블 라이프" /></a></li>
				</ul>
			</div>
		</section>

		<section id="boardList" class="list_box">
			<div class="list_searchbox">
				<div class="total_num">전체 <b class="colorSky">2,264</b>건의 게시물이 있습니다.(<b class="colorSky">4</b>/227)</div>
				<ul class="floats">
					<li><input type="text" class="basic_input" /><button class="searchBtn"><i class="fa fa-search" aria-hidden="true"></i></button></li>
					<li class="fr"><a href="#;" class="change_type list on"><i class="fa fa-th-list" aria-hidden="true"></i></a></li>
					<li class="fr"><a href="#;" class="change_type gallery"><i class="fa fa-th-large" aria-hidden="true"></i></a></li>
				</ul>
			</div>
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="70" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span class="notice">공지</span></td>
							<td class="subject align_left">
								<a href="view.do">
									<em>제주 낚시어선 안전관리점검 8월말까지 실시</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td><span class="notice">공지</span></td>
							<td class="subject align_left">
								<a href="view.do">
									<em>강화도 1박2일여행코스로 알려진 바다좌대낚시 황산조행기 이벤트 발표</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>425</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>[TVreplay] 꽝PD를 찾아 온 진짜 낚시왕 '이태곤'의 하루! &lt;세모방&gt;</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>424</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>[내일의 바다낚시지수] 7월 26일 전 해상 강한 바람과 파도 영향 출조 어려운 곳 많아</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>423</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>영동군청 낚시동호회, 직원 화합과 자연보호'앞장'</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>422</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>불법낚시도구 단속</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>421</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>(포토)"아따 그놈 힘 좋네! 이맛에 낚시하는거제"</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>420</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>[단독] 이경규X이덕화 낚시예능 '도시어부' 출격, 28일 첫 촬영</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
						<tr>
							<td>419</td>
							<td class="subject align_left">
								<a href="view.do">
									<em>'생활의 달인' 김치말이 밥국수 달인 탱글탱글한 밥알의 비법, 대나무 낚시의 달인 노하우 대공개</em>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> 452</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2017-07-26</span>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="gallery_list off">
				<ul class="floats">
					<c:forEach var="i" begin="1" end="4" step="1">
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg1.jpg" alt="" /></a>
						<a href="view.do" class="subject">
							<em class="list_subject">[내일의 바다낚시지수] 7월 26일 전 해상 강한 바람과 파도 영향 출조 후 진행하는 모습을...</em>
							<span class="date">2017-08-24</span>
						</a>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg2.jpg" alt="" /></a>
						<a href="view.do" class="subject">
							<em class="list_subject">[단독] 이경규X이덕화 낚시예능 '도시어부' 출격, 28일 첫 촬영</em>
							<span class="date">2017-08-24</span>
						</a>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg3.jpg" alt="" /></a>
						<a href="view.do" class="subject">
							<em class="list_subject">(포토)"아따 그놈 힘 좋네! 이맛에 낚시하는거제"</em>
							<span class="date">2017-08-24</span>
						</a>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg4.jpg" alt="" /></a>
						<a href="view.do" class="subject">
							<em class="list_subject">[TVreplay] 꽝PD를 찾아 온 진짜 낚시왕 '이태곤'의 하루! &lt;세모방&gt;</em>
							<span class="date">2017-08-24</span>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div style="text-align:center">
				<ul class="pagination">
					<li>
						<a href="#;" aria-label="Previous">
							<i class="fa fa-angle-left" aria-hidden="true"></i>
						</a>
					</li>
					<li class="active"><a href="#;">1</a></li>
					<li><a href="#;">2</a></li>
					<li><a href="#;">3</a></li>
					<li><a href="#;">4</a></li>
					<li><a href="#;">5</a></li>
					<li>
						<a href="#;" aria-label="Next">
							<i class="fa fa-angle-right" aria-hidden="true"></i>
						</a>
					</li>
				</ul>
			</div>
		</section>
	</div>

<script>
	// 게시판 리스트 or 갤러리 토글버튼
	$('.change_type.list').click(function(){
		$(this).addClass("on");
		$('.change_type.gallery').removeClass("on");
		$('.board_list').removeClass("off");
		$('.gallery_list').addClass("off");
	});

	$('.change_type.gallery').click(function(){
		$(this).addClass("on");
		$('.change_type.list').removeClass("on");
		$('.board_list').addClass("off");
		$('.gallery_list').removeClass("off");
	});
</script>

<%@include file="../../layout/m/tail.jsp"%>