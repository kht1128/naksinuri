<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>
	th {border-right: 1px solid #e4eaec;}
	th:last-child {border-right: none;}
</style>

<form:form commandName="eduMemberVO" id="IndvdlInfoViewForm" name="IndvdlInfoViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_HP" value=""/>
</form:form> 
 
<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="logAdmAuthVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchUseYn" value="Y" />
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
								<input type="text" class="form-control input-auto-enter-key" name="searchKeyword" placeholder="검색어를 입력하세요." autocomplete="off" value="${searchKeyword}" data-toggle="tooltip" data-original-title="검색어를 입력하세요.">
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
              	<div class="row">
	              	<div class="col-lg-12 text-right">
	              		<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down" href="javascript:void(0);" 
			        		data-excel-type="/eduadm/member/author_log.do"
			        		data-excel-label="${fn:trim(pageName)}_전체_엑셀다운로드">
			        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
			        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드</span>
			        	</a> 
	              	</div>
            	</div>
           		<div class="example-tooltip">
					<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
						<div class="arrow"></div>
						<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount} 건</div>
					</div>
				</div>
              		<!-- table:start -->
	      			<table id="datalist" class="table table-hover footable footable-paging footable-paging-center">
	          		<colgroup>
	          			<col width="5%"/>
	          			<col width="7%"/>
	          			<col width="10%"/>
	          			<col width="10%"/>
	          			<col width="7%"/>
	          			<col width="10%"/>
	          			<col />
	          			<%-- <col /> --%>
	          			<col width="10%"/>
	          			<col width="5%"/>
	          			<col />
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center" rowspan="2">No</th>
		          			<th class="text-middle text-center" colspan="3">신청정보</th>
		          			<th class="text-middle text-center" colspan="4">승인정보</th>
		          			<!-- <th class="text-middle text-center" rowspan="2">메모</th> -->
		          			<th class="text-middle text-center" rowspan="2">권한등급</th>
		          			<th class="text-middle text-center" rowspan="2">권한명</th>
            			</tr>        				
            			<tr>
		          			<th class="text-middle text-center">신청자</th>
		          			<th class="text-middle text-center">신청일시</th>
		          			<th class="text-middle text-center">신청목적</th>
		          			<th class="text-middle text-center">승인자</th>
		          			<th class="text-middle text-center">승인일시</th>
		          			<th class="text-middle text-center">사유</th>
		          			<th class="text-middle text-center">구분</th>
	          			</tr>
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="11"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 가능한 회원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>	
				                <td class="text-middle text-center">${item.REQST_NM }</td>
				                <td class="text-middle text-center">
				                	<fmt:parseDate var="reqst_dt_string" value="${fn:replace(item.REQST_DT,'.0','')}" pattern="yyyy-MM-dd HH:mm:ss" />
       								<fmt:formatDate var="reqst_dt" value="${reqst_dt_string}" pattern="yyyy.MM.dd HH:mm" />
			                		<c:choose>
			                			<c:when test="${item.CONFM_TYPE eq '권한 삭제'}"></c:when>
			                			<c:when test="${item.CONFM_TYPE eq '실제 데이터 삭제'}"></c:when>
			                			<c:otherwise>${reqst_dt}</c:otherwise>
			                		</c:choose>
				                </td>
				                <td class="text-middle text-center">${item.REQST_CN }</td>
				                <td class="text-middle text-center">${item.CONFM_NM }</td>
				                <td class="text-middle text-center">
				                	<fmt:parseDate var="confm_dt_string" value="${item.CONFM_DT }" pattern="yyyy-MM-dd HH:mm:ss" />
       								<fmt:formatDate var="confm_dt" value="${confm_dt_string}" pattern="yyyy.MM.dd HH:mm" />
				                ${confm_dt}
				                </td>
				                <td class="text-middle text-center">${item.CONFM_CN }</td>
				                <td class="text-middle text-center">${item.CONFM_TYPE }</td>
				               	<%-- <td class="text-middle text-center">${item.MBR_MSG }</td> --%>
				               	<td class="text-middle text-center">${item.MBR_LV }</td>
				               	<td class="text-middle text-center">${item.AUTHOR_NM }</td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
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
    $("#search_sel_area_sido").change(function() {
    	if(this.value=='') {
    		$('#search_sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
    		return;
    	}  
    	var form = document.getElementById('searchFormSido');
    	form.CD_MASTER_ID.value = this.value;
    	form.action = ''; 
    	$.ajax({
    		type:"POST",
    		url :"/all/code.do",
    		data:$('#searchFormSido').serialize(),
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			//console.log(data);
    			//console.log(data.rawdata);
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				var json = JSON.parse(data.rawdata);
    				var htmlString = '<option value="">시군구 전체</option>';
    				for (i=0; i<json.length; i++) {	
    					var item = json[i];
    					htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
    				}
    				//console.log(htmlString);
    				$('#search_sel_area_signgu').html(htmlString).selectpicker('refresh');
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

    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
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
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	var data_mbr_st = $(this).attr('data-mbr-st');
    	var alert_message = "";
    	if(data_mbr_st == 'N') {
    		alert_message = "실제 회원 데이터를 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "회원을 삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
    		form.MBR_ID.value = data_mbr_id;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/member/delete_act.do",
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
    			complete : function() {
    				//console.log('complete!');
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    			}
    		});    		 
    	});
   	});
    
 	//이용정보동의서
    function clk_indvdl_info_view(MBR_ID, MBR_HP){
     	var form = document.getElementById('IndvdlInfoViewForm');
     	console.log(MBR_ID);
     	form.MBR_ID.value = MBR_ID;
     	form.MBR_HP.value = MBR_HP;
     /* var data-rmndr-crs-sn = $(this).attr('data-rmndr-crs-sn');
     	form.RMNDR_CRS_SN.value = data-rmndr-crs-sn; */
     	
     	$.ajax({
  			type:"POST",
  			url :'/eduadm/member/indvdl_info_view.do',
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
			alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.<br><br><span class="red-600">회원상세정보(부가정보)를 포함하기 때문에 데이터는 총 조회 ${paginationInfo.totalRecordCount}건 보다 많을 수 있습니다.</span>',function(){
				document.listForm.action = "";
			});
		  	var form = document.getElementById('listForm');
		  	form.excel_label.value = excel_label;
		  	form.excel_type.value = excel_type;
		  	form.excel_msg.value = val;
		  	form.action = "/all/main/excel/down.do";
		  	form.submit();
		} ,function() {//cancel    			
	
		});
		
	});
</script>


<%@ include file="../tail.jsp" %>
