<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>

<form:form commandName="popupVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="PP_SN" value="" />
</form:form>


<form:form commandName="popupVO" id="listForm" name="listForm" method="post">
<input type="hidden" name="searchUseYn" value="Y" />
<input type="hidden" name="PP_TYPE" value="${PP_TYPE}"/>

<!-- Page -->
<div class="page">
	<div class="page-header">
		<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
		<div class="page-header-actions">
			<a class="append-rows btn btn-primary btn-outline" href="/seadm/popup/write.do" >
				<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">글쓰기</span>
        	</a>
        </div>
	</div>
	<div class="page-content container-fluid">
	
		<div class="nav-tabs-horizontal mb-10" data-plugin="tabs">
			<ul class="nav nav-tabs nav-tabs-line" role="tablist">
				<li class="nav-item" role="presentation"><a class="nav-link <c:if test="${empty PP_TYPE}">active</c:if>" href="javascript:fnSearchTab('')" role="tab">전체</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link <c:if test="${PP_TYPE eq 'popup'}">active</c:if>" href="javascript:fnSearchTab('popup')" role="tab">팝업</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link <c:if test="${PP_TYPE eq 'banner_top'}">active</c:if>" href="javascript:fnSearchTab('banner_top')" role="tab">배너(상단)</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link <c:if test="${PP_TYPE eq 'banner_center'}">active</c:if>" href="javascript:fnSearchTab('banner_center')" role="tab">배너(중앙)</a></li>
			</ul>
		</div>
	
	
		<div class="row">
			<div class="col-xl-12">
	      		<div class="panel mb-20">
	      			<!-- 
					<div class="panel-heading">
		    			<h3 class="panel-title">검색&nbsp;&nbsp;
		      				&nbsp;<small>검색1</small>
		    			</h3>
		  			</div>
		  			 -->
		  			<div class="panel-body">
		    			<div class="input-group col-md-12 float-right">
		    				<div class="mr-10">
		        				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchCondition">
		    						<option value="" <c:if test="${empty searchCondition}">selected</c:if>>검색조건없음</option>
		    						<option value="1" <c:if test="${searchCondition eq '1'}">selected</c:if>>전체</option>
		    						<option value="2" <c:if test="${searchCondition eq '2'}">selected</c:if>>제목</option>
		    						<option value="3" <c:if test="${searchCondition eq '3'}">selected</c:if>>내용</option>
		    					</select>
							</div>
							<input type="text" class="form-control mr-10" name="searchKeyword" placeholder="제목 또는 내용을 입력해주세요." autocomplete="off" value="${searchKeyword}">
							<span class="input-group-btn">
								<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
							</span>
						</div>						
		  			</div>
				</div> 		          
	            <!-- Panel Editing Rows -->
	            <div class="panel">
	              	<div class="panel-body">
	              		<div class="example-tooltip">
							<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
								<div class="arrow"></div>
								<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount} 건</div>
							</div>
						</div>
	              		<!-- table:start -->
		      			<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
		          		<colgroup>
		          			<col width='5%' >
		          		</colgroup>
		          		<thead>
		          			<tr>
			          			<th class="text-middle text-center">번호</th>
			          			<th class="text-middle text-center">구분</th>
								<th class="text-middle text-center">팝업명</th>
		           				<th class="text-middle text-center">게시기간</th>
		           				<th class="text-middle text-center">노출유무</th>
		           				<th class="text-middle text-center">사용구분</th>
		            			<th class="text-middle text-center">관리</th>
	            			</tr>        				
		             	</thead>
						<tbody>
							<c:if test="${empty list}">
								<tr><td colspan="7" class="text-center table-active">등록 된 게시물이 없습니다.</td></tr>	  
			            	</c:if>
			            	<c:forEach var="item" varStatus="status" items="${list}">
							<tr>
	                  			<td class="text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
	                  			<td class="text-center">
	                  				<c:choose>
	                  				<c:when test="${item.PP_TYPE eq 'popup'}">팝업</c:when>
									<c:when test="${item.PP_TYPE eq 'banner_top'}">배너(상단)</c:when>
									<c:when test="${item.PP_TYPE eq 'banner_center'}">배너(중앙)</c:when>
									<c:otherwise>구분없음</c:otherwise>
	                  				</c:choose>
	                  			</td>
	                 			<td class="text-left" >
	                 				<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제된 팝업입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
	                 				<a class="popupAjaxAlignTop" href="/seadm/popup/view.do?PP_SN=${item.PP_SN}">
	                 					<c:choose>
		                 					<c:when test="${fn:length(item.PP_TITLE) > 30}">
			                 					<c:out value="${fn:substring(item.PP_TITLE, 0, 30)} ....."/> 
		                 					</c:when>
		                 					<c:otherwise>
			                 					<c:out value="${item.PP_TITLE}"/>
		                 					</c:otherwise>
	                 					</c:choose>
	                 				</a>
	                 				
	                 			</td>
	                  			<td class="text-center">
	                  				<fmt:parseDate value="${fn:replace(item.PP_STR_DT, '.0', '')}" var="parse_pp_str_dt" pattern="yyyy-MM-dd" scope="page"/>
				            		<fmt:parseDate value="${fn:replace(item.PP_END_DT, '.0', '')}" var="parse_pp_end_dt" pattern="yyyy-MM-dd" scope="page"/>
				            		<fmt:formatDate value="${parse_pp_str_dt}" pattern="yyyy-MM-dd (E)" var="PP_STR_DT"/>
				            		<fmt:formatDate value="${parse_pp_end_dt}" pattern="yyyy-MM-dd (E)" var="PP_END_DT"/>
	                  				<c:out value="${PP_STR_DT} ~ ${PP_END_DT}"/>	                  			
	                  			</td>
	                  			<td class="text-center">
	                  				<c:if test="${item.PP_HIDE_ST eq 'Y'}">노출안함</c:if>
                  					<c:if test="${item.PP_HIDE_ST eq 'N'}">노출중</c:if>
	                  			</td>
	                  			<td class="text-center">
	                  				<c:if test="${item.PP_SEA eq 'Y'}"><span class="badge badge-dark">낚시누리</span></c:if>
                  					<c:if test="${item.PP_EDU eq 'Y'}"><span class="badge badge-dark">낚시전문교육</span></c:if>
                  					<c:if test="${item.PP_EXPO eq 'Y'}"><span class="badge badge-dark">랜딩페이지</span></c:if>
                  					<%-- <c:if test="${item.PP_EXPO eq 'Y'}"><span class="badge badge-dark">박람회</span></c:if>
                  					<c:if test="${item.PP_CTI eq 'Y'}"><span class="badge badge-dark">CTI</span></c:if> --%>
                  				</td>
	                  			<td class="text-center">
	                  				<a href="#;" onclick="javascript:fnModify('${item.PP_SN}'); return false;"><i class="site-menu-icon fas fa-pen" aria-hidden="true"></i></a>
	                  				<a href="#" onclick="javascript:fnDelete('${item.PP_SN}'); return false;"><i class="site-menu-icon fas fa-trash" aria-hidden="true"></i></a>
	                  			</td>     
		                    </tr>
			            	</c:forEach>
						</tbody>
		 				<tfoot>
		 					<tr class="footable-paging">
		 						<td colspan="7">
		 							<div class="footable-pagination-wrapper">
		 								<ul class="pagination">
		 									<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page" />
						        			<form:hidden path="pageIndex" />
		 								</ul>	 								
		 							</div>
		 						</td>
		 					</tr>
		 				</tfoot>				  
						</table>
	              		<!-- table:end -->
	              	</div>
				</div>
			</div>
		</div>
		<!-- End row -->
	</div>
	<!-- End page-content -->
