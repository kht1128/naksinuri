<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %>
<%@ include file="../../left_menu.jsp" %>

<form:form commandName="eduMbrRemindersVO" id="chkForm" name="chkForm" method="post">
<input type="hidden" name="RMNDR_SN" value=""/>
</form:form>

<form:form commandName="eduCenterMbrRemindersVO" id="IndvdlInfoViewForm" name="IndvdlInfoViewForm" method="post">
<input type="hidden" name="RMNDR_MBR_ID" value=""/>
<input type="hidden" name="RMNDR_CRS_SN" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>   

<form:form commandName="eduMbrRemindersVO" id="updateForm" name="updateForm" method="post">
<input type="hidden" name="USE_ST" value=""/>
<input type="hidden" id="RMNDR_SN" name="RMNDR_SN" value=""/>
<input type="hidden" id="chkedRMNDRIds" name="chkedRMNDRIds" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrForm" name="ajaxMbrForm" method="post">
<input type="hidden" name="RMNDR_SN" value=""/>
<input type="hidden" name="RMNDR_DSCRP" value=""/>
</form:form>

<form:form commandName="eduMbrRemindersVO" id="listForm" name="listForm" method="post">
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
	    				<div class="mr-10 col-md-2">
	    					<div class="input-group">
	    						<div class="input-group-prepend">
			           				<span class="input-group-text">출력갯수</span>
			           			</div>
	    						<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage} ">
	    					</div>
						</div>
	    				<div class="mr-10">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchOrderBy">
	    						<option value="" <c:if test="${empty searchOrderBy}">selected</c:if>>신청순</option>
	    						<option value="1" <c:if test="${searchOrderBy eq '1'}">selected</c:if>>이름순</option>
	    					</select>
						</div>
						<div class="">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="frm_CMPLT_ST">
	    						<option value="" <c:if test="${empty frm_CMPLT_ST}">selected</c:if>>처리여부</option>
	    						<option value="N" <c:if test="${frm_CMPLT_ST eq 'N'}">selected</c:if>>처리대기</option>
	    						<option value="Y" <c:if test="${frm_CMPLT_ST eq 'Y'}">selected</c:if>>처리완료</option>
	    					</select>
						</div>
						<div class="col-md-2">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalStrSearch" id="searchStrDate" name="searchStrDate" placeholder="시작일자" autocomplete="off" value="${searchStrDate}">
								<div class="input-group-append">
								    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="searchStrDate" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalEndSearch" id="searchEndDate" name="searchEndDate" placeholder="만료일자" autocomplete="off" value="${searchEndDate}">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="searchEndDate" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<input type="text" class="form-control mr-10 input-auto-enter-key" name="searchKeyword" placeholder="이름,연락처,아이디를 입력하세요." autocomplete="off" value="${searchKeyword}">
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
              		<!-- table:checkbox -->
              		<div class="float-right">
              			<div class="float-left pl10">
			      			<a class="btn btn-dark btn-act-delete-all" href="javascript:void(0);">
				        		<i class="fas fa-check-square" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">일괄삭제</span>
				        	</a>
	              		</div>
           				<!-- <div class="float-left pl10">
				        	<a class="btn btn-outline btn-info btn-act-external-excel-down" 
							data-excel-type="/eduadm/board/rmndr/list_online.do"
			        		data-excel-label="낚시전문교육_문자신청리스트_엑셀다운로드" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">엑셀다운로드</span>
				        	</a>
			        	</div> -->
              		</div>
              		<!-- End table:checkbox -->
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
	          			<col width="10%"/>
	          			<col width=""/>
	          			<col width=""/>
	          			<col width=""/>
	          			<col width="10%"/>
	          			<col width=""/>
	          			<col width="50px"/>
	          			<col width=""/>
	          			<col width="50px"/>
	          			<col width="10%"/>
	          			<col width=""/>
	          			<col width="53px"/>
	          		</colgroup>
	          		<thead>
	          			<tr>
							<th class="text-middle text-center hide-cell-exceldown">
								<span class="checkbox-custom checkbox-primary">
                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
                            	</span>
							</th>
							<th class="text-middle text-center">이름<br>(아이디)</th>
							<th class="text-middle text-center">연락처</th>
							<th class="text-middle text-center">생년월일</th>
							<th class="text-middle text-center">주소</th>
							<th class="text-middle text-center">교육대상구분<br>(선택교육과정)</th>
							<th class="text-middle text-center">수강인구분</th>
							<th class="text-middle text-center">어선명/낚시터명</th>
							<th class="text-middle text-center">신청일자<br/>(처리일자)</th>
							<th class="text-middle text-center">14세미만여부</th>
							<th class="text-middle text-center">메모<br><span class="font-size-11 blue-grey-400">(입력 후 3초뒤 자동저장)</span></th>
							<th class="text-middle text-center">현재상태</th>
							<th class="text-middle text-center hide-cell-exceldown">관리</th>	
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="12" class="text-center table-active">현재 온라인교육신청인원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" value="${item.RMNDR_SN}"><label></label>
	                            	</span>
                            	</td>
				                <td class="text-middle text-center">
				                	<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
				                	${item.RMNDR_MBR_NM}<br>
				                	<c:choose>
										<c:when test="${not empty item.RMNDR_MBR_ID}">(${item.RMNDR_MBR_ID})</c:when>
										<c:otherwise>(비회원)</c:otherwise>											
									</c:choose>
				                </td>
				                <td class="text-middle text-center">${item.RMNDR_MBR_HP}&nbsp;</td>
				                <td class="text-middle text-center">
				                	<%-- <c:catch var="ctagException1">
						                <fmt:parseDate var="parsembrbirthstring" value="${fn:replace(item.RMNDR_MBR_BIRTH,'-','')}" pattern="yyyyMMdd" />
						           		<fmt:formatDate var="birthString" value="${parsembrbirthstring}" pattern="yyyy년 MM월 dd일" />
					           		</c:catch>
					           		<c:if test="${ctagException1==null}">${birthString}</c:if> --%>
					           		${item.RMNDR_MBR_BIRTH}
					           	</td>					           	
					           	<td class="text-middle text-center">${item.RMNDR_MBR_ADDR1}<br>${item.RMNDR_MBR_ADDR2}</td>
					           	<td class="text-middle text-center">
					           		<c:forEach var="item_category" items="${list_mbr_cd}">
					       				<c:if test="${item.RMNDR_DTL_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
									</c:forEach>
									<br>
									<c:choose>
										<c:when test="${not empty item.RMNDR_CRS_SN}">(${item.RMNDR_CRS_NM })</c:when>
										<c:otherwise>(미지정)</c:otherwise>											
									</c:choose>					           	
					           	</td>
					            <td class="text-middle text-center">
					            	<c:forEach var="item_category" items="${list_license_se_cd}">
					       				<c:if test="${item.RMNDR_DTL_LICENSE_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
									</c:forEach>
					            </td>
					            <td class="text-middle text-center">${item.RMNDR_DTL_NM}</td>
					           	<td class="text-middle text-center">
				                	<c:catch var="ctagException2">
						                <fmt:parseDate var="parseregdatestring" value="${item.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           								<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
					           		</c:catch>
					           		<c:catch var="ctagException3">
						                <fmt:parseDate var="parseupddatestring" value="${item.UPD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           								<fmt:formatDate var="upddatestring" value="${parseupddatestring}" pattern="yyyy.MM.dd HH:mm" />
					           		</c:catch>
					           		
					           		<span><c:if test="${ctagException2==null}">${regdatestring}</c:if></span>
					           		<c:if test="${item.CMPLT_ST eq 'Y'}">
					           			<br/><span class="red-600"><c:if test="${ctagException3==null}">(${upddatestring})</c:if></span>
					           		</c:if>
					           	</td>		
					           	<td class="text-middle text-center">
					           		<c:choose>
					           			<c:when test="${item.UNDER_AGE_14_ST eq 'Y'}">
					           				${item.PARNTS_MBR_NM}
					           				<br>${item.PARNTS_MBR_HP}
					           				<br>${item.PARNTS_MBR_BIRTH}
					           			</c:when>
					           			<c:otherwise>
					           				해당안함
					           			</c:otherwise>
					           		</c:choose>
					           	</td>
					           	<td class="text-middle text-center">
			                		<textarea class="form-control typing-text-memo" row="5" data-rmndr-sn="${item.RMNDR_SN}">${item.RMNDR_DSCRP}</textarea>
			                	</td>			           	
				                <td class="text-middle text-center">
				                	<c:choose>
				                		<c:when test="${item.CMPLT_ST eq 'Y'}">
				                			<span class="display-block">처리완료</span>
				                			<button type="button" class="btn btn-outline btn-default btn-sm" onclick="clk_mbr_view('${item.RMNDR_MBR_ID}')">자세히보기</button>
				                			<button type="button" class="btn btn-outline btn-default btn-xs"
				                			 onclick="clk_indvdl_info_view('${item.RMNDR_MBR_ID}', '${item.RMNDR_CRS_SN }')">이용정보동의서</button>
				                		</c:when>
					                	<c:otherwise>
					                		<%-- <a class="append-rows btn btn-warning btn-outline clk_addnew_data" href="javascript:void(0)"
					                		data-linkurl="/eduadm/member/rmndr/write.do"
					                		data-rmndr-sn="${item.RMNDR_SN}">
								        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
								        		<span class="hidden-sm-down">정보확인</span>
								        	</a>  --%>
								        	<button type="button" class="btn btn-outline btn-success btn-xs clk_addnew_data" 
					                		data-linkurl="/eduadm/member/rmndr/write.do"
					                		data-rmndr-sn="${item.RMNDR_SN}"
					                		>처리/정보확인</button>
					                	</c:otherwise>
					                </c:choose>
				                </td>
				                <td class="text-middle text-center">
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-rmndr-sn="${item.RMNDR_SN}"
					                	data-del-st="${item.DEL_ST}"
					                	date-recovery-st="">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
				                	<c:if test="${item.DEL_ST eq '1'}">
					                	<button type="button" class="btn btn-outline btn-default btn-xs clk_del_data" 
						                	data-rmndr-sn="${item.RMNDR_SN}"
						                	data-del-st="${item.DEL_ST}"
						                	date-recovery-st="1">복구</button>
					                </c:if>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging hide-cell-exceldown">
	 						<td colspan="12">
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
    $('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
    });
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    	
    	$('.datepickerModalStrSearch').datepicker({
    	    format: 'yyyy-mm-dd',
    	    //startDate: '0d',
    	    autoclose: true,
    	    language: "kr",
    	});
    	$('.datepickerModalEndSearch').datepicker({
    	    format: 'yyyy-mm-dd',
    	    //startDate: '0d',
    	    autoclose: true,
    	    language: "kr",
    	});
    });
    $('.btn-check-item-all').click( function() {
		var data_status = $(this).attr("data-status");
		if(data_status == 'uncheck') {
			$(this).attr("data-status","");
			$("input[name=checkbox_item]").prop("checked", false);
		} else {
			$(this).attr("data-status","uncheck");
			$("input[name=checkbox_item]").prop("checked", true);
		}
	});
	$(".btn-act-all-cmplt").click( function() {
		var chkedRMNDRIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedRMNDRIds.length!=0) chkedRMNDRIds+=",";
			chkedRMNDRIds+=$(this).val();
		});
		if(chkedRMNDRIds.length==0) {
			alertify.alert("선택 된 항목이 없습니다.");
			return;
		}
		var form = document.getElementById('updateForm');
		form.USE_ST.value = '';
		form.RMNDR_SN.value = '';
    	form.chkedRMNDRIds.value = chkedRMNDRIds;
		form.action="";
    	$.ajax({
			type:"POST",
			url :"/eduadm/board/rmndr/modify_all_act.do",
			data:$('#updateForm').serialize(),
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
    	var date_recovery_st = $(this).attr('date-recovery-st');
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(date_recovery_st == '1') {
    		alert_message = "복구 하시겠습니까?";
    	} else {
	    	if(data_del_st == '1') {
	    		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	    	} else {
	    		alert_message = "삭제 하시겠습니까?";
	    	}
    	}
    	var data_rmndr_sn = $(this).attr('data-rmndr-sn');
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('updateForm');
    		form.USE_ST.value = date_recovery_st;
    		form.RMNDR_SN.value = data_rmndr_sn;
    		form.chkedRMNDRIds.value = '';
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/board/rmndr/delete_act.do",
    			data:$('#updateForm').serialize(),
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
    $(".clk_addnew_data").click(function() {
    	var frm = document.getElementById('chkForm');
	  	frm.RMNDR_SN.value = $(this).attr('data-rmndr-sn');
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#chkForm').serialize(),
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
    
  //회원상세정보보기(자세히보기)
   function clk_mbr_view(RMNDR_MBR_ID) {
   	var form = document.getElementById('ajaxMbrViewForm');
   	form.MBR_ID.value = RMNDR_MBR_ID;
   
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
  
  //이용정보동의서
  function clk_indvdl_info_view(RMNDR_MBR_ID, RMNDR_CRS_SN){
   	var form = document.getElementById('IndvdlInfoViewForm');
   	form.RMNDR_MBR_ID.value = RMNDR_MBR_ID;
   	form.RMNDR_CRS_SN.value = RMNDR_CRS_SN;
   /* var data-rmndr-crs-sn = $(this).attr('data-rmndr-crs-sn');
   	form.RMNDR_CRS_SN.value = data-rmndr-crs-sn; */
   	
   	$.ajax({
			type:"POST",
			url :'/eduadm/board/rmndr/indvdl_info_view.do',
			data:$('#IndvdlInfoViewForm').serialize(),
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
  	}
  
	$(".clk-clear-input").click(function() {
		var target = $(this).attr('for');
	  	$('#'+target).val("");
	});
	
	$(".btn-act-delete-all").click(function() {
    	var alert_message = alert_message = "선택한 항목을 모두 삭제하며 복구는 불가능 합니다.<br>그래도 삭제 하시겠습니까?";
    	
    	var chkedRMNDRIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedRMNDRIds.length!=0) chkedRMNDRIds+=",";
			chkedRMNDRIds+=$(this).val();
		});
		if(chkedRMNDRIds.length==0) {
			alertify.alert("선택 된 항목이 없습니다.");
			return;
		}
		
    	alertify.confirm(alert_message, function(){
    		//ok
    		if(isClickRequestLocked()) {
   				return;
   			}
			    		
			var form = document.getElementById('updateForm');
    		form.USE_ST.value = '';
    		form.RMNDR_SN.value = '';
        	form.chkedRMNDRIds.value = chkedRMNDRIds;
	    	form.action = '';
        	
	    	$.ajax({
    			type:"POST",
    			url :"/eduadm/board/rmndr/delete_act.do",
    			data:$('#updateForm').serialize(),
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
    						alertify.alert(data.msg,function(){
    							document.listForm.submit();	
    						});
    					}
    				}
    			},
    			complete : function() {
    				ajaxLoadingToastAppend();
    				//console.log('complete!');
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				ajaxLoadingToastAppend();
    				//console.log('error!');
    			}
    		});	 
    	});
   	});
	
	//메모저장
    var typingTimer = new Array();              
	var doneTypingInterval = 3000;
	$('.typing-text-memo').keyup(function(){
		
		var RMNDR_SN = $(this).attr('data-rmndr-sn');
		if(!typingTimer.hasOwnProperty(RMNDR_SN)) {
			var dummry = new Array();
			dummry['TIMER'] = null;
			typingTimer[RMNDR_SN] = dummry;//최초초기화용
		}
		clearTimeout(typingTimer[RMNDR_SN]['TIMER']);
		var target = $(this);
		typingTimer[RMNDR_SN]['TIMER'] = setTimeout(function(){
			doneTyping(target);
		}, doneTypingInterval);		
	});
	
	function doneTyping(obj) {
		var form = document.getElementById('ajaxMbrForm');
    	form.RMNDR_DSCRP.value = $(obj).val();
    	form.RMNDR_SN.value = $(obj).attr('data-rmndr-sn');
    	form.action = "";
    	form.target = "";
    	
		$.ajax({
			type:"POST",
			url :"/eduadm/board/rmndr/online_memo_update_act.do",
			data:$('#ajaxMbrForm').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.errCnt > 0) {
   					alertify.alert(data.msg);
   				} else {
   					if(data.error == '1') {
   						alertify.alert(data.msg);
   					} else {
   						toastr.info(data.msg,"",{
   							icon:false,
   							tapToDismiss: false,
   							closeButton: false,
   							debug: false,
   							newestOnTop: false,
   							progressBar: false,
   							positionClass: "toast-bottom-right",
   							preventDuplicates: false,
   							onclick: null,
   							showDuration: "300",
   						  	hideDuration: "300",
   							allowHtml: false,
   							onShown:function(){
   								
   							},
   							onHidden:function(){
   								
   							},
   							onclick:function(){
   								
   							},
   							onCloseClick:function(){
   								
   							}
   						});   						
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
	}
</script>
    
<%@ include file="../../tail.jsp" %>

	<!-- // 엑셀다운로드(tail 뒤에 위치해야함.) // -->
    <script src="/common/js/jquery.table2excel.min.js"></script>
    <script>
    $(".btn-act-excel-down").click( function() {
    	var today = new Date();
    	var d = today.getDay();
    	var m = today.getMonth();
    	var y = today.getFullYear();
    	var hh = today.getHours();
    	var mm = today.getMinutes();
    	var ss = today.getSeconds();
    	var timestr = y+""+m+""+d+""+hh+""+mm+""+ss;
    	$('#datalist').table2excel({
    		name: "${pageName}리스트",
    		filename: "${pageName}리스트_"+timestr+".xls",
    		//fileext: ".xls",
    		exclude_img: true,
    		exclude_links: true,
    		exclude_inputs: true,
    		exclude: ".hide-cell-exceldown",
    	});
    });
    </script>
    <!-- End // 엑셀다운로드 // -->  
