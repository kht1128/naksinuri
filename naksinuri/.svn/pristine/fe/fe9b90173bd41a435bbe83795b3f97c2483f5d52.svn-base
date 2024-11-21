<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach var="item" items="${list}">
<div class="float-left pl-5 pr-5" style="width:33%;">

	<!-- table -->
	<h5>${item.STAFF_NM }</h5>
	<table class="table table-hover footable footable-paging footable-paging-center ">
	<colgroup>
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<thead>
	  	<tr>
		    <th class="text-center text-middle">IVR</th>
			<th class="text-center text-middle">총건수</th>
			<th class="text-center text-middle">일치건수</th>
			<th class="text-center text-middle">매칭율(%)</th>                          
	  	</tr>
	</thead>
	<tbody>
		<c:forEach var="item_row" items="${item.arrItem}" varStatus="status">
		<tr>
	  		<td class="text-center text-middle">
	  			<c:choose>
	  				<c:when test="${not empty item_row.HCALL_IVR_CD_NM}">${item_row.HCALL_IVR_CD_NM}</c:when>
	  				<c:otherwise>구분없음</c:otherwise>
	  			</c:choose>
	  		</td>
			<td class="text-center text-middle">${item_row.TOT_CNT}</td>
			<td class="text-center text-middle">${item_row.MATCH_CNT}</td>
			<td class="text-center text-middle">${item_row.MATCH_CNT_PT}</td>
		</tr>
		<c:if test="${not empty item.arrItem2 and fn:length(item.arrItem2) > 0}">
		<tr>
			<td class="text-center text-middle blue-grey-400">상담분류 상세</td>
			<td colspan="3">
				<ul class="list-group list-group-dividered list-group-full">
				<c:forEach var="item_row2" items="${item.arrItem2}" varStatus="status2">
					<c:if test="${item_row2.HCALL_IVR_CD eq item_row.HCALL_IVR_CD}">
					<li class="list-group-item">
						<c:choose>
			  				<c:when test="${not empty item_row2.HCALL_GUBUN_1_NM}">${item_row2.HCALL_GUBUN_1_NM}</c:when>
			  				<c:otherwise>구분없음</c:otherwise>
			  			</c:choose>
			  			(${item_row2.TOT_CNT }건)
		  			</li>
		  			</c:if>
				</c:forEach>
                </ul>
			</td>
		</tr>
		</c:if>
		</c:forEach>
	</tbody>
	</table>
	<!-- End table -->
	
</div>	
</c:forEach>	

<script>
$(function(){
	$('#analysis_ivr_compare_report_title').html('( ${searchStrDate} ~ ${searchEndDate} )');
});
</script>
