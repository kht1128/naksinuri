<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>


<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>


<form:form commandName="eduCertificateVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchUseYn" value="Y" />
	<input type="hidden" id="MBR_ID" name="MBR_ID" value=""/>
	<input type="hidden" id="CRTF_SN" name="CRTF_SN" value=""/>
	<input type="hidden" name="CRTF_TYPE_ST" value=""/>
	<input type="hidden" name="LOG_UPD_MSG" value="" />
	
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
	    						<%-- <option value="" <c:if test="${empty searchCondition}">selected</c:if>>검색조건없음</option> --%>
	    						<option value="mbr" <c:if test="${empty searchCondition or (searchCondition eq 'mbr')}">selected</c:if>>회원 검색</option>
	    						<option value="crs" <c:if test="${searchCondition eq 'crs'}">selected</c:if>>교육과정 검색</option>
	    						<option value="nonmbr" <c:if test="${searchCondition eq 'nonmbr'}">selected</c:if>>비회원 검색</option>
	    					</select>
						</div>
						<input type="text" class="form-control mr-10" name="searchKeyword" placeholder="검색조건을 선택하시고 이름,연락처,아이디 또는 교육과정명를 입력하세요." autocomplete="off" value="${searchKeyword}">
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
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
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center">No</th>
	          				<!-- <th class="text-middle text-center">교육그룹</th> -->
	          				<th class="text-middle text-center">교육연도</th>
		          			<th class="text-middle text-center">교육과정명${item.MBR_NM}</th>
		          			<th class="text-middle text-center">교육기관</th>
		          			<!-- <th class="text-middle text-center">사업장명</th> -->
		          			<th class="text-middle text-center">이름<br>(닉네임)</th>
		          			<th class="text-middle text-center">생년월일</th>							
	           				<th class="text-middle text-center">연락처</th>
	           				<th class="text-middle text-center">회원정보</th>
	           				<th class="text-middle text-center">이수증정보</th>
	           				<th class="text-middle text-center">발급 된<br>이수증수</th>
	            			<th class="text-middle text-center">비고</th>
            			</tr>        				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="11"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt }" class="text-center table-active">조회 가능한 회원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
							<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
								<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
								<td class="text-middle text-center">
									<%--
		            				<c:choose>
										<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">귀어창업기술교육</c:when>
										<c:when test="${item.CRS_TYPE eq 'wknd_trnng'}">주말교육</c:when>
										<c:otherwise>종합교육</c:otherwise>											
									</c:choose>
									 --%>
									<%-- <c:forEach var="cate" items="${list_all_edu_grp_cd}">
		            					<c:if test="${item.CRS_GRP_CD eq cate.CD_ID}">${cate.CD_NM}</c:if>
									</c:forEach> --%>
									${item.CRS_STR_DT_YEAR}년
		            			</td>
		            			<td class="text-middle">
				                	<c:choose>
										<c:when test="${item.DEL_ST ne '0'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 이수증입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 이수증입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
					                ${item.CRS_NM}
				                </td>
				                <td class="text-middle">
		            				<c:forEach var="cate" items="${list_all_edu_ins_cd}">
		            					<c:if test="${item.CAT_INS_SN eq cate.CAT_INS_SN}">${cate.CAT_INS_NM}</c:if>
									</c:forEach>
		            			</td>
		            			<!-- <td class="text-middle">
		            				사업자명?
		            			</td> -->
				                <c:choose>
					                <c:when test="${empty item.MBR_NM}">
					                	<%-- <td class="text-middle text-center">${item.TMP_MBR_NM}</td>
						                <td class="text-middle text-center">${item.TMP_MBR_HP}</td>
						                <td class="text-middle text-center">${item.TMP_MBR_BIRTH}</td> --%>
						                <td class="text-middle text-center" colspan="5">정보없음 (회원정보가 삭제 되었거나 존재하지 않음)</td>
					                </c:when>
					                <c:otherwise>
							            <td class="text-middle text-center">${item.MBR_NM}<br>(${item.MBR_NCNM})</td>
							            <td class="text-middle text-center">${item.MBR_BIRTH}</td>
						                <td class="text-middle text-center">${item.MBR_HP}</td>
						                <td class="text-middle text-center">
						                	<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
						                </td>   
						                <td class="text-middle text-center">	
						                	<button type="button" class="btn btn-outline btn-primary btn-xs" onclick="showCrtf(this)" 
					                		data-mbr-id="${item.MBR_ID}" 
						                	data-crtf-sn="${item.CRTF_SN}">이수증 열람하기</button>
						                </td> 
					                </c:otherwise>
				                </c:choose>				                
				                <td class="text-middle text-center">
				                	<span class="badge badge-success font-size-14">${item.CRTF_DTL_CNT} 건</span>
				                </td>
				                <td class="text-middle text-center">
			                		<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
				                	data-mbr-id="${item.MBR_ID}" 
				                	data-crtf-sn="${item.CRTF_SN}"
				                	data-linkurl="/eduadm/certificate/modify.do">
				                		<i class="icon wb-wrench" aria-hidden="true"></i>
				                	</a>
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                	data-mbr-id="${item.MBR_ID}"
				                	data-crtf-sn="${item.CRTF_SN}"
				                	data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
			                		<br>
			                		<button type="button" class="btn btn-outline btn-success btn-xs" onclick="pageMove(this)" 
			                		data-crtf-sn="${item.CRTF_SN}"
			                		data-mbr-id="${item.MBR_ID}"
			                		>발급내역 페이지 이동</button>
			                	</td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
	 						<td colspan="${table_cell_cnt }">
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
    function directReload() {//이수증 열람시 페이지 갱신을 위한 처리
    	var form = document.getElementById('listForm');
    	form.action = "/eduadm/certificate/list.do";
    	form.target = "";
    	form.submit();  
    }
    function showCrtf(obj) {
    	alertify.prompt('이수증 발급 사유를 입력해주세요.',function(val, e) {
    		//ok
    		if(val.trim()=='') {
   				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
   				return;
   			} 
    		var gsWin = window.open("about:blank","winCrtf");
        	var form = document.getElementById('listForm');
        	form.CRTF_SN.value = $(obj).attr('data-crtf-sn');
        	form.MBR_ID.value = $(obj).attr('data-mbr-id');
        	form.CRTF_TYPE_ST.value = val;
        	form.LOG_UPD_MSG.value = val;
        	form.action = "/eduadm/certificate/view.do";
        	form.target = "winCrtf";
        	form.submit();         	
    	});    	
    }
    function pageMove(obj) {
    	var form = document.getElementById('listForm');
    	form.CRTF_SN.value = $(obj).attr('data-crtf-sn');
    	form.MBR_ID.value = $(obj).attr('data-mbr-id');
    	form.pageIndex.value = '1';
    	form.action = "/eduadm/certificate/listDtl.do";
    	form.target = "";
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
    	form.target = "";
    	form.submit();
    });   
    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
    	form.CRTF_SN.value = $(this).attr('data-crtf-sn');
    	form.action = '';
    	form.target = "";
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
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	var data_crtf_sn = $(this).attr('data-crtf-sn');
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 발급 된 이수증 및 발급 내역 데이터를 완전히 삭제합니다.<br>그래도 계속 하시겠습니까?";
    	} else {
    		alert_message = "발급 된 이수증를 삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
    		form.MBR_ID.value = data_mbr_id;
    		form.CRTF_SN.value = data_crtf_sn;
	    	form.action = '';
	    	form.target = "";
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/certificate/delete_act.do",
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
    function clk_mbr_view(mbr_id) {
    	var form = document.getElementById('ajaxMbrViewForm');
    	form.MBR_ID.value = mbr_id;
    	$.ajax({
    		type:"POST",
    		url :'/eduadm/member/view.do',
    		data:$('#ajaxMbrViewForm').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#admPublicPanelLayer").html(data);
    			$("#admPublicPanelLayer").show();
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    		}
    	});
    }
    </script>
        

<%@ include file="../tail.jsp" %>
