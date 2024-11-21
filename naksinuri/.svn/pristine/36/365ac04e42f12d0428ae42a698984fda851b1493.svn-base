<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>.pl10 {padding-left: 10px;}</style>

<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>   

<form:form commandName="dwldConfimFileVO" id="updateForm" name="updateForm" method="post">
<input type="hidden" name="CONFIM_MSG" value=""/>
<input type="hidden" name="confirm_type" value=""/>
<input type="hidden" name="USE_ST" value=""/>
<input type="hidden" id="chkedFilekeys" name="chkedFilekeys" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="ajaxSendSmsForm" name="ajaxSendSmsForm" method="post">
<input type="hidden" name="chkedMbrHps" value=""/>
<input type="hidden" name="chkedMbrNms" value=""/>
</form:form>

<form:form commandName="dwldConfimFileVO" id="listForm" name="listForm" method="post">
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
		        	<li class="breadcrumb-item active">${pageName}(요청건)</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
      </div>
      <div class="page-content container-fluid">
      
         <div class="row mb-10">
         	 <div class="col-xl-12">
	             <div class="float-right inline-b">
		        	<button type="button" class="btn btn-default btn-outline btn-category-manage-modal" data-cd-master-id="CID00010" data-title="파일 다운로드 옵션 설정">
		        		<i class="fas fa-cog"></i>
		        		<span class="hidden-sm-down">파일 다운로드 옵션 설정</span>
		        	</button>
		        </div>
	        </div>
        </div>
         
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
	    					<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CONFIM_ST">
	    						<option value="" <c:if test="${empty CONFIM_ST}">selected</c:if>>승인상태(전체)</option>
	    						<option value="N" <c:if test="${CONFIM_ST eq 'N'}">selected</c:if>>승인대기</option>
	    						<option value="Y" <c:if test="${CONFIM_ST eq 'Y'}">selected</c:if>>승인완료</option>
	    						<option value="CANCEL" <c:if test="${CONFIM_ST eq 'CANCEL'}">selected</c:if>>승인취소</option>
	    					</select>
						</div>
						<input type="text" class="form-control mr-10 input-auto-enter-key" name="searchKeyword" placeholder="이름,아이디를 입력하세요." autocomplete="off" value="${searchKeyword}">
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
			      			<a class="btn btn-info btn-act-confirm-all" href="javascript:void(0);"
			      				data-confirm-type="ok">
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">일괄승인처리</span>
				        	</a>
	              		</div>
	              		<div class="float-left pl10">
			      			<a class="btn btn-warning btn-act-confirm-all" href="javascript:void(0);"
			      				data-confirm-type="cancel">
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">일괄취소처리</span>
				        	</a>
	              		</div>
              			<div class="float-left pl10">
			      			<a class="btn btn-danger btn-act-send-sms" href="javascript:void(0);">
				        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">문자보내기</span>
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
							<th class="text-middle text-center">번호</th>
							<th class="text-middle text-center">신청자<br>(신청일자)</th>
							<th class="text-middle text-center">회원정보</th>
							<th class="text-middle text-center">신청파일<br>(개인정보취급건수)</th>
							<th class="text-middle text-center">신청사유</th>
							<th class="text-middle text-center">승인자<br>(승인일자)</th>
							<th class="text-middle text-center">다운로드일자<br>(다운로드횟수)</th>
							<th class="text-middle text-center">상태</th>
							<th class="text-middle text-center">비고(처리사유)</th>
							<%-- <th class="text-middle text-center hide-cell-exceldown">관리</th>	 --%>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="10"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">현재 파일다운로드 건이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" 
	                              			data-mbr-nm="${item.REQ_MBR_NM}" data-file-key="${item.ATCH_FILE_ID};${item.FILE_SN}" value="${item.REQ_MBR_HP}"><label></label>
	                            	</span>
                            	</td>
                            	<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
				                <td class="text-middle text-center">
				                	${item.REQ_MBR_NM}<br>
				                	<fmt:parseDate var="parseregdatestring" value="${item.REG_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
         							<fmt:formatDate var="reg_dt" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
         							<c:out value="( ${reg_dt } )"/>
								</td>
								<td class="text-middle text-center">
									<button type="button" class="btn btn-outline btn-default btn-sm" onclick="clk_mbr_view('${item.REQ_MBR_ID}')">자세히보기</button>
								</td>
				                <td class="text-middle text-center">
				                	${item.ORIGNL_FILE_NM}<br>( ${item.INDVDL_INFO_CNT } 건)
				                	<%--
									<c:choose>
										<c:when test="${item.DWLD_WAIT_ST eq 'Y'}">${item.ORIGNL_FILE_NM}</c:when>
										<c:otherwise>
											<c:import url="/cmm/fms/selectFileInfs.do" >
											<c:param name="param_atchFileId" value="${item.ATCH_FILE_ID}" />
											<c:param name="param_fixFileSn" value="${item.FILE_SN}" />
										    </c:import>
										</c:otherwise>				                	
									</c:choose>
									 --%>
				                </td>
				                <td class="text-middle text-center">${item.REQ_MSG }</td>
				                <td class="text-middle text-center">
				                	<c:if test="${item.CONFIM_ST eq 'Y'}">
				                		<c:if test="${item.CONFIM_MBR_ID eq 'system'}">${item.CONFIM_MBR_ID}</c:if>
					                	${item.CONFIM_MBR_NM}<br>
					                	<fmt:parseDate var="parseconfimdatestring" value="${item.CONFIM_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
	         							<fmt:formatDate var="confim_dt" value="${parseconfimdatestring}" pattern="yyyy.MM.dd HH:mm" />
	         							<c:out value="( ${confim_dt } )"/>
         							</c:if>
								</td>
				                <td class="text-middle text-center">
				                	<c:choose>
				                		<c:when test="${item.CONFIM_ST eq 'Y'}">
				                			<c:choose>
						                		<c:when test="${item.DWLD_CMPLT_ST eq 'Y'}">
						                			다운로드완료<br>
					                				<fmt:parseDate var="parsedwldcmpltdatestring" value="${item.DWLD_CMPLT_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
				         							<fmt:formatDate var="dwld_cmplt_dt" value="${parsedwldcmpltdatestring}" pattern="yyyy.MM.dd HH:mm" />
				         							<c:out value="( ${dwld_cmplt_dt } )"/>
				         							<br>(${item.DWLD_CNT} 건)
						                		</c:when>
						                		<c:otherwise>
						                			<c:if test="${item.REQ_MBR_ID eq CUR_MBR_ID}">					                			
							                			<button type="button" class="btn btn-outline btn-success btn-xs btn-act-file-download" 
										                	data-linkurl="/eduadm/"
										                	data-file-key="${item.ATCH_FILE_ID};${item.FILE_SN}">파일받기
										                </button>
									                </c:if>
						                		</c:otherwise>				                	
						                	</c:choose>	
				                		</c:when>
				                		<c:when test="${item.CONFIM_ST eq 'CANCEL'}">
				                			<c:if test="${item.DWLD_CMPLT_ST eq 'Y'}">
					                			다운로드완료<br>
				                				<fmt:parseDate var="parsedwldcmpltdatestring" value="${item.DWLD_CMPLT_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
			         							<fmt:formatDate var="dwld_cmplt_dt" value="${parsedwldcmpltdatestring}" pattern="yyyy.MM.dd HH:mm" />
			         							<c:out value="( ${dwld_cmplt_dt } )"/>
			         							<br>(${item.DWLD_CNT} 건)
					                		</c:if>
				                		</c:when>
				                		<c:otherwise></c:otherwise>			                	
				                	</c:choose>
				                </td>
				                <td class="text-middle text-center">
				                	<c:choose>
				                		<c:when test="${item.DWLD_WAIT_ST eq 'Y'}">자료준비중</c:when>
				                		<c:otherwise>
				                			<c:choose>
						                		<c:when test="${item.CONFIM_ST eq 'Y'}">승인완료</c:when>
						                		<c:when test="${item.CONFIM_ST eq 'CANCEL'}">승인취소<br>(${item.CONFIM_DT})</c:when>
						                		<c:otherwise>승인대기</c:otherwise>				                	
						                	</c:choose>
				                		</c:otherwise>				                	
				                	</c:choose>
				                </td>
				                <td class="text-middle text-center">${item.CONFIM_MSG }</td>
				                <%-- 
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-esr-sn="${item.FILE_SN}"
					                	data-del-st="${item.USE_ST}"
					                	date-recovery-st="">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>
				                	<c:if test="${item.USE_ST eq 'N'}">
					                	<button type="button" class="btn btn-outline btn-default btn-xs clk_del_data" 
						                	data-esr-sn="${item.FILE_SN}"
						                	data-del-st="${item.USE_ST}"
						                	date-recovery-st="1">복구</button>
					                </c:if>
				                </td>
				                 --%>
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
    $(".btn-act-confirm-all").click(function() {	
    	var confirm_type = $(this).attr('data-confirm-type');
    	var alert_message = "선택한 항목을 모두 승인처리 하시겠습니까?";	
    	if(confirm_type=='cancel') {
    		alert_message = "선택한 항목을 모두 승인취소 하시겠습니까?";
    	} 
    	var chkedFilekeys = "";
    	$("input[name=checkbox_item]:checked").each(function() {
			if(chkedFilekeys.length!=0) {
				chkedFilekeys+=",";
			}
			chkedFilekeys+=$(this).attr('data-file-key');
		});
		if(chkedFilekeys.length==0) {
			alertify.alert("선택 된 항목이 없습니다.");
			return;
		}
		alertify.prompt(alert_message+'<br>처리 사유를 입력해주세요.',function(val, e) {
			//ok
			if(val.trim()=='') {
				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
				return;
			} 
    		//ok
			var form = document.getElementById('updateForm');
			form.CONFIM_MSG.value = val;
    		form.confirm_type.value = confirm_type;
    		form.USE_ST.value = '';
    		form.chkedFilekeys.value = chkedFilekeys;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/main/dwldConfim/modify_all_act.do",
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
    
    
    /*
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
    			url :"/eduadm/main/dwldConfim/delete_act.do",
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
    */
    
    $(".btn-act-file-download").click(function() {
    	var filekey = $(this).attr('data-file-key');
    	var alert_message = "다운로드는 1회만 가능하며 확인 버튼 클릭 후 즉시 완료 처리됩니다.<br>다시 받으셔야 하는 경우에는 재신청을 해주셔야 합니다.<br>계속 하시겠습니까?";
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var parseFilekey = filekey.split(';');
	    	window.open('/cmm/fms/FileDown.do?atchFileId='+parseFilekey[0]+'&fileSn='+parseFilekey[1]);
	    	document.listForm.submit();
    	});
   	});
    

	//회원상세정보보기(자세히보기)
	function clk_mbr_view(MBR_ID) {
	   	var form = document.getElementById('ajaxMbrViewForm');
	   	form.MBR_ID.value = MBR_ID;
	   
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
			chkedMbrNms+=$(this).attr('data-mbr-nm');
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
   $(".btn-category-manage-modal").on("click", function(){
		$.ajax({
			type:"POST",
			url :"/adm/category/manager.do",
			data:{
				TITLE : $(this).attr('data-title'),
				CD_MASTER_ID : $(this).attr('data-cd-master-id'),
			},
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
				//$("#seaAdmEduPublicModal").modal('show');
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false});
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
