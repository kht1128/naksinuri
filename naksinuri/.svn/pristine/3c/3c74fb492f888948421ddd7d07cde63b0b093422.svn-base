<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="pageName" value="auditor" />

<%@include file="../../layout/m/head.jsp"%>

<form:form commandName="boardVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="bo_sn" id="bo_sn">
</form:form>

<form:form commandName="boardVO" id="listForm" name="listForm" method="post">
<input type="hidden" name="pageIndex" value="1">

<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
		<div class="list_searchbox">
			
			<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
			<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
			<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
			
			<select class="basic_select" title="검색조건선택">
				<option>전체</option>
				<option>제목</option>
				<option>내용</option>
				<option>작성자</option>
			</select>
			<input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력"/>
			<button class="searchBtn"><span class="blind">검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>

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
					<c:if test="${empty list}">
						<tr>
							<td class="no alignCenter">조회 된 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr class="noti">
							
							<fmt:parseDate var="parse_date_dt" value="${fn:replace(item.bo_insert_dt, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="timestring" value="${parse_date_dt}" pattern="yyyy-MM-dd" />
	           				<fmt:formatDate var="daystring" value="${parse_date_dt}" pattern="dd" />

							<td class="subject align_left">
								<a class="colorBlue" href="#;" onclick="javascript:fnView('${item.bo_sn}'); return false;">
									<em><c:out value="${fn:substring(item.bo_subject,0,22)}"/><c:if test="${fn:length(item.bo_subject) > 23 }">...</c:if></em>
								</a>
								<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i> <c:out value="${item.bo_view }"></c:out> &nbsp;</span>
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
		
		<div id="btnArea" class="noupline">
			<ul class="floats">
				<li class="fr"><a href="/promotion/m/auditor/board_write.do" class="btn_report btn_red">활동보고서 등록</a></li>
			</ul>
		</div>
	</section>
</div>

</form:form>

<script>
function fnView(bdsn) {
	document.viewForm.bo_sn.value = bdsn;
    document.viewForm.action = "/promotion/m/auditor/board_view.do";
    document.viewForm.submit();
}

/* function fnView(bo_sn){
	var form = document.getElementById('viewForm');
	//$('#bo_sn').val(bo_sn);
	
	form.action="./view.do";
	form.submit();
};	 */

function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "/promotion/m/auditor/board_list.do";
    document.listForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "/promotion/m/auditor/board_list.do";
    document.listForm.submit();
}

/* $("#btn_submit").on("click", function(){
	/* var form = document.getElementById('listForm');
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/promotion/auditor/board_write.do",
		data:$('#listForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			//$("#allPublicModal").html(data);
			//$("#allPublicModal").modal('show');
			//$("#allPublicModal").modal({backdrop: 'static', keyboard: false});
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	}); 
	
	//document.listForm.pageIndex.value = pageNo;
    //document.listForm.action = "/promotion/auditor/board_list.do";
    //document.listForm.submit();
}); */
</script>

<%@include file="../../layout/m/tail.jsp"%>