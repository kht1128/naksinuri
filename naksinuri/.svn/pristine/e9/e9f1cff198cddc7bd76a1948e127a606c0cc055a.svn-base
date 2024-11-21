<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form commandName="ctiCallHistoryVO" id="listFormCallhstry" name="listFormCallhstry" method="post">
<input type="hidden" name="MBR_HP" value=""/>
<input type="hidden" name="CALL_CD" value=""/>
<input type="hidden" name="HCALL_SN" value=""/>
<input type="hidden" name="HCALL_IVR_CD" value=""/>
<input type="hidden" name="CUSTOM_UNIQ_KEY" value=""/>
<input type="hidden" name="LOG_UPD_MSG" value=""/>
</form:form>


<div class="panel-body pt-0 pb-0">
	<span class="badge badge-pill badge-warning badge-custom-warning orange-600">전체 ${paginationInfo.totalRecordCount} 건</span>
	<button type="button" class="btn btn-icon btn-warning btn-xs btn-round pl-10 pr-10 btn-act-dial-tot-search" 
		data-call-number="" 
		data-call-cd=""
		data-call-sn="">
		<i class="fa fa-arrow-right" aria-hidden="true"></i> 상담(통화)이력 직접등록하기
	</button>
</div>

<div class="panel-body pt-20">
	<!-- table:start -->
	<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
	<colgroup>
	</colgroup>
	<thead>
		<tr>
			<th class="text-middle text-center">순번</th>
			<th class="text-middle text-center">분류</th>
			<th class="text-middle text-center">상담사</th>
			<th class="text-middle text-center">내선번호</th>
			<th class="text-middle text-right">발신번호(거신분)</th>
			<th class="text-middle text-right">수신번호(받는분)</th>
			<th class="text-middle text-center">콜상태</th>
			<th class="text-middle text-center">통화시간</th>
			<th class="text-middle text-center">고객명</th>
			<th class="text-middle text-center">상담일</th>
			<th class="text-middle text-center">상담요청IVR</th>
			<th class="text-middle text-center">비고</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty list}">
			<tr><td colspan="12" class="text-center table-active">조회 된 통화이력이 없습니다.</td></tr>  
		</c:if>
		<c:forEach var="item" varStatus="status" items="${list}">
		
			<c:set var="isRealCall" value="false"/>
			<c:if test="${item.HCALL_TYPE eq '수신' or item.HCALL_TYPE eq '발신'}">
				<c:set var="isRealCall" value="true"/>
			</c:if>
	
			<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
				<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
				<td class="text-middle text-center">${item.HCALL_TYPE}</td>
				<td class="text-middle text-center">
					<c:choose>
						<c:when test="${empty item.STAFF_MBR_NM}">미등록계정(${item.STAFF_MBR_ID})</c:when>
						<c:otherwise>${item.STAFF_MBR_NM}</c:otherwise>
					</c:choose>				
				</td>
				<td class="text-middle text-center">${item.EXTNO}</td>
				<c:choose>
					<c:when test="${isRealCall eq true}">
						<td class="text-middle text-right"">
							<a href="#;" class="btn-act-dial-call-number" data-call-number="${item.HCALL_S_TEL}">${item.HCALL_S_TEL}</a>&nbsp;
							<a href="#;" class="btn btn-xs btn-dark white btn-act-dial-tot-search" 
							data-call-number="${item.HCALL_S_TEL}" 
							data-call-cd=""
							data-call-sn=""
							data-call-ivr-cd="">통합검색</a>
						</td>
						<td class="text-middle text-right"">
							<a href="#;" class="btn-act-dial-call-number" data-call-number="${item.HCALL_R_TEL}">${item.HCALL_R_TEL}</a>&nbsp;
							<a href="#;" class="btn btn-xs btn-dark white btn-act-dial-tot-search" 
							data-call-number="${item.HCALL_R_TEL}" 
							data-call-cd=""
							data-call-sn=""
							data-call-ivr-cd="">통합검색</a>
						</td>
						<td class="text-middle text-center">${item.HCALL_ST }</td>
						<td class="text-middle text-center">
							<fmt:parseNumber var="calc_min" value="${item.HCALL_TIME/60}" integerOnly="true"/>
							<fmt:parseNumber var="calc_hour" value="${calc_min/60}" integerOnly="true"/>
							<fmt:parseNumber var="calc_sec" value="${item.HCALL_TIME%60}" integerOnly="true"/>
							<fmt:parseNumber var="calc_min" value="${calc_min%60}" integerOnly="true"/>
							<c:if test="${fn:contains(item.HCALL_ST, '응답')}">
								<fmt:formatNumber minIntegerDigits="2" value="${calc_hour}" type="number"/>:<fmt:formatNumber minIntegerDigits="2" value="${calc_min}" type="number"/>:<fmt:formatNumber minIntegerDigits="2" value="${calc_sec}" type="number"/>
							</c:if>
						</td>
					</c:when>
					<c:otherwise>
						<td class="text-middle text-right">
							<%-- <c:choose>
								<c:when test="${fn:contains(item.HCALL_TYPE, '기타전화')}">
									<i class="icon fa fa-phone-square font-weight-300 font-size-20 blue-grey-400" aria-hidden="true"></i>
								</c:when>	
								<c:otherwise>
									<i class="icon fa fa-blind font-weight-300 font-size-20 blue-grey-400" aria-hidden="true"></i>
								</c:otherwise>
							</c:choose> --%>
							<i class="icon fa fa-edit font-weight-300 font-size-20 blue-grey-400" aria-hidden="true"></i>
						</td>
						<td class="text-middle text-right">
							<a href="#;" class="btn-act-dial-call-number" data-call-number="${item.HCALL_R_TEL}">${item.HCALL_R_TEL}</a>&nbsp;
							<a href="#;" class="btn btn-xs btn-dark white btn-act-dial-tot-search" 
							data-call-number="${item.MBR_HP}" 
							data-call-cd=""
							data-call-sn=""
							data-call-ivr-cd="">통합검색</a>
						</td>
						<td class="text-middle text-center">${item.HCALL_ST }</td>
						<td class="text-middle text-center">-</td>
					</c:otherwise>
				</c:choose>
				<td class="text-middle text-center">
					<c:set var="MBR_NM" value=""/>
					<c:choose>
						<c:when test="${item.IS_JOIN_MBR eq 'Y' and item.IS_CTI_MBR eq 'Y'}"><%-- 메인회원 O , 상담회원 O --%>
							<c:if test="${not empty item.CTI_MBR_NM}">
								<c:set var="MBR_NM" value="${item.CTI_MBR_NM}"/>
							</c:if>
							<c:if test="${empty item.CTI_MBR_NM}">
								<c:if test="${not empty item.CNSLT_MBR_NM}">
									<c:set var="MBR_NM" value="${item.CNSLT_MBR_NM}(CTI연락처만일치)"/>
								</c:if>
								<c:if test="${empty item.CNSLT_MBR_NM}">
									<c:if test="${not empty item.JOIN_MBR_NM}">
										<c:set var="MBR_NM" value="${item.JOIN_MBR_NM}(회원정보매칭안됨,CTI연락처만일치)"/>
									</c:if>
									<c:if test="${empty item.JOIN_MBR_NM}">
										<c:set var="MBR_NM" value="이름정보없음(CTI연락처만일치)"/>
									</c:if>									
								</c:if>	
							</c:if>
						</c:when>
						<c:when test="${item.IS_JOIN_MBR eq 'Y' and item.IS_CTI_MBR eq 'N'}"><%-- 메인회원 O , 상담회원 X --%>
							<c:if test="${not empty item.JOIN_MBR_NM}">
								<c:set var="MBR_NM" value="${item.JOIN_MBR_NM}"/>
							</c:if>
							<c:if test="${empty item.JOIN_MBR_NM}">
								<c:if test="${not empty item.JOIN_HP_MBR_NM }">
									<c:set var="MBR_NM" value="${item.JOIN_HP_MBR_NM}(CTI연락처만일치)"/>
								</c:if>
								<c:if test="${empty item.JOIN_HP_MBR_ID }">
									<c:set var="MBR_NM" value="이름정보없음(CTI연락처만일치)"/>
								</c:if>
							</c:if>							
						</c:when>
						<c:when test="${item.IS_JOIN_MBR eq 'N' and item.IS_CTI_MBR eq 'Y'}"><%-- 메인회원 X , 상담회원 O --%>
							<c:if test="${not empty item.CNSLT_MBR_NM}">
								<c:set var="MBR_NM" value="${item.CNSLT_MBR_NM}(비회원)"/>
							</c:if>							
						</c:when>
						<c:otherwise><c:set var="MBR_NM" value="미등록"/></c:otherwise>											
					</c:choose>
					<c:out value="${MBR_NM}"/><c:if test="${empty MBR_NM}">미등록</c:if>
					<c:if test="${not empty item.MBR_ID }">
						<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('<c:out value="${item.MBR_ID}"/>')">자세히보기</a>
					</c:if>
				</td>
				<td class="text-middle text-center">
					<fmt:parseDate value="${fn:replace(item.HCALL_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy.MM.dd HH:mm:ss"/>					
				</td>
				<td class="text-middle text-center">${item.HCALL_IVR}</td>
				<td class="text-middle text-center">
					<a href="#;" class="btn btn-icon btn-default btn-xs btn-act-dial-tot-search" data-toggle="tooltip" data-original-title="수정" 
                		data-call-number="" 
						data-call-cd="${item.CALL_CD}"
						data-call-sn="${item.HCALL_SN}"
						data-call-ivr-cd="">
                		<i class="icon wb-wrench" aria-hidden="true"></i>
                		<!-- <i class="icon wb-eye" aria-hidden="true"></i> -->
                	</a>
					<c:choose>
						<c:when test="${isRealCall eq true}">
							<c:if test="${fn:contains(item.HCALL_ST, '응답')}">
								<a href="javascript:void(0)" class="btn btn-icon btn-success btn-xs white clk_rec_play" data-toggle="tooltip" data-original-title="녹취파일" 
									data-callid="${item.CALL_CD}"
									data-extno="${item.EXTNO}"
									data-recfilename="${item.REC_FILE_NM}">
			                		<i class="icon wb-volume-high" aria-hidden="true"></i>
			                	</a>			                	
							</c:if>	
						</c:when>
						<c:otherwise>
							<%--
							<a href="#;" class="btn btn-icon btn-default btn-xs btn-act-del-direct-callhstry" data-hcall-sn="${item.HCALL_SN}">삭제하기</a>
							 --%>
						</c:otherwise>
					</c:choose>
				</td>
			</tr> 
		</c:forEach>
	</tbody>
	<tfoot>
		<tr class="footable-paging">
			<td colspan="12">
				<div class="footable-pagination-wrapper">
					<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_callhstry" />
						<form:form commandName="ctiCallHistoryVO" id="listForm3" name="listForm3" method="post">
						<form:hidden path="pageIndex" />
						</form:form>
					</ul>	 								
				</div>
			</td>
		</tr>
	</tfoot>				  
	</table>
	<!-- table:end -->
		
