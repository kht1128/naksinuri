<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../../seadm/head.jsp" %> 
		<%@ include file="../../../seadm/left_menu.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../../eduadm/head.jsp" %> 
		<%@ include file="../../../eduadm/left_menu.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../../head.jsp" %> 
		<%@ include file="../../left_menu.jsp" %>
	</c:otherwise>
</c:choose>

<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="MID" value="" />
</form:form>


<form:form commandName="smsSendVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchCondition" value="Y" />
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

        </div>
      </div>
      
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
          
          	<!-- 검색폼 -->
          	<div class="panel mb-20">
          		<div class="panel-heading">
	    			<h3 class="panel-title">예약 메세지 검색&nbsp;&nbsp;
	      				&nbsp;<small></small>
	    			</h3>
	  			</div>
	  			<div class="panel-body">
	  			
	  				<div class="input-group col-lg-12 p-0">
						<div class="btn-group col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="SIDO_CD" >
								<option value="" >시도 전체</option>
								<c:forEach var="item_category" items="${list_address_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq SIDO_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="SIGNGU_CD" >
								<option value="" >시군구 전체</option>
								<c:forEach var="item_category" items="${list_address_signgu_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq SIGNGU_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10 ">
							<input type="text" class="form-control" name="DTL_NM" placeholder="낚시터/어선명" autocomplete="off" value="${DTL_NM}" data-toggle="tooltip" data-original-title="낚시터 또는 어선명을 입력하세요.">
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<input type="text" class="form-control" name="MBR_NM" placeholder="이름" autocomplete="off" value="${MBR_NM}" data-toggle="tooltip" data-original-title="이름을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control" name="MBR_BIRTH" placeholder="생년월일" autocomplete="off" value="${MBR_BIRTH}" data-toggle="tooltip" data-original-title="생년월일을 입력하세요." numberOnly>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control" name="MBR_HP" placeholder="연락처(휴대폰)" autocomplete="off" value="${MBR_HP}" data-toggle="tooltip" data-original-title="연락처(휴대전화)를 입력하세요." numberOnly>	
						</div>
					</div>	  
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0">
							<input type="text" class="form-control blue-600 datepickerModalStrSearch" name="SEND_DATE_STR" placeholder="전송예약일자" autocomplete="off" value="${SEND_DATE_STR}" data-toggle="tooltip" data-original-title="시작일자를 선택하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control blue-600 datepickerModalEndSearch" name="SEND_DATE_END" placeholder="전송예약일자" autocomplete="off" value="${SEND_DATE_END}" data-toggle="tooltip" data-original-title="종료일자를 선택하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SMS_MENT_DTL_CD" required>
			           			<option value="" >메세지대상선택</option>
								<c:forEach var="item_category" items="${list_target_se_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq SMS_MENT_DTL_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	        			
			     			</select>
						</div>
		      			<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control " name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-4 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control" name="searchKeyword" placeholder="메세지 내용을 입력하세요." autocomplete="off" value="${searchKeyword}">
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
	          			<col width="5%"/>	 
	          			<col width="3%"/>	          	
	          			<col width="100px"/>
						<col width="100px"/>
						<col width="100px"/>
						<col width="100px"/>
						<col width="100px"/>
						<col width=""/>
	          			<col width="100px"/>	          			
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center " colspan="2">No</th>
	          				<th class="text-middle text-center ">예약일시</th>
	          				<th class="text-middle text-center ">이름</th>
	          				<th class="text-middle text-center ">생년월일</th>
	          				<th class="text-middle text-center ">연락처</th>
	          				<th class="text-middle text-center ">회원상세정보</th>
	          				<th class="text-middle text-center ">메세지 내용</th>
							<th class="text-middle text-center ">비고</th>	
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="9"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 된 내용이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            		<c:set var="cellnum" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/>	            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">
		            				${cellnum }
		            			</td>
		            			<td class="text-middle text-center">	
		            				<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash red-600" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
		            			</td>
		            			<td class="text-middle text-center">${item.SEND_DATE }</td>
		            			<td class="text-middle text-center">${item.MBR_NM }</td>
		            			<td class="text-middle text-center">${item.MBR_BIRTH }</td>
		            			<td class="text-middle text-center">${item.MBR_HP }</td>
		            			<td class="text-middle text-center">
		            				<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
		            			</td>
		            			<td class="text-middle text-center">
		            				<c:choose>
			            				<c:when test="${fn:length(item.MSG) > 100}">
			            					${fn:substring(item.MSG,0,100)}<br>...<br>
			            					<a href="#;" class="btn btn-outline btn-default btn-sm clk_msg_view" data-msg="${item.MSG }"<%-- onclick="clk_msg_view('${item.MSG}')" --%>>메세지 내용 자세히보기</a>
			            				</c:when>
			            				<c:otherwise>
			            					${item.MSG}
			            				</c:otherwise>
		            				</c:choose>
		            			</td>
		            			<td class="text-middle text-center">
		            				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-key="${item.MID}">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
		            				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                		data-key="${item.MID}" 
	                  					data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>                 				
		            			</td>		            				
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
function pageMove(obj) {
	var form = document.getElementById('listForm');
	form.action = "/adm/sms/listMent{addWebLink}.do";
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
$(".clk_del_data").click(function() {
	var key = $(this).attr('data-key');
	var del_st = $(this).attr('data-del-st');
	var alert_message = "";
	if(del_st == '1') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	//
	alertify.confirm(alert_message, function(){ 
	  	//ok
		var form = document.getElementById('viewForm');
		form.MID.value = key;
    	form.action = '';
    	$.ajax({
			type:"POST",
			url :"/adm/sms/resve/delete_act.do",
			data:$('#viewForm').serialize(),
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
						alertify.alert(data.msg, function(){
							document.listForm.submit();
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
	});
});	
$(".clk_mod_data").click(function() {
	var key = $(this).attr('data-key');
	var form = document.getElementById('viewForm');
	form.MID.value = key;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/adm/sms/resve/modify.do",
		data:$('#viewForm').serialize(),
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

function clk_msg_view() {
	$("#admPublicPanelLayer").html('');
	$("#admPublicPanelLayer").fadeOut('200');
	//alertify.alert(msg);
}
$(".clk_msg_view").click(function() {
	var msg = $(this).attr('data-msg');
	console.log(msg);
	//alertify.alert(msg);
	var htmlString = '';//'<textarea>'+msg+'</textarea>';
	htmlString += '<div class="panel panel-dark card card-dark card-shadow border border-dark " style="width:600px;">';
	htmlString += '		<div class="panel-heading panel-move  draggable-move cursor-grab ">';
	htmlString += '			<span class="panel-title" style="padding:10px 20px !important;">메세지 상세보기</span>';
	htmlString += '			<div class="panel-actions pr-0">';
	htmlString += '				<a class="panel-action icon wb-close panel-close m-0" onclick="clk_msg_view();" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a>';
	htmlString += '			</div>';
	htmlString += '		</div>';
	htmlString += '		<div class="panel-body p-10 scroll-y " style="height:700px;">';
	htmlString += '			<textarea class="form-control h-600 text-typing-key-up">'+msg+'</textarea>';
	htmlString += '		</div>';
	htmlString += '		<div class="panel-footer p-10">';
	htmlString += '			<div class="float-right">';
	htmlString += '				<button type="button" class="btn btn-default btn-outline" onclick="clk_msg_view();">취소(닫기)</button>';
	htmlString += '			</div>';
	htmlString += '		</div>';
	htmlString += '</div>';
	
	
	$("#admPublicPanelLayer").html(htmlString);
	$("#admPublicPanelLayer").show();
});
	

</script>


<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../../seadm/tail.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../../eduadm/tail.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../../tail.jsp" %>
	</c:otherwise>
</c:choose>

