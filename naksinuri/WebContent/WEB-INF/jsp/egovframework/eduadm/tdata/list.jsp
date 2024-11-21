<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

	
<form:form commandName="eduTrainingDataVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="TRN_SN" name="TRN_SN" value=""/>
	
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
        	<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
        	data-trn-sn=""
        	data-linkurl="/eduadm/tdata/write.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">교육자료 등록하기</span>
        	</a> 
        </div>
      </div>

      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
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
	          		</colgroup>
	          		<thead>
			            <tr>
							<th class="text-middle text-center">자료타입</th>
		                	<th class="text-middle text-center">자료명</th>
		                	<th class="text-middle text-center">교육카테고리(상)<br>교육카테고리(하)</th>
		                	<th class="text-middle text-center">최종수정일<br>마지막수정자</th>
		                 	<th class="text-middle text-center">관리</th>
        				</tr>
	             	</thead>
					<tbody>
						<c:if test="${empty trn_data_list}">
		            		<tr><td colspan="7" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${trn_data_list}">
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">${item.TRN_TYPE_ST}</td>
				                <td class="text-middle">
									<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 교육자료입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 교육자료입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
					                ${item.TRN_NM}
				                </td>
				                <td class="text-middle text-center">${item.CAT_NM}&nbsp;/&nbsp;<br>
				                	<c:if test="${item.LRNNG_GB eq 'online'}"><i class="site-menu-icon wb-desktop" aria-hidden="true" data-content="온라인 교육용 카테고리 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i></c:if>
				                	${item.CAT_DTL_NM}
				                </td>
				                <td class="text-middle text-center">${item.UPD_DT}<br>(${item.UPD_MBR_ID})</td>
				                <td class="text-middle text-center">
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
				                	data-trn-sn="${item.TRN_SN}"
				                	data-linkurl="/eduadm/tdata/modify.do">
				                		<i class="icon wb-wrench" aria-hidden="true"></i>
				                	</a>
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                	data-trn-sn="${item.TRN_SN}"
				                	data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
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
      </div>
    </div>
    <!-- End Page -->
</form:form>

    <script>
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "<c:url value='/eduadm/tdata/list.do'/>";
       	document.listForm.submit();
    }
    
    $(".clk_add_data,.clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.TRN_SN.value = $(this).attr('data-trn-sn');
    	form.action = '';
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#listForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#seaAdmEduPublicModal").html(data);
				$("#seaAdmEduPublicModal").modal('show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});   
    $(".clk_del_data").click(function() {
    	var data_trn_sn = $(this).attr('data-trn-sn');
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 데이터를 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
	    	form.TRN_SN.value = data_trn_sn;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/tdata/delete_act.do",
    			data:$('#listForm').serialize(),
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