</div>

<script>
var cti_rec_file_url = '${cti_rec_file_url}';
//회원상세정보보기
function clk_mbr_view(mbr_id) {
   	$.ajax({
   		type:"POST",
   		url :'/eduadm/member/view.do',
   		data:{
   			MBR_ID:mbr_id,
   		},
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
//녹음파일 재생
$('.clk_rec_play').click(function(){
	fn_vlgLogin();	
	
	var callid = $(this).attr('data-callid');
	var extno = $(this).attr('data-extno');
	var recfilename = $(this).attr('data-recfilename');
	if(recfilename=='') {
		alertify.alert('실행 할 수 없는 파일입니다.');
		return;
	}
	//alert(recfilename);
	//window.open(cti_rec_file_url+"?USERID=U"+extno+"&"+recfilename+"&menucd=VLG200");
	window.open(cti_rec_file_url+"?vo_log_id=U"+extno+"&"+recfilename+"&menuid=VLG200&vlg_tnnt_id=critters");
});
//이력 삭제
$(document).off("click", ".btn-act-dial-tot-delete");
$(document).on("click",".btn-act-dial-tot-delete",function() {	
	var call_cd = $(this).attr('data-call-cd');
	var hcall_sn = $(this).attr('data-call-sn');
	var idkey = $(this).attr('data-custom-uniq-key');
	alertify.prompt('삭제 후 복구가 불가능합니다.<br>삭제하려는 사유를 입력해주세요.',function(val, e) {
		//ok
		if(val.trim()=='') {
			alertify.alert('사유를 정확히 입력해주셔야 합니다.');
			return;
		} 
		//ok
		var form = document.getElementById('listFormCallhstry');
		form.MBR_HP.value = '';
		form.CALL_CD.value = call_cd;
		form.HCALL_SN.value = hcall_sn;
		form.CUSTOM_UNIQ_KEY.value = '';
		form.LOG_UPD_MSG.value = val;
		form.HCALL_IVR_CD.value = '';
		form.action = '';
		$.ajax({
			type:"POST",
			url :"/cti/callhstry/delete_act.do",
			data:$('#listFormCallhstry').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				notificationTopAlert('이력 삭제 처리가 완료 되었습니다.');
					//삭제 후 팝업 페이징 갱신
					$.ajax({
			    		type:"POST",
			    		url :"/cti/callhstry/list_ajax_popup.do",
			    		data:$('#ctiCallhstryListForm'+idkey).serialize(),
			    		dataType: 'html',//"json",
			    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			    		async: true,
			    		beforeSend : function(xhr, opts) {
			    			$("#ctiCallhstryList"+idkey).html(ajaxLoadingHtmlTag());
			    	    },
			    		success: function(data, status, xhr) {
			    			//console.log('success!');
			    			$("#ctiCallhstryList"+idkey).html(data);
			    		},
			    		complete : function() {
			    			//console.log('complete!');
			    	    },
			    		error: function(jqXHR, textStatus, errorThrown) {
			    			//console.log('error!');
			    			//console.log(jqXHR);
			    			//console.log(textStatus);
			    			//console.log(errorThrown);
			    		}
			    	});  
					//End of 삭제 후 팝업 페이징 갱신
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
//통합검색
$(document).off("click", ".btn-act-dial-tot-search");
$(document).on("click",".btn-act-dial-tot-search",function() {
	var call_number = $(this).attr('data-call-number');
	var call_cd = $(this).attr('data-call-cd');
	var call_sn = $(this).attr('data-call-sn');
	var call_ivr_cd = $(this).attr('data-call-ivr-cd');
	var idkey = getAdmPublicPanelLayerIdkey();
	var form = document.getElementById('listFormCallhstry');
	form.MBR_HP.value = call_number;
	form.CALL_CD.value = call_cd;
	form.HCALL_SN.value = call_sn;
	form.CUSTOM_UNIQ_KEY.value = idkey;
	form.HCALL_IVR_CD.value = call_ivr_cd;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/cti/main/list_total.do",
		data:$('#listFormCallhstry').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			//$("#eduAdmEduPublicModal").html(data);
			//$("#eduAdmEduPublicModal").modal('show');
			newAdmPublicPanelLayer(idkey,data);			
		},
		beforeSend : function() {
   			//console.log('before!');
   		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	}); 
});
//유틸리티 - 시분초 표기
function parseTimeLabel(seconds) {
	var hour = parseInt(seconds/3600);
	var min = parseInt((seconds%3600)/60);
	var sec = seconds%60;
	return pad(hour,2)+":"+pad(min,2)+":"+pad(sec,2);
}
//유틸리티 - 앞에0채우기
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}
</script>
