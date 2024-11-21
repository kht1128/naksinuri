<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/head.jsp"%>

<!-- 소통마당 > Q&A { -->
	<article id="qnaArea">
		<div class="searchBox alignCenter">
			<select class="basic_select" title="검색조건선택">
				<option>전체</option>
				<option>제목</option>
				<option>내용</option>
				<option>작성자</option>
			</select>
			<input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력"/>
			<button class="btns bluebtn">검색</button>
		</div>
		<section id="boardList" class="mgt30px">
			<table class="blist_tbl">
				<caption>조회리스트</caption>
				<colgroup>
					<col width="100" />
					<col />
					<col width="150" />
					<col width="150" />
				</colgroup>
				<thead>
					<tr>
						<th>일자</th>
						<th>제목</th>
						<th>작성자</th>
						<th>답변여부</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="no alignCenter" colspan="4">조회 된 내역이 없습니다.</td>
					</tr>
				<!-- 
					<tr>
						<td class="date alignCenter"><em class="today">TODAY</em></td>
						<td class="subject"><a href="./qna_view.jsp">어선선원체험 프로그램 참가 희망자 모집</a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px">답변대기</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>04</em><small>2018/05</small></td>
						<td class="subject"><a href="./qna_view.jsp">2018년 어업창업 기술교육 '경상대학교 (어선어업)' 교육생 모집 공고 비밀글 아이콘</a> <span class="secretico colorRed"><i class="fas fa-unlock"></i></span></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px">답변대기</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>20</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">어선선원체험 프로그램 참가 희망자 모집</a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>12</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">귀어·귀촌인 어울림 쉼터 운영 알림 </a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>08</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">2018년 귀어귀촌 종합교육 5기 모집안내 비밀글 아이콘</a> <span class="secretico colorRed"><i class="fas fa-unlock"></i></span></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>28</em><small>2018/05</small></td>
						<td class="subject"><a href="./sub5_1_view.jsp">2018년 어업창업 기술교육 '경상대학교 (어선어업)' 교육생 모집 공고</a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>16</em><small>2018/05</small></td>
						<td class="subject"><a href="./qna_view.jsp">어선선원체험 프로그램 참가 희망자 모집</a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>04</em><small>2018/05</small></td>
						<td class="subject"><a href="./qna_view.jsp">2018년 어업창업 기술교육 '경상대학교 (어선어업)' 교육생 모집 공고 비밀글 아이콘</a> <span class="secretico colorRed"><i class="fas fa-unlock"></i></span></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>20</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">어선선원체험 프로그램 참가 희망자 모집</a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>12</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">귀어·귀촌인 어울림 쉼터 운영 알림 </a></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					<tr>
						<td class="date alignCenter"><em>08</em><small>2018/04</small></td>
						<td class="subject"><a href="./qna_view.jsp">2018년 귀어귀촌 종합교육 5기 모집안내 비밀글 아이콘</a> <span class="secretico colorRed"><i class="fas fa-unlock"></i></span></td>
						<td class="writer alignCenter">귀어귀촌관리자</td>
						<td class="answerstat alignCenter"><span class="btns h30px Orangebtn">답변완료</span></td>
					</tr>
					 -->
				</tbody>
			</table>
			<!-- 
			<nav aria-label="Page navigation" class="alignCenter">
				<ul class="pagination">
					<li class="disabled"><a href="#" aria-label="Previous"><i class="fas fa-angle-double-left"></i></a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#" aria-label="Next"><i class="fas fa-angle-double-right"></i></a></li>
				</ul>
			</nav>
			<div class="btnArea alignRight">
				<a href="certified.jsp" class="btns bluebtn">글쓰기</a>
			</div>
			 -->
		</section>
	</article>
	<!-- } 소통마당 > Q&A -->

<%@include file="../../../naksinuri_original/naksinuri/layout/tail.jsp"%>
