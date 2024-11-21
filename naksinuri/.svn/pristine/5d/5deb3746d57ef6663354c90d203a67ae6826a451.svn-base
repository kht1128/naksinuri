<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.modal-dialog{
    min-width: 1000px;   
}
</style>

<!-- table:start -->
<div class="example-tooltip">
	<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
		<div class="arrow"></div>
		<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount} 건</div>
	</div>
</div>
<table id="datalist" class="table table-hover footable footable-paging footable-paging-center">
	<colgroup>
	</colgroup>
   	<thead>
      	<tr>
			<th class="text-middle text-center">이름</th>
           	<th class="text-middle text-center">연락처</th>
           	<th class="text-middle text-center">생년월일</th>
           	<th class="text-middle text-center">선박 또는 상호명</th>
           	<th class="text-middle text-center">지역(시도)</th>
           	<th class="text-middle text-center">지역(시군구)</th>
           	<th class="text-middle text-center">상세정보</th>
           	<th class="text-middle text-center">비고</th>
		</tr>
       	</thead>
	<tbody>
	<c:if test="${empty list}">
		<tr><td colspan="8" class="text-center table-active">조회된 회원이 없습니다.</td></tr>  
	</c:if>
	<c:forEach var="item" varStatus="status" items="${list}">
	<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		<td class="text-middle">
			<c:if test="${item.MBR_ST ne 'Y'}">
				<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="9999"></i>
			</c:if>${item.MBR_NM}</td>
		<td class="text-middle text-center">${item.MBR_HP}</td>
		<td class="text-middle text-center">${item.MBR_BIRTH}</td>
		<c:choose>
			<c:when test="${not empty item.DTL_NM}">
				<td class="text-middle text-center">${item.DTL_NM}</td>
				<td class="text-middle text-center">${item.SIDO_NM}</td>
				<td class="text-middle text-center">${item.SIGNGU_NM}</td>
			</c:when>
			<c:otherwise>
				<td class="text-middle text-center" colspan="3">등록 된 상세 정보가 없습니다.</td>
			</c:otherwise>	
		</c:choose>
		<td class="text-middle text-center">
			<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
		</td>
		<td class="text-middle text-center">
			<a href="#" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_add_member" data-toggle="tooltip" data-original-title="선택" data-mbr-id="${item.MBR_ID}">
				<i class="icon wb-plus text-danger" aria-hidden="true"></i>
			</a>
		</td>
	</tr> 
	</c:forEach>
	</tbody>
	<tfoot>
		<tr class="footable-paging">
			<td colspan="8">
				<div class="footable-pagination-wrapper">
					<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_2" />
						<form:form commandName="eduMemberVO" id="listForm3" name="listForm3" method="post">
						<form:hidden path="pageIndex" />
						</form:form>
					</ul>	 								
				</div>
			</td>
		</tr>
	</tfoot>				  
</table>
<!-- table:end -->
<div class="form-group row">
	<div class="col-md-12">
		<input type="text" class="form-control tokenfield2_manual" id="MBR_IDS" name="MBR_ID" placeholder="" autocomplete="off" value="${mbrids}" required>
		<label class="form-control-label text-danger text-left font-size-12">회원을 선택하시면 이곳에 자동으로 추가됩니다.<br>선택 후 삭제한 회원은 재검색 하시면 다시 불러올 수 있습니다.<br><i class="site-menu-icon wb-eye-close" aria-hidden="true"></i>회원을 추가시 자동으로 활성화 상태로 변경됩니다.</label>
	</div>
</div>
<!-- ajax_member_list:end -->

<script>
$(function(){
	$('.tokenfield2_manual').tokenfield();	
});
$(".clk_add_member").click(function() {
	var mbrids = $('#MBR_IDS').val().split(',');
	var mbr_id = $(this).attr('data-mbr-id');
	var rawdata = new Array();
	for(var i=0; i<mbrids.length; i++) {
		var rawObj = {
			value : mbrids[i],
			label : mbrids[i],
		}
		rawdata.push(rawObj);
	}
	var rawObj = {
		value : mbr_id,
		label : mbr_id,
	}
	rawdata.push(rawObj);
	$('.tokenfield2_manual').tokenfield('setTokens', rawdata);
	$(this).remove();
});
</script>
