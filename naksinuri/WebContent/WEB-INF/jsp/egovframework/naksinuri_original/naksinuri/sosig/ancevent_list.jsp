<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="event" />
<c:if test="${pageName eq 'event'}">
	<c:set var="bo_cate" value="ancevent" />
</c:if>


<%@include file="../layout/newHead.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="evn_no" id="evn_no"/>
	
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
	<div id="fishjobList" class="content respon2">
		<section id="webzineList" class="list_box">

			<div class="tabArea tab3 mbNone"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
				<ul class="floats">
					<li><a href="/sosig/event/list.do" title="진행중인 이벤트">진행중인 이벤트</a></li>
					<li><a href="/sosig/endevent/list.do" title="종료된 이벤트">종료된 이벤트</a></li>
					<li class="on"><a href="/sosig/ancevent/list.do" title="선택됨">당첨자 발표</a></li>
				</ul>
			</div>
			
			<div class="tabArea2 mbShow"> <!-- 모바일 ui -->
				<div class="link_select">
					<dl>
						<dt><a href="#;" id="this_cate"> <span><i class="fa fa-angle-down" aria-hidden="true"></i></span></a></dt>
						<dd>
							<ul>
								<li><a href="/sosig/event/list.do">진행중인 이벤트</a></li>
								<li><a href="/sosig/endevent/list.do">종료된 이벤트</a></li>
								<li><a href="/sosig/ancevent/list.do">당첨자 발표</a></li>
							</ul>
						</dd>
					</dl>
				</div>
			</div>
		
			<div class="list_searchbox">
				<div class="total_num">전체 <b class="colorSky">${event_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${Math.round(event_total/pageUnit)+1})</div>
			
				<select class="basic_select" name="searchType" title="검색조건선택">
					<option value="evn_subject">제목</option>
					<option value="evn_content">내용</option>
					<option value="evn_writer">글쓴이</option>
				</select>
				<input type="text" class="basic_input" id ="searchText2" name="searchText" />
				<button class="searchBtn" type="button" onclick="fnSelectInfs(1)"><span class="blind">당첨자 발표 검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
					
			</div>
	
			<div class="board_list">
		
				<table class="list_tbl">
				<caption>당첨자 발표 리스트</caption>
					<colgroup>
						<col width="130" />
						<col />
						<col width="180" class="mbNone" />
						<col width="140" class="mbNone" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th class="mbNone">등록일</th>
							<th class="mbNone">조회수</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${ancevent_list}">
						<tr>
							<td><span>${item.rn}</span></td>
							<td class="subject align_left">
								<a href="#;"onclick="view2('${item.evn_no}')" title="${item.evn_subject} 상세보기">${item.evn_subject}</a>
								<div class="mbShow">
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item.evn_viewhit }"></c:out></span>
									<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>${fn:substring(item.evn_insert_dt,0,10)}</span>
								</div>
							</td>
							<td class="date mbNone">${fn:substring(item.evn_insert_dt,0,10)}</td>
							<td class="mbNone">${item.evn_viewhit }</td>
						</tr>
					</c:forEach>
					<c:if test="${ancevent_list eq null }">
						<tr>
							<td class="noData" colspan="4">작성된 글이 없습니다.</td>
						</tr>					
					</c:if>
					</tbody>
				</table>
				
			</div>
			
		

			
			</div>
		
	
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</section>
	</div>
</form>
<script type="text/javascript">

	// 게시판 리스트 or 갤러리 토글버튼
	var pageName = "${pageName}";
	var gallery_list= "${gallery_list}";
	
	
	
	if(gallery_list == 'list'){
		$('.change_type.list').addClass("on");
		$('.change_type.gallery').removeClass("on");
		$('.board_list').removeClass("off");
		$('.gallery_list').addClass("off");

	}
	if(gallery_list == 'gallery'){
		$('.change_type.gallery').addClass("on");
		$('.change_type.list').removeClass("on");
		$('.board_list').addClass("off");
		$('.gallery_list').addClass("off");

	}
	
// 	if(pageName =='event' && gallery_list == 'gallery'){
// 		$('.change_type.list').removeClass("on");
// 		$('.change_type.gallery').addClass("on");
// 		$('.board_list').addClass("off");
// 		$('.gallery_list').removeClass("off");

// 	}

	
	
	


function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	
	$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/ancevent/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}
function fnSelectInfs2(gallery_list) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(1);
	$("#gallery_list").val(gallery_list);
	$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/ancevent/list.do");
	
	$("#frm").submit();
	
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}	
	

	function view2(evn_no){
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		
		form.action="/sosig/event/view.do";
		form.submit();
	};	

	// 모바일에서 검색창 숨김
	$(document).ready(function (){
		$('.list_searchbox .search_group').addClass('mbNone');
	});
	
</script>

<%@include file="../layout/tail.jsp"%>