<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="auditor" />

<%@include file="../layout/newHead.jsp"%>

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
		<div class="board_list type1">
			<table class="list_tbl">
				<caption>조회리스트</caption>
				<colgroup>
					<col width="100" class="mbNone" />
					<col />
					<col width="80" class="mbNone" />
					<col width="150" class="mbNone" />
					<col width="80" class="mbNone" />
				</colgroup>
				<thead>
					<tr>
						<th class="mbNone">일자</th>
						<th>제목</th>
						<th class="mbNone">첨부</th>
						<th class="mbNone">작성자</th>
						<th class="mbNone">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list_fixed}" varStatus="status">
						<tr class="noti">
							<td class="date alignCenter mbNone"><span class="red-600"><b>TOP</b></td>
							<td class="subject align_left">
								<a href="#;" onclick="javascript:fnView('${item.bo_sn}');"><c:out value="${item.bo_subject}"/></a>
								<div class="mbShow">
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item.bo_view }"></c:out></span>
									<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i><small><c:out value="${ymstring}"/></small></span>
								</div>
							</td>
							<td class="file alignCenter mbNone"><a href="#;" class="colorBlue"><c:if test="${not empty item.bo_main_img}"><i class="fa fa-file"></i></c:if></a></td>
							<td class="writer alignCenter mbNone"><c:out value="${item.bo_name}"/></td>
							<td class="hit alignCenter mbNone"><c:out value="${item.bo_view}"/></td>	
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td class="no alignCenter noData" colspan="5">조회 된 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr class="noti">
							
							<fmt:parseDate var="parse_date_dt" value="${fn:replace(item.bo_insert_dt, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
	           				<fmt:formatDate var="daystring" value="${parse_date_dt}" pattern="dd" />
	           				<fmt:formatDate var="ymstring" value="${parse_date_dt}" pattern="yyyy-MM-dd" />           						
							
							<td class="date alignCenter mbNone"><small><c:out value="${ymstring}"/></small><!-- &nbsp;<em><c:out value="${daystring}"/></em> --></td>
							<td class="subject align_left">
								<a href="#;" onclick="javascript:fnView('${item.bo_sn}');"><c:out value="${item.bo_subject}"/></a>
								<div class="mbShow">
									<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item.bo_view }"></c:out></span>
									<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i><small><c:out value="${ymstring}"/></small></span>
								</div>
							</td>
							<td class="file alignCenter mbNone"><a href="#;" class="colorBlue"><c:if test="${not empty item.bo_main_img}"><i class="fas fa-file-alt"></c:if></i></a></td>
							<td class="writer alignCenter mbNone"><c:out value="${item.bo_name}"/></td>
							<td class="hit alignCenter mbNone"><c:out value="${item.bo_view}"/></td>
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
				<li class="fr"><a href="/promotion/auditor/board_write.do" class="btn_report btn_red">활동보고서 등록</a></li>
			</ul>
		</div>
	</section>
</div>

</form:form>

<script>
function fnView(bdsn) {
	document.viewForm.bo_sn.value = bdsn;
    document.viewForm.action = "/promotion/auditor/board_view.do";
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
    document.listForm.action = "/promotion/auditor/board_list.do";
    document.listForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "/promotion/auditor/board_list.do";
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


<%@include file="../layout/tail.jsp"%>