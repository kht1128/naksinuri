<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<form:form commandName="eduCertificateVO" id="listForm2" name="listForm2" method="post">
	<input type="hidden" id="MBR_ID" name="MBR_ID" value=""/>
	<input type="hidden" id="CRTF_SN" name="CRTF_SN" value=""/>
	<input type="hidden" id="CRTF_DTL_SN" name="CRTF_DTL_SN" value=""/>
</form:form>


<form:form commandName="eduCertificateVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchUseYn" value="Y" />
	<input type="hidden" id="MBR_ID" name="MBR_ID" value=""/>
	<input type="hidden" id="CRTF_SN" name="CRTF_SN" value="${eduCertificateVO.CRTF_SN}"/>
	
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
          
          	<%--
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
	    						<option value="mbr" <c:if test="${searchCondition eq 'mbr'}">selected</c:if>>회원검색</option>
	    						<option value="crs" <c:if test="${searchCondition eq 'crs'}">selected</c:if>>교육과정검색</option>
	    					</select>
						</div>
						<input type="text" class="form-control mr-10" name="searchKeyword" placeholder="검색조건을 선택하시고 이름,연락처,아이디 또는 교육과정명를 입력하세요." autocomplete="off" value="${searchKeyword}">
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
						</span>
					</div>						
	  			</div>
			</div> 
          	--%>
          
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
	          			<col width="50"/>
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center"></th>
	           				<th class="text-middle text-center">출력자 아이디</th>
	          				<th class="text-middle text-center">발급일자</th>
	          				<th class="text-middle text-center">발급용도</th>
	           				<th class="text-middle text-center">발급자</th>
	            			<th class="text-middle text-center">비고</th>
            			</tr>        				
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="6" class="text-center table-active">조회 가능한 회원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
							<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
								<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
			            		<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초 (E)" var="REG_DT"/>
							
								
								<td class="text-middle text-center">
									<c:choose>
										<c:when test="${item.DEL_ST ne '0'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 이수증 발급내역입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="관리자가 발급열람한 기록은 사용자에게 보여지지 않습니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
								</td>			
								<td class="text-middle text-center">${item.REG_MBR_ID}</td>				
								<td class="text-middle text-center">${REG_DT}</td>
								<td class="text-middle text-center">${item.CRTF_TYPE_ST}</td>				                	
				                <c:choose>
				                	<c:when test="${empty item.MBR_NM}">
				                		<td class="text-middle text-center">비회원 본인</td>
				                	</c:when>
				                	<c:otherwise>
				                		<td class="text-middle text-center">${item.MBR_NM}</td>	
				                	</c:otherwise>
				                </c:choose><td class="text-middle text-center">
			                		<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                	data-mbr-id="${item.MBR_ID}"
				                	data-crtf-sn="${item.CRTF_SN}"
				                	data-crtf-dtl-sn="${item.CRTF_DTL_SN}"
				                	data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
			                	</td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
	 						<td colspan="5">
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
    $(".clk_del_data").click(function() {
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	var data_crtf_sn = $(this).attr('data-crtf-sn');
    	var data_crtf_dtl_sn = $(this).attr('data-crtf-dtl-sn');
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 발급내역 기록 데이터를 완전히 삭제합니다.<br>그래도 계속 하시겠습니까?";
    	} else {
    		alert_message = "발급내역 기록을 삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm2');
    		form.MBR_ID.value = data_mbr_id;
    		form.CRTF_SN.value = data_crtf_sn;
    		form.CRTF_DTL_SN.value = data_crtf_dtl_sn;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/certificate/deleteDtl_act.do",
    			data:$('#listForm2').serialize(),
    			dataType: 'json',
    			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    			async: true,
    			success: function(data, status, xhr) {
    				if(data.errCnt > 0) {
    					//alert(data.msg);
    					alertify.alert(data.msg);
    				} else {
    					if(data.error == '1') {
    						//alert(data.msg);
    						alertify.alert(data.msg);
    					} else {
    						window.location.reload();	
    					}
    				}
    			},
    			beforeSend : function(xhr, opts) {
    				//console.log('before!');
    				if(isClickRequestLocked()) {
    					xhr.abort();
    					return;
    				}
    			},
    			complete : function() {
    				//console.log('complete!');
    				clickRequestLockStop();
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    				clickRequestLockStop();
    			}
    		});    		 
    	});
   	}); 
    </script>
        

<%@ include file="../tail.jsp" %>
