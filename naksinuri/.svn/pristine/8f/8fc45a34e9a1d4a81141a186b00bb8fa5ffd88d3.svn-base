<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info" />
<c:set var="pageName" value="lab" />



<%@include file="../layout/newHead.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>
<form id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>
	
	
			
	
	<div id="fishjobList" class="content respon2">
		<section id="webzineList" class="list_box">
		
			<div class="list_searchbox">
			<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${Math.round(select_total/pageUnit)+1})</div>
			<select class="basic_select" name="searchType" title="검색조건선택">
				<option value="title">제목</option>
				<option value="writer_name">글쓴이</option>
			</select>
			<input type="text" class="basic_input" id="searchText2" name="searchText" title="검색어"/>
			<button class="searchBtn" type="button" onclick="fnSelectInfs(1)"><span class="blind">낚시연구소 검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
		</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<caption>낚시연구소 게시물 리스트</caption>
					<colgroup>
						<col width="130" />
						<col />
						<col width="140" class="mbNone" />
						<col width="140" class="mbNone" />
						<col width="140" class="mbNone" />
						<col width="140" class="mbNone" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th class="mbNone">작성자</th>
							<th class="mbNone">파일</th>
							<th class="mbNone">등록일</th>
							<th class="mbNone">조회수</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${select_list}">
						<tr>
							<td><span>${select_total-item.rn+1}</span></td>
							<td class="subject align_left">
								<a href="#;"onclick="view2('${item.bo_sn}')">${item.bo_subject}</a>
								<div class="mbShow">
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item.bo_view }"></c:out></span>
									<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>${fn:substring(item.bo_insert_dt,0,10)}</span>
								</div>
							</td>
							<td class="mbNone"><span>${item.bo_name }</span></td>
							
							<td class="mbNone"><c:if test="${item.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif"></c:if></td>
							
							<td class="date mbNone">${fn:substring(item.bo_insert_dt,0,10)}</td>
							<td class="mbNone"><c:out value="${item.bo_view }"></c:out></td>
						</tr>
					</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>		
							<td class="noData" colspan="6">등록된 게시물이 없습니다.</td>								
						</tr>					
					</c:if>
					</tbody>
				</table>
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

	
	

	

function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	$("#gallery_list").val();
	$("#bo_cate").val();		
	$("#frm").attr("action", "/sosig/lab/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

function fnSelectInfs2(gallery_list) {
	
	$("#pageUnit").val();
	$("#pageIndex").val();
	$("#gallery_list").val(gallery_list);
	$("#frm").attr("action", "/sosig/lab/list.do");
	
	$("#frm").submit();
	
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}	

	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};	

	// 모바일에서 검색창 숨김
	$(document).ready(function (){
		$('.list_searchbox .search_group').addClass('mbNone');
	});
	
</script>

<%@include file="../layout/tail.jsp"%>