<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:scriptlet>
    pageContext.setAttribute("cr", "\r");
    pageContext.setAttribute("lf", "\n");
    pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>


<form name="listForm" id="listForm" action="/seadm/homestay/rqlist.do" method="post">
	<input name="selectedId" type="hidden" />
	<input name="checkedIdForDel" type="hidden" />
	<input name="pageIndex" id="pageIndex" type="hidden" value="<c:out value='${doctorRequestVO.pageIndex}'/>" />
	
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
        	<%--
        	<a class="append-rows btn btn-primary btn-outline" href="" onclick="excelHomestayDown(); return false;">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">엑셀다운로드</span>
        	</a>
        	--%> 
        </div>
      </div>

      <div class="page-content container-fluid">
      
      	<div class="row">
			<div class="col-xl-12">
	      		<div class="panel mb-20">
		  			<div class="panel-body">		  			
		    			<div class="input-group col-md-12 float-right">
		    				<div class="mr-10">
			    				<select name="skey" class="form-control selectpicker_manual" data-style="btn-outline btn-default">
									<option value="" <c:if test="${empty skey}">selected</c:if> >검색조건</option>
									<option value="1" <c:if test="${skey == '1' }">selected</c:if> >연락처</option>
									<option value="2" <c:if test="${skey == '2' }">selected</c:if> >회원아이디</option>
								</select>
							</div>
							<input  name="skeyw" class="form-control" type="text" placeholder="검색어를 입력해주세요." value="<c:out value="${skeyw}"/>" autocomplete="off">
							<span class="input-group-append">
								<button class="btn btn-primary" onclick="submit_func(); return false;"><i class="icon wb-search" aria-hidden="true"></i></button>
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
		          			<col width='10%' >
		                  	<col width='16%'>
		                  	<col width='16%'>
		                  	<col width='16%'>
		                  	<col width='16%'>
		                  	<col width='16%'>
		                  	<col width='10%'>
		          		</colgroup>
		          		<thead>
		          			<tr>
		            			<th class="text-middle text-center">번호</th>
								<th class="text-middle text-center">운영자명</th>
								<th class="text-middle text-center">신청자명</th>
								<th class="text-middle text-center">연락처</th>
								<th class="text-middle text-center">등록일</th>
								<th class="text-middle text-center">상태</th>
								<th class="text-middle text-center">관리</th>
	            			</tr>        				
		             	</thead>
						<tbody>
      						<c:if test="${empty list_total}">
								<tr><td colspan="7" class="text-center table-active">등록 된 게시물이 없습니다.</td></tr>	  
			            	</c:if>
			            	<c:forEach var="item" varStatus="status" items="${list_total}">
							<tr>
	                  			<td class="text-middle text-center">${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}</td>
								<td class="text-middle text-center">${item.hsnm }</td>
								<td class="text-middle text-center">${item.hsmbrnm}</td>
								<td class="text-middle text-center">${item.hshp}</td>
								<td class="text-middle text-center">${item.hsregdt}</td>
								<td class="text-middle text-center"><c:if test="${item.hsprocess eq 'Y' }">답변완료</c:if>	</td>
								<td class="text-middle text-center">
			                      	<a href="/seadm/homestay/rqview.do?sn=${item.hssn}"><i class="site-menu-icon fas fa-pen" aria-hidden="true"></i></a>
			                      	<a href="#" onclick="delact(${item.hssn}); return false;"><i class="site-menu-icon fas fa-trash" aria-hidden="true"></i></a>
								</td>		    
		                    </tr>
			            	</c:forEach>
						</tbody>
		 				<tfoot>
		 					<tr class="footable-paging">
		 						<td colspan="7">
		 							<div class="footable-pagination-wrapper">
		 								<ul class="pagination">
		 									<ui:pagination paginationInfo = "${paginationInfo}" type="list" jsFunction="fnLinkPage" />
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
	    
</form>
    
    <script>
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    });
    function fnLinkPage(pageNo){
        document.listForm.pageIndex.value = pageNo;
        document.listForm.action = "<c:url value='/seadm/homestay/rqlist.do'/>";
        document.listForm.submit();
    }
    function fnSearch(){
    	document.listForm.pageIndex.value = 1;
    	document.listForm.action = "<c:url value='/seadm/homestay/rqlist.do'/>";
        document.listForm.submit();
    }
	function delact(obj) {
		if(confirm('정말 삭제하시겠습니까?')) {
			location.href="/seadm/homestay/delRequestAction.do?sn="+obj;		
		}
	}    
	function submit_func() {
		$('#pageIndex').val(1);
		$('#listForm').submit();
	}
	</script>

<%@ include file="../tail.jsp" %>
