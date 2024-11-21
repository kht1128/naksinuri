<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>



<form:form commandName="eduCategoryInsInfVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="CAT_INS_SN" value="" />
</form:form>


<form:form commandName="eduCategoryInsInfVO" id="listForm" name="listForm" method="post">
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
			<a class="append-rows btn btn-primary btn-outline" href="/eduadm/category/academy/write.do" >
				<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">신규등록</span>
        	</a> 
        </div>
	</div>
	<div class="page-content container-fluid">
		<div class="row">
			<div class="col-xl-12">
	      		<div class="panel mb-20">
		  			<div class="panel-body">
		    			<div class="input-group col-md-12 float-right">
		    				<%--
		    				<div class="mr-10">
		        				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchCondition">
		    						<option value="" <c:if test="${empty searchCondition}">selected</c:if>>검색조건없음</option>
		    						<option value="1" <c:if test="${searchCondition eq '1'}">selected</c:if>>전체</option>
		    						<option value="2" <c:if test="${searchCondition eq '2'}">selected</c:if>>제목</option>
		    						<option value="3" <c:if test="${searchCondition eq '3'}">selected</c:if>>내용</option>
		    					</select>
							</div>
							 --%>
							<input type="text" class="form-control mr-10" name="searchKeyword" placeholder="교육기관명 또는 등록자명을 입력해주세요." autocomplete="off" value="${searchKeyword}">
							<div class="mr-10">
		        				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="USE_ST">
		    						<option value="" <c:if test="${empty USE_ST}">selected</c:if>>사용여부</option>
		    						<option value="1" <c:if test="${USE_ST eq '1'}">selected</c:if>>사용중</option>
		    						<option value="0" <c:if test="${USE_ST eq '0'}">selected</c:if>>사용안함</option>
		    					</select>
							</div>
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
		                  	<col width=''>
		                  	<col width='15%'>
		                  	<col width='15%'>
		                  	<col width='10%'>
		          		</colgroup>
		          		<thead>
		          			<tr>
			          			<th class="text-middle text-center">번호</th>
								<th class="text-middle text-center">교육기관</th>
		           				<th class="text-middle text-center">등록자</th>
		           				<th class="text-middle text-center">등록일자</th>
		            			<th class="text-middle text-center">관리</th>
	            			</tr>        				
		             	</thead>
						<tbody>
							<c:set var="table_cell_cnt" value="5"/>
							<c:if test="${empty list}">
								<tr><td colspan="${table_cell_cnt}" class="text-center table-active">등록 된 게시물이 없습니다.</td></tr>	  
			            	</c:if>
			            	<c:forEach var="item" varStatus="status" items="${list}">
							<tr>
	                  			<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
	                 			<td class="text-middle text-left" >
	                 				<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									<c:if test="${item.USE_ST eq '0'}">
										<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									<a href="javascript:fnView('${item.CAT_INS_SN}');"><c:out value="${item.CAT_INS_NM}"/></a>
								</td>
	                  			<td class="text-middle text-center"><c:out value="${item.CAT_INS_NM}"/></td>
	                  			<td class="text-middle text-center">
	                  				<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  				<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss " var="REG_DT"/>
	                  				<c:out value="${REG_DT}"/>
	                  			</td>
	                  			<td class="text-middle text-center">
	                  				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row" data-toggle="tooltip" data-original-title="수정" 
					                	onclick="javascript:fnModify('${item.CAT_INS_SN}'); return false;">
				                		<i class="icon wb-wrench" aria-hidden="true"></i>
				                	</a>
									<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row" data-toggle="tooltip" data-original-title="삭제" 
										onclick="javascript:fnDelete('${item.CAT_INS_SN}','${item.DEL_ST}'); return false;">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
	                  			</td>     
		                    </tr>
			            	</c:forEach>
						</tbody>
		 				<tfoot>
		 					<tr class="footable-paging">
		 						<td colspan="${table_cell_cnt}">
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



function fnView(cat_ins_sn) {
	document.viewForm.CAT_INS_SN.value = cat_ins_sn;
    document.viewForm.action = "<c:url value='/eduadm/category/academy/view.do'/>";
    document.viewForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/eduadm/category/academy/list.do'/>";
    document.listForm.submit();
}
function fnDelete(cat_ins_sn,del_st) {
	var data_del_st = del_st;
	var alert_message = "";
	if(data_del_st == '1') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	alertify.confirm(alert_message, function(){ 
		document.viewForm.CAT_INS_SN.value = cat_ins_sn;
	    document.viewForm.action = "<c:url value='/eduadm/category/academy/delete_act.do'/>";
	    document.viewForm.submit();	 
	});
}	
function fnModify(cat_ins_sn) {
	document.viewForm.CAT_INS_SN.value = cat_ins_sn;
    document.viewForm.action = "<c:url value='/eduadm/category/academy/modify.do'/>";
    document.viewForm.submit();
}
</script>


<%@ include file="../../tail.jsp" %>
