<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>
	.hide {display: none;}
</style>

<form:form commandName="eduExcelUploadVO" id="dtlListForm" name="dtlListForm" method="post">
	<input type="hidden" name="EU_SN" value="">
	<input type="hidden" name="MBR_REG_TYPE_CD">
</form:form>

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
          		
	            <!-- Panel Editing Rows -->
	            <div class="panel hide" id="excel_dtl_list">
	              	<div class="panel-body" id="excel_upload_list_ajax_body"></div>              
            	</div>
            	<!-- Panel Editing Rows: end -->
            	
            	 <!-- Panel Editing Rows -->
	            <div class="panel" id="excel_list">
	              	<div class="panel-body">
	              		<div class="example-tooltip">
							<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
								<div class="arrow"></div>
								<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount}건</div>
							</div>
						</div>
						<!-- table:start -->
						<table id="datalist" class="table table-hover footable footable-paging footable-paging-center">
							<colgroup>
								<col width="7%"/>
								<col width="7%"/>
								<col width="7%"/>
								<col width=""/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="7%"/>
								<col width="7%"/>
								<col width="5%"/>
							</colgroup>
						   	<thead>
						   		<tr>
						   			<th class="text-middle text-center">구분</th>
									<th class="text-middle text-center">시도</th>
									<th class="text-middle text-center">시군</th>							   		
									<th class="text-middle text-center">파일이름</th>
									<th class="text-middle text-center">업로드<br>아이디</th>
									<th class="text-middle text-center">승인신청일자</th>
									<th class="text-middle text-center">승인일자</th>
									<th class="text-middle text-center">총<br>신청자 수</th>
									<th class="text-middle text-center">승인된<br>신청자 수</th>									
									<th class="text-middle text-center">관리</th>	
						       	</tr>       				
						   	</thead>
							<tbody>
								<c:if test="${empty upload_list}">
									<tr><td colspan="10" class="text-center table-active">현재 업로드한 지자체명단이 없습니다.</td></tr>	  
						       	</c:if>
						       	
						       	<c:forEach var="item" varStatus="status" items="${upload_list}">
						       		<fmt:parseDate value="${fn:replace(item.CONFM_REG_DT, '.0', '')}" var="parse_confm_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						       		<fmt:formatDate value="${parse_confm_reg_dt}" pattern="yyyy-MM-dd hh:mm:ss" var="confm_reg_dt"/>
						       		<fmt:parseDate value="${fn:replace(item.CONFM_UPD_DT, '.0', '')}" var="parse_confm_upd_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						       		<fmt:formatDate value="${parse_confm_upd_dt}" pattern="yyyy-MM-dd hh:mm:ss" var="confm_upd_dt"/>
									<tr>
										<td class="text-middle text-center">
											<c:if test="${item.EU_DTL_ST eq '0'}">낚시터</c:if>
											<c:if test="${item.EU_DTL_ST eq '1'}">낚시어선</c:if>
											<c:if test="${item.EU_DTL_ST eq '2'}">낚시터, 낚시어선</c:if>
										</td>
										<td class="text-middle text-center">${item.SIDO_NM }</td>
										<td class="text-middle text-center">${item.SIGNGU_NM }</td>
						       			<td class="text-middle text-center">
						              		<a href="javascript:void(0)" class="excel_upload_view" data-eu-sn="${item.EU_SN }" data-mbr-reg-type-cd="${MASTER_MBR_POSITION_CD }">${item.ORIGNL_FILE_NM }</a>
						              	</td>
						              	<td class="text-middle text-center">${item.REG_MBR_ID }</td>
						              	<td class="text-middle text-center">${confm_reg_dt }</td>
						              	<td class="text-middle text-center">${confm_upd_dt }</td>
						              	<td class="text-middle text-center">${item.MBR_CNT}</td>
						              	<td class="text-middle text-center">${item.APRV_CNT}</td>						              	
						              	<td class="text-middle text-center">
						              		<%-- <c:if test="${MASTER_MBR_POSITION_CD eq 'PCD0007'}">
						              			<button class="btn btn-outline">승인</button>
						              		</c:if> --%>
						              		<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" 
						              		data-toggle="tooltip" data-original-title="삭제" data-eu-sn="${item.EU_SN}">
						                		<i class="icon wb-trash" aria-hidden="true"></i>
						                	</a>
						              	</td>
						       		</tr>
						       	</c:forEach>
							</tbody>
							<tfoot>
							<form:form commandName="eduExcelUploadVO" name="eduExcelUploadVOPageForm" id="eduExcelUploadVOPageForm" method="post">
								<tr class="footable-paging">
			 						<td colspan="10">
			 							<div class="footable-pagination-wrapper">
			 								<ul class="pagination">
			 									<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page" />
							        			<form:hidden path="pageIndex" />
			 								</ul>	 								
			 							</div>
			 						</td>
			 					</tr>
			 				</form:form>
			 				</tfoot> 
						</table>
						<!-- table:end -->
	              	</div>              
            	</div>
            	<!-- Panel Editing Rows: end -->
						            	
          	</div>
		</div>
	</div>
