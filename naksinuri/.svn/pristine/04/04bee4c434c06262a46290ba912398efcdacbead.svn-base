<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %>
<%@ include file="../../left_menu.jsp" %>

<style>.border-r {border-right: 1px solid #e4eaec;}</style>

<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<form:form commandName="eduAdmCprBplcVO" id="chkForm" name="chkForm" method="post">
<input type="hidden" name="ECB_SN" value=""/>
</form:form>   

<form:form commandName="eduMbrRemindersVO" id="updateForm" name="updateForm" method="post">
<input type="hidden" name="USE_ST" value=""/>
<input type="hidden" id="RMNDR_SN" name="RMNDR_SN" value=""/>
<input type="hidden" id="chkedRMNDRIds" name="chkedRMNDRIds" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="ajaxSendSmsForm" name="ajaxSendSmsForm" method="post">
<input type="hidden" name="chkedMbrHps" value=""/>
<input type="hidden" name="chkedMbrNms" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxForm" name="ajaxForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_NM" value=""/>
<input type="hidden" name="LOG_UPD_MSG" value=""/>
<input type="hidden" name="DEL_NOW" value=""/>
</form:form>

<form:form commandName="eduAdmCprBplcVO" id="listForm" name="listForm" method="post">
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
	    				<div class="mr-10 col-md-3">
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
						<div class="mr-10">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchCondition">
	    						<option value="" <c:if test="${empty searchCondition}">selected</c:if>>처리여부</option>
	    						<option value="N" <c:if test="${searchCondition eq 'N'}">selected</c:if>>처리대기</option>
	    						<option value="Y" <c:if test="${searchCondition eq 'Y'}">selected</c:if>>처리완료</option>
	    					</select>
						</div>
						<input type="text" class="form-control mr-10 input-auto-enter-key" name="searchKeyword" placeholder="이름,연락처를 입력하세요." autocomplete="off" value="${searchKeyword}">
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
              		<!-- <div class="row">
              			<div class="col-lg-12">
			      			<a class="btn btn-danger btn-act-send-sms float-right" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">문자보내기</span>
				        	</a>
	              		</div>	
	              		<div class="col-lg-9">
              				<div class="">
		              			<a class="btn btn-outline btn-info btn-act-excel-down" href="javascript:void(0);">
					        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
					        		<span class="hidden-sm-down">엑셀다운로드</span>
					        	</a>
				        	</div>
			        	</div>
              		</div> -->
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
	          			<%-- <col width="40px"/> --%>
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center border-r" rowspan="2">구분</th>
	          				<th class="text-middle text-center border-r" colspan="5">법인대표자</th>
	          				<th class="text-middle text-center border-r" colspan="2">교육책임자</th>
	          				<th class="text-middle text-center border-r" rowspan="2">신청일자<br>(처리일자)</th>
	          				<th class="text-middle text-center" rowspan="2">비고</th>
	          			</tr>
	          			<tr>
							<!-- <th class="  text-middle text-center hide-cell-exceldown">
								<span class="checkbox-custom checkbox-primary">
                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
                            	</span>
							</th> -->
							<th class="text-middle text-center">성명</th>
							<th class="text-middle text-center">허가·등록 시·군·구</th>
							<th class="text-middle text-center">낚시터/어선명</th>
							<th class="text-middle text-center">휴대전화번호</th>
							<th class="text-middle text-center border-r">주소</th>
							<th class="text-middle text-center">성명</th>
							<th class="text-middle text-center">휴대전화번호</th>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="10" class="text-middle text-center table-active">법인사업장 교육책임자 지정 확인을 신청한 인원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<%-- <td class="  hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" 
	                              			data-esr-mbr-nm="${item.MHC_EDU_NM}" value="${item.MHC_EDU_HP}"><label></label>
	                            	</span>
                            	</td> --%>
                            	<td class="text-middle text-center">
                            		<c:choose>
                            			<c:when test="${item.ECB_DTL_CD eq 'CIDN010200'}">낚시터</c:when>
                            			<c:otherwise>낚시어선</c:otherwise>
                            		</c:choose>
                           		</td>
                            	<td class="text-middle text-center">${item.ECB_CPR_NM }</td>
                            	
                            	<td class="text-middle text-center">
                            		<c:forEach var="addr" items="${list_address_cd}">
                            			<c:if test="${item.ECB_CPR_SIDO_CD eq addr.CD_ID}">${addr.CD_NM }</c:if>
                            		</c:forEach>
                            		<c:out value="${item.CD_SIGNGU_NM }" />
                            	</td>
                            	<td class="text-middle text-center">${item.ECB_CPR_DTL_NM }</td>
                            	<td class="text-middle text-center">${item.ECB_CPR_HP }</td>
                            	<td class="text-middle text-center">${item.ECB_CPR_ADDR1 }</td>
                            	<td class="text-middle text-center">${item.ECB_EDU_NM }</td>
                            	<td class="text-middle text-center">${item.ECB_EDU_HP }</td>
                            	<td class="text-middle text-center">
					                <fmt:parseDate var="parseregdatestring" value="${item.ECB_REG_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
         							<fmt:formatDate var="reg_dt" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
         							<c:out value="${reg_dt }"/>
         							<c:if test="${not empty item.ECB_UPD_DT }">
         								<fmt:parseDate var="parseupddatestring" value="${item.ECB_UPD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
         								<fmt:formatDate var="upd_dt" value="${parseupddatestring}" pattern="yyyy.MM.dd HH:mm" />
         								<span class="red-600">(<c:out value="${upd_dt}"/>)</span>
         							</c:if>
                            	</td>
                            	<td class="text-middle text-center">
				                	<button type="button" class="btn btn-outline btn-success btn-xs clk_addnew_data" 
					                	data-linkurl="/eduadm/board/cpredu/write.do"
					                	data-ecb-sn="${item.ECB_SN}">처리/정보확인
					                </button>
                            	</td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging hide-cell-exceldown">
	 						<td colspan="10">
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
    $('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
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
			if(chkedRMNDRIds.length!=0) {
				chkedRMNDRIds+=",";
			}
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
    	form.
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
   
   $(".btn-act-send-sms").click(function() {
	   	var chkedMbrHps = "";
	   	var chkedMbrNms = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrHps.length!=0) {
				chkedMbrHps+=",";
				chkedMbrNms+=",";
			}
			chkedMbrHps+=$(this).val();
			chkedMbrNms+=$(this).attr('data-esr-mbr-nm');
		});
		if(chkedMbrHps.length==0) {
			alertify.alert("문자를 발송 할 대상자를 선택해주세요.");
			return;
		}
		var form = document.getElementById('ajaxSendSmsForm');
	   	form.chkedMbrHps.value = chkedMbrHps;
	   	form.chkedMbrNms.value = chkedMbrNms;
	   	$.ajax({
				type:"POST",
				url :"/adm/sms/send/write.do",
				data:$('#ajaxSendSmsForm').serialize(),
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
   //수정하기
   $(".clk_mod_data").click(function() {
   	var form = document.getElementById('ajaxForm');
   	form.MBR_ID.value = $(this).attr('data-mbr-id');
   	form.DEL_NOW.value = '';
   	form.action = '';
   	var data_linkurl = $(this).attr('data-linkurl');
   	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#ajaxForm').serialize(),
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
   
   $(".clk_addnew_data").click(function() {
   	var frm = document.getElementById('chkForm');
	  	frm.ECB_SN.value = $(this).attr('data-ecb-sn');
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
    </script>
    
<%@ include file="../../tail.jsp" %>

	<!-- // 엑셀다운로드(tail 뒤에 위치해야함.) // -->
    <!-- <script src="/common/js/jquery.table2excel.min.js"></script>
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
    </script> -->
    <!-- End // 엑셀다운로드 // -->  
