<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!doctype html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>낚시누리</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincommon/css/simple-line-icons.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincommon/css/admin_common.css" />

	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="https://use.fontawesome.com/0973aba921.js"></script>
	<script src="${pageContext.request.contextPath}/admincommon/js/common.js"></script>
</head>
<body oncontextmenu="return false;">

<div id="wrapper">
	<!-- 상단 헤더 { -->
	<header id="header">
		<h1><a href="/admin/lesson/junior/list.do">낚시누리</a></h1>

		<!-- 좌측 메뉴 { -->
		<nav id="gnb">
			<ul>
				<li class="sub-menu active">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe060;"></span><em>낚시업 정보</em></a>
					<ul>
						<li><a href="boatfishing.do" class="active">선상낚시</a></li>
						<li><a href="riverfishing.do">민물낚시</a></li>
						<li><a href="seafishing.do">바다낚시</a></li>
					</ul>
				</li>
				<!-- <li class="sub-menu active">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe060;"></span><em>결제정보</em></a>
					<ul>
						<li><a href="document1_1.html">문서작성</a></li>
						<li><a href="document1_2.html" class="active">진행상태</a></li>
					</ul>
				</li>
				<li class="sub-menu">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe077;"></span><em>보고서 및 현황</em></a>
					<ul>
						<li><a href="document2_1.html">파일반출 현황</a></li>
						<li><a href="#;">부서별 현황</a></li>
						<li><a href="#;">에이전트 설치현황</a></li>
						<li><a href="#;">SIEM 로그 현황</a></li>
						<li><a href="#;">문서보유 현황</a></li>
						<li><a href="#;">시스템 이용 현황</a></li>
					</ul>
				</li>
				<li class="sub-menu">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe013;"></span><em>조직정보</em></a>
					<ul>
						<li><a href="document3_1.html">조직 관리</a></li>
						<li><a href="#;">이용자 관리</a></li>
						<li><a href="#;">컴퓨터 관리</a></li>
					</ul>
				</li>
				<li class="sub-menu">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>게시판</em></a>
					<ul>
						<li><a href="#;">공지사항 관리</a></li>
						<li><a href="#;">공지사항</a></li>
					</ul>
				</li>
				<li class="sub-menu">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe034;"></span><em>시스템 연동</em></a>
					<ul>
						<li><a href="#;">인사정보 연동설정</a></li>
						<li><a href="#;">연동자료 가져오기</a></li>
					</ul>
				</li>
				<li class="sub-menu">
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe052;"></span><em>시스템 설정</em></a>
					<ul>
						<li><a href="document6_1.html">코드 관리</a></li>
						<li><a href="document6_2.html">권한그룹 관리</a></li>
						<li><a href="document6_3.html">메뉴 관리</a></li>
						<li><a href="document6_4.html">메뉴권한 관리</a></li>
						<li><a href="#;">라이선스 관리</a></li>
						<li><a href="#;">시스템 패치 관리</a></li>
					</ul>
				</li> -->
			</ul>
		</nav>
		<!-- } 좌측 메뉴 -->
	</header>
	<!-- } 상단 헤더 -->

	<div id="container">
		<article id="top-mnu">
	
			<div class="admin_set_out">
				<ul class="floats">
					<li><b>슈퍼관리자</b>님 환영합니다.</li>
					<li><a href="#;"><span aria-hidden="true" data-icon="&#xe09a;"></span><span class="skip">환경설정</span></a></li>
					<li><a href="#;"><span aria-hidden="true" data-icon="&#xe097;"></span><span class="skip">로그아웃</span></a></li>
				</ul>
			</div>
		</article>

		<div id="content">			
			<div class="msg">
				메세지 영역 (경고, 알림 메시지 출력 영역 : 숨겨진 영역으로 알림이 있을 경우만 표시된다.)
			</div>
			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
						<li><a href="#;" class="on">목록조회</a></li>
<!-- 						<li><a href="#;">상세내용</a></li> -->
					</ul>
				</div>
				<!-- } 탭 영역 -->

				<!-- 검색 영역 { -->
				<div id="search_form">
					<table class="t_search">
						<colgroup>
							<col width="80" />
							<col />
							<col width="80" />
							<col />
							<col width="80" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>등록날짜</th>
								<td><input type="text" class="frm_input date_input" size="10" /> ~ <input type="text" class="frm_input date_input" size="10" /></td>
								<!-- <th>주소</th>
								<td>
									<select class="frm_select">
										<option>선택</option>
										<option>서울</option>
										<option>부산</option>
										<option>대구</option>
										<option>서울</option>
										<option>서울</option>
										<option>경기도</option>
										<option>강원도</option>
										<option>충청남도</option>
										<option>충청북도</option>
										<option>경상북도</option>
										<option>경상남도</option>
										<option>강원도</option>
										<option>강원도</option>
										<option>강원도</option>
										<option>강원도</option>
									</select>
								</td> -->
								<!-- <th>진행상태</th>
								<td>
									<select class="frm_select">
										<option>선택</option>
										<option>진행상태을 골라주세요</option>
										<option>진행상태을 골라주세요</option>
										<option>진행상태을 골라주세요</option>
									</select>
								</td> -->
							</tr>
							<tr>
								<th>검색</th>
								<td colspan="5">
									<select class="frm_select" style="width:100px">
										<option>선택</option>
										<option>상호(선박)명</option>
										<option>대표자</option>
										<option>주소</option>
									</select>
									<input type="text" class="frm_input" size="70" /> 
									<input type="submit" value="검색" class="btn_size1 btn_violet" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- } 검색 영역 -->

				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>06</b>건</div>
						<div class="listnum">
							출력수 
							<select class="frm_select">
								<option>10</option>
								<option>20</option>
								<option>30</option>
								<option>40</option>
								<option>50</option>
							</select>
						</div>
					</div>
					<table class="t_list">
						<colgroup>
							<col width="50" />
							<col width="110" />
							<col />
							<col width="130" />
							<col width="130" />
<!-- 							<col width="130" /> -->
							<col width="110" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">상호(선박)명</th>
								<th scope="col">주소</th>
								<th scope="col">휴대전화</th>
								<th scope="col">등록날짜</th>
								<!-- <th scope="col">처리상태</th> -->
								<th scope="col">대표자</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${admin_list}">
							<tr>
								<td class="textcenter"></td>
								<td class="textcenter">${item.co_nm}</td>
								<td><a href="boatfinfo.do">${item.co_addr_2}</a></td>
								<td class="textcenter">${item.co_phone}</td>
								<td class="textcenter">${item.reg_date}</td>
<!-- 								<td class="textcenter"><span class="stat stat_gray">요청</span></td> -->
								<td class="textcenter">${item.ceo_nm}</td>
							</tr>
						</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->

				<!-- 페이지 { -->
				<div id="pagenation">
					<a href="#;"><i class="fa fa-chevron-left" aria-hidden="true"></i></a><a href="#;">1</a><span class="on">2</span><a href="#;">3</a><a href="#;">4</a><a href="#;">23</a><a href="#;">24</a><a href="#;">25</a><a href="#;">26</a><a href="#;"><i class="fa fa-chevron-right" aria-hidden="true"></i></a>
				</div>
				<!-- } 페이지 -->
			</section>
		</div>
	</div>

	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>

</body>
</html>