<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>


<form:form commandName="eduCenterBoardVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" id="BD_SN" name="BD_SN" value=""/>
</form:form>

<form:form commandName="eduCenterBoardVO" id="listForm" name="listForm" method="post">


<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
		<div class="list_searchbox">
			
			<select class="basic_select" title="검색조건선택">
				<option>전체</option>
				<option>제목</option>
				<option>내용</option>
				<option>작성자</option>
			</select>
			<input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력"/>
			<button class="searchBtn"><i class="fa fa-search" aria-hidden="true" ></i></button>
			
		</div>
		<div class="">
			<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
			<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
			<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
		</div>
		<div class="board_list">
			<table class="list_tbl">
				<caption>조회리스트</caption>
				<colgroup>
					<col />
				</colgroup>
				<thead>
					<tr>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list_fixed}" varStatus="status">
						<tr class="noti">
							
							<fmt:parseDate var="parse_date_dt" value="${fn:replace(item.BD_REG_DT, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="timestring" value="${parse_date_dt}" pattern="yyyy-MM-dd" />
	           				<fmt:formatDate var="daystring" value="${parse_date_dt}" pattern="dd" />
	           				<fmt:formatDate var="ymstring" value="${parse_date_dt}" pattern="yyyy/MM" />           						

							<td class="subject align_left top">
								<a class="colorBlue" href="#;" onclick="javascript:fnView('${item.BD_SN}'); return false;">
									<em>
										<c:out value="${fn:substring(item.BD_TITLE,0,22)}"/><c:if test="${fn:length(item.BD_TITLE) > 23 }">...</c:if>
										<span class="red-600"><b>TOP</b></span>
									</em>
								</a>
								<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> <c:out value="${item.BD_VIEW_CNT }"></c:out> &nbsp;</span>
								<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> <c:out value="${timestring}"/></span>
							</td>
														
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td class="no alignCenter" >조회 된 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr class="noti">
							
							<fmt:parseDate var="parse_date_dt" value="${fn:replace(item.BD_REG_DT, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="timestring" value="${parse_date_dt}" pattern="yyyy-MM-dd" />
	           				<fmt:formatDate var="daystring" value="${parse_date_dt}" pattern="dd" />
	           				<fmt:formatDate var="ymstring" value="${parse_date_dt}" pattern="yyyy/MM" />           						

							<%-- <td><span><em><c:out value="${daystring}"/></em><small><c:out value="${ymstring}"/></small></span></td> --%>
							<td class="subject align_left">
								<a class="colorBlue" href="#;" onclick="javascript:fnView('${item.BD_SN}'); return false;">
									<em><c:out value="${fn:substring(item.BD_TITLE,0,22)}"/><c:if test="${fn:length(item.BD_TITLE) > 23 }">...</c:if></em>
								</a>
								<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> <c:out value="${item.BD_VIEW_CNT }"></c:out> &nbsp;</span>
								<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> <c:out value="${timestring}"/></span>
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<nav aria-label="Page navigation" class="text-center">
			<ul class="pagination">
				<ui:pagination paginationInfo = "${paginationInfo}" type="list" jsFunction="fnLinkPage" />
				<form:hidden path="pageIndex" />
			</ul>
		</nav>
	</section>
</div>

</form:form>

<script>
function fnView(bdsn) {
	document.viewForm.BD_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/educenter/m/board/file/view.do'/>";
    document.viewForm.submit();
}

function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/educenter/m/board/file/list.do'/>";
    document.listForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/educenter/m/board/file/list.do'/>";
    document.listForm.submit();
}
</script>
	
<%@include file="../../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
