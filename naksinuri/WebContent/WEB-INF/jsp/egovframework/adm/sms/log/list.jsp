<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<%-- <form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form> --%>

<form:form commandName="smsSendVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="MID" value="" />
</form:form>

<form:form commandName="eduMemberVO" id="ajaxForm" name="ajaxForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_NM" value=""/>
<input type="hidden" name="LOG_UPD_MSG" value=""/>
<input type="hidden" name="DEL_NOW" value=""/>
<input type="hidden" name="GNRL_MBER" value="Y"/>
<input type="hidden" name="MBR_SCRTY_KEY" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="listForm" name="listForm" method="post">
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
	    			<h3 class="panel-title">메세지 이력 검색&nbsp;&nbsp;
	      				&nbsp;<small></small>
	    			</h3>
	  			</div>
	  			<div class="panel-body">
	  			
	  				<div class="input-group col-lg-12 p-0">
						<%-- <div class="btn-group col-lg-2 p-0">
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
						<div class="btn-group col-lg-2 p-0 pl-10">
							<input type="text" class="form-control" name="DTL_NM" placeholder="낚시터/어선명" autocomplete="off" value="${DTL_NM}" data-toggle="tooltip" data-original-title="낚시터 또는 어선명을 입력하세요.">
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<input type="text" class="form-control" name="MBR_NM" placeholder="이름" autocomplete="off" value="${MBR_NM}" data-toggle="tooltip" data-original-title="이름을 입력하세요.">
						</div> --%>
						<div class="col-lg-2 p-0">
							<input type="text" class="form-control blue-600 datepickerModalStrSearch" name="SEND_DATE_STR" placeholder="전송일자~부터" autocomplete="off" value="${SEND_DATE_STR}" data-toggle="tooltip" data-original-title="시작일자를 선택하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control blue-600 datepickerModalEndSearch" name="SEND_DATE_END" placeholder="전송일자~까지" autocomplete="off" value="${SEND_DATE_END}" data-toggle="tooltip" data-original-title="종료일자를 선택하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchCondition" required>
			           			<option value="" <c:if test="${empty searchCondition}">selected</c:if>>발송여부</option>
								<option value="0" <c:if test="${searchCondition eq '0'}">selected</c:if>>대기</option>
								<option value="1" <c:if test="${searchCondition eq '1'}">selected</c:if>>발송</option>
								<option value="2" <c:if test="${searchCondition eq '2'}">selected</c:if>>실패</option>
								<option value="4" <c:if test="${searchCondition eq '4'}">selected</c:if>>실패(잔액부족)</option>
								<option value="5" <c:if test="${searchCondition eq '5'}">selected</c:if>>예약(발송전)</option>
								<option value="600" <c:if test="${searchCondition eq '600'}">selected</c:if>>예약(발송후)</option>
			     			</select>
						</div>
					</div>	  
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0">
							<input type="text" class="form-control" name="R_PHONE" placeholder="연락처(휴대폰)" autocomplete="off" value="${R_PHONE}" data-toggle="tooltip" data-original-title="연락처(휴대전화)를 입력하세요." numberOnly>	
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
						<div class="col-lg-6 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control" name="searchKeyword" placeholder="이름,아이디,메세지 제목,메세지 내용을 입력하세요." autocomplete="off" value="${searchKeyword}">
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
	          			<col width="70"/>	 
	          			<col width="120"/>
						<col width="120"/>
						<col width="200"/>
						<col width="150"/>
						<col width="120"/>
						<col width=""/>
	          			<%-- <col width="70"/> --%>	          			
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center ">No</th>
	          				<th class="text-middle text-center ">일시</th>
	          				<th class="text-middle text-center ">발송아이디</th>
	          				<th class="text-middle text-center ">발송여부</th>
	          				<th class="text-middle text-center ">이름</th>
	          				<th class="text-middle text-center ">생년월일</th>
	          				<th class="text-middle text-center ">연락처</th>
	          				<th class="text-middle text-center ">회원상세정보</th>
	          				<th class="text-middle text-center ">메세지 내용</th>
							<!-- <th class="text-middle text-center ">비고</th> -->	
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
		            			<td class="text-middle text-center">${cellnum }</td>
		            			<%-- <td class="text-middle text-center">	
		            				<c:if test="${item.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash red-600" aria-hidden="true" data-content="현재 삭제 된 게시물입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
									
		            			</td> --%>
		            			<td class="text-middle text-center">
		            				<fmt:parseDate var="parseregdatestring" value="${item.REG_DATE }" pattern="yyyy-MM-dd HH:mm:ss" />
         							<fmt:formatDate var="reg_dt" value="${parseregdatestring}" pattern="yyyy.MM.dd HH:mm" />
         							<c:out value="${reg_dt }"/><br>
		            			</td>
		            			<td class="text-middle text-center">${item.REG_MBR_ID }</td>
		            			<td class="text-middle text-center">
		            				<c:choose>
		            					<c:when test="${item.STAT eq '0'}">대기</c:when>
		            					<c:when test="${item.STAT eq '1' and empty item.SEND_DATE}">발송</c:when>
		            					<c:when test="${item.STAT eq '2'}">
		            						실패<br>
		            						<c:if test="${item.RST_CODE eq 2000 }">(포맷 에러)</c:if>
		            						<c:if test="${item.RST_CODE eq 2001 }">(잘못된 번호)</c:if>
		            						<c:if test="${item.RST_CODE eq 2002 }">(컨텐츠 사이즈 및 개수 초과)</c:if>
		            						<c:if test="${item.RST_CODE eq 2003 }">(잘못된 컨텐츠)</c:if>
		            						<c:if test="${item.RST_CODE eq 3000 }">(기업형 MMS 미지원 단말기)</c:if>
		            						<c:if test="${item.RST_CODE eq 3001 }">(단말기 메시지 저장개수 초과)</c:if>
		            						<c:if test="${item.RST_CODE eq 3002 }">(전송시간 초과)</c:if>
		            						<c:if test="${item.RST_CODE eq 3004 }">(전원 꺼짐)</c:if>
		            						<c:if test="${item.RST_CODE eq 3005 }">(음영지역)</c:if>
		            						<c:if test="${item.RST_CODE eq 3006 }">(기타)</c:if>
		            						<c:if test="${item.RST_CODE eq 4000 }">(서버문제로 인한 접수 실패)</c:if>
		            						<c:if test="${item.RST_CODE eq 4001 }">(단말기 일시 서비스 정지)</c:if>
		            						<c:if test="${item.RST_CODE eq 4002 }">(통신사 내부 실패(무선망단))</c:if>
		            						<c:if test="${item.RST_CODE eq 4003 }">(서비스의 일시적인 에러)</c:if>
		            						<c:if test="${item.RST_CODE eq 4101 }">(계정 차단)</c:if>
		            						<c:if test="${item.RST_CODE eq 4102 }">(허용되지 않은 IP 접근)</c:if>
		            						<c:if test="${item.RST_CODE eq 4104 }">(건수 부족)</c:if>
		            						<c:if test="${item.RST_CODE eq 4201 }">(국제 MMS 발송 권한이 없음)</c:if>
		            						<c:if test="${item.RST_CODE eq 5000 }">(번호이동에러)</c:if>
		            						<c:if test="${item.RST_CODE eq 5001 }">(선불발급 발송건수 초과)</c:if>
		            						<c:if test="${item.RST_CODE eq 5003 }">(스팸)</c:if>
		            						<c:if test="${item.RST_CODE eq 5201 }">(중복된 키 접수 차단)</c:if>
		            						<c:if test="${item.RST_CODE eq 5202 }">(중복된 수신번호 접수 차단)</c:if>
		            						<c:if test="${item.RST_CODE eq 5301 }">(사전 미등록 발신번호 사용)</c:if>
		            						<c:if test="${item.RST_CODE eq 5302 }">(전화번호 세칙 미준수 발신번호 사용)</c:if>
		            						<c:if test="${item.RST_CODE eq 5303 }">(발신번호 변작으로 등록된 발신번호 사용)</c:if>
		            						<c:if test="${item.RST_CODE eq 5304 }">(번호도용문자차단서비스에 가입된 발신번호 사용)</c:if>
		            						<c:if test="${item.RST_CODE eq 9001 }">(발송 미허용 시간 때 발송 실패)</c:if>
		            						<c:if test="${item.RST_CODE eq 9002 }">(폰 넘버 에러)</c:if>
		            						<c:if test="${item.RST_CODE eq 9003 }">(스팸 번호(스팸 테이블 사용시))</c:if>
		            						<c:if test="${item.RST_CODE eq 9004 }">(이통사에서 응답 없음)</c:if>
		            						<c:if test="${item.RST_CODE eq 9005 }">(파일크기 오류)</c:if>
		            						<c:if test="${item.RST_CODE eq 9006 }">(지원되지 않는 파일)</c:if>
		            						<c:if test="${item.RST_CODE eq 9007 }">(파일오류)</c:if>
		            						<c:if test="${item.RST_CODE eq 9008 }">(MMS_MSG 의 MSG_TYPE 값이 잘못되었음)</c:if>
		            						<c:if test="${item.RST_CODE eq 9009 }">(환경설정파일의 mms.dup.check.seconds 에 적용 시간(초) 이내에 수신자번호+메시지내용 이 중복인 경우 실패처리)</c:if>
		            						<c:if test="${item.RST_CODE eq 9010 }">(재전송 횟수 초과로 실패)</c:if>
		            						<c:if test="${item.RST_CODE eq 9011 }">(발송 지연으로 인한 실패)</c:if>
		            					</c:when>
		            					<c:when test="${item.STAT eq '4'}">실패<br>(잔액부족)</c:when>
		            					<c:when test="${item.STAT eq '5' and not empty item.SEND_DATE}">예약(발송전)</c:when>
		            					<c:when test="${item.STAT eq '1' and not empty item.SEND_DATE}">예약(발송후)</c:when>
		            					<c:otherwise></c:otherwise>
		            				</c:choose>
		            				<%-- <c:choose>
		            					<c:when test="${item.STAT eq }"
		            				</c:choose> --%>
		            			</td>
		            			<td class="text-middle text-center">${item.MBR_NM }</td>
		            			<td class="text-middle text-center">${item.MBR_BIRTH }</td>
		            			<td class="text-middle text-center"><c:set var="aa" value="${fn:replace(item.r_PHONE,'-','')}"/><c:out value="${aa}"/></td>
		            			<td class="text-middle text-center">
		            				<%-- <a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a> --%>
									<a href="#;" class="btn btn-outline btn-success btn-xs" onclick="clk_mod_data('${item.MBR_ID}','${item.MBR_SCRTY_KEY}')">회원정보수정</a>
		            			</td>
		            			<td class="text-middle text-center">
		            				<c:if test="${fn:length(item.MSG) gt 20}">
							        	<c:out value="${fn:substring(item.MSG, 0, 70)}"/>
							        	<% pageContext.setAttribute("enter", "\n\n"); %>
							        	<i class="icon wb-more-horizontal btn btn-act-txt-more red-600" data-txt-more="${fn:replace(item.MSG, enter, '<br/>' )}"></i>			        	
							        </c:if>
							        <c:if test="${fn:length(item.MSG) lt 20}">${item.MSG}</c:if>
		            			</td>
		            			<%-- <td class="text-middle text-center">
		            				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
				                		data-key="${item.MID}" 
	                  					data-del-st="${item.DEL_ST}">
				                		<i class="icon wb-trash" aria-hidden="true"></i>
				                	</a>                 				
		            			</td> --%>		            				
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

/* function clk_mbr_view(mbr_id) {
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
} */
$('.btn-act-txt-more').click(function(){
	alertify.alert($(this).attr('data-txt-more'));	 
});
function clk_mod_data(mbr_id,mbr_scrty_key) {
	
	if(!mbr_id) {
		alertify.alert("해당 아이디는 삭제된 회원입니다.");
		return;
	}
	
	var form = document.getElementById('ajaxForm');
   	form.MBR_ID.value = mbr_id;
   	form.MBR_SCRTY_KEY.value = mbr_scrty_key;
   	form.DEL_NOW.value = '';
   	form.action = '';
   	$.ajax({
		type:"POST",
		url :'/eduadm/member/modify.do',
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
}
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

