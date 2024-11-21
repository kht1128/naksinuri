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
	</colgroup>
	<thead>
	  	<tr>
		    <th class="text-center text-middle">민원분류</th>
			<th class="text-center text-middle">처리건수</th>                          
	  	</tr>
	</thead>
	<tbody>
		<c:forEach var="item_row" items="${item.arrItem}" varStatus="status">
		<tr>
			<td class="text-center text-middle">${item_row.LOG_TYPE_NM}</td>
			<td class="text-center text-middle">${item_row.TOT_CNT}</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	<!-- End table -->
	
</div>	
</c:forEach>	

<script>
$(function(){
	$('#analysis_cvpl_report_title').html('( ${searchStrDate} ~ ${searchEndDate} )');
});
</script>
