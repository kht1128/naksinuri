<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

	<script>
	$(function(){
		$('#ctiCallhstryListTotalLabel${CUSTOM_UNIQ_KEY}').html('${paginationInfo.totalRecordCount}');
	});
	//페이징을 위한 함수명 처리
	var fn_script = '<script>																										';
	fn_script += '	function fn_egov_link_page_callhstry${CUSTOM_UNIQ_KEY}(pageNo){													';
	fn_script += '		var form = document.getElementById("ctiCallhstryListForm${CUSTOM_UNIQ_KEY}"); 									';
	fn_script += '		form.pageIndex.value = pageNo;																				';
	fn_script += '		form.action = "";																							';
	fn_script += '		$.ajax({																									';
	fn_script += '			type:"POST",																							';
	fn_script += '			url :"/cti/callhstry/list_ajax_popup.do",																';
	fn_script += '			data:$("#ctiCallhstryListForm${CUSTOM_UNIQ_KEY}").serialize(),											';
	fn_script += '			dataType: "html",																						';
	fn_script += '			contentType: "application/x-www-form-urlencoded;charset=UTF-8",											';
	fn_script += '			async: true,																							';
	fn_script += '			beforeSend : function(xhr, opts) {																		';
	fn_script += '				$("#ctiCallhstryList${CUSTOM_UNIQ_KEY}").html(ajaxLoadingHtmlTag());	';
	fn_script += '			},																										';
	fn_script += '			success: function(data, status, xhr) {																	';
	fn_script += '				$("#ctiCallhstryList${CUSTOM_UNIQ_KEY}").html(data);												';
	fn_script += '			},																										';
	fn_script += '			complete : function() {																					';
	fn_script += '																						';
	fn_script += '			},																										';
	fn_script += '			error: function(jqXHR, textStatus, errorThrown) {														';
	fn_script += '			}																										';
	fn_script += '		});   																										';    	
	fn_script += '	} 																												';
	fn_script += ' <\/script>																										'; 
	$('#ctiCallhstryList${CUSTOM_UNIQ_KEY}').append(fn_script);
	//End of 페이징을 위한 함수명 처리
	</script>

	<!-- table:start -->
	<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
	<colgroup>
	</colgroup>
	<thead>
		<tr>
			<th class="text-middle text-center">순번</th>
			<th class="text-middle text-center">일시</th>
			<th class="text-middle text-center">상담사</th>
			<th class="text-middle text-center">상담 대분류</th>
			<th class="text-middle text-center">상담 소분류</th>
			<th class="text-middle text-center">비고</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty list}">
			<tr><td colspan="6" class="text-center table-active">조회 된 이력이 없습니다.</td></tr>  
		</c:if>
		<c:forEach var="item" varStatus="status" items="${list}">
		
			<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
				<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
				<td class="text-middle text-center">
					<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd"/>					
				</td>
				<td class="text-middle text-center">
					<c:choose>
						<c:when test="${empty item.STAFF_MBR_NM}">미등록계정(${item.STAFF_MBR_ID})</c:when>
						<c:otherwise>${item.STAFF_MBR_NM}</c:otherwise>
					</c:choose>				
				</td>
				<td class="text-middle text-center">${item.HCALL_GUBUN_1_NM}</td>
				<td class="text-middle text-center">${item.HCALL_GUBUN_2_NM}</td>
				<td class="text-middle text-center">
					<a href="#;" class="btn btn-icon btn-default btn-xs btn-act-dial-tot-search" data-toggle="tooltip" data-original-title="수정" 
                		data-call-number="" 
						data-call-cd="${item.CALL_CD}"
						data-call-sn="${item.HCALL_SN}"
						data-call-ivr-cd="">
                		<i class="icon wb-wrench" aria-hidden="true"></i>
                	</a>
                	<a href="#;" class="btn btn-icon btn-default btn-xs btn-act-dial-tot-delete" data-toggle="tooltip" data-original-title="삭제" 
                		data-call-number="" 
						data-call-cd="${item.CALL_CD}"
						data-call-sn="${item.HCALL_SN}"
						data-custom-uniq-key="${CUSTOM_UNIQ_KEY}">
                		<i class="icon wb-trash" aria-hidden="true"></i>
                	</a>
				</td>
			</tr> 
		</c:forEach>
	</tbody>
	<tfoot>
		<tr class="footable-paging">
			<td colspan="6">
				<div class="footable-pagination-wrapper">
					<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_callhstry${CUSTOM_UNIQ_KEY}" />
						<form:form commandName="ctiCallHistoryVO" id="listformcallhstry${CUSTOM_UNIQ_KEY}" name="listformcallhstry${CUSTOM_UNIQ_KEY}" method="post">
						<form:hidden path="pageIndex" />
						</form:form>
					</ul>	 								
				</div>
			</td>
		</tr>
	</tfoot>				  
	</table>
	<!-- table:end -->
	