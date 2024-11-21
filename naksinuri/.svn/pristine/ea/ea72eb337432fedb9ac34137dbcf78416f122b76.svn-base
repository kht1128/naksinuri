<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="panel-body pt-0 pb-0">
	<span class="badge badge-pill badge-warning badge-custom-warning orange-600">전체 ${paginationInfo.totalRecordCount} 건</span>
</div>

<div class="panel-body pt-20">
	<!-- table:start -->
   	<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
    	<colgroup>
    	</colgroup>
    	<thead>
        <tr>
			<th class="text-middle text-center">교육분류</th>
			<th class="text-middle text-center">교육과정명</th>
			<th class="text-middle text-center">교육기관명</th>
            <th class="text-middle text-center">교육기간(모집기간)</th>
			<th class="text-middle text-center">교육정보</th>
			<th class="text-middle text-center">신청자수(명)/모집최대인원(명)</th>
		</tr>
		</thead>
		<tbody>
			<c:set var="table_cell_cnt" value="6"/>
			<c:if test="${empty list}">
           		<tr><td colspan="${table_cell_cnt}" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
           	</c:if>
           	<c:forEach var="item" varStatus="status" items="${list}">
		
				<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
           		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) " var="CRS_STR_DT"/>
           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH:mm" var="CRS_END_DT"/>
           		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd" var="RECRUIT_STR_DT"/>
           		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd" var="RECRUIT_END_DT"/>
           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_STR_TIME"/>
           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_END_TIME"/>
           		
			 	<tr>
					<%-- <td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td> --%>
					<td class="text-middle">
           				<c:forEach var="cate" items="${list_all_edu_grp_cd}">
           					<c:if test="${item.CRS_GRP_CD eq cate.CD_ID}">${cate.CD_NM}</c:if>
						</c:forEach>
           			</td>
					<td class="text-middle text-center">${item.CRS_NM}</td>
					<td class="text-middle text-center">${item.CAT_INS_NM}</td>
					<td class="text-middle text-center">${CRS_STR_DT}&nbsp;${CRS_STR_TIME}&nbsp;~&nbsp;${CRS_END_TIME}<br><span class="grey-500">(${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT})</span></td>
	                <td class="text-middle text-center">
	                	<span class="font-size-12">${item.CRS_PLACE}</span> 
	                	<br><span class="font-size-12 grey-500">${item.CRS_ADDR}</span> 
	                </td>
					<td class="text-middle text-center">	
	                	<span class="badge badge-warning">${item.MBR_CUR_CNT}명</span>
	                	&nbsp;/&nbsp;
	                	<span class="badge badge-info">
	                	<c:choose>
	                		<c:when test="${item.MBR_MAX_CNT eq 0}">무제한</c:when>
	                		<c:otherwise>${item.MBR_MAX_CNT}명</c:otherwise>				                	
		                </c:choose>
		                </span>
	                </td>
				</tr>
				
           	</c:forEach>
		</tbody>
		<tfoot>
			<tr class="footable-paging">
				<td colspan="${table_cell_cnt}">
					<div class="footable-pagination-wrapper">
						<ul class="pagination">
							<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_curriculum" />
							<form:form commandName="ctiCurriculumVO" id="listFormCurriculum" name="listFormCurriculum" method="post">
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