</div>
<!-- End Page -->

<script>
function fn_egov_link_page(pageNo){
	document.eduExcelUploadVOPageForm.pageIndex.value = pageNo;
	document.eduExcelUploadVOPageForm.action = "/eduadm/member/loc_gov_adm_upload.do";
   	document.eduExcelUploadVOPageForm.submit();
} 

$(".excel_upload_view").on("click", function(){
	
	$("#excel_list").addClass("hide");
	$("#excel_dtl_list").removeClass("hide");
	
	var mbr_reg_type_cd = $(this).attr('data-mbr-reg-type-cd');
	
	var form = document.getElementById('dtlListForm');
	form.EU_SN.value = $(this).attr("data-eu-sn");
	form.MBR_REG_TYPE_CD.value = mbr_reg_type_cd;
	
	$.ajax({
		type:"POST",
		url :"/eduadm/member/loc_gov_adm_view.do",
		data:$('#dtlListForm').serialize(),
		dataType: "html",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		beforeSend: function(xhr){
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$("#excel_upload_list_ajax_body").html(ajaxLoadingHtmlTag());
		}
	})
	.done(function(data, status, xhr){
		//console.log(data);
		$("#excel_upload_list_ajax_body").html(data);
	})
	.always(function(){
		//console.log("always");
		clickRequestLockStop();
	})
	.fail(function(jqXHR, textStatus, errorThrown){
		//console.log("error");
		$("#excel_upload_list_ajax_body").html(ajaxLoadingFailHtmlTag("에러가 발생했습니다. 잠시후 다시 시도해주세요."));
		clickRequestLockStop();
	});
	
});


$("#excelUpload").on("click", function(){
	
	var form = $("#fileForm")[0];
	var formData = new FormData(form);
	
	var file_nm = $("code").text();
	var last_dot = file_nm.lastIndexOf('.')+1;
	var extension = file_nm.substring(last_dot, file_nm.length).toLowerCase();
	
	if(extension != "xlsx" && extension != "xls" && extension != "csv") {
		alertify.alert("엑셀파일을 선택해주세요");
		return;
	}
	
	var rstMsg = '엑셀 업로드를 시작합니다.<br>';
	alertify.alert(rstMsg,function(){
	
		$.ajax({
	   		type:"POST",
	   		url :"/eduadm/member/loc_gov_upload_act.do",
	   		enctype: 'multipart/form-data',
	   		data:formData,
	   		processData: false, 
	   		contentType: false,
	   		async: true,
	   		beforeSend: function(xhr){
	   			if(isClickRequestLocked()) {
					xhr.abort();
					return;
				}
	   			$("#excel_list").html(ajaxLoadingHtmlTag());
			}
		})
		.done(function(data, status, xhr){
			
			if(data.error == '0'){
				alertify.alert(data.msg, function(){
					location.reload();
				});
			} else {
				alertify.alert(data.msg);
			}
		})
		.always(function(){
			clickRequestLockStop();
		})
		.fail(function(jqXHR, textStatus, errorThrown){
			$("#excel_list").html(ajaxLoadingFailHtmlTag("다시 시도해 주세요."));
			clickRequestLockStop();
		});
	});	
	
});

$(".clk_del_data").click(function() {
	
	var eu_sn = $(this).attr('data-eu-sn');
	
	alertify.confirm("삭제 하시겠습니까?", function(){ 
		//ok
		var form = document.getElementById('dtlListForm');
    	form.EU_SN.value = eu_sn;
    	form.action = '';
    	
    	$.ajax({
			type: "POST",
			url : "/eduadm/member/loc_gov_delete_act.do",
			data: $('#dtlListForm').serialize(),
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				
				if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					alertify.alert(data.msg, function(){
						location.reload();
					});
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

