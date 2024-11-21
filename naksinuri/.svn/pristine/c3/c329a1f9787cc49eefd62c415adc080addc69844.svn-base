<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>



<form:form commandName="eduBoardVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="BD_SN" value="" />
</form:form>


<form:form commandName="eduBoardVO" id="listForm" name="listForm" method="post">
<input type="hidden" name="searchUseYn" value="Y" />

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
			<a class="append-rows btn btn-primary btn-outline" href="/eduadm/board/admnotice/write.do" >
				<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">글쓰기</span>
        	</a> 
        </div>
	</div>
	<div class="page-content container-fluid">
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
		          			<col width='5%'>
		                  	<col width='5%'>
		                  	<col width='15%'>
		                  	<col width='*'>
		                  	<col width='5%'>
		                  	<col width='15%'>
		                  	<col width='10%'>
		                  	<col width='10%'>
		                  	<col width='10%'>
		          		</colgroup>
		          		<thead>
		          			<tr>
			          			<th class="text-middle text-center">번호</th>
								<th class="text-middle text-center">노출여부</th>
								<th class="text-middle text-center">노출대상</th>
								<th class="text-middle text-center">제목</th>
		           				<th class="text-middle text-center">첨부</th>
		           				<th class="text-middle text-center">작성자</th>
		           				<th class="text-middle text-center">작성날짜</th>
		            			<th class="text-middle text-center">조회수</th>
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
	                  			<td class="text-center" >
		                  			<c:if test="${item.BD_LOCK_ST eq 'Y'}"> 노출함 </c:if>
		                  			<c:if test="${item.BD_LOCK_ST eq 'N'}"> 노출안함 </c:if>
	                  			</td>
	                  			<td class="text-left"  >
		                  			<c:set var="BD_TRGT_CDs" value="${fn:split(item.BD_TRGT_CD,',')}"/>
		                  			<c:forEach var="BD_TRGT_CD" items="${BD_TRGT_CDs}" varStatus="idx">
										<c:forEach var="item_category" items="${list_position_cd}" >	
											<c:if test="${fn:trim(BD_TRGT_CD) eq item_category.CD_ID}">
												<span class="badge badge-info badge-sm">${item_category.CD_NM}</span>
											</c:if>
										</c:forEach>
									</c:forEach>
	                  			</td>
	                 			<td class="text-left" >
	                 				<c:if test="${item.BD_ST eq 'N'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									<a href="javascript:fnView('${item.BD_SN}');"><c:out value="${item.BD_TITLE}"/></a><c:if test="${item.BD_TOP_FIX_ST eq 'Y'}"><span class="red-600 font-weight-600">&nbsp;TOP</span></c:if>
								</td>
	                  			<td class="text-center"><c:if test="${not empty item.BD_FILE}"><a href="#;" style="font-size:20px"><i class="far fa-save"></i></a></c:if></td>
	                  			<td class="text-center"><c:out value="${item.MBR_NM}"/></td>
	                  			<td class="text-center">
	                  				<fmt:parseDate value="${fn:replace(item.BD_REG_DT, '.0', '')}" var="parse_bd_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					           		<fmt:formatDate value="${parse_bd_reg_dt}" pattern="yyyy-MM-dd" var="BD_REG_DT"/>
	                  				<c:out value="${BD_REG_DT}"/>	                  			
	                  			</td>
	                  			<td class="text-center"><c:out value="${item.BD_VIEW_CNT}"/></td>
	                  			<td class="text-center">
	                  				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row" data-toggle="tooltip" data-original-title="수정" 
					                	onclick="javascript:fnModify('${item.BD_SN}'); return false;">
				                		<i class="icon wb-wrench" aria-hidden="true"></i>
				                	</a>
									<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row" data-toggle="tooltip" data-original-title="삭제" 
										onclick="javascript:fnDelete('${item.BD_SN}','${item.BD_ST}'); return false;">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>	                  				
	                  			</td>     
		                    </tr>
			            	</c:forEach>
						</tbody>
		 				<tfoot>
		 					<tr class="footable-paging">
		 						<td colspan="9">
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
	document.viewForm.BD_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/eduadm/board/admnotice/view.do'/>";
    document.viewForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/eduadm/board/admnotice/list.do'/>";
    document.listForm.submit();
}
function fnDelete(bdsn,bdst) {
	var data_del_st = bdst;
	var alert_message = "";
	if(data_del_st == 'N') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	var data_crs_sn = $(this).attr('data-crs-sn');
	alertify.confirm(alert_message, function(){ 
		document.viewForm.BD_SN.value = bdsn;
	    document.viewForm.action = "<c:url value='/eduadm/board/admnotice/delete_act.do'/>";
	    document.viewForm.submit();	 
	});
}	
function fnModify(bdsn) {
	document.viewForm.BD_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/eduadm/board/admnotice/modify.do'/>";
    document.viewForm.submit();
}
</script>


<%@ include file="../../tail.jsp" %>
