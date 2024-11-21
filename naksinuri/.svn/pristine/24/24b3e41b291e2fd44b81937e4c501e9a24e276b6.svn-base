<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<c:set var="isLock" value="false"/>
<%--
관리자는 잠겨도 등록이 가능해야 겠죵
<c:if test="${parentInfo.LOCK_ST eq 1}">
	<c:set var="isLock" value="true"/>
</c:if>
 --%>
 
<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="MBR_ID" name="MBR_ID" value="${parentInfo2.MBR_ID}"/>
<input type="hidden" id="MBR_NM" name="MBR_NM" value="${parentInfo2.MBR_NM}"/>
</form:form>
	
<form:form commandName="eduMyHistoryVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo2.CRS_SN}"/>
<input type="hidden" id="CRS_DTL_SN" name="CRS_DTL_SN" value="${parentInfo2.CRS_DTL_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo2.HMBR_SN}"/>
<input type="hidden" id="HMBR_DTL_SN" name="HMBR_DTL_SN" value=""/>
<input type="hidden" id="TRN_SN" name="TRN_SN" value=""/>
<input type="hidden" id="MBR_ID" name="MBR_ID" value="${parentInfo2.MBR_ID}"/>
	
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
		        	<li class="breadcrumb-item active">
		        		<a href="javascript:void(0)" onclick="pageMovePrev()" >이전 목록으로 돌아가기</a>
		        	</li>
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
          
          	<c:if test="${not empty parentInfo}">

				<c:if test="${parentInfo.DEL_ST eq '1'}">
					<div role="alert" class="alert dark alert-danger alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
		      			<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>현재 삭제 된 상태의 교육과정입니다.</p>
					</div>
				</c:if>			
				
	          	<div class="panel">
	          		
	          		<fmt:parseDate value="${fn:replace(parentInfo.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
	           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E)" var="CRS_STR_DT"/>
	           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E)" var="CRS_END_DT"/>
	           		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_STR_DT"/>
	           		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_END_DT"/>
	           		           	
					<!-- 교육분류에 따른 표기 처리 : start -->
            		<c:choose>
						<c:when test="${parentInfo.CRS_TYPE eq 'fshsk_trnng'}">
							<c:set var="label_crs_time" value="개월" />
							<c:set var="is_allow_online" value="0" />
						</c:when>
						<c:otherwise>
							<c:set var="label_crs_time" value="시간" />
							<c:set var="is_allow_online" value="1" />
						</c:otherwise>											
					</c:choose>
					<!-- 교육분류에 따른 표기 처리 : end -->	           		           	
	           		           		
					<div class="panel-heading">
		    			<h3 class="panel-title">${parentInfo.CRS_NM}&nbsp;&nbsp;
		      				<small>${parentInfo.CAT_INS_NM}</small>
		    			</h3>
		  			</div>
		  			<div class="panel-body">
		    			<p>
			    			교육기간 : ${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}
			    			<br>
			    			모집기간 : ${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT}
						</p>						
		  			</div>
				</div> 
				
				<div class="panel panel-bordered panel-primary">	           		           		
					<div class="panel-heading">
		    			<h3 class="panel-title white">${parentInfo2.MBR_NM}&nbsp;&nbsp;
		      				<small class="blue-200">님의&nbsp;&nbsp;교과목 이수정보</small>
		    			</h3>
		  			</div>
		  			<div class="panel-body">
		  				<p>연락처 : ${parentInfo2.MBR_HP}</p>
		  			</div>
				</div> 
				
				<div class="row">
	        		<div class="col-xl-6 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
								<div class="contextual-progress">
		                     		<div class="clearfix">
		                       			<div class="progress-title">온라인교육 이수완료(건)</div>
		                       			<div class="progress-label"></div>
		                     		</div>
		                     		<div class="progress" data-labeltype="steps" data-totalsteps="${paginationInfo.totalRecordCount}" data-goal="80" 
		                     			data-plugin="progress">
		                       			<div class="progress-bar" aria-valuemin="0" aria-valuemax="100" style="width: 50%"
		                         			aria-valuenow="${parentInfo2.LRNNG_CMPLT_CNT/paginationInfo.totalRecordCount*100}" role="progressbar">
		                         			<span class="progress-label"></span>
		                       			</div>
		                     		</div>
		                   		</div>
	            			</div>
	          			</div>
	        		</div>	        		
	        		<div class="col-xl-6 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
                				<div class="contextual-progress">
	                        		<div class="clearfix">
	                          			<div class="progress-title">온라인교육 학습진행률(%)</div>
	                          			<div class="progress-label"></div>
	                        		</div>
	                        		<div class="progress" data-labeltype="percentage" data-goal="-40" data-plugin="progress">
		                          		<div class="progress-bar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="${parentInfo2.LRNNG_PRGRS*100}"
		                            		role="progressbar">
		                            		<span class="progress-label"></span>
		                          		</div>
	                        		</div>
	                      		</div>
	            			</div>
	          			</div>
	        		</div>	        		
				</div>
				
				
				<div class="row">
					<div class="col-xl-6 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">총 이수된 온라인 교육시간</div>
           							<div class="counter-number-group">
             							<span class="counter-number">${parentInfo2.LRNNG_TOT_TIME}</span>
             							<span class="counter-number-related">시간&nbsp;<span class="blue-grey-400 font-size-14">/ 총 ${parentInfo2.HMBR_MAX_TIME - parentInfo.CRS_TOT_TIME} 시간</span></span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>	        		
	        		<div class="col-xl-6 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">총 이수된 온라인 교육점수</div>
           							<div class="counter-number-group">
             							<span class="counter-number">${parentInfo2.LRNNG_TOT_POINT}</span>
             							<span class="counter-number-related">점&nbsp;<span class="blue-grey-400 font-size-14">/ 총 ${parentInfo2.HMBR_MAX_POINT - parentInfo.CRS_TOT_POINT} 점</span></span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>
	        	</div>
	        	
			</c:if>         
          
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
							<th class="text-middle text-center">교과목명</th>
							<th class="text-middle text-center">자료정보<br>(자료구분)</th>
							<th class="text-middle text-center">나의이수시간<br>총 교육시간</th>
							<th class="text-middle text-center">나의이수점수<br>총 교육점수</th>
							<th class="text-middle text-center">학습진행률</th>
							<th class="text-middle text-center">이수상태</th>
		                	<th class="text-middle text-center">관리</th>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="7" class="text-center table-active">이수 가능한 교과목이 없습니다.</td></tr> 
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${empty item.MBR_NM or item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
					                ${item.CRS_DTL_NM}
				                </td>
				                <td class="text-middle">${item.TRN_NM}<br>(${item.TRN_TYPE_ST})</td>
				                <td class="text-middle text-center">
				                	<span class="badge badge-outline badge-dark">${item.LRNNG_CUR_TIME} 시간 이수</span>
				                	<br><span class="badge badge-outline badge-dark">총 ${item.LRNNG_MAX_TIME} 시간 중</span>
				                </td>
				                <td class="text-middle text-center">
				                	<span class="badge badge-outline badge-dark">${item.LRNNG_CUR_POINT} 점 이수</span>
				                	<br><span class="badge badge-outline badge-dark">총 ${item.LRNNG_MAX_POINT} 점 중</span>
				                </td>
				                <td class="text-middle text-center">
				                	<fmt:formatNumber type="percent" value="${item.LRNNG_PRGRS}"  pattern="0.00%"/>
				                </td>
				                <td class="text-middle text-center">		                 			
				                	<c:choose>
				                		<c:when test="${item.LRNNG_CMPLT_DTL_ST eq 1}">이수완료</c:when>
				                		<c:otherwise>수강중</c:otherwise>				                	
				                	</c:choose>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${isLock eq false}">
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-hmbr-dtl-sn="${item.HMBR_DTL_SN}"
					                	data-trn-sn="${item.TRN_SN}"
					                	data-linkurl="/eduadm/mbrhstry/modifyDtl.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
				                	</c:if>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
	 						<c:choose>
								<c:when test="${is_allow_online eq '1'}">
									<td colspan="10">
								</c:when>
								<c:otherwise>
									<td colspan="7">
								</c:otherwise>											
							</c:choose>
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
    function pageMovePrev() {
    	var form = document.getElementById('listForm2');
    	form.action = "/eduadm/member/listDtl.do";
    	form.submit();
    }
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.HMBR_DTL_SN.value = '';
    	document.listForm.TRN_SN.value = '';
    	document.listForm.action = "";
       	document.listForm.submit();
    }
    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.HMBR_DTL_SN.value = $(this).attr('data-hmbr-dtl-sn');
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
    </script>
        

<%@ include file="../tail.jsp" %>