</div>
<!-- End Page -->
</form:form>

<script>
$(function(){
	$('.selectpicker_manual').selectpicker();
	$('.popupAjaxAlignTop').magnificPopup({
   		type: 'ajax',
    	alignTop: true,
    	overflowY: 'scroll' 
  	});
});
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "";
   	document.listForm.submit();
}
$(".clk_search_btn").click(function() {
	var form = document.getElementById('listForm');
	form.pageIndex.value = '1';
	form.action = '';
	form.submit();
});
function fnView(bdsn) {
	var gsWin = window.open("about:blank", "prevWindow", "width=100px, resizable=no, scrollbars=no, status=no;");
	document.viewForm.PP_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/seadm/popup/view.do'/>";
    document.viewForm.target = "prevWindow";
    document.viewForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/seadm/popup/list.do'/>";
    document.listForm.submit();
}
function fnDelete(bdsn) {
	if(confirm('정말 삭제하시겠습니까?')) {
		document.viewForm.PP_SN.value = bdsn;
	    document.viewForm.action = "<c:url value='/seadm/popup/delete_act.do'/>";
	    document.viewForm.submit();
	}
}	
function fnModify(bdsn) {
	document.viewForm.PP_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/seadm/popup/modify.do'/>";
    document.viewForm.submit();
}
function fnSearchTab(idx){
	document.listForm.pageIndex.value = 1;
	document.listForm.PP_TYPE.value = idx;
	document.listForm.action = "<c:url value='/seadm/popup/list.do'/>";
    document.listForm.submit();
}
</script>


<%@ include file="../tail.jsp" %>

