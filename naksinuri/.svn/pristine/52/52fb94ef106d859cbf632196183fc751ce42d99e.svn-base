<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

<%@ include file="../../head.jsp" %>
<%@ include file="../../left_menu.jsp" %>

<style>.pl10 {padding-left: 10px;}</style>

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

<form:form commandName="eduAdmSmsRequstVO" id="updateForm" name="updateForm" method="post">
<input type="hidden" name="ESR_USE_ST" value=""/>
<input type="hidden" id="ESR_SN" name="ESR_SN" value=""/>
<input type="hidden" id="chkedESRSNIds" name="chkedESRSNIds" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="ajaxSendSmsForm" name="ajaxSendSmsForm" method="post">
<input type="hidden" name="chkedMbrHps" value=""/>
<input type="hidden" name="chkedMbrNms" value=""/>
<input type="hidden" name="chkedMbrIds" value=""/>
</form:form>

<form:form commandName="eduAdmSmsRequstVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="excel_msg" value="" />
	<input type="hidden" name="excel_type" value="" />
	<input type="hidden" name="excel_label" value="" />      
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
						<%-- <div class="mr-10">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="frm_CMPLT_ST">
	    						<option value="" <c:if test="${empty frm_CMPLT_ST}">selected</c:if>>처리여부</option>
	    						<option value="N" <c:if test="${frm_CMPLT_ST eq 'N'}">selected</c:if>>처리대기</option>
	    						<option value="Y" <c:if test="${frm_CMPLT_ST eq 'Y'}">selected</c:if>>처리완료</option>
	    					</select>
						</div> --%>
						<div class="mr-10">
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchCondition">
	    						<option value="" <c:if test="${empty searchCondition}">selected</c:if>>수강인구분</option>
	    						<option value="CIDL00001" <c:if test="${searchCondition eq 'CIDL00001'}">selected</c:if>>개인업자</option>
	    						<option value="CIDL00002" <c:if test="${searchCondition eq 'CIDL00002'}">selected</c:if>>법인업자</option>
	    					</select>
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
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">일괄삭제</span>
				        	</a>
	              		</div>
              			<div class="float-left pl10">
			      			<a class="btn btn-danger btn-act-send-sms" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">문자보내기</span>
				        	</a>
	              		</div>	
           				<div class="float-left pl10">
	              			<!-- <a class="btn btn-outline btn-info btn-act-excel-down" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">엑셀다운로드(자바스크립트)</span>
				        	</a> --> 
				        	<a class="btn btn-outline btn-info btn-act-external-excel-down" 
							data-excel-type="/eduadm/board/esrequest/list.do"
			        		data-excel-label="낚시전문교육_문자신청리스트_엑셀다운로드" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">엑셀다운로드</span>
				        	</a>
				        	
			        	</div>
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
	          		</colgroup>
	          		<thead>
	          			<tr>
							<th class="text-middle text-center hide-cell-exceldown">
								<span class="checkbox-custom checkbox-primary">
                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
                            	</span>
							</th>
							<th class="text-middle text-center">이름<!-- <br>(아이디) --></th>
							<th class="text-middle text-center">연락처</th>
							<!-- <th class="text-middle text-center">시도/시군구</th> -->
							<th class="text-middle text-center">교육대상구분<!-- <br>(선택교육과정) --></th>
							<th class="text-middle text-center">교육알림 대상구분</th>
							<th class="text-middle text-center">수강인구분</th>
							<th class="text-middle text-center">신청일자</th>
							<th class="text-middle text-center hide-cell-exceldown">상세정보</th>
							<th class="text-middle text-center hide-cell-exceldown">관리</th>	
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="7" class="text-center table-active">현재 온라인교육신청인원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" 
	                              			data-esr-mbr-nm="${item.ESR_MBR_NM}" data-esr-sn="${item.ESR_SN}" data-esr-mbr-hp="${item.ESR_MBR_HP}"  value="${item.ESR_MBR_ID}"><label></label>
	                            	</span>
                            	</td>
				                <td class="text-middle text-center">
				                	<c:if test="${item.ESR_DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
				                	${item.ESR_MBR_NM}<%-- <br>
				                	<c:choose>
										<c:when test="${not empty item.ESR_MBR_ID}">(${item.ESR_MBR_ID})</c:when>
										<c:otherwise>(비회원)</c:otherwise>											
									</c:choose> --%>
				                </td>
				                <td class="text-middle text-center">${pfpu:phoneHypen(item.ESR_MBR_HP)}&nbsp;</td>
					           	<%-- <td class="text-middle text-center">${item.ESR_SIDO_NM }<br>${item.ESR_SIGNGU_NM }</td> --%>
					           	<td class="text-middle text-center">
					           		<c:forEach var="item_category" items="${list_mbr_cd}">
					       				<c:if test="${item.ESR_DTL_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
									</c:forEach>
									<%-- <br>
									<c:choose>
										<c:when test="${not empty item.RMNDR_CRS_NM}">(${item.RMNDR_CRS_NM })</c:when>
										<c:otherwise>(미지정)</c:otherwise>											
									</c:choose>		 --%>		           	
					           	</td>
					           	<td class="text-middle text-center">
					           		<c:choose>
					           			<c:when test="${item.ESR_TRGET eq 'N'}">기존</c:when>
					           			<c:when test="${item.ESR_TRGET eq 'Y'}">신규</c:when>
					           			<c:when test="${item.ESR_TRGET eq 'R'}">재개자</c:when>
					           			<c:otherwise>-</c:otherwise>
					           		</c:choose>
					           	</td>
					            <td class="text-middle text-center">
					            	<c:forEach var="item_category" items="${list_license_se_cd}">
					       				<c:if test="${item.ESR_DTL_LICENSE_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
									</c:forEach>
					            </td>
					           	<td class="text-middle text-center">
					           		<fmt:parseDate var="parseregdatestring" value="${item.ESR_REG_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
         							<fmt:formatDate var="reg_dt" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
         							<c:out value="${reg_dt }"/><br>
					              	<%-- (<c:choose>
					              		<c:when test="${not empty item.ESR_CMPLT_DT}"><span class="red-600">${item.ESR_CMPLT_DT}</span></c:when>
										<c:otherwise><span class="grey-400">미처리</span></c:otherwise>
					              	</c:choose>) --%>
					           	</td>					           	
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<c:if test="${not empty item.ESR_MBR_ID}">
		                				<button type="button" class="btn btn-outline btn-default btn-sm" onclick="clk_mbr_view('${item.ESR_MBR_ID}')">자세히보기</button>
		                			</c:if>	
				                </td>
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-esr-sn="${item.ESR_SN}"
					                	data-del-st="${item.ESR_DEL_ST}"
					                	date-recovery-st="">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
				                	<c:if test="${item.ESR_DEL_ST eq '1'}">
					                	<button type="button" class="btn btn-outline btn-default btn-xs clk_del_data" 
						                	data-esr-sn="${item.ESR_SN}"
						                	data-del-st="${item.ESR_DEL_ST}"
						                	date-recovery-st="1">복구</button>
					                </c:if>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging hide-cell-exceldown">
	 						<td colspan="9">
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
	/* $(".btn-act-all-cmplt").click( function() {
		var chkedESRSNIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedESRSNIds.length!=0) {
				chkedESRSNIds+=",";
			}
			chkedESRSNIds+=$(this).val();
		});
		if(chkedESRSNIds.length==0) {
			alertify.alert("선택 된 항목이 없습니다.");
			return;
		}
		var form = document.getElementById('updateForm');
		form.USE_ST.value = '';
		form.RMNDR_SN.value = '';
    	form.chkedESRSNIds.value = chkedESRSNIds;
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
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
		
	}); */
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
    $(".btn-act-delete-all").click(function() {
    	var alert_message = alert_message = "선택한 항목을 모두 삭제하며 복구는 불가능 합니다.<br>그래도 삭제 하시겠습니까?";
    	var chkedESRSNIds = "";
    	$("input[name=checkbox_item]:checked").each(function() {
			if(chkedESRSNIds.length!=0) {
				chkedESRSNIds+=",";
			}
			chkedESRSNIds+=$(this).attr('data-esr-sn');
		});
		if(chkedESRSNIds.length==0) {
			alertify.alert("선택 된 항목이 없습니다.");
			return;
		}
		
    	alertify.confirm(alert_message, function(){ 
    		//ok
			var form = document.getElementById('updateForm');
    		form.ESR_USE_ST.value = '';
    		form.ESR_SN.value = '';
    		form.chkedESRSNIds.value = chkedESRSNIds;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/board/esrequest/delete_act.do",
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
    	var data_esr_sn = $(this).attr('data-esr-sn');
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('updateForm');
    		form.ESR_USE_ST.value = date_recovery_st;
    		form.ESR_SN.value = data_esr_sn;
    		form.chkedESRSNIds.value = '';
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/board/esrequest/delete_act.do",
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
   
   $(".btn-act-send-sms").click(function() {
// 	   	var chkedMbrHps = "";
// 	   	var chkedMbrNms = "";
 	   	var chkedMbrIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) {
// 				chkedMbrHps+=",";
// 				chkedMbrNms+=",";
				chkedMbrIds+=",";
			}
// 			chkedMbrHps+=$(this).attr('data-esr-mbr-hp');
// 			chkedMbrNms+=$(this).attr('data-esr-mbr-nm');
			chkedMbrIds+=$(this).val();
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("문자를 발송 할 대상자를 선택해주세요.");
			return;
		}
		var form = document.getElementById('ajaxSendSmsForm');
// 	   	form.chkedMbrHps.value = chkedMbrHps;
// 	   	form.chkedMbrNms.value = chkedMbrNms;
	   	form.chkedMbrIds.value = chkedMbrIds;
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
				alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.',function(){
					document.listForm.action = "";
				});
		    	var form = document.getElementById('listForm');
		    	form.excel_label.value = excel_label;
		    	form.excel_type.value = excel_type;
		    	form.excel_msg.value = val;
		    	form.action = "/all/main/excel/down.do";
		    	form.submit();
			} ,function() { 
			//cancel    			
		});
	});
    </script>
    
<%@ include file="../../tail.jsp" %>

	<!-- // 엑셀다운로드(tail 뒤에 위치해야함.) XLS만 됨, XLSX는 라이브러리를 대체해야 가능할것으로 판단됨 // -->
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
