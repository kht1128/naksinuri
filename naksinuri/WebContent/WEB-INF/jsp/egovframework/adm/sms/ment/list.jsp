<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../../seadm/head.jsp" %> 
		<%@ include file="../../../seadm/left_menu.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../../eduadm/head.jsp" %> 
		<%@ include file="../../../eduadm/left_menu.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../../head.jsp" %> 
		<%@ include file="../../left_menu.jsp" %>
	</c:otherwise>
</c:choose>

<form:form commandName="smsMentVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="SMS_MENT_SN" value="" />
</form:form>


<form:form commandName="smsMentVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchCondition" value="Y" />
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
        	data-title="신규 메세지 탬플릿 추가하기" 
        	data-linkurl="/adm/sms/ment/write.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">신규작성</span>
        	</a>
        </div>
      </div>
      
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
          
          	<!-- 검색폼 -->
          	<div class="panel mb-20">
	  			<div class="panel-body">
	  			
	  				<div class="input-group col-lg-12 p-0">
						<div class="col-lg-6 p-0">

						</div>
		      			<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control " name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-4 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control" name="searchKeyword" placeholder="제목 또는 내용을 입력하세요." autocomplete="off" value="${searchKeyword}">
								<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
							</div>
						</div>
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
	          			<col />	          			
						<col width="120px"/>
						<col width="120px"/>
						<col width="20%"/>
	          			<col width="40%"/>
	          			<col width="150px"/>
	          			<col width="180px"/>	          			
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center ">No</th>
	          				<th class="text-middle text-center ">메세지 구분</th>
	          				<th class="text-middle text-center ">메세지 대상</th>
							<th class="text-middle text-center ">제목</th>
							<th class="text-middle text-center ">내용</th>
							<th class="text-middle text-center ">작성정보<br>(마지막수정)</th>
							<th class="text-middle text-center ">비고</th>	
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="7"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 된 내용이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            		<c:set var="cellnum" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/>	            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">${cellnum }</td>
		            			<td class="text-middle text-center">${item.SMS_MENT_TYPE }</td>
		            			<td class="text-middle text-center">
		            				<c:forEach var="item_category" items="${list_target_se_cd}">
										<c:if test="${item_category.CD_ID eq item.SMS_MENT_DTL_CD }">${item_category.CD_NM}</c:if>
									</c:forEach>
		            			</td>
		            			<td class="text-middle text-center">
		            				<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash red-600" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									<%--
									<c:if test="${item.USE_ST eq '0'}">
										<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									 --%>
		            				${item.SMS_MENT_TITLE }
		            			</td>
		            			<td class="text-middle text-center">
		            				<c:out value="${fn:substring(item.SMS_MENT_CONT,0,60)}"/><c:if test="${fn:length(item.SMS_MENT_CONT) > 61 }">...</c:if>
		            			</td>
		            			<td class="text-middle text-center">${item.UPD_DT }<br><span class="grey-400">${item.UPD_MBR_ID }</span></td>
		            			<td class="text-middle text-center">
		            				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-sms-ment-sn="${item.SMS_MENT_SN}">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                		data-sms-ment-sn="${item.SMS_MENT_SN}" 
	                  					data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>                 				
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
function pageMove(obj) {
	var form = document.getElementById('listForm');
	form.action = "/adm/sms/listMent{addWebLink}.do";
	form.submit();
}
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
$(".clk_add_data").click(function() {
	var data_linkurl = $(this).attr('data-linkurl');
	$.ajax({
		type:"POST",
		url :data_linkurl,
		data:null,
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#admPublicModal").html(data);
			$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
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
	var sms_ment_sn = $(this).attr('data-sms-ment-sn');
	var del_st = $(this).attr('data-del-st');
	var alert_message = "";
	if(del_st == '1') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	//
	alertify.confirm(alert_message, function(){ 
	  	//ok
		var form = document.getElementById('viewForm');
		form.SMS_MENT_SN.value = sms_ment_sn;
    	form.action = '';
    	$.ajax({
			type:"POST",
			url :"/adm/sms/ment/delete_act.do",
			data:$('#viewForm').serialize(),
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
						//window.location.reload();
						document.listForm.submit();
					}
				}
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	});
});	
$(".clk_mod_data").click(function() {
	var sms_ment_sn = $(this).attr('data-sms-ment-sn');
	var form = document.getElementById('viewForm');
	form.SMS_MENT_SN.value = sms_ment_sn;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/adm/sms/ment/modify.do",
		data:$('#viewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#admPublicModal").html(data);
			$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}); 
</script>


<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../../seadm/tail.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../../eduadm/tail.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../../tail.jsp" %>
	</c:otherwise>
</c:choose>

