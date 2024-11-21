<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>.pl10 {padding-left: 10px;}</style>

<form:form commandName="dwldConfimFileVO" id="listForm" name="listForm" method="post">
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
      </div>
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
          
          	<!-- 검색폼 -->
          	<input type="hidden" name="searchUseYn" value="Y" />
          	<div class="panel mb-20">
	  			<div class="panel-body">
	    			<div class="input-group col-md-12 float-right">
	    				<input type="hidden" name="searchOrderBy" value="${searchOrderBy}"/>
	    				<div class="mr-10 col-md-3">
	    					<div class="input-group">
	    						<div class="input-group-prepend">
			           				<span class="input-group-text">출력갯수</span>
			           			</div>
	    						<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage} ">
	    					</div>
						</div>
						<div class="mr-10">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CONFIM_ST">
	    						<option value="" <c:if test="${empty CONFIM_ST}">selected</c:if>>승인상태(전체)</option>
	    						<option value="N" <c:if test="${CONFIM_ST eq 'N'}">selected</c:if>>승인대기</option>
	    						<option value="Y" <c:if test="${CONFIM_ST eq 'Y'}">selected</c:if>>승인완료</option>
	    						<option value="CANCEL" <c:if test="${CONFIM_ST eq 'CANCEL'}">selected</c:if>>승인취소</option>
	    					</select>
						</div>
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
						</span>
					</div>						
	  			</div>
			</div>
			<!-- End 검색폼 --> 
          
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
	          			<col width="40px"/>
	          		</colgroup>
	          		<thead>
	          			<tr>
							<th class="text-middle text-center">번호</th>
							<th class="text-middle text-center">신청일자</th>
							<th class="text-middle text-center">신청자</th>
							<th class="text-middle text-center">신청파일</th>
							<th class="text-middle text-center">승인여부</th>
							<th class="text-middle text-center">다운로드</th>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="6"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">현재 신청 건이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
                            	<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
				                <td class="text-middle text-center">
				                	<fmt:parseDate var="parseregdatestring" value="${item.REG_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
         							<fmt:formatDate var="reg_dt" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
         							<c:out value="${reg_dt }"/>
								</td>
								<td class="text-middle text-center">${item.REQ_MBR_NM}</td>
				                <td class="text-middle text-center">${item.ORIGNL_FILE_NM}</td>
				                <td class="text-middle text-center">
				                	<c:choose>
				                		<c:when test="${item.DWLD_WAIT_ST eq 'Y'}">자료준비중</c:when>
				                		<c:otherwise>
				                			<c:choose>
						                		<c:when test="${item.CONFIM_ST eq 'Y'}">승인완료
						                			<fmt:parseDate var="parseconfimdatestring" value="${item.CONFIM_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
				         							<fmt:formatDate var="confim_dt" value="${parseconfimdatestring}" pattern="yyyy.MM.dd HH:mm" />
				         							<br><c:out value="( ${confim_dt } )"/>
						                		</c:when>
						                		<c:when test="${item.CONFIM_ST eq 'CANCEL'}">승인취소
						                			<fmt:parseDate var="parseconfimdatestring" value="${item.CONFIM_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
				         							<fmt:formatDate var="confim_dt" value="${parseconfimdatestring}" pattern="yyyy.MM.dd HH:mm" />
				         							<br><c:out value="( ${confim_dt } )"/>
						                		</c:when>
						                		<c:otherwise>승인대기</c:otherwise>				                	
						                	</c:choose>
				                		</c:otherwise>				                	
				                	</c:choose>
								</td>
				                <td class="text-middle text-center">				                
				                	<c:choose>
				                		<c:when test="${item.CONFIM_ST eq 'Y'}">
			                				<c:choose>
						                		<c:when test="${item.DWLD_CMPLT_ST eq 'Y'}">
						                			다운로드완료<br>
					                				<fmt:parseDate var="parsedwldcmpltdatestring" value="${item.DWLD_CMPLT_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
				         							<fmt:formatDate var="dwld_cmplt_dt" value="${parsedwldcmpltdatestring}" pattern="yyyy.MM.dd HH:mm" />
				         							<c:out value="( ${dwld_cmplt_dt } )"/>
						                		</c:when>
						                		<c:otherwise>
						                			<button type="button" class="btn btn-outline btn-success btn-xs btn-act-file-download" 
									                	data-linkurl="/eduadm/"
									                	data-file-key="${item.ATCH_FILE_ID};${item.FILE_SN}">파일받기
									                </button>
						                		</c:otherwise>				                	
						                	</c:choose>		         							
				                		</c:when>
				                		<c:when test="${item.CONFIM_ST eq 'CANCEL'}">
				                			${item.CONFIM_MSG}
				                		</c:when>
				                		<c:otherwise></c:otherwise>				                	
				                	</c:choose>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging hide-cell-exceldown">
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
      </div>
    </div>
    <!-- End Page -->
</form:form>

    <script>
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    });
    $('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
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
    
    
    $(".btn-act-file-download").click(function() {
    	var filekey = $(this).attr('data-file-key');
    	var alert_message = "다운로드는 1회만 가능하며 확인 버튼 클릭 후 즉시 완료 처리됩니다.<br>다시 받으셔야 하는 경우에는 재신청을 해주셔야 합니다.<br>계속 하시겠습니까?";
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var parseFilekey = filekey.split(';');
	    	window.open('/cmm/fms/FileDown.do?atchFileId='+parseFilekey[0]+'&fileSn='+parseFilekey[1]);
	    	document.listForm.submit();
    	});
   	});
    
   </script>
    
<%@ include file="../tail.jsp" %>
