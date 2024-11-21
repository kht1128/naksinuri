<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<form:form commandName="logRecordVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="excel_msg" value="" />
	<input type="hidden" name="excel_type" value="" />
	<input type="hidden" name="excel_label" value="" />
	<input type="hidden" name="ADM_ACCESS_LOG" value="[교육센터관리자-로" />
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
        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down" href="javascript:void(0);" 
        		data-excel-type="/adm/log/listRecEduadm.do"
        		data-excel-label="${fn:trim(pageName)}_엑셀다운로드">
        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드</span>
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
						<div class="col-lg-2 p-0">
							<div class="input-group">
								<input type="text" class="form-control datepickerStrSearch " id="datepickerStrSearch" name="searchStrDate" placeholder="조회기간시작일자" autocomplete="off" value="${searchStrDate}" data-toggle="tooltip" data-original-title="조회시작일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerStrSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerEndSearch " id="datepickerEndSearch" name="searchEndDate" placeholder="조회기간종료일자" autocomplete="off" value="${searchEndDate}" data-toggle="tooltip" data-original-title="조회종료일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerEndSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>	
						<div class="col-lg-2 p-0">

						</div>
		      			<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-4 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control input-auto-enter-key" name="searchKeyword" placeholder="검색어를 입력하세요." autocomplete="off" value="${searchKeyword}" data-toggle="tooltip" data-original-title="로그내용,비고(사유), 아이디, 이름 등을 입력해주세요.">
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
	          			<col width="130px"/>
	          			<col width="130px"/>
	          			<col />
	          			<col width="200px"/>
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center ">No</th>
							<th class="text-middle text-center ">로그일자</th>
							<th class="text-middle text-center ">아이디<br>/요청아이피</th>
							<th class="d-none hide-cell-exceldown">레벨</th>
							<th class="text-middle text-center ">로그내용</th>
							<th class="text-middle text-center ">비고(사유)</th>	
		                	<th class="d-none">로그일자</th>	
		                	<th class="d-none">로그일자</th>
		                	<th class="d-none">로그일자</th>
		                	<th class="d-none">로그일자</th>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="5"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 된 내용이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
		            			<td class="text-middle text-center">${item.REG_DT }</td>
		            			<td class="text-middle text-center">
		            				<c:choose>
		            					<c:when test="${not empty item.MBR_ID }">${item.MBR_NCNM }<br>(${item.MBR_NM })</c:when>
		            					<c:otherwise>비로그인</c:otherwise>
		            				</c:choose> 
		            				<br><span class="grey-400">${item.MBR_IP }</span>
		            			</td>
		            			<td class="d-none">${item.MBR_LV }</td>		            			
		            			<td class="text-middle text-left">${item.LOG_DSCRP }</td>
		            			<td class="text-middle text-left">${item.MBR_MSG }</td>         			
				                <td class="d-none">${item.REG_DT}</td>
				                <td class="d-none">${item.REG_DT}</td>
				                <td class="d-none">${item.REG_DT}</td>
				                <td class="d-none">${item.REG_DT}</td>
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

<style>
.datepicker-dropdown{z-index:99999!important;}
</style>
<script>
$('.input-auto-enter-key').keypress(function(key) {
	if(key.keyCode == 13){
		$('.clk_search_btn').click();
    }
});
$(".clk-clear-input").click(function() {
	var target = $(this).attr('for');
	$('#'+target).val("");
});
$(function(){
	$('.selectpicker_manual').selectpicker();
	$('.datepickerStrSearch').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.datepickerEndSearch').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
});
function pageMove(obj) {
	var form = document.getElementById('listForm');
	form.action = "/eduadm/member/access_log.do";
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
</script>

	<script>
	//엑셀다운로드 
    $(".btn-act-external-excel-down").click( function() {
    	var excel_type = $(this).attr('data-excel-type');
    	var excel_label = $(this).attr('data-excel-label');
    	alertify.prompt('엑셀 다운로드 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			/* alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.</span>',function(){
    				document.listForm.action = "";
    			}); */
		    	var form = document.getElementById('listForm');
		    	form.excel_label.value = excel_label;
		    	form.excel_type.value = excel_type;
		    	form.excel_msg.value = val;
		    	//form.action = "/all/main/excel/down.do";
		    	//form.submit();
		    	
		    	$.ajax({
            		type:"POST",
            		url :'/all/main/excel/chkDown.do',
            		data:$('#listForm').serialize(),
            		//dataType: "html",
            		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
            		async: true,
            		success: function(data, status, xhr) {
            			//console.log('success!');
						//console.log(data);
						if(data.error == 1) {
							alertify.alert(data.msg,function(){
								
							});
						} else {							
							var rstMsg = '엑셀 다운로드 신청이 완료되었습니다.<br>다운로드는 관리자 승인 후  <span class="bg-blue-grey-800 orange-600 font-weight-600" style=""><i class="site-menu-icon fa-print mr-5" aria-hidden="true"></i>출력관리-엑셀다운로드</span> 에서 받으실 수 있습니다.<br><span class="blue-600 font-weight-600">'+data.msg+'</span><br><br><span class="red-600">회원상세정보(부가정보)를 포함하기 때문에 데이터는 총 조회 ${paginationInfo.totalRecordCount}건 보다 많을 수 있습니다.</span>';
	            			alertify.alert(rstMsg,function(){
	            				$.ajax({
	                        		type:"POST",
	                        		url :'/all/main/excel/down.do',
	                        		data:$('#listForm').serialize(),
	                        		//dataType: "html",
	                        		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	                        		async: true,
	                        		success: function(data, status, xhr) {
	                        			//console.log('success!');
	            						//console.log(data);            			
	                        		},
	                        		beforeSend : function(xhr, opts) {
	                        			//console.log('before!');
	                        			if(isClickRequestLocked()) {
	                        				xhr.abort();
	                        				return;
	                        			}
	                        			//$('.btn-act-external-excel-down').addClass('disabled');
	                        		},
	                        		complete : function() {
	                        			//console.log('complete!');
	                        			//$('.btn-act-external-excel-down').removeClass('disabled');
	                        			clickRequestLockStop();
	                        	    },
	                        		error: function(jqXHR, textStatus, errorThrown) {
	                        			//console.log(errorThrown);
	                        			//console.log('error!');
	                        			//$('.btn-act-external-excel-down').removeClass('disabled');
	                        			clickRequestLockStop();
	                        		}
	                        	});
	            			});
						}
            		},
            		beforeSend : function() {
            			//console.log('before!');
            			//$('.btn-act-external-excel-down').addClass('disabled');
            		},
            		complete : function() {
            			//console.log('complete!');
            			//$('.btn-act-external-excel-down').removeClass('disabled');
            	    },
            		error: function(jqXHR, textStatus, errorThrown) {
            			//console.log(errorThrown);
            			//console.log('error!');
            			//$('.btn-act-external-excel-down').removeClass('disabled');
            		}
            	});
		    	
		    	
    		} ,function() { 
    		//cancel    			
    	});
    });
    </script>

<%@ include file="../tail.jsp" %>