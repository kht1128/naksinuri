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
<c:if test="${parentInfo.MBR_CUR_CNT > 0}">
<c:set var="isLock" value="true"/>
</c:if>

	
<form:form commandName="eduCurriculumVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="CRS_DTL_SN" name="CRS_DTL_SN" value=""/>
<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	
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
        	<c:if test="${isLock eq false}">
        	<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
        	data-crs-sn="${parentInfo.CRS_SN}" 
        	data-crs-dtl-sn=""
        	data-linkurl="/eduadm/curriculum/writeDtl.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">새로운 교과목 추가하기</span>
        	</a> 
        	</c:if>
        </div>
      </div>
      
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
			
			<c:if test="${isLock eq true}">
				<div role="alert" class="alert dark alert-warning alert-dismissible">
					<button aria-label="Close" data-dismiss="alert" class="close" type="button">
						<span aria-hidden="true">×</span>
					</button>
	      			<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
					<p>현재 수강인원이 1명 이상이므로 교과목을 추가하거나 수정 하실 수 없습니다.<br>교육목록 페이지에 있는 안내 문구를 확인해주세요.</p>
				</div>
			</c:if>
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
  		
  			<div class="row">
  				<div class="col-xl-3 col-md-6 info-panel">
					<div class="card card-shadow">
						<div class="card-block bg-white p-20">
              				<div class="counter text-left">
          							<div class="counter-label">상태</div>
          							<div class="counter-number-group">
            							<span class="counter-number font-size-16">
            								<c:choose>
											<c:when test="${parentInfo.DEL_ST eq '1'}">
												<i class="icon wb-trash red-600" aria-hidden="true" data-toggle="tooltip" data-original-title="삭제"></i>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${parentInfo.CRS_ST eq '1'}"><span class="badge badge-outline badge-primary">승인완료</span></c:when>
													<c:otherwise><span class="badge badge-outline badge-dark">승인대기</span></c:otherwise>											
												</c:choose>
												<c:choose>
													<c:when test="${parentInfo.CRS_ST eq '0'}"><span class="badge badge-dark">신청대기중</span></c:when>
													<c:otherwise>
														<c:choose>
									                		<c:when test="${parentInfo.CRS_ST eq '2'}"><span class="badge badge-dark">신청받지않음</span></c:when>
									                		<c:otherwise>
									                			<c:choose>
											                		<c:when test="${parentInfo.LOCK_ST eq 0}"><span class="badge badge-primary">신청접수중</span></c:when>
											                		<c:otherwise><span class="badge badge-danger">신청마감</span></c:otherwise>				                	
											                	</c:choose>	
									                		</c:otherwise>				                	
									                	</c:choose>										
													</c:otherwise>
												</c:choose>
											</c:otherwise>											
										</c:choose>    
            							</span>
          							</div>
        						</div>
            			</div>
          			</div>
        		</div>	        		
				<div class="col-xl-3 col-md-6 info-panel">
					<div class="card card-shadow">
						<div class="card-block bg-white p-20">
              				<div class="counter text-left">
          							<div class="counter-label">제한인원(최대)</div>
          							<div class="counter-number-group">
            							<span class="counter-number">${parentInfo.MBR_MAX_CNT}</span>
            							<span class="counter-number-related">명</span>
          							</div>
        						</div>
            			</div>
          			</div>
        		</div>
        		<div class="col-xl-3 col-md-6 info-panel">
					<div class="card card-shadow">
						<div class="card-block bg-white p-20">
              				<div class="counter text-left">
          							<div class="counter-label">수강신청인원</div>
          							<div class="counter-number-group">
            							<span class="counter-number">${parentInfo.MBR_CUR_CNT}</span>
            							<span class="counter-number-related">명</span>
          							</div>
        						</div>
            			</div>
          			</div>
        		</div>
        		<div class="col-xl-3 col-md-6 info-panel">
					<div class="card card-shadow">
						<div class="card-block bg-white p-20">
              				<div class="counter text-left">
          							<div class="counter-label">이수완료인원</div>
          							<div class="counter-number-group">
            							<span class="counter-number">${parentInfo.MBR_CMPLT_CNT}</span>
            							<span class="counter-number-related">명</span>
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
          							<div class="counter-label">총 온라인 교육시간</div>
          							<div class="counter-number-group">
            							<span class="counter-number">${parentInfo.SUM_TOT_TIME}</span>
            							<span class="counter-number-related">시간</span>
          							</div>
        						</div>
            			</div>
          			</div>
        		</div>	        		
        		<div class="col-xl-6 col-md-6 info-panel">
					<div class="card card-shadow">
						<div class="card-block bg-white p-20">
              				<div class="counter text-left">
          							<div class="counter-label">총 온라인 교육점수</div>
          							<div class="counter-number-group">
            							<span class="counter-number">${parentInfo.SUM_TOT_POINT}</span>
            							<span class="counter-number-related">점</span>
          							</div>
        						</div>
            			</div>
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
	          		</colgroup>
	          		<thead>
			            <tr>
							<th class="text-middle text-center">교과목명</th>
							<th class="text-middle text-center">교육카테고리(상)<br>교육카테고리(하)</th>
							<th class="text-middle text-center">교육자료목록</th>
		                	<th class="text-middle text-center">상태</th>
		                	<th class="text-middle text-center">관리</th>
        				</tr>
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
		            		<tr><td colspan="5" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            	
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 교과목입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 교과목입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
				               		<c:if test="${item.LOCK_ST eq '1'}">
										<i class="site-menu-icon wb-lock" aria-hidden="true" data-content="현재 수정이 불가능합니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
					                ${item.CRS_DTL_NM}
				                </td>
				                <td class="text-middle text-center">${item.CAT_NM}&nbsp;/&nbsp;<br>
				                	<c:if test="${item.LRNNG_GB eq 'online'}"><i class="site-menu-icon wb-desktop" aria-hidden="true" data-content="온라인 교육용 카테고리 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i></c:if>
				                	${item.CAT_DTL_NM}
				                </td>
				                <td class="text-middle text-center">
				                	<c:forEach var="subitem" items="${fn:split(item.TRN_SN,',')}">
				                		<c:forEach var="raw" items="${traning_data_list}">
				                			<c:if test="${raw.TRN_SN eq fn:trim(subitem)}">
				                				<span class="badge badge-outline badge-info">${raw.TRN_NM}&nbsp;(${raw.TRN_FILE_SN})</span><br>
				                			</c:if>
						                </c:forEach>
				     				</c:forEach>
				                </td>				                
		                 		<td class="text-middle text-center">
		                 			
				                	<c:choose>
				                		<c:when test="${item.LOCK_ST eq 0}"></c:when>
				                		<c:otherwise>잠김</c:otherwise>				                	
				                	</c:choose>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${isLock eq false}">
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-crs-dtl-sn="${item.CRS_DTL_SN}"
					                	data-linkurl="/eduadm/curriculum/modifyDtl.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-crs-dtl-sn="${item.CRS_DTL_SN}"
					                	data-del-st="${item.DEL_ST}">
					                		<i class="icon wb-trash" aria-hidden="true"></i>
					                	</a>
				                	</c:if>
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
    	document.listForm.action = "";
       	document.listForm.submit();
    }
    $(".clk_add_data,.clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.CRS_SN.value = $(this).attr('data-crs-sn');
    	form.CRS_DTL_SN.value = $(this).attr('data-crs-dtl-sn');
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
    	var data_crs_sn = $(this).attr('data-crs-sn');
    	var data_crs_dtl_sn = $(this).attr('data-crs-dtl-sn');
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
	    	form.CRS_SN.value = data_crs_sn;
	    	form.CRS_DTL_SN.value = data_crs_dtl_sn;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/curriculum/deleteDtl_act.do",
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
    						//window.location.reload();
    						document.listForm.submit();
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
