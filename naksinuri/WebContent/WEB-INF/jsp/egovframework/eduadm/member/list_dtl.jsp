<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>


<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
<input type="hidden" id="MBR_ID" name="MBR_ID" value="${member.MBR_ID}"/>
<input type="hidden" id="MBR_NM" name="MBR_NM" value="${member.MBR_NM}"/>
</form:form>

	
<form:form commandName="eduMyHistoryVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="MBR_ID" name="MBR_ID" value="${member.MBR_ID}"/>

    <!-- Page -->
    <div class="page">
      <div class="page-header">
        <c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${member.MBR_NM} 님의 ${subpageName}</h1>
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
			            	<th class="text-middle text-center">교육분류</th>
							<th class="text-middle text-center">교육과정명</th>
							<th class="text-middle text-center">교육기관명</th>
		                	<th class="text-middle text-center">교육기간<br>(모집기간)</th>
		                	<th class="text-middle text-center">상태</th>
		                	<th class="text-middle text-center">교육정보</th>		                	
		                	<th class="text-middle text-center">현재상태<br>(종합)</th>							
							<th class="text-middle text-center">관리</th>
        				</tr>
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
		            		<tr><td colspan="8" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            	
		            		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E)" var="CRS_STR_DT"/>
		            		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E)" var="CRS_END_DT"/>
		            		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_STR_DT"/>
		            		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_END_DT"/>
		            		
		            		<!-- 교육분류에 따른 표기 처리 : start -->
		            		<c:choose>
								<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">
									<c:set var="label_crs_time" value="개월" />
									<c:set var="is_allow_online" value="0" />
								</c:when>
								<c:otherwise>
									<c:set var="label_crs_time" value="시간" />
									<c:set var="is_allow_online" value="1" />
								</c:otherwise>											
							</c:choose>
							<!-- 교육분류에 따른 표기 처리 : end -->
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle">
		            				<c:choose>
										<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">귀어창업기술교육</c:when>
										<c:when test="${item.CRS_TYPE eq 'wknd_trnng'}">주말교육</c:when>
										<c:otherwise>종합교육</c:otherwise>											
									</c:choose>
		            			</td>
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 대기중입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 승인취소 대기중입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
				               		<c:if test="${item.LOCK_ST eq '1' or item.CRS_ST eq '2'}">
										<i class="site-menu-icon wb-lock" aria-hidden="true" data-content="현재 모집이 중단되었습니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
					                ${item.CRS_NM}
				                </td>
				                <td class="text-middle text-center">${item.CAT_INS_NM}</td>
				                <td class="text-middle text-center">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}<br>(${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT})</td>
				                <td class="text-middle text-center">
				                	<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											<span class="badge badge-danger">삭제대기중</span>
										</c:when>
										<c:otherwise>										
											<span class="badge badge-outline badge-dark">
							                	<c:choose>
							                		<c:when test="${item.PURCHASE_CMPLT_ST eq 1}">결제완료</c:when>
							                		<c:when test="${item.PURCHASE_CMPLT_ST eq 2}">결제중</c:when>
							                		<c:otherwise>결제대기</c:otherwise>				                	
							                	</c:choose>		                 			
					                		</span>	
											<br>
											<span class="badge badge-outline badge-dark">
							                	<c:choose>
							                		<c:when test="${item.LRNNG_ST eq 1}">신청승인(완료)</c:when>
							                		<c:when test="${item.LRNNG_ST eq 2 or item.LRNNG_ST eq 3}">신청취소</c:when>
							                		<c:when test="${item.LRNNG_ST eq 4}">신청보류</c:when>
							                		<c:otherwise>신청대기</c:otherwise>				                	
							                	</c:choose>
					                		</span>					                		
											<c:if test="${item.LRNNG_ST eq 1}">
												<br>
												<span class="badge badge-outline badge-dark">
								                	<c:choose>
								                		<c:when test="${item.LRNNG_CMPLT_ST eq 1 or item.LRNNG_CMPLT_ST eq 3}">수강(이수)완료</c:when>
								                		<c:when test="${item.LRNNG_CMPLT_ST eq 2}">수강(이수)취소</c:when>
								                		<c:when test="${item.LRNNG_CMPLT_ST eq 4}">수강(이수)취소</c:when>
								                		<c:otherwise>현재수강중</c:otherwise>				                	
								                	</c:choose>
							                	</span>
											</c:if>		                	
											<br>
											<c:choose>
						                		<c:when test="${item.CRS_ST eq '2'}"><span class="badge badge-dark">교육신청받지않음</span></c:when>
						                		<c:otherwise>
						                			<c:choose>
								                		<c:when test="${item.LOCK_ST eq 0}"><span class="badge badge-primary">신청접수중</span></c:when>
								                		<c:otherwise><span class="badge badge-danger">교육신청마감</span></c:otherwise>				                	
								                	</c:choose>	
						                		</c:otherwise>				                	
						                	</c:choose>							                	
										</c:otherwise>											
									</c:choose>           	
				                </td>
				                <td class="text-middle text-center">
				                	<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_TIME + item.SUM_TOT_TIME} ${label_crs_time} 교육</span>
				                	<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_POINT + item.SUM_TOT_POINT} 점 이수</span>
				                </td>
				                <td class="text-middle text-center">
				                	<span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_TIME} ${label_crs_time}</span>
				                	<br><span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_POINT} 점</span>
				                </td>
				                <td class="text-middle text-center">
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
				                	data-crs-sn="${item.CRS_SN}" 
				                	data-hmbr-sn="${item.HMBR_SN}"
				                	data-linkurl="/eduadm/member/modifyDtl.do">
				                		<i class="icon wb-wrench" aria-hidden="true"></i>
				                	</a>
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-hmbr-sn="${item.HMBR_SN}"
					                	data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
					                </a>
									<c:if test="${is_allow_online eq '1'}">
				                		<br><button type="button" class="btn btn-outline btn-success btn-xs" onclick="pageMoveDtl(this)" 
				                		data-crs-sn="${item.CRS_SN}"
				                		data-hmbr-sn="${item.HMBR_SN}"  
				                		>온라인교과목 관리 이동</button>
				                	</c:if>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
	 						<td colspan="8">
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
    function pageMoveDtl(obj) {
    	var form = document.getElementById('listForm2');
    	form.CRS_SN.value = $(obj).attr('data-crs-sn');
    	form.HMBR_SN.value = $(obj).attr('data-hmbr-sn');
    	form.action = "/eduadm/member/listMbrDtl.do";
    	form.submit();
    }
    function pageMovePrev() {
    	var form = document.getElementById('listForm2');
    	form.CRS_SN.value = '';
    	form.HMBR_SN.value = '';
    	form.action = "/eduadm/member/listDtl.do";
    	form.submit();
    }
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "";
       	document.listForm.submit();
    }
    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('listForm2');
    	form.CRS_SN.value = $(this).attr('data-crs-sn');
    	form.HMBR_SN.value = $(this).attr('data-hmbr-sn');
    	form.action = '';
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#listForm2').serialize(),
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
    	var data_crs_sn = $(this).attr('data-crs-sn');
    	var data_hmbr_sn = $(this).attr('data-hmbr-sn');
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 데이터를 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm2');
    		form.CRS_SN.value = data_crs_sn;
        	form.HMBR_SN.value = data_hmbr_sn;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/member/deleteDtl_act.do",
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
