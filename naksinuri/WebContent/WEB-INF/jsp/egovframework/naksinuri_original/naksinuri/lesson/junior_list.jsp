<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="juniorout"/>
<c:set var="depthName" value="낚시교실" />
<c:set var="pageName" value="초보탈출하기" />


<%@include file="../layout/head.jsp"%>

	<div id="fishjobList" class="content respon2">
		<div class="tabArea"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li class="on"><a href="#;" title="민물(붕어)낚시">민물(붕어)낚시</a></li>
				<li><a href="#;" title="바다낚시" >바다낚시</a></li>
			</ul>
		</div>

		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
				<div class="total_num">전체 <b class="colorSky">2,264</b>건의 게시물이 있습니다.(<b class="colorSky">4</b>/227)</div>
				<select class="basic_select">
					<option>제목+내용</option>
					<option>제목</option>
					<option>내용</option>
				</select>
				<input type="text" class="basic_input" />
				<button class="searchBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
				<a href="#;" class="change_type list"><i class="fa fa-th-list" aria-hidden="true"></i></a>
				<a href="#;" class="change_type gallery on"><i class="fa fa-th-large" aria-hidden="true"></i></a>
			</div>
			<div class="board_list off">
				<table class="list_tbl">
					<colgroup>
						<col width="130" />
						<col />
						<col width="140" />
						<col width="180" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>조회수</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span class="notice">공지</span></td>
							<td class="subject align_left"><a href="view.do">제주 낚시어선 안전관리점검 8월말까지 실시</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td><span class="notice">공지</span></td>
							<td class="subject align_left"><a href="view.do">영종도 해상서 6명 탄 낚시어선 표류…해경 구조</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">강화도 1박2일여행코스로 알려진 바다좌대낚시 황산조행기 이벤트 발표</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">[TVreplay] 꽝PD를 찾아 온 진짜 낚시왕 '이태곤'의 하루! &lt;세모방&gt;</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">[내일의 바다낚시지수] 7월 26일 전 해상 강한 바람과 파도 영향 출조 어려운 곳 많아</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">영동군청 낚시동호회, 직원 화합과 자연보호'앞장'</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">불법낚시도구 단속</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">(포토)"아따 그놈 힘 좋네! 이맛에 낚시하는거제"</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">[단독] 이경규X이덕화 낚시예능 '도시어부' 출격, 28일 첫 촬영</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
						<tr>
							<td>2263</td>
							<td class="subject align_left"><a href="view.do">'생활의 달인' 김치말이 밥국수 달인 탱글탱글한 밥알의 비법, 대나무 낚시의 달인 노하우 대공개</a></td>
							<td>452</td>
							<td class="date">2017-07-26</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="webzine_list">
				<ul class="floats">
					<c:forEach var="i" begin="1" end="4" step="1">
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg1.jpg" width="250" height="170" alt="" /></a>
						<a href="view.do" class="subject">
							<em>제19장 낚시터에서 지켜야 할 예정</em>
							<span class="txt">제20장 낚시터에서 지켜야 할 예절 1. 낚시터에 도착 후 소란스러운 행동은 삼간다 - 큰 소리로 사람을 부르거나 여러 사람이 우르르 몰려 다니는 행위는 무례한 행동이다. 2. 늦게 도착하여 중간에 끼어드는 행위는...</span>
						</a>
						<ul class="eyes_heart floats">
							<li class="name_date"><span class="name">평산 송귀섭</span><span class="date">2015-05-03</span></li>
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
						</ul>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg2.jpg" width="250" height="170" alt="" /></a>
						<a href="view.do" class="subject">
							<em>제19장 낚시터에서 지켜야 할 예정</em>
							<span class="txt">제20장 낚시터에서 지켜야 할 예절 1. 낚시터에 도착 후 소란스러운 행동은 삼간다 - 큰 소리로 사람을 부르거나 여러 사람이 우르르 몰려 다니는 행위는 무례한 행동이다. 2. 늦게 도착하여 중간에 끼어드는 행위는...</span>
						</a>
						<ul class="eyes_heart floats">
							<li class="name_date"><span class="name">평산 송귀섭</span><span class="date">2015-05-03</span></li>
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
						</ul>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg3.jpg" width="250" height="170" alt="" /></a>
						<a href="view.do" class="subject">
							<em>제19장 낚시터에서 지켜야 할 예정</em>
							<span class="txt">제20장 낚시터에서 지켜야 할 예절 1. 낚시터에 도착 후 소란스러운 행동은 삼간다 - 큰 소리로 사람을 부르거나 여러 사람이 우르르 몰려 다니는 행위는 무례한 행동이다. 2. 늦게 도착하여 중간에 끼어드는 행위는...</span>
						</a>
						<ul class="eyes_heart floats">
							<li class="name_date"><span class="name">평산 송귀섭</span><span class="date">2015-05-03</span></li>
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
						</ul>
					</li>
					<li>
						<a href="view.do" class="pic"><img src="/naksinuri_original/common_main/img/sample_fishjobImg4.jpg" width="250" height="170" alt="" /></a>
						<a href="view.do" class="subject">
							<em>제19장 낚시터에서 지켜야 할 예정</em>
							<span class="txt">제20장 낚시터에서 지켜야 할 예절 1. 낚시터에 도착 후 소란스러운 행동은 삼간다 - 큰 소리로 사람을 부르거나 여러 사람이 우르르 몰려 다니는 행위는 무례한 행동이다. 2. 늦게 도착하여 중간에 끼어드는 행위는...</span>
						</a>
						<ul class="eyes_heart floats">
							<li class="name_date"><span class="name">평산 송귀섭</span><span class="date">2015-05-03</span></li>
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
						</ul>
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
		$('.webzine_list').addClass("off");
	});

	$('.change_type.gallery').click(function(){
		$(this).addClass("on");
		$('.change_type.list').removeClass("on");
		$('.board_list').addClass("off");
		$('.webzine_list').removeClass("off");
	});
</script>


<%@include file="../layout/tail.jsp"%>