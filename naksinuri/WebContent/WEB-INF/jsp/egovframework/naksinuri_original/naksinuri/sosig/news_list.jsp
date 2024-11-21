<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="news" />



<%@include file="../layout/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="mid" id="mid"/>
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div class="content respon2">
		<section id="webzineList" class="list_box">
			
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
				<select class="basic_select" name="searchType" title="검색조건선택">
					<option value="title" <c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
					<option value="body" <c:if test="${searchType eq 'body'}">selected</c:if>>내용</option>
				</select>
				<input type="text" class="basic_input" id ="searchText2" name="searchText" value="${searchText}" />
				<button class="searchBtn" type="button" onclick="fnSelectInfs(1)"><span class="blind">검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
					
			</div>
			
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
				
					<c:forEach var="item" items="${select_list}">
						<tr>
							<td><span>${select_total-item.rn+1}</span></td>
							<td class="subject align_left"><a target="_blank" href="${item.link}">${item.title}</a></td>
							<td class="date">${fn:substring(item.regdate,0,10)}</td>
						</tr>
					</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>			
							<td colspan="4">등록된 게시물이 없습니다.</td>
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
	
function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/news/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}
	



	
</script>

<%@include file="../layout/tail.jsp"%>